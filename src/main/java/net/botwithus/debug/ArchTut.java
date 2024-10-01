package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
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

public class ArchTut {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3384, 3392, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate benchcord = new Coordinate(3357, 3395, 0);
    static Area.Circular bencharea = new Area.Circular(benchcord, 10);
    static Coordinate veculciacord = new Coordinate(3342, 3384, 0);
    static Area.Circular veculciaarea = new Area.Circular(veculciacord, 10);
    static Coordinate monolithcord = new Coordinate(3364, 3382, 0);
    static Area.Circular monolithaarea = new Area.Circular(monolithcord, 10);
    static Coordinate mapcord = new Coordinate(3326, 3376, 0);
    static Area.Circular maparea = new Area.Circular(mapcord, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(46463);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Arch Tutorial");

            if (startarea.contains(player)) {
                talktoGuildMaster();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 5, 35, 45:
                    talktoGuildMaster();
                    break;
                case 10:
                    if (Backpack.contains("Bronze mattock")) {
                        Backpack.interact("Bronze mattock", "Add to tool belt");
                        delay(RandomGenerator.nextInt(600, 1200));
                        ;
                        return;
                    }
                    break;
                case 15:

                    break;
                case 20:
                    if (Client.getLocalPlayer().getAnimationId() != -1) {
                        return;
                    }
                    SceneObject soil = SceneObjectQuery.newQuery().name("Senntisten soil").results().nearest();
                    if (soil != null) {
                        soil.interact("Uncover");
                        delay(RandomGenerator.nextInt(600, 1200));
                        ;
                    }
                    break;
                case 25:
                    //46464 Arch tut 0/25 varbit
                    if (Client.getLocalPlayer().getAnimationId() != -1) {
                        return;
                    }
                    SceneObject soilexc = SceneObjectQuery.newQuery().name("Centurion remains").results().nearest();
                    if (soilexc != null) {
                        soilexc.interact("Excavate");
                        delay(RandomGenerator.nextInt(600, 1200));
                        ;
                    }
                    break;
                case 30:
                    if (Backpack.contains(49741)) {
                        Backpack.interact(49741, "Inspect");
                        delay(RandomGenerator.nextInt(1200, 1800));;

                    }
                    talktoGuildMaster();
                    delay(RandomGenerator.nextInt(600, 1200));
                    ;
                    break;
                case 40:
                    if (Interfaces.isOpen(1370)) {
                        MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                        delay(RandomGenerator.nextInt(600, 1200));
                        ;
                        return;
                    }
                    if (Client.getLocalPlayer().getAnimationId() != -1) {
                        return;
                    }
                    SceneObject mesh = SceneObjectQuery.newQuery().name("Mesh").results().nearest();
                    if (mesh != null) {
                        mesh.interact("Screen");
                        delay(RandomGenerator.nextInt(600, 1200));
                        ;
                    }
                    break;
                case 50:

                    if (bencharea.contains(player)) {
                        if (Interfaces.isOpen(660)) {
                            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 43253774);
                            delay(RandomGenerator.nextInt(600, 1200));
                            ;
                            return;
                        }
                        SceneObject storage = SceneObjectQuery.newQuery().name("Material storage container").results().nearest();
                        if (storage != null) {
                            storage.interact("Store");
                            delay(RandomGenerator.nextInt(600, 1200));
                            ;
                        }
                    } else {
                        DebugScript.moveTo(benchcord);
                    }

                    break;
                case 55:
                    if (Client.getLocalPlayer().getAnimationId() != -1) {
                        return;
                    }
                    if (bencharea.contains(player)) {
                        if (Interfaces.isOpen(1370)) {
                            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                            delay(RandomGenerator.nextInt(600, 1200));
                            ;
                            return;
                        }
                        SceneObject bench = SceneObjectQuery.newQuery().name("Archaeologist's workbench").results().nearest();
                        if (bench != null) {
                            bench.interact("Restore");
                            delay(RandomGenerator.nextInt(600, 1200));
                            ;
                        }
                    } else {
                        DebugScript.moveTo(benchcord);
                    }
                    break;
                case 60:
                    if (startarea.contains(player)) {
                        talktoGuildMaster();
                    } else {
                        DebugScript.moveTo(startcord);
                    }
                    break;
                case 65:
                    if (veculciaarea.contains(player)) {
                        if (Interfaces.isOpen(656)) {
                            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 0, 42991641);
                            delay(RandomGenerator.nextInt(600, 1200));
                            ;
                            return;
                        }
                        Npc velucia = NpcQuery.newQuery().name("Velucia").results().nearest();
                        if (velucia != null) {
                            velucia.interact("Talk to");
                            delay(RandomGenerator.nextInt(600, 1200));
                            ;
                        }
                    } else {
                        DebugScript.moveTo(veculciacord);
                    }
                    break;
                case 75:
                    if (startarea.contains(player)) {
                        talktoGuildMaster();
                    } else {
                        DebugScript.moveTo(startcord);
                    }
                    break;
                case 80:
                    if (monolithaarea.contains(player)) {
                        SceneObject monolith = SceneObjectQuery.newQuery().name("Mysterious monolith").results().nearest();
                        if (monolith != null) {
                            monolith.interact("Interact");
                            delay(RandomGenerator.nextInt(600, 1200));
                            ;
                        }
                    } else {
                        DebugScript.moveTo(monolithcord);
                    }
                    break;
                case 85:
                    if (Interfaces.isOpen(691)) {
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 45285447);
                        delay(RandomGenerator.nextInt(1200, 1800));;
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 45285433);
                        delay(RandomGenerator.nextInt(1200, 1800));;
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 45285526);
                        delay(RandomGenerator.nextInt(1200, 1800));;
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 45285522);
                    } else {
                        SceneObject monolith = SceneObjectQuery.newQuery().name("Mysterious monolith").results().nearest();
                        if (monolith != null) {
                            monolith.interact("Manage powers");
                            delay(RandomGenerator.nextInt(600, 1200));
                            ;
                        }
                    }
                    break;
                case 90:
                    talktoGuildMaster();
                    break;
                case 95:
                    if (maparea.contains(player)) {
                        talktoGuildMaster();
                    } else {
                        DebugScript.moveTo(mapcord);
                    }
                    break;
                case 96:
                    if (Interfaces.isOpen(1594)) {
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 0, 104464403);
                        delay(RandomGenerator.nextInt(600, 1200));
                        ;
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 104464438);
                        delay(RandomGenerator.nextInt(600, 1200));
                        ;
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 104464444);
                        delay(RandomGenerator.nextInt(600, 1200));
                        ;
                        return;
                    }
                    Npc ezreal = NpcQuery.newQuery().name("Ezreal").results().nearest();
                    if (ezreal != null) {
                        ezreal.interact("Talk to");
                        delay(RandomGenerator.nextInt(600, 1200));
                        ;
                    }
                    break;
                case 97:
                    talktoGuildMaster();
            }

        }
    }

    public static void talktoGuildMaster() {
        Npc guildmaster = NpcQuery.newQuery().name("Acting Guildmaster", String::contains).results().nearest();
        if (guildmaster != null) {
            guildmaster.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 1200));
            ;
        }
    }
}


