package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Scenes.HUD;
import com.noname.carbonadventure.Screens.PlayScreen;
import com.noname.carbonadventure.Screens.*;


public class Finish4 extends InteractiveTileObject {
    private PlayScreen screen;
    private Play game;

    public static boolean playerFinish = false;

    private GameOverScreen gameoverscreen;

    public Finish4(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Play.GEM_BIT);
    }

    @Override
    public void OnBodyHit() {
        int finalscore = HUD.getScore();
        System.out.println(finalscore);
        playerFinish = true;

        Gdx.app.log("Finish", "Finish line has been triggered.");
        // 490/Play.PPM,3180/Play.PPM
    }
}
