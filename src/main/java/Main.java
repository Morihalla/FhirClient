import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.util.BundleUtil;
import org.docx4j.wml.P;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.*;
import test.GUI;
import test.Util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static test.Util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        client.setEncoding(EncodingEnum.XML);
        client.setPrettyPrint(true);

        // Create parsers
        IParser jsonParser = (IParser) Util.ctx.newJsonParser();
        jsonParser.setPrettyPrint(true);
        IParser xmlParser = (IParser) Util.ctx.newXmlParser();
        xmlParser.setPrettyPrint(true);

        MethodOutcome createOutcome, createPatient, createPractitioner, createFromFile, updateOutcome, deleteOutcome;
        AllergyIntolerance readOutcome;

        AllergyIntolerance allergyIntolerance = new AllergyIntolerance();
        allergyIntolerance.setId("1");

        FileReader reader = new FileReader(upload);
        AllergyIntolerance allergyIntoleranceFromFile = jsonParser.parseResource(AllergyIntolerance.class, reader);

//        CRUD-operations
        createOutcome = client.create()
                .resource(allergyIntolerance)
                .execute();

//         (Create Patient/Practitioner to recognize the one from the example)
//        Patient patient = new Patient();
//        patient.setId("example");
//
//        createOutcome = client.create()
//                .resource(patient)
//                .execute();

//        Practitioner practitioner = new Practitioner();
//        practitioner.setId("example");
//
//        createPractitioner = client.create()
//                .resource(practitioner)
//                .execute();

        createFromFile = client.create()
                .resource(allergyIntoleranceFromFile)
                .execute();

//        updateOutcome = client.update()
//                .resource(allergyIntolerance.setId("new"))
//                .execute();

//        deleteOutcome = client.delete()
//                .resourceById(new IdType("AllergyIntolerance", "1506"))
//                .execute();

//        readOutcome = client.read()
//                .resource(AllergyIntolerance.class)
//                .withId("example")
//                .execute();

        // Search all IA's present
        Bundle searchAll = client
                .search()
                .forResource(AllergyIntolerance.class)
                .count(100)
                .returnBundle(Bundle.class)
                .execute();

        //Create list of every AI present
        List<IBaseResource> allergiesIntolerances = new ArrayList<>();
        allergiesIntolerances.addAll(BundleUtil.toListOfResources(Util.ctx, searchAll));

        //Show result
        for (IBaseResource ai : allergiesIntolerances) {
            System.out.println(ai.getIdElement().getValueAsString());
        }

        Util.printDashedLine();

        //Show first result
        System.out.println(searchAll.getEntry().get(0).getResource().getId());

        GUI.initFrame(createOutcome,
//                createPatient, createPractitioner,
                createFromFile,
//        readOutcome, updateOutcome, deleteOutcome,
                allergiesIntolerances);
    }


}
