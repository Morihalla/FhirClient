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

public class Init implements GeneralUtil, ActionMethods {

    // Create context + client (once)
    static FhirContext ctx = FhirContext.forR5();
    public static File jsonUpload = new File("C:\\Users\\diete\\IdeaProjects\\FhirClient\\example\\AllergyIntoleranceExample.JSON");
    public static File xmlUpload = new File("C:\\Users\\diete\\IdeaProjects\\FhirClient\\example\\AllergyIntoleranceExample.XML");
    static String SERVER_BASE = "https://hapi.fhir.org/baseR5";
    static IGenericClient client = ctx.newRestfulGenericClient(SERVER_BASE);

    // Create input-scanner (keyboard)
    public static Scanner kbd = new Scanner(System.in);

    // For loggin terminal-msg
    public static Logger logger = Logger.getLogger(Init.class.getName());

    // Create all Methods and resources needed
    public static Bundle searchAll;
    public static MethodOutcome createOutcome;
    public static MethodOutcome createPatient;
    public static MethodOutcome createPractitioner;
    public static MethodOutcome createFromJSON;
    public static MethodOutcome createFromXML;
    public static MethodOutcome updateOutcome;
    public static MethodOutcome deleteByID;

    public static AllergyIntolerance readByID;
    static List<IBaseResource> allergiesIntolerances;
    public static IdType id = new IdType("");

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

    public static AllergyIntolerance allergyIntolerance = new AllergyIntolerance();

    public static void initMain(GUI gui) throws FileNotFoundException {

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


        // Search all IA's present
        searchAll = client
                .search()
                .forResource(AllergyIntolerance.class)
                .returnBundle(Bundle.class)
                .execute();

        // Create list of every AI present
        allergiesIntolerances = new ArrayList<>(BundleUtil.toListOfResources(ctx, searchAll));

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
                .withId(allergyIntolerance.getId())
                .execute();

        updateOutcome = client.update()
                .resource(allergyIntolerance.setId("new"))
                .execute();

        deleteByID = client.delete()
                .resourceById(allergyIntolerance.getText().getId(), allergyIntolerance.getId())
                .execute();

        //Init the GUI
        gui.initFrame(searchAll,
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

    public static Bundle getSearchAll() {
        return searchAll;
    }

    public static void setSearchAll(Bundle searchAll) {
        Init.searchAll = searchAll;
    }

    public static MethodOutcome getCreateOutcome() {
        return createOutcome;
    }

    public static void setCreateOutcome(MethodOutcome createOutcome) {
        Init.createOutcome = createOutcome;
    }

    public static MethodOutcome getCreatePatient() {
        return createPatient;
    }

    public static void setCreatePatient(MethodOutcome createPatient) {
        Init.createPatient = createPatient;
    }

    public static MethodOutcome getCreatePractitioner() {
        return createPractitioner;
    }

    public static void setCreatePractitioner(MethodOutcome createPractitioner) {
        Init.createPractitioner = createPractitioner;
    }

    public static MethodOutcome getCreateFromJSON() {
        return createFromJSON;
    }

    public static void setCreateFromJSON(MethodOutcome createFromJSON) {
        Init.createFromJSON = createFromJSON;
    }

    public static MethodOutcome getCreateFromXML() {
        return createFromXML;
    }

    public static void setCreateFromXML(MethodOutcome createFromXML) {
        Init.createFromXML = createFromXML;
    }

    public static MethodOutcome getUpdateOutcome() {
        return updateOutcome;
    }

    public static void setUpdateOutcome(MethodOutcome updateOutcome) {
        Init.updateOutcome = updateOutcome;
    }

    public static MethodOutcome getDeleteByID() {
        return deleteByID;
    }

    public static void setDeleteByID(MethodOutcome deleteByID) {
        Init.deleteByID = deleteByID;
    }

    public static AllergyIntolerance getReadByID() {
        return readByID;
    }

    public static void setReadByID(AllergyIntolerance readByID) {
        Init.readByID = readByID;
    }

    public static List<IBaseResource> getAllergiesIntolerances() {
        return allergiesIntolerances;
    }

    public static void setAllergiesIntolerances(List<IBaseResource> allergiesIntolerances) {
        Init.allergiesIntolerances = allergiesIntolerances;
    }

    public static IdType getId() {
        return id;
    }

    public static void setId(IdType id) {
        Init.id = id;
    }
}
