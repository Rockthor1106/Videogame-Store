package model;

public class Client {
	private int ID;
	private int wishListCode;

	public Client(int iD, int wishListCode) {
		ID = iD;
		this.wishListCode = wishListCode;
	}

	public int getID() {
		return ID;
	}

	public int getWishListCode() {
		return wishListCode;
	}

}
