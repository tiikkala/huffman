package huffman.huffmantree;

import huffman.datastructures.BinaryHeap;
import huffman.datastructures.Node;

/**
 * This algorithm builds the Huffman tree from given character and frequency
 * tables and the size of the character set.
 */
public class HuffmanTreeBuilder { 
    
    /**
     * This internal method creates a min heap representation of the input data.
     *
     * @param freq Frequency table of the ASCII characters.
     * @param size The number of different characters.
     * @return Characters stored in a min heap according to their frequency.
     */
    private BinaryHeap createAndBuildHeap(int[] freq) {
        BinaryHeap heap = new BinaryHeap(freq.length);
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                heap.insert(new Node((char) i, freq[i]));
            }
        }
        return heap;
    }

    /**
     * Method builds the Huffman tree of the input data. First the algorithm
     * arranges the characters accoriding to their frequency into a min heap.
     * Then algorithm iterate through the heap and extracts to characters with
     * minimum frequency, creates a new node by summing their frequencies and
     * setting them as children of th new node, and adding this new node to the
     * heap. Algorithm is finished, when the heap contains only one node, that
     * is the root node of the Huffman tree.
     *
     * @param freq Frequencies of the characters soterd in array.
     * @return Huffman tree representing the characters.
     */
    public HuffmanTree buildTree(int[] freq) {
        Node left;
        Node right;
        Node root;
        BinaryHeap heap = createAndBuildHeap(freq);
        while (heap.getSize() > 1) {
            left = (Node) heap.poll();
            right = (Node) heap.poll();
            // the ¤ character is never used, the actual characters from the
            // file are in the leaf nodes
            root = new Node('¤', left.getFreq() + right.getFreq());
            root.setLeftChild(left);
            root.setRigthChild(right);
            heap.insert(root);
        }
        root = (Node) heap.poll();
        HuffmanTree hTree = new HuffmanTree(root);
        return hTree;
    }
}
