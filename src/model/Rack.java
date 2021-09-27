package model;

import datastructure.HashTable;

public class Rack extends HashTable<VideoGame, Integer, Integer>{

	private char letterId;
	
	public Rack(char letter,int games) {
		super(games);
		letterId = letter;
	}
	
	public char getLetterId() {
		return letterId;
	}

	//If contains a game (code)
	public boolean containsKey(int key) {
		if(this.table.isEmpty()) {
			return false;
		}else {
			boolean found = false;
			for(int i = 0; i<size && !found; i++) {
				if(table.get(i).getKey() == key) {
					found = true;
				}
			}
			return found;
		}
	}
	
	//Get the game by his code
	public VideoGame getItem(int key) {
		boolean found = false;
		VideoGame game = null;
		for(int i = 0; i<table.size()-1 && !found; i++) {
			if((Integer)this.table.get(i).getKey() == key) {
				found = true;
				game = this.table.get(i);
			}
		}
		return game;
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
