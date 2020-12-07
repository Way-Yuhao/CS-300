//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P07 EXPLORING A MAZE
// Files:           StackADT.java, MazeRunnerStack.java, Maze.java, 
//					TestStack.java
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

/**
 * This class tests the implementation of MazeRunnerStack and provides a 
 * simulated setting where the program is designed to solve for and represent
 * a path from the starting to the finishing point within a maze using 
 * MazeRunnerStack.
 * @author LiuYuhao
 *
 */
public class Maze {
	//stack storing the path from starting position to the finish position
	private MazeRunnerStack path; 
	//stack that stores every element in path but in an reversed order
	private MazeRunnerStack revPath;
	private Boolean solved; //indicates if the maze has been solved
	private Boolean unsolvable; //indicates if the maze is impossible to solve
	private char[][] mazeInfo; //2d array containing the layout of the maze
	//stores the position of the starting point
	private int[] startPos = new int[2]; 
	//stores the position of the finishing point
	private int[] finishPos = new int[2];
	
	/**
	 * Default constructor of the class that takes in the mazeInfo parameter,
	 * which contains the layout of the maze.
	 * @param mazeInfo
	 */
	public Maze(char[][] mazeInfo) {
		this.path = null;
		this.solved = null;
		this.mazeInfo = mazeInfo;
		this.solved = false;
		this.unsolvable = false;
	}
	
	/**
	 * The method takes in the starting position and stores in an array.
	 * @param row
	 * @param col
	 */
	public void setStart(int row, int col) {
		startPos[0] = row;
		startPos[1] = col;
		return;
	}
	
	/**
	 * The method takes in the finishing position and stores in an array.
	 * @param row
	 * @param col
	 */
	public void setFinish(int row, int col) {
		finishPos[0] = row;
		finishPos[1] = col;
		return;
	}
	
