package ikkala.huffmancompressor.datastructures;

import java.util.Arrays;

/**
 * A min binary heap data structure.
 */
public class BinaryHeap {

    private Comparable[] nodes;
    private int size = 0;

    /**
     * Default max size for binary heap is 12.
     */
    public BinaryHeap() {
        this.nodes = new Comparable[13]; // add 1 to account for root being at 1;
    }

    /**
     * @param maxSize Maximum size of the heap.
     */
    public BinaryHeap(int maxSize) {
        this.nodes = new Comparable[maxSize + 1]; // 
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Retrieve and remove the top of the heap. Heapify method is called to fix
     * the heap property.
     *
     * @return The object on top of the heap.
     */
    public Object poll() {
        Object head = this.nodes[1];
        this.nodes[1] = this.nodes[this.size]; // replace the root with the last node
        this.size--;
        this.heapify(1); // call heapify for the new root to fix heap property
        return head;
    }

    /**
     * Insert new node to the heap so that heap property is maintained. First
     * the size of the heap is increaded with one. Then the node is inserted to
     * the bottom of the heap and iteratively lifted to the correct place by
     * swapping it with parent nodes with greater value.
     *
     * @param node Node that is inserted.
     */
    public void insert(Comparable node) {
        // if heap size (+1 to account for root being at 1) is greater than the
        // size of the table hosting it, increase the size of the table
        if (this.size+1 == this.nodes.length) {
            this.doubleTheSizeOfTheTable();
        }
        this.size++;
        int index = this.size;
        int parentIndex = this.getParentIndex(index);
        while (index > 1 && this.nodes[parentIndex].compareTo(node) > 0) {
            this.swap(index, parentIndex);
            index = parentIndex;
            parentIndex = this.getParentIndex(index);
        }
        this.nodes[index] = node;
    }

    /**
     * Returns the head of the heap without removing it.
     *
     * @return the node on top of the heap.
     */
    public Object getHead() {
        return this.nodes[1];
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     *
     * @param nodes
     */
    public void setNodes(Comparable[] nodes) {
        this.nodes = nodes;
    }

    /**
     * Swap two nodes. Indexes of the nodes are given as parameters.
     *
     * @param index1 Index of the first node to be swapped.
     *
     * @param index2 Index of the second node to be swapped.
     */
    private void swap(int index1, int index2) {
        Comparable tmp = this.nodes[index1];
        this.nodes[index1] = this.nodes[index2];
        this.nodes[index2] = tmp;
    }

    /**
     * Internal method for doubling the size of the table hosting the heap. The
     * method is called from the insert method every time the table is too small for
     * the new extended heap.
     *
     * @return The table containing the heap doubled in capacity.
     */
    private void doubleTheSizeOfTheTable() {
        Comparable[] newTable = new Comparable[this.nodes.length * 2];
        for (int i = 0; i < this.nodes.length; i++) {
            newTable[i] = this.nodes[i];
        }     
        this.nodes = newTable;
    }

    private int getParentIndex(int index) {
        return index / 2;
    }

    private int leftChildIndex(int index) {
        return index * 2;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 1;
    }

    /**
     * Heapify fixes the heap property: for all 0 <= i <= heap-size - 1 holds
     * nodes[getParentIndex(i)] <= nodes[i]. The method starts from the node
     * nodes[index] and goes down the tree recursively all the way to the leaf
     * level. If the value of the node is smaller than the value of its
     * child(ren), it is swapped with the child with the smallest value. Time
     * and space complexity is O(logn).
     *
     * @param index Index from which the heapify operation is started.
     */
    private void heapify(int index) {
        int leftIndex = this.leftChildIndex(index);
        int rightIndex = this.rightChildIndex(index);
        // if both children exist, check which one is smaller and continue recursively
        if (rightIndex <= this.getSize()) {
            int minNodeIndex;
            if (this.nodes[leftIndex].compareTo(this.nodes[rightIndex]) < 0) {
                minNodeIndex = leftIndex;
            } else {
                minNodeIndex = rightIndex;
            }
            if (this.nodes[index].compareTo(this.nodes[minNodeIndex]) > 0) {
                swap(index, minNodeIndex);
                heapify(minNodeIndex);
            }
            // if only left child exists, check if it's smaller than the current node    
        } else if (leftIndex == this.getSize() && this.nodes[index].compareTo(this.nodes[leftIndex]) > 0) {
            swap(index, leftIndex);
        }
    }
}
