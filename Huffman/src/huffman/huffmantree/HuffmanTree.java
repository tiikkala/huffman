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
    private long compressedFileLenght;
    private int maxCodeLenght = 0; // maximum lenght of codes used
    private int[] symbolsOrderedByCodeLength; // characters used in source file sorted for decoding
    private int[] canonizedCodeLengths; // bit-lenghts of the canonical codes for decoding
    private BinaryHeap leaves = new BinaryHeap(); //  leaves are stored in minheap so that they are easy
    //  to retrieve in the right order for canonization

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
            if (code.length() > this.maxCodeLenght) {
                this.maxCodeLenght = code.length();
            }
            this.compressedFileLenght += node.getFreq() * code.toString().length();
            Leaf leaf = new Leaf(node.getSymbol(), node.getFreq(), code.toString());
            this.leaves.insert(leaf);
        }
    }

    /**
     * Generates <a href="https://en.wikipedia.org/wiki/Canonical_Huffman_code">
     * the canonical Huffman code</a> of the Huffman tree. Workings of the
     * algorithm is described here
     * http://stackoverflow.com/questions/15081300/storing-and-reconstruction-of-huffman-tree
     */
    public void canonizeCodes() {
        // set the capacity of the codelenght count array to maxcodelength + 1
        // so the index of the table corresponds to the lenght of the code
        this.canonizedCodeLengths = new int[this.getMaxCodeLenght() + 2];
        int[] symbols = new int[this.getLeaves().getSize()];
        Leaf[] codes = new Leaf[this.getLeaves().getSize()];
        int codeLengthCounter = 0;
        int code = 1;
        int k = 0;
        Leaf currentLeaf;
        while (!this.getLeaves().isEmpty()) {
            currentLeaf = (Leaf) this.getLeaves().poll();
            // if the current code is shorter than the original Huffman code, add zeros to 
            // the right end
            while (codeLengthCounter < currentLeaf.getRepresentation().length()) {
                code = code << 1;
                codeLengthCounter++;
            }
            // replace the original code with canonized code
//            StringBuilder paddedCode = new StringBuilder();
//            paddedCode.append(Integer.toBinaryString(code));
//            while (paddedCode.length() < codeLengthCounter) {
//                paddedCode.append("0");
//            }
            // increment the code with 1
            currentLeaf.setRepresentation(Integer.toBinaryString(code));
            // store the length of the code into an array
            this.canonizedCodeLengths[Integer.toBinaryString(code).length()]++;
            // store the character
            symbols[k] = currentLeaf.getSymbol();
            // store the canonized code
            codes[k] = currentLeaf;
            k++;
            code++;
        }
        this.canonizedCodes = codes;
        this.symbolsOrderedByCodeLength = symbols;
    }

    public long getCompressedFileLenght() {
        return this.compressedFileLenght;
    }

    public Leaf[] getCanonizedCodes() {
        return this.canonizedCodes;
    }

    public int[] getCanonizedCodeLenghts() {
        return this.canonizedCodeLengths;
    }

    public int[] getSymbolsOrderedByCodeLenghts() {
        return this.symbolsOrderedByCodeLength;
    }

    public Node getRoot() {
        return this.root;
    }

    public BinaryHeap getLeaves() {
        return this.leaves;
    }

    public int getMaxCodeLenght() {
        return this.maxCodeLenght;
    }

    public String canonizedCodesToString() {
        StringBuilder print = new StringBuilder();
        for (Leaf leaf : this.canonizedCodes) {
            if (leaf != null) {
                print.append((char) leaf.getSymbol()).append(": ").append(leaf.getRepresentation()).append("\n");
            }
        }
        return print.toString();
    }
}
