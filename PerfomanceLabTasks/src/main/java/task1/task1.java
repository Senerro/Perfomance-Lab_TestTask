package task1;

import java.util.LinkedList;

import static task1.taskHelpers.ArrayHelper.*;

public class task1 {
   /* class myCircularList {
        Node first;
        Node last;
        LinkedList<Node> array;

        class Node<T> {
            Node next;
            Node previous;
            private T value;

            public Node(T value) {
                this.value = value;
            }

            public T getValue() {
                return value;
            }

            public void setValue(T value) {
                this.value = value;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            public Node getPrevious() {
                return previous;
            }

            public void setPrevious(Node previos) {
                this.previous = previos;
            }
        }

    }*/
    public static void main(String[] args) {
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

}

