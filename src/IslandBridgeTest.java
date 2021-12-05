import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IslandBridgeTest {

    Graph g;
    
    @Before
    public void setUp() {
        g = new Graph(5);
    }
    
    @Test
    public void nonSCCTest() {
        g.addEdge(0, 1, -1);
        g.addEdge(1, 2, -1);
        g.addEdge(3, 2, -1);
        g.addEdge(2, 4, -1);
        g.addEdge(4, 0, -1);
        g.addEdge(0, 4, -1);
        assertTrue(IslandBridge.allNavigable(g, 0));
        
    }
    
    @Test
    public void failingTest() {
        g.addEdge(0, 1, -1);
        g.addEdge(1, 2, -1);
        g.addEdge(3, 2, -1);
        g.addEdge(2, 4, -1);
        g.addEdge(0, 4, -1);
        assertFalse(IslandBridge.allNavigable(g, 0));
    }
    
    @Test
    public void failingTest2() {
        g.addEdge(0, 1, -1);
        g.addEdge(1, 2, -1);
        g.addEdge(2, 3, -1);
        g.addEdge(2, 4, -1);
        g.addEdge(0, 4, -1);
        g.addEdge(4, 0, -1);
        assertFalse(IslandBridge.allNavigable(g, 0));
    }
    
    

}
