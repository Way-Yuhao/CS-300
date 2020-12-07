//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P09 BOARDINGS SCHEDULER
// Files:           Passenger.java, BoardingHeap.java, BoardingScheduler.java
// Course:          CS 300
// Author:          Yuhao Liu
// Email:           liu697@wisc.edu
// Lecturer's Name: Gary Gahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _x_ Write-up states that pair programming is allowed for this assignment.
//   _x_ We have both read and understand the course Pair Programming Policy.
//   _x_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class contains a min-heap designed to put prioritized passengers at the
 * the top of the heap.
 * @author LiuYuhao
 *
 */
public class BoardingHeap {
    //You may store additional private fields as needed
    private Passenger[] heap; //array of passengers currently in the heap
    private int pointer; //points to the index of the last node within the array
    
    /**
     * Default and only constructor of the class that creates an empty array
     * of length 10 and set the pointer to 0
     */
    public BoardingHeap() { 
    	heap = new Passenger[10];
    	pointer = 0;
    }
    
    /**
     * Enqueue method adds a new passenger to the heap and restructure the heap
     * to keep its proper order.
     * @param p
     */
    public void enqueue(Passenger p) { 
    	//if p is the first passenger in the array
    	if (this.isEmpty()) {
    		heap[1] = p; //insert the new passenger to index 1
    		pointer = 1;
    	} else {
    		if (pointer >= heap.length - 1) { //if the array is full
    			expandHeap(); //call helper method to expand the array 
    		}
    		heap[++pointer] = p; //add the new passenger to the end of the array
    		swapUpwardsAt(pointer); //keep the heap in order
    	}
    }
    
    /**
     * Dequeue method pops the passenger with the highest priority out of the array
     * and restructure the array to keep its proper order.
     * @return
     */
    public Passenger dequeue() { 
    	Passenger boardedPassenger = heap[1]; //passenger to be dequeued
    	//replace the boarded passenger with the last passenger in the heap
    	heap[1] = heap[pointer]; //put the last passenger at the head
    	heap[pointer--] = null; //delete the last passenger at from its original spot
    	swapDownwardsAt(1); //keep the heap in order
    	if (pointer < 1) {
    		pointer = 1;
    	}
    	return boardedPassenger; 
    }
    
    /**
     * This method checks if there is any passenger in the array
     * @return true if there is any passenger and vise versa
     */
    public boolean isEmpty() { 
 	   if (heap[1] == null) {
    			return true;
 	   } else {
 		   return false;
 	   }
    }
    
    /**
     * Helper method that expands that size of the array by 2
     * 
     */
   private void expandHeap() {
	   //expand the array to doubled its previous size
	   Passenger[] newHeap = new Passenger[heap.length * 2];
	   //migrate all elements
	   for (int i = 0; i <= heap.length; i++) {
		   newHeap[i] = heap[i];
	   }
	   heap = newHeap; //point heap to the new heap
   }
   
   /**
    * Helper method that swaps a child with its parent whenever there is 
    * a conflict between how they are positioned and their priority
    * @param index
    */
   private void swapUpwardsAt(int index) {
	 //terminate the swapping procedure if the parent does not exist
	   if (index / 2 < 1)
		   return;
	   //Passenger child = heap[index];
	   //Passenger parent = heap[index/2];
	   Passenger temp; //temporary spot used in the swapping procedure
	   //compare priority of the child and parent and determine if there is
	   //a need to swap the two passengers
	   //if the parent has a higher priority than or an equivalent priority 
	   //to the child, stop the swapping procedure
	   if (heap[index/2].compareTo(heap[index]) >= 0) {
	 	   return;
	   } else {//else, if the child has a higher priority, 
		   //swap between the two passengers
		   temp = heap[index/2]; //temp = parent
		   heap[index/2] = heap[index]; //parent = child
		   heap[index] = temp; //child = temp
		   //recursive call to attempt to swap one level above
		   swapUpwardsAt(index/2);
		   return;
	   }
   }  
   
   /**
    * Helper method that swaps a parent with its child whenever there is 
    * a conflict between how they are positioned and their priority
    * @param index
    */
   private void swapDownwardsAt(int index) {
	   Passenger parent = heap[index]; //parent Passenger
	   Passenger leftChild, rightChild, higherPriChild; //child Passenger
	   int nextIndex; //index of the next parent to be swapped with
	   if (index *2 <= pointer) {
		   leftChild = heap[index * 2];
	   } else {
		   leftChild = null;
	   }
	   if (index * 2 + 1 <= pointer) {
		   rightChild = heap[index * 2 + 1];
	   } else {
		   rightChild = null;
	   }
	   Passenger temp;
	   //find the child with the higher priority
	   if ((leftChild == null) && (rightChild == null)) {
		   return; //Base case 1: nothing to swap with
	   } else if (leftChild == null) {
		   higherPriChild = rightChild;
		   nextIndex = index * 2 + 1; //possibly will swap with the right child
	   } else if (rightChild == null) {
		   higherPriChild = leftChild;
		   nextIndex = index * 2; //possibly will swap with the left child
	   } else {
		   higherPriChild = (leftChild.compareTo(rightChild) == 1) ? leftChild : rightChild;
		   nextIndex = (leftChild.compareTo(rightChild) == 1) ? index * 2 : index * 2 + 1;
	   }
	   if (parent.compareTo(higherPriChild) == 1) {
	 	   return;
	   } else { //else, if the child has a higher priority, 
		   //swap between the two passengers
		   temp = parent;
		   parent = higherPriChild;
		   higherPriChild = temp;
		   //recursive call to attempt to swap one level above
		   swapDownwardsAt(nextIndex);
		   return;
	   }  
   }
}
