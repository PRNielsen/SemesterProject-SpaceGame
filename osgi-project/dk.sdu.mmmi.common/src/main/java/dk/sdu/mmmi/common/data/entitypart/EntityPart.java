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
 * @author Alexander from repository: PaxAsteroids/OSGiCommon/dk/sdu/mmmi/cbse/common/data/entityparts/EntityPart
 */
public interface EntityPart {
    void process(GameData gameData, Entity entity);
}
