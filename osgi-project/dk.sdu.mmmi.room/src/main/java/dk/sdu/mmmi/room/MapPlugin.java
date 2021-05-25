/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.room;


import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.WorldMap;
import dk.sdu.mmmi.common.data.worldpart.RoomProperties;
import dk.sdu.mmmi.common.data.worldpart.WorldMapAsset;
import dk.sdu.mmmi.common.services.IGamePluginService;
import java.util.Random;

/**
 *
 * @author asbjo
 */
public class MapPlugin implements IGamePluginService  {
    
    private WorldMap worldMap;
    Random rand = new Random();
    Room[][] rooms;
    private Room currentRoom;
    String assetString = "assets/S.png";
    String jarName = "Room" + "-1.0SNAPSHOT.jar!";
    String identifier = "dk.sdu.mmmi.room";
    RoomProperties prop = new RoomProperties(16,16);
    
    public MapPlugin() {
        
    }

    // @Override
    public void start(GameData gameData, World world) {
        
        worldMap = createWorldMap(gameData);
        world.addWorldMap(worldMap);
    }

    
    
    private WorldMap createWorldMap(GameData gameData) {
        
        worldMap = new Map();
        worldMap.add(new WorldMapAsset(assetString, jarName, identifier));
        this.rooms = generateRandomMap();
        currentRoom = getStartingRoom(rooms);
        currentRoom.setStartingRoomTiles();
        prop = copyMapArray();
        worldMap.add(prop);
        printMap();
        return worldMap;
    }
    
   // @Override
    public void stop(GameData gameData, World world) {
        
        world.removeWorldMap(worldMap);
    }
    
    public String getAssetString() {
        return assetString;
    }
    
    public String getJarName() {
        return jarName;
    }
    
    public String getID() {
        return identifier;
    }
    
    private int getDirection() {
        
        return rand.nextInt(10);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    
    
    private Room[][] generateRandomMap() {
        
        int width = 8;
        int height = 8;
        int roomDensity;
        //worldMap.add(new WorldMap(8, 8));
        
        int counter = 0;
        rooms = new Room[width][height];
        roomDensity = (int) Math.round((width * height) * 0.4);
        
        
        // Chose random index in top row and set it as start
        boolean finished = false;
        int currentY;
        int currentX;
        int startIndex = rand.nextInt(width);
        rooms[0][startIndex] = new Room(RoomType.START);
        currentY = 0;
        currentX = startIndex;
        // Randomly place rooms by probability
        while(!finished) {
            int direction = getDirection();
            // temporary counter

            if (counter == roomDensity) {
                finished = true;
            }

            if (direction <= 1 && currentY != 0) {
                // Set direction up
                currentY--;
                if (rooms[currentY][currentX] == null) {
                    rooms[currentY][currentX] = new Room(RoomType.REGULAR);
                    rooms[currentY][currentX].setSouth(true);
                    rooms[currentY + 1][currentX].setNorth(true);
                    counter++;
                }

            }
            else if (direction >= 2 && direction <= 3 && currentX != 0) {
                // Set direction left
                currentX--;
                if (rooms[currentY][currentX] == null) {
                    rooms[currentY][currentX] = new Room(RoomType.REGULAR);
                    rooms[currentY][currentX].setEast(true);
                    rooms[currentY][currentX + 1].setWest(true);
                    counter++;
                }
            }
            else if (direction >= 4 && direction <= 5 && currentX != width - 1) {
                // set direction right
                currentX++;
                if (rooms[currentY][currentX] == null) {
                    rooms[currentY][currentX] = new Room(RoomType.REGULAR);
                    rooms[currentY][currentX].setWest(true);
                    rooms[currentY][currentX - 1].setEast(true);
                    counter++;
                }
            }
            else if (direction >= 6 && direction <= 9 && currentY != height - 1) {
                // set direction down
                currentY++;
                if (rooms[currentY][currentX] == null) {
                    rooms[currentY][currentX] = new Room(RoomType.REGULAR);
                    rooms[currentY][currentX].setNorth(true);
                    rooms[currentY - 1][currentX].setSouth(true);
                    counter++;
                }
            }

        }

        // Fill all empty indexes with walls
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (rooms[r][c] == null) {
                    rooms[r][c] = new Room(RoomType.NONE);
                }
            }
        }

        // Set TileTypes for all rooms in array
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                rooms[r][c].setTileType();
            }
        }
        
        
        return rooms;
    }
    
    public void printMap() {
        for (Room[] x : rooms) {
            for (Room y : x) {
                System.out.print(y.printRoomTile());
            }
            System.out.println();
        }
    }
    
    private Room getStartingRoom(Room[][] rooms) {
        Room returnRoom = this.rooms[0][0];
        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 7; c++) {
                if (this.rooms[r][c].getRoomType() == RoomType.START) {
                    return rooms[r][c];
                }
            }
        }
        return returnRoom;
    }
    
    private RoomProperties copyMapArray() {
        Tile[][] tiles = currentRoom.getTiles();
        for (int r = 0; r < 16; r++) {
            for (int c = 0; c < 16; c++) {
                Tile tmpTile = new Tile();
                tmpTile = tiles[r][c];
                if (tmpTile.isBlocked()) {
                    prop.setIsBlocked(r, c, true);
                }
            }
        }
        return prop;
    }
    
