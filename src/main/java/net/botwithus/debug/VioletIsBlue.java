package net.botwithus.debug;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;
import net.botwithus.rs3.util.Regex;

import java.util.regex.Pattern;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.game.minimenu.actions.WalkAction.WALK;
import static net.botwithus.rs3.script.Execution.delay;

public class VioletIsBlue {

    static Coordinate startCord = new Coordinate(2854, 3459, 0);
    static Area.Circular startArea = new Area.Circular(startCord, 5);
    static boolean leave = false;
    static boolean allitemsgot = false;
    static boolean allitemstaken = false;

    static Coordinate start;
    //5325 1 quest start
    //5326 5 snowballs - 10 snowballs complete (door, Trevor) -
    // 11 (Trevor) 12(trevor) 15- (Violet) 20 - (voilet) 25
    // - (storage game)
    //30 - trevor
    //32 door leave
    //35 puzzle thing
    //44 trees honeyed - catch flys
    //48 - headless things 50 also // 55 - done heads (violet)
    //60 - build snowmen // 65 water bucket then violet
    //70 puzzle
    //75 violet
    //82 violet
    //85 sled
    // 42962 sled progress
    // 90 quest complete
    // 120 quest complete
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();

    public static void quest2() {
        int QuestVarp = VarManager.getVarbitValue(5325);
        int QuestProg = VarManager.getVarbitValue(5326);
        player = Client.getLocalPlayer().getServerCoordinate();

        if (isDialogOpen()) {
            return;
        }


        if (Client.getLocalPlayer().isMoving() && QuestProg != 5) {
            return;
        }

        if (QuestVarp == 0) {
            ScriptConsole.println("Starting quest... Violet is blue!");

            startQuest();
        } else {
            if (startArea.contains(player)) {
                ifinstartare();
                return;
            }
            switch (QuestProg) {
                case 5:
                    snowball();
                    break;
                case 10, 11, 12:
                    doorandtrevor();
                    break;
                case 15, 20, 48, 55, 75, 82:
                    talkToViolet();
                    break;
                case 25:
                    storageGame();
                    break;
                case 30:
                    talktoTrevor();
                    break;
                case 32:
                    leavedoor();
                    break;
                case 35:
                    honeyshit();
                    break;
                case 44:
                    honeyshit2();
                    break;
                case 50:

                    SceneObject snow = SceneObjectQuery.newQuery().name("Icy snow").results().nearest();
                    if (snow != null) {
                        int snowx = snow.getCoordinate().getX();
                        int snowy = snow.getCoordinate().getY();
                        if (player.getX() == snowx - 2 && player.getY() == snowy + 1) {
                            if (!Client.getLocalPlayer().isMoving()) {
                                headlesspeople();
                            }
                        } else {
                            interactwithsnow();
                        }
                    }

                    break;

                case 60:
                    buildsnowmen();
                    break;
                case 65:
                    icywater();
                    break;
                case 70:
                    puzzle();
                    break;
                case 85:
                    makesledge();
                    break;
                case 90, 120:
                    println("Quest Complete");
                    break;
            }
        }
    }


    public static void takeItem(int num) {

        switch (num) {
            case 1: //socks
            case 2: // weapongizmo
            case 3: // empty jar
            case 4: // Yo-yo
                SceneObject bed = SceneObjectQuery.newQuery().name("Bed").results().nearest();
                if (bed != null) {
                    bed.interact("Look under");
                    delay(RandomGenerator.nextInt(3000, 3800));
                }
                break;
            case 5: // toy lion
            case 6: // book
            case 7: // mittens
            case 8: // fluffy unicorn
                //wardrobe
                SceneObject drobe = SceneObjectQuery.newQuery().name("Wardrobe").results().nearest();
                if (drobe != null) {
                    drobe.interact("Search");
                    delay(RandomGenerator.nextInt(3000, 3800));
                }
                break;
            case 9: // Wood carving
            case 10: // Iron figure
            case 11: // Scarf
            case 12: // Climbing boots
                SceneObject toybox = SceneObjectQuery.newQuery().name("Toybox").results().nearest();
                if (toybox != null) {
                    toybox.interact("Search");
                    delay(RandomGenerator.nextInt(3000, 3800));

                }
                break;
        }
    }

