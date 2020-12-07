//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P02 DORM DESIGNER
// Files:           Main.java
// Course:          CS 300
//
// Author:          Yuhao Liu
// Email:           liu697@wisc.edu
// Lecturer's Name: Gary Gahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __x__ Write-up states that pair programming is allowed for this assignment.
//   __x__ We have both read and understand the course Pair Programming Policy.
//   __x__ We have registered our team prior to the team registration deadline.
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

	
public class Main {
	public static void setup(Data data) {
		//initialize background image
		//data.processing.background(100,150,250); FIXME
		PImage backgroundImage = data.processing.loadImage("images/background.png");
		data.backgroundImage = backgroundImage;
		//initialize bed image
		PImage bedImage = data.processing.loadImage("images/bed.png");
		data.bedImage = bedImage;
		
		//initialize an array that holds the position of beds
		data.bedPositions = new float[][] {null, null, null, null, null, null};
		//initialize an array for dragIndex
		data.dragBedIndex = -1;
		//place the first bed at the center
		data.bedPositions[0] = new float[] {data.processing.width/2, 
				data.processing.height/2};
		
	}

	public static void update(Data data) {
		data.processing.clear();
		data.processing.background(100,150,250);
		//update the background image
		data.processing.image(data.backgroundImage, data.processing.width/2, 
				data.processing.height/2);
		//update the position of any beds that are present
		for(int i = 0; i < data.bedPositions.length; i++) {
			if (data.bedPositions[i] == null) {
				//if such bed has not been initialized, continue to the next one
				continue;
			} else if (i == data.dragBedIndex) {
				//if such bed is being dragged
				//update the bedPosition according to the mouse input
				data.bedPositions[i][0] = data.processing.mouseX;
				data.bedPositions[i][1] = data.processing.mouseY;
				//then process the image
				data.processing.image(data.bedImage, data.bedPositions[i][0], 
						data.bedPositions[i][1]);
			} else {
				//process the image
				data.processing.image(data.bedImage, data.bedPositions[i][0], 
						data.bedPositions[i][1]);
			}
		}
	}
	
	public static void mouseDown(Data data) {
		//check if the mouse is hovering above any beds
		//retrieve data
		//FIXME: using int may cause a precision issue
		float mouseX = data.processing.mouseX;
		float mouseY = data.processing.mouseY;
		float leftEdge, rightEdge, upperEdge, lowerEdge;
		
		
		//iterate through each bed
		for (int i = 0; i < data.bedPositions.length; i++) {
			if (data.bedPositions[i] == null) {
				//only process beds that have already been initialized
				//continue;
			} else {
				//redefine position of edges according to each bed
				leftEdge = data.bedPositions[i][0] - data.bedImage.width/2;
				rightEdge = data.bedPositions[i][0] + data.bedImage.width/2;
				upperEdge = data.bedPositions[i][1] + data.bedImage.height/2;
				lowerEdge = data.bedPositions[i][1] - data.bedImage.height/2;
				
				if ((mouseX > leftEdge) && (mouseX < rightEdge)
						&& (mouseY > lowerEdge) && (mouseY < upperEdge)) {
					data.dragBedIndex = i;
					break; //FIXME: possibility of dragging two beds?	
				}
			}
		}
	}
	
	public static void mouseUp(Data data) {
		//reset the index to -1
		data.dragBedIndex = -1;
	}
	
	public static void keyPressed(Data data) {
		char key = Character.toUpperCase(data.processing.key);
		//if the user pressed key B or b, create a new bed
		if (key == 'B') {
			for (int i = 0; i < data.bedPositions.length; i++) {
				//scan for an empty slot to initialize a new bed
				if (data.bedPositions[i] == null) {
					//place the new bed in the center of the room
					data.bedPositions[i] = new float[] {data.processing.width/2, 
							data.processing.height/2};
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Utility.startApplication();
	}
}
