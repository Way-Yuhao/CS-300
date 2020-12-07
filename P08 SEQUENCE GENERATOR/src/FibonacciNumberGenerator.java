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
 * The FibonacciNumberGenerator class does not define any field and just overrides 
 * the method generateNumber. You do not need to define a constructor for that class. 
 * The default constructor of the super class java.lang.Object would be fine.
 *
 */
public class FibonacciNumberGenerator implements NumberGenerator {
    
	/**
	 * Computes the number of index n in a Fibonacci sequence
	 * iteratively. (Do not use a recursive algorithm here!).
	 */
	@Override
	public int generateNumber(int n) {
		// Time complexity: O(N)
		int preNum = 0; //first of the two bases to be added
		int num = 0; //second of the two bases to be added
		int temp; //temp storage for swapping variables
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			for (int i = 1; i < n; i++) {
				if (i == 1) {
					num = 1;
				} 
				//swap variables
				temp = num;
				num = num + preNum;
				preNum = temp;
			}
			return num;
		}
	}
}