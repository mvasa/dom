import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class HangmanTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void getAWordFromDictionaryTest(){
		System.out.println("Getting a word from the dictionary");
		
		//Arrange
		boolean wordRetrieved = false;
		List<String> dictionary = new ArrayList<String>();
		dictionary.add("womblemongler");
		int length = dictionary.iterator().next().length();
		int max = 6;
		Hangman dictionaryWord = new Hangman(dictionary, length,max );
		
		//Act
		wordRetrieved = dictionaryWord.words().iterator().hasNext();
		System.out.println(dictionaryWord.words().iterator().next());
		
		//Assert
		assertTrue(wordRetrieved);
		
	}
	
	@Test
	public void checkingNumberOfOccurancesInAWordTest(){
		System.out.println("\nChecking the number of occurances of a letter in a word.");
		//Arrange
		List<String> dictionary = new ArrayList<String>();
		dictionary.add("testthiswordforthets");
		int length = dictionary.iterator().next().length();
		int max = 6;
		int expectedOccurances = 5;
		int testOccurances = 0;
		Hangman dictionaryWord = new Hangman(dictionary, length,max );
		
		//Act
		testOccurances = dictionaryWord.record('t');
		System.out.println("expectedOccurances: " + expectedOccurances + 
				" testOccurances: " + testOccurances);
		
		//Assert
		assertEquals(testOccurances, expectedOccurances);
		
	}
	
	@Test
	public void checkingForGuessesLeft(){
		System.out.print("\nchecking for number of guesses left");
		//Arrange
		List<String> dictionary = new ArrayList<String>();
		dictionary.add("testthiswordforthets");
		
		int length = dictionary.iterator().next().length();
		int max = 6;
		int expectedNumberOfGuessesLeft = 2;
		Hangman dictionaryWord = new Hangman(dictionary, length,max );
	
		//Act
		dictionaryWord.record('t');
		dictionaryWord.record('a');
		dictionaryWord.record('b');
		dictionaryWord.record('b');
		dictionaryWord.record('b');
		int guessesLeft = dictionaryWord.guessesLeft();
		System.out.println("expected number of guesses left: "+ expectedNumberOfGuessesLeft +
							"\nnumber of guesses left after test: "+ guessesLeft);
		
		//Assert
		assertEquals(expectedNumberOfGuessesLeft, guessesLeft);
	}
	
	@Test
	public void checkForRightNumberOfMaxGuesses(){
		//Arrange
		List<String> dictionary = new ArrayList<String>();
		int length = 0;
		int max = 0;
		Hangman instance = new Hangman(dictionary, length, max);
		
		//Act
		int testMax = instance.wrongGuessLimit();
		
		//Assert
		assertEquals(testMax, max);
	}
	lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll
	@Test
	public void checkingForAleardyUsedGuesses(){
		System.out.println("\nchecking for used guesses");
		//Arrange
		List<String> dictionary = new ArrayList<String>();
		dictionary.add("testthiswordforthets");
		int length = dictionary.iterator().next().length();
		int max = 0;
		Hangman instance = new Hangman(dictionary, length, max);
		//Act
		instance.record('k');
		instance.record('p');
		//Assert
		assertTrue(instance.guesses().contains('p'));
		assertTrue(instance.guesses().contains('k'));
	}
}
