package net.botwithus.debug;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.cs2.ScriptBuilder;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.rs3.game.cs2.layouts.Layout.INT;
import static net.botwithus.rs3.game.minimenu.actions.ObjectAction.OBJECT1;
import static net.botwithus.rs3.script.Execution.delay;

public class StolenHearts {

    public static final ScriptBuilder dolls = ScriptBuilder.of(6815).args(INT, INT);
    public static final ScriptBuilder test = ScriptBuilder.of(6825).args(INT, INT);
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate start = new Coordinate(3093, 3273, 0);
    static Coordinate palace = new Coordinate(3292, 3168, 0);
    static Area.Circular palacearea = new Area.Circular(start, 10);
    static Area.Circular startarea = new Area.Circular(start, 10);
    static Coordinate craftingshop = new Coordinate(3321, 3191, 0);
    static Area.Circular craftingshoparea = new Area.Circular(craftingshop, 10);
    static Coordinate step1 = new Coordinate(3322, 3195, 1);
    static Area.Circular step1area = new Area.Circular(step1, 10);
    static Coordinate step2 = new Coordinate(3322, 3191, 2);
    static Area.Circular step2area = new Area.Circular(step2, 5);
    static Coordinate step3 = new Coordinate(3317, 3184, 2);
    static Area.Circular step3area = new Area.Circular(step3, 5);
    static Coordinate step4 = new Coordinate(3316, 3175, 1);
    static Area.Circular step4area = new Area.Circular(step4, 10);
    static Coordinate step5 = new Coordinate(3304, 3186, 1);
    static Area.Circular step5area = new Area.Circular(step5, 3);
    static Coordinate step6 = new Coordinate(3297, 3188, 2);
    static Area.Circular step6area = new Area.Circular(step6, 3);
    static Coordinate step7 = new Coordinate(3289, 3188, 2);
    static Area.Circular step7area = new Area.Circular(step7, 3);
    static Coordinate step8 = new Coordinate(3285, 3190, 3);
    static Area.Circular step8area = new Area.Circular(step8, 3);
    static Coordinate step9 = new Coordinate(3276, 3189, 2);
    static Area.Circular step9area = new Area.Circular(step9, 3);
    static Coordinate step10 = new Coordinate(3278, 3187, 1);
    static Area.Circular step10area = new Area.Circular(step10, 3);
    static Coordinate step11 = new Coordinate(3272, 3187, 1);
    static Area.Circular step11area = new Area.Circular(step11, 2);
    static Coordinate step12 = new Coordinate(3271, 3184, 1);
    static Area.Circular step12area = new Area.Circular(step12, 4);
    static Coordinate step13 = new Coordinate(3268, 3174, 1);
    static Area.Circular step13area = new Area.Circular(step13, 3);
    static Coordinate step14 = new Coordinate(3268, 3172, 2);
    static Area.Circular step14area = new Area.Circular(step14, 10);
    static Coordinate step15 = new Coordinate(3282, 3164, 1);
    static Area.Circular step15area = new Area.Circular(step15, 4);
    static Coordinate step16 = new Coordinate(3284, 3164, 2);
    static Area.Circular step16area = new Area.Circular(step15, 15);
    static Coordinate spot1;
    static Coordinate spot2;
    static Coordinate spot3;
    static Coordinate spot4;

    public static void quest2() {
        int QuestVarp = VarManager.getVarbitValue(11870);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }

        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Stolen Hearts!");

