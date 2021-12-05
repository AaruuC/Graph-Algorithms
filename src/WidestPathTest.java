import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class WidestPathTest {
    
    Graph g;
    
    @Before
    public void setUp() {
        g = new Graph(5);
    }
    
    @Test
    public void pathExistsTest() {
        g.addEdge(0, 1, 3);
        g.addEdge(1, 0, 3);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 1, 2);
        g.addEdge(3, 2, 17);
        g.addEdge(2, 3, 17);
        g.addEdge(2, 4, 1);
        g.addEdge(4, 2, 1);
        g.addEdge(4, 0, 5);
        g.addEdge(0, 4, 5);
        ArrayList<Integer> answer = new ArrayList<Integer>();
        answer.add(0);
        answer.add(1);
        answer.add(2);
        answer.add(3);
        assertEquals(answer, WidestPath.getWidestPath(g, 0, 3));
    }
    
    @Test
    public void noPathTest() {
        g.addEdge(0, 1, 3);
        g.addEdge(1, 0, 3);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 1, 2);
        g.addEdge(2, 4, 1);
        g.addEdge(4, 2, 1);
        g.addEdge(4, 0, 5);
        g.addEdge(0, 4, 5);
        g.addEdge(0, 2, 10);
        g.addEdge(2, 0, 10);
        assertEquals(new ArrayList<Integer>(), WidestPath.getWidestPath(g, 0, 3));
    }
    
    @Test
    public void sameVertexTest() {
        g.addEdge(0, 1, 3);
        g.addEdge(1, 0, 3);
        ArrayList<Integer> answer = new ArrayList<Integer>();
        answer.add(0);
        assertEquals(answer, WidestPath.getWidestPath(g, 0, 0));
    }

}
