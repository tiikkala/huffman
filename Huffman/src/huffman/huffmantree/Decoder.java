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
    private long bitsRemaining;
    private final BitInputStream input;
    String[] codes = new String[256];

    public Decoder(BitInputStream in) {
        this.input = in;
        this.readCode();
        this.rebuildCodeBook();
    }

    private void readCode() {
        int sizeOfCodeLenghtSequence = this.input.readInt();
        this.codeLenghtTable = new int[sizeOfCodeLenghtSequence];
        int sizeOfCharacterSequence = this.input.readInt();
        this.characters = new char[sizeOfCharacterSequence];
        for (int i = 0; i < sizeOfCodeLenghtSequence; i++) {
            int next = this.input.readInt();
            this.bitsRemaining += next * i;
            this.codeLenghtTable[i] = next;
        }
        for (int i = 0; i < sizeOfCharacterSequence; i++) {
            this.characters[i] = (char) this.input.readInt();
        }
    }

    /**
     * Algorithm rebuilds the canonical codebook for decompressing the file. The
     * algorithm is described here
     * https://en.wikipedia.org/wiki/Canonical_Huffman_code
     */
    public void rebuildCodeBook() {
        int code = 0;
        int k = 0;
        this.codeLenghtTable[0]--;
        for (int i = 0; i < this.characters.length; i++) {
            this.codes[this.characters[i]] = Integer.toBinaryString(code);
            if (Integer.toBinaryString(code).length() < this.codeLenghtTable.length) {
                // search next codelenght with count != 0
                while (this.codeLenghtTable[k] <= 0) {
                    k++;
                }
                code = (code + 1) << (k - Integer.toBinaryString(code).length());
                this.codeLenghtTable[k]--;
            } else {
                code++;
            }
        }
    }

    public long getBitsRemaining() {
        return this.bitsRemaining;
    }

    public char read() {
        StringBuilder code = new StringBuilder();
        try {
            code.append(String.valueOf(this.input.readBit()));
            this.bitsRemaining--;
            for (int i = 0; i < this.codes.length; i++) {
                if (this.codes[i].equals(code.toString())) {
                    return (char) i;
                } else {
                    try {
                        code.append(Integer.toString(this.input.readBit()));
                    } catch (IOException ex) {
                        Logger.getLogger(Decoder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Decoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
