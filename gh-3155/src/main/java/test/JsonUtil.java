package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dushuyu
 * @date 2024/11/18 16:33
 * @description
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换为 JSON 字符串
     *
     * @param obj 对象
     * @return JSON 字符串
     * @throws JsonProcessingException 如果转换失败
     */
    public static String toJsonString(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * 将 JSON 字符串转换为对象
     *
     * @param jsonStr JSON 字符串
     * @param clazz 目标对象类型
     * @param <T> 泛型类型
     * @return 转换后的对象
     * @throws JsonProcessingException 如果转换失败
     */
    public static <T> T fromJsonString(String jsonStr, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(jsonStr, clazz);
    }
}
