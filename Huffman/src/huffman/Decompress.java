package huffman;

import huffman.huffmantree.Decoder;
import huffman.io.BitInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Decompresses an input file that was comppressed with canonical Huffman coding
 * to an output file.
 */
public final class Decompress {

    public static void main(String[] args) throws IOException {
		// Show what command line arguments to use
//		if (args.length == 0) {
//			System.err.println("Usage: java HuffmanDecompress InputFile OutputFile");
//			System.exit(1);
//			return;
//		}

        // Otherwise, decompress
        File inputFile = new File("testdata/compressedData1");
        File outputFile = new File("testdata/testData1.txt");

        BitInputStream in = new BitInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
        try {
            decompress(in, out);
        } finally {
            out.close();
            in.close();
        }
    }

    static void decompress(BitInputStream in, OutputStream out) throws IOException {
        Decoder dec = new Decoder(in);
        while (dec.getBitsRemaining() > 0) {
            int symbol = dec.read();
            if (symbol >= 0) {
                out.write(symbol);
            }
            if (symbol == -1) {
                break;
            }
        }
    }
}
