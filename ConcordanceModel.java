/*
 Date: 13/01/2020
 Name: Alex Yuk
 File: ConcordanceModel
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ConcordanceModel {

    public LinkedList<HashTable<String, LinkedList<Location>>> hashTableList;
    public LinkedList<LinkedList<String>> contextList;

    public HashTable<String, LinkedList<Location>> sonnetHT;
    public HashTable<String, LinkedList<Location>> hamletHT;
    private final String PUNCTUATION = " \t\b\n\r\f\'\"\\~`!@#$%^&*()_+-=[];,./<>?:{}|_+";

    // Stores context of sonnets and Hamlet
    public LinkedList<String> sonnetContext;
    public LinkedList<String> hamletContext;

    public ConcordanceModel() {
        sonnetHT = new HashTable<>(20089);
        hamletHT = new HashTable<> (30059);

        sonnetContext = new LinkedList<>();
        hamletContext = new LinkedList<>();

        hashTableList = new LinkedList<>();
        contextList = new LinkedList<>();

        readSonnets();
        readHamlet();

        hashTableList.add(sonnetHT);
        hashTableList.add(hamletHT);

        contextList.add(sonnetContext);
        contextList.add(hamletContext);

    }

    // Reads and stored Sonnets.txt file into a hash table
    private void readSonnets() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("include/Sonnets.txt"));

            // Counters to keep track of sonnet and line counter
            int sonnetCounter = 1;
            int lineCounter = 0;
            String sonnet = "";
            String token;
            String line = br.readLine();
            while (line != null) {
                sonnet += line + "\n";
                line = line.toLowerCase();
                // Counts as a new sonnet every time an empty line is read
                if (line.equals("")) {
                    sonnetContext.add(sonnet);
                    sonnet = "";
                    sonnetCounter++;
                    lineCounter = 0;
                }

                StringTokenizer st = new StringTokenizer(line, PUNCTUATION);
                lineCounter++;
                while (st.hasMoreTokens()) {
                    token = st.nextToken();
                    // If key already exists in hash table, the location is added to the old key
                    if (sonnetHT.contains(token)) {
                        sonnetHT.get(token).add(new Location(2));
                        sonnetHT.get(token).get(sonnetHT.get(token).size() - 1).location[0] = sonnetCounter;
                        sonnetHT.get(token).get(sonnetHT.get(token).size() - 1).location[1] = lineCounter;
                    }
                    // If the key is new, the key is added to the hash table
                    else {
                        sonnetHT.put(token, new LinkedList<Location>(new Location(2)));
                        sonnetHT.get(token).get(0).location[0] = sonnetCounter;
                        sonnetHT.get(token).get(0).location[1] = lineCounter;
                    }
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Reads and stored Hamlet.txt file into a hash table
    private void readHamlet() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("include/Hamlet.txt"));

            // Counters to keep track of act, scene, and line
            int actCounter = 0;
            int sceneCounter = 0;
            int lineCounter = 0;
            String temp = "";
            String act = "";
            String token;
            String line = br.readLine();
            while (line != null) {

                // If line is empty, don't count as new line
                if (line.equals("")) {
                    line = br.readLine();
                    continue;
                }

                temp = line;
                line = line.toLowerCase();

                // If line begins with act, count new act, reset scene counter
                if (line.substring(0,3).equals("act")) {
                    hamletContext.add(act);
                    act = "";
                    actCounter++;
                    lineCounter = 0;
                    sceneCounter = 1;
                }

                // If line begins with scene, count new scene
                else if (line.substring(0,5).equals("scene")) {
                    lineCounter = 0;
                    sceneCounter++;
                }
                act += temp + "\n";


                // All lines formatted have two spaces at the front and no space at the 4th index
                if (line.substring(0,2).equals("  ") && line.charAt(4) != ' ') {
                    lineCounter++;
                }

                StringTokenizer st = new StringTokenizer(line, PUNCTUATION);
                while (st.hasMoreTokens()) {
                    token = st.nextToken();
                    if (hamletHT.contains(token)) {
                        hamletHT.get(token).add(new Location(3));
                        hamletHT.get(token).get(hamletHT.get(token).size() - 1).location[0] = actCounter;
                        hamletHT.get(token).get(hamletHT.get(token).size() - 1).location[1] = sceneCounter;
                        hamletHT.get(token).get(hamletHT.get(token).size() - 1).location[2] = lineCounter;
                    }
                    else {
                        hamletHT.put(token, new LinkedList<Location>(new Location(3)));
                        hamletHT.get(token).get(0).location[0] = actCounter;
                        hamletHT.get(token).get(0).location[1] = sceneCounter;
                        hamletHT.get(token).get(0).location[2] = lineCounter;
                    }
                }
                line = br.readLine();
            }

            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
