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
import x.r;
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

import c.f;
import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Bank;
import net.botwithus.api.game.hud.traversal.Lodestone;

public class Murderontheborder {

        static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3302, 3570, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);


    static boolean hasClickedBuild = false;
    static boolean Activeplay = true;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(52689);
        player = Client.getLocalPlayer().getServerCoordinate();

     


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Murder on the Border");

            if (startarea.contains(player)) {
                talktoAster();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 10:
                SceneObject contrutiionhostspot = SceneObjectQuery.newQuery().name("Optimal Construction hotspot").hidden(false).results().nearest();

                 if (contrutiionhostspot == null) {
                        if (Backpack.getCount("Stone wall segment") < 6 && Backpack.getCount("Willow frame") < 12) {
                            println("No frames or stonebricks");
                            SceneObject chest = SceneObjectQuery.newQuery().name("Bank chest").results().nearest();
                            if (chest != null) {
                                println("Interacting with chest " + chest.interact("Use"));
                                //chest.interact("Use");
                                delay(RandomGenerator.nextInt(600, 800));
                            }

                            if (Bank.isOpen()) {
                                if(Backpack.getCount("Willow frame") < 12) {

                                    Bank.withdraw("Willow frame", 6);
                                    delay(RandomGenerator.nextInt(600, 800));
                                    Execution.delayUntil(3000, () -> Interfaces.isOpen(1469));
                                    GameInput.setIntInput(12);
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
                        } else if (Backpack.getCount("Stone wall segment") >= 6 && Backpack.getCount("Willow frame") >= 12) {

                            talktoBill();

                            SceneObject blueprints = SceneObjectQuery.newQuery().name("Fort Forinthry blueprints").results().nearest();
                            if (blueprints != null) {
                                blueprints.interact("Check plans");
                                delay(RandomGenerator.nextInt(600, 800));

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
                case 15:
                SceneObject contrutiionhostspot1 = SceneObjectQuery.newQuery().name("Optimal Construction hotspot").hidden(false).results().nearest();
                SceneObject blueprints = SceneObjectQuery.newQuery().name("Fort Forinthry blueprints").results().nearest();
                if (blueprints != null && contrutiionhostspot1 == null) {
                    blueprints.interact("Check plans");
                    delay(RandomGenerator.nextInt(600, 800));
                    
                    boolean success = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 49, 89849878);
                    delay(RandomGenerator.nextInt(1200, 1800));
                    if (success) {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                        delay(RandomGenerator.nextInt(600, 800));
                    } else {
                        println("Failed to check plans");
                    }
                    
                }
                else if (contrutiionhostspot1 != null) {
                    if (!Client.getLocalPlayer().isMoving()) {
                        if (!hasClickedBuild && Distance.to(contrutiionhostspot1) >= 1) {
                            contrutiionhostspot1.interact("Build");
                            println("Found construction spot");
                            hasClickedBuild = true;

                            if (Activeplay) {
                                Execution.delay(RandomGenerator.nextInt(800, 1200));
                            } else {
                                Execution.delay(RandomGenerator.nextInt(3000, 10000));
                            }

                        } else if (hasClickedBuild && Distance.to(contrutiionhostspot1) > 1) {
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
                case 20:
                talktoAster();
                break;
                case 25:
                SceneObject contiuneportal = SceneObjectQuery.newQuery().name("Murder on the Border").option("Continue").results().nearest();
                SceneObject leaveport = SceneObjectQuery.newQuery().name("Murder on the Border").option("Leave").results().nearest();
                if (contiuneportal != null) {
                    contiuneportal.interact("Continue");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 30, 35,40,45,50:
                break;
                case 55:
                talktoducessalba();   //varbit 52708 chnage to 1 after talking
                break;
                case 65:
                SceneObject stonestairs = SceneObjectQuery.newQuery().name("Stone stairs").option("Climb").results().nearest();
                    SceneObject regularstairs = SceneObjectQuery.newQuery().name("Regular Stairs").option("Climb").results().nearest();
                    Npc Ellamaria = NpcQuery.newQuery().name("Ellamaria").results().nearest();
                if(VarManager.getVarbitValue(52710) == 0) {
                talktoDukeHoarse();
                }
                else if(VarManager.getVarbitValue(52709) == 0 && VarManager.getVarbitValue(52710) == 1) {
                    // SceneObject stonestairs = SceneObjectQuery.newQuery().name("Stone stairs").option("Climb").results().nearest();
                    // SceneObject regularstairs = SceneObjectQuery.newQuery().name("Regular Stairs").option("Climb").results().nearest();
                    // Npc Ellamaria = NpcQuery.newQuery().name("Ellamaria").results().nearest();
                    if (stonestairs != null && Client.getLocalPlayer().getServerCoordinate().getZ() != 1 ) {
                        stonestairs.interact("Climb");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            Dialogs.dialog1188pick(1);
                            // MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856776);
                            // delay(RandomGenerator.nextInt(600, 800));
                        }
                        
                    }else if(regularstairs != null && VarManager.getVarbitValue(52709) == 0 && Client.getLocalPlayer().getServerCoordinate().getZ() == 1) {
                        if(Ellamaria != null) {
                            Ellamaria.interact("Talk to");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else if (VarManager.getVarbitValue(52709) == 1 && VarManager.getVarbitValue(52710) == 1 && Client.getLocalPlayer().getServerCoordinate().getZ() == 1) {
                    if(regularstairs != null && Client.getLocalPlayer().getServerCoordinate().getZ() == 1) {
                        regularstairs.interact("Climb");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            Dialogs.dialog1188pick(2);
                            // MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856776);
                            // delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else if (VarManager.getVarbitValue(52709) == 1 && VarManager.getVarbitValue(52710) == 1 && Client.getLocalPlayer().getServerCoordinate().getZ() ==0) {
                    Npc Aster = NpcQuery.newQuery().name("Aster").results().nearest();
                    if(Aster != null) {
                        Aster.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 90:
                if(VarManager.getVarbitValue(52692) == 0 && VarManager.getVarbitValue(52690) == 0) {
                SceneObject nutroast = SceneObjectQuery.newQuery().name("Nut roast").option("Investigate").results().nearest();
                if(nutroast != null) {
                    nutroast.interact("Investigate");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                }
                else if(VarManager.getVarbitValue(52693) == 0 && VarManager.getVarbitValue(52690) == 2) {
                    {
                        SceneObject strangesatchel = SceneObjectQuery.newQuery().name("Strange satchel").option("Investigate").results().nearest();
                        if(strangesatchel != null) {
                            strangesatchel.interact("Investigate");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else if(VarManager.getVarbitValue(52695) == 0 && VarManager.getVarbitValue(52690) == 6) {
                    Npc Simon = NpcQuery.newQuery().name("Simon").byParentType(29920).results().nearest();
                    if(Simon != null && !Interfaces.isOpen(1030)) {
                        Simon.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));

                        if(Dialogs.isDialogOpen()) {
                            Dialogs.dialog1188pick(2);
                            // MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856776);
                            // delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    else if(Interfaces.isOpen(1030)) {
                        boolean linktoposion = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 2, 67502099);
                        delay(RandomGenerator.nextInt(600, 800));
                        if(linktoposion) {
                            println("Linked to posion");
                            // MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856776);
                            // delay(RandomGenerator.nextInt(600, 800));
                        }
                    }


                }
                else if (VarManager.getVarbitValue(52694) == 0 && VarManager.getVarbitValue(52690) == 22)
                {
                    SceneObject halfburiedbox = SceneObjectQuery.newQuery().name("Half-buried box").option("Investigate").results().nearest();
                    if(halfburiedbox != null) {
                        halfburiedbox.interact("Investigate");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(VarManager.getVarbitValue(52696) == 0 && VarManager.getVarbitValue(52690) == 30) {
                    SceneObject strangesatchel = SceneObjectQuery.newQuery().name("Strange satchel").option("Investigate").results().nearest();
                    SceneObject supplycupboard = SceneObjectQuery.newQuery().name("Supply cupboard").option("Search").results().nearest();
                    if(strangesatchel != null && Backpack.getCount(54584) < 1 && !Backpack.contains(54585) && !Backpack.contains(54588)) {
                        strangesatchel.interact("Investigate");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            if(Backpack.getCount(54584) < 1) {
                                Dialogs.dialog1188pick(1);
                                delay(RandomGenerator.nextInt(600, 800));
                            }else {
                                Dialogs.dialog1188pick(2);
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }
                    else if(supplycupboard != null && Backpack.getCount(54584) >= 1 && Backpack.getCount("Hollyhock") < 1 && !Backpack.contains(54585) && !Backpack.contains(54588)) {
                        supplycupboard.interact("Search");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            if(Backpack.getCount("Hollyhock") < 1) {
                                Dialogs.dialog1188pick(1);
                                delay(RandomGenerator.nextInt(600, 800));
                            }else {
                                Dialogs.dialog1188pick(4);
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }
                    else if(Backpack.getCount("Hollyhock") >= 1 && Backpack.getCount(54584) >= 1 && !Backpack.contains(54585) && !Backpack.contains(54588)) {
                        Item hollyhock = InventoryItemQuery.newQuery(93).ids(54581).results().first();
                        Item vial = InventoryItemQuery.newQuery(93).ids(54584).results().first();
                        if(hollyhock != null && vial != null) {
                            boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, hollyhock.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(600, 800));
                            if(success) {
                                MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, vial.getSlot(), 96534533);
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }
                    else if(Backpack.contains(54585) && !Backpack.contains(54588))
                    {
                        SceneObject range = SceneObjectQuery.newQuery().name("Range").option("Cook-at").results().nearest();
                        if(range != null) {
                            range.interact("Cook-at");
                            delay(RandomGenerator.nextInt(600, 800));
                            if(Interfaces.isOpen(1370)) {
                                MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }
                    else if(Backpack.contains(54588))
                    {
                        SceneObject dukemeal = SceneObjectQuery.newQuery().name("Duke's meal").results().nearest();
                        Item item = InventoryItemQuery.newQuery(93).ids(54588).results().first();
                        if(dukemeal != null && Backpack.contains(54588)) {
                          boolean success = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 0, item.getSlot(), 96534533);
                          delay(RandomGenerator.nextInt(600, 800));
                          if(success) {
                            dukemeal.interact(SelectableAction.SELECT_OBJECT.getType());
                            delay(RandomGenerator.nextInt(600, 800));
                          }
                        }
                            
                    }
                }else if(VarManager.getVarbitValue(52691) == 0 && VarManager.getVarbitValue(52690) == 62)
                {
                    Npc kingroald = NpcQuery.newQuery().name("King Roald").byParentType(29918).results().nearest();
                    if(kingroald != null) {
                        kingroald.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(VarManager.getVarbitValue(52697) == 0 && VarManager.getVarbitValue(52690) == 63)
                {
                    Npc duchessalba = NpcQuery.newQuery().name("Duchess Alba").byParentType(29939).results().nearest();
                    if(duchessalba != null && !Interfaces.isOpen(1030)) {
                        duchessalba.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            Dialogs.dialog1188pick(2);
                            //MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                            //delay(RandomGenerator.nextInt(600, 800));
                        }
                        
                    }
                    else if(Interfaces.isOpen(1030)) {
                        boolean nutroast = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 1, 67502099);
                        delay(RandomGenerator.nextInt(600, 800));
                        if(nutroast) {
                            println("Linked to nut roast");
                            // MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856776);
                            // delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else if(VarManager.getVarbitValue(52698) == 0 && VarManager.getVarbitValue(52690) == 127)
                {
                    Npc bianca = NpcQuery.newQuery().name("Bianca").byParentType(29929).results().nearest();
                    if(bianca != null && !Interfaces.isOpen(1030)) {
                        bianca.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            Dialogs.dialog1188pick(2);
                        }
                    }
                    else if(Interfaces.isOpen(1030)) {
                        boolean stolen = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 4, 67502099);
                        delay(RandomGenerator.nextInt(600, 800));
                        if(stolen) {
                            println("Linked to stolen jewellery");

                        }
                    }
                }
                break;

                case 95:
                SceneObject stonestairs1 = SceneObjectQuery.newQuery().name("Stone stairs").option("Climb").results().nearest();
                if(stonestairs1 != null && Client.getLocalPlayer().getServerCoordinate().getZ() == 0) {
                    stonestairs1.interact("Climb");
                    delay(RandomGenerator.nextInt(600, 800));
                    if(Dialogs.isDialogOpen()) {
                        MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856781);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(stonestairs1 != null && Client.getLocalPlayer().getServerCoordinate().getZ() == 3) {
                   Npc aster = NpcQuery.newQuery().name("Aster").byParentType(29915).results().first();
                   if(aster != null) {
                    aster.interact("Talk to");
                    delay(RandomGenerator.nextInt(600, 800));
                   }
                }
                break;

                case 105:
                Npc aster = NpcQuery.newQuery().name("Aster").byParentType(29915).results().first();
                   if(aster != null) {
                    aster.interact("Talk to");
                    delay(RandomGenerator.nextInt(600, 800));
                   }
                break;
                case 110:
                Npc assassin = NpcQuery.newQuery().name("Assassin").byParentType(29952).results().first();
                if(assassin != null) {
                    assassin.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 120:
                SceneObject trapdoor = SceneObjectQuery.newQuery().name("Trap Door").option("Climb").results().nearest();
                if(trapdoor != null && Client.getLocalPlayer().getServerCoordinate().getZ() == 3) {
                    trapdoor.interact("Climb");
                    delay(RandomGenerator.nextInt(600, 800));
                    if(Dialogs.isDialogOpen()) {
                        MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856781);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }else if(trapdoor != null && Client.getLocalPlayer().getServerCoordinate().getZ() == 0) {
                    Npc kingroald = NpcQuery.newQuery().name("King Roald").byParentType(29918).results().first();
                    if(kingroald != null) {
                        kingroald.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 125:
                if(VarManager.getVarbitValue(52699) == 1 && VarManager.getVarbitValue(52702) == 0) {
                    SceneObject waxseal = SceneObjectQuery.newQuery().name("Wax seal").option("Investigate").results().nearest();
                    if(waxseal != null) {
                        waxseal.interact("Investigate");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(VarManager.getVarbitValue(52699) == 5 && VarManager.getVarbitValue(52703) == 0)
                {
                    Npc Iris = NpcQuery.newQuery().name("Iris").byParentType(29926).results().first();
                    if(Iris != null && !Interfaces.isOpen(1030)) {
                        Iris.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856781);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }else if(Interfaces.isOpen(1030)) {
                        boolean stolen = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 10, 67502099);
                        delay(RandomGenerator.nextInt(600, 800));
                        if(stolen) {
                            println("Linked to Strange Seal");

                        }
                    }
                }
                else if(VarManager.getVarbitValue(52699) == 13 && VarManager.getVarbitValue(52700) == 0)
                {
                    SceneObject burntparchment = SceneObjectQuery.newQuery().name("Burnt parchment").option("Investigate").results().nearest();
                    if(burntparchment != null) {
                        burntparchment.interact("Investigate");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(VarManager.getVarbitValue(52699) == 15 && VarManager.getVarbitValue(52704) == 0)
                {
                    Npc princess = NpcQuery.newQuery().name("Princess").byParentType(29941).results().first();
                    if(princess != null && !Interfaces.isOpen(1030)) {
                        princess.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856781);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    else if(Interfaces.isOpen(1030)) {
                        boolean stolen = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 11, 67502099);
                        delay(RandomGenerator.nextInt(600, 800));
                        if(stolen) {
                            println("Linked to Amulet of Spanielspeak");

                        }
                    }
                }
                break;

                case 130:
                SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").option("Climb").results().nearest();
                Coordinate laddercoords = ladder.getCoordinate();
                Area.Circular ladderarea = new Area.Circular(laddercoords, 3);
                
                if(ladder !=null && !ladderarea.contains(Client.getLocalPlayer().getServerCoordinate()))
                {
                    Movement.walkTo(ladder.getCoordinate().getX(), ladder.getCoordinate().getY(), true);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else
                {
                    Npc princess = NpcQuery.newQuery().name("Princess").byParentType(29941).results().first();
                    if(princess != null) {
                        princess.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 135:
                SceneObject items = SceneObjectQuery.newQuery().name("Miscellaneous items").option("Investigate").results().nearest();
                if(items != null) {
                    items.interact("Investigate");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 140:
                Npc queenellamaria = NpcQuery.newQuery().name("Ellamaria").byParentType(29916).results().first();
                Coordinate queenellamariacoords = queenellamaria.getCoordinate();
                Area.Circular queenellamariacircle = new Area.Circular(queenellamariacoords, 3);
                if(queenellamaria != null && !queenellamariacircle.contains(Client.getLocalPlayer().getServerCoordinate()))
                {
                    Movement.walkTo(queenellamariacoords.getX(), queenellamariacoords.getY(), true);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else
                {
                    Npc princess = NpcQuery.newQuery().name("Princess").byParentType(29941).results().first();
                    if(princess != null) {
                        princess.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 145:
                 SceneObject altar = SceneObjectQuery.newQuery().name("Altar").hidden(false).results().nearest();
                 Coordinate altarcoords = altar.getCoordinate();
                 Area.Circular altararea = new Area.Circular(altarcoords, 3);
                 if(altar != null && !altararea.contains(Client.getLocalPlayer().getServerCoordinate()))
                 {
                    Movement.walkTo(altarcoords.getX(), altarcoords.getY(), true);
                    delay(RandomGenerator.nextInt(600, 800));
                 }
                 else
                 {
                    Npc princess = NpcQuery.newQuery().name("Princess").byParentType(29941).results().first();
                    if(princess != null) {
                        princess.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                 }
                break;
                case 150:
                SceneObject coffer = SceneObjectQuery.newQuery().name("Unearthed coffer").option("Investigate").results().nearest();
                if(coffer != null) {
                    coffer.interact("Investigate");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 155:
                if(VarManager.getVarbitValue(52699) == 31 && VarManager.getVarbitValue(52705) == 0)
                {
                    Npc duchessalba = NpcQuery.newQuery().name("Duchess Alba").byParentType(29939).results().first();
                    if(duchessalba != null) {
                        duchessalba.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856776);
                            delay(RandomGenerator.nextInt(600, 800));
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856781);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else if((VarManager.getVarbitValue(52699) == 63 && VarManager.getVarbitValue(52707) == 0))
                {
                    Npc queenellamaria1 = NpcQuery.newQuery().name("Ellamaria").byParentType(29916).results().first();
                    if(queenellamaria1 != null) {
                        queenellamaria1.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856776);
                            delay(RandomGenerator.nextInt(600, 800));
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856781);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else if(VarManager.getVarbitValue(52699) == 191 && VarManager.getVarbitValue(52707) == 1 && VarManager.getVarbitValue(52706) == 0)
                {
                    Npc rodney = NpcQuery.newQuery().name("Rodney").byParentType(29934).results().first();
                    if(rodney != null && !Interfaces.isOpen(1030)) {
                        rodney.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Dialogs.isDialogOpen()) {
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77856781);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    else if(Interfaces.isOpen(1030)) {
                        boolean stolen = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 12, 67502099);
                        delay(RandomGenerator.nextInt(600, 800));
                        if(stolen) {
                            println("Linked to Scoreched Will");

                        }
                    }
                }
                break;
                case 165:
                Npc aster1 = NpcQuery.newQuery().name("Aster").byParentType(29915).results().first();
                if(aster1 != null) {
                    aster1.interact("Talk to");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 180:
                talktoKingRoald();
                break;
                case 185:
                talktoAster();
                break;
                case 190:
                talktoRodney();
                break;
            }
        }
    }


    public static void talktoAster()
    {
        Npc Aster = NpcQuery.newQuery().name("Aster").results().first();
        if (Aster != null) {
            Aster.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoEllamaria()
    {
        Npc Ellamaria = NpcQuery.newQuery().name("Ellamaria").results().first();
        if (Ellamaria != null) {
            Ellamaria.interact("Talk to");
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

    public static void talktoKingRoald()
    {
        Npc KingRoald = NpcQuery.newQuery().name("King Roald").results().first();
        if (KingRoald != null) {
            KingRoald.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoSimon()
    {
        Npc Simon = NpcQuery.newQuery().name("Simon").results().first();
        if (Simon != null) {
            Simon.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoIris()
    {
        Npc Iris = NpcQuery.newQuery().name("Iris").results().first();
        if (Iris != null) {
            Iris.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoBianca()
    {
        Npc Bianca = NpcQuery.newQuery().name("Bianca").results().first();
        if (Bianca != null) {
            Bianca.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoRodney()
    {
        Npc Rodney = NpcQuery.newQuery().name("Rodney").results().first();
        if (Rodney != null) {
            Rodney.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoducessalba()
    {
        Npc ducessalba = NpcQuery.newQuery().name("Duchess Alba").results().first();
        if (ducessalba != null) {
            ducessalba.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoDukeHoarse()
    {
        Npc DukeHoarse = NpcQuery.newQuery().name("Duke Hoarse").results().first();
        if (DukeHoarse != null) {
            DukeHoarse.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
