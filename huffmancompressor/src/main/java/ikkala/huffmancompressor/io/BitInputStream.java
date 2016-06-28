package ikkala.huffmancompressor.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A stream of bits that can be read. Because they come from an underlying byte
 * stream, the total number of bits is always a multiple of 8.
 */
public class BitInputStream {

    private InputStream input; // underlying byte stream to read from
    private int nextByte;  // either in the range 0x00 to 0xFF if bits are available
    private int numBitsRemaining; // always between 0 and 7, inclusive
    private boolean isEndOfStream;

    /**
     * Creates a bit input stream based on the given byte input stream.
     *
     * @param in
     */
    public BitInputStream(InputStream in) {
        if (in == null) {
            throw new NullPointerException("Argument is null");
        }
        this.input = in;
        this.numBitsRemaining = 0;
        this.isEndOfStream = false;
    }

    /**
     * Reads four bytes from the stream and converts them into int.
     *
     * @return The read bytes converted to int.
     */
    public int readInt() {
        try {
            byte[] bytes = new byte[Integer.BYTES];
            this.input.read(bytes);
            ByteBuffer bb = ByteBuffer.wrap(bytes);
            return bb.getInt();
        } catch (IOException ex) {
            Logger.getLogger(BitInputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Reads eight bytes from the stream and converts them into long,
     * 
     * @return The read bytes converted to long
     */
    public long readLong() {
        try {
            byte[] bytes = new byte[Long.BYTES];
            this.input.read(bytes);
            ByteBuffer bb = ByteBuffer.wrap(bytes);
            return bb.getLong();
        } catch (IOException ex) {
            Logger.getLogger(BitInputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Reads a bit from the stream. Returns 0 or 1 if a bit is available, or -1
     * if the end of stream is reached. The end of stream always occurs on a
     * byte boundary.
     *
     * @return 0 or 1 if a bit is available, -1 is end of stream is reached.
     *
     * @throws IOException
     */
    public int readBit() throws IOException {
        if (this.isEndOfStream) {
            return -1;
        }
        if (this.numBitsRemaining == 0) {
            this.nextByte = this.input.read();
            if (this.nextByte == -1) {
                this.isEndOfStream = true;
                return -1;
            }
            this.numBitsRemaining = 8;
        }
        this.numBitsRemaining--;
        return (this.nextByte >>> this.numBitsRemaining) & 1;
    }
    
    /**
     *
     * @throws IOException
     */
    public void close() throws IOException {
        input.close();
    }
}
