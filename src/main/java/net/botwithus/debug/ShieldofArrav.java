package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

public class ShieldofArrav {

    // Requirements 20 Gold
    static int charlietalk =0;
    static int khatrinetalk =0;
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3208, 3494, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate Baraek = new Coordinate(3217, 3434, 0);
    static Area.Circular Baraekarea = new Area.Circular(Baraek, 10);
    static Coordinate phoneixgang = new Coordinate(3244, 9782, 0);
    static Area.Circular phoneixgangarea = new Area.Circular(phoneixgang, 10);
    static Coordinate bluemoon = new Coordinate(3220, 3396, 0);
    static Area.Circular bluemoonarea = new Area.Circular(bluemoon, 10);
    static Coordinate weaponrackph = new Coordinate(3235, 9765, 0);
    static Area.Circular weaponrackpharea = new Area.Circular(weaponrackph, 10);
    static Coordinate phbowtake = new Coordinate(3248, 3385, 1);
    static Area.Circular phbowtakearea = new Area.Circular(phbowtake, 10);
    static Coordinate charlie = new Coordinate(3210, 3390, 0);
    static Area.Circular charliearea = new Area.Circular(charlie, 10);
    static Coordinate Katrine = new Coordinate(3185, 3385, 0);
    static Area.Circular Katrinearea = new Area.Circular(Katrine, 10);
    static Coordinate Katrinecup = new Coordinate(3186, 3389, 1);
    static Area.Circular Katrinecuparea = new Area.Circular(Katrinecup, 10);
    static Coordinate kingroald = new Coordinate(3221, 3474, 0);
    static Coordinate VarrockLoad = new Coordinate(3213, 3376, 0);
    static Area.Circular kingroaldarea = new Area.Circular(kingroald, 10);

