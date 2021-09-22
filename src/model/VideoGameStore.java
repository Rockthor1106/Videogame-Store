package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VideoGameStore {
	
	private int cashiers;
	private int racksNumber;
	private ArrayList<Rack<Integer, String>> racks = new ArrayList<Rack<Integer, String>>(racksNumber);
	private ArrayList<String> catalog = new ArrayList<String>();
	
	public VideoGameStore() {
		
	}
	
	public int getCashiers() {
		return cashiers;
	}

	public int getRacksNumber() {
		return racksNumber;
	}
	
	public ArrayList<Rack<Integer, String>> getRacks() {
		return racks;
	}
	
	public void registerRack(Rack<Integer, String> rack) {
		for (int i = 0; i < racks.size(); i++) {
			racks.add(i, rack);
		}
	}
	
//	public void insertionSort(ArrayList<E> list) {
//	E toCompare;
//	for (int i = 1; i < list.size(); i++) {
//		toCompare = list.get(i);
//		for (int j = i; j <= 0; j--) {
//		}
//	}
//}

	public void importVideoGamesCatalog(String filename) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(filename));
		bReader.readLine(); //this line reads just the first cell in the CSV document
		String line = bReader.readLine(); //this line reads the first rack
		int counter = 0;
		while(line != null) {
			String[] parts = line.split(",");
			for(int i = 0; i < parts[0].charAt(2); i++) {
				line = bReader.readLine();
				if(line != null)
				System.out.println(line);
			}
			
		}
		bReader.close();
	}
	
	public void registerRack(BufferedReader bReader, char letterId, int amountGames) throws IOException {
		Rack<Integer, String> rack = new Rack<>(letterId, amountGames);
		for (int i = 0; i < amountGames; i++) {
			String line = bReader.readLine();
//			rack.addGameInRack(line[], null);
		}
		
	}

}
