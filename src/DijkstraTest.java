import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DijkstraTest {
    
    Graph g;
    
    @Before
    public void setUp() {
        g = new Graph(5);
    }
    
    @Test
    public void pathExistsTest() {
        g.addEdge(0, 1, 3);
        g.addEdge(1, 2, 2);
        g.addEdge(3, 2, 17);
        g.addEdge(2, 4, 1);
        g.addEdge(4, 2, 5);
        g.addEdge(4, 0, 2);
        g.addEdge(0, 4, 5);
        ArrayList<Integer> answer = new ArrayList<Integer>();
        answer.add(0);
        answer.add(1);
        answer.add(2);
        assertEquals(answer, Dijkstra.getShortestPath(g, 0, 2));
    }
    
    @Test
    public void pathDoesNotExistTest() {
        g.addEdge(0, 1, 3);
        g.addEdge(1, 2, 2);
        g.addEdge(3, 2, 17);
        g.addEdge(2, 4, 1);
        g.addEdge(4, 2, 5);
        g.addEdge(4, 0, 2);
        g.addEdge(0, 4, 5);
        assertEquals(new ArrayList<Integer>(), Dijkstra.getShortestPath(g, 0, 3));
    }

    @Test
    public void sameVertexTest() {
        g.addEdge(0, 1, 3);
        g.addEdge(1, 2, 2);
        g.addEdge(3, 2, 17);
        g.addEdge(2, 4, 1);
        g.addEdge(4, 2, 5);
        g.addEdge(4, 0, 2);
        g.addEdge(0, 4, 5);
        ArrayList<Integer> answer = new ArrayList<Integer>();
        answer.add(0);
        assertEquals(answer, Dijkstra.getShortestPath(g, 0, 0));
    }
}
