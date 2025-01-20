package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Equipment;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.ObjectAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.results.EntityResultSet;
import net.botwithus.rs3.game.queries.results.ResultSet;
import net.botwithus.rs3.input.GameInput;

import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_NPC;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_OBJECT;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.inventories.Bank;


import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.EquipmentInventory;
import net.botwithus.api.game.hud.traversal.Lodestone;
import net.botwithus.api.game.hud.Dialog;

public class BuyersandCellars {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(4662, 5903, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate fatherurhney = new Coordinate(3208, 3150, 0);
    static Area.Circular fatherurhneyarea = new Area.Circular(fatherurhney, 5);
    static Coordinate chiefthiefrobin = new Coordinate(3212, 3208, 0);
    static Area.Circular chiefthiefrobinarea = new Area.Circular(chiefthiefrobin, 5);
    static Coordinate trapdoorguildmaster = new Coordinate(3222, 3269, 0);
    static Area.Circular trapdoorguildmasterarea = new Area.Circular(trapdoorguildmaster, 5);

    static boolean firestarted = false;
    static boolean areareached = false;
    static boolean areareachedinitial = false;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(9022);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Buyers and Cellars");

            initaltheifguild();

        } else {
            switch (QuestVarp) {
                case 5:
                    if (VarManager.getVarbitValue(9023) == 5) {
                        talktoGuildmasterDarrenLightfinger();
                    } else if (VarManager.getVarbitValue(9023) == 7) {
                        if (!chiefthiefrobinarea.contains(player)) {
                            DebugScript.moveTo(chiefthiefrobin);
                        } else {
                            talktochiefthiefrobin();
                        }
                    } else if (VarManager.getVarbitValue(9023) == 10) {
                        if (!fatherurhneyarea.contains(player)) {
                            DebugScript.moveTo(fatherurhney);
                        } else {
                            talktofatherurhney();
                        }
                    } else if (VarManager.getVarbitValue(9023) == 15 && firestarted == false) {
                        Movement.walkTo(3209, 3152, true);
                        delay(RandomGenerator.nextInt(600, 800));
                        if (Backpack.contains("Logs") && !Client.getLocalPlayer().isMoving()) {
                            Backpack.interact("Logs", "Light");
                            delay(RandomGenerator.nextInt(1800, 2400));
                            firestarted = true;
                        }
                    } else if (VarManager.getVarbitValue(9023) == 15 && firestarted == true) {
                        talktofatherurhney();

                        Npc fatherurhney = NpcQuery.newQuery().name("Father Urhney").results().nearest();
                        if (fatherurhney != null) {
                            fatherurhney.interact("Pickpocket");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    } else if (VarManager.getVarbitValue(9023) == 22) {
                        println("Opening display case");
                        SceneObject displaycase = SceneObjectQuery.newQuery().id(51651).option("Open").results().nearest();
                        if (displaycase != null) {
                            displaycase.interact("Open");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    } else if (VarManager.getVarbitValue(9023) == 25) {

                        enterthiefguild();
                    }

                    break;
                case 10:
                    break;
            }
        }
    }

    public static void initaltheifguild() {
        if (!trapdoorguildmasterarea.contains(player) && areareachedinitial == false) {
            println("Entering thief guild and using Item");
            DebugScript.moveTo(trapdoorguildmaster);
            areareachedinitial = true;

        } else if (areareachedinitial == true) {
            println("Entering thief using trapdoor");
            SceneObject trapdoor = SceneObjectQuery.newQuery().name("Trapdoor").option("Enter").results().nearest();
            if (trapdoor != null) {
                areareachedinitial = true;
                trapdoor.interact("Enter");
                delay(RandomGenerator.nextInt(600, 800));

            } else {
                talktoGuildmasterDarrenLightfinger();
            }
        }
    }

    public static void enterthiefguild() {

        if (!trapdoorguildmasterarea.contains(player) && areareached == false) {
            println("Entering thief guild and using Item");
            DebugScript.moveTo(trapdoorguildmaster);
            areareached = true;

        } else if (areareached == true) {
            println("Entering thief using trapdoor");
            SceneObject trapdoor = SceneObjectQuery.newQuery().name("Trapdoor").option("Enter").results().nearest();
            if (trapdoor != null) {
                areareached = true;
                trapdoor.interact("Enter");
                delay(RandomGenerator.nextInt(600, 800));

            } else {
                println("Using chalcedony");
                Item chalcedony = InventoryItemQuery.newQuery(93).ids(18648).results().first();
                Npc darrenlightfinger = NpcQuery.newQuery().name("Darren Lightfinger").results().first();
                if (chalcedony != null) {
                    boolean chalceused = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, chalcedony.getSlot(), 96534533);
                    delay(RandomGenerator.nextInt(600, 800));
                    if (chalceused == true) {
                        MiniMenu.interact(SelectableAction.SELECT_NPC.getType(), darrenlightfinger.getId(), 0, 0);
                        delay(RandomGenerator.nextInt(600, 800));

                    }
                }

            }
        }

    }


    public static void talktochiefthiefrobin() {
        Npc dimintheis = NpcQuery.newQuery().name("Chief Thief Robin").results().first();
        if (dimintheis != null) {
            dimintheis.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktofatherurhney() {
        Npc dimintheis = NpcQuery.newQuery().name("Father Urhney").results().first();
        if (dimintheis != null) {
            dimintheis.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoGuildmasterDarrenLightfinger() {
        Npc dimintheis = NpcQuery.newQuery().name("Darren Lightfinger").results().first();
        if (dimintheis != null) {
            dimintheis.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
