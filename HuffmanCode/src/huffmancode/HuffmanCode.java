package huffmancode;

import huffmancode.BinaryHeap.HuffmanAlgorithm;
import huffmancode.BinaryHeap.Node;

/**
 */
public class HuffmanCode {

    public static void main(String[] args) {
        int[] freq = new FrequencyTable().buildTable("testData/data1.txt");
        int size = freq.length;
        Node huffmanTree = new HuffmanAlgorithm().buildHuffmanTree(freq, size);
        String[] codeTable = new String[256];     
        StringBuilder code = new StringBuilder();
        new HuffmanAlgorithm().buildCode(huffmanTree, codeTable, code);
        new HuffmanAlgorithm().printCodes(codeTable);
    }

}
