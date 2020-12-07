package Exam1;
import java.util.ArrayList;
public class Test3 {
	public static void main(String[] args) throws Exception{
		System.out.println(new Maple().age);
	}
}

class Tree{
	protected int age = 1;
	public Tree() {age = age + 1;}
}

class Maple extends Tree {
	public Maple() {age = age * 2;}
}
