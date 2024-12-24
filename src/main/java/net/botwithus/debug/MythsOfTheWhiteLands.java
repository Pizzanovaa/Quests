package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.traversal.Lodestone;
import net.botwithus.api.game.world.Traverse;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.movement.TeleportData;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.util.RandomGenerator;

import java.util.Objects;

public class MythsOfTheWhiteLands {
    public static boolean checkStep(int varpQuest, int varbit13877, int varbit13878, int varbit13879, int varbit13880, int varbit13881, int varbit13882, int varbit13883, int varbit13884) {
        if (VarManager.getVarbitValue(13876) == varpQuest && VarManager.getVarbitValue(13877) == varbit13877 && VarManager.getVarbitValue(13878) == varbit13878 && VarManager.getVarbitValue(13879) == varbit13879 && VarManager.getVarbitValue(13880) == varbit13880 && VarManager.getVarbitValue(13881) == varbit13881 && VarManager.getVarbitValue(13882) == varbit13882 && VarManager.getVarbitValue(13883) == varbit13883 && VarManager.getVarbitValue(13884) == varbit13884) {
            return true;
        } else return false;
    }

    static String currentStepQuestVarp10 = "";
    static String currentStepQuestVarp40 = "";
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3206, 3243, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate wizardIsidorcord = new Coordinate(3095, 3156, 1);
    static Area.Circular wizardIsidorarea = new Area.Circular(wizardIsidorcord, 6);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(13876);
        player = Client.getLocalPlayer().getServerCoordinate();
        println("QuestVarp: " + QuestVarp);


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            println("Starting quest... Myths of the White Lands");
            if (startarea.contains(player)) {
                talktoExplorerJack();
                //Talk to Postie Pete at the Land of Snow portal. (Chat ✓)
                //New Values
                //The QuestVarp changed to 10
                //The varbit13877 value 0
                //The varbit13878 value 0
                //The varbit13879 value 0
                //The varbit13880 value 0
                //The varbit13881 value 0
                //The varbit13882 value 0
                //The varbit13883 value 0
                //The varbit13884 value 0

            } else {
                DebugScript.moveTo(startcord);
            }
        } else {
            switch (QuestVarp) {
                case 10:
                    if (checkStep(QuestVarp, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        if (currentStepQuestVarp10 == "") {
                            if (!wizardIsidorarea.contains(player)) {
                                DebugScript.moveTo(wizardIsidorcord);
                            } else {
                                talktoWizardIsidor();
                                currentStepQuestVarp10 = "TOTHECAVE";
                            }
                        } else if (currentStepQuestVarp10 == "TOTHECAVE") {
                            player = Client.getLocalPlayer().getServerCoordinate();
                            Traverse.walkTo(new Coordinate(player.getX(), player.getY() - 20, player.getZ()), false);
                            Execution.delay(10000);
                            player = Client.getLocalPlayer().getServerCoordinate();
                            Traverse.walkTo(new Coordinate(player.getX() + 14, player.getY(), player.getZ()), false);
                            Execution.delay(10000);
                            player = Client.getLocalPlayer().getServerCoordinate();
                            Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 12, player.getZ()), false);
                            Execution.delay(10000);
                            player = Client.getLocalPlayer().getServerCoordinate();
                            Traverse.walkTo(new Coordinate(player.getX() + 4, player.getY(), player.getZ()), false);
                            Execution.delay(10000);
                            var caveEntrance = SceneObjectQuery.newQuery().name("Cave entrance").option("Squeeze-through").results().nearest();
                            if (caveEntrance != null) {
                                caveEntrance.interact("Squeeze-through");
                                currentStepQuestVarp10 = "IDLE";
                            }
                        }

                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 1
                        //The varbit13878 value 0
                        //The varbit13879 value 0
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0

                    }
                    if (checkStep(QuestVarp, 1, 0, 0, 0, 0, 0, 0, 0)) {
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 3, player.getY() + 3, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 4, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 4, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY() - 1, player.getZ()), false);
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 2
                        //The varbit13878 value 0
                        //The varbit13879 value 0
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 2, 0, 0, 0, 0, 0, 0, 0)) {
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 9, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 1, player.getY() - 1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 3, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);

                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 3
                        //The varbit13878 value 0
                        //The varbit13879 value 0
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 3, 0, 0, 0, 0, 0, 0, 0)) {
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 8, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 4, player.getY() - 4, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 4, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() - 5, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);

                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 4
                        //The varbit13878 value 0
                        //The varbit13879 value 0
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 0, 0, 0, 0, 0, 0)) {
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 2, player.getY() + 2, player.getZ()), false);
                        Execution.delay(5000);

                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 2, player.getZ()), false);
                        Execution.delay(5000);

                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 5, player.getY(), player.getZ()), false);
                        Execution.delay(5000);

                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 4, player.getZ()), false);
                        Execution.delay(5000);

                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 6, player.getY() - 6, player.getZ()), false);
                        Execution.delay(5000);

                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 2, player.getY() - 2, player.getZ()), false);
                        Execution.delay(5000);

                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);


                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 5
                        //The varbit13878 value 0
                        //The varbit13879 value 0
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 5, 0, 0, 0, 0, 0, 0, 0)) {
//                        East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 3, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                        North-west
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 2, player.getY() + 2, player.getZ()), false);
                        Execution.delay(5000);
