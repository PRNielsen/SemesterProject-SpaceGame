package dk.sdu.mmmi.room;

import java.util.HashMap;
/**
 *
 * @author asbjo
 */
public enum TileType {
    E,
    E_S,
    N,
    N_E,
    N_E_S,
    N_S,
    S,
    W,
    W_E,
    W_E_S,
    W_N,
    W_N_E,
    W_N_E_S,
    W_N_S,
    W_S,
    NONE;
//    // Tiles with attribute constructors:
//    //      WALL_1(1, true, "wall_1")
//    //      FLOOR_1(1, false, "floor_1")
//    public static final int SIZE = 16;
//    
//    private int id;
//    private boolean collides;
//    private String name;
//    
//    private TileType (int id, boolean collides, String name) {
//       this.id = id;
//       this.collides = collides;
//       this.name = name;
//    }
//    
//    
//     
//    public int getId() {
//        return id;
//    }
//    
//    public boolean getCollides() {
//        return collides;
//    }
//
//    private static HashMap<Integer, TileType> tiles;
//    
//    static {
//        for (TileType tileType : TileType.values()) {
//            tiles.put(tileType.getId(), tileType);
//        }
//    }
//    
//    public static TileType getTileTypeById(int id) {
//        return tiles.get(id);
//    }
}