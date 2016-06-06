package huffman.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides methods for writing Huffman encoded files.
 */
public class EncodedFileWriter {

    private FileWriter fWriter;
    private BufferedWriter writer;
    int[] freq;

    public EncodedFileWriter(String pathToFile, String fileName) {
        try {
            this.freq = new FrequencyTable().buildTable(pathToFile);
            this.fWriter = new FileWriter("fileName");
            this.writer = new BufferedWriter(fWriter);
        } catch (IOException ex) {
            Logger.getLogger(EncodedFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public 
    
}
