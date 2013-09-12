package se.liu.ida.adany869.tddc69.lab1;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private List myList;

    public Queue() {
        this.myList = new ArrayList();
    }

    public boolean isEmpty() {
        return myList.isEmpty();
    }

    public int size() {
        return myList.size();
    }

    public boolean contains(Object o) {
        return myList.contains(o);
    }

    public void clear() {
        myList.clear();
    }

    public void push(Object obj) {
        this.myList.add(obj);
    }

    public Object pop() {
        Object obj = myList.get(0);
        myList.remove(0);
        return obj;
    }

}
