import java.util.*;

public class Deque <Item> implements Iterable <Item> {
	
	private int size;
	private node first;
	private node last;
	
	private class node{
		private Item item;
		private node next;
		private node previous;
	}
		
	public Deque(){
		
	}

	public Iterator<Item> iterator(){
		return null;		
	}
	public static void main(String[] args) {

	}

}
