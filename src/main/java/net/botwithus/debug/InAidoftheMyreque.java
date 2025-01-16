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
public class InAidoftheMyreque {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3489, 9823, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate Florincord = new Coordinate(3483, 3242, 0);
    static Area.Circular Florinarea = new Area.Circular(Florincord, 10);
    static Coordinate canffisbank = new Coordinate(3511,3482,0);
    static Area.Circular canffisbankarea = new Area.Circular(canffisbank, 10);
    static Coordinate bankfixcord = new Coordinate(3515, 3238, 0);
    static Area.Circular bankfixarea = new Area.Circular(bankfixcord, 10);
    static Coordinate cornelius = new Coordinate(3497, 3212, 0);
    static Area.Circular corneliusarea = new Area.Circular(cornelius, 10);

    static Coordinate woodenboard = new Coordinate(3481, 9834, 0);
    static Area.Circular woodenboardarea = new Area.Circular(woodenboard, 10);
    static Coordinate alkharidfurance = new Coordinate(3287, 3190, 0);
    static Area.Circular alkharidfurancearea = new Area.Circular(alkharidfurance, 10);
    static Coordinate mausoleumwellcord = new Coordinate(3424, 9892, 0);
    static Area.Circular mausoleumwellarea = new Area.Circular(mausoleumwellcord, 10);

    static Coordinate VeliafPubCoord = new Coordinate(3493, 9629, 0);
    static Area.Circular VeliafPubArea = new Area.Circular(VeliafPubCoord, 10);
    static Coordinate pubcoordbag = new Coordinate(3493, 3231, 0);
    static Area.Circular pubcoordbagarea = new Area.Circular(pubcoordbag, 10);

    static int trip = 0;
    static int talktovillarger = 1;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(11498);
        player = Client.getLocalPlayer().getServerCoordinate();

 


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... In Aid of the Myreque");

