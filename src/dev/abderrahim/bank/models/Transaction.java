package dev.abderrahim.bank.models;

import dev.abderrahim.bank.types.TransactionType;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public record Transaction(int amount, TransactionType type, Instant timestamp, int balance) {
    public Transaction {
        // Validates the given Instant object to prevent inserting an old timestamp
        if (timestamp.isBefore(Instant.now().minusMillis(1)))
            throw new IllegalArgumentException("Invalid timestamp value.");
    }

    @Override
    public String toString() {

        ZonedDateTime date = timestamp().atZone(ZoneId.systemDefault());
        String formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);

        // If the type of the transaction is WITHDRAW then a negative number is used in
        // the output. Otherwise, a positive number is printed
        String amount = this.type().equals(TransactionType.WITHDRAW) ? "-" + this.amount() : "+" + this.amount();

        return formattedDate + "\t||\t" + amount + "\t||\t" + balance();
    }
}