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

public class TheGolem {

    static int letteread =0;
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3490, 3089, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate cave1 = new Coordinate(2722, 4895, 0);
    static Area.Circular cavearea = new Area.Circular(cave1, 10);
    static Coordinate digsite = new Coordinate(3379, 3439, 0);
    static Area.Circular digsitearea = new Area.Circular(digsite, 10);
    static Coordinate examcenter = new Coordinate(3364, 3351, 0);
    static Area.Circular examcenterarea = new Area.Circular(examcenter, 10);
    static Coordinate meseum1st = new Coordinate(3258, 3448, 0);
    static Area.Circular meseum1starea = new Area.Circular(meseum1st, 10);
    static Coordinate museum2nd = new Coordinate(3255, 3454, 1);
    static Area.Circular museum2ndarea = new Area.Circular(museum2nd, 10);
    static Coordinate bookcasecord = new Coordinate(3364,3343,0);

    static Coordinate phoenixfeather = new Coordinate(3348, 3114, 0);
    static Area.Circular phoenixfeatherarea = new Area.Circular(phoenixfeather, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(13639);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... The Golem");

            if (startarea.contains(player)) {
                Brokenclaygolem();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 1:

                    Item backpackitem = InventoryItemQuery.newQuery(93).ids(1761).results().first(); // Paper
                    Npc golem = NpcQuery.newQuery().name("Broken clay golem").results().nearest();

                    if(backpackitem !=null && golem!=null)
                    {
                        if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, backpackitem.getSlot(), 96534533))
                        {
                            delay(RandomGenerator.nextInt(200, 400));
                            MiniMenu.interact(SelectableAction.SELECT_NPC.getType(), golem.getId(),0,0);
                            delay(RandomGenerator.nextInt(200, 400));
                        }
                    }
                    if(!Backpack.contains(1761))
                    {
                        Brokenclaygolem();
                    }


                    break;
                case 2:

                    int test =0;
                    GroundItem item = GroundItemQuery.newQuery().name("Letter").results().nearest();

                    if(item != null && !Backpack.contains(4615))
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
                    else if(Backpack.contains(4615) && VarManager.getVarbitValue(13640) == 0)
                    {
                        Component manual = ComponentQuery.newQuery(1473).item(4615).results().stream().findFirst().orElse(null);
                        if(manual != null)
                        {
                            manual.interact("Read");
                            delay(RandomGenerator.nextInt(1100, 1500));
                            letteread =1;

                            if(!cavearea.contains(player))
                            {
                                SceneObject stair = SceneObjectQuery.newQuery().name("Staircase").results().nearest();

                                if(stair != null)
                                {
                                    stair.interact("Climb-down");
                                    Execution.delayUntil(RandomGenerator.nextInt(3000, 5000), () -> Client.getLocalPlayer().getCoordinate().getRegionId() == 10828);
                                }

                                if(Client.getLocalPlayer().getCoordinate().getRegionId() == 10828 && !cavearea.contains(player))
                                {
                                    DebugScript.moveTo(cave1);
                                }
                            }

                        }
                    }

                    else if(Client.getLocalPlayer().getCoordinate().getRegionId() == 10828 && !(Backpack.contains(4619) && Backpack.contains(4620))) {
                        GroundItem strangeitem = GroundItemQuery.newQuery().ids(4619).results().nearest();
                        SceneObject blackmushroom = SceneObjectQuery.newQuery().name("Black mushrooms").results().nearest();
                        InventoryItemQuery mushroom = InventoryItemQuery.newQuery(93).ids(4620);
                        var muscount = mushroom.results();

                        if (strangeitem != null && !Backpack.contains(4619)) {
                            strangeitem.interact("Take");
                            delay(RandomGenerator.nextInt(600, 1200));
                            if (Interfaces.isOpen(1622)) {
                                Item items = InventoryItemQuery.newQuery(773).ids(4619).results().first();
                                if (items != null) {
                                    println("Item Slot" + items.getSlot());
                                    MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                    delay(RandomGenerator.nextInt(600, 1200));
                                    return;
                                }
                            }
                        }

                        else if(blackmushroom !=null && Backpack.contains(4619) && !Backpack.contains(4620) )
                        {
                            blackmushroom.interact("Pick");
                            Execution.delayUntil(RandomGenerator.nextInt(3000, 4500), () -> Backpack.contains(4620));
                        }
                    }

