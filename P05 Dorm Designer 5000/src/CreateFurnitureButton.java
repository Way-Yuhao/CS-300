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
 * This class contains the fields and methods that pertain to 
 * "Create <Furniture>" button. The overall function of this class is to 
 * initialize a new instance of object Furniture of type sofa.
 * @author Yuhao Liu
 *
 */
public class CreateFurnitureButton extends Button implements DormGUI {
	//the type of furniture that is intended to be created when the 
	//button is pressed
	 private String type;
	 
	/**
	 * This method creates a button with given parameters.
	 * @param x the x coordinate of the center of the button
	 * @param y the y coordinate of the center of the button
	 * @param processing
	 */
	public CreateFurnitureButton(String type, float x, float y, PApplet processing){
		//call the constructor of the Button class
		super(x, y, processing);
		this.type = type.toLowerCase();
		this.label = "Create " + type;
		return;
	}
	
	/**
	 * This method returns a new object of Furniture of type sofa
	 * @return a new instance of object Furniture of type "sofa"
	 */
	@Override
	public void mouseDown(Furniture[] furniture) { 
		for (int i = 0; i < furniture.length; i++) {
			//scan for an empty slot to initialize a new bed
			if (furniture[i] == null) {
				//initiate an new instance of bad
				furniture[i] = new Furniture(type, processing);
				break;
			}
		}
	} 
}
