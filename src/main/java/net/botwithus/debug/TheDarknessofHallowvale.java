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
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_OBJECT;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;
import net.botwithus.api.game.hud.inventories.Bank;



import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.EquipmentInventory;
import net.botwithus.api.game.hud.traversal.Lodestone;
import net.botwithus.api.game.hud.Dialog;

public class TheDarknessofHallowvale {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3491, 3232, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);

    static Coordinate pubcoordbag = new Coordinate(3492, 3229, 0);
    static Area.Circular pubcoordbagarea = new Area.Circular(pubcoordbag, 1);
    static Coordinate boathousecoord = new Coordinate(3522, 3181, 0);
    static Area.Circular boathousearea = new Area.Circular(boathousecoord, 10);

    static Coordinate oldmanralcoord = new Coordinate(3604, 3208, 0);
    static Area.Circular oldmanralarea = new Area.Circular(oldmanralcoord, 2);

    static Coordinate hideoutenterace = new Coordinate(3639, 3250, 0);
    static Area.Circular hideoutenteracearea = new Area.Circular(hideoutenterace, 2);

    static Coordinate vertidacoord = new Coordinate(3629, 9641, 0);
    static Area.Circular vertidacoordarea = new Area.Circular(vertidacoord, 2);

    static Coordinate drezelcoord = new Coordinate(3438, 9895, 0);
    static Area.Circular drezelarea = new Area.Circular(drezelcoord, 10);
    static Coordinate bushescoord = new Coordinate(3387, 3485, 0);
    static Area.Circular bushescoordarea = new Area.Circular(bushescoord, 10);
    static Coordinate kingroaldcoord = new Coordinate(3223, 3474, 0);
    static Area.Circular kingroaldarea = new Area.Circular(kingroaldcoord, 6);
    static Coordinate togetcaughtcoord = new Coordinate(3596, 3184, 0);
    static Area.Circular togetcaughtarea = new Area.Circular(togetcaughtcoord, 6);
    static Coordinate housedmarkedcoord = new Coordinate(3631, 3265, 0);
    static Area.Circular housedmarkedarea = new Area.Circular(housedmarkedcoord, 6);

    static Coordinate sickle1coord = new Coordinate(3556,3384,0);
    static Area.Circular sickle1coordarea = new Area.Circular(sickle1coord, 1);
    static Coordinate sickle2coord = new Coordinate(3524,3348,0);
    static Area.Circular sickle2coordarea = new Area.Circular(sickle2coord, 1);
    static Coordinate sickle3coord = new Coordinate(3583,3334,0);
    static Area.Circular sickle3coordarea = new Area.Circular(sickle3coord, 1);

    static boolean minecartused = false;



    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(10204);
        player = Client.getLocalPlayer().getServerCoordinate();



        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... The Darkness of Hallowvale");

            // if (startarea.contains(player)) {
            //     talktoVeliafHurtz();
            // } else {
            //     DebugScript.moveTo(startcord);
            // }

            Npc VeliafHurtz = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
                if(!pubcoordbagarea.contains(player) && VeliafHurtz == null) {
                    DebugScript.moveTo(pubcoordbag);
                }
                else {
                    SceneObject brokendownwall1 = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();
                    SceneObject trapdoorpub = SceneObjectQuery.newQuery().name("Trapdoor").option("Climb-down").results().first();
                    if(brokendownwall1 != null && VeliafHurtz == null) {
                        if(player.getY() > 3230) {
                            trapdoorpub.interact("Climb-down");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else {
                            brokendownwall1.interact("Climb-over");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        
                    }
                    else if(VeliafHurtz != null) {
                        talktoVeliafHurtz();
                    }
                }

        } else {
            switch (QuestVarp) {

                case 10:
                Npc VeliafHurtz = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
                SceneObject ladderup = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().first();
                SceneObject brokendownwall1 = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();
                SceneObject boat = SceneObjectQuery.newQuery().name("Boat").option("Search").results().first();
                
                Item plank = InventoryItemQuery.newQuery().name("Plank").results().first();
                if(VeliafHurtz != null && ladderup != null) {
                    ladderup.interact("Climb-up");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                
                    if(player.getY() > 3230 && brokendownwall1 != null) {
                        brokendownwall1.interact("Climb-over");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                
                if(boat !=null)
                {
                   if(plank != null) {
                    boolean plankuse = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, plank.getSlot(), 96534533);
                    if(plankuse) {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(SELECT_OBJECT.getType(), 12944, boat.getCoordinate().getX(), boat.getCoordinate().getY());
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                   }
                }
                break;
                case 20:
                SceneObject Boatchute = SceneObjectQuery.newQuery().name("Boat Chute").option("Fix").results().first();
                Item planksecondItem = InventoryItemQuery.newQuery().name("Plank").results().first();

                if(Boatchute != null && planksecondItem != null) {
                    boolean plankuse = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, planksecondItem.getSlot(), 96534533);
                    if(plankuse) {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(SELECT_OBJECT.getType(), 12947, Boatchute.getCoordinate().getX(), Boatchute.getCoordinate().getY());
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 30:
                SceneObject boatpush = SceneObjectQuery.newQuery().name("Boat").option("Push").results().first();
                SceneObject boatboard = SceneObjectQuery.newQuery().name("Boat").option("Board").results().first();
                if(boatpush != null) {
                    boatpush.interact("Push");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                if(boatboard != null) {
                    boatboard.interact("Board");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 40:
                SceneObject rockjump = SceneObjectQuery.newQuery().name("Rock").option("Jump-onto").results().first();
                if(rockjump != null) {
                    rockjump.interact("Jump-onto");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 50:
                SceneObject climpupRock = SceneObjectQuery.newQuery().name("Rock").option("Climb-up").results().first();
                if(climpupRock != null) {
                    climpupRock.interact("Climb-up");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                SceneObject kickdownfloor = SceneObjectQuery.newQuery().name("Floor").option("Kick-down").results().first();
                SceneObject floor = SceneObjectQuery.newQuery().name("Floor").option("Climb-down").results().first();
                if(kickdownfloor != null) {
                    kickdownfloor.interact("Kick-down");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                if(floor != null) {
                    floor.interact("Climb-down");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                SceneObject wallrubble = SceneObjectQuery.newQuery().name("Wall rubble").option("Climb-over").results().first();
                if(wallrubble != null && player.getX() < 3592) {
                    wallrubble.interact("Climb-over");
                    delay(RandomGenerator.nextInt(600, 800));
                } else if(player.getX() >= 3592) {
                    talktomeiyerditchcitizen();
                }

                
                break;
                case 60:
                if(!oldmanralarea.contains(player)) {
                    DebugScript.moveTo(oldmanralcoord);
                }
                else {
                    talktooldmanral();
                }
                break;
                case 65:
                println(" Maze needs to progress manually");
                break;

                case 80:
                if(VarManager.getVarbitValue(10209) == 1) {
                    talktovertida();
                }
                break;
                case 90:
                Npc VeliafHurtz1 = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
                SceneObject brokendownwall3 = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();
                    SceneObject trapdoorpub = SceneObjectQuery.newQuery().name("Trapdoor").option("Climb-down").results().first();
                    if(!pubcoordbagarea.contains(player) && VeliafHurtz1 == null && brokendownwall3 == null) {
                        DebugScript.moveTo(pubcoordbag);
                    }
                    else {
                    
                    if(brokendownwall3 != null && VeliafHurtz1 == null) {
                        if(player.getY() > 3230) {
                            trapdoorpub.interact("Climb-down");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else {
                            brokendownwall3.interact("Climb-over");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        
                    }
                    else if(VeliafHurtz1 != null) {
                        talktoVeliafHurtz();
                    }
                    }
                break;
                case 110:
                if(!drezelarea.contains(player)) {
                    DebugScript.moveTo(drezelcoord);
                }
                else {
                    talktoDrezel();
                }
                break;
                case 120:
                if(!bushescoordarea.contains(player)) {
                    DebugScript.moveTo(bushescoord);
                }
                else {
                    SceneObject bushes = SceneObjectQuery.newQuery().name("Bush").option("Search").results().first();
                    if(bushes != null) {
                        bushes.interact("Search");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 130:
                if(!drezelarea.contains(player)) {
                    DebugScript.moveTo(drezelcoord);
                }
                else {
                    talktoDrezel();
                }
                break;
                case 140:
                if(!kingroaldarea.contains(player)) {
                    DebugScript.moveTo(kingroaldcoord);
                }
                else {
                    talktoKingRoald();
                }
                break;
                case 150, 160:
                if(!drezelarea.contains(player)) {
                    DebugScript.moveTo(drezelcoord);
                }
                else {
                    talktoDrezel();
                }
                break;
                case 170:
                talktoveiafpub();
                break;
                case 180:

                if(VarManager.getVarbitValue(10205) == 1 && VarManager.getVarbitValue(10211) != 0) {
                    if(!housedmarkedarea.contains(player)) {
                        DebugScript.moveTo(housedmarkedcoord);
                    }
                    else {
                        println(" Reached Housed, Puase the script and go to underground to talk to Vertida");
                    }
                }
                else if(VarManager.getVarbitValue(10205) == 0 && VarManager.getVarbitValue(10211) == 0) {
                    Npc VeliafHurtz2 = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
                    SceneObject daeyaltveins = SceneObjectQuery.newQuery().name("Daeyalt vein").option("Mine").results().first();
                    Item daeyaltveinsItem = InventoryItemQuery.newQuery().name("Daeyalt ore").results().first();
                    SceneObject minecart = SceneObjectQuery.newQuery().name("Mine cart").option("Search").results().first();
                    if(VeliafHurtz2 != null) {
                        gettingoutpub();
                    }
                    else if(VeliafHurtz2 == null && daeyaltveins == null) {
                        if(!togetcaughtarea.contains(player)) {
                            DebugScript.moveTo(togetcaughtcoord);
                        }
                        else {
                            talktomeiyerditchcitizen();
                        }
                    }
                    else if(daeyaltveins != null && Backpack.getQuantity(9632) < 16 && !minecartused) {
                        println("Mining daeyalt vein");
                        daeyaltveins.interact("Mine");
                        delay(RandomGenerator.nextInt(1200, 1800));
                    }
                    else if(daeyaltveinsItem != null && Backpack.getQuantity(9632)  >= 15 && !minecartused) {
                        println("Using daeyalt vein on minecart");
                        boolean useonminecart = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, daeyaltveinsItem.getSlot(), 96534533);
                        if(useonminecart) {
                            delay(RandomGenerator.nextInt(600, 800));
                            MiniMenu.interact(SELECT_OBJECT.getType(), 18119, minecart.getCoordinate().getX(), minecart.getCoordinate().getY());
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        minecartused = true;
                    }
                    else if(minecartused) {
                        talktoguard();
                    }
                }
                
                break;
                case 190:
                println(" Perform Maze Manually and talk to Safalaan");
                break;
                case 200:
                if(!sickle1coordarea.contains(player)) {
                    DebugScript.moveTo(sickle1coord);
                }
                else {
                    Item papyrus = InventoryItemQuery.newQuery().name("Papyrus").results().first();
                    Item charcoal = InventoryItemQuery.newQuery().name("Charcoal").results().first();
                    if(papyrus != null && charcoal != null) {
                        boolean useoncharcoal = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, papyrus.getSlot(), 96534533);
                        if(useoncharcoal) {
                            delay(RandomGenerator.nextInt(600, 800));
                            MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, charcoal.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                break;
                case 210:
                if(!sickle2coordarea.contains(player)) {
                    DebugScript.moveTo(sickle2coord);
                }
                else {
                    Item papyrus = InventoryItemQuery.newQuery().name("Papyrus").results().first();
                    Item charcoal = InventoryItemQuery.newQuery().name("Charcoal").results().first();
                    if(papyrus != null && charcoal != null) {
                        boolean useoncharcoal = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, papyrus.getSlot(), 96534533);
                        if(useoncharcoal) {
                            delay(RandomGenerator.nextInt(600, 800));
                            MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, charcoal.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                break;
                case 220:
                if(!sickle3coordarea.contains(player)) {
                    DebugScript.moveTo(sickle3coord);
                }
                else {
                    Item papyrus = InventoryItemQuery.newQuery().name("Papyrus").results().first();
                    Item charcoal = InventoryItemQuery.newQuery().name("Charcoal").results().first();
                    if(papyrus != null && charcoal != null) {
                        boolean useoncharcoal = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, papyrus.getSlot(), 96534533);
                        if(useoncharcoal) {
                            delay(RandomGenerator.nextInt(600, 800));
                            MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, charcoal.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                break;
                case 250:
                if(!Backpack.contains(9648)) {
                    if(!sickle3coordarea.contains(player)) {
                        DebugScript.moveTo(sickle3coord);
                    }
                    else {
                        Item papyrus = InventoryItemQuery.newQuery().name("Papyrus").results().first();
                        Item charcoal = InventoryItemQuery.newQuery().name("Charcoal").results().first();
                        if(papyrus != null && charcoal != null) {
                            boolean useoncharcoal = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, papyrus.getSlot(), 96534533);
                            if(useoncharcoal) {
                                delay(RandomGenerator.nextInt(600, 800));
                                MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, charcoal.getSlot(), 96534533);
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }
                }
                else if(VarManager.getVarbitValue(10206) == 0){
                    println(" Talk to Safalaan in Hideout, Manual Maze solving required");
                }
                else if(VarManager.getVarbitValue(10206) == 1){
                    println(" Locate the Laboratory Manually");
                }
                
                break;

                case 310:
                talktoveiafpub();
                break;
            }
        }
    }


    public static void talktoVeliafHurtz()
    {
        Npc VeliafHurtz = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
        if (VeliafHurtz != null) {
            VeliafHurtz.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktomeiyerditchcitizen() {
        Npc meiyerditchcitizen = NpcQuery.newQuery().name("Meiyerditch citizen").results().first();
        if(meiyerditchcitizen != null) {
            meiyerditchcitizen.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktooldmanral() {
        Npc oldmanral = NpcQuery.newQuery().name("Old Man Ral").results().first();
        if(oldmanral != null) {
            oldmanral.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktovertida() {
        Npc vertida = NpcQuery.newQuery().name("Vertida Sefalatis").results().first();
        if(vertida != null) {
            vertida.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoDrezel() {
        Npc drezel = NpcQuery.newQuery().name("Drezel").results().first();
        if(drezel != null) {
            drezel.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoKingRoald() {
        Npc kingofhallowvale = NpcQuery.newQuery().name("King Roald").results().first();
        if(kingofhallowvale != null) {
            kingofhallowvale.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoveiafpub() {
        Npc VeliafHurtz1 = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
                SceneObject brokendownwall3 = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();
                    SceneObject trapdoorpub = SceneObjectQuery.newQuery().name("Trapdoor").option("Climb-down").results().first();
                    if(!pubcoordbagarea.contains(player) && VeliafHurtz1 == null && brokendownwall3 == null) {
                        DebugScript.moveTo(pubcoordbag);
                    }
                    else {
                    
                    if(brokendownwall3 != null && VeliafHurtz1 == null) {
                        if(player.getY() > 3230) {
                            trapdoorpub.interact("Climb-down");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else {
                            brokendownwall3.interact("Climb-over");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        
                    }
                    else if(VeliafHurtz1 != null) {
                        talktoVeliafHurtz();
                    }
                    }
    }

    public static void gettingoutpub()
    {
        Npc VeliafHurtz = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
                SceneObject ladderup = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().first();
                SceneObject brokendownwall1 = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();
                SceneObject boat = SceneObjectQuery.newQuery().name("Boat").option("Search").results().first();
                
                Item plank = InventoryItemQuery.newQuery().name("Plank").results().first();
                if(VeliafHurtz != null && ladderup != null) {
                    ladderup.interact("Climb-up");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                
                    if(player.getY() > 3230 && brokendownwall1 != null) {
                        brokendownwall1.interact("Climb-over");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                
    }

    public static void talktoguard() {
        Npc guard = NpcQuery.newQuery().name("Juvinate guard").results().first();
        if(guard != null) {
            guard.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }


}
