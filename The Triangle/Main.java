import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt();
        int[][] triangle = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++)
                triangle[i][j] = keyboard.nextInt();
        }

        int[][] sumArr = new int[n][n];

        for (int i = 0; i < n; i++)
            sumArr[n-1][i] = triangle[n-1][i];

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++)
                sumArr[i][j] = triangle[i][j] + Math.max(sumArr[i + 1][j], sumArr[i + 1][j + 1]);
        }
        System.out.println(sumArr[0][0]);
    }
}