    public static void talkToViolet() {
        Npc Violet = NpcQuery.newQuery().name("Violet").results().nearest();
        if (Violet != null) {
            Violet.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
            return;
        }
    }

    public static void talktoTrevor() {
        Npc trevor = NpcQuery.newQuery().name("Trevor").results().nearest();
        if (trevor != null) {
            trevor.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }


    //5325 1 quest start
    //5326 5 snowballs - 10 snowballs complete (door, Trevor) -
    // 11 (Trevor) 12(trevor) 15- (Violet) 20 - (voilet) 25
    // - (storage game)
    //30 - trevor
    //32 door leave
    //35 puzzle thing
    //44 trees honeyed - catch flys
    //48 - headless things 50 also // 55 - done heads (violet)
    //60 - build snowmen // 65 water bucket then violet
    //70 puzzle
    //75 violet
    //82 violet
    //85 sled
    // 42962 sled progress
    // 90 quest complete
    // 120 quest complete

    public static void snowball() {
        SceneObject portal = SceneObjectQuery.newQuery().name("Land of Snow portal").option("Exit").results().nearest();
        if (portal != null) {
            //inside instance
            Coordinate portalcord = portal.getCoordinate();
            println(player.toString());
            Coordinate bottomright = new Coordinate(portalcord.getX() - 1, portalcord.getY(), portalcord.getZ());
            Coordinate topright = new Coordinate(portalcord.getX() - 6, portalcord.getY() + 57, portalcord.getZ());
            Area area = new Area.Rectangular(bottomright, topright);

            //x-3 ceneter
            //

            if (!area.contains(player)) {
                Npc Violet = NpcQuery.newQuery().name("Violet").results().nearest();
                if (Violet != null) {
                    Violet.interact("Talk to");
                    delay(RandomGenerator.nextInt(600, 800));
                    return;
                }
            }
            Coordinate playerfakecord = new Coordinate(player.getX(), player.getY() + 4, player.getZ());
            Coordinate middle = new Coordinate(portalcord.getX() - 3, player.getY(), player.getZ());
            Npc violet = NpcQuery.newQuery().name("Violet").results().nearest();
            if (violet != null) {
                println(String.valueOf(violet.distanceTo(player)));
                if (violet.distanceTo(player) <= 7) {
                    violet.interact("Talk to");
                    delay(600);
                }
            }
            Npc snowball = NpcQuery.newQuery().name("Snowball").results().nearestTo(playerfakecord);
            if (snowball != null) {


                int x1 = snowball.getServerCoordinate().getX() + 1;
                int x2 = snowball.getServerCoordinate().getX();
                int x3 = snowball.getServerCoordinate().getX() - 1;
                if (player.getX() == x1 || player.getX() == x2 || player.getX() == x3) {
                    Coordinate moveright = new Coordinate(snowball.getServerCoordinate().getX() - 2, player.getY() + 1, player.getZ());
                    Coordinate moveleft = new Coordinate(snowball.getServerCoordinate().getX() + 2, player.getY() + 1, player.getZ());

                    if (moveright.distanceTo(middle) < moveleft.distanceTo(middle)) {
                        if (area.contains(moveright)) {
                            Movement.walkTo(moveright.getX(), moveright.getY(), false);
                        } else {
                            Movement.walkTo(moveleft.getX(), moveleft.getY(), false);
                        }
                    } else {
                        if (area.contains(moveleft)) {
                            Movement.walkTo(moveleft.getX(), moveleft.getY(), false);
                        } else {
                            Movement.walkTo(moveright.getX(), moveright.getY(), false);

                        }
                    }
                } else {
                    if (area.contains(new Coordinate(player.getX(), player.getY() + 4, player.getZ()))) {
                        Movement.walkTo(player.getX(), player.getY() + 4, false);
                    }
                }

                if (snowball.getServerCoordinate().getY() < player.getCoordinate().getY()) {
                    return;
                }
            } else {
                if (area.contains(new Coordinate(player.getX(), player.getY() + 4, player.getZ()))) {
                    Movement.walkTo(player.getX(), player.getY() + 4, false);
                } else {
                    Movement.walkTo(player.getX(), player.getY() + 2, false);
                }
            }

            return;
        }
    }

    public static void doorandtrevor() {

        int QuestProg = VarManager.getVarbitValue(5326);
        if (QuestProg == 10) {
            talkToViolet();
        }
        Npc trevor = NpcQuery.newQuery().name("Trevor").results().nearest();
        if (trevor != null) {
            trevor.interact("Talk to");
            delay(RandomGenerator.nextInt(600, 800));
        }

        SceneObject door = SceneObjectQuery.newQuery().name("Door").option("Knock").results().first();
        if (door != null) {
            door.interact("Knock");
            delay(RandomGenerator.nextInt(600, 800));
            return;
        }
    }

    public static void leavedoor() {
        SceneObject door = SceneObjectQuery.newQuery().name("Door").option("Exit").results().first();
        if (door != null) {
            if (Client.getLocalPlayer().isMoving()) {
                return;
            }
            door.interact("Exit");
            delay(RandomGenerator.nextInt(600, 800));
            return;
        } else {
            talktoTrevor();
        }
    }

    public static void storageGame() {
        Pattern pattern = Regex.getPatternForContainingOneOf("Spare sock", "Weapon gizmo", "Empty jar", "Yo-yo", "Wooden carving",
                "Iron figure", "Scarf", "Climbing boots", "Toy lion",
                "'How to be a Witch'", "Mittens", "Fluffy unicorn");
        int item1 = VarManager.getVarbitValue(42971);
        int item2 = VarManager.getVarbitValue(42972);
        int item3 = VarManager.getVarbitValue(42973);
        int item4 = VarManager.getVarbitValue(42974);
        int item5 = VarManager.getVarbitValue(42975);
        int currentitem = VarManager.getVarbitValue(5327);
        int itemnum = VarManager.getVarbitValue(42876);

        if (itemnum < 6) {
            if (!Backpack.contains(pattern)) {
                openItemInter(currentitem);
            } else {
                talkToViolet();
            }
        }

    }

    public static void openItemInter(int num) {

        switch (num) {
            case 1: //socks
            case 2: // weapongizmo
            case 3: // empty jar
            case 4: // Yo-yo
                SceneObject bed = SceneObjectQuery.newQuery().name("Bed").results().nearest();
                if (bed != null) {
                    bed.interact("Look under");
                    delay(RandomGenerator.nextInt(3000, 3800));
                }
                break;
            case 5: // toy lion
            case 6: // book
            case 7: // mittens
            case 8: // fluffy unicorn
                //wardrobe
                SceneObject drobe = SceneObjectQuery.newQuery().name("Wardrobe").results().nearest();
                if (drobe != null) {
                    drobe.interact("Search");
                    delay(RandomGenerator.nextInt(3000, 3800));
                }
                break;
            case 9: // Wood carving
            case 10: // Iron figure
            case 11: // Scarf
            case 12: // Climbing boots
                SceneObject toybox = SceneObjectQuery.newQuery().name("Toybox").results().nearest();
                if (toybox != null) {
                    toybox.interact("Search");
                    delay(RandomGenerator.nextInt(3000, 3800));

                }
                break;
        }
    }

    public static void honeyshit() {


        if (Backpack.contains("Bucket of syrup")) {
            SceneObject tree3 = SceneObjectQuery.newQuery().name("Leaning tree").hidden(false).results().nearest();
            if (tree3 != null) {
                tree3.interact("Check");
                delay(RandomGenerator.nextInt(600, 800));
            }
            return;
        }

        SceneObject tree2 = SceneObjectQuery.newQuery().name("Maple tree with spile").results().nearest();
        if (tree2 != null && !Backpack.contains("Bucket of syrup")) {
            tree2.interact("Investigate");
            delay(RandomGenerator.nextInt(600, 800));
            return;
        }

        if (!Backpack.contains("Empty bucket")) {
            SceneObject bucket = SceneObjectQuery.newQuery().name("Frozen bucket").hidden(false).results().nearest();
            if (bucket != null) {
                bucket.interact("Investigate");
                delay(RandomGenerator.nextInt(600, 800));
            }
            return;
        }

        if (!Backpack.contains("Maple logs") || Backpack.contains("Spile")) {
            SceneObject tree = SceneObjectQuery.newQuery().name("Maple tree").results().nearest();
            if (tree != null) {
                tree.interact("Investigate");
                delay(RandomGenerator.nextInt(600, 800));
            }
            println("maple log");
            return;
        }

        if (Backpack.contains("Maple logs") && !Backpack.contains("Spile")) {
            Backpack.interact("Maple logs", "Investigate");
            delay(RandomGenerator.nextInt(600, 800));
            return;
        }


        if (Backpack.contains("Bucket of syrup")) {
            SceneObject tree3 = SceneObjectQuery.newQuery().name("Leaning tree").hidden(false).results().nearest();
            if (tree3 != null) {
                tree3.interact("Check");
                delay(RandomGenerator.nextInt(600, 800));
            }
            return;

        }

    }

    public static void icywater() {
        if (Backpack.contains("Barrel")) {
            talkToViolet();
        } else {
            SceneObject barrel = SceneObjectQuery.newQuery().name("Icy water").results().nearest();
            if (barrel != null) {
                barrel.interact("Reach into");
                delay(RandomGenerator.nextInt(600, 800));
                return;
            }
        }
    }

    public static void honeyshit2() {
        if (VarManager.getVarbitValue(42947) < 10) {
            if (Backpack.contains("Empty jar") || Backpack.contains("Jar of fireflies")) {
                Npc firefly = NpcQuery.newQuery().name("Firefly").results().nearest();
                if (firefly != null) {
                    firefly.interact("Catch");
                    delay(RandomGenerator.nextInt(600, 800));
                    return;

                }
                SceneObject bush = SceneObjectQuery.newQuery().name("Bush").hidden(false).id(43832).results().nearest();
                if (bush != null) {
                    bush.interact("Shake");
                    delay(RandomGenerator.nextInt(600, 800));
                }
                return;
            } else {
                talktoTrevor();
            }
        } else {
            talkToViolet();
        }
    }

    public static void interactwithsnow() {
        SceneObject icysnow = SceneObjectQuery.newQuery().name("Icy snow").results().nearest();
        if (icysnow != null) {
            icysnow.interact("Take");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }

    public static void startQuest() {
        if (!startArea.contains(player)) {
            DebugScript.moveTo(startCord);
            return;
        } else {
            Npc Violet = NpcQuery.newQuery().name("Violet").results().nearest();
            if (Violet != null) {
                Violet.interact("Talk to");
                delay(RandomGenerator.nextInt(600, 800));
                return;
            }


        }
    }

    public static void headlesspeople() {
        Npc headless = NpcQuery.newQuery().name("Headless ice golem").results().random();
        if (headless != null) {
            Coordinate head = headless.getCoordinate();
            MiniMenu.interact(WALK.getType(), 0, head.getX(), head.getY());
            return;

        } else {
            Npc Violet = NpcQuery.newQuery().name("Violet").results().nearest();
            if (Violet != null) {
                Violet.interact("Talk to");
                delay(RandomGenerator.nextInt(600, 800));
                return;
            }
        }
    }

    public static void ifinstartare() {
        SceneObject portal = SceneObjectQuery.newQuery().name("Land of Snow portal").results().nearest();
        if (portal != null) {
            portal.interact("Enter");
            delay(RandomGenerator.nextInt(600, 800));
        }
    }


    public static void buildsnowmen() {
        //42957 //42956 // 42955
        int snowman1 = VarManager.getVarbitValue(42957);
        int snowman2 = VarManager.getVarbitValue(42956);
        int snowman3 = VarManager.getVarbitValue(42955);

        if (snowman1 != 0 || snowman2 != 0 || snowman3 != 0) {
            ScriptConsole.println("11");
            int count2 = SceneObjectQuery.newQuery().name("Broken snowman").results().size();
            if (count2 >= 1) {
                Npc Violet = NpcQuery.newQuery().name("Violet").results().nearest();
                if (Violet != null) {
                    Violet.interact("Talk to");
                    delay(RandomGenerator.nextInt(600, 800));
                    return;
                }
            }
            int count = SceneObjectQuery.newQuery().name("Snowman").option("Admire").results().size();
            if (count >= 3) {
                Npc Violet = NpcQuery.newQuery().name("Violet").results().nearest();
                if (Violet != null) {
                    Violet.interact("Talk to");
                    delay(RandomGenerator.nextInt(600, 800));
                    return;
                }
            }
            SceneObject snowsnow = SceneObjectQuery.newQuery().name("Snowman").option("Add branches", "Add face", "Add hat").results().nearest();
            if (snowsnow != null) {
                snowsnow.interact("Add branches");
                snowsnow.interact("Add face");
                snowsnow.interact("Add hat");


                delay(RandomGenerator.nextInt(600, 800));
                return;
            }
            SceneObject snowman = SceneObjectQuery.newQuery().name("Melted snowman").results().nearest();
            if (snowman != null) {
                snowman.interact("Build");
                delay(RandomGenerator.nextInt(600, 800));
                return;
            }
        } else if (snowman1 == 5 && snowman2 == 5 && snowman3 == 5) {
            ScriptConsole.println("222");

            talkToViolet();
        } else {
            ScriptConsole.println("222");
            ScriptConsole.println("222");

            if (Backpack.containsAllOf("Coal", "Carrot", "Top hat", "Snowball", "Branch") && Backpack.getItem("Branch").getStackSize() == 6) {
                SceneObject snowman = SceneObjectQuery.newQuery().name("Melted snowman").results().nearest();
                if (snowman != null) {
                    snowman.interact("Build");
                    delay(RandomGenerator.nextInt(600, 800));
                    return;
                }
            } else {

                if (Backpack.containsAllOf("Coal", "Carrot", "Top hat") && Backpack.getItem("Coal").getStackSize() == 21 && Backpack.getItem("Carrot").getStackSize() == 3 && Backpack.getItem("Top hat").getStackSize() == 3) {
                    ScriptConsole.println("Hi");
                    if (Backpack.contains("Branch") && Backpack.getItem("Branch").getStackSize() == 6) {
                        return;
                    }
                    SceneObject tree = SceneObjectQuery.newQuery().name("Tree").option("Prune").results().nearest();
                    if (tree != null) {
                        tree.interact("Prune");
                        delay(RandomGenerator.nextInt(600, 800));
                    }
                    return;
                }


                if (!Backpack.contains("Snowball") || (Backpack.contains("Snowball") && Backpack.getItem("Snowball").getStackSize() < 75)) {
                    ScriptConsole.println("Hi");
                    SceneObject snow = SceneObjectQuery.newQuery().name("Pile of snow").results().nearest();
                    if (snow != null) {
                        snow.interact("Collect snow");
                        delay(RandomGenerator.nextInt(600, 800));
                        return;
                    }
                }

                SceneObject crate = SceneObjectQuery.newQuery().name("Abandoned crate").hidden(false).results().nearest();
                if (crate != null) {
                    crate.interact("Search");
                    delay(RandomGenerator.nextInt(600, 800));
                    return;
                }

            }

        }
    }

    public static void puzzle() {
        if (start == null) {
            start = player.getCoordinate();
        }
        Coordinate spot1violet = new Coordinate(start.getX(), start.getY() + 1, start.getZ());
        Coordinate spot1me = new Coordinate(start.getX(), start.getY(), start.getZ());
        Coordinate spot2violet = new Coordinate(start.getX(), start.getY() + 5, start.getZ());
        Coordinate spot2me = new Coordinate(start.getX(), start.getY() + 4, start.getZ());
        Coordinate spot3me = new Coordinate(start.getX() + 5, start.getY() + 4, start.getZ());
        Coordinate spot4me = new Coordinate(start.getX() + 5, start.getY() + 5, start.getZ());
        Coordinate spot5me = new Coordinate(start.getX() + 1, start.getY() + 5, start.getZ());
        Coordinate spot5violet = new Coordinate(start.getX(), start.getY() + 5, start.getZ());
        Coordinate spot6me = new Coordinate(start.getX() - 4, start.getY() + 5, start.getZ());
        Coordinate spot7me = new Coordinate(start.getX() - 4, start.getY(), start.getZ());
        Coordinate spot8me = new Coordinate(start.getX() - 5, start.getY() + 1, start.getZ());
        Coordinate spot9me = new Coordinate(start.getX() - 5, start.getY() + 4, start.getZ());
        Coordinate spot9violt = new Coordinate(start.getX() - 5, start.getY() + 5, start.getZ());
        Coordinate spot10me = new Coordinate(start.getX() - 5, start.getY() + 9, start.getZ());
        Coordinate spot11me = new Coordinate(start.getX() - 8, start.getY() + 9, start.getZ());
        Coordinate spot12me = new Coordinate(start.getX() - 6, start.getY() + 7, start.getZ());
        Coordinate spot13me = new Coordinate(start.getX() - 6, start.getY() + 10, start.getZ());
        Coordinate spot13violt = new Coordinate(start.getX() - 5, start.getY() + 10, start.getZ());
        Coordinate spot14me = new Coordinate(start.getX() + 2, start.getY() + 10, start.getZ());
        Coordinate spot15me = new Coordinate(start.getX() + 2, start.getY() + 7, start.getZ());
        Coordinate spot16me = new Coordinate(start.getX() + 5, start.getY() + 7, start.getZ());
        Coordinate spot17me = new Coordinate(start.getX() + 3, start.getY() + 9, start.getZ());
        Coordinate spot17violt = new Coordinate(start.getX() + 3, start.getY() + 10, start.getZ());
        Coordinate spot18me = new Coordinate(start.getX() + 3, start.getY() + 12, start.getZ());
        Coordinate spot19me = new Coordinate(start.getX() + 4, start.getY() + 12, start.getZ());
        Coordinate spot20me = new Coordinate(start.getX() + 4, start.getY() + 15, start.getZ());
        Coordinate spot21me = new Coordinate(start.getX() + 8, start.getY() + 15, start.getZ());
        Coordinate spot22me = new Coordinate(start.getX() + 8, start.getY() + 13, start.getZ());
        Coordinate spot23me = new Coordinate(start.getX() + 4, start.getY() + 13, start.getZ());
        Coordinate spot23violt = new Coordinate(start.getX() + 3, start.getY() + 13, start.getZ());
        Coordinate spot24me = new Coordinate(start.getX(), start.getY() + 9, start.getZ());
        Coordinate spot25me = new Coordinate(start.getX(), start.getY() + 12, start.getZ());
        Coordinate spot25violt = new Coordinate(start.getX(), start.getY() + 13, start.getZ());
        Coordinate spot26me = new Coordinate(start.getX(), start.getY() + 20, start.getZ());


        ScriptConsole.println(start);
        Npc violet = NpcQuery.newQuery().name("Violet in a barrel").results().nearest();
        if (violet != null) {
            if (Client.getLocalPlayer().isMoving()) {
                return;
            }
            if (player.equals(spot25me) && !violet.getCoordinate().equals(spot25violt)) {
                Movement.walkTo(spot26me.getX(), spot26me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot25me) && violet.getCoordinate().equals(spot25violt)) {
                violet.interact("Push");
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot24me)) {
                Movement.walkTo(spot25me.getX(), spot25me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot23me) && !violet.getCoordinate().equals(spot23violt)) {
                Movement.walkTo(spot24me.getX(), spot24me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot23me) && violet.getCoordinate().equals(spot23violt)) {
                violet.interact("Push");
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot22me)) {
                Movement.walkTo(spot23me.getX(), spot23me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot21me)) {
                Movement.walkTo(spot22me.getX(), spot22me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot20me)) {
                Movement.walkTo(spot21me.getX(), spot21me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot19me)) {
                Movement.walkTo(spot20me.getX(), spot20me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot18me)) {
                Movement.walkTo(spot19me.getX(), spot19me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot17me) && !violet.getCoordinate().equals(spot17violt)) {
                Movement.walkTo(spot18me.getX(), spot18me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot17me) && violet.getCoordinate().equals(spot17violt)) {
                violet.interact("Push");
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot16me)) {
                println("16 lol");
                Movement.walkTo(spot17me.getX(), spot17me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot15me)) {
                println("15 lol");
                Movement.walkTo(spot16me.getX(), spot16me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot14me)) {
                println("14 lol");

                Movement.walkTo(spot15me.getX(), spot15me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot13me) && !violet.getCoordinate().equals(spot13violt)) {
                Movement.walkTo(spot14me.getX(), spot14me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot13me) && violet.getCoordinate().equals(spot13violt)) {
                violet.interact("Push");
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot12me)) {
                Movement.walkTo(spot13me.getX(), spot13me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot11me)) {
                Movement.walkTo(spot12me.getX(), spot12me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot10me)) {
                Movement.walkTo(spot11me.getX(), spot11me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }


            if (player.equals(spot9me) && !violet.getCoordinate().equals(spot9violt)) {
                Movement.walkTo(spot10me.getX(), spot10me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot9me) && violet.getCoordinate().equals(spot9violt)) {
                violet.interact("Push");
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }

            if (player.equals(spot8me)) {
                Movement.walkTo(spot9me.getX(), spot9me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot7me)) {
                Movement.walkTo(spot8me.getX(), spot8me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot6me)) {
                Movement.walkTo(spot7me.getX(), spot7me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot5me) && !violet.getCoordinate().equals(spot5violet)) {
                Movement.walkTo(spot6me.getX(), spot6me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot5me) && violet.getCoordinate().equals(spot5violet)) {
                violet.interact("Push");
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot4me)) {
                Movement.walkTo(spot5me.getX(), spot5me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot3me)) {
                Movement.walkTo(spot3me.getX(), spot3me.getY() + 1, false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (player.equals(spot2me)) {
                Movement.walkTo(spot2me.getX() + 1, spot2me.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (violet.getCoordinate().equals(spot1violet) && player.equals(spot1me)) {
                println("yo");
                violet.interact("Push");
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            if (violet.getCoordinate().equals(spot2violet) && player.equals(start)) {
                Movement.walkTo(spot1violet.getX(), spot1violet.getY(), false);
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
        } else {
            println("Violet nul");
        }
    }

    public static void interactwithopenspace() {
        SceneObject openspace = SceneObjectQuery.newQuery().name("Open space").results().nearest();
        if (openspace != null) {
            openspace.interact("Investigate");
            delay(RandomGenerator.nextInt(600, 700));
        }
    }

    public static void makesledge() {
        int progress = VarManager.getVarbitValue(42962);

        if (progress == 0) {
            interactwithopenspace();
            return;
        } else if (progress == 1) {
            if (Backpack.containsAllOf("Barrel parts", "Rope", "Unlit lantern", "Charcoal", "Yeti sign")) {
                interactwithopenspace();
                return;
            }
            SceneObject barrle = SceneObjectQuery.newQuery().name("Rotten barrel").results().nearest();
            if (barrle != null && !Backpack.contains("Barrel parts")) {
                barrle.interact("Investigate");
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            SceneObject crate = SceneObjectQuery.newQuery().name("Crate").results().nearest();
            if (crate != null && !Backpack.contains("Rope")) {
                crate.interact("Investigate");
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            SceneObject ice = SceneObjectQuery.newQuery().name("Ice").results().nearest();
            if (ice != null && !Backpack.contains("Unlit lantern")) {
                ice.interact("Investigate");
                delay(RandomGenerator.nextInt(3000, 4000));
                return;
            }
            SceneObject fire = SceneObjectQuery.newQuery().name("Fire").results().nearest();
            if (fire != null && !Backpack.contains("Charcoal")) {
                fire.interact("Investigate");
                delay(RandomGenerator.nextInt(600, 700));
                return;
            }
            SceneObject tree = SceneObjectQuery.newQuery().name("Yeti village sign").results().nearest();
            if (tree != null && !Backpack.contains("Yeti sign")) {
                tree.interact("Chop down");
                delay(RandomGenerator.nextInt(3000, 4000));
                return;
            }
        } else if (progress > 1 && progress != 6) {
            interactwithopenspace();
            delay(RandomGenerator.nextInt(2000, 3000));
            return;
        } else {
            talkToViolet();
            return;
        }
    }
}







