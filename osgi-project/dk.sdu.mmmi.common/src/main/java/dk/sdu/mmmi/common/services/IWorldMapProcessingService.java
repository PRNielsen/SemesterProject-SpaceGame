package dk.sdu.mmmi.common.services;

import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;

/**
 *
 * @author asbjo
 */
public interface IWorldMapProcessingService {
    void process(GameData gameData, World world);
}
