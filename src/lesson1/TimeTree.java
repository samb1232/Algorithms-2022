package lesson1;

import java.io.BufferedWriter;
import java.io.IOException;

public class TimeTree {

    TimeNode root = new TimeNode();


    public int compareNodes(String[] newNode, String[] currentNode) {
        if (Integer.parseInt(newNode[0]) % 12 < Integer.parseInt(currentNode[0]) % 12) return -1;
        else if (Integer.parseInt(newNode[0]) % 12 > Integer.parseInt(currentNode[0]) % 12) return 1;
        else if (Integer.parseInt(newNode[1]) < Integer.parseInt(currentNode[1])) return -1;
        else if (Integer.parseInt(newNode[1]) > Integer.parseInt(currentNode[1])) return 1;
        else if (Integer.parseInt(newNode[2]) < Integer.parseInt(currentNode[2])) return -1;
        else if (Integer.parseInt(newNode[2]) > Integer.parseInt(currentNode[2])) return 1;
        return 0;
    }

    public void addNode(String[] time, String meridiem) {

        TimeNode newNode = new TimeNode(time, meridiem);

        TimeNode currentNode;
        TimeNode parent;


        if (newNode.meridiem.equals("AM")) {
            currentNode = root.leftChild;

            if (currentNode == null) {
                root.leftChild = newNode;
                return;
            }
        } else {
            currentNode = root.rightChild;

            if (currentNode == null) {
                root.rightChild = newNode;
                return;
            }
        }

        while (true) {
            parent = currentNode;

            switch (compareNodes(time, parent.time)) {
                case -1 -> {
                    currentNode = parent.leftChild;
                    if (currentNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                }
                case 1 -> {
                    currentNode = parent.rightChild;
                    if (currentNode == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
                case 0 -> {
                    parent.repetition += 1;
                    return;
                }
            }
        }
    }


    private void sortAndPrint(TimeNode currentNode, BufferedWriter outputFile) throws IOException {
        root.repetition = 0;
        if (currentNode != null) {
            sortAndPrint(currentNode.leftChild, outputFile);
            while (currentNode.repetition > 0) {
                outputFile.write(currentNode.toString());
                currentNode.repetition -= 1;
            }
            sortAndPrint(currentNode.rightChild, outputFile);
        }
    }

    public void sortAndPrint(BufferedWriter outputFile) throws IOException {
        sortAndPrint(root, outputFile);
    }
}


class TimeNode {

    String[] time;
    String meridiem;
    int repetition = 1;

    TimeNode leftChild;
    TimeNode rightChild;

    public TimeNode(String[] time, String meridiem) {
        this.time = time;
        this.meridiem = meridiem;
    }

    public TimeNode() {
    }

    public String toString() {
        return time[0] + ":" + time[1] + ":" + time[2] + " " + meridiem + "\n";
    }
}
