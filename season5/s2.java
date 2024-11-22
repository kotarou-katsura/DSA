import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Stack;
public class s2 {
    void findSame(Stack <Integer> s,Queue <Integer> q){
        while (!s.isEmpty())
        {
            int currentS = s.pop();
            for(int j =0; j<q.size();j++){
                int currentQ = q.poll();
                if(currentQ== currentS)
                    System.out.println(currentQ);
                q.offer(currentQ);
            }
        }
    }
    public static void main(String[] args) {
        s2 function = new s2();
        Stack <Integer> s = new Stack<>();
        Queue <Integer> q = new ArrayDeque<>(5);
        for (int i = 5;i<10;i++){
            s.push(i);
            q.offer(i+2);
        }
        function.findSame(s,q);
    }
}
