
/*----------------------------------------------------------------
 *  Author:        Jayesh Gorasia
 *  Written:       09/25/2015
 *  Last updated:  10/05/2015
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats(N,T)
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private double[] results;
  private int experiments;
  
  public PercolationStats(int N, int T) {
    if (N <= 0 || T <= 0) {
      throw new java.lang.IllegalArgumentException();
    }
    int expCount = 0;
    double result;
    experiments = T;
    results = new double[T];
    while (expCount < T) {
      result = monteCarloSimulation(N);
      results[expCount] = result / (N*N);
      expCount++;
    }
  }

  /*Method to implement the Monte Carlo Simulation*/
  private int monteCarloSimulation(int n) {
    Percolation perc = new Percolation(n);
    int openedSites = 0;
    while (!perc.percolates()) {
      int i = 1 + StdRandom.uniform(n);
      int j = 1 + StdRandom.uniform(n);
      if (!perc.isOpen(i, j)) {
        perc.open(i, j);
        openedSites++;
      }
    }
    return openedSites;
  }

  public double mean() {
    return StdStats.mean(results);
  }

  public double stddev() {
    return StdStats.stddev(results);
  }

  public double confidenceLo() {
    return (mean() - (1.96 * stddev()) / Math.sqrt(experiments));
  }

  public double confidenceHi() {
    return (mean() + (1.96 * stddev()) / Math.sqrt(experiments));
  }

  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(N, T);
    System.out.println("mean                    = " + ps.mean());
    System.out.println("stddev                  = " + ps.stddev());
    System.out.print("95% confidence interval = " + ps.confidenceLo());
    System.out.print(", " + ps.confidenceHi());
  }
}
