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
    private Array<Fella> fellas;
    private Array<Geezer> geezers;
    private Array<Cowboy> cowboys;

    private Array<CowboyDuel> cowboyduel;



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
            } else if ("Fellas".equals(layer.getName())) {
                fellas = new Array<Fella>();
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    fellas.add(new Fella(screen, rect.getX() / Play.PPM, rect.getY() / Play.PPM));
                }
            } else if ("Geezers".equals(layer.getName())) {
                geezers = new Array<Geezer>();
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    geezers.add(new Geezer(screen, rect.getX() / Play.PPM, rect.getY() / Play.PPM));
                }
            } else if ("Cowboys".equals(layer.getName())) {
                cowboys = new Array<Cowboy>();
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    cowboys.add(new Cowboy(screen, rect.getX() / Play.PPM, rect.getY() / Play.PPM));
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
                    new Finish2(screen, rect);
                }
            } else if ("Barricade2".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Barricade2(screen, rect);
                }

            }else if ("Gems2".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Gem2(screen, rect);
                }
            }else if ("Finish3".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Finish3(screen, rect);
                }
            } else if ("Barricade3".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Barricade3(screen, rect);
                }

            }else if ("Gems3".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Gem3(screen, rect);
                }
            }else if ("Finish4".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Finish4(screen, rect);
                }
            } else if ("Barricade4".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Barricade4(screen, rect);
                }

            }else if ("Gems4".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Gem4(screen, rect);
                }
            }else if ("CowboyDuel".equals(layer.getName())) {
                cowboyduel = new Array<CowboyDuel>();
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    cowboyduel.add(new CowboyDuel(screen, rect.getX() / Play.PPM, rect.getY() / Play.PPM));
                }
            }
        }
    }

    public Array<Dude> getDudes() {
        return dudes;
    }
    public Array<NPC> getNPCs(){
        Array<NPC> npcs = new Array<NPC>();
        npcs.addAll(dudes);
        npcs.addAll(fellas);
        npcs.addAll(geezers);
        npcs.addAll(cowboys);
        npcs.addAll(cowboyduel);

        return npcs;
    }
}
