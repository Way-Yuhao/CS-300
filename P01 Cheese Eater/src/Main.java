//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P01 Cheese Eater
// Files:           Main.java
// Course:          CS 300
//
// Author:          Yuhao Liu
// Email:           liu697@wisc.edu
// Lecturer's Name: Gary Gahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   x___ Write-up states that pair programming is allowed for this assignment.
//   x___ We have both read and understand the course Pair Programming Policy.
//   x___ We have registered our team prior to the team registration deadline.
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

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
	
	
public class Main {
	
	static final char EMPTY_CHAR = '.';
	static final char WALL_CHAR = '#';
	static final char CHEESE_CHAR = '%';
	static final char MOUSE_CHAR = '@';
	static final int ROOM_WIDTH = 20;
	static final int ROOM_HEIGHT = 10;
	static final int WALL_AMOUNT = 20;
	
	/**
	 * This method is designed to correctly initialize any room array with any dimension,
	 *  to include any number of walls, and will make use of the provided random number 
	 *  generator to determine where those walls should be.
	 * @param room
	 * @param numberOfWalls
	 * @param randGen
	 * @return null
	 */
	public static void placeWalls(char[][] room, int numberOfWalls, Random randGen) {
		//set the value for empty/occupied cell here
		
		boolean wallsSet = false;
		int rowToPlace, columnToPlace;
		int wallsPlaced = 0;
		
		//retrieve basic dimensions of the room
		int roomHeight = room.length;
		int roomWidth = room[0].length;
		//fill the room as if it were empty
		for (int i = 0; i < roomHeight; i++) {
			for (int j = 0; j < roomWidth; j++) {
				room[i][j] = EMPTY_CHAR;
			}
		}
		
		//place walls
		if (numberOfWalls == 0) {
			return;
		} else {
			do {
				//determine in which position to place a wall
				rowToPlace = randGen.nextInt(roomWidth);
				columnToPlace = randGen.nextInt(roomHeight);
				//check if there is wall present at such position
				if (room[columnToPlace][rowToPlace] == WALL_CHAR) 
					//if true, jump to the next cycle without
					//placing a wall or changing numbers
					continue;
				else {
					//place a wall
					room[columnToPlace][rowToPlace] = WALL_CHAR;
					wallsPlaced++;
				}
				//check if number of walls meets the desired value
				if (wallsPlaced == numberOfWalls) {
					wallsSet = true;
				}
			} while (!wallsSet); 	
		}
		return;
	}
	
	/**
	 * This method is to randomly place cheese into the room
	 * @param cheesePositions
	 * @param room
	 * @param randGen
	 */
	public static void placeCheeses(int[][] cheesePositions, char[][] room, Random randGen) {
		int numCheese = cheesePositions.length;
		int roomHeight = room.length;
		int roomWidth = room[0].length;
		int rowToPlace, columnToPlace;
		int cheeseLeft = numCheese;
		int index = 0;
		
		//if there are still cheese left
		while (cheeseLeft != 0) {
			//determine in which position to place a wall
			rowToPlace = randGen.nextInt(roomWidth);
			columnToPlace = randGen.nextInt(roomHeight);
			//determine if there are any objects present at such position
			if ((room[columnToPlace][rowToPlace] == EMPTY_CHAR) &&
					(!detectCheese(cheesePositions, rowToPlace, columnToPlace))){
				cheeseLeft --;
				//record the position of cheese in cheesePositions[][]
				cheesePositions[index][0]= rowToPlace;
				cheesePositions[index][1]= columnToPlace;
				index++;
			}
		}
		return;
	}
	
	/**
	 * This method is a sub-method used in printRoom() to determine if a certain position 
	 * contains a cheese.
	 * 
	 * @param row
	 * @param col
	 * @return hasCheese
	 */
	public static boolean detectCheese(int [][] cheesePositions, int row, int col) {
		boolean hasCheese = false;
		for (int i = 0; i < cheesePositions.length; i++) {
			//if matches found
			if ((cheesePositions[i][0] == row ) && (cheesePositions[i][1] == col)) {
				hasCheese = true;
				break;
			}
		}
		return hasCheese;
	}
	
