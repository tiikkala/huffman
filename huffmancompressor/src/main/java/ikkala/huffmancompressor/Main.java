package ikkala.huffmancompressor;

import java.io.File;
import java.io.IOException;

/**
 * The main executable of the Huffman compressor.
 */
public class Main {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: java -jar c/d huffmancompressor-1.0-SNAPSHOT.jar");
            System.exit(1);
            return;
        }
        File inputFile = new File(args[1]);
        File outputFile = new File(args[2]);
        Diagnostics diag = new Diagnostics();
        diag.setOriginalFileSize(inputFile);
        diag.startTimer();
        if (null != args[0]) {
            switch (args[0]) {
                case "c": {
                    new Compressor(inputFile, outputFile).compress();
                    break;
                }
                case "d": {
                    new Decompressor(inputFile, outputFile).decompress();
                    break;
                }
                default:
                    System.err.println("First argument needs to be c or d");
                    System.exit(1);
                    return;
            }
        }
        diag.stopTimer();
        diag.compareFileSizes(outputFile);
    }
}
