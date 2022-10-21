package org.team.cuc.billingsystem.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.team.cuc.billingsystem.exception.CustomException;

/**
 * @author jssd
 */
public class JsonUtil {

    /**
     * 对象转json
     *
     * @return json字符串
     */
    public static <T> String objectToJson(T object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw CustomException.systemException(e);
        }
        return s;
    }

}
