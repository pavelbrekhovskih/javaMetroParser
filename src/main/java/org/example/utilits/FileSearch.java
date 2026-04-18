package org.example.utilits;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {
    private static void fileSearch(File file, String format, List<String> list) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;

            for (File file1 : files) {
                fileSearch(file1, format, list);
            }
            return;
        }

        if (file.getName().endsWith(format)) {
            list.add(file.getPath());
        }
    }

    public static List<String> getFilePaths(String folderPath, String format) {
        List<String> fileNames = new ArrayList<>();

        File folderFile = new File(folderPath);
        fileSearch(folderFile, format, fileNames);

        return fileNames;
    }
}
