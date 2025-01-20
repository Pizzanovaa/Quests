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

public class TempleofIkov {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2576, 3320, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate templeofikov = new Coordinate(2680, 3406, 0);
    static Area.Circular templeofikovarea = new Area.Circular(templeofikov, 10);

    static Coordinate testcoordinate = new Coordinate(2670, 9798, 0);
    static Area.Circular testarea = new Area.Circular(testcoordinate, 10);
    static Coordinate adibankcoordinate = new Coordinate(2617, 3333, 0);
    static Area.Circular adibankarea = new Area.Circular(adibankcoordinate, 5);
    static Coordinate luciencoord = new Coordinate(3128, 3487, 0);
    static Area.Circular luciencircle = new Area.Circular(luciencoord, 5);

    static boolean templeofikovdown = false;
    static boolean gatenorthopen = false;
    static boolean largedooropen = false;
    static boolean leveruse = false;
    static boolean leveredpulled = false;
    static boolean eastgateopen = false;
    static boolean templeofikovdown1 = false;
    static boolean templeofikovdown2 = false;
    static boolean gatenorthopen1 = false;
    
    static boolean levelwithtrapsopen1 = false;
    static boolean levelwithtrapsopen2 = false;
    static boolean dooropen = false;
    static boolean dooropen1 = false;
    static boolean pushwallopen = false;

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2148);
        player = Client.getLocalPlayer().getServerCoordinate();

        println("Quest Varp: " + QuestVarp);
        println("2149: " + VarManager.getVarpValue(2149));

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Temple of Ikov");

            if (startarea.contains(player)) {
                talktoLucien();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 10:
                if(VarManager.getVarpValue(2149) == 0) 
                {
                    if(!templeofikovarea.contains(player) && templeofikovdown == false) {
                        DebugScript.moveTo(templeofikov);
                    }
                    else if(templeofikovarea.contains(player) && templeofikovdown == false) {
                        SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").option("Climb-down").results().nearest();
                        if(ladder != null) {
                            ladder.interact("Climb-down");
                            delay(RandomGenerator.nextInt(600, 800));
                            templeofikovdown = true;
                        }
                    }
                    else if(templeofikovdown && !Backpack.contains(88) && !Equipment.contains(89)) {
                        SceneObject ladder = SceneObjectQuery.newQuery().name("Stairs").option("Climb-down").results().nearest();
                        SceneObject web = SceneObjectQuery.newQuery().name("Web").option("Slash").hidden(false).results().nearest();
                        if(ladder != null) {
                            ladder.interact("Climb-down");
                            delay(RandomGenerator.nextInt(600, 800));
                            
                        }
                        else if(web != null) {
                            web.interact("Slash");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else {
                            GroundItem boots = GroundItemQuery.newQuery().name("Boots of lightness").ids(88).results().nearest();
                            if(boots != null) {
                                boots.interact("Take");
                                delay(RandomGenerator.nextInt(600, 1200));
                                if (Interfaces.isOpen(1622)) {
                                Item items = InventoryItemQuery.newQuery(773).ids(88).results().first();
                                if (items != null) {
                                    println("Item Slot" + items.getSlot());
                                    MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                    delay(RandomGenerator.nextInt(600, 1200));
                                    templeofikovdown = false;
                                    return;
                                }
                                }
                            }
                        }
                    }
                    else if(Backpack.contains(88) && !Equipment.contains(89)) {
                        Backpack.interact("Boots of lightness", "Wear");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(Equipment.contains(89) && !Backpack.contains(83) && leveruse == false) {
                        println("Trying to Open Gate");
                        SceneObject gatenorth = SceneObjectQuery.newQuery().name("Gate").option("Open").id(95).results().nearest();
                        SceneObject largedoor = SceneObjectQuery.newQuery().name("Large door").option("Open").id(31814).results().nearest();
                        if(gatenorth != null && gatenorthopen == false) {
                            gatenorth.interact("Open");
                            delay(RandomGenerator.nextInt(600, 800));
                            gatenorthopen = true;
                        }
                        else if(largedoor != null && gatenorthopen == true && largedooropen == false) {
                            Execution.delayUntil(5000 , () -> player.getX() < 2648);
                            largedoor.interact("Open");
                            delay(RandomGenerator.nextInt(600, 800));
                            largedooropen = true;
                        }
                        else if(largedooropen == true) {
                            GroundItem lever = GroundItemQuery.newQuery().name("Lever").ids(83).results().nearest();
                            if(lever != null) {
                                lever.interact("Take");
                                delay(RandomGenerator.nextInt(600, 1200));
                                if (Interfaces.isOpen(1622)) {
                                Item items = InventoryItemQuery.newQuery(773).ids(83).results().first();
                                if (items != null) {
                                    println("Item Slot" + items.getSlot());
                                    MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                    delay(RandomGenerator.nextInt(600, 1200));
                                    templeofikovdown = false;
                                    return;
                                }
                                }
                            }
                        }
    
                    }
                    else if(Backpack.contains(83) && leveruse == false) {
                        println("Inserting Level in Bracket");
                        Item levelpiece = InventoryItemQuery.newQuery(93).ids(83).results().first();
                        //boolean leveruse = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, levelpiece.getSlot(), 96534533);
                        SceneObject lveverbracket = SceneObjectQuery.newQuery().name("Lever bracket").results().nearest();
                        boolean leverusemini = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, levelpiece.getSlot(), 96534533);
                        if(leverusemini == true) {
                            MiniMenu.interact(SELECT_OBJECT.getType(), lveverbracket.getId(), lveverbracket.getCoordinate().getX(), lveverbracket.getCoordinate().getY());
                            delay(RandomGenerator.nextInt(1800, 2000));
                            leveruse = true;
                            //templeofikovdown = false;
                            //return;
                        }
                    }
                    else if(leveruse == true && leveredpulled == false) {
                        SceneObject leverpull = SceneObjectQuery.newQuery().name("Lever").option("Pull").results().nearest();
                        
                        println("Trying to Pull Lever");
    
                        if(leverpull != null) {
                            leverpull.interact("Pull");
                            delay(RandomGenerator.nextInt(600, 800));
                            leveredpulled = true;
                        }
                        // else if(gateeast != null) {
                        //     gateeast.interact("Open");
                        //     delay(RandomGenerator.nextInt(600, 800));
                        // }
                    }
                    else if(leveredpulled == true) {
                        SceneObject gateeast = SceneObjectQuery.newQuery().name("Gate").option("Open").id(90).results().nearest();
                        if(gateeast != null) {
                            gateeast.interact("Open");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else if(VarManager.getVarpValue(2149) == 10) {
                    SceneObject leverpull = SceneObjectQuery.newQuery().name("Lever").option("Pull").results().nearest();
                        
                        println("Trying to Pull Lever");
    
                        if(leverpull != null) {
                            leverpull.interact("Pull");
                            delay(RandomGenerator.nextInt(600, 800));
                            
                        }

                }
                else if(VarManager.getVarpValue(2149) == 20) {

                    if(Backpack.getCount(225) <20 && (!Backpack.contains(78) || !Equipment.contains(78)))
                    {
                        withdrawlimproot();
                    }
                    else if(Backpack.getCount(225) >= 20 && !Backpack.contains(78) && !Equipment.contains(78)) {
                        traveltoicearea();
                    }
                    else if(Backpack.contains(78) && !Equipment.contains(78)) {
                        Backpack.interact("Ice arrows", "Wield");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(Equipment.contains(78) && !Backpack.contains(78)) {
                        traveltofiregaint();
                    }
                    
                }
                
                break;
                case 30:
                SceneObject leverpull = SceneObjectQuery.newQuery().name("Lever").option("Pull").results().nearest();
                SceneObject door = SceneObjectQuery.newQuery().name("Door").option("Open").id(92).results().nearest();
                Npc firegaint = NpcQuery.newQuery().name("Fire Giant").results().first();
                if(levelwithtrapsopen2 == false) {
                    
                    println("Trying to Pull Lever");
                    if(leverpull != null) {
                        leverpull.interact("Pull");
                        delay(RandomGenerator.nextInt(600, 800));
                        levelwithtrapsopen2 = true;
                    }
                }
                else if(levelwithtrapsopen2 == true && dooropen == false) {
                    println("Trying to Open Door");
                    
                    if(door != null) {
                        door.interact("Open");
                        delay(RandomGenerator.nextInt(600, 800));
                        dooropen = true;
                    }
                }
                else if(dooropen == true && firegaint != null) {
                    println("Door is open");
                }
                break;
                case 40:
                SceneObject door1 = SceneObjectQuery.newQuery().name("Door").option("Open").id(93).results().nearest();
                Npc winelda = NpcQuery.newQuery().name("Winelda").results().first();
                if(door1 != null && dooropen1 == false) {
                    door1.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                    dooropen1 = true;
                }
                else if(dooropen1 == true && winelda != null) {
                    winelda.interact(NPCAction.NPC1);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 60:
                SceneObject pushwall = SceneObjectQuery.newQuery().name("Wall").option("Push").results().nearest();
                SceneObject table = SceneObjectQuery.newQuery().name("Table").option("Take-staff").results().nearest();
                if(Backpack.contains(84)) {
                    if(!luciencircle.contains(player)) {
                        DebugScript.moveTo(luciencoord);
                    }
                    else if(luciencircle.contains(player)) {
                        talktoLucien();
                    }
                } 


                if(pushwall != null && pushwallopen == false && !Backpack.contains(84)) {
                    pushwall.interact("Push");
                    delay(RandomGenerator.nextInt(600, 800));
                    pushwallopen = true;
                }
                else if(pushwallopen == true && table != null && !Backpack.contains(84)) {
                    table.interact("Take-staff");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
            }
        }
    }

    public static void traveltofiregaint() {
        if(!templeofikovarea.contains(player) && templeofikovdown2 == false) {
            DebugScript.moveTo(templeofikov);
        }
        else if(templeofikovarea.contains(player) && templeofikovdown2 == false) {
            SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").option("Climb-down").results().nearest();
            if(ladder != null) {
                ladder.interact("Climb-down");
                delay(RandomGenerator.nextInt(600, 800));
                templeofikovdown2 = true;
            }
        }
        else if(templeofikovdown2 == true) {
            SceneObject gatenorth = SceneObjectQuery.newQuery().name("Gate").option("Open").id(95).results().nearest();
            SceneObject levelwithtraps = SceneObjectQuery.newQuery().name("Lever").option("Search for traps").results().nearest();
            SceneObject levelwithtraps2 = SceneObjectQuery.newQuery().name("Lever").option("Pull").results().nearest();
            SceneObject door = SceneObjectQuery.newQuery().name("Door").option("Open").id(93).results().nearest();
                        if(gatenorth != null && gatenorthopen1 == false) {
                            gatenorth.interact("Open");
                            delay(RandomGenerator.nextInt(600, 800));
                            gatenorthopen1 = true;
                        }
                        else if(levelwithtraps != null && gatenorthopen1 == true && levelwithtrapsopen1 == false) {
                            levelwithtraps.interact("Search for traps");
                            delay(RandomGenerator.nextInt(600, 800));
                            
                            levelwithtrapsopen1 = true;
                        }
                        else if(levelwithtrapsopen1 == true && levelwithtraps2 != null && levelwithtrapsopen2 == false) {
                            levelwithtraps2.interact("Pull");
                            delay(RandomGenerator.nextInt(600, 800));
                            levelwithtrapsopen2 = true;
                        }
                        else if(levelwithtrapsopen2 == true && door != null && dooropen == false) {
                            door.interact("Open");
                            delay(RandomGenerator.nextInt(600, 800));
                            dooropen = true;
                        }
                        else if(dooropen == true) {
                            println("Door is open");   
                        }
        }
    }

    public static void traveltoicearea() {
        if(!templeofikovarea.contains(player) && templeofikovdown1 == false) {
            DebugScript.moveTo(templeofikov);
        }
        else if(templeofikovarea.contains(player) && templeofikovdown1 == false) {
            SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").option("Climb-down").results().nearest();
            if(ladder != null) {
                ladder.interact("Climb-down");
                delay(RandomGenerator.nextInt(600, 800));
                templeofikovdown1 = true;
            }
        }
        else if(templeofikovdown1 == true && eastgateopen == false) {
            SceneObject gateeast = SceneObjectQuery.newQuery().name("Gate").option("Open").id(90).results().nearest();
            if(gateeast != null) {
                gateeast.interact("Open");
                delay(RandomGenerator.nextInt(600, 800));
                eastgateopen = true;
            }
        }
        else if(eastgateopen == true) {
            Coordinate nearchest = new Coordinate(2699, 9839,0);
            SceneObject closedchest = SceneObjectQuery.newQuery().name("Closed chest").option("Open").hidden(false).results().nearest();
            SceneObject openchest = SceneObjectQuery.newQuery().name("Open chest").option("Search").hidden(false).results().nearest();
            if(openchest != null) {
                println("Serach for Ice arrow Manually");
            }
            else if(closedchest != null) {
                closedchest.interact("Open");
                delay(RandomGenerator.nextInt(600, 800));

            }
            else {
                DebugScript.moveTo(nearchest);
            }
            
            println("Search for Ice arrow");
        }
    }

    public static void withdrawlimproot() {
        if(!adibankarea.contains(player)) {
            DebugScript.moveTo(adibankcoordinate);
        }
        else {
            SceneObject adibank = SceneObjectQuery.newQuery().name("Bank booth").option("Bank").results().nearest();
            if(adibank != null && !Bank.isOpen()) {
                adibank.interact("Bank");
                delay(RandomGenerator.nextInt(600, 800));
            }
            else if(Bank.isOpen() && adibank != null && Backpack.getCount(225) < 20) {
                Bank.withdraw("Limpwurt root", 6);
                delay(RandomGenerator.nextInt(600, 800));
                Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                GameInput.setIntInput(20);
                delay(RandomGenerator.nextInt(600, 800));
            }
        }
    }


    public static void talktoLucien()
    {
        Npc lucien = NpcQuery.newQuery().name("Lucien").results().first();
        if (lucien != null) {
            lucien.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