                    else if (Backpack.contains(4619) && Backpack.contains(4620) && VarManager.getVarbitValue(13640) == 1) {
                        if (digsitearea.contains(player)) {

                             talktoElissa();
                        } else {
                            DebugScript.moveTo(digsite);
                        }
                    }
                    else if ((Backpack.contains(4619) && Backpack.contains(4620)) && VarManager.getVarbitValue(13640) == 2)
                    {
                        if (!examcenterarea.contains(player) && !Backpack.contains(4616))
                        {
                            DebugScript.moveTo(examcenter);
                            SceneObject bookshelf = SceneObjectQuery.newQuery().name("Bookshelf").results().nearestTo(bookcasecord);
                            if(bookshelf !=null)
                            {
                                bookshelf.interact("Search");
                                delay(RandomGenerator.nextInt(600, 1200));
                            }
                        }
                        else if(Backpack.contains(4616))
                        {
                            Component notes = ComponentQuery.newQuery(1473).item(4616).results().stream().findFirst().orElse(null);
                            if(notes != null)
                            {
                                notes.interact("Read");
                                delay(RandomGenerator.nextInt(1100, 1500));
                            }
                        }
                    }
                    else if (VarManager.getVarbitValue(13640) == 3)
                    {
                        if(!meseum1starea.contains(player))
                        {
                            DebugScript.moveTo(meseum1st);
                        }
                        else
                        {
                            talktoCurator();
                        }
                    }


