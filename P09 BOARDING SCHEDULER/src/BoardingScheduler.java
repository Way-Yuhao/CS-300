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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The class BoardingScheduler implements both BaordingHeap and Passenger class and is 
 * the engine that drives the check-in and boarding process.
 * @author LiuYuhao
 *
 */
public class BoardingScheduler {
	//stores the passenger that has already begun boarded but not yet finished
	//in a chronological order
	BoardingHeap boardingHeap;
	//stores the passenger that has already checked in and ready to be enqueued
	//to the priority boarding list at a given time
	private ArrayList<Passenger> boardedBuffer;
	private int currentTime; //store the present and actual time at the airport
	
	/**
	 * This method calculates how much time passengers ahead would delay upon a 
	 * certain passenger based on their boarding time and seat assignment.
	 * @param p
	 * @return time in minutes delayed
	 */
	public int calculateDoneTimeEstimate(Passenger p) { 
		int timeAdded = checkCongestionAhead(p); //calling helper method
		return 5 + currentTime + timeAdded;
	}
	
	/**
	 * Helper method that calculates the time in minutes delayed by checking all
	 * passengers that have already boarded on that plane.
	 * @param p
	 * @return
	 */
	private int checkCongestionAhead(Passenger p) {
		//the sum of the time delayed by all possible passengers upon the 
		//new arriving passenger p
		int timeAdded = 0; //congestion time that would have caused by current passenger examined
		int finalTimeAdded = 0; //congestion time evaluated on all passengers ahead
		int newPassRow = p.getRow(); //the row number of the new passenger
		int passAheadRow; //the row number of the passenger ahead
		if (boardedBuffer.isEmpty())
			return 0; //skip empty 
		for (Passenger pAhead: boardedBuffer) {
			passAheadRow = pAhead.getRow();
			//if the passenger ahead sits one that same row as or in front of 
			//the newly arriving passenger
			if (newPassRow >= passAheadRow) {
				//calculate time
				if (currentTime - pAhead.getTimeBoarded() < 5) {
					//only keep record of the maximum amount delayed
					timeAdded = pAhead.getTimeBoarded() + 5 - currentTime;
					finalTimeAdded = (timeAdded > finalTimeAdded) ? timeAdded : finalTimeAdded;
				}
			}
		}
		return timeAdded; 
	}
	
	/**
	 * Mutator that sets the starting time, the earliest that the first passenger can check in
	 * @param time
	 */
	private void setStartTime(int time) {
		this.currentTime = time;
	}
	
	/**
	 * This method checks if all passengers have been checked in and boarded on the plane.
	 * It does so by checking if the iterator of the check-in list and priority boarding heap
	 * have been cleared
	 * @return
	 */
	private boolean checkIfAllCleared(Iterator<Passenger> passengers, Passenger nextP) {
		//check if there is any passenger waiting to be boarded or checked in
		if ((!passengers.hasNext()) && (boardingHeap.isEmpty()) && (nextP == null))
			return true;
		else 
			return false;
	}
	
	/**
	 * This method enqueues all passengers that checked in at a given time t
	 * and return the very next passenger to be checked in at t + 1
	 * @param passengers
	 * @return the next passenger to be checked in at the next minute
	 */
	private Passenger enqueueAll(Iterator<Passenger> checkInQueue, Passenger nextInLineP) {
		Passenger nextP;
		if (nextInLineP != null) {
			// attempt to first enqueue the next passenger that was rejected at check-in 1
			// minute before
			nextP = nextInLineP;
			// enqueue this passenger if it is time for him/her to check in
			if (nextP.getTimeCheckedIn() <= currentTime) {
				nextP.setDoneTimeEstimate(calculateDoneTimeEstimate(nextP));
				boardingHeap.enqueue(nextP);
			} else {
				// if it is not yet the time for the next passenger to check in
				// reject the passenger at time t by returning
				return nextP;
			}
		}
		// if the next passenger in line was checked in, continue check the next
		// passenger
		while (checkInQueue.hasNext()) {
			// retrieve the next passenger waiting to be checked in
			nextP = checkInQueue.next();
			// enqueue this passenger if it is time for him/her to check in
			if (nextP.getTimeCheckedIn() <= currentTime) {
				nextP.setDoneTimeEstimate(calculateDoneTimeEstimate(nextP));
				boardingHeap.enqueue(nextP);
			} else {
				// if it is not yet the time for the next passenger to check in
				// reject the passenger at time t by returning
				return nextP;
			}
		}
		return null; //if all passengers have been checked in, return null
	}
	
