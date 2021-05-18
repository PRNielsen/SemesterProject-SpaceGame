package dk.sdu.mmmi.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author jcs
 */
public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private final Map<String, WorldMap> gameWorldMap = new ConcurrentHashMap<>();

    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }
    
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }
    
        public String addWorldMap(WorldMap worldMap) {
        gameWorldMap.put(worldMap.getID(), worldMap);
        return worldMap.getID();
    }

    public void removeWorldMap(String worldMapID) {
        gameWorldMap.remove(worldMapID);
    }

    public void removeWorldMap(WorldMap worldMap) {
        gameWorldMap.remove(worldMap.getID());
    }
    
    public Collection<WorldMap> getWorldMaps() {
        return gameWorldMap.values();
    }

    public <W extends WorldMap> List<WorldMap> getWorldMaps(Class<W>... worldMapTypes) {
        List<WorldMap> r = new ArrayList<>();
        for (WorldMap w : getWorldMaps()) {
            for (Class<W> worldMapType : worldMapTypes) {
                if (worldMapType.equals(w.getClass())) {
                    r.add(w);
                }
            }
        }
        return r;
    }
    
}
