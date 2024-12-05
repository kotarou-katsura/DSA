import java.util.Scanner;
import java.util.Stack;
import java.awt.*;
import javax.swing.*;

public class Expression extends JPanel {
    //char arr[];
    String str, expression = "",fExp;
    Stack<String> stackOperator = new Stack<>();
    Stack<String> stackOperand = new Stack<>();
    int sign = -1;

    Expression(String str) {
        this.str = str;
    }

    String toPrefix() {
        char x;
        for (int i = 0; i < str.length(); i++) {
            x = str.charAt(i);
            if (x == '-' && (i == 0 || str.charAt(i - 1) == '-' || str.charAt(i - 1) == '+' || str.charAt(i - 1) == '/' || str.charAt(i - 1) == '*' || str.charAt(i - 1) == '^' || str.charAt(i - 1) == '(')) {
                i++;
                x = str.charAt(i);
                if ((int) x >= 48 && (int) x != 94) {
                    int j = 0;
                    while (i + j < str.length() && str.charAt(i + j) >= 48 && str.charAt(i + j) != 94) j++;
                    stackOperand.push('-'+str.substring(i, i + j));
                    i += j - 1;
                }
            }
            else if ((int) x >= 48 && (int) x != 94) {
                int j = 0;
                while (i + j < str.length() && str.charAt(i + j) >= 48 && str.charAt(i + j) != 94) j++;
                stackOperand.push(str.substring(i, i + j));
                i += j - 1;
            } else {
                if ((int) x == 41)  //)
                {
                    String temp = "";
                    String f, s;
                    s = stackOperand.pop();
                    f = stackOperand.pop();
                    temp = f + " " + s;
                    while (!stackOperator.isEmpty() && !stackOperator.peek().equals("(")) {
                        temp = stackOperator.pop() + " " + temp;
                    }
                    stackOperator.pop();
                    stackOperand.push(temp);
                } else if (!higherPriority(x)) {
                    while (!stackOperator.isEmpty() && !higherPriority(x)) {
                        if (!stackOperator.peek().equals("(")) {
                            String temp = "";
                            String f, s;
                            s = stackOperand.pop();
                            temp=s;
                            if (!stackOperand.isEmpty()) {
                                f = stackOperand.pop();
                                temp = f + " " + s;
                            }
                            stackOperand.push(stackOperator.pop() + " " + temp);
                        } else stackOperator.pop();
                    }
                    stackOperator.push(String.valueOf(x));

                } else stackOperator.push(String.valueOf(x));
            }
        }
        while (!stackOperator.isEmpty()) {
            String temp = "";
            String f, s;
            s = stackOperand.pop();
            f = stackOperand.pop();
            temp = f + " " + s;
            stackOperand.push(stackOperator.pop() + " " + temp);
        }
        return stackOperand.peek();
    }

    String toPostfix() {
        expression = "";
        char x;
        for (int i = 0; i < str.length(); i++) {
            x = str.charAt(i);
            if (x == '-' && (i == 0 || str.charAt(i - 1) == '-' || str.charAt(i - 1) == '+' || str.charAt(i - 1) == '/' || str.charAt(i - 1) == '*' || str.charAt(i - 1) == '^' || str.charAt(i - 1) == '(')) {
                i++;
                x = str.charAt(i);
                if ((int) x >= 48 && (int) x != 94) {
                    int j = 0;
                    while (i + j < str.length() && str.charAt(i + j) >= 48 && str.charAt(i + j) != 94) j++;
                    expression += '-' + str.substring(i, i + j) + " ";
                    i += j - 1;
                }
            } else {
                if ((int) x >= 48 && (int) x != 94) {
                    int j = 0;
                    while (i + j < str.length() && str.charAt(i + j) >= 48 && str.charAt(i + j) != 94) j++;
                    expression += str.substring(i, i + j) + " ";
                    i += j - 1;
                } else {
                    if ((int) x == 41)  //)
                    {
                        while (!stackOperator.isEmpty() && !stackOperator.peek().equals("(")) {
                            expression = expression + stackOperator.pop() + " ";
                        }
                        stackOperator.pop();
                    } else if (!higherPriority(x)) {
                        while (!higherPriority(x)) {
                            expression = expression + stackOperator.pop() + " ";
                        }
                        stackOperator.push(String.valueOf(x));
                    } else stackOperator.push(String.valueOf(x));
                }
            }
        }
        while (!stackOperator.isEmpty()) {
            expression = expression + stackOperator.pop();
        }
        return expression;
    }

