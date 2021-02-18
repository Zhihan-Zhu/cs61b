package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF sitesNoBottom;
    private boolean[] openSites;
    private int openCount;
    private int virtualTop;
    private int virtualBottom;
    private int N;

    /**
     * create N-by-N grid, with all sites initially blocked
     * @param N
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        sites = new WeightedQuickUnionUF(N * N + 2);
        // add another Disjoint set that doesn't have the virtual bottom site
        sitesNoBottom = new WeightedQuickUnionUF(N * N + 1);
        openSites = new boolean[N * N];
        openCount = 0;
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        this.N = N;
    }

    /**
     * represent the grid by 1D int
     * a 5 x 5 grid is equivalent to
     * 0  | 1  | 2  | 3  | 4
     * 5  | 6  | 7  | 8  | 9
     * ...
     * 20 | 21 | 22 | 23 | 24
     * @param x
     * @param y
     * @return
     */
    private int xyTo1D(int x, int y) {
        return x * N + y;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        int index = xyTo1D(row, col);
        openSites[index] = true;
        openCount += 1;
        if (row == 0) {
            sites.union(index, virtualTop);
            sitesNoBottom.union(index, virtualTop);
        }
        if (row == N - 1) {
            sites.union(index, virtualBottom);
        }
        if (index % N != 0 // site not on left edge
                && index - 1 >= 0
                && openSites[index - 1]) {
            sites.union(index - 1, index);
            sitesNoBottom.union(index - 1, index);
        }
        if (index % N != N - 1 // site not on right edge
                && index + 1 < N * N
                && openSites[index + 1]) {
            sites.union(index + 1, index);
            sitesNoBottom.union(index + 1, index);
        }
        if (index - N >= 0 && openSites[index - N]) {
            sites.union(index - N, index);
            sitesNoBottom.union(index - N, index);
        }
        if (index + N < N * N && openSites[index + N]) {
            sites.union(index + N, index);
            sitesNoBottom.union(index + N, index);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        int index = xyTo1D(row, col);
        return openSites[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        int index = xyTo1D(row, col);
        /*
        use the disjoint set without virtual bottom site to prevent backwash
        (site is full only when it's connected directly to the top, npt through bottom)
         */
        return sitesNoBottom.connected(index, virtualTop);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return sites.connected(virtualTop, virtualBottom);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(2, 4);
        p.open(3, 4);
        p.open(1, 4);
        p.open(1, 3);
        p.open(0, 3);
        System.out.println(p.isOpen(2, 4));
        System.out.println(p.numberOfOpenSites());
        System.out.println(p.isFull(2, 4));
    }
}
