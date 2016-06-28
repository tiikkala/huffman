package huffman.huffmantree;

import huffman.io.BitInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for decoding the canonical Huffman code.
 */
public final class Decoder {

    private int[] codeLenghtTable;
    private char[] characters;
    private long bitsRemaining = 0;
    private final BitInputStream input;
    private final String[] codes = new String[256];

    public Decoder(BitInputStream in) {
        this.input = in;
        this.readCode();
        this.rebuildCodeBook();
    }

    private void readCode() {
        this.bitsRemaining = this.input.readLong();
        int sizeOfCodeLenghtSequence = this.input.readInt();
        this.codeLenghtTable = new int[(sizeOfCodeLenghtSequence / Integer.BYTES)];
        int sizeOfCharacterSequence = this.input.readInt();
        this.characters = new char[(sizeOfCharacterSequence / Integer.BYTES)];
        for (int i = 0; i < this.codeLenghtTable.length; i++) {
            int next = this.input.readInt();
            this.codeLenghtTable[i] = next;
        }
        for (int i = 0; i < this.characters.length; i++) {
            this.characters[i] = (char) this.input.readInt();
        }
    }

    /**
     * Algorithm rebuilds the canonical codebook for decompressing the file. The
     * algorithm is described here
     * https://en.wikipedia.org/wiki/Canonical_Huffman_code
     */
    public void rebuildCodeBook() {
        int code = 1;
        int codeLenghtCounter = 0;
        // search the first code
        for (int i = 0; i < this.codeLenghtTable.length; i++) {
            if (this.codeLenghtTable[i] != 0) {
                codeLenghtCounter = i;
                code = code << 1;
                break;
            }
            code = code << 1;
        }
        StringBuilder representation;
        for (int i = 0; i < this.characters.length; i++) {
            representation = new StringBuilder(Integer.toBinaryString(code));
            representation.deleteCharAt(0);
            this.codes[this.characters[i]] = representation.toString();
            this.codeLenghtTable[codeLenghtCounter]--;
            if (representation.length() < this.codeLenghtTable.length) {
                // search next codelenght with count != 0
                while (this.codeLenghtTable[codeLenghtCounter] <= 0) {
                    codeLenghtCounter++;
                }
                code = (code + 1) << (codeLenghtCounter + 1 - representation.length());
            } else {
                code++;
            }
        }
    }

    public long getBitsRemaining() {
        return this.bitsRemaining;
    }

    public String[] getCodes() {
        return this.codes;
    }

    /**
     * Reads a code from the input file and returns the corresponding symbol
     * value. Everytime a bit is read, the bitsRemaining counter is decremented
     * with 1.
     *
     * @return symbol value of the code
     */
    public int read() {
        StringBuilder code = new StringBuilder();
        for (int j = 0; j < this.codeLenghtTable.length; j++) {
            try {
                code.append(String.valueOf(this.input.readBit()));
                this.bitsRemaining--;
            } catch (IOException ex) {
                Logger.getLogger(Decoder.class.getName()).log(Level.SEVERE, null, ex);
            }
            // put codes into hashmap for speed?
            for (int i = 0; i < this.codes.length; i++) {
                if (this.codes[i] == null) {
                    continue;
                }
                if (this.codes[i].equals(code.toString())) {
                    return i;
                }
            }
        }
        // should not reach here
        return -1;
    }
}
