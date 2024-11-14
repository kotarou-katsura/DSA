import java.util.Scanner;

public class BigNumber {
    int numberOfDigits = 10;
    int[] Digits;
    char sign = '+';

    public BigNumber() {
        this.Digits = new int[numberOfDigits];
    }

    public BigNumber(String str) {
        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
            sign = str.charAt(0);
            str = str.substring(1);
        }
        String[] stringNumb = str.split("");
        this.numberOfDigits = stringNumb.length;
        this.Digits = new int[numberOfDigits];
        for (int i = 0; i < numberOfDigits; i++) {
            this.Digits[i] = Integer.parseInt(stringNumb[i]);
        }
    }

    public BigNumber(int num) {
        this(Integer.toString(num));
    }

    public void copyBigNumber(BigNumber other) {

        this.numberOfDigits = other.numberOfDigits;
        this.Digits = other.Digits;
        this.sign = other.sign;
    }

    public BigNumber shiftR(int x) {
        this.reversePadding();
        BigNumber result = new BigNumber();
        result.sign = this.sign;
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
            if (this.Digits[i] == 0) count++;
            else break;
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
        result.sign = this.sign;
        result.numberOfDigits = this.numberOfDigits + (int) x;
        int[] newDigits = new int[result.numberOfDigits];
        for (int i = 0; i < this.numberOfDigits; i++) {
            newDigits[i] = this.Digits[i];
        }
        result.Digits = newDigits;
        return result;
    }


    public BigNumber sum(BigNumber other) {
        BigNumber result = new BigNumber();
        if (this.sign == other.sign) result.sign = this.sign;
        else if (this.sign == '-') {
            this.swapSign();
            result.copyBigNumber(other.minus(this));
            this.swapSign();
            return result;
        } else {
            other.swapSign();
            result.copyBigNumber(this.minus(other));
            other.swapSign();
            return result;
        }
        other.reversePadding();
        this.reversePadding();
        if (other.numberOfDigits > this.numberOfDigits) {
            this.padding(other.numberOfDigits - this.numberOfDigits);
        } else if (other.numberOfDigits < this.numberOfDigits)
            other.padding(this.numberOfDigits - other.numberOfDigits);

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
        if (this.sign == '-') System.out.print(sign);
        for (int num : this.Digits) {
            System.out.print(num);
        }
    }

    public BigNumber minus(BigNumber other) {
        int[] grater;
        int[] lower;
        this.reversePadding();
        other.reversePadding();
        BigNumber result = new BigNumber();
        if (this.sign != other.sign) {
            BigNumber first = new BigNumber();
            first.copyBigNumber(this);
            BigNumber second = new BigNumber();
            second.copyBigNumber(other);
            first.sign = this.sign;
            second.sign = this.sign;
            return second.sum(first);

        } else {
            if (this.firstIsGrater(other)) {
                if (this.numberOfDigits != other.numberOfDigits)
                    other.padding(this.numberOfDigits - other.numberOfDigits);
                result.sign = this.sign;
                grater = this.Digits;
                lower = other.Digits;
            } else {
                if (this.numberOfDigits != other.numberOfDigits)
                    this.padding(other.numberOfDigits - this.numberOfDigits);
                result.sign = this.sign;
                result.swapSign();
                grater = other.Digits;
                lower = this.Digits;

            }
        }

        int[] newDigits = new int[grater.length];
        for (int i = grater.length - 1; i >= 0; i--) {
            if (grater[i] + newDigits[i] < lower[i]) {
                newDigits[i - 1] -= 1;
                newDigits[i] += 10;
            }
            newDigits[i] += (grater[i] - lower[i]);
        }
        result.Digits = newDigits;
        result.reversePadding();
        return result;
    }

    public void swapSign() {
        if (this.sign == '+') this.sign = '-';
        else this.sign = '+';
    }

    public BigNumber multByOneDigit(int d) {

        BigNumber result = new BigNumber();
        int carry = 0;
        result.numberOfDigits = this.numberOfDigits + 1;
        int[] newDigits = new int[result.numberOfDigits];
        for (int i = result.numberOfDigits - 1; i >= 1; i--) {
            int k = this.Digits[i - 1] * d + carry;
            newDigits[i] = k % 10;
            carry = k / 10;
        }
        if ((result.sign == '-' && this.sign == '+') || (result.sign == '+' && this.sign == '-')) result.sign = '-';
        else result.sign = '+';
        newDigits[0] = carry;
        result.Digits = newDigits;
        result.reversePadding();
        return result;
    }

    public BigNumber mult(BigNumber other) {
        this.reversePadding();
        BigNumber result = new BigNumber();
        result.numberOfDigits = this.numberOfDigits + other.numberOfDigits;
        for (int i = 0; i < other.numberOfDigits; i++) {
            if (i != other.numberOfDigits - 1) result = result.sum(this.multByOneDigit(other.Digits[i])).shiftL(1);
            else result = result.sum(this.multByOneDigit(other.Digits[i]));
        }
        if (this.sign == '-' && other.sign == '-') result.sign = '+';
        else if ((other.sign == '-' && this.sign == '+') || (other.sign == '+' && this.sign == '-')) result.sign = '-';
        else result.sign = '+';
        return result;
    }

    public BigNumber pow(int n) {
        this.reversePadding();
        BigNumber result = new BigNumber(1);
        for (int i = 0; i < n; i++) {
            result = result.mult(this);
        }
        if (n % 2 == 0) result.sign = '+';
        else if (this.sign == '-') result.sign = '-';
        return result;
    }

    public String converToString() {
        StringBuilder sb = new StringBuilder();
        for (int num : this.Digits) {
            sb.append(num);
        }
        return sb.toString();
    }

    public BigNumber multKaratsuba(BigNumber other) {
        if (this.converToString().length() == 1 || other.converToString().length() == 1)
            return new BigNumber(this.Digits[0] * other.Digits[0]);
        else {
            int max = Math.max(this.numberOfDigits, other.numberOfDigits);
            if (max % 2 != 0) max += 1;
            this.padding(max - this.numberOfDigits);
            other.padding(max - other.numberOfDigits);
            BigNumber result = new BigNumber();
            int firstHalf = this.numberOfDigits / 2;
            BigNumber num1_firstHalf = new BigNumber(this.converToString().substring(0, firstHalf));
            BigNumber num1_lastHalf = new BigNumber(this.converToString().substring(firstHalf));
            BigNumber num2_firstHalf = new BigNumber(other.converToString().substring(0, firstHalf));
            BigNumber num2_lastHalf = new BigNumber(other.converToString().substring(firstHalf));
            return (num1_firstHalf.multKaratsuba(num2_firstHalf).shiftL(firstHalf * 2).sum(num1_lastHalf.multKaratsuba(num2_lastHalf))).sum(num1_firstHalf.multKaratsuba(num2_lastHalf).shiftL((firstHalf)).sum(num1_lastHalf.multKaratsuba(num2_firstHalf).shiftL((firstHalf))));
        }
    }

    public boolean firstIsGrater(BigNumber other) {
        this.reversePadding();
        other.reversePadding();
        if (other.numberOfDigits > this.numberOfDigits) return false;
        else if (other.numberOfDigits < this.numberOfDigits) return true;
        else {
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
            if (firstGreater == -1) return false;

            else return true;

        }
    }

    public void copyPart(BigNumber other, int k) {
        this.reversePadding();
        this.numberOfDigits = k;
        this.Digits = new int[k];
        for (int i = 0; i < k; i++) {
            this.Digits[i] = other.Digits[i];
        }
        this.sign = other.sign;
    }

    public BigNumber div(BigNumber other) {
        BigNumber result = new BigNumber();
        if (this.numberOfDigits < other.numberOfDigits) return new BigNumber(0);
        else {
            boolean firstTime = true;
            result.numberOfDigits = this.numberOfDigits - other.numberOfDigits;
            BigNumber b1 = new BigNumber();
            b1.copyBigNumber(this);
            b1.sign = '+';
            char b2_sign = other.sign;
            other.sign = '+';
            BigNumber sub_b1 = new BigNumber();
            sub_b1.copyPart(b1, other.numberOfDigits);
            if (this.Digits[0] / other.Digits[0] >= 1) {
                result.numberOfDigits += 1;
            }
            result.Digits = new int[result.numberOfDigits];
            int dif1 = -1;
            int dif2 = -1;
            for (int i = 0; i < result.numberOfDigits; i++) {
                if (!b1.firstIsGrater(other)) break;
                int j = 0;
                sub_b1.copyPart(b1, other.numberOfDigits);
                if (!sub_b1.firstIsGrater(other)) {
                    sub_b1.copyPart(b1, other.numberOfDigits + 1);
                    for (j = 9; j > 0; j--) {
                        if (sub_b1.firstIsGrater(other.multByOneDigit(j))) break;
                    }
                    if (dif1 != dif2 && dif2 != -1)
                        i = result.numberOfDigits - (b1.numberOfDigits - other.numberOfDigits);
                    result.Digits[i] = j;
                    dif1 = b1.numberOfDigits;
                    b1.copyBigNumber(b1.minus(other.multByOneDigit(j).shiftL(b1.numberOfDigits - other.numberOfDigits - 1)));
                    dif2 = b1.numberOfDigits;
                } else {
                    for (j = 9; j > 0; j--) {
                        if (sub_b1.firstIsGrater(other.multByOneDigit(j))) break;
                    }
                    if (dif1 != dif2 && dif2 != -1)
                        i = result.numberOfDigits - (b1.numberOfDigits - other.numberOfDigits) - 1;
                    result.Digits[i] = j;
                    dif1 = b1.numberOfDigits;
                    b1.copyBigNumber(b1.minus(other.multByOneDigit(j).shiftL(b1.numberOfDigits - other.numberOfDigits)));
                    dif2 = b1.numberOfDigits;

                }
            }
            other.sign = b2_sign;
            if (this.sign == '-' && other.sign == '-') result.sign = '+';
            else if ((other.sign == '-' && this.sign == '+') || (other.sign == '+' && this.sign == '-'))
                result.sign = '-';
            else result.sign = '+';
            return result;
        }
    }

    public BigNumber big_factoriel(int n) {
        this.reversePadding();
        BigNumber result = new BigNumber(1);
        for (int i = 2; i <= n; i++) {
            result = result.mult(new BigNumber(i));
        }
        return result;
    }

    public static void main(String[] args) {
        BigNumber t = new BigNumber();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter B1 :");
        String first_str = scanner.nextLine();
        BigNumber first = new BigNumber(first_str);
        System.out.println("enter B2 (second big number):");
        String second_str = scanner.nextLine();
        BigNumber second = new BigNumber(second_str);
        System.out.print("\nB1+B2 =");
        first.sum(second).print();
        System.out.print("\nB1-B2 =");
        first.minus(second).print();
        System.out.print("\nB2-B1 =");
        second.minus(first).print();
        System.out.println("\nenter the number n for >>n and <<n :");
        int n = scanner.nextInt();
        System.out.print("\nB2>>" + n + "(right shift) =");
        second.shiftR(n).print();
        System.out.print("\nB2<<n" + n + "(left shift) =");
        second.shiftL(n).print();
        System.out.println();
        System.out.print("B1/B2 = ");
        first.div(second).print();
        System.out.println();
        System.out.print("B1*B2 (karatsuba) = ");
        first.multKaratsuba(second).print();
        System.out.println();
        System.out.print("B1*B2             = ");
        first.mult(second).print();
        System.out.println();
        System.out.print("B1^3  = ");
        first.pow(3).print();
        System.out.println();
        System.out.print("enter B3 (for factorial):");
        int b3 = scanner.nextInt();
        BigNumber third = new BigNumber();
        System.out.print("B3! = ");
        third.big_factoriel(b3).print();
        System.out.println();
    }
}
