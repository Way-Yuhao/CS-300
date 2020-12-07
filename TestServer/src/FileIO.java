import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class FileIO {
	public static void main(String[] args) {
		File file = new File("tata.txt");
		try {
		PrintWriter fout = new PrintWriter(new File("output.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("bruh");
		}
	}
}
