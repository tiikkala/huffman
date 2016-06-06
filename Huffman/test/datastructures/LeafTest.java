package datastructures;

import huffman.datastructures.Leaf;
import org.junit.Test;
import static org.junit.Assert.*;


public class LeafTest {
    
    private Leaf l1;
    private Leaf l2;
    
    @Test
    public void comparingToSmallerCodeLengthReturnsPositive() {
        l1 = new Leaf('g', 1, "01");
        l2 = new Leaf('l', 1, "0");
        assertTrue(l1.compareTo(l2) > 0);
    }   
    
    @Test
    public void comparingToGreaterCodeLengthReturnsNegative() {
        l1 = new Leaf('l', 1, "2");
        l2 = new Leaf('g', 1, "02");
        assertTrue(l1.compareTo(l2) < 0);
    }
    
    @Test
    public void comparingToEqualLengthWithSmallerCharValueReturnsPositive() {
        l1 = new Leaf('q', 1, "0");
        l2 = new Leaf('a', 1, "0");
        assertTrue(l1.compareTo(l2) > 0);
    }
    
    @Test
    public void comparingToEqualLengthWithGreaterCharValueReturnsNegative() {
        l1 = new Leaf('a', 1, "0");
        l2 = new Leaf('b', 2, "0");
        assertTrue(l1.compareTo(l2) < 0);
    }
}
