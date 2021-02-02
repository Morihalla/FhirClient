package test;

import javax.swing.*;
import java.awt.*;

public class gui{
    public static void initFrame(){

        //Creating the Frame
        JFrame frame = new JFrame("FHIR-client");
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("File");
        mb.add(file);
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save as");
        file.add(open);
        file.add(save);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Entry");
        JTextField tf = new JTextField("Enter code here",10);
        tf.getNavigationFilter();
        JButton create = new JButton("Create");
        JButton read = new JButton("Read");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(create);
        panel.add(read);
        panel.add(update);
        panel.add(delete);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH,mb);
//        frame.getContentPane().add(BorderLayout.AFTER_LAST_LINE,ta);
//
        JLabel rawCodeLabel = new JLabel("Raw code");
        frame.getContentPane().add(BorderLayout.CENTER,rawCodeLabel);
//
//        JTextField rawCodeEntry = new JTextField();
//        rawCodeEntry.setBounds(141, 7, 376, 22);
//        frame.getContentPane().add(BorderLayout.AFTER_LAST_LINE,rawCodeEntry);
//        rawCodeEntry.setColumns(10);
//        rawCodeEntry.setEditable(false);
//        frame.getContentPane().add(BorderLayout.AFTER_LAST_LINE,rawCodeEntry);
//
        frame.getContentPane().add(BorderLayout.SOUTH,panel);
        frame.setVisible(true);


    }
}