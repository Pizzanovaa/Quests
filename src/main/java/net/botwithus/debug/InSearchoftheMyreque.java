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

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.EquipmentInventory;
import net.botwithus.api.game.hud.traversal.Lodestone;
import net.botwithus.api.game.hud.Dialog;

public class InSearchoftheMyreque {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3503, 3475, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate CyregPaddlehorn = new Coordinate(3522, 3284, 0);
    static Area.Circular CyregPaddlehornarea = new Area.Circular(CyregPaddlehorn, 10);
    static Coordinate log = new Coordinate(3476, 3456, 0);
    static Area.Circular logarea = new Area.Circular(log, 10);
    static Coordinate curpilefyodcord = new Coordinate(3507, 3440, 0);
    static Area.Circular curpilefyodarea = new Area.Circular(curpilefyodcord, 10);
    static Coordinate searchcoord = new Coordinate(3480, 9836, 0);
    static Area.Circular searcharea = new Area.Circular(searchcoord, 10);
    static Coordinate bridgeskip1 = new Coordinate(3450, 3381, 0);
    static Area.Circular bridgeskiper = new Area.Circular(bridgeskip1, 10);
    static Coordinate bridge2 = new Coordinate(3502, 3423, 0);
    static Area.Circular bridgeskiper2 = new Area.Circular(bridge2, 10);
    static Coordinate bridgestep1 = new Coordinate(3502, 3427, 0);
    static Coordinate bridgestep2 = new Coordinate(3502, 3428, 0);
    static Coordinate bridgestep3 = new Coordinate(3502, 3429, 0);
    static Coordinate bridgestep4 = new Coordinate(3502, 3430, 0);
    static Coordinate hideout = new Coordinate(3508,9837,2);
    static Area.Circular hideoutarea = new Area.Circular(hideout, 10);
    static Coordinate hideout2 = new Coordinate(3508,9837,0);
    static Area.Circular hideoutarea2 = new Area.Circular(hideout2, 10);




    static boolean bridgeskip = false;

