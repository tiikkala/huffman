package huffman.datastructures;

/**
 * Represents a leaf of a HuffmanTree. 
 */
public class Leaf extends Node {
    
    private String representation; // String representation of the binary code

    public Leaf(char character, long freq, String representation) {
        super(character, freq);
        this.representation = representation;
    }
    
    public String getRepresentation() {
        return this.representation;
    }
    
    public void setRepresentation(String representation) {
        this.representation = representation;
    }
    
    /**
     * The leaves are compared first according to the length of their binary codes
     * in ascending order, then according to the alphabetical order of 
     * the characters they are representing.This allows canonizing the codes.
     * 
     * @param o Compared leaf 
     * 
     * @return standard compareTo
     */
    @Override
    public int compareTo(Object o) {
        Leaf comparedLeaf = (Leaf) o;
        if (this.getRepresentation().length() > comparedLeaf.getRepresentation().length()) {
            return 1;
        }
        if (this.getRepresentation().length() < comparedLeaf.getRepresentation().length()) {
            return -1;
        }
        if (this.getChar() > comparedLeaf.getChar()) {
            return 1;
        }
        if (this.getChar() < comparedLeaf.getChar()) {
            return -1;
        }
        return 0;
    }
    
}
