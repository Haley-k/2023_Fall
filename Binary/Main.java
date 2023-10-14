import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        StringBuilder result;

        int n = keyboard.nextInt(); keyboard.nextLine();

        for (int i = 0; i < n; i++) {
            int input = keyboard.nextInt(); keyboard.nextLine();
            result = new StringBuilder();

            if (input > 0) {
                if (input != 1) {
                    for (int j = 0; input != 1; j++) {
                        result.append(input % 2);
                        input /= 2;
                    } // Can use Integer.toBinaryString(input); instead
                }
                result.append(1);

                int length = result.length();
                int remainingZeros = 4 - (length % 4);

                if (length % 4 != 0)
                    result.append("0".repeat(remainingZeros));

                result.reverse();

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