import java.util.Scanner;
public class s6 {
    void khayamPascal(int n) {
        int[] arr1 = new int[] {0,1,0};
        for (int j = 3; j <= n + 2; j++) {
            int[] arr2 = new int[j + 1];
            for (int i = 1; i < j -1; i++) {
                if (i==1)
                    for (int k=0;k<n-j+2;k++)
                        System.out.print(" ");
                arr2[i] = arr1[i-1] + arr1[i];
                System.out.print(arr2[i]+ " ");
            }
            System.out.println();
            arr1=arr2;
        }

    }

    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("enter the number:");
        int n = scanner.nextInt();
        s6 t = new s6();
        t.khayamPascal(n);
    }
}
