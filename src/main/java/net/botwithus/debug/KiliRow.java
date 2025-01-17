package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.queries.results.EntityResultSet;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

public class KiliRow {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(1146, 1805, 1);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate ritualcord = new Coordinate(1038, 1772, 1);
    static Area.Circular ritualarea = new Area.Circular(ritualcord, 20);
    static Coordinate chickenpencord = new Coordinate(3230, 3296, 0);
    static Area.Circular chickenpenarea = new Area.Circular(chickenpencord, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(53496);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Kili Row");

            if (startarea.contains(player)) {
                talkToKili();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {

                case 5, 30, 20, 10:
                    talkToKili();
                    break;
                case 15:
                    if (ritualarea.contains(player) && !Backpack.contains("Kili's tools (ensouled)")) {
                        SceneObject pedestal = SceneObjectQuery.newQuery().name("Pedestal (ensoul material)").results().nearest();
                        EntityResultSet<Npc> elementglyph = NpcQuery.newQuery().name("Elemental I").results();
                        EntityResultSet<Npc> changeglyph = NpcQuery.newQuery().name("Change I").results();
                        EntityResultSet<Npc> candle = NpcQuery.newQuery().name("Basic ritual candle", String::contains).results();
                        if (pedestal == null) {
                            if (Backpack.contains("Kili's tools")) {
                                setpedestalfocus();
                            } else {
                                SceneObject RitualChest = SceneObjectQuery.newQuery().name("Ritual chest").results().nearest();
                                if (RitualChest != null && !Backpack.contains("Kili's tools (ensouled)")) {
                                    if (Interfaces.isOpen(168)) {
                                        println("Inside the Inventory");
                                        Item essence = InventoryItemQuery.newQuery(941).ids(55221).results().first();
                                        if (essence != null) {
                                            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, essence.getSlot(), 11010083);
                                            delay(RandomGenerator.nextInt(600, 1200));
                                        }
                                    } else {
                                        RitualChest.interact("Open");
                                        delay(RandomGenerator.nextInt(600, 800));
                                    }
                                }
                            }
                        } else {
                            if (elementglyph.size() < 2)
                                drawglyphselement();
                            else if (changeglyph.size() < 1)
                                drawglyphschange();
                            else if (candle.size() < 4) {
                                placelightsource();
                            } else {
                                doRitual();
                            }
                        }
                    } else {
                        if (Backpack.contains("Kili's tools (ensouled)")) {
                            if (startarea.contains(player)) {
                                talkToKili();
                            } else {
                                DebugScript.moveTo(startcord);
                            }
                        } else {
                            DebugScript.moveTo(ritualcord);
                        }
                    }
                    break;
                case 25:
                    Item item = InventoryItemQuery.newQuery().name("Soul urn").results().first();
                    if (item != null) {
                        int progress = VarManager.getInvVarbit(item.getInventoryType().getId(), item.getSlot(), 53600); // Progress
                        int needed = item.getConfigType().getIntParam(8940); // Amount needed
                        if (progress < needed) {
                            if (chickenpenarea.contains(player)) {
                                Npc chicken = NpcQuery.newQuery().name("Chicken").health(1, 2000).results().nearest();
                                if (chicken != null) {
                                    chicken.interact("Attack");
                                    delay(RandomGenerator.nextInt(600,1200));;
                                }
                            } else {
                                DebugScript.moveTo(chickenpencord);
                            }
                        } else {
                            if (startarea.contains(player)) {
                                talkToKili();
                            } else {
                                DebugScript.moveTo(startcord);
                            }
                        }
                    }
                    break;
                case 35:
                    ScriptConsole.println("Quest complete.");
                    break;


            }
        }
    }

    public static void doRitual() {
        if (VarManager.getVarpValue(10937) == 0) {
            SceneObject depleted = SceneObjectQuery.newQuery().name("(depleted)", String::contains).results().nearest();
            if (depleted != null) {
                SceneObject middle = SceneObjectQuery.newQuery().name("Pedestal", String::contains).results().first();
                if (middle != null) {
                    middle.interact("Repair all");
                    delay(RandomGenerator.nextInt(1200, 2000));
                }
            }
        }
        if (Client.getLocalPlayer().getAnimationId() == -1 && !Client.getLocalPlayer().isMoving()) {
            SceneObject ritual = SceneObjectQuery.newQuery().option("Start ritual").results().first();
            if (ritual != null) {
                ritual.interact("Start ritual");
                delay(RandomGenerator.nextInt(600, 800));
            }
        }

        if(VarManager.getVarpValue(10937) > 0 && Client.getLocalPlayer().getAnimationId() == -1 && !Client.getLocalPlayer().isMoving())
        {
            SceneObject ritual = SceneObjectQuery.newQuery().name("Pedestal", String::contains).results().first();
            if (ritual != null) {
                ritual.interact("Continue ritual");
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
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
        }
    }

    public static void drawglyphselement() {
        Npc glyph = NpcQuery.newQuery()
            .option("Draw glyph")
            .results()
            .stream()
            .filter(npc -> !npc.getName().equals("Elemental I") && !npc.getName().equals("Commune I"))
            .findFirst()
            .orElse(null);
        EntityResultSet<Npc> elementI = NpcQuery.newQuery().name("Elemental I", "Elemental I (depleted)").results();
        //Npc elementII = NpcQuery.newQuery().results().stream().filter(npc -> npc.getName().equals("Elemental II") || npc.getName().equals("Elemental II (depleted)")).findFirst().orElse(null);
        if (!Client.getLocalPlayer().isMoving()) {
            if (glyph != null && elementI.size() < 2) {
                glyph.interact("Draw glyph");
                delay(RandomGenerator.nextInt(1250, 2000));
                ScriptConsole.println("Interface is open" + Interfaces.isOpen(1371));
                Execution.delayUntil(5000, () -> Interfaces.isOpen(1371));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 1, 89849878);
                delay(RandomGenerator.nextInt(1250, 2000));
                if (Interfaces.isOpen(1370)) {
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(1250, 2000));
                }

                }
        }
    }
    public static void drawglyphschange() {
            Npc glyph1 = NpcQuery.newQuery()
            .option("Draw glyph")
            .results()
            .stream()
            .filter(npc -> !npc.getName().equals("Change I") && !npc.getName().equals("Elemental I"))
            .findFirst()
            .orElse(null);
        EntityResultSet<Npc> changeI = NpcQuery.newQuery().name("Change I", "Change I (depleted)").results();
        if (!Client.getLocalPlayer().isMoving()) {
            if (glyph1 != null && changeI.size() < 2) {
                glyph1.interact("Draw glyph");
                delay(RandomGenerator.nextInt(1250, 2000));
                ScriptConsole.println("Interface is open" + Interfaces.isOpen(1371));
                Execution.delayUntil(5000, () -> Interfaces.isOpen(1371));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 13, 89849878);
                delay(RandomGenerator.nextInt(1250, 2000));
                if (Interfaces.isOpen(1370)) {
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(1250, 2000));
                }

            }
        }

    }

    public static void setpedestalfocus() {
        SceneObject pedestal = SceneObjectQuery.newQuery().name("Pedestal", String::contains).results().nearest();
        if (pedestal != null) {
            if (Interfaces.isOpen(1224)) {
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 60, 80216098);
                delay(RandomGenerator.nextInt(600,1200));;
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 80216108);
                delay(RandomGenerator.nextInt(600,1200));;
            } else {
                if (pedestal.getOptions().contains("Replace focus")) {
                    pedestal.interact("Replace focus");
                } else {
                    pedestal.interact("Place focus");
                }
            }
        }
    }

    public static void talkToKili() {
        Npc kili = NpcQuery.newQuery().name("Kili").results().nearest();
        if (kili != null) {
            kili.interact("Talk to");
            delay(RandomGenerator.nextInt(2000,2400));
        }
    }

}