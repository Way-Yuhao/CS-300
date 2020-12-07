
public class Furniture {
	private PApplet processing;
	private PImage image;
	private float[] position;
	private boolean isDragging;
	private int rotations;
	private String type;
	
	/** initializes the fields of a new bed object positioned 
	 * in the center of the display
	 * @param processing
	 */
	public Furniture( String type, PApplet processing) { 
		this.processing = processing;
		this.type = type;
		position = new float[2];
		position[0] = processing.width / 2;
		position[1] = processing.height / 2;
		image = processing.loadImage("images/" + type + ".png");
		
	}
	
	/** draws this bed at its current position
	 * 
	 */
	public void update() { 
		if (!isDragging) { //if the furniture is not being dragged
			//simply print out its position according to position recorded
			processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
		} else {
			//update the position according to mouse input
			position[0] = processing.mouseX;
			position[1] = processing.mouseY;
			//then process the image
			processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
		}
	}
	
	/** used to start dragging the bed, when the mouse is over
	 *  this bed when it is pressed
	 */
	public void mouseDown() { 
		if (isMouseOver()) {
			isDragging = true;
		} else {
			isDragging = false;
		}
	}
	
	/** used to indicate that the bed is no longer being dragged
	 * 
	 */
	public void mouseUp() { 
		isDragging = false;
	}
	/** helper method to determine whether the mouse is currently over this bed
	 * 
	 * @return
	 */
	public boolean isMouseOver() { 
		float mouseX = processing.mouseX;
		float mouseY = processing.mouseY;
		float leftEdge, rightEdge, upperEdge, lowerEdge;
		int width, height;
		
		//retrieve the width and height of the image while taking 
		//the orientation into consideration
		if (rotations % 2 == 0) { //if the furniture is placed horizontally
			width = image.width;
			height = image.height;
		} else { //if the furniture is placed vertically 
			width = image.height;
			height = image.width;
		}
		leftEdge = position[0] - width/2;
		rightEdge = position[0] + width/2;
		upperEdge = position[1] + height/2;
		lowerEdge = position[1] - height/2;
		if ((mouseX > leftEdge) && (mouseX < rightEdge)
				&& (mouseY > lowerEdge) && (mouseY < upperEdge)) {
			return true;
		} else {
			return false;
		}
	}
	
	/** 
	 * To track and update rotations
	 */
	public void rotate() { 
		rotations ++;
	}
	
	/**
	 * Getter method that returns the type of the furniture.
	 * @return
	 */
	public String getType() {
		return this.type;
	}
}
