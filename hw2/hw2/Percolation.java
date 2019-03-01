package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] world;
    private int openSites;
    private WeightedQuickUnionUF uf;
    // uf with no virtual bottom.
    private WeightedQuickUnionUF ufNoBot;
    /**
     * create N-by-N grid, with all sites initially blocked.
     * @param N the size of the percolation system
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        world = new boolean[N][N];
        openSites = 0;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufNoBot = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                world[i][j] = false;
            }
        }
        for (int i = 0; i < N; i++) {
            uf.union(N * N, i);
            uf.union(N * N + 1,  N * N - 1 - i);
            ufNoBot.union(N * N, i);
        }
    }

    /**
     * open the site (row, col) if it is not open already.
     * @param row from 0 to N - 1
     * @param col from 0 to N - 1
     */
    public void open(int row, int col) {
        if (row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!world[row][col]) {
            world[row][col] = true;
            openSites = openSites + 1;
        } else {
            return;
        }
        if (row != 0 && row != world.length - 1 && col != 0 && col != world.length - 1) {
            if (isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
            }
            if (isOpen(row + 1, col)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
            }
            if (isOpen(row, col - 1)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
            }
            if (isOpen(row, col + 1)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
            }
        }
        if (row == 0 && col != 0 && col != world.length - 1) {
            if (isOpen(row + 1, col)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
            }
            if (isOpen(row, col - 1)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
            }
            if (isOpen(row, col + 1)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
            }
        }
        if (row != 0 && col == 0 && row != world.length - 1) {
            if (isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
            }
            if (isOpen(row + 1, col)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
            }
            if (isOpen(row, col + 1)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
            }
        }
        if (row == world.length - 1 && col != world.length - 1 && col != 0) {
            if (isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
            }
            if (isOpen(row, col - 1)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
            }
            if (isOpen(row, col + 1)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
            }
        }
        if (row != world.length - 1 && col == world.length - 1 && row != 0) {
            if (isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
            }
            if (isOpen(row + 1, col)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
            }
            if (isOpen(row, col - 1)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
            }
        }
        if (row == 0 && col == 0) {
            if (isOpen(row + 1, col)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
            }
            if (isOpen(row, col + 1)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
            }
        }
        if (row == 0 && col == world.length - 1) {
            if (isOpen(row + 1, col)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row + 1, col, world.length));
            }
            if (isOpen(row, col - 1)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
            }
        }
        if (row == world.length - 1 && col == 0) {
            if (isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
            }
            if (isOpen(row, col + 1)) {
                uf.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
                ufNoBot.union((xyTo1D(row, col, world.length)),
                        xyTo1D(row, col + 1, world.length));
            }
        }
        if (row == world.length - 1 && col == world.length - 1) {
            if (isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row - 1, col, world.length));
            }
            if (isOpen(row, col - 1)) {
                uf.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
                ufNoBot.union(xyTo1D(row, col, world.length),
                        xyTo1D(row, col - 1, world.length));
            }
        }


    }
    private void unionOpen() {

    }

    /**
     * is the site (row, col) open?
     * @param row from 0 to N - 1
     * @param col from 0 to N - 1
     * @return
     */
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return world[row][col];
    }
    private int xyTo1D(int row, int col, int N) {
        return N * row + col;
    }

    /**
     * is the site (row, col) full?
     * @param row from 0 to N - 1
     * @param col from 0 to N - 1
     * @return
     */
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return ufNoBot.connected(world.length * world.length, xyTo1D(row, col, world.length));
        }
        return false;
    }

    /**
     * calculate number of open sites
     * @return
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * does the system percolate?
     * @return
     */
    public boolean percolates() {
        return uf.connected(world.length * world.length, world.length * world.length + 1);
    }
    public static void main(String[] args) {

    }
}
