/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.common.data.worldpart;

import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.WorldMap;

/**
 *
 * @author asbjo
 */
public class RoomProperties implements WorldPart {
    private boolean isBlocked;
    private boolean[][] tiles;

    public RoomProperties(int r, int c) {
        this.tiles = new boolean[r][c];
    }
   
    public boolean isIsBlocked(int r, int c) {
        return tiles[r][c];
    }

    public void setIsBlocked(int r, int c, boolean isBlocked) {
        this.tiles[r][c] = isBlocked;
    }

    // sets tile at given indexes. 
    public void setTile(int r, int c, boolean isBlocked) {
        this.tiles[r][c] = isBlocked;
    }
    
    // Takes pixel coordinates
    public boolean getTile(float posX, float posY) {
        if(posX <= 800 && posY <= 800) {
            int r = (int) posX / 50;
            int c = (int) posY / 50;
            return tiles[r][c];
        } 
        return tiles[0][0];
    }
    
    // Takes indexes of row and column and outputs array defining area:
    // E.g tiles[
    public int[] getAreaOfTile(int r, int c) {
        int x1 = c * 50;
        int x2 = x1 + 50;
        int y1 = 800 - (r * 50);
        int y2 = y1 + 50;
        return new int[] {x1, x2, y1, y2};
    }
    
    @Override
    public void process(GameData gameData, WorldMap worldMap) {
        
    }
    
}