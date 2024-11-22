import java.util.Stack;
public class s11 {
    Stack<Integer> s1;
    Stack <Integer> s2;
    s11(){
        s1 =new Stack<>();
        s2 =new Stack<>();
    }
    s11(int n){
        s1 =new Stack<>();
        s2 =new Stack<>();
    }
    void enqueue(int k){
        s1.push(k);
    }
    int dequeue () {
        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }
        s1.addAll(s2);
        s2.clear();
        int temp = s1.pop();
        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }
        s1.addAll(s2);
        s2.clear();
      return   temp;
    }

    public static void main(String[] args) {
        s11 queue = new s11();
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(100);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());


    }
}
