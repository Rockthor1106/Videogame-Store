package model;

import datastructure.HashItem;

public class VideoGame extends HashItem<Integer, Integer>{

	private int amount;
	
	public VideoGame(int key, int value, int a) {
		super(key, value);
		amount = a;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void decreaseAmount() {
		amount--;
	}
}
