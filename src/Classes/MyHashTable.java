package Classes;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<K, V>[] chainArray;
    static private int M = 11;
    private int size;

    public MyHashTable(){
        this(M);
    }

    public MyHashTable(int M){
        if (M < 1)
            throw new IllegalArgumentException("Illegal Initial Chain Size: "+ M);
        this.chainArray = new HashNode[M];
        this.size = 0;
    }

    private int hash(K key){
        return 0;
    }

    public void put(K key, V value){

    }

    public V get(K key){
        return null;
    }

    public V remove(K key){
        return null;
    }

    public boolean contains(K key){
        return false;
    }

    public K getKey(V value){
        return null;
    }
}
