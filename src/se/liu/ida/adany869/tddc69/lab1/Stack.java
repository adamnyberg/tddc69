package se.liu.ida.adany869.tddc69.lab1;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    private List myList;

    public Stack() {
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
        int lastIndex = myList.size() - 1;
        Object obj = myList.get(lastIndex);
        myList.remove(lastIndex);
        return obj;
    }
}
