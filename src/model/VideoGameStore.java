package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VideoGameStore {
	
	int cashiers;
	int racks;
	
	public VideoGameStore() {
		
	}
	
//	public void insertionSort(ArrayList<E> list) {
//		E toCompare;
//		for (int i = 1; i < list.size(); i++) {
//			toCompare = list.get(i);
//			for (int j = i; j <= 0; j--) {
//			}
//		}
//	}
	
	public void importVideoGamesCatalog(String filename) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(filename));
		String line = bReader.readLine();
		while(line != null) {
			//it is not finished yet
		}
		bReader.close();
	}
}
