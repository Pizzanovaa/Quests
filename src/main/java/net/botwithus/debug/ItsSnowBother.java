package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.game.movement.Movement.onChatMsgEvent;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.rs3.events.impl.ChatMessageEvent;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.actionbar.ActionBar;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.input.GameInput;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Bank;
import net.botwithus.api.game.hud.traversal.Lodestone;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.queries.results.EntityResultSet;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.Item;

public class ItsSnowBother {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(5246, 9773, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);

    static Coordinate varrocksleigh = new Coordinate(3217, 3436, 0);
    static Area.Circular varrocksleigharea = new Area.Circular(varrocksleigh, 10);

    static Coordinate lumbridgesleigh = new Coordinate(3236, 3218, 0);
    static Area.Circular lumbridgesleigharea = new Area.Circular(lumbridgesleigh, 10);

    static Coordinate lumbridgemarket = new Coordinate(3211, 3257, 0);
    static Area.Circular lumbridgemarketarea = new Area.Circular(lumbridgemarket, 10);

    static Coordinate lumbridgechrushpile = new Coordinate(3243, 3218, 0);
    static Area.Circular lumbridgechrushpilearea = new Area.Circular(lumbridgechrushpile, 2);

    static Coordinate faladorsiramik = new Coordinate(2959, 3337, 2);
    static Area.Circular faladorsiramikarea = new Area.Circular(faladorsiramik, 4);
    static Coordinate boblumbridge = new Coordinate(3232, 3203, 0);
    static Area.Circular boblumbridgearea = new Area.Circular(boblumbridge, 4);
    static Coordinate brugsen = new Coordinate(3166, 3473, 0);
    static Area.Circular brugsenarea = new Area.Circular(brugsen, 4);
    static Coordinate doric = new Coordinate(2960, 3439, 0);
    static Area.Circular doricarea = new Area.Circular(doric, 4);
    static Coordinate reldo = new Coordinate(3210, 3494, 0);
    static Area.Circular reldoarea = new Area.Circular(reldo, 4);



