package com.noname.carbonadventure.Tools;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.noname.carbonadventure.Play;
import com.noname.carbonadventure.Screens.PlayScreen;
import com.noname.carbonadventure.Sprites.*;

import java.util.List;

public class B2WorldCreator {
    private Array<Dude> dudes;
    private Array<Fella> fellas;
    private Array<Geezer> geezers;
    private Array<Cowboy> cowboys;
    private Array<Train> trains;
    private Array<Elvis> elvis;

    private PlayScreen playScreen;

    private Array<CowboyDuel> cowboyduel;

    public B2WorldCreator(PlayScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        this.playScreen = screen;

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
            } else if ("Cheeseburger".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Cheeseburger(screen, rect);
                }
            } else if ("Paths".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Path(screen, rect);
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
            } else if ("Bus_Stop_level2".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Bus_Stop_Level2(screen, rect);
                }
            }else if ("Bus_Stop_level3".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Bus_Stop_Level3(screen, rect);
                }
            }else if ("Bus_Stop_level4".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Bus_Stop_Level4(screen, rect);
                }
            }else if ("Dudes".equals(layer.getName())) {
                dudes = new Array<Dude>();
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    Dude dude = new Dude(screen, rect.getX() / Play.PPM, rect.getY() / Play.PPM);
                    dudes.add(dude);
                }
            } else if ("Elvis".equals(layer.getName())) {
                elvis = new Array<Elvis>();
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    elvis.add(new Elvis(screen, rect.getX() / Play.PPM, rect.getY() / Play.PPM));
                }
            } else if ("Fellas".equals(layer.getName())) {
                fellas = new Array<Fella>();
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    fellas.add(new Fella(screen, rect.getX() / Play.PPM, rect.getY() / Play.PPM));
                }
            } else if ("Trains".equals(layer.getName())) {
                trains = new Array<Train>();
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    trains.add(new Train(screen, rect.getX() / Play.PPM, rect.getY() / Play.PPM));
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
            }else if ("FinishTutorial".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new FinishTutorial(screen, rect);
                }
            }else if ("GemTutorial".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new GemTutorial(screen, rect);
                }
            }else if ("Bus_Stop_Tutorial".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Bus_Stop_Tutorial(screen, rect);
                }
            }else if ("BarricadeTutorial".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new BarricadeTutorial(screen, rect);
                }
            }else if ("GarbageTutorial".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new GarbageTutorial(screen, rect);
                }
            } else if ("Bus_Stop".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    Bus_Stop busStop = new Bus_Stop(screen, rect);
                    playScreen.getBusStops().add(busStop);
                }
            }else if ("Train_Station_Tutorial".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Train_Station_Tutorial(screen, rect);
                }
            }else if ("Train_Station".equals(layer.getName())) {
                for (MapObject object : layer.getObjects().getByType(RectangleMapObject.class)) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    new Train_Station(screen, rect);
                }
            }
        }
    }

    private void createDialogueForNPC(PlayScreen screen, NPC npc, String title, String message, List<String> options, boolean isBusStopDialogue) {
        Vector2 npcPosition = new Vector2(npc.getX(), npc.getY());
        screen.displayNPCDialogue(title, message, options, isBusStopDialogue, npcPosition);
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
        npcs.addAll(trains);
        npcs.addAll(elvis);
        return npcs;
    }
}
