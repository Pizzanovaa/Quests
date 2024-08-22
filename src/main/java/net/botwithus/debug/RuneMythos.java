package net.botwithus.debug;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
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

import static net.botwithus.debug.DebugScript.currentQuest;
import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

public class RuneMythos {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(1037, 1763, 1);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate darkportal = new Coordinate(1165, 1830, 1);
    static Area.Circular darkportalarea = new Area.Circular(darkportal, 20);


    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(53546);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Rune Mythos");

            if (startarea.contains(player)) {
                talktoMalignius();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 15:
                    SceneObject lessessence = SceneObjectQuery.newQuery().name("Pedestal (lesser essence)").results().nearest();
                    EntityResultSet<Npc> elementglyph = NpcQuery.newQuery().name("Elemental I").results();
                    EntityResultSet<Npc> changeglyph = NpcQuery.newQuery().name("Change I").results();
                    EntityResultSet<Npc> candle = NpcQuery.newQuery().name("Basic ritual candle").results();
                    if(lessessence ==null) {
                        placeFocus();
                    }
                    if(lessessence != null)
                    {
                        if(elementglyph.size() <2)
                            drawglyphselement();
                        else if (changeglyph.size() <2)
                            drawglyphschange();
                        else if(candle.size() <4)
                        {
                            placelightsource();
                        }
                        else if(elementglyph.size() ==2 && changeglyph.size() ==2 && candle.size() ==4 )
                        {
                            doRitual();
                        }
                    }
                break;
                case 20:
                    SceneObject RitualChest = SceneObjectQuery.newQuery().name("Ritual chest").results().nearest();
                    if(RitualChest !=null && !Backpack.contains("Impure essence"))
                    {
                        RitualChest.interact("Open");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Interfaces.isOpen(168))
                        {
                            println("Inside the Inventory");
                            Item essence = InventoryItemQuery.newQuery(941).ids(55667).results().first();
                            if(essence !=null)
                            {
                                println("essence slot" + essence.getSlot());
                                println("essence " + essence.getName());
                                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, essence.getSlot(),11010083);
                                delay(RandomGenerator.nextInt(600, 1200));
                            }
                        }
                    }
                    if(Backpack.contains("Impure essence"))
                    {
                        if(!darkportalarea.contains(player) && player.getCoordinate().getRegionId() != 5150)
                        {
                            DebugScript.moveTo(darkportal);
                        }
                        else if(darkportalarea.contains(player) && player.getCoordinate().getRegionId() != 5150) {
                            SceneObject Portal = SceneObjectQuery.newQuery().name("Dark portal").option("Enter").results().nearest();
                            if (Portal != null) {
                                Portal.interact("Enter");
                            }
                        }
                        if (player.getCoordinate().getRegionId() == 5150)
                            {
                                SceneObject Spirit = SceneObjectQuery.newQuery().name("Spirit altar").option("Craft runes").results().nearest();


                                if (Spirit != null) {
                                    if(!Interfaces.isOpen(1184))
                                    {
                                        println("Interact with Runes Altar" + Spirit.interact("Craft runes"));
                                        Execution.delay(2000);
                                    }
                                    else {
                                        MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77594639);
                                        delay(RandomGenerator.nextInt(900, 1200));
                                    }
                                }
                                raisal();

                            }
                    }
                    break;
                case 22:
                    SceneObject Spirit = SceneObjectQuery.newQuery().name("Spirit altar").option("Craft runes").results().nearest();
                    if (Spirit != null && !Backpack.contains("Spirit runes")) {
                        println("Interact with Runes Altar" + Spirit.interact("Craft runes"));
                        Execution.delay(2000);
                    }

                    break;
                case 25:

                    if (startarea.contains(player)) {
                        talktoMalignius();
                    } else {
                        DebugScript.moveTo(startcord);
                    }

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
    public static void raisal() {
        Npc malgi = NpcQuery.newQuery().name("Rasial, the First Necromancer").results().first();
        if (malgi != null) {
            malgi.interact("Talk to");
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

    public static void placeFocus() {
        SceneObject middle = SceneObjectQuery.newQuery().name("Pedestal").results().first();
        if (middle != null) {
            middle.interact("Place focus");
            delay(RandomGenerator.nextInt(600, 800));
            Execution.delayUntil( 2000, () -> Interfaces.isOpen(1224));
            if(Interfaces.isOpen(1224))
            {
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1,13,80216098);
                delay(RandomGenerator.nextInt(900, 1250));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1,-1,80216108);
                delay(RandomGenerator.nextInt(900, 1250));
            }
        }
    }

    public static void drawglyphselement() {
        Npc glyph = NpcQuery.newQuery().name("Glyph spot").option("Draw glyph").results().nearest();
        if (!Client.getLocalPlayer().isMoving()) {
            if (glyph != null) {
                //glyph.interact("Draw glyph");

                glyph.interact("Draw glyph");
                delay(RandomGenerator.nextInt(1250, 2000));
                println("Interface is open" + Interfaces.isOpen(1371));
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
            Npc glyph1 = NpcQuery.newQuery().name("Glyph spot").option("Draw glyph").results().nearest();
            if (!Client.getLocalPlayer().isMoving()) {
                if (glyph1 != null) {
                    //glyph.interact("Draw glyph");

                    glyph1.interact("Draw glyph");
                    delay(RandomGenerator.nextInt(1250, 2000));
                    println("Interface is open" + Interfaces.isOpen(1371));
                    Execution.delayUntil(5000, () -> Interfaces.isOpen(1371));
                    MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1,13, 89849878);
                    delay(RandomGenerator.nextInt(1250, 2000));
                    if(Interfaces.isOpen(1370))
                    {
                        MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                        delay(RandomGenerator.nextInt(1250, 2000));
                    }

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
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
        }
    }

}
