package com.motivity.banking.com;

public class AccountHolder {

    public static class WithdrawMoney implements Runnable {
        private BankAccount bankAccount;
        private double withdrawAmount;
        Thread t1;

      public WithdrawMoney(BankAccount bankAccount, double withdrawAmount, Thread t1){
             this.bankAccount = bankAccount;
             this.withdrawAmount = withdrawAmount;
             this.t1=t1;
        }
        @Override
        public void run () {
            try {


                makeWithdrawal(withdrawAmount);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (bankAccount.getBalance() < 0) {
                System.out.println("not enough cash to withdrawal ");
            }
           t1.start();

        }
        private synchronized void makeWithdrawal ( double withdrawAmount) throws InterruptedException {

            if (bankAccount.getBalance() >= withdrawAmount) {

                System.out.println(Thread.currentThread().getName() + "  is going to withdraw Rs " + withdrawAmount);
                Thread.sleep(3000);
                bankAccount.withdraw(withdrawAmount);
                System.out.println(Thread.currentThread().getName() + " Successfully Withdrawal Amount " + withdrawAmount);

            }
            else {

                System.out.println(Thread.currentThread().getName() + " Not enough money to withdraw ");
                System.out.println(" available balance is " + bankAccount.getBalance());
            }
        }
    }

    public static class DepositMoney implements Runnable{
        private double depositAmount;
        private BankAccount bankAccount;

        public DepositMoney( BankAccount bankAccount ,double depositAmount) {
            this.depositAmount = depositAmount;
            this.bankAccount = bankAccount;
        }

        @Override
        public void run() {
            try {
                makeDeposit();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private synchronized void makeDeposit() throws InterruptedException {
            if(depositAmount>=100 )
            {
                System.out.println(depositAmount+"  deposited successfully  ");
                bankAccount.deposit(depositAmount);
                System.out.println(" Available Balance is "+bankAccount.getBalance());

            }
            else
            {
                System.out.println("deposit amount more then 100 ");
            }

        }
    }
    public  static class SendEmail implements Runnable
    {
        BankAccount account;

        public SendEmail(BankAccount account) {
            this.account = account;
        }

        void sendingMessage() throws InterruptedException {

            DepositMoney depositMoney;

            System.out.println(Thread.currentThread().getName()+" sending notification to mail and mobile  \n Available balance is  "+account.getBalance());
        }
        @Override
        public void run() {
            try {


                sendingMessage();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
