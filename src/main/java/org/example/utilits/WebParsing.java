package org.example.utilits;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.example.models.MetroLine;
import org.example.models.MetroStation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebParsing {
    private static final List<MetroLine> lines = new ArrayList<>();
    private static final List<MetroStation> stations = new ArrayList<>();

    private static Document getDocument(String path) throws IOException {
        String htmlStr = Files.readString(Paths.get(path));

        return Jsoup.parse(htmlStr);
    }

    public static List<MetroLine> getLines() throws IOException {
        Document doc = getDocument("src/main/resources/metro-MSK-list.html");
        Elements linesNameElements = doc.select(".t-metrostation-list-header");

        for (Element elem : linesNameElements) {
            lines.add(new MetroLine(elem.attr("data-line"), elem.text()));
        }

        return lines;
    }

    public static List<MetroStation> getStations() throws IOException {
        Document doc = getDocument("src/main/resources/metro-MSK-list.html");
        Elements stationElements = doc.select(".single-station");

        String regexLine = "ln-\\w+";
        String regexConnectionStName = "«[^»]+»";
        Pattern patternLine = Pattern.compile(regexLine);
        Pattern patternConnection = Pattern.compile(regexConnectionStName);

        for (Element elem : stationElements) {
            List<Map<String, String>> connections = new ArrayList<>();

            boolean hasConnection = false;

            for (Element subElem : elem.children()) {
                Map<String, String> connectionsItem = new HashMap<>();

                String className = subElem.className();
                String title = subElem.attr("title");
                if (className.contains("ln")) {
                    hasConnection = true;

                    Matcher matcherLine = patternLine.matcher(className);
                    Matcher matcherStation = patternConnection.matcher(title);

                    String connectionLineInfo = matcherLine.find() ? matcherLine.group(0) : "";
                    String connectionStationInfo = matcherStation.find() ? matcherStation.group(0) : "";

                    String connectionsLineNumber = connectionLineInfo.substring(3);
                    String connectionStation = connectionStationInfo.substring(1, connectionStationInfo.length() - 1);
                    connectionsItem.put("line", connectionsLineNumber);
                    connectionsItem.put("station", connectionStation);
                    connections.add(connectionsItem);
                }
            }

            String stationName = elem.getElementsByClass("name").text();
            String stationLineNumber = elem.parent().attr("data-line");

            stations.add(new MetroStation(stationName, stationLineNumber, hasConnection, connections));
        }

        return stations;
    }
}
