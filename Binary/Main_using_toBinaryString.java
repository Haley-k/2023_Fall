import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt(); keyboard.nextLine();

        for (int i = 0; i < n; i++) {
            int input = keyboard.nextInt(); keyboard.nextLine();
            String result = "";

            if (input > 0) {
                result = Integer.toBinaryString(input);

                int length = result.length();
                int remainingZeros = 4 - (length % 4);

                if (length % 4 != 0)
                    result = "0".repeat(remainingZeros) + result;

                for (int j = 0; j < result.length(); j++) {
                    if (j % 4 == 0 && j > 1)
                        System.out.print(" ");
                    System.out.print(result.charAt(j));
                }
                System.out.println();
            } else
                System.out.println("0000");
        }
    }
}