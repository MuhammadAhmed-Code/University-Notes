import java.util.*;

class TopologicalSortDFS {

    private Map<String, List<String>> graph = new HashMap<>();
    private Set<String> visited = new HashSet<>();
    private LinkedList<String> result = new LinkedList<>();

    public TopologicalSortDFS() {
        initializeGraph();
    }

    private void initializeGraph() {
        String[] vertices = {"m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        for (String v : vertices) {
            graph.put(v, new ArrayList<>());
        }

        graph.get("m").addAll(Arrays.asList("r", "q", "x"));
        graph.get("n").addAll(Arrays.asList("o", "u", "q"));
        graph.get("o").addAll(Arrays.asList("s", "v", "r"));
        graph.get("p").addAll(Arrays.asList("z", "s", "o"));
        graph.get("q").addAll(Arrays.asList("t"));
        graph.get("r").addAll(Arrays.asList("y", "u"));
        graph.get("s").addAll(Arrays.asList("r"));
        graph.get("u").addAll(Arrays.asList("t"));
        graph.get("v").addAll(Arrays.asList("w", "x"));
        graph.get("w").addAll(Arrays.asList("z"));
        graph.get("y").addAll(Arrays.asList("v"));
    }

    public void dfs(String node) {
        visited.add(node);

        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor);
            }
        }
        result.addFirst(node);
    }

    public void topologicalSort() {
        String[] allNodes = {"m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

        for (String node : allNodes) {
            if (!visited.contains(node)) {
                dfs(node);
            }
        }

        System.out.println("Topological Sort Order using DFS:");
        System.out.println(result);
    }
}

public class Question03Answer {
    public static void main(String[] args) {
        TopologicalSortDFS ts = new TopologicalSortDFS();
        ts.topologicalSort();
    }
}
