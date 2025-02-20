package org.example;

import org.example.model.QueueOfStacks;
import org.example.model.StaticStack;
import org.example.model.MatrixOperations;

public class Main {
    public static void main(String[] args) {
        int n = 3;


        QueueOfStacks qOfStacks = new QueueOfStacks(n);


        StaticStack row0 = new StaticStack();
        row0.add(1); // col 0
        row0.add(2); // col 1
        row0.add(3); // col 2
        qOfStacks.enqueue(row0);


        StaticStack row1 = new StaticStack();
        row1.add(4);
        row1.add(5);
        row1.add(6);
        qOfStacks.enqueue(row1);


        StaticStack row2 = new StaticStack();
        row2.add(7);
        row2.add(8);
        row2.add(9);
        qOfStacks.enqueue(row2);


        MatrixOperations.printMatrix(qOfStacks, "Matriz original");


        int traza = MatrixOperations.trace(qOfStacks);
        System.out.println("Traza = " + traza);


        QueueOfStacks snail = MatrixOperations.snailMatrix(n);
        MatrixOperations.printMatrix(snail, "Matriz caracol");


        QueueOfStacks snailT = MatrixOperations.transpose(snail);
        MatrixOperations.printMatrix(snailT, "Matriz caracol traspuesta");
    }
}