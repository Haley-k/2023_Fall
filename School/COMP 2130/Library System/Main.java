import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/*
This program is a library management system.
A library contains a collection of books,
and a book contains a title, author, ISBN, and is either available or borrowed.

The main class contains a menu system and a user can enter the input number of the option they wish to choose.
The error message will be prompted if a user inputs an invalid number.

The book class contains the attributes which are the title, author, ISBN, status, and borrow date.
There are also two functions; to borrow and to return a book.
Two functions check the book's status first, and then execute the function.

The library class also contains attributes which are the array of books, the max number of books, and the current count.
You can add, borrow, and return a book in the library class.
The borrow and return functions will ask the user for the ISBN for the book the user wishes to borrow or return,
then the function will check if book with that input ISBN exists
and it executes the function in the book class if the book is in the library.
The user also can print a list of books borrowed in the last 7 days.
 */

public class Main {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Library library = new Library(100);

        //ANSI colour codes to colour the text output
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String reset = "\u001B[0m";

        while (true) {
            System.out.println("=========================================");
            System.out.println("Welcome to the Library Management System!");
            System.out.println(red + "1" + reset + ". Add a Book");
            System.out.println(red + "2" + reset + ". Borrow a Book");
            System.out.println(red + "3" + reset + ". Return a Book");
            System.out.println(red + "4" + reset + "." + green + " Display All" + reset + " Books");
            System.out.println(red + "5" + reset + ". Display books borrowed in the last 7 days");
            System.out.println(red + "6" + reset + ". Exit");
            System.out.print("Enter your choice: ");

            int choice = keyboard.nextInt(); keyboard.nextLine();
            System.out.println("=========================================");

            switch (choice) {
                case 1: // Add a Book
                    System.out.print("Enter book title: ");
                    String title = keyboard.nextLine();
                    System.out.print("Enter author: ");
                    String author = keyboard.nextLine();
                    System.out.print("Enter ISBN: ");
                    String ISBN = keyboard.nextLine();

                    Book newBook = new Book(title, author, ISBN);
                    library.addBook(newBook);
                    break;
                case 2: // Borrow a Book
                    System.out.print("Enter the ISBN of the book you would like to borrow: ");
                    ISBN = keyboard.nextLine();
                    library.borrowBook(ISBN);
                    break;
                case 3: // Return a Book
                    System.out.print("Enter the ISBN of the book you would like to return: ");
                    ISBN = keyboard.nextLine();
                    library.returnBook(ISBN);
                    break;
                case 4: // Display All Books
                    library.displayAllBooks();
                    break;
                case 5: // Display books borrowed in the last 7 days
                    library.borrowedInLast7days();
                    break;
                case 6: // Exit
                    System.exit(0);
                default: // Print error message if user enters other than 1-6
                    System.out.println("Invalid choice. Please enter a valid number.");
                    break;
            }
        }
    }
}