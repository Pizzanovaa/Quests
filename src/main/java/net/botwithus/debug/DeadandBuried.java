package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Distance;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.input.GameInput;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.rs3.script.Execution.delay;
import static net.botwithus.rs3.script.ScriptConsole.print;

import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
//import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Bank;
import net.botwithus.api.game.hud.traversal.Lodestone;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;

import static net.botwithus.rs3.game.movement.Movement.walkTo;

public class DeadandBuried {
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3292, 3544, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate raptor = new Coordinate(3292, 3544, 0);
    static Area.Circular raptorarea = new Area.Circular(raptor, 10);
    static Coordinate reldo = new Coordinate(3210, 3494, 0);
    static Area.Circular reldoarea = new Area.Circular(reldo, 10); 
    static Coordinate billCoordinate = new Coordinate(3290, 3555, 0);
    static Area.Circular billarea = new Area.Circular(billCoordinate, 10);
    static Coordinate heraldofVarrock = new Coordinate(3240, 3433, 0);
    static Area.Circular heraldofVarrockarea = new Area.Circular(heraldofVarrock, 10);
    static Coordinate cryptentrance = new Coordinate(3291, 3609, 0);
    static Area.Circular cryptentrancearea = new Area.Circular(cryptentrance, 10);
    static Coordinate overseersiv = new Coordinate(3317, 3541, 0);
    static Area.Circular overseersivarea = new Area.Circular(overseersiv, 10);

    static boolean hasClickedBuild = false;
    static boolean Activeplay = true;

    static boolean hastalkedtoRaptor = false;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(53035);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Dead and Buried");

