package net.botwithus.debug;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Equipment;
import net.botwithus.api.game.hud.inventories.EquipmentInventory;
import net.botwithus.api.game.hud.inventories.Equipment.Slot;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.game.quest.Quest;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.debug.DebugScript.currentQuest;
import static net.botwithus.debug.DebugScript.moveTo;
import static net.botwithus.rs3.script.Execution.delay;

import java.util.Collections;
import java.util.List;

public class GhostsAhoy {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    //List<Coordinate> StartCord = Quest.byId(92).map(Quest::getStartLocations).orElse(Collections.emptyList());
    static Coordinate startcord = new Coordinate(3679, 3509, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate ectofuntus = new Coordinate(3659, 3517, 0);
    static Area.Circular ectofuntusarea = new Area.Circular(ectofuntus, 10);
    static Coordinate netty = new Coordinate(3461, 3558, 0);
    static Area.Circular nettyarea = new Area.Circular(netty, 10);
    static Coordinate cabinwind = new Coordinate(3617, 3543, 2);
    static Area.Circular cabinwindarea = new Area.Circular(cabinwind, 10);
    static Coordinate secondchest = new Coordinate(3604, 3564, 1);
    static Area.Circular secondchestarea = new Area.Circular(secondchest, 10);
    static Coordinate thirdchest = new Coordinate(3619, 3543, 0);
    static Area.Circular thirdchestarea = new Area.Circular(thirdchest, 10);
    static Coordinate thirdchest1 = new Coordinate(3618, 3542, 0);
    static Coordinate ghostcaptain = new Coordinate(3699, 3488, 0);
    static Area.Circular ghostcaptainarea = new Area.Circular(ghostcaptain, 10);
    static Coordinate akharan = new Coordinate(3690, 3496, 0);
    static Area.Circular akharanarea = new Area.Circular(akharan, 10);
    static Coordinate robin = new Coordinate(3672, 3492, 0);
    static Area.Circular robinarea = new Area.Circular(robin, 10);
    static Coordinate ghostinnkeeper = new Coordinate(3679, 3495, 0);
    static Area.Circular ghostinnkeeperarea = new Area.Circular(ghostinnkeeper, 10);
    static Coordinate gravingas = new Coordinate(3660, 3498, 0);
    static Area.Circular gravingasarea = new Area.Circular(gravingas, 10);
    static Coordinate coffin = new Coordinate(3655, 3515, 1);
    static Area.Circular coffinarea = new Area.Circular(coffin, 10);

    
    static Coordinate digplace = new Coordinate(3803, 3530, 0);
    static Area.Circular digplacearea = new Area.Circular(digplace, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(13497);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Ghost Ahoy");

            if (startarea.contains(player)) {
                //Quest Giver
                talktoVelorina();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {

                case 1:
                    if(!ectofuntusarea.contains(player)){
                        DebugScript.moveTo(ectofuntus);
                    } else {
                        talktoNecrovarus();
                    }
                    break;
                case 2:
                    if(!startarea.contains(player)){
                        DebugScript.moveTo(startcord);
                    } else {
                        talktoVelorina();
                    }
                    break;
                case 3:
                    if(Backpack.contains(4239) && VarManager.getVarbitValue(13491) < 2)
                    {
                        if(!nettyarea.contains(player)){
                            DebugScript.moveTo(netty);
                        } else {
                            talktoNetty();
                        }
                    }
                    else if(VarManager.getVarbitValue(13491) == 2 && !Backpack.contains(4245) && !Backpack.contains(4246))
                    {
                        
                        Item nettletea = InventoryItemQuery.newQuery(93).ids(4239).results().first();
                        Item cup = InventoryItemQuery.newQuery(93).ids(4244).results().first();
                        if(nettletea != null && cup != null){
                            if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, nettletea.getSlot(), 96534533))
                            {
                                delay(RandomGenerator.nextInt(200, 400));
                                MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, cup.getSlot(), 96534533);
                                delay(RandomGenerator.nextInt(200, 400));
                            }
                        }
                    }
                    else if(VarManager.getVarbitValue(13491) == 2 && Backpack.contains(4245))
                    {
                        Item milkbucket = InventoryItemQuery.newQuery(93).ids(1927).results().first();
                        Item teacup = InventoryItemQuery.newQuery(93).ids(4245).results().first();
                        
                        if(milkbucket != null)
                        {
                            if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, milkbucket.getSlot(), 96534533))
                            {
                                delay(RandomGenerator.nextInt(200, 400));
                                MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, teacup.getSlot(), 96534533);
                                delay(RandomGenerator.nextInt(200, 400));
                            }
                        }
                    }
                    else if(VarManager.getVarbitValue(13491) == 2 && Backpack.contains(4246))
                    {
                        
                        talktoNetty();
                    }
                    break;
                case 4:
                   
                        if(Backpack.contains(950) && Backpack.contains(4253))
                        {
                            Item silk = InventoryItemQuery.newQuery(93).ids(950).results().first();
                            Item brokentboat = InventoryItemQuery.newQuery(93).ids(4253).results().first();
                            if(silk != null && brokentboat != null){
                                if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, silk.getSlot(), 96534533))
                                    {
                                    delay(RandomGenerator.nextInt(200, 400));
                                    MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, brokentboat.getSlot(), 96534533);
                                    delay(RandomGenerator.nextInt(200, 400));
                                    }
                                }
                        }
                        else if(Backpack.contains(4254))
                        {
                            if(VarManager.getVarbitValue(13491) == 3 && !cabinwindarea.contains(player)){
                                DebugScript.moveTo(cabinwind);
                            } else {
                                println("Manually Effort Required - Dye the boat and collect all three map pieces manually");
                                // Need to Color Manually
                                // Varbit for Wind 13485 = 1 is low - check flag color
                            }
                        }
                        else if(VarManager.getVarbitValue(13494) == 2 && Backpack.contains(4273))
                        {
                            Item key = InventoryItemQuery.newQuery(93).ids(4273).results().first();
                            SceneObject chest = SceneObjectQuery.newQuery().name("Closed chest").hidden(false).results().nearest();
                            if(key != null && chest != null){
                                
                                if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, key.getSlot(), 96534533))
                                {
                                    delay(RandomGenerator.nextInt(200, 400));
                                    chest.interact(SelectableAction.SELECT_OBJECT.getType());
                                    delay(RandomGenerator.nextInt(200, 400));
                                }
                              
                            
                            }
                        }
                        else if(VarManager.getVarbitValue(13494) == 3 && !Backpack.contains(4274) && !Backpack.contains(4277))
                        {
                            SceneObject chest = SceneObjectQuery.newQuery().name("Closed chest").hidden(false).results().nearest();
                            if(chest != null){
                                chest.interact("Open");
                                delay(RandomGenerator.nextInt(200, 400));
                                chest.interact("Search");
                                delay(RandomGenerator.nextInt(200, 400));
                            }
                        }
                        else if(VarManager.getVarbitValue(13494) == 3 && Backpack.contains(4274) && !Backpack.contains(4276))
                        {
                            if(!secondchestarea.contains(player)){      
                                DebugScript.moveTo(secondchest);
                            } else 
                            {
                                // Need to get stuff from the second chest manually - To lazy to create a NAV path toward second chest - Manual effor requiried
                                SceneObject chest = SceneObjectQuery.newQuery().name("Closed chest").hidden(false).results().nearest();
                                SceneObject openchest = SceneObjectQuery.newQuery().name("Open chest").hidden(false).results().nearest();
                                if(chest != null){
                                    chest.interact("Open");
                                    delay(RandomGenerator.nextInt(200, 400));
                                }
                                if(openchest != null){
                                    openchest.interact("Search");
                                    delay(RandomGenerator.nextInt(200, 400));
                                }
                            }
                        }
                        else if(VarManager.getVarbitValue(13494) == 3 && Backpack.contains(4274) && Backpack.contains(4276) && VarManager.getVarbitValue(13495) == 0)
                        {
                            // Navigate to the ship manually
                            
                            if(!thirdchestarea.contains(player)){
                                DebugScript.moveTo(thirdchest);
                            } else {
                                SceneObject chest = SceneObjectQuery.newQuery().name("Closed chest").hidden(false).results().nearestTo(thirdchest1);
                                SceneObject chest1 = SceneObjectQuery.newQuery().name("Opened chest").hidden(false).results().nearestTo(thirdchest1);
                                if(chest != null){
                                    chest.interact("Open");
                                    delay(RandomGenerator.nextInt(200, 400));
                                }
                                if(chest1 != null){
                                    chest1.interact("Search");
                                    delay(RandomGenerator.nextInt(200, 400));
                                }
                                Npc lobster = NpcQuery.newQuery().name("Giant lobster").results().first();
                                if(lobster != null){
                                    lobster.interact("Attack");
                                    delay(RandomGenerator.nextInt(600, 800));
                                }
                            }
                        }
                        else if(VarManager.getVarbitValue(13495) == 1 && Backpack.contains(4274) && Backpack.contains(4276)&& !Backpack.contains(4275))
                        {
                            
                            SceneObject chest = SceneObjectQuery.newQuery().name("Closed chest").hidden(false).results().nearestTo(thirdchest1);
                            SceneObject chest1 = SceneObjectQuery.newQuery().name("Opened chest").hidden(false).results().nearestTo(thirdchest1);
                            if(chest != null){
                                chest.interact("Open");
                                delay(RandomGenerator.nextInt(200, 400));
                            }
                            if(chest1 != null){
                                chest1.interact("Search");
                                delay(RandomGenerator.nextInt(200, 400));
                            }
                        }
                        else if(VarManager.getVarbitValue(13495) == 1 && Backpack.contains(4274) && Backpack.contains(4276) && Backpack.contains(4275))
                        {
                            
                            Item map1 = InventoryItemQuery.newQuery(93).ids(4274).results().first();
                            Item map2 = InventoryItemQuery.newQuery(93).ids(4276).results().first();
                            if(map1 != null && map2 != null){
                                if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, map1.getSlot(), 96534533))
                                {
                                    delay(RandomGenerator.nextInt(200, 400));
                                    MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, map2.getSlot(), 96534533);
                                    delay(RandomGenerator.nextInt(200, 400));
                                }
                            }
                        }
                        else if(Backpack.contains(4277) && Client.getLocalPlayer().getServerCoordinate().getRegionId() != 15159 && !Backpack.contains(4248))
                        {
                            if(!ghostcaptainarea.contains(player)){
                                DebugScript.moveTo(ghostcaptain);
                            } else {
                                talktoGhostCaptain();
                            }
                        }
                        else if(Backpack.contains(4277) && Client.getLocalPlayer().getServerCoordinate().getRegionId() == 15159 && !Backpack.contains(4248))
                        {
                            if(!digplacearea.contains(player)){
                                DebugScript.moveTo(digplace);
                            } else {
                                //Item spade = InventoryItemQuery.newQuery(93).ids(4277).results().first();
                                Component sapde = ComponentQuery.newQuery(1473).item(952).results().stream().findFirst().orElse(null);
                                if(sapde != null){
                                    sapde.interact("Dig");
                                    delay(RandomGenerator.nextInt(200, 400));
                                }
                            }
                        }
                        else if(Backpack.contains(4277) && Backpack.contains(4248) && VarManager.getVarbitValue(13492) == 0)
                        {
                            if(!akharanarea.contains(player)){
                                DebugScript.moveTo(akharan);
                            } else {
                                talktoAkHaranu();
                            }
                        }
                        else if(VarManager.getVarbitValue(13492) == 1)
                        {
                            if(!robinarea.contains(player)){
                                DebugScript.moveTo(robin);
                            } else {
                                talktoRobin();
                            }
                        }
                        else if(VarManager.getVarbitValue(13492) == 2)
                        {
                            //Play Rune game round 1
                        }
                        else if(VarManager.getVarbitValue(13492) == 3)
                        {
                            //Play Rune game round 2
                        }
                        else if(VarManager.getVarbitValue(13492) == 4)
                        {
                            //Play Rune game round 3
                        }
                        else if(VarManager.getVarbitValue(13492) == 5)
                        {
                            //Play Rune game round 4
                        }
                        else if(VarManager.getVarbitValue(13492) == 6)
                        {
                            talktoRobin();
                        }
                        else if(VarManager.getVarbitValue(13492) == 7)
                        {
                            if(!akharanarea.contains(player)){
                                DebugScript.moveTo(akharan);
                            } else {
                                talktoAkHaranu();
                            }
                        }
                        else if(VarManager.getVarbitValue(13492) == 8 && !Backpack.contains(4284) && !Backpack.contains(4285) && VarManager.getVarbitValue(13489) == 0)
                        {
                            if(!ghostinnkeeperarea.contains(player)){
                                DebugScript.moveTo(ghostinnkeeper);
                            } else {
                                talktoghostinnkeeper();
                            }
                        }
                        else if(VarManager.getVarbitValue(13492) == 8 && Backpack.contains(4284) && !Backpack.contains(4285))
                        {
                            Item bedsheet = InventoryItemQuery.newQuery(93).ids(4284).results().first();
                            Item slime = InventoryItemQuery.newQuery(93).ids(4286).results().first();
                            if(bedsheet != null && slime != null){
                                if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, bedsheet.getSlot(), 96534533))
                                {
                                    delay(RandomGenerator.nextInt(200, 400));
                                    MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, slime.getSlot(), 96534533);
                                    delay(RandomGenerator.nextInt(200, 400));
                                }
                            }
                        }
                        else if(VarManager.getVarbitValue(13492) == 8 && Backpack.contains(4285) && VarManager.getVarbitValue(13489) == 0)
                        {
                            if(!gravingasarea.contains(player)){
                                DebugScript.moveTo(gravingas);
                            } else {
                                talktoGravingas();
                            }
                        }
                        else if(VarManager.getVarbitValue(13489) == 1 && Backpack.contains(4283))    /// Getting 10 Signuatres
                        {
                            Component slimesheet = ComponentQuery.newQuery(1473).item(4285).results().stream().findFirst().orElse(null);
                            if(slimesheet != null && !Equipment.contains("Bedsheet")){
                                slimesheet.interact("Wear");
                                delay(RandomGenerator.nextInt(200, 400));
                            }
                            else if(Equipment.contains("Bedsheet"))
                            {
                                if(!gravingasarea.contains(player)){
                                    DebugScript.moveTo(gravingas);
                                } else {
                                    talktoGhostvillager();
                                }
                            }
                        }
                        else if(VarManager.getVarbitValue(13489) == 2 && Backpack.contains(4283))
                        {
                            if(!gravingasarea.contains(player)){
                                DebugScript.moveTo(gravingas);
                            } else {
                                talktoGhostvillager();
                            }
                        }
                        else if(VarManager.getVarbitValue(13489) == 3 && Backpack.contains(4283))
                        {
                            if(!gravingasarea.contains(player)){
                                DebugScript.moveTo(gravingas);
                            } else {
                                talktoGhostvillager();
                            }
                        }
                        else if(VarManager.getVarbitValue(13489) == 4 && Backpack.contains(4283))
                        {
                            if(!gravingasarea.contains(player)){
                                DebugScript.moveTo(gravingas);
                            } else {
                                talktoGhostvillager();
                            }
                        }
                        else if(VarManager.getVarbitValue(13489) == 5 && Backpack.contains(4283))
                        {
                            if(!gravingasarea.contains(player)){
                                DebugScript.moveTo(gravingas);
                            } else {
                                talktoGhostvillager();
                            }
                        }   
                        else if(VarManager.getVarbitValue(13489) == 6 && Backpack.contains(4283))
                        {
                            if(!gravingasarea.contains(player)){
                                DebugScript.moveTo(gravingas);
                            } else {
                                talktoGhostvillager();
                            }
                        }       
                        else if(VarManager.getVarbitValue(13489) == 7 && Backpack.contains(4283))
                        {
                            if(!gravingasarea.contains(player)){
                                DebugScript.moveTo(gravingas);
                            } else {
                                talktoGhostvillager();
                            }
                        }
                        else if(VarManager.getVarbitValue(13489) == 8 && Backpack.contains(4283))
                        {
                            if(!gravingasarea.contains(player)){
                                DebugScript.moveTo(gravingas);
                            } else {
                                talktoGhostvillager();
                            }
                        }   
                        else if(VarManager.getVarbitValue(13489) == 9 && Backpack.contains(4283))
                        {
                            if(!gravingasarea.contains(player)){
                                DebugScript.moveTo(gravingas);
                            } else {
                                talktoGhostvillager();
                            }
                        }
                        else if(VarManager.getVarbitValue(13489) == 10 && Backpack.contains(4283))
                        {
                            if(!gravingasarea.contains(player)){
                                DebugScript.moveTo(gravingas);
                            } else {
                                talktoGhostvillager();
                            }
                        }
                        else if(VarManager.getVarbitValue(13489) == 11 && Backpack.contains(4283) && Equipment.contains("Bedsheet"))
                        {
                            Equipment.interact(Slot.HEAD, "Remove");
                            delay(RandomGenerator.nextInt(200, 400));
                        }
                        else if(VarManager.getVarbitValue(13489) == 11 && Backpack.contains(4283) && !Equipment.contains("Bedsheet"))
                        {
                            
                            if(ectofuntusarea.contains(player)){
                                talktoNecrovarus();                               
                            }
                            else
                            {
                                SceneObject barrier = SceneObjectQuery.newQuery().name("Energy Barrier").results().nearest();
                                if(barrier != null){
                                barrier.interact("Pass");
                                delay(RandomGenerator.nextInt(600, 1200));
                                DebugScript.moveTo(ectofuntus);
                            }
                            }
                        }
                        else if(VarManager.getVarbitValue(13489) == 31 && !Backpack.contains(4272))
                        {
                            GroundItem key = GroundItemQuery.newQuery().name("Bone key").results().nearest();
                            if(key != null)
                            {
                                key.interact("Take");
                                delay(RandomGenerator.nextInt(200, 400));
                                    if (Interfaces.isOpen(1622)) 
                                    {
                                    Item items = InventoryItemQuery.newQuery(773).name("Bone key").results().first();
                                    if (items != null) 
                                    {
                                    println("Item Slot" + items.getSlot());
                                    MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                    delay(RandomGenerator.nextInt(600, 1200));
                                    return;
                                    }
                                }
                            }
                        }
                        else if(VarManager.getVarbitValue(13489) == 31 && Backpack.contains(4272) && VarManager.getVarbitValue(13493) == 0)
                        {
                            if(!coffinarea.contains(player)){
                                DebugScript.moveTo(coffin);
                            } 
                            else {
                                
                                SceneObject door = SceneObjectQuery.newQuery().name("Door").results().nearest();
                                Item bonekey = InventoryItemQuery.newQuery(93).name("Bone key").results().first();
                                if(bonekey != null && door != null){
                                
                                    if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, bonekey.getSlot(), 96534533))
                                    {
                                        delay(RandomGenerator.nextInt(200, 400));
                                        door.interact(SelectableAction.SELECT_OBJECT.getType());
                                        delay(RandomGenerator.nextInt(200, 400));

                                        door.interact("Open");
                                        delay(RandomGenerator.nextInt(600, 1200));
                                    }
                                }
                              
                            }
                        }
                        else if(VarManager.getVarbitValue(13493) == 1 && !Backpack.contains(4247))
                        {
                            
                            SceneObject coffin = SceneObjectQuery.newQuery().name("Coffin").results().nearest();
                            SceneObject opencoffin = SceneObjectQuery.newQuery().name("Coffin").hidden(false).results().nearest();
                            
                            if(coffin != null){
                                coffin.interact("Open");
                                delay(RandomGenerator.nextInt(600, 1200));
                            }
                            if(opencoffin != null){
                                opencoffin.interact("Search");
                                delay(RandomGenerator.nextInt(600, 1200));
                            }
                        }
                        else if(VarManager.getVarbitValue(13493) == 1 && Backpack.contains(4247))
                        {
                            if(!nettyarea.contains(player)){
                                DebugScript.moveTo(netty);
                            } else {
                                talktoNetty();
                            }
                        }
                        break;
                case 5:
                break;
                case 6:
                        if(!ectofuntusarea.contains(player)){
                            DebugScript.moveTo(ectofuntus);
                        } else {
                            talktoNecrovarus();
                        }
                break;
                case 7:
                if(Backpack.contains(4285))
                        {
                            Component bedsheet = ComponentQuery.newQuery(1473).item(4285).results().stream().findFirst().orElse(null);
                            if(bedsheet != null){

                                bedsheet.interact("Drop");
                                delay(RandomGenerator.nextInt(200, 400));
                            }
                        }
                        else if(!startarea.contains(player))
                            {
                            DebugScript.moveTo(startcord);
                            } 
                           else {
                            talktoVelorina();
                            }
                    break;
            }
        }
    }


    public static void talktoVelorina() {
        Npc malgi = NpcQuery.newQuery().name("Velorina").results().first();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoNecrovarus() {
        Npc malgi = NpcQuery.newQuery().name("Necrovarus").results().first();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoNetty() {
        Npc malgi = NpcQuery.newQuery().name("Netty").results().first();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoGhostCaptain() {
        Npc malgi = NpcQuery.newQuery().name("Ghost captain").results().nearestTo(ghostcaptain);
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void talktoAkHaranu() {
        Npc malgi = NpcQuery.newQuery().name("Ak-Haranu").results().nearest();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoRobin()
    {
        Npc malgi = NpcQuery.newQuery().name("Robin").results().nearest();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoghostinnkeeper()
    {
        Npc malgi = NpcQuery.newQuery().name("Ghost innkeeper").results().nearest();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoGravingas()
    {
        Npc malgi = NpcQuery.newQuery().name("Gravingas").results().nearest();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoGhostvillager()
    {
        Npc malgi = NpcQuery.newQuery().name("Ghost villager").results().random();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    
}
