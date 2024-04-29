package task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Math.abs;

public class task4 {
    private final static ArrayList<Integer> list = new ArrayList<>();

    private static void fillInCollection(String path) {
        List<String> dataList = getDataFromFileByCommandLine(path);
        for (var element : dataList)
            list.add(Integer.parseInt(element));
    }

    private static List<String> getDataFromFileByCommandLine(String path) {
        List<String> stringElementsList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (var element : lines)
                stringElementsList.addAll(Arrays.stream(element.split(" ")).toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringElementsList;
    }

    private static int calculateAvgElement() {
        float result = 0;
        for (var element : list)
            result += element;

        return Math.round(result / list.size());
    }

    private static int getBaseElement() {
        int baseElement = list.get(0);
        int calculatedAvgElement = calculateAvgElement();
        int difference = abs(baseElement - calculatedAvgElement);
        for (var element : list)
            if (abs(abs(element) - abs(calculateAvgElement())) < difference) {
                baseElement = element;
                difference = abs(abs(element) - abs(calculateAvgElement()));
            }

        return baseElement;
    }

    private static int calculateStepsAndBriningToBaseElement(int baseElement) {
        int step = 0;
        for (int i = 0; i < list.size(); i++) {
            int tmp = abs(abs(list.get(i)) - abs(baseElement));
            list.set(i, baseElement);
            step += tmp;
        }
        return step;
    }

    private static void showInformation(int step) {
        showInformation();
        System.out.println("Minimum count of step is " + step);
    }

    private static void showInformation() {
        System.out.print("Current array is ");
        System.out.print("[ ");
        for (var element : list)
            System.out.print(element + " ");
        System.out.println("]");
    }

    private static void invokeLogic() {
        var path = getPathToFile();
        fillInCollection(path);
        showInformation();
        int baseElement = getBaseElement();
        var steps = calculateStepsAndBriningToBaseElement(baseElement);
        showInformation(steps);
    }

    private static String getPathToFile() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        String path = "";

        System.out.println("Are you going to use default file or write down the path yourself");
        System.out.println("[1] default file");
        System.out.println("[2] write down the path");
        String answer = scanner.next();
        switch (answer) {

            case "1":
                Properties properties = new Properties();
                try {
                    properties.load(new FileReader("src/main/resources/arrayLocation.properties"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                path = properties.getProperty("array");
            break;
            case "2": path = scanner2.nextLine();
            break;
            default: System.out.println("Wrong answer"); getPathToFile();
        }
        return path;
    }

    public static void main(String[] args) {
        invokeLogic();
    }
}
