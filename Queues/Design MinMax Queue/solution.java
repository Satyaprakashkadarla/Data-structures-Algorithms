import java.util.*;

class SpecialQueue {
    private Deque<Integer> queue;
    private Deque<Integer> minDeque;
    private Deque<Integer> maxDeque;

    public SpecialQueue() {
        queue = new LinkedList<>();
        minDeque = new LinkedList<>();
        maxDeque = new LinkedList<>();
    }

    public void enqueue(int x) {
        queue.offer(x);

        while (!minDeque.isEmpty() && minDeque.peekLast() > x) {
            minDeque.pollLast();
        }
        minDeque.offer(x);

        while (!maxDeque.isEmpty() && maxDeque.peekLast() < x) {
            maxDeque.pollLast();
        }
        maxDeque.offer(x);
    }

    public void dequeue() {
        if (!queue.isEmpty()) {
            int val = queue.poll();

            if (val == minDeque.peek()) {
                minDeque.poll();
            }

            if (val == maxDeque.peek()) {
                maxDeque.poll();
            }
        }
    }

    public int getFront() {
        if (!queue.isEmpty()) {
            return queue.peek();
        }
        throw new NoSuchElementException("Queue is empty");
    }

    public int getMin() {
        if (!minDeque.isEmpty()) {
            return minDeque.peek();
        }
        throw new NoSuchElementException("Queue is empty");
    }

    public int getMax() {
        if (!maxDeque.isEmpty()) {
            return maxDeque.peek();
        }
        throw new NoSuchElementException("Queue is empty");
    }
}
