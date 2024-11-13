package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.inventories.Backpack;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;

import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;

public class mogre_lore_activity {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2977, 3195, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(15799);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            //ScriptConsole.println("Starting quest... Mogre Activity");

            if (!startarea.contains(player)) {
                DebugScript.moveTo(startcord);
            } else {
                if(Backpack.contains(1929) && Backpack.contains(4239) && Backpack.contains(231) && Backpack.contains(1927) && Backpack.contains(1975))
                {
                    talktoSkippy();
                    println("Giving Skippy the Water to sober up");
                    talktoSkippy2();
                }
                else if(Backpack.contains(4239) && Backpack.contains(231) && Backpack.contains(1927) && Backpack.contains(1975))
                {
                    talktoSkippy();

                }
                else if(Backpack.contains(231) && Backpack.contains(1927) && Backpack.contains(1975))
                {
                    Item snapgrass = InventoryItemQuery.newQuery().ids(231).results().first();
                    Item bucketofmilk = InventoryItemQuery.newQuery().ids(1927).results().first();
                    Item chocolate = InventoryItemQuery.newQuery().ids(1975).results().first();
                    Item chocloate_milk = InventoryItemQuery.newQuery().ids(1977).results().first();
                    if(snapgrass != null && bucketofmilk != null && chocolate != null)
                    {
                        if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, chocolate.getSlot(), 96534533))
                        {
                            delay(RandomGenerator.nextInt(600,800));
                            MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, bucketofmilk.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(600,800));
                            MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, snapgrass.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(600,800));
                            MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, chocloate_milk.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(600,800));

                        }
                    }
                }
                else if(Backpack.contains(231) && Backpack.contains(1977))
                {
                    Item snapgrass = InventoryItemQuery.newQuery().ids(231).results().first();
                    Item chocloate_milk = InventoryItemQuery.newQuery().ids(1977).results().first();
                    if(snapgrass != null && chocloate_milk != null)
                    {
                        if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, snapgrass.getSlot(), 96534533))
                        {
                            delay(RandomGenerator.nextInt(600,800));
                            MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, chocloate_milk.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(600,800));
                        }
                    }
                }
                else if(Backpack.contains(1504))
                {
                    talktoSkippy();
                }
                
            }

        }
    }


    public static void talktoSkippy()
    {
        Npc skippy = NpcQuery.newQuery().name("Skippy").results().nearest();
        if(skippy != null)
        {
            skippy.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(400,800));
        }
    }

    public static void talktoSkippy2()
    {
        Npc skippy = NpcQuery.newQuery().name("Skippy").results().nearest();
        if(skippy != null)
        {
            skippy.interact(NPCAction.NPC2);
            delay(RandomGenerator.nextInt(400,800));
        }
    }

}
