package dk.sdu.mmmi.Collision;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.WorldMap;
import dk.sdu.mmmi.common.data.entitypart.Health;
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

                // if the two entities are identical, skip the iteration
                if (entity.getID().equals(room.getID())) {
                    continue;

                    // remove entities with zero in expiration
                }

                // CollisionDetection
                if (this.Collides(entity, room)) {
                    //if entity has been hit, and should have its life reduced
                    //if (entityLife.getLife() > 0) {
                    //    entityLife.setLife(entityLife.getLife() - 1);
                    //    entityLife.setIsHit(true);
                    //    // if entity is out of life - remove
                    //    if (entityLife.getLife() <= 0) {
                    //        world.removeEntity(entity);
                    //    }
                    System.out.println("COLLISION WTIH ROOM");
                }
            }
        }
    }

    public Boolean Collides(Entity entity, WorldMap entity2) {
        boolean northNeighbourTile = false;
        boolean southNeighbourTile = false;
        boolean westNeighbourTile = false;
        boolean eastNeighbourTile = false;
        Position entityPos = entity.getPart(Position.class);
        RoomProperties room = entity2.getPart(RoomProperties.class);

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
        boolean playerTile = room.getTile(entityPos.getX(), entityPos.getY()); //get the tile the entity is currently on
        int r = (int) entityPos.getX() / 50;
        if (r > 15) {
            r = 15;
        }
        int c = 15 - ((int) entityPos.getY() / 50);
        if (c < 0) {
            c = 0;
        }
        //System.out.println(entity.getClass());
        System.out.println("Entity x pos: " + entityPos.getX());
        System.out.println("Entity y pos: " + entityPos.getY());
        System.out.println("entity x tile pos: " + r);
        System.out.println("Entity y tile pos: " + c);

        float testX = 0;
        float testY = 0;

        float sx = 0, sw = 0, sy = 0, sh = 0;

        if (r > 0) {
            northNeighbourTile = roomTiles[r - 1][c];
            //System.out.println("North: " + northNeighbourTile);
        }
        if (r < 15) {
            southNeighbourTile = roomTiles[r + 1][c];
            //System.out.println("South: " + southNeighbourTile);
        }
        if (c > 0) {
            westNeighbourTile = roomTiles[r][c - 1];
            //System.out.println("West: " + westNeighbourTile);
        }
        if (c < 15) {
            eastNeighbourTile = roomTiles[r][c + 1];
            //System.out.println("East: " + eastNeighbourTile);
        }

        if (northNeighbourTile) {
            if (!(c <= 0)) {

                float[] coordinates = room.getAreaOfTile(r, c - 1);
                sx = coordinates[0];
                sw = coordinates[1];
                sy = coordinates[2];
                sh = coordinates[3];
                //System.out.println("North");
            }
        }
        if (southNeighbourTile) {
            float[] coordinates = room.getAreaOfTile(r, c + 1);
            sx = coordinates[0];
            sw = coordinates[1];
            sy = coordinates[2];
            sh = coordinates[3];
            //System.out.println("South");
        }
        if (westNeighbourTile) {
            float[] coordinates = room.getAreaOfTile(r - 1, c);
            sx = coordinates[0];
            sw = coordinates[1];
            sy = coordinates[2];
            sh = coordinates[3];
            //System.out.println("West");
        }
        if (eastNeighbourTile) {
            float[] coordinates = room.getAreaOfTile(r + 1, c);
            sx = coordinates[0];
            sw = coordinates[1];
            sy = coordinates[2];
            sh = coordinates[3];
            //System.out.println("East");
        }

        if (entityPos.getX() < sx) {
            testX = sx;
        } else if (entityPos.getX() > sx + sw) {
            testX = sx + sw;
        }
        if (entityPos.getY() < sy) {
            testY = sy;
        } else if (entityPos.getX() > sy + sh) {
            testY = sy + sh;
        }

        float distX = entityPos.getX() - testX;
        float distY = entityPos.getY() - testY;
        float distance = (float) Math.sqrt((distX * distX) + (distY * distY));

        //float dx = (float) entMov.getX() - (float) entMov2.getX();
        //float dy = (float) entMov.getY() - (float) entMov2.getY();
        //float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance <= (entity.getRadius())) {
            return true;
        }
        return false;
    }

}
