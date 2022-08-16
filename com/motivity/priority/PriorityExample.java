package com.motivity.priority;

class Ambulance implements Runnable{
    @Override
    public void run() {
        for (int i=1;i<6;++i)
        {
            System.out.println("high priority Ambulance  first 5 times");

        }
    }
}
class Buses implements Runnable{
    @Override
    public void run() {
        System.out.println("low priority thread current priority is "+Thread.currentThread().getPriority());
        for (int i=1;i<6;++i)
        {
            System.out.println("low priority buses last 5 times");
             }
    }
}
public class PriorityExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("priority of "+Thread.currentThread().getName()+" thread  is  "+Thread.currentThread().getPriority());

        Ambulance ambulanceThread=new Ambulance();
        Thread thread1=new Thread(ambulanceThread);
        Buses busThread=new Buses();
        Thread thread2=new Thread(busThread);
        thread1.start();
        thread1.join(100);
        thread2.start();
        thread1.setPriority(10);//we can set priority of the thread 1-10
        thread2.setPriority(10);
        System.out.println("priority of child "+thread1.getName()+" is "+thread1.getPriority());//return the priority of thread
    }
}
