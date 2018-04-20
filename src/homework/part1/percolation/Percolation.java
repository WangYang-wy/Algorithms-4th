package homework.part1.percolation;

/**
 * http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.jetbrains.annotations.Contract;

public class Percolation {
    // create n-by-n grid, with all sites blocked
    private boolean[][] table;
    private final int nVal;
    private int openSites;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf1;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        nVal = n;
        openSites = 0;
        table = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                table[i][j] = false;
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf1 = new WeightedQuickUnionUF(n * n + 2);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validIndices(row, col);
        if (!table[row - 1][col - 1]) {
            table[row - 1][col - 1] = true;
            openSites++;
            int pos = map2Dto1D(row, col);
            if (row == 1) {
                uf.union(pos, 0);
                uf1.union(pos, 0);
            }

            if (row > 1 && table[row - 2][col - 1]) {
                uf.union(pos, pos - nVal);
                uf1.union(pos, pos - nVal);
            }

            if (row < nVal && table[row][col - 1]) {
                uf.union(pos, pos + nVal);
                uf1.union(pos, pos + nVal);
            }

            if (col > 1 && table[row - 1][col - 2]) {
                uf.union(pos, pos - 1);
                uf1.union(pos, pos - 1);
            }

            if (col < nVal && table[row - 1][col]) {
                uf.union(pos, pos + 1);
                uf1.union(pos, pos + 1);
            }

            if (row == nVal)
                uf.union(pos, nVal * nVal + 1);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validIndices(row, col);
        return table[row - 1][col - 1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validIndices(row, col);
//        if (!table[row][col])
//            return false;
        int pos = map2Dto1D(row, col);
        return uf1.connected(pos, 0);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, nVal * nVal + 1);
    }

    private void validIndices(int row, int col) {
        if (row < 1 || row > nVal || col < 1 || col > nVal)
            throw new IllegalArgumentException();
    }

    @Contract(pure = true)
    private int map2Dto1D(int row, int col) {
        return (row - 1) * nVal + col;
    }
}