import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {
    String address;
    public List<Book> bookCatalog = new ArrayList<>();

    public Library(String address) {
        this.address = address;
    }

    public void printAddress() {
        System.out.println(address);
    }

    public static void printOpeningHours() {
        String hours = "Libraries are open daily from 9am to 5pm";
        System.out.println(hours);
    }

    public void addBook(Book book) {
        bookCatalog.add(book);
    }

    private void borrowBook(String bookTitle) {
        Iterator<Book> itBookList = bookCatalog.iterator();
        boolean bookExists = false;

        while (itBookList.hasNext()) {
            Book thisBook = itBookList.next();

            if (thisBook.getTitle().equals(bookTitle)) {
                if (thisBook.isBorrowed()) {
                    System.out.printf("Sorry! %s is already borrowed.\n\n", bookTitle);
                } else {
                    System.out.printf("You've successfully checked out %s!\n\n", bookTitle);
                    thisBook.borrowed();
                }
                bookExists = true;
                break; // Once the book is found, no need to continue iterating.
            }
        }

        if (!bookExists) {
            System.out.printf("Sorry, %s is not available in the library.\n\n", bookTitle);
        }
    }

    private void printAvailableBooks() {
        for (Book book : bookCatalog) {
            if (!book.isBorrowed()) {
                System.out.println(book.getTitle());
            }
        }
    }

    private void returnBook(String bookTitle) {
        for (Book book : bookCatalog) {
            if (book.getTitle().equals(bookTitle)) {
                book.returned();
                System.out.printf("%s has been returned successfully!\n\n", bookTitle);
                return;
            }
        }
        System.out.printf("The book %s is not part of the library's catalog.\n\n", bookTitle);
    }

    public static void main(String[] args) {
        // Create two libraries
        Library firstLibrary = new Library("10 Main St.");
        Library secondLibrary = new Library("228 Liberty St.");

        // Add four books to the first library
        firstLibrary.addBook(new Book("The Da Vinci Code"));
        firstLibrary.addBook(new Book("Le Petit Prince"));
        firstLibrary.addBook(new Book("A Tale of Two Cities"));
        firstLibrary.addBook(new Book("The Lord of the Rings"));
        secondLibrary.addBook(new Book("Learn Java the Easy Way: A Hands-On Introduction to Programming"));

        // Print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

        // Try to borrow The Lords of the Rings from the first library
        System.out.println("Borrowing The Lord of the Rings:");
        firstLibrary.borrowBook("The Lord of the Rings");
        firstLibrary.borrowBook("The Lord of the Rings");
        secondLibrary.borrowBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of all available books from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();

        // Return The Lord of the Rings to the first library
        System.out.println("Returning The Lord of the Rings:");
        firstLibrary.returnBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of available books from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println("\nBooks available in the second library:");
        secondLibrary.printAvailableBooks();
    }
}
