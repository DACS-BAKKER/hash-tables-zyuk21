import javax.swing.*;
import java.awt.*;

public class ConcordanceView extends JFrame{

    private static final int APPLICATION_WIDTH = 480;
    private static final int APPLICATION_HEIGHT = 480;

    public static void main(String[] args) {

        new ConcordanceView();
    }

    DefaultListModel lineModel;

    JTextArea context;
    JList lineList;
    JScrollPane contextPane;
    JScrollPane linePane;

    JTextField userInput;

    JTextArea information;

    JLabel instructions;

    String currentKey;

    public ConcordanceView() {

        ConcordanceController C = new ConcordanceController();

        userInput = new JTextField();
        userInput.setBounds(15, 15, (APPLICATION_WIDTH - 45) / 3, 20);

        information = new JTextArea("Enter word to search");
        information.setFont(new Font("", Font.PLAIN, 12));
        information.setBounds(18, 55, (APPLICATION_WIDTH - 45) / 3 - 3, 50);

        userInput.addActionListener(e->{
            currentKey = userInput.getText();
            C.getLocation(currentKey);
        });

        context = new JTextArea();
        context.setFont(new Font("", Font.PLAIN, 12));
        contextPane = new JScrollPane(context, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contextPane.setBounds(APPLICATION_WIDTH / 3 + 15,15,(APPLICATION_WIDTH - 45) / 3 * 2 ,APPLICATION_HEIGHT - 50);

        lineModel = new DefaultListModel();
        lineList = new JList(lineModel);
        linePane = new JScrollPane(lineList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        linePane.setBounds(15,120,(APPLICATION_WIDTH - 45) / 3,APPLICATION_HEIGHT - 155);

        lineList.addListSelectionListener(e->{
            String temp = (String) lineList.getSelectedValue();
            int index = lineList.getSelectedIndex();

            C.showContext(index);

            System.out.println(temp + "\n" + index);
        });

        setTitle("Concordance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
        getContentPane().setLayout(null);
        setVisible(true);

        add(contextPane);
        add(linePane);
        add(userInput);
        add(information);
        pack();
    }

    public void listLocation(String line) {
        lineModel.addElement(line);
    }


}
