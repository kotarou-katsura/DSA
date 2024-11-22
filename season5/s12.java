import java.util.Queue;
import java.util.ArrayDeque;
public class s12 {
    Queue <Integer> q1;
    Queue <Integer> q2;
    s12(){
         q1 =new ArrayDeque<>(5);
         q2 =new ArrayDeque<>(5);
    }
    s12(int n){
         q1 =new ArrayDeque<>(n);
         q2 =new ArrayDeque<>(n);
    }
    void push(int k){
        q1.offer(k);
    }
    int pop (){
        if ((q1.isEmpty()))
            throw new IllegalStateException("stack is empty");
        else {
            while(q1.size()>1){
                q2.offer(q1.poll());
            }
            int te = q1.poll();
            Queue <Integer> temp= q1;
            q1=q2;
            q2=temp;
            return te;
        }
    }

    public static void main(String[] args) {
        s12 stack = new s12(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
