package huffman.huffmantree;

import huffman.datastructures.BinaryHeap;
import huffman.datastructures.Leaf;
import huffman.datastructures.Node;

/**
 * Represents a Huffman tree. Class provides methods for building the Huffman
 * code and canonizing it.
 */
public class HuffmanTree {

    private Leaf[] canonizedCodes;
    private final Node root;
    private int maxCodeLength = 0; // needed for canonizing
    private BinaryHeap leaves = new BinaryHeap(); //  leaves in minheap so that they are easy
    //  to retrieve in the right order for canonizing

    public HuffmanTree(Node root) {
        this.root = root;
        this.buildCodes(root, new StringBuilder());
    }

    /**
     * Method builds a lookup table of character codes from the Huffman tree.
     * Starting from root, going to the left means appending 0 to the code,
     * going to the right appending 1. If node is a leaf, it holds a character
     * and its code is the path to the leaf. The leaf is inserted into the
     * leaves heap.
     *
     * @param node Tree from which the code is obtained.
     *
     * @param code Code holds the Huffman code of the current path.
     */
    private void buildCodes(Node node, StringBuilder code) {
        if (!node.isLeaf()) {
            buildCodes(node.getLeftChild(), code.append("0"));
            // remove the last character as it does not belong to the path
            // of the current node in the recursive loop
            code.deleteCharAt(code.length() - 1);
            buildCodes(node.getRightChild(), code.append("1"));
            code.deleteCharAt(code.length() - 1);
        } else {
            if (code.length() > this.maxCodeLength) {
                this.maxCodeLength = code.length();
            }
            Leaf leaf = new Leaf(node.getChar(), node.getFreq(), code.toString());
            this.leaves.insert(leaf);
        }
    }

    public void canonizeCodes() {
        this.canonizedCodes = new Leaf[this.getLeaves().getSize()];
        int k = 0;
        int code = 0;
        while (!this.getLeaves().isEmpty()) {
            Leaf currentLeaf = (Leaf) this.getLeaves().poll();
            if (Integer.bitCount(code) < currentLeaf.getRepresentation().length()) {
                code = code << 1;
            }
            currentLeaf.setRepresentation(Integer.toBinaryString(code));
            this.canonizedCodes[k] = currentLeaf;
            k++;
            code++;
        }
    }

    public Node getRoot() {
        return this.root;
    }

    public BinaryHeap getLeaves() {
        return this.leaves;
    }

    public int getMaxCodeLenght() {
        return this.maxCodeLength;
    }

    public String originalCodestoString() {
        StringBuilder print = new StringBuilder();
        while (!this.leaves.isEmpty()) {
            Leaf leaf = (Leaf) this.leaves.poll();
            print.append(leaf.getChar() + ": " + leaf.getRepresentation() + "\n");
        }
        return print.toString();
    }

    public String canonizedCodesToString() {
        StringBuilder print = new StringBuilder();
        this.canonizeCodes();
        for (Leaf leaf : this.canonizedCodes) {
            print.append(leaf.getChar() + ": " + leaf.getRepresentation() + "\n");
        }
        return print.toString();
    }
}
