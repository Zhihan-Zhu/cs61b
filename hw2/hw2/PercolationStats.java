package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

//import java.util.Arrays;

public class PercolationStats {
    private double[] thresholds;
//    private Percolation perc;


    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        thresholds = new double[T];
        int row;
        int col;
        Percolation perc;
        for (int i = 0; i < T; i += 1) {
            perc = pf.make(N);
            while (!perc.percolates()) {
                row = StdRandom.uniform(N);
                col = StdRandom.uniform(N);
                while (perc.isOpen(row, col)) {
                    row = StdRandom.uniform(N);
                    col = StdRandom.uniform(N);
                }
                perc.open(row, col);
            }
            thresholds[i] = (double) perc.numberOfOpenSites() / (N * N);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(thresholds.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(thresholds.length);
    }

    public double[] getThresholds() {
        return thresholds;
    }

//    public static void main(String[] args) {
//        PercolationStats p = new PercolationStats(10, 30, new PercolationFactory());
//        System.out.println(p.mean());
//        System.out.println(Arrays.toString(p.getThresholds()));
//        System.out.println(p.stddev());
//        System.out.println(p.confidenceLow());
//        System.out.println(p.confidenceHigh());
//    }
}
