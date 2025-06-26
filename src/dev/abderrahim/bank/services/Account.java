package dev.abderrahim.bank.services;

import java.util.concurrent.ThreadLocalRandom;

public class Account implements AccountService {
    private final long id;
    private int balance;

    public Account() {
        this.id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        this.balance = 0;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Cannot deposit a negative amount.");

        this.balance += amount;
    }

    @Override
    public void withdraw(int amount) {

        if (amount <= 0)
            throw new IllegalArgumentException("Withdrawn amount cannot be zero or a negative number.");
        else if (balance != 0 && amount > balance)
            throw new IllegalArgumentException("The amount is greater than the current balance");

        this.balance -= amount;
    }

    @Override
    public void printStatement() {
        System.out.println("Date\t\t||\tAmount\t||\tBalance");
    }
}