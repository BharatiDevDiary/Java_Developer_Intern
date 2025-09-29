import java.util.ArrayList;
import java.util.Scanner;

class Account {
  private String accountNumber;
  private String ownerName;
  private double balance;
  private ArrayList<String> transactions;

  // Constructor
  public Account(String accountNumber, String ownerName, double initialBalance) {
    this.accountNumber = accountNumber;
    this.ownerName = ownerName;
    this.balance = initialBalance;
    this.transactions = new ArrayList<>();
    transactions.add("Account created with balance: " + initialBalance);
  }

  // Deposit method
  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
      transactions.add("Deposited: " + amount + ", New Balance: " + balance);
      System.out.println("Deposited " + amount + " successfully.");
    } else {
      System.out.println("Invalid deposit amount.");
    }
  }

  // Withdraw method
  public void withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      transactions.add("Withdrew: " + amount + ", New Balance: " + balance);
      System.out.println("Withdrew " + amount + " successfully.");
    } else {
      System.out.println("Insufficient balance or invalid amount.");
    }
  }

  // Show Balance
  public void showBalance() {
    System.out.println("Current Balance: " + balance);
  }

  // Show transaction history
  public void showTransactionHistory() {
    System.out.println("Transaction History:");
    for (String t : transactions) {
      System.out.println(t);
    }
  }
}

public class BankSimulation {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Account acc = new Account("12345", "Bharati", 1000.0);

    while (true) {
      System.out.println("\n--- Bank Menu ---");
      System.out.println("1. Deposit");
      System.out.println("2. Withdraw");
      System.out.println("3. Show Balance");
      System.out.println("4. Show Transaction History");
      System.out.println("5. Exit");
      System.out.print("Choose option: ");

      int choice = sc.nextInt();

      switch (choice) {
        case 1:
          System.out.print("Enter deposit amount: ");
          double dep = sc.nextDouble();
          acc.deposit(dep);
          break;
        case 2:
          System.out.print("Enter withdraw amount: ");
          double wd = sc.nextDouble();
          acc.withdraw(wd);
          break;
        case 3:
          acc.showBalance();
          break;
        case 4:
          acc.showTransactionHistory();
          break;
        case 5:
          System.out.println("Thank you for banking with us!");
          sc.close();
          return;
        default:
          System.out.println("Invalid option. Try again.");
      }
    }
  }
}
