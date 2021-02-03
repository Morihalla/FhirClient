package test;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.AllergyIntolerance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {

    public static void initFrame(MethodOutcome createMethod,
                                 AllergyIntolerance readMethod,
                                 MethodOutcome updateMethod,
                                 MethodOutcome deleteMethod,
                                 List<IBaseResource> ai) {


        //Creating the Frame
        JFrame frame = new JFrame("FHIR-client");
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save as");
        JButton searchAll = new JButton("Search ALL");

        file.add(open);
        file.add(save);

        mb.add(file);
        mb.add(searchAll);

        // Text Area at the Center
        JTextArea result = new JTextArea();

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Entry");
        JTextField tf = new JTextField("Enter code here", 10);
        JButton create = new JButton("Create");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("Created: " + createMethod.getId());
            }
        });
        JButton read = new JButton("Read");
        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("Read: " + readMethod.getId());
            }
        });
        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("Updated: " + updateMethod.getId());
            }
        });
        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("Deleted: " + deleteMethod.getId());
            }
        });
        searchAll.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            result.setText("Loaded " + ai.size() + " Allergies/Intolerances!");

                                        }
                                    }
        );
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(create);
        panel.add(read);
        panel.add(update);
        panel.add(delete);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, mb);

        JLabel rawCodeLabel = new JLabel("Raw code");
        frame.getContentPane().add(BorderLayout.WEST, rawCodeLabel);

        frame.getContentPane().add(BorderLayout.CENTER, result);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);

        JScrollPane scroll = new JScrollPane(result,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.add(scroll);
        frame.setVisible(true);


    }
}