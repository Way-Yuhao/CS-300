import java.util.ArrayList;
public class Person {
	
	//instance fields
	String name;
	int age;
	
	//if fields are static, accessers and mutators will still work
	//but values will be the same across all objects
	
	public Person() {
		
	}
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public static void main(String[] args) {
		ArrayList<Person> people = new ArrayList<Person>();
		
		Person p = new Person();
		p.name = "Ahmed";
		p.age = 14;
		
		Person p2 = new Person();
		p2.name = "Bianca";
		p2.age = 62;
		
		System.out.println(people.get(0).name.length()); 
		//there are three places in this line that could possible 
		//lead to a NullPointerExcpetion
		
		
	}
}
