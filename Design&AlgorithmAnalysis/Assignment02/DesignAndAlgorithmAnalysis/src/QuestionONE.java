import java.util.*;

public class QuestionONE {

    static int V = 7; // A=0, B=1, C=2, D=3, E=4, F=5, G=6
    static int[][] capacity = new int[V][V];
    static int[][] original = new int[V][V];
    static int[] parent = new int[V];

    static boolean bfs(int source, int sink) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < V; v++) {
                if (!visited[v] && capacity[u][v] > 0) {
                    visited[v] = true;
                    parent[v] = u;
                    if (v == sink) return true;
                    queue.add(v);
                }
            }
        }
        return false;
    }

    static int edmondsKarp(int source, int sink) {
        int maxFlow = 0;

        while (bfs(source, sink)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v]);
            }

            // print path
            List<Integer> path = new ArrayList<>();
            for (int v = sink; v != source; v = parent[v]) path.add(v);
            path.add(source);
            Collections.reverse(path);
            String[] names = {"A","B","C","D","E","F","G"};
            StringBuilder sb = new StringBuilder("Path: ");
            for (int i = 0; i < path.size(); i++) {
                sb.append(names[path.get(i)]);
                if (i < path.size()-1) sb.append(" --> ");
            }
            System.out.println(sb + "; Bottleneck: " + pathFlow);

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= pathFlow;
                capacity[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    static void minCut(int source) {
        // BFS on residual to find reachable nodes from source
        boolean[] reachable = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        reachable[source] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < V; v++) {
                if (!reachable[v] && capacity[u][v] > 0) {
                    reachable[v] = true;
                    queue.add(v);
                }
            }
        }

        String[] names = {"A","B","C","D","E","F","G"};
        System.out.println("\nMin-Cut edges (reachable -> not reachable, Capacity):");
        for (int u = 0; u < V; u++)
            for (int v = 0; v < V; v++)
                if (reachable[u] && !reachable[v] && original[u][v] > 0)
                    System.out.println("  " + names[u] + " --> " + names[v] + " (cap=" + original[u][v] + ")");
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0,3,3},{2,0,3},{0,1,3}, // A->D, C->A, A->B
                {2,3,1},{2,4,2},{1,2,4}, // C->D, C->E, B->C
                {3,5,6},{3,4,2},         // D->F, D->E
                {4,1,1},{4,6,1},         // E->B, E->G
                {5,6,9}                  // F->G
        };

        for (int[] e : edges) {
            capacity[e[0]][e[1]] = e[2];
            original[e[0]][e[1]] = e[2];
        }

        System.out.println("Edmonds-Karp Max Flow (Source: A, Sink: G)\n");
        int maxFlow = edmondsKarp(0, 6);
        System.out.println("\nMaximum Flow = " + maxFlow);
        minCut(0);
    }
}