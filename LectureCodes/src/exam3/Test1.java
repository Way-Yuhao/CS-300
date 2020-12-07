package exam3;

public class Test1 {
	public static void main(String[] args) {
		Parent p = new Parent();
		Child c = new Child();
		p = c;
	}
}

class Parent{
	
}

class Child extends Parent{
	
}