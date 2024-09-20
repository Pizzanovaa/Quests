package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.minimenu.actions.NPCAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.queries.results.EntityResultSet;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.debug.Dialogs.println;
import static net.botwithus.rs3.script.Execution.delay;
import static net.botwithus.rs3.script.Execution.delayUntil;

public class TomesOfWarlock {

    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate startcord = new Coordinate(1101, 1776, 1);
    static Area.Circular startarea = new Area.Circular(startcord, 10);

    static Coordinate nabanikCoord = new Coordinate(3352, 3194, 0);
    static Area.Circular nabanikArea = new Area.Circular(nabanikCoord, 10);

    static Coordinate examCentreCoord = new Coordinate(3360, 3355, 0);
    static Area.Circular examCentreArea = new Area.Circular(examCentreCoord, 10);

    static Coordinate varrockLibrary = new Coordinate(3209, 3495, 0);
    static Area.Circular varrockLibraryArea = new Area.Circular(varrockLibrary, 10);

    static Coordinate auburyCoord = new Coordinate(3253, 3402, 0);
    static Area.Circular auburyArea = new Area.Circular(auburyCoord, 4);

    static Coordinate wizardsTower = new Coordinate(3102, 3165, 0);
    static Area.Circular wizardsTowerArea = new Area.Circular(wizardsTower, 10);

    static Coordinate wizardsGuild = new Coordinate(2589, 9489, 0);
    static Area.Circular wizardsGuildArea = new Area.Circular(wizardsGuild, 5);

    static Coordinate menaphosLibrary = new Coordinate(2107, 6756, 1);
    static Area.Circular menaphosLibraryArea = new Area.Circular(menaphosLibrary, 10);

    static Coordinate sundialCoordinate = new Coordinate(2078, 6752, 1);
    static Area.Circular sundialCoordinateArea = new Area.Circular(sundialCoordinate, 4);

    static Coordinate benchPosition = new Coordinate(3355, 3396, 0);
    static Area.Circular benchArea = new Area.Circular(benchPosition, 10);

    static Coordinate tedLocation = new Coordinate(1040, 1784, 1);
    static Area.Circular tedLocationArea = new Area.Circular(tedLocation, 10);

    private static final int[] BOOK_IDS = {55250, 55252, 55254, 55256};
    static int VARBIT_QUEST_PROGRESS = 53555;
    static int VARBIT_BOOK_ICE = 53558;
    static int VARBIT_BOOK_SMOKE = 53559;
    static int VARBIT_BOOK_SHADOW = 53560;
    static int VARBIT_BOOK_BLOOD = 53561;

