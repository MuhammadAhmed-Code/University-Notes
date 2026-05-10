class LCS {
    StringBuilder sb = new StringBuilder();
    int[][] buildTable(String name, String string) {
        int n = name.length();
        int m = string.length();
        int[][] dynamic = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (name.charAt(i - 1) == string.charAt(j - 1)) {
                    dynamic[i][j] = 1 + dynamic[i - 1][j - 1];
                } else {
                    dynamic[i][j] = Math.max(dynamic[i - 1][j], dynamic[i][j - 1]);
                }
            }
        }
        return dynamic;
    }

    String getLCSString(int[][] dynamic, String name, String string) {
        int i = name.length();
        int j = string.length();

        while (i > 0 && j > 0) {
            if (name.charAt(i - 1) == string.charAt(j - 1)) {
                sb.append(name.charAt(i - 1));
                i--;
                j--;
            }
            else if (dynamic[i - 1][j] >= dynamic[i][j - 1]) {
                i--;
            }
            else {
                j--;
            }
        }
        return sb.reverse().toString();
    }

    void printTable(int[][] dynamic, String name, String string) {
        System.out.print("   ");
        for (char c : string.toCharArray()) {
            System.out.printf("%3c", c);
        }
        System.out.println();

        for (int i = 0; i < name.length(); i++) {
            System.out.print(name.charAt(i) + "  ");

            for (int j = 1; j <= string.length(); j++) {
                System.out.printf("%3d", dynamic[i + 1][j]);
            }
            System.out.println();
        }
    }

    public void display(){

        String name = "MUHAMMADAHMED";
        String string = "ETAOINSHR";

        System.out.println("String 1 : " + name);
        System.out.println("String 2 : " + string);

        int[][] dp = buildTable(name, string);

        System.out.println("Dynamic Programming Table:");
        printTable(dp, name, string);

        String lcsString = getLCSString(dp, name, string);

        System.out.println("\nResults:");
        System.out.println("LCS Length : " + dp[name.length()][string.length()]);
        System.out.println("LCS String : " + lcsString);
    }
}

public class Question04Answer {
    public static void main(String[] args) {
        LCS lcs = new LCS();
        lcs.display();
    }
}
