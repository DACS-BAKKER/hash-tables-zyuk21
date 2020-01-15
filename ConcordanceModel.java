import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ConcordanceModel {

    public HashTable<String, LinkedList<Location>> sonnetHT;
    public HashTable<String, LinkedList<Location>> hamletHT;
    private final String PUNCTUATION = " \t\b\n\r\f\'\"\\~`!@#$%^&*()_+-=[];,./<>?:{}|_+";

    public LinkedList<String> sonnets;
    public LinkedList<String> hamlet;

    public ConcordanceModel() {
        sonnetHT = new HashTable<>(20089);
        hamletHT = new HashTable<> (30059);

        sonnets = new LinkedList<>();

        readSonnets();
        readHamlet();
    }

    private void readSonnets() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("include/Sonnets.txt"));

            int sonnetCounter = 1;
            int lineCounter = 0;
            String sonnet = "";
            String token;
            String line = br.readLine();
            while (line != null) {
                sonnet += line + "\n";
                line = line.toLowerCase();
                if (line.equals("")) {
                    sonnets.add(sonnet);
                    sonnet = "";
                    sonnetCounter++;
                    lineCounter = 0;
                }

                StringTokenizer st = new StringTokenizer(line, PUNCTUATION);
                lineCounter++;
                while (st.hasMoreTokens()) {
                    token = st.nextToken();
                    if (sonnetHT.contains(token)) {
                        sonnetHT.get(token).add(new Location(2));
                        sonnetHT.get(token).get(sonnetHT.get(token).size() - 1).location[0] = sonnetCounter;
                        sonnetHT.get(token).get(sonnetHT.get(token).size() - 1).location[1] = lineCounter;
                    }
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

    private void readHamlet() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("include/Hamlet.txt"));

            int actCounter = 0;
            int sceneCounter = 0;
            int lineCounter = 0;
            String token;
            String line = br.readLine();
            while (line != null) {

                if (line.equals("")) {
                    line = br.readLine();
                    continue;
                }

                line = line.toLowerCase();

                if (line.substring(0,3).equals("act")) {
                    actCounter++;
                    lineCounter = 0;
                    sceneCounter = 1;
                }
                else if (line.substring(0,5).equals("scene")) {
                    lineCounter = 0;
                    sceneCounter++;
                }

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
