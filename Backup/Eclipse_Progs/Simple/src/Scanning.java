import java.util.*;
public class Scanning {
	public static void main (String[] args){
		Scanner s = new Scanner(System.in);
		Vector data;
		data = new Vector();
		System.out.println("enter some integers here");
		while(s.hasNext()){
			String st = s.next();
			data.add(st);
		}
		s.close();
		
	}

}
