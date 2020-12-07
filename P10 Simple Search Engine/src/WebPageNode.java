//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P10 P10 SIMPLE SEARCH ENGINE 
// Files:           WebPageNode.java, SearchEngine.java
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
 * This class acts as a individual web site that contains both an id and a weblink
 * in a form of String.
 * @author LiuYuhao
 *
 */
public class WebPageNode{
    private final String id; 		// The id of the web page
	private final String webLink;   // The web link of the web page
    private WebPageNode leftChild;  // The leftChild of the the current WebPageNode
    private WebPageNode rightChild; // The rightChild of the the current WebPageNode
    
    /**
     * The only and default constructor of the class.
     * @param id
     * @param webLink
     */
    public WebPageNode(String id, String webLink) { 
    	this.id = id;
    	this.webLink = webLink;
    	leftChild = null; 
    	rightChild = null; 
    } 
    
	/**
	 * This method returns the reference of the left node.
	 * @return left node
	 */
	public WebPageNode getLeftChild() {
		return leftChild; 
	}
	
	/**
	 * This method sets left node.
	 * @param leftChild
	 */
	public void setLeftChild(WebPageNode leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * This method returns the reference of the right node.
	 * @return right node
	 */
	public WebPageNode getRightChild() {
		return rightChild; 
	}
	
	/**
	 * This method sets the right node.
	 * @param rightChild
	 */
	public void setRightChild(WebPageNode rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * This method returns the reference of id
	 * @return id
	 */
	public String getId() {
		return this.id; 
	}
	
	/**
	 * This method returns the reference of webLink
	 * @return webLink
	 */
	public String getWebLink() {
		return this.webLink;
	}
}