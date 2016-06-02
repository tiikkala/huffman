package huffman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for constructing frequency table from given text file.
 */
public class FrequencyTable {

    int[] freq;

    public FrequencyTable() {
        this.freq = new int[256];
    }

    public int[] buildTable(String file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrequencyTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (br != null) {
                String line;
                while ((line = br.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        this.freq[line.charAt(i)]++;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FrequencyTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.freq;
    }
}
