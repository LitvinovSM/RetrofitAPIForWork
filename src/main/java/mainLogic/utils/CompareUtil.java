package mainLogic.utils;

import io.cucumber.datatable.DataTable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CompareUtil {

    private static final String PARAMETER_NAME = "НАЗВАНИЕ ПАРАМЕТРА";
    private static final String CRITERIA = "УСЛОВИЕ СРАВНЕНИЯ";
    private static final String EXPECTED_VALUE = "ОЖИДАЕМОЕ ЗНАЧЕНИЕ";
    private static final String DATA_TYPE = "ТИП ДАННЫХ";

    private static final String EQUAL = "равно";
    private static final String NOT_EQUAL = "не равно";
    private static final String CONTAINS = "содержит";
    private static final String NOT_CONTAINS = "не содержит";

    private static final String NULLABLE = "нуллабельное";
    private static final String NOT_NULLABLE = "не нуллабельное";
    private static final List<String> validStringCriteriaOfComparison = List.of(EQUAL,NOT_EQUAL,CONTAINS,NOT_CONTAINS,NULLABLE,NOT_NULLABLE);

    private static final Map<String, ?> DATA_TYPE_MAP = Map.ofEntries(
            Map.entry("String", String.class),
            Map.entry("Integer", Integer.class),
            Map.entry("Double", Double.class)
    );

    private static final Map<String, String> fieldsToMethodsMap = Map.of("id", "getId");

    /**
     * Получение метода по названию из какого либо класса
     *
     * @param clazz      целевой класс
     * @param methodName имя метода
     */
    public static <T> Method getMethodByName(Class<T> clazz, String methodName) throws NoSuchMethodException {
        return clazz.getMethod(methodName);
    }

    /**
     * Вызов метода по названию из какого либо класса
     *
     * @param clazz        целевой класс
     * @param methodName   имя метода
     * @param targetObject объект, над которым вызывается целевой метод
     */
    public static <T> Object invokeMethodByName(Class<T> clazz, Object targetObject, String methodName) {
        try {
            return getMethodByName(clazz, methodName).invoke(targetObject);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    void comparatorFromStandardTableAsMap(DataTable dataTable) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> mapItem : table) {
            String nameOfParam = mapItem.get(PARAMETER_NAME);
            String comparison = mapItem.get(CRITERIA);
            String expectedValue = mapItem.get(EXPECTED_VALUE);
            String dataType = mapItem.get(DATA_TYPE);
        }
    }

    <T> void comparator(Class<T> clazz, Object targetObject, String paramName, String criteria, String expectedValue) {
        String methodName = fieldsToMethodsMap.get(paramName);
        Object resultedObject = invokeMethodByName(clazz, targetObject, methodName);
        if (criteria.equals(EQUAL)) {
            Object object = invokeMethodByName(clazz, targetObject, methodName);
            assertEquals(object, expectedValue);
        } else if (criteria.equals(NOT_EQUAL)) {
            Object object = invokeMethodByName(clazz, targetObject, methodName);
            assertNotEquals(object, expectedValue);
        }
    }

    /**
     * Сравниватель для типа стринг
     *
     * @param resultedObject - объект, который получается в результате вызова строк выше
     *                       String methodName = fieldsToMethodsMap.get(paramName);
     *                       Object resultedObject = invokeMethodByName(clazz,targetObject,methodName);
     */
    private void comparatorForStringType(Object resultedObject, String criteria, String expectedValue) {
        String resultedStringValue = castObjectToString(resultedObject);
        if (criteria.equalsIgnoreCase(EQUAL)) {
            assertEquals(resultedStringValue, expectedValue, String.format("Ожидалось что итоговое значение: \n\r %s \n\r соответствует ожидаемому значению: \n\r %s ", resultedStringValue, expectedValue));
        } else if (criteria.equalsIgnoreCase(NOT_EQUAL)) {
            assertNotEquals(resultedStringValue, expectedValue, String.format("Ожидалось что итоговое значение: \n\r %s \n\r не соответствует ожидаемому значению: \n\r %s ", resultedStringValue, expectedValue));
        } else if (criteria.equalsIgnoreCase(CONTAINS)) {
            assertTrue(resultedStringValue.contains(expectedValue), String.format("Ожидалось что итоговое значение: \n\r %s \n\r содержит ожидаемое значение: \n\r %s ", resultedStringValue, expectedValue));
        } else if (criteria.equalsIgnoreCase(NOT_CONTAINS)) {
            assertFalse(resultedStringValue.contains(expectedValue), String.format("Ожидалось что итоговое значение: \n\r %s \n\r не содержит ожидаемое значение: \n\r %s ", resultedStringValue, expectedValue));
        } else if (criteria.equalsIgnoreCase(NULLABLE)) {
            assertNull(resultedStringValue, String.format("Ожидалось что итоговое значение: \n\r %s \n\r нуллабельное, но оно: \n\r %s ", resultedStringValue, expectedValue));
        } else if (criteria.equalsIgnoreCase(NOT_NULLABLE)) {
            assertNotNull(resultedStringValue, String.format("Ожидалось что итоговое значение: \n\r %s \n\r не нуллабельное, но оно: \n\r %s ", resultedStringValue, expectedValue));
        }
    }

    String castObjectToString(Object targetObject) {
        return targetObject.toString();
    }

    int castObjectToInt(Object targetObject) {
        return Integer.parseInt(targetObject.toString());
    }

    double castObjectToDouble(Object targetObject) {
        return Double.parseDouble(targetObject.toString());
    }
}
