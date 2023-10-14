import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Library {
    private Book[] books;
    private int maxBooks;
    private int currentCount;

    public Library(int maxBooks) {
        this.books = new Book[maxBooks];
        this.maxBooks = maxBooks;
        this.currentCount = 0;
    }

    public void addBook(Book book) {
        if (currentCount < maxBooks) { //Check if there is space in the library
            books[currentCount] = book;
            currentCount++;
            System.out.println("The book [" + book.getTitle() + "] was added successfully.");
        } else
            System.out.println("The library is full. No more books can be added.");
    }

    public void borrowBook(String ISBN) {
        if (currentCount != 0) {
            for (int i = 0; i < currentCount; i++) {
                if (books[i].getISBN().equals(ISBN)) {
                    books[i].borrowBook();
                    return;
                }
            }
            System.out.println("This book is not in the library");
        } else
            System.out.println("There are no books in the library");
    }

    public void returnBook(String ISBN) {
        if (currentCount != 0) {
            for (int i = 0; i < currentCount; i++) {
                if (books[i].getISBN().equals(ISBN)) {
                    books[i].returnBook();
                    return;
                }
            }
            System.out.println("This book is not in the library");
        } else
            System.out.println("There are no books in the library");
    }

    public void borrowedInLast7days() {
        LocalDate today = LocalDate.now();
        LocalDate borrowDate = null;

        System.out.println("--- Books borrowed in the last 7 days ---");

        for (int i = 0; i < currentCount; i++) {
            borrowDate = books[i].getBorrowDate();

            if (borrowDate != null && ChronoUnit.DAYS.between(borrowDate, today) <= 7) {
                books[i].displayInfo();

                if (!books[i].isBorrowed())
                    System.out.println("Borrow Date : " + books[i].getBorrowDate());

                System.out.println();
            }
        }
    }

    public void displayAllBooks() {
        if (currentCount != 0) {
            System.out.println("--------- Library ---------");
            for (int i = 0; i < currentCount; i++) {
                books[i].displayInfo(); System.out.println();
            }
        } else
            System.out.println("There are no books in the library");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Library{");
        sb.append("books=").append(Arrays.toString(books));
        sb.append(", maxBooks=").append(maxBooks);
        sb.append(", currentCount=").append(currentCount);
        sb.append('}');
        return sb.toString();
    }
}
