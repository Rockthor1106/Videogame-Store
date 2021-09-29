package model;

import datastructure.HashItem;

public class VideoGame extends HashItem<Integer, Integer>{

	private int amount;
	private char rack;
	
	public VideoGame(int key, int value, int a, char r) {
		super(key, value);
		amount = a;
		rack = r;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void decreaseAmount() {
		amount--;
	}
	
	public char getRack() {
		return rack;
	}
	
}
