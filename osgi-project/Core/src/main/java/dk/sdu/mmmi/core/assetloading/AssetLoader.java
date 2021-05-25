package dk.sdu.mmmi.core.assetloading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
/**
 *
 * @author asbjo
 */
public class AssetLoader {
    
    public String getJarUrl(String assetString, String jarName, String identifier) {
        String jarUrl = java.nio.file.Paths.get(new File("").getAbsolutePath(), "target", jarName, assetString).toString();
        return jarUrl = jarUrl.replace("runner", "" + identifier).replace('\\', '/');
    }
    
    // Returns array of strings from comma separated string
    public String[] csvTrimmer(String string) {
        return string.split("\\s*,\\s*" );
    }
    
    public void scanTextFile(String pathString) throws FileNotFoundException {
        //URL url = getClass().getResource(pathString);
        //if (url != null) {
            File file = new File(pathString);
            Scanner input = new Scanner(file);
            String line = "";
            int lineNumber = 1;
            while (input.hasNext()) {
            line = input.nextLine();
            System.out.println("Line number: " + lineNumber + ": " + line);
            lineNumber++;
            } 
       // } else {
       //     System.out.println("HAHAHAH");
        //}
    }

//        InputStream is = getClass().getResourceAsStream(pathString);
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader br = new BufferedReader(isr);
//        String line;
//    while ((line = br.readLine()) != null) {
//        System.out.println("Read line: " + line);
//    }
//    br.close();
//    isr.close();
//    is.close();
}

