package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECTABLE_COMPONENT;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_COMPONENT_ITEM;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_OBJECT;
import static net.botwithus.rs3.script.Execution.delay;
import static net.botwithus.rs3.script.Execution.delayExt;

import net.botwithus.api.game.hud.Dialog;
import net.botwithus.api.game.world.Traverse;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.js5.types.QuestType;
import net.botwithus.rs3.game.js5.types.configs.ConfigManager;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import java.util.Objects;

public class ErnestTheChicken {
    public static boolean checkStep(int varpQuest, int varbit2168, int varbit2169, int varbit2170, int varbit2171, int varbit2172, int varbit2173, int varbit2174, int varbit2175, int varbit2176, int varbit2177, int varbit2178, int varbit2179, int varbit2180, int varbit2181, int varbit2182, int varbit2183, int varbit2184, int varbit2185, int varbit2186, int varbit2187, int varbit2188, int varbit2189, int varbit2190, int varbit2191, int varbit2192, int varbit2193, int varbit2194, int varbit2195, int varbit2196, int varbit2197, int varbit37057) {
        if (VarManager.getVarpValue(2183) == varpQuest
                && VarManager.getVarbitValue(2168) == varbit2168
                && VarManager.getVarbitValue(2169) == varbit2169
                && VarManager.getVarbitValue(2170) == varbit2170
                && VarManager.getVarbitValue(2171) == varbit2171
                && VarManager.getVarbitValue(2172) == varbit2172
                && VarManager.getVarbitValue(2173) == varbit2173
                && VarManager.getVarbitValue(2174) == varbit2174
                && VarManager.getVarbitValue(2175) == varbit2175
                && VarManager.getVarbitValue(2176) == varbit2176
                && VarManager.getVarbitValue(2177) == varbit2177
                && VarManager.getVarbitValue(2178) == varbit2178
                && VarManager.getVarbitValue(2179) == varbit2179
                && VarManager.getVarbitValue(2180) == varbit2180
                && VarManager.getVarbitValue(2181) == varbit2181
                && VarManager.getVarbitValue(2182) == varbit2182
                && VarManager.getVarbitValue(2183) == varbit2183
                && VarManager.getVarbitValue(2184) == varbit2184
                && VarManager.getVarbitValue(2185) == varbit2185
                && VarManager.getVarbitValue(2186) == varbit2186
                && VarManager.getVarbitValue(2187) == varbit2187
                && VarManager.getVarbitValue(2188) == varbit2188
                && VarManager.getVarbitValue(2189) == varbit2189
                && VarManager.getVarbitValue(2190) == varbit2190
                && VarManager.getVarbitValue(2191) == varbit2191
                && VarManager.getVarbitValue(2192) == varbit2192
                && VarManager.getVarbitValue(2193) == varbit2193
                && VarManager.getVarbitValue(2194) == varbit2194
                && VarManager.getVarbitValue(2195) == varbit2195
                && VarManager.getVarbitValue(2196) == varbit2196
                && VarManager.getVarbitValue(2197) == varbit2197
                && VarManager.getVarbitValue(37057) == varbit37057) {
            return true;
        } else {
            return false;
        }
    }

    enum CurrentStepVarp1 {
        FIRSTSTEP,
    }

