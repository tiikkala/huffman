package ikkala.huffmancompressor.datastructures;

/**
 * A simple list that supports two operations: adding an item to the list and 
 * returning the whole list. THe class is designed solely for the use the keySet()
 * method in HashTable.
 * 
 * @param <T>
 */
public class List<T>{
    
    T[] list;
    int end;
    
    /**
     *
     * @param size
     */
    public List(int size) {
        this.end = 0;      
        this.list = (T[]) new Object[size];
    }
    
    /**
     * Adds the given object to list.
     * 
     * @param lisattava 
     */
    public void add(T lisattava){
        this.list[this.end] = lisattava;
        this.end++;
        if(this. end >= this.list.length){
            this.increaseSize();
        }
    }
    
    /**
     * Double the size of the list.
     */
    private void increaseSize() {
        T[] newTable = (T[]) new Object[this.end*2];
        for (int i = 0; i < this.list.length; i++) {
            newTable[i] = this.list[i];
        }
        this.list = newTable;
    }
    
    /**
     * Returns the list as a table without empty enrties.
     * 
     * @return The current list.
     */
    public T[] get(){
        T[] rList = (T[]) new Object[this.end];
        for (int i = 0; i < rList.length; i++) {
            rList[i] = this.list[i];
        }
        return (T[]) rList;
    }    
}
