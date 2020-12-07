package Exam1;

public class Test4 {
	public static void main(String[] args) {
		Ring ring = new Ring();
		Jewelry  jewelry = ring;
		//System.out.println(jewelry.getPrice() + jewelry.getDescription());
		System.out.println(ring.getDescription());
	}
}

class Jewelry {
	public int getPrice() {return 50;}
	public String getDescription() {return ", " + this.getPrice();}

}

class Ring extends Jewelry{
	public int getPrice() {return 100;}
}