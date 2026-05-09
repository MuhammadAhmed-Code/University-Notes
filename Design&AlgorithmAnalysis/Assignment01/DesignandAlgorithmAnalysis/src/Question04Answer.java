class LCS {

    int[][] buildTable(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }

    String getLCSString(int[][] dp, String s1, String s2) {
        int i = s1.length();
        int j = s2.length();
        StringBuilder sb = new StringBuilder();

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            }
            else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            }
            else {
                j--;
            }
        }
        return sb.reverse().toString();
    }

    void printTable(int[][] dp, String s1, String s2) {
        System.out.print("   ");
        for (char c : s2.toCharArray()) {
            System.out.printf("%3c", c);
        }
        System.out.println();

        for (int i = 0; i < s1.length(); i++) {
            System.out.print(s1.charAt(i) + "  ");

            for (int j = 1; j <= s2.length(); j++) {
                System.out.printf("%3d", dp[i + 1][j]);
            }
            System.out.println();
        }
    }

    public void display(){

        String string1 = "MUHAMMADAHMED";
        String string2 = "ETAOINSHR";

        System.out.println("String 1 : " + string1);
        System.out.println("String 2 : " + string2);

        int[][] dp = buildTable(string1, string2);

        System.out.println("Dynamic Programming Table:");
        printTable(dp, string1, string2);

        String lcsString = getLCSString(dp, string1, string2);

        System.out.println("\nResults:");
        System.out.println("LCS Length : " + dp[string1.length()][string2.length()]);
        System.out.println("LCS String : " + lcsString);
    }
}

public class Question04Answer {
    public static void main(String[] args) {
        LCS lcs = new LCS();
        lcs.display();
    }
}
