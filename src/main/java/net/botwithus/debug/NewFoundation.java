package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Distance;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.ObjectAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.util.RandomGenerator;
import static net.botwithus.rs3.script.Execution.delay;
import net.botwithus.rs3.game.Item;


import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Bank;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;    
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;



import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;

public class NewFoundation {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3217, 3473, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate instance = new Coordinate(6673, 4305, 0);
    static Area.Circular instancearea = new Area.Circular(instance, 10);
    static Coordinate fortForinthry = new Coordinate(3303, 3526, 0);
    static Area.Circular fortForinthryarea = new Area.Circular(fortForinthry, 10);

    static Coordinate aster = new Coordinate(3216, 3405, 0);
    static Area.Circular asterarea = new Area.Circular(aster, 10);
    static Coordinate overseer = new Coordinate(3086, 3421, 0);
    static Area.Circular overseerarea = new Area.Circular(overseer, 10);
    static Coordinate fatherflint = new Coordinate(3085, 3250, 0);
    static Area.Circular fatherflintarea = new Area.Circular(fatherflint, 10);

    static Coordinate nearbill = new Coordinate(3290, 3555, 0);
    static Area.Circular nearbillarea = new Area.Circular(nearbill, 10);

    static boolean hasClickedBuild = false;
    static boolean Activeplay = true;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(52592);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... New Foundation");

