package org.example.utilits;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.DepthMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JSONParsing {
    public static List<DepthMap> getObjects(String jsonPath) throws IOException {
        List<DepthMap> depths = new ArrayList<>();

        String jsonStr = Files.readString(Paths.get(jsonPath));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = objectMapper.readTree(jsonStr);

        for (JsonNode jsonObj: jsonData) {
            DepthMap depths1 = objectMapper.treeToValue(jsonObj, DepthMap.class);
            depths.add(depths1);

        }

        return depths;
    }
}
