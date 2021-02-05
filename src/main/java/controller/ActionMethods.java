package controller;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.r5.model.AllergyIntolerance;
import org.hl7.fhir.r5.model.IdType;
import org.hl7.fhir.r5.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public interface ActionMethods {


    // ActionListener Add-method
    static void addActionListenerMethod(JButton button, JLabel result, String text, MethodOutcome method, JTextField id) {
        button.addActionListener(e -> result.setText(text + id.getText()));
        method.setId(new IdType(id.getText()));
        ActionMethods.addMouseAction(button, method.getId().toString());
    }

    static void addActionListenerMethodByID(JButton button, JTextField input, JLabel result, String text, MethodOutcome method) {
        button.addActionListener((new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IdType id = new IdType(input.getText());
                method.setId(id);
                result.setText(text + method.getId().toString());
                ActionMethods.addMouseAction(button, method.getId().toString());
            }
        }));
    }

    static void addActionListenerResourceByID(JButton button, JTextField input, JLabel result, String text, Resource resource) {
        IdType id = new IdType(input.getText());
        //TODO: get output
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText(text + id.toString());
                String url = (Init.SERVER_BASE.concat("/AllergyIntolerance/" + id));
                ActionMethods.addMouseAction(button, url);

//                button.addActionListener(e -> result.setText(text + resource.getId()));
//                .addMouseAction(button,resource.getId());


            }
        });
        }

    static void addActionListenerResource(JButton button, JLabel result, String text, Resource resource) {
        button.addActionListener(e -> result.setText(text + resource.getId()));
        ActionMethods.addMouseAction(button, resource.getId());
    }

    static void addMouseAction(JButton click, String link) {
        click.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(link));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // the mouse has entered the label
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }
        });
    }

    static void clickLink(JLabel click, String link) {
        click.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(link));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // the mouse has entered the label
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // the mouse has exited the label
            }
        });
    }

//    public static MethodOutcome uploadAction(JButton loadBtn) {
//        IParser jsonParser = (IParser) Util.ctx.newJsonParser();
//        jsonParser.setPrettyPrint(true);
//        loadBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser fileChooser = new JFileChooser();
//                int returnValue = fileChooser.showOpenDialog(null);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = fileChooser.getSelectedFile();
//                }
//            }
////                        Desktop.getDesktop().browseFileDirectory(file = selectedFile);
//        });
//        return client.create()
//                .resource(upload.toString())
//                .execute();
//    }
}


