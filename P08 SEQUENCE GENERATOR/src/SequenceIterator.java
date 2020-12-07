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
import java.util.Iterator;

/**
 * SequenceIterator class that implements the interface Iterator<Integer> of
 * java.util package and can iterate over any sequence of numbers.
 * 
 */
public class SequenceIterator implements Iterator<Integer>{
	//a NumberGenerator object that generates and returns 
	//a number of index n in a sequence of numbers 
    private NumberGenerator generator; 
    private int size;  // size of the sequence
    private int index; // index of the next number to be generated in the sequence
    
    /**
     * Constructs a SequenceIterator with given number generator and size
     * This constructs initializes also the index to 0
     * @param generator
     * @param size
     */
    public SequenceIterator(NumberGenerator generator, int size) {
        this.generator = generator;
        this.size = size;
        index = 0;
    }
    
    /**
     * Returns true if the sequence contains more elements after a
     * given index.
     */
    @Override
    public boolean hasNext() {
        if (size > index) 
        	return true;
        else 
        	return false;
    }
    
    /**
     * The method points to the next element within the sequence.
     */
    @Override
    public Integer next() {
        return generator.generateNumber(index++);
    }
}