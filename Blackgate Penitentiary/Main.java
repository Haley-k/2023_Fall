import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        TreeSet<String> names;
        TreeMap<Integer, TreeSet<String>> info = new TreeMap<>();
        String[] input;
        String name;
        int height;

        int n = keyboard.nextInt(); keyboard.nextLine();

        for (int i = 0; i < n; i++) {
            input = keyboard.nextLine().split(" ");
            name = input[0];
            height = Integer.parseInt(input[1]);
            names = info.get(height);
            
            if (names == null) 
                names = new TreeSet<>();
            
            names.add(name);
            info.put(height, names);
        }

        int start = 1, end = 0;
        String result;
        
        for (Map.Entry<Integer, TreeSet<String>> entry : info.entrySet()) {
            result = "";
            Iterator<String> it = entry.getValue().iterator();

            while (it.hasNext()) {
                result += it.next() + " ";
                end++;
            }

            result += start + " " + end;
            System.out.println(result);
            start = end + 1;
        }
    }
}