	/**
	 * The displayMaze() method should produce an ASCII-graphics version of the maze. 
	 * It may be called before or after the start and finish positions have been set,
	 * and before or after the maze has been solved.
	 */
	public void displayMaze() {
		//check if the maze has been solved and printout the corresponding header
		if (solved) {
			System.out.println("Solution is:");
		} else if (unsolvable) {
			System.out.println("No solution could be found.");
		}
		//(A) print out the upper boundaries
		for (int col = 0; col < mazeInfo[0].length; col++) {
			System.out.print("+---");
		}
		System.out.print("+\n"); // print out the last '+' at the end of the line
		//Main sequence
		for (int row = 0; row < mazeInfo.length; row++) {
			//(B) print out vertical boundaries for each cell
			for (int col = 0; col < mazeInfo[row].length; col++) {
				//left boundary of each cell
				if ((col == 0 ) || (mazeInfo[row][col] == '|') 
						|| (mazeInfo[row][col] == 'L')){
					System.out.print("|");
				} else {
					System.out.print(" ");
				}
				//content of each cell
				if ((startPos[0] == row) && (startPos[1] == col)) {
					System.out.print(" S ");
				} else if ((finishPos[0] == row) && (finishPos[1] == col)) {
					System.out.print(" F ");
				} else if ((path != null) && (path.contains(new Position(row, col)))) {
					System.out.print(" * ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.print("|\n"); //print out the right boundary for each row
			//(C) print out the lower boundaries for each cell
			for (int col = 0; col < mazeInfo[row].length; col++) {
				if ((mazeInfo[row][col] == 'L') || (mazeInfo[row][col] == '_')) {
					System.out.print("+---");
				} else {
					System.out.print("+   ");
				}
			}
			System.out.print("+\n");
		}
		//if the maze has been solved, display the path
		if (this.solved) {
			System.out.print("Path is: ");
			loadRevPath();
			while(!revPath.isEmpty()) {
				Position pos = revPath.pop();
				System.out.print("[" + pos.row + "," + pos.col + "]");
				if (!revPath.isEmpty()) {//if the path still contains more position
					System.out.print(" --> ");
				}
			}
			System.out.println();
		} 
	}
	
	/**
	 * This method solves the maze, that is to find a path from the starting point to the
	 * finishing point without hitting a wall using the technique named "right hand rule".
	 */
	public void solveMaze() {
		path = new MazeRunnerStack();
		int row , col; //a pair of integer representing the current location
		unsolvable = false;
		//to prevent infinite loop, the method will terminate once reaching this number
		int maxStepRemaining = mazeInfo.length * mazeInfo[0].length * 4;
		//assign currentLocation with the starting point
		row = startPos[0];
		col = startPos[1];
		int directionFacing = 41; //an integer representing the direction facing
		//to interpret this variable, divide this number by 4. If the remainder is 0,  
		//then it's facing up; likewise, 1 means right, 2 means down, 3 means left.
		
		//add the starting position to the path
		updateStack(row, col, directionFacing);
		while ((!checkIfFinised(row, col)) && (maxStepRemaining >= 0)){
			maxStepRemaining--;
			switch (directionFacing % 4) { //decoding the direction facing
			case 0: //facing up
				//if the cell above does not have a lower boundary
				if ((row > 0) && (mazeInfo[row - 1][col] != 'L') 
						&& (mazeInfo[row - 1][col] != '_')) {
					row--; //move up
					directionFacing ++; //turn right
				} else {
					directionFacing --;
				}
				break;
			case 1: //facing right
				if ((col < mazeInfo[row].length - 1) && (mazeInfo[row][col + 1] != '|') &&
						(mazeInfo[row][col + 1] != 'L')) {
					col++;
					directionFacing ++; //turn right if changed location
				} else {
					directionFacing --; //turn left if encountered a wall
				}
				break;
			case 2: //facing down
				//if the current cell does not have a lower boundary
				if ((row < mazeInfo.length - 1) && (mazeInfo[row][col] != 'L')
						&& (mazeInfo[row][col] != '_')) {
					row++;
					directionFacing ++;
				} else {
					directionFacing--;
				}
				break;
			case 3: //facing left
				//if the current cell does not have a left boundary
				if ((col > 0) && ((mazeInfo[row][col]) != 'L') && 
						(mazeInfo[row][col]) != '|') { 
					col--;
					directionFacing ++;
				} else {
					directionFacing --;
				}
				break;
			default:
				break;
			}
			updateStack(row, col, directionFacing); //update the path stack
		}
		//after while loop, check if the maze has been solved
		if (maxStepRemaining == -1) { //if the maze is impossible to solve
			path = null;
			unsolvable = true;
		} else {
			this.solved = true;
		}
	}
	
	/**
	 * Helper method that checks if the object has reached the finishing point
	 * at a given row and column.
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean checkIfFinised(int row, int col) {
		if ((row == finishPos[0]) && (col == finishPos[1])) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Helper method that updates the stack and pops the repetitive steps 
	 * when necessary.
	 * @param x
	 * @param y
	 * @param direction
	 */
	private void updateStack(int x, int y, int direction) {
		Position newPos = new Position(x, y);
		
		while (path.contains(newPos)) { //if path already contains this cell
			path.pop(); //pop all steps until reaching the position before this cell
		}
		path.push(newPos); //add the new Position
	}
	
	/**
	 * Helper method that loads the path at the reversed order.
	 */
	private void loadRevPath() {
		revPath = new MazeRunnerStack();
		while (!path.isEmpty()) {
			revPath.push(path.pop());
		}
	}
	
	/**
	 * Main method that tests the implementation of the Maze class.
	 * @param args
	 */
	public static void main(String[] args) {
		//layout copied from the site
		char[][] maze1 = new char[][] {
			{'L', '.', '|'},
			{'.', '_', '.'},
			{'.', '.', '.'},
			{'L', '_', '_'},
		};
		Maze maze = new Maze(maze1);
		//set starting and finishing points
		maze.setStart(0, 2);
		maze.setFinish(2, 1);
		//display before solving the maze
		maze.displayMaze();
		maze.solveMaze();
		//display after solving the maze
		maze.displayMaze();
	} 
}
