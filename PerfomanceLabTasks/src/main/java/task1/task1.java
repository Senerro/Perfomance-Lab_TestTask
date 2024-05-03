package task1;

public class task1 {
    private static int[] circleArray;
    public static void main(String[] args) {
        int arraySize = Integer.parseInt(args[0]);
        System.out.println("array size is " + arraySize);
        int intervalSize = Integer.parseInt(args[1]);
        System.out.println("intervalSize is " + intervalSize);

        arrayInitialization(arraySize);
        routeMaking(arraySize, intervalSize);
    }

    private static void routeMaking(int arraySize, int intervalSize) {
        StringBuilder route = new StringBuilder();
        int pointer = 0;
        do {
            route.append(circleArray[pointer]);
            pointer = (pointer + intervalSize - 1) % arraySize;
        }
        while (pointer != 0);

        System.out.println("Route is " + route);
    }

    public static void arrayInitialization(int arraySize) {
        circleArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
            circleArray[i] = i + 1;
    }
}