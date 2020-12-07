package exam2;

public class Inheritence {
	public static void main(String[] args) {
		Parent a = new Child(); //upcast
		//Child b = new Parent(); //downcast
	}
}

class Parent <T> {
	
	public T minusOne(T para) {
		
		return null; //FIXME
	}
}

class Child extends Parent {
	
}
