package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
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

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.traversal.Lodestone;

public class BattleofForinthry {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3292, 3544, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(54707);
        player = Client.getLocalPlayer().getServerCoordinate();

     

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Battle of Forinthry");

            if (startarea.contains(player)) {
                talktoTheRaptor();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 5:
                SceneObject beginEncounter = SceneObjectQuery.newQuery().name("Fort Forinthry Gate").results().nearest();
                if(beginEncounter != null)
                {
                    beginEncounter.interact("Begin Encounter");
                    delay(RandomGenerator.nextInt(600, 800));
                }

                break;
                case 10:
                if(VarManager.getVarbitValue(54709) == 0  && VarManager.getVarbitValue(54713) == 0)
                {
                    //talktoTheRaptor();
                    talktoOverseersiv();
                }
                else if(VarManager.getVarbitValue(54709) == 1  && VarManager.getVarbitValue(54713) == 1 && VarManager.getVarbitValue(54708) == 0 )
                {
                    talktoBill();
                }
                else if(VarManager.getVarbitValue(54708) == 1  && VarManager.getVarbitValue(54713) == 2 && VarManager.getVarbitValue(54712) == 0)
                {
                    talktoOak();
                }
                else if(VarManager.getVarbitValue(54713) == 3 && VarManager.getVarbitValue(54712) == 1)
                {
                    talktoCopperpot();
                }
                else if(VarManager.getVarbitValue(54713) == 4 && VarManager.getVarbitValue(54711) == 1)
                {
                    talktoRodney();
                }
                else if(VarManager.getVarbitValue(54713) == 5)
                {
                    talktoTheRaptor();
                }
                break;
                case 15:
                talktoBill();
                break;
                case 20:
                SceneObject pileofstone = SceneObjectQuery.newQuery().name("Pile of stone").results().nearest();
                if(pileofstone != null && Backpack.getCount("Stone wall segment") < 1)
                {
                    pileofstone.interact("Take from");
                    delay(RandomGenerator.nextInt(600, 800));
                }else if(pileofstone == null && Backpack.getCount("Stone wall segment") < 1){
                    Npc sofit = NpcQuery.newQuery().name("Guard captain Sof",String::contains).results().nearest();// walk here incase out of range.
                    if(sofit != null){
                        Movement.walkTo(sofit.getCoordinate().getX(),sofit.getCoordinate().getY(),false);
                        delay(RandomGenerator.nextInt(600, 800));
                        return;
                    }
                }
                else
                {
                    SceneObject hotspot = SceneObjectQuery.newQuery().name("Repair hotspot").option("Repair").hidden(false).results().nearest();
                    if(hotspot != null)
                    {
                        ScriptConsole.println("Repairing...");
                        hotspot.interact("Repair");
                        Execution.delayUntil(10000, () -> Client.getLocalPlayer().getAnimationId() != -1);
                        //delay(RandomGenerator.nextInt(10000, 12000));
                    }else{
                        Npc sofit = NpcQuery.newQuery().name("Guard captain Sofia").results().nearest(); // walk here incase out of range.
                        if(sofit != null){
                            Movement.walkTo(sofit.getCoordinate().getX(),sofit.getCoordinate().getY(),false);
                            delay(RandomGenerator.nextInt(600, 800));
                            return;
                        }
                    }
                }
                break;
                case 25:
                talktoOverseersiv();
                break;
                case 30:
                talktoAster();
                break;
                case 35:
                talktoTheRaptor();

                
                break;
                case 40:
                SceneObject floorhatch = SceneObjectQuery.newQuery().name("Floor hatch").results().nearest();
                Npc Zemouregal1 = NpcQuery.newQuery().name("Zemouregal").results().first();
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
                break;
            }
        }
    }


    public static void talktoTheRaptor()
    {
        Npc theRaptor = NpcQuery.newQuery().name("The Raptor").results().first();
        if (theRaptor != null) {
            theRaptor.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoBill()
    {
        Npc bill = NpcQuery.newQuery().name("Bill").results().first();
        if (bill != null) {
            bill.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoOverseersiv()
    {
        Npc overseersiv = NpcQuery.newQuery().name("Overseer Siv").results().nearest();
        if (overseersiv != null) {
            overseersiv.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoCopperpot()
    {
        Npc copperpot = NpcQuery.newQuery().name("Copperpot").results().nearest();
        if (copperpot != null) {
            copperpot.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoRodney()
    {
        Npc rodney = NpcQuery.newQuery().name("Rodney").results().nearest();
        if (rodney != null) {
            rodney.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoOak()
    {
        Npc oak = NpcQuery.newQuery().name("Oak").results().nearest();
        if (oak != null) {
            oak.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoAster()
    {
        Npc aster = NpcQuery.newQuery().name("Aster").results().nearest();
        if (aster != null) {
            aster.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