	/**
	 * This method contains a series of operations to be performed when checking
	 * in the next passenger
	 * @return the passenger that just boarded
	 */
	private Passenger boardNextPassenger() {
		//dequeue the next passenger from the priority queue
		Passenger pBoarded = boardingHeap.dequeue();
		//check if there is any passengers to board
		if (pBoarded == null) { //if there is none, return null
			return null;
		} else {
			//else, add this passenger to the boardedBuffer
			pBoarded.setTimeBoarded(currentTime);
			pBoarded.setTimeSeated(currentTime + checkCongestionAhead(pBoarded) + 5);
			boardedBuffer.add(pBoarded);
			return pBoarded;
		}
	}
	
	/**
	 * Reads in a file containing a list of flight passengers in the order they
	 * check in and runs the boardFlight() method with those passengers.
	 * @author Tina, Alexi
	 * @param flight is the name of the input file in the project directory
	 */
	public static void checkIn(String flight) {
	    File f = new File(flight);
	    try {
	        Scanner s = new Scanner(f);
	        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	        while(s.hasNextLine()) {
	            //Data are separated by commas and possibly also whitespace.
	            String[] line = s.nextLine().split("\\s*,\\s*");
	            if (line.length == 3) //Default preferredBoarding 0 constructor
	                passengers.add(new Passenger(line[0],
	                        Integer.parseInt(line[1]),
	                        line[2]));
	            else //Use the preferredBoarding number if given
	                passengers.add(new Passenger(line[0],
	                        Integer.parseInt(line[1]),
	                        line[2],
	                        Integer.parseInt(line[3])));
	        }
	        s.close();
	        boardFlight(passengers.iterator(), 0); //FIX_ME artificially introduced parameter
	    } catch (IOException e) {
	        System.out.println("Error: Unable to load file " + flight);
	    }
	}
	
	/**
	 * General checking in and boarding procedure. This method also updates the current time
	 * so that passenger can be checked in and boarded at the right time.
	 * @param passengers
	 * @param startTime
	 */
	public static void boardFlight(Iterator<Passenger> passengers, int startTime) {
		BoardingScheduler scheduler = new BoardingScheduler(); 
		//new instance of BoardingScheduer
		scheduler.boardingHeap = new BoardingHeap(); //priority boarding heap
		scheduler.boardedBuffer = new ArrayList<Passenger>(); //all passengers already boarded
		Passenger nextPToCheckIn = null; //next passenger to check in
		Passenger pBoarded = null; //the previous passenger that boarded on the plane
		
		//set start time
		scheduler.setStartTime(startTime);
		System.out.println(scheduler.currentTime + " Boarding begins\n");
		//outer loop the. Current time is updated per loop
		while (!scheduler.checkIfAllCleared(passengers, nextPToCheckIn)) {
			//store all passengers checked in at time t into enqueueBuffer
			nextPToCheckIn = scheduler.enqueueAll(passengers, nextPToCheckIn);
			//board the next passenger
			pBoarded = scheduler.boardNextPassenger();
			if (pBoarded != null)
				System.out.println(scheduler.currentTime + " "+ pBoarded.toString());
			scheduler.currentTime ++;
		}
		System.out.println(pBoarded.getTimeSeated() + 1 + " All passengers have boarded!");
	}
	
	/**
	 * Main method that initiates the sequence of checking in and boarding for a
	 * particular flight
	 * @param args
	 */
	public static void main(String[] args) {
		checkIn("DL 518");//enter file name here
	}
}
	

