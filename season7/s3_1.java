public class s3_1 {
    //the first part of the question 3
    private Node2 head;
    private Node2 tail;
    public s3_1() {
    }
    public s3_1(Node2 head) {
        this.head=head;
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
    public void Print(){
        Node2 current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        s3_1 jp = new s3_1();
        jp.enqueue(10);
        jp.enqueue(1);
        jp.enqueue(18);
        jp.enqueue(3);
        jp.enqueue(15);
        jp.enqueue(7);
        jp.enqueue(2);
        jp.enqueue(33);
        System.out.print("the queue:");
        jp.Print();
        System.out.println();
        System.out.println(jp.dequeue());
        System.out.println(jp.dequeue());
        System.out.println();
        System.out.print("the new queue:");
        jp.Print();

    }
}
