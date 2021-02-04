package Controller;

import java.io.File;

public interface GeneralUtil extends Init {

    // Spacing between results
    static void printDashedLine() {
        System.out.println("-".repeat(100));

    }

    // Check for Json or XML
    static String getFileFormat(File file) {
        return file.getName().substring(file.getName().lastIndexOf("."));
    }


}
