package model;

import controller.ActionMethods;
import controller.GeneralUtil;
import controller.Init;

import java.io.IOException;

public class Main implements GeneralUtil, ActionMethods, Init {

    static String url = "http://hapi.fhir.org/baseR5/AllergyIntolerance";

    public static void main(String[] args) throws IOException {

        Init.initMain();

        GeneralUtil.printDashedLine();

        //Test to get extention of files
        logger.info(GeneralUtil.getFileFormat(jsonUpload));
        logger.info(GeneralUtil.getFileFormat(xmlUpload));


    }
}
