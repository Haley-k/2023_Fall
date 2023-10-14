import java.util.*;
/*
I misunderstood the problem and made a different program
which prints the left-most letter, but it has not been printed before.
Uploading it together since there is nothing wrong with the program itself.
 */

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            String letters = keyboard.nextLine();

            for (int j = 0; j < letters.length(); j++) {
                char c = letters.charAt(j);

                if (result.indexOf(String.valueOf(c)) == -1) {
                    result.append(c);
                    System.out.println(c);
                    break;
                }
            }
        }
    }
}