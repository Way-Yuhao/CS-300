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
public class LoadButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	 
	private PApplet processing;
	private float[] position;
	private String label = "Load Room";
	private float leftEdge, rightEdge, upperEdge, lowerEdge;
	private Scanner sc;
	 
	/**
	 * This method creates a button with given parameters.
	 * @param x the x coordinate of the center of the button
	 * @param y the y coordinate of the center of the button
	 * @param processing
	 */
	public LoadButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		//calculating the position for edges
		leftEdge = position[0] - WIDTH/2;
		rightEdge = position[0] + WIDTH/2;
		upperEdge = position[1] + HEIGHT/2;
		lowerEdge = position[1] - HEIGHT/2;
		return;
	}
	
	/**
	 * This method prints out the button when being called
	 */
	public void update() { 
		if (isMouseOver()) { //if the mouse is hovering over the button
			processing.fill(100);
			processing.rect(leftEdge, upperEdge, rightEdge, lowerEdge);
		} else { //if the mouse is not hovering over the button 
			processing.fill(200);
			processing.rect(leftEdge, upperEdge, rightEdge, lowerEdge);
		}
		//print out label
		processing.fill(0); //set the text color to black
		processing.text(label, position[0], position[1]);	
	}
	
	/**
	 * This method returns a new object of Furniture of type bed
	 * @param furniture the furniture array passed in from main 
	 * that needs to be modified.
	 * 
	 */
	public void mouseDown(Furniture[] furniture) throws FileNotFoundException{ 
		int index = 0; //used to indicate the sequence in furniture array
		String[] curLine;
		String[] furProperties;
		String type;
		float[] position = new float[2];
		int rotations;
		
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
					if (index >= 6) {
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
					furniture[index] = new Furniture(type, position, 
							rotations, processing);	
				} catch (InputMismatchException | NumberFormatException e) {
					System.out.println("WARNING: Found incorrectly formatted "
							+ "line in file: " + index);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("WARNING: Could not load room contents "
							+ "from file RoomData.ddd."); 
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
	 * This method functions to check if the mouse if hovering the button.
	 * 
	 */
	public boolean isMouseOver() { 
		float mouseX = processing.mouseX;
		float mouseY = processing.mouseY;
		
		if ((mouseX > leftEdge) && (mouseX < rightEdge)
				&& (mouseY > lowerEdge) && (mouseY < upperEdge)) {
			return true;
		} else {
			return false;
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


