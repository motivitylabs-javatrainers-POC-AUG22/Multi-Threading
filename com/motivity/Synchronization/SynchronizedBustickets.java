package com.motivity.Synchronization;

public class SynchronizedBustickets implements Runnable{
    int tickets = 4;
    int wantedtickets1 , wantedtickets2 , wantedtickets3 ;

    SynchronizedBustickets(int wantedtickets1, int wantedtickets2, int wantedtickets3) {
        this.wantedtickets1 = wantedtickets1;
        this.wantedtickets2 = wantedtickets2;
        this.wantedtickets3 = wantedtickets3;
    }

    synchronized void bookticket (String name, int wantedtickets) //only one thread must be executing this method code.
    {
        if (wantedtickets <= tickets) {
            System.out.println(wantedtickets + "  tickets booked to " + name);
            tickets = tickets - wantedtickets;
        } else {
            System.out.println(" No tickets to book "+Thread.currentThread().getName());
        }
    }
    @Override
    public void run () {

        String name = Thread.currentThread().getName();
        if (name.equals("thread1")) {
            bookticket(name, wantedtickets1);
        } else if (name.equals("thread2")) {
            bookticket(name, wantedtickets2);
        } else {
            bookticket(name, wantedtickets3);
        }
    }

    public static void main (String[]args) throws InterruptedException {

        SynchronizedBustickets tickets = new SynchronizedBustickets(1,1,4);

        Thread thread1 = new Thread(tickets);
        Thread thread2 = new Thread(tickets);
        Thread thread3 = new Thread(tickets);
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread3.setName("thread3");
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
