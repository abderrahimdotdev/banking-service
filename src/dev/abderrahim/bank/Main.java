package dev.abderrahim.bank;

import dev.abderrahim.bank.services.Account;
import dev.abderrahim.bank.services.AccountService;

public class Main {
    public static void main(String[] args) throws Exception {
        
        AccountService acc = new Account();

        try{
            acc.deposit(1000);
            acc.deposit(2000);
            acc.withdraw(500);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        

        acc.printStatement();
    }
}
