import java.io.*;
import java.util.Scanner;

public class NotesApp {
  private static final String FILE_NAME = "notes.txt";

  // Method to add a new note
  public static void addNote(String note) {
    try (FileWriter fw = new FileWriter(FILE_NAME, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)) {
      out.println(note);
      System.out.println(" Note added successfully!");
    } catch (IOException e) {
      System.out.println("Error writing note: " + e.getMessage());
    }
  }

  // Method to read all notes
  public static void viewNotes() {
    try (FileReader fr = new FileReader(FILE_NAME);
        BufferedReader br = new BufferedReader(fr)) {
      String line;
      System.out.println("\n Your Notes:");
      while ((line = br.readLine()) != null) {
        System.out.println("- " + line);
      }
    } catch (FileNotFoundException e) {
      System.out.println(" No notes found! Add some first.");
    } catch (IOException e) {
      System.out.println(" Error reading notes: " + e.getMessage());
    }
  }

  // Menu-driven program
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int choice;

    do {
      System.out.println("\n===== Notes App =====");
      System.out.println("1. Add Note");
      System.out.println("2. View Notes");
      System.out.println("3. Exit");
      System.out.print("Enter choice: ");
      choice = sc.nextInt();
      sc.nextLine(); // consume newline

      switch (choice) {
        case 1:
          System.out.print("Enter your note: ");
          String note = sc.nextLine();
          addNote(note);
          break;
        case 2:
          viewNotes();
          break;
        case 3:
          System.out.println(" Exiting Notes App. Goodbye!");
          break;
        default:
          System.out.println("Invalid choice! Try again.");
      }
    } while (choice != 3);

    sc.close();
  }
}
