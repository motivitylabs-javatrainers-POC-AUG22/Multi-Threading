package com.motivity.lockExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountConditions {
    private static Account account = new Account();

    public static void main(String[] args) {
        System.out.println("Thread 1\t\t Thread 2\t\tBalance");
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(new DepositTask(100));
        executor.execute(new WithdrawTask(500));
        executor.shutdown();

        while (!executor.isShutdown()) {
        }
    }

    public static class DepositTask implements Runnable {
        int amount;

        public DepositTask(int amount) {
            this.amount = amount;
        }

        public void run() {
            try {
                while (true) {
                    account.deposit( amount);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static class WithdrawTask implements Runnable{
        int amount;

        public WithdrawTask(int amount) {
            this.amount = amount;
        }

        public void run() {
            while(true) {
                account.withdraw(amount);
            }
        }
    }


    private static class Account{
        private static Lock lock = new ReentrantLock(true);
        private static Condition newDeposit = lock.newCondition();
        private int balance = 0;
        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            lock.lock();
            try {
                while(balance < amount) {
                    System.out.println("\t\t\tWait for a deposit");
                    newDeposit.await();
                }
                balance -= amount;
                System.out.println("\t\t\tWithdraw " + amount + "\t\t\t\t\t" + getBalance());
            }
            catch(InterruptedException ex) {
                ex.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }

        public void deposit(int amount) {
            lock.lock();
            try {
                balance += amount;
                System.out.println("Deposit " + amount +
                        "\t\t\t\t\t" + getBalance());
                newDeposit.signalAll();
            }
            finally {
                lock.unlock();
            }
        }

    }
}
