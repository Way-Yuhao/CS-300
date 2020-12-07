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
 * This class defines two private fields: first term “init”, and common ratio “ratio”.
 * It defines a constructor that constructs a GeometricNumberGenerator object with given 
 * start value and common ratio. It throws an IllegalArgumentException if “init” or 
 * “ratio” is less than or equal to zero.
 * It overrides the generateNumber method to generate and return the number of index n 
 * in the geometric sequence with start value init and common ratio “ratio”. We suppose 
 * that the first number in the sequence is of index 0. You should also provide a recursive 
 * implementation for the method generateNumber.
 *
 */
public class GeometricNumberGenerator implements NumberGenerator {
    private final int init;  // The first term in the sequence
    private final int ratio; // The common ratio
    
    /**
     * Constructs a geometric number generator with given
     * start value init and common ratio ratio
     * @param init start value
     * @param diff common difference
     * @throws IllegalArgumentException if any of the input arguments
     * is illegal
     */
    public GeometricNumberGenerator(int init, int ratio) throws IllegalArgumentException{
	    	if ((init > 0) && (ratio > 0)) {
    			this.init = init;
    			this.ratio = ratio;
    		} else { //if init or ration is zero or negative 
    			throw new IllegalArgumentException();
    		}
    }
    
    /**
     * This method generates the number of index n in 
     * the defined geometric sequence recursively.
     * @param n
     * @return
     */
    @Override
    public int generateNumber(int n) {
	    	// Time Complexity: O(N)
    		if (n == 0) {
    			return init;
    		} else {
    			//recursive call while reducing n by 1
    			return generateNumber (n - 1) * ratio;
    		}
    }
}
