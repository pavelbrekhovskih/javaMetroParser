package org.example.models;

import java.util.Comparator;

public class DepthComparator implements Comparator<DepthMap> {
    @Override
    public int compare(DepthMap o1, DepthMap o2) {
        if (o1.getStationName().equalsIgnoreCase(o2.getStationName())) {
            if (o1.getDepth() != null && o2.getDepth() != null) {
                return (int) (o1.getDepth() - o2.getDepth());
            }
        }

        return o1.getStationName().toLowerCase().compareTo(o2.getStationName().toLowerCase());
    }
}
