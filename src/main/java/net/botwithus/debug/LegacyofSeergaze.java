package net.botwithus.debug;

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
import net.botwithus.rs3.game.hud.interfaces.Component;
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
import net.botwithus.rs3.game.queries.results.EntityResultSet;
import net.botwithus.rs3.game.queries.results.ResultSet;
import net.botwithus.rs3.input.GameInput;
import static net.botwithus.rs3.game.minimenu.actions.SelectableAction.SELECT_OBJECT;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;
import net.botwithus.api.game.hud.inventories.Bank;



import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.EquipmentInventory;
import net.botwithus.api.game.hud.traversal.Lodestone;
import net.botwithus.api.game.hud.Dialog;

public class LegacyofSeergaze {


    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3439, 3485, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate drezelcoord = new Coordinate(3438, 9895, 0);
    static Area.Circular drezelarea = new Area.Circular(drezelcoord, 10);
    static Coordinate stairwaymosCoordinate = new Coordinate(3424, 9900, 0);
    static Area.Circular stairwaymosarea = new Area.Circular(stairwaymosCoordinate, 2);
    static Coordinate pubcoordbag = new Coordinate(3492, 3229, 0);
    static Area.Circular pubcoordbagarea = new Area.Circular(pubcoordbag, 1);

    static Coordinate zaromakrsilvercoord = new Coordinate(13434, 1864, 2);
    static Area.Circular zaromakrsilverarea = new Area.Circular(zaromakrsilvercoord, 5);
    static Coordinate vertidacoord = new Coordinate(3629, 9641, 0);
    static Area.Circular vertidacoordarea = new Area.Circular(vertidacoord, 2);

    static boolean gatecross = false;
    static boolean wallstorageSearch = false;
    static boolean bookread = false;
    static boolean doorpass = false;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(13978);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Legacy of Seergaze");

