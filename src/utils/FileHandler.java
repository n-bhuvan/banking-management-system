package utils;

import java.io.*;
import java.util.ArrayList;

import models.Account;

public class FileHandler {

    private static final String FILE_NAME = "accounts.dat";

    // Save Accounts
    public static void saveAccounts(ArrayList<Account> accounts) {

        try {

            ObjectOutputStream outputStream =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME));

            outputStream.writeObject(accounts);

            outputStream.close();

            System.out.println("Accounts Saved Successfully!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // Load Accounts
    public static ArrayList<Account> loadAccounts() {

        try {

            ObjectInputStream inputStream =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME));

            ArrayList<Account> accounts =
                    (ArrayList<Account>) inputStream.readObject();

            inputStream.close();

            return accounts;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }
}