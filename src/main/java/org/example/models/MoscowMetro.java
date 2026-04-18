package org.example.models;

import java.util.List;
import java.util.Map;

public record MoscowMetro(
        Map<String,
        List<String>> stations,
        List<List<Map<String, String>>> connections,
        List<MetroLine> lines) {
}
