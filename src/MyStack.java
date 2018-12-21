
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Negin Firouzian
 */


public class MyStack {

    private int maxSize;
    String [] stackArray;
    private int top;

    public MyStack(int s) {
        
        maxSize = s;
        
        stackArray = new String[maxSize];
        
        top = -1;
    }

    public void push(String j) {
        stackArray[++top] = j;
    }

    public String pop() {
        return stackArray[top--];
    }

    public String peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }
}
