package exam2;

class Sample {
	public static void main(String[] args) {
		traceB(3);
	}
	// we practiced and demonstrated the following trace in the JavaVisualizer:
	public static void traceA(int i) {
		System.out.println(i);
		if (i > 10)
			return;
		if (i % 2 == 0)
			traceA(i + 3);
		else
			traceA(i * 2);
		System.out.println(i);
	}

	// here is a more difficult variation for further practice:
	public static int traceB(int i) {
		System.out.println(i);
		if (i > 10)
			return i - 1;
		if (i % 2 == 0)
			i = traceB(i + 3);
		else
			traceB(i * 2);
		System.out.println(i);
		return i - 1;
	}
}
