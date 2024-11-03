import java.util.Scanner;

public class BigNumber {
    int numberOfDigits;
    int[] Digits;
    char sign;

    public BigNumber() {
        this.numberOfDigits = 10;
        this.Digits = new int[numberOfDigits];
        this.sign = '+';
    }

    public BigNumber(String str) {
        String[] stringNumb = str.split("");
        this.numberOfDigits = stringNumb.length;
        this.Digits = new int[numberOfDigits];
        for (int i = 0; i < numberOfDigits; i++) {
            this.Digits[i] = Integer.parseInt(stringNumb[i]);
        }
        this.getSign();
    }

    public BigNumber(int num) {
        this(Integer.toString(num));
    }

    public void getSign() {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the sign(- or +):");
        this.sign = scan.nextLine().charAt(0);
    }

    public void copyBigNumber(BigNumber other) {
        this.numberOfDigits = other.numberOfDigits;
        this.Digits = other.Digits;
        this.sign = other.sign;
    }

    public BigNumber shiftR(int x) {
        this.reversePadding();
        BigNumber result = new BigNumber();
        result.numberOfDigits = this.numberOfDigits - x;
        int[] newDigits = new int[result.numberOfDigits];
        for (int i = 0; i < result.numberOfDigits; i++) {
            newDigits[i] = this.Digits[i];
        }
        result.Digits = newDigits;
        return result;
    }

    public void reversePadding() {
        this.numberOfDigits = this.Digits.length;
        int count = 0;
        for (int i = 0; i < this.numberOfDigits; i++) {
            if (this.Digits[i] == 0)
                count++;
            else
                break;

        }
        if (count > 0) {
            this.numberOfDigits -= count;
            int[] newDigits = new int[this.numberOfDigits];
            for (int i = 0; i < this.numberOfDigits; i++) {
                newDigits[i] = this.Digits[i + count];
            }
            this.Digits = newDigits;
        }
    }

    public BigNumber padding(int x) {
        this.numberOfDigits += x;
        int[] newDigits = new int[this.numberOfDigits];
        for (int i = 0; i < this.numberOfDigits - x; i++) {
            newDigits[i + x] = this.Digits[i];
        }
        this.Digits = newDigits;
        return this;
    }

    public BigNumber shiftL(int x) {
        this.reversePadding();
        BigNumber result = new BigNumber();
        result.numberOfDigits = this.numberOfDigits + x;
        int[] newDigits = new int[result.numberOfDigits];
        for (int i = 0; i < this.numberOfDigits; i++) {
            newDigits[i] = this.Digits[i];
        }
        result.Digits = newDigits;
        return result;
    }

    public BigNumber preSum(BigNumber other) {
        BigNumber result = new BigNumber();
        if (this.sign == other.sign)
            return this.sum(other, this.sign);
        else if (this.sign == '-') {
            this.swapSign();
            result.copyBigNumber(other.preMinus(this));
            this.swapSign();
            return result;
        } else {
            other.swapSign();
            result.copyBigNumber(this.preMinus(other));
            other.swapSign();
            return result;
        }

    }

    public BigNumber sum(BigNumber other, char sign) {

        BigNumber result = new BigNumber();
        result.sign = this.sign;
        other.reversePadding();
        this.reversePadding();
        if (other.numberOfDigits > this.numberOfDigits) {
            this.padding(other.numberOfDigits - this.numberOfDigits);
        } else if (other.numberOfDigits < this.numberOfDigits) {
            other.padding(this.numberOfDigits - other.numberOfDigits);
        }

        result.numberOfDigits = this.numberOfDigits + 1;
        int carry = 0;
        int[] newDigits = new int[result.numberOfDigits];
        for (int i = result.numberOfDigits - 1; i >= 1; i--) {
            if (this.Digits[i - 1] + other.Digits[i - 1] + carry >= 10) {
                int k = this.Digits[i - 1] + other.Digits[i - 1] + carry;
                newDigits[i] = k % 10;
                carry = k / 10;
            } else {
                newDigits[i] = this.Digits[i - 1] + other.Digits[i - 1] + carry;
                carry = 0;
            }
        }
        newDigits[0] = carry;
        result.Digits = newDigits;
        result.reversePadding();
        result.sign = sign;
        return result;
    }

    public void print() {
        if (this.sign == '-')
            System.out.print(sign);
        for (int num : this.Digits) {
            System.out.print(num);
        }
    }