	/**
	 * This method functions to visualize the randomly positioned contents 
	 * within the room
	 * @param room
	 * @param cheesePositions
	 * @param mouseX
	 * @param mouseY
	 */
	public static void printRoom(char[][] room, int[][] cheesePositions, 
			int mouseX, int mouseY) {		
		for (int i = 0; i < room.length; i++) {
			for (int j = 0; j < room[0].length; j++) {
				
				//print characters
				//Priority 1: Mouse
				if ((j == mouseX) && (i == mouseY)) {
					System.out.print(MOUSE_CHAR);
				} else if (detectCheese(cheesePositions, j, i)) { //Priority 2: Cheese
					System.out.print(CHEESE_CHAR);
				} else { //Priority 3: Walls or Empty space
					System.out.print(room[i][j]);
				}
				
			}
			System.out.println();
		}
		return;
	}
	
	
	/**
	 * This method moves the mouse within the room.
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @param room
	 * @param move
	 * @return
	 */
	public static int[] moveMouse(int mouseX, int mouseY, char[][] room, char move) {
		//this Array holds the updated position of the mouse, where the first element
		//represents the x position and the second element represents the y position
		int [] mousePosition = new int[2];
		
		//convert user command to upper case and save a copy of the original command
		char oriMove = move;
		move = Character.toUpperCase(move);
		
		//calculate the updated position while scanning for errors
		switch(move) {
			case 'W':
				mouseY--;
				break;
			case 'A':
				mouseX--;
				break;
			case 'S':
				mouseY++;
				break;
			case 'D':
				mouseX++;
				break;
			default:
				//Error 1
				System.out.println("WARNING: Didn’t recognize move command: " + oriMove);
				return null; //abort method and return null
		}
		
		//check if the mouse moves out of bounds
		if ((mouseX < 0) || (mouseX >= room[0].length) || 
				(mouseY < 0) || (mouseY >= room.length)) {
			System.out.println("WARNING: Mouse cannot move outside the room.");
			return null; //return null and abort method
		} else if (room[mouseY][mouseX] == WALL_CHAR) {//if the mouse moves into a wall
			System.out.println("WARNING: Mouse cannot move into wall.");
			return null;
		}
		
		//if no errors have been detected
		mousePosition[0] = mouseX;
		mousePosition[1] = mouseY;
		return mousePosition;
	}
	
	/**
	 * This method handles the circumstances where a mouse is at the same position as a cheese.
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @param cheesePositions
	 * @return hasCheese
	 */
	public static boolean tryToEatCheese(int mouseX, int mouseY, int[][] cheesePositions) {
		boolean hasCheese = false;
		for (int i = 0; i < cheesePositions.length; i++) {
			//if matches found
			if ((cheesePositions[i][0] == mouseX ) && (cheesePositions[i][1] == mouseY)) {
				hasCheese = true;
				//update the position of this cheese to the sentinel position {-1, -1}
				cheesePositions[i][0] = -1;
				cheesePositions[i][1] = -1;
				break;
			}
		}
		return hasCheese;
	}
	

	public static void main(String[] args) {
		//initialize Scanner and Random
		Scanner sc = new Scanner(System.in);
		Random randGen = new Random();
		
		//basic variables
		int simCycles = 0;
		//variables pertaining to the mouse
		int positionArr[]= new int[2];
		int xPosition, yPosition;
		int cheeseEaten = 0;
		boolean hitWalls = false;
		boolean ateCheese = false;
		char userCmd = ' ';
		//declaring arrays
		char [][] room = new char [ROOM_HEIGHT][ROOM_WIDTH];
		int[][] cheesePositions = new int[10][2];
		
		//place walls and cheeses
		placeWalls(room, WALL_AMOUNT, randGen);
		placeCheeses(cheesePositions, room, randGen);
		
		//place a mouse
		do {
			xPosition = randGen.nextInt(ROOM_WIDTH);
			yPosition = randGen.nextInt(ROOM_HEIGHT);
			//check if there is a wall present at such position
			if (room[yPosition][xPosition] == WALL_CHAR) {
				hitWalls = true;
			} else {
				hitWalls = false;
			}
		} while (hitWalls);
		
		//print out program header
		System.out.println("Welcome to the Cheese Eater simulation.\n"
				+"=======================================");
		System.out.print("Enter the number of steps for this simulation to run: ");
		simCycles = sc.nextInt();
		
		//game loop
		for (int cycle = simCycles; cycle > 0; cycle--) {
			// 1)print the room's contents
			System.out.println();
			System.out.println("The mouse has eaten " + cheeseEaten + " cheese!");
			printRoom(room, cheesePositions, xPosition, yPosition);
			// 2) ask the user to enter the next move command for the mouse
			System.out.print("Enter the next step you'd like the mouse to take (WASD): ");
			userCmd = sc.next().charAt(0);
			// 3)move the mouse according to that command
			positionArr = moveMouse(xPosition, yPosition, room, userCmd);
			//when error detected, exclude this step
			if (positionArr == null) {
				cycle++;
			} else {
				//if no errors are present, record the new position of the mouse
				xPosition = positionArr[0];
				yPosition = positionArr[1];
				//check if mouse has eaten any cheese
				ateCheese = tryToEatCheese(xPosition, yPosition, cheesePositions);
				if (ateCheese) {
					cheeseEaten++;
					ateCheese = false; //reset
				}
			}
		}
		//end of game output
		//finish printing the result from the last step of simulation
		System.out.println();
		System.out.println("The mouse has eaten " + cheeseEaten + " cheese!");
		printRoom(room, cheesePositions, xPosition, yPosition);
		System.out.println("==================================================\n" + 
				"Thank you for running the Cheese Eater simulation.");
		sc.close();
	}
}
