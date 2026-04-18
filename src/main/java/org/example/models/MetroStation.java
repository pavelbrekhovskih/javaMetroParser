package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;

public class MetroStation {
    private final String name;
    private final String lineNumber;
    private final boolean hasConnections;
    @JsonIgnore
    private final List<Map<String, String>> connections;

    public MetroStation(String name,
                        String lineNumber,
                        boolean hasConnections,
                        List<Map<String, String>> connections)
    {
        this.name = name;
        this.lineNumber = lineNumber;
        this.hasConnections = hasConnections;
        this.connections = connections;
    }

    public String getName() {
        return name;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public boolean isHasConnections() {
        return hasConnections;
    }

    public List<Map<String, String>> getConnections() {
        return connections;
    }

    @Override
    public String toString() {
        return "MetroStation{" +
                "name='" + name + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", hasConnections=" + hasConnections +
                ", connectionLines=" + connections +
                '}';
    }
}
