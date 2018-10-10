import java.io.*;
import java.util.*;
import java.lang.*;

/**
* Checks the spelling of words and corrects for common mistakes
*
* @author Elizabeth Muirhead
* @version November 2017
*/

public class SpellChecker{

	// Array to hold every word; words are of type string
	private static List<String> wordArray = new ArrayList<String>();
	
	// Holds the contents of a split line
	private static String[] tempList;
	
	// Creates the dictionry that we use to compare the words
	public static SpellDictionary spellDict = new SpellDictionary();

	/** opens and reads file, returns it as an array of strings */
	public static List<String> readFile(String fname){

		BufferedReader br = null; // reads the file
		String currentLine = null; // keeps track of line

		// opens the file
		try {
			br = new BufferedReader(new FileReader(fname));
		}
		catch (IOException e){
			System.err.printf("Problem reading file " + fname + "\n");
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

		// keeps reading lines until done
		while(currentLine != null){
			tempList = currentLine.split(" ");
			
			// splits the line on the spaces and adds them to the word array
			for (int i=0; i<tempList.length; i++){
				wordArray.add(tempList[i]);
			}

			try{
				currentLine = br.readLine();
			}
			catch (IOException e) {
				System.err.printf("Problem reading input\n");
				System.exit(-1);
			}
		}
		// returns the array of all words from the file
		return wordArray; 
	}

	
	/**
	 * main checks if the file is being read from standard input of file, builtds the
	 * array of words, checks if they’re correct, tries changing the incorrect words
	 */
	public static void main(String[] args) {

		// uses the readFile method for files
		if (args[0].contains(".txt")){ 
			wordArray = readFile(args[0]);
		}

		// else, reads from standard input
		else{
			for(int i=0; i<=args.length-1; i++){ 
				wordArray.add(args[i]);
			}
		}
		
		String word;
		// loops through every word in the wordArray
		for (String elem : wordArray){
			elem = elem.toLowerCase();
			word = elem;
			

			// if the words is not in the dictionary, it will begin the spellcheck
			if (spellDict.checkWord(elem) == false){
				
				// ArrayList to hold the possible intended words
				ArrayList<String> possibleWord = new ArrayList<String>();
				 
				// tries deletion
				word = spellDict.deletionCheck(elem);
				if (spellDict.checkWord(word)){
					possibleWord.add(word);
				}
				
				//tries insertion
				word = spellDict.insertionCheck(elem);
				if (spellDict.checkWord(word)){
					possibleWord.add(word);
				}

				//tries substitution
				word = spellDict.substitutionCheck(elem);
				if (spellDict.checkWord(word)){
					possibleWord.add(word);
				}

				// tries transposition
				word = spellDict.transpositionCheck(elem);
				if (spellDict.checkWord(word)){
					possibleWord.add(word);
				}
				
				//tries splitting
				word = spellDict.splitCheck(elem);
				if (spellDict.checkWord(word.split(" ")[0]) && spellDict.checkWord(word.split(" ")[1])) {
					possibleWord.add(word);
				}
				
				//if the checker didn't find any possible words, marks as mispelled
				if (possibleWord.size() < 1) {				
					System.out.print(word+" (MISELLED) ");
				}
				
				// else, prints the possible intended words
				else {
					System.out.print("Possible Words: ");
					for (int counter = 0; counter < possibleWord.size(); counter++) { 		      
						System.out.print(possibleWord.get(counter)+"	"); 		
					}  
					System.out.println();
				}
			}
			
			// if the word if correct, it gets printed without the tests
			else{
				System.out.println(elem+" ");
			}
		}
	}
}
