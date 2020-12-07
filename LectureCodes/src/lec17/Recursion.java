package lec17;

public class Recursion {
	public static void recursiveMethod() {
		recursiveMethod(); // do not call
	}

	public static double power(int base, int exp) {
		if (exp == 0) {
			return 1;
		} else if (exp == 1) {
			return base;
		} else if (exp < 0) {
			return (1.0 / (base * power(base, -exp - 1))); // JJ.MAX();
		} else {
			return base * power(base, exp - 1);
		}
	}
	
	public static int trace(int i) {
		System.out.println(i);
		if (i > 10) return i;
		if (i % 2 == 0) trace (i + 3);
		else trace(i * 2);
		System.out.println(i);
		return i;
	}

	public static void main(String[] args) {
		// recursiveMethod();
		System.out.println(power(2, -3));
		trace(3);
	}
}
