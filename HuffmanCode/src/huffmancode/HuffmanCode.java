package huffmancode;

import huffmancode.BinaryHeap.HuffmanAlgorithm;
import huffmancode.BinaryHeap.Node;

/**
 */
public class HuffmanCode {

    public static void main(String[] args) {
        char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};
        int freq[] = {5, 9, 12, 13, 16, 45};
        int size = characters.length;
        Node huffmanTree = new HuffmanAlgorithm().buildHuffmanTree(characters, freq, size);
        String[] codeTable = new String[256];     
        StringBuilder code = new StringBuilder();
        new HuffmanAlgorithm().buildCode(huffmanTree, codeTable, code);
        new HuffmanAlgorithm().printCodes(codeTable);
    }

}
