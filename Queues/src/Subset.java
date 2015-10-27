import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
  public static void main(String[] args) {
      RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();

      int k = Integer.parseInt(args[0]);
      int count = 0;

      while (!StdIn.isEmpty()) {
        if (StdRandom.uniform(count) < k)
          randomizedQueue.enqueue(StdIn.readString());
        count++;
      }
      /*while (count - k > 0) {
          randomizedQueue.dequeue();
          count--;
      }*/

      for (int i = 0; i < k; i++)
          StdOut.println(randomizedQueue.dequeue());
  }
}