    public static void quest() {
        player = Client.getLocalPlayer().getServerCoordinate();

        int questProgress = VarManager.getVarbitValue(VARBIT_QUEST_PROGRESS);
        int bookIce = VarManager.getVarbitValue(VARBIT_BOOK_ICE);
        int bookSmoke = VarManager.getVarbitValue(VARBIT_BOOK_SMOKE);
        int bookShadow = VarManager.getVarbitValue(VARBIT_BOOK_SHADOW);
        int bookBlood = VarManager.getVarbitValue(VARBIT_BOOK_BLOOD);

        println("Current quest progress: " + questProgress);
        println("Book progress - Ice: " + bookIce + ", Smoke: " + bookSmoke + ", Shadow: " + bookShadow + ", Blood: " + bookBlood);

        if (isDialogOpen()) {
            println("Dialog is open, waiting for it to close.");
            return;
        }

        if (Client.getLocalPlayer().isMoving()) {
            println("Player is currently moving, waiting for them to stop.");
            return;
        }

        switch (questProgress) {
            case 0:
                println("Starting quest, checking if we are in the starting area.");
                if (!startarea.contains(player)) {
                    println("Player is not in the starting area. Moving to start coordinates.");
                    DebugScript.moveTo(startcord);
                } else {
                    println("Player is in the starting area. Talking to Death.");
                    talkToDeath();
                }
                break;

            case 7:
                println("Head to Dr Nabanik.");
                if (!nabanikArea.contains(player)) {
                    println("Player is not near Dr Nabanik. Moving to his coordinates.");
                    DebugScript.moveTo(nabanikCoord);
                } else {
                    println("Player is in Dr Nabanik's area. Talking to Dr Nabanik.");
                    talkToNabanik();
                }
                break;

            case 10:
                println("Head to the Exam Centre.");
                if (!examCentreArea.contains(player)) {
                    println("Player is not near the Exam Centre. Moving to Exam Centre.");
                    DebugScript.moveTo(examCentreCoord);
                } else {
                    println("Player is in the Exam Centre area. Talking to Head of Research until all book varbits are 1.");
                    talkToHeadOfResearchUntilBooksReady();
                }
                break;

            case 20:
                println("Collecting books.");
                if (bookIce == 1) {
                    println("Collecting Ice book from Varrock Library.");
                    collectBook("Varrock Library", varrockLibraryArea, varrockLibrary, "Reldo");
                } else if (bookSmoke == 1) {
                    println("Talking to Aubury about the Smoke book.");
                    collectBook("Aubury's Rune Shop", auburyArea, auburyCoord, "Aubury");
                } else if (bookSmoke == 2) {
                    println("Proceed to Wizard Borann to collect the Smoke book.");
                    collectBook("Wizards' Tower", wizardsTowerArea, wizardsTower, "Wizard Borann");
                } else if (bookShadow == 1) {
                    println("Collecting Shadow book from Menaphos Library.");
                    collectBook("Menaphos Library", menaphosLibraryArea, menaphosLibrary, "Kohnen the librarian");
                } else if (bookShadow == 2) {
                    println("Collecting Shadow book from sundial");
                    if (!sundialCoordinateArea.contains(player)) {
                        println("Player is not near the Sundial coordinates. Moving to Sundial coordinates.");
                        DebugScript.moveTo(sundialCoordinate);
                    } else {
                        println("Player is near Sundial coordinates. Inspecting....");
                        EntityResultSet<SceneObject> sundials = SceneObjectQuery.newQuery().name("Sundial (Menaphos)").option("Inspect").results();
                        SceneObject sundial = sundials.nearest();
                        if (sundial != null) {
                            sundial.interact("Inspect");
                            delay(RandomGenerator.nextInt(2000, 3000));
                        }
                    }
                } else if (bookBlood == 2) {
                    println("Collecting Blood book....");
                    collectBook("Wizards' Guild", wizardsGuildArea, wizardsGuild, "Wizard Frumscone");
                } else if (bookBlood == 1) {
                    println("Talk to Boraan about Book of Blood.");
                    if (!wizardsTowerArea.contains(player)) {
                        println("Player is not near Boraan. Moving to Boraan coordinates.");
                        DebugScript.moveTo(wizardsTower);
                    }
                } else {
                    println("Player is near Boraan coordinates. Talking....");
                    talkToNpc("Wizard Borann");
                }
                if (bookIce == 3 && bookSmoke == 3 && bookShadow == 3 && bookBlood == 3) {
                    println("All books collected. Proceeding to repair stage.");
                    repairBooks();

                }
                break;
            case 27:
                println("Talking to Death.");
                if (!startarea.contains(player)) {
                    println("Player is not in the starting area. Moving to start coordinates.");
                    DebugScript.moveTo(startcord);
                } else {
                    println("Player is in the starting area. Talking to Death.");
                    talkToDeath();
                }
                break;
            case 30:
                println("Talking to Ted to complete the quest.");
                if (!tedLocationArea.contains(player)) {
                    println("Player is not near Ted to complete the quest.");
                    DebugScript.moveTo(tedLocation);
                } else {
                    println("Player is near Ted to complete the quest.");
                    talkToNpc("Ted");
                }
                break;
            case 31:
                println("Quest Complete");
                break;
            default:
                println("Unknown quest stage.");
                break;
        }
    }

