import java.util.*;

public class Queue_And_Stack {

    static class Element {
        int value, time;

        Element(int value, int time) {
            this.value = value;
            this.time = time;
        }
    }

    // Stack using PriorityQueue and Comparator (LIFO)
    static class PriorityQueueStack {
        int time = 0;
        PriorityQueue<Element> stack;

        PriorityQueueStack() {
            stack = new PriorityQueue<>(
                    Comparator.comparingInt((Element e) -> -e.time)
            );
        }

        void push(int val) {
            stack.offer(new Element(val, time++));
        }

        int pop() {
            return stack.poll().value;
        }

        boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    // Queue using PriorityQueue and Comparator (FIFO)
    static class PriorityQueueQueue {
        int time = 0;
        PriorityQueue<Element> queue;

        PriorityQueueQueue() {
            queue = new PriorityQueue<>(
                    Comparator.comparingInt(e -> e.time)
            );
        }

        void enqueue(int val) {
            queue.offer(new Element(val, time++));
        }

        int dequeue() {
            return queue.poll().value;
        }

        boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Stack ===");
        PriorityQueueStack stack = new PriorityQueueStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        while (!stack.isEmpty()) {
            System.out.println("Pop: " + stack.pop());
        }

        System.out.println("\n=== Queue ===");
        PriorityQueueQueue queue = new PriorityQueueQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        while (!queue.isEmpty()) {
            System.out.println("Dequeue: " + queue.dequeue());
        }
    }
}
