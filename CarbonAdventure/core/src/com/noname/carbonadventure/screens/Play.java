package com.noname.carbonadventure.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.noname.carbonadventure.entities.Player;
import com.noname.carbonadventure.entities.Gem;
import com.badlogic.gdx.math.Vector2;

public class Play implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private SpriteBatch spriteBatch;
    private Player player;
    private Texture gemTexture;
    private Gem gem;
    private AnimationManager animationManager;


    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/Level_1.1.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        animationManager = new AnimationManager();
        Vector2 startPosition = new Vector2(340, 340);
        AnimationManager animationManager = new AnimationManager();
        this.player = new Player(startPosition, animationManager);
        gemTexture = new Texture(Gdx.files.internal("img/gem.png"));
        gem = new Gem(gemTexture, new Vector2(-10, -10));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update game logic here (e.g., player movement)
        player.update(delta);

        // Make the camera follow the player
        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        camera.update();

        renderer.setView(camera);
        renderer.render();


        renderer.setView(camera);
        renderer.render();

        renderer.getBatch().begin();
        SpriteBatch spriteBatch = (SpriteBatch) renderer.getBatch();
        player.draw(spriteBatch);
        renderer.getBatch().end();
    }


    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        player.getTexture().dispose();
        gemTexture.dispose();

    }
}

