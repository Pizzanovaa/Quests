package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Distance;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.input.GameInput;
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
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.actionbar.ActionBar;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.queries.results.EntityResultSet;

import static net.botwithus.debug.DebugScript.currentQuest;
import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECTABLE_COMPONENT;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_OBJECT;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Bank;
import net.botwithus.api.game.hud.traversal.Lodestone;

public class Icthlarinlittlehelper {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3318, 2846, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate highPriest = new Coordinate(3304,2730,0);
    static Area.Circular highPriestarea = new Area.Circular(highPriest, 10);
    static Coordinate embalmerloc = new Coordinate(3283,2766,0);
    static Area.Circular embalmerarea = new Area.Circular(embalmerloc, 10);


    static int talktoratul = 0;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(10987);
        player = Client.getLocalPlayer().getServerCoordinate();

        // println("Region ID: " + Client.getLocalPlayer().getCoordinate().getRegionId());
        // println("Coordinate : " + Client.getLocalPlayer().getCoordinate());

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Icthlarin's Little Helper");

            if (startarea.contains(player)) {
                talktowanderer();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 3:
                if(Backpack.contains(4681)) {
                    SceneObject catdoor = SceneObjectQuery.newQuery().name("Door").option("Open").results().first();
                    if (catdoor != null) {
                        catdoor.interact("Open");
                    }
                }
                break;
                case 4:
                 println (" Manual avoid pitfall and puzzle will be solved automatically");
                 // varbit change when inside the  11015 to 1 from 0
                 SceneObject doorway = SceneObjectQuery.newQuery().name("Doorway").option("Open").results().first();
                 if(doorway != null && doorway.distanceTo(player) < 5 && !Interfaces.isOpen(147)) {
                    doorway.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                    
                 }
                 if(Interfaces.isOpen(147))
                  {
                    boolean move1 = MiniMenu.interact(ComponentAction.COMPONENT.getType(),1, -1, 9633801);
                    if(move1) {
                        delay(RandomGenerator.nextInt(600, 800));
                        boolean move2 = MiniMenu.interact(ComponentAction.COMPONENT.getType(),1, -1, 9633799);
                        if(move2) {
                            delay(RandomGenerator.nextInt(600, 800));
                            boolean move3 = MiniMenu.interact(ComponentAction.COMPONENT.getType(),1, -1, 9633809);
                            if(move3) {
                                delay(RandomGenerator.nextInt(600, 800));
                                println("Puzzle Solved");
                            }
                        }
                    }
                  }
                break;
                case 5:
                talktoSphinx();
                break;
                case 6:
                if(highPriestarea.contains(player)) {
                    talktoHighPriest();
                } else {
                    DebugScript.moveTo(highPriest);
                }
                break;
                case 7:
                if(Backpack.contains(4681)) {
                    SceneObject catdoor = SceneObjectQuery.newQuery().name("Door").option("Open").results().first();
                    if (catdoor != null) {
                        catdoor.interact("Open");
                    }
                }
                break;
                case 8:
                SceneObject pit = SceneObjectQuery.newQuery().name("Pit").results().first();
                println (" Manual avoid pitfall and jump across");
                // if(pit != null) {
                //     pit.interact("Jump-across");
                // }
                break;
                case 9:
                if(Backpack.contains(4683))
                {
                //     SceneObject doorwaywest = SceneObjectQuery.newQuery().name("Doorway").option("Open").results().first();
                //  if(doorwaywest != null && doorwaywest.distanceTo(player) < 5) {
                //     doorwaywest.interact("Open");
                //     delay(RandomGenerator.nextInt(600, 800));

                    
                    
                 
                }
                break;
                case 12:
                    SceneObject doorwaywest = SceneObjectQuery.newQuery().name("Doorway").option("Open").results().first();
                 if(doorwaywest != null && doorwaywest.distanceTo(player) < 5) {
                    doorwaywest.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 13:
                SceneObject canopicjar = SceneObjectQuery.newQuery().name("Canopic jar").option("Return").results().first();
                if(canopicjar != null) {
                    canopicjar.interact("Return");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 14:
                SceneObject doorwaywestout = SceneObjectQuery.newQuery().name("Doorway").option("Open").results().first();
                SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().first();
                if(doorwaywestout != null && doorwaywestout.distanceTo(player) < 10) {
                    doorwaywestout.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(ladder != null && ladder.distanceTo(player) < 5)
                {
                    
                if(ladder != null ) {
                        ladder.interact("Climb-up");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else 
                {
                    if(!highPriestarea.contains(player)) {
                        DebugScript.moveTo(highPriest);
                    }
                    else {
                        talktoHighPriest();
                    }
                }
                break;
                case 15:
                if(!embalmerarea.contains(player) && Backpack.contains("Pile of salt") && Backpack.contains("Bucket of sap")) {
                    DebugScript.moveTo(embalmerloc);
                }
                else if( Backpack.contains("Pile of salt") || Backpack.contains("Bucket of sap")){
                    talktoembalmer();
                }
                else if(Backpack.contains("Willow logs")) {
                    talktocarpenter();
                }
                else if(!Backpack.contains("Linen") && talktoratul ==0)
                {
                    
                    talktoraetul();
                    talktoratul = 1;
                }
                else if(Backpack.contains("Linen") && !Backpack.contains("Holy symbol"))
                {
                    talktoembalmer();
                }
                else
                {
                    talktocarpenter();
                }
                break;
                case 16:
                if(Backpack.contains(4682)) {
                    SceneObject catdoor = SceneObjectQuery.newQuery().name("Door").option("Open").results().first();
                    if (catdoor != null) {
                        catdoor.interact("Open");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else 
                {
                    println("Do the maze manually");
                }
                break;
                case 17:
                SceneObject doorwayeast = SceneObjectQuery.newQuery().name("Doorway").option("Open").results().last();
                SceneObject sarcophagus = SceneObjectQuery.newQuery().name("Sarcophagus").option("Search").results().last();
                if(doorwayeast != null && doorwayeast.distanceTo(player) > 10 && Backpack.contains(4683)) {
                    println("Open the door");
                    doorwayeast.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(sarcophagus != null && Backpack.contains(4683)) {
                    println("Search the sarcophagus");
                    sarcophagus.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(!Backpack.contains(4683) && doorwayeast != null && doorwayeast.distanceTo(player) < 10) {
                    println("Open the door to exit");
                    doorwayeast.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 18:
                SceneObject doorwayeastexit = SceneObjectQuery.newQuery().name("Doorway").option("Open").results().last();
                if(doorwayeastexit != null && doorwayeastexit.distanceTo(player) < 10) {
                    println("Open the door to exit");
                    doorwayeastexit.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 19:
                SceneObject doorwayeastreenterObject = SceneObjectQuery.newQuery().name("Doorway").option("Open").results().last();
                if(doorwayeastreenterObject != null && doorwayeastreenterObject.distanceTo(player) > 10 && Backpack.contains(4682)) {
                    println("Open the door to reenter");
                    doorwayeastreenterObject.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 23:
                talktoHighPriest();
                break;
                case 25:
                SceneObject doorwayeastexit1 = SceneObjectQuery.newQuery().name("Doorway").option("Open").results().last();
                if(doorwayeastexit1 != null && doorwayeastexit1.distanceTo(player) < 10) {
                    println("Open the door to exit");
                    doorwayeastexit1.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else 
                {
                    talktoHighPriest();
                }
                break;

            }
        }
    }


    public static void talktowanderer()
    {
        Npc wanderer = NpcQuery.newQuery().name("Wanderer").results().first();
        if (wanderer != null) {
            wanderer.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoSphinx() {
        Npc sphinx = NpcQuery.newQuery().name("Sphinx").results().first();
        if (sphinx != null) {
            sphinx.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void talktoHighPriest() {
        Npc highPriest = NpcQuery.newQuery().name("High Priest").results().first();
        if (highPriest != null) {
            highPriest.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoembalmer() {
        Npc embalmer = NpcQuery.newQuery().name("Embalmer").results().first();
        if (embalmer != null) {
            embalmer.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktocarpenter() {
        Npc carpenter = NpcQuery.newQuery().name("Carpenter").results().first();
        if (carpenter != null) {
            carpenter.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoraetul() {
        Npc raetul = NpcQuery.newQuery().name("Raetul").results().first();
        if (raetul != null) {
            raetul.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }


}
