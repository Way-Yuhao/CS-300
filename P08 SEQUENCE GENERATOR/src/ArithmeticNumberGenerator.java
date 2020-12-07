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
 * This class defines two private fields: first term “init”, and common difference “diff”.
 * It defines a constructor that constructs an ArithmeticNumberGenerator object with given 
 * start value and common difference. It throws an IllegalArgumentException if “init” or 
 * “diff” are negative.
 * It overrides the generateNumber method to generate and return the number of index n in 
 * the arithmetic sequence with start value init and common difference diff. We suppose 
 * that the first number in the sequence is of index 0. In addition, for this class, 
 * you should provide a recursive implementation for the method generateNumber.
 *
 */
 
public class ArithmeticNumberGenerator implements NumberGenerator {
    private final int init; // first term in the sequence
    private final int diff; // common difference
    
    /**
     * Constructs an arithmetic number generator with given
     * start value init and common difference diff
     * @param init start value
     * @param diff common difference
     * @throws IllegalArgumentException if any of the input arguments
     * is illegal
     */
    public ArithmeticNumberGenerator(int init, int diff) throws IllegalArgumentException{
    		if ((init >= 0) && (diff >= 0)) {
    			this.init = init;
    			this.diff = diff;
    		} else { //if init or diff is negative, throw an IllegalArgumentException
    			throw new IllegalArgumentException();
    		}
    }
    
    /**
     * This method generates the number of index n in an arithmetic sequence recursively
     * 
     */
    @Override
    public int generateNumber(int n) {
		// Time Complexity: O(N)
    		if (n == 0) {
    			return init;
    		} else {
    			//recursive call while reducing n by 1
    			return generateNumber (n - 1) + diff;
    		}
    }
}