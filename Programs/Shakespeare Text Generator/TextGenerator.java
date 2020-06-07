import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

import javax.management.InstanceNotFoundException;

public class TextGenerator {

	HashMap<String, ArrayList<String>> map;
	String sentence;
	public TextGenerator()
	{
		map = new HashMap<String, ArrayList<String>>();
		//add in your text files
		//add these text files to the map
		String[] filenames = {"hamlet.txt", "antony.txt", "julius.txt", "kinghenryv.txt", "kinglear.txt","macbeth.txt", "midsummer.txt", "othello.txt", "romeo.txt", "sonnets.txt", "tamingoftheshrew.txt"};
		for(String file: filenames)
			updateMap(textToList(file));
	}
	/**
	 * This method takes the given text file and adds the words one
	 * at a time to an ArrayList and returns that list.
	 * @param name - file
	 * @return the list of words
	 */
	public ArrayList<String> textToList(String name){
		ArrayList<String> fullText = new ArrayList<String>();
		InputStream inputFile = this.getClass().getResourceAsStream(name);
		//fileString = fileString.replaceAll("%20", " ");
		//fileString = fileString.replaceAll(Matcher.quoteReplacement("/"), Matcher.quoteReplacement("\\\\"));
		//String fileString = name;
		//File f = new File(fileString);
		Scanner sc;
			
			sc = new Scanner(inputFile);
			while(sc.hasNext()){
				fullText.add(sc.next());
			}
			
		return fullText;
	}
	/**
	 * Adds every word in the text to the HashMap map except
	 * the last word. The keys are the words and the values
	 * are the ArrayList<String> of all the words that follow that word.
	 * @param text
	 */
	public void updateMap(ArrayList<String> text){
		for(int i = 0; i < text.size()-1; i++){
			if(!map.containsKey(text.get(i)))
				map.put(text.get(i), new ArrayList<String>());
			map.get(text.get(i)).add(text.get(i+1));
		}
	}

/**
	 * Returns a string representing a sentence from the given hash map
	 */
	public String generateSentence(){
		String lastWord = getRandomFirstWord();
		sentence = lastWord;
		while(!lastWord.contains(".") && !lastWord.contains("!") && !lastWord.contains("?")){
			lastWord = nextWord(lastWord);
			sentence += (" " + lastWord);
		}
		return sentence;
	}
	/**
	 * 
	 * @param seed
	 * @return string representing a sentence from the given hash map, starting with the seed
	 */
	public String generateSentence(String seed){
		String[] seeds = seed.split(" ");
		String lastWord = seeds[seeds.length-1];
		sentence = seed;
		while(!lastWord.contains(".") && !lastWord.contains("!") && !lastWord.contains("?")){
			lastWord = nextWord(lastWord);
			sentence += (" " + lastWord);
		}
		return sentence;
	}

	/**
	 * Gets a starting word for the sentence.
	 * @return
	 */
	public String getRandomFirstWord(){
		String[] words = map.keySet().toArray(new String[map.size()]);
		String word;
		do{
			word = words[(int) (Math.random()*words.length)];
		}while(word.charAt(0)>90 || word.charAt(0)<65);
		return word;
	}

	/**
	 * Gets the next random word in the sentence.
	 * @param word - current word
	 * @return the next word
	 */
	public String nextWord(String word){
		return map.get(word).get((int) (Math.random()*map.get(word).size()));
	}

	
	
	
	
}
