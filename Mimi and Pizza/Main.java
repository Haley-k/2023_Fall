import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        Pizza[] pizza = new Pizza[4];
        pizza[0] = new Pizza("A", 5, false);
        pizza[1] = new Pizza("B", 5, true);
        pizza[2] = new Pizza("C", 2, false);
        pizza[3] = new Pizza("D", 2, true);

        int budget = keyboard.nextInt();
        int numOfPizza = keyboard.nextInt();
        String isVegetarian = keyboard.next();

        if (isVegetarian.equals("N")) {
            if (pizza[0].price * numOfPizza <= budget)
                System.out.println("A");
            else if (pizza[2].price * numOfPizza <= budget)
                System.out.println("C");
            else
                System.out.println("NO PIZZA");
        } else {
            if (pizza[1].price * numOfPizza <= budget)
                System.out.println("B");
            else if (pizza[3].price * numOfPizza <= budget)
                System.out.println("D");
            else
                System.out.println("NO PIZZA");
        }
    }

    public static class Pizza {
        private String name;
        private int price;
        private boolean vegetarian;

        public Pizza(String name, int price, boolean vegetarian) {
            this.name = name;
            this.price = price;
            this.vegetarian = vegetarian;
        }
    }
}