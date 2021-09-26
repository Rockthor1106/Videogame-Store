package model;

import datastructure.HashItem;

public class VideoGame extends HashItem<Integer, Integer>{

	private int amount;
	
	public VideoGame(int k, int v, int a) {
		super(k, v);
		amount = a;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void decreaseAmount() {
		amount--;
	}
}
