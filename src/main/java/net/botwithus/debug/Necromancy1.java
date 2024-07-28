package net.botwithus.debug;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

public class Necromancy1 {


    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate draynorlode = new Coordinate(3104, 3310, 0);
    static Area.Circular draynorloadarea = new Area.Circular(draynorlode, 10);

    public static void quest2() {
        int QuestVarp = VarManager.getVarpValue(10982);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Necormancy!");

            if (draynorloadarea.contains(player)) {
                interactWithDoor();
            } else {
                DebugScript.moveTo(draynorlode);
            }

        } else {
            if (draynorloadarea.contains(player)) {
                interactWithDoor();
            }
            switch (QuestVarp) {
                case 178:
                    println("178");
                    cleardust();
                    break;
                case 562:
                    placeFocus();
                    break;
                case 690:
                    placelightsource();
                    break;
                case 818, 112188:
                    talktoMalignius();
                    break;
                case 946:
                    drawglyphs();
                    break;
                case 1074:
                    RepairGlyph();
                    break;
                case 1330:
                    doRitual();
                    break;
                case 1596:
                    if (!Interfaces.isOpen(1222)) {
                        clickwellofsouls();
                    }
                    break;
                case 3644:
                    if (!Interfaces.isOpen(1222)) {
                        clickwellofsouls();
                    }else {
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 1, 80085019);
                    }
                case 9788:
                    if (Backpack.contains("Death guard")) {
                        Backpack.interact("Death guard", "Wield");
                        delay(RandomGenerator.nextInt(600, 800));
                        return;
                    }else {
                        killTroll1();
                    }
                    break;
                case 75324:
                    killTroll2();
                    break;
                case 110140:
                    if (!Interfaces.isOpen(1222)) {
                        clickwellofsouls();
                        delay(RandomGenerator.nextInt(600, 800));
                    } else {
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 1, 80085019);
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 80085046);
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 80085066);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    break;
                case 114246:
                    if(Interfaces.isOpen(955)){
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 62586895);
                        delay(RandomGenerator.nextInt(600, 800));

                    }
                case 245138:
                    println("Quest complete");
                    break;
            }

        }
    }


    public static void interactWithDoor() {
        SceneObject door = SceneObjectQuery.newQuery().name("Underworld portal").results().first();
        if (door != null) {
            door.interact("Enter");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void placeFocus() {
        SceneObject middle = SceneObjectQuery.newQuery().name("Pedestal").results().first();
        if (middle != null) {
            middle.interact("Place focus");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void killTroll1() {
        if (!Client.getLocalPlayer().inCombat()) {
            Npc troll1 = NpcQuery.newQuery().name("Ghost troll lout").results().first();
            if (troll1 != null) {
                troll1.interact("Attack");
                delay(RandomGenerator.nextInt(600, 800));
                return;
            }
        }

    }

    public static void killTroll2() {

        Npc troll1 = NpcQuery.newQuery().name("Ghost troll brute").results().first();
        if (troll1 != null) {
            troll1.interact("Attack");
            delay(RandomGenerator.nextInt(600, 800));
            return;
        }


    }

    public static void clickwellofsouls() {
        SceneObject souls = SceneObjectQuery.newQuery().name("Well of Souls").results().first();
        if (souls != null) {
            souls.interact("Manage");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void doRitual() {
        if (Client.getLocalPlayer().getAnimationId() == -1 && !Client.getLocalPlayer().isMoving()) {
            SceneObject ritual = SceneObjectQuery.newQuery().option("Start ritual").results().first();
            if (ritual != null) {
                ritual.interact("Start ritual");
                delay(RandomGenerator.nextInt(600, 800));
            }
        }
    }

    public static void talktoMalignius() {
        Npc malgi = NpcQuery.newQuery().name("Malignius Mortifer").results().first();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void cleardust() {
        Npc dust = NpcQuery.newQuery().option("Clear").name("Mound of dust").results().first();
        if (dust != null) {
            if (!Client.getLocalPlayer().isMoving() && Client.getLocalPlayer().getAnimationId() == -1) {
                dust.interact("Clear");
                delay(RandomGenerator.nextInt(600, 800));
            }
        }
    }

    public static void placelightsource() {
        Npc light = NpcQuery.newQuery().option("Place light source").name("Light source spot").results().first();
        if (light != null) {
            if (!Client.getLocalPlayer().isMoving() && Client.getLocalPlayer().getAnimationId() == -1) {
                delay(RandomGenerator.nextInt(600, 800));
                if (!Interfaces.isOpen(1370)) {
                    light.interact("Place light source");
                    delay(RandomGenerator.nextInt(600, 800));
                } else {
                    //add
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
        }
    }


    public static void drawglyphs() {
        Npc glyph = NpcQuery.newQuery().name("Glyph spot").option("Draw glyph").results().nearest();
        if (!Client.getLocalPlayer().isMoving()) {
                if (glyph != null) {
                    glyph.interact("Draw glyph");
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
        }
    public static void RepairGlyph() {
        Npc glyph = NpcQuery.newQuery().name("Elemental I (depleted)").results().first();
        if (glyph != null) {
            glyph.interact("Repair");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    }

