package util;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.AllergyIntolerance;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.IdType;
import org.yaml.snakeyaml.events.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                                 List<IBaseResource> ai,
                                 IdType id) {


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
        JTextField input = new JTextField("Enter code here", 10);

        // Creating Buttons
        JButton createBtn = new JButton("Create");
        JButton readBtn = new JButton("Read");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");


        // Link actions to buttons

        // MenuBar Actions
//        TODO: Get Count + list as output
//        searchAllBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                String outputCount = "Loaded:"  + ai.size() +  " Allergies/Intolerances!";
//                JList <Object> letsTry = new JList();
//                letsTry.setListData(GeneralUtil.resultList(searchAll).toArray());
//                List resultlist = null;
//                resultlist.add(GeneralUtil.resultList(searchAll));
//                JList <String> outputLines = new JList<String>((String[]) resultlist.toArray());
//                outputList.add(String.valueOf(outputCount));
//                output.add((Component) outputList);
//                output.add((Component) resultlist);
//                outputLines.add(GeneralUtil.resultList(searchAll))
//                outputStrings.add(GeneralUtil.resultList((searchAll)));
//            }
//        });
//    searchAllBtn.addActionListener(e -> outputText.setText("Loaded " + ai.size() + " Allergies/Intolerances!\n"
//        + GeneralUtil.resultList(searchAll)));

        // TODO: Get input from user
        ActionMethods.addActionListenerMethod(createFromFileBtn,outputText,"Created from internal file: ",createFromJson);
        ActionMethods.addActionListenerMethod(createFromFileBtn,outputText,"Created from internal file: ",createFromXML);
        ActionMethods.addActionListenerMethod(createPatientBtn,outputText,"New patient created: ",createPatient);
        ActionMethods.addActionListenerMethod(createPractitionerBtn,outputText,"New Practitioner created: ",createPractitioner);

//        ActionMethods.addActionListenerMethod(createBtn, outputText, "Created: ", createMethod);
        ActionMethods.addActionListenerMethodByID(createBtn,input,outputText,"Created : ",createMethod);

        ActionMethods.addActionListenerResourceByID(readBtn,input,readMethod);

//        ActionMethods.addActionListenerResource(readBtn, outputText, "Read: ", readMethod);
        ActionMethods.addActionListenerMethod(updateBtn, outputText, "Updated: ", updateMethod);
        ActionMethods.addActionListenerMethod(deleteBtn, outputText, "Deleted: ", deleteMethod);


//        Util.uploadAction(uploadBtn);
//        Util.addActionListenerMethod(uploadBtn,outputText,"Uploaded: ",Util.uploadAction(uploadBtn));

        ActionMethods.clickLink(outputText, outputText.toString());


        panel.add(label);
        panel.add(input);
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