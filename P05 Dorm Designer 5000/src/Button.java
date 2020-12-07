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
 * The button superclass contains most of the parameters and default methods used by all 
 * button subclasses.
 * @author LiuYuhao
 *
 */
public class Button implements DormGUI{
	//width and height of the button in the GUI
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	protected PApplet processing;
	//an array that stores the location of the center of the button
	private float[] position;
	//location of the edges of the button that help simplify computations
	protected float leftEdge, rightEdge, upperEdge, lowerEdge;
	//a string representation of what is being displayed on the button
	protected String label;
	 
	/**
	 * Default Button class constructor
	 * @param x
	 * @param y
	 * @param processing
	 */
	public Button(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		//store x and y positions into the array
		position[0] = x;
		position[1] = y;
		//calculating the position for edges
		leftEdge = position[0] - WIDTH/2;
		rightEdge = position[0] + WIDTH/2;
		upperEdge = position[1] + HEIGHT/2;
		lowerEdge = position[1] - HEIGHT/2;
		//default label
		label = "Button";
		return;
	}
	 
	/**
	 * This method prints out the button when being called.
	 */
	public void update() {
		if (isMouseOver()) { //if the mouse is hovering over the button
			processing.fill(100);
			processing.rect(leftEdge, upperEdge, rightEdge, lowerEdge);
		} else { //if the mouse is not hovering over the button 
			processing.fill(200);
			//call method to draw the button in a shape of the rectange
			processing.rect(leftEdge, upperEdge, rightEdge, lowerEdge);
		}
		//print out label
		processing.fill(0); //set the text color to black
		processing.text(label, position[0], position[1]);
	}
	
	/**
	 * Default method. Will be called by Main if the mouse is being 
	 * clicked while hovering over this button.
	 */
	public void mouseDown(Furniture[] furniture) {
		//default function that needs to be overwritten
		System.out.println("A button was pressed.");
	}
	
	/**
	 * Method defined in the DormGUI interface
	 */
	public void mouseUp() {
		//do nothing
	}
	
	/**
	 * This method functions to check if the mouse if hovering above the button.
	 */
	public boolean isMouseOver() { 
		//retrieve the value of x, y position of the mouse
		float mouseX = processing.mouseX;
		float mouseY = processing.mouseY;
		if ((mouseX > leftEdge) && (mouseX < rightEdge)
				&& (mouseY > lowerEdge) && (mouseY < upperEdge)) {
			return true;
		} else {
			return false;
		}
	}	
}
