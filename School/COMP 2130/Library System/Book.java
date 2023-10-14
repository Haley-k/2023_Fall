import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isBorrowed;
    private LocalDate borrowDate;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isBorrowed = false;
        this.borrowDate = null;
    }

    public String getTitle() { return title; }
    public String getISBN() { return ISBN; }
    public boolean isBorrowed() { return isBorrowed; }
    public LocalDate getBorrowDate() { return borrowDate; }

    public void borrowBook() {
        if (!isBorrowed) { //Check if book is available
            isBorrowed = true;
            borrowDate = LocalDate.now();
            System.out.println("The book [" + title + "] has been borrowed.");
        } else
            System.out.println("The book [" + title + "] is already borrowed");
    }

    public void returnBook() {
        if(isBorrowed) {//Check if book is currently borrowed
            isBorrowed = false;
            System.out.println("The book [" + title + "] has been returned.");
        } else
            System.out.println("The book [" + title + "] is not currently borrowed");
    }

    public void displayInfo() {
        System.out.println("Title : " + title);
        System.out.println("Author : " + author);
        System.out.println("ISBN : " + ISBN);
        if (isBorrowed) {
            System.out.println("Status : Not Available");
            System.out.println("Borrow Date : " + borrowDate);
        } else
            System.out.println("Status : Available");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("title='").append(title).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", ISBN='").append(ISBN).append('\'');
        sb.append(", isBorrowed=").append(isBorrowed);
        sb.append('}');
        return sb.toString();
    }
}
