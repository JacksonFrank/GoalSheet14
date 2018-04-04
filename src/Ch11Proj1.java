/*
 Jackson Frank
 File: Ch11Proj1
 Purpose: To find the edit distance (Levenshtein distance) between 2 words
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ch11Proj1 implements Callback{
	
	Map<String, Set<String>> map = new HashMap<String, Set<String>>();
	int threads, threadsCompleted = 0;
	int initPrintInc;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Ch11Proj1 Ch1 = new Ch11Proj1();
		Ch1.Start();
	}
	
	public void Start() throws FileNotFoundException, InterruptedException {
		String file = "dictionary.txt";
		
		String s1, s2;
		Scanner console = new Scanner(System.in);
		System.out.print("First word: ");
		s1 = console.nextLine();
		System.out.print("Second word: ");
		s2 = console.nextLine();
		System.out.println();
		console.close();
		
		
		long startTime = System.currentTimeMillis();
		
		findEditDistance(file);
		
		
		
		
		
		long totalTime = (System.currentTimeMillis() - startTime) / 1000;
		
		//input.close();
		
		System.out.println("Done loading");
		System.out.print("Time to load: " + totalTime + " seconds");
		
	}
	
	public void findEditDistance(String file) throws FileNotFoundException, InterruptedException {
		Scanner input = new Scanner(new File("src/" + file));
		Set<String> set = new HashSet<String>();
		while(input.hasNextLine()) {
			set.add(input.nextLine());
		}
		threads = set.size();
		initPrintInc = threads / 1000;
		
	
		for(String s : set) {
			Calculate calc = new Calculate(s, set, this);
			calc.start();
			
		}
		
		
	}

	@Override
	public void getSets(Set<String> stringSet, String target) throws FileNotFoundException {
		map.put(target, stringSet);
		threadsCompleted ++;
		if(threadsCompleted % initPrintInc == 0) {
			System.out.printf("Percent Loaded: %.1f\n", ((double)threadsCompleted / (double)threads) * 100f);
			System.out.println("Threads: " + threadsCompleted + " out of " + threads);
			System.out.println();
		}
		if(threadsCompleted >= threads) {
			printToFile();
		}
	}
	
	public void printToFile() throws FileNotFoundException {
		PrintStream p = new PrintStream(new File("src/MapFile"));
		for(String s : map.keySet()) {
			p.print(s + " ");
			for(String set : map.get(s)) {
				p.print(set + " ");
			}
			p.println();
		}
	}
	
	
	
	
	
}

