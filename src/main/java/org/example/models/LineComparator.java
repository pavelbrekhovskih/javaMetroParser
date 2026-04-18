package org.example.models;

import java.util.Comparator;

public class LineComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        int i1 = 0;
        int i2 = 0;

        while (i1 < s1.length() && Character.isDigit(s1.charAt(i1))) {
            i1++;
        }

        while (i2 < s2.length() && Character.isDigit(s2.charAt(i2))){
            i2++;
        }

        String numPart1 = s1.substring(0, i1);
        String numPart2 = s1.substring(0, i2);

        if (numPart1.isEmpty() && numPart2.isEmpty()) {
            return s1.compareTo(s2);
        }

        if (numPart1.isEmpty()) {
            return -1;
        }

        if (numPart2.isEmpty()) {
            return 1;
        }

        int num1 = Integer.parseInt(s1.substring(0, i1));
        int num2 = Integer.parseInt(s2.substring(0, i2));

        if (num1 != num2) {
            return Integer.compare(num1, num2);
        }

        return s1.substring(i1).compareTo(s2.substring(i2));
    }
}
