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
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.util.RandomGenerator;

import java.util.Objects;

public class MythsOfTheWhiteLands {
    public static boolean checkStep(int varpQuest, int varbit13877, int varbit13878, int varbit13879, int varbit13880, int varbit13881, int varbit13882, int varbit13883, int varbit13884) {
        if (VarManager.getVarbitValue(13876) == varpQuest && VarManager.getVarbitValue(13877) == varbit13877 && VarManager.getVarbitValue(13878) == varbit13878
                && VarManager.getVarbitValue(13879) == varbit13879 && VarManager.getVarbitValue(13880) == varbit13880 && VarManager.getVarbitValue(13881) == varbit13881
                && VarManager.getVarbitValue(13882) == varbit13882 && VarManager.getVarbitValue(13883) == varbit13883 && VarManager.getVarbitValue(13884) == varbit13884) {
            return true;
        } else return false;
    }

    static String currentStep = "";
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
                //The QuestVarp changed to 5
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
                case 5:
                    if (checkStep(QuestVarp, 0, 0, 0, 0, 0, 0, 0, 0)) {


                        //New Values
                        //The QuestVarp changed to 5
                        //The varbit13877 value 0
                        //The varbit13878 value 0
                        //The varbit13879 value 0
                        //The varbit13880 value 0
                        //The varbit13881 value 0
                        //The varbit13882 value 0
                        //The varbit13883 value 0
                        //The varbit13884 value 0
                    }
                    break;


                case 60:
                    println("Quest Completed!");
                    break;
            }
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
