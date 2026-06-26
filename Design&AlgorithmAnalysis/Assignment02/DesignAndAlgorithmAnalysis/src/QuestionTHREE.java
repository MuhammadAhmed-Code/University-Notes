import java.util.*;

public class QuestionTHREE {

    static final int INF = Integer.MAX_VALUE;
    static int V = 9;

    static void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        // min-heap: {distance, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[1];

            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != INF) {
                    int newDist = dist[u] + graph[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        parent[v] = u;
                        pq.offer(new int[]{dist[v], v});
                    }
                }
            }
        }

        printResults(dist, parent, src);
    }

    static void printResults(int[] dist, int[] parent, int src) {
        System.out.println("Dijkstra Shortest Path from Node " + src + " \n");
        System.out.printf("%-6s %-10s %s%n", "Node", "Distance", "Path");

        for (int i = 0; i < V; i++) {
            System.out.printf("%-6d %-10d %s%n", i, dist[i], getPath(parent, i));
        }

        System.out.println("\nShortest Path Tree Edges");
        for (int i = 0; i < V; i++) {
            if (parent[i] != -1)
                System.out.println("  " + parent[i] + " --> " + i);
        }
    }

    static String getPath(int[] parent, int node) {
        if (parent[node] == -1) return String.valueOf(node);
        return getPath(parent, parent[node]) + " --> " + node;
    }

    public static void main(String[] args) {
        int[][] graph = new int[V][V];

        int[][] edges = {
                {0, 1, 4}, {0, 7, 8},
                {1, 2, 8}, {1, 7, 11},
                {2, 3, 7}, {2, 5, 4}, {2, 8, 2},
                {3, 4, 9}, {3, 5, 14},
                {4, 5, 10},
                {5, 6, 2},
                {6, 7, 1}, {6, 8, 6},
                {7, 8, 7}
        };

        for (int[] e : edges) {
            graph[e[0]][e[1]] = e[2];
            graph[e[1]][e[0]] = e[2];
        }

        dijkstra(graph, 0);
    }
}
