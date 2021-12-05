import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {

    Graph g;
    Graph gInvalid;
    
    @Before
    public void setUp() {
        g = new Graph(5);
    }
    
    @Test
    public void constructorTest() {
        assertTrue(g.adjList != null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constuctorZeroInputTest() {
        gInvalid = new Graph(0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void constuctorNegativeInputTest() {
        gInvalid = new Graph(-5);
    }
    
    @Test
    public void getSizeTest() {
        assertEquals(5, g.getSize());
    }
    
    @Test
    public void hasEdgeTest() {
        g.addEdge(0, 1, 2);
        assertFalse(g.hasEdge(0, 0));
        assertTrue(g.hasEdge(0, 1));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void invalidVertexHasEdgeTest() {
        gInvalid = new Graph(4);
        gInvalid.hasEdge(0, 5);
    }

    @Test
    public void getWeightTest() {
        g.addEdge(0, 1, 2);
        g.addEdge(3, 1, 100);
        assertEquals(2, g.getWeight(0, 1));
        assertEquals(100, g.getWeight(3, 1));
    }
    
    @Test(expected = NoSuchElementException.class)
    public void invalidEdgeGetWeightTest() {
        g.addEdge(0, 1, 3);
        g.getWeight(0, 2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void invalidVertexGetWeightTest() {
        g.getWeight(0, 7);
    }
    
    @Test 
    public void addEdgeTest() {
        assertTrue(g.addEdge(0, 1, 2));
        assertFalse(g.addEdge(0, 1, 5));
        assertEquals(2, g.getWeight(0, 1));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void invalidVertexAddEdgeTest() {
        g.addEdge(0, 6, 3);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void sameInputAddEdgeTest() {
        g.addEdge(0, 0, 3);
    }
    
    @Test
    public void outNeighborsTest() {
        g.addEdge(0, 1, 3);
        g.addEdge(0, 2, 5);
        g.addEdge(0, 3, 4);
        assertTrue(g.outNeighbors(0).contains(1));
        assertTrue(g.outNeighbors(0).contains(2));
        assertTrue(g.outNeighbors(0).contains(3));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void invalidVertexOutNeighborsTest() {
        g.outNeighbors(6);
    }
}
