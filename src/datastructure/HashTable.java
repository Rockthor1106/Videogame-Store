package datastructure;

import java.util.ArrayList;

public class HashTable<K, V> {
	
	int size;
	ArrayList<HashItem<K, V>> table;
	
	public HashTable(int s) {
		size = s;
		table = new ArrayList<>(s);
		for(int i = 0; i<size; i++) {
			table.add(i, null);
		}
	}
	
	public void addItem(K key, V value) {
		boolean added = false;
		for(int i = 0; i<size && !added; i++) {
			if(table.get(i) == null) {
				table.set(i, new HashItem<K, V>(key, value));
				added = true;
			}
		}
		System.out.println(table.toString());
	}

	public boolean containsKey(K key) {
		if(table.isEmpty()) {
			return false;
		}else {
			boolean found = false;
			for(int i = 0; i<size && !found; i++) {
				if(table.get(i) != null) {
					if(table.get(i).getKey().equals(key)) {
						found = true;					
					}
				}
			}
			return found;
		}
	}

	public boolean containsValue(V value) {
		if(table.isEmpty()) {
			return false;
		}else {
			boolean found = false;
			for(int i = 0; i<size; i++) {
				if(table.get(i) != null) {
					if(table.get(i).getValue().equals(value)) {
						found = true;
					}
				}
			}
			return found;
		}
	}

	public V get(K key) {
		if(table.isEmpty()) {
			return null;
		}else {
			for(int i = 0; i<size; i++) {
				if(table.get(i) != null) {
					if(table.get(i).getKey().equals(key)) {
						return table.get(i).getValue();
					}
				}
			}
			return null;
		}
	}
}
