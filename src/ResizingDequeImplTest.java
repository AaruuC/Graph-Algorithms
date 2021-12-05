import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ResizingDequeImplTest {

    ResizingDequeImpl<Integer> deque;
    
    @Before
    public void setUp() {
        deque = new ResizingDequeImpl<Integer>();
    }
    
    @Test
    public void emptySizeTest() {
        assertEquals(0, deque.size());
    }

    @Test
    public void addFirstSingleTest() {
        deque.addFirst(1);
        assertArrayEquals(new Integer[] {1, null}, deque.getArray());
    }
    
    @Test
    public void addFirstMultiTest() {
        deque.addFirst(1);
        deque.addFirst(1);
        assertArrayEquals(new Integer[] {1, 1}, deque.getArray());
    }
    
    @Test
    public void addLastSingleTest() {
        deque.addLast(1);
        assertArrayEquals(new Integer[] {1, null}, deque.getArray());
    }
    
    @Test
    public void addLastMultiTest() {
        deque.addLast(1);
        deque.addLast(1);
        assertArrayEquals(new Integer[] {1, 1}, deque.getArray());
    }
    
    @Test
    public void resizeDoubleAddFirstTest() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertEquals(3, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);
        assertArrayEquals(new Integer[] {2, 1, null, 3}, deque.getArray());
    }
    
    @Test
    public void resizeDoubleAddLastTest() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals(3, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);
        assertArrayEquals(new Integer[] {1, 2, 3, null}, deque.getArray());
    }
    
    @Test (expected = NoSuchElementException.class)
    public void emptyPollFirstTest() {
        deque.pollFirst();
    }
    
    @Test (expected = NoSuchElementException.class)
    public void emptyPollLastTest() {
        deque.pollLast();
    }
    
    @Test
    public void pollFirstTest() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertEquals(3, deque.pollFirst().intValue());
        assertArrayEquals(new Integer[] {2, 1, null, null}, deque.getArray());
    }
    
    @Test
    public void pollLastTest() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals(3, deque.pollLast().intValue());
        assertArrayEquals(new Integer[] {1, 2, null, null}, deque.getArray());
    }
    
    @Test
    public void resizeHalfPollFirstTest() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        assertEquals(5, deque.pollFirst().intValue());
        assertEquals(4, deque.pollFirst().intValue());
        assertEquals(3, deque.pollFirst().intValue());
        assertEquals(2, deque.pollFirst().intValue());
        assertEquals(1, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);
        assertArrayEquals(new Integer[] {1, null, null, null}, deque.getArray());
    }
    
    @Test
    public void resizeHalfPollLastTest() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        assertEquals(5, deque.pollLast().intValue());
        assertEquals(4, deque.pollLast().intValue());
        assertEquals(3, deque.pollLast().intValue());
        assertEquals(2, deque.pollLast().intValue());
        assertEquals(1, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);
        assertArrayEquals(new Integer[] {1, null, null, null}, deque.getArray());
    }
    
    @Test
    public void peekFirstTest() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        assertEquals(5, deque.peekFirst().intValue());
        assertEquals(5, deque.size());
        assertEquals(8, ((Object[]) deque.getArray()).length);
        assertArrayEquals(new Integer[] {4, 3, 2, 1, null, null, null, 5}, deque.getArray());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void peekFirstEmptyQueueTest() {
        deque.peekFirst();
    }
    
    @Test
    public void peekLastTest() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        assertEquals(1, deque.peekLast().intValue());
        assertEquals(5, deque.size());
        assertEquals(8, ((Object[]) deque.getArray()).length);
        assertArrayEquals(new Integer[] {4, 3, 2, 1, null, null, null, 5}, deque.getArray());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void peekLastEmptyQueueTest() {
        deque.peekLast();
    }
    
    @SuppressWarnings("rawtypes")
    @Test
    public void iteratorTest() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        Iterator iter = deque.iterator();
        assertTrue(iter.hasNext());
        assertEquals(4, iter.next());
        assertEquals(3, iter.next());
        assertEquals(2, iter.next());
        assertEquals(1, iter.next());
        assertNull(iter.next());
        assertTrue(iter.hasNext());
        assertNull(iter.next());
        assertNull(iter.next());
        assertEquals(5, iter.next());
        assertFalse(iter.hasNext());
    }
    
    @SuppressWarnings("rawtypes")
    @Test(expected = NoSuchElementException.class)
    public void iteratorNextTest() {
        deque.addFirst(1);
        deque.addFirst(2);
        Iterator iter = deque.iterator();
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        iter.next();
    }
    
    @Test
    public void addNullTest() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(null);
        deque.addFirst(5);
        assertEquals(5, deque.size());
        assertEquals(8, ((Object[]) deque.getArray()).length);
        assertArrayEquals(new Integer[] {null, 3, 2, 1, null, null, null, 5}, deque.getArray());
        assertEquals(5, deque.pollFirst().intValue());
        assertEquals(4, deque.size());
        assertNull(deque.pollFirst());
        assertEquals(3, deque.size());
        assertEquals(3, deque.pollFirst().intValue());
        assertEquals(2, deque.size());
        assertEquals(2, deque.pollFirst().intValue());
        assertEquals(1, deque.size());
    }
}
