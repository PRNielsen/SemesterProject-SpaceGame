package dk.sdu.mmmi.common.data;

import dk.sdu.mmmi.common.data.worldpart.WorldPart;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * @author asbjo
 */
public class WorldMap implements Serializable {
    private final UUID ID = UUID.randomUUID();
    
    private Map<Class, WorldPart> parts;
    
    
    public WorldMap() {
        parts = new ConcurrentHashMap<>();
    }
    
    public void add(WorldPart part) {
        parts.put(part.getClass(), part);
    }
    
    public void remove(Class partClass) {
        parts.remove(partClass);
    }
    
    public <W extends WorldPart> W getPart(Class partClass) {
        return (W) parts.get(partClass);
    }
    
    public String getID() {
        return ID.toString();
    }
}