    static CurrentStepVarp1 currentStepVarp1 = CurrentStepVarp1.FIRSTSTEP;
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3108, 3331, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate manorEntranceCoord = new Coordinate(3108, 3351, 0);
    static Area.Circular manorEntranceArea = new Area.Circular(manorEntranceCoord, 2);

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2183);
        player = Client.getLocalPlayer().getServerCoordinate();
        println("QuestVarp: " + QuestVarp);


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }
        String status = "Not Started";
        QuestType questType = ConfigManager.getQuestType(DebugScript.currentQuest.getQuestId());
        assert questType != null;
        int[][] varps = questType.progressVarps();
        if (varps != null && varps.length > 0) {
            for (int[] varp : varps) {
                if (varp.length == 3) {
                    int currentVarpValue = VarManager.getVarpValue(varp[0]);
                    status = currentVarpValue < varp[1] ? "Not Started" :
                            currentVarpValue < varp[2] ? "In Progress" : "Complete";
                }
            }
        }

        if (QuestVarp == 0 && status.equals("Not Started")) {
            println("Starting quest... Ernest the Chicken");
            if (startarea.contains(player)) {
                talktoVeronica();
                // New Values
                // The QuestVarp changed to 0
                // The varbit2168 value 0
                // The varbit2169 value 0
                // The varbit2170 value 0
                // The varbit2171 value 0
                // The varbit2172 value 0
                // The varbit2173 value 0
                // The varbit2174 value 0
                // The varbit2175 value 0
                // The varbit2176 value 0
                // The varbit2177 value 0
                // The varbit2178 value 0
                // The varbit2179 value 0
                // The varbit2180 value 0
                // The varbit2181 value 0
                // The varbit2182 value 0
                // The varbit2183 value 0
                // The varbit2184 value 0
                // The varbit2185 value 0
                // The varbit2186 value 0
                // The varbit2187 value 0
                // The varbit2188 value 0
                // The varbit2189 value 0
                // The varbit2190 value 0
                // The varbit2191 value 0
                // The varbit2192 value 0
                // The varbit2193 value 0
                // The varbit2194 value 0
                // The varbit2195 value 0
                // The varbit2196 value 0
                // The varbit2197 value 0
                // The varbit37057 value 0

            } else {
                DebugScript.moveTo(startcord);
            }
        } else {
            switch (QuestVarp) {
                case 1:
                    if (checkStep(QuestVarp, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        if (!manorEntranceArea.contains(player)) {
                            DebugScript.moveTo(manorEntranceCoord);
                        }

                        var entranceDoor = SceneObjectQuery.newQuery().name("Large door").option("Open").hidden(false).results().nearest();
                        if (entranceDoor != null) {
                            entranceDoor.interact("Open");
                            Execution.delay(RandomGenerator.nextInt(1800, 2400));
                        } else {
                            return;
                        }
                        var staircase = SceneObjectQuery.newQuery().name("Staircase").option("Climb-up").hidden(false).results().nearest();
                        if (staircase != null) {
                            staircase.interact("Climb-up");
                            Execution.delay(RandomGenerator.nextInt(4800, 5400));
                        } else {
                            return;
                        }

                        staircase = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").hidden(false).results().nearest();
                        if (staircase != null) {
                            staircase.interact("Climb-up");
                            Execution.delay(RandomGenerator.nextInt(4800, 5400));
                        } else {
                            return;
                        }
                        Npc npc = NpcQuery.newQuery().name("Professor Oddenstein").results().first();
                        if (npc != null) {
                            var professorDoor = SceneObjectQuery.newQuery().name("Door").option("Open").hidden(false).results().nearestTo(npc);
                            if (professorDoor != null) {
                                professorDoor.interact("Open");
                                Execution.delay(RandomGenerator.nextInt(4800, 5400));
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                        talktoProfessorOdden();
                        Execution.delay(RandomGenerator.nextInt(4800, 5400));

                        // New Values
                        // The QuestVarp changed to 2
                        // The varbit2168 value 0
                        // The varbit2169 value 0
                        // The varbit2170 value 0
                        // The varbit2171 value 0
                        // The varbit2172 value 0
                        // The varbit2173 value 0
                        // The varbit2174 value 0
                        // The varbit2175 value 0
                        // The varbit2176 value 0
                        // The varbit2177 value 0
                        // The varbit2178 value 0
                        // The varbit2179 value 0
                        // The varbit2180 value 0
                        // The varbit2181 value 0
                        // The varbit2182 value 0
                        // The varbit2183 value 0
                        // The varbit2184 value 0
                        // The varbit2185 value 0
                        // The varbit2186 value 0
                        // The varbit2187 value 0
                        // The varbit2188 value 0
                        // The varbit2189 value 0
                        // The varbit2190 value 0
                        // The varbit2191 value 0
                        // The varbit2192 value 0
                        // The varbit2193 value 0
                        // The varbit2194 value 0
                        // The varbit2195 value 0
                        // The varbit2196 value 0
                        // The varbit2197 value 0
                        // The varbit37057 value 0

                    }
                    break;

                case 2:
                    if (checkStep(QuestVarp, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        if (!Backpack.contains("Pressure gauge")) {
                            if (!Backpack.contains("Fish food") && !Backpack.contains("Poisoned fish food")) {
                                if (!new Area.Circular(new Coordinate(3108, 3357, 1), 2).contains(player)) {
                                    DebugScript.moveTo(new Coordinate(3108, 3357, 1));
                                    return;
                                } else {
                                    var fishFood = GroundItemQuery.newQuery().name("Fish food").results().first();
                                    if (fishFood != null) {
                                        fishFood.interact("Take");
                                    } else {
                                        ScriptConsole.println("Fish Food - Not Found");
                                        return;
                                    }
                                }
                            } else if (!Backpack.contains("Poison") && !Backpack.contains("Poisoned fish food")) {
                                if (!new Area.Circular(new Coordinate(3100, 3365, 0), 2).contains(player)) {
                                    DebugScript.moveTo(new Coordinate(3100, 3365, 0));
                                    return;
                                } else {
                                    var fishFood = GroundItemQuery.newQuery().name("Poison").results().first();
                                    if (fishFood != null) {
                                        fishFood.interact("Take");
                                        Execution.delay(RandomGenerator.nextInt(4800, 5400));
                                    } else {
                                        ScriptConsole.println("Poison Not - Not Found");
                                        return;
                                    }
                                }
                            } else if (Backpack.contains("Poison") && Backpack.contains("Fish food") && !Backpack.contains("Poisoned fish food")) {
                                int useItem = net.botwithus.api.game.hud.inventories.Backpack.getItem("Poison").getSlot();
                                int onItem = net.botwithus.api.game.hud.inventories.Backpack.getItem("Fish food").getSlot();
                                ScriptConsole.println("Use Item");
                                MiniMenu.interact(SELECTABLE_COMPONENT.getType(), 0, useItem, 96534533);
                                Execution.delay(500);
                                ScriptConsole.println("On Item");
                                MiniMenu.interact(SELECT_COMPONENT_ITEM.getType(), 0, onItem, 96534533);
                                Execution.delay(500);
                            } else if (Backpack.contains("Poisoned fish food")) {
                                if (!new Area.Circular(new Coordinate(3091, 3335, 0), 2).contains(player)) {
                                    DebugScript.moveTo(new Coordinate(3091, 3335, 0));
                                    return;
                                } else {
                                    int useItem = net.botwithus.api.game.hud.inventories.Backpack.getItem("Poisoned fish food").getSlot();
                                    ScriptConsole.println("Use Item");
                                    MiniMenu.interact(SELECTABLE_COMPONENT.getType(), 0, useItem, 96534533);
                                    Execution.delay(500);
                                    ScriptConsole.println("On Item");
                                    MiniMenu.interact(SELECT_OBJECT.getType(), 153, 3087, 3334);
                                    Execution.delay(RandomGenerator.nextInt(4800, 5400));
                                    var fountain = SceneObjectQuery.newQuery().name("Fountain").option("Search").results().nearest();
                                    if (fountain != null) {
                                        fountain.interact("Search");
                                        Execution.delay(RandomGenerator.nextInt(4800, 5400));
                                    }
                                    return;
                                }
                            }
                        } else if (!Backpack.contains("Rubber tube")) {
                            if (!Backpack.contains("Grimy key")) {
                                if (!new Area.Circular(new Coordinate(3086, 3358, 0), 2).contains(player)) {
                                    DebugScript.moveTo(new Coordinate(3086, 3358, 0));
                                    return;
                                } else {
                                    var compostMound = SceneObjectQuery.newQuery().name("Compost heap").option("Search").results().nearest();
                                    if (compostMound != null) {
                                        compostMound.interact("Search");
                                        Execution.delay(RandomGenerator.nextInt(4800, 5400));
                                        return;
                                    }
                                }
                            } else {
                                if (!new Area.Circular(new Coordinate(3111, 3369, 0), 2).contains(player)) {
                                    DebugScript.moveTo(new Coordinate(3111, 3369, 0));
                                    return;
                                } else {
                                    var door = SceneObjectQuery.newQuery().name("Door").results().nearest();
                                    if (door != null) {
                                        door.interact("Open");
                                        Execution.delay(RandomGenerator.nextInt(4800, 5400));
                                    }
                                    var rubberTube = GroundItemQuery.newQuery().name("Rubber tube").results().first();
                                    if (rubberTube != null) {
                                        rubberTube.interact("Take");
                                        Execution.delay(RandomGenerator.nextInt(4800, 5400));
                                    } else {
                                        ScriptConsole.println("Rubber tube - Not Found");
                                        return;
                                    }
                                }
                            }
                        } else if (!Backpack.contains("Oil can")) {
                            if (!new Area.Circular(new Coordinate(3113, 9754, 0), 50).contains(player)) {
                                DebugScript.moveTo(new Coordinate(3097, 3359, 0));
                                Execution.delay(RandomGenerator.nextInt(3600, 4200));
                                var candle = SceneObjectQuery.newQuery().name("Candle sconce").option("Search").results().nearest();
                                if (candle != null) {
                                    candle.interact("Search");
                                    Execution.delay(RandomGenerator.nextInt(4800, 5400));
                                } else {
                                    return;
                                }
                                var ladder = SceneObjectQuery.newQuery().name("Ladder").option("Climb-down").results().nearest();
                                if (ladder != null) {
                                    ladder.interact("Climb-down");
                                    Execution.delay(RandomGenerator.nextInt(4800, 5400));
                                } else {
                                    return;
                                }
                                return;
                            } else {
                                ScriptConsole.println("In puzzle room! SOLVE MANUALLY CAUSE CBA TO DEAL WITH THIS");
//                                var oilCan = GroundItemQuery.newQuery().name("Oil can").results().first();
//                                var leverA = SceneObjectQuery.newQuery().name("Lever A").results().first();
//                                var leverB = SceneObjectQuery.newQuery().name("Lever B").results().first();
//                                var leverC = SceneObjectQuery.newQuery().name("Lever C").results().first();
//                                var leverD = SceneObjectQuery.newQuery().name("Lever D").results().first();
//                                var leverE = SceneObjectQuery.newQuery().name("Lever E").results().first();
//                                var leverF = SceneObjectQuery.newQuery().name("Lever F").results().first();
//                                if (oilCan != null && leverA != null && leverB != null && leverC != null && leverD != null && leverE != null && leverF != null) {
//                                    leverA.interact("Pull");
//                                    Execution.delay(RandomGenerator.nextInt(6000, 6600));
//                                    leverB.interact("Pull");
//                                    Execution.delay(RandomGenerator.nextInt(6000, 6600));
//                                    leverD.interact("Pull");
//                                    Execution.delay(RandomGenerator.nextInt(6000, 6600));
//                                    leverA.interact("Pull");
//                                    Execution.delay(RandomGenerator.nextInt(6000, 6600));
//                                    leverB.interact("Pull");
//                                    Execution.delay(RandomGenerator.nextInt(6000, 6600));
//                                    leverE.interact("Pull");
//                                    Execution.delay(RandomGenerator.nextInt(6000, 6600));
//                                    leverF.interact("Pull");
//                                    Execution.delay(RandomGenerator.nextInt(6000, 6600));
//                                    leverC.interact("Pull");
//                                    Execution.delay(RandomGenerator.nextInt(6000, 6600));
//                                    leverE.interact("Pull");
//                                    Execution.delay(RandomGenerator.nextInt(6000, 6600));
//                                    oilCan.interact("Take");
//                                    Execution.delay(RandomGenerator.nextInt(6000, 6600));
//                                } else {
//                                    ScriptConsole.println("Puzzle Items - Not Found");
//                                    return;
//                                }
                            }
                        }else{
                            if (!manorEntranceArea.contains(player)) {
                                DebugScript.moveTo(manorEntranceCoord);
                            }

                            var entranceDoor = SceneObjectQuery.newQuery().name("Large door").option("Open").hidden(false).results().nearest();
                            if (entranceDoor != null) {
                                entranceDoor.interact("Open");
                                Execution.delay(RandomGenerator.nextInt(1800, 2400));
                            } else {
                                return;
                            }
                            var staircase = SceneObjectQuery.newQuery().name("Staircase").option("Climb-up").hidden(false).results().nearest();
                            if (staircase != null) {
                                staircase.interact("Climb-up");
                                Execution.delay(RandomGenerator.nextInt(4800, 5400));
                            } else {
                                return;
                            }

                            staircase = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").hidden(false).results().nearest();
                            if (staircase != null) {
                                staircase.interact("Climb-up");
                                Execution.delay(RandomGenerator.nextInt(4800, 5400));
                            } else {
                                return;
                            }
                            Npc npc = NpcQuery.newQuery().name("Professor Oddenstein").results().first();
                            if (npc != null) {
                                var professorDoor = SceneObjectQuery.newQuery().name("Door").option("Open").hidden(false).results().nearestTo(npc);
                                if (professorDoor != null) {
                                    professorDoor.interact("Open");
                                    Execution.delay(RandomGenerator.nextInt(4800, 5400));
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                            talktoProfessorOdden();
                            Execution.delay(RandomGenerator.nextInt(14000, 15000));
                        }
                    }

                    // New Values
                    // The QuestVarp changed to 3
                    // The varbit2168 value 0
                    // The varbit2169 value 0
                    // The varbit2170 value 0
                    // The varbit2171 value 0
                    // The varbit2172 value 0
                    // The varbit2173 value 0
                    // The varbit2174 value 0
                    // The varbit2175 value 0
                    // The varbit2176 value 0
                    // The varbit2177 value 0
                    // The varbit2178 value 0
                    // The varbit2179 value 0
                    // The varbit2180 value 0
                    // The varbit2181 value 0
                    // The varbit2182 value 0
                    // The varbit2183 value 0
                    // The varbit2184 value 0
                    // The varbit2185 value 0
                    // The varbit2186 value 0
                    // The varbit2187 value 0
                    // The varbit2188 value 0
                    // The varbit2189 value 0
                    // The varbit2190 value 0
                    // The varbit2191 value 0
                    // The varbit2192 value 0
                    // The varbit2193 value 0
                    // The varbit2194 value 0
                    // The varbit2195 value 0
                    // The varbit2196 value 0
                    // The varbit2197 value 0
                    // The varbit37057 value 0

                    break;

                case 3:
                    println("Quest Completed!");
                    break;
            }
        }
    }


    public static void talktoVeronica() {
        Npc npc = NpcQuery.newQuery().name("Veronica").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoProfessorOdden() {
        Npc npc = NpcQuery.newQuery().name("Professor Oddenstein").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
