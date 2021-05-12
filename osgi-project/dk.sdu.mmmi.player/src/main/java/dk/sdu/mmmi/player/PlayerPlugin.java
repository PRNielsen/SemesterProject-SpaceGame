package dk.sdu.mmmi.player;

import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.entitypart.Health;
import dk.sdu.mmmi.common.data.entitypart.Movement;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.services.IGamePluginService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

//@ServiceProviders(value = {
   // @ServiceProvider(service = IGamePluginService.class),})
public class PlayerPlugin implements IGamePluginService {
    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayer(gameData);
        world.addEntity(player);
    }

    private Entity createPlayer(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = /*gameData.getDisplayWidth()*/ + 400;
        float y = /*gameData.getDisplayHeight()*/ + 300;
        float radians = 3.1415f / 2;

        Entity player = new Player();
        player.setRadius(8);
        player.add(new Movement(deacceleration, acceleration, maxSpeed, rotationSpeed));
        player.add(new Position(x, y, radians));
        player.add(new Health(1));

        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