            if (startarea.contains(player)) {
                SceneObject stalagmite = SceneObjectQuery.newQuery().name("Stalagmite").option("Squeeze-past").results().first();
                if (stalagmite != null) {
                    stalagmite.interact("Squeeze-past");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                talktoVeliafHurtz();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 20:
                if(!Florinarea.contains(player)) {
                    DebugScript.moveTo(Florincord);
                }
                else {
                    talktoVeliafHurtz();
                }
                break;
                case 30:
                SceneObject openchest = SceneObjectQuery.newQuery().name("Open chest").option("Search").results().first();
                if (openchest != null) {
                    openchest.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));

                    Item food = InventoryItemQuery.newQuery(93).categories(58).results().first();
                    var fooduse = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, food.getSlot(), 96534533);
                    if (fooduse) {
                        delay(RandomGenerator.nextInt(600, 800));
                        openchest.interact(ObjectAction.OBJECT2);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 40:
                talktoFlorin();
                break;
                case 60:
                
                SceneObject gate = SceneObjectQuery.newQuery().name("Gate").option("Jump").results().first();
                if (gate != null && player.getY() >= 3244) {
                    gate.interact("Jump");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if (player.getY() < 3244)
                {
                    SceneObject brokendownwall = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();
                    if (brokendownwall != null) {
                        brokendownwall.interact("Climb-over");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 70:
                SceneObject rubble = SceneObjectQuery.newQuery().name("Rubble").option("Mine").results().first();
                if (rubble != null) {
                    rubble.interact("Mine");
                    Execution.delayUntil(4000, () -> Client.getLocalPlayer().getAnimationId() != 1);
                }
                break;
                case 80:
                SceneObject trapdoor = SceneObjectQuery.newQuery().name("Trapdoor").option("Open").results().first();
                if (trapdoor != null) {
                    trapdoor.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 90:
                SceneObject trapdoor1 = SceneObjectQuery.newQuery().name("Trapdoor").option("Climb-down").results().first();
                if (trapdoor1 != null) {
                    trapdoor1.interact("Climb-down");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 100:
                
                EntityResultSet<SceneObject> rubbledown = SceneObjectQuery.newQuery().name("Rubble").option("Mine").results();
                EntityResultSet<SceneObject> rubbledownremove = SceneObjectQuery.newQuery().name("Rubble").option("Remove").results();
                SceneObject rubblebasement = rubbledown.nearestTo(player);
                SceneObject rubblebasementremove = rubbledownremove.nearestTo(player);
                if(rubblebasement != null && rubblebasementremove == null) {
                    rubblebasement.interact("Mine");
                    delay(RandomGenerator.nextInt(1200, 1500));
                }
                else if(rubblebasementremove != null) {
                    rubblebasementremove.interact("Remove");
                    delay(RandomGenerator.nextInt(1200, 1500));
                }
                break;
                case 110:
                 SceneObject plaque = SceneObjectQuery.newQuery().name("Plaque").option("Search").results().first();
                // if (plaque != null) {
                //     plaque.interact("Search");
                //     delay(RandomGenerator.nextInt(600, 800));
                // }
                SceneObject ladderup = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().first();
                SceneObject brokendownwall = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();
                Npc Razvan = NpcQuery.newQuery().name("Razvan").results().first();
                if (ladderup != null && plaque != null) {
                    ladderup.interact("Climb-up");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(brokendownwall != null && plaque == null && player.getY() >= 3231 && brokendownwall.distanceTo(player) <= 3) {
                    brokendownwall.interact("Climb-over");
                    delay(RandomGenerator.nextInt(600, 800));
                    
                }
                else if(Razvan != null && plaque == null ) {
                    talktorazvan();
                }
                break;
                case 140:
                if(trip == 0){
                if(Backpack.getCount("Plank") < 10 && Backpack.getQuantity("nails") < 30)
                {
                    if(!canffisbankarea.contains(player)) {
                        DebugScript.moveTo(canffisbank);
                    }
                    else {
                        SceneObject canffisbank = SceneObjectQuery.newQuery().name("Bank booth").option("Bank").results().first();
                        if (canffisbank != null && !Bank.isOpen()) {
                            canffisbank.interact("Bank");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else if(canffisbank != null && Bank.isOpen() && !Backpack.isEmpty()) {
                            Bank.depositAll();
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else if(canffisbank != null && Bank.isOpen() && Backpack.isEmpty()) {
                            Bank.withdraw("Plank",6);
                            delay(RandomGenerator.nextInt(600, 800));
                            Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                            GameInput.setIntInput(11);
                            delay(RandomGenerator.nextInt(600, 800));
                            Bank.withdraw("Steel nails", 6);
                            delay(RandomGenerator.nextInt(600, 800));
                            Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                            GameInput.setIntInput(44);
                            delay(RandomGenerator.nextInt(600, 800));

                            trip = 1;
                        }
    
                    }
                }
                    
                }
                else if(trip == 1){
                    SceneObject brokenroof = SceneObjectQuery.newQuery().name("Broken Roof").option("Search").results().first();
                    SceneObject ladderupbank = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().nearestTo(player);
                    SceneObject ladderdownbank = SceneObjectQuery.newQuery().name("Ladder").option("Climb-down").results().nearestTo(player);
                    Item plank = InventoryItemQuery.newQuery(93).name("Plank").results().first();
                    if(!bankfixarea.contains(player) && brokenroof == null) {
                        println("Debugging running");
                        DebugScript.moveTo(bankfixcord);
                    }
                    else {
                        if(ladderupbank != null && player.getZ() == 0) {
                            println("Debugging Ladder up");
                            ladderupbank.interact("Climb-up");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        // else if(ladderdownbank != null && player.getZ() == 2) {
                        //     println("Debugging Ladder down");
                        //     ladderdownbank.interact("Climb-down");
                        //     delay(RandomGenerator.nextInt(600, 800));
                        // }
                        else if(brokenroof != null && player.getZ() == 2 && plank != null) {
                            
                            println("Fixing Broken roof");
                            boolean plankuseroof = MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, plank.getSlot(), 96534533);
                            if(plankuseroof) {
                                delay(RandomGenerator.nextInt(600, 800));
                                brokenroof.interact(ObjectAction.OBJECT1);
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                            //brokenroof.interact("Search");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        }
                    }
                
                

                break;
                case 150:
                SceneObject ladderdownbank = SceneObjectQuery.newQuery().name("Ladder").option("Climb-down").results().nearestTo(player);
                Item plank = InventoryItemQuery.newQuery(93).name("Plank").results().first();
                if(ladderdownbank != null && player.getZ() == 2) {
                    ladderdownbank.interact("Climb-down");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else
                {
                    SceneObject damagewall = SceneObjectQuery.newQuery().name("Damaged wall").option("Search").results().nearestTo(player);
                    if(damagewall != null && plank != null) {
                        boolean plankusewall = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, plank.getSlot(), 96534533);
                        if(plankusewall) {
                            delay(RandomGenerator.nextInt(600, 800));
                            damagewall.interact(ObjectAction.OBJECT1);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        
                    }
                }
                break;
                case 160:
                talktoAurel();
                break;
                case 165:
                if(VarManager.getVarbitValue(11499) == 0 && VarManager.getVarbitValue(11500) == 0 && VarManager.getVarbitValue(11501) == 0) {
                    if(!canffisbankarea.contains(player)) {
                        DebugScript.moveTo(canffisbank);
                    }
                    else {
                        SceneObject canffisbank = SceneObjectQuery.newQuery().name("Bank booth").option("Bank").results().first();
                        
                            if (canffisbank != null && !Bank.isOpen() && !Backpack.contains("Bronze hatchet") && !Backpack.contains("Raw mackerel") && !Backpack.contains("Tinderbox")) {
                                canffisbank.interact("Bank");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                            else if(canffisbank != null && Bank.isOpen() && Backpack.containsAnyExcept("Crate") && !Backpack.contains("Bronze hatchet") && !Backpack.contains("Raw mackerel") && !Backpack.contains("Tinderbox")) {
                                Bank.depositAllExcept("Crate");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                            else if(canffisbank != null && Bank.isOpen() && Backpack.contains(7630) && !Backpack.contains("Bronze hatchet") && !Backpack.contains("Raw mackerel") && !Backpack.contains("Tinderbox")) {
                                Bank.withdraw("Bronze hatchet",6);
                                delay(RandomGenerator.nextInt(600, 800));
                                Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                GameInput.setIntInput(10);
                                delay(RandomGenerator.nextInt(600, 800));
                                Bank.withdraw("Raw mackerel", 6);
                                delay(RandomGenerator.nextInt(600, 800));
                                Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                GameInput.setIntInput(10);
                                delay(RandomGenerator.nextInt(600, 800));
                                Bank.withdraw("Tinderbox", 6);
                                delay(RandomGenerator.nextInt(600, 800));
                                Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                GameInput.setIntInput(3);
                                delay(RandomGenerator.nextInt(600, 800));
        
                                
                            }
                            else if(Backpack.getCount("Bronze hatchet") == 10 && Backpack.getCount("Raw mackerel") == 10 && Backpack.getCount("Tinderbox") == 3) {
                                Backpack.interact("Crate", "Fill");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }
                    else if(VarManager.getVarbitValue(11499) == 10 && VarManager.getVarbitValue(11500) == 10 && VarManager.getVarbitValue(11501) == 3) {
                        if(!bankfixarea.contains(player)) {
                            DebugScript.moveTo(bankfixcord);
                        }
                        else {
                            talktoAurel();
                        }
                    }
                    
                
                break;
                case 170:
                if(!canffisbankarea.contains(player) && !Backpack.contains("Plank") && !Backpack.contains("Steel nails")) {
                    DebugScript.moveTo(canffisbank);
                }
                else if(canffisbankarea.contains(player) && !Backpack.contains("Plank") && !Backpack.contains("Steel nails")){
                    
                        SceneObject canffisbank = SceneObjectQuery.newQuery().name("Bank booth").option("Bank").results().first();
                        
                            if (canffisbank != null && !Bank.isOpen()) {
                                canffisbank.interact("Bank");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                            else if(canffisbank != null && Bank.isOpen() && !Backpack.isEmpty()) {
                                Bank.depositAll();
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                            else if(canffisbank != null && Bank.isOpen()) {
                                Bank.withdraw("Plank",6);
                                delay(RandomGenerator.nextInt(600, 800));
                                Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                GameInput.setIntInput(5);
                                delay(RandomGenerator.nextInt(600, 800));
                                Bank.withdraw("Steel nails", 6);
                                delay(RandomGenerator.nextInt(600, 800));
                                Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                GameInput.setIntInput(20);
                                delay(RandomGenerator.nextInt(600, 800));
                                Bank.withdraw("Swamp paste", 6);
                                delay(RandomGenerator.nextInt(600, 800));
                                Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                GameInput.setIntInput(1);
                                delay(RandomGenerator.nextInt(600, 800));
        
                                
                            }
                            
                        
                    
                }
                else if(Backpack.contains("Plank") && Backpack.contains("Steel nails") && Backpack.contains("Swamp paste")){
                    SceneObject bankbooth = SceneObjectQuery.newQuery().name("Bank booth").option("Search").results().first();
                    SceneObject Damagedwall = SceneObjectQuery.newQuery().name("Damaged wall").option("Search").results().first();
                    Item plank1 = InventoryItemQuery.newQuery(93).name("Plank").results().first();
                    if(!corneliusarea.contains(player)) {
                        DebugScript.moveTo(cornelius);
                    }
                    else {
                        if(bankbooth != null && plank1 !=null)
                        {
                            boolean plankusebooth = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, plank1.getSlot(), 96534533);
                            if(plankusebooth) {
                                delay(RandomGenerator.nextInt(600, 800));
                                MiniMenu.interact(SELECT_OBJECT.getType(), 12759, bankbooth.getCoordinate().getX(), bankbooth.getCoordinate().getY());
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                        else if(Damagedwall != null && plank1 != null) {
                            boolean plankusebooth = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, plank1.getSlot(), 96534533);
                            if(plankusebooth) {
                                delay(RandomGenerator.nextInt(600, 800));
                                MiniMenu.interact(SELECT_OBJECT.getType(), 12760, Damagedwall.getCoordinate().getX(), Damagedwall.getCoordinate().getY());;
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }
                }
                break;
                case 180:
                SceneObject Damagedwall = SceneObjectQuery.newQuery().name("Damaged wall").option("Search").results().first();
                    Item plank1 = InventoryItemQuery.newQuery(93).name("Plank").results().first();
                if(Damagedwall != null && plank1 != null) {
                    boolean plankusebooth = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, plank1.getSlot(), 96534533);
                    if(plankusebooth) {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(SELECT_OBJECT.getType(), 12760, Damagedwall.getCoordinate().getX(), Damagedwall.getCoordinate().getY());;
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }

                break;
                case 190:
                talktoCornelius();
                
                break;
                case 200:
                if(talktovillarger ==1)
                {
                    talktoGrigore();
                    talktovillarger = 2;
                }
                else if(talktovillarger ==2)
                {
                    talktoValeria();
                    talktovillarger = 3;
                }
                else if(talktovillarger ==3)
                {
                    talktosimona();
                }
                break;
                case 205:
                SceneObject bankbooth = SceneObjectQuery.newQuery().name("Bank Booth").option("Bank").results().first();
                if(bankbooth != null && !Bank.isOpen() && !Backpack.contains("Steel bar") && !Backpack.contains("Coal")) {
                    bankbooth.interact("Bank");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(bankbooth != null && Bank.isOpen() && !Backpack.isEmpty() && !Backpack.contains("Steel bar") && !Backpack.contains("Coal")) {
                    Bank.depositAll();
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(bankbooth != null && Bank.isOpen() && !Backpack.contains("Steel bar") && !Backpack.contains("Coal")) {
                    Bank.withdraw("Steel bar", 6);
                    delay(RandomGenerator.nextInt(600, 800));
                    Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                    GameInput.setIntInput(2);
                    delay(RandomGenerator.nextInt(600, 800));
                    Bank.withdraw("Coal", 6);
                    delay(RandomGenerator.nextInt(600, 800));
                    Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                    GameInput.setIntInput(1);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(Backpack.contains("Steel bar") && Backpack.contains("Coal")) {
                    //Backpack.interact("Crate", "Fill");
                    SceneObject furnace = SceneObjectQuery.newQuery().name("Broken furnace").option("Search").results().first();
                    Item steelbar = InventoryItemQuery.newQuery(93).name("Steel bar").results().first();
                    if(furnace != null && steelbar != null) {
                        boolean steelbaruse = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, steelbar.getSlot(), 96534533);
                        if(steelbaruse) {
                            delay(RandomGenerator.nextInt(600, 800));
                            MiniMenu.interact(SELECT_OBJECT.getType(), 12805, furnace.getCoordinate().getX(), furnace.getCoordinate().getY());
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    
                }
                break;
                case 210:
                SceneObject furnace = SceneObjectQuery.newQuery().name("Repaired furnace").option("Search").results().first();
                Item coal = InventoryItemQuery.newQuery(93).name("Coal").results().first();
                if(furnace != null && coal != null) {
                    MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, coal.getSlot(), 96534533);
                    delay(RandomGenerator.nextInt(600, 800));
                    MiniMenu.interact(SELECT_OBJECT.getType(), 12805, furnace.getCoordinate().getX(), furnace.getCoordinate().getY());
                    delay(RandomGenerator.nextInt(1800, 2400));

                   

                }else if(furnace !=null && coal == null)
                {
                    furnace.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 230:
                if(VarManager.getVarbitValue(11503) == 0) {
                    if(!Backpack.contains("Silver sickle (b)"))
                {
                    SceneObject bankbooth1 = SceneObjectQuery.newQuery().name("Bank Booth").option("Bank").results().first();
                    if(bankbooth1 != null && !Bank.isOpen() && !Backpack.contains("Silver sickle (b)")) {
                        bankbooth1.interact("Bank");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(bankbooth1 != null && Bank.isOpen() && !Backpack.contains("Silver sickle (b)")) {
                        Bank.withdraw("Silver sickle (b)", 1);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(Backpack.contains("Silver sickle (b)") )
                {
                    talktoGadderanks();
                }
                }
                else if(VarManager.getVarbitValue(11503) == 1 && VarManager.getVarbitValue(11504) == 0)
                {
                    
                    talktoWiskit();
                }
                else if(VarManager.getVarbitValue(11503) == 1 && VarManager.getVarbitValue(11504) == 1 && VarManager.getVarbitValue(11505) == 0)
                {
                    talktovampyreJuvinate();
                }
                break;
                case 240, 250, 260:
                if(VarManager.getVarbitValue(11510) !=0 && VarManager.getVarbitValue(11507) ==0)
                {
                    Npc Gadderanks = NpcQuery.newQuery().name("Gadderanks").option("Attack").results().first();
                    if(Gadderanks != null)
                    {
                        Gadderanks.interact("Attack");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(VarManager.getVarbitValue(11510) !=0 && (VarManager.getVarbitValue(11507) ==1 || VarManager.getVarbitValue(11507) ==2))
                {
                    Backpack.interact("Silver sickle (b)", "Wield");
                    delay(RandomGenerator.nextInt(600, 800));
                    Npc Vam = NpcQuery.newQuery().name("Vampyre Juvinate").results().first();
                    if(Vam != null)
                    {
                        Vam.interact("Attack");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(VarManager.getVarbitValue(11507) ==3)
                {
                    talktoGadderanks();
                }
                break;
                case 280:
                talktoVeliafHurtz();
                break;
                case 290:  // Need to talk to Polmafi, Radigad and then Ivan and play the game manually
                if (startarea.contains(player)) {
                    SceneObject stalagmite = SceneObjectQuery.newQuery().name("Stalagmite").option("Squeeze-past").results().first();
                    if (stalagmite != null) {
                        stalagmite.interact("Squeeze-past");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    talktoVeliafHurtz();
                } else {
                    DebugScript.moveTo(startcord);
                }
                break;
                case 300:
                talktoRadigadPonfit();
                break;
                case 310:
                talktoIvanStrom();
                println("Playing the game manually, Select Route 1");
                break;
                case 315:
                SceneObject Mausoleumentrance = SceneObjectQuery.newQuery().name("Mausoleum entrance").option("Enter").results().first();
                Npc Drezel = NpcQuery.newQuery().name("Drezel").results().first();
                if(Mausoleumentrance != null) {
                    Mausoleumentrance.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(Drezel != null) {
                    Drezel.interact(NPCAction.NPC1);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 320:
                break;
                case 325:
                break;
                case 330, 340:
                break;
                case 350:
                SceneObject keyhole = SceneObjectQuery.newQuery().name("Keyhole").option("Search").results().first();
                SceneObject trapdoorlib = SceneObjectQuery.newQuery().name("Trapdoor").option("Climb-down").results().first();
                Item key = InventoryItemQuery.newQuery(93).name("Temple library key").results().first();
                if(keyhole != null && key != null && trapdoorlib == null) {
                    MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, key.getSlot(), 96534533);
                    delay(RandomGenerator.nextInt(600, 800));
                    MiniMenu.interact(SELECT_OBJECT.getType(), 12765, keyhole.getCoordinate().getX(), keyhole.getCoordinate().getY());
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(trapdoorlib != null) {
                    trapdoorlib.interact("Climb-down");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 360:
                SceneObject Bookcase = SceneObjectQuery.newQuery().name("Bookcase").option("Search").results().last();
                if(Bookcase != null) {
                    Bookcase.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 370:
                Backpack.interact("The Sleeping Seven", "Read");
                delay(RandomGenerator.nextInt(600, 800));
                break;
                case 375:
                if(!canffisbankarea.contains(player) && !Backpack.contains("Rope") && !Backpack.contains("Water rune") && !Backpack.contains("Cosmic rune") && !Backpack.contains("Soft clay") && !Backpack.contains("Sapphire") && !Backpack.contains("Silver bar") && !Backpack.contains("Mithril bar")) {
                    DebugScript.moveTo(canffisbank);
                }
                else if(canffisbankarea.contains(player) && !Backpack.contains("Rope") && !Backpack.contains("Water rune") && !Backpack.contains("Cosmic rune") && !Backpack.contains("Soft clay") && !Backpack.contains("Sapphire") && !Backpack.contains("Silver bar") && !Backpack.contains("Mithril bar")) {
                    SceneObject bankboothcanffis = SceneObjectQuery.newQuery().name("Bank booth").option("Bank").results().first();
                    if(bankboothcanffis != null && !Bank.isOpen() && !Backpack.contains("Rope") && !Backpack.contains("Water rune") && !Backpack.contains("Cosmic rune") && !Backpack.contains("Soft clay") && !Backpack.contains("Sapphire") && !Backpack.contains("Silver bar") && !Backpack.contains("Mithril bar")) {
                        bankboothcanffis.interact("Bank");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(bankboothcanffis != null && Bank.isOpen() && !Backpack.contains("Rope") && !Backpack.contains("Water rune") && !Backpack.contains("Cosmic rune") && !Backpack.contains("Soft clay") && !Backpack.contains("Sapphire") && !Backpack.contains("Silver bar") && !Backpack.contains("Mithril bar")) {
                        Bank.withdraw("Rope", 2);
                        delay(RandomGenerator.nextInt(600, 800));
                        Bank.withdraw("Water rune", 2);
                        delay(RandomGenerator.nextInt(600, 800));
                        Bank.withdraw("Cosmic rune", 2);
                        delay(RandomGenerator.nextInt(600, 800));
                        Bank.withdraw("Soft clay", 2);
                        delay(RandomGenerator.nextInt(600, 800));
                        Bank.withdraw("Cut sapphire", 2);
                        delay(RandomGenerator.nextInt(600, 800));
                        Bank.withdraw("Silver bar", 2);
                        delay(RandomGenerator.nextInt(600, 800));
                        Bank.withdraw("Mithril bar", 2);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(Backpack.contains("Rope") && Backpack.contains("Water rune") && Backpack.contains("Cosmic rune") && Backpack.contains("Soft clay") && Backpack.contains("Sapphire") && Backpack.contains("Silver bar") && Backpack.contains("Mithril bar")) {
                    if(!woodenboardarea.contains(player)) {
                        DebugScript.moveTo(woodenboard);
                    }
                    else {
                        SceneObject woodenboard = SceneObjectQuery.newQuery().name("Woodenboards").option("Smash-board").results().first();
                        if(woodenboard != null) {
                            woodenboard.interact("Smash-board");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else if(woodenboard == null) {
                            SceneObject CaveEntrance = SceneObjectQuery.newQuery().name("Cave entrance").option("Enter").results().nearest();
                            if(CaveEntrance != null) {
                                CaveEntrance.interact("Enter");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }
                }
                break;
                case 380:
                SceneObject tombs = SceneObjectQuery.newQuery().name("Tomb").option("Search").results().first();
                Item softclay = InventoryItemQuery.newQuery(93).name("Soft clay").results().first();
                if(tombs != null && softclay != null) {
                    MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, softclay.getSlot(), 96534533);
                    delay(RandomGenerator.nextInt(600, 800));
                    MiniMenu.interact(SELECT_OBJECT.getType(), 12802, tombs.getCoordinate().getX(), tombs.getCoordinate().getY());
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 390:
                // SceneObject tombs1 = SceneObjectQuery.newQuery().name("Tomb").option("Search").results().first();
                // if(tombs1 != null) {
                //     Lodestone.AL_KHARID.teleport();
                //     delay(RandomGenerator.nextInt(600, 800));
                // }
                if(!alkharidfurancearea.contains(player)) {
                    DebugScript.moveTo(alkharidfurance);
                }
                else {
                    SceneObject furnacesmelt = SceneObjectQuery.newQuery().name("Furnace").option("Smelt").results().nearest();
                    if(furnacesmelt != null && !Interfaces.isOpen(37)) {
                        furnacesmelt.interact("Smelt");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(furnacesmelt != null && Interfaces.isOpen(37)) {
                        // boolean selecctsilverbar = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 1, 2423894);
                        // delay(RandomGenerator.nextInt(1200, 1800));
                        // if(selecctsilverbar) {
                        //     delay(RandomGenerator.nextInt(600, 800));
                        //     MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 13, 2424935);
                        //     delay(RandomGenerator.nextInt(1200, 1800));
                        //     MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 2424995);
                        //     delay(RandomGenerator.nextInt(1200, 1800));
                        // }
                        println("Furnace is open and Create a solvthrid rod");
                    }
                    
                }

                break;
                case 400:
                println("Enchant a silvthril rod with Lvl1-Enchant");
                break;
                case 410:
                if(!mausoleumwellarea.contains(player)) {
                    DebugScript.moveTo(mausoleumwellcord);
                }
                else {
                    SceneObject Well = SceneObjectQuery.newQuery().name("Well").option("Search").results().first();
                    Item rod = InventoryItemQuery.newQuery(93).ids(7638).results().first();
                    if(Well != null && rod != null) {
                        MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, rod.getSlot(), 96534533);
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(SELECT_OBJECT.getType(), 3485, Well.getCoordinate().getX(), Well.getCoordinate().getY());
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 420:
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

    public static void talktoFlorin()
    {
        Npc Florin = NpcQuery.newQuery().name("Florin").results().first();
        if (Florin != null) {
            Florin.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktorazvan()
    {
        Npc Razvan = NpcQuery.newQuery().name("Razvan").results().first();
        if (Razvan != null) {
            Razvan.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoAurel() {
        Npc Aurel = NpcQuery.newQuery().name("Aurel").results().first();
        if (Aurel != null) {
            Aurel.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoValeria() {
        Npc Valeria = NpcQuery.newQuery().name("Valeria").results().first();
        if (Valeria != null) {
            Valeria.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoCornelius() {
        Npc Cornelius = NpcQuery.newQuery().name("Cornelius").results().first();
        if (Cornelius != null) {
            Cornelius.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoGrigore() {
        Npc Grigore = NpcQuery.newQuery().name("Grigore").results().first();
        if (Grigore != null) {
            Grigore.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktosimona() {
        Npc Simona = NpcQuery.newQuery().name("Simona").results().first();
        if (Simona != null) {
            Simona.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoGadderanks()
    {
        Npc Gadderanks = NpcQuery.newQuery().name("Gadderanks").results().first();
        if (Gadderanks != null) {
            Gadderanks.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void talktovampyreJuvinate()
    {
        Npc Vam = NpcQuery.newQuery().name("Vampyre Juvinate").byType(3515).results().first();
        if (Vam != null) {
            Vam.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktovampyreJuvinate2()
    {
        Npc Vam = NpcQuery.newQuery().name("Vampyre Juvinate").byType(3514).results().first();
        if (Vam != null) {
            Vam.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoWiskit()
    {
        Npc Wiskit = NpcQuery.newQuery().name("Wiskit").results().first();
        if (Wiskit != null) {
            Wiskit.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoIvanStrom()
    {
        Npc IvanStrom = NpcQuery.newQuery().name("Ivan Strom").results().first();
        if (IvanStrom != null) {
            IvanStrom.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoRadigadPonfit()
    {
        Npc RadigadPonfit = NpcQuery.newQuery().name("Radigad Ponfit").results().first();
        if (RadigadPonfit != null) {
            RadigadPonfit.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
