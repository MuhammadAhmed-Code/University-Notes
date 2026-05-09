public class Main {
    public static void main(String[] args) {
        System.out.println("Answer 01");
        TheoryDescribe td = new TheoryDescribe();
        AdjacencyMatrix am = new AdjacencyMatrix(4);
        AdjacencyList al = new AdjacencyList(4);

        td.theory();
        am.adjacentMatrix();
        al.adjacentList();

        System.out.println("\nAnswer 02");
        PkChange coins = new PkChange(1988);
        coins.coinchange();

        System.out.println("\nAnswer 03");
        TopologicalSortDFS ts = new TopologicalSortDFS();
        ts.topologicalSort();

        System.out.println("\nAnswer 04");
        LCS lcs = new LCS();
        lcs.display();
    }
}