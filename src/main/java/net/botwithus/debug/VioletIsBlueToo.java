package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.world.Traverse;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.util.RandomGenerator;

import java.util.Objects;

public class VioletIsBlueToo {
    public static boolean checkStep(int varpQuest, int varbit40610, int varbit48722, int varbit48723, int varbit48724, int varbit48725, int varbit48726, int varbit48727, int varbit48728, int varbit48729) {
        if (VarManager.getVarbitValue(36215) == varpQuest && VarManager.getVarbitValue(40610) == varbit40610 && VarManager.getVarbitValue(48722) == varbit48722 && VarManager.getVarbitValue(48723) == varbit48723 && VarManager.getVarbitValue(48724) == varbit48724 && VarManager.getVarbitValue(48725) == varbit48725 && VarManager.getVarbitValue(48726) == varbit48726 && VarManager.getVarbitValue(48727) == varbit48727 && VarManager.getVarbitValue(48728) == varbit48728 && VarManager.getVarbitValue(48729) == varbit48729) {
            return true;
        } else return false;
    }

    static String currentStep = "";
    static String currentStep2 = "";
    static String currentStep3 = "";
    static String currentStep4 = "";
    static String currentStep5 = "";
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2854, 3457, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate violethousecord = new Coordinate(12642, 1435, 0);
    static Area.Circular violethousearea = new Area.Circular(violethousecord, 2);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(36215);
        player = Client.getLocalPlayer().getServerCoordinate();
        println("QuestVarp: " + QuestVarp);


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            println("Starting quest... Violet is blue too");
            if (startarea.contains(player)) {
                talktoPostiePete();
                //Talk to Postie Pete at the Land of Snow portal. (Chat ✓)
                //New Values
                //The QuestVarp changed to 5
                //The varbit40610 value 0
                //The varbit40622 value 0
                //The varbit40623 value 0
                //The varbit40624 value 0
                //The varbit40625 value 0
                //The varbit40626 value 0
                //The varbit40627 value 0
                //The varbit40628 value 0
                //The varbit40629 value 0

            } else {
                DebugScript.moveTo(startcord);
            }
        } else {
            switch (QuestVarp) {

                case 5:
                    if (checkStep(QuestVarp, 0, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        var portal = SceneObjectQuery.newQuery().name("Land of Snow portal").option("Enter").results().first();
                        if (portal != null) {
                            portal.interact("Enter");
                        } else {
                            portal = SceneObjectQuery.newQuery().name("Land of Snow portal").option("Exit").results().first();
                            if (portal != null) {
                                talktoPosty();
                            }

                        }
                        //New Values
                        //The QuestVarp changed to 10
                        //The varbit40610 value 0
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    break;

                case 10:
                    if (checkStep(QuestVarp, 0, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        //WALK UP THE HILL
                        //NEEDS FURTHER TESTING
                        if (!violethousearea.contains(player)) {
                            DebugScript.moveTo(violethousecord);
                        }
                        //New Values
                        //The QuestVarp changed to 15
                        //The varbit40610 value 0
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    break;

                case 15:
                    if (checkStep(QuestVarp, 0, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        //Know on the door
                        var door = SceneObjectQuery.newQuery().name("Door").option("Knock").results().first();
                        if (door != null) {
                            door.interact("Knock");
                            Execution.delay(15000);
                        }


                        //New Values
                        //The QuestVarp changed to 25
                        //The varbit40610 value 0
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    break;

                case 25:
                    if (checkStep(QuestVarp, 0, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        talktoViolet();
                        //New Values
                        //The QuestVarp changed to 30
                        //The varbit40610 value 0
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    break;
                case 30:
                    if (checkStep(QuestVarp, 0, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        //Search Chest
                        var chest = SceneObjectQuery.newQuery().name("Chest").results().first();
                        if (chest != null) {
                            chest.interact("Search");
                        }
                        //New Values
                        //The QuestVarp changed to 30
                        //The varbit40610 value 1
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 1, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        //Search Rug
                        var rug = SceneObjectQuery.newQuery().name("Rug").option("Search").results().first();
                        if (rug != null) {
                            rug.interact("Search");
                        }
                        //New Values
                        //The QuestVarp changed to 30
                        //The varbit40610 value 2
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 2, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        //Search fireplace
                        var fireplace = SceneObjectQuery.newQuery().name("Fireplace").option("Search").results().first();
                        if (fireplace != null) {
                            fireplace.interact("Search");
                        }
                        //New Values
                        //The QuestVarp changed to 30
                        //The varbit40610 value 3
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 3, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        //Search barrel of fish
                        var barreloffish = SceneObjectQuery.newQuery().name("Barrel of fish").option("Search").results().first();
                        if (barreloffish != null) {
                            barreloffish.interact("Search");
                        }
                        //New Values
                        //The QuestVarp changed to 35
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    break;

                case 35:
                    if (checkStep(QuestVarp, 4, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        //Talk to violet outside the house and then talk to an imp
                        talktoViolet();
                        Execution.delay(7200);
                        talktoSnowImp();
                        //New Values
                        //The QuestVarp changed to 40
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    break;

                case 40:
                    if (checkStep(QuestVarp, 4, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        //Go south through the gate to the abandoned farm
                        var gate = SceneObjectQuery.newQuery().name("Gate").results().first();
                        if (gate != null) {
                            Traverse.walkTo(new Coordinate(gate.getCoordinate().getX(), gate.getCoordinate().getY() - 3, gate.getCoordinate().getZ()), true);
                        }
                        //New Values
                        //The QuestVarp changed to 45
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    break;
                case 45:
                    if (checkStep(QuestVarp, 4, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        //Open chest then talk to marvin
                        var npc = NpcQuery.newQuery().name("Marvin Claus").results().first();
                        if (npc != null) {
                            npc.interact("Talk to");
                        } else {
                            var chest = SceneObjectQuery.newQuery().name("Chest").results().first();
                            if (chest != null) {
                                chest.interact("Open");
                            }
                        }
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 0
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    break;

                case 60:
                    if (checkStep(QuestVarp, 4, 0, 0, 0, 0, 0, 0, 0, 0)) {
                        //Talk to assistant brad then move to the trees
                        if (Objects.equals(currentStep, "")) {
                            talktoAssistantBrad();
                            Execution.delay(1800);
                            currentStep = "GOTOTAYLOR";
                        } else if (Objects.equals(currentStep, "GOTOTAYLOR")) {
                            var npcMovement = NpcQuery.newQuery().name("Marvin Claus").results().nearest();
                            println("X: " + npcMovement.getCoordinate().getX()); // 11937
                            println("X: " + npcMovement.getCoordinate().getY()); // 2606
                            Traverse.walkTo(new Coordinate(npcMovement.getCoordinate().getX() - 1, npcMovement.getCoordinate().getY() + 33, npcMovement.getCoordinate().getZ()), true);
                            Execution.delay(1800);
                            Traverse.walkTo(new Coordinate(npcMovement.getCoordinate().getX() - 29, npcMovement.getCoordinate().getY() + 39, npcMovement.getCoordinate().getZ()), true);
                            currentStep = "TALKTOTAYLOR";
                        } else if (Objects.equals(currentStep, "TALKTOTAYLOR")) {
                            talktoTaylor();
                            currentStep = "TALKTOBRAD";
                        }
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 0
                        //The varbit40624 value 1
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 0, 1, 0, 0, 0, 0, 0)) {
                        //Inspect wonky tree
                        var wonkyTree = SceneObjectQuery.newQuery().name("Wonky tree").results().first();
                        if (wonkyTree != null) {
                            wonkyTree.interact("Inspect");
                        }

                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 0
                        //The varbit40626 value 0
                        //The varbit40627 value 0
                        //The varbit40628 value 0
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 0, 0, 0, 0, 0)) {
                        //Go Back to town center and talk to brad
//Stating Position 11895 - 2635
                        if (currentStep2 == "") {
                            Traverse.walkTo(new Coordinate(player.getX() + 42, player.getY() - 28, player.getZ()), true);
                            Execution.delay(1800);
                            talktoAssistantBrad();
                            currentStep2 = "TALKTOSUSI";
                        }
                        if (currentStep2 == "TALKTOSUSI") {
                            talktoSusi();
                        }
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 1
                        //The varbit40626 value 1
                        //The varbit40627 value 1
                        //The varbit40628 value 1
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 1, 1, 1, 1, 0)) {
                        //After Susi go knock on 1st door
                        //11941 - 2596
                        var susicord = NpcQuery.newQuery().name("Assistant Susi").results().first().getCoordinate();
                        Traverse.walkTo(new Coordinate(susicord.getX() + 13, susicord.getY() + 1, susicord.getZ()), true);
                        var door = SceneObjectQuery.newQuery().name("Door").results().nearest();
                        if (door != null) {
                            door.interact("Knock");
                        }
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 1
                        //The varbit40626 value 2
                        //The varbit40627 value 1
                        //The varbit40628 value 1
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 1, 2, 1, 1, 0)) {
                        //go knock on 2nd doors
                        //11941 - 2596
                        var susicord = NpcQuery.newQuery().name("Assistant Susi").results().first().getCoordinate();
                        Traverse.walkTo(new Coordinate(susicord.getX() + 3, susicord.getY() + 20, susicord.getZ()), true);
                        var door = SceneObjectQuery.newQuery().name("Door").results().nearest();
                        if (door != null) {
                            door.interact("Knock");
                        }
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 2
                        //The varbit40626 value 2
                        //The varbit40627 value 1
                        //The varbit40628 value 1
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 2, 2, 1, 1, 0)) {
                        //go knock on 3rd door
                        //11941 - 2596
                        var susicord = NpcQuery.newQuery().name("Assistant Susi").results().first().getCoordinate();
                        Traverse.walkTo(new Coordinate(susicord.getX() - 10, susicord.getY() + 16, susicord.getZ()), true);
                        var door = SceneObjectQuery.newQuery().name("Door").results().nearest();
                        if (door != null) {
                            door.interact("Knock");
                        }
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 2
                        //The varbit40626 value 2
                        //The varbit40627 value 1
                        //The varbit40628 value 2
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 2, 2, 1, 2, 0)) {
                        //go knock on 4th door
                        //11941 - 2596
                        var susicord = NpcQuery.newQuery().name("Assistant Susi").results().first().getCoordinate();
                        Traverse.walkTo(new Coordinate(susicord.getX() - 15, susicord.getY() - 4, susicord.getZ()), true);
                        var door = SceneObjectQuery.newQuery().name("Door").results().nearest();
                        if (door != null) {
                            door.interact("Knock");
                        }
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 2
                        //The varbit40626 value 2
                        //The varbit40627 value 2
                        //The varbit40628 value 2
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 2, 2, 2, 2, 0)) {
                        talktoSusi();
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 4
                        //The varbit40626 value 4
                        //The varbit40627 value 4
                        //The varbit40628 value 4
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 4, 4, 4, 4, 0)) {
                        //Lamp Post1
                        //11941 - 2596
                        if (currentStep3 == "") {
                            var lamppost = SceneObjectQuery.newQuery().name("Lamppost").option("Decorate!").results().nearest();
                            if (lamppost != null) {
                                lamppost.interact("Decorate!");
                            } else {
                                currentStep3 = "DOHOUSES";
                            }
                        } else if (currentStep3 == "DOHOUSES") {
                            var lamppost = SceneObjectQuery.newQuery().name("Door").option("Decorate!").results().nearest();
                            if (lamppost != null) {
                                lamppost.interact("Decorate!");
                            }
                        }
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 4
                        //The varbit40626 value 4
                        //The varbit40627 value 4
                        //The varbit40628 value 5
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 4, 4, 4, 5, 0)) {
                        //Lamp Post1
                        //11941 - 2596
                        var lamppost = SceneObjectQuery.newQuery().name("Door").option("Decorate!").results().nearest();
                        if (lamppost != null) {
                            lamppost.interact("Decorate!");
                        }

                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 4
                        //The varbit40627 value 4
                        //The varbit40628 value 5
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 5, 4, 4, 5, 0)) {
                        //Lamp Post1
                        //11941 - 2596
                        var lamppost = SceneObjectQuery.newQuery().name("Door").option("Decorate!").results().nearest();
                        if (lamppost != null) {
                            lamppost.interact("Decorate!");
                        }

                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 4
                        //The varbit40628 value 5
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 5, 5, 4, 5, 0)) {
                        //Lamp Post1
                        //11941 - 2596

                        var lamppost = SceneObjectQuery.newQuery().name("Door").option("Decorate!").results().nearest();
                        if (lamppost != null) {
                            lamppost.interact("Decorate!");
                        }

                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 0
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 5, 5, 5, 5, 0)) {
                        if (currentStep4 == "") {
                            talktoSusi();
                            currentStep4 = "TALKTOTIMOTHY";

                        } else if (currentStep4 == "TALKTOTIMOTHY") {
                            talktoTimothy();
                            currentStep4 = "TALKTOPOSTY";
                        } else if (currentStep4 == "TALKTOPOSTY") {
                            talktoPosty();
                            currentStep4 = "DONE";
                        }
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 1
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 5, 5, 5, 5, 1)) {
                        var elizabeth = NpcQuery.newQuery().name("Elizabeth").results().first();
                        elizabeth.interact("Talk to");

                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 2
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 5, 5, 5, 5, 2)) {
                        var elizabeth = NpcQuery.newQuery().name("Peter").results().first();
                        elizabeth.interact("Talk to");

                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 3
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 5, 5, 5, 5, 3)) {
                        var elizabeth = NpcQuery.newQuery().name("Mozzie").results().first();
                        elizabeth.interact("Talk to");

                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 4
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 5, 5, 5, 5, 4)) {
                        var elizabeth = NpcQuery.newQuery().name("Neal").results().first();
                        elizabeth.interact("Talk to");

                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 5
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 5, 5, 5, 5, 5)) {
                        var santasList = SceneObjectQuery.newQuery().name("Santa's 'naughty or nice' list").results().first();
                        if (santasList == null) {
                            println("NOT FOUND SANTAS LIST");
                        } else {
                            santasList.interact("Take");
                        }
                        //New Values
                        //The QuestVarp changed to 60
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 6
                    }
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 5, 5, 5, 5, 6)) {
                        //11939 - 2574
                        var nealCord = NpcQuery.newQuery().name("Neal").results().first().getCoordinate();
                        Traverse.walkTo(new Coordinate(nealCord.getX(), nealCord.getY() + 30, nealCord.getZ()), true);
                        talktoPosty();
                        //New Values
                        //The QuestVarp changed to 65
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 1
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 7
                    }
                    break;

                case 65:
                    if (checkStep(QuestVarp, 4, 0, 1, 1, 5, 5, 5, 5, 7)) {

                        if (currentStep5 == "") {
                            talktoMarvinClaus();
                            currentStep5 = "TALKTOHAL";
                        } else if (currentStep5 == "TALKTOHAL") {
                            talktoHal();
                        } else if (currentStep5 == "GOUPHILL") {
                            println("Launching IMP");
                            // 11941 - 2596
                            var susicord = NpcQuery.newQuery().name("Assistant Susi").results().first().getCoordinate();
                            Traverse.walkTo(new Coordinate(susicord.getX() - 4, susicord.getY() - 17, susicord.getZ()), true);
                            currentStep5 = "THROWIMP";
                        } else if (currentStep5 == "THROWIMP") {
                            Execution.delay(6000);
                            Backpack.interact("Snow impling", "Launch");
                            Execution.delay(6000);
                            Backpack.interact("Snow impling", "Launch");
                            Execution.delay(6000);
                            Backpack.interact("Snow impling", "Launch");
                            Execution.delay(6000);
                        }

                        //New Values
                        //The QuestVarp changed to 65
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 4
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 7

                    }
                    if (checkStep(QuestVarp, 4, 0, 4, 1, 5, 5, 5, 5, 7)) {
                        var wonkyTree = SceneObjectQuery.newQuery().name("Wonky tree").results().first();
                        if (wonkyTree != null) {
                            wonkyTree.interact("Admire");
                        }


                        //New Values
                        //The QuestVarp changed to 70
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 4
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 7
                    }
                    break;
                case 70:
                    if (checkStep(QuestVarp, 4, 0, 4, 1, 5, 5, 5, 5, 7)) {
                        talktoMarvinClaus();
                        //New Values
                        //The QuestVarp changed to 75
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 4
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 7
                    }
                    break;
                case 75:
                    if (checkStep(QuestVarp, 4, 0, 4, 1, 5, 5, 5, 5, 7)) {
                        var door = SceneObjectQuery.newQuery().name("Door").option("Knock").results().nearest();
                        if (door != null) {
                            door.interact("Knock");
                        }
                        println("Talk to Trevor");
                        talktoTrevor();

                        //New Values
                        //The QuestVarp changed to 100
                        //The varbit40610 value 4
                        //The varbit40622 value 0
                        //The varbit40623 value 4
                        //The varbit40624 value 1
                        //The varbit40625 value 5
                        //The varbit40626 value 5
                        //The varbit40627 value 5
                        //The varbit40628 value 5
                        //The varbit40629 value 7
                    }
                    break;
                case 100:
                    println("Quest Completed!");
                    break;
            }
        }
    }

    public static void talktoTrevor() {
        Npc npc = NpcQuery.newQuery().name("Trevor").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoMarvinClaus() {
        Npc npc = NpcQuery.newQuery().name("Marvin Claus").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoHal() {
        Npc npc = NpcQuery.newQuery().name("Hal the snow impling").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoTaylor() {
        Npc npc = NpcQuery.newQuery().name("Taylor").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoTimothy() {
        Npc npc = NpcQuery.newQuery().name("Assistant Timothy").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoSusi() {
        Npc npc = NpcQuery.newQuery().name("Assistant Susi").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoPostiePete() {
        Npc npc = NpcQuery.newQuery().name("Postie Pete").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoViolet() {
        Npc npc = NpcQuery.newQuery().name("Violet").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoSnowImp() {
        Npc npc = NpcQuery.newQuery().name("Snow imp").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoAssistantBrad() {
        Npc npc = NpcQuery.newQuery().name("Assistant Brad").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoPosty() {
        Npc npc = NpcQuery.newQuery().name("Posty").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
