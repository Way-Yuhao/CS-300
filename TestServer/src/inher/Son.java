package inher;

public class Son extends Father{
	
	public Son() {
		super.length = 14;
	}
	
	@Override
	public void erect() {
		length += 1;
	}
	
	
	public boolean isHeReady() {
		erect();
		System.out.println(length);
		return false;
	}
}
