package task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class task2 {
    static class Circle {
        private final float x;
        private final float y;
        private final float r;

        public Circle(File file) {
            this(getCircleParamsFromFile(file, 0), getCircleParamsFromFile(file, 1), getCircleParamsFromFile(file, 2));
        }

        private Circle(float x, float y, float r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        private static float getCircleParamsFromFile(File file, int paramNumber) {
            List<String> circleData = new ArrayList<>();
            List<String> lines = getTaskDataFromFile(file);
            for (var line : lines)
                circleData.addAll(Arrays.asList(line.split(" ")));

            return Float.parseFloat(circleData.get(paramNumber));
        }

        public void definePointsLocation(File file) {
            var pointList = getTaskDataFromFile(file);
            for (var point : pointList)
                defineCurrentPointLocation(
                        Float.parseFloat(point.split(" ")[0]),
                        Float.parseFloat(point.split(" ")[1])
                );
        }

        public void defineCurrentPointLocation(float x, float y) {
            var powDistance = Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2);
            if (powDistance > Math.pow(r, 2))
                System.out.println(2);
            else if (powDistance < Math.pow(r, 2))
                System.out.println(1);
            else
                System.out.println(0);
        }

        private static List<String> getTaskDataFromFile(File file) {
            try {
                return Files.readAllLines(Paths.get(file.getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Circle circle = new Circle (new File(args[0]));
        circle.definePointsLocation(new File(args[1]));
    }
}
