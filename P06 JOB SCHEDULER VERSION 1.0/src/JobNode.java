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
 * This class contains methods and fields used by individual nodes within 
 * the JobLList.
 *
 */
public class JobNode {
		// Class Fields
		private static int jobCount = 0; // number of jobs already created
		// Object Fields
		private int jobId; 			// unique job identifier
		private float arrivalTime;  // arrival time in seconds
		private int userId;			// identifier of the user that created the job
		private int priority; 		// job priority
		private int timeToLive; 	// job Time To Live in seconds
		private String description; // job description
		
		private JobNode next; // reference to the next job in the linked list
		
		/**
		 * Description of the constructor comes here
		 * @param arrivalTime
		 * @param userId
		 * @param priority
		 * @param ttl
		 * @param description
		 */
		public JobNode(float arrivalTime, int userId, int priority, 
				int ttl, String description) {
			this.arrivalTime = arrivalTime;
			this.userId = userId;
			this.priority = priority;
			this.timeToLive = ttl;
			this.description = description;
			jobCount++;
			jobId = jobCount;
		}
		
		/**
		 * This method returns a new reference to a copy of the current JobNode
		 * @return a new reference to a copy of this (instanceof JobNode)
		 */
		public JobNode copy() {
			int jobCountTemp = jobCount;
			//create a new instance of JobNode and copy the value of the current JobNode
			JobNode jobNodeCopy = new JobNode(arrivalTime, userId, priority, 
				timeToLive, description);
			jobCount = jobCountTemp; //do not increase jobCount
			jobNodeCopy.jobId = jobCountTemp; //retain the original jobID
			return jobNodeCopy;
		}
		
		/**
		 * Getter method that retrieves the value of jobCount.
		 * @return
		 */
		public static int getJobCount() {
			return jobCount;
		}
		
		/**
		 * Getter method that retrieves the value of jobId.
		 * @return
		 */
		public int getJobId() {
			return this.jobId;
		}
		
		/**
		 * Getter method that retrieves the value of arrivalTime.
		 * @return
		 */
		public float getArrivalTime() {
			return this.arrivalTime;
		}
		
		/**
		 * Getter method that retrieves the value of userId.
		 * @return
		 */
		public int getUserId() {
			return this.userId;
		}
		
		/**
		 * Getter method that retrieves the value of priority.
		 * @return
		 */
		public int getPriority() {
			return this.priority;
		}
		
		/**
		 * Getter method that retrieves the value of timeToLive.
		 * @return
		 */
		public int getTimeToLive() {
			return this.timeToLive;
		}
		
		/**
		 * Getter method that retrieves the content of description.
		 * @return
		 */
		public String getDescription() {
			return this.description;
		}
		
		/**
		 * Getter method that retrieves the reference of next JobNode.
		 * @return
		 */
		public JobNode getNext() {
			return this.next;
		}
		
		/**
		 * Setter method that sets the value of jobId.
		 * @param jobId
		 */
		public void setJobId(int jobId) {
			this.jobId = jobId;
		}
		
		/**
		 * Setter method that sets the value of arrivalTime.
		 * @param arrivalTime
		 */
		public void setArrivalTime(float arrivalTime) {
			this.arrivalTime = arrivalTime;
		}
		
		/**
		 * Setter method that sets the value of userId.
		 * @param id
		 */
		public void setUserId(int id) {
			this.userId = id;
		}
		
		/**
		 * Setter method that sets the value of priority.
		 * @param priority
		 */
		public void setPriority(int priority) {
			this.priority = priority;
		}
		
		/**
		 * Setter method that sets the value of timeToLive.
		 * @param ttl
		 */
		public void setTimeToLive(int ttl) {
			this.timeToLive = ttl;
		}
		
		/**
		 * Setter method that sets the value of description.
		 * @param description
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
		/**
		 * Set the reference to the next Node
		 * @param next
		 */
		public void setNext(JobNode next) {
			this.next = next;
		}
}
