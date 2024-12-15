package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
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
import net.botwithus.api.game.hud.traversal.Lodestone;

public class AncientAwakening {
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3319, 3541, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate cryptentrance = new Coordinate(3291, 3609, 0);
    static Area.Circular cryptentrancearea = new Area.Circular(cryptentrance, 10);
    static Coordinate jollyboarinn = new Coordinate(3281, 3513, 0);
    static Area.Circular jollyboarinnarea = new Area.Circular(jollyboarinn, 10);
    static Coordinate bridgechampguild = new Coordinate(3177, 3362, 0);
    static Area.Circular bridgechampguildarea = new Area.Circular(bridgechampguild, 10);
    static Coordinate forttownhall = new Coordinate(3304, 3560, 0);
    static Area.Circular forttownhallarea = new Area.Circular(forttownhall, 10);
    static Coordinate grove = new Coordinate(3344, 3551, 0);
    static Area.Circular grovearea = new Area.Circular(grove, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(54562);
        player = Client.getLocalPlayer().getServerCoordinate();



        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Ancient Awakening");

            if (startarea.contains(player)) {
                talktoOverseer();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 4:
                SceneObject crypt = SceneObjectQuery.newQuery().name("Wilderness Crypt Entrance").option("Enter").results().first();
                    SceneObject cryptexit = SceneObjectQuery.newQuery().name("Wilderness Crypt Exit").option("Exit").results().nearest();
                    SceneObject ancientdoor = SceneObjectQuery.newQuery().name("Ancient door").option("Enter").results().nearest();
                    SceneObject ancientdoorexit = SceneObjectQuery.newQuery().name("Ancient door").option("Exit").results().nearest();
                if(!cryptentrancearea.contains(player) && cryptexit == null && ancientdoorexit == null)
                {
                    DebugScript.moveTo(cryptentrance);
                }
                else
                {
                    
                    if (crypt != null && cryptexit == null) {
                        crypt.interact("Enter");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(cryptexit != null)
                    {
                        ancientdoor.interact("Enter");
                        delay(RandomGenerator.nextInt(600, 800));

                    }
                    else
                    {
                        talktoAster();
                        
                    }
                }
                break;
                case 6:

                //varbit 54563 - Dragonkin artefact collected
                int DragonkinArtefactCollected = VarManager.getVarbitValue(54563);
                SceneObject artifact = SceneObjectQuery.newQuery().name("Dragonkin artefact").option("Collect").results().nearest();
                if(artifact != null)
                {
                    artifact.interact("Collect");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                if(DragonkinArtefactCollected < 20)
                {
                    Npc tombzombie = NpcQuery.newQuery().name("Tomb zombie").results().first();
                    GroundItem item = GroundItemQuery.newQuery().name("Dragonkin artefact").results().nearest();
                    if(item != null && DragonkinArtefactCollected < 20)
                    {
                        item.interact("Take");
                        delay(RandomGenerator.nextInt(600, 1200));
                        if (Interfaces.isOpen(1622)) {
                            Item items = InventoryItemQuery.newQuery(773).ids(55940).results().first();
                            if (items != null) {
                                ScriptConsole.println("Item Slot" + items.getSlot());
                                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                delay(RandomGenerator.nextInt(600, 1200));
                                return;
                            }
                        }
                    }
                    if(tombzombie != null)
                    {
                        tombzombie.interact("Attack");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 8:
                talktoAster();
                break;
                case 10:
                SceneObject maptable = SceneObjectQuery.newQuery().name("Map table").results().nearest();
                SceneObject continuequest = SceneObjectQuery.newQuery().name("Ancient Awakening").option("Continue").hidden(false).results().nearest();
                SceneObject leavequest = SceneObjectQuery.newQuery().name("Ancient Awakening").option("Leave").hidden(false).results().nearest();
                if(maptable != null)
                {
                    Lodestone.VARROCK.teleport();
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(maptable == null && !jollyboarinnarea.contains(player) && leavequest == null)
                {
                    DebugScript.moveTo(jollyboarinn);
                }
                else 
                {
                    
                    if(continuequest != null)
                    {
                        continuequest.interact("Continue");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(leavequest != null)
                    {
                        talktoBill();
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 12:
                SceneObject stairs = SceneObjectQuery.newQuery().name("Staircase").option("Climb-up").hidden(false).results().nearest();
                Npc bartender = NpcQuery.newQuery().name("Bartender").results().nearest();
                if(stairs != null && bartender != null)
                {
                    ScriptConsole.println("Climbing stairs");
                    stairs.interact("Climb-up");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else
                {
                    ScriptConsole.println("Talking to Ellamaria");
                    talktoEllamaira();
                }
                break;
                case 16:
                SceneObject continuequest1 = SceneObjectQuery.newQuery().name("Ancient Awakening").option("Continue").hidden(false).results().nearest();
                SceneObject leavequest1 = SceneObjectQuery.newQuery().name("Ancient Awakening").option("Leave").hidden(false).results().nearest();
                Npc asterguild = NpcQuery.newQuery().name("Aster").results().nearest();
                if(!bridgechampguildarea.contains(player) && leavequest1 != null && asterguild == null)
                {
                    Lodestone.VARROCK.teleport();
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(!bridgechampguildarea.contains(player) && leavequest1 == null)
                {
                    DebugScript.moveTo(bridgechampguild);
                }
                else
                {
                    if(continuequest1 != null && asterguild == null)
                    {
                        continuequest1.interact("Continue");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(leavequest1 != null && asterguild != null)
                    {
                        talktoAster();
                    }
                }
                break;
                case 18:
                SceneObject woodenheadstone = SceneObjectQuery.newQuery().name("Wooden headstone").hidden(false).results().nearest();
                SceneObject continuequest2 = SceneObjectQuery.newQuery().name("Ancient Awakening").option("Continue").hidden(false).results().nearest();
                SceneObject leavequest2 = SceneObjectQuery.newQuery().name("Ancient Awakening").option("Leave").hidden(false).results().nearest();
                if(woodenheadstone != null)
                {
                    Lodestone.FORT_FORINTHRY.teleport();
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(!forttownhallarea.contains(player) && woodenheadstone == null && leavequest2 == null)
                {
                    DebugScript.moveTo(forttownhall);
                }
                else
                {
                    if(continuequest2 != null)
                    {
                        continuequest2.interact("Continue");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(leavequest2 != null)
                    {
                        talktoAster();
                    }
                }
                break;
                case 20:
                SceneObject leavequest3 = SceneObjectQuery.newQuery().name("Ancient Awakening").option("Leave").hidden(false).results().nearest();
                SceneObject rowboat = SceneObjectQuery.newQuery().name("Rowboat").option("Interact").results().nearest();
                SceneObject rowboat2 = SceneObjectQuery.newQuery().name("Rowboat").option("Leave").results().nearest();
                if(leavequest3 != null)
                {
                    leavequest3.interact("Leave");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(!grovearea.contains(player) && leavequest3 == null )
                {
                    DebugScript.moveTo(grove);
                }
                else
                {
                    
                    if(rowboat != null)
                    {
                        rowboat.interact("Interact");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 24:
                Npc zombiewarrior = NpcQuery.newQuery().name("Zombie warrior").results().nearest();
                if(zombiewarrior != null)
                {
                    zombiewarrior.interact("Attack");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 28:
                SceneObject ruinsentrance = SceneObjectQuery.newQuery().name("Ruins Entrance").option("Interact").results().nearest();
                SceneObject towerstairs = SceneObjectQuery.newQuery().name("Tower entrance").option("Interact").results().nearest();
                if(ruinsentrance != null)
                {
                    ruinsentrance.interact("Interact");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                if(towerstairs != null)
                {
                    towerstairs.interact("Interact");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 30, 32:
                ScriptConsole.println("Wave are done manually");
                break;
                case 38:
                SceneObject doorway = SceneObjectQuery.newQuery().name("Doorway").hidden(false).option("Interact").results().nearest();
                if(doorway != null)
                {
                    doorway.interact("Interact");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 40:
                SceneObject runicprojecter = SceneObjectQuery.newQuery().name("Runic projector").hidden(false).option("Interact").results().nearest();
                if(runicprojecter != null)
                {
                    runicprojecter.interact("Interact");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 42:
                Npc moteofengergy = NpcQuery.newQuery().name("Mote of energy").option("Catch").results().nearest();
                if(VarManager.getVarbitValue(54566) == 0)
                {
                    if(moteofengergy != null )
                    {
                        moteofengergy.interact("Catch");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }else if(VarManager.getVarbitValue(54566) == 1 && moteofengergy == null)
                {
                    SceneObject bookcase = SceneObjectQuery.newQuery().name("Bookcase").hidden(false).option("Search").results().nearest();
                    if(bookcase != null)
                    {
                        bookcase.interact("Search");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }else if(VarManager.getVarbitValue(54566) == 1 && moteofengergy != null)
                {
                    if(moteofengergy != null )
                    {
                        moteofengergy.interact("Catch");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                } else if(VarManager.getVarbitValue(54566) == 2 && moteofengergy == null)
                {
                    SceneObject ruinedpillar = SceneObjectQuery.newQuery().name("Ruined pillar").hidden(false).option("Search").results().nearest();
                    if(ruinedpillar != null)
                    {
                        ruinedpillar.interact("Search");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }else if(VarManager.getVarbitValue(54566) == 2 && moteofengergy != null)
                {
                    moteofengergy.interact("Catch");
                    delay(RandomGenerator.nextInt(600, 800));
                }else if(VarManager.getVarbitValue(54566) == 3 && moteofengergy == null)
                {
                    SceneObject runicprojecterfinal = SceneObjectQuery.newQuery().name("Runic projector").hidden(false).option("Interact").results().nearest();
                    if(runicprojecterfinal != null)
                    {
                        runicprojecterfinal.interact("Interact");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }

                break;
                case 44:
                Npc archivist = NpcQuery.newQuery().name("Archivist").results().nearest();
                if(archivist != null)
                {
                    archivist.interact("Listen to");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 46:
                SceneObject mysteriousdevice = SceneObjectQuery.newQuery().name("Mysterious device").hidden(false).option("Inspect").results().nearest();
                if(mysteriousdevice != null)
                {
                    mysteriousdevice.interact("Inspect");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 48:
                SceneObject maptable1 = SceneObjectQuery.newQuery().name("Map table").results().nearest();
                SceneObject continuequest3 = SceneObjectQuery.newQuery().name("Ancient Awakening").option("Continue").hidden(false).results().nearest();
                SceneObject leavequest4 = SceneObjectQuery.newQuery().name("Ancient Awakening").option("Leave").hidden(false).results().nearest();
                if(maptable1 != null)
                {
                    Lodestone.FORT_FORINTHRY.teleport();
                    delay(RandomGenerator.nextInt(600, 800));
                }else if(!forttownhallarea.contains(player) && maptable1 == null && leavequest4 == null)
                {
                    DebugScript.moveTo(forttownhall);
                }
                else
                {
                    
                    if(continuequest3 != null && leavequest4 == null)
                    {
                        continuequest3.interact("Continue");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(leavequest4 != null)
                    {
                        talktoAster();
                    }
                }
                break;
            }
        }
    }


    public static void talktoOverseer()
    {
        Npc overseer = NpcQuery.newQuery().name("Overseer Siv").results().first();
        if (overseer != null) {
            overseer.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoAster()
    {
        Npc aster = NpcQuery.newQuery().name("Aster").results().first();
        if (aster != null) {
            aster.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoEllamaira()
    {
        Npc ellamaria = NpcQuery.newQuery().name("Ellamaria").results().nearest();
        if (ellamaria != null) {
            ellamaria.interact("Talk to");
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



}
