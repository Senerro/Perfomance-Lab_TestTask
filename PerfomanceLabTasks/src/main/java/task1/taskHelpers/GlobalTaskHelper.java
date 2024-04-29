package task1.taskHelpers;

import java.util.Scanner;

public class GlobalTaskHelper {
    private static int arraySize;
    private static int intervalSize;
    public static void getSizesFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        arraySize = scanner.nextInt();
        System.out.print("Enter size of interval: ");
        intervalSize = scanner.nextInt();
        System.out.println();
    }
    public static boolean sizeValidator() {
        return arraySize % intervalSize == 0 || intervalSize % arraySize == 0;
    }

    public static int getArraySize() {
        return arraySize;
    }

    public static int getIntervalSize() {
        return intervalSize;
    }
}
