package Classes;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

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

    public MyHashTable(){
        this(initM);
    }

    public MyHashTable(int M){
        if (M < 1)
            throw new IllegalArgumentException("Illegal Initial Chain Size: "+ M);
        chainArray = new HashNode[M];
        this.M = M;
        size = 0;
    }

    private int hash(K key){
        if (key == null) {
            throw new NullPointerException();
        }
        return (key.hashCode()) % M;
    }

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

    public V get(K key){
        int index = hash(key);
        HashNode<K, V> hashNode = findNodeKey(chainArray[index], key);
        if (hashNode != null){
            return hashNode.value;
        }
        return null;
    }

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

    public boolean contains(V value){
        return !(findNodeValue(value) == null);
    }

    public K getKey(V value){
        HashNode<K, V> hashNode = findNodeValue(value);
        return (hashNode == null) ? null : hashNode.key;
    }

    private HashNode<K, V> findNodeKey(HashNode<K, V> hashNode, K key){
        while (hashNode != null){
            if (hashNode.key.equals(key)){
                return hashNode;
            }
            hashNode = hashNode.next;
        }
        return null;
    }

    private HashNode<K, V> findNodePrevKey(HashNode<K, V> hashNode, K key){
        while (hashNode != null){
            if (hashNode.next != null && hashNode.next.key.equals(key)){
                return hashNode;
            }
            hashNode = hashNode.next;
        }
        return null;
    }

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

    public int getChainSize(int n){
        HashNode<K, V> hashNode = chainArray[n];
        int result = 0;
        while (hashNode != null){
            result++;
            hashNode = hashNode.next;
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public int getM(){
        return M;
    }
}
