import java.util.*;

public class StackUsingQueues {
    private Queue<Integer> queue1 = new LinkedList<>();
    private Queue<Integer> queue2 = new LinkedList<>();

    public void push(int x) {
        queue1.add(x);
    }

    public int pop() {
        if (queue1.isEmpty())
            throw new NoSuchElementException("Stack is empty!");

        while (queue1.size() > 1) {
            queue2.add(queue1.remove());
        }
        int popped = queue1.remove();

        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return popped;
    }

    public int top() {
        if (queue1.isEmpty())
            throw new NoSuchElementException("Stack is empty!");

        while (queue1.size() > 1) {
            queue2.add(queue1.remove());
        }
        int top = queue1.peek();
        queue2.add(queue1.remove());

        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return top;
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        StackUsingQueues stack = new StackUsingQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Top Element: " + stack.top());
        System.out.println("Popped: " + stack.pop());
    }
}
