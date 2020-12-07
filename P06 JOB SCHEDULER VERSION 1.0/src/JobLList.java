//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P06 JOB SCHEDULER v1.0
// Files:           JobNode.java, WaitingListADT.java, JobLList.java, 
//					JobScheduler.java
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
 * This class is linked list containing a number of individual JobNodes.
 * @author LiuYuhao
 *
 */
public class JobLList implements WaitingListADT<JobNode>{
	JobNode head; //head of the list
	int size; //number of jobs in the list

	/**
	 * Default constructor that creates an empty list.
	 */
	public JobLList() {
		head = null;
		size = 0;
	}
	/**
	 * Adds an item of type <JobNode> to the waiting list
	 * according to a specific scheduling policy
	 * @param newObject
	 */
	public void schedule(JobNode newJob) {
		//if the new job has priority 0
		if (newJob.getPriority() == 0) {
			if (this.head == null) {
				this.head = newJob;
			} else {
				JobNode lastNode = head;
				//keep skipping through the list until reaches the end 
				while(lastNode.getNext() != null)
					lastNode = lastNode.getNext();
				newJob.setNext(lastNode.getNext());
				lastNode.setNext(newJob);
			}
		} else if (newJob.getPriority() == 1) {
			if (this.head == null) {
				this.head = newJob;
			} else {
				//if the first node has priority 0
				if (head.getPriority() == 0) {
					//place the higher-prioritized node at head
					//then attach the old head next to newJob
					newJob.setNext(head);
					head = newJob;
				} else { //if the first node has priority 1
					JobNode lastNode = head;
					//iterate through the list and find the node whose next node has low priority
					while((lastNode.getNext() != null) && (lastNode.getNext().getPriority() == 1))
						lastNode = lastNode.getNext();
					newJob.setNext(lastNode.getNext());
					lastNode.setNext(newJob);
				}
			}
		}
		size++; //increase the size of the list by 1;
	}
	
	/**
	 * Checks if the waiting list is empty
	 * returns true if the waiting list empty
	 * false otherwise
	 * @return
	 */
	public boolean isEmpty() {
		if (this.size == 0)
			return true;
		else 
			return false;
	}
	
	/**
	 * Returns the number of items in the waiting list
	 * @return
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Removes the obsolete items from the waiting list
	 * @param cleaningTime
	 * @return
	 */
	public int clean(float cleaningTime) {
		JobNode currentJob = head;
		int jobsRemoved = 0; //number of Nodes removed
		if (this.size == 0) //if the list contains no elements, return 0
			return 0;
		int index = 0; //indicating the sequence of Node being examined
		do {
			if (currentJob.getArrivalTime() + currentJob.getTimeToLive() < cleaningTime) {
				 this.removeJob(index); //remove the JobNode at index i
				jobsRemoved ++;
				index--; //since of of the node has been removed, rewind index
			}
			currentJob = currentJob.getNext();//iterate to the next JobNode
			index++;
		} while (currentJob != null); //repeat until reaches the last Node
		return jobsRemoved; 
	}
	
	/**
	 * Helper method that removes a JobNode at a given index.
	 * @param index
	 */
	public void removeJob(int index) {
		JobNode previousJob = head;
		//if the head is to be removed
		if (index == 0) {
			head = head.getNext();
			this.size--; //reduce the size of the list by 1
			return;
		} else { //if a Job other than the head is to be removed
			int steps = 0;
			while(previousJob != null) {
				if(steps == index - 1) { 
					// remove node after beforeIndexthNode:
					if ((previousJob.getNext() != null) && (previousJob.getNext().getNext() != null))
						previousJob.setNext(previousJob.getNext().getNext());
					else 
						previousJob.setNext(null);
					this.size--; //reduce the size of the list by 1
					return;
				}
				steps++;
				previousJob = previousJob.getNext();
			}
		}
		
	}
	
	/**
	 * Removes all the items in the waiting list
	 */
	public void clear() {
		head = null;
		size = 0;
	}
	
	/**
	 * Returns the reference to the head JobNode in the list
	 * @return
	 */
	public JobNode getHead() {
		return this.head;
	}
	
	/**
	 * Returns the size of the list.
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Returns a String representation of the JobLList.
	 * @return string representation of the object
	 */
	public String toString() { 
		String str = ""; //the String to be returned
		//if 
		str += "Job List is empty: ";
		if (size == 0)
			str += "true\n";
		else 
			str += "false\n";
		str += "The size is: " + size + " job(s).\n"; 
		JobNode currentJob = head;
		//add the each job elements 
		for (int i = 0; i < size; i++) {
			if (currentJob != null) {
				str += "job #" + currentJob.getJobId() + " : " + currentJob.getDescription()
						+ " (UID " + currentJob.getUserId() + ") "
						+ currentJob.getPriority() + "\n";
				//iterate to the next JobNode
				currentJob = currentJob.getNext();
			}
		}
		return str;
	}
	
	/**
	 * Returns a new reference to a duplicate copy of the list
	 * @return a new reference to a duplicate copy of the list
	 */
	public WaitingListADT<JobNode> duplicate(){
		JobLList listCopy = new JobLList();
		listCopy.head = this.head.copy();
		listCopy.size = 1;
		JobNode currentNode = head;
		JobNode currentNodeCopy = listCopy.head;
		for(int i = 1; i < size; i ++) {
			currentNodeCopy.setJobId(currentNode.getJobId()); //copy over the value of JobID
			currentNodeCopy.setNext(currentNode.getNext().copy());
			listCopy.size++; //increase the size by 1
			currentNode = currentNode.getNext();
			currentNodeCopy = currentNodeCopy.getNext();
		}
		//set the jobID of the last node to the corrected value
		currentNodeCopy.setJobId(currentNode.getJobId()); 
		return listCopy;
	}
}
