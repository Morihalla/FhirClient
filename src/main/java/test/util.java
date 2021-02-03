package test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r5.model.Resource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class util {

    // Create context (once) + create client
    public static FhirContext ctx = FhirContext.forR5();
    static String serverBase = "https://hapi.fhir.org/baseR5";
    public static IGenericClient client = ctx.newRestfulGenericClient(serverBase);

    // ActionListener Add-method
    public static void addActionListenerMethod(JButton button, JTextArea result, String text, MethodOutcome method) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText(text + method.getId());
            }
        });
    }

    public static void addActionListenerResource(JButton button, JTextArea result, String text, Resource resource) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText(text + resource.getId());
            }
        });
    }

    // Spacing between results
    public static void printDashedLine() {
        System.out.println("-".repeat(100));

    }
}
