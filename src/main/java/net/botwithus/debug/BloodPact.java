package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.inventories.Backpack;
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
import static net.botwithus.rs3.script.Execution.delay;

public class BloodPact {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3242, 3200, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(10826);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Blood Pact");

            if (startarea.contains(player)) {
                talktoxeric();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            if (startarea.contains(player) && QuestVarp < 50) {
                SceneObject entrance = SceneObjectQuery.newQuery().name("Catacombs").option("Enter").results().first();
                if (entrance != null) {
                    entrance.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                return;
            }
            switch (QuestVarp) {
                case 10:
                    SceneObject entrance = SceneObjectQuery.newQuery().name("Catacombs").option("Enter").results().first();
                    if (entrance != null) {
                        entrance.interact("Enter");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                case 12:
                    Npc kyle = NpcQuery.newQuery().name("Kayle").results().nearest();
                    if (kyle != null) {
                        Movement.walkTo(kyle.getCoordinate().getX(), kyle.getCoordinate().getY() - 1, false);
                        delay(2000);
                    }
                    break;
                case 15:
                    talktoxeric();
                    break;
                case 20:
                    Npc kyle2 = NpcQuery.newQuery().name("Kayle").results().nearest();
                    if (kyle2 != null) {
                        if (!Client.getLocalPlayer().hasTarget()) {
                            kyle2.interact("Attack");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    break;
                case 25:
                    Npc kyle3 = NpcQuery.newQuery().name("Kayle").results().nearest();
                    if (kyle3 != null) {
                        kyle3.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    break;
                case 30:
                    case30();
                    break;
                case 35:
                    SceneObject winch = SceneObjectQuery.newQuery().name("Winch").results().nearest();
                    if (winch != null) {
                        winch.interact("Operate");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    break;
                case 36:
                    Npc Caitlin = NpcQuery.newQuery().name("Caitlin").results().nearest();
                    if (Caitlin != null) {
                            Caitlin.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    break;
                case 40:
                    SceneObject tombdoor = SceneObjectQuery.newQuery().name("Stairs").results().nearest();
                    if (tombdoor != null) {
                        tombdoor.interact("Climb down");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    break;
                case 41:
                    Npc reese = NpcQuery.newQuery().name("Reese").results().first();
                    if(reese != null){
                        if(!Client.getLocalPlayer().hasTarget()){
                            reese.interact("Attack");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    break;
                case 50:
                    Npc llona = NpcQuery.newQuery().name("Ilona").results().first();
                    if (llona != null) {
                        llona.interact("Untie");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    break;
            }

        }
    }

    public static void talktoxeric() {
        Npc npc = NpcQuery.newQuery().name("Xenia").results().first();
        if (npc != null) {
            npc.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }


    public static void case30() {

        if (Backpack.contains("Kayle's chargebow")) {
            Backpack.interact("Kayle's chargebow", "Wield");
            return;
        }
        GroundItem item = GroundItemQuery.newQuery().results().nearest();
        if (item != null) {
            item.interact("Take");
            delay(RandomGenerator.nextInt(600, 800));
        } else {
            SceneObject tombdoor = SceneObjectQuery.newQuery().name("Tomb door").results().nearest();
            if (tombdoor != null) {
                tombdoor.interact("Open");
                delay(RandomGenerator.nextInt(600, 800));
            } else {
                Npc Caitlin = NpcQuery.newQuery().name("Caitlin").results().nearest();
                if (Caitlin != null) {
                    if (!Client.getLocalPlayer().hasTarget()) {
                        Caitlin.interact("Attack");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
            }
        }
    }
}
/*   public void doquest() {
   else if (value == 40) {
            if (isOpen()) {
                if (Dialog.hasOption("I'm your worst nightmare, Zamorakian scum!")) {
                    interact("I'm your worst nightmare, Zamorakian scum!");
                    return;
                }
                Dialog.select();
                return;
            }
            if (Backpack.contains("Caitlin's staff")) {
                Backpack.interact("Caitlin's staff", "Wield");
                return;
            }
            GroundItem item = GroundItemQuery.newQuery().results().nearest();
            if (item != null) {
                item.interact("Take");
                delay(2000);
            } else {
                SceneObject tombdoor = SceneObjectQuery.newQuery().name("Stairs").results().nearest();
                if (tombdoor != null) {
                    tombdoor.interact("Climb down");
                    delay(2000);
                }
            }
        } else if (value == 41) {
            if (isOpen()) {
                if (Dialog.hasOption("I'm your worst nightmare, Zamorakian scum!")) {
                    interact("I'm your worst nightmare, Zamorakian scum!");
                    return;
                }
                Dialog.select();
                return;
            }
        } else if (value == 45) {
            if (isOpen()) {
                if (Dialog.hasOption("Time for you to die!")) {
                    interact("Time for you to die!");
                    return;
                }
                Dialog.select();
                return;
            }
        } else if (value == 50) {
            if (isOpen()) {
                println(Dialog.getOptions());
                if (Dialog.hasOption("Are you ready to return to the surface?")) {
                    interact("Yes, rescue Ilona.");
                    return;
                }
                Dialog.select();
                return;
            }
            Npc llona = NpcQuery.newQuery().name("Ilona").results().first();
            if (llona != null) {
                llona.interact("Untie");
                delay(2000);
            }
        } else if (value == 55) {
            if (Interfaces.isOpen(1184)) {

            }
            if (isOpen()) {
                if (Dialog.hasOption("I'm ready for my reward.")) {
                    interact("I'm ready for my reward.");
                    return;
                }
                Dialog.select();
                return;
            }
        }
    }*/
