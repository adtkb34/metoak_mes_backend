package com.metoak.mes.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParser {
    public static Properties main() {
        Properties properties = new Properties();
        try (InputStream is = PropertiesParser.class.getClassLoader().getResourceAsStream("step-mapping.properties")) {
            if (is == null) {
                throw new IOException("step-mapping.properties not found in resources");
            }
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return properties;

    }
}