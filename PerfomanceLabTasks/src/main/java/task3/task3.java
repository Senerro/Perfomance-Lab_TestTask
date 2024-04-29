package task3;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import task3.pojo.JsonDTO;
import task3.pojo.TestsJson;
import task3.pojo.ValuesJson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class task3 {
    private final static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static TestsJson getTestsObjects(String path) {

        TestsJson tests;
        try {
            tests = objectMapper.readValue(new File(path), TestsJson.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tests;
    }

    private static ValuesJson getValuesObject(String path) {

        ValuesJson values;
        try {
            values = objectMapper.readValue(new File(path), ValuesJson.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return values;
    }

    private static void merge(TestsJson tests, ValuesJson values) {
        for (JsonDTO value : values.getValues())
            mergeFoundElement(tests.getTests(), value);
    }

    private static void mergeFoundElement(List<JsonDTO> list, JsonDTO testPOJO) {
        for (var element : list) {
            if (element.equals(testPOJO)) element.merge(testPOJO);

            if (element.getValues() != null) mergeFoundElement(element.getValues(), testPOJO);
        }
    }

    private static void saveReport(TestsJson tests, String path) {
        try {
            objectMapper.writeValue(new File(path), tests);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you going to use default files OR write down the paths for your files?");
        System.out.println("[1] default files");
        System.out.println("[2] write the paths by console");

        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                invokeByDefaultData();
                break;
            case "2":
                invokeByCustomData();
                break;
            default:
                System.out.println("Be carefully and answer once more");
                main(new String[0]);
        }
    }

    private static void invokeByCustomData() {
        System.out.println("*fileWithTest.json*");
        TestsJson tests = getTestsObjects(new Scanner(System.in).nextLine());
        System.out.println("*fileWithValues.json*");
        ValuesJson values = getValuesObject(new Scanner(System.in).nextLine());
        merge(tests, values);
        System.out.println("*reportFilePath.json*");
        saveReport(tests, new Scanner(System.in).nextLine());
    }


    private static void invokeByDefaultData() {
        File file = new File("src/main/resources/jsonLocations.properties");

        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
            TestsJson tests = getTestsObjects(properties.getProperty("tests"));
            ValuesJson values = getValuesObject(properties.getProperty("values"));
            merge(tests, values);
            saveReport(tests, properties.getProperty("report"));

            System.out.println("Go to " + properties.getProperty("report") + " and check the report");

        } catch (IOException e) {
            System.out.println("Something happens, try to use console");
            invokeByCustomData();
        }
    }
}
