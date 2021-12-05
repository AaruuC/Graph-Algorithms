import java.util.ArrayList;
import java.util.List;

final public class MazeSolver {
    private MazeSolver() {}

    /**
     * Returns a list of coordinates on the shortest path from {@code src} to {@code tgt}
     * by executing a breadth-first search on the graph represented by a 2D-array of size m x n.
     * Please note, you MUST use your ResizingDeque implementation as the BFS queue for this method.
     * <p>
     * Input {@code maze} guaranteed to be a non-empty and valid matrix.
     * Input {@code src} and {@code tgt} are guaranteed to be valid, in-bounds, and not blocked.
     * <p>
     * Do NOT modify this method header.
     *
     * @param maze the input maze, which is a 2D-array of size m x n
     * @param src The starting Coordinate of the path on the matrix
     * @param tgt The target Coordinate of the path on the matrix
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     * @implSpec This method should run in worst-case O(m x n) time.
     */
    public static List<Coordinate> getShortestPath(int[][] maze, Coordinate src, Coordinate tgt) {
        if (src.equals(tgt)) {
            ArrayList<Coordinate> list = new ArrayList<Coordinate>();
            list.add(src);
            return list;
        }
        int len = maze[0].length;
        Graph graph = new Graph(maze.length * maze[0].length);
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                int curr = maze[i].length * i + j;
                if (maze[i][j] == 0) {
                    if (i != 0) {
                        if (maze[i - 1][j] == 0) {
                            graph.addEdge(curr, curr - maze[i].length, -1);
                        }
                    }
                    if (i != maze.length - 1) {
                        if (maze[i + 1][j] == 0) {
                            graph.addEdge(curr, curr + maze[i].length, -1);
                        }
                    }
                    if (j != 0) {
                        if (maze[i][j - 1] == 0) {
                            graph.addEdge(curr, curr - 1, -1);
                        }
                    }
                    if (j != maze[i].length - 1) {
                        if (maze[i][j + 1] == 0) {
                            graph.addEdge(curr, curr + 1, -1);
                        }
                    }
                }
            }
        }
        
        boolean[] discovered = new boolean[graph.adjList.size()];
        Integer[] parent = new Integer[discovered.length];
        
        for (int i = 0; i < discovered.length; i++) {
            discovered[i] = false;
            parent[i] = null;
        }
        
        ResizingDequeImpl<Coordinate> queue = new ResizingDequeImpl<Coordinate>();
        queue.addLast(src);
        discovered[src.getY() * len + src.getX()] = true;
        
        while (queue.size != 0) {
            Coordinate vertex = queue.pollFirst();
            
            for (int i : graph.outNeighbors(vertex.getY() * len + vertex.getX())) {
                if (!discovered[i]) {
                    discovered[i] = true;
                    queue.addLast(new Coordinate(i % len, i / len));
                    parent[i] = vertex.getY() * len + vertex.getX();
                }
                
                if (parent[tgt.getY() * len + tgt.getX()] != null) {
                    ArrayList<Coordinate> list = new ArrayList<Coordinate>();
                    list.add(tgt);
                    Coordinate curr = tgt;
                    while (parent[curr.getY() * len + curr.getX()] != null) {
                        Coordinate add = new Coordinate(
                                parent[curr.getY() * len + curr.getX()] % len,
                                parent[curr.getY() * len + curr.getX()] / len);
                        list.add(add);
                        curr = add;
                    }
                    ArrayList<Coordinate> revList = new ArrayList<Coordinate>();
                    for (int j = list.size() - 1; j >= 0; j--) {
                        revList.add(list.get(j));
                    }
                    return revList;
                }
            }
        }
        return new ArrayList<Coordinate>();
    }
}