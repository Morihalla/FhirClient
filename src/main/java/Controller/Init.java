package controller;

import view.GUI;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public interface Init {

    // Create context + client (once)
    FhirContext ctx = FhirContext.forR5();
    File jsonUpload = new File("C:\\Users\\diete\\IdeaProjects\\FhirClient\\example\\AllergyIntoleranceExample.JSON");
    File xmlUpload = new File("C:\\Users\\diete\\IdeaProjects\\FhirClient\\example\\AllergyIntoleranceExample.XML");
    String SERVER_BASE = "https://hapi.fhir.org/baseR5";
    IGenericClient client = ctx.newRestfulGenericClient(SERVER_BASE);

    // Create input-scanner (keyboard)
    Scanner kbd = new Scanner(System.in);

    // For loggin terminal-msg
    Logger logger = Logger.getLogger(Init.class.getName());


    static void initMain() throws FileNotFoundException {

        // Init the clientprops
        client.setEncoding(EncodingEnum.XML);
        client.setPrettyPrint(true);

        // Create parsers
        IParser jsonParser = ctx.newJsonParser();
        jsonParser.setPrettyPrint(true);
        IParser xmlParser = ctx.newXmlParser();
        xmlParser.setPrettyPrint(true);

        // Read predetermined files and parse
        FileReader jsonReader = new FileReader(jsonUpload);
        FileReader xmlReader = new FileReader(xmlUpload);
        AllergyIntolerance allergyIntoleranceFromJSONFile = jsonParser.parseResource(AllergyIntolerance.class, jsonReader);
        AllergyIntolerance allergyIntolerance1FromXMLFile = xmlParser.parseResource(AllergyIntolerance.class, xmlReader);
        try {
            jsonReader.close();
            xmlReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create all Methods and resources needed
        MethodOutcome createOutcome, createPatient, createPractitioner, createFromJSON, createFromXML, updateOutcome, deleteByID;
        AllergyIntolerance readByID;
        IdType id = new IdType("");

        AllergyIntolerance allergyIntolerance = new AllergyIntolerance();
        allergyIntolerance.setId("");


        // Search all IA's present
        Bundle searchAll = client
                .search()
                .forResource(AllergyIntolerance.class)
                .returnBundle(Bundle.class)
                .execute();

        // Create list of every AI present
        List<IBaseResource> allergiesIntolerances = new ArrayList<>(BundleUtil.toListOfResources(ctx, searchAll));

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

        readByID = client.read()
                .resource(AllergyIntolerance.class)
                .execute();

        updateOutcome = client.update()
                .resource(allergyIntolerance.setId("new"))
                .execute();

        deleteByID = client.delete()
                .resourceById(allergyIntolerance.getText().getId(),allergyIntolerance.getId())
                .execute();

        //Init the GUI
        GUI.initFrame(searchAll,
                createOutcome,
                createFromJSON,
                createFromXML,
                createPatient,
                createPractitioner,
                readByID,
                updateOutcome,
                deleteByID,
                allergiesIntolerances,
                id);
    }

    //Create String-list of every AI present
    static List<String> resultList(Bundle searchAll) {

        List<String> lines = new ArrayList<>();
        String result;

        //Create Resource-list from String-list
        List<IBaseResource> allergiesIntolerances = new ArrayList<>();
        allergiesIntolerances.addAll(BundleUtil.toListOfResources(Init.ctx, searchAll));

        //Show result
        for (IBaseResource ai : allergiesIntolerances) {
            result = ai.getIdElement().getValueAsString();
            lines.add(result + "\n");

//            //Show result
//            for (IBaseResource ai : allergiesIntolerances) {
//                System.out.println(ai.getIdElement().getValueAsString());
//            }

            //Show first result
            System.out.println(searchAll.getEntry().get(0).getResource().getId());


        }
        return lines;
    }


}
