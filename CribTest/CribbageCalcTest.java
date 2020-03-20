package CribTest;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import Cribbage.*;
import Calculators.*;

public class CribbageCalcTest {

	static List <Card> hand;
	@BeforeClass
	public static void init () {
//		Card AOfSpade;
//		Card FiveOfClub;
//		Card TenOfHeart;
//		Card KingOfDiamond;
//		Card FourOfClub;
//		Card FiveOfSpade;
		hand = new CardsForTesting().toList();
	}
	@Test
	public void testConstructor() {
		Calculator c = new Calculator (hand);
		assertThat (c, notNullValue());
		assertThat (c.getPoints(), is (0));
		assertThat (c.getNumCards(), is (6));
		Calculator d = new Calculator (new ArrayList<Card> ());
		assertThat (d, notNullValue());
		assertThat (d.getPoints(), is (0));
		assertThat (d.getNumCards(), is(0));
		
	}

}
