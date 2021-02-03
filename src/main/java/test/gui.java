package test;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.AllergyIntolerance;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class gui {

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
        JButton uploadBtn = new JButton("Upload");

        file.add(open);
        file.add(save);

        mb.add(file);
        mb.add(searchAll);
        mb.add(uploadBtn);

        // Design Output
        JLabel rawText = new JLabel("Raw text");

        JTextArea output = new JTextArea();
        JLabel outputText = new JLabel();
        outputText.setForeground(Color.BLUE.darker());
        outputText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        output.add(outputText);

        //Creating the panel at bottom
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Entry");
        JTextField tf = new JTextField("Enter code here", 10);

        // Creating Buttons
        JButton createBtn = new JButton("Create");
        JButton readBtn = new JButton("Read");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");


        // Link actions to buttons
        util.addActionListenerMethod(createBtn, outputText, "Created: ", createMethod);
        util.addActionListenerResource(readBtn, outputText, "Read: ", readMethod);
        util.addActionListenerMethod(updateBtn, outputText, "Updated: ", updateMethod);
        util.addActionListenerMethod(deleteBtn, outputText, "Deleted: ", deleteMethod);

        searchAll.addActionListener(e -> outputText.setText("Loaded " + ai.size() + " Allergies/Intolerances!"));

        util.uploadAction(uploadBtn);

        util.clickLink(outputText, outputText.toString());


        panel.add(label);
        panel.add(tf);
        panel.add(createBtn);
        panel.add(readBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.WEST, rawText);
        frame.getContentPane().add(BorderLayout.EAST, output);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);

        // Scroll enabled
        JScrollPane scroll = new JScrollPane(outputText,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        frame.add(scroll);
        frame.setVisible(true);

    }
}