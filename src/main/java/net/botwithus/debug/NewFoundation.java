package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.ObjectAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.util.RandomGenerator;
import static net.botwithus.rs3.script.Execution.delay;
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
                if(!fortForinthryarea.contains(player))
                {
                    DebugScript.moveTo(fortForinthry);
                }
                else
                {
                    interactwithcportal();
                }
                break;
                case 20:
                break;
                case 25:
                break;
                case 30:
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
            Npc troll1 = NpcQuery.newQuery().name("Armoured zombies").results().first();
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



}
