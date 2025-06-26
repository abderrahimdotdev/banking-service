package dev.abderrahim.bank;

import dev.abderrahim.bank.services.Account;
import dev.abderrahim.bank.services.AccountService;

public class Main {
    public static void main(String[] args) throws Exception {
        
        AccountService acc = new Account();

        acc.deposit(1000);
        acc.deposit(2000);
        acc.withdraw(500);

        acc.printStatement();
    }
}
