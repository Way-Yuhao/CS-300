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
 * This class implements StackADT and stores a series of individual Position
 * objects. 
 * @author LiuYuhao
 *
 */
public class MazeRunnerStack implements StackADT<Position>{
	private Position stack[]; //array that stores Position objects
	private int topIndex; //index pertaining to the element at the top of the stack
	
	/**
	 * Default MazeRunneStack constructor. No Position objects will be added
	 * when this constructor is called.
	 */
	public MazeRunnerStack() {
		stack = new Position[0];
		topIndex = -1;
	}
	
	/**
	 * This method adds a new item to the top of the stack.
	 * @param item
	 */
	public void push(Position item) {
		//check if there is a need to expand stack's capacity
		if (stack == null) { //if the stack is empty
			stack = new Position[10]; //initialize an array of length 10
			stack[0] = item; //add the item into the array
			topIndex = 0;
		} else if ((topIndex + 1) >= stack.length) { 
			//if the capacity of the array is too small
			Position[] newStack = new Position[stack.length + 10]; //expand the capacity by 10
			migrateContent(stack, newStack); //copy elements in the old stack into the new one
			stack = newStack; //point the stack reference to the new stack
			stack[++topIndex] = item;
		} else { //if there is extra room in the array for the new item
			stack[++topIndex] = item;
		}
	}
	
	/**
	 * This method removes the top item from the stack and returns it
	 * @return
	 * @throws EmptyStackException
	 */
	public Position pop() throws EmptyStackException{
		if ((stack == null) || (topIndex == -1)) {
			throw new EmptyStackException(); //throw exception when the stack is empty 
		}
		//Retrieve the element at the highest available index
		Position item = stack[topIndex]; 
		stack[topIndex] = null; //delete the element on the top
		topIndex--; 
		return item;
	}
	
	/**
	 * This method returns the top item from the stack without removing it
	 * @return
	 * @throws EmptyStackException
	 */
	public Position peek() throws EmptyStackException{
		if ((stack == null) || (topIndex == -1)) {
			throw new EmptyStackException(); //throw exception when the stack is empty 
		}
		//Retrieve the element at the highest available index
		Position item = stack[topIndex];
		return item;
	}
	 
	/**
	 * This method returns true if the stack is empty, otherwise returns false
	 * @return
	 */
	public boolean isEmpty() {
		if (stack == null) { // if the stack contains no elements
			return true;
		} else {
			for (int i = 0; i < stack.length; i++) { //iterate through the entire array
				if (stack[i] != null)
					return false; //if found any non-null elements, return false
			}
			return true; //if all elements are null
		}
	}
	
	/**
	 * Reports whether the Position p can be found within the stack
	 * @param p
	 * @return
	 */
	public boolean contains(Position p) {
		for (int i = 0; i <= topIndex; i++) {
			if (stack[i].equals(p)) { //compare value of the two Position objects
				return true;
			}
		}
		return false;
	} 
	
	/**
	 * Deep copy all elements from the original array into the modified array.
	 * @param ori
	 * @param mod
	 */
	private void migrateContent(Position[] ori, Position[] mod) {
		for (int i = 0; i < ori.length; i++) {
			mod[i] = ori[i];
		}
	}

}

/**
 * The Position class uses a pair of integers to store the row and column at which
 * the object occupies.
 * @author LiuYuhao
 *
 */
class Position {
	int col; //at which row is the object located
	int row; //at which column is the object located
	
	/**
	 * Default constructor that sets the value of row and column via parameters.
	 * @param row
	 * @param col
	 */
	Position(int row, int col) {
		this.col = col;
		this.row = row;
	}
	
	/**
	 * Method that checks if a Position object contains the same reference as the 
	 * object compared.
	 * @param other
	 * @return
	 */
	boolean equals(Position other) {
		return this.col==other.col && this.row==other.row;
	}
	
	/**
	 * Returns a string representation of the object containing the information of 
	 * its row and column.
	 * @return a string representation of the obejct
	 */
	public String toString() {
		String str = "[Position: row " + row + ", col " + col + "]";
		return str;
	}
}

