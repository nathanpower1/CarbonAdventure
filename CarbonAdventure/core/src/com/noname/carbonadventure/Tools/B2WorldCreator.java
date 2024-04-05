package com.noname.carbonadventure.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Sprites.Gem;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // create walls objects
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ Play.PPM,(rect.getY()+rect.getHeight()/2)/ Play.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2 / Play.PPM,rect.getHeight()/2 / Play.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // create Gems objects
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

           new Gem(world, map, rect);
        }


    }
}
