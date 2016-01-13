package datastructures;

import java.util.Iterator;

/**
 * Simple hash table implementation, which does not permit null values or
 * null keys. Collisions are handled using linked-list buckets for
 * simplicity. This implementation provides:
 * 	<li> O(1) search, insertion, and deletion on average
 * 	<li> O(n) for worst-case if hashes of keys are not distributed equally
 *
 * @param <K>
 * @param <V>
 */
public class HashTable<K, V> implements Iterable<HashTable<K, V>.Entry>
{
	private static final int NUM_BUCKETS = 16;
	private LinkedList<Entry>[] entries;
	
	public class Entry
	{
		K key;
		V value;
		
		Entry(K k, V v)
		{
			key = k;
			value = v;
		}
		
		@Override
		public boolean equals(Object o)
		{
			// Two Entry objects are equal if their keys are equal
			
			if (o == null || o.getClass() != Entry.class)
				return false;
			
			Entry other = (Entry)o;
			
			return key.equals(other.key);
		}
	}
	
	/**
	 * Standard constructor which creates a default number of buckets
	 */
	public HashTable()
	{
		this(NUM_BUCKETS);
	}
	
	/**
	 * Another constructor which allows custom specification of number of
	 * buckets.
	 * @param buckets Number of buckets in the hash table
	 */
	public HashTable(int buckets)
	{
		entries = new LinkedList[buckets];
	}
	
	private void checkForNulls(Object... objs)
	{
		for (Object o : objs)
		{
			if (o == null)
				throw new IllegalArgumentException("NULL argument detected!");
		}	
	}
	
	/**
	 * Places a new entry in the hash table structure. If the key
	 * already exists, then overwrites the entry with the new value.
	 * 
	 * @param key Key
	 * @param value Value association
	 * @throws IllegalArgumentException if either key or value is null
	 */
	public void put(K key, V value)
	{
		checkForNulls(key, value);
		int n = key.hashCode() % entries.length;
		
		if (entries[n] == null)
		{
			entries[n] = new LinkedList<>();
			entries[n].addFirst(new Entry(key, value));
		}
		else
		{
			Entry e = new Entry(key, value);
			
			if (entries[n].contains(e) == true)
				entries[n].remove(e);
			
			entries[n].addFirst(e);
		}
	}
	
	/**
	 * Removes the current key mapping in the hash table.
	 * 
	 * @param key Key to search for
	 * @return The value associated with the removed key, or NULL if the
	 * 			key was not found.
	 * @throws IllegalArgumentException if key is null
	 */
	public V remove(K key)
	{
		checkForNulls(key);
		int n = key.hashCode() % entries.length;
		
		if (entries[n] == null)
			return null;
		
		V ret = null;
		for (Entry e : entries[n])
		{
			if (key.equals(e.key) == true)
				ret = e.value;
		}
		
		if (ret == null)
			return null;
		
		entries[n].remove(new Entry(key, null));
		return ret;
	}
	
	/**
	 * Obtains the value associated with the input key.
	 * 
	 * @param key Key to search for
	 * @return The value associated with the key, or NULL if
	 * 		the key does not exist in the hash table.
	 * @throws IllegalArgumentException if key is null
	 */
	public V get(K key)
	{
		checkForNulls(key);
		int n = key.hashCode() % entries.length;
		
		if (entries[n] == null)
			return null;
		
		for (Entry e : entries[n])
		{
			if (key.equals(e.key) == true)
				return e.value;
		}
		
		return null;
	}
	
	/**
	 * Check if the key exists in the hash table.
	 * 
	 * @param key Key to search for
	 * @return true if the key exists, false if not
	 * @throws IllegalArgumentException if key is null
	 */
	public boolean contains(K key)
	{
		checkForNulls(key);
		int n = key.hashCode() % entries.length;
		
		return (entries[n] == null) ? false : entries[n].contains(new Entry(key, null));
	}
	
	/**
	 * Obtain the number of mappings that currently exist.
	 * @return Number of key-value associations
	 */
	public int size()
	{
		int size = 0;
		
		for (LinkedList<Entry> list : entries)
			size += (list == null) ? 0 : list.size();
		
		return size;
	}
	
	public Iterator<HashTable<K, V>.Entry> iterator()
	{
		LinkedList<Entry> list = new LinkedList<>();
		
		for (LinkedList<Entry> l : entries)
		{
			if (l != null)
				list.addAll(l);
		}
		
		return list.iterator();
	}
}
