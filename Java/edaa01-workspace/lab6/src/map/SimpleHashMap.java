package map;

public class SimpleHashMap<K, V> implements Map<K, V> {	
	private Entry<K, V>[] table;
	private static final double MAX_LOAD_FACTOR = 0.75;
	private double loadFactor;
	private int numberOfEntries = 0;
	
	public SimpleHashMap() {
		this(16);
	}
	
	public SimpleHashMap(int capacity) {
		this.table = (Entry<K,V>[]) new Entry[capacity];
		this.loadFactor = 0.75;
	}
	
	public String show() {
		StringBuilder sb = new StringBuilder();
		
		for(Entry<K, V> entry : table) {
			if(entry == null) continue;
			
			Entry<K, V> subEntry = entry;
			
			sb.append(index(subEntry.getKey()));
			sb.append(": ");
			while(subEntry != null) {
				sb.append(subEntry.toString());
				sb.append(" ");
				subEntry = subEntry.getNext();
			}
			sb.append('\n');
		}
		
		return sb.toString();
	}
	
	@Override
	public V get(Object keyObject) {
		K key = (K) keyObject;
		
		Entry<K, V> entry = find(key);
		
		if(entry == null) return null;
		
		while(!entry.getKey().equals(key)) {
			if(entry.getNext() == null) return null;
			
			entry = entry.getNext();
		}
		
		return entry.getValue();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private int index(K key) {
		// return return (key.hashCode() % table.length + table.length) % table.length;  
		return Math.abs(key.hashCode() % table.length);
	}
	
	private Entry<K, V> find(K key) {
		return find(index(key) ,key);
	}
	
	private Entry<K, V>  find(int index, K key) {
		if(table[index] == null) return null;
		
		Entry<K, V> currentEntry = table[index];
		
		while(currentEntry != null && !currentEntry.getKey().equals(key)) {
			currentEntry = currentEntry.getNext();
		}
		
		return currentEntry;
	}
	
	private void rehash() {
		Entry<K, V>[] oldTable = table;
		Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[oldTable.length * 2];
		table = newTable;
		
		numberOfEntries = 0;
		
		for(Entry<K, V> entry : oldTable) {
			if(entry == null) continue;
			
			Entry<K, V> subEntry = entry;
			while(subEntry != null) {
				put(subEntry.getKey(), subEntry.getValue());
				
				subEntry = subEntry.getNext();
			}
		}
		
		loadFactor = size() /  (double) table.length;
	}

	@Override
	public V put(K key, V value) {
		V oldValue = null;
		
		Entry<K, V> newEntry = new Entry<K, V>(key, value);
		
		int tableIndex = index(key);
		
		if(table[tableIndex] == null) {
			table[tableIndex] = newEntry;
		} else {
			Entry<K, V> subEntry = table[tableIndex];
			
			// Om den första entryn's nyckel är samma så byt ut den
			if(subEntry.getKey().equals(key)) {
				newEntry.next = subEntry.next;
				oldValue = subEntry.getValue();
				
				table[tableIndex] = newEntry;
				
				return oldValue;
			}
			
			// Gå igenom elementen tills de antingen tar slut eller att nästa element har samma nyckel
			while(subEntry.getNext() != null && !subEntry.getNext().getKey().equals(key)) {
				subEntry = subEntry.getNext();
			}
			
			// Om elementen tog slut, sätt det sista till det nya (lägg till den)
			if(subEntry.getNext() == null) {
				subEntry.next = newEntry;	
			// Om nästas nyckel är den samma som den nyas nyckel, byt ut den
			} else {
				oldValue = subEntry.getNext().getValue();
				newEntry.next = subEntry.getNext().getNext();
				subEntry.next = newEntry;
				
				return oldValue;
			}
		}
		
		numberOfEntries++;
		loadFactor = size() / (double) table.length;
		
		if(loadFactor > MAX_LOAD_FACTOR) {
			rehash();
		}
		
		return oldValue;
	}

	@Override
	public V remove(Object keyObject) {
		if(size() == 0) return null;
		
		K key = (K) keyObject;
		V oldValue = null;
		
		Entry<K, V> entry = table[index(key)];
		
		if(entry == null) return null;
		
		if(entry.getKey().equals(key)) {
			oldValue = entry.getValue();
			table[index(key)] = entry.getNext();
		} else {
			while(entry.getNext() != null && !entry.getNext().getKey().equals(key)) {
				entry = entry.getNext();
			}
			
			if(entry.getNext() == null) {
				return null;
			} else {
				oldValue = entry.getNext().getValue();
				entry.next = entry.getNext().getNext();
			}
		}
		
		numberOfEntries--;
		loadFactor = size() / (double) table.length;
		
		return oldValue;
	}

	@Override
	public int size() {
		return numberOfEntries;
	}
	
	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
		@Override
		public String toString() {
			return key.toString() + "=" + value.toString();
		}
		
		public Entry<K, V> getNext() {
			return next;
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return this.value;
		}
	}
}