    static Coordinate bookcasecord = new Coordinate(3212,3493,0);


    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2738);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Shield of Arrav");

            if (startarea.contains(player)) {
                reldo();

            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 1:
                    SceneObject bookcase = SceneObjectQuery.newQuery().name("Bookcase").hidden(false).id(2402).results().nearestTo(bookcasecord);

                    if (bookcase !=null && !Backpack.contains(757))
                    {
                        bookcase.interact("Search");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else
                    {
                        Component manual = ComponentQuery.newQuery(1473).item(757).results().stream().findFirst().orElse(null);
                        if(manual != null)
                        {
                            manual.interact("Read");
                            delay(RandomGenerator.nextInt(1100, 1500));
                        }
                    }
                    break;
                case 2:
                    if (startarea.contains(player)) {
                        reldo();

                    } else {
                        DebugScript.moveTo(startcord);
                    }
                    break;
                case 3:
                    if (Baraekarea.contains(player)) {
                        Baraek();

                    } else {
                        DebugScript.moveTo(Baraek);
                    }
                    break;
                case 4:
                    if (phoneixgangarea.contains(player)) {
                        Straven();

                    } else {
                        DebugScript.moveTo(phoneixgang);
                    }
                    break;
                case 5:
                    if((!bluemoonarea.contains(player)) && !Backpack.contains(761))
                    {
                        DebugScript.moveTo(bluemoon);
                    }
                    else {
                        Npc jonny = NpcQuery.newQuery().name("Jonny the beard").byType(645).results().nearest();
                        GroundItem item = GroundItemQuery.newQuery().name("Intel report").results().nearest();
                        if(item == null && !Backpack.contains(761))
                        {
                            if (jonny != null) {
                                println("Found Jonny");
                                if (!Client.getLocalPlayer().hasTarget()) {
                                    jonny.interact("Attack");
                                    delay(RandomGenerator.nextInt(600, 800));
                                    return;
                                }
                            }
                        }
                        else if (item != null) { // Take item off the ground.
                            println(" Taking item" + item.interact("Take"));
                            delay(RandomGenerator.nextInt(600, 1200));
                            if (Interfaces.isOpen(1622)) {
                                Item items = InventoryItemQuery.newQuery(773).ids(761).results().first();
                                if (items != null) {
                                    println("Item Slot" + items.getSlot());
                                    MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                    delay(RandomGenerator.nextInt(600, 1200));
                                    return;
                                }
                            }
                        }
                        else if(Backpack.contains(761))
                        {
                            if (!phoneixgangarea.contains(player)) {
                                DebugScript.moveTo(phoneixgang);
                            } else {
                                Straven();
                            }
                        }
                    }
                    break;
                case 6:
                    int QuestVarp2 = VarManager.getVarpValue(2739);
                    if (QuestVarp2 == 0) {
                        if (!weaponrackpharea.contains(player) && !Backpack.contains(763) && khatrinetalk == 0) {
                            DebugScript.moveTo(weaponrackph);
                        } else if (!Backpack.contains(763) && weaponrackpharea.contains(player)) {
                            SceneObject chestph = SceneObjectQuery.newQuery().name("Chest").hidden(false).results().nearest();
                            if (chestph != null) {
                                chestph.interact("Open");
                                delay(RandomGenerator.nextInt(600, 800));
                                chestph.interact("Search");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (Backpack.contains(763)) {
                            InventoryItemQuery bow = InventoryItemQuery.newQuery(93).ids(767);
                            GroundItem item = GroundItemQuery.newQuery().ids(767).results().nearest();
                            var resultbow = bow.results();

                            if (!phbowtakearea.contains(player) && resultbow.size() < 2) {
                                DebugScript.moveTo(phbowtake);
                            } else if (resultbow.size() <= 2) {
                                if (item != null) { // Take item off the ground.
                                    println(" Taking item" + item.interact("Take"));
                                    delay(RandomGenerator.nextInt(600, 1200));
                                    if (Interfaces.isOpen(1622)) {
                                        Item items = InventoryItemQuery.newQuery(773).ids(767).results().first();
                                        if (items != null) {
                                            println("Item Slot" + items.getSlot());
                                            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                            delay(RandomGenerator.nextInt(600, 1200));
                                            return;
                                        }
                                    }
                                }

                            } else if (resultbow.size() >= 2 && charlietalk < 1) {
                                if (!charliearea.contains(player)) {
                                    DebugScript.moveTo(charlie);
                                } else {
                                    charlie();
                                }
                            }
                        }
                    } else if ((QuestVarp2 == 1 || QuestVarp2 == 2) && !Backpack.contains(11164)) {
                        if (!Katrinearea.contains(player)) {
                            DebugScript.moveTo(Katrine);
                        } else
                            Katrine();
                    } else if (QuestVarp2 == 3 && !Backpack.contains(11164)) {
                        if (!Katrinecuparea.contains(player)) {
                            DebugScript.moveTo(Katrinecup);
                        } else if (!Backpack.contains(765)) {
                            SceneObject cuboard = SceneObjectQuery.newQuery().name("Cupboard").hidden(false).results().nearest();
                            if (cuboard != null) {
                                cuboard.interact("Open");
                                delay(RandomGenerator.nextInt(600, 800));
                                cuboard.interact("Search");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        } else if (Backpack.contains(765) && Backpack.contains(763)) {
                            Item backpackitem = InventoryItemQuery.newQuery(93).ids(763).results().first(); // Paper
                            Item targetitem = InventoryItemQuery.newQuery(93).ids(765).results().first();

                            if (backpackitem != null && targetitem != null) { // Place paper into folder.
                                if (MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, backpackitem.getSlot(), 96534533)) {
                                    delay(RandomGenerator.nextInt(200, 400));
                                    MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, targetitem.getSlot(), 96534533);
                                    delay(RandomGenerator.nextInt(200, 400));
                                }
                        }
                        }
                    }
                        else if( Backpack.contains(11164)) {
                        if (Katrinecuparea.contains(player)) {
                            DebugScript.moveTo(VarrockLoad);
                            return;
                        } else {
                            if (!kingroaldarea.contains(player)) {
                                DebugScript.moveTo(kingroald);
                            } else {
                                kingRoald();
                            }
                        }

                    }

                    println("Varp" + QuestVarp);
                    break;
            }
        }
    }

    public static void reldo() {
        Npc npc = NpcQuery.newQuery().name("Reldo").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }
    public static void Baraek() {
        Npc npc = NpcQuery.newQuery().name("Baraek").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void Straven() {
        Npc npc = NpcQuery.newQuery().name("Straven").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }
    public static void charlie() {
        Npc npc = NpcQuery.newQuery().name("Charlie the Tramp").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void Katrine() {
        Npc npc = NpcQuery.newQuery().name("Katrine").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }
    public static void kingRoald() {
        Npc npc = NpcQuery.newQuery().name("King Roald").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

}
