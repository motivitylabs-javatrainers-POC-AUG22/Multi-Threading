package com.motivity.staticsynchronization;

class ATMCenter
{
    static synchronized void userAction()
    {
        System.out.println(Thread.currentThread().getName() + " entered");
        System.out.println("Performed Transaction");
        try
        {
            Thread.sleep(400);
        }
        catch (Exception e)
        {
            System.out.println("Thread Interrupted");
        }
        System.out.println(Thread.currentThread().getName() + " transaction complete");
        System.out.println(Thread.currentThread().getName() + " leaving.......");
        System.out.println("-----------------------------------------------");
    }
}
class AccountHolder extends Thread
{
    //ATMCenter s = new ATMCenter();
    @Override
    public void run()
    {
        //s.userAction();
        ATMCenter.userAction();
    }
}
public class StaticSynchronization {
    public static void main(String[] args)
    {
        for (int i = 1; i < 4; i++)
        {
            AccountHolder thread = new AccountHolder();
            thread.setName("Person" + i);
            thread.start();
        }
    }
}