//    
//    private Room setStartingRoom() {
//        
//    }
}


//    // Map constructor that sets map size and density of traversable rooms
//    private void setMapSize(int width, int height) {
//        rooms = new Room[width][height];
//        roomDensity = (int) Math.round((width * height) * 0.4);
//    }
//
//    public String getTileType(int row, int col) {
//        return rooms[row][col].getTileTypeString();
//    }
//
//    private Room[][] generateEmptyMap() {
//        for (int r = 0; r < height; r++) {
//            for (int c = 0; c < width; c++) {
//                rooms[r][c] = new Room(RoomType.NONE);
//                //rooms[i][j].setRoomType(RoomType.REGULAR);
//            }
//        }
//        return rooms;
//    }
//
//    public void printMap() {
//        for (Room[] x : rooms) {
//            for (Room y : x) {
//                System.out.print(y.printRoomTile());
//            }
//            System.out.println();
//        }
//    }
//
//    public Room[][] generateRandomMap() {
//        // Chose random index in top row and set it as start
//        boolean finished = false;
//        int currentY;
//        int currentX;
//        int startIndex = rand.nextInt(width);
//        rooms[0][startIndex] = new Room(RoomType.START);
//        currentY = 0;
//        currentX = startIndex;
//        // Randomly place rooms by probability
//        while(!finished) {
//            int direction = getDirection();
//            // temporary counter
//
//            if (counter == roomDensity) {
//                finished = true;
//            }
//
//            if (direction <= 1 && currentY != 0) {
//                // Set direction up
//                currentY--;
//                if (rooms[currentY][currentX] == null) {
//                    rooms[currentY][currentX] = new Room(RoomType.REGULAR);
//                    rooms[currentY][currentX].setSouth(true);
//                    rooms[currentY + 1][currentX].setNorth(true);
//                    counter++;
//                    System.out.println("UP");
//                }
//
//            }
//            else if (direction >= 2 && direction <= 3 && currentX != 0) {
//                // Set direction left
//                currentX--;
//                if (rooms[currentY][currentX] == null) {
//                    rooms[currentY][currentX] = new Room(RoomType.REGULAR);
//                    rooms[currentY][currentX].setEast(true);
//                    rooms[currentY][currentX + 1].setWest(true);
//                    counter++;
//                    System.out.println("LEFT");
//                }
//            }
//            else if (direction >= 4 && direction <= 5 && currentX != width - 1) {
//                // set direction right
//                currentX++;
//                if (rooms[currentY][currentX] == null) {
//                    rooms[currentY][currentX] = new Room(RoomType.REGULAR);
//                    rooms[currentY][currentX].setWest(true);
//                    rooms[currentY][currentX - 1].setEast(true);
//                    counter++;
//                    System.out.println("RIGHT");
//                }
//            }
//            else if (direction >= 6 && direction <= 9 && currentY != height - 1) {
//                // set direction down
//                currentY++;
//                if (rooms[currentY][currentX] == null) {
//                    rooms[currentY][currentX] = new Room(RoomType.REGULAR);
//                    rooms[currentY][currentX].setNorth(true);
//                    rooms[currentY - 1][currentX].setSouth(true);
//                    counter++;
//                    System.out.println("DOWN");
//                }
//            }
//
//        }
//
//        // Fill all empty indexes with walls
//        for (int r = 0; r < height; r++) {
//            for (int c = 0; c < width; c++) {
//                if (rooms[r][c] == null) {
//                    rooms[r][c] = new Room(RoomType.NONE);
//                }
//            }
//        }
//
//        // Set TileTypes for all rooms in array
//        for (int r = 0; r < height; r++) {
//            for (int c = 0; c < width; c++) {
//                rooms[r][c].setTileType();
//            }
//        }
//
//        return rooms;
//    }
//
//    // Rotate array clockwise
//    private int[][] rotateClockWise(int[][] array) {
//        int size = array.length;
//        int[][] rotatedArr = new int[size][size];
//
//        for (int i = 0; i < size; ++i)
//            for (int j = 0; j < size; ++j)
//                rotatedArr[i][j] = array[size - j - 1][i];
//
//        return rotatedArr;
//    }
//
//    // Rotate array counter-clockwise
//    private int[][] rotateCounterClockWise(int[][] array) {
//        int size = array.length;
//        int[][] rotatedArr = new int[size][size];
//
//        for (int i = 0; i < size; ++i)
//            for (int j = 0; j < size; ++j)
//                rotatedArr[i][j] = array[j][size - i - 1];
//
//        return rotatedArr;
//    }
//
//    
//}
