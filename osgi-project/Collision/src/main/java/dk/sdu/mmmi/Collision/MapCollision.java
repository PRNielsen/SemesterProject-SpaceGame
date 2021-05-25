package dk.sdu.mmmi.Collision;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.WorldMap;
import dk.sdu.mmmi.common.data.entitypart.Health;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.data.worldpart.RoomProperties;
import dk.sdu.mmmi.common.services.IPostEntityProcessingService;

public class MapCollision implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity : world.getEntities()) {
            for (WorldMap room : world.getWorldMaps()) {
                // get life parts on all entities
                Health entityLife = entity.getPart(Health.class);
                Movement movement = entity.getPart(Movement.class); 
                // if the two entities are identical, skip the iteration
                if (entity.getID().equals(room.getID())) {
                    continue;

                    // remove entities with zero in expiration
                }

                // CollisionDetection
                if (collides(entity, room)) {
                    movement.setCollision();

                }
            }
        }
    }

    public boolean collides(Entity entity, WorldMap worldMap) {
        boolean northNeighbourTile = false;
        boolean southNeighbourTile = false;
        boolean westNeighbourTile = false;
        boolean eastNeighbourTile = false;
        Position entityPos = entity.getPart(Position.class);
        
        RoomProperties room = worldMap.getPart(RoomProperties.class);

        boolean[][] roomTiles = room.getTiles();

//        for (boolean[] row : roomTiles) {
//            for (boolean field : row) {
//                if (field) {
//                    System.out.print(" B ");
//                } else {
//                    System.out.print(" W ");
//                }
//            }
//            System.out.println(" ");
//        }
        // Returns if boolean is blocked
        boolean playerTile = room.getTile(entityPos.getX(), entityPos.getY());
        //get the tile the entity is currently on
     
        int r = (int) entityPos.getY() / 50;
        int c = 15 - ((int) entityPos.getX() / 50);
        if (r < 0) {
            r = 0;
        }
        if (r > 15) {
            r = 15;
        }
        if (c < 0 ) {
            c = 0;
        }
        if (c > 15) {
            c = 15;
        }

        return playerTile;

    }

}
