package Classes;

import java.util.*;

/**
 * Implementation of a binary search tree as per given assignment structure.
 * @param <K> The data type of keys.
 * @param <V> The data type of mapped values.
 */
public class BST<K extends Comparable<K>, V>{
    private Node root;
    private int size;

    /**
     * Node class representing an element within BST.
     */
    public class Node{
        private K key;
        private V value;
        private Node left, right;

        /**
         * Constructor to create a new node with the given key and value.
         * @param key The key of the node.
         * @param value The value associated with the key.
         */
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key of the node.
         * @return The key of the node.
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value of the node.
         * @return The value of the node.
         */
        public V getValue() {
            return value;
        }
    }

    /**
     * Inserts a key-value pair into the BST.
     * Calls the reccuring 'put' method from below.
     * @param key The key to be inserted.
     * @param value The value associated with the key.
     */
    public void put(K key, V value){
        root = put(root, key, value);
    }

    /**
     * The reccuring method that locates and inserts the new Node into the correct spot.
     * @param current Currently searched Node.
     * @param key The key to be inserted.
     * @param value The value associated with the key.
     * @return
     */
    private Node put(Node current, K key, V value){
        if (current ==  null) {
            size++;
            return new Node(key, value);
        }

        int comparison = key.compareTo(current.key);

        if (comparison < 0)
            current.left = put(current.left, key, value);
        else if (comparison > 0)
            current.right = put(current.right, key, value);
        else
            current.value = value;

        return current;
    }

    /**
     * Retrieves the value associated with the given key.
     * @param key The key whose associated value is to be returned.
     * @return The value associated with the key, or null if the key is not found.
     */
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

    /**
     * Deletes the key-value pair associated with the given key from the BST.
     * Calls the reccuring 'delete' method from below.
     * @param key The key to be deleted.
     */
    public void delete(K key){
        root = delete(root, key);
    }

    /**
     * The reccuring method that locates and deletes the searched for Node
     * and then fixes the remaining based on how many children said Node had.
     * @param current Currently searched Node.
     * @param key The given key to be deleted.
     * @return
     */
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

            if (current.left == null){
                size--;
                return current.right;
            }

            if (current.right == null){
                size--;
                return current.left;
            }

            //case 3: two children

            K smallestValue = findSmallestValue(current.left);
            current.key = smallestValue;
            current.left = delete(current.left, smallestValue);
        }

        return current;
    }

    /**
     * Iterable integration to make complex for functions possible.
     * @return Iterable<Node> implemented interface.
     */
    public Iterable<Node> iterable(){
        return new Iterable<Node>() {
            /**
             * Iterator integration to make Iterable functional.
             * @return Iterator<Node> implemented interface.
             */
            @Override
            public Iterator<Node> iterator() {
                return new Iterator<Node>() {
                    private Deque<Node> stack = new ArrayDeque<>(List.of(root));
                    private Node current = root;

                    /**
                     * Checks if next exists.
                     * @return If exists => true.
                     */
                    @Override
                    public boolean hasNext(){
                        return !stack.isEmpty();
                    }

                    /**
                     * next method, as required for the iterator.
                     * It goes through all elements as long as hasNext did not throw an error.
                     * @return The next Node on the list.
                     */
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

    /**
     * Performs in-order traversal of the BST starting from a given point.
     * Used to write a given LinkedList all BST element in order from left to right.
     * @param node Currently searched Node.
     * @param keyList The LinkedList of all BST elements in order.
     */
    private void inOrder(Node node, LinkedList<K> keyList) {
        if (node != null) {
            inOrder(node.left, keyList);
            keyList.add(node.key);
            inOrder(node.right, keyList);
        }
    }

    /**
     * Performs a search for the min value in the chain.
     * Used in the delete method to find the replacement Node in case where it had 2 children
     * @param node Currently searched Node
     * @return Currently searched Node if it has no more eligible children, otherwise recursive itself.
     */
    private K findSmallestValue(Node node) {
        return node.right == null ? node.key : findSmallestValue(node.right);
    }

    /**
     * Return size of the tree.
     * @return size of the tree.
     */
    public int getSize() {
        return size;
    }
}
