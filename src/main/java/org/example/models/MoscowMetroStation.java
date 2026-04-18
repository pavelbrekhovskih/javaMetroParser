package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties({"connectionLinesNumbers"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MoscowMetroStation extends MetroStation {
    private final String date;
    private final Double depth;

    public MoscowMetroStation(String name,
                              String lineNumber,
                              boolean hasConnections,
                              List<Map<String, String>> connections,
                              String date,
                              Double depth) {
        super(name, lineNumber, hasConnections, connections);
        this.date = date;
        this.depth = depth;
    }

    public String getDate() {
        return date;
    }

    public Double getDepth() {
        return depth;
    }
}
