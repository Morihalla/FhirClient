import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.AllergyIntolerance;
import org.hl7.fhir.r5.model.Bundle;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {

        // Create context and client
        FhirContext ctx = FhirContext.forR5();
        String serverBase = "https://hapi.fhir.org/baseR5";
        IGenericClient client = ctx.newRestfulGenericClient(serverBase);

        //Create list
        List<IBaseResource> allergiesIntolerances = new ArrayList<>();

// Search all IA's present
        Bundle bundle = client
                .search()
                .forResource(AllergyIntolerance.class)
                .returnBundle(Bundle.class)
                .execute();
        allergiesIntolerances.addAll(BundleUtil.toListOfResources(ctx, bundle));

        //Show result
        System.out.println("Loaded " + allergiesIntolerances.size() + " Allergies/Intolerances!");
        for (IBaseResource ai : allergiesIntolerances) {
            System.out.println(ai.getIdElement().toString());
        }
    }
}
