import java.io.*;
import java.util.*;
import java.lang.*;

/**
* Builds a dictionary and contains methods to check spelling mistakes
*
* @author Elizabeth Muirhead
* @version November 2017
*/

public class SpellDictionary{
	
	// hashset to hold the words
	HashSet<String> hashDict = new HashSet<String>();

	// string to hold word that has currently tested
	String test_str;

	public SpellDictionary(){
		// reads the file
		BufferedReader br = null;
		// keeps track of line
		String currentLine = null; 
		
		//tries to find the dictionary file
		try {
			br = new BufferedReader(new FileReader("/usr/share/dict/words"));
		}

		catch (FileNotFoundException e1){
			System.err.printf("FileNotFound");
			System.exit(-1);
		}

		//Read first line
		try{
			currentLine = br.readLine();
		}

		catch (IOException e) {
			System.err.printf("Problem reading input\n");
			System.exit(-1);
		}
		
		// keeps reading until all words are in dictionary
		while(currentLine != null){
			// adds the words to the dict
			hashDict.add(currentLine);

			// reads next line
			try{
				currentLine = br.readLine(); 
			}
			catch (IOException e) {
				System.err.printf("Problem reading input\n");
				System.exit(-1);
			}
		}
	}

	/** checks if incoming word is in hashDict */
	public Boolean checkWord(String word){
		Boolean test = hashDict.contains(word);
		return test;
	}

	/** checks if a deletion would fix the word */
	public String deletionCheck(String word){
		for(int i = 0; i<word.length(); i++){
			String test_str = word.substring(0,i)+word.substring(i+1);
			if (hashDict.contains(test_str)){
				word = test_str;
			}
		}
		return word;
	}

	/** checks if an insertion would fix word */
	public String insertionCheck(String word){
		for (int i = 0; i<word.length(); i++){
			for (int j = 65; j<=90; j++) {
				test_str = word.substring(0,i)+((char)j)+word.substring(i);
				test_str = test_str.toLowerCase();
				if (hashDict.contains(test_str)){
					word = test_str;
				}
			}
		}
		return word;
	}

	/** checks if a substitution would fix word */
	public String substitutionCheck(String word){
		for (int i = 0; i<word.length(); i++){
			for (int j = 65; j<=90; j++) {
				test_str = word.substring(0,i)+((char)j)+word.substring(i+1);
				test_str = test_str.toLowerCase();
				if (hashDict.contains(test_str)){
					word = test_str;
				}
			}
		}
		return word;
	}

	/** checks if two letters need to be swapped */
	public String transpositionCheck(String word){
		for (int i = 0; i<word.length()-1; i++){
			test_str = word.substring(0,i)+word.charAt(i+1)+word.charAt(i)+word.substring(i+2);
			if (hashDict.contains(test_str)){
				word = test_str;
			}
		}
		return word;
	}

	/** checks is word can be split into two correct words */
	public String splitCheck(String word){
		for (int i = 0; i<word.length(); i++){
			String test_str1 = word.substring(0,i);
			String test_str2 = word.substring(i);
			if (hashDict.contains(test_str1) && hashDict.contains(test_str2)){
				String returnString = test_str1+" "+test_str2;
				word = returnString;
			}
		}
		return word;
	}
}
