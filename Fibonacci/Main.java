import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(keyboard.readLine());

        for (int i = 0; i < n; i++) {
            int disaster = Integer.parseInt(keyboard.readLine()) + 1;
            BigInteger[] fib = new BigInteger[disaster + 1];

            BigInteger result = fibonacci(disaster, fib);
            System.out.println(result.mod(BigInteger.TEN));
        }
    }

    public static BigInteger fibonacci (int x, BigInteger[] fib) {
        if (x == 0)
            return BigInteger.ZERO;
        else if (x == 1)
            return BigInteger.ONE;

        if (fib[x] != null)
            return fib[x];

        fib[x] = fibonacci(x - 1, fib).add(fibonacci(x - 2, fib));
        return fib[x];
    }
}