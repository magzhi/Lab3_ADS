package Classes;

/**
 * Implementation of a hash table.
 * @param <K> The type of keys maintained.
 * @param <V> The type of mapped values.
 */
public class MyHashTable<K, V> {
    /**
     * Node class representing an element in the hash table's chain as a linked list.
     * @param <K> The type of keys maintained.
     * @param <V> The type of mapped values.
     */
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        /**
         * Constructor to create a new Node with the given key, value and next Node.
         * @param key The key of the node.
         * @param value The value associated with the key.
         * @param next The next Node from the current one in the chain.
         */
        public HashNode(K key, V value, HashNode next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<K, V>[] chainArray;
    private int M;
    static private int initM = 11;
    private int size;

    /**
     * Default constructor initializing the hash table with a default capacity of initM.
     */
    public MyHashTable(){
        this(initM);
    }

    /**
     * Constructor initializing the hash table with a specified capacity.
     * @param M The initial number of buckets in the hash table.
     */
    public MyHashTable(int M){
        if (M < 1)
            throw new IllegalArgumentException("Illegal Initial Chain Size: "+ M);
        chainArray = new HashNode[M];
        this.M = M;
        size = 0;
    }

    /**
     * Computes the hash code for the given key.
     * Safety checks first in case of a null is given as a key.
     * @param key The key for which the hash code is to be computed.
     * @return The hash code of the key.
     */
    private int hash(K key){
        if (key == null) {
            throw new NullPointerException();
        }
        return (key.hashCode()) % M;
    }

    /**
     * Adds a key-value pair to the hash table. If the key already exists, updates its value.
     * Safety checks first in case of a null is given as a key.
     * @param key The key to be added or updated.
     * @param value The value to be associated with the key.
     */
    public void put(K key, V value){
        if (value == null) {
            throw new NullPointerException();
        }

        int index = hash(key);
        HashNode<K, V> hashNode = findNodeKey(chainArray[index], key);
        if (hashNode != null){
            hashNode.value = value;
            //System.out.println("Debug: Value got Overridden");
            return;
        }
        chainArray[index] = new HashNode<>(key, value, chainArray[index]);
        size++;
    }

    /**
     * Retrieves the value associated with the given key.
     * @param key The key whose associated value is to be returned.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V get(K key){
        int index = hash(key);
        HashNode<K, V> hashNode = findNodeKey(chainArray[index], key);
        if (hashNode != null){
            return hashNode.value;
        }
        return null;
    }

    /**
     * Removes the key-value pair associated with the given key from the hash table.
     * @param key The key to be removed.
     * @return The value associated with the removed key, or null if the key is not found.
     */
    public V remove(K key){
        int index = hash(key);
        HashNode<K, V> previous = findNodePrevKey(chainArray[index], key);
        HashNode<K, V> found = previous.next;
        if (previous == null){
            found = findNodeKey(chainArray[index], key);
            chainArray[index] = found.next;
            size--;
            return found.value;
        }
        if (found != null){
            previous.next = found.next;
            size--;
            return found.value;
        }
        return null;
    }

    /**
     * Checks if the hash table contains the given value.
     * @param value The value to be checked.
     * @return True if the value is found, false otherwise.
     */
    public boolean contains(V value){
        return !(findNodeValue(value) == null);
    }

    /**
     * Retrieves the key associated with the given value.
     * @param value The value whose associated key is to be returned.
     * @return The key associated with the value, or null if the value is not found.
     */
    public K getKey(V value){
        HashNode<K, V> hashNode = findNodeValue(value);
        return (hashNode == null) ? null : hashNode.key;
    }

    /**
     * Support function used to find a specific Node.
     * @param hashNode Currently searched Node.
     * @param key The key that is being searched.
     * @return The Node associated with the key, or null if the Node is not found.
     */
    private HashNode<K, V> findNodeKey(HashNode<K, V> hashNode, K key){
        while (hashNode != null){
            if (hashNode.key.equals(key)){
                return hashNode;
            }
            hashNode = hashNode.next;
        }
        return null;
    }

    /**
     * Support function used to find a parent of a specific Node.
     * @param hashNode Currently searched Node.
     * @param key The key that is being searched.
     * @return The Node associated with the key, or null if the Node is not found.
     */
    private HashNode<K, V> findNodePrevKey(HashNode<K, V> hashNode, K key){
        while (hashNode != null){
            if (hashNode.next != null && hashNode.next.key.equals(key)){
                return hashNode;
            }
            hashNode = hashNode.next;
        }
        return null;
    }

    /**
     * Support function used to find a specific Node based on value.
     * It iterates through every chain until it finds value.
     * @param value Currently searched Node.
     * @return The Node associated with the value, or null if the Node is not found.
     */
    private HashNode<K, V> findNodeValue(V value){
        for (int i = 0; i < M; i++) {
            HashNode<K, V> hashNode = chainArray[i];
            while (hashNode != null){
                if (hashNode.value.equals(value)){
                    return hashNode;
                }
                hashNode = hashNode.next;
            }
        }
        return null;
    }

    /**
     * Support function used to find the size of the selected chain.
     * @param n The selected chain from chainArray[n].
     * @return The size of the selected chain.
     */
    public int getChainSize(int n){
        HashNode<K, V> hashNode = chainArray[n];
        int result = 0;
        while (hashNode != null){
            result++;
            hashNode = hashNode.next;
        }
        return result;
    }

    /**
     * Returns size of the hash table.
     * @return size of the hash table.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns capacity of the hash table.
     * @return capacity of the hash table.
     */
    public int getM(){
        return M;
    }
}
