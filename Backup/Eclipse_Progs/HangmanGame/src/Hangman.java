import java.io.File;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
//import for hashset
import java.util.*;

public class Hangman implements HangmanManager {
	/**
	 * dictionary contains words
	 * length is the length of the word that needs to be searched
	 * max is number of wrong guesses allowed
	 */
	String currentWord;
	public List<String> dictionary;
	public List<String> usedWords;
	public int length = 0;
	public int max = 0;
	int finalMax = 0;

	Hangman(List<String> dictionary,int length, int max){
		this.dictionary = dictionary;
		this.length = length;
		this.max = max;
		this.finalMax = max;
	}
	
	 /**
     * Access the set of candidate words;
     *     if size == 1, contents are the actual goal word.
     * @return the goal word or the candidate goal words
     */
	public Set<String> words() {
		Set<String> candidateWords = new TreeSet<String>();
		for (String i: dictionary){
			if(i.length() == length){
				candidateWords.add(i);
			}
		}
		return candidateWords;
		
	}
	
	public String getWord(){
		String candidate = words().iterator().next();
		/*for(String i: usedWords){
			if(candidate == i && words().iterator().hasNext()){
				candidate = words().iterator().next();
			}
			else{
				return candidate;
			}
		}*/
		
		return candidate;
	}
    
    /**
     * Access the limit on wrong guesses.
     * @return the number of wrong guesses that results in a player loss
     */
    public int wrongGuessLimit(){
    	return finalMax;
    }

    /**
     * Access the number of wrong guesses that result in a loss
     *     for the player given the current state of the game.
     * @return the number of wrong guesses that would result in a loss
     */
    public int guessesLeft(){ 
    	int guessesRemaining = max;
    	return guessesRemaining;
    }

    /**
     * Access the set of letters already guessed by the player.
     * @return the set of letters guessed by the player
     */
    Character currentGuess;
    SortedSet<Character> usedGuesses = new TreeSet<Character>();
    
    public SortedSet<Character> guesses(){
    	int occurances = 0;
    	for(Character i : usedGuesses){
    		if(i == currentGuess){
    			occurances++;
    		}
    	}
    	if(occurances == 0){
    		usedGuesses.add(currentGuess);
    	}
    	return usedGuesses;
    }
    
   

    /**
     * Return the hangman-style display pattern of letters and dashes
     * (with interpolated spaces) appropriate to the current state
     * based on the letters already guessed and the goal.
     * @throws IllegalStateException if there is no goal word
     * @return the hangman-style pattern to be displayed to the user
     */
    public String pattern(){
    	
    	
    	return createDashPattern();

    	
    }
  public String createDashPattern(){
    	String dashPattern = " ";
    	for(int i = 0; i< currentWord.length()-1; i++){
    		if(currentWord.charAt(i) == ' '){
    			dashPattern += " ";
    		}
    		else{
    			dashPattern += "-";
    		}
    	}
    	return dashPattern;
    }
  /*  public String insertCharAt(){
    	String currentPattern = createDashPattern();
    	for(int i =0; i < currentWord.length()-1; i++){
    		if(currentWord.charAt(i)== currentGuess){
    			currentPattern.charAt(i) = currentGuess;
    			
    		}
    	}
    	return currentPattern;
    }*/

    /**
     * Record state changes based on new letter guess.
     * @throws IllegalStateException if no guesses left or no goal word
     * @throws IllegalArgumentException if letter is already guessed
     * @param guess: the letter being guessed
     *   [Precondition: guess must be lower-case letter]
     *   [Precondition: guess must not be among letters already guessed]
     * @return the number of occurrences of the guessed letter in the goal
     */
    public int record(char guess){
    	currentGuess = (Character)guess;
    	guesses();
    	int i = 0;
    	int occurances = 0;
    	currentWord = getWord();
    	char currentChar = guess;
    	
    	for(i = 0; i < currentWord.length() - 1; i++){	
    		if(currentWord.charAt(i) == currentChar){
    			occurances++;
    		}
    	}
    	if(occurances == 0){
    		max = max -1;
    	}
    	return occurances;
    }
    

}







