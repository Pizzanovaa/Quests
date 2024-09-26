package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

public class DaughterofChaos {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(2983,3341,2);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate questAnneDimitriLocation = new Coordinate(2970, 3339, 1);
    static Area.Circular questAnneDimitriArea = new Area.Circular(questAnneDimitriLocation, 10);
    static Coordinate questAnneDimitriLocation2 = new Coordinate(3138, 3533, 0);
    static Area.Circular questAnneDimitriArea2 = new Area.Circular(questAnneDimitriLocation2, 10);
    static Coordinate questAnneDimitriLocation3 = new Coordinate(14382, 893, 0);
    static Area.Circular questAnneDimitriArea3 = new Area.Circular(questAnneDimitriLocation3, 10);


    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(51682);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Daughter of Chaos");

            if (startarea.contains(player)) {
                
                talktoAdrasteia();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {

                case 15:
                if (!questAnneDimitriArea.contains(player)) {
                    DebugScript.moveTo(questAnneDimitriLocation);
                } else {
                    talktoAnneDimitri();
                }
                break;
                case 20:
                if(!questAnneDimitriArea2.contains(player)) {
                    DebugScript.moveTo(questAnneDimitriLocation2);
                } else {
                    talktoAnneDimitri();
                }
                break;
                case 25:
                break;
                case 30:
                break;
                case 35:
                break;
                case 40:
                talktoAnneDimitri();    
                break;
                case 45:
                pointofinterest();
                talktoAnneDimitri();    
                break;
                case 50:
                Npc npc = NpcQuery.newQuery().name("Memory fragment").results().first();
                if (npc != null) {
                    npc.interact(NPCAction.NPC1);
                    delay(RandomGenerator.nextInt(1200, 1500));
                }
                else
                {
                    talktoAnneDimitri();
                }
                break;
                case 55:
                Npc npc1 = NpcQuery.newQuery().name("Restored Memory").results().first();
                if (npc1 != null) {
                    npc1.interact(NPCAction.NPC1);
                    delay(RandomGenerator.nextInt(1200, 1500));
                }
                break;
                case 60:
                Npc npc2 = NpcQuery.newQuery().name("Restored Memory").results().first();
                if (npc2 != null) {
                    npc2.interact(NPCAction.NPC1);
                    delay(RandomGenerator.nextInt(1200, 1500));
                }
                break;
                case 65:
                talktoEnakhra();
                talktoGeneralKhazard();
                talktoZemouregal();
                talktoBilrach();
                break;
                case 70:

                if(VarManager.getVarbitValue(51683) == 1 && VarManager.getVarbitValue(51684) == 0) // Select Easy mode
                {
                    talktoAnneDimitri();
                }
                else if(VarManager.getVarbitValue(51683) == 1 && VarManager.getVarbitValue(51684) == 1)  // Inside Easy mode
                { 
                    talktoAnneDimitri();
                    println("Manual Fight");
                    // int currentHealth = Client.getLocalPlayer().getCurrentHealth();
                    // int maxHealth = Client.getLocalPlayer().getMaximumHealth();
                    // int healthPercentage = (currentHealth * 100) / maxHealth;

                    // Npc healthorb = NpcQuery.newQuery().name("Health orb").results().first();
                    // if(healthPercentage < 50 && healthorb != null)
                    // {
                    // healthorb.interact(NPCAction.NPC1);
                    // delay(RandomGenerator.nextInt(400, 600));
                    // return;
                    // }

                    // if(Client.getLocalPlayer().getFollowing() == null && !Client.getLocalPlayer().hasTarget() && !Client.getLocalPlayer().inCombat())
                    // {
                    //     killDemon();
                    // }
                    // else if(Client.getLocalPlayer().getFollowing() == null && !Client.getLocalPlayer().hasTarget() && !Client.getLocalPlayer().inCombat())
                    // {
                    //     killCultist();
                    // }
                }
                else if(VarManager.getVarbitValue(51683) == 0 && VarManager.getVarbitValue(51684) == 0)
                {
                    println("Manual Fight");
                //     Npc npc3 = NpcQuery.newQuery().name("Chaos demon").results().first();
                //     Npc healthorb = NpcQuery.newQuery().name("Health orb").results().first();
                //     int currentHealth = Client.getLocalPlayer().getCurrentHealth();
                //     int maxHealth = Client.getLocalPlayer().getMaximumHealth();
                //     int healthPercentage = (currentHealth * 100) / maxHealth;


                //     if (npc3 != null && Client.getLocalPlayer().hasTarget()) {
                 
                // MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 68550678); //3
                // delay(RandomGenerator.nextInt(400, 600));
                // MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 68550673); // 2
                // delay(RandomGenerator.nextInt(400, 600));
                // MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 1, -1, 68550668);
                // delay(RandomGenerator.nextInt(400, 600));
                // }
                // if(healthPercentage < 50 && healthorb != null)
                // {
                //     healthorb.interact(NPCAction.NPC1);
                //     delay(RandomGenerator.nextInt(400, 600));
                // }
                }
                
                break;
                case 75:
                talktoBilrach();
                break;
                case 80:
                talktoAnneDimitri();
                break;
                case 85:
                
                break;
                case 90:
                
                break;
                case 95:
                talktoAnneDimitri();
                break;
                case 100:
                pointofinterest();
                talktoAnneDimitri();
                break;
                case 105:
                Npc npc4 = NpcQuery.newQuery().name("Memory fragment").results().first();
                if (npc4 != null) {
                    npc4.interact(NPCAction.NPC1);
                    delay(RandomGenerator.nextInt(1200, 1500));
                }
                break;
                case 110:
                Npc npc5 = NpcQuery.newQuery().name("Restored Memory").results().first();
                if (npc5 != null) {
                    npc5.interact(NPCAction.NPC1);
                    delay(RandomGenerator.nextInt(1200, 1500));
                }
                break;
                case 115:
                Npc npc6 = NpcQuery.newQuery().name("Restored Memory").results().first();
                if (npc6 != null) {
                    npc6.interact(NPCAction.NPC1);
                    delay(RandomGenerator.nextInt(1200, 1500));
                }
                break;
                case 120:
                break;
                case 125:
                println("Manual Fight");
                break;
                case 130:
                talktocultist();
                break;
                case 135:
                break;
                case 140:
                break;
                case 145:
                break;
                case 150:
                break;
                case 155:
                break;
                case 160:
                break;
                case 165:
                talktoAnneDimitri();
                break;
                case 170:
                Npc npc7 = NpcQuery.newQuery().name("Restored Memory").results().first();
                if (npc7 != null) {
                    npc7.interact(NPCAction.NPC1);
                    delay(RandomGenerator.nextInt(1200, 1500));
                }
                break;
                case 175:
                println("Manual Fight");
                break;
                case 180:
                break;
                case 185:
                break;
                case 190:
                talktoMoia();
                break;
                case 195:
                break;
                case 200:
                
                break;
                case 205:
                 if(!startarea.contains(player))
                 {
                    DebugScript.moveTo(startcord);
                 }
                 else
                 {
                    talktoAdrasteia();
                 }
                break;

                
            }
        }
    }

    public static void talktoAdrasteia()
    {
        Npc npc = NpcQuery.newQuery().name("Adrasteia").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void talktoAnneDimitri()
    {
        Npc npc = NpcQuery.newQuery().name("Anne Dimitri").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void talktoMoia()
    {
        Npc npc = NpcQuery.newQuery().name("Moia").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void talktoTrindine()
    {
        Npc npc = NpcQuery.newQuery().name("Trindine").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void pointofinterest()
    {
        Npc npc = NpcQuery.newQuery().name("Point of interest").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void talktoBilrach()
    {
        Npc npc = NpcQuery.newQuery().name("Bilrach").results().nearest();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void talktoEnakhra()
    {
        Npc npc = NpcQuery.newQuery().name("Enakhra").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void talktoGeneralKhazard()
    {
        Npc npc = NpcQuery.newQuery().name("General Khazard").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void talktoZemouregal()
    {
        Npc npc = NpcQuery.newQuery().name("Zemouregal").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void talktocultist()
    {
        Npc npc = NpcQuery.newQuery().name("Wounded cultist").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void attacknpc()
    {
        Npc npc = NpcQuery.newQuery().name("Chaos demon", "Zamorakian cultist").results().first();
        if (npc != null) {
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(1200, 1500));
        }
    }

    public static void killDemon() {
        if (!Client.getLocalPlayer().inCombat()) {
            Npc demon = NpcQuery.newQuery().name("Chaos demon").results().first();
            if (demon != null) {
                demon.interact("Attack");
                delay(RandomGenerator.nextInt(600, 800));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 68550678); //3
                delay(RandomGenerator.nextInt(400, 600));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 68550673); // 2
                delay(RandomGenerator.nextInt(400, 600));
                return;
            }
        }

    }
    public static void killCultist() {
        if (!Client.getLocalPlayer().inCombat()) {
            Npc cultist = NpcQuery.newQuery().name("Zamorakian cultist").results().first();
            if (cultist != null) {
                cultist.interact("Attack");
                delay(RandomGenerator.nextInt(600, 800));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 68550678); //3
                delay(RandomGenerator.nextInt(400, 600));
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 68550673); // 2
                delay(RandomGenerator.nextInt(400, 600));
                return;
            }
        }

    }


}
