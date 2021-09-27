package model;

public class Client {
	
	private int ID;
	private int[] wishListCode;
	private int time;

	public Client(int iD, int[] wishListCode, int t) {
		ID = iD;
		this.wishListCode = wishListCode;
		time = t;
	}

	public int getID() {
		return ID;
	}

	public int[] getWishListCode() {
		return wishListCode;
	}

	public int getTime() {
		return time;
	}
	
	public void increaseTime() {
		time++;
	}
}
