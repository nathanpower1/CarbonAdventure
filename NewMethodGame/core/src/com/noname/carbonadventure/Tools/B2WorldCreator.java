package com.noname.carbonadventure.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;
import com.noname.carbonadventure.Sprites.*;

public class B2WorldCreator {
    private Array<Dude> dudes;

    public B2WorldCreator(PlayScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (MapLayer layer : map.getLayers()) {
            if ("Walls".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Walls(screen, rect);
                }
            } else if ("Gems".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Gem(screen, rect);
                }
            } else if ("Garbage".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Garbage(screen, rect);
                }
            } else if ("Bus_Stop".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Bus_Stop(screen, rect);
                }
            } else if ("Dudes".equals(layer.getName())) {
                dudes = new Array<Dude>();
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    dudes.add(new Dude(screen, rect.getX() / Play.PPM, rect.getY() / Play.PPM));
                }
            } else if ("Finish".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Finish(screen, rect);
                }
            } else if ("Barricade".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Barricade(screen, rect);
                }

            }else if ("Finish2".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Finish(screen, rect);
                }
            } else if ("Barricade2".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Barricade(screen, rect);
                }

            }else if ("Gems2".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Gem(screen, rect);
                }
            }else if ("Finish3".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Finish(screen, rect);
                }
            } else if ("Barricade3".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Barricade(screen, rect);
                }

            }else if ("Gems4".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Gem(screen, rect);
                }
            }else if ("Finish4".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Finish(screen, rect);
                }
            } else if ("Barricade4".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Barricade(screen, rect);
                }

            }else if ("Gems4".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Gem(screen, rect);
                }
            }
        }
    }

    public Array<Dude> getDudes() {
        return dudes;
    }
}
