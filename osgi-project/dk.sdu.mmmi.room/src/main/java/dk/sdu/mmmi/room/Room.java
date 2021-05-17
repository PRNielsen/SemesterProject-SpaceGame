package dk.sdu.mmmi.room;

import java.util.HashSet;
import java.util.Set;

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
    private Tile[][] tiles;

    public Room (RoomType roomType) { 
        this.roomType = roomType; 
        this.tiles = new Tile[16][16];
    }
    
    public Tile getTile(int posX, int posY){
        // insert pixel dimensions, return tiles;
      return tiles[posX][posY];
    } 
   
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

    public Tile[][] getTiles() {
        return tiles;
    }
    
    
    
    // Temprorary method for initail room generation
    public void setStartingRoomTiles() {
        for (int r = 0; r < 16; r++) {
            for (int c = 0; c < 16; c++) {
                if (r <= 1) {
                    Tile tmpTile = new Tile();
                    tmpTile.setBlocked(true);
                    this.tiles[r][c] = tmpTile;
                } else if (r == 15) {
                    Tile tmpTile = new Tile();
                    tmpTile.setBlocked(true);
                    this.tiles[r][c] = tmpTile;
                } else if (6 < r && r < 10 ) {
                    Tile tmpTile = new Tile();
                    tmpTile.setBlocked(true);
                    this.tiles[r][c] = tmpTile;
                } else if (c == 0) {
                    Tile tmpTile = new Tile();
                    tmpTile.setBlocked(true);
                    this.tiles[r][c] = tmpTile;
                } else if (c == 15) {
                    Tile tmpTile = new Tile();
                    tmpTile.setBlocked(true);
                    this.tiles[r][c] = tmpTile;
                } else {
                    Tile tmpTile = new Tile();
                    tmpTile.setBlocked(false);
                    this.tiles[r][c] = tmpTile;
                }

            }
        }
        for (int r = 0; r < 16; r++) {
            for (int c = 0; c < 16; c++) {
                if (r > 6 && r < 10) {
                    if (c == 4 || c == 11) {
                        Tile tmpTile = new Tile();
                        tmpTile.setBlocked(false);
                        this.tiles[r][c] = tmpTile;
                    }
                }
            }
        }
        // Printing algorithm 
        System.out.println("------  Room Tiles -------");
        for (Tile[] row : this.tiles) {
            for (Tile field : row) {
                if (field.isBlocked()) {
                    System.out.print(" B ");
                } else {
                    System.out.print(" W ");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------");
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
