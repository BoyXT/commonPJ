package com.ytw.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public final class JsonUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil()
    {
    }

    public static String object2String(Object obj)
    {
        try
        {
            return objectMapper.writeValueAsString(obj);
        }
        catch (IOException ex)
        {
            LOGGER.error("Object2String occur a exception : {}", ex.getMessage());
            return null;
        }
    }

    public static <T> T string2Bean(String str, Class<T> clazz)
    {
        try
        {
            return objectMapper.readValue(str, clazz);
        }
        catch (IOException ex)
        {
            LOGGER.error("String2Bean occur a exception : {}", ex.getMessage());
            return null;
        }
    }

    public static <T> T file2Bean(File file, Class<T> clazz)
    {
        try
        {
            return objectMapper.readValue(file, clazz);
        }
        catch (IOException ex)
        {
            LOGGER.error("File2Bean occur a exception : {}", ex.getMessage());
            return null;
        }
    }
}
