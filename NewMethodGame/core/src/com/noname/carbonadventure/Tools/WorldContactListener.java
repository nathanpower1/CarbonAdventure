package com.noname.carbonadventure.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.noname.carbonadventure.Sprites.InteractiveTileObject;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Sprites.NPC;


public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

            Object userDataA = fixA.getUserData();
            Object userDataB = fixB.getUserData();

        // Get the category bits of fixtures
        short categoryA = fixA.getFilterData().categoryBits;
        short categoryB = fixB.getFilterData().categoryBits;

            Gdx.app.log("Contact", "Begin Contact between " + userDataA + " and " + userDataB);
            Gdx.app.log("Contact", "Begin Contact between " + categoryA + " and " + categoryB);

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        // Handle cases where user data is null
        if (userDataA != null && userDataB != null) {
            switch (cDef){
                case Play.NPC_BIT | Play.PLAYER_BIT:
                    if(fixA.getFilterData().categoryBits == Play.NPC_BIT)
                        ((NPC) fixA.getUserData()).BodyHit();
                    else if(fixB.getFilterData().categoryBits == Play.NPC_BIT)
                        ((NPC) fixB.getUserData()).BodyHit();
            }
        } else {
            Gdx.app.error("Contact", "One of the fixtures has null user data.");
        }
        if (userDataA != null && userDataB != null) {
        if(fixA.getUserData() == "Player_body" || fixB.getUserData() == "Player_body"){
            Fixture body = fixA.getUserData() == "Player_body" ? fixA : fixB;
            Fixture object = body == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).OnBodyHit();
            }
        }
        } else {
            Gdx.app.error("Contact", "One of the fixtures has null user data.");
        }

        switch (cDef) {
            case Play.NPC_BIT | Play.PLAYER_BIT:
                if (fixA.getFilterData().categoryBits == Play.NPC_BIT &&
                        fixB.getFilterData().categoryBits == Play.PLAYER_BIT)
                    ((NPC) fixA.getUserData()).BodyHit();
                else if (fixB.getFilterData().categoryBits == Play.NPC_BIT &&
                        fixA.getFilterData().categoryBits == Play.PLAYER_BIT)
                    ((NPC) fixB.getUserData()).BodyHit();
                break;
            case Play.NPC_BIT | Play.OBJECT_BIT :
                if (fixA.getFilterData().categoryBits == Play.NPC_BIT)
                    ((NPC) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((NPC) fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Play.NPC_BIT | Play.GEM_BIT :
                if (fixA.getFilterData().categoryBits == Play.NPC_BIT)
                    ((NPC) fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((NPC) fixB.getUserData()).reverseVelocity(false, true);
                break;
            case Play.NPC_BIT | Play.NPC_BIT :
                ((NPC) fixA.getUserData()).reverseVelocity(true, false);
                ((NPC) fixB.getUserData()).reverseVelocity(true, false);
                break;


            }
        }



    @Override
    public void endContact(Contact contact) {



    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
