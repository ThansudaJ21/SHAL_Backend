package com.se.shal.line.config;

import lombok.Data;

@Data
public class LineMenuIdSingleton {
    private static LineMenuIdSingleton lineMenuIdSingleton;
    String homeId;

    public static LineMenuIdSingleton getInstance() {
        if (lineMenuIdSingleton == null) {
            lineMenuIdSingleton = new LineMenuIdSingleton();
        }
        return lineMenuIdSingleton;
    }
}
