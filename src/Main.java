import Classes.MyHashTable;
import Classes.MyTestingClass;

import java.util.Random;

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
        for (int i = 0; i < myHashTable.getM(); i++) {
            System.out.println("The chain size of chain number " + i + " is: " + myHashTable.getChainSize(i));
        }

        // BST now
    }
}