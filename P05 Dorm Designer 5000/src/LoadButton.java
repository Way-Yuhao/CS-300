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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class contains the fields and methods that pertain to 
 * "Load Room" button. The overall function of this class is to 
 * read data from a .ddd file to reconstruct the room from the
 * stored data.
 * @author Yuhao Liu
 *
 */
public class LoadButton extends Button implements DormGUI{
	private Scanner sc;
	// Max number of furniture that LoadButton will be allowed to load at once.    
	private final static int MAX_LOAD_FURNITURE = 100; 
	 
	/**
	 * This method creates a button with given parameters.
	 * @param x the x coordinate of the center of the button
	 * @param y the y coordinate of the center of the button
	 * @param processing
	 */
	public LoadButton(float x, float y, PApplet processing) {
		//call the constructor of the Button class
		super(x, y, processing);
		label = "Load Room";
		return;
	}
	
	/**
	 * This method returns a new object of Furniture of type bed
	 * @param furniture the furniture array passed in from main 
	 * that needs to be modified.
	 * 
	 */
	public void mouseDown(Furniture[] furniture) { 
		try {
			//call helper method
			load(furniture);
		} catch (FileNotFoundException e) {
			//if the system is unable to process the input file,
			//then generate the following exception.
			System.out.println("WARNING: Could not load room "
					+ "contents from file RoomData.ddd.");
		}
	} 
	
	/**
	 * Helper method assisting mouseDown() method
	 * @param furniture
	 * @throws FileNotFoundException
	 */
	private void load(Furniture[] furniture) throws FileNotFoundException {
		int index = 0; //used to indicate the sequence in furniture array
		String[] curLine; //stores the elements in current line
		String[] furProperties; //stores properties of the object
		String type; //type of the Furniture
		float[] position = new float[2]; //x, y positions
		int rotations; //number of 90-degree rotations applied
		
		File inputFile = new File("RoomData.ddd");
		if (!inputFile.exists()) {
			//print out error message for missing file
			System.out.println("WARNING: Could not load room contents "
					+ "from file RoomData.ddd.");
		} else { //if the file exist, proceed subsequent procedures
			sc = new Scanner(inputFile);
			//initialize a new array
			//clear the existing array to null
			for (int i = 0; i < furniture.length; i++) {
				furniture[i] = null;
			}
			while (sc.hasNextLine()) {
				//create new furniture objects that corresponds to the contents 
				//of the "RoomData.ddd"
				try {
					//check if there is available room for more furniture
					if (index >= MAX_LOAD_FURNITURE) { 
						throw new ArrayIndexOutOfBoundsException();
					}
					//split the String at ":"
					curLine = sc.nextLine().split(":");
					//check if this is an empty line
					if (curLine[0].trim().isEmpty()) {
						//if true, jump to the next line
						continue;
					}
					//store the first string segment in type
					type = curLine[0].trim(); 
					//throw an exception if type is empty
					if ((type == null) || (type.isEmpty())) {
						throw new InputMismatchException();
					}
					//check if the furniture name is present
					checkFurniture(type);
					//re-divide the second segment of the string ","s
					furProperties = curLine[1].split(",");
					//throw an exception if there is a format error
					if (furProperties.length != 3) {
						throw new InputMismatchException();
					}
					//convert position data into float 
					position[0] = Float.parseFloat(furProperties[0].trim());
					position[1] = Float.parseFloat(furProperties[1].trim());
					rotations = Integer.parseInt(furProperties[2].trim());
					//if no error is present, initialize a new furniture object
					furniture[index] = new Furniture(type, position[0], 
							position[1], rotations, processing);	
				} catch (InputMismatchException | NumberFormatException e) {
					System.out.println("WARNING: Found incorrectly formatted "
							+ "line in file: " + index);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("WARNING: Unable to load more furniture."); 
					break; 
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} finally {
					//reset variables, regardless if there is an exception thrown 
					type = null;
					position = new float[2];
					rotations = 0;
					curLine = null;
					furProperties = null;
				}
				//if no exception is thrown, iterate to the next index
				index ++;
			}
		}
	}
	
	/**
	 * This method checks if there is a matching image available
	 * to a specified type of furniture
	 * @param type the type of furniture that needs to be checked
	 * @throws FileNotFoundException
	 */
	
	private void checkFurniture(String type) throws FileNotFoundException {
		File file = new File("images/" + type + ".png");
		if (!file.exists())
			throw new FileNotFoundException("WARNING: Could not find an image"
					+ " for a furniture object of type: " + type);
	}
}


