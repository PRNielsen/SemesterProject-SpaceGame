package dk.sdu.mmmi.common.data.entitypart;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;

/**
 *
 * @author asbjo
 */
public class Asset implements EntityPart {
    
    String assetName;
    String jarUrl;
    String identifier;
    
    public Asset(String name, String url, String identifier) {
        this.assetName = name;
        this.jarUrl = url;
        this.identifier = identifier;
    }
    
    public String getAssetName() {
        return assetName;
    }
    
    public void setAssetName(String name) {
        this.assetName = name;
    }
    
    public String getJarUrl() {
        return jarUrl;
    }
    
    public void setJarUrl(String url) {
        this.jarUrl = url;
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    public void process(GameData gameData, Entity entity) {
    }
    
}
