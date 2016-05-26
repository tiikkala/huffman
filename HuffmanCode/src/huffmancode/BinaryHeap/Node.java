package huffmancode.BinaryHeap;

/**
 * All characters in the source file are associated with a node
 * that holds its frequency in the source file.
 */
public class Node implements Comparable {
    
    private final char character;
    private final int freq;
    private Node left, right;
    
    public Node(char character, int freq) {
        this.character = character;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    /**
     * Nodes are compared according to their frequency in the
     * source file.
     * 
     * @param o Compared node.
     * 
     * @return 0 If freqs are equal, <0 if the parameter nodes freq is
     * greater, >0 if the parameter nodes freq is less.
     */
    @Override
    public int compareTo(Object o) {
        Node comparedNode = (Node) o;
        return this.freq - comparedNode.freq;
    }
    
    public Node getLeftChild() {
        return this.left;
    }
    
    public Node getRightChild() {
        return this.right;
    }
    
    public int getFreq() {
        return this.freq;
    }
    
    public char getChar() {
        return this.character;
    }
    
    public void setLeftChild(Node node) {
        this.left = node;
    }
    
    public void setRigthChild(Node node) {
        this.right = node;
    }
    
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
    
}
