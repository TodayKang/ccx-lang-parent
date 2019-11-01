package com.ccx.common.lang.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * This is the util method for getting value in Properties File<br>
 * Notices are below：<br>
 * 1.all the method parameters should not be NULL or empty;<br>
 * 2.the getString(...) method has used trim;<br>
 */
@Slf4j
public class PropertiesUtils {

    /**
     * get the InputStream by file path<br>
     * it should not contain "File Path Separator Char" in path
     *
     * @param path The file path relative in classpath
     * @return InputStream
     */
    public static InputStream getResourceAsStream(@NonNull String path) {
        log.debug("getResourceAsStream path:{}", path);
        path = StringUtils.removeStart(path, "/");
        path = StringUtils.removeStart(path, "\\");
        return StringUtils.isBlank(path) ? null//
                : Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    /**
     * resolve the Properties File by file path
     *
     * @param path The file path relative in classpath
     * @return Properties Object
     */
    public static Properties load(@NonNull String path) {
        Properties properties = new Properties();
        try {
            @Cleanup
            InputStream inputStream = getResourceAsStream(path);
            Assert.notNull(inputStream, "ERROR! Will get null for InputStreamReader !");

            @Cleanup // read with UTF-8
                    InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    /**
     * @param path The file path relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Object get(@NonNull String path, @NonNull String key) {
        Properties properties = load(path);
        return properties.get(key);
    }

    /**
     * @param properties The Properties Object value
     * @param key        The key in Properties File
     * @return The value in Properties File
     */
    public static Object get(@NonNull Properties properties, @NonNull String key) {
        return properties.get(key);
    }

    /**
     * @param path The file path relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static String getProperty(@NonNull String path, @NonNull String key) {
        Properties properties = load(path);
        return properties.getProperty(key);
    }

    /**
     * @param properties The Properties Object value
     * @param key        The key in Properties File
     * @return The value in Properties File
     */
    public static String getProperty(@NonNull Properties properties, @NonNull String key) {
        return properties.getProperty(key);
    }

    /**
     * @param path The file path relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static String getString(@NonNull String path, @NonNull String key) {
        String value = getProperty(path, key);
        return StringUtils.trimToNull(value);
    }

    /**
     * @param properties The Properties Object value
     * @param key        The key in Properties File
     * @return The value in Properties File
     */
    public static String getString(@NonNull Properties properties, @NonNull String key) {
        String value = getProperty(properties, key);
        return StringUtils.trimToNull(value);
    }

    /**
     * @param path The file path relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Integer getInteger(@NonNull String path, @NonNull String key) {
        String value = getString(path, key);
        return Integer.valueOf(value);
    }

    /**
     * @param properties The Properties Object value
     * @param key        The key in Properties File
     * @return The value in Properties File
     */
    public static Integer getInteger(@NonNull Properties properties, @NonNull String key) {
        String value = getString(properties, key);
        return Integer.valueOf(value);
    }

    /**
     * @param path The file path relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Float getLong(@NonNull String path, @NonNull String key) {
        String value = getString(path, key);
        return Float.valueOf(value);
    }

    /**
     * @param properties The Properties Object value
     * @param key        The key in Properties File
     * @return The value in Properties File
     */
    public static Float getLong(@NonNull Properties properties, @NonNull String key) {
        String value = getString(properties, key);
        return Float.valueOf(value);
    }

    /**
     * @param path The file path relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Long getFloat(@NonNull String path, @NonNull String key) {
        String value = getString(path, key);
        return Long.valueOf(value);
    }

    /**
     * @param properties The Properties Object value
     * @param key        The key in Properties File
     * @return The value in Properties File
     */
    public static Long getFloat(@NonNull Properties properties, @NonNull String key) {
        String value = getString(properties, key);
        return Long.valueOf(value);
    }

    /**
     * @param path The file path relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Double getDouble(@NonNull String path, @NonNull String key) {
        String value = getString(path, key);
        return Double.valueOf(value);
    }

    /**
     * @param properties The Properties Object value
     * @param key        The key in Properties File
     * @return The value in Properties File
     */
    public static Double getDouble(@NonNull Properties properties, @NonNull String key) {
        String value = getString(properties, key);
        return Double.valueOf(value);
    }

    /**
     * @param path The file path relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Boolean getBoolean(@NonNull String path, @NonNull String key) {
        String value = getString(path, key);
        return Boolean.valueOf(value);
    }

    /**
     * @param properties The Properties Object value
     * @param key        The key in Properties File
     * @return The value in Properties File
     */
    public static Boolean getBoolean(@NonNull Properties properties, @NonNull String key) {
        String value = getString(properties, key);
        return Boolean.valueOf(value);
    }

}
