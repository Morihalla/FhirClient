package test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r5.model.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class util {

    // Create context (once) + create client
    public static FhirContext ctx = FhirContext.forR5();
    static String serverBase = "https://hapi.fhir.org/baseR5";
    public static IGenericClient client = ctx.newRestfulGenericClient(serverBase);



    // ActionListener Add-method
    public static void addActionListenerMethod(JButton button, JLabel result, String text, MethodOutcome method) {
        button.addActionListener(e -> result.setText(text + method.getId().toString()));
    }

    public static void addActionListenerResource(JButton button, JLabel result, String text, Resource resource) {
        button.addActionListener(e -> result.setText(text + resource.getId()));
    }

    public static void addMouseAction (JButton click,String link) {
        click.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI(link));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }            }

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

    // Spacing between results
    public static void printDashedLine() {
        System.out.println("-".repeat(100));

    }
}
