public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> QuestionONE.main(null), "EdmondsKarp-Thread");
        Thread t2 = new Thread(() -> QuestionTWO.main(null),  "BoyerMoore-Thread");
        Thread t3 = new Thread(() -> QuestionTHREE.main(null),    "Dijkstra-Thread");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
