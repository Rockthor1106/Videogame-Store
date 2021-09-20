package model;

public class Rack<K, V> {
	
	private char letterId;
	private int height; //It refers to how tall a shelf will be in real life
	private int gamesAmount;
	private K key;
	private V value;
	private String[] videoGames;
	
	public Rack(char letterId, int height) {
		this.letterId = letterId;
		this.height = height;
		videoGames = new String[height];
	}
	
	public char getLetterId() {
		return letterId;
	}
	
	public int getGamesAmount() {
		return gamesAmount;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
	
	public String[] getVideoGames() {
		return videoGames;
	}

	private int hashFuction(K key) {
		int index = 0;
		index = key.hashCode() % height; // & 0x7fffffff it keep k's value positive
		return index;
	}
	
	public void addGameInRack(K key, V value) {
		if (videoGames[hashFuction(key)] == null) {
			videoGames[hashFuction(key)] = value.toString();
		}
		else {
			for (int i = 0; i < videoGames.length; i++) {
				if (videoGames[i] == null) {
					videoGames[i] = value.toString();
				}
			}
		}
		
	}
	
}
