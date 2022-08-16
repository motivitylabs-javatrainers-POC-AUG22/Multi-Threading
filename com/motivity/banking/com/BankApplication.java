package com.motivity.banking.com;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankApplication {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount=new BankAccount();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Welcome to banking Application ");
        System.out.println("********** select banking service ********** \n 1.withdrawal \n 2.deposit");
        int choice=scanner.nextInt();
        ExecutorService executorService= Executors.newFixedThreadPool(3);

        Thread thread=new Thread(new AccountHolder.SendEmail(bankAccount));
        if(choice==1)
        {
            System.out.println("Enter withdrawal Amount");
            double withdrawAmount=scanner.nextDouble();
            executorService.execute(new AccountHolder.WithdrawMoney(bankAccount,withdrawAmount,thread));

        } else if (choice==2) {
            System.out.println("Enter deposit Amount");
            double depositamount=scanner.nextDouble();
          executorService.execute(new AccountHolder.DepositMoney(bankAccount,depositamount));

        }


      // executorService.execute(new AccountHolder.SendEmail());


        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }
}
