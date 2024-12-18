package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.util.RandomGenerator;

public class WolfWhistle {

    public static boolean checkStep(int varpQuest, int varbit12335, int varbit12336, int varbit12337, int varbit12338, int varbit12339, int varbit12340, int varbit12341, int varbit12342, int varbit12343, int varbit12344, int varbit12345, int varbit12346, int varbit12347, int varbit12348) {
        if (VarManager.getVarbitValue(12334) == varpQuest
                && VarManager.getVarbitValue(12335) == varbit12335
                && VarManager.getVarbitValue(12336) == varbit12336
                && VarManager.getVarbitValue(12337) == varbit12337
                && VarManager.getVarbitValue(12338) == varbit12338
                && VarManager.getVarbitValue(12339) == varbit12339
                && VarManager.getVarbitValue(12340) == varbit12340
                && VarManager.getVarbitValue(12341) == varbit12341
                && VarManager.getVarbitValue(12342) == varbit12342
                && VarManager.getVarbitValue(12343) == varbit12343
                && VarManager.getVarbitValue(12344) == varbit12344
                && VarManager.getVarbitValue(12345) == varbit12345
                && VarManager.getVarbitValue(12346) == varbit12346
                && VarManager.getVarbitValue(12347) == varbit12347
                && VarManager.getVarbitValue(12348) == varbit12348) {
            return true;
        } else
            return false;
    }

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2930, 3448, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate wellcord = new Coordinate(2883, 3431, 0);
    static Area.Circular wellarea = new Area.Circular(wellcord, 10);
    static Coordinate firstfloorcord = new Coordinate(2930, 3445, 1);
    static Area.Circular firstfloorarea = new Area.Circular(firstfloorcord, 10);
    static Coordinate animalshopcord = new Coordinate(2932, 3433, 0);
    static Area.Circular animalshoparea = new Area.Circular(animalshopcord, 10);
    static Coordinate whitewolfmountaincord = new Coordinate(2859, 3476, 0);
    static Area.Circular whitewolfmountainarea = new Area.Circular(whitewolfmountaincord, 10);


    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(12334);
        player = Client.getLocalPlayer().getServerCoordinate();
        //println("QuestVarp: " + QuestVarp);


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            println("Starting quest... Wolf Whistle");
            if (startarea.contains(player)) {
                talktoPikkupstix();
                //Talk to Pikkupstix in his house just north of Taverley's east entrance. (Chat 2•✓)
                //The varbit 12345 value changes from 0 to 1
                //QuestVarp changed to 5
            } else {
                DebugScript.moveTo(startcord);
            }
        } else {
            switch (QuestVarp) {
                case 5:
                    if (!wellarea.contains(player)) {
                        DebugScript.moveTo(wellcord);
                    } else {
                        println("Talking to Scalectrix");
                        talktoScalectrix();
                        //Talk to Scalectrix, then watch the short cutscene. (Chat 1)
                        //The varbit 12345 value changes from 1 to 0
                        //The varbit 12344 value changes from 0 to 1
                        //The varbit 12342 value changes from 0 to 1
                        //QuestVarp changed to 10
                    }
                    break;

                case 10:
                    if (startarea.contains(player)) {
                        talktoPikkupstix();
                        //Talk to Pikkupstix back at the east side of Taverley. (Chat 2•5)
                        //The varbit 14334 value changes from 0 to 1
                        //The varbit 12346 value changes from 0 to 1
                        //QuestVarp changed to 15
                    } else {
                        DebugScript.moveTo(startcord);
                    }
                    break;
                case 15:
                    if (checkStep(15, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0)) {
                        //Go upstairs at Pikkupstix's house and search the cluttered drawers in the north-west corner.
                        if (!firstfloorarea.contains(player)) {
                            DebugScript.moveTo(firstfloorcord);
                        } else {
                            println("Search cluttered drawers");
                            var shelf = SceneObjectQuery.newQuery().name("Cluttered drawers").results().first();
                            if (shelf != null) {
                                shelf.interact("Search");
                            }
                            //The varbit 12338 value changes from 0 to 1
                        }
                    }
                    if (checkStep(15, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0)) {
                        //Go to the pet shop south of Pikkupstix and ask the owner about white hare meat. (Chat 1)
                        if (!animalshoparea.contains(player)) {
                            DebugScript.moveTo(animalshopcord);
                        } else {
                            talktoPetShopOwner();
                            //The varbit 12337 value changes from 0 to 1
                        }
                    }
                    if (checkStep(15, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0)) {
                        //Go back towards the well and go west onto White Wolf Mountain and search the body
                        if (!whitewolfmountainarea.contains(player)) {
                            DebugScript.moveTo(whitewolfmountaincord);
                        } else {
                            println("Searching Body");
                            var body = SceneObjectQuery.newQuery().name("Stikklebrix's body").results().first();
                            if (body != null) {
                                body.interact("Search");
                                //The varbit 12336 value changes from 0 to 1
                            }
                        }
                    }
                    if (checkStep(15, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0)) {
                        //Talk to Pikkupstix after the items are obtained. (Chat 2)
                        if (!startarea.contains(player)) {
                            DebugScript.moveTo(startcord);
                        } else {
                            talktoPikkupstix();
                            //New Values
                            //The QuestVarp changed to 20
                            //The varbit 12335 value 0
                            //The varbit 12336 value 1
                            //The varbit 12337 value 1
                            //The varbit 12338 value 1
                            //The varbit 12339 value 0
                            //The varbit 12340 value 1
                            //The varbit 12341 value 0
                            //The varbit 12342 value 1
                            //The varbit 12343 value 0
                            //The varbit 12344 value 1
                            //The varbit 12345 value 0
                            //The varbit 12346 value 0
                            //The varbit 12347 value 0
                            //The varbit 12348 value 0

                        }
                    }

                    break;

                case 20:
                    if (checkStep(20, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0)) {
                        //Infuse the pouch using the obelisk on the eastern side of his house.
                        if (!startarea.contains(player)) {
                            DebugScript.moveTo(startcord);
                        } else {
                            var obelisk = SceneObjectQuery.newQuery().name("Obelisk").results().first();
                            if (obelisk != null) {
                                obelisk.interact("Infuse-pouch");
                            }
                            //New Values
                            //The QuestVarp changed to 25
                            //The varbit 12335 value 0
                            //The varbit 12336 value 1
                            //The varbit 12337 value 1
                            //The varbit 12338 value 1
                            //The varbit 12339 value 0
                            //The varbit 12340 value 1
                            //The varbit 12341 value 0
                            //The varbit 12342 value 1
                            //The varbit 12343 value 0
                            //The varbit 12344 value 1
                            //The varbit 12345 value 0
                            //The varbit 12346 value 0
                            //The varbit 12347 value 0
                            //The varbit 12348 value 0

                        }
                    }
                    break;

                case 25:
                    if (checkStep(25, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0)) {
                        //Infuse the pouch using the obelisk on the eastern side of his house.
                        if (!startarea.contains(player)) {
                            DebugScript.moveTo(startcord);
                        } else {
                            talktoPikkupstix();
                            //New Values
                            //The QuestVarp changed to 30
                            //The varbit 12335 value 0
                            //The varbit 12336 value 1
                            //The varbit 12337 value 1
                            //The varbit 12338 value 1
                            //The varbit 12339 value 0
                            //The varbit 12340 value 1
                            //The varbit 12341 value 0
                            //The varbit 12342 value 1
                            //The varbit 12343 value 0
                            //The varbit 12344 value 0
                            //The varbit 12345 value 1
                            //The varbit 12346 value 0
                            //The varbit 12347 value 0
                            //The varbit 12348 value 0

                        }
                    }
                    break;
                case 30:
                    if (checkStep(30, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0)) {
                        //Go back to the well watch cutscene
                        if (!wellarea.contains(player)) {
                            DebugScript.moveTo(wellcord);
                        } else {
                            talktoScalectrix();
                            //Quest Finished
                        }
                    }
                    break;
                case 35:
                    println("Quest Completed!");
                    break;
            }
        }
    }

    public static void talktoPikkupstix() {
        Npc npc = NpcQuery.newQuery().name("Pikkupstix").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoScalectrix() {
        Npc npc = NpcQuery.newQuery().name("Scalectrix").results().first();
        if (npc != null) {
            npc.interact("Talk-To");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoPetShopOwner() {
        Npc npc = NpcQuery.newQuery().name("Pet shop owner").results().first();
        if (npc != null) {
            npc.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
