package org.utils.deserializeStrategies;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTime extends JsonDeserializer<LocalDateTime> {
    /**
     * Метод для дессериализации строки из джейсона в конкретный тип объекта
     */
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        // Создаем форматтер, на основании которого будет парситься время
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //парсим текст из джейсона в соответствующий формат, для тех полей, которые помечены аннотацией @JsonDeserialize(using = DateDeserializer.class)
        return LocalDateTime.parse(jsonParser.getText(), formatter);
    }

}
