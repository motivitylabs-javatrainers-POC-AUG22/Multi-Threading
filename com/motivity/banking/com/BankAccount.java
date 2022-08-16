package com.motivity.banking.com;

public class BankAccount {
    private double ac_balance=1000 ;
    public double getBalance()
    {
        return ac_balance;
    }
    public void withdraw(double amount)
    {
        System.out.println("waiting for a withdrawal on progress....");
        ac_balance=ac_balance-amount;

    }
    public void deposit(double deposit)
    {
        ac_balance+=deposit;
}

}