                    break;
                case 3:
                    if(VarManager.getVarbitValue(13640) == 3 && !Backpack.contains(4617)) {
                        if (!meseum1starea.contains(player)) {
                            DebugScript.moveTo(meseum1st);
                        }
                        else {
                            Npc npc = NpcQuery.newQuery().name("Curator Haig Halen").results().first();
                            if (npc != null) {
                             npc.interact("Pickpocket");
                            }
                        }
                    }
                    else if(VarManager.getVarbitValue(13640) == 3 && Backpack.contains(4617) && VarManager.getVarbitValue(13648) == 0)
                    {
                        if (!museum2ndarea.contains(player)) {
                            DebugScript.moveTo(museum2nd);
                        }
                        else
                        {
                            SceneObject displaycase = SceneObjectQuery.newQuery().name("Display case").option("Open").results().nearest();
                            if(displaycase !=null)
                            {
                                displaycase.interact("Open");
                                delay(RandomGenerator.nextInt(600, 1200));
                            }
                        }
                    }
                    else if (!phoenixfeatherarea.contains(player) && !Backpack.contains(4621) && VarManager.getVarbitValue(13648) == 1) {
                        DebugScript.moveTo(phoenixfeather);
                        }
                    else if(!Backpack.contains(4621) && phoenixfeatherarea.contains(player) && VarManager.getVarbitValue(13648) == 1)
                    {
                        Npc desertph = NpcQuery.newQuery().name("Desert Phoenix").results().nearest();
                        if(desertph != null)
                        {
                            desertph.interact("Grab-feather");
                        }
                    }
                    else if(Backpack.contains(4621) && Backpack.contains(4618) && VarManager.getVarbitValue(13648) == 1)
                    {
                        if (!startarea.contains(player) && Client.getLocalPlayer().getCoordinate().getRegionId() != 10828) {
                            DebugScript.moveTo(startcord);
                        }else if(Backpack.contains(4621) && Backpack.contains(4618) && Client.getLocalPlayer().getCoordinate().getRegionId() == 10828)
                        {
                            SceneObject alcove = SceneObjectQuery.newQuery().name("Alcove").results().nearest();
                            if(alcove != null)
                            {
                                Item statue = InventoryItemQuery.newQuery(93).ids(4618).results().first();
                                if (statue != null && alcove != null)
                                {
                                    if(MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, statue.getSlot(), 96534533)) {
                                        delay(RandomGenerator.nextInt(200, 400));
                                        alcove.interact(SelectableAction.SELECT_OBJECT.getType());
                                        //MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, targetitem.getSlot(), 96534533);
                                        delay(RandomGenerator.nextInt(200, 400));
                                    }
                                }

                            }
                        }
                        else {

                            if(!cavearea.contains(player))
                            {
                                SceneObject stair = SceneObjectQuery.newQuery().name("Staircase").results().nearest();

                                if(stair != null)
                                {
                                    stair.interact("Climb-down");
                                    Execution.delayUntil(RandomGenerator.nextInt(3000, 5000), () -> Client.getLocalPlayer().getCoordinate().getRegionId() == 10828);
                                }

                                if(Client.getLocalPlayer().getCoordinate().getRegionId() == 10828 && !cavearea.contains(player))
                                {
                                    DebugScript.moveTo(cave1);
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    SceneObject statue1 = SceneObjectQuery.newQuery().interactId(6303).results().nearest();
                    SceneObject statue2 = SceneObjectQuery.newQuery().interactId(6304).results().nearest();
                    SceneObject statue3 = SceneObjectQuery.newQuery().interactId(6305).results().nearest();
                    SceneObject statue4 = SceneObjectQuery.newQuery().interactId(6306).results().nearest();
                    if(statue1 !=null && VarManager.getVarbitValue(13642) !=1)
                    {
                        statue1.interact("Turn");
                        delay(RandomGenerator.nextInt(400, 600));
                    }
                    if(statue2 !=null && VarManager.getVarbitValue(13643) !=1)
                    {
                        statue2.interact("Turn");
                        delay(RandomGenerator.nextInt(400, 600));
                    }
                    if(statue3 !=null && VarManager.getVarbitValue(13644) !=0)
                    {
                        statue3.interact("Turn");
                        delay(RandomGenerator.nextInt(400, 600));
                    }
                    if(statue4 !=null && VarManager.getVarbitValue(13645) !=2)
                    {
                        statue4.interact("Turn");
                        delay(RandomGenerator.nextInt(400, 600));
                    }

                    if(VarManager.getVarbitValue(13642) ==1 && VarManager.getVarbitValue(13643) ==1 && VarManager.getVarbitValue(13644) ==0 && VarManager.getVarbitValue(13645) ==2)
                    {
                        SceneObject door = SceneObjectQuery.newQuery().name("Door").results().nearest();
                        if(door !=null)
                        {
                            door.interact("Enter");
                            delay(RandomGenerator.nextInt(1200, 1500));
                        }

                    }
                    break;
                case 5:
                    if(VarManager.getVarbitValue(13642) ==1 && VarManager.getVarbitValue(13643) ==1 && VarManager.getVarbitValue(13644) ==0 && VarManager.getVarbitValue(13645) ==2)
                    {
                        SceneObject door = SceneObjectQuery.newQuery().name("Door").results().nearest();
                        if(door !=null)
                        {
                            door.interact("Enter");
                            delay(RandomGenerator.nextInt(1200, 1500));
                        }

                    }
                      break;
                case 6:
                    if(!startarea.contains(player)) {
                        DebugScript.moveTo(startcord);
                        talktogolem();
                    }
                    else
                    {
                        talktogolem();
                    }
                    break;
                case 7:
                    Item phonenixfeather = InventoryItemQuery.newQuery(93).ids(4621).results().first();
                    Component blackmusroom = ComponentQuery.newQuery(1473).item(4620).results().stream().findFirst().orElse(null);
                    Item ink = InventoryItemQuery.newQuery(93).ids(4622).results().first();
                    Item phonenixfeatherquil = InventoryItemQuery.newQuery(93).ids(4623).results().first();
                    Item papyrus = InventoryItemQuery.newQuery(93).ids(970).results().first();
                    Item golemprogram = InventoryItemQuery.newQuery(93).ids(4624).results().first();
                    Item strangekey = InventoryItemQuery.newQuery(93).ids(4619).results().first();
                    Npc golem1 = NpcQuery.newQuery().name("Clay golem").results().nearest();
                    if(blackmusroom != null && ink == null)
                    {

                        blackmusroom.interact("Grind");
                        delay(RandomGenerator.nextInt(1200, 1500));
                    }

                    if(phonenixfeather !=null && ink !=null)
                    {

                        if (MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, phonenixfeather.getSlot(), 96534533)) {
                            delay(RandomGenerator.nextInt(200, 400));
                            MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, ink.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(200, 400));
                        }
                    }

                    if(phonenixfeatherquil !=null && papyrus !=null)
                    {

                        if (MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, phonenixfeatherquil.getSlot(), 96534533)) {
                            delay(RandomGenerator.nextInt(200, 400));
                            MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, papyrus.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(200, 400));
                        }
                    }

                    if(golemprogram !=null && strangekey !=null)
                    {

                        if (MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, strangekey.getSlot(), 96534533)) {
                            delay(RandomGenerator.nextInt(200, 400));
                            golem1.interact(SelectableAction.SELECT_NPC.getType());
                            delay(RandomGenerator.nextInt(1200, 1500));
                        }

                        if (MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, golemprogram.getSlot(), 96534533)) {
                            delay(RandomGenerator.nextInt(200, 400));
                            golem1.interact(SelectableAction.SELECT_NPC.getType());
                            delay(RandomGenerator.nextInt(200, 400));
                        }
                    }
                    break;
            }
        }
    }

    public static void  Brokenclaygolem() {
        Npc npc = NpcQuery.newQuery().name("Broken clay golem").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void  talktoElissa() {
        Npc npc = NpcQuery.newQuery().name("Elissa").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void  talktoCurator() {
        Npc npc = NpcQuery.newQuery().name("Curator Haig Halen").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void  talktogolem() {
        Npc npc = NpcQuery.newQuery().name("Clay golem").results().first();
        if (npc != null) {
            println("Talk to" + npc.interact(NPCAction.NPC1));
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }


}
