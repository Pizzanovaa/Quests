package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.queries.results.EntityResultSet;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import static net.botwithus.rs3.script.Execution.delay;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;

public class KiliRowIV {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(1146, 1805, 1);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate ritualcord = new Coordinate(1038, 1772, 1);
    static Area.Circular ritualarea = new Area.Circular(ritualcord, 20);
    static Coordinate ankoucord = new Coordinate(3159, 3743, 0);
    static Area.Circular ankouarea = new Area.Circular(ankoucord, 25);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(53746);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Kili's Row IV");

            if (startarea.contains(player)) {
                talkToKili();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {

                case 5:
                Item item = InventoryItemQuery.newQuery().name("Soul urn").results().first();
                int progress = VarManager.getInvVarbit(item.getInventoryType().getId(), item.getSlot(), 53601); // Progress
                int needed = item.getConfigType().getIntParam(8941);
                

                if(!ritualarea.contains(player) && progress < needed)
                {
                    DebugScript.moveTo(ritualcord);
                }
                else if (ritualarea.contains(player) && progress < needed) {
                    SceneObject pedestal = SceneObjectQuery.newQuery().name("Pedestal (greater communion)").results().nearest();
                    EntityResultSet<Npc> elementIIglyph = NpcQuery.newQuery().name("Elemental II", "Elemental II (depleted)").results();
                    EntityResultSet<Npc> communeg1lyph = NpcQuery.newQuery().name("Commune I", "Commune I (depleted)").results();
                    EntityResultSet<Npc> commune2glyph = NpcQuery.newQuery().name("Commune II", "Commune II (depleted)").results();
                    EntityResultSet<Npc> candle = NpcQuery.newQuery().name("Basic ritual candle", String::contains).results();
                    
                    if (pedestal == null) {
                        
                        
                       placeFocus();
                    }

                    if(pedestal != null)
                    {
                        
                        if (elementIIglyph.size() < 2)
                            drawglyphselement();
                        else if (communeg1lyph.size() < 1)
                            drawglyphscommuneI();
                        else if (commune2glyph.size() < 2)
                            drawglyphscommuneII();
                        else if (candle.size() < 6) {
                            placelightsource();
                        } else {
                            
                            doRitual();
                        }
                    }
                }
                else if(progress == needed && Backpack.contains(55562) && !Backpack.contains(55563))
                {
                    if(!ankouarea.contains(player))
                    {
                        DebugScript.moveTo(ankoucord);
                    }
                    else
                    {
                        Npc ankou = NpcQuery.newQuery().name("Ankou").results().nearest();
                        Item ankouagitator = InventoryItemQuery.newQuery(93).ids(55562).results().first();
                        GroundItem item1 = GroundItemQuery.newQuery().ids(55563).results().nearest();
                        if(ankou != null && ankouagitator != null && !Client.getLocalPlayer().inCombat() && item1 == null)
                        {
                            boolean success = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, ankouagitator.getSlot(), 96534533);
                            if(success)
                            {
                                delay(RandomGenerator.nextInt(600, 800));
                                //MiniMenu.interact(SelectableAction.SELECT_NPC.getType(), cat1.getId(), 0, 0)
                                //ankou.interact(SelectableAction.SELECT_NPC.getType());
                                MiniMenu.interact(SelectableAction.SELECT_NPC.getType(), ankou.getId(), 0, 0);
                                Execution.delayUntil(10000, () -> !Client.getLocalPlayer().isMoving());
                                delay(RandomGenerator.nextInt(600, 800));
                                ankou.interact(NPCAction.NPC1);
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                        

                        if(item1 != null && !Backpack.contains(55563))
                        {
                            item1.interact("Take");
                            delay(RandomGenerator.nextInt(600, 1200));
                            if (Interfaces.isOpen(1622)) {
                                Item items = InventoryItemQuery.newQuery(773).ids(55563).results().first();
                                if (items != null) {
                                    println("Item Slot" + items.getSlot());
                                    MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                    delay(RandomGenerator.nextInt(600, 1200));
                                    return;
                                }
                            }
                        }

                    }
                }
                else if(progress == needed && Backpack.contains(55563))
                {
                    if(!startarea.contains(player))
                    {
                        DebugScript.moveTo(startcord);
                    }
                    else
                    {
                        talkToKili();
                    }
                }
                break;

            }
        }
    }

