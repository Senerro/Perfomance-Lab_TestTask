package task2;

import task2.Enums.ParamNamesEnum;
import task2.Enums.PointLocationEnum;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class task2 {
    static class Circle {
        private final float x;
        private final float y;
        private final float r;

        public Circle(File file) {
            this(getXFromFile(file), getYFromFile(file), getRadiusFromFile(file));
        }

        private Circle(float x, float y, float r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        private static float getRadiusFromFile(File file) {
            return getCircleParamsFromFile(file, ParamNamesEnum.R);
        }

        private static float getYFromFile(File file) {
            return getCircleParamsFromFile(file, ParamNamesEnum.Y);
        }

        private static float getXFromFile(File file) {
            return getCircleParamsFromFile(file, ParamNamesEnum.X);
        }

        private static float getCircleParamsFromFile(File file, ParamNamesEnum namesEnum) {
            List<String> circleData = new ArrayList<>();
            List<String> lines = getTaskDataFromFile(file);
            for (var line : lines)
                circleData.addAll(Arrays.asList(line.split(" ")));

            return Float.parseFloat(circleData.get(namesEnum.ordinal()));
        }

        public void definePointsLocation(File file) {
            var pointList = getTaskDataFromFile(file);
            for (var point : pointList)
                defineCurrentPointLocation(
                        Float.parseFloat(point.split(" ")[ParamNamesEnum.X.ordinal()]),
                        Float.parseFloat(point.split(" ")[ParamNamesEnum.Y.ordinal()])
                );
        }

        public void defineCurrentPointLocation(float x, float y) {
            var powDistance = Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2);
            if (powDistance > Math.pow(r, 2))
                System.out.println(PointLocationEnum.OUTSIDE.ordinal());
            else if (powDistance < Math.pow(r, 2))
                System.out.println(PointLocationEnum.INSIDE.ordinal());
            else
                System.out.println(PointLocationEnum.ON_THE_CIRCLE.ordinal());
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
       // Circle circle = new Circle (new File("C:\\Perfomance lab\\Perfomance-Lab_TestTask\\PerfomanceLabTasks\\src\\main\\java\\task2\\files\\circle"));
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("C:\\Perfomance lab\\Perfomance-Lab_TestTask\\PerfomanceLabTasks\\src\\main\\resources\\task2\\cirleTaskPathes.properties"))) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Circle circle = new Circle (new File(props.getProperty("circle")));
        circle.definePointsLocation(new File(props.getProperty("points")));
    }
}
