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
 *  The Sequence class implements the interface Iterable<Integer> and represents
 *   any sequence of integer numbers. 
 *
 */
public class Sequence implements Iterable<Integer>{
	// a NumberGenerator object that generates and returns a number of index
	//n in a sequence of numbers
    private NumberGenerator generator; 
    private int size; // Number of items in the sequence
    
    /**
     * Creates a Sequence of Integers with a given instance of
     * NumberGenerator and a given size
     * @param generator
     * @param size
     * @throws IllegalArgumentException if size is negative
     */
    public Sequence(NumberGenerator generator, int size) 
    		throws IllegalArgumentException{
    		if (size < 0) {
    			throw new IllegalArgumentException();
    		} else {
    			this.generator = generator;
    			this.size = size;
    		}
    }
    
    /**
     * Return a reference to an Object of type Iterator<Integer> 
     * to iterate over that sequence
     */
    @Override
    public Iterator<Integer> iterator() {
        return new SequenceIterator(generator, size);
    }
    
    /**
     * Overrides the toString method of java.lang.Object 
     * class to return a String representation of the sequence.
     * The different numbers of the sequence would be 
     * separated by a single space
     */
    @Override
    public String toString(){
    	Iterator<Integer> iter = iterator();
		String str = "";
		while (iter.hasNext()) {
			str += iter.next() + " ";
		}
		str += "\n";
		return str;
    }
    
    /**
     * Main method to test the impelmentation of the class.
     * @param args
     */
    public static void main(String[] args) {
    	System.out.println("****************************************");
    	System.out.println("           Sequence Generator");
    	System.out.println("****************************************");
    	         
    	System.out.println("==========Arithmetic Sequence==========");
    	Sequence sequence = new Sequence(new ArithmeticNumberGenerator(2,2),4);
    	System.out.println(sequence.toString());
    	         
    	System.out.println("==========Geometric Sequence==========");
    	sequence = new Sequence(new GeometricNumberGenerator(2,2),10);
    	System.out.println(sequence.toString());
    	         
    	System.out.println("==========Fibonacci Sequence==========");
    	sequence = new Sequence(new FibonacciNumberGenerator(),20);
    	System.out.println(sequence.toString());
    }
}   