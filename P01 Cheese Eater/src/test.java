import java.util.Arrays;
import java.util.Random;
public class test {
	public static void main(String args[]) {
		Random randGen = new Random();
		int[][] cheesePositions = new int[10][2];
		char [][] room = new char [20][10];
		Main.placeWalls(room, 10, randGen);
		Main.placeCheeses(cheesePositions, room, randGen);
		//Force modify
		cheesePositions[0][0] = 0;
		cheesePositions[0][1] = 0;
		Main.printRoom(room, cheesePositions, 1, 1);
		System.out.println(Arrays.asList(cheesePositions));
	}
}
