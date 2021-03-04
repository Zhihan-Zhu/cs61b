package hw3.hash;

import edu.princeton.cs.introcs.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        /* After getting your simpleOomages to spread out
           nicely, be sure to try
           scale = 0.5, N = 2000, M = 100. */

        double scale = 1.0;
        int N = 100;
        int M = 10;
//        double scale = 0.5;
//        int N = 2000;
//        int M = 100;

        HashTableDrawingUtility.setScale(scale);
        List<Oomage> oomies = new ArrayList<>();

        Integer[] param;

        for (int i = 0; i < N; i += 1) {
//           oomies.add(SimpleOomage.randomSimpleOomage());
//           oomies.add(ComplexOomage.randomComplexOomage());
           int firstParam = StdRandom.uniform(255);
           param = new Integer[]{firstParam, 0, 0, 0, 0};
           oomies.add(new ComplexOomage(Arrays.asList(param)));

        }
        visualize(oomies, M, scale);
    }

    public static void visualize(List<Oomage> oomages, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        int[] numInBucket = new int[M];
        for (Oomage s : oomages) {
            int bucketNumber = (s.hashCode() & 0x7FFFFFFF) % M;
            double x = HashTableDrawingUtility.xCoord(numInBucket[bucketNumber]);
            numInBucket[bucketNumber] += 1;
            double y = HashTableDrawingUtility.yCoord(bucketNumber, M);
            s.draw(x, y, scale);
        }
    }
} 
