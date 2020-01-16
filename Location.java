/*
 Date: 13/01/2020
 Name: Alex Yuk
 File: HashTable
 */

public class Location {

    // Stores locations
    public int[] location;

    private int size;

    public Location(int size) {
        location = new int[size];
        this.size = size;
    }

    public String toString() {
        if (size == 2)
            return "Sonnet: " + location[0] + " Line: " + location[1];
        if (size == 3)
            return "Act: " + location[0] + " Scene: " + location[1] + " Line: " + location[2];

        return null;
    }
}
