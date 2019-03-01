package hw2;
import edu.princeton.cs.algs4.StdRandom;
public class PercolationStats {
    double[] item;
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
        item = new double[N];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.isOpen(row, col);
            }
            double threshod = (double) p.numberOfOpenSites() / N / N;
            item[i] = threshod;
        }
    }

    /**
     * sample mean of percolation threshold.
     * @return
     */
    public double mean() {
        double sum = 0;
        for (double d : item) {
            sum += d;
        }
        return sum / item.length;
    }

    /**
     * sample standard deviation of percolation threshold.
     * @return
     */
    public double stddev() {
        double sampleMean = mean();
        double variance = 0;
        for (double d : item) {
            variance += (d - sampleMean) * (d - sampleMean);
        }
        variance = variance / (item.length - 1);
        return Math.sqrt(variance);
    }

    /**
     * low endpoint of 95% confidence interval.
     * @return
     */
    public double confidenceLow() {
        double sampleMean = mean();
        double standardDeviation = stddev();
        return sampleMean - 1.96 * standardDeviation / Math.sqrt(item.length);
    }

    /**
     * high endpoint of 95% confidence interval.
     * @return
     */
    public double confidenceHigh() {
        double sampleMean = mean();
        double standardDeviation = stddev();
        return sampleMean + 1.96 * standardDeviation / Math.sqrt(item.length);
    }
}
