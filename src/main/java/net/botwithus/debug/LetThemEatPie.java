package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECTABLE_COMPONENT;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_COMPONENT_ITEM;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_OBJECT;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.inventories.Bank;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import java.util.regex.Pattern;

public class LetThemEatPie {
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2881, 3443, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate baitcord = new Coordinate(2905, 3412, 0);
    static Area.Circular baitarea = new Area.Circular(baitcord, 6);
    static Coordinate wheatcord = new Coordinate(2897, 3403, 0);
    static Area.Circular wheatarea = new Area.Circular(wheatcord, 6);
    static Coordinate millcord = new Coordinate(2892, 3423, 0);
    static Area.Circular millarea = new Area.Circular(millcord, 6);
    static Coordinate mill1stfloorcord = new Coordinate(2892, 3426, 1);
    static Area.Circular mill1stfloorarea = new Area.Circular(mill1stfloorcord, 6);
    static Coordinate fishingcord = new Coordinate(2896, 3470, 0);
    static Area.Circular fishingarea = new Area.Circular(fishingcord, 6);
    static Coordinate cutscenecord = new Coordinate(2887, 3482, 0);
    static Coordinate farmerjoecord = new Coordinate(2889, 3469, 0);
    static Area.Circular farmerjoearea = new Area.Circular(farmerjoecord, 6);
    static Coordinate holecord = new Coordinate(2871, 3478, 0);
    static Area.Circular holerea = new Area.Circular(holecord, 6);
    static Coordinate ovencord = new Coordinate(2895, 3411, 0);
    static Area.Circular ovenrea = new Area.Circular(ovencord, 5);

