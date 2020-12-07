package exam2;

import java.util.Iterator;

public class UnsortedArray <T extends Comparable<T>> 
implements PriorityQueueADT<T> , Iterable<T>{
	//limits T to only the objects that have a .compareTo method
	//private T bad = new T();
	//T must have a no-arg constructor, thus won't compile
	private T[] array = (T[]) new Comparable[10];
	//will compile with Array, but not ArrayLists
	//will cause a RuntimeException if using Objects instead of Comparable
	//private static T bad;
	//cannot be static
	
	private int size = 0;

	@Override
	public void enqueue(T newObject) {
		//if (size == array.length)
		//options: throw exception, copy into bigger array, or use a shadow array
		array[size] =newObject;
		size++;
	}

	@Override
	public T dequeue() {
		//if (isEmpty) throw exception
		int bestIndex = 0;
		for (int i = 0; i < size; i++) {
			if (array[bestIndex].compareTo(array[i]) > 0) 
				//if bextIndex is later in the sequence
				bestIndex = i;
		}
		T value = array[bestIndex];
		//remove without worrying about the order
		array[bestIndex] = array[size - 1]; //-1?!!
		array[size-1] = null;
		size--;
		return value;
	}

	@Override
	public T peek() {
		//if (isEmpty) throw exception
		int bestIndex = 0;
		for (int i = 0; i < size; i++) {
			if (array[bestIndex].compareTo(array[i]) > 0) 
				//if bextIndex is later in the sequence
				bestIndex = i;
		}
		return array[bestIndex];
	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (T t: this) //has to be an array or iterables
			s += t + " ";
			//t = null; will not change the actual list TODO
		return s;
	}

	@Override
	public Iterator<T> iterator() {
		return new UnsortedArrayIterator(array, size);
	}
}

class UnsortedArrayIterator<T> implements Iterator<T> {
	private T[] array;
	private int size;
	private int nextIndex;
	public UnsortedArrayIterator(T[] array, int size) {
		this.array = array;
		this.size = size;
	}
	
	@Override
	public boolean hasNext() {
		return nextIndex < size; //WHAT THE FUCK TODO
	}

	@Override
	public T next() {
		T t = array[nextIndex];
		nextIndex++;
		return t;
	}
	
}