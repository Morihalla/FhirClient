import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.*;
import util.GUI;
import util.ActionMethods;
import util.GeneralUtil;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.ActionMethods.*;

public class Main implements GeneralUtil, ActionMethods {

    public static void main(String[] args) throws IOException  {

        client.setEncoding(EncodingEnum.XML);
        client.setPrettyPrint(true);

        // Create parsers
        IParser jsonParser = (IParser) ActionMethods.ctx.newJsonParser();
        jsonParser.setPrettyPrint(true);
        IParser xmlParser = (IParser) ActionMethods.ctx.newXmlParser();
        xmlParser.setPrettyPrint(true);

        MethodOutcome createOutcome, createPatient, createPractitioner, createFromJSON, createFromXML, updateOutcome, deleteOutcome;
        AllergyIntolerance readOutcome;

        AllergyIntolerance allergyIntolerance = new AllergyIntolerance();
        allergyIntolerance.setId("example");

        // Read predetermined files and parse
        FileReader jsonReader = new FileReader(jsonUpload);
        FileReader xmlReader = new FileReader(xmlUpload);
        AllergyIntolerance allergyIntoleranceFromJSONFile = jsonParser.parseResource(AllergyIntolerance.class, jsonReader);
        AllergyIntolerance allergyIntolerance1FromXMLFile = xmlParser.parseResource(AllergyIntolerance.class,xmlReader);

        // CRUD-operations
        createOutcome = client.create()
                .resource(allergyIntolerance)
                .execute();

        // (Create Patient/Practitioner to get the ID for the exampleFile)
        Patient patient = new Patient();
        patient.setId("example");
        createPatient = client.create()
                .resource(patient)
                .execute();

        Practitioner practitioner = new Practitioner();
        practitioner.setId("example");
        createPractitioner = client.create()
                .resource(practitioner)
                .execute();

        createFromJSON = client.create()
                .resource(allergyIntoleranceFromJSONFile)
                .execute();

        createFromXML = client.create()
                .resource(allergyIntolerance1FromXMLFile)
                .execute();

        updateOutcome = client.update()
                .resource(allergyIntolerance.setId("new"))
                .execute();

        deleteOutcome = client.delete()
                .resourceById(new IdType("AllergyIntolerance", "1506"))
                .execute();

        readOutcome = client.read()
                .resource(AllergyIntolerance.class)
                .withId("2130")
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
        allergiesIntolerances.addAll(BundleUtil.toListOfResources(ActionMethods.ctx, searchAll));

        //Show result
        for (IBaseResource ai : allergiesIntolerances) {
            System.out.println(ai.getIdElement().getValueAsString());
        }

        GeneralUtil.printDashedLine();

        System.out.println(GeneralUtil.getFileFormat(jsonUpload));
        System.out.println(GeneralUtil.getFileFormat(xmlUpload));

        //Show first result
        System.out.println(searchAll.getEntry().get(0).getResource().getId());

        GUI.initFrame(createOutcome,
                createFromJSON,
                createFromXML,
                createPatient,
                createPractitioner,
                readOutcome,
                updateOutcome,
                deleteOutcome,
                allergiesIntolerances);
    }


}