    static Coordinate rolocord = new Coordinate(2894, 3442, 1);
    static Area.Circular rolorea = new Area.Circular(rolocord, 5);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(13240);
        player = Client.getLocalPlayer().getServerCoordinate();
        println("QuestVarp: " + QuestVarp);

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            println("Starting quest... Let Them Eat Pie");
            if (startarea.contains(player)) {
                talktoNailsNewton();
            } else {
                DebugScript.moveTo(startcord);
            }
        } else {
            switch (QuestVarp) {
                case 6:
                    if (!Backpack.contains("Fishing bait")) {
                        if (!baitarea.contains(player)) {
                            DebugScript.moveTo(baitcord);
                        } else {

                            println("Getting Fishing bait.");
                            var bait = GroundItemQuery.newQuery().name("Fishing bait").results().nearest();
                            if (bait != null)
                                bait.interact("Take");
                            Execution.delay(600);
                            var lootMenu = ComponentQuery.newQuery(1622).componentIndex(22).results().first();
                            if (lootMenu != null)
                                lootMenu.interact();
                        }
                    } else if (!Backpack.contains("Wheat") && !Backpack.contains("Maggoty flour")) {
                        if (!wheatarea.contains(player)) {
                            DebugScript.moveTo(wheatcord);
                        } else {
                            println("Getting Wheat.");
                            var bait = SceneObjectQuery.newQuery().name("Wheat").results().nearest();
                            if (bait != null)
                                bait.interact("Pick");
                        }
                    } else if (!Backpack.contains("Maggoty flour")) {
                        if (!Backpack.contains("Empty pot")) {
                            if (!millarea.contains(player)) {
                                DebugScript.moveTo(millcord);
                            } else {

                                println("Getting a Pot.");
                                var bait = GroundItemQuery.newQuery().name("Empty pot").results().nearest();
                                if (bait != null)
                                    bait.interact("Take");
                                Execution.delay(600);
                                var lootMenu = ComponentQuery.newQuery(1622).componentIndex(22).results().first();
                                if (lootMenu != null)
                                    lootMenu.interact();
                            }
                        } else {
                            if (!mill1stfloorarea.contains(player)) {
                                DebugScript.moveTo(mill1stfloorcord);
                            } else {
                                println("Interaction with wheat");
                                SceneObject hopper = SceneObjectQuery.newQuery().name("Hopper").results().nearest();
                                if (hopper != null) {
                                    int itemslot = net.botwithus.api.game.hud.inventories.Backpack.getItem("Wheat").getSlot();
                                    MiniMenu.interact(SELECTABLE_COMPONENT.getType(), 0, itemslot, 96534533);
                                    delay(RandomGenerator.nextInt(300, 500));
                                    //Interact Hopper
                                    MiniMenu.interact(SELECT_OBJECT.getType(), 67774, 2893, 3426);
                                    delay(RandomGenerator.nextInt(2000, 3000));
                                    Execution.delay(3000);
                                    SceneObject hopperControls = SceneObjectQuery.newQuery().name("Hopper controls").results().nearest();
                                    if (hopperControls != null) {
                                        hopperControls.interact("Operate");
                                        Execution.delay(6000);
                                        DebugScript.moveTo(millcord);
                                        SceneObject flourBin = SceneObjectQuery.newQuery().name("Flour bin").results().nearest();
                                        if (flourBin != null) {
                                            flourBin.interact("Take-flour");
                                            Execution.delay(3000);
                                            DebugScript.moveTo(startcord);
                                            Execution.delay(3000);
                                            talktoNailsNewton();
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;

                case 9:
                    if (!Backpack.contains("Raw crayfish") && !Backpack.contains("Manky crayfish")) {
                        println("Catching crayfish");
                        if (!fishingarea.contains(player)) {
                            DebugScript.moveTo(fishingcord);
                        } else {
                            var fishingspot = NpcQuery.newQuery().name("Fishing spot").results().nearest();
                            if (fishingspot != null) {
                                fishingspot.interact("Cage");
                                Execution.delayUntil(1800, () -> Backpack.contains("Raw crayfish"));
                            }
                        }
                    } else if (!Bank.contains("Manky crayfish")) {
                        //Watch CutScene
                        println("Watch Cutscene");
                        DebugScript.moveTo(cutscenecord);
                        Execution.delay(5000);
                        println("Go to Nails");
                        DebugScript.moveTo(startcord);
                        Execution.delay(5000);
                        println("Talk to Nails");
                        talktoNailsNewton();
                        Execution.delay(5000);
                    }

                    break;
                case 10:
                    if (!Backpack.contains("Raw potato") && !Backpack.contains("Stinking potato")) {
                        if (Interfaces.isOpen(1265)) {
                            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 2, 1, 82903054);
                            Execution.delay(600);
                            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 82903184);
                        } else {
                            if (!farmerjoearea.contains(player)) {
                                println("Go to Farmer Jones");
                                DebugScript.moveTo(farmerjoecord);
                                Execution.delay(600);
                            } else {
                                println("Trade With Farmer Jones");
                                var FarmerJones = NpcQuery.newQuery().name("Head Farmer Jones").option("Trade").results().nearest();
                                if (FarmerJones != null) {
                                    FarmerJones.interact("Trade");
                                }
                                Execution.delay(5000);
                            }
                        }
                    } else if (!Backpack.contains("Stinking potato")) {
                        if (!holerea.contains(player)) {
                            DebugScript.moveTo(holecord);
                        } else {
                            SceneObject hole = SceneObjectQuery.newQuery().name("Hole").option("Inspect").results().nearest();
                            if (hole != null) {
                                hole.interact("Inspect");
                                Execution.delay(5000);
                            }
                        }
                    } else {

                        int fishslot = net.botwithus.api.game.hud.inventories.Backpack.getItem("Manky crayfish").getSlot();
                        int shellslot = net.botwithus.api.game.hud.inventories.Backpack.getItem("Maggoty pie shell").getSlot();
                        ScriptConsole.println("Interact fish");
                        MiniMenu.interact(SELECTABLE_COMPONENT.getType(), 0, fishslot, 96534533);
                        Execution.delay(500);
                        ScriptConsole.println("Interact pie");
                        MiniMenu.interact(SELECT_COMPONENT_ITEM.getType(), 0, shellslot, 96534533);
                        Execution.delay(500);
                    }
                    break;

                case 12:
                    if (!Backpack.contains("Terrible pie (uncooked)")) {
                        ScriptConsole.println("PIE NOT FOUND!!");
                        return;
                    } else {
                        if (!ovenrea.contains(player)) {
                            DebugScript.moveTo(ovencord);
                            return;
                        }
                        var oven = SceneObjectQuery.newQuery()
                                .name("Oven").option("Cook-at").results().nearest();
                        if (oven != null) {
                            oven.interact("Cook-at");
                            Execution.delay(600);
                            return;
                        }
                    }
                    break;
                case 13:
                    if (!startarea.contains(player))
                        DebugScript.moveTo(startcord);

                    talktoNailsNewton();
                    break;
                case 15:
                    if (!Backpack.contains("Expensive spices")) {
                        pickpocketFoppishPierre();
                        Execution.delay(600);
                        return;
                    } else {
                        int fishslot = net.botwithus.api.game.hud.inventories.Backpack.getItem("Expensive spices").getSlot();
                        int shellslot = net.botwithus.api.game.hud.inventories.Backpack.getItem("Terrible pie").getSlot();
                        ScriptConsole.println("Interact spices");
                        MiniMenu.interact(SELECTABLE_COMPONENT.getType(), 0, fishslot, 96534533);
                        Execution.delay(500);
                        ScriptConsole.println("Interact pie");
                        MiniMenu.interact(SELECT_COMPONENT_ITEM.getType(), 0, shellslot, 96534533);
                        Execution.delay(500);
                    }
                    break;
                case 20:
                    if (!rolorea.contains(player)) {
                        DebugScript.moveTo(ovencord);
                        var stairs = SceneObjectQuery.newQuery()
                                .name("Spiral Stairs").option("Climb-up").results().nearest();
                        if (stairs != null) {
                            stairs.interact("Climb-up");
                            Execution.delay(600);
                        }
                        return;
                    } else {
                        talktoRolo();
                    }
                    break;
                case 25:
                    if (!Backpack.contains("Seal")) {
                        if (!rolorea.contains(player)) {
                            DebugScript.moveTo(ovencord);
                            var stairs = SceneObjectQuery.newQuery()
                                    .name("Spiral Stairs").option("Climb-up").results().nearest();
                            if (stairs != null) {
                                stairs.interact("Climb-up");
                                Execution.delay(600);
                            }
                            return;
                        } else {
                            var chest = SceneObjectQuery.newQuery()
                                    .name("Chest").option("Search").hidden(false).results().nearest();
                            if (chest != null) {
                                ScriptConsole.println("Search Chest found");
                                chest.interact("Search");
                                Execution.delay(600);
                            } else {
                                ScriptConsole.println("Search Chest not found");
                                chest = SceneObjectQuery.newQuery()
                                        .name("Chest").option("Open").hidden(false).results().nearest();
                                if (chest != null) {
                                    ScriptConsole.println("Open Chest found");
                                    chest.interact("Open");
                                    Execution.delay(600);
                                }
                            }
                        }
                    } else {
                        ScriptConsole.println("Has Seal");
                        if (!startarea.contains(player)) {
                            DebugScript.moveTo(startcord);

                        } else {
                            talktoNailsNewton();
                        }

                    }
                    break;
                case 30:
                    if (!startarea.contains(player)) {
                        DebugScript.moveTo(startcord);

                    } else {
                        talktoFoppishPierre();
                    }
                    break;

                case 35:
                    if (!startarea.contains(player)) {
                        DebugScript.moveTo(startcord);

                    } else {
                        talktoNailsNewton();
                    }
                    break;
                case 40:
                    println("Quest Completed!");
                    break;
            }
        }
    }

    public static void talktoNailsNewton() {
        Npc npc = NpcQuery.newQuery().name("Nails Newton").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoRolo() {
        Npc npc = NpcQuery.newQuery().name("Rolo the Stout").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void talktoFoppishPierre() {
        Npc npc = NpcQuery.newQuery().name("Foppish Pierre").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void pickpocketFoppishPierre() {
        Npc npc = NpcQuery.newQuery().name("Foppish Pierre").results().first();
        if (npc != null) {
            npc.interact("Pickpocket");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
