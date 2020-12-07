////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           P05 DORM DESIGNER 5000
//Files:           Main.java, Furniture.java, Button.java, CreateFurniture.java, 
//				   LoadButton.java, SaveButton.java, ClearButton.java, DormGUI.java
//Course:          CS 300, SP 2018
//
//Author:          Yuhao Liu
//Email:           liu697@wisc.edu
//Lecturer's Name: Gary Dahl
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    None
//Partner Email:   None
//Lecturer's Name: None
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//_x_ Write-up states that pair programming is allowed for this assignment.
//_x_ We have both read and understand the course Pair Programming Policy.
//_x_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates 
//strangers, etc do.  If you received no outside help from either type of 
//source, then please explicitly indicate NONE.
//
//Persons:         None
//Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This file contains that interface DormGUI, which is implemented by all
 * Button and Furniture classes.
 * @author LiuYuhao
 *
 */
public interface DormGUI {
	/**
	 * This method updates any elements contained in the room and related buttons.
	 */
	public void update();
	
	/**
	 * This method triggers the related functions when the mouse first 
	 * pressed down. The method of the corresponding class will be called
	 * by Main.
	 */
	public void mouseDown(Furniture[] furniture);
	
	/**
	 * This method triggers the related functions when the mouse first pressed down
	 */
	public void mouseUp();
	
	/**
	 * This method returns a boolean value representing if the mouse is hovering
	 * over the object.
	 * @return true if the mouse is hovering over the object
	 */
	public boolean isMouseOver();
}
