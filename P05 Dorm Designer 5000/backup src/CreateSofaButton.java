/**
 * This class contains the fields and methods that pertain to 
 * "Create Sofa" button. The overall function of this class is to 
 * initialize a new instance of object Furniture of type sofa.
 * @author Yuhao Liu
 *
 */
public class CreateSofaButton extends Button implements DormGUI{
	 
	/**
	 * This method creates a button with given parameters.
	 * @param x the x coordinate of the center of the button
	 * @param y the y coordinate of the center of the button
	 * @param processing
	 */
	public CreateSofaButton(float x, float y, PApplet processing) {
		super(x, y, processing);
		label = "Create Sofa";
		return;
	}
	
	/**
	 * This method returns a new object of Furniture of type sofa
	 * @return a new instance of object Furniture of type "sofa"
	 */
	@Override
	public void mouseDown(Furniture[] furniture) { 
		//return new Furniture("sofa", processing); FIXME
		for (int i = 0; i < furniture.length; i++) {
			//scan for an empty slot to initialize a new bed
			if (furniture[i] == null) {
				//initiate an new instance of bad
				furniture[i] = new Furniture("sofa", processing);
				break;
			}
		}
	} 
}
