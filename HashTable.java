/*
 Date: 13/01/2020
 Name: Alex Yuk
 File: HashTable
 */

public class HashTable<Key, Value> {

    private Key[] keys;
    private Value[] values;

    private int size;
    private int trueSize;

    // Used for counting probes for analysis, won't be in an actual HashTable class
    public int putCounter;
    public int hitCounter;
    public int missCounter;

    public HashTable() {
        new HashTable(2);
    }

    public HashTable(int n) {
        keys = (Key[]) new Object[n];
        values = (Value[]) new Object[n];
        size = 0;
        trueSize = n;

        // Used for counting probes for analysis, won't be in an actual HashTable class
        putCounter = hitCounter = missCounter = 0;
    }

    public void put(Key key, Value value) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % trueSize) {
            putCounter++;
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }

        putCounter++;
        keys[i] = key;
        values[i] = value;
        size++;
    }

    private int hash(Key key) {
        int hash = 0;
        for (int i = 0; i < key.toString().length(); i++) {
            hash = (hash + key.toString().charAt(i)) * 31 % trueSize;
        }
        return hash;
    }

    public int size() {
        return size;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % trueSize) {
            hitCounter++;
            if (keys[i].equals(key))
                return values[i];
        }

        return null;
    }

    // Used for counting probes for analysis, won't be in an actual HashTable class
    public Value getMiss(Key key) {
        for (int i = missHash(key); keys[i] != null; i = (i + 1) % trueSize) {
            missCounter++;
            if (keys[i].equals(key))
                return values[i];
        }

        return null;
    }

    // Used for counting probes for analysis, won't be in an actual HashTable class
    private int missHash(Key key) {
        int hash = 0;
        for (int i = 0; i < key.toString().length() - 1; i++) {
            hash = (hash + key.toString().charAt(i)) * 31 % trueSize;
        }
        return hash;
    }

    public static void main(String[]args) {
        HashTable<String, Integer> ht = new HashTable<>(2087);
        ht.put("asd", 0);
    }
}
