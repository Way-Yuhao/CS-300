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
 * The class Passenger is used to represent each passenger that is about to board
 * on a particular plane. 
 * @author LiuYuhao
 *
 */
public class Passenger implements Comparable<Passenger> {
	private String name; //the name of the passenger
	private int time; //The time a passenger checked in, 
	//as a number in minutes after the airport opened for business that day.
	private String seat; //the seat number of the passenger.
	private int preferredBoarding; // indicates extra priority that a passenger may have
	private int doneTimeEstimate; //the estimated time that a passenger will finish boarding
	private int timeSeated; //the actual time that a passenger finishes boarding
	private int timeBoarded; //the actual time that the passenger boards on the plane
	
	/**
	 * Default Passenger Constructor that takes in name, time, and seat in the form of
	 * parameters and set preferredBoarding field to zero.
	 * @param name
	 * @param time
	 * @param seat
	 */
    public Passenger(String name, int time, String seat) {
    	this.name = name;
    	this.time = time;
    	this.seat = seat;
    	this.preferredBoarding = 0; //by default
    	this.doneTimeEstimate = -1; //by default
    }
    
    /**
     * Secondary Passenger constructor that takes in name, time, seat, and preferredBoarding
     * values.
     * @param name
     * @param time
     * @param seat
     * @param preferredBoarding
     */
    public Passenger(String name, int time, String seat, int preferredBoarding) {
    	this.name = name;
    	this.time = time;
    	this.seat = seat;
    	this.preferredBoarding = preferredBoarding; 
    	this. doneTimeEstimate = -1; //by default
    }
    
    /**
     * This method is used to store a passenger’s estimated boarding completion time 
     * (a component of their priority) as they are enqueued into the heap.
     * @param estimate
     */
    public void setDoneTimeEstimate(int estimate) { 
    	this.doneTimeEstimate = estimate;
    }
    
    /**
     * This method is used to use to determine whether two passengers should be swapped
     * when enqueuing/dequeuing.
     * @return 1 if passenger passed in has a lower priority (the should not be swapped), 
     * -1 if the passenger passed in has a higher priority (they should be swapped).
     * 
     */
    @Override
    public int compareTo(Passenger other) {
    	int result; //Compared result
    	//if the new passenger has a lower priority
    	if (this.preferredBoarding > other.preferredBoarding) {
    		result = 1; //do not swap
    	//if the two passengers have the same priority, then compare boarding time
    	} else if (this.preferredBoarding == other.preferredBoarding) {
    		//if the new passenger boards sooner
    		if (this.doneTimeEstimate > other.doneTimeEstimate) 
    			result = -1; //swap
    		else if (this.doneTimeEstimate == other.doneTimeEstimate)
    			result = 0; //the two have the same priority
    		else
    			result = 1; //do not swap
    	} else { //if the new passenger has a higher priority
    		result = -1; //swap
    	}
    	return result;
    }
    
    /**
     * This method returns the row number that the passenger is assigned at check-in
     * @return row number
     */
    public int getRow() {
    	String row = new String(seat); //generate a copy of the seat
    	row = row.substring(0, row.length() - 1);
    	return Integer.parseInt(row);
    }
    
    /**
     * This method returns the time that the passenger checks in
     * @return the time that the passenger checks in
     */
    public int getTimeCheckedIn() {
    	return this.time;
    }
    
    /**
     * This method gets the value of doneTimeEstimate
     * @return doneTimeEstimate
     */
    public int getDoneTimeEstimate() {
    	return doneTimeEstimate;
    }
    
    /**
     * Mutator that sets the field timeBoarded
     * @param timeBoarded
     */
    public void setTimeBoarded(int timeBoarded) {
    	this.timeBoarded = timeBoarded;
    }
    
    /**
     * Mutator that sets the field timeSeated
     * @param timeSeated
     */
    public void setTimeSeated(int timeSeated) {
    	this.timeSeated = timeSeated;
    }
    
    /**
     * Getter for the field TimeSeated
     * @return the actual time that the passenger finishes boarding
     */
    public int getTimeSeated() {
    	return this.timeSeated;
    }
    
    /**
     * Getter method for the field timeBoared
     * @return the actual time that a passenger starts boarding
     */
    public int getTimeBoarded() {
    	return this.timeBoarded;
    }
    
    /**
     * Returns a string representation of the passenger that contains
     * information on name, seat, and the actual time that the passenger 
     * finishes boarding.
     * @return a string representation of the passenger object
     */
    @Override
    public String toString() {
    	return name + " " + seat + " (done " + timeSeated + ")";
    }
}