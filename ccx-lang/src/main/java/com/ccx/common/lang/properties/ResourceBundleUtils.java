package com.ccx.common.lang.properties;

import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import lombok.NonNull;

public class ResourceBundleUtils {

    /**
     * @param path The file path(resource bundle using the specified base name) relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static ResourceBundle getBundle(@NonNull String path, @NonNull String key) {
        return ResourceBundle.getBundle(path);
    }

    /**
     * @param path The file path(resource bundle using the specified base name) relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Object get(@NonNull String path, @NonNull String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(path);
        return resourceBundle.getObject(key);
    }

    /**
     * @param resourceBundle Resource bundles contain locale-specific objects
     * @param key            The key in Properties File
     * @return The value in Properties File
     */
    public static Object get(@NonNull ResourceBundle resourceBundle, @NonNull String key) {
        return resourceBundle.getObject(key);
    }

    /**
     * @param path The file path(resource bundle using the specified base name) relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static String getProperty(@NonNull String path, @NonNull String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(path);
        return resourceBundle.getString(key);
    }

    /**
     * @param resourceBundle Resource bundles contain locale-specific objects
     * @param key            The key in Properties File
     * @return The value in Properties File
     */
    public static String getProperty(@NonNull ResourceBundle resourceBundle, @NonNull String key) {
        return resourceBundle.getString(key);
    }

    /**
     * @param path The file path(resource bundle using the specified base name) relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static String getString(@NonNull String path, @NonNull String key) {
        String value = getProperty(path, key);
        return StringUtils.trimToNull(value);
    }

    /**
     * @param resourceBundle Resource bundles contain locale-specific objects
     * @param key            The key in Properties File
     * @return The value in Properties File
     */
    public static String getString(@NonNull ResourceBundle resourceBundle, @NonNull String key) {
        String value = getProperty(resourceBundle, key);
        return StringUtils.trimToNull(value);
    }

    /**
     * @param path The file path(resource bundle using the specified base name) relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Integer getInteger(@NonNull String path, @NonNull String key) {
        String value = getString(path, key);
        return Integer.valueOf(value);
    }

    /**
     * @param resourceBundle Resource bundles contain locale-specific objects
     * @param key            The key in Properties File
     * @return The value in Properties File
     */
    public static Integer getInteger(@NonNull ResourceBundle resourceBundle, @NonNull String key) {
        String value = getString(resourceBundle, key);
        return Integer.valueOf(value);
    }

    /**
     * @param path The file path(resource bundle using the specified base name) relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Long getLong(@NonNull String path, @NonNull String key) {
        String value = getString(path, key);
        return Long.valueOf(value);
    }

    /**
     * @param resourceBundle Resource bundles contain locale-specific objects
     * @param key            The key in Properties File
     * @return The value in Properties File
     */
    public static Long getLong(@NonNull ResourceBundle resourceBundle, @NonNull String key) {
        String value = getString(resourceBundle, key);
        return Long.valueOf(value);
    }

    /**
     * @param path The file path(resource bundle using the specified base name) relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Float getFloat(@NonNull String path, @NonNull String key) {
        String value = getString(path, key);
        return Float.valueOf(value);
    }

    /**
     * @param resourceBundle Resource bundles contain locale-specific objects
     * @param key            The key in Properties File
     * @return The value in Properties File
     */
    public static Float getFloat(@NonNull ResourceBundle resourceBundle, @NonNull String key) {
        String value = getString(resourceBundle, key);
        return Float.valueOf(value);
    }

    /**
     * @param path The file path(resource bundle using the specified base name) relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Double getDouble(@NonNull String path, @NonNull String key) {
        String value = getString(path, key);
        return Double.valueOf(value);
    }

    /**
     * @param resourceBundle Resource bundles contain locale-specific objects
     * @param key            The key in Properties File
     * @return The value in Properties File
     */
    public static Double getDouble(@NonNull ResourceBundle resourceBundle, @NonNull String key) {
        String value = getString(resourceBundle, key);
        return Double.valueOf(value);
    }

    /**
     * @param path The file path(resource bundle using the specified base name) relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static Boolean getBoolean(@NonNull String path, @NonNull String key) {
        String value = getString(path, key);
        return Boolean.valueOf(value);
    }

    /**
     * @param resourceBundle Resource bundles contain locale-specific objects
     * @param key            The key in Properties File
     * @return The value in Properties File
     */
    public static Boolean getBoolean(@NonNull ResourceBundle resourceBundle, @NonNull String key) {
        String value = getString(resourceBundle, key);
        return Boolean.valueOf(value);
    }

    /**
     * @param path The file path(resource bundle using the specified base name) relative in classpath
     * @param key  The key in Properties File
     * @return The value in Properties File
     */
    public static String[] getStringArray(@NonNull String path, @NonNull String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(path);
        return resourceBundle.getStringArray(key);
    }

}
