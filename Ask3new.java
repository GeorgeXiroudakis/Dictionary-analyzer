package A13;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Arrays;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Ask3{
	public static void main(String[] args){
		String filePath = null;

		System.out.println("Please give the path of the dictionary.");
		try {
			Scanner scan = new Scanner(System.in);
			filePath = scan.nextLine();
		}catch (Exception e) {
			System.out.println("Problem in scanning");
			System.exit(1);
		}

		//start timing
		long StartTime = System.currentTimeMillis();
		
		//we can definitely get away with not having this array and running throw the words only once 
		//but i that way the console message is formated better(same with the palidromik words)
		ArrayList<String> words = new ArrayList<String>();
		try {
			FileInputStream f = new FileInputStream(filePath);
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()){
				words.add(scan.nextLine());
			}
		}catch(Exception e){
			System.out.println(e);
		}
		long SumOfLetters = 0;
		int SumOfPalidromikWords = 0;
		ArrayList<String> Palidromik = new ArrayList<String>();
		
		for (int i = 0; i < words.size(); i++){
			SumOfLetters += words.get(i).length();
			if(Ask3.isPalindromikiFrash(words.get(i))){
				SumOfPalidromikWords++;
				Palidromik.add(words.get(i));
			}
		}
		//Lodging results...
		System.out.println("-The Dictionery had " + words.size()+" words." + "\n");
		System.out.println("-The avarege size of the words was: " + SumOfLetters / words.size() + "\n");
		System.out.println("-There were " + SumOfPalidromikWords + " Palindromik words, and they are the folloing:");
		for(int i = 0; i < Palidromik.size(); i++){
			System.out.println(">" + Palidromik.get(i));
		}
		System.out.printf("\n\n-The %f%% of the words was Palindromik.\n\n",( SumOfPalidromikWords/(float)words.size()) * 100);
		//end timer, convert time it took in secs, print message
		long EndTime = System.currentTimeMillis();
		float sec = (EndTime - StartTime) / 1000F; 
		System.out.println("\nThe analysis took " + sec + " seconds to complete.");
	}


	public static boolean isPalindromikiFrash(String s){
		
		
		//normalize string to remove the accents in case of a Greek sentence
		s = Normalizer.normalize(s, Form.NFD). 
		replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		
		
		//convert to lower case to ignore the case for the comparison
		s = s.toLowerCase();
		
		//only keep the Greek and English chars and numbers
		s = s.replaceAll("[^a-zα-ω0-9]", ""); //if needed exclude numbers too
		s = s.replaceAll("ς", "σ"); 
		
		//create the char arrays to compare
		char[] normal = new char[s.length()];
		char[] reverse = new char[s.length()];

		//pass the according chars to the arrays
		for( int i = 0; i < s.length(); i++) {
			normal[i] = s.charAt(i); 
			reverse[s.length() - 1 -  i] = s.charAt(i);
		}
		
		//compare the arrays 
		//return accordingly
		if(Arrays.equals(normal, reverse))return true;
		return false;
		
	}
}
