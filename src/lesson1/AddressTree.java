package lesson1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class AddressTree {
    AddressNode root;

    private int compareNodes(AddressNode newNode, AddressNode currentNode) {
        if (newNode.address[0].compareTo(currentNode.address[0]) < 0) return -1;
        else if (newNode.address[0].compareTo(currentNode.address[0]) > 0) return 1;
        else if (Integer.parseInt(newNode.address[1]) < Integer.parseInt(currentNode.address[1])) return -1;
        else if (Integer.parseInt(newNode.address[1]) > Integer.parseInt(currentNode.address[1])) return 1;
        return 0;
    }

    public void addNode(String citizen, String[] address) {

        AddressNode newNode = new AddressNode(citizen, address);

        if (root == null) {
            root = newNode;
            return;
        }

        AddressNode currentNode = root;
        AddressNode parent;

        while (true) {
            parent = currentNode;

            switch (compareNodes(newNode, currentNode)) {
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
                    currentNode.citizens.add(newNode.citizen);
                    return;
                }
            }
        }
    }

    public void sortAndPrint(AddressNode currentNode, BufferedWriter outputFile) throws IOException {
        if (currentNode != null) {
            sortAndPrint(currentNode.leftChild, outputFile);
            outputFile.write(currentNode.toString());
            sortAndPrint(currentNode.rightChild, outputFile);
        }
    }

    public void sortAndPrint(BufferedWriter outputFile) throws IOException {
        sortAndPrint(root, outputFile);
    }


    static class AddressNode {
        String[] address;
        String citizen;
        LinkedList<String> citizens = new LinkedList();

        AddressNode leftChild;
        AddressNode rightChild;

        public AddressNode(String citizen, String[] address) {
            this.address = address;
            this.citizen = citizen;
            citizens.add(citizen);
        }

        @Override
        public String toString() {
            Collections.sort(citizens);
            return address[0] + " " + address[1] + " - " + String.join(", ", citizens) + "\n";
        }
    }

}