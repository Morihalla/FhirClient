package controller;

import java.io.File;

public interface GeneralUtil {

    // Spacing between results
    static void printDashedLine() {
        System.out.println("-".repeat(100));

    }

    // Check for Json or XML
    static String getFileFormat(File file) {
        return file.getName().substring(file.getName().lastIndexOf("."));
    }


}
