package mainLogic.utils;

import io.cucumber.datatable.DataTable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CompareUtil {

    /* Заголовки DataTable */
    private static final String PARAMETER_NAME = "НАЗВАНИЕ ПАРАМЕТРА";
    private static final String CRITERIA = "УСЛОВИЕ СРАВНЕНИЯ";
    private static final String EXPECTED_VALUE = "ОЖИДАЕМОЕ ЗНАЧЕНИЕ";
    private static final String DATA_TYPE = "ТИП ДАННЫХ";

    /* Условия сравнения */
    private static final String EQUAL = "равно";
    private static final String NOT_EQUAL = "не равно";
    private static final String CONTAINS = "содержит";
    private static final String NOT_CONTAINS = "не содержит";
    private static final String NULLABLE = "нуллабельное";
    private static final String NOT_NULLABLE = "не нуллабельное";
    private static final String MORE = "больше";
    private static final String LESS = "меньше";

    /* Типы данных */
    private static final String STRING_TYPE = "String";
    private static final String INT_TYPE = "Int";
    private static final String DOUBLE_TYPE = "Double";


    /*Валидные критерии для сравнения по типам данных */
    private static final List<String> VALID_STRING_CRITERIA_OF_COMPARISON = List.of(EQUAL,NOT_EQUAL,CONTAINS,NOT_CONTAINS,NULLABLE,NOT_NULLABLE);
    private static final List<String> VALID_INT_CRITERIA_OF_COMPARISON = List.of(EQUAL,NOT_EQUAL,MORE,LESS);
    private static final List<String> VALID_DOUBLE_CRITERIA_OF_COMPARISON = List.of(EQUAL,NOT_EQUAL,MORE,LESS);

    /*Валидные типы данных*/
    private static final List<String> VALID_DATA_TYPES = List.of(STRING_TYPE,INT_TYPE,DOUBLE_TYPE);

    /**
     * Получение метода по названию из какого либо объекта
     * @param targetObject целевой объект какого то типа
     * @param methodName имя метода
     */
    private static <T> Method getMethodByName(T targetObject, String methodName) throws NoSuchMethodException {
        Class<?> clazz = targetObject.getClass();
        return clazz.getMethod(methodName);
    }

    /**
     * Вызов метода по названию из какого либо класса
     *
     * @param methodName   имя метода
     * @param targetObject объект, над которым вызывается целевой метод
     */
    private static <T> Object invokeMethodByName(T targetObject, String methodName) {
        try {
            return getMethodByName(targetObject, methodName).invoke(targetObject);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    /** Применение подходов сравнения над таблицей из feature файла
     * @param dataTable целевая таблица
     * @param targetObject объект в котором мы ожидаем найти все поля и будем их сравнивать
     * @param fieldsToMethodsMap ключ-карта названия полей целевого объекта, на методы для получения этих полей
     * */
    public static <T> void useComparatorOnDataTable(DataTable dataTable, T targetObject, Map<String, String> fieldsToMethodsMap) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> mapItem : table) {
            String parameterName = mapItem.get(PARAMETER_NAME);
            String criteria = mapItem.get(CRITERIA);
            String expectedValue = mapItem.get(EXPECTED_VALUE);
            String dataType = mapItem.get(DATA_TYPE);
            String methodName = fieldsToMethodsMap.get(parameterName);
            Object resultedObject = invokeMethodByName(targetObject, methodName);
            chooseComparatorByDataType(resultedObject,criteria,expectedValue,dataType);
        }
    }

    /** Вызов метода сравнения в зависимости от типа данных. Для пустого типа данных используется метод сравнения для String
     * @param resultedObject объект, который содержит примитивный тип данных
     * @param criteria условие сравнения
     * @param expectedValue ожидаемое целевое значение
     * @param dataType тип данных*/
    private static void chooseComparatorByDataType(Object resultedObject, String criteria, String expectedValue, String dataType) {
        if(dataType==null){
            comparatorForStringType(resultedObject,criteria,expectedValue);
        } else if (dataType.equalsIgnoreCase(INT_TYPE)) {
            comparatorForIntType(resultedObject,criteria,expectedValue);
        } else if (dataType.equalsIgnoreCase(DOUBLE_TYPE)) {
            comparatorForDoubleType(resultedObject,criteria,expectedValue);
        } else if (dataType.equalsIgnoreCase(STRING_TYPE)){
            comparatorForStringType(resultedObject,criteria,expectedValue);
        }
    }

    /**
     * Метод сравнения для типа String
     * @param resultedObject - объект, который содержит примитивный тип данных
     */
    private static void comparatorForStringType(Object resultedObject, String criteria, String expectedValue) {
        assertTrue(VALID_STRING_CRITERIA_OF_COMPARISON.contains(criteria),String.format("Указанный критерий сравнения для типа %s (или для дефолтного типа): %s не входит в список валидных критериев сравнения для этих типов данных: \n\r %s",STRING_TYPE,criteria, VALID_STRING_CRITERIA_OF_COMPARISON));
        String resultedStringValue = castObjectToString(resultedObject);
        if (criteria.equalsIgnoreCase(EQUAL)) {
            assertEquals(resultedStringValue, expectedValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r соответствует ожидаемому значению: \n\r %s ", resultedStringValue, expectedValue));
        } else if (criteria.equalsIgnoreCase(NOT_EQUAL)) {
            assertNotEquals(resultedStringValue, expectedValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r не соответствует ожидаемому значению: \n\r %s ", resultedStringValue, expectedValue));
        } else if (criteria.equalsIgnoreCase(CONTAINS)) {
            assertTrue(resultedStringValue.contains(expectedValue), String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r содержит ожидаемое значение: \n\r %s ", resultedStringValue, expectedValue));
        } else if (criteria.equalsIgnoreCase(NOT_CONTAINS)) {
            assertFalse(resultedStringValue.contains(expectedValue), String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r не содержит ожидаемое значение: \n\r %s ", resultedStringValue, expectedValue));
        } else if (criteria.equalsIgnoreCase(NULLABLE)) {
            assertNull(resultedStringValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r нуллабельное, но оно: \n\r %s ", resultedStringValue, expectedValue));
        } else if (criteria.equalsIgnoreCase(NOT_NULLABLE)) {
            assertNotNull(resultedStringValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r не нуллабельное, но оно: \n\r %s ", resultedStringValue, expectedValue));
        }
    }

    /**
     * Метод сравнения для типа int
     * @param resultedObject - объект, который содержит примитивный тип данных
     */
    private static void comparatorForIntType(Object resultedObject, String criteria, String expectedValue) {
        assertTrue(VALID_INT_CRITERIA_OF_COMPARISON.contains(criteria),String.format("Указанный критерий сравнения для типа %s: %s не входит в список валидных критериев сравнения для этих типов данных: \n\r %s",INT_TYPE,criteria, VALID_STRING_CRITERIA_OF_COMPARISON));
        int resultedIntValue = castObjectToInt(resultedObject);
        int expectedIntValue = castObjectToInt(expectedValue);
        if (criteria.equalsIgnoreCase(EQUAL)) {
            assertEquals(resultedIntValue, expectedIntValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r равно ожидаемому значению: \n\r %s ", resultedIntValue, expectedIntValue));
        } else if (criteria.equalsIgnoreCase(NOT_EQUAL)) {
            assertNotEquals(resultedIntValue, expectedIntValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r не равно ожидаемому значению: \n\r %s ", resultedIntValue, expectedIntValue));
        } else if (criteria.equalsIgnoreCase(MORE)) {
            assertTrue(resultedIntValue>expectedIntValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r больше ожидаемого значения: \n\r %s ", resultedIntValue, expectedIntValue));
        } else if (criteria.equalsIgnoreCase(LESS)) {
            assertTrue(resultedIntValue<expectedIntValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r меньше ожидаемого значения: \n\r %s ", resultedIntValue, expectedIntValue));
        }
    }

    /**
     * Сравниватель для типа дабл
     * @param resultedObject - объект, который содержит примитивный тип данных
     */
    private static void comparatorForDoubleType(Object resultedObject, String criteria, String expectedValue) {
        assertTrue(VALID_DOUBLE_CRITERIA_OF_COMPARISON.contains(criteria),String.format("Указанный критерий сравнения для типа %s: %s не входит в список валидных критериев сравнения для этих типов данных: \n\r %s",DOUBLE_TYPE,criteria, VALID_STRING_CRITERIA_OF_COMPARISON));
        double resultedDoubleValue = castObjectToDouble(resultedObject);
        double expectedDoubleValue = castObjectToInt(expectedValue);
        if (criteria.equalsIgnoreCase(EQUAL)) {
            assertEquals(resultedDoubleValue, expectedDoubleValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r равно ожидаемому значению: \n\r %s ", resultedDoubleValue, expectedDoubleValue));
        } else if (criteria.equalsIgnoreCase(NOT_EQUAL)) {
            assertNotEquals(resultedDoubleValue, expectedDoubleValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r не равно ожидаемому значению: \n\r %s ", resultedDoubleValue, expectedDoubleValue));
        } else if (criteria.equalsIgnoreCase(MORE)) {
            assertTrue(resultedDoubleValue>expectedDoubleValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r больше ожидаемого значения: \n\r %s ", resultedDoubleValue, expectedDoubleValue));
        } else if (criteria.equalsIgnoreCase(LESS)) {
            assertTrue(resultedDoubleValue<expectedDoubleValue, String.format("\n\r Ожидалось что фактическое значение: \n\r %s \n\r меньше ожидаемого значения: \n\r %s ", resultedDoubleValue, expectedDoubleValue));
        }
    }

    private static void comparatorForDateType(Object resultedObject, String criteria, String expectedValue) {
        //TODO: сделать сравниватель для дат
    }

    /** Метод для преобразования объекта в String*/
    private static String castObjectToString(Object targetObject) {
        return targetObject.toString();
    }
    /** Метод для преобразования объекта в int*/
    private static int castObjectToInt(Object targetObject) {
        return Integer.parseInt(targetObject.toString());
    }
    /** Метод для преобразования объекта в double*/
    private static double castObjectToDouble(Object targetObject) {
        return Double.parseDouble(targetObject.toString());
    }
}
