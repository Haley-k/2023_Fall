import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        int n = keyboard.nextInt();
        int costB = keyboard.nextInt();
        int costP = keyboard.nextInt(); keyboard.nextLine();
        int numB = 0, numP = 0;

        for (int i = 0; i < n; i++) {
            numB += keyboard.nextInt();
            numP += keyboard.nextInt(); keyboard.nextLine();
        }

        if (numB % 10 != 0 && numP % 10 != 0) {
            numB = (numB / 10) + 1;
            numP = (numP / 10) + 1;
        } else if (numB % 10 == 0 && numP % 10 != 0) {
            numB /= 10;
            numP = (numP / 10) + 1;
        } else if (numB % 10 != 0 && numP % 10 == 0) {
            numB = (numB / 10) + 1;
            numP /= 10;
        } else {
            numB /= 10;
            numP /= 10;
        }

        int result = (costB * numB) + (costP * numP);

        System.out.println(result);
    }
}