package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Bank;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
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


public class WhatsMineIsYours {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2959, 3439, 0);    // Doric location
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate dwarven_mine = new Coordinate(3024, 9813, 0);    // Doric location
    static Area.Circular dwarven_mine_area = new Area.Circular(dwarven_mine, 10);
    static Coordinate rimmington_mine = new Coordinate(2970, 3236, 0);    // Doric location
    static Area.Circular rimmington_mine_area = new Area.Circular(rimmington_mine, 10);
    static Coordinate sw_varrock_mine = new Coordinate(3184, 3372, 0);    // Doric location
    static Area.Circular sw_varrock_mine_area = new Area.Circular(sw_varrock_mine, 10);
    static Coordinate se_varrock_mine = new Coordinate(3282, 3371, 0);    // Doric location
    static Area.Circular se_varrock_mine_area = new Area.Circular(se_varrock_mine, 10);
    static Coordinate artisansworkshop = new Coordinate(3038, 3340, 0);    // Doric location
    static Area.Circular artisansworkshop_area = new Area.Circular(artisansworkshop, 10);
    static Coordinate white_knights_castle = new Coordinate(2962, 3339, 0);    // Doric location
    static Area.Circular white_knights_castle_area = new Area.Circular(white_knights_castle, 10);
    static Coordinate doricbasement = new Coordinate(1636, 5988, 0);
    static Area.Circular doricbasement_area = new Area.Circular(doricbasement, 10);

    //static int dwminevisted = 0;
    //static int dwminevisted = 0;
    //static int dwminevisted = 0;
    //static int dwminevisted = 0;


    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(9998);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }

        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0 && (!Backpack.contains(25324) || !Backpack.contains(25325))) {
            ScriptConsole.println("Starting quest... What's Mine Is Yours");

            if (startarea.contains(player)) {
                Doric();
                //Quest Giver
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 0:
                    SceneObject copperore = SceneObjectQuery.newQuery().name("Copper rock").results().nearest();
                    if(copperore != null)
                    {
                        copperore.interact("Mine");
                        delay(RandomGenerator.nextInt(1200, 1500));
                    }

                    Npc npc = NpcQuery.newQuery().name("Living rock brawler").results().first();
                    //varp 8180
                    // High quailty ore : 25327

                    //Talk to Doric // Ore bag ID 25324
                break;
                case 6:
                 Item item = InventoryItemQuery.newQuery().name("Ore bag").results().first();
                 int progress = VarManager.getInvVarbit(item.getInventoryType().getId(), item.getSlot(), 25324); // Progress
                 println("Progress: " + progress);
                 if(VarManager.getVarbitValue(9999) == 0)
                 {
                    if(!dwarven_mine_area.contains(player))
                    {
                        DebugScript.moveTo(dwarven_mine);
                    }
                    else
                    {
                        Npc brawler = NpcQuery.newQuery().name("Living rock brawler").results().first();
                        GroundItem hqco = GroundItemQuery.newQuery().ids(25328).results().nearest();
                        if(brawler == null)
                        {
                            SceneObject rock = SceneObjectQuery.newQuery().name("Copper rock").results().nearest();
                            if(rock != null && hqco == null)
                            {
                                rock.interact("Mine");
                                delay(RandomGenerator.nextInt(1200, 1500));
                                
                            }
                            else if(hqco != null)
                            {
                                hqco.interact("Take");
                                delay(RandomGenerator.nextInt(600, 1200));
                                if (Interfaces.isOpen(1622)) {
                                    Item items = InventoryItemQuery.newQuery(773).ids(25327).results().first();
                                    if (items != null) {
                                        println("Item Slot" + items.getSlot());
                                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                        delay(RandomGenerator.nextInt(600, 1200));
                                        return;
                                    }
                                }
                            }
                        }
                        else if(brawler != null)
                        {
                            brawler.interact(NPCAction.NPC1);
                            delay(RandomGenerator.nextInt(1200, 1500));
                        }  
                    }
                        
                    

                    ///25328
                 }
                 else if(VarManager.getVarbitValue(9999) == 1 && VarManager.getVarbitValue(10000) == 0)
                 {
                    if(!sw_varrock_mine_area.contains(player))
                    {
                        DebugScript.moveTo(sw_varrock_mine);
                    }
                    else
                    {
                        Npc brawler = NpcQuery.newQuery().name("Living rock brawler").results().first();
                        GroundItem hqco = GroundItemQuery.newQuery().ids(25327).results().nearest();
                        if(brawler == null)
                        {
                            SceneObject rock = SceneObjectQuery.newQuery().name("Copper rock").results().nearest();
                            if(rock != null && hqco == null)
                            {
                                rock.interact("Mine");
                                delay(RandomGenerator.nextInt(1200, 1500));
                                
                            }
                            else if(hqco != null)
                            {
                                hqco.interact("Take");
                                delay(RandomGenerator.nextInt(600, 1200));
                                if (Interfaces.isOpen(1622)) {
                                    Item items = InventoryItemQuery.newQuery(773).ids(25327).results().first();
                                    if (items != null) {
                                        println("Item Slot" + items.getSlot());
                                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                        delay(RandomGenerator.nextInt(600, 1200));
                                        return;
                                    }
                                }
                            }
                        }
                        else if(brawler != null)
                        {
                            brawler.interact(NPCAction.NPC1);
                            delay(RandomGenerator.nextInt(1200, 1500));
                        }
                    }
                 }
                 else if(VarManager.getVarbitValue(9999) == 1 && VarManager.getVarbitValue(10000) == 1 && VarManager.getVarbitValue(10001) == 0)
                 {
                    if(!se_varrock_mine_area.contains(player))
                    {
                        DebugScript.moveTo(se_varrock_mine);
                    }
                    else
                    {
                        Npc brawler = NpcQuery.newQuery().name("Living rock brawler").results().first();
                        GroundItem hqco = GroundItemQuery.newQuery().ids(25328).results().nearest();
                        if(brawler == null)
                        {
                            SceneObject rock = SceneObjectQuery.newQuery().name("Copper rock").results().nearest();
                            if(rock != null && hqco == null)
                            {
                                rock.interact("Mine");
                                delay(RandomGenerator.nextInt(1200, 1500));
                                
                            }
                            else if(hqco != null)
                            {
                                hqco.interact("Take");
                                delay(RandomGenerator.nextInt(600, 1200));
                                if (Interfaces.isOpen(1622)) {
                                    Item items = InventoryItemQuery.newQuery(773).ids(25327).results().first();
                                    if (items != null) {
                                        println("Item Slot" + items.getSlot());
                                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                        delay(RandomGenerator.nextInt(600, 1200));
                                        return;
                                    }
                                }
                            }
                        }
                        else if(brawler != null)
                        {
                            brawler.interact(NPCAction.NPC1);
                            delay(RandomGenerator.nextInt(1200, 1500));
                        }
                    }
                 }
                 else if(VarManager.getVarbitValue(9999) == 1 && VarManager.getVarbitValue(10000) == 1 && VarManager.getVarbitValue(10001) == 1 && VarManager.getVarbitValue(10002) == 0)
                 {
                    if(!rimmington_mine_area.contains(player))
                    {
                        DebugScript.moveTo(rimmington_mine);
                    }
                    else
                    {
                        Npc brawler = NpcQuery.newQuery().name("Living rock brawler").results().first();
                        GroundItem hqco = GroundItemQuery.newQuery().ids(25327).results().nearest();
                        if(brawler == null)
                        {
                            SceneObject rock = SceneObjectQuery.newQuery().name("Copper rock").results().nearest();
                            if(rock != null && hqco == null)
                            {
                                rock.interact("Mine");
                                delay(RandomGenerator.nextInt(1200, 1500));
                                
                            }
                            else if(hqco != null)
                            {
                                hqco.interact("Take");
                                delay(RandomGenerator.nextInt(600, 1200));
                                if (Interfaces.isOpen(1622)) {
                                    Item items = InventoryItemQuery.newQuery(773).ids(25327).results().first();
                                    if (items != null) {
                                        println("Item Slot" + items.getSlot());
                                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                        delay(RandomGenerator.nextInt(600, 1200));
                                        return;
                                    }
                                }
                            }
                        }
                        else if(brawler != null)
                        {
                            brawler.interact(NPCAction.NPC1);
                            delay(RandomGenerator.nextInt(1200, 1500));
                        }
                    }
                 }
                break;
                case 7:
                if(Backpack.contains(436))
                {
                    Component copperore1 = ComponentQuery.newQuery(1473).item(436).results().stream().findFirst().orElse(null);
                    if(copperore1 != null)
                    {
                        copperore1.interact("Drop");
                        delay(RandomGenerator.nextInt(600, 1200));
                    }
                }
                else
                {
                    if(!startarea.contains(player))
                    {
                        DebugScript.moveTo(startcord);
                    }
                    else
                    {
                        Doric();
                        
                    }
                }

                break;
                case 10:
                int highqualitytincount = net.botwithus.api.game.hud.inventories.Backpack.getCount(25327);
                int highqualitycoppercount = net.botwithus.api.game.hud.inventories.Backpack.getCount(25328);
                Component orebag = ComponentQuery.newQuery(1473).item(25324).results().stream().findFirst().orElse(null);
                if(orebag != null && (highqualitytincount < 2 || highqualitycoppercount < 2) && !Backpack.contains(25329) && (!Backpack.contains(25330) || !Backpack.contains(25331)))
                {
                    orebag.interact("Withdraw");
                    delay(RandomGenerator.nextInt(600, 1200));
                }
                else 
                {
                    if(highqualitytincount >= 2 && highqualitycoppercount >= 2)
                    {
                        SceneObject furnace = SceneObjectQuery.newQuery().name("Furnace").results().nearest();
                        if(furnace != null)
                        {
                            furnace.interact("Smelt");
                           Execution.delayUntil(3000, () -> Interfaces.isOpen(1371));
                           if(Interfaces.isOpen(1371))
                           {
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                            delay(RandomGenerator.nextInt(600, 1200));
                           }
                           
                        }

                    }
                    else if(net.botwithus.api.game.hud.inventories.Backpack.getCount(25329) >= 1)
                    {
                        SceneObject anvil = SceneObjectQuery.newQuery().name("Anvil").results().nearest();
                        if(anvil != null)
                        {
                            anvil.interact("Smith");
                            Execution.delayUntil(3000, () -> Interfaces.isOpen(1371));
                           if(Interfaces.isOpen(1371))
                           {
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                            delay(RandomGenerator.nextInt(600, 1200));
                           };
                        }
                    }
                    else if(Backpack.contains(25330) || Backpack.contains(25331))
                    {
                        Doric();
                    }

                }
                break;
                case 15:
                if(!artisansworkshop_area.contains(player))
                {
                    DebugScript.moveTo(artisansworkshop);
                }
                else
                {
                    talktoAksel();
                }
                break;
                case 20:
                if(!white_knights_castle_area.contains(player))
                {
                    DebugScript.moveTo(white_knights_castle);
                }
                else
                {
                    talktoSquire();
                }
                break;
                case 40:
                if(!startarea.contains(player))
                {
                    DebugScript.moveTo(startcord);
                }
                else
                {
                    Doric();
                }
                
                break;
                case 45:
                if(Backpack.contains(25329))
                {
                    SceneObject anvil = SceneObjectQuery.newQuery().name("Anvil").results().nearest();
                        if(anvil != null)
                        {
                            anvil.interact("Smith");
                            Execution.delayUntil(3000, () -> Interfaces.isOpen(1371));
                           if(Interfaces.isOpen(1371))
                           {
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                            delay(RandomGenerator.nextInt(600, 1200));
                           };
                        }
                }
                else if(Backpack.contains(25334) || Backpack.contains(25335) || Backpack.contains(25336))
                {
                    if(!doricbasement_area.contains(player) && VarManager.getVarbitValue(10008) == 0)
                    {
                        SceneObject stairs = SceneObjectQuery.newQuery().name("Stairs").results().nearest();
                        if(stairs != null)
                        {
                            stairs.interact("Climb down");
                            Execution.delayUntil(3000, () -> doricbasement_area.contains(player));
                        }
                    }
                    else
                    {
                        SceneObject rocks = SceneObjectQuery.newQuery().name("Rocks").results().nearest();
                        if(rocks != null && VarManager.getVarbitValue(10008) == 0)
                        {
                            rocks.interact("Mine");
                            Execution.delayUntil(3000, () -> Client.getLocalPlayer().getAnimationId() == -1);
                        }
                        else if(VarManager.getVarbitValue(10008) == 1)
                        {
                            if(!startarea.contains(player))
                            {
                                SceneObject stairs = SceneObjectQuery.newQuery().name("Stairs").results().nearest();
                                if(stairs != null)
                                {
                                    stairs.interact("Climb-up");
                                    Execution.delayUntil(3000, () -> startarea.contains(player));
                                }
                            }
                            else
                            {
                                Doric();
                            }
                        }
                    }
                }
                break;
            }
        }
    }
    public static void  Doric() {
        Npc npc = NpcQuery.newQuery().name("Doric").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void  attackRock() {
        Npc npc = NpcQuery.newQuery().name("Living rock brawler").results().first();
        if (npc != null) {
            println("Attack" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }
    public static void talktoAksel() {
        Npc npc = NpcQuery.newQuery().name("Aksel").results().first();
        if (npc != null) {
            println(npc.interact(NPCAction.NPC1) + "Talking to: " + npc.getName());
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }
    public static void talktoSquire() {
        Npc npc = NpcQuery.newQuery().name("Squire Cerlyn").results().first();
        if (npc != null) {
            println(npc.interact(NPCAction.NPC1) + "Talking to: " + npc.getName());
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

}
