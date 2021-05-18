package dk.sdu.mmmi.room;

/**
 *
 * @author asbjo
 */
public class Room {
    private TileType tileType;
    private RoomType roomType;
    private boolean west = false;
    private boolean north = false;
    private boolean east = false;
    private boolean south = false;

    public Room (RoomType roomType) { this.roomType = roomType; }
    
    // remove need for abstract!
    //public TileType getTileTypeByLocation(int layer, float x, float y) {
    //    return this.getTileTypeByCoordinates(layer, (int) (x / TileType.SIZE), (int) (y / TileType.SIZE));
    //}
    
     
    //public abstract TileType getTileTypeByCoordinates(int layer, int col, int row);


    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getTileTypeString() {
        return tileType.name();
    }

    public void setTileType(TileType tileType) { this.tileType = tileType; }

    public boolean isWest() {
        return west;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    public boolean isNorth() {
        return north;
    }

    public void setNorth(boolean north) {
        this.north = north;
    }

    public boolean isEast() {
        return east;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public boolean isSouth() {
        return south;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public void setTileType() {
        // E
        if (east == true && north != true && west != true && south != true) {
            setTileType(TileType.E);
        }
        // E_S
        if (east == true && north != true && west != true && south == true) {
            setTileType(TileType.E_S);
        }
        // N
        if (east != true && north == true && west != true && south != true) {
            setTileType(TileType.N);
        }
        // N_E
        if (east == true && north == true && west != true && south != true) {
            setTileType(TileType.N_E);
        }
        // N_E_S
        if (east == true && north == true && west != true && south == true) {
            setTileType(TileType.N_E_S);
        }
        // N_S
        if (east != true && north == true && west != true && south == true) {
            setTileType(TileType.N_S);
        }
        // W
        if (east != true && north != true && west == true && south != true) {
            setTileType(TileType.W);
        }
        // W_E
        if (east == true && north != true && west == true && south != true) {
            setTileType(TileType.W_E);
        }
        // W_E_S
        if (east == true && north != true && west == true && south == true) {
            setTileType(TileType.W_E_S);
        }
        // W_N
        if (east != true && north == true && west == true && south != true) {
            setTileType(TileType.W_N);
        }
        // W_N_E
        if (east == true && north == true && west == true && south != true) {
            setTileType(TileType.W_N_E);
        }
        // W_N_E_S
        if (east == true && north == true && west == true && south == true) {
            setTileType(TileType.W_N_E_S);
        }
        // W_N_S
        if (east != true && north == true && west == true && south == true) {
            setTileType(TileType.W_N_S);
        }
        // W_S
        if (east != true && north != true && west == true && south == true) {
            setTileType(TileType.W_S);
        }
        // S
        if (east != true && north != true && west != true && south == true) {
            setTileType(TileType.S);
        }
        if (east != true && north != true && west != true && south != true) {
            setTileType(TileType.NONE);
        }
    }

    public String printRoomTile() {
        switch(roomType) {
            case START:
                return " S ";
            case REGULAR:
                return " O ";
            case KEY:
                return " K ";
            case TREASURE:
                return " T ";
            case BOSS:
                return " B ";
            case NONE:
                return " X ";
        }
        return "";
    }
}
