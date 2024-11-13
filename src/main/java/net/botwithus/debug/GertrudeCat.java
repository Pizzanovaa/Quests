package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
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
import net.botwithus.rs3.game.*;

public class GertrudeCat {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3151, 3411, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate varrocksquare = new Coordinate(3218, 3432,0);
    static Area.Circular varrockarea = new Area.Circular(varrocksquare, 10);
    static Coordinate catlocation = new Coordinate(3186, 3415, 1);
    static Area.Circular catarea = new Area.Circular(catlocation, 10);
    static Coordinate jigglecrate = new Coordinate(3195, 3412, 0);
    static Area.Circular jigglecratearea = new Area.Circular(jigglecrate, 3);

    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2175);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Gertrude Cat");

            if (startarea.contains(player)) {
                talktoGertrude();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 1:
                if(!Backpack.contains(1573))
                {
                    GroundItem doogleleave = GroundItemQuery.newQuery().ids(1573).results().nearest();
                    if(doogleleave !=null)
                    {
                        doogleleave.interact("Take");
                        delay(RandomGenerator.nextInt(600, 1200));
                            if (Interfaces.isOpen(1622)) {
                                Item items = InventoryItemQuery.newQuery(773).ids(1573).results().first();
                                if (items != null) {
                                    println("Item Slot" + items.getSlot());
                                    MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                    delay(RandomGenerator.nextInt(600, 1200));
                                    return;
                                }
                            }
                    }
                }
                else if(Backpack.contains(1573))
                {
                    if(!varrockarea.contains(player))
                    {
                        DebugScript.moveTo(varrocksquare);
                    }
                    else {
                        talktoShilop();
                    }
                }
                break;
                case 2:
                if(!catarea.contains(player))
                {
                    DebugScript.moveTo(catlocation);
                }
                else {
                    
                    Npc cat = NpcQuery.newQuery().name("Fluffs").results().nearest();
                    Item bucketofmilk = InventoryItemQuery.newQuery().ids(1927).results().first();
                    Item rawsardine = InventoryItemQuery.newQuery().ids(327).results().first();
                    Item doogleleave = InventoryItemQuery.newQuery().ids(1573).results().first();   
                    if(cat != null)
                    {
                        if(VarManager.getVarpValue(2176) == 96)
                        {
                            MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, bucketofmilk.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(600,800));
                            MiniMenu.interact(SelectableAction.SELECT_NPC.getType(), cat.getId(), 0,0);
                            delay(RandomGenerator.nextInt(600,800));
                        }
                        else {
                            cat.interact(NPCAction.NPC1);
                            delay(RandomGenerator.nextInt(600,800));
                        }
                    }

                }
                break;
                case 3:
                Npc cat = NpcQuery.newQuery().name("Fluffs").results().nearest();
                    Item rawsardine = InventoryItemQuery.newQuery().ids(327).results().first();
                    Item doogleleave = InventoryItemQuery.newQuery().ids(1573).results().first();
                    Item dooglesardine = InventoryItemQuery.newQuery().ids(1552).results().first();   
                    if(cat != null && Backpack.contains(327) && Backpack.contains(1573))
                    {
                        cat.interact(NPCAction.NPC1);
                        delay(RandomGenerator.nextInt(1200,1500));
                        
                        
                        MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, rawsardine.getSlot(), 96534533);
                        delay(RandomGenerator.nextInt(600,800));
                        MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, doogleleave.getSlot(), 96534533);
                        delay(RandomGenerator.nextInt(600,800));
                    }
                    else if(Backpack.contains(1552))
                    {
                        MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, dooglesardine.getSlot(), 96534533);
                        delay(RandomGenerator.nextInt(600,800));
                        MiniMenu.interact(SelectableAction.SELECT_NPC.getType(), cat.getId(), 0,0);
                        delay(RandomGenerator.nextInt(600,800));
                    }
                    
                break;
                case 4:
                    if(Backpack.contains(13236))
                    {
                      if(!catarea.contains(player))
                      {
                        DebugScript.moveTo(catlocation);
                      }
                      else {
                        Npc cat1 = NpcQuery.newQuery().name("Fluffs").results().nearest();
                        Item kittens = InventoryItemQuery.newQuery().ids(13236).results().first();
                        if(cat1 != null && kittens != null)
                        {
                            if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, kittens.getSlot(), 96534533))
                             {
                                delay(RandomGenerator.nextInt(600,800));
                                MiniMenu.interact(SelectableAction.SELECT_NPC.getType(), cat1.getId(), 0,0);
                                delay(RandomGenerator.nextInt(600,800));
                             }
                        }
                      }
                    } 
                    else if(!Backpack.contains(13236))
                    {
                        if(!jigglecratearea.contains(player))
                        {
                            DebugScript.moveTo(jigglecrate);
                        }
                        else {
                            SceneObject jigglecrate = SceneObjectQuery.newQuery().ids(110061).results().nearest();
                            if(jigglecrate != null)
                            {
                                jigglecrate.interact("Search");
                                delay(RandomGenerator.nextInt(600,800));
                            }
                        } 
                    }
                    
                break;
                case 5:
                if(!startarea.contains(player))
                {
                    DebugScript.moveTo(startcord);
                }
                else {
                    talktoGertrude();
                }
                break;
            }
        }
    }

    public static void talktoGertrude() {
        Npc gertrude = NpcQuery.newQuery().name("Gertrude").results().nearest();
        if (gertrude != null) {
            gertrude.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(2000,2400));
        }

    }

    public static void talktoShilop() {
        Npc shilop = NpcQuery.newQuery().name("Shilop").results().nearest();
        if (shilop != null) {
            shilop.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(2000,2400));
        }
    }

}
