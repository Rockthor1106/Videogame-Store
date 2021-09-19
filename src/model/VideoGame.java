package model;

public class VideoGame {
	int code;
	double price;
	int amount; 
	//idk what do you think about add another attribute, the rack that each game belongs to
	
	public VideoGame(int code, double price) {
		this.code = code;
		this.price = price;
	}

	public int getCode() {
		return code;
	}

	public double getPrice() {
		return price;
	}
	
	public int getAmout() {
		return amount;
	}
	
}
