import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        char[] parts = {'B', 'F', 'T', 'L', 'C'};

        String in = keyboard.nextLine();
        char[] input = in.toCharArray();

        boolean missing = false;

        for (int i = 0; i < parts.length; i++) {
            boolean found = false;
            for (int j = 0; j < input.length; j++) {
                if (parts[i] == input[j]) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.print(parts[i]);
                missing = true;
            }
        }
        if (!missing)
            System.out.println("NO MISSING PARTS");
    }
}