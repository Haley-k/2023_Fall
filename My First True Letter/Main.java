import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            String letters = keyboard.nextLine();

            for (int j = 0; j < letters.length(); j++) {
                char c = letters.charAt(j);
                if (letters.indexOf(c) == j && letters.lastIndexOf(c) == j) {
                    System.out.println(c);
                    break;
                }
            }
        }
    }
}