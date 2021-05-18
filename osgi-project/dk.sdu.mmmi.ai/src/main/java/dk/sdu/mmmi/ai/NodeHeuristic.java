/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.ai;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;

public class NodeHeuristic implements Heuristic<Node>{

    @Override
    public float estimate(Node currentNode, Node goalNode) {
        return Vector2.dst(currentNode.x, currentNode.y, goalNode.x, goalNode.y);
    }
}
