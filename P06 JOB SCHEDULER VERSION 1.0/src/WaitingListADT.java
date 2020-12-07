//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P06 JOB SCHEDULER v1.0
// Files:           JobNode.java, WaitingListADT.java, JobLList.java, 
//					JobScheduler.java
// Course:          CS 300
//
// Author:          Yuhao Liu
// Email:           liu697@wisc.edu
// Lecturer's Name: Gary Gahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __x__ Write-up states that pair programming is allowed for this assignment.
//   __x__ We have both read and understand the course Pair Programming Policy.
//   __x__ We have registered our team prior to the team registration deadline.
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
 * This interface specifies the methods used by some self-defined linked lists.
 * @author LiuYuhao
 *
 * @param <T>
 */
public interface WaitingListADT<T>{
	/**
	 * Adds an item of type <T> to the waiting list
	 * according to a specific scheduling policy
	 * @param newObject
	 */
	public void schedule(T newObject); 
	
	/**
	 * Checks if the waiting list is empty
	 * returns true if the waiting list empty
	 * false otherwise
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of items in the waiting list
	 * @return
	 */
	public int size();
	
	/**
	 * Removes the obsolete items from the waiting list
	 * @param cleaningTime
	 * @return
	 */
	public int clean(float cleaningTime);
	
	/**
	 * Removes all the items in the waiting list
	 */
	public void clear();
	
	/**
	 * Returns a new reference to a duplicate copy of the list
	 * @return a new reference to a duplicate copy of the list
	 */
	public WaitingListADT<T> duplicate();
}
