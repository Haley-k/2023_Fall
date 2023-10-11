import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt(); keyboard.nextLine();

        for (int i = 0; i < n; i++) {
            String compound = keyboard.nextLine();
            if (isMolecular(compound))
                System.out.println("Molecular!");
            else
                System.out.println("Not molecular!");
        }
    }

    public static boolean isMolecular(String compound) {
        String[] nonMetals = {"Cl", "Br", "Xe", "Kr", "Si", "As", "Rn", "Ne", "He", "H", "C", "N", "O", "F", "P", "S", "I"};

        for (String element : compound.split(" ")) {
            if (!isNonMetal(element, nonMetals))
                return false;
        }
        return true;
    }

    public static boolean isNonMetal(String element, String[] nonMetals) {
        for (String nonMetal : nonMetals) {
            if (element.equals(nonMetal))
                return true;
        }
        return false;
    }
}