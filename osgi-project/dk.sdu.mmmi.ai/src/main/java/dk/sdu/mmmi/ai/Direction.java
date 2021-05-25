/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.ai;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;

public class Direction implements Connection<Node>{
    Node fromNode;
    Node toNode;
    float cost;
    boolean right = false;
    boolean left = false;
    boolean up = false;
    boolean down = false;
    boolean[] booleanArray = new boolean[4];

    public Direction(Node fromNode, Node toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        cost = Vector2.dst(fromNode.x, fromNode.y, toNode.x, toNode.y);
        this.booleanArray = booleanArray;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public Node getFromNode() {
        return fromNode;
    }

    @Override
    public Node getToNode() {
        return toNode;
    } 
    
    public boolean[] getBools(Node fromNode, Node toNode){
        if (fromNode.x > toNode.x){
            left = true;
        } 
        if (fromNode.x < toNode.x){
            right = true;
        }
        if (fromNode.y < toNode.y){
            up = true;
        }
        if (fromNode.y > toNode.y){
            down = true;
        }
        boolean[] leftRightUpDown = {left, right, up, down};
        for (int i = 0; i < booleanArray.length; i++){
            booleanArray[i] = leftRightUpDown[i];
        }
        return booleanArray;
    }
}
