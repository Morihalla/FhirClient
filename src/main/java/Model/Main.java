package Model;

import Controller.ActionMethods;
import Controller.GeneralUtil;
import Controller.Init;

import java.io.IOException;

import static java.lang.System.out;

public class Main implements GeneralUtil, ActionMethods, Init {

    public static void main(String[] args) throws IOException {

        Init.initMain();

        GeneralUtil.printDashedLine();

        out.println(GeneralUtil.getFileFormat(jsonUpload));
        out.println(GeneralUtil.getFileFormat(xmlUpload));


    }
}