            if (startarea.contains(player)) {
                tallktoMercenaryAdventurer();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {

                case 10:
                if(drezelarea.contains(player)) {
                    talktoDrezel();
                }else{
                    DebugScript.moveTo(drezelcoord);
                }
                break;
                case 20:
                SceneObject stairwaymos = SceneObjectQuery.newQuery().name("Stairway").option("Climb-down").results().first();
                SceneObject wallstorage = SceneObjectQuery.newQuery().name("Wall storage").option("Search").results().first();
                SceneObject stairwaymosup = SceneObjectQuery.newQuery().name("Stairs").option("Climb-up").results().first();
                if(!stairwaymosarea.contains(player) && !gatecross && stairwaymos != null) {
                    SceneObject Gate = SceneObjectQuery.newQuery().name("Gate").option("Open").results().nearest();
                    if(Gate != null ) {
                        Gate.interact("Open");
                        delay(RandomGenerator.nextInt(600, 800));
                        gatecross = true;
                    }
                    
                }
                // else if (gatecross) {
                //     Movement.walkTo(stairwaymos.getCoordinate().getX(), stairwaymos.getCoordinate().getY(), true);
                // }
                else if(stairwaymos != null && gatecross) {
                    stairwaymos.interact("Climb-down");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(wallstorage != null && !wallstorageSearch) {
                    wallstorage.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                    wallstorageSearch = true;
                }
                else if(stairwaymosup != null && wallstorageSearch) {
                    if(!zaromakrsilverarea.contains(player)) {
                        DebugScript.moveTo(zaromakrsilvercoord);
                    }
                    else {
                        
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    // stairwaymosup.interact("Climb-up");
                    // delay(RandomGenerator.nextInt(600, 800));
                }
                 
                break;
                case 40:
                SceneObject crudetable = SceneObjectQuery.newQuery().name("Crude table").option("Search").results().first();
                if(crudetable != null) {
                    crudetable.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 50:
                SceneObject crudetabletelport = SceneObjectQuery.newQuery().name("Crude table").option("Search").results().first();
                if(crudetabletelport != null) {
                    Lodestone.CANIFIS.teleport();
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(drezelarea.contains(player)) {
                    talktoDrezel();
                }
                else {
                    DebugScript.moveTo(drezelcoord);
                }
                break;
                case 60:
                SceneObject path = SceneObjectQuery.newQuery().name("Path").option("Continue (Easy)").results().first();
                if(path != null) {
                    path.interact("Continue (Easy)");
                    println("Play the game manually");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(startarea.contains(player) && path == null) {
                    tallktoMercenaryAdventurer();
                }
                else if (path == null) {
                    DebugScript.moveTo(startcord);
                }
                break;
                case 75:
                talktoveiafpub();
                break;
                case 80:
                talktoVeliafHurtz();
                break;
                case 90:
                SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().first();
                SceneObject brokendownwall3 = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();

                if(ladder != null && brokendownwall3 == null) {
                    ladder.interact("Climb-up");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(brokendownwall3 != null && player.getY() > 3230) {
                    brokendownwall3.interact("Climb-over");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(player.getY() <= 3230) {
                    // if(!vertidacoordarea.contains(player)) {
                    //     DebugScript.moveTo(vertidacoord);
                    // }
                    // else {
                    //     println("Take a boat from shore an go to hideout manually");
                    //     delay(RandomGenerator.nextInt(600, 800));
                    // }

                    println("Take a boat from shore an go to hideout and talk to Lfaygian Screte manually");  // Quest varps value change to 100 and varbit 13986 to 1 after talking to Lfaygian Screte
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 110:
                talktokaelforshaw();
                break;
                case 120:
                //Coordinate bunkbedcoord = new Coordinate(3424, 9900, 0);
                SceneObject bunkbed = SceneObjectQuery.newQuery().name("Bunk bed").id(30491).option("Search").results().first();
                if(bunkbed != null) {
                    bunkbed.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 130:
                if(!bookread) {
                    Backpack.interact("Combat book", "Read");
                    delay(RandomGenerator.nextInt(600, 800));
                    bookread = true;
                }
                else 
                {
                    talktoLfaygianScrete();
                    if(Interfaces.isOpen(1469))
                    {
                        GameInput.setIntInput(10);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 140, 150:
                if(Interfaces.isOpen(1469))
                    {
                        GameInput.setIntInput(10);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                break;
                case 160:
                println(" Perform Steps manually to create blessed sickle and use to on Flaygian from Quest varp 160 to 270");
                break;
                case 270:
                println("Use blessed sickle on Flaygian");
                talktoLfaygianScrete();
                
                break;
                case 280:
                talktoSafalaan();
                break;
                case 290:
                println(" Go to Laboratory area manually");
                SceneObject safalaanbody = SceneObjectQuery.newQuery().name("Safalaan").option("Look-at").results().first();
                if(safalaanbody != null) {
                    safalaanbody.interact("Look-at");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 300:
                SceneObject doorserach = SceneObjectQuery.newQuery().name("Door").option("Search").results().first();
                if(doorserach != null && !Interfaces.isOpen(1179)) {
                    doorserach.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(Interfaces.isOpen(1179)) {
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0,-1,77266971);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 310:
                SceneObject door = SceneObjectQuery.newQuery().name("Door").option("Climb-through").results().first();
                if(door != null) {
                    door.interact("Climb-through");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 320:
                println(" Move to cofin area manually");
                break;
                case 325:
                if(Backpack.getQuantity("Fire rune") <5 && Backpack.getQuantity("Law rune") <1 && Backpack.getQuantity("Cosmic rune") <1) {
                    SceneObject corpse = SceneObjectQuery.newQuery().name("Corpse").option("Search").results().first();
                if(corpse != null) {
                    corpse.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                }
                else 
                {
                    SceneObject strangestone = SceneObjectQuery.newQuery().name("Strange stones").option("Search").results().first();
                    if(strangestone != null) {
                        strangestone.interact("Search");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                
                break;
                case 330:
                talktoSafalaan();
                break;
                case 340:
                break;
                case 350:
                talktoSafalaan();
                break;
                case 360:
                Item emerald = InventoryItemQuery.newQuery().name("Emerald").results().first();
                Item sickle = InventoryItemQuery.newQuery().name("Silver sickle(b)").results().first();
                Item emerladsickle = InventoryItemQuery.newQuery().ids(13155).results().first();
                if(!Backpack.contains(13155)) {
                    if(emerald != null && sickle != null) {
                        boolean result = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, emerald.getSlot(), 96544533);
                        if(result) {
                            delay(RandomGenerator.nextInt(600, 800));
                            MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, sickle.getSlot(), 96544533);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else if(Backpack.contains(13155)) {
                    boolean usespell = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, 29, 95748097);
                    if(usespell) {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, emerladsickle.getSlot(), 95748097);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                
                break;
                case 370:
                break;
                case 380:
                Item ehcnahtedemerladsickle = InventoryItemQuery.newQuery(93).ids(13156).results().first();
                Item chain = InventoryItemQuery.newQuery(93).ids(13154).results().first();
                if(ehcnahtedemerladsickle != null && chain != null) {
                    
                    boolean result = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, chain.getSlot(), 96544533);
                    if(result) {
                        delay(RandomGenerator.nextInt(600, 800));
                        MiniMenu.interact(SelectableAction.SELECT_COMPONENT_ITEM.getType(), 0, ehcnahtedemerladsickle.getSlot(), 96544533);
                    delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 390:
                talktoSafalaan();
                break;
                case 400, 410:
                println("Kill vyrewatch and pick up corpse and brinng back to safalaan");
                break;
                case 420:
                talktoveiafpub();
                break;
                case 430:
                if(!drezelarea.contains(player)) {
                    DebugScript.moveTo(drezelcoord);
                }
                else {
                    talktoDrezel();
                }
                break;
                case 440:
                SceneObject gate1 = SceneObjectQuery.newQuery().name("Gate").option("Open").results().first();
                SceneObject stairwaysecond = SceneObjectQuery.newQuery().name("Stairway").option("Climb-down").results().first();
                if(gate1 != null && !doorpass) {
                    gate1.interact("Open");
                    delay(RandomGenerator.nextInt(600, 800));
                    doorpass = true;
                }
                else if(doorpass && stairwaysecond != null)
                {
                    
                    if(stairwaysecond != null) {
                        stairwaysecond.interact("Climb-down");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if(stairwaysecond == null) {
                    SceneObject funeralpyre = SceneObjectQuery.newQuery().name("Funeral pyre").option("Prepare").results().first();
                    SceneObject funeralpyre2 = SceneObjectQuery.newQuery().name("Funeral pyre").option("Add corpse").results().first();
                    if(funeralpyre != null) {
                        funeralpyre.interact("Prepare");
                        delay(RandomGenerator.nextInt(600, 800));
                    }

                }
                break;
                case 450:
                SceneObject funeralpyre2 = SceneObjectQuery.newQuery().name("Funeral pyre").option("Add corpse").results().first();
                if(funeralpyre2 != null) {
                    funeralpyre2.interact("Add corpse");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 460:
                SceneObject funeralpyre3 = SceneObjectQuery.newQuery().name("Funeral pyre").option("Light").results().first();
                if(funeralpyre3 != null) {
                    funeralpyre3.interact("Light");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 470:
                if(!Backpack.contains(13159)) {
                    GroundItem Ornatetombkey = GroundItemQuery.newQuery().name("Ornate tomb key").results().first();
                    if(Ornatetombkey != null) {
                        Ornatetombkey.interact("Take");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                else if (Backpack.contains(13159)) {
                    SceneObject  wallstoragesecond = SceneObjectQuery.newQuery().name("Wall storage").option("Search").results().first();
                    Item Ornatetombkey = InventoryItemQuery.newQuery().ids(13159).results().first();
                    if(wallstoragesecond != null && Ornatetombkey != null) {
                        boolean result = MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, Ornatetombkey.getSlot(), 96544533);
                        if(result) {
                            delay(RandomGenerator.nextInt(600, 800));
                            MiniMenu.interact(SELECT_OBJECT.getType(), 30622, wallstoragesecond.getCoordinate().getX(),wallstoragesecond.getCoordinate().getY());
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }

                }
                break;
                case 490:
                if(!drezelarea.contains(player)) {
                    DebugScript.moveTo(drezelcoord);
                }
                else {
                    talktoDrezel();
                }
                break;
                case 495:
                talktoveiafpub();
                break;
                case 500:
                break;
            }
        }
    }


    public static void tallktoMercenaryAdventurer()
    {
        Npc mercenaryAdventurer = NpcQuery.newQuery().name("Mercenary Adventurer").results().first();
        if (mercenaryAdventurer != null) {
            mercenaryAdventurer.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoDrezel() {
        Npc drezel = NpcQuery.newQuery().name("Drezel").results().first();
        if(drezel != null) {
            drezel.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoveiafpub() {
        Npc VeliafHurtz1 = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
                SceneObject brokendownwall3 = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();
                    SceneObject trapdoorpub = SceneObjectQuery.newQuery().name("Trapdoor").option("Climb-down").results().first();
                    if(!pubcoordbagarea.contains(player) && VeliafHurtz1 == null && brokendownwall3 == null) {
                        DebugScript.moveTo(pubcoordbag);
                    }
                    else {
                    
                    if(brokendownwall3 != null && VeliafHurtz1 == null) {
                        if(player.getY() > 3230) {
                            trapdoorpub.interact("Climb-down");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        else {
                            brokendownwall3.interact("Climb-over");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                        
                    }
                    else if(VeliafHurtz1 != null) {
                        talktoVeliafHurtz();
                    }
                    }
    }

    public static void talktoVeliafHurtz()
    {
        Npc VeliafHurtz = NpcQuery.newQuery().name("Veliaf Hurtz").results().first();
        if (VeliafHurtz != null) {
            VeliafHurtz.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoLfaygianScrete() {
        Npc LfaygianScrete = NpcQuery.newQuery().name("Flaygian Screwte").results().first();
        if (LfaygianScrete != null) {
            LfaygianScrete.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktokaelforshaw() {
        Npc KaelForshaw = NpcQuery.newQuery().name("Kael Forshaw").results().first();
        if (KaelForshaw != null) {
            KaelForshaw.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoSafalaan() {
        Npc Safalaan = NpcQuery.newQuery().name("Safalaan").results().first();
        if (Safalaan != null) {
            Safalaan.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }


}
