package mainLogic.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CompareUtil {

    /**Получение метода по названию из какого либо класса
     * @param clazz целевой класс
     * @param methodName имя метода*/
    public static <T> Method getMethodByName(Class<T> clazz, String methodName) throws NoSuchMethodException{
        return clazz.getMethod(methodName);
    }

    /**Вызов метода по названию из какого либо класса
     * @param clazz целевой класс
     * @param methodName имя метода
     * @param targetObject объект,над которым вызывается целевой метод*/
    public static <T> Object invokeMethodByName(Class<T> clazz, Object targetObject,String methodName){
        try {
            return getMethodByName(clazz,methodName).invoke(targetObject);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
