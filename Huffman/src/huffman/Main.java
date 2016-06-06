package huffman;

import huffman.io.FrequencyTable;
import huffman.huffmantree.HuffmanTreeBuilder;
import huffman.datastructures.Node;
import huffman.huffmantree.HuffmanTree;

/**
 */
public class Main {

    public static void main(String[] args) {
        int[] freq = new FrequencyTable().buildTable("testdata/data1.txt");
        HuffmanTree hTree = new HuffmanTreeBuilder().buildTree(freq);
        hTree.buildCodes(hTree.getRoot(), new StringBuilder());
        hTree.canonizeCodes();
      // System.out.println(hTree.originalCodestoString());
      System.out.println(hTree.canonizedCodesToString());
    }

}
