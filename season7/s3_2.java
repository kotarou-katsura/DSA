public class s3_2 {
    //the second part of the question 3
    private Node2 head;
    private Node2 tail;

    public s3_2() {
    }

    public s3_2(Node2 head) {
        this.head = head;
    }

    public void enqueue(int data) {
        Node2 newNode = new Node2(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public int dequeue() {
        Node2 current = head;
        Node2 smallest = head;
        while (current != null) {
            if (smallest.data > current.data) {
                smallest = current;
            }
            current = current.next;
        }
        if (smallest==head){
            head=smallest.next;
            head.prev=null;
        }
        else{
            current = smallest.prev;
            current.next = smallest.next;
            current.next.prev = smallest.prev;}
        return smallest.data;

    }

    public void Print() {
        Node2 current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void sort(){
        Node2 end = tail;
        for (int i=0;i<8;i++)
        {
            Node2 current = head;
            Node2 smallest = head;
            while (current != end) {
                if (smallest.data > current.data) {
                    smallest = current;
                }
                current = current.next;
            }
            if (smallest==head){
                head=smallest.next;
                head.prev=null;
            }
            else{
            current = smallest.prev;
            current.next = smallest.next;
            current.next.prev = smallest.prev;}
            this.enqueue(smallest.data);
            if(i==0){
                end=tail;
            }
        }
    }
    public static void main(String[] args) {
        s3_2 jp = new s3_2();
        jp.enqueue(1);
        jp.enqueue(10);
        jp.enqueue(18);
        jp.enqueue(3);
        jp.enqueue(15);
        jp.enqueue(7);
        jp.enqueue(2);
        jp.enqueue(33);
        System.out.print("the queue:");
        jp.Print();
        jp.sort();
        System.out.print("the queue:");
        jp.Print();

    }

}
