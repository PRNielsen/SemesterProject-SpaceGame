/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.ai;

/**
 *
 * @author Shark
 */
public class Node {  
    // Coordinates for the start and target.
    public float x,y;
    String name;
    
    // Index used b y the A* algorithm. Keep track of it so we don't have to recalculate it later.
    int index;

    public Node(float x, float y) {
        this.x = x;
        this.y = y;
//        this.name = name;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
