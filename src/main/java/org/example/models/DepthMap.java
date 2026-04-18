package org.example.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.example.utilits.SafeDoubleDesearializer;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DepthMap {
    private String stationName;
    @JsonDeserialize(using = SafeDoubleDesearializer.class)
    private Double depth;

    public String getStationName() {
        return stationName;
    }

    public Double getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return "DepthMap{" +
                "stationName='" + stationName + '\'' +
                ", depth='" + depth + '\'' +
                '}';
    }
}
