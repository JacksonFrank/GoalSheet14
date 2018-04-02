
import java.util.*;
import java.io.*;

public class Calculate extends Thread{
	
	private String reference;
	private Set<String> set;
	private Set<String> stringSet;
	private Callback callback;
	
	public Calculate(String reference, Set<String> set, Callback callback) throws FileNotFoundException {
		this.reference = reference;
		this.set = set;
		this.callback = callback;
		
		stringSet = new HashSet<String>();
		
	}
	
	@Override
	public void run() {
		for(String current : set){
			if(compare(reference, current)) {
				stringSet.add(current);
			}
		}
		
		try {
			callback.getSets(stringSet, reference);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static boolean compare(String s1, String s2) {

		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		if(s1.length() < s2.length()) {
			c1 = s2.toCharArray();
			c2 = s1.toCharArray();
		}
		
		int count = 0;
		
		for(int i = 0; i < c1.length; i++) {
			if(i < c2.length) {
				if(c1[i] != c2[i]) {
					count ++;
				}
			}
			else {
				count ++;
			}
			if(count > 1) {
				return false;
			}
		}
		
		if(count == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
