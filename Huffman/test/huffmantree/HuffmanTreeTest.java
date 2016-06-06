package huffmantree;

import huffman.huffmantree.HuffmanTreeBuilder;
import huffman.io.FrequencyTable;
import huffman.datastructures.Leaf;
import huffman.huffmantree.HuffmanTree;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Huffman coding algorithm.
 */
public class HuffmanTreeTest {

    int[] freq = new FrequencyTable().buildTable("testdata/data1.txt");
    private final int size = freq.length;
    HuffmanTree huffmanTree;

    @Before
    public void setUp() {
        this.huffmanTree = new HuffmanTreeBuilder().buildTree(freq);
    }

    @Test
    public void huffmanAlgorithmBuildsCorrectCodes() {
        String[] actualCodes = {"0", "100", "101", "111", "1100", "1101"};
        String[] obtainedCode = new String[6];
        this.huffmanTree.buildCodes(this.huffmanTree.getRoot(), new StringBuilder());
        int i = 0;
        while (!this.huffmanTree.getLeaves().isEmpty()) {
            Leaf leaf = (Leaf) this.huffmanTree.getLeaves().poll();
            obtainedCode[i] = leaf.getRepresentation();
            i++;
        }
        assertArrayEquals(actualCodes, obtainedCode);
    }

    @Test
    public void codesAreCanonizedCorrectly() {
        String[] correctlyCanonizedCodes = {"0", "100", "101", "110", "1110", "1111"};
        String[] obtaindeCodes = new String[6];
        this.huffmanTree.buildCodes(this.huffmanTree.getRoot(), new StringBuilder());
        this.huffmanTree.canonizeCodes();
        int i = 0;
        for (Leaf code : this.huffmanTree.getCanonizedCodes()) {
            obtaindeCodes[i] = code.getRepresentation();
            i++;
        }
        assertArrayEquals(correctlyCanonizedCodes, obtaindeCodes);
    }
}
