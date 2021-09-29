package model;

public class Client {
	
	private int ID;
	private int[] wishListCode;
	private int time;
	private int toPay;
	
	private VideoGame[] gamesToBuy;
	private int games;

	public Client(int iD, int[] wishListCode, int t) {
		ID = iD;
		this.wishListCode = wishListCode;
		time = t;
		toPay = 0;
	}

	public int getID() {
		return ID;
	}

	public int getToPay() {
		return toPay;
	}
	
	public void setToPay(int tp) {
		toPay = tp;
	}
	
	public int[] getWishListCode() {
		return wishListCode;
	}

	public int getTime() {
		return time;
	}
	
	public void increaseTime(int gamesQuantity) {
		time += gamesQuantity;
	}
	
	public void setGamesToBuy(VideoGame[] gtb) {
		games = gtb.length;
		gamesToBuy = gtb;
		int pay = 0;
		for(int i = 0; i<gtb.length; i++) {
			pay += gtb[i].getValue();
		}
		setToPay(pay);
	}
	
	public VideoGame[] getGamesToBuy() {
		return gamesToBuy;
	}
	
	public int getGamesQuantity() {
		return games;
	}
	
	public void decreaseGames() {
		games--;
	}
	
	public String toString() {
		String str = "";
		str += ""+this.getID()+" "+this.getToPay()+"\n";
		for(int i = 0; i<gamesToBuy.length; i++) {
			if(i == gamesToBuy.length-1) {
				str += ""+gamesToBuy[i].getKey();
			}else str += ""+gamesToBuy[i].getKey()+" ";
		}
		return str;
	}
	
	public String toStringGames() {
		String str = "";
		for(int i = 0; i<gamesToBuy.length; i++) {
			if(i == gamesToBuy.length-1) {
				str += ""+gamesToBuy[i].getKey();
			}else str += ""+gamesToBuy[i].getKey()+"\n";
		}
		return str;
	}
	
	public String toStringGames2() {
		String str = "";
		for(int i = gamesToBuy.length-1; i>=0 ; i--) {
			if(i == 0) {
				str += ""+gamesToBuy[i].getKey();
			}else str += ""+gamesToBuy[i].getKey()+"\n";
		}
		return str;
	}
}
