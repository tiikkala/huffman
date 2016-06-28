package huffmantree;

import huffman.huffmantree.HuffmanTreeBuilder;
import huffman.huffmantree.FrequencyTable;
import huffman.datastructures.Leaf;
import huffman.huffmantree.HuffmanTree;
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Huffman coding algorithm.
 */
public class HuffmanTreeTest {

    int[] freq = new FrequencyTable(new File("testdata/data1.txt")).getFrequencies();
    private final int size = freq.length;
    HuffmanTree huffmanTree;

    @Before
    public void setUp() {
        this.huffmanTree = new HuffmanTreeBuilder().buildTree(freq);
    }

    @Test
    public void huffmanAlgorithmBuildsCorrectCodes() {
        String[] actualCodes = {"0", "100", "101", "111", "1101", "11000", "11001"};
        String[] obtainedCode = new String[7];
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
        String[] correctlyCanonizedCodes = {"0", "100", "101", "110", "1110", "11110", "11111"};
        String[] obtaindeCodes = new String[7];
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
