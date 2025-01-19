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

public class TheBranchesofDarkmeyer {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3490, 3231, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate pubcoordbag = new Coordinate(3492, 3229, 0);
    static Area.Circular pubcoordbagarea = new Area.Circular(pubcoordbag, 1);

    static Coordinate mekrituscoord = new Coordinate(3602, 3263, 1);
    static Area.Circular mekritusarea = new Area.Circular(mekrituscoord, 10);

    static Coordinate nessiecoord = new Coordinate(3604, 3259, 0);
    static Area.Circular nessiearea = new Area.Circular(nessiecoord, 3);
    static Coordinate vertidacoord = new Coordinate(3629, 9641, 0);
    static Area.Circular vertidacoordarea = new Area.Circular(vertidacoord, 2);

    static Coordinate vancesculacoord = new Coordinate(3632, 3332, 0);
    static Area.Circular vancesculacoordarea = new Area.Circular(vancesculacoord, 3);
    static Coordinate Sanguinuscoord = new Coordinate(3667, 3380, 0);
    static Area.Circular Sanguinusarea = new Area.Circular(Sanguinuscoord, 3);

    static boolean talktosentinel = false;
    static boolean talktosentinel2 = false;

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(11020);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... The Branches of Darkmeyer");

            talktoveiafpub();

        } else {
            switch (QuestVarp) {

                case 5:
                SceneObject caveentrance = SceneObjectQuery.newQuery().name("Cave entrance").option("Enter").results().first();
                SceneObject ladderup = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().first();
                SceneObject brokendownwall3 = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();
                if(ladderup != null && brokendownwall3 == null) {
                    ladderup.interact("Climb-up");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(brokendownwall3 != null && player.getY() > 3230) {
                    brokendownwall3.interact("Climb-over");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(caveentrance != null) {
                    caveentrance.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 7:
                GroundItem letter = GroundItemQuery.newQuery().name("Letter").results().first();    
                SceneObject coffin = SceneObjectQuery.newQuery().name("Coffin").option("Search").results().first();
                if(letter != null) {
                    letter.interact("Take");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(coffin != null) {
                    coffin.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 10:
                talktoveiafpub();
                break;
                case 15:
                talktoSafalaan();
                break;
                case 20:
                break;
                case 25:
                println("Manualy travel to mekritus");
                if(!mekritusarea.contains(player)) {
                    DebugScript.moveTo(mekrituscoord);
                }
                else {
                    talktoMekritus();
                }
                break;
                case 30:
                if(!mekritusarea.contains(player)) {
                    DebugScript.moveTo(mekrituscoord);
                }
                else {
                    talktoMekritus();
                }
                break;
                case 35:
                SceneObject floorboards = SceneObjectQuery.newQuery().name("Floorboards").option("Drop-down").hidden(false).results().first();
                SceneObject door = SceneObjectQuery.newQuery().name("Door").option("Open").hidden(false).results().first();
                if(floorboards != null) {
                    floorboards.interact("Drop-down");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(floorboards == null && !nessiearea.contains(player)) {
                    DebugScript.moveTo(nessiecoord);
                }
                else {
                    talktonessie();
                }
                break;
                case 40:
                Npc vancesculadrakan = NpcQuery.newQuery().name("Vanescula Drakan").results().nearest();
                if(vancesculadrakan != null) {
                    vancesculadrakan.interact("Attack");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 45:
                talktoVancesculadrakan();
                break;
                case 55:
                // if(!vertidacoordarea.contains(player)) {
                //     DebugScript.moveTo(vertidacoord);
                // }
                // else {
                //     talktovertida();
                // }
                Npc vertida = NpcQuery.newQuery().name("Vertida Sefalatis").results().first();
                Npc Safalaan = NpcQuery.newQuery().name("Safalaan").results().first();
                if(Safalaan != null) {
                    Safalaan.interact(NPCAction.NPC1);
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else {
                    Component medallion = ComponentQuery.newQuery(1473).item(21575).results().stream().findFirst().orElse(null);
                    if(medallion != null && !Interfaces.isOpen(720)) {
                        medallion.interact("Teleport");
                        delay(RandomGenerator.nextInt(600, 800));
                    }

                    if(Interfaces.isOpen(720))
                    {
                        MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 47185940);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                break;
                case 60:
                talktovertida();
                break;
                case 65:
                SceneObject crates = SceneObjectQuery.newQuery().name("Crate").ids(61298).option("Search").results().first();
                if(crates != null) {
                    crates.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                break;
                case 70:
                println(" Navigate to Vanescula manually");
                break;
                case 75:
                talktoVancesculadrakan();
                break;
                case 80:
                println(" Manually Search the chest in the building using smoke bombs");
                break;
                case 85:
                if(!vancesculacoordarea.contains(player)) {
                    DebugScript.moveTo(vancesculacoord);
                }
                else {
                    talktoVancesculadrakan();
                }
                break;
                case 90:
                if(VarManager.getVarbitValue(11031) ==0){
                    if(!Sanguinusarea.contains(player)) {
                        DebugScript.moveTo(Sanguinuscoord);
                    }
                    else {
                        talktoSanguinus();
                    }
                }
                else if(VarManager.getVarbitValue(11031) ==1 && VarManager.getVarbitValue(11026) <25)
                {
                    SceneObject propagandaposter = SceneObjectQuery.newQuery().name("Propaganda poster").option("Vandalise").results().nearest();
                    Npc bloodveldyoungling = NpcQuery.newQuery().name("Bloodveld youngling").option("Pick-up").results().nearest();
                    if(propagandaposter != null) {
                        propagandaposter.interact("Vandalise");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    else if(bloodveldyoungling != null) {
                        bloodveldyoungling.interact("Pick-up");
                        delay(RandomGenerator.nextInt(600, 800));
                    }

                }
                else if(VarManager.getVarbitValue(11031) ==1 && VarManager.getVarbitValue(11026) <37)
                {
                    Npc MariaGadderanks = NpcQuery.newQuery().name("Maria Gadderanks").results().first();
                    if(MariaGadderanks != null) {
                        MariaGadderanks.interact(NPCAction.NPC1);
                        delay(RandomGenerator.nextInt(600, 800));
                    
                    }
                    // Npc SentinelDraemus = NpcQuery.newQuery().name("Sentinel Draemus").results().first();
                    // if(SentinelDraemus != null && talktosentinel == false) {
                    //     SentinelDraemus.interact(NPCAction.NPC1);
                    //     delay(RandomGenerator.nextInt(600, 800));
                    //     talktosentinel = true;
                    // }
                    // else if(talktosentinel == true) {
                    //     Npc SentinelGorthaur = NpcQuery.newQuery().name("Sentinel Gorthaur").results().first();
                    //     if(SentinelGorthaur != null) {
                    //         SentinelGorthaur.interact(NPCAction.NPC1);
                    //         delay(RandomGenerator.nextInt(600, 800));
                    //     }
                    // }
                }
                else if(VarManager.getVarbitValue(11031) ==1 && VarManager.getVarbitValue(11026) <50)
                {
                    Npc ValentinaKaust = NpcQuery.newQuery().name("Valentina Kaust").results().first();
                    if(ValentinaKaust != null) {
                        ValentinaKaust.interact(NPCAction.NPC1);
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
                
                
                break;
                case 95:
                println("Gain Reputation manually");
                break;
                case 105:
                println("Play thhe game manually");
                break;
                case 110:
                SceneObject blisterwoodtree = SceneObjectQuery.newQuery().name("Blisterwood Tree").option("Chop").results().first();
                if(blisterwoodtree != null) {
                    blisterwoodtree.interact("Chop");
                    delay(RandomGenerator.nextInt(1200, 1800));
                }
                break;
                case 120:
                SceneObject arboretumdoor = SceneObjectQuery.newQuery().name("Arboretum Door").option("Open").results().nearest();
                
                if(arboretumdoor != null) {
                    arboretumdoor.interact("Open");
                    delay(RandomGenerator.nextInt(1200, 1800));
                }
                talktoVancesculadrakan();   
                break;
                case 125:
                talktovertida();
                break;
                case 135:
                talktovertida();
                break;
                case 140:
                if(!Backpack.contains(2961)) {
                    talktokaelforshaw();
                }
                else
                {
                    println("Teleport to Darkmeyer and craft blisterwood weapons");
                }
                break;
                case 145:
                println("Kil Harold with any weapon and throw water at him");
                break;
                case 150, 155:
                println("Perform boss fight manually");
                break;
                case 160:
                talktovertida();
                break;
                case 165:
                talktoveiafpub();
                break;
               
            }
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

    public static void gooutsidepub(){
        
                SceneObject ladderup = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().first();
                SceneObject brokendownwall3 = SceneObjectQuery.newQuery().name("Broken down wall").option("Climb-over").results().first();
                if(ladderup != null && brokendownwall3 == null) {
                    ladderup.interact("Climb-up");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(brokendownwall3 != null && player.getY() > 3230) {
                    brokendownwall3.interact("Climb-over");
                    delay(RandomGenerator.nextInt(600, 800));
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

    public static void talktoSafalaan() {
        Npc Safalaan = NpcQuery.newQuery().name("Safalaan").results().first();
        if (Safalaan != null) {
            Safalaan.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoMekritus() {
        Npc Mekritus = NpcQuery.newQuery().name("Mekritus A'hara").results().first();
        if (Mekritus != null) {
            Mekritus.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktonessie() {
        Npc Nessie = NpcQuery.newQuery().name("Nessie").results().first();
        if (Nessie != null) {
            Nessie.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoVancesculadrakan() {
        Npc VancesculaDrakan = NpcQuery.newQuery().name("Vanescula Drakan").results().first();
        if (VancesculaDrakan != null) {
            VancesculaDrakan.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktovertida() {
        Npc vertida = NpcQuery.newQuery().name("Vertida Sefalatis").results().first();
        if(vertida != null) {
            vertida.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoSanguinus() {
        Npc Sanguinus = NpcQuery.newQuery().name("Sanguinus Varnis").results().first();
        if(Sanguinus != null) {
            Sanguinus.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktokaelforshaw() {
        Npc Kaelforshaw = NpcQuery.newQuery().name("Kael Forshaw").results().first();
        if(Kaelforshaw != null) {
            Kaelforshaw.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

}

