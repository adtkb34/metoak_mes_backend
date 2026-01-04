package com.metoak.mes.service;

import com.metoak.mes.common.MOException;
import com.metoak.mes.common.config.DatabaseConfig;
import com.metoak.mes.common.config.MesDatabaseProperties;
import com.metoak.mes.entity.MoCalibration;
import com.metoak.mes.enums.OriginEnum;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.enums.StepMappingEnum;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessStepLatestResultService {

    private final MesDatabaseProperties databaseProperties;

    public Optional<Boolean> findLatestSuccess(String productSn, String stepTypeNo) {
        if (!StringUtils.hasText(productSn)) {
            return Optional.empty();
        }
        String normalizedSn = productSn.trim();
        StepDefinition definition = StepDefinition.fromCode(stepTypeNo)
                .orElseThrow(() -> new MOException(ResultCodeEnum.PRO_PROCESS_TYPE_NO_NOT_FOUND));

        Map<OriginEnum, DatabaseConfig> originConfigMap = databaseProperties.asOriginMap();
        if (originConfigMap.isEmpty()) {
            log.warn("No database configurations available for process step lookup");
            return Optional.empty();
        }

        for (OriginEnum origin : OriginEnum.values()) {
            DatabaseConfig config = originConfigMap.get(origin);
            if (config == null) {
                continue;
            }
            Optional<ProcessRecord> record = queryLatestRecord(config, normalizedSn, definition);
            if (record.isPresent()) {
                return Optional.of(record.get().success());
            }
        }
        return Optional.empty();
    }

    private Optional<ProcessRecord> queryLatestRecord(DatabaseConfig config, String productSn, StepDefinition definition) {
        try (HikariDataSource dataSource = buildDataSource(config)) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            Set<String> candidates = resolveSerialCandidates(jdbcTemplate, productSn);
            if (CollectionUtils.isEmpty(candidates)) {
                return Optional.empty();
            }
            ProcessRecord latest = null;
            for (String candidate : candidates) {
                ProcessRecord record = definition.fetchLatest(jdbcTemplate, candidate);
                if (record == null) {
                    continue;
                }
                if (latest == null || record.isAfter(latest)) {
                    latest = record;
                }
            }
            return Optional.ofNullable(latest);
        } catch (Exception ex) {
            log.error("Failed to query latest process result", ex);
            return Optional.empty();
        }
    }

    private Set<String> resolveSerialCandidates(JdbcTemplate jdbcTemplate, String productSn) {
        LinkedHashSet<String> candidates = new LinkedHashSet<>();
        if (StringUtils.hasText(productSn)) {
            candidates.add(productSn.trim());
        }
        String sql = "SELECT camera_sn, shell_sn FROM mo_tag_shell_info WHERE camera_sn = ? OR shell_sn = ? ORDER BY id DESC LIMIT 10";
        jdbcTemplate.query(sql, ps -> {
            ps.setString(1, productSn);
            ps.setString(2, productSn);
        }, rs -> {
            while (rs.next()) {
                String camera = rs.getString("camera_sn");
                if (StringUtils.hasText(camera)) {
                    candidates.add(camera.trim());
                }
                String shell = rs.getString("shell_sn");
                if (StringUtils.hasText(shell)) {
                    candidates.add(shell.trim());
                }
            }
            return null;
        });
        return candidates;
    }

    private HikariDataSource buildDataSource(DatabaseConfig config) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setDriverClassName(config.getDriverClassNameOrDefault());
        hikariConfig.setMaximumPoolSize(2);
        hikariConfig.setMinimumIdle(0);
        hikariConfig.setPoolName("dynamic-process-step-latest");
        hikariConfig.setAutoCommit(true);
        hikariConfig.setConnectionTimeout(10_000L);
        return new HikariDataSource(hikariConfig);
    }

    private enum StepDefinition {
        DUAL_TARGET_CALIB(StepMappingEnum.DUAL_TARGET_CALIB) {
            @Override
            ProcessRecord fetchLatest(JdbcTemplate jdbcTemplate, String sn) {
                String sql = "SELECT  id, error_code, start_time, end_time FROM mo_calibration WHERE camera_sn = ?  ORDER BY start_time DESC, id DESC LIMIT 1";
                System.out.println(sql);
                return jdbcTemplate.query(
                        sql,
                        (PreparedStatementSetter) ps -> ps.setString(1, sn),
                        (ResultSetExtractor<ProcessRecord>) rs -> mapCalibration(rs)
                );

            }
        },
        AUTO_ADJUST(StepMappingEnum.AUTO_ADJUST) {
            @Override
            ProcessRecord fetchLatest(JdbcTemplate jdbcTemplate, String sn) {
                String sql = "SELECT id, add_time, error_code FROM mo_auto_adjust_info WHERE beam_sn = ? ORDER BY add_time DESC, id DESC LIMIT 1";
                System.out.println(sql);
                return jdbcTemplate.query(
                        sql,
                        (PreparedStatementSetter) ps -> ps.setString(1, sn),
                        (ResultSetExtractor<ProcessRecord>) rs -> mapAutoAdjust(rs)
                );

            }
        },
        S315_FINAL_CHECK(StepMappingEnum.S315_FINAL_CHECK) {
            @Override
            ProcessRecord fetchLatest(JdbcTemplate jdbcTemplate, String sn) {
                String sql = "SELECT id, check_result, check_time  FROM mo_final_result WHERE camera_sn = ? ORDER BY check_time DESC, id DESC LIMIT 1";
                System.out.println(sql);
                return jdbcTemplate.query(
                        sql,
                        (PreparedStatementSetter) ps -> ps.setString(1, sn),
                        (ResultSetExtractor<ProcessRecord>) rs -> mapFinalResult(rs)
                );
            }
        };

        private final String code;

        StepDefinition(StepMappingEnum mapping) {
            this.code = mapping.getCode();
        }

        static Optional<StepDefinition> fromCode(String code) {
            for (StepDefinition definition : values()) {
                if (definition.code.equals(code)) {
                    return Optional.of(definition);
                }
            }
            return Optional.empty();
        }

        abstract ProcessRecord fetchLatest(JdbcTemplate jdbcTemplate, String sn);

        private static ProcessRecord mapCalibration(ResultSet rs) throws SQLException {
            if (!rs.next()) {
                return null;
            }
            Integer errorCode = (Integer) rs.getObject("error_code");
            LocalDateTime timestamp = extractTimestamp(rs.getTimestamp("end_time"), rs.getTimestamp("start_time"));
            long id = rs.getLong("id");
            boolean success = errorCode != null && errorCode == 0;
            return new ProcessRecord(success, timestamp, id);
        }

        private static ProcessRecord mapAutoAdjust(ResultSet rs) throws SQLException {
            if (!rs.next()) {
                return null;
            }
            Integer errorCode = (Integer) rs.getObject("error_code");
            LocalDateTime timestamp = extractTimestamp(rs.getTimestamp("add_time"));
            long id = rs.getLong("id");
            boolean success = errorCode != null && errorCode == 0;
            return new ProcessRecord(success, timestamp, id);
        }

        private static ProcessRecord mapFinalResult(ResultSet rs) throws SQLException {
            if (!rs.next()) {
                return null;
            }
            Boolean checkResult = (Boolean) rs.getObject("check_result");
            LocalDateTime timestamp = extractTimestamp(rs.getTimestamp("check_time"));
            long id = rs.getLong("id");
            boolean success = Boolean.TRUE.equals(checkResult);
            return new ProcessRecord(success, timestamp, id);
        }

        private static LocalDateTime extractTimestamp(Timestamp primary) {
            return primary == null ? null : primary.toLocalDateTime();
        }

        private static LocalDateTime extractTimestamp(Timestamp primary, Timestamp secondary) {
            LocalDateTime time = extractTimestamp(primary);
            if (time != null) {
                return time;
            }
            return extractTimestamp(secondary);
        }
    }

    private record ProcessRecord(boolean success, LocalDateTime timestamp, long id) {
        boolean isAfter(ProcessRecord other) {
            if (other == null) {
                return true;
            }
            if (timestamp != null && other.timestamp != null) {
                int compare = timestamp.compareTo(other.timestamp);
                if (compare != 0) {
                    return compare > 0;
                }
            } else if (timestamp != null) {
                return true;
            } else if (other.timestamp != null) {
                return false;
            }
            return id > other.id;
        }
    }
}
