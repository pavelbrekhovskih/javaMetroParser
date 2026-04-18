package org.example.models;

public record MetroLine(String number, String name) {
    @Override
    public String toString() {
        return "MetroLine{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
