import java.util.*;

public class QuestionTWO {

    static int[] buildBadCharTable(String pattern) {
        int[] table = new int[256];
        Arrays.fill(table, -1);
        for (int i = 0; i < pattern.length(); i++)
            table[pattern.charAt(i)] = i;
        return table;
    }

    static int[] buildGoodSuffixTable(String pattern) {
        int m = pattern.length();
        int[] shift = new int[m + 1];
        int[] border = new int[m + 1];

        Arrays.fill(shift, m);

        int i = m, j = m + 1;
        border[i] = j;

        while (i > 0) {
            while (j <= m && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (shift[j] == m) shift[j] = j - i;
                j = border[j];
            }
            i--;
            j--;
            border[i] = j;
        }

        j = border[0];
        for (i = 0; i <= m; i++) {
            if (shift[i] == m) shift[i] = j;
            if (i == j) j = border[j];
        }

        return shift;
    }

    static void boyerMoore(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int[] badChar  = buildBadCharTable(pattern);
        int[] goodSuffix = buildGoodSuffixTable(pattern);

        int shift = 0;
        boolean found = false;

        while (shift <= n - m) {
            int j = m - 1;

            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j))
                j--;

            if (j < 0) {
                System.out.println("Pattern found at index: " + shift);
                found = true;
                shift += goodSuffix[0];
            } else {
                int badCharShift  = j - badChar[text.charAt(shift + j)];
                int goodSuffixShift = goodSuffix[j + 1];
                shift += Math.max(badCharShift, goodSuffixShift);
            }
        }

        if (!found)
            System.out.println("Pattern not found.");
    }

    public static void main(String[] args) {
        String text = "Insertion sort typically has a smaller constant factor than merge sort";
        String pattern = "sort";

        System.out.println("Boyer-Moore String Matching");
        System.out.println("Text   : " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println();

        boyerMoore(text, pattern);
    }
}