//                        East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 11, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                        South-west
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 1, player.getY() - 1, player.getZ()), false);
                        Execution.delay(5000);
//                        East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);


                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 6
                        //The varbit13878 value 0
                        //The varbit13879 value 0
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 6, 0, 0, 0, 0, 0, 0, 0)) {
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);

                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 6
                        //The varbit13878 value 0
                        //The varbit13879 value 0
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    break;
                case 20:
                    if (checkStep(QuestVarp, 6, 0, 0, 0, 0, 0, 0, 0)) {
//                        South-east
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY() - 1, player.getZ()), false);
                        Execution.delay(5000);
//                        East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 4, player.getY(), player.getZ()), false);
                        Execution.delay(5000);

//                        South
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() - 1, player.getZ()), false);
                        Execution.delay(5000);
//                        Push block west
                        //PUSHEVENT HERE
                        var stone = NpcQuery.newQuery().name("Stone block").results().nearest();
                        if (stone != null) {
                            stone.interact("Push");
                        }
                        Execution.delay(5000);
//                        South
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() - 4, player.getZ()), false);
                        Execution.delay(5000);
//                        North-west
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 3, player.getY() + 3, player.getZ()), false);
                        Execution.delay(5000);
//                        Push block north
                        //PUSHEVENT HERE
                        stone = NpcQuery.newQuery().name("Stone block").results().nearest();
                        if (stone != null) {
                            stone.interact("Push");
                        }
                        Execution.delay(5000);
//                        North
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 5, player.getZ()), false);
                        Execution.delay(5000);
//                        West
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 2, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                        North-east
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY() + 1, player.getZ()), false);
                        Execution.delay(5000);
//                        Push block east
                        //PUSHEVENT HERE
                        stone = NpcQuery.newQuery().name("Stone block").results().nearest();
                        if (stone != null) {
                            stone.interact("Push");
                        }
                        Execution.delay(5000);
//                        East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 5, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                        South
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() - 1, player.getZ()), false);
                        Execution.delay(5000);
//                        East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                        Push block north (block is now in place)
                        //PUSHEVENT HERE
                        stone = NpcQuery.newQuery().name("Stone block").results().nearest();
                        if (stone != null) {
                            stone.interact("Push");
                        }
                        Execution.delay(5000);

                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 6
                        //The varbit13878 value 0
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 6, 0, 1, 0, 0, 0, 0, 0)) {
                        // West
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 7, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//South
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() - 2, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() - 4, player.getZ()), false);
                        Execution.delay(5000);

