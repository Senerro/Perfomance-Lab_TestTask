package task1;

import java.util.ArrayList;
import java.util.Scanner;

import static task1.taskHelpers.ArrayHelper.*;
import static task1.taskHelpers.GlobalTaskHelper.*;

public class task1 {
    static class myCircularList<T extends Number> {
        private Node<T> first;
        private Node<T> last;
        private final ArrayList<ArrayList<Node<T>>> intervalsList = new ArrayList<>();
        private int intervalSize;

        private static class Node<T extends Number> {
            Node<T> next;
            Node<T> previous;
            private final T value;

            public Node(T value) {
                this.value = value;
            }

            public void setNext(Node<T> next) {
                this.next = next;
            }

            public void setPrevious(Node<T> previous) {
                this.previous = previous;
            }

            @Override
            public String toString() {
                return "Node{" + "value=" + value + '}';
            }
        }

        public myCircularList() {
            first = null;
            last = null;
        }

        public myCircularList(int size, int interval) {
            this();
            myCircularListGenerator(size);
            this.intervalSize = interval;
            this.makeIntervals();
        }

        private void myCircularListGenerator(int size) {
            for (int i = 0; i < size; i++)
                add(i + 1);
        }

        public void add(int value) {
            Node<T> node = new Node(value);
            if (first == null) {
                first = node;
                last = node;
            } else {
                last.setNext(node);
                node.setPrevious(last);
                node.setNext(first);
                first.setPrevious(node);
                last = node;
            }
        }

        public Node<T> iterate(Node<T> currentNode) {
            return currentNode.next;
        }

        private void makeInterval(Node<T> start) {
            intervalsList.add(new ArrayList<>());
            Node<T> iterableNode = start;
            for (int i = 0; i < intervalSize; i++) {
                getLastIntervalOfList().add(iterableNode);
                iterableNode = iterate(iterableNode);
            }
        }

        public boolean checkLastNodeOfIntervalEqualsFirst() {
            return getLast().equals(first);
        }

        private ArrayList<Node<T>> getLastIntervalOfList() {
            return intervalsList.get(intervalsList.size() - 1);
        }

        private Node<T> getLast() {
            return getLastIntervalOfList().get(getLastIntervalOfList().size() - 1);
        }

        private void makeIntervals() {
            Node<T> currentNode = first;
            do {
                makeInterval(currentNode);
                currentNode = getLast();
            } while (!checkLastNodeOfIntervalEqualsFirst());
        }

        public void showIntervals() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < intervalsList.size(); i++) {
                stringBuilder.append("interval [").append(i + 1).append("] ");
                for (var element : intervalsList.get(i))
                    stringBuilder.append(element.value);
                stringBuilder.append("\n");
            }
            System.out.println(stringBuilder);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Route: ");
            for (var list : intervalsList) {
                builder.append(list.get(0).value);
            }
            return builder.toString();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are uou going to check realization with custom circle collection or using array only?");
        System.out.println("[1] custom collection");
        System.out.println("[2] array only");
        String answer = scanner.next();
        switch(answer) {
            case "1": customCollectionRealisation(); break;
            case "2": arrayRealisation(); break;

        }
        main(new String[0]);
    }

    private static void arrayRealisation() {
        StringBuilder route = new StringBuilder();
        int pointer = 0, counter = 1;
        int[] circleArray = arrayInitialization();

        do {
            System.out.print("Step [" + counter + "] ");
            for (int i = getIntervalSize(); i > 0; i--) {
                System.out.print(circleArray[pointer]);
                pointer = incrementPointer(pointer);
            }
            pointer = decrementPointer(pointer);
            counter++;
            System.out.println();
        }
        while ((circleArray[pointer] != circleArray[0]));

        pointer = 0;
        do {
            route.append(getCircleArray()[pointer]);
            pointer = (pointer + getIntervalSize() - 1) % getArraySize();
        }
        while (pointer != 0);

        System.out.println("Route is " + route);
    }

    private static void customCollectionRealisation() {
        getSizesFromConsole();
        myCircularList<Integer> list = new myCircularList<>(getArraySize(), getIntervalSize());
        list.showIntervals();
        System.out.println(list);
    }
}

