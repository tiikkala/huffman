package ikkala.huffmancompressor;

import ikkala.huffmancompressor.huffmantree.Decoder;
import ikkala.huffmancompressor.io.BitInputStream;
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
public class Decompressor {
    
    private final File inputFile;
    private final File outputFile;
    
    /**
     *
     * @param inputFile
     * @param outputFile
     */
    public Decompressor(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     *
     * @throws IOException
     */
    public void decompress() throws IOException {
        BitInputStream in = new BitInputStream(new BufferedInputStream(new FileInputStream(this.inputFile)));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(this.outputFile));
        try {
            decode(in, out);
        } finally {
            out.close();
            in.close();
        }
    }

    private void decode(BitInputStream in, OutputStream out) throws IOException {
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
