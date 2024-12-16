package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.util.RandomGenerator;

public class DruidicRitual {
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2924, 3481, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate sanfewcord = new Coordinate(2917, 3439, 0);
    static Area.Circular sanfewarea = new Area.Circular(sanfewcord, 6);
    static Coordinate waterspringcord = new Coordinate(2210, 4496, 0);
    static Area.Circular waterspringarea = new Area.Circular(waterspringcord, 6);
    static Coordinate wyrmwoodcord = new Coordinate(2908, 3385, 0);
    static Area.Circular wyrmwoodarea = new Area.Circular(wyrmwoodcord, 6);
    static Coordinate fishingcord = new Coordinate(2904, 3397, 0);
    static Area.Circular fishingarea = new Area.Circular(fishingcord, 6);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(13366);
        player = Client.getLocalPlayer().getServerCoordinate();
        //println("QuestVarp: " + QuestVarp);

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            println("Starting quest... Druidic Ritual");
            if (startarea.contains(player)) {
                talktoKaqemeex();
            } else {
                DebugScript.moveTo(startcord);
            }
        } else {
            switch (QuestVarp) {
                case 2:
                    if (!sanfewarea.contains(player)) {
                        DebugScript.moveTo(sanfewcord);
                    } else {
                        talktoSanfew();
                    }
                    break;

                case 4:
                    if (!Backpack.contains("Living water")) {
                        println("Getting 'Living Water'...");
                        if (!waterspringarea.contains(player)) {
                            DebugScript.moveTo(waterspringcord);
                        } else {
                            SceneObjectQuery.newQuery().option("Fill").results().nearest().interact("Fill");
                        }
                    } else if (!Backpack.contains("Wandering wyrmwood")) {
                        if (!wyrmwoodarea.contains(player)) {
                            DebugScript.moveTo(wyrmwoodcord);
                        } else {
                            var npc = NpcQuery.newQuery().name("Wandering wyrmwood").option("Take-from").results().nearest();
                            if (npc != null)
                                npc.interact("Take-from");
                        }
                    } else if (!Backpack.contains("Stone fish")) {
                        if (!fishingarea.contains(player)) {
                            DebugScript.moveTo(fishingcord);
                        } else {
                            SceneObjectQuery.newQuery().option("Bait").results().nearest().interact("Bait");
                            while (Client.getLocalPlayer().getAnimationId() != -1) {
                                println("FISHING. Please Wait.");
                            }
                        }
                    } else if (!Backpack.contains("Stone scales") && Backpack.contains("Stone fish")) {
                        Backpack.interact("Stone fish", "Gather-scales");
                        Execution.delay(1800);
                        ComponentQuery.newQuery(1179).componentIndex(12).results().first().interact();
                    } else {
                        if (!sanfewarea.contains(player)) {
                            DebugScript.moveTo(sanfewcord);
                        } else {
                            talktoSanfew();
                        }
                    }
                    break;
                case 8:
                    println("Quest Completed!");
                    break;
            }
        }
    }

    public static void talktoKaqemeex() {
        Npc npc = NpcQuery.newQuery().name("Kaqemeex").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoSanfew() {
        Npc npc = NpcQuery.newQuery().name("Sanfew").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }


}