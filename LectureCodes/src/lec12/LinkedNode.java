package lec12;

public class LinkedNode<T> {
	public T data;
	public LinkedNode<T> next;
	public LinkedNode(T data, LinkedNode<T> next) {
		this.data = data;
		this.next = next;
	}
	
	public static void main(String[] args) {
		LinkedNode<String> list = new LinkedNode<>(
				"A", new LinkedNode<>(
				"B", new LinkedNode<>(
				"C", null)));
		//to access data
		System.out.println(list.next.next.data);
		
		
		//list.next = new LinkedNode("X", list.next.next);
		//originally, list -> "A", list.next -> "B"
		//will replace "B" with "X" 
		
	}
}

