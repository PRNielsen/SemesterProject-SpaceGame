/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset.classes;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import java.io.File;

/**
 *
 * @author asbjo
 */
public class AssetLoader {
    

    
    // Example 
   // String img = "assets/texture.png";
    //String jarUrl = getJarUrlPlayer(img);    
    // ---------------------------------------
   //         am.load(jarUrl, Texture.class);
   //     am.finishLoading();
        
   //Texture result = am.get(jarUrl, Texture.class);S
    public String getJarUrl(String assetString, String jarName, String identifier) {
        String jarUrl = java.nio.file.Paths.get(new File("").getAbsolutePath(), "target", jarName, assetString).toString();
        return jarUrl = jarUrl.replace("runner", "" + identifier).replace('\\', '/');
    }
    
//    public Texture getJarUrlRoom(String fileName) {
//        String jarName = "Room" + "-1.0SNAPSHOT.jar!";
//        String jarUrl = java.nio.file.Paths.get(new File("").getAbsolutePath(), "target", jarName, fileName).toString();
//        jarUrl = jarUrl.replace("runner", "" + "dk.sdu.mmmi.room").replace('\\', '/');
//        am.load(jarUrl, Texture.class);
//        am.finishLoading();
//        return am.get(jarUrl, Texture.class);
//    }
//    
//    public String getJarUrlPlayer(String fileName) {
//        if(am == null) {
//            System.out.println("Asset Manager is null!");
//        }
//        System.out.println("-----------------------------");
//        System.out.println(fileName);
//        String jarName = "Player" + "-1.0-SNAPSHOT.jar!";
//        String jarUrl = java.nio.file.Paths.get(new File("").getAbsolutePath(), "target", jarName, fileName).toString();
//        System.out.println("-----------------------------");
//        return jarUrl.replace("runner", "" + "dk.sdu.mmmi.player").replace('\\', '/');
//        
//    }
//    
//    // Enemy not yet implemented. Adjust jarname and .replace() accordingly
//    public Texture getJarUrlEnemy(String fileName) {
//        String jarName = "Enemy" + "-1.0SNAPSHOT.jar!";
//        String jarUrl = java.nio.file.Paths.get(new File("").getAbsolutePath(), "target", jarName, fileName).toString();
//        jarUrl = jarUrl.replace("runner", "" + "dk.sdu.mmmi.room").replace('\\', '/');
//        am.load(jarUrl, Texture.class);
//        am.finishLoading();
//        return am.get(jarUrl, Texture.class);
   
}