    public BigNumber minus(BigNumber first, BigNumber second) {
        this.numberOfDigits = first.numberOfDigits;
        int[] newDigits = new int[this.numberOfDigits];
        for (int i = this.numberOfDigits - 1; i >= 0; i--) {
            if (first.Digits[i] + newDigits[i] < second.Digits[i]) {
                newDigits[i - 1] -= 1;
                newDigits[i] += 10;
            }
            newDigits[i] += (first.Digits[i] - second.Digits[i]);
        }
        this.Digits = newDigits;
        this.reversePadding();
        return this;
    }

    public void swapSign() {
        if (this.sign == '+')
            this.sign = '-';
        else
            this.sign = '+';
    }

    public BigNumber preMinus(BigNumber other) {
        this.reversePadding();
        other.reversePadding();
        BigNumber result = new BigNumber();
        if (this.sign == other.sign) {
            if (other.numberOfDigits > this.numberOfDigits) {

                this.padding(other.numberOfDigits - this.numberOfDigits);
                result.sign = this.sign;
                result.swapSign();
                return result.minus(other, this);
            } else if (other.numberOfDigits < this.numberOfDigits) {
                result.sign = this.sign;
                other.padding(this.numberOfDigits - other.numberOfDigits);
                return result.minus(this, other);
            } else {
                int firstGreater = 0;
                for (int i = 0; i < this.numberOfDigits; i++) {
                    if (this.Digits[i] > other.Digits[i]) {
                        firstGreater = 1;
                        break;
                    } else if (this.Digits[i] < other.Digits[i]) {
                        firstGreater = -1;
                        break;
                    }

                }
                if (firstGreater == -1) {
                    result.sign = '-';
                    return result.minus(other, this);
                } else {
                    return result.minus(this, other);
                }
            }

        } else if (this.sign == '+' && other.sign == '-') {
            return this.sum(other, '+');
        } else
            return this.sum(other, '-');
    }

    public static void main(String[] args) {
        BigNumber t = new BigNumber();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter B1 (first big number):");
        String first_str = scanner.nextLine();
        BigNumber first = new BigNumber(first_str);
        System.out.println("enter B2 (second big number):");
        String second_str = scanner.nextLine();
        BigNumber second = new BigNumber(second_str);
        System.out.print("\nB1+B2 =");
        first.preSum(second).print();
        System.out.print("\nB1-B2 =");
        first.preMinus(second).print();
        System.out.print("\nB2-B1 =");
        second.preMinus(first).print();
        System.out.println("\nenter the number n for >>n and <<n :");
        int n = scanner.nextInt();
        System.out.print("\nB2>>" + n + "(right shift) =");
        second.shiftR(n).print();
        System.out.print("\nB2<<n" + n + "(left shift) =");
        second.shiftL(n).print();
    }

    //    public BigNumber multByOneDigit(int d){
//        this.reversePadding();
//        BigNumber result = new BigNumber();
//        int carry=0;
//        result.numberOfDigits=this.numberOfDigits+1;
//        int [] newDigits = new int[result.numberOfDigits];
//        for (int i = result.numberOfDigits-1; i >=1; i--) {
//               int k=this.Digits[i-1]*d+carry;
//               newDigits[i]= k%10;
//               carry=k/10;
//        }
//        newDigits[0]=carry;
//        result.Digits=newDigits;
//        result.reversePadding();
//        return result;
//
//    }
//    public BigNumber mult(BigNumber other){
//        this.reversePadding();
//        BigNumber result = new BigNumber();
//        result.numberOfDigits=this.numberOfDigits+other.numberOfDigits;
//        int [] newDigits = new int[result.numberOfDigits];
//        for (int i =0;i<other.numberOfDigits;i++){
//            if (i!= other.numberOfDigits -1)
//                result=result.sum(this.multByOneDigit(other.Digits[i])).shiftL(1);
//            else
//                result=result.sum(this.multByOneDigit(other.Digits[i]));
//        }
//        return result;
//    }
//    public BigNumber pow(int n){
//        this.reversePadding();
//        BigNumber result = new BigNumber(1);
//        for (int i =0;i<n;i++) {
//            result = result.mult(this);
//
//        }
//        return result;
//    }
//    public BigNumber big_factoriel(int n){
//        this.reversePadding();
//        BigNumber result = new BigNumber(1);
//        for (int i =2;i<=n;i++) {
//            result = result.mult( new BigNumber(i));
//
//        }
//        return result;
//    }
//
//    public BigNumber div(BigNumber other){
//        this.reversePadding();
//        BigNumber result = new BigNumber(1);
//        for (int i = 0;i<())
//        return result;
//    }
}
