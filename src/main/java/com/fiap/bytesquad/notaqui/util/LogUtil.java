package com.fiap.bytesquad.notaqui.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LogUtil {

    public static String logJson(Object json) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(json);
        } catch (Exception e) {
            return "ERRO!: " + e.getMessage();
        }
    }
}
