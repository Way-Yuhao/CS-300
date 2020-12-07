package exam2;

import java.util.Iterator;

public class SortedLinkedList <T extends Comparable<T>> implements PriorityQueueADT<T>, Iterable<T> {
	
	private LinkedNode<T> head = null;

	@Override
	public void enqueue(T newObject) { // O(n) - spend more time finding sorted position to insert at
		head = enqueueHelper(newObject, head);
	}
	
	// recursive helper method implementation
	private LinkedNode<T> enqueueHelper(T newObject, LinkedNode<T> head) {
		if(head == null || newObject.compareTo( head.data ) < 0)
			head = new LinkedNode<T>(newObject, head);
		else
			head.next = enqueueHelper(newObject, head.next);
		return head;
	}
	
	@Override
	public T peek() {
		if(head == null) throw new IllegalStateException("Cannot peek on empty SortedLinkedList.");
		return head.data;
	}

	@Override
	public T dequeue() {
		if(head == null) throw new IllegalStateException("Cannot peek on empty SortedLinkedList.");
		T value = head.data;
		head = head.next;
		return value;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return new SortedLinkedListIterator<T>(head);
	}
	
	@Override
	public String toString() {
		String s = "";
		for(T t : this)
			s += t + " ";
		return s;
	}
	
	private static String[] data = "X A B Z Y C".split(" ");
	public static void main(String[] args) {
		PriorityQueueADT<String> q = new SortedLinkedList<String>();
		for(int i=0;i<data.length;i++)
			q.enqueue( data[i] );
		System.out.println( q );
		while( !q.isEmpty() )
			System.out.print( q.dequeue() + " " );
	}
}

class SortedLinkedListIterator<T> implements Iterator<T> {
	private LinkedNode<T> nextNode;

	SortedLinkedListIterator(LinkedNode<T> head) {
		nextNode = head;
	}
	
	@Override
	public boolean hasNext() {
		return nextNode != null;
	}

	@Override
	public T next() {
		T value = nextNode.data;
		nextNode = nextNode.next;
		return value;
	}	
}

class LinkedNode<T> {
	public T data;
	public LinkedNode<T> next;
	public LinkedNode(T data, LinkedNode<T> next) {
		this.data = data;
		this.next = next;
	}
}