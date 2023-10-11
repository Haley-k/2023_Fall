import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            String input = keyboard.nextLine();
            char[] inputArr = input.toCharArray();
            String result = "";

            for (char n: inputArr) {
                if (Character.isUpperCase(n)) {
                    char resultChar = (char)(((n - 'A' + 13) % 26) + 'A');
                    result += resultChar;
                } else if (Character.isLowerCase(n)) {
                    char resultChar = (char)(((n - 'a' + 13) % 26) + 'a');
                    result += resultChar;
                } else {
                    result += n;
                }
            }
            System.out.println(result);
        }
    }
}