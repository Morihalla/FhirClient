import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.okhttp.client.OkHttpRestfulClientFactory;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.AllergyIntolerance;
import org.hl7.fhir.r5.model.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) throws IOException {

        // Create context for use of Ok Http + create client
        FhirContext ctx = FhirContext.forR5();
        ctx.setRestfulClientFactory(new OkHttpRestfulClientFactory(ctx));

        String serverBase = "https://hapi.fhir.org/baseR4";
        IGenericClient client = ctx.newRestfulGenericClient(serverBase);
        client.setEncoding(EncodingEnum.XML);
        client.setPrettyPrint(true);

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
            System.out.println(ai.getIdElement().getValueAsString());
        }

        printDashedLine();
        CRUDmethods.read();
        printDashedLine();
        CRUDmethods.delete();
        printDashedLine();
        CRUDmethods.create();
        printDashedLine();
        CRUDmethods.update();

    }

    public static void printDashedLine() {
        System.out.println("-" .repeat(100));
    }


}
