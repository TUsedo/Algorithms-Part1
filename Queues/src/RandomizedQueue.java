import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
  
  private Item[] rq;
  private int size;
  
  public RandomizedQueue() {
    rq = (Item[]) new Object[1];
    size = 0;
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public int size() {
    return size;
  }
  
  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (size == rq.length) {
      resize(2 * size);
    }
    rq[size] = item;
    size++;
  }
  
  private void resize(int resize) { 
    Item[] temp = (Item[]) new Object[resize];
    for (int i = 0; i < size; i++) {
      temp[i] = rq[i];
    }
    rq = temp;
  }

  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item item;
    int offset = StdRandom.uniform(size);
    item = rq[offset];
    if (size > 1) {
      rq[offset] = rq[size - 1];
    }
    rq[size - 1] = null;
    size--;
    if (size > 0 && size == rq.length/4) {
      resize(rq.length/4);
    }
    return item;
  }
  
  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Item item;
    int offset = StdRandom.uniform(size);
    item = rq[offset];
    return item;
  }
  
  private class ArrayIterator implements Iterator<Item> {
    private int sizeCopy = size;
   private Item[] rqCopy = (Item[]) new Object[sizeCopy];

   private ArrayIterator() {
      for (int i = 0; i < size; i++) {
        rqCopy[i] = rq[i];
      }
    }
    
    @Override
    public boolean hasNext() {
      return (sizeCopy > 0);
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      int offset = StdRandom.uniform(sizeCopy);
      Item item = rqCopy[offset];
      if (sizeCopy > 1) {
        rqCopy[offset] = rqCopy[sizeCopy - 1];
      }
      rqCopy[sizeCopy - 1] = null;
      sizeCopy--;
      return item;
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
    
  }
  
  public Iterator<Item> iterator() {
    return new ArrayIterator();
  }
  
/*  public static void main(String[] args) {  // unit testing
    RandomizedQueue<Integer> randomQueue = new RandomizedQueue<Integer>();

    randomQueue.enqueue(1);
    randomQueue.enqueue(2);
    randomQueue.enqueue(3);
    randomQueue.enqueue(4);
    randomQueue.enqueue(4);
    randomQueue.enqueue(6);
    randomQueue.enqueue(7);
    randomQueue.enqueue(8);
    randomQueue.enqueue(9);
    randomQueue.dequeue();
    randomQueue.dequeue();

    StdOut.println("Output: ");
    for (Integer x : randomQueue) {
        StdOut.println("  X : " + x + " ");
//        StdOut.println();
    }
}*/
}
