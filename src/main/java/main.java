import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.AllergyIntolerance;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.IdType;
import test.gui;
import test.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static test.util.client;

public class main {

    public static void main(String[] args) throws IOException {

        client.setEncoding(EncodingEnum.XML);
        client.setPrettyPrint(true);

        // Create parsers
        IParser jsonParser = (IParser) util.ctx.newJsonParser();
        jsonParser.setPrettyPrint(true);
        IParser xmlParser = (IParser) util.ctx.newXmlParser();
        xmlParser.setPrettyPrint(true);

        MethodOutcome createOutcome, updateOutcome, deleteOutcome;
        AllergyIntolerance readOutcome;

        AllergyIntolerance allergyIntolerance = new AllergyIntolerance();
        allergyIntolerance.setId("example");

        //CRUD-operations
        createOutcome = client.create()
                .resource(allergyIntolerance)
                .execute();

        updateOutcome = client.update()
                .resource(allergyIntolerance.setId("new"))
                .execute();

        deleteOutcome = client.delete()
                .resourceById(new IdType("AllergyIntolerance", "1506"))
                .execute();

        readOutcome = client.read()
                .resource(AllergyIntolerance.class)
                .withId("1902")
                .execute();

        // Search all IA's present
        Bundle searchAll = client
                .search()
                .forResource(AllergyIntolerance.class)
                .count(100)
                .returnBundle(Bundle.class)
                .execute();

        //Create list of every AI present
        List<IBaseResource> allergiesIntolerances = new ArrayList<>();
        allergiesIntolerances.addAll(BundleUtil.toListOfResources(util.ctx, searchAll));

        //Show result
        for (IBaseResource ai : allergiesIntolerances) {
            System.out.println(ai.getIdElement().getValueAsString());
        }

        util.printDashedLine();

        //Show first result
        System.out.println(searchAll.getEntry().get(0).getResource().getId());

        gui.initFrame(createOutcome, readOutcome, updateOutcome, deleteOutcome, allergiesIntolerances);
    }


}