    public static void doRitual() {
        if (VarManager.getVarpValue(10937) == 0) {
            Npc depleted = NpcQuery.newQuery().name("(depleted)", String::contains).results().nearest();
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
        .filter(npc -> !npc.getName().equals("Elemental II") && !npc.getName().equals("Commune II") && !npc.getName().equals("Commune I"))
        .findFirst()
        .orElse(null);
        EntityResultSet<Npc> elementII = NpcQuery.newQuery().name("Elemental II", "Elemental II (depleted)").results();
        //Npc elementII = NpcQuery.newQuery().results().stream().filter(npc -> npc.getName().equals("Elemental II") || npc.getName().equals("Elemental II (depleted)")).findFirst().orElse(null);
        if (!Client.getLocalPlayer().isMoving()) {
            if (glyph != null && elementII.size() < 2) {
                glyph.interact("Draw glyph");
                delay(RandomGenerator.nextInt(1250, 2000));
                ScriptConsole.println("Interface is open" + Interfaces.isOpen(1371));
                Execution.delayUntil(5000, () -> Interfaces.isOpen(1371));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 5, 89849878);
                delay(RandomGenerator.nextInt(1250, 2000));
                if (Interfaces.isOpen(1370)) {
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(1250, 2000));
                }

            }
        }
    }


    public static void drawglyphscommuneI() {
        Npc glyph1 = NpcQuery.newQuery()
                .option("Draw glyph")
                .results()
                .stream()
                .filter(npc -> !npc.getName().equals("Elemental II") && !npc.getName().equals("Commune II") && !npc.getName().equals("Commune I"))
                .findFirst()
                .orElse(null);

        EntityResultSet<Npc> communeI = NpcQuery.newQuery().name("Commune I", "Commune I (depleted)").results();        
        if (!Client.getLocalPlayer().isMoving()) {
            if (glyph1 != null && communeI.size() < 1) {
                glyph1.interact("Draw glyph");
                delay(RandomGenerator.nextInt(1250, 2000));
                ScriptConsole.println("Interface is open" + Interfaces.isOpen(1371));
                Execution.delayUntil(5000, () -> Interfaces.isOpen(1371));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 25, 89849878);
                delay(RandomGenerator.nextInt(1250, 2000));
                if (Interfaces.isOpen(1370)) {
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(1250, 2000));
                }

            }
        }

    }

    public static void drawglyphscommuneII() {
        Npc glyph1 = NpcQuery.newQuery()
                .option("Draw glyph")
                .results()
                .stream()
                .filter(npc -> !npc.getName().equals("Elemental II") && !npc.getName().equals("Commune I") && !npc.getName().equals("Commune II"))
                .findFirst()
                .orElse(null);
        EntityResultSet<Npc> communeII = NpcQuery.newQuery().name("Commune II", "Commune II (depleted)").results();
        if (!Client.getLocalPlayer().isMoving()) {
            if (glyph1 != null && communeII.size() < 2) {
                glyph1.interact("Draw glyph");
                delay(RandomGenerator.nextInt(1250, 2000));
                ScriptConsole.println("Interface is open" + Interfaces.isOpen(1371));
                Execution.delayUntil(5000, () -> Interfaces.isOpen(1371));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 29, 89849878);
                delay(RandomGenerator.nextInt(1250, 2000));
                if (Interfaces.isOpen(1370)) {
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(1250, 2000));
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
    
    public static void placeFocus() {
        SceneObject middle = SceneObjectQuery.newQuery().name("Pedestal",String::contains).results().first();
        if (middle != null) {
            if(Interfaces.isOpen(1224))
            {
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1,22,80216098);
                delay(RandomGenerator.nextInt(900, 1250));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1,-1,80216108);
                delay(RandomGenerator.nextInt(900, 1250));
            }else {

                if (middle.getOptions().contains("Replace focus")) {
                    middle.interact("Replace focus");
                } else {
                    middle.interact("Place focus");
                }

                delay(RandomGenerator.nextInt(600, 800));
                Execution.delayUntil(2000, () -> Interfaces.isOpen(1224));
            }
        }else{
            ScriptConsole.println("Middle null");
        }
    }

}
