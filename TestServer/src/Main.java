import java.util.Scanner;

public class Main {
	private Scanner in;
	
	public Main(Scanner in) {
		this.in = in;
	}
	
	public int readNumber() {
		System.out.println("Please enter a number");
		if (in.hasNextInt()) {
			return in.nextInt();
		} else {
			//in.nextLine();
			//return readNumber(); //Recursion
			//return -1; Alternative approach, but may cause ambiguity
			//return Integer.MIN_VALUE;
			//return null;
			//return new MaybeInteger(false);
			
			throw new NumberFormatException("user entered a number that"
					+ "cannot be read in as an int.");
		}
	}
	
	
	public static void main(String[] args) {
		try {
		Main main = new Main( new Scanner(System.in));
		System.out.println("My number is bigger: " + (main.readNumber()));
		} catch (NumberFormatException e ) {
			System.out.println("you bum!");
		}
	}
}
