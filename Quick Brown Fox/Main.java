import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        StringBuilder ap = new StringBuilder("abcdefghijklmnopqrstuvwxyz");

        int n = keyboard.nextInt(); keyboard.nextLine();

        for (int i = 0; i < n; i++) {
            StringBuilder inputString = new StringBuilder(keyboard.nextLine().toLowerCase());
            StringBuilder outputString = new StringBuilder(ap);

            for (int j = 0; j < inputString.length(); j++) {
                for (int k = 0; k < outputString.length(); k++) {
                    if (inputString.charAt(j) == outputString.charAt(k))
                        outputString.deleteCharAt(k);
                }
            }
            if (outputString.length() != 0)
                System.out.println("missing " + outputString);
            else
                System.out.println("pangram");
        }
    }
}