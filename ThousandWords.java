/*
 Date: 13/01/2020
 Name: Alex Yuk
 File: ThousandWords, HashTable Tester
 */

import java.io.*;
import java.util.ArrayList;

public class ThousandWords {

    private static ArrayList<Integer> primes = new ArrayList<>();
    private static final int NUM_PRIMES = 150;

    private static ArrayList<String> dictionary = new ArrayList<>();

    // Used to keep track of stats
    private static ArrayList<Double> putCounter = new ArrayList<>();
    private static ArrayList<Double> hitCounter = new ArrayList<>();
    private static ArrayList<Double> missCounter = new ArrayList<>();
    private static ArrayList<Double> loadCapacities = new ArrayList<>();

    public static void main(String[]args) {
        readPrimes();
        readDictionary();

        for (int i = 0; i < NUM_PRIMES; i++) {
            HashTable<String, Integer> ht = new HashTable<>(primes.get(i));
            for (int j = 0; j < dictionary.size(); j++) {
                ht.put(dictionary.get(j), 0);
            }
            for (int j = 0; j < dictionary.size(); j++) {
                ht.get(dictionary.get(j));
            }
            for (int j = 0; j < dictionary.size(); j++) {
                ht.getMiss(dictionary.get(j) + " ");
            }

            putCounter.add(Math.round((double) ht.putCounter / 1000 * 100.0) / 100.0);
            hitCounter.add(Math.round((double) ht.hitCounter / 1000 * 100.0) / 100.0);
            missCounter.add(Math.round((double) ht.missCounter / 1000 * 100.0) / 100.0);
            loadCapacities.add(Math.round((double) ht.size() / primes.get(i) * 100.0) / 100.0);
        }

        for (int i = 0; i < putCounter.size(); i++) {
            System.out.println(putCounter.get(i));
        }
        writeFile();
    }

    // The main file that the program will output. The first row are the prime numbers, second row are the put averages
    // third is the hit averages, fourth is the miss averages, and the last is the load capacities
    private static void writeFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output/Counts.txt"));
            for (int i = 0; i < NUM_PRIMES; i++) {
                bw.write(primes.get(i) + "\t" + putCounter.get(i) + "\t" + hitCounter.get(i) + "\t" + missCounter.get(i) + "\t" + loadCapacities.get(i));
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void readPrimes() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("include/PrimeNumberList.txt"));

            String line = br.readLine();
            while (line != null) {
                primes.add(Integer.parseInt(line));
                line = br.readLine();
            }

            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void readDictionary() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("include/1000WordDictionary.txt"));

            String line = br.readLine();
            while (line != null) {
                dictionary.add(line);
                line = br.readLine();
            }

            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
