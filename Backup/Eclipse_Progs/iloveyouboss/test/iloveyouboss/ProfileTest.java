package iloveyouboss;

import static org.junit.Assert.*;

import org.junit.Before;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class ProfileTest {
	private Profile profile;
	private BooleanQuestion question;
	private Criteria criteria;
	
	@Before
	public void create(){
		profile = new Profile("Bull Hockey, Inc.");
		question = new BooleanQuestion(1, "Got Bonuses?");
		criteria = new Criteria();
	}
	
	
	@Test
	public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
		//Arrange
		Answer profileAnswer = new Answer(question, Bool.FALSE);
		profile.add(profileAnswer);
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
		criteria.add(criterion);
		
		//Act
		boolean matches = profile.matches(criteria);
		
		//Assert
		assertFalse(matches);
	}
	@Test
	public void matchAnswersTrueForAnyDontCareCriteria() {
		//Arrange
		Answer profileAnswer = new Answer(question, Bool.FALSE);
		profile.add(profileAnswer);
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
		criteria.add(criterion);
		
		//Act
		boolean matches = profile.matches(criteria);
		
		//Assert
		assertTrue(matches);
	}

}


