/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class Main {
    public static void main(String[] args) {
        SavingsAccountYear account = new SavingsAccountYear(10000, 3);
        for (int i = 0; i < 60; i++) {
            System.out.println(i + ": $" + account.startingBalance());
            account = account.nextYear();
        }
    }
}
