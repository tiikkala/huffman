package huffman;

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
        File inputFile = new File("testdata/big1.txt");
        File outputFile = new File("testdata/big1");
        // read input file once to compute symbol d1frequencies
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
        Encoder enc = new Encoder(out, hTree);
        try {
            enc.writeHeaderforDecoding();
            compress(enc, in, out);
        } finally {
            out.close();
            in.close();
        }
    }

    static void compress(Encoder encoder, InputStream in, BitOutputStream out) throws IOException {
        while (true) {
            int b = in.read();
            if (b == -1) {
                break;
            }
            encoder.write(b);
        }
    }
}
