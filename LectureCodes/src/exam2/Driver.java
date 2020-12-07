package exam2;
class Container {
	private Object field;
	public Container(Object value) { field = value;}
	public Object get() { return field;}
}
public class Driver {
	public static void main(String[] args) {
		Container c = new Container("string");
		Object o = c.get();
		if (o instanceof String) {
			String s = (String)o;
			System.out.println(s.length());
		}
	}
}
