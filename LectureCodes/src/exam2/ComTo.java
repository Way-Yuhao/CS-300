package exam2;

public class ComTo {

	public static void main(String[] args) {
		double a = 1;
		double b = 2;
		//int result = a.compareTo(b);
		System.out.println(findMin(a, b));
	}
	
	public static <T extends Comparable<T>> T findMin(T a, T b) {
		if (a.compareTo(b) >= 0) {
			return a;
		} else {
			return b;
		}
	}
}
