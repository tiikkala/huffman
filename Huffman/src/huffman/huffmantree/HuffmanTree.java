package huffman.huffmantree;

import huffman.datastructures.BinaryHeap;
import huffman.datastructures.Leaf;
import huffman.datastructures.Node;

/**
 * Represents a Huffman tree. Class provides methods for building the Huffman
 * code, canonizing it for efficient compressing and decoding, and rebuilding a
 * canonical Huffman tree from codelenghts of the canonized codes.
 */
public class HuffmanTree {

    private Leaf[] canonizedCodes;
    private final Node root;
    private final int[] bitLengths = new int[256]; // bit-lenghts of the canonical codes allows decoding of a compressed file
    private BinaryHeap leaves = new BinaryHeap(); //  leaves are stored in minheap so that they are easy
    //  to retrieve in the right order for canonizing

    public HuffmanTree(Node root) {
        this.root = root;
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
    public void buildCodes(Node node, StringBuilder code) {
        if (!node.isLeaf()) {
            buildCodes(node.getLeftChild(), code.append("0"));
            // remove the last character as it does not belong to the path
            // of the current node in the recursive loop
            code.deleteCharAt(code.length() - 1);
            buildCodes(node.getRightChild(), code.append("1"));
            code.deleteCharAt(code.length() - 1);
        } else {
            Leaf leaf = new Leaf(node.getChar(), node.getFreq(), code.toString());
            this.leaves.insert(leaf);
        }
    }

    /**
     * Generates <a href="https://en.wikipedia.org/wiki/Canonical_Huffman_code">
     * the canonical Huffman code</a> of the Huffman tree. Workings of the
     * algorithm is described here
     * http://stackoverflow.com/questions/15081300/storing-and-reconstruction-of-huffman-tree
     *
     * @return Array of leaves that holds the coded symbols and canonized
     * Huffman codes.
     */
    public void canonizeCodes() {
        Leaf[] codes = new Leaf[this.getLeaves().getSize()];
        int k = 0;
        int code = 0;
        while (!this.getLeaves().isEmpty()) {
            Leaf currentLeaf = (Leaf) this.getLeaves().poll();
            while (Integer.toBinaryString(code).length() < currentLeaf.getRepresentation().length()) {
                code = code << 1;
            }
            currentLeaf.setRepresentation(Integer.toBinaryString(code));
            codes[k] = currentLeaf;
            k++;
            code++;
        }
        this.canonizedCodes = codes;
    }
    
    public Leaf[] getCanonizedCodes() {
        return this.canonizedCodes;
    }

    public Node getRoot() {
        return this.root;
    }

    public BinaryHeap getLeaves() {
        return this.leaves;
    }

//    public String originalCodestoString() {
//        StringBuilder print = new StringBuilder();
//        while (!this.originalCodes.isEmpty()) {
//            Leaf currentLeaf = (Leaf) this.originalCodes.poll();
//            print.append(currentLeaf.getChar() + ": " + currentLeaf.getRepresentation() + "\n");
//        }
//        return print.toString();
//    }

    public String canonizedCodesToString() {
        StringBuilder print = new StringBuilder();
        for (Leaf leaf : this.canonizedCodes) {
            if (leaf != null) {
                print.append(leaf.getChar()).append(": ").append(leaf.getRepresentation()).append("\n");
            }
        }
        return print.toString();
    }
}