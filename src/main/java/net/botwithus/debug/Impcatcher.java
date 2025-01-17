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

public class Impcatcher {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3109, 3148, 1);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate waterruins = new Coordinate(3163,3182,0);
    static Area.Circular waterruinsarea = new Area.Circular(waterruins, 10);
    static Coordinate fireruins = new Coordinate(3310,3257,0);
    static Area.Circular fireruinsarea = new Area.Circular(fireruins, 10);
    static Coordinate earthruins = new Coordinate(3308,3474,0);
    static Area.Circular earthruinsarea = new Area.Circular(earthruins, 10);
    static Coordinate airruins = new Coordinate(3126, 3409,0);
    static Area.Circular airruinsarea = new Area.Circular(airruins, 20);

    
    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2669);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Imp Catcher");

            if (!startarea.contains(player) && !Backpack.contains("Message from Mizgog")) {

                DebugScript.moveTo(startcord);
            } else if (startarea.contains(player) && !Backpack.contains("Message from Mizgog")) {
                    talktoWizardMizgog();
                
            }
            

        } else {
            switch (QuestVarp) {
                case 1:
                SceneObject phlegmaticbead = SceneObjectQuery.newQuery().name("Phlegmatic bead").results().first();
                SceneObject cholericbead = SceneObjectQuery.newQuery().name("Choleric bead").results().first();
                SceneObject melancholicbead = SceneObjectQuery.newQuery().name("Melancholic bead").results().first();
                SceneObject sanguinebead = SceneObjectQuery.newQuery().name("Sanguine bead").results().first();
                if(Backpack.contains("Message from Mizgog") &&  !Backpack.contains(39840)) {
                    if(Client.getLocalPlayer().getServerCoordinate().getZ() == 1) {
                        SceneObject beam = SceneObjectQuery.newQuery().name("Beam").option("Bottom floor").results().first();
                        if(beam != null) {
                            beam.interact("Bottom floor");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    else if (!waterruinsarea.contains(player)) {
                        DebugScript.moveTo(waterruins);
                    }
                    else if (waterruinsarea.contains(player) && !Backpack.contains("Bowl of hot water") && phlegmaticbead == null) {
                        println("You need to get a bowl of hot water");
                    }
                    else if (Backpack.contains("Bowl of hot water") && waterruinsarea.contains(player)) {
                        talktophegmaticimp();
                    }
                    else if(phlegmaticbead != null) {
                        phlegmaticbead.interact("Take");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(Backpack.contains("Message from Mizgog") &&  Backpack.contains(39840) && !Backpack.contains(39838)) {
                    if(!fireruinsarea.contains(player)) {
                        DebugScript.moveTo(fireruins);
                    }
                    else if(fireruinsarea.contains(player) && cholericbead == null) {
                        talktocholericimp();
                    }
                    else if(cholericbead != null) {
                        cholericbead.interact("Take");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(Backpack.contains("Message from Mizgog") &&  Backpack.contains(39840) && Backpack.contains(39838) && !Backpack.contains(39839)) {
                    if(!earthruinsarea.contains(player)) {
                        DebugScript.moveTo(earthruins);
                    }
                    else if(earthruinsarea.contains(player) && melancholicbead == null) {
                        talktomelancholicimp();
                    }
                    else if(melancholicbead != null) {
                        melancholicbead.interact("Take");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(Backpack.contains("Message from Mizgog") &&  Backpack.contains(39840) && Backpack.contains(39838) && Backpack.contains(39839) && !Backpack.contains(39837)) {
                    if(!airruinsarea.contains(player)) {
                        DebugScript.moveTo(airruins);
                    }
                    else if(airruinsarea.contains(player) && sanguinebead == null) {
                        talktosanguineimp();
                    }
                    else if(sanguinebead != null) {
                        sanguinebead.interact("Take");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(Backpack.contains("Message from Mizgog") &&  Backpack.contains(39840) && Backpack.contains(39838) && Backpack.contains(39839) && Backpack.contains(39837)) {
                    if(!startarea.contains(player)) {
                        DebugScript.moveTo(startcord);
                    }
                    else if(startarea.contains(player)) {
                        talktoWizardMizgog();
                    }
                }
                    
                break; 
                case 2:
                break;
            }
        }
    }


    public static void talktoWizardMizgog()
    {
        Npc wizardMizgog = NpcQuery.newQuery().name("Wizard Mizgog").results().first();
        if (wizardMizgog != null) {
            wizardMizgog.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktophegmaticimp()
    {
        Npc phlegmaticimp = NpcQuery.newQuery().name("Phlegmatic imp").results().first();
        if (phlegmaticimp != null) {
            phlegmaticimp.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktocholericimp()
    {
        Npc cholericimp = NpcQuery.newQuery().name("Choleric imp").results().first();
        if (cholericimp != null) {
            cholericimp.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktomelancholicimp()
    {
        Npc melancholicimp = NpcQuery.newQuery().name("Melancholic imp").results().first();
        if (melancholicimp != null) {
            melancholicimp.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktosanguineimp()
    {
        Npc sanguineimp = NpcQuery.newQuery().name("Sanguine imp").results().first();
        if (sanguineimp != null) {
            sanguineimp.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