    int calculate(String expression) {
        Stack<Integer> st = new Stack<>();
        char x;

        for (int i = 0; i < expression.length(); i++) {
            x = expression.charAt(i);
            if ( x == ' ') continue;
            else if (i!=expression.length()-1 && x == '-' &&  expression.charAt(i + 1) != ' ') {
                i++;
                x = expression.charAt(i);
                if ((int) x >= 48 &&  (int) x != 94) {
                    int j = 0;
                    int temp = 0;
                    while (i + j < expression.length() && expression.charAt(i + j) >= 48 && expression.charAt(i + j) != 94) {

                        temp += expression.charAt(i + j) - '0';
                        j++;
                        temp *= 10;
                    }
                    temp /= 10;
                    st.push(-temp);
                    i += j - 1;
                }
            }
            else if ((int) x >= 48 && (int) x != 94) {
                int j = 0;
                int temp = 0;
                while (i + j < expression.length() && expression.charAt(i + j) >= 48 && expression.charAt(i + j) != 94) {

                    temp += expression.charAt(i + j) - '0';
                    j++;
                    temp *= 10;
                }
                temp /= 10;
                st.push(temp);
                i += j - 1;
            } else {
                int f, s;
                s = st.pop();
                f = st.pop();
                if (expression.charAt(i) == 45) //-
                    st.push(f - s);
                else if (expression.charAt(i) == 43) //+
                    st.push(f + s);
                else if (expression.charAt(i) == 42) //*
                    st.push(f * s);
                else if (expression.charAt(i) == 47) //*
                    st.push(f / s);
                else if (expression.charAt(i) == 94) //^
                    st.push((int) Math.pow(f, s));
            }
        }
        return st.pop();
    }

    String pointValue(int value,String expression) {

        String string = "";
        char x;
        for (int i = 0; i < expression.length(); i++) {
            x = expression.charAt(i);
            if ((int) x >= 48 && (int) x != 94) {
                int j = 0;
                while (i + j < expression.length() && expression.charAt(i + j) >= 48 && expression.charAt(i + j) != 94) j++;
                string += String.valueOf(value);
                i += j - 1;
            } else string += expression.charAt(i);
        }
//        System.out.println(expression);
        expression = string;
        return string;
    }

    boolean higherPriority(char x) {
        if (stackOperator.empty()) return true;
        if ((x == '*' || x == '/') && (this.stackOperator.peek().equals("-") || this.stackOperator.peek().equals("+")))
            return true;
        else if ((x == '*' || x == '/' || x == '+' || x == '-') && (this.stackOperator.peek().equals("("))) return true;
        else if (x == '(') return true;
        else if (x == '^') return true;
        else return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int margin = 50;

        // Draw axes
        g2d.drawLine(margin, height / 2, width - margin, height / 2);
        g2d.drawLine(width / 2, margin, width / 2, height - margin);
        int range=100;
        int scale=100;
        for (int x =-range ; x <= range; x++) {

            int x1=x;
            int y1 = this.calculate(this.pointValue(x,this.toPostfix()));
//            System.out.println(x);
//            System.out.println(y1);
            int x2=x+1;
            int y2 = this.calculate(this.pointValue(x+1,this.toPostfix()));
            g2d.fillOval(x + getWidth() / 2, getHeight() / 2 - y1/scale, 2, 2);
            g2d.drawLine(x1 + getWidth() / 2, getHeight() / 2 - y1/scale, x2 + getWidth() / 2,getHeight() / 2 - y2/scale);
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        System.out.println("please enter the infix expression");
        String l = scanner.next();
        if (l.charAt(0) == 'x' || l.charAt(1) == 'x') {
            System.out.println("enter the value for x:");
            int value = choice.nextInt();
            Expression d = new Expression(l);
            d.pointValue(value, d.toPostfix());
            System.out.print("the answer:");
            System.out.println(d.calculate(d.pointValue(value, d.toPostfix())));
            JFrame frame = new JFrame(" Drawing Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.add(d);
            frame.setVisible(true);
        } else {
            Expression d = new Expression(l);
            System.out.print("Postfix:");
            System.out.println(d.toPostfix());
            System.out.print("the answer:");
            System.out.println(d.calculate(d.toPostfix()));
            System.out.print("Prefix:");
            System.out.println(d.toPrefix());
        }
    }
}