    static int giftcounter = 0;
    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(54817);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }
        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... It's Snow Bother");
            Npc SantaClaus = NpcQuery.newQuery().name("Santa Claus").results().first();

            if (startarea.contains(player) || SantaClaus != null) {
                talktosantaclaus();
            } else {
                if(ActionBar.containsAbility("Christmas Village Teleport") && SantaClaus == null)
                {
                    ActionBar.useAbility("Christmas Village Teleport");
                   
                    delay(RandomGenerator.nextInt(600, 800));
                    //counterspellchristmas = counterspellchristmas + 1;
                }
                //DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 5:
                break;
                case 10:
                talktosnowimp();
                //talktoVarrockImp();

                SceneObject map = SceneObjectQuery.newQuery().name("Christmas spirit map").results().first();
                if (map != null) {
                    map.interact("Investigate");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 15:
                SceneObject map1 = SceneObjectQuery.newQuery().name("Christmas spirit map").option("Investigate").results().first();
                SceneObject decobench = SceneObjectQuery.newQuery().name("Decoration bench").option("Create").results().first();
                //Falaor   
                if(map1 != null && decobench != null)
                {
                    decobench.interact("Create");
                    delay(RandomGenerator.nextInt(600, 800));
                    //Lodestone.FALADOR.teleport();
                }
                break;
                case 20:
                SceneObject map2 = SceneObjectQuery.newQuery().name("Christmas spirit map").option("Investigate").results().first();
                if(map2 != null)
                {
                    Lodestone.FALADOR.teleport();
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(map2 == null)
                {
                    SceneObject sleigh = SceneObjectQuery.newQuery().name("Sleigh").option("Search").results().first();
                    if(sleigh != null)
                    {
                        sleigh.interact("Search");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 25:
                Npc pileofsnow = NpcQuery.newQuery().name("Pile of snow").option("Search").results().first();
                SceneObject brokentree = SceneObjectQuery.newQuery().name("Missing Christmas decorations").option("Add").results().first();
                SceneObject chrismastree = SceneObjectQuery.newQuery().name("Christmas Tree").results().first();
                SceneObject sleigh = SceneObjectQuery.newQuery().name("Sleigh").option("Search").results().first();
                if(pileofsnow != null && VarManager.getVarbitValue(54818) != 31)  //FALADOR AREA
                {
                    pileofsnow.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(VarManager.getVarbitValue(54818) == 31 && brokentree != null)
                {
                    //SceneObject brokentree = SceneObjectQuery.newQuery().name("Missing Christmas decorations").option("Add").results().first();
                    if(brokentree != null)
                    {
                        brokentree.interact("Add");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(VarManager.getVarbitValue(54818) == 31 && brokentree == null && VarManager.getVarbitValue(54819) != 31)
                {
                    if(!lumbridgesleigharea.contains(player) && pileofsnow == null && VarManager.getVarbitValue(54819) == 0)
                    {
                        DebugScript.moveTo(lumbridgesleigh);
                    }
                    else
                    {
                        println("Lumbridge area");
                        //SceneObject sleigh = SceneObjectQuery.newQuery().name("Sleigh").option("Search").results().first();
                        if(sleigh != null && pileofsnow == null)
                        {
                            println("Sleigh found");
                            sleigh.interact("Search");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else if(VarManager.getVarbitValue(54819) == 30 && pileofsnow != null)
                        {
                            println("Lumbridge Market Area");
                            if(!lumbridgemarketarea.contains(player))
                            {
                                DebugScript.moveTo(lumbridgemarket);
                            }
                            else
                            {
                                pileofsnow.interact("Search");
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                        else if(pileofsnow != null)
                        {
                            pileofsnow.interact("Search");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else if(VarManager.getVarbitValue(54818) == 31 && VarManager.getVarbitValue(54819) == 31 && brokentree == null)
                {
                    if(!varrocksleigharea.contains(player) && pileofsnow == null)
                    {
                        DebugScript.moveTo(varrocksleigh);
                    }
                    else
                    {
                        //SceneObject sleigh = SceneObjectQuery.newQuery().name("Sleigh").option("Search").results().first();
                        if(sleigh != null && pileofsnow == null)
                        {
                            sleigh.interact("Search");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else if(pileofsnow != null)
                        {
                            pileofsnow.interact("Search");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                break;
                case 30:
                SceneObject map3 = SceneObjectQuery.newQuery().name("Christmas spirit map").option("Investigate").results().first();
                if(map3 != null)
                {
                    map3.interact("Investigate");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else
                {
                    ActionBar.useAbility("Christmas Village Teleport");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 35:
                SceneObject santarequest = SceneObjectQuery.newQuery().name("Santa's request").results().first();
                int varpValue = VarManager.getVarpValue(11576);
                //int lastTwoDigits = varpValue & 0xFF;
                int lastTwoDigits = varpValue % 100; 
                
                if(santarequest != null && !Backpack.contains("Santa's sack"))
                {
                    santarequest.interact("Take");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                
                else if(lastTwoDigits ==48 &&   Backpack.contains("Santa's sack")  && !Interfaces.isOpen(1279) && giftcounter==0)
                {
                    Backpack.interact("Santa's sack", "Check");
                    delay(RandomGenerator.nextInt(600, 800));
                    if(DebugScript.message.contains("Delivered 0/5 presents to citizens of Gielinor"))
                    {
                        giftcounter = 0;
                    }
                    if(DebugScript.message.contains("Delivered 1/5 presents to citizens of Gielinor"))
                    {
                        giftcounter = 1;
                    }
                    if(DebugScript.message.contains("Delivered 2/5 presents to citizens of Gielinor"))
                    {
                        giftcounter = 2;
                    }
                    if(DebugScript.message.contains("Delivered 3/5 presents to citizens of Gielinor"))
                    {
                        giftcounter = 3;
                    }
                    if(DebugScript.message.contains("Delivered 4/5 presents to citizens of Gielinor"))
                    {
                        giftcounter = 4;
                    }
                   
                }
             
                else if(lastTwoDigits ==48 && Interfaces.isOpen(1279) && DebugScript.message.contains("Delivered 0/5 presents to citizens of Gielinor"))
                {
                    boolean success = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 83820554);   // Selecting Sir Amik Varze
                    if(success)
                    {   
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 83820578);   
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(lastTwoDigits ==48 && Interfaces.isOpen(1279) && DebugScript.message.contains("Delivered 1/5 presents to citizens of Gielinor"))
                {
                    boolean success = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 83820555);   // Bob
                    if(success)
                    {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 83820578); 
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(lastTwoDigits ==48 && Interfaces.isOpen(1279) && DebugScript.message.contains("Delivered 2/5 presents to citizens of Gielinor"))
                {
                    boolean success = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 83820556);   // Selecting Brugsen
                    if(success)
                    {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 83820578); 
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(lastTwoDigits ==48 && Interfaces.isOpen(1279) && DebugScript.message.contains("Delivered 3/5 presents to citizens of Gielinor"))
                {
                    boolean success = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 83820557);   // Selecting Doric
                    if(success)
                    {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 83820578); 
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(lastTwoDigits ==48 && Interfaces.isOpen(1279) && DebugScript.message.contains("Delivered 4/5 presents to citizens of Gielinor"))
                {
                    boolean success = MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 83820564);   // Selecting Reldo
                    if(success)
                    {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 83820578); 
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(lastTwoDigits ==49)
                {
                    if(!faladorsiramikarea.contains(player))
                    {
                        DebugScript.moveTo(faladorsiramik);
                    }
                    else
                    {
                        Npc siramik = NpcQuery.newQuery().name("Sir Amik Varze").results().first();
                        if(siramik != null)
                        {
                            siramik.interact("Talk-to");
                            delay(RandomGenerator.nextInt(600, 800));
                            if(giftcounter == 0)
                        {
                            Backpack.interact("Santa's sack", "Check");
                            delay(RandomGenerator.nextInt(600, 800));
                            giftcounter = 0;
                        }
                        }
                    }

                }
                else if(lastTwoDigits ==50 )
                {
                   if(!boblumbridgearea.contains(player))
                   {
                    DebugScript.moveTo(boblumbridge);
                   }
                   else
                   {
                    Npc bob = NpcQuery.newQuery().name("Bob").results().first();
                    if(bob != null)
                    {
                        bob.interact("Talk-to");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(giftcounter == 1)
                        {
                            Backpack.interact("Santa's sack", "Check");
                            delay(RandomGenerator.nextInt(600, 800));
                            giftcounter = 0;
                        }
                    }
                   }
                }
                else if(lastTwoDigits ==51)
                {
                   if(!brugsenarea.contains(player))
                   {
                    DebugScript.moveTo(brugsen);
                   }
                   else
                   {
                    Npc brugsen = NpcQuery.newQuery().name("Brugsen Bursen").results().first();
                    if(brugsen != null)
                    {
                        brugsen.interact("Talk-to");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(giftcounter == 2)
                        {
                            Backpack.interact("Santa's sack", "Check");
                            delay(RandomGenerator.nextInt(600, 800));
                            giftcounter = 0;
                        }
                    }
                   }
                }
                else if(lastTwoDigits ==52)
                {
                    if(!doricarea.contains(player))
                    {
                        DebugScript.moveTo(doric);
                    }
                    else
                    {
                        Npc doric = NpcQuery.newQuery().name("Doric").results().first();
                        if(doric != null)
                        {
                            doric.interact("Talk-to");
                            delay(RandomGenerator.nextInt(600, 800));
                            if(giftcounter == 3)
                        {
                            Backpack.interact("Santa's sack", "Check");
                            delay(RandomGenerator.nextInt(600, 800));
                            giftcounter = 0;
                        }
                        }
                    }
                }
                else if(lastTwoDigits ==59)
                {
                    if(!reldoarea.contains(player))
                    {
                        DebugScript.moveTo(reldo);
                    }
                    else
                    {
                        Npc reldo = NpcQuery.newQuery().name("Reldo").results().first();
                        if(reldo != null)
                        {
                            reldo.interact("Talk to");
                            delay(RandomGenerator.nextInt(600, 800));
                            if(giftcounter == 4)
                        {
                            Backpack.interact("Santa's sack", "Check");
                            delay(RandomGenerator.nextInt(600, 800));
                            giftcounter = 0;
                        }
                        }
                    }
                }

                break;
                case 40:
                Npc santaclaus = NpcQuery.newQuery().name("Santa Claus").results().first();
                if(santaclaus != null)
                {
                    santaclaus.interact("Talk to");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else
                {
                    ActionBar.useAbility("Christmas Village Teleport");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
            }
        }
    }


    public static void talktosantaclaus()
    {
        Npc santaclaus = NpcQuery.newQuery().name("Santa Claus").results().first();
        if (santaclaus != null) {
            santaclaus.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktosnowimp()
    {
        Npc mariusclaus = NpcQuery.newQuery().name("Marius Claus","Barry Claus", "Benny Claus", "Boris Claus","Magnus","Marcus Claus","Charlie Claus","Marvin Claus","Morris Claus","Murphy Claus", "Dennis Claus" ,"Freddie Claus", "Norris Claus", "Rasmus Claus").results().first();
        if (mariusclaus != null) {
            mariusclaus.interact("Talk-to");
            mariusclaus.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoVarrockImp()
    {
        Npc varrockimp = NpcQuery.newQuery().name("Varrock representative").results().first();
        if (varrockimp != null) {
            varrockimp.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}
