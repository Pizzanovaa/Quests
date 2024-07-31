package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.quest.Quest;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

public class TheKnightSword {

    //Requiired item 2 Iron Bar, 2 Burite Ore
    //

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2977, 3342, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 15);
    static Coordinate reldo = new Coordinate(3210, 3492, 0);
    static Area.Circular reldosarea = new Area.Circular(reldo, 10);
    static Coordinate thurgo = new Coordinate(2996, 3145, 0);
    static Area.Circular thurgosarea = new Area.Circular(thurgo, 10);
    static Coordinate cupboard = new Coordinate(2983, 3335, 2);
    static Area.Circular cupboardarea = new Area.Circular(cupboard, 10);
    static Coordinate Bluriteore = new Coordinate(3053, 9568, 0);
    static Area.Circular Bluriteorearea = new Area.Circular(Bluriteore, 10);


    private static int reladoconvo =0;

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2547);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... What Lie Below");

            if (startarea.contains(player)) {
                println("Inside quest start if statement");
                talktosquire();

            } else {
                println("Inside Debug start if statement");
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp)
            {

                case 2:
                    if (!reldosarea.contains(player) && !Quest.byId(288).get().isComplete()) {
                        DebugScript.moveTo(reldo);
                    }
                    if(reladoconvo == 0 && reldosarea.contains(player) && !Quest.byId(288).get().isComplete())
                    {
                        reldo();
                    }
                    else {
                        if(!thurgosarea.contains(player))
                        {
                            DebugScript.moveTo(thurgo);

                        }
                        else
                        {
                            thurgo();
                        }
                    }
                    break;
                case 3:
                    if(!thurgosarea.contains(player))
                    {
                        DebugScript.moveTo(thurgo);
                    }
                    else
                    {
                        thurgo();
                    }
                    break;
                case 4:
                    if (startarea.contains(player)) {
                        talktosquire();

                    } else {
                        DebugScript.moveTo(startcord);
                    }
                    break;
                case 5:
                    if(!cupboardarea.contains(player) && !Backpack.contains(666))
                    {
                        DebugScript.moveTo(cupboard);
                    }
                    else if(Backpack.contains(666))
                    {
                        if(!thurgosarea.contains(player))
                        {
                            DebugScript.moveTo(thurgo);
                        }
                        else
                        {
                            thurgo();
                        }
                    }
                    else
                    {
                        SceneObject cuboard = SceneObjectQuery.newQuery().name("Cupboard").hidden(false).results().nearest();
                        if(cuboard !=null)
                        {
                            cuboard.interact("Open");
                            delay(RandomGenerator.nextInt(600, 800));
                            cuboard.interact("Search");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    break;
                case 6:
                    //Component backblurite = ComponentQuery.newQuery(1473).itemName("Blurite ore").results().stream().findAny().orElse(null);
                    InventoryItemQuery ore = InventoryItemQuery.newQuery(93).ids(668);
                    var resultore = ore.results();

                    if(!Bluriteorearea.contains(player) && resultore.size() <2 && !Backpack.contains(667))
                    {
                        DebugScript.moveTo(Bluriteore);
                    }

                    else if(resultore.size() <=3 && !Backpack.contains(667))
                    {
                        SceneObject bluriteore = SceneObjectQuery.newQuery().name("Blurite rock").results().nearest();
                        println("Ore " + resultore.size());
                        if(bluriteore != null)
                        {
                            bluriteore.interact("Mine");
                            delay(RandomGenerator.nextInt(4400, 6600));
                            Movement.walkTo(3052, 9568,false);  // Lazy fix to stop mining
                        }
                    }
                    else if(Backpack.contains(667))
                    {
                        if (startarea.contains(player)) {
                            talktosquire();

                        } else {
                            DebugScript.moveTo(startcord);
                        }
                    }
                    else
                    {
                        if(resultore.size() >=2)
                        {

                            if(!thurgosarea.contains(player))
                            {
                                DebugScript.moveTo(thurgo);
                            }
                            else
                            {
                                thurgo();
                            }
                        }
                    }
                    break;
            }
        }
    }

    public static void  talktosquire()
    {
        Npc npc = NpcQuery.newQuery().name("Squire Asrol").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void reldo() {
        Npc npc = NpcQuery.newQuery().name("Reldo").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));


        }
    }

    public static void thurgo() {
        Npc npc = NpcQuery.newQuery().name("Thurgo").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }


}

