import Classes.MyHashTable;
import Classes.MyTestingClass;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> myHashTable = new MyHashTable<>(100);
        Random random = new Random(1234);
        for (int i = 0; i < 10000; i++) {
            StringBuilder name = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                name.append((char) (random.nextInt(26) + 'a'));
            }
            MyTestingClass key = new MyTestingClass(name.toString());
            myHashTable.put(key, i);
        }
        
    }
}