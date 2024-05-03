package task4;

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

    public static void main(String[] args) {
        fillInCollection(args[0]);
        int baseElement = getBaseElement();
        System.out.println(calculateStepsAndBriningToBaseElement(baseElement));
    }
}