    static int memecounter = 1;

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2696);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... In Search of the Myreque");

            if (startarea.contains(player)) {
                talktovanstromklause();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                //13381 bridge1
                //13382 bridge2
                //13383 bridge3
                case 5:
                    if(VarManager.getVarbitValue(13383) != 1) { // repair bridge
                        if (bridgeskiper.contains(player) && !bridgeskip) {
                            bridgeskip = true;
                        } else if (!bridgeskip && !bridgeskiper.contains(player)) {
                            DebugScript.moveTo(bridgeskip1);
                        } else if (bridgeskip && !bridgeskiper2.contains(player)) {
                            DebugScript.moveTo(bridge2); //Scuffed method to nav while avoiding the bridge to get to the correct side.
                        } else if (bridgeskiper2.contains(player)) {


                            if (!bridgestep1.equals(player) && !bridgestep2.equals(player) && !bridgestep3.equals(player) && !bridgestep4.equals(player)) {
                                SceneObject tree = SceneObjectQuery.newQuery().name("Tree").option("Climb").results().nearest();
                                if (tree != null) {
                                    tree.interact("Climb");
                                    delay(RandomGenerator.nextInt(600, 800));
                                }
                            } else {
                                if (VarManager.getVarbitValue(13381) == 0) {
                                    SceneObject step1 = SceneObjectQuery.newQuery().name("Rope bridge").option("Repair").on(bridgestep2).results().nearest();
                                    if (step1 != null) {
                                        step1.interact("Repair");
                                        delay(RandomGenerator.nextInt(600, 800));
                                    }
                                } else if (VarManager.getVarbitValue(13382) == 0) {
                                    SceneObject step1 = SceneObjectQuery.newQuery().name("Rope bridge").option("Repair").on(bridgestep3).results().nearest();
                                    if (step1 != null) {
                                        step1.interact("Repair");
                                        delay(RandomGenerator.nextInt(600, 800));
                                    }
                                }else if (VarManager.getVarbitValue(13383) == 0) {
                                    SceneObject step1 = SceneObjectQuery.newQuery().name("Rope bridge").option("Repair").on(bridgestep4).results().nearest();
                                    if (step1 != null) {
                                        step1.interact("Repair");
                                        delay(RandomGenerator.nextInt(600, 800));
                                    }
                                }
                            }
                        }
                    }else {
                        //Scuffed bridge handling...

                        if(bridgestep4.equals(player)){
                            SceneObject step1 = SceneObjectQuery.newQuery().name("Rope bridge").option("Walk-here").on(bridgestep3).results().nearest();
                            if (step1 != null) {
                                step1.interact("Walk-here");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                            return;
                        }else if(bridgestep3.equals(player)){
                            SceneObject step1 = SceneObjectQuery.newQuery().name("Rope bridge").option("Walk-here").on(bridgestep2).results().nearest();
                            if (step1 != null) {
                                step1.interact("Walk-here");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                            return;
                        }else if(bridgestep2.equals(player)){
                            SceneObject step1 = SceneObjectQuery.newQuery().name("Rope bridge").option("Walk-here").on(bridgestep1).results().nearest();
                            if (step1 != null) {
                                step1.interact("Walk-here");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                            return;
                        }else if(bridgestep1.equals(player)){
                            SceneObject tree = SceneObjectQuery.newQuery().name("Tree").option("Climb").results().nearest();
                            if (tree != null) {
                                tree.interact("Climb");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                            return;
                        }
                        Item druidpouch = InventoryItemQuery.newQuery(93).name("Druid pouch").results().first();
                        int progress = druidpouch.getStackSize();
                        //ScriptConsole.println("Progress: " + progress);
                        if (!logarea.contains(player) && progress < 5) { //Nav will try use bridge but wont work if not repaired
                            DebugScript.moveTo(log);
                        } else if (progress >= 5) {
                            if (!CyregPaddlehornarea.contains(player)) {
                                DebugScript.moveTo(CyregPaddlehorn);
                            } else {
                                talktoCyregPaddlehorn();
                            }
                        } else {
                            SceneObject fungionlog = SceneObjectQuery.newQuery().name("Fungi on log").results().first();

                            if (fungionlog != null) {
                                fungionlog.interact("Pick");
                                delay(RandomGenerator.nextInt(600, 800));
                            } else if (Backpack.getCount("Mort myre fungus") < 6 && fungionlog == null && progress < 6) {
                                Backpack.interact("Silver sickle (b)", "Bloom");
                                delay(RandomGenerator.nextInt(600, 800));
                            } else if (progress < 6) {
                                Backpack.interact("Druid pouch", "Fill");
                                delay(RandomGenerator.nextInt(600, 800));
                            }

                        }
                    }
                    break;
                case 20:

                    SceneObject swampboat = SceneObjectQuery.newQuery().name("Swamp Boat").results().first();
                    if (swampboat != null) {
                        swampboat.interact("Board");
                        delay(RandomGenerator.nextInt(600, 800));

                        if (Dialogs.isDialogOpen()) {
                            Dialogs.dialog1188pick(1);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    break;
                case 25:
                    //Scuffed bridge handling...
                    if(bridgestep4.equals(player)){
                        SceneObject tree = SceneObjectQuery.newQuery().name("Tree").option("Climb").results().nearest();
                        if (tree != null) {
                            tree.interact("Climb");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        return;
                    }else if(bridgestep3.equals(player)){
                        SceneObject step1 = SceneObjectQuery.newQuery().name("Rope bridge").option("Walk-here").on(bridgestep4).results().nearest();
                        if (step1 != null) {
                            step1.interact("Walk-here");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        return;
                    }else if(bridgestep2.equals(player)){
                        SceneObject step1 = SceneObjectQuery.newQuery().name("Rope bridge").option("Walk-here").on(bridgestep3).results().nearest();
                        if (step1 != null) {
                            step1.interact("Walk-here");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        return;
                    }else if(bridgestep1.equals(player)){
                        SceneObject step1 = SceneObjectQuery.newQuery().name("Rope bridge").option("Walk-here").on(bridgestep2).results().nearest();
                        if (step1 != null) {
                            step1.interact("Walk-here");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        return;
                    }
                    if (!curpilefyodarea.contains(player)) {
                        DebugScript.moveTo(curpilefyodcord);
                    } else {
                        talktoCurpileFyod();
                    }


                    break;
                case 52:
                    if (!curpilefyodarea.contains(player)) {
                        DebugScript.moveTo(curpilefyodcord);
                    } else {
                        talktoCurpileFyod();
                    }
                    break;
                case 55:
                    SceneObject woodendoors = SceneObjectQuery.newQuery().name("Wooden doors").results().first();
                    if (woodendoors != null) {
                        woodendoors.interact("Open");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    break;
                case 60:


                    Npc VeliafHurtz = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();

                    SceneObject stalagmite = SceneObjectQuery.newQuery().name("Stalagmite").option("Squeeze-past").results().first();
                    if (stalagmite != null && !hideoutarea.contains(player)) {
                        stalagmite.interact("Squeeze-past");
                        delay(RandomGenerator.nextInt(600, 800));
                    } else if (VeliafHurtz != null) {
                        VeliafHurtz.interact("Talk-to");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    break;
                case 65:
                    int progress = VarManager.getVarbitValue(13379); // Varbit increases when talking to each person
                    if (progress == 0) {
                        talktoIvanStrom();
                    } else if (progress == 16) {
                        talktoPolmafiFerdygris();
                    } else if (progress == 24) {
                        talktoHaroldEvans();
                    } else if (progress == 26) {
                        talktoRadigadPonfit();
                    } else if (progress == 30) {
                        talktoSaniPiliu();
                    } else if (progress == 31) {
                        talktoVeliafHurtz();
                    }
                    break;
                case 85:

                    talktoVeliafHurtz();

                    break;
                case 90:
                    SceneObject wall2 = SceneObjectQuery.newQuery().name("Wall").option("Search").hidden(false).results().nearest();

                    if(hideoutarea2.contains(player)){
                        SceneObject entrance = SceneObjectQuery.newQuery().name("Cave entrance").results().nearest();
                        if(entrance != null){
                            entrance.interact("Enter");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        return;
                    }else if(player.getY() <= 9836 && wall2 != null) {
                            wall2.interact("Search");
                            delay(RandomGenerator.nextInt(600, 800));
                        return;
                    }
                    break;
                case 95:
                    SceneObject wall = SceneObjectQuery.newQuery().name("Wall").option("Search").hidden(false).results().nearest();

                    if(player.getY() <= 9836 && wall != null) {
                            wall.interact("Search");
                            delay(RandomGenerator.nextInt(600, 800));
                        return;
                    }else {
                        SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().first();
                        if (ladder != null) {
                            ladder.interact("Climb-up");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    break;
                case 97:
                    talktoStranger();
                    break;
            }
        }
    }


    public static void talktovanstromklause() {
        Npc vanstromklause = NpcQuery.newQuery().name("Vanstrom Klause").results().first();
        if (vanstromklause != null) {
            vanstromklause.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoCyregPaddlehorn() {
        Npc CyregPaddlehorn = NpcQuery.newQuery().name("Cyreg Paddlehorn").results().first();
        if (CyregPaddlehorn != null) {
            CyregPaddlehorn.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoCurpileFyod() {
        Npc CurpileFyod = NpcQuery.newQuery().name("Curpile Fyod").results().first();
        if (CurpileFyod != null) {
            CurpileFyod.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoVeliafHurtz() {
        Npc VeliafHurtz = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
        if (VeliafHurtz != null) {
            VeliafHurtz.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoIvanStrom() {
        Npc IvanStrom = NpcQuery.newQuery().name("Ivan Strom").results().first();
        if (IvanStrom != null) {
            IvanStrom.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoPolmafiFerdygris() {
        Npc PolmafiFerdygris = NpcQuery.newQuery().name("Polmafi Ferdygris").results().first();
        if (PolmafiFerdygris != null) {
            PolmafiFerdygris.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoSaniPiliu() {
        Npc SaniPiliu = NpcQuery.newQuery().name("Sani Piliu").results().first();
        if (SaniPiliu != null) {
            SaniPiliu.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoHaroldEvans() {
        Npc HaroldEvans = NpcQuery.newQuery().name("Harold Evans").results().first();
        if (HaroldEvans != null) {
            HaroldEvans.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoRadigadPonfit() {
        Npc RadigadPonfit = NpcQuery.newQuery().name("Radigad Ponfit").results().first();
        if (RadigadPonfit != null) {
            RadigadPonfit.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoStranger() {
        Npc Stranger = NpcQuery.newQuery().name("Stranger").results().first();
        if (Stranger != null) {
            Stranger.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