//                                East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 6, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                        North
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 1, player.getZ()), false);
                        Execution.delay(5000);
//                        Push block east
                        //PUSHEVENT HERE
                        var stone = NpcQuery.newQuery().name("Stone block").results().nearest();
                        if (stone != null) {
                            stone.interact("Push");
                        }
                        Execution.delay(5000);
//                                East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 5, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                        South
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() - 1, player.getZ()), false);
                        Execution.delay(5000);
//                                East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                        Push block north
                        stone = NpcQuery.newQuery().name("Stone block").results().nearest();
                        if (stone != null) {
                            stone.interact("Push");
                        }
                        Execution.delay(5000);
//                                North
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 3, player.getZ()), false);
                        Execution.delay(5000);
//                        East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                                North
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 1, player.getZ()), false);
                        Execution.delay(5000);
//                        Push block west
                        //PUSHEVENT HERE
                        stone = NpcQuery.newQuery().name("Stone block").results().nearest();
                        if (stone != null) {
                            stone.interact("Push");
                        }
                        Execution.delay(5000);
//                                South
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() - 1, player.getZ()), false);
                        Execution.delay(5000);
//                        West
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 3, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                        Push block north
                        //PUSHEVENT HERE
                        stone = NpcQuery.newQuery().name("Stone block").results().nearest();
                        if (stone != null) {
                            stone.interact("Push");
                        }
                        Execution.delay(5000);
//                        North-east
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY() + 1, player.getZ()), false);
                        Execution.delay(5000);
//                        North
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() + 8, player.getZ()), false);
                        Execution.delay(5000);
//                        Push block west
                        //PUSHEVENT HERE
                        stone = NpcQuery.newQuery().name("Stone block").results().nearest();
                        if (stone != null) {
                            stone.interact("Push");
                        }
                        Execution.delay(5000);
//                        South-west
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 2, player.getY() - 2, player.getZ()), false);
                        Execution.delay(5000);
//                        West
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 5, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//                        North-east
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY() + 1, player.getZ()), false);
                        Execution.delay(5000);
