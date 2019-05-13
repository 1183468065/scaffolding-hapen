package com.example.example.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = ObjectMapperHolder.getObjectMapper();

    public static <T> T fromJsonQuietly(String json, Class<T> type) {
        try {
            return fromJson(json, type);
        } catch (Exception e) {
            logger.error("fromJson failed: {}", e.getMessage(), e);
        }
        return null;
    }

    public static <T> T fromJson(String json, Class<T> type) {
        if (type == null || StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> fromListJson(String json, Class<T> type) {
        if (type == null || json == null) {
            return null;
        }

        JavaType javaType = getCollectionType(ArrayList.class, type);
        List<T> lst;
        try {
            lst = (List<T>) mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> fromMapJson(String json, Class<T> type) {
        if (type == null || json == null) {
            return null;
        }

        JavaType javaType = getCollectionType(HashMap.class, String.class, type);
        Map<String, T> map;
        try {
            map = (Map<String, T>) mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public static String toJsonQuietly(Object obj) {
        try {
            return toJson(obj);
        } catch (Exception e) {
            logger.error("toJson failed: {}", e.getMessage(), e);
        }
        return null;
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            String json = mapper.writeValueAsString(obj);
            return json;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class ObjectMapperHolder {
        public static ObjectMapper getObjectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(MapperFeature.USE_ANNOTATIONS);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            mapper.setSerializationInclusion(Include.NON_NULL);
            return mapper;
        }
    }
}
