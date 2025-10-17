package com.metoak.mes.common.config;

import com.metoak.mes.enums.OriginEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Component
@ConfigurationProperties(prefix = "mes.database")
public class MesDatabaseProperties {

    private Map<String, DatabaseConfig> origins = new HashMap<>();

    public Map<String, DatabaseConfig> getOrigins() {
        return origins;
    }

    public void setOrigins(Map<String, DatabaseConfig> origins) {
        this.origins = origins;
    }

    public Optional<DatabaseConfig> findByOrigin(OriginEnum origin) {
        if (origin == null || origins == null) {
            return Optional.empty();
        }
        DatabaseConfig config = origins.get(origin.name().toLowerCase(Locale.ROOT));
        if (config == null) {
            config = origins.get(origin.name());
        }
        return Optional.ofNullable(config);
    }

    public Map<OriginEnum, DatabaseConfig> asOriginMap() {
        if (origins == null || origins.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<OriginEnum, DatabaseConfig> result = new EnumMap<>(OriginEnum.class);
        for (OriginEnum origin : OriginEnum.values()) {
            findByOrigin(origin).ifPresent(config -> result.put(origin, config));
        }
        return result;
    }
}
