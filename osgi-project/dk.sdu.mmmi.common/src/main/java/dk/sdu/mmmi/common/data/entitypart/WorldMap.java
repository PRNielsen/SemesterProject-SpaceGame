package dk.sdu.mmmi.common.data.entitypart;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;

/**
 *
 * @author asbjo
 */
public class WorldMap implements EntityPart {
    
    private int mapWidth;
    private int mapHeight;
    
    
    public WorldMap(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }
    
    public void setMapDimensions(int width, int height) {
        this.mapWidth = width;
        this.mapHeight = height;
    }
    
    public int getMapWidth() {
        return mapWidth;
    }
    
    public int getMapHeight() {
        return mapHeight;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
    }
    
    
}
