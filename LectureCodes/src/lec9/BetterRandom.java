package lec9;
import java.util.Random;

public class BetterRandom extends Random{
	/*private Random random;
	
	public BetterRandom() {
		random = new Random();
	}
	
	public BetterRandom(long seed) {
		random = new Random(seed);
	}
	
	public int nextInt() {
		//to ensure that the return value is positive
		return random.nextInt(Integer.MAX_VALUE);
		//add a +1 to avoid returning 0
	}
	
	public int nextInt(int max) {
		return random.nextInt(max) +1;
	}
	
	public int nextInt(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	} */
	
	//every class needs a constructor, whether or not it has extended a class
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BetterRandom() {
		System.out.println("BetterRand");
	} //will implicitly run super()
	
	public BetterRandom(long seed) {
		//will run implicitly if not specified
		//super();
		super(seed); //has to be the very FIRST thing ***
	}
	
	@Override 
	//signals to whom is reading your code 
	//Use the compiler to help you check the signature
	//Although having @Override there is not required
	public int nextInt() {
		return this.nextInt(Integer.MAX_VALUE) + 1;
	}
	public static void main(String[] args) {
		//will generate a BetterRandom object
		Random randGen = new BetterRandom();
		//System.out.println(randGen.nextInt(1, 2));
		//will generate error, compile time error
	}
}
