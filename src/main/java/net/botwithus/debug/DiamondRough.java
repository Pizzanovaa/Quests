package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;

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
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import static net.botwithus.rs3.script.Execution.delay;
import static net.botwithus.rs3.game.movement.Movement.walkTo;

import net.botwithus.api.game.hud.Dialog;
import net.botwithus.api.game.hud.inventories.Equipment;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;    
import net.botwithus.rs3.game.scene.entities.object.SceneObject;

public class DiamondRough {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3293, 3167, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate startcord1 = new Coordinate(3290, 3157, 0);
    static Area.Circular startarea1 = new Area.Circular(startcord1, 10);
    static Coordinate ozancord = new Coordinate(3293, 3181, 0);
    static Area.Circular ozancordarea = new Area.Circular(ozancord, 10);
    static Coordinate shantaycord = new Coordinate(3302, 3124, 0);
    static Area.Circular shantaycordarea = new Area.Circular(shantaycord, 10);
    static Coordinate sundials2 = new Coordinate(3274, 3088, 0);
    static Area.Circular sundialsarea2 = new Area.Circular(sundials2, 10);
    static Coordinate sundials3 = new Coordinate(3246, 3115, 0);
    static Area.Circular sundialsarea3 = new Area.Circular(sundials3, 10);
    static Coordinate ladykelicord = new Coordinate(3204, 3083, 0);
    static Area.Circular ladykelicordarea = new Area.Circular(ladykelicord, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(9429);
        player = Client.getLocalPlayer().getServerCoordinate();

       

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Diamond in the rough");
            Npc Osman = NpcQuery.newQuery().name("Osman").results().nearest();
            if (startarea.contains(player) && Osman != null) {
                
                if (player.distanceTo(Osman.getServerCoordinate()) < 6.0) {
                    talktoOsman();
                }
                else
                {
                    SceneObject door = SceneObjectQuery.newQuery().name("Palace entrance").results().nearest();
                    if (door != null) {
                    door.interact("Pass-through");
                    delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                   
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {

                case 5:
                if (!ozancordarea.contains(player)) {
                    DebugScript.moveTo(ozancord);
                }
                else {
                    talkToOzan();
                }
                break;

                case 12:
                SceneObject het_scale = SceneObjectQuery.newQuery().name("Het scales").results().nearest();
                if (het_scale != null) {
                    het_scale.interact("Inspect");
                    delay(RandomGenerator.nextInt(600, 800));
                }              
                break;
                case 15:
                SceneObject rope = SceneObjectQuery.newQuery().name("Climbing rope").results().nearest();
                if (rope != null) {
                rope.interact("Climb");
                delay(RandomGenerator.nextInt(600, 800));
                }
                SceneObject dropoff = SceneObjectQuery.newQuery().name("Roof").results().nearest();
                if (dropoff != null) {
                    dropoff.interact("Drop-off");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                if(VarManager.getVarbitValue(11884) == 15) {
                    SceneObject rope1 = SceneObjectQuery.newQuery().name("Rope").results().nearest();
                    if (rope1 != null) {
                    rope1.interact("Shimmy-across");
                    delay(RandomGenerator.nextInt(600, 800));
                    }

                    SceneObject rug = SceneObjectQuery.newQuery().name("Rug").results().nearest();
                    if (rug != null) {
                    rug.interact("Parrot drop");
                    delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 20:
                if (!shantaycordarea.contains(player)) {
                    DebugScript.moveTo(shantaycord);
                }
                else {
                    talkToShantay();
                }
                break;
                case 25:
                SceneObject shantypass = SceneObjectQuery.newQuery().name("Shantay Pass").results().nearest();
                if (shantypass != null) {
                    shantypass.interact("Go-through");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 30:
                break;
                case 35:
                SceneObject sundials = SceneObjectQuery.newQuery().name("Sundial (Het)").results().nearest();
                if (sundials != null) {
                    sundials.interact("Inspect");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                ScriptConsole.println("Click on Sundial (Het) Manually");
                break;
                case 40:
                if (!sundialsarea2.contains(player)) {
                    DebugScript.moveTo(sundials2);
                }
                else {
                    //Nothing required here
                }
                break;
                case 45:
                SceneObject sundials2 = SceneObjectQuery.newQuery().name("Sundial (Apmeken)").results().nearest();
                if (sundials2 != null) {
                    sundials2.interact("Inspect");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 50:
                break;
                case 55:
                GroundItem item = GroundItemQuery.newQuery().name("Sundial gnomon").results().nearest();

                    if(item != null && !Backpack.contains("Sundial gnomon"))
                    {
                        item.interact("Take");
                        delay(RandomGenerator.nextInt(600, 1200));
                        if (Interfaces.isOpen(1622)) {
                            Item items = InventoryItemQuery.newQuery(773).ids(4615).results().first();
                            if (items != null) {
                                println("Item Slot" + items.getSlot());
                                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                delay(RandomGenerator.nextInt(600, 1200));
                                return;
                            }
                        }
                    }
                
                if(VarManager.getVarbitValue(11884) == 6)
                {
                    SceneObject sundials2a = SceneObjectQuery.newQuery().name("Sundial (Apmeken)").results().nearest();
                if (sundials2a != null) {
                    sundials2a.interact("Inspect");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                }
                break;
                case 57:
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 88735753);
                delay(RandomGenerator.nextInt(600, 800));
                break;
                case 60:
                if (!sundialsarea3.contains(player)) {
                    DebugScript.moveTo(sundials3);
                }
                else {
                    //Nothing required here
                }
                break;
                case 65:
                int test =0;
                SceneObject sundials2a = SceneObjectQuery.newQuery().name("Sundial (Crondis)").results().nearest();
                if (sundials2a != null && test == 0) {
                    sundials2a.interact("Inspect");
                    delay(RandomGenerator.nextInt(600, 800));
                    test = test + 1;
                }
                SceneObject tunnel = SceneObjectQuery.newQuery().name("Tunnel").results().nearest();
                if (tunnel != null) {
                    tunnel.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                if(Dialog.getText().contains("What am I sitting on? It's really unconfortable..."))
                {
                    MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 77856772);
                    delay(RandomGenerator.nextInt(600, 800));
                    if (sundials2a != null) {
                        sundials2a.interact("Inspect");
                        delay(RandomGenerator.nextInt(600, 800));
                        test = test + 1;
                    }

                }
                break;
                case 67:
                SceneObject sundials23 = SceneObjectQuery.newQuery().name("Sundial (Crondis)").results().nearest();
                if (sundials23 != null) {
                    sundials23.interact("Inspect");
                    delay(RandomGenerator.nextInt(600, 800));
                    if(Interfaces.isOpen(1354))
                    {
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 88735756);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                     
                    
                }
                break;
                case 70:
                Coordinate tunnelcord1 = new Coordinate(12424, 4683, 0);
                Coordinate tunnelcord2 = new Coordinate(14339, 839, 0);
                if(VarManager.getVarbitValue(11884) != 15)
                {SceneObject tunnel1 = SceneObjectQuery.newQuery().name("Tunnel").results().nearestTo(tunnelcord1);
                if (tunnel1 != null) {
                    tunnel1.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }}
                else
                {
                    walkTo(14339,   839 ,false);
                    delay(RandomGenerator.nextInt(600,800));
                    SceneObject tunnel1 = SceneObjectQuery.newQuery().name("Tunnel").results().nearestTo(tunnelcord2);
                    if (tunnel1 != null) {
                        tunnel1.interact("Enter");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }

                break;
                case 75:
                GroundItem item11 = GroundItemQuery.newQuery().name("Bronze scimitar").results().nearest();

                    if(item11!= null && !Backpack.contains("Bronze scimitar"))
                    {
                        item11.interact("Take");
                        delay(RandomGenerator.nextInt(600, 1200));
                        if (Interfaces.isOpen(1622)) {
                            Item items = InventoryItemQuery.newQuery(773).ids(1321).results().first();
                            if (items != null) {
                                println("Item Slot" + items.getSlot());
                                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                delay(RandomGenerator.nextInt(600, 1200));
                                return;
                            }
                        }
                    }
                    if(Backpack.contains("Bronze scimitar"))
                    {
                        Backpack.interact("Bronze scimitar", "Wield");
                        delay(RandomGenerator.nextInt(600, 800));
                        return;
                    }
                    Npc kalphite = NpcQuery.newQuery().name("Dung kalphite").spotAnimation(3405).results().random();
                    if (kalphite != null && !Equipment.Slot.WEAPON.name().equals("Bronze scimitar")) {
                        ScriptConsole.println("Kalphite found and slicing open");
                        kalphite.interact("Slice open");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                break;  
                case 76:
                Npc kalphite1 = NpcQuery.newQuery().name("Dung kalphite").spotAnimation(3405).results().random();
                    if (kalphite1 != null && !Equipment.Slot.WEAPON.name().equals("Bronze scimitar")) {
                        ScriptConsole.println("Kalphite found and slicing open");
                        kalphite1.interact("Slice open");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                break;
                case 77:
                Npc kalphite2 = NpcQuery.newQuery().name("Dung kalphite").spotAnimation(3405).results().random();
                    if (kalphite2 != null && !Equipment.Slot.WEAPON.name().equals("Bronze scimitar")) {
                        ScriptConsole.println("Kalphite found and slicing open");
                        kalphite2.interact("Slice open");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                break;
                case 78:
                Npc kalphite3 = NpcQuery.newQuery().name("Dung kalphite").spotAnimation(3405).results().random();
                    if (kalphite3 != null && !Equipment.Slot.WEAPON.name().equals("Bronze scimitar")) {
                        ScriptConsole.println("Kalphite found and slicing open");
                        kalphite3.interact("Slice open");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                break;
                case 79:
                Npc kalphite4 = NpcQuery.newQuery().name("Dung kalphite").spotAnimation(3405).results().random();
                    if (kalphite4 != null && !Equipment.Slot.WEAPON.name().equals("Bronze scimitar")) {
                        ScriptConsole.println("Kalphite found and slicing open");
                        kalphite4.interact("Slice open");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                
                    GroundItem item12 = GroundItemQuery.newQuery().name("Kharid-ib").results().nearest();

                    if(item12!= null && !Backpack.contains("Kharid-ib"))
                    {
                        item12.interact("Take");
                        delay(RandomGenerator.nextInt(600, 1200));
                        if (Interfaces.isOpen(1622)) {
                            Item items = InventoryItemQuery.newQuery(773).ids(25117).results().first();
                            if (items != null) {
                                println("Item Slot" + items.getSlot());
                                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                delay(RandomGenerator.nextInt(600, 1200));
                                return;
                            }
                        }
                    }    
                break;
                case 80:
                SceneObject exit = SceneObjectQuery.newQuery().name("Exit").results().nearest();
                if (exit != null) {
                    exit.interact("Climb-out");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 85:
                talkToOzan();
                break;
                case 92:
                SceneObject sundials4a = SceneObjectQuery.newQuery().name("Sundial (Scabaras)").results().nearest();
                if (sundials4a != null) {
                    sundials4a.interact("Inspect");
                    delay(RandomGenerator.nextInt(600, 800));
                    if(Interfaces.isOpen(1354))
                    {
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 88735759);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 95:
                if (!ladykelicordarea.contains(player)) {
                    DebugScript.moveTo(ladykelicord);
                }
                else {
                    talkToLadyKeli();
                }
                break;
                case 100:
                // Revo Fight - Killing Apep and Heru
                ScriptConsole.println("Revo Fight - Killing Apep and Heru");
                break;
                case 105:
                talktoPrince();
                break;
                case 110:
                break;
                
                
            }
        }
    }

    public static void talktoOsman() {
        Npc Osman = NpcQuery.newQuery().name("Osman").results().nearest();
        if (Osman != null) {
            Osman.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talkToOzan() {
        Npc Ozan = NpcQuery.newQuery().name("Ozan").results().nearest();
        if (Ozan != null) {
            Ozan.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talkToShantay() {
        Npc Shantay = NpcQuery.newQuery().name("Shantay").results().nearest();
        if (Shantay != null) {
            Shantay.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talkToLadyKeli() {
        Npc LadyKeli = NpcQuery.newQuery().name("Lady Keli").results().nearest();
        if (LadyKeli != null) {
            LadyKeli.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoPrince() {
        Npc Prince = NpcQuery.newQuery().name("Prince Ali Mirza").results().nearest();
        if (Prince != null) {
            Prince.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
