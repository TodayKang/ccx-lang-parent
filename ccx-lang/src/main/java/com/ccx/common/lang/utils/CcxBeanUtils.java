package com.ccx.common.lang.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class CcxBeanUtils {

	/**
	 * 使用 org.apache.commons.beanutils.BeanUtils 将Map转换为对象
	 *
	 * @param <T>   对象
	 * @param map   源Map对象
	 * @param clazz 目标类
	 * @return 目标类对象
	 * @throws Exception 异常
	 */
	public static <T> T toObjectByApache(Map<String, ? extends Object> map, Class<T> clazz) throws Exception {
		Assert.notNull(clazz, "target Class should be not null !");
		if (MapUtils.isEmpty(map)) {
			return null;
		}

		T object = clazz.newInstance();
		BeanUtils.populate(object, map);
		return object;
	}

	/**
	 * 使用 reflect（反射） 将Map转换为对象
	 *
	 * @param <T>   对象
	 * @param map   源Map对象
	 * @param clazz 目标类
	 * @return 目标类对象
	 * @throws Exception 异常
	 */
	public static <T> T toObjectByReflect(Map<String, ? extends Object> map, Class<T> clazz) throws Exception {
		Assert.notNull(clazz, "target Class should be not null !");
		if (MapUtils.isEmpty(map)) {
			return null;
		}

		T object = clazz.newInstance();
		Field[] fields = object.getClass().getFields();
		for (Field field : fields) {
			int modifiers = field.getModifiers();
			if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
				continue;
			}
			field.setAccessible(true);
			field.set(object, map.get(field.getName()));
		}

		return object;
	}

	/**
	 * 使用 reflect使用 Introspector 将Map转换为对象
	 *
	 * @param <T>   对象
	 * @param map   源Map对象
	 * @param clazz 目标类
	 * @return 目标类对象
	 * @throws Exception 异常
	 */
	public static <T> T toObjectByIntrospector(Map<String, ? extends Object> map, Class<T> clazz) throws Exception {
		Assert.notNull(clazz, "target Class should be not null !");
		if (MapUtils.isEmpty(map)) {
			return null;
		}

		T object = clazz.newInstance();
		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			// 获取 setter Method
			Method method = propertyDescriptor.getWriteMethod();
			if (method != null) {
				method.invoke(object, map.get(propertyDescriptor.getName()));
			}
		}

		return object;
	}

	/**
	 * 使用 org.apache.commons.beanutils.PropertyUtils 将对象转换成Map
	 *
	 * @param object 源对象
	 * @return 目标Map
	 * @throws Exception 异常
	 */
	public static Map<String, Object> toMapByApache(Object object) throws Exception {
		if (object == null) {
			return new HashMap<>();
		}

		return PropertyUtils.describe(object);
	}

	/**
	 * 使用 Introspector 将对象转换成Map
	 *
	 * @param object 源对象
	 * @return 目标Map
	 * @throws Exception 异常
	 */
	public static Map<String, Object> toMapByIntrospector(Object object) throws Exception {
		if (object == null) {
			return new HashMap<>();
		}

		Map<String, Object> map = new HashMap<>();
		Class<? extends Object> clazz = object.getClass();
		while (clazz != null) {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				// 获取 getter Method
				Method getterMethod = propertyDescriptor.getReadMethod();
				if (getterMethod != null) {
					String key = propertyDescriptor.getName();
					Object value = getterMethod.invoke(object);
					map.put(key, value);
				}
			}

			// 遍历父类
			clazz = clazz.getSuperclass();
		}

		return map;
	}

	/**
	 * 使用 reflect（反射） 将对象转换成Map
	 *
	 * @param object 源对象
	 * @return 目标Map
	 * @throws Exception 异常
	 */
	public static Map<String, Object> toMapByReflect(Object object) throws Exception {
		if (object == null) {
			return new HashMap<>();
		}

		Map<String, Object> map = new HashMap<>();
		Class<? extends Object> clazz = object.getClass();
		while (clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String key = field.getName();
				Object value = field.get(object);

				if (StringUtils.equalsIgnoreCase("serialVersionUID", key) || StringUtils.isBlank(key)) {
					continue;
				}
				map.put(key, value);
			}

			// 遍历父类
			clazz = clazz.getSuperclass();
		}

		return map;
	}

}
