package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RackTest {
	
	private Rack rack;
	
	public void setupScenary1() {
		rack = new Rack('A', 4);
	}
	
	public void setupScenary2() {
		rack = new Rack('A', 4);
		
		rack.addItem(new VideoGame(331, 17000, 3, 'A'), 331);
		rack.addItem(new VideoGame(665, 30000, 2, 'A'), 331);
		rack.addItem(new VideoGame(251, 20000, 1, 'A'), 331);
		rack.addItem(new VideoGame(444, 90000, 10, 'A'), 331);
	}
	
	@Test
	void testRackCreation() {
		setupScenary1();
		
		assertEquals('A', rack.getLetterId());
	}
	
	
	//In this test also is tested the containsKey method
	@Test
	void testAddItemMethod() {
		setupScenary1();
		
		rack.addItem(new VideoGame(331, 17000, 3, 'A'), 331);
		rack.addItem(new VideoGame(665, 30000, 2, 'A'), 331);
		rack.addItem(new VideoGame(251, 20000, 1, 'A'), 331);
		rack.addItem(new VideoGame(444, 90000, 10, 'A'), 331);
	
		assertEquals(true, rack.containsKey(331));
		assertEquals(true, rack.containsKey(665));
		assertEquals(true, rack.containsKey(444));
		assertEquals(true, rack.containsKey(251));
		assertEquals(false, rack.containsKey(603));
	}
	
	@Test
	void testGetItem() {
		setupScenary2();
		assertEquals(331, rack.getItem(331).getKey());
		assertEquals(17000, rack.getItem(331).getValue());
		assertEquals(3, rack.getItem(331).getAmount());
		assertEquals('A', rack.getItem(331).getRack());
	}
	
	@Test
	void testGetvalue() {
		setupScenary2();
		
		assertEquals(17000, rack.getValue(331));
		assertEquals(90000, rack.getValue(444));
	}
	
	@Test
	void testDecreaseGameAmount() {
		setupScenary2();
		assertEquals(3, rack.getItem(331).getAmount());
		rack.decreaseGameAmount(331);
		assertEquals(2, rack.getItem(331).getAmount());
	}
	
}
