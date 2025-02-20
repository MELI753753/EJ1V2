package org.example.model;

public class QueueOfStacks {
    private StaticStack[] array;
    private int front;
    private int count;
    private final int max;

    public QueueOfStacks(int n) {
        this.max = n;
        this.array = new StaticStack[n];
        this.front = 0;
        this.count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == max;
    }


    public void enqueue(StaticStack st) {
        if (isFull()) {
            throw new RuntimeException("QueueOfStacks llena");
        }
        int pos = (front + count) % max;
        array[pos] = st;
        count++;
    }


    public StaticStack dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("QueueOfStacks vacia");
        }
        StaticStack st = array[front];
        front = (front + 1) % max;
        count--;
        return st;
    }


    public int size() {
        return count;
    }
}