package com.metoak.mes.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mes/v1")
public class HealthController {

    @Value("${application.version}")
    private String version;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "UP");
        body.put("service", "mes-service");
        body.put("version", version);
        body.put("timestamp", Instant.now().toString());

        Map<String, Object> components = new HashMap<>();
        components.put("mysql", Map.of(
                "status", checkMysql() ? "UP" : "DOWN"
        ));

        body.put("components", components);
        return ResponseEntity.ok(body);
    }

    private boolean checkMysql() {
        try (Connection conn = dataSource.getConnection()) {
            return conn.isValid(1);
        } catch (Exception e) {
            return false;
        }
    }

}
