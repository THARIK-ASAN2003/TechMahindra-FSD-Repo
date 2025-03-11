import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class LibraryManagementSystem {
    // Book class
    static class Book implements Serializable {
        private String title;
        private String author;
        private String ISBN;
        private boolean isAvailable;
        private String reservedBy;

        public Book(String title, String author, String ISBN) {
            this.title = title;
            this.author = author;
            this.ISBN = ISBN;
            this.isAvailable = true;
            this.reservedBy = null;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getISBN() { return ISBN; }
        public boolean isAvailable() { return isAvailable; }
        public String getReservedBy() { return reservedBy; }

        public void setAvailable(boolean available) { this.isAvailable = available; }
        public void setReservedBy(String userID) { this.reservedBy = userID; }

        @Override
        public String toString() {
            return String.format("Book{title='%s', author='%s', ISBN='%s', available=%b}",
                title, author, ISBN, isAvailable);
        }
    }

    // User class
    static class User implements Serializable {
        private String name;
        private String userID;
        private List<Book> borrowedBooks;
        private static final int MAX_BOOKS = 3;

        public User(String name, String userID) {
            this.name = name;
            this.userID = userID;
            this.borrowedBooks = new ArrayList<>();
        }

        public String getName() { return name; }
        public String getUserID() { return userID; }
        public List<Book> getBorrowedBooks() { return borrowedBooks; }

        public void borrowBook(Book book) throws MaxBooksAllowedException {
            if (borrowedBooks.size() >= MAX_BOOKS) {
                throw new MaxBooksAllowedException("User has reached maximum number of borrowed books");
            }
            borrowedBooks.add(book);
        }

        public void returnBook(Book book) {
            borrowedBooks.remove(book);
        }

        @Override
        public String toString() {
            return String.format("User{name='%s', userID='%s', borrowedBooks=%d}",
                name, userID, borrowedBooks.size());
        }
    }

    // Custom Exceptions
    static class BookNotFoundException extends Exception {
        public BookNotFoundException(String message) { super(message); }
    }

    static class UserNotFoundException extends Exception {
        public UserNotFoundException(String message) { super(message); }
    }

    static class MaxBooksAllowedException extends Exception {
        public MaxBooksAllowedException(String message) { super(message); }
    }

    // Library Manager class
    static class LibraryManager {
        private final List<Book> books;
        private final List<User> users;
        private final ReentrantLock lock;
        private static final String DATA_FILE = "library_data.ser";

        public LibraryManager() {
            this.books = new ArrayList<>();
            this.users = new ArrayList<>();
            this.lock = new ReentrantLock();
            loadData();
        }

        public void addBook(Book book) {
            lock.lock();
            try {
                books.add(book);
                saveData();
                System.out.println("Added book: " + book);
            } finally {
                lock.unlock();
            }
        }

        public void addUser(User user) {
            lock.lock();
            try {
                users.add(user);
                saveData();
                System.out.println("Added user: " + user);
            } finally {
                lock.unlock();
            }
        }

        public void borrowBook(String ISBN, String userID)
                throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException {
            lock.lock();
            try {
                Book book = findBookByISBN(ISBN);
                User user = findUser(userID);

                if (!book.isAvailable()) {
                    throw new BookNotFoundException("Book is not available for borrowing");
                }

                user.borrowBook(book);
                book.setAvailable(false);
                saveData();
                System.out.println("User " + userID + " borrowed book: " + book.getTitle());
            } finally {
                lock.unlock();
            }
        }

        public void returnBook(String ISBN, String userID)
                throws BookNotFoundException, UserNotFoundException {
            lock.lock();
            try {
                Book book = findBookByISBN(ISBN);
                User user = findUser(userID);

                user.returnBook(book);
                book.setAvailable(true);

                if (book.getReservedBy() != null) {
                    System.out.println("Book is reserved by user: " + book.getReservedBy());
                }

                saveData();
                System.out.println("User " + userID + " returned book: " + book.getTitle());
            } finally {
                lock.unlock();
            }
        }

        public void reserveBook(String ISBN, String userID)
                throws BookNotFoundException, UserNotFoundException {
            lock.lock();
            try {
                Book book = findBookByISBN(ISBN);
                findUser(userID); // Verify user exists

                if (book.isAvailable()) {
                    throw new BookNotFoundException("Book is available and can be borrowed directly");
                }

                book.setReservedBy(userID);
                saveData();
                System.out.println("User " + userID + " reserved book: " + book.getTitle());
            } finally {
                lock.unlock();
            }
        }

        public Book searchBook(String title) {
            return books.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .findFirst()
                    .orElse(null);
        }

        private User findUser(String userID) throws UserNotFoundException {
            return users.stream()
                    .filter(user -> user.getUserID().equals(userID))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException("User not found: " + userID));
        }

        private Book findBookByISBN(String ISBN) throws BookNotFoundException {
            return books.stream()
                    .filter(book -> book.getISBN().equals(ISBN))
                    .findFirst()
                    .orElseThrow(() -> new BookNotFoundException("Book not found: " + ISBN));
        }

        private void saveData() {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(DATA_FILE))) {
                oos.writeObject(books);
                oos.writeObject(users);
                System.out.println("Library data saved successfully");
            } catch (IOException e) {
                System.err.println("Error saving library data: " + e.getMessage());
            }
        }

        @SuppressWarnings("unchecked")
        private void loadData() {
            File file = new File(DATA_FILE);
            if (!file.exists()) return;

            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(DATA_FILE))) {
                List<Book> loadedBooks = (List<Book>) ois.readObject();
                List<User> loadedUsers = (List<User>) ois.readObject();
                books.addAll(loadedBooks);
                users.addAll(loadedUsers);
                System.out.println("Library data loaded successfully");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading library data: " + e.getMessage());
            }
        }

        public void displayLibraryStatus() {
            System.out.println("\nLibrary Status:");
            System.out.println("Books:");
            books.forEach(book -> System.out.println("- " + book));
            System.out.println("\nUsers:");
            users.forEach(user -> System.out.println("- " + user));
        }
    }

    // Main method
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();

        // Initialize sample data
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "123456");
        Book book2 = new Book("1984", "George Orwell", "789012");
        Book book3 = new Book("To Kill a Mockingbird", "Harper Lee", "345678");

        User user1 = new User("John Doe", "U001");
        User user2 = new User("Jane Smith", "U002");

        // Add books and users
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addUser(user1);
        library.addUser(user2);

        System.out.println("\nInitial library status:");
        library.displayLibraryStatus();

        // Demonstrate library operations
        try {
            // Borrow books
            library.borrowBook("123456", "U001");
            library.borrowBook("789012", "U002");

            System.out.println("\nLibrary status after borrowing:");
            library.displayLibraryStatus();

            // Return a book
            library.returnBook("123456", "U001");

            // Reserve a book
            library.reserveBook("789012", "U001");

            // Search for a book
            Book searchResult = library.searchBook("1984");
            System.out.println("\nSearch result for '1984': " + searchResult);

            System.out.println("\nFinal library status:");
            library.displayLibraryStatus();

        } catch (BookNotFoundException | UserNotFoundException | MaxBooksAllowedException e) {
            System.err.println("Error during library operations: " + e.getMessage());
        }
    }
}