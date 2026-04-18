package org.example.utilits;

import org.example.models.DateMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVParsing {
    public static List<DateMap> getObjects(String csvPath) throws IOException {
        List<DateMap> dates = new ArrayList<>();
        List<String> lines;

        lines = Files.readAllLines(Paths.get(csvPath));
        boolean passFirstLine = true;
        for (String line : lines) {
            if (passFirstLine) {
                passFirstLine = false;
                continue;
            }

            String[] fragments = line.split(",");
            String name = fragments[0];
            String date = fragments[1];

            DateMap date1 = new DateMap(name, date);

            dates.add(date1);

        }

        return dates;
    }
}
