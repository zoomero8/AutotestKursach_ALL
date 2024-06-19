package data;

import io.qameta.allure.internal.shadowed.jackson.core.JsonParser;
import io.qameta.allure.internal.shadowed.jackson.databind.DeserializationContext;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonDeserializer;
import io.qameta.allure.internal.shadowed.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateDeserializer extends JsonDeserializer<LocalDateTime> {
    @JsonDeserialize
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return LocalDateTime.parse(jsonParser.getText(), formatter);
    }
}