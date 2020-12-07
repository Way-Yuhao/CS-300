package exam2;

class Animal {
	public void greet(Animal animal) {
		System.out.println("<s>");
	}
}

public class Lion extends Animal {
	public void greet(Animal animal) {
		System.out.println("<g>");
	}
	public void greet(Lion lion) {
		System.out.println("<b>");
	}
	
	public static void main(String[] args) {
		Animal a = new Lion();
		a.greet(a);
	}
}
