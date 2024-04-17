package com.noname.carbonadventure.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.*;
import com.noname.carbonadventure.Sprites.NPC;
import com.noname.carbonadventure.Sprites.Player;
import com.noname.carbonadventure.Tools.B2WorldCreator;
import com.noname.carbonadventure.Tools.WorldContactListener;
import com.noname.carbonadventure.Sprites.Car;


import static com.noname.carbonadventure.Scenes.HUD.stage;

public class PlayScreen implements Screen {
    private Play game;
    private TextureAtlas atlas;
    private TextureAtlas NPCatlas;
    private TextureAtlas fellaAtlas;
    private TextureAtlas cowboyAtlas;

    private TextureAtlas geezerAtlas;

    private TextureAtlas UIatlas;
    private HUD hud;
    private OrthographicCamera gamecam;

    private Viewport gamePort;

    private TmxMapLoader loader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    private Player player;

    private Music music;

    private MiniMap miniMap;

    private boolean isMiniMapVisible = false;
    private int mapWidth;
    private int mapHeight;

    private GameMenu gameMenu;

    private Dialogue currentDialogue;

    private Rectangle currentBusStopBounds;

    private Car car;

    private Sprite currentCharacter;

    private Vector2 playerLastPosition = new Vector2();
    private Vector2 carLastPosition = new Vector2();

    private PlayerNameDisplay playerNameDisplay;





