package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.cs2.ScriptBuilder;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.util.RandomGenerator;
import static net.botwithus.rs3.script.Execution.delay;
import net.botwithus.rs3.util.Regex;
import java.util.regex.Pattern;


import net.botwithus.api.game.hud.inventories.Backpack;


import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;    
import net.botwithus.rs3.game.scene.entities.object.SceneObject;




public class VesselHarbinger {


static Pattern pedestalpattern = Regex.getPatternForContainingOneOf("Pedestal (lesser necroplasm)",
            "Pedestal (greater necroplasm)",
            "Pedestal (powerful necroplasm)",
            "Pedestal (lesser essence)",
            "Pedestal (greater essence)",
            "Pedestal (lesser communion)",
            "Pedestal (greater communion)",
            "Pedestal (powerful communion)",
            "Pedestal (essence)",
            "Pedestal (lesser ensoul material)",
            "Pedestal (ensoul material)",
            "Pedestal (greater ensoul material)",
            "Pedestal (manifest ectoplasm)",
            "Pedestal (ensoul moonstone)",
            "Pedestal (empower vessel)"
            );

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    
    static Coordinate startcord = new Coordinate(1102, 1776, 1);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate ectofuntus = new Coordinate(3659, 3517, 0);
    static Area.Circular ectofuntusarea = new Area.Circular(ectofuntus, 10); 
    static Coordinate torturedsoul = new Coordinate(3660, 3536, 0);
    static Area.Circular torturedsoularea = new Area.Circular(torturedsoul, 10); 
    static Coordinate netty = new Coordinate(3461, 3558, 0);
    static Area.Circular nettyarea = new Area.Circular(netty, 10);
    static Coordinate velorina = new Coordinate(3679, 3509, 0);
    static Area.Circular velorinaarea = new Area.Circular(velorina, 10);
    static Coordinate nettyum = new Coordinate(1122, 1776, 1);
    static Area.Circular nettyumarea = new Area.Circular(nettyum, 10);
    static Coordinate ghostbanker = new Coordinate(3689, 3467, 0);
    static Area.Circular ghostbankerarea = new Area.Circular(ghostbanker, 10);
    static Coordinate ritualsite = new Coordinate(1037, 1770, 1);
    static Area.Circular ritualsitearea = new Area.Circular(ritualsite, 20);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(53499);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Vessel Harbinger");

