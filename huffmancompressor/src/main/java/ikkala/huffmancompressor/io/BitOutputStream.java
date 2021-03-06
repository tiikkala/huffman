package ikkala.huffmancompressor.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A stream where bits can be written to.
 */
public class BitOutputStream {

    private final OutputStream output; // underlying byte stream to write to
    private int currentByte;
    private int numBitsInCurrentByte; // always between 0 and 7, inclusive

    /**
     *
     * @param out
     */
    public BitOutputStream(OutputStream out) {
        if (out == null) {
            throw new NullPointerException("Argument is null");
        }
        this.output = out;
        this.currentByte = 0;
        this.numBitsInCurrentByte = 0;
    }

    /**
     * Writes integer to the stream as four bytes.
     *
     * @param i Integer to write.
     */
    public void writeIntegerAsByteArray(int i) {
            byte[] bytes = this.intToBytes(i);
            this.writeByteArray(bytes);
        }

    private void writeByteArray(byte[] bytes) {
        try {
            this.output.write(bytes);
            this.currentByte += bytes.length;
        } catch (IOException ex) {
            Logger.getLogger(BitOutputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes long to the strema as eight bytes.
     * 
     * @param l Long to write.
     */
    public void writeLongAsByteArray(long l) {
            byte[] bytes = this.longToBytes(l);
            this.writeByteArray(bytes);
    }

    private byte[] intToBytes(int i) {
        return new byte[]{
            (byte) (i >>> 24),
            (byte) (i >>> 16),
            (byte) (i >>> 8),
            (byte) i};
    }

    private byte[] longToBytes(long l) {
        byte[] result = new byte[Long.BYTES];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte) (l & 0xFF);
            l >>= Long.BYTES;
        }
        return result;
    }

    /**
     * Writes a character to the stream as two bytes.
     *
     * @param c Character to write.
     */
    public void writeChar(char c) {
        int symbol = (int) c;
        try {
            this.output.write(c);
        } catch (IOException ex) {
            Logger.getLogger(BitOutputStream.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes a bit to the stream.
     *
     * @param b Bit to write.
     */
    public void writeBit(int b) {
        if (!(b == 0 || b == 1)) {
            throw new IllegalArgumentException("Argument must be 0 or 1");
        }
        this.currentByte = this.currentByte << 1 | b;
        this.numBitsInCurrentByte++;
        if (this.numBitsInCurrentByte == 8) {
            try {
                this.output.write(this.currentByte);

            } catch (IOException ex) {
                Logger.getLogger(BitOutputStream.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            this.numBitsInCurrentByte = 0;
        }
    }

    /**
     * Closes this stream and the underlying OutputStream. If called when this
     * bit stream is not at a byte boundary, then the minimum number of zeros
     * (between 0 and 7) are written as padding to reach a byte boundary.
     */
    public void close() {
        try {
            while (numBitsInCurrentByte != 0) {
                writeBit(0);
            }
            output.close();

        } catch (IOException ex) {
            Logger.getLogger(BitOutputStream.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
