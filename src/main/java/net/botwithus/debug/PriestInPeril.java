package net.botwithus.debug;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.LootInventory;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.SelectableAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.GroundItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.item.GroundItem;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.rs3.script.Execution.delay;

public class PriestInPeril {
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(3223, 3474, 0);
    static Area.Circular startarea = new Area.Circular(startcord, 6);
    static Coordinate templecord = new Coordinate(3403, 3485, 0);
    static Area.Circular templearea = new Area.Circular(templecord, 10);
    static Coordinate downladder = new Coordinate(3403, 3485, 0);
    static Coordinate intemplex = new Coordinate(3405, 3489, 0);
    static Coordinate intempley = new Coordinate(3416, 3481, 0);
    static Area insideTempleArea = new Area.Rectangular(intemplex,intempley);
    static Coordinate secoundfloor = new Coordinate(3415,3489,1);
    static Area secoundfloorArea = new Area.Circular(secoundfloor,20);
    static boolean talkedtoMonk = false;
    public static void quest() {
        int QuestVarp = VarManager.getVarpValue(2171);
        player = Client.getLocalPlayer().getServerCoordinate();


        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving()) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Priest in peril");

            if (startarea.contains(player)) {
                talktoKingRonald();
            } else {
                DebugScript.moveTo(startcord);
            }

        } else {
            switch (QuestVarp) {
                case 1:
                    if (templearea.contains(player)) {
                        SceneObject door = SceneObjectQuery.newQuery().name("Large door").results().nearest();
                        if(door != null){
                            door.interact("Enter");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    } else {
                        DebugScript.moveTo(templecord);
                    }
                    break;
                case 2:
                    SceneObject entrance = SceneObjectQuery.newQuery().name("Mausoleum").results().nearest();
                    Npc cerb = NpcQuery.newQuery().name("Cerberus").results().nearest();
                    if(entrance != null){
                        entrance.interact("Enter");
                        delay(RandomGenerator.nextInt(600, 800));
                    }else if(cerb != null){
                        if(!Client.getLocalPlayer().hasTarget()){
                            cerb.interact("Attack");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }
                    break;
                case 3:
                    SceneObject ladder  = SceneObjectQuery.newQuery().name("Ladder").results().nearest();
                    if(!talkedtoMonk) {
                            if (templearea.contains(player)) {
                                SceneObject door = SceneObjectQuery.newQuery().name("Large door").results().nearest();
                                if (door != null) {
                                    door.interact("Enter");
                                    delay(RandomGenerator.nextInt(600, 800));
                                }
                            } else {
                                DebugScript.moveTo(templecord);
                            }
                    }else{
                            if (startarea.contains(player)) {
                                talktoKingRonald();
                            } else {
                                DebugScript.moveTo(startcord);
                            }
                        }
                    break;
                case 4:
                    if(!Backpack.contains("Golden key") && !Backpack.contains("Iron key")) {
                        if (templearea.contains(player)) {
                            if (insideTempleArea.contains(player)) {
                                GroundItem key = GroundItemQuery.newQuery().name("Golden key").results().nearest();
                                if (key != null && !Interfaces.isOpen(1622)) {
                                    key.interact("Take");
                                    delay(RandomGenerator.nextInt(600, 800));
                                } else if (key != null && Interfaces.isOpen(1622)) {
                                    LootInventory.take("Golden key");
                                }
                                Npc monk = NpcQuery.newQuery().name("Monk of Zamorak").combatLevel(23).results().nearest();
                                if (monk != null && !Client.getLocalPlayer().hasTarget()) {
                                    monk.interact("Attack");
                                    delay(RandomGenerator.nextInt(600, 800));
                                }

                            } else {
                                SceneObject door = SceneObjectQuery.newQuery().name("Large door").results().nearest();
                                if (door != null) {
                                    door.interact("Enter");
                                    delay(RandomGenerator.nextInt(600, 800));
                                }
                            }
                        } else {
                            DebugScript.moveTo(templecord);
                        }
                    }else{
                        if (insideTempleArea.contains(player) && !Backpack.contains("Iron key")) {
                            SceneObject door = SceneObjectQuery.newQuery().name("Large door").results().nearest();
                            if (door != null) {
                                door.interact("Enter");
                                delay(RandomGenerator.nextInt(600, 800));
                            }else{
                                    DebugScript.moveTo(templecord);
                            }
                        }else{

                            SceneObject entrance2 = SceneObjectQuery.newQuery().name("Mausoleum").results().nearest();
                            SceneObject gate = SceneObjectQuery.newQuery().name("Gate").option("Open").results().nearest();
                            SceneObject coffin = SceneObjectQuery.newQuery().name("Morytania coffin").results().nearest();
                            SceneObject ladder2 = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().nearest();

                            if(coffin != null){
                                SceneObject celldoor = SceneObjectQuery.newQuery().name("Cell door").results().nearest();
                                Item ironkey = Backpack.getItem("Iron key");
                                if(celldoor != null && Backpack.contains("Iron key")){
                                    MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, ironkey.getSlot(), 96534533);
                                    delay(RandomGenerator.nextInt(600, 800));
                                    celldoor.interact(SelectableAction.SELECT_OBJECT.getType());
                                    delay(RandomGenerator.nextInt(600, 800));
                                }



                                return;
                            }
                            if(Backpack.contains("Iron key") && Backpack.contains("Bucket of murky water") && entrance2 != null){
                                if (!insideTempleArea.contains(player) && !secoundfloorArea.contains(player)) {
                                    SceneObject door = SceneObjectQuery.newQuery().name("Large door").results().nearest();
                                    if (door != null) {
                                        door.interact("Enter");
                                        delay(RandomGenerator.nextInt(600, 800));
                                    }
                                }else{
                                    SceneObject staircase = SceneObjectQuery.newQuery().name("Staircase").option("Climb up").results().nearest();
                                    if(staircase != null){
                                        staircase.interact("Climb up");
                                        delay(RandomGenerator.nextInt(600, 800));
                                    }
                                }
                            }
                            if(entrance2 != null &&   !Backpack.contains("Bucket of murky water")){
                                entrance2.interact("Enter");
                                delay(RandomGenerator.nextInt(600, 800));
                            }else{
                                if(gate != null && player.getY() >= gate.getCoordinate().getY()){
                                    if(!Backpack.contains("Bucket of murky water")) {
                                        gate.interact("Open");
                                        delay(RandomGenerator.nextInt(600, 800));
                                    }else {
                                        if (ladder2 != null) {
                                            ladder2.interact("Climb-up");
                                            delay(RandomGenerator.nextInt(600, 800));
                                        }
                                    }

                                }else{
                                    if(Backpack.contains("Bucket of murky water")){
                                        if(ladder2 != null && ladder2.distanceTo(player) > 12){
                                            ScriptConsole.println(ladder2.distanceTo(player));
                                            gate.interact("Open");
                                            delay(RandomGenerator.nextInt(600, 800));
                                        }else if(ladder2 != null){
                                            ladder2.interact("Climb");
                                            delay(RandomGenerator.nextInt(600, 800));
                                        }
                                    }else {
                                        if (Backpack.contains("Gold key")) {
                                            ScriptConsole.println("here");
                                            SceneObject monument = SceneObjectQuery.newQuery().id(3497).results().nearest();
                                            if (monument != null) {
                                                Item goldkey = Backpack.getItem("Golden key");
                                                if (goldkey != null) {
                                                    MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, goldkey.getSlot(), 96534533);
                                                    delay(RandomGenerator.nextInt(600, 800));
                                                    monument.interact(SelectableAction.SELECT_OBJECT.getType());
                                                    delay(RandomGenerator.nextInt(600, 800));
                                                }
                                            }
                                        } else {
                                            if (Backpack.contains("Bucket")) {
                                                Item bucket = Backpack.getItem("Bucket");
                                                SceneObject well = SceneObjectQuery.newQuery().name("Well").results().nearest();
                                                if (well != null) {
                                                    if (bucket != null) {
                                                        MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, bucket.getSlot(), 96534533);
                                                        delay(RandomGenerator.nextInt(600, 800));
                                                        well.interact(SelectableAction.SELECT_OBJECT.getType());
                                                        delay(RandomGenerator.nextInt(600, 800));
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                    break;
                case 6:
                    SceneObject coffin2 = SceneObjectQuery.newQuery().name("Morytania coffin").results().nearest();

                    if(Backpack.contains("Bucket of blessed water") && coffin2 != null){
                        Item blessedwater = Backpack.getItem("Bucket of blessed water");
                        if(blessedwater != null){
                            MiniMenu.interact(SelectableAction.SELECTABLE_COMPONENT.getType(), 0, blessedwater.getSlot(), 96534533);
                            delay(RandomGenerator.nextInt(600, 800));
                            coffin2.interact(SelectableAction.SELECT_OBJECT.getType());
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }else if(Backpack.contains("Bucket of murky water")){
                         talktoDrezel();
                    }

                    break;
                case 7:
                    SceneObject ladder3 = SceneObjectQuery.newQuery().name("Ladder").option("Climb-up").results().nearest();
                    SceneObject gate3 = SceneObjectQuery.newQuery().name("Gate").option("Open").results().nearest();
                    SceneObject entrance3 = SceneObjectQuery.newQuery().name("Mausoleum").results().nearest();
                    if(player.getZ() > 0) {
                        SceneObject stairs = SceneObjectQuery.newQuery().name("Staircase").option("Climb down").results().nearest();
                        if (stairs != null) {
                            stairs.interact("Climb down");
                        }
                    }else if (insideTempleArea.contains(player)) {
                        SceneObject door = SceneObjectQuery.newQuery().name("Large door").results().nearest();
                        if (door != null) {
                            door.interact("Enter");
                            delay(RandomGenerator.nextInt(600, 800));
                        }
                    }else if(entrance3 != null){
                        entrance3.interact("Enter");
                        delay(RandomGenerator.nextInt(600, 800));
                    }else if(gate3 != null && player.getY() >= gate3.getCoordinate().getY()){
                        gate3.interact("Open");
                        delay(RandomGenerator.nextInt(600, 800));
                    }else{
                        talktoDrezel();
                    }
                    break;
                case 10:
                    talktoDrezel();
                    break;


            }
        }
    }
    public static void talktoKingRonald(){
        Npc ronald = NpcQuery.newQuery().name("King Roald").results().nearest();
        if(ronald != null){
            ronald.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void talktoDrezel(){
        Npc drezel = NpcQuery.newQuery().name("Drezel").results().nearest();
        if(drezel != null){
            drezel.interact("Talk-to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }
}
