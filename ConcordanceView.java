/*
 Date: 13/01/2020
 Name: Alex Yuk
 File: ConcordanceView
 */

import javax.swing.*;
import java.awt.*;

public class ConcordanceView extends JFrame{

    private static final int APPLICATION_WIDTH = 480;
    private static final int APPLICATION_HEIGHT = 480;

    String currentKey;

    JTextField userInput;

    JTextArea information;

    DefaultListModel fileModel;
    JList fileList;
    JScrollPane filePane;

    DefaultListModel lineModel;
    JList lineList;
    JScrollPane linePane;

    JTextArea context;
    JScrollPane contextPane;

    public static void main(String[] args) {
        new ConcordanceView();
    }

    // Constructor sets up GUI of the program.
    public ConcordanceView() {
        ConcordanceController C = new ConcordanceController();

        userInput = new JTextField();
        userInput.setBounds(15, 15, (APPLICATION_WIDTH - 45) / 3, 20);

        userInput.addActionListener(e->{
            currentKey = userInput.getText();
            // Calls on method from controller as it needs to access information from the model
            C.getLocation(currentKey);
        });

        information = new JTextArea("Enter word to search");
        information.setFont(new Font("", Font.PLAIN, 12));
        information.setBounds(18, 55, (APPLICATION_WIDTH - 45) / 3 - 3, 30);

        context = new JTextArea();
        context.setFont(new Font("", Font.PLAIN, 12));
        contextPane = new JScrollPane(context, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contextPane.setBounds(APPLICATION_WIDTH / 3 + 15,15,(APPLICATION_WIDTH - 45) / 3 * 2 ,APPLICATION_HEIGHT - 50);

        lineModel = new DefaultListModel();
        lineList = new JList(lineModel);
        linePane = new JScrollPane(lineList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        linePane.setBounds(15,160,(APPLICATION_WIDTH - 45) / 3,APPLICATION_HEIGHT - 195);

        lineList.addListSelectionListener(e->{
            String temp = (String) lineList.getSelectedValue();
            int index = lineList.getSelectedIndex();
            C.showContext(index);
            // Calls on method from controller as it needs to access information from the model
//            System.out.println(temp + "\n" + index);
        });

        fileModel = new DefaultListModel();
        fileList = new JList(fileModel);
        filePane = new JScrollPane(fileList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        filePane.setBounds(15,100,(APPLICATION_WIDTH - 45) / 3,APPLICATION_HEIGHT - 440);
        fileList.addListSelectionListener(e->{
            String temp = (String) fileList.getSelectedValue();
            int index = fileList.getSelectedIndex();
            // Calls on method from controller as it needs to access information from the model
            C.changeFile(index);
        });

        fileModel.addElement("Sonnets");
        fileModel.addElement("Hamlet");

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
        add(filePane);
        pack();
    }

    // Adds element into the linePane list
    public void listLocation(String line) {
        lineModel.addElement(line);
    }


}
