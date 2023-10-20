import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int n = keyboard.nextInt();

        for (int i = 0; i < n; i++) {
            int stairs = keyboard.nextInt();
            System.out.println(countWays(stairs));
        }
    }

    public static BigInteger countWays(int stairs) {
        if (stairs == 1)
            return BigInteger.valueOf(1);
        if (stairs == 2)
            return BigInteger.valueOf(2);

        BigInteger[] ways = new BigInteger[stairs];
        ways[0] = BigInteger.valueOf(1);
        ways[1] = BigInteger.valueOf(2);

        for (int i = 2; i < stairs; i++)
            ways[i] = ways[i - 1].add(ways[i - 2]);

        return ways[stairs - 1];
    }
}
