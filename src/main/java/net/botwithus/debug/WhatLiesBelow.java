package net.botwithus.debug;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.ObjectAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.queries.results.EntityResultSet;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

public class WhatLiesBelow {

    static int paperpick = 0;
    static int outlawkiill = 0;
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3267, 3334, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 15);
    static Coordinate outlaw = new Coordinate(3119, 3474, 0);
    static Area.Circular outlawarea = new Area.Circular(outlaw, 20);
    static Coordinate surokmagis = new Coordinate(3210, 3492, 0);
    static Area.Circular surokmagisarea = new Area.Circular(surokmagis, 10);
    static Coordinate chaosalter = new Coordinate(3060, 3587, 0);

    static Area.Circular chaosalterarea = new Area.Circular(chaosalter, 5);
    static Coordinate buttomladder = new Coordinate(2259, 4846, 0);
    static Area.Circular buttomladderarea = new Area.Circular(buttomladder, 5);
    static Coordinate chaosalternear = new Coordinate(2269, 4844, 0);
    static Area.Circular chaosalterneararea = new Area.Circular(chaosalternear, 10);
    static Coordinate topladderclimbdown = new Coordinate(2255, 4830, 2);
    static Area.Circular topladderclimbdownarea = new Area.Circular(topladderclimbdown, 5);

    static Coordinate secondladder = new Coordinate(2275, 4833, 2);
    static Area.Circular secondladderarea = new Area.Circular(secondladder, 5);
    static Coordinate thirdladder = new Coordinate(2259, 4846, 0);
    static Area.Circular thirdladderarea = new Area.Circular(thirdladder, 5);

    static Coordinate zaff = new Coordinate(3203, 3432, 0);
    static Area.Circular zaffarea = new Area.Circular(zaff, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(10939);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... What Lie Below");

            if (startarea.contains(player)) {
                println("Inside quest start if statement");
                talktoratburgiss();

            } else {
                println("Inside Debug start if statement");
                DebugScript.moveTo(startcord);
            }

        } else {
           /* if (startarea.contains(player) && QuestVarp < 150) {
                Npc
                SceneObject entrance = SceneObjectQuery.newQuery().name("Catacombs").option("Enter").results().first();
                if (entrance != null) {
                    entrance.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                return;*/

            switch (QuestVarp) {
                case 10:
                    if (Backpack.contains(11007)) {
                        if (startarea.contains(player)) {
                            talktoratburgiss();

                        } else {
                            DebugScript.moveTo(startcord);
                        }
                    }
                    if (!Backpack.contains(11007)) {
                        if (!outlawarea.contains(player)) {
                            DebugScript.moveTo(outlaw);
                        } else {
                            if (VarManager.getVarbitValue(10941) != 5) {//Amount of paper in folder.
                                Npc outlaw = NpcQuery.newQuery().name("Outlaw").results().nearest();
                                GroundItem item = GroundItemQuery.newQuery().ids(11008).results().nearest(); // Paper
                                Item backpackitem = InventoryItemQuery.newQuery(93).ids(11008).results().first(); // Paper
                                Item targetitem = InventoryItemQuery.newQuery(93).name("Used folder", "An empty folder").results().first();

                                if (backpackitem != null && targetitem != null) { // Place paper into folder.
                                    if (MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, backpackitem.getSlot(), 96534533)) {
                                        delay(RandomGenerator.nextInt(200, 400));
                                        MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, targetitem.getSlot(), 96534533);
                                        delay(RandomGenerator.nextInt(200, 400));
                                    }
                                } else if (item != null) { // Take item off the ground.
                                    println(" Taking item" + item.interact("Take"));
                                    delay(RandomGenerator.nextInt(600, 1200));
                                    if (Interfaces.isOpen(1622)) {
                                        Item items = InventoryItemQuery.newQuery(773).ids(11008).results().first();
                                        if (items != null) {
                                            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                            delay(RandomGenerator.nextInt(200, 400));
                                            return;
                                        }
                                    }
                                } else if (item == null) { // If no items on the ground kill outlaw.
                                    if (outlaw != null) {
                                        if (!Client.getLocalPlayer().hasTarget() || Client.getLocalPlayer().getTarget().getCurrentHealth() <= 1) {
                                            outlaw.interact("Attack");
                                            delay(RandomGenerator.nextInt(600, 800));
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;
                case 30:     /// Required Bowl and 15 pure essences in inventory
                    if (surokmagisarea.contains(player)) {
                        surokmagis();

                    } else {
                        DebugScript.moveTo(surokmagis);
                    }
                    break;
                case 50:
                    if (!chaosalterarea.contains(player) && !(Client.getLocalPlayer().getCoordinate().getRegionId() == 9035)) {
                        DebugScript.moveTo(chaosalter);
                    } else {
                        SceneObject chaosruins = SceneObjectQuery.newQuery().name("Chaos ruins").option("Enter").results().first();
                        if (chaosruins != null) {
                            chaosruins.interact("Enter");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        ScriptConsole.println(player.getRegionId());
                        if (Client.getLocalPlayer().getCoordinate().getRegionId() == 9035 && !buttomladderarea.contains(player)) {
                            SceneObject laddertop = SceneObjectQuery.newQuery().name("Ladder").hidden(false).option("Climb-down").results().nearest();
                            if (laddertop != null && player.getCoordinate().getZ() == 3) {
                                println(" 3 floor Climb down");
                                laddertop.interact("Climb-down");
                                Execution.delayUntil(3000, () -> !Client.getLocalPlayer().isMoving());
                                delay(RandomGenerator.nextInt(1200, 2000));
                                return;
                            } else if (laddertop != null && player.getCoordinate().getZ() == 2) {
                                println(" 2 floor Climb down");
                                laddertop.interact(ObjectAction.OBJECT1);
                                Execution.delayUntil(3000, () -> !Client.getLocalPlayer().isMoving());
                                delay(RandomGenerator.nextInt(1200, 2000));
                                return;
                            } else if (laddertop != null && player.getCoordinate().getZ() == 1) {
                                println(" 1 floor Climb down");
                                laddertop.interact(ObjectAction.OBJECT1);
                                Execution.delayUntil(3000, () -> !Client.getLocalPlayer().isMoving());
                                delay(RandomGenerator.nextInt(1200, 2000));
                                return;
                            }
                        }
                        if (player.getCoordinate().getZ() == 0 && !chaosalterneararea.contains(player)) {
                            DebugScript.moveTo(chaosalternear);
                        }

                        if (Backpack.contains(11012) && chaosalterneararea.contains(player)) {
                            SceneObject chaosatar = SceneObjectQuery.newQuery().name("Chaos altar").results().first();
                            Item backpackitem = InventoryItemQuery.newQuery(93).ids(11012).results().stream().findFirst().orElse(null);

                            boolean itemselected = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, backpackitem.getSlot(), 96534533);
                            if (itemselected) {
                                //MiniMenu.interact(SelectableAction.SELECT_TILE.getType(), 0, chaosatar.getCoordinate().getX(), chaosatar.getCoordinate().getY());
                                MiniMenu.interact(SelectableAction.SELECT_OBJECT.getType(), 2487, chaosatar.getCoordinate().getX(), chaosatar.getCoordinate().getY());
                                delay(RandomGenerator.nextInt(800, 1200));
                            }
                        }

                    }
                    break;
                case 55:
                    if (Backpack.contains(11013)) {
                        if (surokmagisarea.contains(player)) {
                            surokmagis();
                        } else {
                            DebugScript.moveTo(surokmagis);
                        }
                    }
                    break;
                case 60:
                    if (Backpack.contains(11010)) {
                        if (startarea.contains(player)) {
                            talktoratburgiss();

                        } else {
                            DebugScript.moveTo(startcord);
                        }
                    }
                    break;
                case 80:
                    if (zaffarea.contains(player)) {
                        talktozaff();
                        println("Varp" + QuestVarp);
                    } else {
                        DebugScript.moveTo(zaff);
                    }
                    break;
                case 110:
                    if (surokmagisarea.contains(player)) {
                        surokmagis();

                    } else {
                        DebugScript.moveTo(surokmagis);
                    }
                    break;
                case 120:
                    Npc kingRoald = NpcQuery.newQuery().name("King Roald").results().first();
                    if (kingRoald != null) {
                        kingRoald.interact("Attack");
                        delay(RandomGenerator.nextInt(600, 800));

                        if (kingRoald.getCurrentHealth() == 1) {
                            Item backpackitem = InventoryItemQuery.newQuery(93).ids(11014).results().stream().findFirst().orElse(null);
                            Component ratring = ComponentQuery.newQuery(1473).item(11014).results().stream().findFirst().orElse(null);

                            if (ratring != null) {
                                ratring.interact("Summon");
                            }
                        }
                    }
                    break;
                case 140:
                    if (startarea.contains(player)) {
                        talktoratburgiss();

                    } else {
                        DebugScript.moveTo(startcord);
                    }
                    break;
                case 150:
                    println("Quest complete!");
                    break;
            }
        }
    }

    public static void talktoratburgiss() {
        Npc npc = NpcQuery.newQuery().name("Rat Burgiss").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void surokmagis() {
        Npc npc = NpcQuery.newQuery().name("Surok Magis").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void talktozaff() {
        Npc npc = NpcQuery.newQuery().name("Zaff").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }


    public void grounditem() {
        EntityResultSet<GroundItem> paper = GroundItemQuery.newQuery().results();

        if (!paper.isEmpty()) {
            GroundItem papers = paper.random();
            if (papers != null) {
                papers.interact("Take");
                Execution.delayUntil(2000, () -> Client.getLocalPlayer().isMoving());


            }
        }

    }


}




