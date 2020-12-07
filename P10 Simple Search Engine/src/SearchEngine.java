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

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class implements WebPageNode and uses a Binary Search Tree data
 * structure to store and operate on these Nodes.
 * @author LiuYuhao
 *
 */
public class SearchEngine {
    private WebPageNode root; // root of the BST-based search engine
    
    /**
     * Default constructor creates an empty search engine.
     * 
     */
    public SearchEngine() {
    	this.root = null;
    }
	
    /**
     * Returns true if the search engine is empty; false otherwise
     * @return
     */
	public boolean isEmpty() {
		if (this.root == null)
			return true; //returns true if there are no web pages
		else 
			return false;
	}
    
	/**
	 * This method inserts an instance of WebPageNode with the given id and weblink 
	 * into the search engine conforming to the search order property of a BST. 
	 * This method throws an exception if the user tries to insert an entry with a 
	 * duplicate id.
	 * !!!! A helper method should be used here !!!!
	 * The insert method may call a private recursive helper method to operate
	 * @param id
	 * @param webLink
	 * 
	 */
    public void insert(String id, String webLink) {
    	if (isEmpty()) { //if the SE is empty
    		//insert the new array at root
    		root = new WebPageNode(id, webLink);
    		root.setLeftChild(null);
    		root.setRightChild(null);
    		return;
    	} else if (root.getId().equals(id)) {
    	//check for duplication at root level
    		throw new IllegalArgumentException("WARNING: "
    				+ "failed to insert duplicate web page: "+ id + "."); 
    	} else {
    		//call recursive method to insert at a deeper level
    		recInsert(root, id, webLink);
    		return;
    	}
    }
    
    /**
     * Private helper method that recursively inserts a node at proper location.
     * @param cur
     * @param id
     * @param webLink
     */
    private void recInsert(WebPageNode cur, String id, String webLink) {
		if (id.compareTo(cur.getId()) < 0) { 
		//search to the left
			if (cur.getLeftChild() == null) {
				//if the left child is empty
				//insert the new node at left child
				cur.setLeftChild(new WebPageNode(id, webLink));
				return; 
			} else {
				//advance to a level deeper
				recInsert(cur.getLeftChild(), id, webLink);
				return;
			}
		} else if (id.compareTo(cur.getId()) > 0) {
		//search to the right
			if (cur.getRightChild() == null) {
				//if the right child is empty
				//insert the new node at right child
				cur.setRightChild(new WebPageNode(id, webLink));
				return; 
			} else {
				//advance to a level deeper
				recInsert(cur.getRightChild(), id, webLink);
				return;
			}
		} else { //duplicate id
			throw new IllegalArgumentException("WARNING: "
					+ "failed to insert duplicate web page: "+ id + ".");
		}
    }
    
    /**
     * A look-up method that searches for a webPageNode with the given id in the 
     * current search engine and returns the related weblink if that webPageNode 
     * is present. Otherwise, a Warning message starting with "No web link found" 
     * should be returned back, for instance: 
     * "No web link found for the web page <id>".
     * !!!! A helper method should be used here !!!!
     * The searchWebPage method may call a private recursive helper method to operate.
     * @param id
     * @return webLink of the given id
     * 
     */
    public String searchWebPage(String id){
    	String webLink;
    	if (isEmpty()) {
    		return "No web link found for the web page " + id + ".";
    	} else {
    		//recursive call to search at deeper levels
    		webLink = recSearch(root, id);
    		return webLink;
    	}
    }
    
    /**
     * Private helper method that search for a node with id.
     * @param cur
     * @param id
     * @return webLink of a given id
     */
    private String recSearch(WebPageNode cur, String id) {
    	String webLink;
    	if (cur == null) {
    		//base case 1: reached the end of a branch before finding
    		//a result
    		return "No web link found for the web page " + id + ".";
    	} else if (cur.getId().equals(id)) {
    		//base case 2: found a match
    		return cur.getWebLink();
    	} else {
    		if (id.compareTo(cur.getId()) < 0) {
    			//recursive call
    			webLink = recSearch(cur.getLeftChild(), id);
    			return webLink;
    		} else {
    			//recursive call
    			webLink = recSearch(cur.getRightChild(), id);
    			return webLink;
    		}
    	}
    }
    
    /**
     * This method returns the number of webPageNodes stored in the search engine
     * !!!! A helper method should be used here !!!!
     * The getWebPageCount method should call a private recursive helper method to operate.
     * @return the total number of WebPageNodes
     * 
     */
    public int getWebPageCount() {
    	if (isEmpty()) {
    		return 0;
    	} else {
    		//recursive call at root
    		return recCount(root);
    	}
    }
    
    /**
     * Private helper method that recursively counts the total number of nodes.
     * @param cur
     * @return the total number of nodes
     */
    private int recCount(WebPageNode cur) {
    	int pageCount = 0;
    	if (cur == null) {
    		return pageCount;
    	} else {
    		//add 1 to total pageCount if the current node is not null
    		pageCount ++;
    		pageCount += recCount(cur.getLeftChild());
    		pageCount += recCount(cur.getRightChild());
    		return pageCount;
    	}
    }
    
