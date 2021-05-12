/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.common.data.entitypart;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;

/**
 *
 * @author Daniel Tran
 */
public class Combat implements EntityPart {
    int atkDmg;
    int range = 1;
    
    
    public Combat(int atkDmg, int range){
        this.atkDmg = atkDmg;
        this.range = range;
    }
    
    public int getAtkDmg(){
        return this.atkDmg;
    }
    
    public void setAtkDmg(int atkDmg){
        this.atkDmg = atkDmg;
    }
    
    public int getRange(){
        return this.range;
    }
    
     public void setRange(int range){
        this.range = range;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        
        
    }
    
}
