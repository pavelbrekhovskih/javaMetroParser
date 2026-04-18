package org.example.utilits;

public class Printer {
    public void printElements(Iterable<Object> elements) {
        elements.forEach(System.out::println);
    }
}