            if (startarea.contains(player)) {
                
                    talktoDeath();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {

                case 5:
                if(!ectofuntusarea.contains(player))
                {
                    DebugScript.moveTo(ectofuntus);
                }
                else
                {
                    talktoNecrovarus();
                }
                break;

                case 10:
                if(!torturedsoularea.contains(player))
                {
                    DebugScript.moveTo(torturedsoul);
                }
                else
                {
                    Item urn = InventoryItemQuery.newQuery().name("Soul urn").ids(55224).results().first();
                    if(urn != null)
                    {
                    int currentlydone = VarManager.getInvVarbit(urn.getInventoryType().getId(),urn.getSlot(), 53600);
                    int amountneeded = urn.getConfigType().getIntParam(8940);

                    if(currentlydone < amountneeded)    
                    {
                        tortuedsoul();
                    }
                    else if(currentlydone == amountneeded)
                    {
                        //
                        SceneObject ectoaltar = SceneObjectQuery.newQuery().name("Ectofuntus").results().nearest();
                        if(ectoaltar != null)
                        {
                            ectoaltar.interact("Worship");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    }
                    
                }
                break;

                case 15:
                talktoNecrovarus();
                break;

                case 20:
                if(!nettyarea.contains(player))
                {
                    DebugScript.moveTo(netty);
                }
                else
                {
                    SceneObject item = SceneObjectQuery.newQuery().name("Pile of letters").results().nearest();
                    if(item != null)
                    {
                        item.interact("Inspect");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 25:
                if(!velorinaarea.contains(player))
                {
                    DebugScript.moveTo(velorina);
                }
                else
                {
                    talktoVelorina();
                }
                break;
                case 30:
                if(!nettyumarea.contains(player))
                {
                    DebugScript.moveTo(nettyum);
                }
                else
                {
                    talktoNetty();
                }
                break;
                case 35:
                if(!ghostbankerarea.contains(player))
                {
                    DebugScript.moveTo(ghostbanker);
                }
                else
                {
                    talktoghostbanker();
                }
                break;
                case 40:
                if(!nettyarea.contains(player) && VarManager.getVarbitValue(53500) != 1 && VarManager.getVarbitValue(53501) != 1)
                {
                    DebugScript.moveTo(netty);
                }
                else
                {
                    SceneObject chest = SceneObjectQuery.newQuery().name("Locked chest").results().nearest();
                    Npc nettyskelton = NpcQuery.newQuery().name("Netty's skeleton").results().nearest();
                    Item book = InventoryItemQuery.newQuery(93).ids(55227).results().first();
                    if(chest != null && nettyskelton == null && book == null && VarManager.getVarbitValue(53500) != 1 && VarManager.getVarbitValue(53501) != 1)
                    {
                        chest.interact("Open");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                     else if(VarManager.getVarbitValue(53500) == 1 && VarManager.getVarbitValue(53501) == 1)
                    {
                        if(!ectofuntusarea.contains(player))
                        {
                            DebugScript.moveTo(ectofuntus);
                        }
                        else
                        {
                            talktoNecrovarus();
                        }
                    }
                    else
                    {
                        nettyskelton.interact("Attack");
                        delay(RandomGenerator.nextInt(600, 800));
                    }

                    
                }
                break;
                case 45:
                    SceneObject ectoaltar = SceneObjectQuery.newQuery().name("Ectofuntus").results().nearest();
                    Item emptybucket = InventoryItemQuery.newQuery(93).ids(1925).results().first();
                    if(ectoaltar != null && emptybucket != null)
                    {
                        if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, emptybucket.getSlot(), 96534533))
                        {
                            delay(RandomGenerator.nextInt(200, 400));
                            ectoaltar.interact(SelectableAction.SELECT_OBJECT.getType());
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }

                    if(Backpack.contains(55228))
                    {
                        if(!ritualsitearea.contains(player))
                        {
                            DebugScript.moveTo(ritualsite);
                        }
                        else
                        {
                            SceneObject pedstal = SceneObjectQuery.newQuery().name(pedestalpattern).results().nearest();
                            SceneObject platform = SceneObjectQuery.newQuery().name("Platform").results().nearest();

                            if(pedstal != null)
                            {
                                pedstal.interact("Place focus");
                                //delay(RandomGenerator.nextInt(600, 800));
                                Execution.delayUntil(3000, () -> Interfaces.isOpen(1224));
                                if(Interfaces.isOpen(1224))
                                {
                                    if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 1, 61, 80216098))
                                    {
                                        delay(RandomGenerator.nextInt(200, 400));
                                        MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 1, -1, 80216108);
                                        delay(RandomGenerator.nextInt(200, 400));
                                    }
                                }

                                
                            }
                            
                        }
                    }else if(!Backpack.contains(55228) && Client.getLocalPlayer().getCoordinate().getRegionId() == 4123)
                    {
                        doRitual();
                    }
                break;
                case 50:
                if(!startarea.contains(player))
                {
                    DebugScript.moveTo(startcord);
                }
                else
                {
                    talktoDeath();
                }
                break;
                
               
            }
        }
    }

    public static void talktoDeath() {
        Npc harbinger = NpcQuery.newQuery().name("Death").results().nearest();
        if (harbinger != null) {
            harbinger.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoNecrovarus() {
        Npc malgi = NpcQuery.newQuery().name("Necrovarus").results().first();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void tortuedsoul() {

        Npc troll1 = NpcQuery.newQuery().name("Tortured soul").results().nearest();
        if (troll1 != null) {
            troll1.interact("Attack");
            delay(RandomGenerator.nextInt(600, 800));
            return;
        }
    }
    public static void talktoVelorina() {
        Npc malgi = NpcQuery.newQuery().name("Velorina").results().first();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void talktoNetty() {
        Npc malgi = NpcQuery.newQuery().name("Netty").results().first();
        if (malgi != null) {
            malgi.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
    public static void talktoghostbanker() {
        Npc malgi = NpcQuery.newQuery().name("Ghost banker").results().first();
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

}
