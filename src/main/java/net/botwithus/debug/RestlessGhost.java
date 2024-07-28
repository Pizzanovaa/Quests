package net.botwithus.debug;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

public class RestlessGhost {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3245, 3208, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate swamphut = new Coordinate(3207, 3150, 0);
    static Area.Circular swamphutarea = new Area.Circular(swamphut, 10);
    static Coordinate coffin = new Coordinate(3248, 3193, 0);
    static Area.Circular coffinarea = new Area.Circular(coffin, 6);
    static Coordinate rock = new Coordinate(3237, 3149, 0);
    static Area.Circular rockarea = new Area.Circular(rock, 6);

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2324);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }

        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Restless Ghost!");

            if (startarea.contains(player)) {
                talkToPriest();
            } else {
                DebugScript.moveTo(startcord);
            }
        }else{
                switch (QuestVarp) {
                    case 1:
                        if (!swamphutarea.contains(player)) {
                            DebugScript.moveTo(swamphut);
                        } else {
                            talktoPriest2();
                        }
                        break;
                    case 2:
                        if (Backpack.contains("Ghostspeak amulet")) {
                            Backpack.interact("Ghostspeak amulet", "Wear");
                        } else {
                            if (!coffinarea.contains(player)) {
                                DebugScript.moveTo(coffin);
                            } else {
                                SceneObject coffin = SceneObjectQuery.newQuery().name("Coffin").hidden(false).option("Open").results().nearest();
                                if (coffin != null) {
                                    coffin.interact("Open");
                                    delay(RandomGenerator.nextInt(600, 800));
                                } else {
                                    Npc ghost = NpcQuery.newQuery().name("Restless ghost").results().nearest();
                                    if (ghost != null) {
                                        ghost.interact("Talk-to");
                                        delay(RandomGenerator.nextInt(600, 800));

                                    }
                                }
                            }
                        }
                        break;
                    case 3:
                        if (!rockarea.contains(player)) {
                            DebugScript.moveTo(rock);
                        } else {
                            SceneObject rock = SceneObjectQuery.newQuery().name("Rocks").results().nearest();
                            if (rock != null) {
                                rock.interact("Search");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                        break;
                    case 4:
                        if (!coffinarea.contains(player)) {
                            DebugScript.moveTo(coffin);
                        } else {
                            SceneObject coffin = SceneObjectQuery.newQuery().name("Coffin").hidden(false).option("Open").results().nearest();
                            if (coffin != null) {
                                coffin.interact("Open");
                                delay(RandomGenerator.nextInt(600, 800));
                            } else {
                                SceneObject coffin2 = SceneObjectQuery.newQuery().name("Coffin").hidden(false).option("Search").results().nearest();
                                if (coffin2 != null) {
                                    coffin2.interact("Search");
                                    delay(RandomGenerator.nextInt(600, 800));
                                }
                            }
                        }
                        break;
                    case 5:
                        println("Quest Complete");
                        break;


                }
        }
    }

    public static void talktoPriest2() {
        Npc priest = NpcQuery.newQuery().name("Father Urhney").results().nearest();
        if (priest != null) {
            priest.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talkToPriest() {
        Npc priest = NpcQuery.newQuery().name("Father Aereck").results().nearest();
        if (priest != null) {
            priest.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
