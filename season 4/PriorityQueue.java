public class PriorityQueue {
    private int[] elements;
    private int front;
    private int rear;
    private int n;

    public PriorityQueue() {
        this(20);
    }

    public PriorityQueue(int n) {
        this.n = n;
        elements = new int[n];
        front = 0;
        rear = -1;
    }

    public void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % n;
        elements[rear] = item;
    }

    public int dequeueH() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int item = elements[front];
        int index = 0;
        for (int i = this.front; i <= rear; i++) {
            if(item<=elements[i]) {
                item = elements[i];
                index =i;
            }
        }
        for (int i =index; i<rear;i++)
        {
            elements[i]=elements[i+1];
        }
        rear--;
        return item;
    }

    public int dequeueL() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int item = elements[front];
        int index = 0;
        for (int i = this.front; i <= rear; i++) {
            if(item>=elements[i]) {
                item = elements[i];
                index =i;
            }
        }
        rear--;
        for (int i =index; i<rear;i++)
        {
            elements[i]=elements[i+1];
        }
        return item;
    }

    public boolean isEmpty() {
        return rear == -1;
    }

    public boolean isFull() {
        return rear == n;
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(5);
        queue.enqueue(1);
        queue.enqueue(30);
        queue.enqueue(20);
        queue.enqueue(7);
        queue.enqueue(57);
        System.out.println("Elements in priority order:");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeueH());
        }
    }
}
