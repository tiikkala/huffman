package datastructures;

import ikkala.huffmancompressor.datastructures.BinaryHeap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the binary heap data structure.
 */
public class BinaryHeapTest {

    BinaryHeap minHeap;

    /**
     *
     */
    @Before
    public void setUp() {
        this.minHeap = new BinaryHeap();
    }

    /**
     *
     */
    @Test
    public void insertingNodeToEmptyHeapIncreasesSizeByOne() {
        this.minHeap.insert(1);
        assertEquals(1, this.minHeap.getSize());
    }

    /**
     *
     */
    @Test
    public void pollingNodeFromHeapDecreasesSizeByOne() {
        this.minHeap.insert(1);
        this.minHeap.insert(2);
        this.minHeap.poll();
        assertEquals(1, this.minHeap.getSize());
    }

    /**
     *
     */
    @Test
    public void isEmptyReturnsTrueForEmptyHeap() {
        assertTrue(this.minHeap.isEmpty());
    }

    /**
     *
     */
    @Test
    public void isEmptyReturnsFalseForNonEmptyHeap() {
        this.minHeap.insert(1);
        assertFalse(this.minHeap.isEmpty());
    }

    /**
     *
     */
    @Test
    public void inseringAndPollingMaintainsHeapProperty() {
        this.minHeap.insert(1);
        this.minHeap.insert(0);
        this.minHeap.insert(2);
        int[] expectedOrder = {0, 1, 2};
        int[] actualOrder = new int[3];
        for (int i = 0; i < 3; i++) {
            actualOrder[i] = (int) this.minHeap.poll();
        }
        assertArrayEquals(expectedOrder, actualOrder);
    }

    /**
     *
     */
    @Test
    public void heapSizeIsCorrectWithMoreThanTwelveNodes() {
        for (int i = 1; i <= 13; i++) {
            this.minHeap.insert(i);
        }
        assertEquals(13, this.minHeap.getSize());
    }

    /**
     *
     */
    @Test
    public void heapPropertyIsMaintainedWithMoreThanTwelveNodes() {
        for (int i = 0; i < 13; i++) {
            this.minHeap.insert(12 - i);
        }
        int[] expectedOrder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] actualOrder = new int[13];
        for (int i = 0; i < 13; i++) {
            actualOrder[i] = (int) this.minHeap.poll();
        }
        assertArrayEquals(expectedOrder, actualOrder);
    }

    /**
     *
     */
    @Test
    public void largeHeapMaintainsHeapProperty() {
        int[] expectedOrder = new int[10000];
        int[] actualOrder = new int[10000];
        for (int i = 0; i < 10000; i++) {
            this.minHeap.insert(10000 - i);
            expectedOrder[i] = i;
        }
        for (int i = 1; i < 10000; i++) {
            actualOrder[i] = (int) this.minHeap.poll();
        }
        assertArrayEquals(expectedOrder, actualOrder);
    }
}
