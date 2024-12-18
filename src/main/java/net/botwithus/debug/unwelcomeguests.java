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

public class unwelcomeguests {

        static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3319, 3541, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate zombies = new Coordinate(3266, 3558, 0);
    static Area.Circular zombiesarea = new Area.Circular(zombies, 10);
    static Coordinate theRaptor = new Coordinate(3292, 3546, 0);
    static Area.Circular theRaptorarea = new Area.Circular(theRaptor, 10);

    static boolean hasClickedBuild = false;
    static boolean Activeplay = false;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(52844);
        player = Client.getLocalPlayer().getServerCoordinate();



        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Unwelcome Guests");

            if (startarea.contains(player)) {
                talktoOverseersiv();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 5:
                talktoTheRaptor();
                break;
                case 10:
                // if(!zombiesarea.contains(player)) {
                //     DebugScript.moveTo(zombies);
                // } else {
                //     Npc zombies = NpcQuery.newQuery().name("Fetid Zombie").results().first();
                //     if (zombies != null) {
                //         zombies.interact("Attack");
                //         delay(RandomGenerator.nextInt(600, 800));
                //     }
                // }
                SceneObject gates = SceneObjectQuery.newQuery().name("Gates").option("Open").results().first();
                Npc zombies = NpcQuery.newQuery().name("Fetid zombie").byParentType(30035).results().nearest();
                Npc TheRaptor = NpcQuery.newQuery().name("The Raptor").byParentType(30037).results().first();
                if (gates != null && zombies == null) {
                    gates.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                // }else if (TheRaptor != null && zombies != null) {
                    
                //         zombies.interact("Attack");
                //         delay(RandomGenerator.nextInt(600, 800));
                    
                }

                else if(VarManager.getVarbitValue(52845) <= 10 && zombies != null) {
                    zombies.interact("Attack");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(VarManager.getVarbitValue(52845) == 10)
                {
                    gates.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                }

                
                
                break;
                case 15:
                talktoTheRaptor();
                break;
                case 20:
                talktoBill();
                break;
                case 25:
                // SceneObject gates1 = SceneObjectQuery.newQuery().name("Gates").option("Open").results().first();
                // SceneObject Buildingsupplies = SceneObjectQuery.newQuery().name("Building supplies").option("Construct defence").results().first();
               
                // if (Buildingsupplies != null) {
                //     Coordinate build = new Coordinate(Buildingsupplies.getCoordinate().getX(), Buildingsupplies.getCoordinate().getY(), 0);
                //     Coordinate build1 = Buildingsupplies.getCoordinate();
                //     Area.Circular buildarea = new Area.Circular(build, 10);
                //     if(!buildarea.contains(player)) {
                //        Movement.walkTo (build1.getX(), build1.getY(), true);
                //        //DebugScript.moveTo(build);
                //     } else {
                //         Buildingsupplies.interact("Construct defence");
                //         Execution.delay(RandomGenerator.nextInt(4000, 5000));
                //         //Execution.delayUntil(5000, () -> Client.getLocalPlayer().getAnimationId() == -1);
                //     }
                // }
                break;
                case 30:
                if(!startarea.contains(player)) {
                    DebugScript.moveTo(startcord);
                } else {
                    talktoOverseersiv();
                }
                break;
                case 35:
                talktoBill();
                break;
                case 40:
                SceneObject contrutiionhostspot = SceneObjectQuery.newQuery().name("Optimal Construction hotspot").hidden(false).results().nearest();

                 if (contrutiionhostspot == null) {
                        if (Backpack.getCount("Stone wall segment") < 6 && Backpack.getCount("Maple frame") < 14) {
                            println("No frames or stonebricks");
                            SceneObject chest = SceneObjectQuery.newQuery().name("Bank chest").results().nearest();
                            if (chest != null) {
                                println("Interacting with chest " + chest.interact("Use"));
                                //chest.interact("Use");
                                delay(RandomGenerator.nextInt(600, 800));
                            }

                            if (Bank.isOpen()) {
                                if(Backpack.getCount("Maplle frame") < 14) {

                                    Bank.withdraw("Maple frame", 6);
                                    delay(RandomGenerator.nextInt(600, 800));
                                    Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                    GameInput.setIntInput(14);
                                    delay(RandomGenerator.nextInt(600, 800));
                                }
                                                
                    
                                if(Backpack.getCount("Stone wall segment") < 6) {
                                Bank.withdraw("Stone wall segment", 6);
                                delay(RandomGenerator.nextInt(600, 800));
                                Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                GameInput.setIntInput(6);
                                delay(RandomGenerator.nextInt(600, 800));
                                }
                                                 
                            }
                        } else if (Backpack.getCount("Stone wall segment") >= 6 && Backpack.getCount("Maple frame") >= 14) {

                            

                            SceneObject blueprints = SceneObjectQuery.newQuery().name("Fort Forinthry blueprints").results().nearest();
                            if (blueprints != null) {
                                blueprints.interact("Check plans");
                                delay(RandomGenerator.nextInt(600, 800));
                                boolean success = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 61, 89849878);
                                delay(RandomGenerator.nextInt(1200, 1800));
                                if (success) {
                                    delay(RandomGenerator.nextInt(600, 800));
                                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                                    delay(RandomGenerator.nextInt(600, 800));
                                } else {
                                    println("Failed to check plans");
                                }

                                // MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                                // delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }  else {
                        if (!Client.getLocalPlayer().isMoving()) {
                            if (!hasClickedBuild && Distance.to(contrutiionhostspot) >= 1) {
                                contrutiionhostspot.interact("Build");
                                println("Found construction spot");
                                hasClickedBuild = true;

                                if (Activeplay) {
                                    Execution.delay(RandomGenerator.nextInt(800, 1200));
                                } else {
                                    Execution.delay(RandomGenerator.nextInt(3000, 10000));
                                }

                            } else if (hasClickedBuild && Distance.to(contrutiionhostspot) > 1) {
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
                case 45:
                talktoTheRaptor();
                break;
                case 50:
                println("Kill 10 Zombies manually");
                break;
                case 60:
                if(!theRaptorarea.contains(player)) {
                    DebugScript.moveTo(theRaptor);
                } else {
                    talktoTheRaptor();
                }
                break;
            }
        }
    }


    public static void talktoOverseersiv()
    {
        Npc Overseersiv = NpcQuery.newQuery().name("Overseer Siv").results().first();
        if (Overseersiv != null) {
            Overseersiv.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoTheRaptor()
    {
        Npc TheRaptor = NpcQuery.newQuery().name("The Raptor").results().first();
        if (TheRaptor != null) {
            TheRaptor.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoBill()
    {
        Npc Bill = NpcQuery.newQuery().name("Bill").results().first();
        if (Bill != null) {
            Bill.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
