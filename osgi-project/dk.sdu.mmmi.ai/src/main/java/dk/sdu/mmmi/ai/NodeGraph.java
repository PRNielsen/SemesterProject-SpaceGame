/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.ai;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class NodeGraph implements IndexedGraph<Node>{
    
    NodeHeuristic nodeHeuristic = new NodeHeuristic();
    Array<Node> nodes = new Array<>();
    Array<Direction> directions = new Array<>();
    
    //Map of Nodes to Directions starting in that Node.
    ObjectMap<Node, Array<Connection<Node>>> directionMap = new ObjectMap<>();
    
    private int lastNodeIndex = 0;
    
    public void addNode(Node node){
        node.index = lastNodeIndex;
        lastNodeIndex++;
        
        nodes.add(node);
    }

    public Array<Node> getNodes() {
        return nodes;
    }
    
    public void connectNodes(Node fromNode, Node toNode){
        Direction direction = new Direction(fromNode, toNode);
        if(!directionMap.containsKey(fromNode)){
            directionMap.put(fromNode, new Array<>());
        }
        directionMap.get(fromNode).add(direction);
        directions.add(direction);
    }
    
    public GraphPath<Node> findPath(Node startNode, Node goalNode){
        GraphPath<Node> nodePath = new DefaultGraphPath<>();
        new IndexedAStarPathFinder<>(this).searchNodePath(startNode, goalNode, nodeHeuristic, nodePath);
        return nodePath;
    }

    @Override
    public int getIndex(Node n) {
        return n.index;
    }

    @Override
    public int getNodeCount() {
        return lastNodeIndex;
    }

    @Override
    public Array<Connection<Node>> getConnections(Node fromNode) {
        if(directionMap.containsKey(fromNode)){
            return directionMap.get(fromNode);
        }
        return new Array<>(0);
    }
}
