package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] threshold;
    /**
     * perform T independent experiments on an N-by-N grid
     * @param N
     * @param T
     * @param pf
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        threshold = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation item = pf.make(N);
            while (!item.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                item.open(row, col);
            }
            threshold[i] = (double) item.numberOfOpenSites() / N / N;
        }
    }

    /**
     * sample mean of percolation threshold.
     * @return
     */
    public double mean() {
        return  StdStats.mean(threshold);
    }

    /**
     * sample standard deviation of percolation threshold.
     * @return
     */
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    /**
     * low endpoint of 95% confidence interval.
     * @return
     */
    public double confidenceLow() {
        double sampleMean = mean();
        double standardDeviation = stddev();
        return sampleMean - 1.96 * standardDeviation / Math.sqrt(threshold.length);
    }

    /**
     * high endpoint of 95% confidence interval.
     * @return
     */
    public double confidenceHigh() {
        double sampleMean = mean();
        double standardDeviation = stddev();
        return sampleMean + 1.96 * standardDeviation / Math.sqrt(threshold.length);
    }
}
