package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.util.RandomGenerator;
import static net.botwithus.rs3.script.Execution.delay;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import static net.botwithus.rs3.script.Execution.delay;
import static net.botwithus.rs3.game.movement.Movement.walkTo;

import net.botwithus.api.game.hud.Dialog;
import net.botwithus.api.game.hud.inventories.Equipment;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;    
import net.botwithus.rs3.game.scene.entities.object.SceneObject;

public class JackofSpades {

        static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3293, 3150, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate osman = new Coordinate(3299, 3152, 1);
    static Area.Circular osmanarea = new Area.Circular(osman, 10);
    static Coordinate batal = new Coordinate(3151, 2793, 0);
    static Area.Circular batalarea = new Area.Circular(batal, 10);
    static Coordinate akhomet = new Coordinate(3172, 2730, 0);
    static Area.Circular akhometarea = new Area.Circular(akhomet, 10);
    static Coordinate admiral = new Coordinate(3177, 2650, 0);
    static Area.Circular admiralarea = new Area.Circular(admiral, 10);
    static Coordinate tomb = new Coordinate(2079, 6950, 0);
    static Area.Circular tombarea = new Area.Circular(tomb, 10);
    static Coordinate grandviziermen = new Coordinate(3196, 2771, 0);
    static Area.Circular grandviziermenarea = new Area.Circular(grandviziermen, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(36140);
        player = Client.getLocalPlayer().getServerCoordinate();

        ScriptConsole.println("Quest Varp: " + QuestVarp);

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Jack of Spades");

            if (startarea.contains(player)) {
                //Quest Giver
                talktoPrinceAli();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 5:
                break;
                case 10:
                break;
                case 15:
                break;
                case 20:
                if (!osmanarea.contains(player)) {
                    DebugScript.moveTo(osman);
                } else {
                    talktoOsman();
                }
                break;
                case 25:
                SceneObject steps = SceneObjectQuery.newQuery().name("Steps").results().nearest();
                if (steps != null) {
                    steps.interact("Climb-up");
                    delay(RandomGenerator.nextInt(600, 800));
                }

                talktoGrandVizier();
                break;
                case 30:
                break;
                case 35:
                talktoGrandVizier();
                talktoHassan();
                break;
                case 40:
                break;
                case 45:
                break;
                case 50:
                break;
                case 55:
                talktoGrandVizier();
                break;
                case 60:
                talktoGuard();

                if(VarManager.getVarbitValue(36143) == 0)
                {
                    if(!admiralarea.contains(player))
                    {
                        DebugScript.moveTo(admiral);
                    } else {
                        talktoAdmiral();
                    }
                }
                break;
                case 65:
                if(!akhometarea.contains(player))
                {
                    DebugScript.moveTo(akhomet);
                } else {
                    talktoAkhomet();
                }
                break;
                case 70:
                if(!batalarea.contains(player))
                {
                    DebugScript.moveTo(batal);
                } else {
                    talktoBatal();
                }
                break;
                case 75:
                if(!tombarea.contains(player))
                {
                    DebugScript.moveTo(tomb);
                } else {
                    talktospade();
                }
                break;
                case 80:
                talkToOzan();
                break;
                case 85:
                break;
                case 90:
                if(VarManager.getVarbitValue(36145) == 0)
                {
                    if(!akhometarea.contains(player))
                    {
                        DebugScript.moveTo(akhomet);
                    } else {
                        talktoAkhomet();
                    }
                }else if(VarManager.getVarbitValue(36146) == 0)
                {
                    if(!batalarea.contains(player))
                    {
                        DebugScript.moveTo(batal);
                    } else {
                        talktoBatal();
                    }
                }
                else if(VarManager.getVarbitValue(36144) == 0)
                {
                    if(!admiralarea.contains(player)    )
                    {
                        DebugScript.moveTo(admiral);
                    } else {
                        talktoAdmiral();
                    }
                }
                else if(VarManager.getVarbitValue(36143) == 0)
                {
                    if(!grandviziermenarea.contains(player))
                    {
                        DebugScript.moveTo(grandviziermen);
                    } else {
                        talktoEsan();
                    }
                }
                
                break;
                case 95:
                talktoGrandVizier();
                break;
                case 100:
                break;  
            }
        }
    }

    public static void talktoPrinceAli() {
        Npc Prince = NpcQuery.newQuery().name("Emir Ali Mirza").results().nearest();
        if (Prince != null) {
            Prince.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void talktoOsman() {
        Npc Osman = NpcQuery.newQuery().name("Osman").results().nearestTo(osman);
        if (Osman != null) {
            Osman.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void talktoGrandVizier() {
        Npc GrandVizier = NpcQuery.newQuery().name("Grand Vizier Hassan").results().nearest();
        if (GrandVizier != null) {
            GrandVizier.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoHassan() {
        Npc Hassan = NpcQuery.newQuery().name("Hassan").results().nearest();
        if (Hassan != null) {
            Hassan.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoGuard()
    {
        Npc Guard = NpcQuery.newQuery().name("Menaphite guard").results().nearest();
        if (Guard != null) {
            Guard.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoAdmiral()
    {
        Npc Guard = NpcQuery.newQuery().name("'Admiral' Wadud (Ports)").results().nearest();
        if (Guard != null) {
            Guard.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoAkhomet()
    {
        Npc Akhomet = NpcQuery.newQuery().name("Commander Akhomet (Imperial)").results().nearest();
        if (Akhomet != null) {
            Akhomet.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoBatal()
    {
        Npc Batal = NpcQuery.newQuery().name("Batal (Worker)").results().nearest();
        if (Batal != null) {
            Batal.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    
    public static void talktospade()
    {
        Npc Spade = NpcQuery.newQuery().name("Jack of Spades").results().nearest();
        if (Spade != null) {
            Spade.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoEsan()
    {
        Npc Ehsan = NpcQuery.newQuery().name("Grand Vizier Ehsan (Merchant)").results().nearest();
        if (Ehsan != null) {
            Ehsan.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void talkToOzan()
    {
        Npc Ozan = NpcQuery.newQuery().name("Ozan").results().nearest();
        if (Ozan != null) {
            Ozan.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
