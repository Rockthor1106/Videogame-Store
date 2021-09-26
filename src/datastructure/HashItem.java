package datastructure;

public class HashItem<K, V> {
	K key;
	V value;
	
	public HashItem(K k, V v) {
		key = k;
		value = v;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
}
