package dk.sdu.mmmi.core.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.GameKeys;

public class GameInputProcessor extends InputAdapter {
    private final GameData gameData;

    public GameInputProcessor(GameData gameData) {
        this.gameData = gameData;
    }

    public boolean keyDown(int k) {
        if(k == Keys.W) {
            gameData.getKeys().setKey(GameKeys.UP, true);
        }
        if(k == Keys.A) {
            gameData.getKeys().setKey(GameKeys.LEFT, true);
        }
        if(k == Keys.S) {
            gameData.getKeys().setKey(GameKeys.DOWN, true);
        }
        if(k == Keys.D) {
            gameData.getKeys().setKey(GameKeys.RIGHT, true);
        }
        if(k == Keys.SPACE) {
            gameData.getKeys().setKey(GameKeys.SPACE, true);
        }
        return true;
    }
	
    public boolean keyUp(int k) {
        if(k == Keys.W) {
            gameData.getKeys().setKey(GameKeys.UP, false);
        }
        if(k == Keys.A) {
            gameData.getKeys().setKey(GameKeys.LEFT, false);
        }
        if(k == Keys.S) {
            gameData.getKeys().setKey(GameKeys.DOWN, false);
        }
        if(k == Keys.D) {
            gameData.getKeys().setKey(GameKeys.RIGHT, false);
        }
        if(k == Keys.SPACE) {
            gameData.getKeys().setKey(GameKeys.SPACE, false);
        }
        return true;
    }
}








