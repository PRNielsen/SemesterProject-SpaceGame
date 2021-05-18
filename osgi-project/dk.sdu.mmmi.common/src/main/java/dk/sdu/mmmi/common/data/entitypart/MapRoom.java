package dk.sdu.mmmi.common.data.entitypart;
/**
 *
 * @author asbjo
 */
public class MapRoom {
    private boolean northExit;
    private boolean eastExit;
    private boolean southExit;
    private boolean westExit;
    
    public MapRoom(boolean north, boolean east, boolean south, boolean west) {
        this.northExit = north;
        this.eastExit = east;
        this.southExit = south;
        this.westExit = west;
    }
    
    public boolean isNorth() {
        return northExit;
    }
    
    public boolean isEast() {
        return eastExit;
    }
        
    public boolean isSouth() {
        return southExit;
    }
    
    public boolean isWest() {
        return westExit;
    }

    public void setNorth(boolean north) {
        this.northExit = north;
    }
    
    public void setEast(boolean east) {
        this.eastExit = east;
    }
    
    public void setSouth(boolean south) {
        this.southExit = south;
    }
    
    public void setWest(boolean west) {
        this.westExit = west;
    }    
}
