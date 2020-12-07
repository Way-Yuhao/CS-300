
public class Test {
	public static void main(String[] args) {
		JobLList jb = new JobLList();
		//jb.startScheduling("jb.txt");
		//System.out.println(jb.toString());
		jb.schedule(new JobNode(2.131880f, 10, 0, 4, "AA"));
		jb.schedule(new JobNode(3.34880f, 11, 0, 5, "BB"));
		System.out.println(jb.size);
		jb.clean(999);
		System.out.println(jb.toString());
		
		
		
	}
}
