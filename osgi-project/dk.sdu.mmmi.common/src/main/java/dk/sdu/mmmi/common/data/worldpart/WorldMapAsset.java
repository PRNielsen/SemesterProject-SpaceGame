/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.common.data.worldpart;

import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.WorldMap;

/**
 *
 * @author asbjo
 */
public class WorldMapAsset implements WorldPart {
    String assetName;
    String jarUrl;
    String identifier;
    
    public WorldMapAsset(String name, String url, String identifier) {
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
    
    public void process(GameData gameData, WorldMap worldMap) {
    }
}
