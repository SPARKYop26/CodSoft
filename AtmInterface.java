import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: " + balance);
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
            return false;
        }
    }
}

class ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    public ATM(BankAccount account) {
        bankAccount = account;
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        } while (choice != 4);
    }

    private void checkBalance() {
        System.out.println("Current balance: " + bankAccount.getBalance());
    }

    private void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        bankAccount.deposit(amount);
    }

    private void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        boolean success = bankAccount.withdraw(amount);
        if (!success) {
            System.out.println("Transaction failed.");
        }
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter initial balance: ");
        double initialBalance = inputScanner.nextDouble();
        
        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);
        
        System.out.println("Welcome to the ATM!");
        atm.run();
        
        inputScanner.close();
    }
}
