package main;

import java.util.Scanner;

import services.BankService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BankService bankService = new BankService();

        int choice;

        do {

            System.out.println("\n===== Banking System Menu =====");

            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Account Details");
            System.out.println("5. Show Transaction History");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                // Create Account
                case 1:

                    System.out.print("Enter Account Number: ");
                    int accNo = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter Account Holder Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();

                    bankService.createAccount(accNo, name, balance);

                    break;

                // Deposit Money
                case 2:

                    System.out.print("Enter Account Number: ");
                    int depositAccNo = scanner.nextInt();

                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();

                    bankService.depositMoney(depositAccNo,
                                             depositAmount);

                    break;

                // Withdraw Money
                case 3:

                    System.out.print("Enter Account Number: ");
                    int withdrawAccNo = scanner.nextInt();

                    System.out.print("Enter Withdrawal Amount: ");
                    double withdrawAmount = scanner.nextDouble();

                    bankService.withdrawMoney(withdrawAccNo,
                                              withdrawAmount);

                    break;

                // Display Account
                case 4:

                    System.out.print("Enter Account Number: ");
                    int displayAccNo = scanner.nextInt();

                    bankService.displayAccount(displayAccNo);

                    break;

                // Show Transactions
                case 5:

                    System.out.print("Enter Account Number: ");
                    int transactionAccNo = scanner.nextInt();

                    bankService.showTransactions(transactionAccNo);

                    break;

                // Exit
                case 6:

                    System.out.println("Thank You For Using Banking System!");

                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        scanner.close();
    }
}