    public PlayScreen(Play game){
        atlas = new TextureAtlas("player.atlas");
        NPCatlas = new TextureAtlas("NPC.atlas");
        fellaAtlas = new TextureAtlas("fella.atlas");
        geezerAtlas = new TextureAtlas("geezer.atlas");
        cowboyAtlas = new TextureAtlas("cowboy.atlas");
        UIatlas = new TextureAtlas("ui.atlas");

        this.game = game;
        //create cam to follow player
        gamecam = new OrthographicCamera();
        //create fitview to maintain aspect ratio
        gamePort = new ExtendViewport(Play.V_WIDTH / Play.PPM,Play.V_HEIGHT / Play.PPM,gamecam);
        stage = new Stage(new ExtendViewport(800, 480), game.batch);

        Skin uiSkin = new Skin(Gdx.files.internal("data/uiskin.json")); // Ensure this skin is loaded

        playerNameDisplay = new PlayerNameDisplay(game, stage, uiSkin);
        Gdx.app.log("Debug", "Retrieved Name: " + game.getPlayerName());
        // create HUD
        //hud = new HUD(game.batch);


        //create map
        loader = new TmxMapLoader();
        map = loader.load("maps/Level_X.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ Play.PPM);

        //initial starting camera position
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2 ,0);

        world = new World(new Vector2(),true);
        b2dr = new Box2DDebugRenderer();
        b2dr.setDrawBodies(false);

        creator = new B2WorldCreator(this);

        player = new Player(this);
        Play.player = player;
        car = new Car(world);
        currentCharacter = player;

        // create HUD
        hud = new HUD(game.batch , player);

        gameMenu = new GameMenu(game);

        world.setContactListener(new WorldContactListener());

        miniMap = new MiniMap(game.batch, 2000, 2000, 100, 100);

        gamecam.zoom = 1;

        gamecam.update();

        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);



    }

    public Stage getStage() {
        return stage;
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }
    public TextureAtlas getNPCAtlas(){
        return NPCatlas;
    }

    public TextureAtlas getFellaAtlas(){
        return fellaAtlas;
    }
    public TextureAtlas getCowboyAtlas(){
        return cowboyAtlas;
    }


    public TextureAtlas getGeezerAtlas(){
        return geezerAtlas;
    }

    public TextureAtlas getUIatlas(){
        return UIatlas;
    }

    @Override
    public void show() {


    }

    public Player getPlayer() {
        return player;
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
        if (currentCharacter.equals(player)) {
            player.draw(game.batch);
        } else if (currentCharacter.equals(car)) {
            car.draw(game.batch);
        }
        for (NPC npc : creator.getNPCs())
            npc.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(stage.getCamera().combined);

        stage.draw();
        gameMenu.render(delta);

        if(gameOver()){
            game.setScreen(new GameOverScreen(game));
            dispose();
        }


            if (isMiniMapVisible) {
                miniMap.render(); // Use the instance method here
            }

        stage.act(delta); 
        stage.draw();


    }

    public boolean gameOver(){
        if(player.currentState == Player.State.DEAD && player.getStateTimer() > 3){
            return true;
        }
        return false;
    }

    public void handleInput(float dt) {
        // Toggle between player and car unless the player is dead
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            if (currentCharacter.equals(player)) {
                Vector2 currentPosition = new Vector2(player.b2body.getPosition());

                currentCharacter = car;
                car.b2body.setTransform(currentPosition, 0);
                car.setPosition(currentPosition.x - car.getWidth() / 2, currentPosition.y - car.getHeight() / 2);

            } else {
                Vector2 currentPosition = new Vector2(car.b2body.getPosition());

                currentCharacter = player;
                player.b2body.setTransform(currentPosition, 0);
                player.setPosition(currentPosition.x - player.getWidth() / 2, currentPosition.y - player.getHeight() / 2);
            }
        }


        // Exit early if the player is dead, no further controls should be processed
        if (player.currentState == Player.State.DEAD) {
            return;
        }

        // Prepare to accumulate direction based on key presses
        Vector2 direction = new Vector2();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction.y += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction.y -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction.x += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction.x -= 1;
        }

        // Normalize the direction vector to avoid faster diagonal movement
        if (direction.len() > 0) {
            direction.nor();
        }

        // Apply movement mechanics based on the current active character
        if (currentCharacter instanceof Player) {
            // Adjust for Player's specific speed or movement characteristics
            float playerSpeed = 0.5f;
            ((Player) currentCharacter).b2body.setLinearVelocity(direction.scl(playerSpeed));
        } else if (currentCharacter instanceof Car) {

            ((Car) currentCharacter).accelerate(direction);
        }

        // Toggle the minimap visibility
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            isMiniMapVisible = !isMiniMapVisible;
        }
    }

    public void updateCamera(float delta) {
        Vector2 position;
        if (currentCharacter instanceof Player) {
            position = ((Player) currentCharacter).b2body.getPosition();
        } else if (currentCharacter instanceof Car) {
            position = ((Car) currentCharacter).b2body.getPosition();
        } else {
            return;
        }

        float camHalfWidth = gamecam.viewportWidth * 0.5f;
        float camHalfHeight = gamecam.viewportHeight * 0.5f;

        float mapWidthInUnits = mapWidth / Play.PPM;
        float mapHeightInUnits = mapHeight / Play.PPM;

        float clampedX = MathUtils.clamp(position.x, camHalfWidth, mapWidthInUnits - camHalfWidth);
        float clampedY = MathUtils.clamp(position.y, camHalfHeight, mapHeightInUnits - camHalfHeight);

        gamecam.position.set(clampedX, clampedY, 0);
        gamecam.update();
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f,6,2);

        player.update(dt);
        car.update(dt);
        for (NPC npc : creator.getNPCs()){
            npc.update(dt);
            if (npc.getX() < player.getX() + 1)
                npc.b2body.setActive(true);
        }
        hud.update(dt);

        if(player.currentState != Player.State.DEAD){
            gamecam.position.x= player.b2body.getPosition().x;
            gamecam.position.y= player.b2body.getPosition().y;

        }

       // gamecam.position.x= player.b2body.getPosition().x;
       // gamecam.position.y= player.b2body.getPosition().y;

        updateCamera(dt);
        gamecam.update();
        renderer.setView(gamecam);

        Vector2 velocity = player.b2body.getLinearVelocity();
        float maxSpeed = 1f;
        if (velocity.len() > maxSpeed) {
            velocity = velocity.nor().scl(maxSpeed);
            player.b2body.setLinearVelocity(velocity);
        }

        if (currentCharacter.equals(player)) {
            player.update(dt);
        } else if (currentCharacter.equals(car)) {
            car.update(dt);
        }

    }

    public void teleportPlayer(Player player, float destinationX, float destinationY) {
        // Schedule the teleportation to happen after the current physics step
        Gdx.app.postRunnable(() -> {
            // Set the new position of the player's Box2D body
            player.b2body.setTransform(destinationX, destinationY, player.b2body.getAngle());

            // Update the position of the sprite to match the new position of the Box2D body
            player.setPosition(destinationX - player.getWidth() / 2, destinationY - player.getHeight() / 2);
        });
    }


    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
        stage.getViewport().update(width, height, true);
        gameMenu.resize(width, height);




    }

    public  TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
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
        gameMenu.dispose();



    }



    }




