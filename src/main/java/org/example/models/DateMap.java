package org.example.models;

public record DateMap(String name, String date) {
    @Override
    public String toString() {
        return "DateMap{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
