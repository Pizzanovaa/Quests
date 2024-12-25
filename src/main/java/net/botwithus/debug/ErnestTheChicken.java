package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECTABLE_COMPONENT;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_OBJECT;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.Dialog;
import net.botwithus.api.game.world.Traverse;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Backpack;
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
import net.botwithus.rs3.util.RandomGenerator;

import java.util.Objects;

public class ErnestTheChicken {
    public static boolean checkStep(int varpQuest, int varbit2168, int varbit2169, int varbit2170, int varbit2171, int varbit2172, int varbit2173, int varbit2174, int varbit2175, int varbit2176, int varbit2177, int varbit2178, int varbit2179, int varbit2180, int varbit2181, int varbit2182, int varbit2183, int varbit2184, int varbit2185, int varbit2186, int varbit2187, int varbit2188, int varbit2189, int varbit2190, int varbit2191, int varbit2192, int varbit2193, int varbit2194, int varbit2195, int varbit2196, int varbit2197, int varbit37057) {
        if (VarManager.getVarbitValue(2183) == varpQuest
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

    enum CurrentStepVarp1{
        FIRSTSTEP,
    }
    static CurrentStepVarp1 currentStepVarp1 = CurrentStepVarp1.FIRSTSTEP;
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3112, 3329, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);


    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(2183);
        player = Client.getLocalPlayer().getServerCoordinate();
        println("QuestVarp: " + QuestVarp);


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            println("Starting quest... Ernest the Chicken");
            if (startarea.contains(player)) {
                talktoVeronica();
                //Talk to Postie Pete at the Land of Snow portal. (Chat ✓)
                // New Values
                // The QuestVarp changed to 1
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


                        //Talk to Postie Pete at the Land of Snow portal. (Chat ✓)
                        // New Values
                        // The QuestVarp changed to 1
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

                case 3:
                    println("Quest Completed!");
                    break;
            }
        }
    }


    public static void talktoVeronica() {
        Npc npc = NpcQuery.newQuery().name("Veronica").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
