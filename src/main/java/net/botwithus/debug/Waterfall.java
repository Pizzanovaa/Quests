package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.js5.types.vars.VarDomainType;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.queries.results.ResultSet;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.input.GameInput;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import static net.botwithus.rs3.script.Execution.delay;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.Item;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.rs3.script.Execution.delay;
import static net.botwithus.rs3.script.ScriptConsole.println;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Bank;



public class Waterfall {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2521, 3495, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate bookcoordinate = new Coordinate(2519, 3427, 1);
    static Area.Circular bookarea = new Area.Circular(bookcoordinate, 10);
    static Coordinate gnomedungeoncord = new Coordinate(2531, 3156, 0);
    static Area.Circular gnomedungeonarea = new Area.Circular(gnomedungeoncord, 10); 
    static Coordinate gatecoordinate = new Coordinate(2515, 9575, 0);
    static Area.Circular gatearea = new Area.Circular(gatecoordinate, 10);
    static Coordinate tombs = new Coordinate(2556, 3445, 0);
    static Area.Circular tombsarea = new Area.Circular(tombs, 10);
    static Coordinate ardibank = new Coordinate(2617, 3334, 0);
    static Area.Circular ardibankarea = new Area.Circular(ardibank, 10);
    static Coordinate gatecoordinate1 = new Coordinate(2513, 3495, 0);
    static Area.Circular gatearea1 = new Area.Circular(gatecoordinate1, 10);
    static Coordinate onrockinturrupt = new Coordinate(2512, 3481, 0);
    static Area.Circular onrockinturruptarea = new Area.Circular(onrockinturrupt, 10);
    static Coordinate tree = new Coordinate(2511, 3466, 0);
    static Area.Circular treearea = new Area.Circular(tree, 5);
    static Coordinate crate = new Coordinate(2589, 9888, 0);
    static Area.Circular cratearea = new Area.Circular(crate, 5);


