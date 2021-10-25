package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VideoGameStore {
	
	private int cashiers;
	private int racksNumber;
	private int clients;
	
	private ArrayList<Rack> racks;
	
	public VideoGameStore() {
		racks = new ArrayList<>();
	}
	
	public void setCashiers(int cashiers) {
		this.cashiers = cashiers;
	}
	public void setRacksNumber(int racksNumber) {
		this.racksNumber = racksNumber;
	}
	public void setClients(int clients) {
		this.clients = clients;
	}
	public int getCashiers() {
		return cashiers;
	}
	public int getRacksNumber() {
		return racksNumber;
	}
	public int getClients() {
		return clients;
	}
	public ArrayList<Rack> getRacks() {
		return racks;
	}
	
	public void importVideoGamesCatalog(String filename) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(filename));
		bReader.readLine(); //this line reads just the first cell in the CSV document
		String line = bReader.readLine(); //this line reads the first rack
		racks = new ArrayList<Rack>();
		while(line != null) {
			String[] parts = line.split(" ");
			char letterId = parts[0].charAt(0);
			int gamesAmount = Integer.parseInt(parts[1]);
			Rack rack = new Rack(letterId, gamesAmount);
			for(int i = 0; i<Integer.parseInt(parts[1]); i++) {
				line = bReader.readLine();
				String[] game = line.split(" ");
				int key = Integer.parseInt(game[0]);
				int price = Integer.parseInt(game[1]);
				int amount = Integer.parseInt(game[2]);
				rack.addItem(new VideoGame(key, price, amount, letterId), key);
			}
			racks.add(rack);
			line = bReader.readLine();
		}
		bReader.close();
	}
	
	/*<b> pre: </b> The integer called key which corresponds with the identifier of a game must be exist within the video games catalog
	 * since we suppose that our client came to our store after of generate his wish list in the web page where only 
	 * was displayed video games that existed within the catalog <br>
	 * */
	public VideoGame getVideoGame(int key) {
		boolean founded = false;
		VideoGame game = null;
		for(int i = 0; i<racksNumber && !founded; i++) {
			if(racks.get(i).containsKey(key)) {
				game = racks.get(i).getItem(key);
				game.decreaseAmount();
				founded = true;
			}
		}
		if(game.getAmount() >= 0) {
			return game;
		}else return new VideoGame(0, 0, 0, '0');
	}
	
	public VideoGame[] orderList(int[] gamesList, boolean sort) {
		VideoGame[] games = new VideoGame[gamesList.length];
		int[] gamesCodes = new int[gamesList.length];
		for(int i = 0; i<gamesList.length; i++) {
			games[i] = getVideoGame(gamesList[i]);
			if(games[i] != null) {
				gamesCodes[i] = games[i].getKey();
			}else gamesCodes[i] = 0;
		}
		if(sort) {
			return bubbleSort(games);
		}else {
			return insertionSort(games);
		}
	}
	
	private VideoGame[] bubbleSort(VideoGame[] games) {
		for(int i = games.length; i>0; i--) {
			for(int j = 0; j<i-1; j++) {
				if(games[j].getRack() > games[j+1].getRack()) {
					VideoGame temp = games[j+1];
					games[j+1] = games[j];
					games[j] = temp;
				}
			}
		}
		return games;
	}
	public VideoGame[] insertionSort(VideoGame[] games) {
		for(int i = 1; i<games.length; i++) {
			for(int j = i; j>0 && games[j].getRack()<games[j-1].getRack(); j--) {
				VideoGame temp = games[j];
				games[j] = games[j-1];
				games[j-1] = temp;
			}
		}
		return games;
	}
}
