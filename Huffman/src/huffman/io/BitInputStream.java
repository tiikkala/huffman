package huffman.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A stream of bits that can be read. Because they come from an underlying byte
 * stream, the total number of bits is always a multiple of 8.
 */
public class BitInputStream {

    private InputStream input; // underlying byte stream to read from
    private int nextByte;  // either in the range 0x00 to 0xFF if bits are available, or is -1 if the end of stream is reached
    private int numBitsRemaining; // always between 0 and 7, inclusive
    private boolean isEndOfStream;

    public BitInputStream(InputStream in) {
        if (in == null) {
            throw new NullPointerException("Argument is null");
        }
        this.input = in;
        this.numBitsRemaining = 0;
        this.isEndOfStream = false;
    }

    public int read() throws IOException {
        if (this.isEndOfStream) {
            return -1;
        }
        if (this.numBitsRemaining == 0) {
            this.nextByte = input.read();
            if (nextByte == -1) {
                this.isEndOfStream = true;
                return -1;
            }
            this.numBitsRemaining = 8;
        }
        this.numBitsRemaining--;
        return (this.nextByte >>> this.numBitsRemaining) & 1;
    }

    public int readNoEof() throws IOException {
        int result = this.read();
        if (result != -1) {
            return result;
        } else {
            throw new EOFException("End of stream reached");
        }
    }

    public void close() throws IOException {
        input.close();
    }
}
