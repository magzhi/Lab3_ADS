import Classes.BST;
import Classes.MyHashTable;
import Classes.MyTestingClass;

import java.util.Random;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> myHashTable = new MyHashTable<>(100);
        Random random = new Random(1234);
        for (int i = 0; i < 10000; i++) {
            StringBuilder name = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                name.append((char) (random.nextInt(26) + 'a'));
            }
            MyTestingClass key = new MyTestingClass(name.toString());
            myHashTable.put(key, i);
        }
        System.out.println("Size is: " + myHashTable.getSize());
        // For random seed '1234' it creates 6 same random names, so total size will be 9994 instead of 10000
        int averageDifference = 0;
        for (int i = 0; i < myHashTable.getM(); i++) {
            System.out.println("The chain size of chain number " + i + " is: " + myHashTable.getChainSize(i));
            averageDifference += abs(100 - myHashTable.getChainSize(i));
        }
        System.out.println("Average difference from the mean is: " + averageDifference/100.0);
        System.out.println();

        // BST now

        BST<Integer, Integer> binaryTree = new BST<>();

        binaryTree.put(5,3);
        binaryTree.put(3,2);
        binaryTree.put(4,7);
        binaryTree.put(2,9);
        binaryTree.put(6,0);
        binaryTree.put(7,3);
        System.out.println("Data read from left to right before remove: ");

        System.out.println();
        for (var elem : binaryTree.iterable()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
        System.out.println("Data read from left to right after remove: ");
        binaryTree.delete(5);
        System.out.println();
        for (var elem : binaryTree.iterable()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}