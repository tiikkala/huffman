package datastructures;



import huffman.datastructures.Node;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class NodeTest {
    
    Node greaterNode;
    Node lesserNode;
    Node anotherGreatNode;
    
    @Before
    public void setUp() {
        this.greaterNode = new Node('g', 100);
        this.lesserNode = new Node('l', 1);
        this.anotherGreatNode = new Node('a', 100);
        
    }
    
    @Test
    public void comparingGreaterNodeToLesserNodeReturnsPositive() {
        assertTrue(this.greaterNode.compareTo(this.lesserNode) > 0);
    }
    
    @Test
    public void comparingLesseNodeToGreaterNodeReturnsNegative() {
        assertTrue(this.lesserNode.compareTo(this.greaterNode) < 0);
    }
    
    @Test
    public void comparingTwoEquallyFreqNodesReturnsZero() {
        assertTrue(this.greaterNode.compareTo(this.anotherGreatNode) == 0);
    }
    
}
