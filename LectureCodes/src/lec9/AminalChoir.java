package lec9;

import java.util.ArrayList;

public class AminalChoir {
	public static void main(String[] args) {
		ArrayList<Singer> singers = new ArrayList();
		singers.add(new Dog());
	}
}

//class Animal {public void sing() {}}

interface Singer {
	public void sing();
	}

class Dog implements Singer {
	public void sing() {
		System.out.println("woof");
	}
}

class Cat implements Singer {
	public void sing() {
		System.out.println("moew");
	}
}

class Bird implements Singer



{
	public void sing() {
		System.out.println("tweet");
	}
}