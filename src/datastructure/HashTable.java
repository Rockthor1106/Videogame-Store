package datastructure;

import java.util.ArrayList;

public abstract class HashTable<I, K, V> {
	
	protected int size;
	protected ArrayList<I> table;
	
	public HashTable(int s) {
		size = s;
		table = new ArrayList<>(s);
		for(int i = 0; i<size; i++) {
			table.add(i, null);
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public void addItem(I item, K key) {
		int index = key.hashCode() % size;
		if(table.get(index) == null) {
			table.set(index, item);
		}else {
			while(table.get(index) != null) {
				if(index < size-1) {
					index++;
				}else {
					index = 0;
				}
			}
			table.set(index, item);
		}
	}
	
	public ArrayList<I> getTable(){
		return table;
	}

	//public abstract boolean containsKey(K key);
	//public abstract I getItem(K key);
}