    /**
     * Returns an ArrayList of String that stores all the id fields of the webPageNodes
     * present in the current search engine, sorted in alphabetical order from left to right.
     * !!!! A helper method should be used here !!!!
     * The getAllWebPages method may call a private recursive helper method to operate.
     * @return an arrayList containing the id's of all web pages
     * 
     */
    public ArrayList<String> getAllWebPages(){
    	//ArrayList to return
    	ArrayList<String> webPages = new ArrayList<String>();
    	if (isEmpty()) {
    		return null;
    	} else {
    		//recursive call to go down a deeper level
    		recGetPages(root, webPages);
    		return webPages;
    	}
    }
    
    /**
     * Private helper method that recursively gathers the id's of 
     * all web pages.
     * @param cur
     * @param webPages
     */
    private void recGetPages(WebPageNode cur, ArrayList<String> webPages) {
    	if (cur == null) { 
    		//recursive call to find the left most child from a given branch
    		return;
    	} else { //if reached there are no more left children left
    		recGetPages(cur.getLeftChild(), webPages);
    		webPages.add(cur.getId()); //add the id of itself to the list
    		recGetPages(cur.getRightChild(), webPages); //advance to its right child
    		return; 
    	}
    }
    
    /**
     * Main method that tests the implementation of the method.
     * @param args
     */
    public static void main(String[] args) {
    	SearchEngine google = new SearchEngine(); //new instance of search engine
    	Scanner sc = new Scanner(System.in); //scanner for user input
    	String [] input; //current line split by space
    	String tempLine = ""; //temporary String that stores the entire user input line by line
    	char userCmd = ' '; //user command
    	//prompt on start-up
    	do {
    		//prompt the user
        	System.out.println( "\n" + 
        			"=========================== Search Engine ============================\n"
        			+ "1. Enter 'i <id> <webLink>' to insert a web page in the search engine\n"
        			+ "2. Enter 's <id>' to search a web link in the search engine\n"
        			+ "3. Enter 'p' to print all the web page ids in the search engine\n"
        			+ "4. Enter 'c' to get the count of all web pages in the search engine\n"
        			+ "5. Enter 'q' to quit the program\n"
        			+ "======================================================================\n");
    		System.out.print("Please enter your command:");
    		try {
    			//split input by spaces
    			tempLine = sc.nextLine();
	    		input = tempLine.split(" ");
	    		//check for illegal command
	    		if ((input == null)||(input.length == 0)||(input[0].length() != 1)) {
	    			throw new InputMismatchException("WARNING: Unrecognized command.");
	    		}
	    		userCmd = Character.toLowerCase(input[0].charAt(0)); 
	    		//convert user command to lower case
	    		switch (userCmd) {
	    		case 'i': //insert(id, webLink)
	    			if (input.length > 3)
	    				throw new InputMismatchException("WARNING: Unrecognized command.");
	    			else if (input.length < 3)
	    					throw new InputMismatchException(
	    							"WARNING: failed to insert web page: "
	    							+ "Id/web link can’t be blank!");
	    			google.insert(input[1], input[2]);
	    			break;
	    		case 's': //searchWebPage(id)
	    			if (input.length == 1) {
	    				throw new InputMismatchException(
	    						"WARNING: Invalid syntax for search operation: "
	    						+ "Id link can’t be blank!");
	    			} else if (input.length != 2)
	    				throw new InputMismatchException("WARNING: Unrecognized command.");
	    			System.out.println(input[1] + " - " + google.searchWebPage(input[1]));
	    			break;
	    		case 'p': //getAllWebPages()
	    			if ((input.length != 1) || (tempLine.length() != 1))
	    				throw new InputMismatchException("WARNING: Unrecognized command.");
	    			ArrayList<String> webPages = google.getAllWebPages();
	    			if ((webPages == null) || (webPages.isEmpty())) {
	    				System.out.println("Search Engine is empty.");
	    			} else {
	    				String output = webPages.toString();
	    				output = output.substring(1, output.length() - 1);
	    				System.out.println(output);
	    			}
	    			break;
	    		case 'c': //getWebPageCount()
	    			if ((input.length != 1) || (tempLine.length() != 1))
	    				throw new InputMismatchException("WARNING: Unrecognized command.");
	    			System.out.println(google.getWebPageCount());
	    			break;
	    		case 'q': //quit
	    			if (tempLine.length() != 1)
	    				throw new InputMismatchException("WARNING: Unrecognized command.");
	    			break;
	    		default: //unrecognized command
	    			System.out.println("WARNING: Unrecognized command.");
	    			break;
	    		}
    		} catch (IllegalArgumentException e) {
    			System.out.println(e.getMessage());
    			continue;
    		} catch (InputMismatchException e) {
    			System.out.println(e.getMessage());
    			continue;
    		}
    	} while (!tempLine.toLowerCase().equals("q")); //quit when the user types in 'q'
    	System.out.println(
    			"============================== END ===================================");
    	sc.close();
    }
}