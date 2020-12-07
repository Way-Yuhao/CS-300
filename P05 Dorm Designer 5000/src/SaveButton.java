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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This class contains the fields and methods that pertain to 
 * "Save Room" button. The overall function of this class is to 
 * store the the contents and layout of the existing room to 
 * a .ddd file.
 * @author Yuhao Liu
 *
 */
public class SaveButton extends Button implements DormGUI {
	 
	/**
	 * This method creates a button with given parameters.
	 * @param x the x coordinate of the center of the button
	 * @param y the y coordinate of the center of the button
	 * @param processing
	 */
	public SaveButton(float x, float y, PApplet processing) {
		//call the constructor of the Button class
		super(x, y, processing);
		label = "Save Room";
		return;
	}
	
	/**
	 * This method returns a new object of Furniture of type bed.
	 * 
	 */
	public void mouseDown(Furniture[] furniture) { 
		try {
			//generate a file named "RoomData.ddd" for the output file
			File file = new File("RoomData.ddd");
			PrintWriter output = new PrintWriter(file);
			//output data that are stored in furniture[]
			for (int i = 0; i < furniture.length; i++) {
				//disregard uninitialized furniture objects
				if (furniture[i] != null) {
					output.println(furniture[i].getType() + ": " + furniture[i].getPosition()[0] + 
							", " + furniture[i].getPosition()[1] + ", "
							+ furniture[i].getOrientation());
				}
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");
		}
	} 
}
