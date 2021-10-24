package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClientTest {

	private Client client;
	
	public void setupScenary1() {
		client = new Client(1627, new int[] {612,333,287,465},0);
	}
	
	@Test
	void testClientCreation() {
		setupScenary1();
		
		assertEquals(1627, client.getID());
		assertEquals(0, client.getToPay());
		client.setToPay(145000);
		assertEquals(145000, client.getToPay());
		assertArrayEquals(new int[] {612,333,287,465}, client.getWishListCode());
		assertEquals(0, client.getTime());
		client.increaseTime(4);
		assertEquals(4, client.getTime());
		
	}

}
