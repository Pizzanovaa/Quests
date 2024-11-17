package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import static net.botwithus.rs3.script.Execution.delay;
import static net.botwithus.rs3.game.movement.Movement.walkTo;
import static net.botwithus.rs3.script.Execution.delayUntil;
import net.botwithus.rs3.game.minimenu.MiniMenu;

import java.lang.reflect.Executable;

import static net.botwithus.rs3.script.Execution.delay;


import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;

public class AnachroniaTut {


    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3387, 3421, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate firstbonecord = new Coordinate(3263, 3405, 0);
    static Area.Circular firstbonearea = new Area.Circular(firstbonecord, 10);
    static Coordinate secondbonecord = new Coordinate(3263, 3405, 0);
    static Area.Circular secondbonearea = new Area.Circular(secondbonecord, 10);
    


    static int varrockinteraction = 0;
    static int arrivedatAnachronia = 0;
    static int firstbone = 0;
    static int secondbone = 0;
    static int talktoGil = 0;
    static int assinworker =0;
    static int buildbase =0;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(44451);
        player = Client.getLocalPlayer().getServerCoordinate();



        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            //ScriptConsole.println("Starting quest... Anachronia Base Tutorial");

            if(!startarea.contains(player) && varrockinteraction == 0){
                DebugScript.moveTo(startcord);
            }
            else if(varrockinteraction == 0 && startarea.contains(player)){
                SceneObject strombreaker = SceneObjectQuery.newQuery().name("The Stormbreaker").results().nearest();
                if(strombreaker != null){
                    strombreaker.interact("Board");
                    varrockinteraction = 1;
                    delay(RandomGenerator.nextInt(400, 600));
                    
                }
            }
            else if(varrockinteraction == 1 && arrivedatAnachronia == 0){
                ScriptConsole.println("Arrived at Anachronia, Delaying to inspect footprint");
                delay(RandomGenerator.nextInt(15000, 25000));
                SceneObject firstboneobj = SceneObjectQuery.newQuery().name("Bones").hidden(false).results().nearest();
                SceneObject footprint = SceneObjectQuery.newQuery().name("Footprint").results().nearest();
                if(footprint != null){
                    footprint.interact("Inspect");
                    arrivedatAnachronia = 1;
                    delay(RandomGenerator.nextInt(400, 600));
                    
                }
                
            }
            else if(arrivedatAnachronia == 1 && firstbone == 0){
                
                SceneObject firstboneobj = SceneObjectQuery.newQuery().name("Bones").hidden(false).results().nearest();
                Coordinate firstbonecord1 = new Coordinate(firstboneobj.getCoordinate().getX() + 4, firstboneobj.getCoordinate().getY() + 15, 0);
                Coordinate firstbonemidpoint = new Coordinate(firstboneobj.getCoordinate().getX() - 10, firstboneobj.getCoordinate().getY() + 50, 0);
                //Area.Circular firstbonearea1 = new Area.Circular(firstbonecord1, 10);
                ScriptConsole.println("Distance to first bone: " + player.distanceTo(firstboneobj.getCoordinate()));
                 
                if(player.distanceTo(firstbonecord1.getCoordinate()) > 60)
                {
                    ScriptConsole.println("Walking to first bone");
                    walkTo(firstboneobj.getCoordinate().getX() - 10, firstboneobj.getCoordinate().getY() + 50, true);
                    //walkTo(firstbonearea1.getCoordinate().getX(), firstbonearea1.getCoordinate().getY(), true);
                    Execution.delayUntil(RandomGenerator.nextInt(5000,7000), () -> !Client.getLocalPlayer().isMoving());
                }
                else if(player.distanceTo(firstboneobj.getCoordinate()) < 60 && player.distanceTo(firstboneobj.getCoordinate()) > 20)
                {
                    ScriptConsole.println("Walking to first bone");
                    walkTo(firstboneobj.getCoordinate().getX() + 4, firstboneobj.getCoordinate().getY() + 15, true);
                    //walkTo(firstbonearea1.getCoordinate().getX(), firstbonearea1.getCoordinate().getY(), true);
                    Execution.delayUntil(RandomGenerator.nextInt(5000,7000), () -> !Client.getLocalPlayer().isMoving());
                }
                else if(firstboneobj != null && player.distanceTo(firstboneobj.getCoordinate()) < 20){
                    ScriptConsole.println("Investigating first bone");               
                    firstboneobj.interact("Investigate");
                    firstbone = 1;
                    delay(RandomGenerator.nextInt(400, 600));
                    
                }
            }
            else if(firstbone == 1 && secondbone == 0){
                delay(RandomGenerator.nextInt(3000, 5000));
                SceneObject secondboneobj = SceneObjectQuery.newQuery().name("Bones").id(113907).hidden(false).results().nearest();
                if(player.distanceTo(secondboneobj.getCoordinate()) > 10)
                {
                    ScriptConsole.println("Walking to second bone");
                    walkTo(secondboneobj.getCoordinate().getX() + 1, secondboneobj.getCoordinate().getY() - 3, true);
                    Execution.delayUntil(RandomGenerator.nextInt(3000,5000), () -> !Client.getLocalPlayer().isMoving());
                }
                if(secondboneobj != null && player.distanceTo(secondboneobj.getCoordinate()) < 10){
                    ScriptConsole.println("Investigating second bone");               
                    secondboneobj.interact("Investigate");
                    secondbone = 1;
                    delay(RandomGenerator.nextInt(400, 600));
                    
                }
                
            }
            else if(secondbone == 1 && talktoGil == 0){
                Npc gil = NpcQuery.newQuery().name("Giles").results().nearest();
                if(gil != null){
                    gil.interact("Talk to");
                    talktoGil = 1;
                    delay(RandomGenerator.nextInt(400, 600));
                    Execution.delayUntil(10000, () -> Interfaces.isOpen(176));
                    
                    
                }
            }
            else if(talktoGil == 1 && assinworker == 0){
                if(Interfaces.isOpen(176)){
                    boolean success = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 11534343);
                    if(success){
                        delay(RandomGenerator.nextInt(400, 600));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 2, 11534372);
                        delay(RandomGenerator.nextInt(400, 600));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 2, 11534372);
                        delay(RandomGenerator.nextInt(400, 600));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 5, 11534372);
                        delay(RandomGenerator.nextInt(400, 600));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 5, 11534372);
                        delay(RandomGenerator.nextInt(400, 600));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 14, 11534372);
                        delay(RandomGenerator.nextInt(400, 600));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 14, 11534372);
                        delay(RandomGenerator.nextInt(1200, 1800));
                        // Add a check to close the interface
                        assinworker = 1;
                    }

                }
            }else if(assinworker == 1 && buildbase == 0){
                Npc gil = NpcQuery.newQuery().name("Giles").results().nearest();
                if(gil != null){
                    gil.interact("Talk to");
                    
                    delay(RandomGenerator.nextInt(400, 600));
                    Execution.delayUntil(10000, () -> Interfaces.isOpen(176));
                    if(Interfaces.isOpen(176)){
                        MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 1, -1, 11534396);
                        delay(RandomGenerator.nextInt(400, 600));
                        buildbase = 1;
                    }
                    
                    
                }


            }
            

        } else {
            switch (QuestVarp) {
            }
        }
    }

}
