package task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class task4 {
    private final static ArrayList<Integer> list = new ArrayList<>();

    private static void fillInTestCollection(String path) {
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
        System.out.print("Current array is ");
        System.out.print("[ ");
        for (var element : list)
            System.out.print(element + " ");
        System.out.println("]");
        System.out.println("Minimum count of step is " + step);
    }

    public static void invokeLogic() {
        var path = getPathToFile();
        fillInTestCollection(path);
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
            case "1": path = "C:\\Perfomance lab\\Perfomance-Lab_TestTask\\PerfomanceLabTasks\\src\\main\\resources\\array";
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
