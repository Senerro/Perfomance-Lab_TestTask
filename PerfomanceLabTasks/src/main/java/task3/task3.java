package task3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class task3 {
    public static void main(String[] args) throws IOException {
        //0-values.json
        //1-tests.json
        //2-report.json
        SaveReportToFile(args[2], makeReportString(args[1], getValuesMap(args[0])));
        //SaveReportToFile("C:\\Perfomance lab\\Perfomance-Lab_TestTask\\PerfomanceLabTasks\\src\\main\\java\\task3\\files\\report.json", makeReportString("src/main/java/task3/files/tests.json", getValuesMap("src/main/java/task3/files/values.json")));
    }

    private static void SaveReportToFile(String path, String value) throws IOException {
        File file = new File(path);
        file.createNewFile();
        try {
            FileWriter writer = new FileWriter(file.getPath());
            writer.write(value);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    private static HashMap<String, String> getValuesMap(String path) throws IOException {
        String values = Files.readString(Paths.get(path));
        var lines = values.split("\\{");
        HashMap<String, String> valuesMap = new HashMap<>();
        for (var line : lines) {
            var sublines = line.split(",");
            System.out.println();
            if (sublines.length > 2) {
                var id = sublines[0].replaceAll("[^0-9]", "");
                var valueArray = sublines[1].replaceAll("[^A-Za-z\":]", "").split(":");
                var value = String.join(": ", valueArray);
                valuesMap.put(id, value);
            }
        }
        return valuesMap;
    }

    private static String makeReportString(String path, HashMap<String, String> map) throws IOException {
        String tests = Files.readString(Paths.get(path));
        var lines = tests.split("\\{");
        for (int i = 0; i < lines.length; i++) {
            for (var key : map.keySet()) {
                if (lines[i].contains(key)) {
                    lines[i] = lines[i].replace("\"value\": \"\"", map.get(key));
                    break;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < lines.length; i++) {
            builder.append('{');
            builder.append(lines[i]);
        }
        return builder.toString();
    }
}
