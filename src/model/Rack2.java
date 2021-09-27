package model;

import datastructure.HashTable;

public class Rack2 extends HashTable<VideoGame, Integer, Integer>{

	private char letterId;
	
	public Rack2(char letter,int games) {
		super(games);
		letterId = letter;
	}
	
	public char getLetterId() {
		return letterId;
	}

	//If contains a game (code)
	@Override
	public boolean containsKey(Integer key) {
		if(this.table.isEmpty()) {
			return false;
		}else {
			boolean found = false;
			for(int i = 0; i<this.size && !found; i++) {
				if(table.get(i) != null) {
					if(table.get(i).getKey().equals(key)) {
						found = true;					
					}
				}
			}
			return found;
		}
	}

	//Get the game by his code
	@Override
	public VideoGame getItem(Integer key) {
		if(table.isEmpty()) {
			return null;
		}else {
			for(int i = 0; i<size; i++) {
				if(table.get(i) != null) {
					if(table.get(i).getKey().equals(key)) {
						return table.get(i);
					}
				}
			}
			return null;
		}
	}
	
	//Get the value of a game
	public int getValue(int key) {
		return getItem(key).getValue();
	}

	//Decrease the game amount by his code
	public void decreaseGameAmount(int key) {
		getItem(key).decreaseAmount();
	}
}
