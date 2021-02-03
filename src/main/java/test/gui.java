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

        file.add(open);
        file.add(save);

        mb.add(file);
        mb.add(searchAll);

        // Design Output
        JLabel result = new JLabel();
        result.setForeground(Color.BLUE.darker());
        result.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
        util.addActionListenerMethod(createBtn, result, "Created: ", createMethod);
        util.addActionListenerResource(readBtn, result, "Read: ", readMethod);
        util.addActionListenerMethod(updateBtn, result, "Updated: ", updateMethod);
        util.addActionListenerMethod(deleteBtn, result, "Deleted: ", deleteMethod);
        searchAll.addActionListener(e -> result.setText("Loaded " + ai.size() + " Allergies/Intolerances!"));

        panel.add(label);
        panel.add(tf);
        panel.add(createBtn);
        panel.add(readBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        JLabel rawCodeLabel = new JLabel("Raw code");
        frame.getContentPane().add(BorderLayout.WEST, rawCodeLabel);
        frame.getContentPane().add(BorderLayout.CENTER, result);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);

        // Scroll enabled
        JScrollPane scroll = new JScrollPane(result,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.add(scroll);
        frame.setVisible(true);

    }
}