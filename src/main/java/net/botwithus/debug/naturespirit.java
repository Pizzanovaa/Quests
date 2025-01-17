package net.botwithus.debug;

import net.botwithus.api.game.hud.inventories.LootInventory;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Equipment;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.ObjectAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.EquipmentInventory;
import net.botwithus.api.game.hud.traversal.Lodestone;

public class naturespirit {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3438, 9895, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate grottotreeccord = new Coordinate(3439, 3334, 0);
    static Area.Circular grottotreecarea = new Area.Circular(grottotreeccord, 5);
    static Coordinate logstotransform = new Coordinate(3429, 3330, 0);
    static Area.Circular logstotransformarea = new Area.Circular(logstotransform, 10);
    public static boolean planDialog = false;

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2355);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Nature Spirit");

            if (startarea.contains(player)) {
                talktoDrezel();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 5:
                if(!grottotreecarea.contains(player)) {
                    DebugScript.moveTo(grottotreeccord);
                }
                break;
                case 10:
                if(!Backpack.contains(2966) ) {
                    SceneObject bench = SceneObjectQuery.newQuery().name("Bench").option("Search").results().first();
                    if(bench != null) {
                        bench.interact("Search");
                    }
                }
                else if(!Backpack.contains(2967) && Backpack.contains(2966))
                {
                    SceneObject Grottotree = SceneObjectQuery.newQuery().name("Grotto tree").option("Search").results().first();
                    if(Grottotree != null) {
                        Grottotree.interact("Search");
                    }
                }
                else if(Backpack.contains(2967) && Backpack.contains(2966))
                {
                    
                    if(Equipment.contains("Ghostspeak amulet"))
                    {
                        talktoFillimanTarlock();
                    }
                    else
                    {
                        println("Wearing Ghostspeak amulet");
                        Backpack.interact("Ghostspeak amulet", "Wear");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 30:
                    if(!grottotreecarea.contains(player)) {
                        DebugScript.moveTo(grottotreeccord);
                    }
                    talktoFillimanTarlock();
                    break;
                case 35:
                if(!startarea.contains(player)) {
                    DebugScript.moveTo(startcord);
                }
                else
                {
                    talktoDrezel();
                }
                break;
                case 40:
                    if(grottotreecarea.contains(player)){
                        SceneObject bridge = SceneObjectQuery.newQuery().name("Bridge").results().nearest();
                        if(bridge != null){
                            bridge.interact("Jump");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        return;
                    }
                if(!logstotransformarea.contains(player)) {
                    DebugScript.moveTo(logstotransform);
                }
                else
                {
                    SceneObject fungionlog = SceneObjectQuery.newQuery().name("Fungi on log").results().first();

                    if(Backpack.contains(2968) && fungionlog == null)
                    {
                        Backpack.interact("Druidic spell", "Cast");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(fungionlog != null)
                    {
                        fungionlog.interact("Pickup");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 45:
                SceneObject fungionlog = SceneObjectQuery.newQuery().name("Fungi on log").results().first();
                if(fungionlog != null)
                {
                    fungionlog.interact("Pick");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 50:
                if(Backpack.contains(2970))
                {
                    if(grottotreecarea.contains(player))
                    {
                        talktoFillimanTarlock();
                    }
                    else
                    {
                        DebugScript.moveTo(grottotreeccord);
                    }
                }
                break;
                case 55:
                Coordinate orangestonecord = new Coordinate(3440, 3335, 0);
                Coordinate yellostonecord = new Coordinate(3439, 3336, 0);
                Coordinate greystonecord = new Coordinate(3441, 3336, 0);

                SceneObject orangestone = SceneObjectQuery.newQuery().name("Stone").on(orangestonecord).results().first();
                SceneObject yellostone = SceneObjectQuery.newQuery().name("Stone").on(yellostonecord).results().first();
                SceneObject greystone = SceneObjectQuery.newQuery().name("Stone").on(greystonecord).results().first();

                Item fungus = InventoryItemQuery.newQuery(93).name("Mort myre fungus").results().first();
                Item usedspell = InventoryItemQuery.newQuery(93).ids(2969).results().first();

                if(yellostone != null && fungus != null)
                {
                    boolean isYellow = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, fungus.getSlot(), 96534533);
                    if(isYellow)
                    {
                        yellostone.interact(ObjectAction.OBJECT1);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(greystone !=null && usedspell != null)
                {
                    boolean isGreen = MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, usedspell.getSlot(), 96534533);
                    if(isGreen)
                    {
                        greystone.interact(ObjectAction.OBJECT1);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(orangestone !=null && !player.getCoordinate().equals(orangestonecord) && fungus == null && usedspell == null)
                {
                    Movement.walkTo(orangestonecord.getX(), orangestonecord.getY(), true);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else
                {
                    talktoFillimanTarlock();
                }
                break;
                case 60:
                SceneObject grottotree = SceneObjectQuery.newQuery().name("Grotto tree").option("Enter").results().first();
                if(grottotree != null)
                {
                    grottotree.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 65:
                talktoFillimanTarlock();
                break;
                case 70:
                talktonaturespirit();
                break;
                case 75:
                SceneObject exitgrotto = SceneObjectQuery.newQuery().name("Grotto").option("Exit").results().first();
                SceneObject fungionlog1 = SceneObjectQuery.newQuery().name("Fungi on log").results().first();
                GroundItem druidpouch = GroundItemQuery.newQuery().name("Druid pouch").results().nearest();
                if(!Backpack.contains("Druid pouch") && druidpouch != null){
                    if(LootInventory.isOpen()){
                        LootInventory.take("Druid pouch");
                    }else {
                        druidpouch.interact("Take");
                    }
                    return;
                }
                if(exitgrotto != null)
                {
                    exitgrotto.interact("Exit");
                    delay(RandomGenerator.nextInt(600, 800));
                    return;
                }
                if(!logstotransformarea.contains(player))
                {
                    DebugScript.moveTo(logstotransform);
                }
                else if(fungionlog1 == null)
                {

                    Backpack.interact("Silver sickle (b)", "Bloom");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(fungionlog1 != null)
                {
                    fungionlog1.interact("Pick");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 80:
                SceneObject fungionlog2 = SceneObjectQuery.newQuery().name("Fungi on log").results().first();
                if(fungionlog2 == null)
                {

                    Backpack.interact("Silver sickle (b)", "Bloom");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(fungionlog2 != null)
                {
                    fungionlog2.interact("Pick");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 85:
                SceneObject fungionlog3 = SceneObjectQuery.newQuery().name("Fungi on log").results().first();
                if(fungionlog3 == null && Backpack.getCount(2970) < 4)
                {

                    Backpack.interact("Silver sickle (b)", "Bloom");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(fungionlog3 != null)
                {
                    fungionlog3.interact("Pick");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(Backpack.getCount(2970) > 3)
                {
                    Backpack.interact("Druid pouch", "Fill");
                    delay(RandomGenerator.nextInt(600, 800));
                    // Npc Ghast = NpcQuery.newQuery().name("Ghast").results().nearest();
                    // if(Ghast != null)
                    // {
                    //     Ghast.interact("Invoke");
                    //     delay(RandomGenerator.nextInt(600, 800));
                    // }
                }
                break;
                case 90, 95, 100:
                Npc Ghast = NpcQuery.newQuery().name("Ghast").results().nearest();
                if(Ghast != null && Ghast.getOptions().contains("Invoke"))
                {
                    Ghast.interact("Invoke");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(Ghast.getOptions().contains("Attack"))
                {
                    Ghast.interact("Attack");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 105:
                Npc naturespirit = NpcQuery.newQuery().name("Nature Spirit").results().first();
                if(!grottotreecarea.contains(player) && naturespirit == null)
                {
                    DebugScript.moveTo(grottotreeccord);
                }
                else if(naturespirit != null)
                {
                    naturespirit.interact("Talk-to");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else
                {
                    SceneObject grottotree1 = SceneObjectQuery.newQuery().name("Grotto tree").option("Enter").results().first();
                    if(grottotree1 != null)
                    {
                        grottotree1.interact("Enter");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                
            }
        }
    }


    public static void talktoDrezel()
    {
        Npc drezel = NpcQuery.newQuery().name("Drezel").results().first();
        if (drezel != null) {
            drezel.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoFillimanTarlock()
    {
        Npc FillimanTarlock = NpcQuery.newQuery().name("Filliman Tarlock").results().first();
        if (FillimanTarlock != null) {
            FillimanTarlock.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktonaturespirit()
    {
        Npc naturespirit = NpcQuery.newQuery().name("Nature Spirit").results().first();
        if (naturespirit != null) {
            naturespirit.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
