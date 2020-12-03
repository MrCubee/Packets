package fr.mrcubee.util;

import com.mysql.jdbc.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection {

    public static Object executeStaticMethod(Class<?> clazz, Object instance, String methodName, Class<?>[] parameters, Object... objects) {
        Method method;
        Object result = null;

        if (methodName == null || parameters == null || objects == null)
            return null;
        try {
            method = clazz.getMethod(methodName, parameters);
            method.setAccessible(true);
            result = method.invoke(instance, objects);
        } catch (Exception ignored) {}
        return result;
    }

    public static Object executeStaticMethod(Object instance, String methodName, Class<?>[] parameters, Object... objects) {
        return executeMethod(instance.getClass(), instance, methodName, parameters, objects);
    }

    public static Object executeMethod(Class<?> clazz, Object instance, String methodName, Class<?>[] parameters, Object... objects) {
        Method method;
        Object result = null;

        if (methodName == null || parameters == null || objects == null)
            return null;
        try {
            method = clazz.getDeclaredMethod(methodName, parameters);
            method.setAccessible(true);
            result = method.invoke(instance, objects);
        } catch (Exception ignored) {}
        return result;
    }

    public static Object executeMethod(Object instance, String methodName, Class<?>[] parameters, Object... objects) {
        return executeMethod(instance.getClass(), instance, methodName, parameters, objects);
    }

    public static boolean setValue(Class<?> clazz, Object instance, String fieldName, Object value) {
        Field field;

        if (clazz == null || instance == null || StringUtils.isEmptyOrWhitespaceOnly(fieldName))
            return false;
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
            return true;
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
        return false;
    }

    public static boolean setValue(Object instance, String fieldName, Object value) {
        return setValue(instance.getClass(), instance, fieldName, value);
    }

    public static Object getValue(Class<?> clazz, Object instance, String fieldName) {
        Field field;
        Object result = null;

        if (clazz == null || instance == null || StringUtils.isEmptyOrWhitespaceOnly(fieldName))
            return null;
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            result = field.get(instance);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
        return result;
    }

    public static Object getValue(Object instance, String fieldName) {
        return getValue(instance.getClass(), instance, fieldName);
    }
}
