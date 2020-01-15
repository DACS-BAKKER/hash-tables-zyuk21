public class ConcordanceController {

    static ConcordanceView V;
    static ConcordanceModel M;

    public static void main (String[]args) {
        V = new ConcordanceView();
        M = new ConcordanceModel();

//        System.out.println(M.hamletHT.get("end").get(16).location[2]);
//        for (int i = 0; i < M.hamletHT.get("shoot").size(); i++) {
//            System.out.println("Act: " + M.hamletHT.get("shoot").get(i).location[0] + " Scene: " + M.hamletHT.get("shoot").get(i).location[1] + " Line: " + M.hamletHT.get("shoot").get(i).location[2]);
//        }
    }

    public static void getLocation(String key) {
        V.lineModel.clear();

        if (M.sonnetHT.get(key) != null) {
            for (int i = 0; i < M.sonnetHT.get(key).size(); i++) {
                V.listLocation(("Sonnet: " + M.sonnetHT.get(key).get(i).location[0] + " Line: " + M.sonnetHT.get(key).get(i).location[1]));
            }
            V.information.setText("Occurrences: " + M.sonnetHT.get(key).size());
        }
        else {
            V.information.setText("Word not found");
        }

    }

    public static void showContext(int index) {
        V.context.setText(M.sonnets.get(M.sonnetHT.get(V.currentKey).get(index).location[0] - 1));
    }
}