package huffman;

import huffman.datastructures.Leaf;
import huffman.huffmantree.Encoder;
import huffman.huffmantree.HuffmanTree;
import huffman.huffmantree.HuffmanTreeBuilder;
import huffman.io.BitOutputStream;
import huffman.io.FrequencyTable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Huffman coding to compress an input text file to an output file. The
 * compressed file format contains the code length of each character under a
 * canonical code and list of characters ordered first by lenght then
 * alphabetically followed by the Huffman-coded data.
 */
public class Compress {

    public static void main(String[] args) throws IOException {
        // Show what command line arguments to use
//        if (args.length == 0) {
//            System.err.println("Usage: java io.Compress InputFile OutputFile");
//            System.exit(1);
//            return;
//        }
        // otherwise, compress
        File inputFile = new File("testdata/data2.txt");
        File outputFile = new File("testdata/compressedData2");
        // read input file once to compute symbol frequencies
        FrequencyTable freq = new FrequencyTable(inputFile);
        // build the code tree
        HuffmanTree hTree = new HuffmanTreeBuilder().buildTree(freq.getFrequencies());
        // build code book
        hTree.buildCodes(hTree.getRoot(), new StringBuilder());
        // canonize codes
        hTree.canonizeCodes();
        // read input file again, compress with Huffman coding and write ouput file
        InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
        BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
        try {
            writeCode(out, hTree);
            compress(hTree.getCanonizedCodes(), in, out);
        } finally {
            out.close();
            in.close();
        }
    }

    static void writeCode(BitOutputStream out, HuffmanTree hTree) throws IOException {
        int[] codeLenghts = hTree.getCanonizedCodeLenghts();
        char[] characters = hTree.getCharactersOrderedByCodeLenghts();
        // write lenght of codelenght table as the first four bytes
        out.writeIntegerAsByteArray(codeLenghts.length-1 * Integer.BYTES);
        // write lenght of the cahracter table as the next four bytes
        out.writeIntegerAsByteArray(characters.length * Integer.BYTES);
        // write codelenght table
        for (int i = 1; i < codeLenghts.length; i++) {
            out.writeIntegerAsByteArray(codeLenghts[i]);
        }
        // write character table
        for (int j = 0; j < characters.length; j++) {
            out.writeIntegerAsByteArray(characters[j]);
        }
    }

    static void compress(Leaf[] codeBook, InputStream in, BitOutputStream out) throws IOException {
        Encoder enc = new Encoder(out, codeBook);
        while (true) {
            int b = in.read();
            if (b == -1) {
                break;
            }
            enc.write(b);
        }
    }
}
