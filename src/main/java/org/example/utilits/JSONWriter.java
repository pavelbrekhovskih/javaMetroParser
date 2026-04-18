package org.example.utilits;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JSONWriter {
    ObjectMapper objectMapper = new ObjectMapper();
    private final List<MetroStation> stations;
    List<MetroLine> lines;
    List<String> csvResult;
    List<String> jsonResult;
    List<DepthMap> depths = new ArrayList<>();;
    List<DateMap> dates = new ArrayList<>();;

    public JSONWriter() throws IOException {
        stations = WebParsing.getStations();
        lines = WebParsing.getLines();
        csvResult = FileSearch.getFilePaths("src/main/resources", ".csv");
        jsonResult = FileSearch.getFilePaths("src/main/resources", ".json");
    }

    public void writeMapJson() throws IOException {
        Map<String, List<String>> mapStations = new TreeMap<>(new LineComparator());
        List<List<Map<String, String>>> connections = new ArrayList<>();

        for (MetroLine line : lines) {
            List<String> groupedStations = new ArrayList<>();
            for (MetroStation station : stations) {
                if (line.number().equals(station.getLineNumber())) {
                    groupedStations.add(station.getName());
                }
            }
            mapStations.put(line.number(), groupedStations);
        }

        for (MetroStation metroStation : stations) {
            if (!metroStation.getConnections().isEmpty()) {
                connections.add(metroStation.getConnections());
            }
        }

        MoscowMetro metro = new MoscowMetro(mapStations, connections, lines);
        FileWriter fileWriter = new FileWriter("map.json");
        String jsonFile = objectMapper.writeValueAsString(metro);

        fileWriter.write(jsonFile);
        fileWriter.close();
    }

    public void writeStationsJson() throws IOException {
        Map<String, List<MoscowMetroStation>> myStationsMap = new HashMap<>();
        List<MoscowMetroStation> myStations = new ArrayList<>();

        for (String jsonPath : jsonResult) {
            depths.addAll(JSONParsing.getObjects(jsonPath));
        }

        depths.sort(new DepthComparator());

        for (String csvPath : csvResult) {
            dates.addAll(CSVParsing.getObjects(csvPath));
        }

        for (MetroStation metroStation : stations) {
            String name = metroStation.getName();
            String number = metroStation.getLineNumber();

            myStations.add(new MoscowMetroStation(
                    name,
                    getLineName(lines, number),
                    metroStation.isHasConnections(),
                    metroStation.getConnections(),
                    getStationDate(dates, name),
                    getStationDepth(depths, name)
            ));
        }

        myStations.sort(new StationComparator());
        myStationsMap.put("stations", myStations);

        String jsonFile = objectMapper.writeValueAsString(myStationsMap);

        FileWriter fileWriter = new FileWriter("stations.json");
        fileWriter.write(jsonFile);
        fileWriter.close();
    }

    public Double getStationDepth(List<DepthMap> depthMapList, String stationName) {
        Double depth = 0.0;
        for (DepthMap item : depthMapList) {
            if (item.getStationName().equals(stationName)) {
                depth = item.getDepth();
                break;
            }
        }

        return depth;
    }

    public String getStationDate(List<DateMap> dateMapList, String stationName) {
        String date = "";
        for (DateMap item : dateMapList) {
            if (item.name().equals(stationName)) {
                date = item.date();
            }
        }

        return date;
    }

    public String getLineName(List<MetroLine> metroLines, String lineNumber) {
        String name = "";
        for (MetroLine item : metroLines) {
            if (item.number().equals(lineNumber)) {
                name = item.name();
            }
        }

        return name;
    }
}
