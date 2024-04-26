package task1.taskHelpers;

import java.util.Scanner;

public class ArrayHelper {
    private static int arraySize;
    private static int intervalSize;
    private static int[] circleArray;
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] arrayInitialization() {
        do getSizesFromConsole();
        while (sizeValidator(arraySize, intervalSize));

        circleArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
            circleArray[i] = i + 1;

        return circleArray;
    }

    private static boolean sizeValidator(int size, int interval) {
        return size % interval == 0 || interval % size == 0;
    }

    public static int getArraySize() {
        return arraySize;
    }

    public static int getIntervalSize() {
        return intervalSize;
    }

    public static int[] getCircleArray() {
        return circleArray;
    }

    public static int incrementPointer(int pointer) {
        return ++pointer >= circleArray.length ? 0 : pointer;
    }
    public static int decrementPointer(int pointer){
        return --pointer < 0 ? circleArray.length - 1 : pointer;
    }

    private static void getSizesFromConsole() {
        System.out.print("Enter size of array: ");
        arraySize = scanner.nextInt();
        System.out.print("Enter size of interval: ");
        intervalSize = scanner.nextInt();
        System.out.println();
    }
}
