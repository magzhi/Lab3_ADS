package Classes;

import java.util.*;

public class BST<K extends Comparable<K>, V>{
    private Node root;
    public class Node{
        private K key;
        private V value;
        private Node left, right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public void put(K key, V value){
        root = put(root, key, value);
    }

    private Node put(Node current, K key, V value){
        if (current ==  null)
            return new Node(key, value);

        int comparison = key.compareTo(current.key);

        if (comparison < 0)
            current.left = put(current.left, key, value);
        else if (comparison > 0)
            current.right = put(current.right, key, value);
        else
            current.value = value;

        return current;
    }

    public V get(K key){
        Node current = root;
        while (current != null){
            int comparison = key.compareTo(current.key);

            if (comparison < 0)
                current = current.left;
            else if (comparison > 0)
                current = current.right;
            else
                return current.value;
        }
        return null;
    }

    public void delete(K key){
        root = delete(root, key);
    }

    private Node delete(Node current, K key){
        if (current ==  null)
            return null;

        int comparison = key.compareTo(current.key);

        if (comparison < 0)
            current.left = delete(current.left, key);
        else if (comparison > 0)
            current.right = delete(current.right, key);
        else {
            //case 1: no child
            if (current.left == null && current.right == null)
                return null;

            //case 2: only one child

            if (current.left == null)
                return current.right;

            if (current.right == null)
                return current.left;

            //case 3: two children

            K smallestValue = findSmallestValue(current.left);
            current.key = smallestValue;
            current.left = delete(current.left, smallestValue);
        }

        return current;
    }

    public Iterable<Node> iterable(){
        return new Iterable<Node>() {
            @Override
            public Iterator<Node> iterator() {
                return new Iterator<Node>() {
                    private Deque<Node> stack = new ArrayDeque<>(List.of(root));
                    private Node current = root;

                    @Override
                    public boolean hasNext(){
                        return !stack.isEmpty();
                    }

                    public K getKey(){
                        return current.key;
                    }

                    public V getValue(){
                        return current.value;
                    }

                    @Override
                    public Node next(){
                        if (!hasNext()) {
                            throw new IndexOutOfBoundsException("No more elements");
                        }
                        current = stack.pop();
                        if (current.right != null) stack.push(current.right);
                        if (current.left != null) stack.push(current.left);
                        return current;
                    }
                };
            }
        };
    }

    private void inOrder(Node node, LinkedList<K> keyList) {
        if (node != null) {
            inOrder(node.left, keyList);
            keyList.add(node.key);
            inOrder(node.right, keyList);
        }
    }

    private K findSmallestValue(Node node) {
        return node.right == null ? node.key : findSmallestValue(node.right);
    }
}
