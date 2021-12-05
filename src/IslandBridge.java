import java.util.ArrayList;

final public class IslandBridge {
    private IslandBridge() {}

    /**
     * See question details in the write-up. Input is guaranteed to be valid.
     *
     * @param g the input graph representing the islands as vertices and bridges as directed edges
     * @param x the starting node
     * @return true, if no matter how you navigate through the one-way bridges from node x,
     * there is always a way to drive back to node x, and false otherwise.
     * @implSpec This method should run in worst-case O(n + m) time.
     */
    public static boolean allNavigable(Graph g, int x) {
        Graph transpose = new Graph(g.getSize());
        
        boolean[] discovered = new boolean[g.adjList.size()];
        Integer[] parent = new Integer[discovered.length];
        
        for (int i = 0; i < discovered.length; i++) {
            discovered[i] = false;
            parent[i] = null;
        }
        
        ResizingDequeImpl<Integer> queue = new ResizingDequeImpl<Integer>();
        queue.addLast(x);
        discovered[x] = true;
        
        while (queue.size != 0) {
            Integer vertex = queue.pollFirst();
            
            for (int i : g.outNeighbors(vertex)) {
                transpose.addEdge(i, vertex, -1);
                if (!discovered[i]) {
                    discovered[i] = true;
                    queue.addLast(i);
                    parent[i] = vertex;
                }
            }
        }
        
        // Finished first runthrough of BST and creation of transpose graph
        
        ArrayList<Integer> discoverCheck = new ArrayList<Integer>();
        
        for (int i = 0; i < discovered.length; i++) {
            if (discovered[i]) {
                discoverCheck.add(i);
            }
        }
        
        // Finished checking all of the vertices that were discovered through BST
        
        boolean[] discovered2 = new boolean[g.adjList.size()];
        Integer[] parent2 = new Integer[discovered2.length];
        
        for (int i = 0; i < discovered2.length; i++) {
            discovered2[i] = false;
            parent2[i] = null;
        }
        
        queue.addLast(x);
        discovered2[x] = true;
        
        while (queue.size != 0) {
            Integer vertex = queue.pollFirst();
            
            for (int i : transpose.outNeighbors(vertex)) {
                if (!discovered2[i]) {
                    discovered2[i] = true;
                    queue.addLast(i);
                    parent2[i] = vertex;
                }
            }
        }
        
        for (Integer i : discoverCheck) {
            if (!discovered2[i]) {
                return false;
            }
        }
        
        return true;
    }
    
}