            if (startarea.contains(player)) {
                talktoTheRaptor();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 4:
                    SceneObject portal = SceneObjectQuery.newQuery().name("Dead and Buried").option("Continue").results().first();
                    SceneObject portal2 = SceneObjectQuery.newQuery().name("Instance").option("Leave").results().first();
                    if (portal != null && portal2 == null) {
                        portal.interact("Continue");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    if(portal2 !=null){
                        
                        Npc heraldofVarrock = NpcQuery.newQuery().name("Herald of Varrock").results().first();
                        if(heraldofVarrock != null){
                            heraldofVarrock.interact("Talk to");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }

                    break;
                case 8:
                    // TALK TO HERALD OF VARROCK
                    break;
                case 10:
                int acadiaframes = Backpack.getCount("Acadia frame");
                int stonewallsegments = Backpack.getCount("Stone wall segment");

                if(acadiaframes <= 14 && stonewallsegments <= 6)
                {
                    SceneObject bank = SceneObjectQuery.newQuery().name("Bank chest").results().nearest();
                    if(bank !=null){
                        bank.interact("Use");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Interfaces.isOpen(517))
                        {
                            if(Backpack.getCount("Acadia frame") < 14)
                            {
                                Bank.withdraw("Acadia frames", 6);
                                delay(RandomGenerator.nextInt(600, 800));
                                Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                GameInput.setIntInput(14);
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                            if(Backpack.getCount("Stone wall segment") < 6)
                            {
                                Bank.withdraw("Stone wall segment", 6);
                                delay(RandomGenerator.nextInt(600, 800));
                                Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                GameInput.setIntInput(6);
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }

                    }
                }
                else 
                {
                    talktoBill();
                }
                break;
                case 12:
                SceneObject contrutiionhostspot = SceneObjectQuery.newQuery().name("Optimal Construction hotspot").hidden(false).results().nearest();

                if(Backpack.getCount("Acadia frame") >= 14 && Backpack.getCount("Stone wall segment") >= 6)
                {
                    SceneObject blueprints = SceneObjectQuery.newQuery().name("Fort Forinthry blueprints").results().nearest();
                            if (blueprints != null) {
                                blueprints.interact("Check plans");
                                delay(RandomGenerator.nextInt(600, 800));

                                MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                }
                else 
                {
                    if(contrutiionhostspot != null)
                    {
                        if (!Client.getLocalPlayer().isMoving()) {
                            if (!hasClickedBuild && Distance.to(contrutiionhostspot) >= 1) {
                                contrutiionhostspot.interact("Build");
                                ScriptConsole.println("Found construction spot");
                                hasClickedBuild = true;

                                if (Activeplay) {
                                    Execution.delay(RandomGenerator.nextInt(800, 1200));
                                } else {
                                    Execution.delay(RandomGenerator.nextInt(3000, 10000));
                                }

                            } else if (hasClickedBuild && Distance.to(contrutiionhostspot) > 1) {
                                if (Activeplay) {
                                    Execution.delay(RandomGenerator.nextInt(800, 1200));
                                } else {
                                    Execution.delay(RandomGenerator.nextInt(3000, 10000));
                                }
                                hasClickedBuild = false;
                            }
                        }
                    }
                   
                }
                break;
                case 13:
                ScriptConsole.println("Talk to guardsofia");
                if(VarManager.getVarbitValue(53045) == 1)
                {
                    talktoGuardsofia();
                }
                break;
                case 14:
                

                if(!Backpack.containsItemByCategory(58))
                {
                    SceneObject bank = SceneObjectQuery.newQuery().name("Bank chest").results().nearest();
                    if(bank !=null){
                        bank.interact("Use");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Interfaces.isOpen(517))
                        {
                            
                                Bank.withdraw("Shark", 4);
                                delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else
                {
                    SceneObject portal4 = SceneObjectQuery.newQuery().name("Wilderness Crypt Exit").option("Exit").results().nearest();
                    if(!cryptentrancearea.contains(player) && !Client.getLocalPlayer().isMoving() && portal4 == null)
                    {
                        DebugScript.moveTo(cryptentrance);
                        
                    }
                    else
                    {
                       
                        SceneObject portal3 = SceneObjectQuery.newQuery().name("Dead and Buried").option("Continue").results().first();
                        
                        Npc theRaptor = NpcQuery.newQuery().name("The Raptor").results().nearest();
                        if(portal3 != null && portal4 == null) {
                            ScriptConsole.println("Entering wilderness crypt");
                            portal3.interact("Continue");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        ScriptConsole.println("Talking to the raptor");
                        if(theRaptor != null && portal4 != null)
                        {
                            theRaptor.interact("Talk to");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                break;
                case 16:
                if(VarManager.getVarbitValue(53046) < 447)
                {
                    SceneObject spookyhole = SceneObjectQuery.newQuery().name("Spooky hole").results().nearest();
                    if(spookyhole != null && !Client.getLocalPlayer().isMoving())
                    {
                        spookyhole.interact("Search");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(VarManager.getVarbitValue(53046) > 511)
                {
                    Npc ghouls = NpcQuery.newQuery().name("Material ghoul").results().nearest();
                    if(ghouls != null && !Client.getLocalPlayer().isMoving())
                    {
                        ghouls.interact("Attack");
                        delay(RandomGenerator.nextInt(600, 800));
                    }



                }
                else if(VarManager.getVarbitValue(53046) == 511)
                {
                    SceneObject bridge = SceneObjectQuery.newQuery().name("Bridge").results().nearest();
                    if(bridge != null)
                    {
                        bridge.interact("Construct");
                        delay(RandomGenerator.nextInt(5000, 8000));
                    }
                }

                break;
                case 18:
                SceneObject ancientdoor = SceneObjectQuery.newQuery().name("Ancient door").results().nearest();
                if(ancientdoor != null)
                {
                    ancientdoor.interact("Investigate");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 20:
                SceneObject pillar = SceneObjectQuery.newQuery().name("Pillar").hidden(false).results().first();
                SceneObject pillar2 = SceneObjectQuery.newQuery().name("Pillar").hidden(false).results().last();
                Npc theRaptor = NpcQuery.newQuery().name("The Raptor").option("Switch to").results().nearest();
                if(pillar != null && theRaptor == null)
                {
                    pillar.interact("Push");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(theRaptor != null && pillar2 != null)
                {
                    theRaptor.interact("Switch to");
                    delay(RandomGenerator.nextInt(600, 800));
                    pillar2.interact("Push");
                    delay(RandomGenerator.nextInt(600, 800));
                }

                break;
                case 22:
                SceneObject ancientdoor2 = SceneObjectQuery.newQuery().name("Ancient door").results().nearest();
                if(ancientdoor2 != null)
                {
                    ancientdoor2.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 24:
                if(Backpack.contains("Dragonkin device"))
                {
                    talktoTheRaptor();
                }
                break;
                case 26:
                SceneObject ancientdoor3 = SceneObjectQuery.newQuery().name("Ancient door").option("Search").results().nearest();
                SceneObject portalreldo = SceneObjectQuery.newQuery().name("Instance").option("Leave").results().first();
                if(Backpack.contains("Dragonkin device"))
                {
                    if(!reldoarea.contains(player) && ancientdoor3 != null)
                    {
                        ScriptConsole.println("Moving to Varrock");
                        Lodestone.VARROCK.teleport();
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(ancientdoor3 == null && !reldoarea.contains(player) && portalreldo == null)
                    {
                        DebugScript.moveTo(reldo);
                    }
                    else if(portalreldo == null)
                    {
                        SceneObject portal3 = SceneObjectQuery.newQuery().name("Dead and Buried").option("Continue").results().first();
                        
                        
                        if(portal3 != null) {
                            
                            portal3.interact("Continue");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    else if(portalreldo != null)
                    {
                        talktoReldo();
                    }
                }
                break;
                case 32:
                if(Backpack.contains("Dragonkin device") && Backpack.contains(55163))
                {
                    Backpack.interact("Potion of dreamless sleep", "Drink");
                    delay(RandomGenerator.nextInt(600, 800));
                    Backpack.interact("Dragonkin device", "Inspect");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 50:

                //plates varbit --  Rock 1-- 53037 - 1 and 53038 - 1

                //plates varbit -- Rock 2 and Gate 1 -- 53037 - 2 and 53038 - 1  and 53039 - 1
                //plates varbit -- Gate 2 -- 53037 - 3 and 53038 - 1  and 53039 - 1 and 53040 - 1
                //plates varbit -- Gate 3 -- 53037 - 4 and 53038 - 1  and 53039 - 1 and 53040 - 1 and 53041 - 1
                //plates varbit -- Gate 4 -- 53037 - 5 and 53038 - 1  and 53039 - 1 and 53040 - 1 and 53041 - 1 and 53042 - 1
                //plates varbit -- Gate 5 -- 53037 - 6 and 53038 - 1  and 53039 - 1 and 53040 - 1 and 53041 - 1 and 53042 - 1 and 53043 - 1
                print("Manual interaction required with Puzzle room and nightmare room");
                break;
                case 52:
                Npc strangegirl = NpcQuery.newQuery().name("Strange girl").results().nearest();
                if(strangegirl != null)
                {
                    walkTo(strangegirl.getCoordinate().getX(), strangegirl.getCoordinate().getY(), true);
                    delay(RandomGenerator.nextInt(600, 800));
                }

                break;
                case 54:
                // SceneObject dimlight = SceneObjectQuery.newQuery().name("Dim light").results().nearest();
                // Npc theRaptorkill = NpcQuery.newQuery().name("The Raptor").option("Switch to").results().nearest();
                // Npc player = NpcQuery.newQuery().id(30089).option("Switch to").results().nearest();
                // Npc nightmare = NpcQuery.newQuery().name("The Nightmare").results().nearest();
                // if(dimlight != null && player == null && nightmare == null)
                // {
                //     if(player != null)
                //     {
                //         player.interact("Switch to");
                //         delay(RandomGenerator.nextInt(600, 800));
                //     }
                //     else
                //     {
                //         dimlight.interact("Interact");
                //         delay(RandomGenerator.nextInt(600, 800));
                //     }
                // }
                // else if(nightmare != null && player == null)
                // {
                //     nightmare.interact("Attack");
                //     delay(RandomGenerator.nextInt(600, 800));

                //     if(nightmare.getCurrentHealth() <=30)
                //     {
                //         theRaptorkill.interact("Switch to");
                //         delay(RandomGenerator.nextInt(600, 800));
                //     }
                // }

                break;
                case 88:
                if(!startarea.contains(player) && !hastalkedtoRaptor)
                {
                    DebugScript.moveTo(startcord);
                }
                else if(startarea.contains(player) && !hastalkedtoRaptor)
                {
                    talktoTheRaptor();
                    hastalkedtoRaptor = true;
                }
                else if(!cryptentrancearea.contains(player))
                {
                    DebugScript.moveTo(cryptentrance);
                }
                else
                {
                    SceneObject portal3 = SceneObjectQuery.newQuery().name("Dead and Buried").option("Continue").results().first();
                    SceneObject portal4 = SceneObjectQuery.newQuery().name("Wilderness Crypt Exit").option("Exit").results().nearest();
                        
                        Npc theRaptor2 = NpcQuery.newQuery().name("The Raptor").results().nearest();
                        if(portal3 != null && portal4 == null) {
                            ScriptConsole.println("Entering wilderness crypt");
                            portal3.interact("Continue");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        ScriptConsole.println("Talking to the raptor");
                        if(theRaptor2 != null && portal4 != null)
                        {
                            theRaptor2.interact("Talk to");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                }
                break;
                case 90:
                SceneObject ancientdoor4 = SceneObjectQuery.newQuery().name("Ancient door").option("Enter").results().nearest();
                SceneObject maptable = SceneObjectQuery.newQuery().name("Table").option("Investigate").results().nearest();
                
                if(ancientdoor4 != null)
                {
                    ancientdoor4.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(maptable != null)
                {
                    maptable.interact("Investigate");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 92:
                Npc zombies = NpcQuery.newQuery().name("Fetid zombie").results().nearest();
                GroundItem item = GroundItemQuery.newQuery().name("Map piece").results().nearest();
                if(item != null && Backpack.getCount("Map piece") < 10)
                    {
                        item.interact("Take");
                        delay(RandomGenerator.nextInt(600, 1200));
                        if (Interfaces.isOpen(1622)) {
                            Item items = InventoryItemQuery.newQuery(773).ids(55164).results().first();
                            if (items != null) {
                                ScriptConsole.println("Item Slot" + items.getSlot());
                                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                delay(RandomGenerator.nextInt(600, 1200));
                                return;
                            }
                        }
                    }
                if(zombies != null && item == null && Backpack.getCount("Map piece") < 10)
                {
                    zombies.interact("Attack");
                    delay(RandomGenerator.nextInt(600, 800));
                }

                if(Backpack.getCount("Map piece") == 10)
                {
                    SceneObject table = SceneObjectQuery.newQuery().name("Map table").option("Deposit").results().nearest();
                    if(table != null)
                    {
                        table.interact("Deposit");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 93:
                print("Manual interaction required with map table");
                break;
                case 96:
                SceneObject mural = SceneObjectQuery.newQuery().name("Mural").results().nearest();
                if(mural != null)
                {
                    Lodestone.FORT_FORINTHRY.teleport();
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(mural == null && !overseersivarea.contains(player))
                {
                    DebugScript.moveTo(overseersiv);
                }
                else 
                {
                    talktoOverseersiv();
                }
                break;
                case 98:
                talktoTheRaptor();
                break;
            }
        }
    }


    public static void talktoTheRaptor()
    {
        Npc raptor = NpcQuery.newQuery().name("The Raptor").results().first();
        if (raptor != null) {
            raptor.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoKingRoald()
    {
        Npc kingRoald = NpcQuery.newQuery().name("King Roald").results().first();
        if (kingRoald != null) {
            kingRoald.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoBill()
    {
        Npc bill = NpcQuery.newQuery().name("Bill").results().first();
        if (bill != null) {
            bill.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoHeraldofVarrock()
    {
        Npc heraldofVarrock = NpcQuery.newQuery().name("Herald of Varrock").results().first();
        if (heraldofVarrock != null) {
            heraldofVarrock.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoReldo()
    {
        Npc reldo = NpcQuery.newQuery().name("Reldo").results().first();
        if (reldo != null) {
            reldo.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoGuardsofia()
    {
        Npc guardsofia = NpcQuery.newQuery().name("Guard captain Sofía").results().nearest();
        if (guardsofia != null) {
            guardsofia.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoOverseersiv()
    {
        Npc overseersiv = NpcQuery.newQuery().name("Overseer Siv").results().nearest();
        if (overseersiv != null) {
            overseersiv.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
