/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.room;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.services.IEntityProcessingService;

/**
 *
 * @author asbjo
 */
public class MapControlSystem /*implements IEntityProcessingService*/ {

   // @Override
    public void process(GameData gameData, World world) {
        for (Entity map : world.getEntities(Map.class)) {
            updateShape(map);
        }
        
    }
    
    private void updateAssets(Entity entity) {
        
    }
    
    private void updateShape(Entity entity) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];
        
        shapex[0] = -4;
        shapey[0] = -4;
        
        shapex[1] = 4;
        shapey[1] = 4;
        
        shapex[2] = -8;
        shapey[2] = -8;
        
        shapex[3] = 8;
        shapey[3] = 8;
        
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    
    
}
