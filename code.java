import java.util.Scanner;

public class SudokuValidator {
    private static int[][] sudoku;
    private static boolean[] valid;

    public static class RowColumnObject {
        int row;
        int col;
        RowColumnObject(int row, int column) {
            this.row = row;
            this.col = column;
        }
    }

    public static class IsRowValid extends RowColumnObject implements Runnable {
        IsRowValid(int row, int column) {
            super(row, column);
        }

        @Override
        public void run() {
            for (int i = 0; i < sudoku.length; i++) {
                if (!isValid(sudoku[row][i])) {
                    return;
                }
            }
            valid[row] = true;
        }
    }

    public static class IsColumnValid extends RowColumnObject implements Runnable {
        IsColumnValid(int row, int column) {
            super(row, column);
        }

        @Override
        public void run() {
            for (int i = 0; i < sudoku.length; i++) {
                if (!isValid(sudoku[i][col])) {
                    return;
                }
            }
            valid[sudoku.length + col] = true;
        }
    }

    public static class IsSubgridValid extends RowColumnObject implements Runnable {
        IsSubgridValid(int row, int column) {
            super(row, column);
        }

        @Override
        public void run() {
            int subgridSize = (int) Math.sqrt(sudoku.length);
            boolean[] subgridValid = new boolean[sudoku.length];
            for (int i = row; i < row + subgridSize; i++) {
                for (int j = col; j < col + subgridSize; j++) {
                    if (!isValid(sudoku[i][j]) || subgridValid[sudoku[i][j] - 1]) {
                        return;
                    }
                    subgridValid[sudoku[i][j] - 1] = true;
                }
            }
            valid[2 * sudoku.length + (row / subgridSize) * subgridSize + (col / subgridSize)] = true;
        }
    }

    public static boolean isValid(int num) {
        return num >= 1 && num <= sudoku.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the Sudoku puzzle (e.g., 4 for 4x4, 9 for 9x9):");
        int size = scanner.nextInt();

        sudoku = new int[size][size];
        valid = new boolean[3 * size];

        System.out.println("Enter the Sudoku puzzle:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sudoku[i][j] = scanner.nextInt();
            }
        }

        Thread[] threads = new Thread[3 * size];
        int threadIndex = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0) {
                    threads[threadIndex++] = new Thread(new IsColumnValid(i, j));
                }
                if (j == 0) {
                    threads[threadIndex++] = new Thread(new IsRowValid(i, j));
                }
                if (i % Math.sqrt(size) == 0 && j % Math.sqrt(size) == 0) {
                    threads[threadIndex++] = new Thread(new IsSubgridValid(i, j));
                }
            }
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < valid.length; i++) {
            if (!valid[i]) {
                System.out.println("Sudoku solution is invalid!");
                return;
            }
        }
        System.out.println("Sudoku solution is valid!");
    }
}