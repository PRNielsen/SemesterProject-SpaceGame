package dk.sdu.mmmi.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import dk.sdu.mmmi.common.data.Entity;
import dk.sdu.mmmi.common.data.GameData;
import dk.sdu.mmmi.common.data.World;
import dk.sdu.mmmi.common.data.WorldMap;
import dk.sdu.mmmi.common.data.entitypart.Position;
import dk.sdu.mmmi.common.data.entitypart.Asset;
import dk.sdu.mmmi.common.data.worldpart.WorldMapAsset;
import dk.sdu.mmmi.common.services.IEntityProcessingService;
import dk.sdu.mmmi.common.services.IGamePluginService;
import dk.sdu.mmmi.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.common.services.IWorldMapProcessingService;
import dk.sdu.mmmi.core.assetloading.AssetLoader;
import dk.sdu.mmmi.core.assetloading.AssetsJarFileResolver;
import dk.sdu.mmmi.core.managers.GameInputProcessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements ApplicationListener {
    
    private AssetLoader al;
    private static OrthographicCamera cam;
    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private FitViewport viewport;
    private ShapeRenderer sr;
    private SpriteBatch batch;
    private Sprite sprite;
    private Texture tex;
    private Texture backgroundTex;
    private String entityClass;
    private String worldMapClass;
    private String[] mapAssetList;
    private String tmpMapString;
    private final GameData gameData = new GameData();
    private static World world = new World();
    private static final List<IEntityProcessingService> entityProcessorList = new CopyOnWriteArrayList<>();
    private static final List<IGamePluginService> gamePluginList = new CopyOnWriteArrayList<>();
    private static List<IPostEntityProcessingService> postEntityProcessorList = new CopyOnWriteArrayList<>();
    private static List<IWorldMapProcessingService> worldMapList = new CopyOnWriteArrayList<>();
    private Map<String , Sprite > spriteMap;
    

    public Game(){
        init();
    }

    public void init() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Required-< <>-Interfaces";
        cfg.width = 800;
        cfg.height = 800;
        cfg.useGL30 = false;
        cfg.resizable = false;

        new LwjglApplication(this, cfg);
    }

    @Override
    public void create() {
   
        gameData.setDisplayWidth(Gdx.graphics.getWidth()/2);
        gameData.setDisplayHeight(Gdx.graphics.getHeight()/2);

        
        cam = new OrthographicCamera();
        viewport = new FitViewport(256, 256, cam);   
        cam.update();

        sr = new ShapeRenderer();
        batch = new SpriteBatch();
        al = new AssetLoader();
        spriteMap = new HashMap<>();
        AssetsJarFileResolver jfhr = new AssetsJarFileResolver();
        AssetManager am = new AssetManager(jfhr);
        am.setLoader(TiledMap.class, new TmxMapLoader());
        
        String assetPath = "";
        
        // Map assets loaded into GameEngine
        for (WorldMap worldMap : world.getWorldMaps()) {
            WorldMapAsset worldAssetPart = worldMap.getPart(WorldMapAsset.class);
            // Get .txt file with the file names and file class. 
            assetPath = al.getJarUrl(worldAssetPart.getAssetName(),
                    worldAssetPart.getJarUrl(),
                    worldAssetPart.getIdentifier()
            );
            am.load(assetPath, Texture.class);
            am.finishLoading();
            backgroundTex = am.get(assetPath, Texture.class);
        }

        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        cam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
      
        // Entity rendering assets loaded into GameEngine
        for (Entity entity : world.getEntities()) {
            entityClass = entity.getClass().toString();
            if (!spriteMap.containsKey(entityClass)) {
                Asset assetPart = entity.getPart(Asset.class);
                System.out.println(assetPart.getAssetName());
                System.out.println(assetPart.getJarUrl());
                System.out.println(assetPart.getIdentifier());
                assetPath = al.getJarUrl(assetPart.getAssetName(), 
                    assetPart.getJarUrl(), 
                    assetPart.getIdentifier()
                );
                am.load(assetPath, Texture.class);
                am.finishLoading();
                tex = am.get(assetPath, Texture.class);
                sprite = new Sprite(tex);
                spriteMap.put(entityClass, sprite);
            }                   
        }
        
        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));
        
        for (IGamePluginService plugin : gamePluginList) {
            plugin.start(gameData, world);
        }

    }

    @Override
    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();

        update();
        draw();
    }

    private void update() {
        
        // Update
        for (IEntityProcessingService entityProcessorService : entityProcessorList) {
            entityProcessorService.process(gameData, world);
        }

        // Post Update
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessorList) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        
        batch.begin();
        batch.draw(backgroundTex, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());   
        batch.end();
        for (Entity entity : world.getEntities()) {

            Position positionPart = entity.getPart(Position.class);
            float x = positionPart.getX();
            float y = positionPart.getY();
            
            cam.update();
            mapRenderer.setView(cam);
            
            // 
            entityClass = entity.getClass().toString();
            sprite = spriteMap.get(entityClass);
            //sprite.setCenter(x, y);
            sprite.setOrigin(x,y);
            
            batch.begin();
            batch.draw(sprite, x - 25, y - 25, 50, 50);
            batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void addEntityProcessingService(IEntityProcessingService eps) {
        this.entityProcessorList.add(eps);
    }

    public void removeEntityProcessingService(IEntityProcessingService eps) {
        this.entityProcessorList.remove(eps);
    }

    public void addPostEntityProcessingService(IPostEntityProcessingService eps) {
        postEntityProcessorList.add(eps);
    }

    public void removePostEntityProcessingService(IPostEntityProcessingService eps) {
        postEntityProcessorList.remove(eps);
    }

    public void addGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.add(plugin);
        plugin.start(gameData, world);

    }

    public void removeGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.remove(plugin);
        plugin.stop(gameData, world);
    }

}
