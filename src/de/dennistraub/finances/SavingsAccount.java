/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class SavingsAccount {
    int balance = 0;

    public void deposit(int amount) {
        balance += amount;
    }

    public int balance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }
}