            if (startarea.contains(player)) {
                talkToOzan();
            } else {
                DebugScript.moveTo(start);
            }

        } else {
            switch (QuestVarp) {
                case 5:
                    SceneObject trapdoor = SceneObjectQuery.newQuery().name("Trapdoor").results().nearest();
                    if (trapdoor != null) {
                        trapdoor.interact("Open");
                        delay(RandomGenerator.nextInt(600, 800));
                    } else {
                        Npc Khrum = NpcQuery.newQuery().name("Khnum").results().nearest();
                        if (Khrum != null) {
                            Khrum.interact("Talk to");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    break;
                case 20:
                    Npc ozan = NpcQuery.newQuery().name("Ozan").results().nearest();
                    if (ozan != null) {
                        ozan.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                    } else {
                        SceneObject ladders = SceneObjectQuery.newQuery().name("Ladder").results().nearest();
                        if (ladders != null) {
                            ladders.interact("Climb-up");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    break;
                case 25:
                    if (startarea.contains(player)) {
                        talkToOzan();
                        spot1 = null;
                    } else {
                        ScriptConsole.println("Start follow");
                        if (spot1 == null) {
                            SceneObject water = SceneObjectQuery.newQuery().name("Waterpump").results().nearest();
                            if (water != null) {
                                Coordinate waterpump = water.getCoordinate();
                                int waterx = waterpump.getX();
                                int watery = waterpump.getY();
                                spot1 = new Coordinate(waterx - 6, watery, 0);
                                spot2 = new Coordinate(waterx - 7, watery - 16, 0);
                                spot3 = new Coordinate(waterx + 17, watery - 12, 0);
                            }
                        }
                        Npc Khrum = NpcQuery.newQuery().name("Khnum").results().first();
                        if (Khrum != null) {
                            if (!Client.getLocalPlayer().isMoving()) {
                                double distancespot1 = spot1.distanceTo(Khrum.getCoordinate());
                                double distancespot2 = spot2.distanceTo(Khrum.getCoordinate());
                                double distancespot3 = spot3.distanceTo(Khrum.getCoordinate());

                                ScriptConsole.println("SPOT1: " + distancespot1);
                                ScriptConsole.println("SPOT2 " + distancespot2);
                                ScriptConsole.println("SPOT3: " + distancespot3);

                                Coordinate closestSpot;
                                if (distancespot1 <= distancespot2 && distancespot1 <= distancespot3) {

                                    closestSpot = spot1;
                                } else if (distancespot2 <= distancespot1 && distancespot2 <= distancespot3) {
                                    closestSpot = spot2;
                                } else {
                                    closestSpot = spot3;
                                }

                                if (!player.equals(closestSpot)) {
                                    Movement.walkTo(closestSpot.getX(), closestSpot.getY(), false);
                                    delay(RandomGenerator.nextInt(600, 800));
                                }
                            }
                        }
                    }
                    break;
                case 30:
                    ScriptConsole.println("Hi");
                    SceneObject wall = SceneObjectQuery.newQuery().name("Spear wall").results().first();
                    if (wall != null) {
                        SceneObject Door = SceneObjectQuery.newQuery().name("Door").hidden(false).results().nearestTo(wall);
                        if (Door != null) {
                            Door.interact("Open");
                            delay(RandomGenerator.nextInt(600, 800));
                        } else {
                            talkToOzan();
                        }
                    } else {
                        talkToOzan();
                    }
                    break;
                case 35, 40:
                    talkToOzan();
                    break;
                case 45:
                    Npc nerc = NpcQuery.newQuery().name("Mercenary Joe").results().nearest();
                    if (nerc == null) {
                        DebugScript.moveTo(new Coordinate(3129, 3204, 0));
                    } else {
                        nerc.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                case 50:
                    break;
                case 55:
                    GroundItem note = GroundItemQuery.newQuery().name("Ransom note").results().nearest();
                    if (note != null) {
                        note.interact("Take");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    break;
                case 60:
                    Npc leela = NpcQuery.newQuery().name("Leela").results().nearest();
                    if (leela != null) {
                        leela.interact("Talk to");
                        delay(RandomGenerator.nextInt(5000, 6000));
                        Npc guard = NpcQuery.newQuery().name("Palace guard").results().nearest();
                        if (guard != null) {
                            guard.interact("Talk-to");
                            delay(RandomGenerator.nextInt(600, 800));
                        } else {
                            DebugScript.moveTo(palace);
                        }
                    } else {
                        Npc guard = NpcQuery.newQuery().name("Palace guard").results().nearest();
                        if (guard != null) {
                            guard.interact("Talk-to");
                            delay(RandomGenerator.nextInt(600, 800));
                        } else {
                            DebugScript.moveTo(palace);
                        }
                    }
                    break;
                case 65:
                    Npc guard = NpcQuery.newQuery().name("Palace guard").results().nearest();
                    if (guard != null) {
                        guard.interact("Talk-to");
                        delay(RandomGenerator.nextInt(600, 800));
                    } else {
                        DebugScript.moveTo(palace);
                    }
                    break;
                case 70:
                    if (player.getZ() == 0) {
                        if (!craftingshoparea.contains(player)) {
                            DebugScript.moveTo(craftingshop);
                        } else {
                            SceneObject stairs = SceneObjectQuery.newQuery().name("Steps").results().nearestTo(craftingshop);
                            if (stairs != null) {
                                stairs.interact("Climb-up");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    } else {
                        if (step1area.contains(player)) {
                            SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").results().nearest();
                            if (ladder != null) {
                                ladder.interact("Climb");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step2area.contains(player)) {
                            SceneObject plank = SceneObjectQuery.newQuery().name("Plank").results().nearest();
                            if (plank != null) {
                                plank.interact("Walk across");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step3area.contains(player)) {
                            SceneObject thing = SceneObjectQuery.newQuery().name("Awning").results().nearest();
                            if (thing != null) {
                                thing.interact("Slide down");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step4area.contains(player)) {
                            SceneObject line = SceneObjectQuery.newQuery().name("Washing line").results().nearest();
                            if (line != null) {
                                line.interact("Walk across");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step5area.contains(player)) {
                            SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").results().nearest();
                            if (ladder != null) {
                                ladder.interact("Climb");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step6area.contains(player)) {
                            SceneObject planks = SceneObjectQuery.newQuery().name("Planks").results().nearest();
                            if (planks != null) {
                                planks.interact("Cross");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step7area.contains(player)) {
                            SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").results().nearest();
                            if (ladder != null) {
                                ladder.interact("Climb");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step8area.contains(player)) {
                            SceneObject scaffold = SceneObjectQuery.newQuery().name("Scaffold").results().nearest();
                            if (scaffold != null) {
                                scaffold.interact("Jump from");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step9area.contains(player)) {
                            SceneObject rug = SceneObjectQuery.newQuery().name("Rug").results().nearest();
                            if (rug != null) {
                                rug.interact("Climb down");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step10area.contains(player)) {
                            SceneObject frame = SceneObjectQuery.newQuery().name("Wooden frame").results().nearest();
                            if (frame != null) {
                                frame.interact("Swing across");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step11area.contains(player)) {
                            SceneObject scaffold = SceneObjectQuery.newQuery().name("Scaffold").results().nearest();
                            if (scaffold != null) {
                                scaffold.interact("Jump from");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step12area.contains(player)) {
                            SceneObject thing = SceneObjectQuery.newQuery().name("Awning").results().nearest();
                            if (thing != null) {
                                thing.interact("Bounce on");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step13area.contains(player)) {
                            SceneObject bricks = SceneObjectQuery.newQuery().name("Brickwork").results().nearest();
                            if (bricks != null) {
                                bricks.interact("Climb up");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step14area.contains(player)) {
                            SceneObject rope = SceneObjectQuery.newQuery().name("Rope").results().first();
                            if (rope != null) {
                                rope.interact("Shimmy across");
                                delay(RandomGenerator.nextInt(600, 800));
                            } else {
                                SceneObject flag = SceneObjectQuery.newQuery().name("Flagpole").results().nearest();
                                if (flag != null) {
                                    flag.interact("Leave it to Ozan");
                                    delay(RandomGenerator.nextInt(600, 800));
                                }
                            }
                        } else if (step15area.contains(player)) {
                            SceneObject bricks = SceneObjectQuery.newQuery().name("Brickwork").results().nearest();
                            if (bricks != null) {
                                bricks.interact("Climb up");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (step16area.contains(player)) {
                            SceneObject skylight = SceneObjectQuery.newQuery().name("Skylight").results().nearest();
                            if (skylight != null) {
                                skylight.interact("Break in");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }
                    break;
                case 72:
                    if (step14area.contains(player)) {
                        SceneObject rope = SceneObjectQuery.newQuery().name("Rope").results().first();
                        if (rope != null) {
                            rope.interact("Shimmy across");
                            delay(RandomGenerator.nextInt(600, 800));
                        } else {
                            SceneObject flag = SceneObjectQuery.newQuery().name("Flagpole").results().nearest();
                            if (flag != null) {
                                flag.interact("Leave it to Ozan");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    } else if (step15area.contains(player)) {
                        SceneObject bricks = SceneObjectQuery.newQuery().name("Brickwork").results().nearest();
                        if (bricks != null) {
                            bricks.interact("Climb up");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    break;
                case 80:
                    if (step15area.contains(player)) {
                        SceneObject bricks = SceneObjectQuery.newQuery().name("Brickwork").results().nearest();
                        if (bricks != null) {
                            bricks.interact("Climb up");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    } else if (step16area.contains(player)) {
                        SceneObject skylight = SceneObjectQuery.newQuery().name("Skylight").results().nearest();
                        if (skylight != null) {
                            skylight.interact("Break in");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    break;
                case 85:
                    int slot = VarManager.getVarbitValue(11901);
         /*           int progress = VarManager.getVarbitValue(11902);
                    if(progress < 2) {
                        if (slot <= 9) {
                            int id = 0;
                            switch (slot) {
                                case 1 -> id = 88539202;
                                case 2 -> id = 88539205;
                                case 3 -> id = 88539206;
                                case 4 -> id = 88539207;
                                case 5 -> id = 88539208;
                                case 6 -> id = 88539209;
                                case 7 -> id = 88539210;
                                case 8 -> id = 88539211;
                                case 9 -> id = 88539212;
                                case 10 -> id = 88539231;
                                case 11 -> id = 88539232;
                                case 12 -> id = 88539233;
                                case 13 -> id = 88539236;
                                case 14 -> id = 88539237;
                                case 15 -> id = 88539238;
                                case 16 -> id = 88539213;
                            }
                            dolls.invokeExact(id, 88539232);
                            delay(RandomGenerator.nextInt(600, 800));
                            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 88539200);
                        }
                    }else{


                    }*/
                    // VarManager.getVarbitValue(11901) Diamond slot

                    if (Backpack.contains("Weight")) {
                        SceneObject display = SceneObjectQuery.newQuery().name("Kharid-ib display").results().nearest();
                        if (display != null) {
                            display.interact(OBJECT1);
                            delay(RandomGenerator.nextInt(600, 1200));
                        }
                    } else {
                        ScriptConsole.println("Diamond idol is in slot: " + slot);
                        ScriptConsole.println("1-9 Bottom row");
                        delay(RandomGenerator.nextInt(3000, 4000));
                    }
                    break;
                case 100: // still need password fixing
                    ScriptConsole.println("Quest Complete.");
                    break;

            }

        }
    }

    public static void trapdoor() {
        SceneObject trapdoor = SceneObjectQuery.newQuery().name("Trapdoor").results().nearest();
        if (trapdoor != null) {
            trapdoor.interact("Open");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talkToOzan() {
        Npc ozan = NpcQuery.newQuery().name("Ozan").results().nearest();
        if (ozan != null) {
            ozan.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
