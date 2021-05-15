package asset.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.mmmi.common.data.World;
/**
 *
 * @author asbjo
 */
public interface IDrawingService {
    void render(SpriteBatch sb, World world);
}
