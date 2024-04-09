package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Sprites.Player;
import com.noname.carbonadventure.Tools.B2WorldCreator;
import com.noname.carbonadventure.Screens.MainMenuScreen;

public class PlayScreen implements Screen {
    private Play game;
    private TextureAtlas atlas;
    private HUD hud;
    private OrthographicCamera gamecam;

    private Viewport gamePort;

    private TmxMapLoader loader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;




    public PlayScreen(Play game){
        atlas = new TextureAtlas("player.atlas");
        this.game = game;
        //create cam to follow player
        gamecam = new OrthographicCamera();
        //create fitview to maintain aspect ratio
        gamePort = new FitViewport(Play.V_WIDTH / Play.PPM,Play.V_HEIGHT / Play.PPM,gamecam);

        // create HUD
        hud = new HUD(game.batch);

        //create map
        loader = new TmxMapLoader();
        map = loader.load("maps/Level_1.1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ Play.PPM);

        //initial starting camera position
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2 ,0);

        world = new World(new Vector2(),true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world,map);

        player = new Player(world, this);

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render game map
        renderer.render();

        // render our Box2DDebugLines
        b2dr.render(world,gamecam.combined);



        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();



    }

    public void handleInput(float dt){
        //up
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y <=2)
            player.b2body.applyLinearImpulse(new Vector2(0,1f), player.b2body.getWorldCenter(),true);
        //down
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)  && player.b2body.getLinearVelocity().y >= -2)
            player.b2body.applyLinearImpulse(new Vector2(0,-1f), player.b2body.getWorldCenter(),true);
        //right
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)  && player.b2body.getLinearVelocity().x <=2)
            player.b2body.applyLinearImpulse(new Vector2(1f,0), player.b2body.getWorldCenter(),true);
        //right
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)  && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-1f,0), player.b2body.getWorldCenter(),true);



    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f,6,2);

        player.update(dt);

        gamecam.position.x= player.b2body.getPosition().x;
        gamecam.position.y= player.b2body.getPosition().y;

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();


    }
}
