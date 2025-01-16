package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Equipment;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.ObjectAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.hud.interfaces.Component;
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
import net.botwithus.api.game.hud.inventories.EquipmentInventory;
import net.botwithus.api.game.hud.traversal.Lodestone;
import net.botwithus.api.game.hud.Dialog;

public class InSearchoftheMyreque {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3503, 3475, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate CyregPaddlehorn = new Coordinate(3522, 3284, 0);
    static Area.Circular CyregPaddlehornarea = new Area.Circular(CyregPaddlehorn, 10);
    static Coordinate log = new Coordinate(3476,3456,0);
    static Area.Circular logarea = new Area.Circular(log, 10);
    static Coordinate curpilefyodcord = new Coordinate(3507, 3440, 0);
    static Area.Circular curpilefyodarea = new Area.Circular(curpilefyodcord, 10);
    static Coordinate searchcoord = new Coordinate(3480, 9836, 0);
    static Area.Circular searcharea = new Area.Circular(searchcoord, 10);

    static int memecounter = 1;

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2696);
        player = Client.getLocalPlayer().getServerCoordinate();



        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... In Search of the Myreque");

            if (startarea.contains(player)) {
                talktovanstromklause();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 5:
                Item druidpouch = InventoryItemQuery.newQuery(93).name("Druid pouch").results().first();
                int progress = druidpouch.getStackSize();
                //ScriptConsole.println("Progress: " + progress);
                
                if(!logarea.contains(player) && progress < 5) {
                    DebugScript.moveTo(log);
                } 
                else if(progress >= 5)
                    {
                        if(!CyregPaddlehornarea.contains(player)) {
                            DebugScript.moveTo(CyregPaddlehorn);
                        } else {
                            talktoCyregPaddlehorn();
                        }
                    }
                    else {
                        SceneObject fungionlog = SceneObjectQuery.newQuery().name("Fungi on log").results().first();
    
                        if(fungionlog != null) {
                            fungionlog.interact("Pick");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else if(Backpack.getCount("Mort myre fungus") < 6 && fungionlog == null && progress < 6){
                            Backpack.interact("Silver sickle (b)", "Bloom");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else if(progress < 6)
                        {
                            Backpack.interact("Druid pouch", "Fill");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        
                    }    
                break;
                case 20:
                SceneObject swampboat = SceneObjectQuery.newQuery().name("Swamp Boat").results().first();
                if(swampboat != null) {
                    swampboat.interact("Board");
                    delay(RandomGenerator.nextInt(600, 800));

                    if(Dialogs.isDialogOpen()) {
                        Dialogs.dialog1188pick(1);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 25:
                if(!curpilefyodarea.contains(player)) {
                    DebugScript.moveTo(curpilefyodcord);
                } else {
                    talktoCurpileFyod();
                }
                // SceneObject tree = SceneObjectQuery.newQuery().name("Tree").option("Climb").results().first();
                // SceneObject ropebridge = SceneObjectQuery.newQuery().name("Rope bridge").option("Repair").results().first();
                // if(tree != null && ropebridge != null) {
                //     tree.interact("Climb");
                //     delay(RandomGenerator.nextInt(600, 800));
                //     ropebridge.interact("Repair");
                //     delay(RandomGenerator.nextInt(600, 800));
                // }
                
                break;
                case 52:
                if(Interfaces.isOpen(1188))
                {
                    Component interface1188 = ComponentQuery.newQuery(1188).componentIndex(3).subComponentIndex(14).results().first();
                    if(interface1188 != null && interface1188.getText().equals("Who is the youngest member of the Myreque?")) {
                        Dialog.interact("Ivan Strom.");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(interface1188 != null && interface1188.getText().equals("Name the only female member of the Myreque.")) {
                        Dialog.interact("Sani Piliu.");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(interface1188 != null && interface1188.getText().equals("Who is the leader of the Myreque?")) {
                        Dialog.interact("Veliaf Hurtz.");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(interface1188 != null && interface1188.getText().equals("What family is rumoured to rule over Morytania?")) {
                        Dialog.interact("Drakan.");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(interface1188 != null && interface1188.getText().equals("Which member of the Myreque was originally a scholar?")) {
                        Dialog.interact("Polmafi Ferdygris.");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(interface1188 != null && interface1188.getText().equals("What does Myreque mean?")) {
                        Dialog.interact("Hidden in Myre.");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(interface1188 != null && interface1188.getText().equals("What is the boatman's name?")) {
                        Dialog.interact("Cyreg Paddlehorn..");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(interface1188 != null && interface1188.getText().equals("Who was previously a scholar?")) {
                        Dialog.interact("Polmafi Ferdygris.");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 55:
                SceneObject woodendoors = SceneObjectQuery.newQuery().name("Wooden doors").results().first();
                if(woodendoors != null) {
                    woodendoors.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 60:
                 
                Npc VeliafHurtz = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
                // if(VeliafHurtz != null) {
                //     VeliafHurtz.interact("Talk-to");
                //     delay(RandomGenerator.nextInt(600, 800));
                // }
                SceneObject stalagmite = SceneObjectQuery.newQuery().name("Stalagmite").results().first();
                if(stalagmite != null && VeliafHurtz == null) {
                    stalagmite.interact("Squeeze-past");
                    delay(RandomGenerator.nextInt(600, 800));

                 SceneObject caveentrance = SceneObjectQuery.newQuery().name("Cave entrance").results().nearest();
                if(caveentrance != null) {
                    caveentrance.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                }
                else if(VeliafHurtz != null)
                {
                    VeliafHurtz.interact("Talk-to");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 65:
                if(memecounter == 1) {
                    talktoIvanStrom();
                    memecounter++;
                }
                else if(memecounter == 2) {
                    talktoPolmafiFerdygris();
                    memecounter++;
                }
                else if(memecounter == 3) {
                    talktoHaroldEvans();
                    memecounter++;
                }
                else if(memecounter == 4) {
                    talktoRadigadPonfit();
                    memecounter++;
                }
                else if(memecounter == 5) {
                    talktoSaniPiliu();
                    memecounter++;
                }
                else if(memecounter == 6) {
                    talktoVeliafHurtz();
                    memecounter++;
                }
                break;
                case 85:
                if(Interfaces.isOpen(1188)) {
                    Dialogs.dialog1188pick(3);
                    delay(RandomGenerator.nextInt(600, 800));
                    
                }
                talktoVeliafHurtz();

                break;
                case 90:
                if(!searcharea.contains(player)) {
                    DebugScript.moveTo(searchcoord);
                }
                // Npc VeliafHurtz1 = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
                // SceneObject woodendoors1 = SceneObjectQuery.newQuery().name("Wooden doors").results().first();
                // SceneObject caveentrance = SceneObjectQuery.newQuery().name("Cave entrance").results().nearest();
                // if(caveentrance != null && player.distanceTo()) {
                //     caveentrance.interact("Enter");
                //     delay(RandomGenerator.nextInt(600, 800));
                // }
                // else if(woodendoors1 != null) {
                //     woodendoors1.interact("Open");
                //     delay(RandomGenerator.nextInt(600, 800));
                // }
                break;
                case 95:
                SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().first();
                if(ladder != null) {
                    ladder.interact("Climb-up");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 97:
                talktoStranger();
                break;
            }
        }
    }


    public static void talktovanstromklause()
    {
        Npc vanstromklause = NpcQuery.newQuery().name("Vanstrom Klause").results().first();
        if (vanstromklause != null) {
            vanstromklause.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    
    public static void talktoCyregPaddlehorn()
    {
        Npc CyregPaddlehorn = NpcQuery.newQuery().name("Cyreg Paddlehorn").results().first();
        if (CyregPaddlehorn != null) {
            CyregPaddlehorn.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoCurpileFyod()
    {
        Npc CurpileFyod = NpcQuery.newQuery().name("Curpile Fyod").results().first();
        if (CurpileFyod != null) {
            CurpileFyod.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoVeliafHurtz()
    {
        Npc VeliafHurtz = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
        if (VeliafHurtz != null) {
            VeliafHurtz.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoIvanStrom()
    {
        Npc IvanStrom = NpcQuery.newQuery().name("Ivan Strom").results().first();
        if (IvanStrom != null) {
            IvanStrom.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoPolmafiFerdygris()
    {
        Npc PolmafiFerdygris = NpcQuery.newQuery().name("Polmafi Ferdygris").results().first();
        if (PolmafiFerdygris != null) {
            PolmafiFerdygris.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoSaniPiliu()
    {
        Npc SaniPiliu = NpcQuery.newQuery().name("Sani Piliu").results().first();
        if (SaniPiliu != null) {
            SaniPiliu.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoHaroldEvans()
    {
        Npc HaroldEvans = NpcQuery.newQuery().name("Harold Evans").results().first();
        if (HaroldEvans != null) {
            HaroldEvans.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoRadigadPonfit()
    {
        Npc RadigadPonfit = NpcQuery.newQuery().name("Radigad Ponfit").results().first();
        if (RadigadPonfit != null) {
            RadigadPonfit.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoStranger()
    {
        Npc Stranger = NpcQuery.newQuery().name("Stranger").results().first();
        if (Stranger != null) {
            Stranger.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
