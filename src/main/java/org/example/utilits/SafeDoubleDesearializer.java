package org.example.utilits;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class SafeDoubleDesearializer extends JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            String text = p.getText();
            if (text == null || text.isEmpty()) {
                return null;
            }

            double value = Double.parseDouble(text.replace(',', '.'));

            return value;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
