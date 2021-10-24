package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VideoGameStoreTest {

	private VideoGameStore videoGameStore;
	
	public void setupScenary1() {
		videoGameStore = new VideoGameStore();
		
		Rack rack1 = new Rack('A', 4);
		Rack rack2 = new Rack('B', 5);
		Rack rack3 = new Rack('C', 2);
		
		videoGameStore.setRacksNumber(3);
		
		//Catalog
		rack1.addItem(new VideoGame(331, 17000, 3, 'A'), 331);
		rack1.addItem(new VideoGame(465, 60000, 6, 'A'), 465);
		rack1.addItem(new VideoGame(612, 80000, 2, 'A'), 612);
		rack1.addItem(new VideoGame(971, 70000, 6, 'A'), 971);
		
		rack2.addItem(new VideoGame(441, 30000, 3, 'B'), 441);
		rack2.addItem(new VideoGame(112, 22000, 6, 'B'), 112);
		rack2.addItem(new VideoGame(229, 28000, 6, 'B'), 229);
		rack2.addItem(new VideoGame(281, 38000, 2, 'B'), 281);
		rack2.addItem(new VideoGame(333, 43000, 6, 'B'), 333);
		
		rack3.addItem(new VideoGame(767, 40000, 2, 'C'), 767);
		rack3.addItem(new VideoGame(287, 65000, 6, 'C'), 287);

		videoGameStore.getRacks().add(rack1);
		videoGameStore.getRacks().add(rack2);
		videoGameStore.getRacks().add(rack3);

	}
	
	@Test
	void testGetVideoGameMethod() {
		setupScenary1();
		assertEquals(441, videoGameStore.getVideoGame(441).getKey());
		assertEquals(30000, videoGameStore.getVideoGame(441).getValue());
		assertEquals('B', videoGameStore.getVideoGame(441).getRack());
		/*The expected value is 0 because we are getting the game, 
		then the amount of that game within the rack is decreased*/
		assertEquals(0, videoGameStore.getVideoGame(441).getAmount());
	}

	@Test
	void testOrderListMethod() {
		setupScenary1();
		/*The boolean value in orderList method corresponds with the type of sorting
		true to use bubble sort
		false to use insertion sort*/
		assertArrayEquals(new int[] {612, 465, 333, 287}, new int[] {videoGameStore.orderList(new int[] {612, 333, 287, 465}, true)[0].getKey(),videoGameStore.orderList(new int[] {612, 333, 287, 465}, true)[1].getKey(), videoGameStore.orderList(new int[] {612, 333, 287, 465}, true)[2].getKey(), videoGameStore.orderList(new int[] {612, 333, 287, 465}, true)[3].getKey()});
	}
	
	/*I divide the test of orderList method since each time when I use .orderList within that method a game is picked, then the amount
	 * of that game within the rack is decreased and if I use assertArrayEquals after use it to test the bubble sort, then the amount
	 * of every game would be 0*/
	@Test
	void testOrderListMethod2() {
		setupScenary1();
		
		assertArrayEquals(new int[] {612, 465, 333, 287}, new int[] {videoGameStore.orderList(new int[] {612, 333, 287, 465}, false)[0].getKey(),videoGameStore.orderList(new int[] {612, 333, 287, 465}, false)[1].getKey(), videoGameStore.orderList(new int[] {612, 333, 287, 465}, false)[2].getKey(), videoGameStore.orderList(new int[] {612, 333, 287, 465}, false)[3].getKey()});
	}

}
