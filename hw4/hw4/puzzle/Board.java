package hw4.puzzle;

import java.util.ArrayList;

public class Board implements WorldState {
    private int[][] board;
    private int zeroRow;
    private int zeroColumn;
    private int hammingDistance;
    private int manhattenDistance;

    public Board(int[][] tiles) {
        board = new int[tiles.length][tiles.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = tiles[i][j];
                if (tiles[i][j] == 0) {
                    zeroRow = i;
                    zeroColumn = j;
                }
                if (board[i][j] != 0 && board[i][j] != goal(i, j)) {
                    hammingDistance = hammingDistance + 1;
                }
                if (board[i][j] != 0 && board[i][j] != goal(i, j)) {
                    int goalOfR = goalOfRow(board[i][j]);
                    int goalOfC = goalOfColumn(board[i][j]);
                    manhattenDistance += Math.abs(goalOfR - i) + Math.abs(goalOfC - j);
                }
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return board[i][j];
    }

    public int size() {
        return board.length;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        ArrayList<WorldState> neighbor = new ArrayList<>();
        try {
            Board b = swap(zeroRow + 1, zeroColumn, zeroRow, zeroColumn, board);
            neighbor.add(b);
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            Board b = swap(zeroRow - 1, zeroColumn, zeroRow, zeroColumn, board);
            neighbor.add(b);
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            Board b = swap(zeroRow, zeroColumn + 1, zeroRow, zeroColumn, board);
            neighbor.add(b);
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            Board b = swap(zeroRow, zeroColumn - 1, zeroRow, zeroColumn, board);
            neighbor.add(b);
        } catch (IndexOutOfBoundsException e) {

        }
        return neighbor;
    }

    private Board swap(int row1, int column1, int row2, int column2, int[][] boardDes) {
        int[][] changeBoard = copyBoard(boardDes);
        int temp = changeBoard[row1][column1];
        changeBoard[row1][column1] = changeBoard[row2][column2];
        changeBoard[row2][column2] = temp;
        return new Board(changeBoard);
    }

    private int[][] copyBoard(int [][] des) {
        int[][] copy = new int[des.length][des.length];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy.length; j++) {
                copy[i][j] = des[i][j];
            }
        }
        return copy;
    }
    private int goal(int row, int column) {
        if (row == size() - 1 && column == size() - 1) {
            return 0;
        } else {
            return row * size() + column + 1;
        }
    }
    public int hamming() {
        return hammingDistance;
    }
    private int goalOfRow(int goal) {
        if (goal == 0) {
            return size() - 1;
        } else {
            return (goal - 1) / size();
        }
    }
    private int goalOfColumn(int goal) {
        if (goal == 0) {
            return size() - 1;
        } else {
            return goal - 1 - goalOfRow(goal) * size();
        }
    }
    public int manhattan() {
        return manhattenDistance;
    }
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (getClass() != y.getClass()) {
            return false;
        }
        Board o = (Board) y;
        if (o.size() != board.length) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (board[i][j] != o.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    /** Returns the string representation of the board.
     * Uncomment this method. */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
    @Override
    public int hashCode() {
        int hashNumber = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                hashNumber = hammingDistance + i * 100 + j * 1000 + board[i][j] * 10000;
            }
        }
        return hashNumber;
    }
}
