import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MazeSolverTest {

    int[][] smallWriteupMaze;
    int[][] impossibleWriteupMaze;
    
    @Before
    public void setUp() {
        smallWriteupMaze = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 0, 0},
            {0, 0, 0, 1},
            {0, 0, 1, 0}
        };
    
        impossibleWriteupMaze = new int[][] {
            {0, 0, 0, 0},
            {1, 1, 0, 0},
            {0, 1, 0, 1},
            {0, 0, 1, 0}
        };
    }
    
    @Test
    public void test() {
        List<Coordinate> asd = MazeSolver.getShortestPath(smallWriteupMaze, 
                new Coordinate(0,0), new Coordinate(1, 3));
        ArrayList<Coordinate> solution = new ArrayList<Coordinate>();
        solution.add(new Coordinate(0,0));
        solution.add(new Coordinate(1,0));
        solution.add(new Coordinate(2,0));
        solution.add(new Coordinate(2,1));
        solution.add(new Coordinate(2,2));
        solution.add(new Coordinate(1,2));
        solution.add(new Coordinate(1,3));
        assertEquals(solution, asd);
    }
    
    @Test
    public void noSolutionTest() {
        List<Coordinate> asd = MazeSolver.getShortestPath(impossibleWriteupMaze, 
                new Coordinate(0,0), new Coordinate(1, 3));
        assertTrue(asd.isEmpty());
    }
    
    @Test
    public void sameCoordinateTest() {
        List<Coordinate> asd = MazeSolver.getShortestPath(smallWriteupMaze, 
                new Coordinate(0,0), new Coordinate(0, 0));
        ArrayList<Coordinate> solution = new ArrayList<Coordinate>();
        solution.add(new Coordinate(0,0));
        assertEquals(solution, asd);
    }
}
