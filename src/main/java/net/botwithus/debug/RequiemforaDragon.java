package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.input.GameInput;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
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
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.actionbar.ActionBar;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.queries.results.EntityResultSet;

import static net.botwithus.debug.DebugScript.currentQuest;
import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Bank;
import net.botwithus.api.game.hud.traversal.Lodestone;

public class RequiemforaDragon {

        static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3302, 3569, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate grove = new Coordinate(3344, 3551, 0);
    static Area.Circular grovearea = new Area.Circular(grove, 10);
    static Coordinate death  = new Coordinate(1102, 1776, 1);
    static Area.Circular deatharea = new Area.Circular(death, 10);
    static Coordinate treeofblance = new Coordinate(3318, 3342, 0);
    static Area.Circular treeofblancearea = new Area.Circular(treeofblance, 10);
    static Coordinate hallofmemories = new Coordinate(2287, 3539, 0);
    static Area.Circular hallofmemoriesarea = new Area.Circular(hallofmemories, 10);
    static Coordinate archworkbench = new Coordinate(3356, 3395, 0);
    static Area.Circular archworkbencharea = new Area.Circular(archworkbench, 10);
    static Coordinate ritualcord = new Coordinate(1038, 1772, 1);
    static Area.Circular ritualarea = new Area.Circular(ritualcord, 20);
    static Coordinate vorkathcity = new Coordinate(1137, 1720, 1);
    static Area.Circular vorkathcityarea = new Area.Circular(vorkathcity, 20);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(55367);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            
                return;
            
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Requiem for a Dragon");

