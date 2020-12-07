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

import java.util.ArrayList;
/**
 * This class contains the main framework of this application. It serves to
 * initialize the graphic interface, initialize fields used here and in 
 * additional classes, and call methods that pertains to other classes.
 * @author Yuhao Liu
 *
 */
public class Main {
	private PApplet processing;
	private PImage backgroundImage; //stores the background image of the room
	private ArrayList<DormGUI> guiObjects;
	// Max number of furniture that LoadButton will be allowed to load at once.    
	private final static int MAX_LOAD_FURNITURE = 100;     
	
	/**
	 * The Main method initializes elements are are used to store, process
	 * and delete furniture, while initializing the graphic interface.
	 * @param processing
	 * 
	 */
	public Main(PApplet processing) {
		this.processing = processing;
		//initialize background image
		this.backgroundImage = processing.loadImage("images/background.png");
		guiObjects = new ArrayList<DormGUI>();
		//add all buttons into guiObjects
		guiObjects.add(new CreateFurnitureButton("Bed", 50, 24, processing));
		guiObjects.add(new CreateFurnitureButton("Sofa", 150, 24, processing));
		guiObjects.add(new CreateFurnitureButton("Dresser", 250, 24, processing));
		guiObjects.add(new CreateFurnitureButton("Desk", 350, 24, processing));
		guiObjects.add(new CreateFurnitureButton("Sink", 450, 24, processing));
		guiObjects.add(new ClearButton(550, 24, processing));
		guiObjects.add(new SaveButton(650, 24, processing));
		guiObjects.add(new LoadButton(750, 24, processing));
	}
	
	/**
	 * This method updates any elements contained in the room and related buttons.
	 */
	public void update() {
		//refresh the entire room
		processing.clear();
		//set the background color
		processing.background(100,150,250);
		//update the background image
		processing.image(backgroundImage, processing.width/2, processing.height/2);
		//update all guiObjects
		for (int i = 0; i < guiObjects.size(); i++) {
			guiObjects.get(i).update();
		}
	}
	
	/**
	 * This method triggers the related functions when the mouse first pressed down
	 */
	public void mouseDown() {
		for (int i = 0; i < guiObjects.size(); i++) {
			//iterate through each guiObjects
			if (guiObjects.get(i).isMouseOver()) {
				//if the mouse if hovering over a certain guiObject,
				//call the corresponding mouseDown() method
				if (guiObjects.get(i) instanceof Furniture) {
					guiObjects.get(i).mouseDown(null); 
				} else { //refers to button objects
					//extract Furniture objects from the arrayList
					Furniture[] furniture = extractFurnitureFromGUIObjects();
					//pass in the extract objects into the corresponding method
					guiObjects.get(i).mouseDown(furniture);
					//update the arrayList
					replaceFurnitureInGUIObjects(furniture);
				}
			}
		}
	}
	
	/**
	 * This method triggers the related functions when the mouse first pressed down
	 */
	public void mouseUp() {
		for (int i = 0; i < guiObjects.size(); i++) {
			guiObjects.get(i).mouseUp();
		}
	}
	
	/**
	 * This method determines which key is pressed by the user and 
	 * then trigger related functions.
	 */
	public void keyPressed() {
		//stores the character that the user types in
		char key = Character.toUpperCase(processing.key); 
		switch (key) {
		case 'D': //delete the furniture that the mouse is hovering on
			for (int i = 0; i < guiObjects.size(); i++) {
				if ((guiObjects.get(i) instanceof Furniture) && 
						(guiObjects.get(i).isMouseOver())) {
					guiObjects.remove(i);
					break;
				}
			}
			break;
		case 'R':
			for (int i = 0; i < guiObjects.size(); i++) {
				if ((guiObjects.get(i) instanceof Furniture) && 
						(guiObjects.get(i).isMouseOver())) {
					((Furniture)guiObjects.get(i)).rotate();
					break;
				}
			}
			break;
		}
	}
	
	/**
	 * Main method used to initiated the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Utility.startApplication();
	}
	
	/**
	 * This method creates a new Furniture[] for the old mouseDown() methods
	 * to make use of.  It does so by copying all Furniture references from
	 * this.guiObjects into a temporary array of size MAX_LOAD_FURNITURE.
	 * @return that array of Furniture references.
	 */
	private Furniture[] extractFurnitureFromGUIObjects() {
	    Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
	    int nextFreeIndex = 0;
	    for(int i=0;i<guiObjects.size() && nextFreeIndex < furniture.length;i++)
	        if(guiObjects.get(i) instanceof Furniture)
	        		//add all furniture objects back into guiObjects
	            furniture[nextFreeIndex++] = (Furniture)guiObjects.get(i);        
	    return furniture;        
	}  
	
	/**
	 * This method first removes all Furniture references from this.guiObjects,
	 * and then adds back in all of the non-null references from it's parameter.
	 * @param furniture contains the only furniture that will be left in 
	 *   this.guiObjects after this method is invoked (null references ignored).
	 */
	private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
	    for(int i=0;i<guiObjects.size();i++)
	        if(guiObjects.get(i) instanceof Furniture)
	        		//remove this furniture object
	            guiObjects.remove(i--);
	    for(int i=0;i<furniture.length;i++)
	        if(furniture[i] != null)
	            guiObjects.add(furniture[i]);
	}
}
