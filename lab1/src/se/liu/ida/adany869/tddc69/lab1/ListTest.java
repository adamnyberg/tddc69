package se.liu.ida.adany869.tddc69.lab1;

public class ListTest {
    public static void main(String[] args) {

        // Test stack class
        Stack stack = new Stack();
        stack.push("adam");
        stack.push("greta");
        stack.push("harald");
        stack.push("jonas");

        System.out.println("Stack:");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(' ');

        // Test queue class
        Queue queue = new Queue();
        queue.push("adam");
        queue.push("greta");
        queue.push("harald");
        queue.push("jonas");

        System.out.println("Queue");
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}
