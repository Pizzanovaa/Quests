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
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

public class DeathPlateau {
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2917, 3559, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate sabbotcord = new Coordinate(2269, 4755, 0);
    static Coordinate fredacord = new Coordinate(2821, 3554, 0);
    static Area.Circular fredaarea = new Area.Circular(fredacord, 10);
    static Coordinate dustancord = new Coordinate(2923, 3550, 0);
    static Area.Circular dustanarea = new Area.Circular(dustancord, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(10848);
        player = Client.getLocalPlayer().getServerCoordinate();
        ScriptConsole.println("QuestVarp: " + QuestVarp);

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            //ScriptConsole.println("Starting quest... Anachronia Base Tutorial");
            if (startarea.contains(player)) {
                talktoCommanderDenulth();
            } else {
                DebugScript.moveTo(startcord);
            }
        } else {
            switch (QuestVarp) {
                case 5:
                    if (player.getRegionId() != 9034) {
                        DebugScript.moveTo(sabbotcord);
                    } else {
                        talktoSabbot();
                    }
                    break;

                case 10, 30:
                    if (!fredaarea.contains(player)) {
                        DebugScript.moveTo(fredacord);
                    } else {
                        talktoFreda();
                    }
                    break;

                case 15:
                    if (!dustanarea.contains(player)) {
                        DebugScript.moveTo(dustancord);
                    } else {
                        talktoDustan();
                    }
                    break;

                case 35:
                    var surveyIsOpen = ComponentQuery.newQuery(1242).componentIndex(24).subComponentIndex(-1).hidden(false).results().first();
                    if (surveyIsOpen != null) {
                        surveyIsOpen.interact();
                        return;
                    }
                    if (Backpack.contains("Survey")) {
                        Backpack.interact("Survey", "Read");
                        Execution.delay(1200);
                        DebugScript.moveTo(fredacord);
                    } else {
                        println("YOU DONT HAVE THE SURVEY ON YOU");
                    }
                    break;

                case 40:
                    if (player.getRegionId() != 9034) {
                        DebugScript.moveTo(sabbotcord);
                    } else {
                        var miningWall = SceneObjectQuery.newQuery().name("Rock Wall").option("Mine").results().nearest();
                        if (miningWall != null) {
                            miningWall.interact("Mine");
                            Execution.delay(1200);
                        }
                    }
                    break;

                case 45:
                    println("Varp 45");
                    Coordinate endCoord = new Coordinate(3435, 4240, 2);
                    Area.Circular endArea = new Area.Circular(endCoord, 2);
                    Coordinate startCoord = new Coordinate(3405, 4283, 2);
                    Area.Circular startArea = new Area.Circular(startCoord, 150);
                    if (endArea.contains(player)) {
                        var miningWall = SceneObjectQuery.newQuery().name("Cliffside hole").option("Exit").results().nearest();
                        if (miningWall != null) {
                            miningWall.interact("Exit");
                        }
                        return;
                    }

                    if (startArea.contains(player)) {
                        DebugScript.moveTo(endCoord);
                        return;
                    }

                    if (player.getRegionId() != 9034) {
                        println("Moving to sabot");
                        DebugScript.moveTo(sabbotcord);
                        return;
                    } else {
                        println("In sabbot cave");
                        var miningWall = SceneObjectQuery.newQuery().name("Cavern").option("Enter").results().nearest();
                        if (miningWall != null) {
                            miningWall.interact("Enter");
                            return;
                        }
                        println("Cavern Entrance not found????");
                    }
                    break;

                case 50:
                    talktoTheMap();
                    break;

                case 55:
                    println("In Combat - Waiting for The Map to die");
                    break;

                case 60:
                    if (!startarea.contains(player)) {
                        DebugScript.moveTo(startcord);
                    } else {
                        talktoCommanderDenulth();
                    }
                    break;

                case 65:
                    println("Quest Done");
                    break;
            }
        }
    }

    public static void talktoTheMap() {
        Npc npc = NpcQuery.newQuery().name("The Map").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoCommanderDenulth() {
        Npc npc = NpcQuery.newQuery().name("Commander Denulth").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoSabbot() {
        Npc npc = NpcQuery.newQuery().name("Sabbot").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoFreda() {
        Npc npc = NpcQuery.newQuery().name("Freda").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoDustan() {
        Npc npc = NpcQuery.newQuery().name("Dunstan").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
