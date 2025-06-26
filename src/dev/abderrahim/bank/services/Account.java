package dev.abderrahim.bank.services;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.SequencedCollection;

import dev.abderrahim.bank.models.Transaction;
import dev.abderrahim.bank.types.TransactionType;

public class Account implements AccountService {
    private int balance;
    // Since transaction history must be kept ordered and to reduce coupling on an
    // actual implementation, I chose to use a SequencedCollection as data type of
    // transactions history
    private final SequencedCollection<Transaction> transactionsHistory;

    public Account() {
        this.balance = 0;
        this.transactionsHistory = new LinkedList<>();
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Cannot deposit a negative amount.");

        this.balance += amount;
        logTransaction(amount, TransactionType.DEPOSIT);
    }

    @Override
    public void withdraw(int amount) {

        if (amount <= 0)
            throw new IllegalArgumentException("Withdrawn amount cannot be zero or a negative number.");
        else if (balance != 0 && amount > balance)
            throw new IllegalArgumentException("The amount is greater than the current balance");

        this.balance -= amount;
        logTransaction(amount, TransactionType.WITHDRAW);
    }

    @Override
    public void printStatement() {
        System.out.println("Date\t\t||\tAmount\t||\tBalance");
        transactionsHistory.forEach(System.out::println);
    }

    private void logTransaction(int amount, TransactionType type) {
        Transaction t = new Transaction(amount, type, ZonedDateTime.now().toInstant(), balance);
        transactionsHistory.addFirst(t);
    }
}