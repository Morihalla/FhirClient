package util;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.AllergyIntolerance;
import org.hl7.fhir.r5.model.Bundle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI implements GeneralUtil, ActionMethods {

    public static void initFrame(Bundle searchAll,
                                 MethodOutcome createMethod,
                                 MethodOutcome createFromJson,
                                 MethodOutcome createFromXML,
                                 MethodOutcome createPatient,
                                 MethodOutcome createPractitioner,
                                 AllergyIntolerance readMethod,
                                 MethodOutcome updateMethod,
                                 MethodOutcome deleteMethod,
                                 List<IBaseResource> ai) {


        //Creating the Frame
        JFrame frame = new JFrame("FHIR-client");
        frame.setBounds(100, 100, 600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save as");
        JButton searchAllBtn = new JButton("Search ALL");
        JButton uploadBtn = new JButton("Upload");
        JButton createFromFileBtn = new JButton("File Insert");
        JButton createPatientBtn = new JButton("New Patient");
        JButton createPractitionerBtn = new JButton("New Practitioner");




        file.add(open);
        file.add(save);

        mb.add(file);
        mb.add(searchAllBtn);
        mb.add(uploadBtn);
        mb.add(createFromFileBtn);
        mb.add(createPatientBtn);
        mb.add(createPractitionerBtn);

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

        // MenuBar Actions
        searchAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JList <String> outputStrings = new JList<String>();
                outputStrings.add("Loaded:"  + ai.size() +  " Allergies/Intolerances!",(outputText));
//                outputStrings.add(GeneralUtil.resultList((searchAll)));
            }
        });
//    searchAllBtn.addActionListener(e -> outputText.setText("Loaded " + ai.size() + " Allergies/Intolerances!\n"
//        + GeneralUtil.resultList(searchAll)));
        ActionMethods.addActionListenerMethod(createFromFileBtn,outputText,"Created from internal file: ",createFromJson);
        ActionMethods.addActionListenerMethod(createFromFileBtn,outputText,"Created from internal file: ",createFromXML);
        ActionMethods.addActionListenerMethod(createPatientBtn,outputText,"New patient created: ",createPatient);
        ActionMethods.addActionListenerMethod(createPractitionerBtn,outputText,"New Practitioner created: ",createPractitioner);

        ActionMethods.addActionListenerMethod(createBtn, outputText, "Created: ", createMethod);
//        Util.addActionListenerMethodByID(createBtn,outputText,"Created: ",createMethod);
        ActionMethods.addActionListenerResource(readBtn, outputText, "Read: ", readMethod);
        ActionMethods.addActionListenerMethod(updateBtn, outputText, "Updated: ", updateMethod);
        ActionMethods.addActionListenerMethod(deleteBtn, outputText, "Deleted: ", deleteMethod);


//        Util.uploadAction(uploadBtn);
//        Util.addActionListenerMethod(uploadBtn,outputText,"Uploaded: ",Util.uploadAction(uploadBtn));

        ActionMethods.clickLink(outputText, outputText.toString());


        panel.add(label);
        panel.add(tf);
        panel.add(createBtn);
        panel.add(readBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);

        //Adding Components to the frame.
        frame.setLayout(new BorderLayout());
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