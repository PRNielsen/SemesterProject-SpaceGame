/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.room;

import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;

/**
 *
 * @author asbjo
 */
interface WorldMapProcessingService {
    public void process(GameData gameData, World world);
}
