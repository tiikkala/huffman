package huffman.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A stream where bits can be written to.
 */
public class BitOutputStream {

    private final OutputStream output; // underlying byte stream to write to
    private int currentByte; // always in the range 0x00 to 0xFF
    private int numBitsInCurrentByte; // always between 0 and 7, inclusive

    public BitOutputStream(OutputStream out) {
        if (out == null) {
            throw new NullPointerException("Argument is null");
        }
        this.output = out;
        currentByte = 0;
        numBitsInCurrentByte = 0;
    }

    /**
     * Writes a bit to the stream.
     *
     * @param b Bit to write.
     */
    public void write(int b) {
        if (!(b == 0 || b == 1)) {
            throw new IllegalArgumentException("Argument must be 0 or 1");
        }
        this.currentByte = this.currentByte << 1 | b;
        this.numBitsInCurrentByte++;
        if (this.numBitsInCurrentByte == 8) {
            try {
                output.write(this.currentByte);
            } catch (IOException ex) {
                Logger.getLogger(BitOutputStream.class.getName()).log(Level.SEVERE, null, ex);
                numBitsInCurrentByte = 0;
            }
        }
    }

    /**
     * Closes this stream and the underlying OutputStream. If called when this
     * bit stream is not at a byte boundary, then the minimum number of zeros
     * (between 0 and 7) are written as padding to reach a byte boundary.
     *
     */
    public void close() {
        try {
            while (numBitsInCurrentByte != 0) {
                write(0);
            }
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(BitOutputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
