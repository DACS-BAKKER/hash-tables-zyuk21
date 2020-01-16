/*
 Date: 13/01/2020
 Name: Alex Yuk
 File: ConcordanceController
 */

public class ConcordanceController {

    private static ConcordanceView V;
    private static ConcordanceModel M;
    
    private int currentFile;

    public static void main (String[]args) {
        // Creating instances of View and Model to interact between them
        V = new ConcordanceView();
        M = new ConcordanceModel();
    }

    // Lists location of all occurrences of Key on graphic
    public void getLocation(String key) {
        V.lineModel.clear();

        if (M.hashTableList.get(currentFile).get(key) != null) {
            for (int i = 0; i < M.hashTableList.get(currentFile).get(key).size(); i++)
                V.listLocation((M.hashTableList.get(currentFile).get(key).get(i).toString()));
            V.information.setText("Occurrences: " + M.hashTableList.get(currentFile).get(key).size());
        }
        else
            V.information.setText("Word not found");
    }

    // Shows context of specific choice on occurrences
    public void showContext(int index) {
        if (currentFile == 0)
            V.context.setText(M.sonnetContext.get(M.sonnetHT.get(V.currentKey).get(index).location[0] - 1));
        else
            V.context.setText(M.hamletContext.get( M.hamletHT.get(V.currentKey).get(index).location[0]));
    }


    public void changeFile(int index) {
        currentFile = index;
    }
}