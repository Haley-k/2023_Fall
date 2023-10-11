import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        String in = keyboard.nextLine();
        char[] input = in.toCharArray();

        String out = "Neither";

        switch (input[0]) {
            case 'R' :
                if (input[1] != 'L' && input[1] != 'R' && input[2] == 'L')
                    out = "Crossover";
                break;
            case 'L' :
                if (input[1] != 'R' && input[1] != 'L' && input[2] == 'R')
                    out = "Crossover";
                break;
            case 'U' :
                if (input[1] != 'D' && input[1] != 'U' && input[2] == 'D')
                    out = "Candle";
                break;
            case 'D' :
                if (input[1] != 'U' && input[1] != 'D' && input[2] == 'U')
                    out = "Candle";
                break;
        }
        System.out.println(out);
    }
}