package com.teste.apiTeste.infra.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JSON {

    public static final ObjectMapper mapper;
    public static final ObjectMapper snakeMapper;
    private static final JsonNodeFactory nodeFactory;

    static {
        JsonFactory fac = new JsonFactory();
        // to avoid the OutputStream auto-close on a 'writeValue'
        fac.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

        mapper = new ObjectMapper(fac);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(SerializationFeature.CLOSE_CLOSEABLE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new JavaTimeModule());
        nodeFactory = mapper.getNodeFactory();

        snakeMapper = new ObjectMapper(fac);
        snakeMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE); //Camel Case resolution
        snakeMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        snakeMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        snakeMapper.disable(SerializationFeature.CLOSE_CLOSEABLE);
        snakeMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        snakeMapper.registerModule(new JavaTimeModule());
    }

    public static JsonNode valueToTree(Object data) throws Exception {
        try {
            return mapper.valueToTree(data);
        } catch (IllegalArgumentException e) {
            throw new Exception("Failed to parse object data", e);
        }
    }

    public static JsonNode readTree(String data) throws Exception {
        try {
            return mapper.readTree(data);
        } catch (IOException e) {
            throw new Exception("Failed to parse: " + data, e);
        }
    }

    public static JsonNode readTree(File file) throws Exception {
        try {
            return mapper.readTree(file);
        } catch (IOException e) {
            throw new Exception("Failed to parse data from file: " + file.getAbsolutePath(), e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new Exception("Conversion from Object to JSON failed: " + json, e);
        }
    }


    public static String toJson(Object value) throws Exception {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new Exception("Conversion from Object to JSON failed", e);
        }
    }

    public static JsonNodeFactory getNodeFactory() {
        return nodeFactory;
    }

    public static <T> T treeToObject(JsonNode tree, Class<T> clazz) throws Exception {
        try {
            return mapper.treeToValue(tree, clazz);
        } catch (IOException e) {
            throw new Exception("Failed to convert JSON to Object", e);
        }
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> T fromJson(byte[] bytea, Class<T> klass) throws Exception {
        try {
            return mapper.readValue(bytea, klass);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public static byte[] toJSONBytes(Object config) throws Exception {
        try {
            return mapper.writeValueAsBytes(config);
        } catch (JsonProcessingException e) {
            throw new Exception(e);
        }
    }

    public static String toJsonSnake(Object value) throws Exception {
        try {
            return snakeMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new Exception(e);
        }
    }

    public static <T> T fromJson(File file, Class<T> clazz) throws Exception {
        try {
            return mapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public static Map<String, Object> toMap(Object object) {
        return mapper.convertValue(object, Map.class);
    }
}