            if (startarea.contains(player)) {
                talktoAster();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 5:
                SceneObject rowboat = SceneObjectQuery.newQuery().name("Rowboat").option("Interact").results().first();
                if(!grovearea.contains(player)) {
                    DebugScript.moveTo(grove);
                }else{      
                if (rowboat != null) {
                    rowboat.interact("Interact");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                }
            break;
            case 7:
            Npc zamorakianscout = NpcQuery.newQuery().name("Zamorakian scout").results().nearest();
            SceneObject Colossalcave = SceneObjectQuery.newQuery().name("Colossal cave").option("Enter").results().first();
           // SceneObject Vorkath = SceneObjectQuery.newQuery().name("Vorkath").option("Approach").results().first();
            SceneObject Pedestal = SceneObjectQuery.newQuery().name("Pedestal").results().first();
            Npc Vorkath = NpcQuery.newQuery().name("Vorkath").option("Approach").results().first();
            if(Colossalcave != null){
                Colossalcave.interact("Enter");
                delay(RandomGenerator.nextInt(600, 800));
            }
            else if(Pedestal != null && Vorkath == null){
                Movement.walkTo(Pedestal.getCoordinate().getX(), Pedestal.getCoordinate().getY(), true);
                delay(RandomGenerator.nextInt(600, 800));
            }
            else if(Vorkath != null){
               Vorkath.interact("Approach");
                delay(RandomGenerator.nextInt(600, 800));
            }
            break;
            case 9:
            if(Backpack.contains("Crystal shard")){
                Backpack.interact("Crystal shard", "Inspect");
                delay(RandomGenerator.nextInt(600, 800));
            }
            break;
            case 11:
            SceneObject stairs = SceneObjectQuery.newQuery().name("Stairs").option("Climb down").results().first();
            SceneObject ruinsentrance = SceneObjectQuery.newQuery().name("Ruins Entrance").option("Interact").results().nearest();
            SceneObject imposingstatue = SceneObjectQuery.newQuery().name("Imposing Statue").option("Inspect").hidden(false).results().first();
            SceneObject doorway = SceneObjectQuery.newQuery().name("Doorway").option("Interact").results().nearest();
            Npc Translator = NpcQuery.newQuery().name("Translator").results().first();
            if(stairs != null){
                stairs.interact("Climb down");
                delay(RandomGenerator.nextInt(600, 800));
            }
            else if(ruinsentrance != null){
                ruinsentrance.interact("Interact");
                delay(RandomGenerator.nextInt(600, 800));
            }
            if(VarManager.getVarbitValue(55378) == 0){
                if(imposingstatue != null){
                    imposingstatue.interact("Inspect");
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }else if(VarManager.getVarbitValue(55378) == 1 && VarManager.getVarbitValue(55379) == 0){
                if(doorway != null){
                    doorway.interact("Interact");
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
            else if(VarManager.getVarbitValue(55378) == 1 && VarManager.getVarbitValue(55379) == 1 && VarManager.getVarbitValue(55373) == 0){
                if(Translator != null){
                    Translator.interact("Attack");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(Translator == null){
                    GroundItem item = GroundItemQuery.newQuery().name("Zamorakian translator's notes").results().nearest();

                    if(item != null && !Backpack.contains(56762))
                    {
                        item.interact("Take");
                        delay(RandomGenerator.nextInt(600, 1200));
                        if (Interfaces.isOpen(1622)) {
                            Item items = InventoryItemQuery.newQuery(773).ids(56762).results().first();
                            if (items != null) {
                                println("Item Slot Picking up" + items.getName()+ " " + items.getSlot());
                                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                delay(RandomGenerator.nextInt(600, 1200));
                                return;
                            }
                        }
                    }
                    else if(Backpack.contains(56762)){
                        Backpack.interact("Zamorakian translator's notes", "Read");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                }
            }
            else if(VarManager.getVarbitValue(55373) == 1 && !Backpack.contains(56768)){
                SceneObject Bookcase = SceneObjectQuery.newQuery().name("Bookcase").option("Search").results().first();
                if(Bookcase != null){
                    Bookcase.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
            } else if((VarManager.getVarbitValue(55373) == 1 && Backpack.contains(56768) && !Backpack.contains(56765)))
            {
                SceneObject Shelves = SceneObjectQuery.newQuery().name("Shelves").option("Search").results().first();
                if(Shelves != null){
                    Shelves.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }else if((VarManager.getVarbitValue(55373) == 1 && Backpack.contains(56768) && Backpack.contains(56765) && !Backpack.contains(56764)))
            {
                GroundItem item = GroundItemQuery.newQuery().name("Congealed potion").results().nearest();

                    if(item != null && !Backpack.contains(56764))
                    {
                        item.interact("Take");
                        delay(RandomGenerator.nextInt(600, 1200));
                        if (Interfaces.isOpen(1622)) {
                            Item items = InventoryItemQuery.newQuery(773).ids(56764).results().first();
                            if (items != null) {
                                println("Item Slot Picking up" + items.getName()+ " " + items.getSlot());
                                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, items.getSlot(), 106299403);
                                delay(RandomGenerator.nextInt(600, 1200));
                                return;
                            }
                        }
                    }
                // SceneObject dragonkinmural = SceneObjectQuery.newQuery().name("Dragonkin mural").option("Inspect").results().first();
                // if(dragonkinmural != null){
                //     dragonkinmural.interact("Inspect");
                //     delay(RandomGenerator.nextInt(600, 800));
                // }
            }
            else if((VarManager.getVarbitValue(55373) == 1 && Backpack.contains(56768) && Backpack.contains(56765) && Backpack.contains(56764)))
            {
                Npc Archivist = NpcQuery.newQuery().name("Archivist").results().first();
                if(Archivist != null){
                    Archivist.interact("Listen to");
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
            
            // if(Translator != null){
            //     Translator.interact("Attack");
            //     delay(RandomGenerator.nextInt(600, 800));
            // }
            break;
            case 14:
            Npc Archivist = NpcQuery.newQuery().name("Archivist").results().first();
            Npc Zemouregal = NpcQuery.newQuery().name("Zemouregal").results().first();
            if(Archivist != null){
                Lodestone.FORT_FORINTHRY.teleport();
                delay(RandomGenerator.nextInt(600, 800));
            }
            else if(!startarea.contains(player) && Archivist == null && Zemouregal == null){
                DebugScript.moveTo(startcord);
            }
            else
            {
                SceneObject floorhatch = SceneObjectQuery.newQuery().name("Floor hatch").results().nearest();
                
                if(floorhatch != null && Zemouregal == null)
                {
                    floorhatch.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(Zemouregal != null)
                {
                    Zemouregal.interact("Talk to");
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
            break;
            case 26:
            // SceneObject ladder = SceneObjectQuery.newQuery().name("Ladder").option("Climb").results().first();
            // if(ladder != null){
            //     ladder.interact("Climb");
            //     delay(RandomGenerator.nextInt(600, 800));
            // }
            if(!deatharea.contains(player)){
                DebugScript.moveTo(death);
            }
            else {
                talktoDeath();
            }
            
            break;
            case 29:
            if(!treeofblancearea.contains(player)){
                DebugScript.moveTo(treeofblance);
            }
            else {
                treeofblance();
            }
            break;
            case 32:
            if(!hallofmemoriesarea.contains(player) && !Backpack.contains(56761) && !Backpack.contains(56760)){
                DebugScript.moveTo(hallofmemories);
            }
            else {
                SceneObject fertilesoil = SceneObjectQuery.newQuery().name("Fertile soil").option("Uncover").results().nearest();
                SceneObject materialstoragecontainer = SceneObjectQuery.newQuery().name("Material storage container").option("Store").results().nearest();
                SceneObject runicdebris = SceneObjectQuery.newQuery().name("Runic debris").option("Excavate").results().nearest();
                SceneObject standingstone = SceneObjectQuery.newQuery().name("Standing stone debris").option("Excavate").results().nearest();
                if(fertilesoil != null){
                    fertilesoil.interact("Uncover");
                    Execution.delayUntil(3000, () -> Client.getLocalPlayer().getAnimationId() != -1);
                    
                }
                else if(runicdebris != null && !Backpack.isFull() && !Backpack.contains(56760)){
                    runicdebris.interact("Excavate");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(standingstone != null && !Backpack.isFull() && !Backpack.contains(56761) && Backpack.contains(56760))
                {
                    standingstone.interact("Excavate");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(Backpack.contains(56761) && Backpack.contains(56760)){
                    if(!treeofblancearea.contains(player)){
                        DebugScript.moveTo(treeofblance);
                    }
                    else {
                        treeofblance();
                    }
                }
                else if(Backpack.isFull()){
                    materialstoragecontainer.interact("Deposit all");
                    delay(RandomGenerator.nextInt(600, 800));
                }


            }
            break;

            case 35:
            if(!archworkbencharea.contains(player) && Backpack.contains(56759)){
                DebugScript.moveTo(archworkbench);
            }
            else if(Backpack.contains(56758))
            {
                if(!treeofblancearea.contains(player)){
                    DebugScript.moveTo(treeofblance);
                }
                else {
                    treeofblance();
                }
            }
            else {
                if(Backpack.contains(56759) && !Backpack.contains("Greater ensouled bar"))
                {
                    SceneObject bankchest = SceneObjectQuery.newQuery().name("Bank chest").results().first();
                    if(bankchest != null){
                        bankchest.interact("Use");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Interfaces.isOpen(517)){
                            Bank.withdraw("Greater ensouled bar", 3);
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                }
                else if (Backpack.contains(56759) && Backpack.contains("Greater ensouled bar"))
                {
                    println("Restoring Greater ensouled bar");
                    SceneObject workbench = SceneObjectQuery.newQuery().name("Archaeologist's workbench").results().nearest();
                    if(workbench != null){
                        workbench.interact("Restore");
                        delay(RandomGenerator.nextInt(600, 800));
                        if(Interfaces.isOpen(1370)){
                            boolean success = MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                            if(success){
                                delay(RandomGenerator.nextInt(600, 800));
                            }
                        }
                    }
                }
            }
        
            break;
            case 41:
            if(!deatharea.contains(player)){
                DebugScript.moveTo(death);
            }
            else {
                talktoDeath();
            }
            break;
            case 47:
            if(Backpack.contains(56758) && Backpack.getQuantity("Basic ghostly ink") < 4 && Backpack.getQuantity("Greater ghostly ink") < 16 && Backpack.getQuantity("Regular ghostly ink") < 10  && Backpack.getQuantity("Basic ritual candle") < 12)
            {
                SceneObject bankcounter = SceneObjectQuery.newQuery().name("Bank counter").results().nearest();
                if(bankcounter != null){
                    bankcounter.interact("Bank");
                    delay(RandomGenerator.nextInt(600, 800));
                    if(Interfaces.isOpen(517)){
                        Bank.withdraw("Basic ghostly ink", 1);
                        delay(RandomGenerator.nextInt(600, 800));
                        Bank.withdraw("Greater ghostly ink", 1);
                        delay(RandomGenerator.nextInt(600, 800));
                        Bank.withdraw("Regular ghostly ink", 1);
                        delay(RandomGenerator.nextInt(600, 800));
                        Bank.withdraw("Basic ritual candle", 1);
                        delay(RandomGenerator.nextInt(600, 800));
                        
                    }
                }

            }
            else if(Backpack.contains(56758) && Backpack.getQuantity("Basic ghostly ink") >= 4 && Backpack.getQuantity("Greater ghostly ink") >= 16 && Backpack.getQuantity("Regular ghostly ink") >= 10  && Backpack.getQuantity("Basic ritual candle") >= 12)
            {
                if(!ritualarea.contains(player)){
                    DebugScript.moveTo(ritualcord);
                }
                else {
                    println("Ritual area reached");
                }
            }
            break;
            case 50:
            SceneObject rowboat1 = SceneObjectQuery.newQuery().name("Rowboat").option("Interact").results().first();
            SceneObject Colossalcave1 = SceneObjectQuery.newQuery().name("Colossal cave").option("Enter").results().first();
            Npc runiedglyph = NpcQuery.newQuery().name("Ruined glyph").option("Repair glyph spot").results().first();
            Npc runiedplatform = NpcQuery.newQuery().name("Ruined platform").option("Repair light source spot").results().first();
            SceneObject ancientritualpedestal = SceneObjectQuery.newQuery().name("Ancient ritual pedestal").option("Repair pedestal").results().first();
            SceneObject ancientritualplatform = SceneObjectQuery.newQuery().name("Ancient ritual platform").option("Repair platform").results().first();
            SceneObject Pedestal1 = SceneObjectQuery.newQuery().name("Pedestal").results().first();
            if(Backpack.contains(56758) && !grovearea.contains(player) && Colossalcave1 == null && Pedestal1 == null){
                DebugScript.moveTo(grove);
            }
            else if(Colossalcave1 != null && Pedestal1 == null){
                Colossalcave1.interact("Enter");
                delay(RandomGenerator.nextInt(600, 800));
            }
            else if(Pedestal1 != null && Colossalcave1 == null && runiedglyph == null && runiedplatform == null && ancientritualpedestal == null && ancientritualplatform == null){
                Movement.walkTo(Pedestal1.getCoordinate().getX(), Pedestal1.getCoordinate().getY(), true);
                delay(RandomGenerator.nextInt(600, 800));
            }
            else if(runiedglyph != null && runiedplatform != null && ancientritualpedestal != null && ancientritualplatform != null){
                runiedglyph.interact("Repair glyph spot");
                Execution.delayUntil(3000, () -> Client.getLocalPlayer().getAnimationId() == -1);
                delay(RandomGenerator.nextInt(600, 800));
                runiedplatform.interact("Repair light source spot");
                Execution.delayUntil(3000, () -> Client.getLocalPlayer().getAnimationId() == -1);
                delay(RandomGenerator.nextInt(600, 800));
                ancientritualpedestal.interact("Repair pedestal");
                Execution.delayUntil(3000, () -> Client.getLocalPlayer().getAnimationId() == -1);
                delay(RandomGenerator.nextInt(600, 800));
                ancientritualplatform.interact("Repair platform");
                Execution.delayUntil(3000, () -> Client.getLocalPlayer().getAnimationId() == -1);
                delay(RandomGenerator.nextInt(600, 800));
            }
            else {
                
                      
                if (rowboat1 != null) {
                    rowboat1.interact("Interact");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                
            }
            break;
            case 53:
            EntityResultSet<Npc> runiedglyph1 = NpcQuery.newQuery().name("Ruined glyph").option("Repair glyph spot").results();
            EntityResultSet<Npc> runiedplatform1 = NpcQuery.newQuery().name("Ruined platform").option("Repair light source spot").results();
            EntityResultSet<SceneObject> ancientritualpedestal1 = SceneObjectQuery.newQuery().name("Ancient ritual pedestal").option("Repair pedestal").results();
            EntityResultSet<SceneObject> ancientritualplatform1 = SceneObjectQuery.newQuery().name("Ancient ritual platform").option("Repair platform").results();
            for(Npc runiedglyph2 : runiedglyph1){
                if(runiedglyph2 != null){
                    runiedglyph2.interact("Repair glyph spot");
                    Execution.delayUntil(3000, () -> Client.getLocalPlayer().getAnimationId() == -1);
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
            for(Npc runiedplatform2 : runiedplatform1){
                if(runiedplatform2 != null){
                    runiedplatform2.interact("Repair light source spot");
                    Execution.delayUntil(3000, () -> Client.getLocalPlayer().getAnimationId() == -1);
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
            for(SceneObject ancientritualpedestal2 : ancientritualpedestal1){
                if(ancientritualpedestal2 != null){
                    ancientritualpedestal2.interact("Repair pedestal");
                Execution.delayUntil(3000, () -> Client.getLocalPlayer().getAnimationId() == -1);
                delay(RandomGenerator.nextInt(600, 800));
                }
            }
            for(SceneObject ancientritualplatform2 : ancientritualplatform1){
                if(ancientritualplatform2 != null){
                    ancientritualplatform2.interact("Repair platform");
                    Execution.delayUntil(3000, () -> Client.getLocalPlayer().getAnimationId() == -1);
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
            break; 
            case 56:
            SceneObject Pedestalsoulfarer = SceneObjectQuery.newQuery().name("Pedestal (soulfarer)").results().first();
            EntityResultSet<Npc> elementIIglyph = NpcQuery.newQuery().name("Elemental II", "Elemental II (depleted)").results();
            EntityResultSet<Npc> changeIIyph = NpcQuery.newQuery().name("Change II", "Change II (depleted)").results();
            EntityResultSet<Npc> commune2glyph = NpcQuery.newQuery().name("Commune II", "Commune II (depleted)").results();
            EntityResultSet<Npc> candle = NpcQuery.newQuery().name("Basic ritual candle", String::contains).results();
            if(Pedestalsoulfarer == null){
                placeFocus();
            }

            if(Pedestalsoulfarer != null)
                    {
                        
                        if (elementIIglyph.size() < 1)
                            drawglyphselement();
                        else if (changeIIyph.size() < 2)
                            drawglyphschangeII();
                        else if (commune2glyph.size() < 1)
                            drawglyphscommuneII();
                        else if (candle.size() < 6) {
                            placelightsource();
                        } else {
                            
                            doRitual();
                        }
                    }
            break;
            case 59:
            Npc Vorkath1 = NpcQuery.newQuery().name("Vorkath").option("Inspect").results().first();
            if(Vorkath1 != null){
                Vorkath1.interact("Inspect");
                delay(RandomGenerator.nextInt(600, 800));
            }
            break;
            case 62:
            Npc Vorkath2 = NpcQuery.newQuery().name("Vorkath").option("Inspect").results().first();
            if(Vorkath2 != null){
                Lodestone.CITY_OF_UM.teleport();
                delay(RandomGenerator.nextInt(600, 800));
            }
            if(!vorkathcityarea.contains(player) && Vorkath2 == null){
                DebugScript.moveTo(vorkathcity);
            }
            else {
                Npc approchVorkath = NpcQuery.newQuery().name("Vorkath").option("Approach").results().first();
                if(approchVorkath != null){
                    approchVorkath.interact("Approach");
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
            break;
            case 63:
            Npc Zemouregal1 = NpcQuery.newQuery().name("Zemouregal").results().first();
           
            if(!startarea.contains(player) && Zemouregal1 == null){
                DebugScript.moveTo(startcord);
            }
            else
            {
                SceneObject floorhatch = SceneObjectQuery.newQuery().name("Floor hatch").results().nearest();
                
                if(floorhatch != null && Zemouregal1 == null)
                {
                    floorhatch.interact("Enter");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                else if(Zemouregal1 != null)
                {   
                    Zemouregal1.interact("Talk to");
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
            break;
            }
        }
    }


    public static void talktoAster()
    {
        Npc Aster = NpcQuery.newQuery().name("Aster").results().first();
        if (Aster != null) {
            Aster.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoDeath()
    {
        Npc Death = NpcQuery.newQuery().name("Death").results().first();
        if (Death != null) {
            Death.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void treeofblance()
    {
        SceneObject treeofblance = SceneObjectQuery.newQuery().name("Tree of Balance").results().first();
        if(treeofblance != null){
            treeofblance.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void placeFocus() {
        SceneObject middle = SceneObjectQuery.newQuery().name("Pedestal",String::contains).results().first();
        if (middle != null) {
            if(Interfaces.isOpen(1224))
            {
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1,64,80216098);
                delay(RandomGenerator.nextInt(900, 1250));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1,-1,80216108);
                delay(RandomGenerator.nextInt(900, 1250));
            }else {

                if (middle.getOptions().contains("Replace focus")) {
                    middle.interact("Replace focus");
                } else {
                    middle.interact("Place focus");
                }

                delay(RandomGenerator.nextInt(600, 800));
                Execution.delayUntil(2000, () -> Interfaces.isOpen(1224));
            }
        }else{
            ScriptConsole.println("Middle null");
        }
    }


    public static void doRitual() {
        if (VarManager.getVarpValue(10937) == 0) {
            Npc depleted = NpcQuery.newQuery().name("(depleted)", String::contains).results().nearest();
            if (depleted != null) {
                SceneObject middle = SceneObjectQuery.newQuery().name("Pedestal", String::contains).results().first();
                if (middle != null) {
                    middle.interact("Repair all");
                    delay(RandomGenerator.nextInt(1200, 2000));
                }
            }
        }
        if (Client.getLocalPlayer().getAnimationId() == -1 && !Client.getLocalPlayer().isMoving()) {
            SceneObject ritual = SceneObjectQuery.newQuery().option("Start ritual").results().first();
            if (ritual != null) {
                ritual.interact("Start ritual");
                delay(RandomGenerator.nextInt(600, 800));
            }
        }

        if (Client.getLocalPlayer().getAnimationId() == -1 && !Client.getLocalPlayer().isMoving()) {
            SceneObject ritual = SceneObjectQuery.newQuery().option("Start ritual").results().first();
            if (ritual != null) {
                ritual.interact("Start ritual");
                delay(RandomGenerator.nextInt(600, 800));
            }
        }
    }

    public static void placelightsource() {
        Npc light = NpcQuery.newQuery().option("Place light source").name("Light source spot").results().first();
        if (light != null) {
            if (!Client.getLocalPlayer().isMoving() && Client.getLocalPlayer().getAnimationId() == -1) {
                delay(RandomGenerator.nextInt(600, 800));
                if (!Interfaces.isOpen(1370)) {
                    light.interact("Place light source");
                    delay(RandomGenerator.nextInt(600, 800));
                } else {
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(600, 800));
                }
            }
        }
    }

    public static void drawglyphselement() {
        Npc glyph = NpcQuery.newQuery()
        .option("Draw glyph")
        .results()
        .stream()
        .filter(npc -> !npc.getName().equals("Elemental II") && !npc.getName().equals("Commune II") && !npc.getName().equals("Change II"))
        .findFirst()
        .orElse(null);
        EntityResultSet<Npc> elementII = NpcQuery.newQuery().name("Elemental II", "Elemental II (depleted)").results();
        //Npc elementII = NpcQuery.newQuery().results().stream().filter(npc -> npc.getName().equals("Elemental II") || npc.getName().equals("Elemental II (depleted)")).findFirst().orElse(null);
        if (!Client.getLocalPlayer().isMoving()) {
            if (glyph != null && elementII.size() < 1) {
                glyph.interact("Draw glyph");
                delay(RandomGenerator.nextInt(1250, 2000));
                ScriptConsole.println("Interface is open" + Interfaces.isOpen(1371));
                Execution.delayUntil(5000, () -> Interfaces.isOpen(1371));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 5, 89849878);
                delay(RandomGenerator.nextInt(1250, 2000));
                if (Interfaces.isOpen(1370)) {
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(1250, 2000));
                }

            }
        }
    }


    public static void drawglyphschangeII() {
        Npc glyph1 = NpcQuery.newQuery()
                .option("Draw glyph")
                .results()
                .stream()
                .filter(npc -> !npc.getName().equals("Elemental II") && !npc.getName().equals("Commune II") && !npc.getName().equals("Change II"))
                .findFirst()
                .orElse(null);

        EntityResultSet<Npc> communeI = NpcQuery.newQuery().name("Change II", "Change II (depleted)").results();        
        if (!Client.getLocalPlayer().isMoving()) {
            if (glyph1 != null && communeI.size() < 2) {
                glyph1.interact("Draw glyph");
                delay(RandomGenerator.nextInt(1250, 2000));
                ScriptConsole.println("Interface is open" + Interfaces.isOpen(1371));
                Execution.delayUntil(5000, () -> Interfaces.isOpen(1371));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 17, 89849878);
                delay(RandomGenerator.nextInt(1250, 2000));
                if (Interfaces.isOpen(1370)) {
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(1250, 2000));
                }

            }
        }

    }

    public static void drawglyphscommuneII() {
        Npc glyph1 = NpcQuery.newQuery()
                .option("Draw glyph")
                .results()
                .stream()
                .filter(npc -> !npc.getName().equals("Elemental II") && !npc.getName().equals("Change II") && !npc.getName().equals("Commune II"))
                .findFirst()
                .orElse(null);
        EntityResultSet<Npc> communeII = NpcQuery.newQuery().name("Commune II", "Commune II (depleted)").results();
        if (!Client.getLocalPlayer().isMoving()) {
            if (glyph1 != null && communeII.size() < 1) {
                glyph1.interact("Draw glyph");
                delay(RandomGenerator.nextInt(1250, 2000));
                ScriptConsole.println("Interface is open" + Interfaces.isOpen(1371));
                Execution.delayUntil(5000, () -> Interfaces.isOpen(1371));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, 29, 89849878);
                delay(RandomGenerator.nextInt(1250, 2000));
                if (Interfaces.isOpen(1370)) {
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    delay(RandomGenerator.nextInt(1250, 2000));
                }

            }
        }

    }

}
