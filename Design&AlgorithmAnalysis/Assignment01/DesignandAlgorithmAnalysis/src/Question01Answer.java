import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TheoryDescribe {
    public void theory(){
        String string = "There are three formats for graph representation and storage:\n" +
                "1. Adjacency Matrix:\n" +
                "A 2D array of size N×N where N is the number of vertices. If vertex i is connected to vertex j, the value is 1, otherwise 0. Checking if edge (u,v) exists takes O(1) time. Identifying all edges takes O(N²) time. Space required is O(N²).\n" +
                "2. Adjacency List:\n" +
                "Each vertex stores a list of its neighboring vertices. Space required is O(N+M) where N is vertices and M is edges. Checking if edge (u,v) exists takes O(degree of u) time. Identifying all edges takes O(N+M) time.\n" +
                "3. Edge List:\n" +
                "A simple list of all edges stored as pairs of vertices. Example: [(0,1), (0,2), (1,2)]. Space required is O(M) where M is total number of edges. Simple to implement but slower to search.";
        System.out.println(string);
    }
}

class AdjacencyMatrix {
    protected int[][] adjacencyMatrix;
    private int vertex;

    public AdjacencyMatrix (int vertex) {
        this.vertex = vertex;
        adjacencyMatrix = new int[vertex][vertex];
    }

    public void adjacentMatrix (){
        for (int i = 0; i < vertex; i++){
            for (int j = 0; j < vertex; j++){
                if (i != j) adjacencyMatrix[i][j] = 1;
            }
        }

        System.out.println("Adjacency Matrix: ");
        for (int[] i : adjacencyMatrix){
            System.out.println(Arrays.toString(i));
        }
    }
}

class AdjacencyList {
    private int vertex;
    private List<List<Integer>> list;

    public AdjacencyList (int vertex) {
        this.vertex = vertex;
        list = new ArrayList<>();
    }

    public void adjacentList () {
        for (int i = 0; i < vertex; i++){
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < vertex; i++){
            for (int j = 0; j < vertex; j++){
                if (i != j) list.get(i).add(j);
            }
        }

        System.out.println("\nAdjacency List: ");
        for (int i = 0; i < vertex; i++){
            System.out.println(i + " -> " + list.get(i));
        }
    }
}

public class Question01Answer {
    public static void main(String[] args) {
        TheoryDescribe td = new TheoryDescribe();
        AdjacencyMatrix am = new AdjacencyMatrix(4);
        AdjacencyList al = new AdjacencyList(4);

        td.theory();
        am.adjacentMatrix();
        al.adjacentList();
    }
}
