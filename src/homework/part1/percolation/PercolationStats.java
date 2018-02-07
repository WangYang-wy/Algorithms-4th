package homework.percolation;

/**
 * http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private int dimension;

    private final double BOUND = 1.96;

    private double[] threshold = null;

//    private Percolation percolation = null;

    /**
     * perform trials independent experiments on an n-by-n grid.
     * The constructor should throw a java.lang.IllegalArgumentException
     * if either n ≤ 0 or trials ≤ 0.
     *
     * @param n      dimension.
     * @param trials trials.
     */
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.dimension = n;

//        this.percolation = new Percolation(n);
        this.threshold = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int count = 0;

            do {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);

                if (percolation.isOpen(row, col)) {
//                    continue;
                    ;
                } else {
                    percolation.open(row, col);
                    count++;
                }
            } while (!percolation.percolates());
            this.threshold[i] = ((double) count) / (this.dimension * this.dimension);
        }
    }

    /**
     * sample mean of percolation threshold
     *
     * @return mean value.
     */
    public double mean() {
        return StdStats.mean(this.threshold);
    }

    /**
     * sample standard deviation of percolation threshold
     *
     * @return stddev value.
     */
    public double stddev() {
        return StdStats.stddev(this.threshold);
    }

    /**
     * low  endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceLo() {
        return this.mean() - this.stddev() * this.BOUND / Math.sqrt(this.dimension);
    }

    /**
     * high endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceHi() {
        return this.mean() + this.stddev() * this.BOUND / Math.sqrt(this.dimension);
    }

    /**
     * test client (described below)
     *
     * @param args
     */
    public static void main(String[] args) {
        int length = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        StdOut.println("length = " + length);
        StdOut.println("trials = " + trials);
        PercolationStats percolations = new PercolationStats(length, trials);
        StdOut.println("mean                    = " + percolations.mean());
        StdOut.println("stddev                  = " + percolations.stddev());
        StdOut.println("95% confidence interval = ["
                + percolations.confidenceLo() + ", "
                + percolations.confidenceHi() + "]");
    }

}
