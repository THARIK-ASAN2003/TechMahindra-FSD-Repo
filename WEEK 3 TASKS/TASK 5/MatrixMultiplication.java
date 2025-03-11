public class MatrixMultiplication {
    static class MatrixMultiplicationTask extends Thread {
        int[][] A, B, C;
        int row, col;

        MatrixMultiplicationTask(int[][] A, int[][] B, int[][] C, int row, int col) {
            this.A = A; this.B = B; this.C = C; this.row = row; this.col = col;
        }

        public void run() {
            int sum = 0;
            for (int k = 0; k < B.length; k++)
                sum += A[row][k] * B[k][col];
            C[row][col] = sum;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        int rows = A.length, cols = B[0].length;
        int[][] C = new int[rows][cols];

        MatrixMultiplicationTask[][] tasks = new MatrixMultiplicationTask[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tasks[i][j] = new MatrixMultiplicationTask(A, B, C, i, j);
                tasks[i][j].start();
            }
        }

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                tasks[i][j].join();

        System.out.println("Matrix multiplication result:");
        for (int i = 0; i < rows; i++)
            System.out.println(java.util.Arrays.toString(C[i]));
    }
}