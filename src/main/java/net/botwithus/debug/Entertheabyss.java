package net.botwithus.debug;

import static net.botwithus.debug.DebugScript.moveTo;
import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.movement.TraverseEvent;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import static net.botwithus.rs3.script.Execution.delay;
import net.botwithus.rs3.script.Execution;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.rs3.script.Execution.delay;
import static net.botwithus.rs3.script.ScriptConsole.println;

import net.botwithus.api.game.hud.traversal.Lodestone;


public class Entertheabyss {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3106, 3558, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate mageofzamorakcord = new Coordinate(3259, 3388, 0);
    static Area.Circular mageofzamorakarea = new Area.Circular(mageofzamorakcord, 10);
    static Coordinate auburycord = new Coordinate(3253, 3402, 0);
    static Area.Circular auburyarea = new Area.Circular(auburycord, 10);
    static Coordinate sedridorcord = new Coordinate(3101, 3147, 2);
    static Area.Circular sedridorarea = new Area.Circular(sedridorcord, 10);
    static Coordinate crompertycord = new Coordinate(2681, 3324, 0);
    static Area.Circular crompertyarea = new Area.Circular(crompertycord, 10);

    static int tracktalkto = 0;

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(3149);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Enter the Abyss");

            if (startarea.contains(player)) {
                talktomageofzamorak();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 1:
                if(!mageofzamorakarea.contains(player)) {
                    DebugScript.moveTo(mageofzamorakcord);
                }   
                else {
                    talktomageofzamorak();
                }
                break;
                case 2:
                if(auburyarea.contains(player) && tracktalkto == 0) {
                    tracktalkto = 1;
                    println("Talked to Aubury: " + tracktalkto);
                    teleportaubury();
                    
                    
                    
                }else if(tracktalkto == 1 && sedridorarea.contains(player)){
                    tracktalkto = 2;
                    println("Talked to Sedridor: " + tracktalkto);
                    teleportSedridor();
                    
                }else if(tracktalkto == 2 && crompertyarea.contains(player)){
                    tracktalkto = 3;
                    println("Talked to Cromperty: " + tracktalkto);
                    teleportCromperty();
                    
                }
                else if(tracktalkto == 0 && !auburyarea.contains(player)){
                    if(DebugScript.moveTo(auburycord) == false){
                        println("No path found, teleporting to Burthorpe by Default");
                        Lodestone.BURTHORPE.teleport();
                    }else{
                        DebugScript.moveTo(auburycord) ;
                    }
                }
                else if(tracktalkto == 1 && !sedridorarea.contains(player)){
                    DebugScript.moveTo(sedridorcord);
                }
                else if(tracktalkto == 2 && !crompertyarea.contains(player)){
                    DebugScript.moveTo(crompertycord);
                }
                else if(tracktalkto == 3 && !mageofzamorakarea.contains(player)){
                    DebugScript.moveTo(mageofzamorakcord);
                }
                else if(tracktalkto == 3 && mageofzamorakarea.contains(player)){
                    talktomageofzamorak();
                }
                break;
                case 3:
                if(mageofzamorakarea.contains(player)){
                    talktomageofzamorak();
                }
                else {
                    DebugScript.moveTo(mageofzamorakcord);
                }
                break;
                case 4:
                break;
            }
        }
    }

    public static void talktomageofzamorak() {
        Npc mageofzamorak = NpcQuery.newQuery().name("Mage of Zamorak").results().first();
        if (mageofzamorak != null) {
            mageofzamorak.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void teleportaubury()
    {
        Npc aubury = NpcQuery.newQuery().name("Aubury").results().first();
        if (aubury != null) {
            aubury.interact("Teleport");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void teleportSedridor()
    {
        Npc sedridor = NpcQuery.newQuery().name("Archmage Sedridor").results().first();
        if (sedridor != null) {
            sedridor.interact("Teleport");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void teleportCromperty()
    {
        Npc cromperty = NpcQuery.newQuery().name("Wizard Cromperty").results().first();
        if (cromperty != null) {
            cromperty.interact("Teleport");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }


}


