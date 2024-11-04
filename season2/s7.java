import java.util.Scanner;

public class s7 {
    boolean isTranspose(int[][] a, int[][] b, int n) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (a[i][j] != b[j][i])
                    return false;


        return true;
    }

    public static void main(String[] args) {
        s7 t = new s7();
        int[][] matrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrixB = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };

        System.out.print(t.isTranspose(matrixA, matrixB, 3));
    }
}
