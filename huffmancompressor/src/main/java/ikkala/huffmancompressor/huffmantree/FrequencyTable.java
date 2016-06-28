package ikkala.huffmancompressor.huffmantree;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for constructing frequency table from the given file.
 */
public final class FrequencyTable {

    private int[] freq;

    /**
     *
     * @param file
     */
    public FrequencyTable(File file) {
        this.freq = new int[256];
        try {
            this.buildTable(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrequencyTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method reads the file byte by byte and updates the frequency table on the
     * go.
     *
     * @param file File to be compressed.
     *
     * @throws FileNotFoundException
     */
    private void buildTable(File file) throws FileNotFoundException {
        InputStream input = new BufferedInputStream(new FileInputStream(file));
        try {
            while (true) {
                int b = input.read();
                if (b == -1) {
                    break;
                }
                this.increment(b);
            }
        } catch (IOException ex) {
            Logger.getLogger(FrequencyTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(FrequencyTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void increment(int symbol) {
        if (this.freq[symbol] == Integer.MAX_VALUE) {
            throw new RuntimeException("Arithmetic overflow");
        }
        this.freq[symbol]++;
    }

    /**
     *
     * @return
     */
    public int[] getFrequencies() {
        return this.freq;
    }
}
