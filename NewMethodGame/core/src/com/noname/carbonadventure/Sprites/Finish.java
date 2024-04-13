package com.noname.carbonadventure.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;

public class Finish extends InteractiveTileObject {
    private PlayScreen screen;

    public Finish(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Play.FINISH_BIT);
    }

    @Override
    public void OnBodyHit() {
        Gdx.app.log("Finish", "Finish line has been triggered.");
    }
}


