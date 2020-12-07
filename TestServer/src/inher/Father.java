package inher;

public class Father {
	public  int length;
	
	public Father() {
		length = 18;
	}
	
	public void erect() {
		length += 5;
	}
	
	public boolean isReady() {
		if (length >= 20)
			return true;
		else 
			return false;
	}
}