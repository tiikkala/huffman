package ikkala.huffmancompressor.datastructures;

/**
 * All characters in the source file are associated with a node
 * that holds its frequency in the source file.
 */
public class Node implements Comparable {
    
    private final int symbol;

    /**
     *
     */
    protected final long freq;
    private Node left, right;
    
    /**
     *
     * @param symbol
     * @param freq
     */
    public Node(int symbol, long freq) {
        this.symbol = symbol;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    /**
     * Nodes are compared according to the frequency of the character attribute
     * in the source file in descenging order.
     * 
     * @param o Compared node
     * 
     * @return standard compareTo
     */
    @Override
    public int compareTo(Object o) {
        Node comparedNode = (Node) o;
        if (this.freq < comparedNode.getFreq()) {
            return -1;
        }
        if (this.freq == comparedNode.freq) {
            return 0;
        }
        return 1;
    }
    
    /**
     *
     * @return
     */
    public Node getLeftChild() {
        return this.left;
    }
    
    /**
     *
     * @return
     */
    public Node getRightChild() {
        return this.right;
    }
    
    /**
     *
     * @return
     */
    public long getFreq() {
        return this.freq;
    }
    
    /**
     *
     * @return
     */
    public int getSymbol() {
        return this.symbol;
    }
    
    /**
     *
     * @param node
     */
    public void setLeftChild(Node node) {
        this.left = node;
    }
    
    /**
     *
     * @param node
     */
    public void setRigthChild(Node node) {
        this.right = node;
    }
    
    /**
     *
     * @return
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
    
}
