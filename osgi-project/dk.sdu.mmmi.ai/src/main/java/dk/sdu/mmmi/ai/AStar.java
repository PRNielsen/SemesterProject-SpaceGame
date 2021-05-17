/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.ai;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.entitypart.Position;

public class AStar{
    
    NodeGraph nodeGraph;
    GraphPath<Node> nodePath;

    public AStar() {
    }
    
    public NodeGraph create(){
        nodeGraph = new NodeGraph();
        
        Node startNode = new Node(0,0);
        Node aNode = new Node(200,0);
        Node bNode = new Node(400,0);
        Node cNode = new Node(600,0);
        Node dNode = new Node(800,0);
        
        Node eNode = new Node(200,200);
        Node fNode = new Node(200,400);
        Node gNode = new Node(200,600);
        Node hNode = new Node(200,800);
        
        Node iNode = new Node(400,200);
        Node jNode = new Node(400,400);
        Node kNode = new Node(400,600);
        Node lNode = new Node(400,800);
        
        Node mNode = new Node(600,200);
        Node nNode = new Node(600,400);
        Node oNode = new Node(600,600);
        Node pNode = new Node(600,800);
        
        Node qNode = new Node(800,200);
        Node rNode = new Node(800,400);
        Node sNode = new Node(800,600);
        Node tNode = new Node(800,800);
        
        Node uNode = new Node(0, 200);
        Node vNode = new Node(0, 400);
        Node xNode = new Node(0,600);
        Node yNode = new Node(0,800);
        
        nodeGraph.addNode(startNode);
        nodeGraph.addNode(aNode);
        nodeGraph.addNode(bNode);
        nodeGraph.addNode(cNode);
        nodeGraph.addNode(dNode);
        nodeGraph.addNode(eNode);
        nodeGraph.addNode(fNode);
        nodeGraph.addNode(gNode);
        nodeGraph.addNode(hNode);
        nodeGraph.addNode(iNode);
        nodeGraph.addNode(jNode);
        nodeGraph.addNode(kNode);
        nodeGraph.addNode(lNode);
        nodeGraph.addNode(mNode);
        nodeGraph.addNode(nNode);
        nodeGraph.addNode(oNode);
        nodeGraph.addNode(pNode);
        nodeGraph.addNode(qNode);
        nodeGraph.addNode(rNode);
        nodeGraph.addNode(sNode);
        nodeGraph.addNode(tNode);
        nodeGraph.addNode(uNode);
        nodeGraph.addNode(vNode);
        nodeGraph.addNode(xNode);
        nodeGraph.addNode(yNode);
        
        nodeGraph.connectNodes(startNode, aNode);
        nodeGraph.connectNodes(startNode, uNode);
        nodeGraph.connectNodes(startNode, eNode);
        
        nodeGraph.connectNodes(aNode, bNode);
        nodeGraph.connectNodes(aNode, uNode);
        nodeGraph.connectNodes(aNode, eNode);
        nodeGraph.connectNodes(aNode, iNode);
        
        nodeGraph.connectNodes(bNode, cNode);
        nodeGraph.connectNodes(bNode, iNode);
        nodeGraph.connectNodes(bNode, mNode);
        nodeGraph.connectNodes(bNode, eNode);
        
        nodeGraph.connectNodes(cNode, dNode);
        nodeGraph.connectNodes(cNode, mNode);
        nodeGraph.connectNodes(cNode, qNode);
        nodeGraph.connectNodes(cNode, iNode);
        
        nodeGraph.connectNodes(dNode, qNode);
        nodeGraph.connectNodes(dNode, mNode);
        
        nodeGraph.connectNodes(uNode, eNode);
        nodeGraph.connectNodes(uNode, vNode);
        nodeGraph.connectNodes(uNode, fNode);
        
        nodeGraph.connectNodes(vNode, fNode);
        nodeGraph.connectNodes(vNode, xNode);
        nodeGraph.connectNodes(vNode, gNode);
        nodeGraph.connectNodes(vNode, eNode);
        
        nodeGraph.connectNodes(xNode, gNode);
        nodeGraph.connectNodes(xNode, yNode);
        nodeGraph.connectNodes(xNode, hNode);
        nodeGraph.connectNodes(xNode, fNode);
        
        nodeGraph.connectNodes(yNode, hNode);
        nodeGraph.connectNodes(yNode, gNode);
        
        nodeGraph.connectNodes(eNode, fNode);
        nodeGraph.connectNodes(eNode, iNode);
        nodeGraph.connectNodes(eNode, jNode);
        
        nodeGraph.connectNodes(fNode, gNode);
        nodeGraph.connectNodes(fNode, jNode);
        nodeGraph.connectNodes(fNode, kNode);
        nodeGraph.connectNodes(cNode, iNode);
        
        nodeGraph.connectNodes(gNode, hNode);
        nodeGraph.connectNodes(gNode, kNode);
        nodeGraph.connectNodes(gNode, lNode);
        nodeGraph.connectNodes(gNode, jNode);
        
        nodeGraph.connectNodes(hNode, lNode);
        nodeGraph.connectNodes(hNode, kNode);
        
        nodeGraph.connectNodes(iNode, jNode);
        nodeGraph.connectNodes(iNode, mNode);
        nodeGraph.connectNodes(iNode, nNode);
        
        nodeGraph.connectNodes(jNode, kNode);
        nodeGraph.connectNodes(jNode, nNode);
        nodeGraph.connectNodes(jNode, mNode);
        nodeGraph.connectNodes(jNode, oNode);
        
        nodeGraph.connectNodes(kNode, lNode);
        nodeGraph.connectNodes(kNode, nNode);
        nodeGraph.connectNodes(kNode, pNode);
        nodeGraph.connectNodes(kNode, oNode);
        
        nodeGraph.connectNodes(lNode, pNode);
        nodeGraph.connectNodes(lNode, oNode);
        
        nodeGraph.connectNodes(mNode, nNode);
        nodeGraph.connectNodes(mNode, qNode);
        nodeGraph.connectNodes(mNode, rNode);
        
        nodeGraph.connectNodes(nNode, oNode);
        nodeGraph.connectNodes(nNode, rNode);
        nodeGraph.connectNodes(fNode, sNode);
        nodeGraph.connectNodes(fNode, qNode);
        
        nodeGraph.connectNodes(oNode, pNode);
        nodeGraph.connectNodes(oNode, sNode);
        nodeGraph.connectNodes(oNode, tNode);
        nodeGraph.connectNodes(oNode, rNode);
        
        nodeGraph.connectNodes(pNode, sNode);
        nodeGraph.connectNodes(pNode, tNode);
        
        nodeGraph.connectNodes(qNode, rNode);
        
        nodeGraph.connectNodes(rNode, sNode);
        
        nodeGraph.connectNodes(sNode, tNode);
        
        
//        goalNode = new Node(goalNodeX, goalNodeY);
        nodePath = nodeGraph.findPath(startNode, tNode);
        
        return nodeGraph;
    }
    
    public Node getGoal(Entity player){
//        Entity player = world.getEntities(Player.class).get(0);
        Position targetPosition = player.getPart(Position.class);
        Node target = new Node(targetPosition.getX(),targetPosition.getY());
       
        Node goalNode;
        float goalNodeX = 0;
        float goalNodeY =0;
        float smallestDist = 50;
        for (Node x : nodeGraph.getNodes()){
            Node tempNode = x;
            float tempDist = Vector2.dst(tempNode.x, tempNode.y, target.x, target.y);
            if (tempDist < smallestDist){
                smallestDist = tempDist;
                goalNodeX = tempNode.x;
                goalNodeY = tempNode.y;
            }
        }
        goalNode = new Node(goalNodeX, goalNodeY);
        return goalNode;
    }
    
}
