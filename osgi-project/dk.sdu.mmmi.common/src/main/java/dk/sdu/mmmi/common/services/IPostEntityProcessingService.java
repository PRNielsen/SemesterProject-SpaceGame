package dk.sdu.mmmi.common.services;

import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;

/**
 *
 * @author jcs from repository: PaxAsteroids/OSGiCommon/dk/sdu/mmmi/cbse/common/data/services/IPostEntityProcessingService
 */
public interface IPostEntityProcessingService  {
        void process(GameData gameData, World world);
}
