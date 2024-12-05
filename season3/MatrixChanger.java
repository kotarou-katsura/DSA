import java.util.Scanner;
public class MatrixChanger {

    public static void changeValue(int[][] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    arr[i][j] += 1;
                if (i + j == n - 1)
                    arr[i][j] -= 1;
                if (i == j - 1 || i + j == n - 2)
                    arr[i][j] += 2;                if (i - 1 == j || i + j == n)
                    arr[i][j] -= 2;
                if (i - 1 == j || i + j == n - 2)
                    arr[i][j] += 3;
                if (i == j - 1 || i + j == n)
                    arr[i][j] -= 3;
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the matrix (n): ");
        int n = scanner.nextInt();
        int[][] arr = new int[n][n];
        System.out.println("Enter the elements of the " + n + "x" + n + " matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        changeValue(arr, n);
        System.out.println("changed matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
