package model;

import controller.ActionMethods;
import controller.GeneralUtil;
import controller.Init;
import view.GUI;

import java.io.IOException;;

public class Main implements GeneralUtil, ActionMethods {

    public static void main(String[] args) throws IOException {


        GUI gui = new GUI();

        Init.initMain(gui);

        GeneralUtil.printDashedLine();

        //Test to get extention of files
        Init.logger.info(GeneralUtil.getFileFormat(Init.jsonUpload));
        Init.logger.info(GeneralUtil.getFileFormat(Init.xmlUpload));


    }
}