    private static void talkToHeadOfResearchUntilBooksReady() {
        int bookIce = VarManager.getVarbitValue(VARBIT_BOOK_ICE);
        int bookSmoke = VarManager.getVarbitValue(VARBIT_BOOK_SMOKE);
        int bookShadow = VarManager.getVarbitValue(VARBIT_BOOK_SHADOW);
        int bookBlood = VarManager.getVarbitValue(VARBIT_BOOK_BLOOD);

        while (bookIce < 1 || bookSmoke < 1 || bookShadow < 1 || bookBlood < 1) {
            println("Talking to Head of Research to obtain all book locations.");
            talkToNpc("Head of Research");
            delay(RandomGenerator.nextInt(1000, 1500));
            bookIce = VarManager.getVarbitValue(VARBIT_BOOK_ICE);
            bookSmoke = VarManager.getVarbitValue(VARBIT_BOOK_SMOKE);
            bookShadow = VarManager.getVarbitValue(VARBIT_BOOK_SHADOW);
            bookBlood = VarManager.getVarbitValue(VARBIT_BOOK_BLOOD);
        }
        println("All books are ready for collection.");
    }

    private static void collectBook(String locName, Area.Circular area, Coordinate coord, String npcName) {
        if (!area.contains(Client.getLocalPlayer().getServerCoordinate())) {
            println("Player is not in " + locName + ". Moving to the specific coordinates: " + coord);
            DebugScript.moveTo(coord);
        } else {
            println("Player is at " + locName + ". Talking to " + npcName + " to collect the book.");
            talkToNpc(npcName);
            delay(RandomGenerator.nextInt(1000, 1500));
        }
    }

    public static void talkToNpc(String npcName) {
        println("Searching for NPC: " + npcName);
        Npc npc = NpcQuery.newQuery().name(npcName).results().nearest();
        if (npc != null) {
            println("NPC " + npcName + " found. Interacting.");
            npc.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        } else {
            println("NPC " + npcName + " not found.");
        }
    }

    public static void talkToDeath() {
        println("Searching for Death NPC.");
        Npc death = NpcQuery.newQuery().name("Death").results().nearest();
        if (death != null) {
            println("Death NPC found. Interacting.");
            death.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        } else {
            println("Death NPC not found.");
        }
    }

    public static void talkToNabanik() {
        Npc nabanik = NpcQuery.newQuery().name("Dr Nabanik").results().nearest();
        if (nabanik != null) {
            println("Talking to Dr Nabanik.");
            nabanik.interact(NPCAction.NPC1);
            delay(RandomGenerator.nextInt(600, 800));
        } else {
            println("Dr Nabanik not found.");
        }
    }

    private static void repairBooks() {
        println("Checking inventory for all required books.");

        boolean hasAllBooks = true;

        for (int bookId : BOOK_IDS) {
            if (InventoryItemQuery.newQuery().ids(bookId).results().isEmpty()) {
                hasAllBooks = false;
                break;
            }
        }

        if (hasAllBooks) {
            println("All books are present. Moving to exam center.");
            DebugScript.moveTo(examCentreCoord);
            talkToNpc("Head of Research");
            return;
        }

        println("Books missing. Proceeding with repair process.");

        EntityResultSet<SceneObject> workbenches = SceneObjectQuery.newQuery().name("Archaeologist's workbench").option("Restore").results();
        if (!workbenches.isEmpty()) {
            SceneObject workbench = workbenches.first();
            if (workbench != null) {
                println("Workbench found. Interacting with the workbench to restore books.");
                workbench.interact("Restore");
                if (Execution.delayUntil(5000, () -> Interfaces.isOpen(1370))) {
                    println("Workbench interface opened. Interacting with the restore dialogue.");
                    MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
                    Execution.delayUntil(6000, () -> Interfaces.isOpen(1251));
                } else {
                    println("Failed to open workbench interface.");
                }
            }
        } else {
            println("Workbench not found. Moving to the workbench location.");
            DebugScript.moveTo(benchPosition);
        }
    }
}