//                        Push block north (block is now in place)
                        //PUSHEVENT HERE
                        stone = NpcQuery.newQuery().name("Stone block").results().nearest();
                        if (stone != null) {
                            stone.interact("Push");
                        }
                        Execution.delay(5000);

                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 6
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 6, 1, 1, 0, 0, 0, 0, 0)) {
                        // West
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 4, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
//South
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY() - 5, player.getZ()), false);
                        Execution.delay(5000);
                        var archway = SceneObjectQuery.newQuery().name("Archway").option("Exit-room").results().nearest();
                        if (archway != null) {
                            archway.interact("Exit-room");
                        }
                        Execution.delay(5000);

                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 5
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 5, 1, 1, 0, 0, 0, 0, 0)) {
                        //North-East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY() - 1, player.getZ()), false);
                        Execution.delay(5000);
                        //North-West
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 6, player.getY() + 6, player.getZ()), false);
                        Execution.delay(5000);
                        //East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 7, player.getY(), player.getZ()), false);
                        Execution.delay(5000);

                        var archway = SceneObjectQuery.newQuery().name("Crevice").option("Squeeze-through").results().nearest();
                        if (archway != null) {
                            archway.interact("Squeeze-through");
                        }
                        Execution.delay(5000);

                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 6
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 1
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 6, 1, 1, 0, 0, 0, 1, 0)) {
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 2, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 2, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 2, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        var archway = SceneObjectQuery.newQuery().name("Crevice").option("Squeeze-through").results().nearest();
                        if (archway != null) {
                            archway.interact("Squeeze-through");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit13877 value 7
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 1
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 7, 1, 1, 0, 0, 0, 1, 0)) {
//                        player = Client.getLocalPlayer().getServerCoordinate();
//                        Traverse.walkTo(new Coordinate(player.getX() +2, player.getY(), player.getZ()), false);
//                        Execution.delay(5000);

                        var archway = SceneObjectQuery.newQuery().name("Wall").option("Study").results().nearest();
                        if (archway != null) {
                            archway.interact("Study");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 30
                        //The varbit13877 value 7
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 1
                        //The varbit13884 value 0
                    }
                    break;
                case 30:
                    if (checkStep(QuestVarp, 7, 1, 1, 0, 0, 0, 1, 0)) {
                        var archway = SceneObjectQuery.newQuery().name("Crevice").option("Squeeze-through").results().nearest();
                        if (archway != null) {
                            archway.interact("Squeeze-through");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 30
                        //The varbit13877 value 6
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 1
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 6, 1, 1, 0, 0, 0, 1, 0)) {
                        var archway = SceneObjectQuery.newQuery().name("Crevice").option("Squeeze-through").results().nearest();
                        if (archway != null) {
                            archway.interact("Squeeze-through");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit13877 value 7
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 1
                        //The varbit13884 value 0
                    }
                    break;
                case 40:
                    if (checkStep(QuestVarp, 7, 1, 1, 0, 0, 0, 1, 0)) {
                        var archway = SceneObjectQuery.newQuery().name("Pipe").option("Shout-in").results().nearest();
                        if (archway != null) {
                            archway.interact("Shout-in");
                        }
                        Execution.delay(5000);
                        archway = SceneObjectQuery.newQuery().name("Crevice").option("Squeeze-through").results().nearest();
                        if (archway != null) {
                            archway.interact("Squeeze-through");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit13877 value 6
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 1
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 6, 1, 1, 0, 0, 0, 1, 0)) {
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 2, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 2, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 2, player.getY(), player.getZ()), false);
                        Execution.delay(5000);

                        var archway = SceneObjectQuery.newQuery().name("Crevice").option("Squeeze-through").results().nearest();
                        if (archway != null) {
                            archway.interact("Squeeze-through");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit13877 value 5
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 1
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 5, 1, 1, 0, 0, 0, 1, 0)) {
                        //West
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() - 8, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        //South-east
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY() - 1, player.getZ()), false);
                        Execution.delay(5000);
                        //East
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);

                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit13877 value 6
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 6, 1, 1, 0, 0, 0, 0, 0)) {
                       if(currentStepQuestVarp40=="") {
                           //West to the next cave
                           player = Client.getLocalPlayer().getServerCoordinate();
                           Traverse.walkTo(new Coordinate(player.getX() + 1, player.getY(), player.getZ()), false);
                           Execution.delay(5000);
                           currentStepQuestVarp40="SECONDPASS";
                           //New Values
                           //The QuestVarp changed to 40
                           //The varbit13877 value 7
                           //The varbit13878 value 1
                           //The varbit13879 value 1
                           //The varbit13880 value 0
                           //The varbit13881 value 0
                           //The varbit13882 value 0
                           //The varbit13883 value 0
                           //The varbit13884 value 0
                       }else if(currentStepQuestVarp40=="SECONDPASS"){
                           var stones = SceneObjectQuery.newQuery().name("Archway").option("Exit-room").results().first();
                           if (stones != null) {
                               stones.interact("Exit-room");
                           }
                           //New Values
                           //The QuestVarp changed to 40
                           //The varbit13877 value 5
                           //The varbit13878 value 1
                           //The varbit13879 value 1
                           //The varbit13880 value 0
                           //The varbit13881 value 0
                           //The varbit13882 value 0
                           //The varbit13883 value 0
                           //The varbit13884 value 0
                       }


                    }
                    if (checkStep(QuestVarp, 7, 1, 1, 0, 0, 0, 0, 0)) {
                        //West to the next cave
                        player = Client.getLocalPlayer().getServerCoordinate();
                        var stones = SceneObjectQuery.newQuery().name("Stones").option("Pick-up").results().first();
                        if (stones != null) {
                            stones.interact("Pick-up");
                        }
                        Execution.delay(5000);
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX()-1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);

                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit13877 value 6
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 7, 1, 1, 0, 0, 0, 0, 0)) {
                        //West to the next cave
                        player = Client.getLocalPlayer().getServerCoordinate();
                        var stones = SceneObjectQuery.newQuery().name("Stones").option("Pick-up").results().first();
                        if (stones != null) {
                            stones.interact("Pick-up");
                        }
                        Execution.delay(5000);
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX()-1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);

                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit13877 value 5
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 5, 1, 1, 0, 0, 0, 0, 0)) {
                        //Leave Cave
                        println("Leaving cave 5");
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()-1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX()-1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()+1, player.getZ()), false);
                        Execution.delay(5000);
                        var stones = SceneObjectQuery.newQuery().name("Archway").option("Exit-room").results().first();
                        if (stones != null) {
                            stones.interact("Exit-room");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit13877 value 4
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 4, 1, 1, 0, 0, 0, 0, 0)) {
                        //Leave Cave
                        println("Leaving cave 4");
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()+1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX()-1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()+1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX()-1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()-1, player.getZ()), false);
                        Execution.delay(5000);
                        var stones = SceneObjectQuery.newQuery().name("Archway").option("Exit-room").results().first();
                        if (stones != null) {
                            stones.interact("Exit-room");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit13877 value 3
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 3, 1, 1, 0, 0, 0, 0, 0)) {
                        //Leave Cave
                        println("Leaving cave 3");
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()+1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX()-1, player.getY()-1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX()-1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()-1, player.getZ()), false);
                        Execution.delay(5000);
                        var stones = SceneObjectQuery.newQuery().name("Archway").option("Exit-room").results().first();
                        if (stones != null) {
                            stones.interact("Exit-room");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit13877 value 2
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 2, 1, 1, 0, 0, 0, 0, 0)) {
                        //Leave Cave
                        println("Leaving cave 2");
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()-1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()+1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX()-1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()+1, player.getZ()), false);
                        Execution.delay(5000);
                        var stones = SceneObjectQuery.newQuery().name("Archway").option("Exit-room").results().first();
                        if (stones != null) {
                            stones.interact("Exit-room");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit13877 value 1
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    if (checkStep(QuestVarp, 1, 1, 1, 0, 0, 0, 0, 0)) {
                        //Leave Cave
                        println("Leaving cave 1");
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()+1, player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX()-1, player.getY(), player.getZ()), false);
                        Execution.delay(5000);
                        player = Client.getLocalPlayer().getServerCoordinate();
                        Traverse.walkTo(new Coordinate(player.getX(), player.getY()-1, player.getZ()), false);
                        Execution.delay(5000);
                        var stones = SceneObjectQuery.newQuery().name("Archway").option("Exit-room").results().first();
                        if (stones != null) {
                            stones.interact("Exit-room");
                        }
                        Execution.delay(5000);
                        //New Values
                        //The QuestVarp changed to 50
                        //The varbit13877 value 0
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    break;

                case 50:
                    if (checkStep(QuestVarp, 0, 1, 1, 0, 0, 0, 0, 0)) {
                        if (startarea.contains(player)) {
                            talktoExplorerJack();
                        } else {
                            Lodestone.LUMBRIDGE.teleport();
                            Execution.delay(10000);
                            DebugScript.moveTo(startcord);
                        }

                        //New Values
                        //The QuestVarp changed to 50
                        //The varbit13877 value 0
                        //The varbit13878 value 1
                        //The varbit13879 value 1
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    break;
                case 61:
                    println("Quest Completed!");
                    break;
            }
        }
    }

    public static void talktoWizardIsidor() {
        Npc npc = NpcQuery.newQuery().name("Wizard Isidor").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoExplorerJack() {
        Npc npc = NpcQuery.newQuery().name("Explorer Jack").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
