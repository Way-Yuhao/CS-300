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
 * This classes contains methods used to clear out all furniture objects within the room
 * @author LiuYuhao
 */
public class ClearButton extends Button implements DormGUI{
	
	/**
	 * This method creates a button with given parameters.
	 * @param x the x coordinate of the center of the button
	 * @param y the y coordinate of the center of the button
	 * @param processing
	 */
	public ClearButton(float x, float y, PApplet processing) {
		//call the constructor of the Button class
		super(x, y, processing);
		//the text that the button displays
		label = "Clear Room";
		return;
	}
	
	/**
	 * This method clears all instances of Furniture in the array
	 * to null.
	 * @param furniture the furniture array passed in from main 
	 * that needs to be modified.
	 * 
	 */
	@Override
	public void mouseDown(Furniture[] furniture) {
		for (int i = 0; i < furniture.length; i++) {
			//set all elements within the array to null
			furniture[i] = null;
		}
	}
}
