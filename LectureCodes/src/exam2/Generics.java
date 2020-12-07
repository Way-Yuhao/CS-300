package exam2;

import java.util.ArrayList;

//simple class similar to those we have seen in the past
//this one implements the Comparable<Person> interface
class Person implements Comparable<Person> {
	@Override
	public int compareTo(Person other) {
		return age - other.age;
	}
	public void celebrateBirthday() { age++; }
	
	private final String NAME;
	private int age;
	public Person(String name, int age) { this.NAME = name; this.age = age; }
	public String toString() { return NAME + " (" + age + ")"; }
}

public class Generics {
	// the generic parameter type eliminates the need for explicit down-casting to return value
	// the type bound allows us to make use of the compareTo() method within references of this generic type
	public static <T extends Comparable<T>> T findEarliest(T[] objects) {
		T earliest = objects[0];
		
		for(int i=1; i<objects.length; i++)
			if( earliest.compareTo(objects[i]) > 0 )
				earliest = objects[i];
		return earliest;
	}
	
	public static void main(String[] args) {		
		Person[] people = new Person[] {
				new Person("Yolanda", 23), // this
				new Person("Zane", 19),
				new Person("Xavier", 34) }; // other
		
		// trace to review compareTo() method discussed last week
		System.out.println( people[0].compareTo( people[2] ) < 0 );
		
		// old version required explicit down-cast, but generic helps us avoid this:
		findEarliest(people).celebrateBirthday(); 
		System.out.println( findEarliest(people) );

		// we've been using generics with our arrays all along
		// leaving this generic type out, results in raw (Object) type
		ArrayList<Person> list = new ArrayList<Person>();
		for(int i=0;i<people.length;i++)
			list.add( people[i] );
		//list.add( new java.util.Random() ); // which other kinds of objects can be added to
		list.get(0).celebrateBirthday(); // and down-casting will be required with
		// but specifying the generic type helps us with both of these kinds of problems
		System.out.println( list.get(0) );

		// we also defined a new class (below) that makes use of two generic type parameters
		Pair<String,Integer> a = new Pair<String,Integer>("Veronica", 27);
		Pair<Integer,String> b = new Pair<Integer,String>(24,"Wayne"); // Note Different Types:
		Pair<Integer,String> c = b; // is OK
		//c = a; // is not OK
		
		// and we can also group more than two things within these Pairs:
		// holds three strings total: similar linked lists (topic for next week)
		Pair<String, Pair<String, Pair<String, String>>> z; 
		// holds four string: similar to binary tree (topic for later in the course)
		Pair<Pair<String,String>, Pair<String,String>> w;		
	}
}

class Pair <LeftType, RightType> {
	public LeftType left;  // first,  left, car
	public RightType right; // second, right, cdr
	public Pair(LeftType left, RightType right) { this.left = left; this.right = right; }
}