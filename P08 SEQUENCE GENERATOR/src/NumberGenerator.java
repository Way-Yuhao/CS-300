//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P08 SEQUENCE GENERATOR
// Files:           NumberGenerator.java, ArithemeticNumberGenerator.java,
//					GeometricNumberGenerator.java, FibonacciNumberGenerator.java,
//					SequenceIterator.java, Sequence.java
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
 * This interface contains one method that generates and returns the term of order 
 * n in a sequence of numbers.
 *
 */
public interface NumberGenerator {
	
	/**
	 * generates and returns the number of order n in a sequence of numbers
	 * @param n order of the generated number in the sequence
	 * @return the nth number in the sequence
	 */
    public int generateNumber(int n);
}