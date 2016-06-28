package huffman.datastructures;

/**
 * HashTable data structure for quickly retreiving the value associated with a key.

 * @param <K> Key.
 * @param <V> Value.
 */
public class HashTable<K, V> {
    
    /** An element of the HashTable.
     * 
     * @param <K> Type of the key
     * @param <V> Type of the value
     */
    private class Node<K, V>{
        K key;
        V value;
        Node<K,V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }      
    }
    
    Node<K, V>[] values;
    List<K> keyset;
    int size; 

    public HashTable(int size) {    
        this.size = size;
        this.values = new Node[size];
        this.keyset = new List<>(size);
    }
    
    /**
     * A simple hash function. [Work in progress].
     * 
     * @param key
     * 
     * @return 
     */
    private int hash(K key){
        return key.hashCode() % this.size;
    }
    
    /**
     * Returns the value associated with the key.
     * 
     * @param key The key the value of which is looked for.
     * 
     * @return Value associated with the key.
     */
    public V get(K key){
        Node<K, V> node = this.values[hash(key)];
        if(node == null){
            return null;
        }
        while(node.key.hashCode() != key.hashCode()){
            node = node.next;
            if(node == null){
                return null;
            }
        }
        return node.value;
    }
    
    /**
     * Method checks if the key exists in the hash table.
     * 
     * @param key The key that is looked for.
     * 
     * @return true if the key exists, ohterwise false.
     */
    public boolean containsKey(K key){
        Node node = this.values[hash(key)];
        while(node != null){
            if(node.key.hashCode() == key.hashCode()){
                return true;
            }
            node = node.next;
        }
        return false;
    }
    
    /**
     * Adds the given entry to the hash table
     * 
     * @param key Key of the enrty.
     * 
     * @param value Value of the entry.
     */
    public void put(K key, V value){  
        int index = hash(key);
        Node<K, V> node = values[index];
        if(node == null){
            node = new Node<>(key, value);
            this.values[hash(key)] = node;
            this.keyset.add(key);
            return;
        }
        if(node.key.hashCode() == key.hashCode()){
            node = new Node<>(key, value);
            node.next = this.values[index].next;
            this.values[index] = node;
            return;
        }
        Node<K, V> edellinen = node;
        while(node != null){
            if(node.key.hashCode() == key.hashCode()){
                Node seuraava = node.next;
                node = new Node<>(key, value);
                edellinen.next = node;
                node.next = seuraava;
                return;
            }
            edellinen = node;
            node = node.next;
        }
        node = new Node<>(key, value);
        keyset.add(key);
        edellinen.next = node;
    }
    
    /**
     * Returns a table with all the keys of the hash table.
     * 
     * @return Table with all the keys of the hash table.
     */
    public K[] keySet(){
        return this.keyset.get();
    }
    
}
