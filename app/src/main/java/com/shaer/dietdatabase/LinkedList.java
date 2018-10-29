package com.shaer.dietdatabase;

/**
 * Created by mohammad on 12/11/17.
 */

public class LinkedList {

    private Node First = null;
    private Node Last = null;
    private int Size = 0;

    public boolean isEmpty() {
        return Size == 0;
    }

    public void add(Node n) {

        if (isEmpty()) {
            First = n;
        } else {
            Last.setNext(n);
        }

        Last = n;

        Size++;


    }

    public Node getHead(){
        return First;
    }

    public int getSize(){
        return Size;
    }
}


