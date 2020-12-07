////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           P04 DORM DESIGNER 4000
//Files:           Main.java, Furniture.java, CreateBedButton.java, 
//				   CreateSofaButton.java, LoadButton.java, SaveButton.java
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

import java.io.FileNotFoundException;

/**
 * This class contains the main framework of this application. It serves to
 * initialize the graphic interface, initialize fields used here and in 
 * additional classes, and call methods that pertains to other classes.
 * @author Yuhao Liu
 *
 */
public class Main {
	private PApplet processing;
	private PImage backgroundImage; 
	private Furniture[] furniture;
	private CreateBedButton bedButton;
	private CreateSofaButton sofaButton;
	private SaveButton saveButton;
	private LoadButton loadButton;
	
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
		//initialize an array that holds bed objects
		furniture = new Furniture[] {null, null, null, null, null, null}; 
		bedButton = new CreateBedButton(50, 24, processing);
		sofaButton = new CreateSofaButton(150, 24, processing);
		saveButton = new SaveButton(650, 24, processing);
		loadButton = new LoadButton(750, 24, processing);
	}
	
	/**
	 * This method updates any elements contained in the room and related buttons.
	 */
	public void update() {
		processing.clear();
		processing.background(100,150,250);
		//update buttons
		bedButton.update();
		sofaButton.update();
		saveButton.update();
		loadButton.update();
		//update the background image
		processing.image(backgroundImage, processing.width/2, processing.height/2);
		for (int i = 0; i < furniture.length; i++) {
			if (furniture[i] != null)
				furniture[i].update();
		}
		
	}
	
	/**
	 * This method triggers the related functions when the mouse first pressed down
	 */
	public void mouseDown() {
		//iterate through each bed
		for (int i = 0; i < furniture.length; i++) {
			if (furniture[i] != null) 
				furniture[i].mouseDown();
		}
		//check if the mouse is over any button
		if (bedButton.isMouseOver()) {
			//call its mouseDown() method
			Furniture newFur = bedButton.mouseDown();
			addFurniture(newFur);
		} else if (sofaButton.isMouseOver()) { 
			//check if the mouse is over "Create Sofa" button
			//call its mouseDown() method
			Furniture newFur = sofaButton.mouseDown();
			addFurniture(newFur);
		} else if (saveButton.isMouseOver()) {
			saveButton.mouseDown(furniture);
		} else if (loadButton.isMouseOver()) {
			try {
				loadButton.mouseDown(furniture);
			} catch (FileNotFoundException e) {
				//if the system is unable to process the input file,
				//then generate the following exception.
				System.out.println("WARNING: Could not load room "
						+ "contents from file RoomData.ddd.");
			}
		}
	}
	
	/**
	 * This method triggers the related functions when the mouse first pressed down
	 */
	public void mouseUp() {
		for (int i = 0; i < furniture.length; i++) {
			if (furniture[i] != null) 
				furniture[i].mouseUp();
		}
	}
	
	/**
	 * This method determines which key is pressed by the user and 
	 * then trigger related functions.
	 */
	public void keyPressed() {
		char key = Character.toUpperCase(processing.key);
		switch (key) {
		case 'D': //delete the bed that the mouse is hovering on
			for (int i = 0; i < furniture.length; i++) {
				//if the mouse is hovering over a bed
				if ((furniture[i] != null) && (furniture[i].isMouseOver())){
						//delete such bed
						furniture[i] = null;
						break;
					}	
				}
			break;
		case 'R':
			for (int i = 0; i < furniture.length; i++) {
				//if the mouse is hovering over a bed
				if ((furniture[i] != null) && (furniture[i].isMouseOver())){
						furniture[i].rotate();
						break;
					}	
				}
			break;
		default:
			break;
		}
	}
	
	/**
	 * This method adds a new Furniture object of the specified type.
	 * @param newFur a new instance of a Furniture object
	 */
	public void addFurniture(Furniture newFur) {
		for (int i = 0; i < furniture.length; i++) {
			//scan for an empty slot to initialize a new bed
			if (furniture[i] == null) {
				//initiate an new instance of bad
				furniture[i] = newFur;
				break;
			}
		}
	}
	
	/**
	 * Main method used to initiated the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Utility.startApplication();
	}
}
