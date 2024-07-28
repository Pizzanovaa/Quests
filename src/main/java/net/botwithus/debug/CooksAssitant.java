package net.botwithus.debug;

import com.google.common.flogger.FluentLogger;
import net.botwithus.api.game.hud.Dialog;
import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECTABLE_COMPONENT;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_OBJECT;
import static net.botwithus.rs3.script.Execution.delay;

public class CooksAssitant {

    final static Coordinate startCord = new Coordinate(3207, 3215, 0);
    final static Area.Circular startArea = new Area.Circular(startCord, 5);
    final static Coordinate cowCord = new Coordinate(3262, 3276, 0);
    final static Area.Circular cowArea = new Area.Circular(cowCord, 5);
    final static Coordinate chickenCord = new Coordinate(3228, 3299, 0);
    final static Area.Circular chickenArea = new Area.Circular(chickenCord, 5);
    final static Coordinate wheatCord = new Coordinate(3161, 3295, 0);
    final static Area.Circular wheatArea = new Area.Circular(wheatCord, 5);
    final static Coordinate hopperCord = new Coordinate(3165, 3307, 2);
    final static Area.Circular hopperArea = new Area.Circular(hopperCord, 5);
    final static Coordinate hopperCord2 = new Coordinate(3165, 3307, 0);
    final static Area.Circular hopperArea2 = new Area.Circular(hopperCord2, 5);
    static boolean claimedall = false;

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2492);
        Coordinate player = Client.getLocalPlayer().getCoordinate();
        if (Client.getLocalPlayer().isMoving()) {
            return;
        }


        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Cooks Assitant!");

            if (Dialog.isOpen()) {
                return;
            }

            if (!startArea.contains(player) && !Backpack.contains("Empty pot")) {
                DebugScript.moveTo(startCord);
                return;
            }

            if (startArea.contains(player) && !Backpack.contains("Empty pot")) {
                GroundItem bucket = GroundItemQuery.newQuery().name("Empty pot").results().nearest();
                if (bucket != null) {
                    bucket.interact("Take");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                return;
            }

            if (!startArea.contains(player)) {
                DebugScript.moveTo(startCord);
                return;
            }


            Npc cook = NpcQuery.newQuery().name("Cook").results().nearest();
            if (cook != null) {
                cook.interact("Talk-to");
                delay(RandomGenerator.nextInt(600, 800));
            }
            return;

        } else if (QuestVarp == 1) {
            if (Dialog.isOpen()) {
                return;
            }

            if (!claimedall) {
                if (!Backpack.contains("Top-quality milk")) {
                    if (!cowArea.contains(player) && Backpack.contains("Empty pot")) {
                        DebugScript.moveTo(cowCord);
                        return;
                    }

                    if (cowArea.contains(player) && !Backpack.contains("Bucket")) {
                        GroundItem bucket = GroundItemQuery.newQuery().name("Bucket").results().nearest();
                        if (bucket != null) {
                            bucket.interact("Take");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        return;
                    }

                    if (cowArea.contains(player) && Backpack.contains("Bucket")) {
                        SceneObject dairycow = SceneObjectQuery.newQuery().name("Prized dairy cow").results().nearest();
                        if (dairycow != null) {
                            dairycow.interact("Milk");
                            delay(RandomGenerator.nextInt(600, 800));
                        } else {
                            println("Cow null");
                        }
                        return;
                    }

                    return;
                }

                if (!Backpack.contains("Super large egg")) {
                    if (!chickenArea.contains(player)) {
                        DebugScript.moveTo(chickenCord);
                        return;
                    }

                    if (chickenArea.contains(player)) {
                        GroundItem bucket = GroundItemQuery.newQuery().name("Super large egg").results().nearest();
                        if (bucket != null) {
                            bucket.interact("Take");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        return;
                    }
                }

                if (!Backpack.contains("Super fine grain")) {
                    if (VarManager.getVarpValue(8180) < 3) {

                        if (!Backpack.contains("Wheat")) {
                            if (!wheatArea.contains(player)) {
                                DebugScript.moveTo(wheatCord);
                            } else {
                                SceneObject wheat = SceneObjectQuery.newQuery().name("Wheat").results().nearest();
                                if (wheat != null) {
                                    wheat.interact("Pick");
                                    delay(RandomGenerator.nextInt(700, 800));
                                }
                            }
                            return;
                        }

                        if(!hopperArea2.contains(player)){
                            DebugScript.moveTo(hopperCord2);
                            return;
                        }else {
                            Npc millie = NpcQuery.newQuery().name("Millie Miller").results().nearest();
                            if (millie != null) {
                                millie.interact("Talk-to");
                                delay(RandomGenerator.nextInt(700, 800));
                                return;
                            }
                        }
                    } else {
                        if (Backpack.contains("Wheat") && !hopperArea.contains(player)) {
                            DebugScript.moveTo(hopperCord);
                            delay(RandomGenerator.nextInt(700, 800));
                            return;
                        }

                        if (VarManager.getVarpValue(3193) != 1) {
                            if (hopperArea.contains(player)) {
                                SceneObject hopper = SceneObjectQuery.newQuery().name("Hopper").results().nearest();
                                if (hopper != null) {
                                    int itemslot = Backpack.getItem("Wheat").getSlot();
                                    MiniMenu.interact(SELECTABLE_COMPONENT.getType(), 0, itemslot, 96534533);
                                    delay(RandomGenerator.nextInt(300, 500));
                                    MiniMenu.interact(SELECT_OBJECT.getType(), 70034, 3166, 3307);
                                    delay(RandomGenerator.nextInt(2000, 3000));
                                }
                                SceneObject lever = SceneObjectQuery.newQuery().name("Hopper controls").results().nearest();
                                if (lever != null) {
                                    lever.interact("Operate");
                                    delay(RandomGenerator.nextInt(1200, 2400));
                                }
                                return;
                            }
                        } else {
                            if (hopperArea2.contains(player)) {
                                SceneObject hopper = SceneObjectQuery.newQuery().name("Flour bin").results().nearest();
                                if (hopper != null) {
                                    hopper.interact("Take-flour");
                                    delay(RandomGenerator.nextInt(1200, 2400));
                                }
                            } else {
                                DebugScript.moveTo(hopperCord2);
                            }
                            return;
                        }
                    }
                }
                claimedall = true;
            }

            if (!startArea.contains(player)) {
                DebugScript.moveTo(startCord);
                return;
            }


            Npc cook = NpcQuery.newQuery().name("Cook").results().nearest();
            if (cook != null) {
                cook.interact("Talk-to");
                delay(RandomGenerator.nextInt(600, 800));
            }
        }
    }
}
