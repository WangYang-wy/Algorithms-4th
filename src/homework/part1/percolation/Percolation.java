package homework.percolation;

/**
 * http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;

    private boolean[] grid;

    private int dimension;

    private static boolean OPEN = true;
    private static boolean BLOCKED = false;

    /**
     * create n-by-n grid, with all sites blocked
     *
     * @param n dimension.
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.dimension = n;

        this.grid = new boolean[n * n];

        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);

        for (int i = 0; i < this.grid.length; i++) {
            grid[i] = BLOCKED;
        }
    }

    /**
     * open site (row, col) if it is not open already
     *
     * @param row row.
     * @param col col.
     */
    public void open(int row, int col) {
        if (outOfIndices(row, col))
            throw new IllegalArgumentException("outOfIndices......");
        if (isOpen(row, col)) {
            return;
        }

        if (row == 1) {//top sites
            weightedQuickUnionUF.union(0, (row - 1) * this.dimension + col);
            grid[(row - 1) * this.dimension + col - 1] = true;
        } else if (row == this.dimension) {
            weightedQuickUnionUF.union(this.dimension * this.dimension + 1, (row - 1) * this.dimension + col);
            grid[(row - 1) * this.dimension + col - 1] = true;
        }
        if (!outOfIndices(row - 1, col) && isOpen(row - 1, col)) {//上site
            weightedQuickUnionUF.union((row - 1) * this.dimension + col, (row - 2) * this.dimension + col);
            grid[(row - 1) * this.dimension + col - 1] = true;
        }
        if (!outOfIndices(row + 1, col) && isOpen(row + 1, col)) {//下site
            weightedQuickUnionUF.union(row * this.dimension + col, (row - 1) * this.dimension + col);
            grid[(row - 1) * this.dimension + col - 1] = true;
        }
        if (!outOfIndices(row, col - 1) && isOpen(row, col - 1)) {//左site
            weightedQuickUnionUF.union((row - 1) * this.dimension + col - 1, (row - 1) * this.dimension + col);
            grid[(row - 1) * this.dimension + col - 1] = true;
        }
        if (!outOfIndices(row, col + 1) && isOpen(row, col + 1)) {//右site
            weightedQuickUnionUF.union((row - 1) * this.dimension + col, (row - 1) * this.dimension + col + 1);
            grid[(row - 1) * this.dimension + col - 1] = true;
        }
        if (!outOfIndices(row, col + 1)) {
            grid[(row - 1) * this.dimension + col - 1] = true;
        }
//        numberopen++;
    }

    /**
     * is site (row, col) open?
     *
     * @param row row.
     * @param col col.
     * @return open or not.
     */
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > this.dimension || col < 1 || col > this.dimension) {
            throw new IllegalArgumentException();
        }

        return OPEN == this.grid[(row - 1) * this.dimension + (col - 1)];
    }

    /**
     * is site (row, col) full?
     *
     * @param row row.
     * @param col col.
     * @return is site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        return weightedQuickUnionUF.connected((row - 1) * this.dimension + col, 0);
    }

    public boolean outOfIndices(int row, int col) {
        if ((row > 0 && row <= this.dimension) && (col > 0 && col <= this.dimension))
            return false;
        return true;
    }

    /**
     * number of open sites
     *
     * @return number of open sites.
     */
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < this.dimension; i++) {
            if (OPEN == this.grid[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * does the system percolate?
     *
     * @return does the system percolate?
     */
    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, this.dimension * this.dimension + 1);
    }

    /**
     * test client (optional)
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}
