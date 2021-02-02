import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.okhttp.client.OkHttpRestfulClientFactory;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.AllergyIntolerance;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.IdType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) throws IOException {

        // Create context once + create client
        FhirContext ctx = FhirContext.forR5();
        ctx.setRestfulClientFactory(new OkHttpRestfulClientFactory(ctx));

        String serverBase = "https://hapi.fhir.org/baseR5";
        IGenericClient client = ctx.newRestfulGenericClient(serverBase);
        client.setEncoding(EncodingEnum.XML);
        client.setPrettyPrint(true);

        IParser parser = (IParser) ctx.newJsonParser();
        parser.setPrettyPrint(true);

        AllergyIntolerance allergyIntolerance = new AllergyIntolerance();
        allergyIntolerance.setId("example");


        //CRUD-operations
        MethodOutcome createOutcome = client.create()
                .resource(allergyIntolerance)
                .execute();

        AllergyIntolerance readOutcome = client.read()
                .resource(AllergyIntolerance.class)
                .withId("1902")
                .execute();

        MethodOutcome updateOutcome = client.update()
                .resource(allergyIntolerance.setId("new"))
                .execute();

        MethodOutcome deleteOutcome = client
                .delete()
                .resourceById(new IdType("AllergyIntolerance", "1506"))
                .execute();

        // Search all IA's present
        Bundle bundle = client
                .search()
                .forResource(AllergyIntolerance.class)
                .count(100)
                .returnBundle(Bundle.class)
                .execute();

        //Show results
        System.out.println("Created: " + createOutcome.getId());
        printDashedLine();
        System.out.println("Read: " + readOutcome.getId());
        ;
        printDashedLine();
        System.out.println("Updated: " + updateOutcome.getId());
        printDashedLine();
        System.out.println("Deleted: " + deleteOutcome.getId());

        printDashedLine();

        //Create list of every AI present
        List<IBaseResource> allergiesIntolerances = new ArrayList<>();
        allergiesIntolerances.addAll(BundleUtil.toListOfResources(ctx, bundle));

        //Show result
        System.out.println("Loaded " + allergiesIntolerances.size() + " Allergies/Intolerances!");
        for (IBaseResource ai : allergiesIntolerances) {
            System.out.println(ai.getIdElement().getValueAsString());
        }

        printDashedLine();

        //Show first result
        System.out.println("Loaded " + bundle.getTotal() + " Allergies/Intolerances!");
        System.out.println(bundle.getEntry().get(0).getResource().getId());


    }

    public static void printDashedLine() {
        System.out.println("-" .repeat(100));
    }


}
