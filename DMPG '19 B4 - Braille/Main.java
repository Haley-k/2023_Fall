import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String brailleTranslation = translateToBraille(input);
        System.out.println(brailleTranslation);
    }
    public static String translateToBraille(String input) {
        // Define a mapping of English characters to Braille representations
        HashMap<Character, String> brailleMap = new HashMap<>();
        brailleMap.put('A', "o.\n..\n.."); brailleMap.put('B', "o.\no.\n..");
        brailleMap.put('C', "oo\n..\n.."); brailleMap.put('D', "oo\n.o\n..");
        brailleMap.put('E', "o.\n.o\n.."); brailleMap.put('F', "oo\no.\n..");
        brailleMap.put('G', "oo\noo\n.."); brailleMap.put('H', "o.\noo\n..");
        brailleMap.put('I', ".o\no.\n.."); brailleMap.put('J', ".o\noo\n..");
        brailleMap.put('K', "o.\n..\no."); brailleMap.put('L', "o.\no.\no.");
        brailleMap.put('M', "oo\n..\no."); brailleMap.put('N', "oo\n.o\no.");
        brailleMap.put('O', "o.\n.o\no."); brailleMap.put('P', "oo\no.\no.");
        brailleMap.put('Q', "oo\noo\no."); brailleMap.put('R', "o.\noo\no.");
        brailleMap.put('S', ".o\no.\no."); brailleMap.put('T', ".o\noo\no.");
        brailleMap.put('U', "o.\n..\noo"); brailleMap.put('V', "o.\no.\noo");
        brailleMap.put('W', ".o\noo\n.o"); brailleMap.put('X', "oo\n..\noo");
        brailleMap.put('Y', "oo\n.o\noo"); brailleMap.put('Z', "o.\n.o\noo");
        brailleMap.put(' ', "..\n..\n..");

        StringBuilder[] brailleLines = new StringBuilder[3];
        for (int i = 0; i < 3; i++)
            brailleLines[i] = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (brailleMap.containsKey(c)) {
                String[] brailleParts = brailleMap.get(c).split("\n");
                for (int i = 0; i < 3; i++)
                    brailleLines[i].append(brailleParts[i]);
            }
        }

        StringBuilder brailleTranslation = new StringBuilder();
        for (int i = 0; i < 3; i++)
            brailleTranslation.append(brailleLines[i]).append("\n");

        return brailleTranslation.toString();
    }
}