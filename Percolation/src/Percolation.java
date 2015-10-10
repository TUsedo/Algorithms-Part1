/*----------------------------------------------------------------
 *  Author:        Jayesh Gorasia
 *  Written:       09/25/2015
 *  Last updated:  10/05/2015
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  
  private static final byte BLOCKED = 0;
  private static final byte OPEN = 1;
  private static final byte FULL = 2;
  private static final byte BOTTOMCONNETED = 4;
  
  private boolean percolation;
  private byte[] status;
  private WeightedQuickUnionUF uf;
  private int gridSize;
  private int rowLength;
  
  public Percolation(int N) {
    if (N <= 0)
      throw new IllegalArgumentException();
    rowLength = N;
    gridSize = N*N;
    status = new byte[gridSize];
    uf = new WeightedQuickUnionUF(gridSize);
    
  }
    
  public void open(int i, int j) {
    checkInput(i, j);
    status[gridArrayIndex(i - 1, j - 1)] = (byte) (status[gridArrayIndex(i - 1, j - 1)] | OPEN);
    checkNeighbors(i - 1, j - 1);
    if (status[gridArrayIndex(i - 1, j - 1)] == (byte) 7) 
      percolation = true;
  }
  
  public boolean isOpen(int i, int j) {
    checkInput(i, j);
    return (status[(gridArrayIndex(i-1, j-1))] & OPEN) != 0;    
  }
  
  public boolean isFull(int i, int j) {
    checkInput(i, j);
    return ((status[uf.find(gridArrayIndex(i-1, j-1))] & 2) != BLOCKED);
  }
  
  public boolean percolates() {
    return percolation;
  }
  
  private void checkNeighbors(int i, int j) {
    int index = gridArrayIndex(i, j);    
    if (i == 0) {
      status[gridArrayIndex(i, j)] = (byte) (status[gridArrayIndex(i, j)] | OPEN | FULL);
     }
    if (i == rowLength - 1) {
      status[gridArrayIndex(i, j)] = (byte) (status[gridArrayIndex(i, j)] | OPEN | BOTTOMCONNETED);
     }
    if (i > 0 && status[gridArrayIndex(i-1, j)] > BLOCKED) {
      connectToNeighbor(index, i-1, j);
    }
    if (i < rowLength-1 && status[gridArrayIndex(i+1, j)] > BLOCKED) {
      connectToNeighbor(index, i+1, j);
    }
    if (j > 0 && status[gridArrayIndex(i, j-1)] > BLOCKED) {
      connectToNeighbor(index, i, j-1);
    }
    if (j < rowLength-1 && status[gridArrayIndex(i, j+1)] > BLOCKED) {
      connectToNeighbor(index, i, j+1);
    }
  }
  
  private void connectToNeighbor(int index, int i, int j) {
    int nIndex = gridArrayIndex(i, j);
    int oldRoot = uf.find(index);
    int nRoot = uf.find(nIndex);
    uf.union(index, nIndex);
    int newRoot = uf.find(index);
    if ((status[oldRoot] & FULL) != 0 || (status[nRoot] & FULL) != 0) {
      status[newRoot] = (byte) (status[newRoot] | FULL);
    }
    if (status[oldRoot] > BOTTOMCONNETED || status[nRoot] > BOTTOMCONNETED) {
      status[newRoot] = (byte) (status[newRoot] | BOTTOMCONNETED);
    }
    if (status[newRoot] == (byte) (7)) {
      percolation = true;
    }
  }
  
  private void checkInput(int i, int j) {
    if (i < 1 || i > rowLength) {
      throw new java.lang.IndexOutOfBoundsException();
    }
    if (j < 1 || j > rowLength) {
      throw new java.lang.IndexOutOfBoundsException();
    }
  }
  
  private int gridArrayIndex(int i, int j) {
    return (rowLength * i + j);
  }
}