            SceneObject portal1 = SceneObjectQuery.newQuery().name("New Foundations").hidden(false).results().nearest();
            if (startarea.contains(player)) {
                                      
                SceneObject portal = SceneObjectQuery.newQuery().name("New Foundations").hidden(false).results().nearest();
                if (portal != null) {
                    portal.interact("Start Quest");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                
               
                //Quest Giver
            }
            else if(portal1 == null)
            {
                
                SceneObject instance = SceneObjectQuery.newQuery().name("Instance").hidden(false).results().first();
                if (instance != null) {
                    
                    SceneObject door = SceneObjectQuery.newQuery().name("Door").hidden(false).results().nearest();
                    if (door != null) {
                        
                        println(" Interacting with door " + door.interact("Open"));
                        delay(RandomGenerator.nextInt(1200, 1800));
                        talkToRoald();
                    }
                }
            }
            else 
            {
                DebugScript.moveTo(startcord);
                }

        } else {
            switch (QuestVarp) {
                case 5:
                break;
                case 10:
                SceneObject instance = SceneObjectQuery.newQuery().name("Instance").hidden(false).results().first();
                if (instance != null) {
                    instance.interact("Leave");
                    delay(RandomGenerator.nextInt(1200, 1800));
                }



                break;
                case 15:
                SceneObject portal1 = SceneObjectQuery.newQuery().name("New Foundations").hidden(true).results().nearest();
                if(!fortForinthryarea.contains(player) && portal1 == null)
                {
                    DebugScript.moveTo(fortForinthry);
                }
                else
                {
                    SceneObject portal = SceneObjectQuery.newQuery().name("New Foundations").hidden(false).results().nearest();
                    if (portal != null) {
                        portal.interact("Continue");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else
                    {
                        killzombies();
                    }
                }
                break;
                case 20:
                talkToBill();
                break;
                case 25:
                InventoryItemQuery frames = InventoryItemQuery.newQuery(93).name("Wooden frame");
                InventoryItemQuery stonebricks = InventoryItemQuery.newQuery(93).name("Stone wall segment");
                var resultframes = frames.results();
                var resultstonebricks = stonebricks.results();
                SceneObject contrutiionhostspot = SceneObjectQuery.newQuery().name("Construction hotspot").hidden(false).results().nearest();
                

                if(contrutiionhostspot == null)
                {
                    if(resultframes.size() < 8 || resultstonebricks.size() < 6)
                    {
                    println("No frames or stonebricks");
                    SceneObject chest = SceneObjectQuery.newQuery().name("Bank chest").results().nearest();
                    if(chest != null)
                    {
                        println("Interacting with chest " + chest.interact("Use"));
                        //chest.interact("Use");
                        delay(RandomGenerator.nextInt(600, 800));
                    }

                    if(Bank.isOpen())
                    {
                        if(resultframes.size() < 8)
                        {
                        Bank.withdraw("Wooden frame", 4);
                        delay(RandomGenerator.nextInt(600, 800));
                        }
                        if(resultstonebricks.size() < 6)
                        {
                        Bank.withdraw("Stone wall segment", 4);
                        delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    }
                    else if(resultframes.size() >= 8 && resultstonebricks.size() >= 6)
                    {
                    SceneObject blueprints = SceneObjectQuery.newQuery().name("Fort Forinthry blueprints").results().nearest();
                    if(blueprints != null)
                    {
                        blueprints.interact("Check plans");
                        delay(RandomGenerator.nextInt(600, 800));

                        MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    }
                }
                else
                {
                    if(!Client.getLocalPlayer().isMoving())
                    {
                        if (!hasClickedBuild && Distance.to(contrutiionhostspot) >= 1) { 
                            contrutiionhostspot.interact("Build");
                            println("Found construction spot"); 
                            hasClickedBuild = true;

                        if (Activeplay) {
                            Execution.delay(RandomGenerator.nextInt(800, 1200));
                            } else {
                            Execution.delay(RandomGenerator.nextInt(3000, 10000));
                        }
                    
                        } else if (hasClickedBuild && Distance.to(contrutiionhostspot) > 1) 
                        {
                            if (Activeplay) {
                                Execution.delay(RandomGenerator.nextInt(800, 1200));
                            } else {
                            Execution.delay(RandomGenerator.nextInt(3000, 10000));
                            }
                            hasClickedBuild = false;
                        }
                }
                }
                break;
                case 30:
                break;
                case 35:
                if(!asterarea.contains(player))
                {
                    DebugScript.moveTo(aster);
                }
                else
                {
                    talktoAster();
                }
                break;
                case 40:
                if(!overseerarea.contains(player))
                {
                    DebugScript.moveTo(overseer);
                }
                else
                {
                    talktoOverseer();
                }
                break;
                case 45:
                if(!fatherflintarea.contains(player))
                {
                    DebugScript.moveTo(fatherflint);
                }
                else
                {
                    talktoFatherFlint();
                }
                break;
                case 50:
                if(!nearbillarea.contains(player))
                {
                    DebugScript.moveTo(nearbill);
                }
                else
                {
                    talkToBill();
                }
                break;
                case 55:
                build();

                break;
                case 60:
                talkToBill();
                break;
            }
        }
    }

     public static void talkToRoald() {
        Npc Roald = NpcQuery.newQuery().name("King Roald").results().nearest();
        if (Roald != null) {
            Roald.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talkToBill() {
        Npc Bill = NpcQuery.newQuery().name("Bill").results().nearest();
        if (Bill != null) {
            Bill.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void interactwithportal() {
        SceneObject portal = SceneObjectQuery.newQuery().name("New Foundations").results().nearest();
        if (portal != null) {
            portal.interact("Start Quest");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void interactwithcportal() {
        SceneObject portal = SceneObjectQuery.newQuery().name("New Foundations").results().nearest();
        if (portal != null) {
            portal.interact("Continue");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void killzombies() {
        if (!Client.getLocalPlayer().inCombat()) {
            Npc troll1 = NpcQuery.newQuery().name("Armoured zombie").results().nearestTo(player);
            if (troll1 != null) {
                troll1.interact("Attack");
                delay(RandomGenerator.nextInt(600, 800));
                return;
            }
        }
    }

    public static void talktoAster() {
        Npc Aster = NpcQuery.newQuery().name("Aster").results().nearest();
        if (Aster != null) {
            Aster.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    
    public static void talktoFatherFlint() {
        Npc FatherFlint = NpcQuery.newQuery().name("Father Flint").results().nearest();
        if (FatherFlint != null) {
            FatherFlint.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoOverseer() {
        Npc Overseer = NpcQuery.newQuery().name("Overseer Siv").results().nearest();
        if (Overseer != null) {
            Overseer.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void build()
    {
                InventoryItemQuery planks = InventoryItemQuery.newQuery(93).name("Plank");
                InventoryItemQuery stonebricks = InventoryItemQuery.newQuery(93).name("Stone wall segment");
                var resultframes = planks.results();
                var resultstonebricks = stonebricks.results();
                SceneObject contrutiionhostspot = SceneObjectQuery.newQuery().name("Optimal Construction hotspot").hidden(false).results().nearest();
                

                if(contrutiionhostspot == null)
                {
                    if(resultframes.size() < 10 || resultstonebricks.size() < 6)
                    {
                    println("No Planks or stonebricks");


                        if(Bank.isOpen())
                        {
                            if(resultframes.size() < 10)
                            {
                            Bank.withdraw(960, 4);
                            delay(RandomGenerator.nextInt(1200, 1800));
                            }
                            else if(resultstonebricks.size() < 6)
                            {
                            Bank.withdraw(54460, 4);
                            delay(RandomGenerator.nextInt(1200, 1800));
                            }
                            else
                            {
                                return;
                            }
                        }

                        SceneObject chest = SceneObjectQuery.newQuery().name("Bank chest").results().nearest();
                        if(chest != null)
                        {
                            println("Interacting with chest " + chest.interact("Use"));
                            //chest.interact("Use");
                            delay(RandomGenerator.nextInt(600, 800));
                        }

                    
                    }
                    else if(resultframes.size() >= 10 && resultstonebricks.size() >= 6)
                    {
                    SceneObject blueprints = SceneObjectQuery.newQuery().name("Fort Forinthry blueprints").results().nearest();
                    if(blueprints != null)
                    {
                        blueprints.interact("Check plans");
                        delay(RandomGenerator.nextInt(600, 800));

                        MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    }
                }
                else
                {
                    if(!Client.getLocalPlayer().isMoving())
                    {
                        if (!hasClickedBuild && Distance.to(contrutiionhostspot) >= 1) { 
                            contrutiionhostspot.interact("Build");
                            println("Found construction spot"); 
                            hasClickedBuild = true;

                        if (Activeplay) {
                            Execution.delay(RandomGenerator.nextInt(800, 1200));
                            } else {
                            Execution.delay(RandomGenerator.nextInt(3000, 10000));
                        }
                    
                        } else if (hasClickedBuild && Distance.to(contrutiionhostspot) > 1) 
                        {
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

}
