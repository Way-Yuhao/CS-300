
public class CreateSofaButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	 
	private PApplet processing;
	private float[] position;
	private String label = "Create Sofa";
	float leftEdge, rightEdge, upperEdge, lowerEdge;
	 
	/**
	 * This method creates a button with given parameters.
	 * @param x
	 * @param y
	 * @param processing
	 */
	public CreateSofaButton(float x, float y, PApplet processing) {
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
	 * This method returns a new object of Furniture of type sofa
	 * @return
	 */
	public Furniture mouseDown() { 
		return new Furniture("sofa", processing); //FIXME
	} // After step 10, this method will instead return Furniture
	
	/**
	 * This method functions to check if the mouse if hovering the button.
	 * @return
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
	
	
}
