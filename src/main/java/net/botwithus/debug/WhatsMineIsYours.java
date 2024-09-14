package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

public class WhatsMineIsYours {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2959, 3439, 0);    // Doric location
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate dwarven_mine = new Coordinate(3024, 9807, 0);    // Doric location
    static Area.Circular dwarven_mine_area = new Area.Circular(dwarven_mine, 10);
    static Coordinate rimmington_mine = new Coordinate(2970, 3236, 0);    // Doric location
    static Area.Circular rimmington_mine_area = new Area.Circular(rimmington_mine, 10);
    static Coordinate sw_varrock_mine = new Coordinate(3184, 3372, 0);    // Doric location
    static Area.Circular sw_varrock_mine_area = new Area.Circular(sw_varrock_mine, 10);
    static Coordinate se_varrock_mine = new Coordinate(3287, 3366, 0);    // Doric location
    static Area.Circular se_varrock_mine_area = new Area.Circular(se_varrock_mine, 10);

    //static int dwminevisted = 0;
    //static int dwminevisted = 0;
    //static int dwminevisted = 0;
    //static int dwminevisted = 0;


    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(9998);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0 && (Backpack.contains(25324) || Backpack.contains(25325))) {
            ScriptConsole.println("Starting quest... What's Mine Is Yours");

            if (startarea.contains(player)) {
                Doric();
                //Quest Giver
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 0:
                    SceneObject copperore = SceneObjectQuery.newQuery().name("Copper rock").results().nearest();
                    if(copperore != null)
                    {
                        copperore.interact("Mine");
                        delay(RandomGenerator.nextInt(1200, 1500));
                    }

                    Npc npc = NpcQuery.newQuery().name("Living rock brawler").results().first();
                    //varp 8180
                    // High quailty ore : 25327

                    //Talk to Doric // Ore bag ID 25324
                break;
            }
        }
    }
    public static void  Doric() {
        Npc npc = NpcQuery.newQuery().name("Doric").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

}
