package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.actionbar.ActionBar;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.input.GameInput;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Bank;
import net.botwithus.api.game.hud.traversal.Lodestone;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.queries.results.EntityResultSet;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.Item;


public class ChristmasReunion {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(5246, 9773, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate pixielumb = new Coordinate(3160, 3237, 0);;
    static Area.Circular pixielumbarea = new Area.Circular(pixielumb, 2);
    static Coordinate pixiealk = new Coordinate(3410, 3167, 0);;
    static Area.Circular pixiealkarea = new Area.Circular(pixiealk, 10);
    static Coordinate pixiede = new Coordinate(3438, 3746, 1);
    static Area.Circular pixiedeisarea = new Area.Circular(pixiede, 10);
    static Coordinate iffie = new Coordinate(3206, 3416, 0);
    static Area.Circular iffiearea = new Area.Circular(iffie, 10);


    static int counterspellchristmas = 0;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(56876);
        player = Client.getLocalPlayer().getServerCoordinate();



        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Christmas Reunion");

            Npc diango = NpcQuery.newQuery().name("Diango").results().first();

            if (startarea.contains(player) || diango != null) {
                talktodiango();
            } else {
                if(ActionBar.containsAbility("Christmas Village Teleport") && diango == null)
                {
                    ActionBar.useAbility("Christmas Village Teleport");
                   
                    delay(RandomGenerator.nextInt(600, 800));
                    counterspellchristmas = counterspellchristmas + 1;
                }
                //DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {

                case 5:
                break;
                case 10:
                 SceneObject bank = SceneObjectQuery.newQuery().name("Bank chest").results().nearest();
                 //SceneObject firtree = SceneObjectQuery.newQuery().name("Fir").hidden(false).results().nearest();
                 EntityResultSet<SceneObject> treeResults = SceneObjectQuery.newQuery()
                    .name("Fir")
                    .option("Chop down")
                    .results();
                 SceneObject  pileofplanks = SceneObjectQuery.newQuery().name("Pile of planks").hidden(false).results().nearest();
                 SceneObject  unfiniishedwork = SceneObjectQuery.newQuery().name("Unfinished workbench").hidden(false).results().nearest();
                 SceneObject tree = treeResults.nearest();
                 SceneObject paintingbench = SceneObjectQuery.newQuery().name("Painting bench").hidden(false).results().nearest();
                 SceneObject carvingbench = SceneObjectQuery.newQuery().name("Carving bench").hidden(false).results().nearest();
                 SceneObject finishingbench = SceneObjectQuery.newQuery().name("Finishing bench").hidden(false).results().nearest();

                 if(Backpack.getCount(57922) <3 && !Backpack.contains("Split fir logs") && (paintingbench == null || carvingbench == null || finishingbench == null))   //Fir Wood
                 {
                    if(tree != null && !tree.isHidden() )
                    {   
                        //println("Chopping down fir tree");
                        println("Fir Log: " + Backpack.getCount(57922));
                        println("Chop down: " + tree.interact("Chop down"));
                        Execution.delayUntil(RandomGenerator.nextInt(5000, 10000), () -> Backpack.getCount(57922) > 3);
                        //delay(RandomGenerat5or.nextInt(600, 800));
                    }

                 }
                 else if(Backpack.getCount(57922) >=3 && Backpack.getCount(57923) <=3 && (paintingbench == null || carvingbench == null || finishingbench == null))
                 {
                    EntityResultSet<SceneObject> stumpResults = SceneObjectQuery.newQuery()
                    .name("Log splitting stump")
                    .option("Split logs")
                    .results();

                    SceneObject stump = stumpResults.nearest();
                    if (stump != null) {
                        println("Splitting fir logs...");
                        stump.interact("Split logs");
                        Execution.delayUntil(90000, () -> !Backpack.contains("Fir logs"));
                        Execution.delay(RandomGenerator.nextInt(600, 2000));
                    }
                 }
                 else if(Backpack.getQuantity(1539) <= 15 && Backpack.getCount(57923) >=3 && (paintingbench == null || carvingbench == null || finishingbench == null))  // Steel nails
                 {
                    if (Client.getLocalPlayer().isMoving()) {
                        return;
                    }

                    if(bank != null)
                    {
                        bank.interact("Use");
                        if(Interfaces.isOpen(517))
                        {
                            delay(RandomGenerator.nextInt(600, 800));
                            Bank.withdraw(1539, 6);
                            delay(RandomGenerator.nextInt(600, 800));
                            Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                            GameInput.setIntInput(15);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    
                 }
                 else if(pileofplanks != null && (paintingbench == null || carvingbench == null || finishingbench == null) && Backpack.getQuantity("Steel nails") >= 15 && Backpack.getCount(57923) >=3)
                 {
                    if (Client.getLocalPlayer().isMoving()) {
                        return;
                    }
                    pileofplanks.interact("Build");
                    Execution.delay(RandomGenerator.nextInt(3000, 5000));
                    //Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                 }
                 else if(unfiniishedwork != null && pileofplanks == null && (paintingbench == null || carvingbench == null || finishingbench == null))
                 {
                    unfiniishedwork.interact("Build");
                    Execution.delay(RandomGenerator.nextInt(3000, 5000));
                 }
                 else if(paintingbench != null && carvingbench != null && finishingbench != null)
                 {
                    talktodiango();
                 }
                break;
                case 20:
                Npc diango = NpcQuery.newQuery().name("Diango").results().first();
                if(diango != null && VarManager.getVarbitValue(56885) == 0 && VarManager.getVarbitValue(56886) == 0 && VarManager.getVarbitValue(56887) == 0)
                {
                    Lodestone.BURTHORPE.teleport();
                }
                if(VarManager.getVarbitValue(56885) == 1 && VarManager.getVarbitValue(56886) == 1 && VarManager.getVarbitValue(56887) == 1 && diango == null)
                {
                    ActionBar.useAbility("Christmas Village Teleport");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else
                {
                    talktodiango();
                }
                if(diango == null) //&& VarManager.getVarbitValue(56885) == 0 && VarManager.getVarbitValue(56886) == 0 && VarManager.getVarbitValue(56887) == 0
                {
                    if(VarManager.getVarbitValue(56885) == 0)   //pxie in lumb  -- pixie spawn anywhere 56880
                    {
                        if(!pixielumbarea.contains(player))
                        {
                            DebugScript.moveTo(pixielumb);
                            
                        }
                        else
                        {
                          
                             if(VarManager.getVarbitValue(56880) == 0)
                                {
                                    if(Backpack.contains(58020))
                                    {
                                        Component orb = ComponentQuery.newQuery(1473).item(58020).results().stream().findFirst().orElse(null);
                                        if(orb != null)
                                        {
                                        orb.interact("Scan");
                                        delay(RandomGenerator.nextInt(1100, 1500));
                                        }
                                    }
                                }
                                else 
                                {
                                    talktoAofie();
                                }
                                
                            
                        }
                    } else if(VarManager.getVarbitValue(56886) == 0)
                    {
                        if(!pixiealkarea.contains(player))
                        {
                            DebugScript.moveTo(pixiealk);
                        }
                        else 
                        {
                            if(VarManager.getVarbitValue(56881) == 0)
                            {
                                if(Backpack.contains(58020))
                                {
                                    Component orb = ComponentQuery.newQuery(1473).item(58020).results().stream().findFirst().orElse(null);
                                    if(orb != null)
                                    {
                                    orb.interact("Scan");
                                    delay(RandomGenerator.nextInt(1100, 1500));
                                    }
                                }
                            }
                            else 
                            {
                                talktoShauna();
                            }
                        }
                    }
                    else if(VarManager.getVarbitValue(56887) == 0)
                    {
                        if(!pixiedeisarea.contains(player))
                        {
                            DebugScript.moveTo(pixiede);
                        }
                        else
                        {
                            if(VarManager.getVarbitValue(56882) == 0)
                            {
                                if(Backpack.contains(58020))
                                {
                                    Component orb = ComponentQuery.newQuery(1473).item(58020).results().stream().findFirst().orElse(null);
                                    if(orb != null)
                                    {
                                        orb.interact("Scan");
                                    delay(RandomGenerator.nextInt(1100, 1500));
                                    }
                                }
                            }
                            else 
                            {
                                talktoMaeve();
                            }
                        }
                    }
                    
                }
                break;
                case 30:
                    if(Backpack.contains("Red dye") && Backpack.contains("Green dye") && Backpack.getCount("Bolt of cloth") >= 6 && Backpack.contains("Thread"))
                    {
                        Npc diango1 = NpcQuery.newQuery().name("Diango").results().first();
                        if(diango1 != null)
                        {
                            Lodestone.VARROCK.teleport();
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else if(diango1 == null)
                        {
                            if(!iffiearea.contains(player))
                        {
                            DebugScript.moveTo(iffie);
                        }
                        else
                        {
                            talktoIffie();
                        }
                        }
                    
                    }
                    else
                    {
                        itemset2();
                    }
                break;
                case 35:
                Item reddye = InventoryItemQuery.newQuery(93).name("Red dye").results().first();
                Item greendye = InventoryItemQuery.newQuery(93).name("Green dye").results().first();

                if(Backpack.contains(58022) && reddye != null && greendye != null)
                {
                    println("Using red dye on green dye");
                    boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, reddye.getSlot(), 96534533);
                    if(success)
                    {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, greendye.getSlot(), 96534533);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(Backpack.contains(58021) && !Backpack.contains(58023))
                {
                    Backpack.interact("Pixie uniform pattern", "Craft");
                    delay(RandomGenerator.nextInt(600, 800));
                    if(Interfaces.isOpen(1370))
                    {
                        MiniMenu.interact(ComponentAction.DIALOGUE.getType(),0,-1,89784350);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    
                }
                else if(Backpack.contains(58023))
                {
                    Npc diango1 = NpcQuery.newQuery().name("Diango").results().first();
                    if(diango1 == null)
                    {
                        ActionBar.useAbility("Christmas Village Teleport");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(diango1 != null)
                    {
                        talktodiango();
                    }
                }
                

                break;
                case 40:
                break;
                case 45:
                // Npc aoife = NpcQuery.newQuery().name("Aoife").results().first();
                // if(aoife != null)
                // {
                //     aoife.interact("Talk to");
                //     delay(RandomGenerator.nextInt(600, 800));
                // }

                SceneObject hotchocolate = SceneObjectQuery.newQuery().name("Hot chocolate storage").hidden(false).option("Add hot chocolate powder").results().nearest();
                SceneObject biscuit = SceneObjectQuery.newQuery().name("Biscuit storage").hidden(false).option("Add biscuits").results().nearest();
                if(Backpack.contains("Chocolate dust") && Backpack.contains("Bucket of milk") && hotchocolate != null && VarManager.getVarbitValue(56890) ==1)
                {
                    //SceneObject hotchocolate = SceneObjectQuery.newQuery().name("Hot chocolate storage").hidden(false).results().nearest();
                    if(hotchocolate != null)
                    {
                    hotchocolate.interact("Add hot chocolate powder");
                    delay(RandomGenerator.nextInt(600, 800));
                    if(Interfaces.isOpen(1469))
                    {
                        GameInput.setIntInput(0);
                        delay(RandomGenerator.nextInt(600, 800));
                        GameInput.setIntInput(2);
                        delay(RandomGenerator.nextInt(600, 800));
                        GameInput.setIntInput(3);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    }
                }
                else if(Backpack.contains("Biscuits") && biscuit != null && VarManager.getVarbitValue(56891) ==1)
                {
                    
                    if(biscuit != null)
                    {
                    biscuit.interact("Add biscuits");
                    delay(RandomGenerator.nextInt(600, 800));
                    if(Interfaces.isOpen(1469))
                    {
                        GameInput.setIntInput(1);
                        delay(RandomGenerator.nextInt(600, 800));
                        GameInput.setIntInput(2);
                        delay(RandomGenerator.nextInt(600, 800));
                        GameInput.setIntInput(2);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    }
                }
                else if(hotchocolate == null && biscuit == null && VarManager.getVarbitValue(56893) == 1 && VarManager.getVarbitValue(56892) == 1)
                {
                    talktodiango();
                }
                else 
                {
                    itemset3();
                }
                break;
            }
        }
    }


    public static void talktodiango()
    {
        Npc diango = NpcQuery.newQuery().name("Diango").results().first();
        if (diango != null) {
            diango.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoAofie()
    {
        Npc aofie = NpcQuery.newQuery().name("Aoife").results().first();
        if (aofie != null) {
            aofie.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoShauna()
    {
        Npc shauna = NpcQuery.newQuery().name("Shauna").results().first();
        if (shauna != null) {
            shauna.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }

    }
    public static void talktoMaeve()
    {
        Npc maeve = NpcQuery.newQuery().name("Maeve").results().first();
        if (maeve != null) {
            maeve.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoIffie()
    {
        Npc iffie = NpcQuery.newQuery().name("Iffie").results().first();
        if (iffie != null) {
            iffie.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void itemset2()
    {
        SceneObject bank = SceneObjectQuery.newQuery().name("Bank chest").hidden(false).results().nearest();
        if(bank != null)
        {
            bank.interact("Use");
            delay(RandomGenerator.nextInt(600, 800));
            if(Interfaces.isOpen(517))
            { 
                if(!Backpack.contains("Red dye"))
                {
                    Bank.withdraw("Red dye", 2);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(!Backpack.contains("Green dye"))
                {
                    Bank.withdraw("Green dye", 2);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(!Backpack.contains("Bolt of cloth"))
                {
                    Bank.withdraw("Bolt of cloth", 6);
                    Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                    GameInput.setIntInput(6);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(!Backpack.contains("Thread"))
                {
                    Bank.withdraw("Thread", 2);
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
        }
    }

    public static void itemset3()
    {
        SceneObject bank = SceneObjectQuery.newQuery().name("Bank chest").hidden(false).results().nearest();
        if(bank != null)
        {
            bank.interact("Use");
            delay(RandomGenerator.nextInt(600, 800));
            if(Interfaces.isOpen(517))
            {
                if(!Backpack.contains("Biscuits") && VarManager.getVarbitValue(56891) ==1)
                {
                    Bank.withdraw("Biscuits", 2);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(!Backpack.contains("Chocolate dust") && VarManager.getVarbitValue(56890) ==1)
                {
                    Bank.withdraw("Chocolate dust", 2);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(!Backpack.contains("Bucket of milk") && VarManager.getVarbitValue(56890) ==1)
                {
                    Bank.withdraw("Bucket of milk", 2);
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
        }

    }

}
