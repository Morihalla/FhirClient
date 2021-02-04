package util;

import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.Bundle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface GeneralUtil {

    // Spacing between results
    static void printDashedLine() {
        System.out.println("-".repeat(100));

    }

    // Check for Json or XML
    static String getFileFormat(File file) {
        return file.getName().substring(file.getName().lastIndexOf("."));
    }

    //Create list of every AI present
    static List<String> resultList(Bundle searchAll) {

        List<String> lines = new ArrayList<>();
        String result;

        //Create list of every AI present
        List<IBaseResource> allergiesIntolerances = new ArrayList<>();
        allergiesIntolerances.addAll(BundleUtil.toListOfResources(ActionMethods.ctx, searchAll));

        //Show result
        for (IBaseResource ai : allergiesIntolerances) {
            result = ai.getIdElement().getValueAsString();
            lines.add(result + "\n");
        }
        return lines;
    }
}
