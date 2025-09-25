import java.util.*;

// Book class
class Book {
  private String title;
  private String author;
  private boolean isIssued;

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
    this.isIssued = false;
  }

  public String getTitle() {
    return title;
  }

  public boolean isIssued() {
    return isIssued;
  }

  public void issueBook() {
    isIssued = true;
  }

  public void returnBook() {
    isIssued = false;
  }

  @Override
  public String toString() {
    return title + " by " + author + (isIssued ? " [Issued]" : " [Available]");
  }
}

// User class
class User {
  private String name;
  private int userId;
  private List<Book> borrowedBooks;

  public User(String name, int userId) {
    this.name = name;
    this.userId = userId;
    this.borrowedBooks = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public int getUserId() {
    return userId;
  }

  public void borrowBook(Book book) {
    borrowedBooks.add(book);
  }

  public void returnBook(Book book) {
    borrowedBooks.remove(book);
  }

  public List<Book> getBorrowedBooks() {
    return borrowedBooks;
  }

  @Override
  public String toString() {
    return "User: " + name + " (ID: " + userId + ")";
  }
}

// Library class
class Library {
  private List<Book> books;
  private List<User> users;

  public Library() {
    books = new ArrayList<>();
    users = new ArrayList<>();
  }

  public void addBook(Book book) {
    books.add(book);
  }

  public void addUser(User user) {
    users.add(user);
  }

  public void showBooks() {
    for (Book book : books) {
      System.out.println(book);
    }
  }

  public void issueBook(String title, int userId) {
    for (Book book : books) {
      if (book.getTitle().equalsIgnoreCase(title) && !book.isIssued()) {
        for (User user : users) {
          if (user.getUserId() == userId) {
            book.issueBook();
            user.borrowBook(book);
            System.out.println("Book issued: " + book.getTitle() + " to " + user.getName());
            return;
          }
        }
      }
    }
    System.out.println("Book not available or user not found.");
  }

  public void returnBook(String title, int userId) {
    for (User user : users) {
      if (user.getUserId() == userId) {
        for (Book book : user.getBorrowedBooks()) {
          if (book.getTitle().equalsIgnoreCase(title)) {
            book.returnBook();
            user.returnBook(book);
            System.out.println("Book returned: " + book.getTitle());
            return;
          }
        }
      }
    }
    System.out.println("Return failed. Check user/book details.");
  }
}

// Main class
public class LibraryManagementSystem {
  public static void main(String[] args) {
    Library library = new Library();
    Scanner sc = new Scanner(System.in);

    // Add sample books
    library.addBook(new Book("Java Basics", "James Gosling"));
    library.addBook(new Book("Effective Java", "Joshua Bloch"));
    library.addBook(new Book("Clean Code", "Robert C. Martin"));

    // Add sample users
    library.addUser(new User("Bharati", 1));
    library.addUser(new User("Alice", 2));

    boolean running = true;
    while (running) {
      System.out.println("\n===== Library Menu =====");
      System.out.println("1. Show Books");
      System.out.println("2. Issue Book");
      System.out.println("3. Return Book");
      System.out.println("4. Exit");
      System.out.print("Choose an option: ");
      int choice = sc.nextInt();
      sc.nextLine();

      switch (choice) {
        case 1:
          library.showBooks();
          break;
        case 2:
          System.out.print("Enter book title: ");
          String issueTitle = sc.nextLine();
          System.out.print("Enter user ID: ");
          int issueId = sc.nextInt();
          library.issueBook(issueTitle, issueId);
          break;
        case 3:
          System.out.print("Enter book title: ");
          String returnTitle = sc.nextLine();
          System.out.print("Enter user ID: ");
          int returnId = sc.nextInt();
          library.returnBook(returnTitle, returnId);
          break;
        case 4:
          running = false;
          System.out.println("Exiting system...");
          break;
        default:
          System.out.println("Invalid option!");
      }
    }
    sc.close();
  }
}
