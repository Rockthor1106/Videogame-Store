package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VideoGameTest {
	
	private VideoGame videoGame;

	public void setupScenary1() {
		videoGame = new VideoGame(331, 17000, 3, 'A');
	}
	
	/*The following method test if a game actually was created using
	the methods getAmount, decreaseAmount and getRack*/
	@Test
	void testVideoGameCreation() {
		setupScenary1();
		
		assertEquals(3, videoGame.getAmount());
		videoGame.decreaseAmount();
		assertEquals(2, videoGame.getAmount());
		assertEquals('A', videoGame.getRack());
		assertEquals(331, videoGame.getKey());
		assertEquals(17000, videoGame.getValue());
	}

}
