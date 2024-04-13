package com.noname.carbonadventure.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.Vector2;
import com.noname.carbonadventure.Sprites.Player;
import com.sun.tools.javac.util.Convert;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


public class MiniMap {
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Image mapImage;
    private Image playerMarker;

    private float worldSize;
    private float miniMapSize;
    private Vector2 miniMapTopLeftCorner;

    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;


    public MiniMap(SpriteBatch batch, float worldWidth, float worldHeight, float viewportWidth, float viewportHeight) {
        this.worldSize = Math.max(worldWidth, worldHeight);
        this.miniMapSize = Math.min(viewportWidth, viewportHeight);


        camera = new OrthographicCamera();
        this.camera.zoom = 15f;

        viewport = new FitViewport(viewportWidth, viewportHeight, camera);
        stage = new Stage(viewport, batch);

        //Texture mapTexture = new Texture("img/miniMap.png");
        //mapImage = new Image(mapTexture);
        tiledMap = new TmxMapLoader().load("maps/Level_1.0.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 45f / miniMapSize, batch);

        //stage.addActor(mapImage);

        Texture playerTexture = new Texture("img/gem.png");
        playerMarker = new Image(playerTexture);
        //stage.addActor(playerMarker);

        float margin = 1f;
        float miniMapWidth = viewportWidth;
        float miniMapHeight = viewportHeight;
        miniMapTopLeftCorner = new Vector2(Gdx.graphics.getWidth() + 50 - margin, Gdx.graphics.getHeight() + 200 - margin);

        viewport.setWorldSize(viewportWidth, viewportHeight);
        //mapImage.setPosition(miniMapTopLeftCorner.x, miniMapTopLeftCorner.y);


        //miniMapTopLeftCorner = new Vector2(-1250, -1250);
    }

    public void updatePlayerPosition(Player player) {
        Vector2 playerWorldPos = player.b2body.getPosition();

        //Convert world position to mini-map coordinates
        Vector2 miniMapPos = new Vector2(
               (playerWorldPos.x * miniMapSize) / worldSize,
                (playerWorldPos.y * miniMapSize) / worldSize);

        playerMarker.setPosition(miniMapTopLeftCorner.x + miniMapPos.x, miniMapTopLeftCorner.y + miniMapPos.y);
    }

    public void render() {
        camera.position.set(miniMapTopLeftCorner.x + miniMapSize / 2, miniMapTopLeftCorner.y + miniMapSize / 2, 0);
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();

        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(miniMapTopLeftCorner.x + miniMapSize / 2, miniMapTopLeftCorner.y + miniMapSize / 2, 0);
        camera.update();
    }

}

