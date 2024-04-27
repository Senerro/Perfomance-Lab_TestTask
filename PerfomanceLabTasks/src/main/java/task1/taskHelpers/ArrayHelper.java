package task1.taskHelpers;

import static task1.taskHelpers.GlobalTaskHelper.*;

public class ArrayHelper {
    private static int[] circleArray;

    public static int[] arrayInitialization() {
        do getSizesFromConsole();
        while (sizeValidator());

        circleArray = new int[getArraySize()];
        for (int i = 0; i < getArraySize(); i++)
            circleArray[i] = i + 1;

        return circleArray;
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
}
