package ikkala.huffmancompressor;

import huffman.huffmantree.Encoder;
import huffman.huffmantree.HuffmanTree;
import huffman.huffmantree.HuffmanTreeBuilder;
import huffman.io.BitOutputStream;
import huffman.huffmantree.FrequencyTable;
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
public class Compressor {

    private final File inputFile;
    private final File outputFile;

    public Compressor(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void compress() throws IOException {
        // read input file once to compute symbol d1frequencies
        FrequencyTable freq = new FrequencyTable(this.inputFile);
        // build the code tree       
        HuffmanTree hTree = new HuffmanTreeBuilder().buildTree(freq.getFrequencies());
        // build code book
        hTree.buildCodes(hTree.getRoot(), new StringBuilder());
        // canonize codes
        hTree.canonizeCodes();
        // read input file again, compress with Huffman coding and write ouput file
        InputStream in = new BufferedInputStream(new FileInputStream(this.inputFile));
        BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(this.outputFile)));
        Encoder enc = new Encoder(out, hTree);
        try {
            encode(enc, in, out);
        } finally {
            out.close();
            in.close();
        }
    }

    private void encode(Encoder encoder, InputStream in, BitOutputStream out) throws IOException {
        encoder.writeHeaderforDecoding();
        while (true) {
            int b = in.read();
            if (b == -1) {
                break;
            }
            encoder.write(b);
        }
    }
}
