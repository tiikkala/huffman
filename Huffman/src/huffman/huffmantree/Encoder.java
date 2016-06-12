package huffman.huffmantree;

import huffman.datastructures.Leaf;
import huffman.io.BitOutputStream;
import java.io.IOException;

/**
 * Encoder class writes the encoded characters to the ouput stream.
 */
public final class Encoder {

    private BitOutputStream output;
    private Leaf[] codeBook;

    public Encoder(BitOutputStream out, Leaf[] codes) {
        if (out == null) {
            throw new NullPointerException("Argument is null");
        }
        this.output = out;
        this.codeBook = codes;
    }

    /**
     * Writes the code associated with the symbol given as a parameter to the
     * output stream.
     *
     * @param symbol The symbol to be coded.
     * @throws IOException
     */
    public void write(int symbol) throws IOException {
        if (this.codeBook == null) {
            throw new NullPointerException("Code book is null");
        }
        for (Leaf l : this.codeBook) {
            if (symbol == l.getChar()) {
                for (int i = 0; i < l.getRepresentation().length(); i++) {
                    this.output.writeBit(Character.getNumericValue(l.getRepresentation().charAt(i)));
                }            
            }
        }
    }
}
