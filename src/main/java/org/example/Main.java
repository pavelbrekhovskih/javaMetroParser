package org.example;

import org.example.utilits.*;
import org.apache.logging.log4j.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            JSONWriter jsonWriter = new JSONWriter();
            jsonWriter.writeMapJson();
            jsonWriter.writeStationsJson();
        } catch (Exception e) {
            logger.log(Level.ERROR, e + " " + e.getStackTrace()[0].toString());
        }
    }
}