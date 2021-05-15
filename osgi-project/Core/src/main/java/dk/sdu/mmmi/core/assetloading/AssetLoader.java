package dk.sdu.mmmi.core.assetloading;

import java.io.File;
/**
 *
 * @author asbjo
 */
public class AssetLoader {
    
    public String getJarUrl(String assetString, String jarName, String identifier) {
        String jarUrl = java.nio.file.Paths.get(new File("").getAbsolutePath(), "target", jarName, assetString).toString();
        return jarUrl = jarUrl.replace("runner", "" + identifier).replace('\\', '/');
    }
}
