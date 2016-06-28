package ikkala.huffmancompressor.huffmantree;

import ikkala.huffmancompressor.datastructures.Leaf;
import ikkala.huffmancompressor.io.BitOutputStream;
import java.io.IOException;

/**
 * Encoder class writes the encoded characters to the ouput stream.
 */
public class Encoder {

    private BitOutputStream output;
    private HuffmanTree hTree;

    /**
     * @param out Stream of bits where the compressed file is written to.
     * @param hTree Huffman tree genereted from the source file.
     */
    public Encoder(BitOutputStream out, HuffmanTree hTree) {
        if (out == null) {
            throw new NullPointerException("Argument is null");
        }
        this.output = out;
        this.hTree = hTree;
    }
       
    /**
     * Writes a header to the compressed file for decoding. The first eight bytes
     * represent the lenght of the compressed file (plus the header). The next four bytes
     * represent the lenght of the codelenght table in bytes. The next four bytes represent
     * the lenght of the symbol table in bytes. Then the values of the codelenght table
     * are written as integers (4 bytes each) followed by symbol values written as
     * integers.
     */
    public void writeHeaderforDecoding() {
        // write lenght of the compressed file as the first eight bytes
        this.output.writeLongAsByteArray(this.hTree.getCompressedFileLenght());
        // write lenght of codelenght table as the next four bytes (-1 to accounts for the empty entry at index 0)
        this.output.writeIntegerAsByteArray((this.hTree.getCanonizedCodeLenghts().length - 1) * Integer.BYTES);
        // write lenght of the cahracter table as the next four bytes
        this.output.writeIntegerAsByteArray(this.hTree.getSymbolsOrderedByCodeLenghts().length * Integer.BYTES);
        // write codelenght table
        for (int i = 1; i < this.hTree.getCanonizedCodeLenghts().length; i++) {
            this.output.writeIntegerAsByteArray(this.hTree.getCanonizedCodeLenghts()[i]);
        }
        // write character table
        for (int j = 0; j < this.hTree.getSymbolsOrderedByCodeLenghts().length; j++) {
            this.output.writeIntegerAsByteArray(this.hTree.getSymbolsOrderedByCodeLenghts()[j]);
        }
    }

    /**
     * Writes the code associated with the symbol given as a parameter to the
     * output stream.
     *
     * @param symbol The symbol to be coded.
     * 
     * @throws IOException
     */
    public void write(int symbol) throws IOException {
        if (this.hTree.getCanonizedCodes() == null) {
            throw new NullPointerException("Code book is null");
        }
        for (Leaf l : this.hTree.getCanonizedCodes()) {
            if (symbol == l.getSymbol()) {
                for (int i = 0; i < l.getRepresentation().length(); i++) {
                    this.output.writeBit(Character.getNumericValue(l.getRepresentation().charAt(i)));
                }    
                break;
            }
        }
    }
}
