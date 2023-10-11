import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        String r = "OlEASGBg";
        char[] replace = r.toCharArray();

        String r2 = "01345689";
        char[] replace2 = r2.toCharArray();

        String in = keyboard.nextLine();
        char[] input = in.toCharArray();

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < replace2.length; j++) {
                if (input[i] == replace2[j])
                    input[i] = replace[j];
            }
        }
        System.out.println(input);
    }
}