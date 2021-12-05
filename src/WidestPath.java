import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Returns a widest path between two vertices in an undirected graph. A widest path between two
 * vertices maximizes the weight of the minimum-weight edge in the path.
 * <p/>
 * There are multiple ways to solve this problem. The following algorithms may be helpful:
 * - Kruskal's algorithm using Union Find, or
 * - Prim's algorithm using Binary Min Heap (Priority Queue)
 * Feel free to use any previous algorithms that you have already implemented.
 */
public final class WidestPath {
    static int[] parent;
    static int[] rank;
    
    static class Edge implements Comparable<Edge> {
        int u; 
        int v;
        int weight;
        
        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }
    
    private WidestPath() {}
    
    /**
     * Computes a widest path from {@param src} to {@param tgt} for an undirected graph.
     * If there are multiple widest paths, this method may return any one of them.
     * Input {@param g} guaranteed to be undirected.
     * Input {@param src} and {@param tgt} are guaranteed to be valid and in-bounds.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param g   the graph
     * @param src the vertex from which to start the search
     * @param tgt the vertex to find via {@code src}
     * @return an ordered list of vertices on a widest path from {@code src} to {@code tgt}, or an
     * empty list if there is no such path. The first element is {@code src} and the last
     * element is {@code tgt}. If {@code src == tgt}, a list containing just that element is
     * returned.
     * @implSpec This method should run in worst-case O((n + m) log n) time.
     */
    public static List<Integer> getWidestPath(Graph g, int src, int tgt) {
        if (src == tgt) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(src);
            return list;
        }
        
        Graph t = new Graph(g.getSize());
        
        ArrayList<Edge> edges = new ArrayList<Edge>();
        parent = new int[g.getSize()];
        rank = new int[g.getSize()];
        
        for (int i = 0; i < g.getSize(); i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        
        for (int i = 0; i < g.getSize(); i++) {
            for (int c : g.outNeighbors(i)) {
                if (!edges.contains(new Edge(c, i, -g.adjList.get(i).get(c)))) {
                    edges.add(new Edge(i, c, -g.adjList.get(i).get(c)));
                }
            }
        }
        
        Collections.sort(edges);
        
        int counter = 0;
        int e = 0;
        while (e < g.getSize() - 1) {
            if (counter < edges.size()) {
                Edge next = edges.get(counter++);
                int x = find(next.u);
                int y = find(next.v);
                
                if (x != y) {
                    t.addEdge(next.u, next.v, next.weight);
                    t.addEdge(next.v, next.u, next.weight);
                    e++;
                    union(x, y);
                }
            } else {
                break;
            }
        }
        
        boolean[] discovered = new boolean[t.adjList.size()];
        Integer[] parent2 = new Integer[discovered.length];
        
        for (int i = 0; i < discovered.length; i++) {
            discovered[i] = false;
            parent2[i] = null;
        }
        
        ResizingDequeImpl<Integer> queue = new ResizingDequeImpl<Integer>();
        queue.addLast(src);
        discovered[src] = true;
        
        while (queue.size != 0) {
            Integer vertex = queue.pollFirst();
            
            for (int i : t.outNeighbors(vertex)) {
                if (!discovered[i]) {
                    discovered[i] = true;
                    queue.addLast(i);
                    parent2[i] = vertex;
                }
                
                if (parent2[tgt] != null) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(tgt);
                    Integer curr = tgt;
                    while (parent2[curr] != null) {
                        list.add(parent2[curr]);
                        curr = parent2[curr];
                    }
                    ArrayList<Integer> revList2 = new ArrayList<Integer>();
                    for (int j = list.size() - 1; j >= 0; j--) {
                        revList2.add(list.get(j));
                    }
                    return revList2;
                }
            }
        }
        return new ArrayList<Integer>();
    }
    
    static int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }
    
    static void union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);
        
        // can change
        if (rank[xroot] < rank[yroot]) {
            parent[xroot] = yroot;
        } else if (rank[xroot] > rank[yroot]) {
            parent[yroot] = xroot;
        } else {
            parent[yroot] = xroot;
            rank[xroot]++;
        }
        
    }
}
