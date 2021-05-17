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
public interface WorldPart {
    void process(GameData gameData, WorldMap worldMap);
}
