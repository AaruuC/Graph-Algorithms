import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to Dijkstra's algorithm for a weighted graph.
 */
final public class Dijkstra {
    private Dijkstra() {}

    /**
     * Computes the shortest path between two nodes in a weighted graph.
     * Input graph is guaranteed to be valid and have no negative-weighted edges.
     *
     * @param g   the weighted graph to compute the shortest path on
     * @param src the source node
     * @param tgt the target node
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     */
    public static List<Integer> getShortestPath(Graph g, int src, int tgt) {
        if (src == tgt) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(src);
            return list;
        }
        BinaryMinHeapImpl<Integer, Integer> queue = new BinaryMinHeapImpl<Integer, Integer>();
        int[] distance = new int[g.adjList.size()];
        Integer[] parent = new Integer[g.adjList.size()];
        
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = null;
        }
        
        distance[src] = 0;
        
        for (int i = 0; i < g.adjList.size(); i++) {
            queue.add(distance[i], i);
        }
        
        while (!queue.isEmpty()) {
            BinaryMinHeap.Entry<Integer, Integer> u = queue.extractMin();
            for (int v : g.adjList.get(u.value).keySet()) {
                if (queue.containsValue(v)) {
                    if (distance[v] > distance[u.value] + g.getWeight(u.value, v)) {
                        distance[v] = distance[u.value] + g.getWeight(u.value, v);
                        parent[v] = u.value;
                        queue.decreaseKey(v, distance[v]);
                    }
                }
            }
        }

        if (parent[tgt] != null) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(tgt);
            Integer curr = tgt;
            while (parent[curr] != null) {
                list.add(parent[curr]);
                curr = parent[curr];
            }
            ArrayList<Integer> revList = new ArrayList<Integer>();
            for (int j = list.size() - 1; j >= 0; j--) {
                revList.add(list.get(j));
            }
            return revList;
        }
        
        return new ArrayList<Integer>();

    }
}
