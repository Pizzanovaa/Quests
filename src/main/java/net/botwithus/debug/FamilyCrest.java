package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.rs3.script.Execution.delay;
import static net.botwithus.rs3.script.ScriptConsole.println;

public class FamilyCrest {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3263, 3405, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2663);
        player = Client.getLocalPlayer().getServerCoordinate();

        int value = VarManager.getVarpValue(2663);
        int value1 = VarManager.getVarpValue(2664);
        int value2 = VarManager.getVarpValue(2665);
        int value3 = VarManager.getVarpValue(2666);
        int value4 = VarManager.getVarpValue(2667);
        int value5 = VarManager.getVarpValue(2668);
        int value6 = VarManager.getVarpValue(2669);
        int value7 = VarManager.getVarpValue(2670);
        int value8 = VarManager.getVarpValue(2671);
        int value9 = VarManager.getVarpValue(2672);
        int value10 = VarManager.getVarpValue(2673);
        int value11 = VarManager.getVarpValue(2674);
        
        

        println("Varp 2663 Quest:" + value);
        println("Varp 2664 :" + value1);
        println("Varp 2665 :" + value2);
        println("Varp 2666 :" + value3);
        println("Varp 2667 :" + value4);
        println("Varp 2668 :" + value5);
        println("Varp 2669 :" + value6);
        println("Varp 2670 :" + value7);
        println("Varp 2671 :" + value8);
        println("Varp 2672 :" + value9);
        println("Varp 2673 :" + value10);
        println("Varp 2674 :" + value11);


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Family Crest");

            if (startarea.contains(player)) {
                talktoDimintheis();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
            }
        }
    }


    public static void talktoDimintheis()
    {
        Npc dimintheis = NpcQuery.newQuery().name("Dimintheis").results().first();
        if (dimintheis != null) {
            dimintheis.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
