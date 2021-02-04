package util;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r5.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public interface ActionMethods {


    // Create context (once) + create client
    FhirContext ctx = FhirContext.forR5();
    File jsonUpload = new File("C:\\Users\\diete\\IdeaProjects\\FhirClient\\example\\AllergyIntoleranceExample.JSON");
    File xmlUpload = new File("C:\\Users\\diete\\IdeaProjects\\FhirClient\\example\\AllergyIntoleranceExample.XML");
    String serverBase = "https://hapi.fhir.org/baseR5";
    IGenericClient client = ctx.newRestfulGenericClient(serverBase);


    // ActionListener Add-method
    static void addActionListenerMethod(JButton button, JLabel result, String text, MethodOutcome method) {
        button.addActionListener(e -> result.setText(text + method.getId().toString()));
        ActionMethods.addMouseAction(button, method.getId().toString());
    }

    static void addActionListenerMethodByID(JButton button, JLabel result, String text, MethodOutcome method) {
        button.addActionListener(e -> result.setText(text + method.getId().toString()));
        ActionMethods.addMouseAction(button, method.getId().toString());
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