    static int pillar1 = 0;
    static int pillar2 = 0;
    static int pillar3 = 0;
    static int pillar4 = 0;
    static int pillar5 = 0;
    static int pillar6 = 0;
    static int airunen1 = 0;
    static int waterrunen1 = 0;
    static int earthrunen1 = 0;
       

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2353);
        player = Client.getLocalPlayer().getServerCoordinate();
       
        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            println("Starting quest... Waterfall Quest");

            if (startarea.contains(player)) {
                //Quest Giver
                talktoAlmera();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 1:
                if(!onrockinturruptarea.contains(player)){
                    DebugScript.moveTo(onrockinturrupt);
                }
                    // SceneObject Gate = SceneObjectQuery.newQuery().name("Gate").id(1551).results().nearest();
                    // if(Gate !=null && Gate.isHidden() == false)
                    // {
                    //     println(" Try to open" + Gate.interact("Open"));
                    //     Execution.delayUntil(3000, () -> !Client.getLocalPlayer().isMoving());
                        
                    // }
                    // else if(Gate !=null )
                    // {
                    //     SceneObject raft = SceneObjectQuery.newQuery().name("Log raft").results().nearest();
                    //     if(raft !=null)
                    //     {
                    //         println(" Try to board" + raft.interact("Board"));
                    //     }
                    // }
                    
                    //board the raft and talk to hudon
                    break;
                case 2:
                Npc hudon = NpcQuery.newQuery().name("Hudon").results().first();
                
                if (hudon != null && player.distanceTo(hudon) < 4) {
                   
                   talktohudon();

                   SceneObject river = SceneObjectQuery.newQuery().name("River").results().nearest();
                    if(river !=null)
                    {
                         println(" Try to swim" + river.interact("Swim"));
                    }
                }
                else
                {
                    if(!bookarea.contains(player)){
                        DebugScript.moveTo(bookcoordinate);
                    }
                    else if(bookarea.contains(player) && Backpack.contains("Book on Baxtorian")){
                        Coordinate bookcasecoordinate1 = new Coordinate(2520, 3426, 1);
                        SceneObject bookcase = SceneObjectQuery.newQuery().name("Bookcase").results().nearestTo(bookcasecoordinate1);
                        if(bookcase !=null){
                            bookcase.interact("Search");
                        }
                    }
                    else if(Backpack.contains("Book on Baxtorian")){
                        Component book = ComponentQuery.newQuery(1473).item(292).results().stream().findFirst().orElse(null);
                            if(book != null)
                            {
                                book.interact("Read");
                                delay(RandomGenerator.nextInt(1100, 1500));
                            }
                    }
                }
                break;
                case 3:
                if(!gnomedungeonarea.contains(player) && Client.getLocalPlayer().getServerCoordinate().getRegionId() != 10133 && !Backpack.contains(294)){
                    DebugScript.moveTo(gnomedungeoncord);
                }
                else if(Client.getLocalPlayer().getServerCoordinate().getRegionId() == 10033 && !Backpack.contains(294)){
                    SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").results().nearest();
                    if(ladder !=null){
                        ladder.interact("Climb-down");
                    }
                    //Region ID 10033
                    //Region ID 10133
                }
                else if(Client.getLocalPlayer().getServerCoordinate().getRegionId() == 10133 && !Backpack.contains(293) && !Backpack.contains(294)){
                    SceneObject chest = SceneObjectQuery.newQuery().ids(1990).results().nearest();
                    if(chest !=null){
                        chest.interact("Search");
                    }
                }
                else if(Backpack.contains(293))
                {
                    SceneObject gate = SceneObjectQuery.newQuery().ids(1991).results().nearest();
                    Npc golrie = NpcQuery.newQuery().name("Golrie").results().first();
                    if(gate !=null){
                        if(!gatearea.contains(player)){
                            DebugScript.moveTo(gatecoordinate);
                        }
                        else{ 
                            Execution.delayUntil(3000, () -> !Client.getLocalPlayer().isMoving());
                            Item key = InventoryItemQuery.newQuery(93).ids(293).results().first();
                            if(key !=null){
                            if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, key.getSlot(), 96534533)) {
                                        delay(RandomGenerator.nextInt(200, 400));
                                        gate.interact(SelectableAction.SELECT_OBJECT.getType());
                                        delay(RandomGenerator.nextInt(200, 400));
                                        Execution.delayUntil(3000, () -> player.distanceTo(golrie) < 4);
                                        talktoGolrie();
                                    }
                                }
                            }
                            
                    }
                }
                else if(Backpack.contains(294) && Client.getLocalPlayer().getServerCoordinate().getRegionId() != 10137){
                    if(!tombsarea.contains(player)){
                        DebugScript.moveTo(tombs);
                    }
                    
                    
                    else
                    {
                        ///
                        SceneObject tomb = SceneObjectQuery.newQuery().name("Glarial's tombstone").results().nearest();
                        if(tomb !=null){
                            Item pebble = InventoryItemQuery.newQuery(93).ids(294).results().first();
                            if(pebble !=null){
                                if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, pebble.getSlot(), 96534533)) {
                                    delay(RandomGenerator.nextInt(200, 400));
                                    tomb.interact(SelectableAction.SELECT_OBJECT.getType());
                                    delay(RandomGenerator.nextInt(200, 400));
                                }
                            }
                            }
                        }
                    
                }
                break;
                case 4:
                    int airrunes = Backpack.getQuantity("Air rune");
                    int waterrunes = Backpack.getQuantity("Water rune");
                    int earthrunes = Backpack.getQuantity("Earth rune");
                    int rope = Backpack.getCount("Rope");
                        
                if(Client.getLocalPlayer().getServerCoordinate().getRegionId() == 10137 && Backpack.contains(294) && !Backpack.contains(295)){
                    SceneObject chest = SceneObjectQuery.newQuery().name("Closed chest").hidden(false).results().nearest();
                    SceneObject openchest = SceneObjectQuery.newQuery().name("Open chest").hidden(false).results().nearest();
                    if(chest !=null){
                        chest.interact("Open");
                    }
                    else if(openchest !=null){
                        openchest.interact("Search");
                    }
                }
                else if(Client.getLocalPlayer().getServerCoordinate().getRegionId() == 10137 && Backpack.contains(294) && Backpack.contains(295) && !Backpack.contains(296)){
                    ///
                    SceneObject tomb = SceneObjectQuery.newQuery().name("Glarial's tomb").results().nearest();
                    if(tomb !=null){
                        tomb.interact("Search");
                    }
                }
                else if(Client.getLocalPlayer().getServerCoordinate().getRegionId() == 10137 && Backpack.contains(294) && Backpack.contains(295) && Backpack.contains(296)){
                    ///
                    // SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").results().nearest();
                    // if(ladder !=null){
                    //     ladder.interact("Climb-up");
                    // }
                    if(!ardibankarea.contains(player)){
                        DebugScript.moveTo(ardibank);
                    }
                   
                }
                else if(Backpack.contains(294) && Backpack.contains(295) && Backpack.contains(296) && ardibankarea.contains(player) && airrunes < 6 && waterrunes < 6 && earthrunes < 6 && rope < 1)
                {                       
                        if( airrunes < 6 || waterrunes < 6 || earthrunes < 6 || rope < 1)
                        {
                            println("Not enough runes found");

                            if (Bank.isOpen()) {
                                if (airrunes < 6) {
                                    //Execution.delay(withdrawFromBank("Air rune", 6));
                                    Bank.withdraw("Air rune", 1);
                                    //Execution.delayUntil(withdrawFromBank("Air rune", 6), () -> airrunes >= 6);
                                    delay(RandomGenerator.nextInt(1200, 1800));
                                } else if (waterrunes < 6) {
                                    //Execution.delay(withdrawFromBank("Water rune", 6));
                                    Bank.withdraw("Water rune", 1);
                                    delay(RandomGenerator.nextInt(1200, 1800));
                                } else if (earthrunes < 6) {
                                    //Execution.delay(withdrawFromBank("Earth rune", 6));
                                    Bank.withdraw("Earth rune", 1);
                                    delay(RandomGenerator.nextInt(1200, 1800));
                                } else if (rope < 1) {
                                    //Execution.delay(withdrawFromBank("Rope", 1));
                                    Bank.withdraw("Rope", 2);
                                    delay(RandomGenerator.nextInt(1200, 1800));
                                } else {

                                    return;
                                }
                            }
            
                            SceneObject chest = SceneObjectQuery.newQuery().name("Bank booth").results().nearest();
                            if (chest != null) {
                                println("Interacting with chest " + chest.interact("Bank"));
                                //chest.interact("Use");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    
                }
                else if(Backpack.contains(294) && Backpack.contains(295) && Backpack.contains(296) && airrunes >= 6 && waterrunes >= 6 && earthrunes >= 6 && rope >= 1)
                {
                    
                    
                    if(!treearea.contains(player)){
                        DebugScript.moveTo(tree);
                    }
                    else{
                        
                        Item rope1 = InventoryItemQuery.newQuery(93).ids(954).results().first();
                        SceneObject tree1 = SceneObjectQuery.newQuery().name("Dead tree").id(2020).results().nearest();
                        if(rope1!=null){
                            boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, rope1.getSlot(), 96534533);
                            if(success) 
                                {
                                delay(RandomGenerator.nextInt(200, 400));
                                tree1.interact(SelectableAction.SELECT_OBJECT.getType());
                                delay(RandomGenerator.nextInt(1200, 1800));
                                SceneObject door = SceneObjectQuery.newQuery().name("Door").results().nearest();
                                if(door !=null){
                                    door.interact("Enter");
                                }
                                   
                                }
                            }
                    }
                }
                break;
                case 5:
                if(Backpack.contains(298))
                {
                    Coordinate largedooreastcoordinate = new Coordinate(2565, 9881, 0);
                    Coordinate door1 = new Coordinate(2565, 9881, 0);

                    SceneObject largerdoorwest = SceneObjectQuery.newQuery().name("Large door").results().nearestTo(largedooreastcoordinate);
                    if(largerdoorwest !=null && largerdoorwest.isHidden() == false){
                        largerdoorwest.interact("Open");
                    }
                    else if(largerdoorwest !=null && largerdoorwest.isHidden() == true){
                        
                        SceneObject door11 = SceneObjectQuery.newQuery().name("Door").results().nearestTo(door1);
                        if(door11 !=null){
                            door11.interact("Open");
                            delay(RandomGenerator.nextInt(1200, 1800));
                            
                        }

                    }
                }
                else{
                    
                    Coordinate largedooreastcoordinate = new Coordinate(2582, 9876, 0);
                    SceneObject largedooreast = SceneObjectQuery.newQuery().name("Large door").id(31820).results().nearestTo(largedooreastcoordinate);
                    if(largedooreast !=null && largedooreast.isHidden() == false){
                        largedooreast.interact("Open");
                    }
                    else if( largedooreast !=null && largedooreast.isHidden() == true)
                    {
                        SceneObject crate1 = SceneObjectQuery.newQuery().name("Crate").id(1999).results().nearest();
                        if(crate1 !=null){
                        crate1.interact("Search");
                        delay(RandomGenerator.nextInt(1200, 1800));
                    }
                    }
                }
                    
                    
                    
                
                break;

                case 6:
                    Coordinate door12 = new Coordinate(2566, 9901, 0);
                    SceneObject door121 = SceneObjectQuery.newQuery().name("Door").results().nearestTo(door12);
                    SceneObject pillar = SceneObjectQuery.newQuery().name("Pillar").results().nearest();
                    //println("Pillar distance: " + player.distanceTo(pillar));
                    //println("Door distance: " + door121.distanceTo(pillar));
                     if(door121 !=null && door121.distanceTo(pillar)  == player.distanceTo(pillar)){
                         Item key = InventoryItemQuery.newQuery(93).ids(298).results().first();
                         if(key !=null){
                             boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, key.getSlot(), 96534533);
                             if(success){
                                 delay(RandomGenerator.nextInt(200, 400));
                                 door121.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                         }   
                     }
                     else if(door121.distanceTo(pillar) > player.distanceTo(pillar)){
                        Coordinate pillar1coordinate = new Coordinate(2562, 9910, 0);
                        Coordinate pillar2coordinate = new Coordinate(2562, 9912, 0);
                        Coordinate pillar3coordinate = new Coordinate(2562, 9914, 0);
                        Coordinate pillar4coordinate = new Coordinate(2569, 9910, 0);
                        Coordinate pillar5coordinate = new Coordinate(2569, 9912, 0);
                        Coordinate pillar6coordinate = new Coordinate(2569, 9914, 0);
                        
                        SceneObject pillar11 = SceneObjectQuery.newQuery().name("Pillar").results().nearestTo(pillar1coordinate);
                        SceneObject pillar22 = SceneObjectQuery.newQuery().name("Pillar").results().nearestTo(pillar2coordinate);
                        SceneObject pillar33 = SceneObjectQuery.newQuery().name("Pillar").results().nearestTo(pillar3coordinate);
                        SceneObject pillar44 = SceneObjectQuery.newQuery().name("Pillar").results().nearestTo(pillar4coordinate);
                        SceneObject pillar55 = SceneObjectQuery.newQuery().name("Pillar").results().nearestTo(pillar5coordinate);
                        SceneObject pillar66 = SceneObjectQuery.newQuery().name("Pillar").results().nearestTo(pillar6coordinate);
                        Item airrune = InventoryItemQuery.newQuery(93).ids(556).results().first();
                        Item waterrune = InventoryItemQuery.newQuery(93).ids(555).results().first();
                        Item earthrune = InventoryItemQuery.newQuery(93).ids(557).results().first();
                        Item amulet = InventoryItemQuery.newQuery(93).ids(295).results().first();

                        if(pillar1 ==0 && pillar11 !=null)
                        {
                            if(airrune !=null && airunen1 ==0)
                            {
                                boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, airrune.getSlot(), 96534533);
                             if(success){
                                airunen1 = 1;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar11.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(waterrune !=null && waterrunen1 ==0){
                                boolean success1 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, waterrune.getSlot(), 96534533);
                             if(success1){
                                waterrunen1 = 1;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar11.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(earthrune !=null && earthrunen1 ==0){
                                boolean success2 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, earthrune.getSlot(), 96534533);
                             if(success2){
                                pillar1 = 1;
                                earthrunen1 = 1;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar11.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                       
                        }
                        else if(pillar2 ==0 && pillar22 !=null)
                        {
                            if(airrune !=null && airunen1 ==1)
                            {
                                boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, airrune.getSlot(), 96534533);
                             if(success){
                                airunen1 = 2;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar22.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(waterrune !=null && waterrunen1 ==1){
                                boolean success1 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, waterrune.getSlot(), 96534533);
                             if(success1){
                                waterrunen1 = 2;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar22.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(earthrune !=null && earthrunen1 ==1){
                                boolean success2 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, earthrune.getSlot(), 96534533);
                             if(success2){
                                pillar2 = 1;
                                earthrunen1 = 2;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar22.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                        }
                        else if(pillar3 ==0 && pillar33 !=null)
                        {
                            if(airrune !=null && airunen1 ==2)
                            {
                                boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, airrune.getSlot(), 96534533);
                             if(success){
                                airunen1 = 3;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar33.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(waterrune !=null && waterrunen1 ==2){
                                boolean success1 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, waterrune.getSlot(), 96534533);
                             if(success1){
                                waterrunen1 = 3;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar33.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(earthrune !=null && earthrunen1 ==2){
                                boolean success2 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, earthrune.getSlot(), 96534533);
                             if(success2){
                                pillar3 = 1;
                                earthrunen1 = 3;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar33.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                        }
                        else if(pillar4 ==0 && pillar44 !=null)
                        {
                            if(airrune !=null && airunen1 ==3)
                            {
                                boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, airrune.getSlot(), 96534533);
                             if(success){
                                airunen1 = 4;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar44.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(waterrune !=null && waterrunen1 ==3){
                                boolean success1 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, waterrune.getSlot(), 96534533);
                             if(success1){
                                waterrunen1 = 4;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar44.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(earthrune !=null && earthrunen1 ==3){
                                boolean success2 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, earthrune.getSlot(), 96534533);
                             if(success2){
                                pillar4 = 1;
                                earthrunen1 = 4;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar44.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                        }
                        else if(pillar5 ==0 && pillar55 !=null)
                        {
                            if(airrune !=null && airunen1 ==4)
                            {
                                boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, airrune.getSlot(), 96534533);
                             if(success){
                                airunen1 = 5;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar55.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(waterrune !=null && waterrunen1 ==4){
                                boolean success1 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, waterrune.getSlot(), 96534533);
                             if(success1){
                                waterrunen1 = 5;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar55.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(earthrune !=null && earthrunen1 ==4){
                                boolean success2 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, earthrune.getSlot(), 96534533);
                             if(success2){
                                
                                pillar5 = 1;
                                earthrunen1 = 5;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar55.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                        }
                        else if(pillar6 ==0 && pillar66 !=null)
                        {
                            if(airrune !=null && airunen1 ==5)
                            {
                                boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, airrune.getSlot(), 96534533);
                             if(success){
                                airunen1 = 6;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar66.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(waterrune !=null && waterrunen1 ==5){
                                boolean success1 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, waterrune.getSlot(), 96534533);
                             if(success1){
                                waterrunen1 = 6;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar66.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                             else if(earthrune !=null && earthrunen1 ==5){
                                boolean success2 = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, earthrune.getSlot(), 96534533);
                             if(success2){
                                
                                pillar6 = 1;
                                earthrunen1 = 6;
                                 delay(RandomGenerator.nextInt(200, 400));
                                 pillar66.interact(SelectableAction.SELECT_OBJECT.getType());
                                 delay(RandomGenerator.nextInt(1200, 1800));
                             }
                            }
                        }
                        else if(amulet !=null && pillar1 ==1 && pillar2 ==1 && pillar3 ==1 && pillar4 ==1 && pillar5 ==1 && pillar6 ==1){
                            println("Activating Glarial");
                            SceneObject Glarial = SceneObjectQuery.newQuery().id(98546).results().nearest();
                            
                            if(Glarial !=null){
                                boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, amulet.getSlot(), 96534533);
                                if(success){
                                    delay(RandomGenerator.nextInt(200, 400));
                                    Glarial.interact(SelectableAction.SELECT_OBJECT.getType());
                                    delay(RandomGenerator.nextInt(1200, 1800));
                                }
                            }
                            
                         }
                        
                }
            
                
                break;
                case 8:
                Item urn = InventoryItemQuery.newQuery(93).ids(296).results().first();
                SceneObject chalce = SceneObjectQuery.newQuery().name("Chalice of Eternity").id(2014).results().nearest();
                if(chalce !=null && urn !=null){
                    boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, urn.getSlot(), 96534533);
                    if(success){
                        delay(RandomGenerator.nextInt(200, 400));
                        chalce.interact(SelectableAction.SELECT_OBJECT.getType());
                        delay(RandomGenerator.nextInt(1200, 1800));
                    }
                }
                break;
            }
            
        }
    }

    public static void talktoAlmera() {
        Npc malgi = NpcQuery.newQuery().name("Almera").results().first();
        if (malgi != null) {
            malgi.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktohudon() {
        Npc hudon = NpcQuery.newQuery().name("Hudon").results().first();
        if (hudon != null) {
            hudon.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoGolrie() {
        Npc golrie = NpcQuery.newQuery().name("Golrie").results().first();
        if (golrie != null) {
            golrie.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }


        /**
     * Withdraws a specified amount of an item from the bank.
     *
     * @param itemName The name of the item to withdraw.
     * @param amount   The amount of the item to withdraw.
     * @return The delay time in milliseconds before the next action.
     */
    public static long withdrawFromBank(String itemName, int amount)
    {
        // Query the bank interface for the component that matches the item name
        Component itemComponent = ComponentQuery.newQuery(new int[]{517})
                .itemName(itemName)
                .results()
                .first();

        // If the component is found, attempt to withdraw the specified amount
        if (itemComponent != null)
        {
            String interactOption = getWithdrawOption(amount);
            boolean withdrawSuccess = itemComponent.interact(interactOption);

            if (withdrawSuccess)
            {
                println("[Bank] Successfully withdrew " + amount + " " + itemName);
            }
            else
            {
                println("[Bank] Failed to withdraw " + amount + " " + itemName);
            }
        }
        else
        {
            println("[Error] Item " + itemName + " not found in bank.");
        }
        return RandomGenerator.nextInt(1100, 1550);
    }

    /**
     * Determines the interaction option based on the specified withdrawal amount.
     *
     * @param amount The amount to withdraw.
     * @return The interaction option as a string.
     */
    private static String getWithdrawOption(int amount)
    {
        switch (amount)
        {
            case 1 ->
            {
                return "Withdraw-1";
            }
            case 5 ->
            {
                return "Withdraw-5";
            }
            case 10 ->
            {
                return "Withdraw-10";
            }
            case 0 ->
            {
                return "Withdraw-All";
            }
            default ->
            {
                GameInput.setIntInput(amount);
                return "Withdraw-X";
            }
        }
    }
}
