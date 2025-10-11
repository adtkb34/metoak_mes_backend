package com.metoak.mes.common.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public String getDriverClassNameOrDefault() {
        return driverClassName == null || driverClassName.isBlank()
                ? "com.mysql.cj.jdbc.Driver"
                : driverClassName;
    }
}
