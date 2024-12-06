public class s5 {
    //I implemented the stack so that the push and pop operations occur at the end of the linked list.
    private Node1 head;
    private Node1 tail;

    s5() {

    }

    void push(int data) {
        Node1 newNode = new Node1(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    int pop() {
        Node1 current = head;
        if (head!=tail) {
            while (current.next.next != null) {
                current = current.next;
            }
        }
         if (tail == head) {
            int num = head.data;
            head = head.next;
            tail= head;
            return num;
        } else {
            int num = tail.data;
            current.next = null;
            tail = current;
            return num;
        }

    }

    public void Print() {
        Node1 current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        s5 jp = new s5();
        jp.push(10);
        jp.push(1);
        jp.push(18);
        jp.push(3);
        jp.push(15);
        jp.push(7);
        jp.push(2);
        jp.push(33);
        System.out.print("the queue:");
        jp.Print();
        System.out.println();
        System.out.println(jp.pop());
        System.out.println(jp.pop());
        System.out.println(jp.pop());
        System.out.println(jp.pop());
        System.out.println(jp.pop());
        System.out.println(jp.pop());
        System.out.println(jp.pop());
        System.out.println(jp.pop());
        System.out.println();


    }
}
