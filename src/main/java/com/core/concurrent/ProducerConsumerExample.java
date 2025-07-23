package main.java.com.core.concurrent;

//Implement a simple Producer-Consumer pattern using Java's BlockingQueue.
// Create a producer that adds elements to the queue and a consumer that takes elements
// from the queue.

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable{

private final BlockingQueue blockingDeque;

public Producer(BlockingQueue blockingDeque){
    this.blockingDeque = blockingDeque;
}
    @Override
    public void run() {
    try {
        for(int i=0; i<5; i++){
            blockingDeque.put(i);
            System.out.println("Produced" +i);
            Thread.sleep(100);
        }
    }catch (InterruptedException e){
        Thread.currentThread().interrupt();
    }

    }
}

class Consumer implements Runnable{

    private final BlockingQueue blockingDeque;

    public Consumer(BlockingQueue blockingDeque){
        this.blockingDeque = blockingDeque;
    }
    @Override
    public void run() {
        try {
            while (true) {
                Integer value = (Integer) blockingDeque.take();
                System.out.println("Consumed: " + value);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        BlockingQueue blockingDeque = new ArrayBlockingQueue<>(10);
        Thread produccerThread = new Thread(new Producer(blockingDeque));
        Thread consumerThread = new Thread(new Consumer(blockingDeque));
        produccerThread.start();
        consumerThread.start();
    }
}
