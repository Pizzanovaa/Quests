package net.botwithus.debug;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.pressDialog;
import static net.botwithus.rs3.script.Execution.delay;

import net.botwithus.api.game.hud.traversal.Lodestone;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

public class Spirit_War {

static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(1084, 1813, 1);
    static Area.Circular startarea = new Area.Circular(startcord, 10);
    static Coordinate edcord = new Coordinate(1115, 1832, 1);
    static Area.Circular edcordarea = new Area.Circular(edcord, 10);

    static Coordinate rescuecord1 = new Coordinate(1000, 1892, 1);
    static Area.Circular rescuearea1 = new Area.Circular(rescuecord1, 10);
    static Coordinate rescuecord2 = new Coordinate(1015, 1692, 1);
    static Area.Circular rescuearea2 = new Area.Circular(rescuecord2, 10);
    static Coordinate rescuecord3 = new Coordinate(1118, 1690, 1);
    static Area.Circular rescuearea3 = new Area.Circular(rescuecord3, 10);
    static Coordinate rescuecord4 = new Coordinate(1200, 1899, 1);
    static Area.Circular rescuearea4 = new Area.Circular(rescuecord4, 10);

    public static void quest() {
        int QuestVarp = VarManager.getVarbitValue(53503);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... The Spirit of War");

            if (startarea.contains(player)) {
                talktoKharen();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 10:
                    if(VarManager.getVarbitValue(53504) == 0 && VarManager.getVarbitValue(53505) == 0 && VarManager.getVarbitValue(53506) == 0 && VarManager.getVarbitValue(53507) == 0 && VarManager.getVarbitValue(53509) != 4)
                    {
                        if (edcordarea.contains(player)) {
                            talktoEd();
                        } else {
                            DebugScript.moveTo(edcord);
                        }
                    }
                    else if(VarManager.getVarbitValue(53504) == 1 ){
                        if(!rescuearea1.contains(player)){
                            DebugScript.moveTo(rescuecord1);
                        } else {
                            rescueSoul();
                        }
                    }
                    else if(VarManager.getVarbitValue(53505) == 1 ){
                        if(!rescuearea2.contains(player)){
                            DebugScript.moveTo(rescuecord2);
                        } else {
                            rescueSoul();
                        }
                    }
                    else if(VarManager.getVarbitValue(53506) == 1 ){
                        if(!rescuearea3.contains(player)){
                            DebugScript.moveTo(rescuecord3);
                        } else {
                            rescueSoul();
                        }
                    }
                    else if(VarManager.getVarbitValue(53507) == 1 ){
                        if(!rescuearea4.contains(player)){
                            DebugScript.moveTo(rescuecord4);
                        } else {
                            rescueSoul();
                        }
                    }
                    else if(VarManager.getVarbitValue(53509) == 4)
                    {
                        ScriptConsole.println("Going back to Kharen");
                        if(!startarea.contains(player)){
                            DebugScript.moveTo(startcord);
                        } else {
                            talktoKharen();
                        }
                    }
                    break;
                case 20:
                    talktoRaisal();
                    break;
                case 25:
                    //Fighting Hermond
                    // Minionn Name : Armoured phantom
                    break;
                case 30:
                talktoRaisal();
                    break;
                case 35:
                if(!startarea.contains(player)){
                    DebugScript.moveTo(startcord);
                } else {
                    talktoKharen();
                }
                break;
            }
        }
    }

    public static void talktoKharen() {
        Npc harbinger = NpcQuery.newQuery().name("Kharen").results().nearest();
        if (harbinger != null) {
            harbinger.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoEd() {
        Npc harbinger = NpcQuery.newQuery().name("Ed").results().nearest();
        if (harbinger != null) {
            harbinger.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void rescueSoul(){
        Npc sould = NpcQuery.newQuery().name("Wayward Soul").results().nearest();
        if (sould != null) {
            sould.interact("Rescue");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoRaisal() {
        Npc harbinger = NpcQuery.newQuery().name("Rasial, the First Necromancer").results().nearest();
        if (harbinger != null) {
            harbinger.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
