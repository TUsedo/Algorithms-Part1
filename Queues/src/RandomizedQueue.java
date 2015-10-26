import java.util.*;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
  
  private Item[] rq;
  private int size;
  
  @SuppressWarnings("unchecked")
  public RandomizedQueue() {
    rq = (Item[])new Object[1];
    size = 0;
  }
  
  public boolean isEmpty() {
    return size == 0;
  }
  
  public int size () {
    return size;
  }
  
  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (size == rq.length) {
      resize(2*size);
    }
    rq[size] = item;
    size++;
  }
  
  private void resize(int resize) { 
    @SuppressWarnings("unchecked")
    Item[] temp = (Item[]) new Object[resize];
    for (int i = 0; i < rq.length; i++) {
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
    private int sizeCopy;
    private Item[] rqCopy;
    
    @SuppressWarnings("unchecked")
    private ArrayIterator() {
      sizeCopy = size;
      rqCopy = (Item[]) new Object[sizeCopy];
      for (int i = 0; i < size; i++) {
        rqCopy[i] = rq[i];
      }
    }
    
    @Override
    public boolean hasNext() {
      return (size != 0) ;
    }

    @Override
    public Item next() {
     if(sizeCopy == 0) {
       throw new NoSuchElementException();
     }
      int offset = StdRandom.uniform(sizeCopy);
      Item item = rqCopy[offset];
      if(sizeCopy > 1)
        rqCopy[offset] = rq[sizeCopy - 1];
      rqCopy[sizeCopy - 1] = null;
      sizeCopy--;
      return item;
    }
    
  }
  
  public Iterator<Item> iterator() {
    return new ArrayIterator();
  }
  public static void main(String[] args) {
    
  }
}
