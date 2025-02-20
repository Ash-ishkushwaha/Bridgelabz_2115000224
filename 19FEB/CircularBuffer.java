import java.util.*;

class CircularBuffer {
    private int[] buffer;
    private int head = 0, tail = 0, size = 0, capacity;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
    }

    public void enqueue(int value) {
        if (size == capacity) {
            head = (head + 1) % capacity;
        } else {
            size++;
        }
        buffer[tail] = value;
        tail = (tail + 1) % capacity;
    }

    public List<Integer> getBufferContents() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0, index = head; i < size; i++) {
            result.add(buffer[index]);
            index = (index + 1) % capacity;
        }
        return result;
    }

    public static void main(String[] args) {
        CircularBuffer buffer = new CircularBuffer(3);
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);
        System.out.println(buffer.getBufferContents());
        buffer.enqueue(4);
        System.out.println(buffer.getBufferContents());
    }
}