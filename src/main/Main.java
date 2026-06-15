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
            System.out.println("2. Login");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. Display Account Details");
            System.out.println("7. Show Transaction History");
            System.out.println("8. Check Admin Access");
            System.out.println("9. Exit");

            System.out.print("Enter Your Choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                // Create Account
                case 1:

                    System.out.print(
                            "Enter Account Number: ");

                    int accNo = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print(
                            "Enter Account Holder Name: ");

                    String name =
                            scanner.nextLine();

                    System.out.print(
                            "Enter Initial Balance: ");

                    double balance =
                            scanner.nextDouble();

                    scanner.nextLine();

                    System.out.print(
                            "Set Account Password: ");

                    String password =
                            scanner.nextLine();

                    System.out.print(
                            "Enter Role (Customer/Admin): ");

                    String role =
                            scanner.nextLine();

                    bankService.createAccount(
                            name,
                            balance,
                            password,
                            role);

                    break;

                case 2:


                System.out.print(
                                "Enter Account Number: ");

                        long loginAccNo =
                                Long.parseLong(
                                        scanner.nextLine());

                        System.out.print(
                                "Enter Password: ");

                        String loginPassword =
                                scanner.nextLine();

                        boolean loginSuccess =
                                bankService.login(
                                        loginAccNo,
                                        loginPassword);

                    if (loginSuccess) {

                        System.out.println(
                                "Login Successful!");

                    } else {

                        System.out.println(
                                "Invalid Credentials!");
                    }

                    break;

                // Deposit Money
                case 3:

                    System.out.print("Enter Account Number: ");
                    int depositAccNo = scanner.nextInt();

                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();

                    bankService.depositMoney(depositAccNo,
                                             depositAmount);

                    break;

                // Withdraw Money
                case 4:

                    System.out.print("Enter Account Number: ");
                    int withdrawAccNo = scanner.nextInt();

                    System.out.print("Enter Withdrawal Amount: ");
                    double withdrawAmount = scanner.nextDouble();

                    bankService.withdrawMoney(withdrawAccNo,
                                              withdrawAmount);

                    break;
                
                case 5:

                    System.out.print(
                            "Enter Sender Account Number: ");

                    int senderAccNo =
                            scanner.nextInt();

                    System.out.print(
                            "Enter Receiver Account Number: ");

                    int receiverAccNo =
                            scanner.nextInt();

                    System.out.print(
                            "Enter Transfer Amount: ");

                    double transferAmount =
                            scanner.nextDouble();

                    bankService.transferMoney(
                            senderAccNo,
                            receiverAccNo,
                            transferAmount);

                    break;
                // Display Account
                case 6:

                    System.out.print("Enter Account Number: ");
                    int displayAccNo = scanner.nextInt();

                    bankService.displayAccount(displayAccNo);

                    break;

                // Show Transactions

                case 7:

                    System.out.print(
                            "Enter Account Number: ");

                    int adminAccNo =
                            scanner.nextInt();

                    boolean isAdmin =
                            bankService.isAdmin(adminAccNo);

                    if (isAdmin) {

                        System.out.println(
                                "Admin Access Granted!");

                    } else {

                        System.out.println(
                                "User Access Only!");
                    }

                    break;

                // Exit
                case 8:

                    System.out.println("Thank You For Using Banking System!");

                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        } while (choice != 8);

        scanner.close();
    }
}