package org.example.models;

import java.util.Comparator;

public class StationComparator implements Comparator<MoscowMetroStation> {
    @Override
    public int compare(MoscowMetroStation st1, MoscowMetroStation st2) {
        return st1.getName().toLowerCase().compareTo(st2.getName().toLowerCase());
    }
}
