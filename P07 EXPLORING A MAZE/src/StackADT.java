//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P07 EXPLORING A MAZE
// Files:           StackADT.java, MazeRunnerStack.java, Maze.java, 
//					TestStack.java
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

import java.util.EmptyStackException;

/**
 * This interface defines an abstract data type named stack.
 * @author LiuYuhao
 *
 * @param <E>
 */
public interface StackADT<E> {
	/**
	 * This method adds a new item to the top of the stack.
	 * @param item
	 */
	public void push(E item); 
	
	/**
	 * This method removes the top item from the stack and returns it
	 * @return
	 * @throws EmptyStackException
	 */
	public E pop() throws EmptyStackException; 
	
	/**
	 * This method returns the top item from the stack without removing it
	 * @return
	 * @throws EmptyStackException
	 */
	public E peek() throws EmptyStackException; 
	 
	/**
	 * This method returns true if the stack is empty, otherwise returns false
	 * @return
	 */
	public boolean isEmpty(); 
}
