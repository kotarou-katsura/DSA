import java.util.Scanner;
public class BigNumber {
    int numberOfDigits;
    int[] Digits;
    boolean sign;
    public BigNumber(){
        this.numberOfDigits= 10;
        this.Digits = new int[numberOfDigits];
        this.sign=true;
    }
    public BigNumber(String str){
        String[] stringNumb = str.split("");
        this.numberOfDigits = stringNumb.length;
        this.Digits = new int[numberOfDigits];
        for (int i=0;i<numberOfDigits;i++){
            this.Digits[i]=Integer.parseInt(stringNumb[i]);
        }
        this.sign=true;
    }
    public BigNumber(int num){
        this(Integer.toString(num));
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
    public void reversePadding(){
        this.numberOfDigits=this.Digits.length;
        int count = 0;
        for (int i = 0; i <this.numberOfDigits; i++) {
            if (this.Digits[i]==0)
                count++;
            else
                break;

        }
        if (count>0) {
            this.numberOfDigits -= count;
            int[] newDigits = new int[this.numberOfDigits];
            for (int i = 0; i < this.numberOfDigits; i++) {
                newDigits[i] = this.Digits[i + count];
            }
            this.Digits = newDigits;
        }
    }
    public BigNumber padding(int x){
        this.numberOfDigits +=x;
        int [] newDigits = new int[this.numberOfDigits];
        for(int i=0;i<this.numberOfDigits-x;i++ ){
            newDigits[i+x]=this.Digits[i];
        }
        this.Digits=newDigits;
        return this;
    }
    public BigNumber shiftL(int x){
        this.reversePadding();
        BigNumber result = new BigNumber();
        result.numberOfDigits = this.numberOfDigits+ x;
        int [] newDigits = new int[result.numberOfDigits];
        for(int i=0;i<this.numberOfDigits;i++){
            newDigits[i]=this.Digits[i];
        }
        result.Digits = newDigits;
        return result;
    }
    public BigNumber sum(BigNumber other){
        BigNumber result = new BigNumber();
        other.reversePadding();
        this.reversePadding();
        if( other.numberOfDigits > this.numberOfDigits){
            this.padding(other.numberOfDigits -this.numberOfDigits);
        }
        else if( other.numberOfDigits < this.numberOfDigits) {
            other.padding(this.numberOfDigits-other.numberOfDigits);
        }

        result.numberOfDigits = this.numberOfDigits+1;
        int carry=0;
        int [] newDigits = new int[result.numberOfDigits];
        for (int i = result.numberOfDigits-1; i >=1; i--) {
            if (this.Digits[i-1] + other.Digits[i-1]+carry>=10) {
                int k = this.Digits[i-1] + other.Digits[i-1]+carry;
                newDigits[i] = k%10;
                carry =k/10;
            }
            else{
                newDigits[i] = this.Digits[i-1] + other.Digits[i-1]+carry;
            carry =0;
            }
        }
        newDigits[0]=carry;
        result.Digits=newDigits;
        result.reversePadding();
        return result;
    }
    public void greater (BigNumber other){

    }
    public void print(){
        if(!this.sign)
            System.out.print("-");
        for (int num:this.Digits){
            System.out.print(num);
        }
    }
//    public BigNumber greater(){}
    public BigNumber minus (BigNumber first,BigNumber second){
        this.numberOfDigits = first.numberOfDigits;
        int [] newDigits = new int[this.numberOfDigits];
        for (int i = this.numberOfDigits-1; i >=0; i--) {
            if (first.Digits[i] < second.Digits[i]) {
                first.Digits[i-1]-=1;
                first.Digits[i] +=10;
            }
            newDigits[i] = first.Digits[i] - second.Digits[i];
        }
//        System.out.println("\nfor ckeck  ");
//
//        first.print();
//        System.out.println("\nfor ckeck  ");
//        second.print();
//        System.out.println("\n");

        this.Digits=newDigits;
        this.reversePadding();
        return this;
    }
    public BigNumber preMinus(BigNumber first,BigNumber second){
        BigNumber result = new BigNumber();
//        first.reversePadding();
//        second.reversePadding();
        if( second.numberOfDigits > first.numberOfDigits){
            first.padding(second.numberOfDigits -first.numberOfDigits);
            result.sign=false;
           return result.minus(second,first);
        }
        else if( second.numberOfDigits < first.numberOfDigits) {
            second.padding(first.numberOfDigits-second.numberOfDigits);
           return result.minus(first,second);
        }
        else
        {
            int firstGreater = 0;
            for (int i = 0; i <first.numberOfDigits; i++) {
                if (first.Digits[i]>second.Digits[i]) {
                    firstGreater =1;
                    break;
                }
                else if (first.Digits[i]<second.Digits[i]) {
                    firstGreater =-1;
                    break;
                }

            }
            if (firstGreater==-1){
                result.sign=false;
                return result.minus(second,first);
            }
            else {
                return result.minus(first,second);
            }
        }
    }
    public BigNumber multByOneDigit(int d){
        this.reversePadding();
        BigNumber result = new BigNumber();
        int carry=0;
        result.numberOfDigits=this.numberOfDigits+1;
        int [] newDigits = new int[result.numberOfDigits];
        for (int i = result.numberOfDigits-1; i >=1; i--) {
               int k=this.Digits[i-1]*d+carry;
               newDigits[i]= k%10;
               carry=k/10;
        }
        newDigits[0]=carry;
        result.Digits=newDigits;
        result.reversePadding();
        return result;

    }
    public BigNumber mult(BigNumber other){
        this.reversePadding();
        BigNumber result = new BigNumber();
        result.numberOfDigits=this.numberOfDigits+other.numberOfDigits;
        int [] newDigits = new int[result.numberOfDigits];
        for (int i =0;i<other.numberOfDigits;i++){
            if (i!= other.numberOfDigits -1)
                result=result.sum(this.multByOneDigit(other.Digits[i])).shiftL(1);
            else
                result=result.sum(this.multByOneDigit(other.Digits[i]));
        }
        return result;
    }
    public BigNumber pow(int n){
        this.reversePadding();
        BigNumber result = new BigNumber(1);
        for (int i =0;i<n;i++) {
            result = result.mult(this);

        }
        return result;
    }
    public BigNumber big_factoriel(int n){
        this.reversePadding();
        BigNumber result = new BigNumber(1);
        for (int i =2;i<=n;i++) {
            result = result.mult( new BigNumber(i));

        }
        return result;
    }
//
//    public BigNumber div(BigNumber other){
//        this.reversePadding();
//        BigNumber result = new BigNumber(1);
//        for (int i = 0;i<())
//        return result;
//    }


    public static void main(String[] args) {
        BigNumber t = new BigNumber();
        System.out.println(t.numberOfDigits);
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter B1 (first big number):");
        String first_str = scanner.nextLine();
        BigNumber first = new BigNumber(first_str);
        System.out.println("enter B2 (second big number):");
        String second_str = scanner.nextLine();
        BigNumber second = new BigNumber(second_str);
        System.out.print("\nB1+B2 =");
        first.sum(second).print();
//      in java,objects are inherently passed by reference so we make copy of them
        BigNumber first_copy = new BigNumber(first_str);
        BigNumber second_copy = new BigNumber(second_str);
        System.out.print("\nB1-B2 =");
        first_copy.preMinus(first_copy, second_copy).print();
        System.out.print("\nB2-B1 =");
        second_copy.preMinus(second_copy, first_copy).print();
//        System.out.print("\nB1 =");
//        first.print();
//        System.out.print("\nB2 =");
//        second.print();

        System.out.println("\nenter the number n for >>n and <<n");
        int n = scanner.nextInt();
        System.out.print("\nB2>>" +n+ "(right shift) =");
        second.shiftR(n).print();
        System.out.print("\nB2<<n" +n+ "(left shift) =");
        second.shiftL(n).print();
    }



}
