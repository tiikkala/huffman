
import huffmancode.BinaryHeap.HuffmanAlgorithm;
import huffmancode.BinaryHeap.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Huffman coding algorithm.
 */
public class HuffmanAlgorithmTest {

    private char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};
    private int freq[] = {5, 9, 12, 13, 16, 45};
    private int size = characters.length;
    private Node huffmanTree;

    @Before
    public void setUp() {
        this.huffmanTree = new HuffmanAlgorithm().buildHuffmanTree(characters, freq, size);
    }
    
    @Test
    public void huffmanAlgorithmBuildsCorrectCode() {
        StringBuilder code = new StringBuilder();
        String[] codeTable = new String[256];
        String[] actualCodes = {"1100", "1101", "100", "101", "111", "0"};
        new HuffmanAlgorithm().buildCode(huffmanTree, codeTable, code);
        String[] obtainedCode = new String[6];
        int k = 0;
        for (int i = 0; i < 256; i++) {
            if (codeTable[i] != null) {
                obtainedCode[k] = codeTable[i];
                k++;
            }
        }
        assertArrayEquals(actualCodes, obtainedCode);
    }
}
