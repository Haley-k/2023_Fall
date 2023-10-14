import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt(); keyboard.nextLine();

        for (int i = 0; i < n; i++) {
            int inputNum = keyboard.nextInt();
            int inputNum2 = keyboard.nextInt();
            int result = 0;

            for (int j = inputNum; j < inputNum2; j++) {
                int count = 0;
                
                for (int k = 1; k <= j; k++) {
                    if (j % k == 0)
                        count++;
                }
                if (count == 2)
                    result++;
            }
            System.out.println(result);
        }
    }
}