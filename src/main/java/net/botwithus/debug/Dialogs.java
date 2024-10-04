package net.botwithus.debug;


import net.botwithus.api.game.hud.Dialog;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;

import java.util.List;

import static net.botwithus.debug.DebugScript.Quest.*;
import static net.botwithus.debug.DebugScript.currentQuest;

public class Dialogs {



    public static void dialog1188() {
        int number = getDialogueNumber();
        int option = 1;
        switch (number) {
            case 1 -> option = 77856776;
            case 2 -> option = 77856781;
            case 3 -> option = 77856786;
            case 4 -> option = 77856791;
            case 5 -> option = 77856796;
            default -> option = -1; // Optional: handle cases not covered above
        }
        if (option != -1) {
            MiniMenu.interact(16, 0, -1, option);
        }

    }

    public static boolean isDialogOpen() {
        if (Interfaces.isOpen(1188) ||
                Interfaces.isOpen(1184) ||
                Interfaces.isOpen(1191) ||
                Interfaces.isOpen(1193) ||
                Interfaces.isOpen(1500) ||
                Interfaces.isOpen(1189) ||
                Interfaces.isOpen(1186) ||
                Interfaces.isOpen(720) ||
                (Interfaces.isOpen(1370) && currentQuest == DebugScript.Quest.NECROMANCY_INTRO) ||
                Interfaces.isOpen(1251) ||
                Interfaces.isOpen(847) ||
                Interfaces.isOpen(1187) ||
                VarManager.getVarbitValue(21222) == 1/* ||
                Interfaces.isOpen(955)*/) {
            return true;
        }
        return false;
    }


    public static void dialog1188pick(int num) {
        int number = num;
        int option = 1;
        switch (number) {
            case 1 -> option = 77856776;
            case 2 -> option = 77856781;
            case 3 -> option = 77856786;
            case 4 -> option = 77856791;
            case 5 -> option = 77856796;
            default -> option = -1; // Optional: handle cases not covered above
        }
        if (option != -1) {
            MiniMenu.interact(16, 0, -1, option);
        }
    }

    public static void pressDialog() {

        if (Interfaces.isOpen(1188)) {
            //Violetisblue dialog
            if (VarManager.getVarbitValue(5326) == 25) {
                int num = VarManager.getVarbitValue(5327);
                switch (num) {
                    case 1, 5, 9:
                        dialog1188pick(1);
                        break;
                    case 2, 6, 10: // weapongizmo
                        dialog1188pick(2);
                        break;
                    case 3, 11, 7: // empty jar
                        dialog1188pick(3);
                        break;
                    case 4, 8, 12: // Yo-yo
                        dialog1188pick(4);
                        break;
                }
            } else {
                dialog1188();
            }
        } else if (Interfaces.isOpen(1184)) {
            ScriptConsole.println(Dialog.getText());
            if (Dialog.getText().contains("redberry pie. They REALLY like redberry pie.")) { // Knights sword endless chat from reldo
                println("Found redberry pie msg..");
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 77856772);
            } else if (Dialog.getText().contains("If I were you I would talk to Baraek,")) {
                println("Baraek into Located"); // Shield of Arrav
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 77856772);
            } else if (Dialog.getText().contains("The ruthless and notorious Black Arm ")) {
                println("Talk to Charlie"); // Shield of Arrav
                MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 77856772);
            } else {
                MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77594639);
            }
        } else if (Interfaces.isOpen(1187)) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77791252);
        } else if (Interfaces.isOpen(1191)) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 78053391);
        } else if (Interfaces.isOpen(1193)) {
            ScriptConsole.println("@ me in disc if u see this");
        } else if (Interfaces.isOpen(1500)) {
            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 98304409); // accept
        } else if (Interfaces.isOpen(1189)) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77922323);
        } else if (Interfaces.isOpen(1186)) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77725704);
        } else if (Interfaces.isOpen(720)) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 47185921);
        } else if (Interfaces.isOpen(1224)) {
            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 80216108);
        } else if (Interfaces.isOpen(1370) && currentQuest == DebugScript.Quest.NECROMANCY_INTRO) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 89784350);
        } else if (Interfaces.isOpen(847)) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 55509014);
        } else if (Interfaces.isOpen(960)) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 62914638);
        }  // read Book
        /*else if (Interfaces.isOpen(960)) { // Unreachable and worked without anyway :P
            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 62914639);} */ // Close Open Book
        else if (isCLick()) {
            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 62586895);
        }
    }

    public static Integer getDialogueNumber() {
        ScriptConsole.println(Dialog.getOptions());
        List<String> options = Dialog.getOptions();

        if (!options.isEmpty() && currentQuest != null) {
            for (Dialogue dialogue : Dialogue.values()) {
                // Only consider dialogues related to the current quest
                if (dialogue.getQuest() == currentQuest && options.contains(dialogue.getText())) {

                    // Check if this dialogue should be skipped
                    if (shouldSkipDialogue(dialogue)) {
                        continue;
                    }

                    int size = options.size();
                    int option = -1;

                    for (int i = 0; i < size; ++i) {
                        if (options.get(i).contains(dialogue.getText())) {
                            ScriptConsole.println("Interacting with option: " + dialogue.getText() + " Option: " + (i + 1 ));
                            option = i + 1;
                            break;
                        }
                    }

                    return option; // Return the found option
                }
            }
        }
        return -1; // Return -1 if no matching option found
    }

    // Method to determine if the dialogue should be skipped
    private static boolean shouldSkipDialogue(Dialogue dialogue) {

         int VARBIT_BOOK_ICE = 53558;
         int VARBIT_BOOK_SMOKE = 53559;
         int VARBIT_BOOK_SHADOW = 53560;
         int VARBIT_BOOK_BLOOD = 53561;

        switch (dialogue) {
            case ICE_TOMES:
                return VarManager.getVarbitValue(VARBIT_BOOK_ICE) >= 1; // boolean condition for if the dialog should be skipped
            case SMOKE_TOMES:
                return VarManager.getVarbitValue(VARBIT_BOOK_SMOKE) >= 1;
            case SHADOW_TOMES:
                return VarManager.getVarbitValue(VARBIT_BOOK_SHADOW) >= 1;
            case BLOOD_TOMES:
                return VarManager.getVarbitValue(VARBIT_BOOK_BLOOD) >= 1;
            default:
                return false; // Default case: do not skip
        }
    }


    public static boolean isCLick() { // Continue promt
        Component thing = ComponentQuery.newQuery(955).componentIndex(16).subComponentIndex(14).results().first();
        if (thing != null && !thing.getText().equals("") && !thing.getText().isBlank()) {
            return true;
        }
        return false;
    }

    public static enum Dialogue {

        //LOST CITY
        WHY_CAMPED_OUT_HERE(1, "Why are you camped out here?", TEST_DONTSELECT),
        WHOS_ZANARIS(1, "Who's Zanaris?", TEST_DONTSELECT),
        HIDDEN_FIND_IT(1, "If it's hidden, how are you planning to find it?", TEST_DONTSELECT),
        DONT_KNOW_EITHER(2, "Looks like you don't know either.", TEST_DONTSELECT),
        IVE_BEEN_IN_THAT_SHED(2, "I've been in that shed and I didn't see a city.", TEST_DONTSELECT),
        YES_PLEASE_TELEPORT_USEFUL(1, "Yes, please, a teleport would be useful.", TEST_DONTSELECT),
        WELL_THAT_IS_A_RISK(2, "Well, that is a risk I will have to take.", TEST_DONTSELECT),

        //Cook's Assistant
        IM_AFTER_SOME_TOP_QUALITY_MILK(1, "I'm after some top-quality milk.", COOKS_ASSISTANT),
        IM_LOOKING_FOR_EXTRA_FINE_FLOWER(1, "I'm looking for extra fine flour.", COOKS_ASSISTANT),
        DO_YOU_HAVE_ANY_OTHER_QUESTS(1, "Do you have any other quests for me?", COOKS_ASSISTANT),
        ANGRY_IT_MAKES_ME_ANGRY(1, "Angry! It makes me angry!", COOKS_ASSISTANT),
        WHAT_SEEMS_TO_BE_THE_PROBLEM(1, "What seems to be the problem?", COOKS_ASSISTANT),
        IM_FINE_THANKS(2, "I'm fine, thanks.", COOKS_ASSISTANT),
        WHAT_WRONG(1, "What's wrong?", COOKS_ASSISTANT),
        I_GET_ON_IT(1, "I'll get right on it.", COOKS_ASSISTANT),

        //Violet is Blue
        KNOCK(1, "Knock again.", VIOLET_IS_BLUE),
        READY_FOR_ADVENTURE(1, "Go on an adventure!", VIOLET_IS_BLUE),
        BREAK_THE_DOOR_DOWN(3, "Break the door down.", VIOLET_IS_BLUE),
        YOUVE_CAPTURED_A_HUMAN_GIRL(1, "YOU'VE CAPTURED A HUMAN GIRL!", VIOLET_IS_BLUE),
        USEFUL(1, "Do you have anything useful?", VIOLET_IS_BLUE),
        HEAD(1, "Happy face.", VIOLET_IS_BLUE),
        YES(1, "Yes!", VIOLET_IS_BLUE),

        //BLOOD PACT
        HANDLE(4, "I can handle this.", BLOOD_PACT),
        YES_NOW_DIE(3, "Yes. Now die!", BLOOD_PACT),
        TIME_DIE(2, "Time for you to die!", BLOOD_PACT),
        GOANYWAY(1, "Go downstairs anyway.", BLOOD_PACT),
        NIGHTMARE(4, "I'm your worst nightmare, Zamorakian scum!", BLOOD_PACT),
        WHAT_HELP_DO_YOU_NEED(1, "What help do you need?", BLOOD_PACT),
        ILL_HELP_YOU(1, "I'll help you.", BLOOD_PACT),
        YES_RESCUE_ILONA(1, "Yes, rescue Ilona.", BLOOD_PACT),
        IM_READY_FOR_MY_REWARD(1, "I'm ready for my reward.", BLOOD_PACT),

        //RESTLESS GHOST
        IM_LOOKING_FOR_A_QUEST(3, "I'm looking for a quest!", RESTLESS_GHOST),
        FATHER_AERECK_SENT_ME(2, "Father Aereck sent me to talk to you.", RESTLESS_GHOST),
        A_GHOST_IS_HAUNTING(1, "A ghost is haunting his graveyard.", RESTLESS_GHOST),
        YEP_NOW_TELL_ME(1, "Yep. Now, tell me what the problem is.", RESTLESS_GHOST),
        SKULL(1, "Put the skull in the coffin.", RESTLESS_GHOST),

        //WHAT LIES BELOW
        HELLO_THERE(2, "Hello there!", WHAT_LIES_BELOW),
        SHALL_I_GET_THEM_BACK(3, "Shall I get them back for you?", WHAT_LIES_BELOW),
        BRING_IT_ON(1, "Bring it on!", WHAT_LIES_BELOW),
        GO_ON_THEN(1, "Go on, then!", WHAT_LIES_BELOW),
        YES_I_HAVE_A_LETTER_FOR_YOU(1, "Yes! I have a letter for you.", WHAT_LIES_BELOW),
        RAT_BURGISS_SENT_ME(4, "Rat Burgiss sent me.", WHAT_LIES_BELOW),
        I_HAVE_THE_THINGS_YOU_WANTED(1, "I have the things you wanted!", WHAT_LIES_BELOW),

        //The Knight's Sword
        CLOSE(6, "redberry pie. They REALLY like redberry pie.", THE_KNIGHT_SWORD),
        CHAT(1, "Chat", THE_KNIGHT_SWORD),
        ASK_LIFE(1, "And how is life as a squire?", THE_KNIGHT_SWORD),
        OFFER_SWORD(2, "I can make a new sword if you like...", THE_KNIGHT_SWORD),
        ASK_DWARVES(1, "So would these dwarves make another one?", THE_KNIGHT_SWORD),
        SOMETHING_ELSE(2, "Something else.", THE_KNIGHT_SWORD),
        OFFER_PIE(2, "Would you like some redberry pie?", THE_KNIGHT_SWORD),
        IMCANDO_DWARVES(3, "What do you know about the Imcando dwarves?", THE_KNIGHT_SWORD),

        //Shield of Arrav
        SEARCH_QUEST(1, "I'm in search of a ", SHIELD_OF_ARRAV),
        FIND_PHOENIX_GANG(1, "Do you know where I can find the Phoenix Gang?", SHIELD_OF_ARRAV),
        PHOENIX_GANG_LOCATION(1, "Can you tell me where I can find the Phoenix Gang?", SHIELD_OF_ARRAV),
        OFFER_GOLD(1, "Alright. Have 10 gold coins.", SHIELD_OF_ARRAV),
        THANKS(2, "Thanks!", SHIELD_OF_ARRAV),
        IDENTIFY(1, "I know who you are!", SHIELD_OF_ARRAV),
        OFFER_SERVICES(1, "I'd like to offer you my services.", SHIELD_OF_ARRAV),
        FIND_BLACK_ARM_GANG(3, "Do you know where I can find the Black Arm Gang hideout?", SHIELD_OF_ARRAV),
        FAIR_PAYMENT(1, "That sounds fair. (Pay 10 gold.)", SHIELD_OF_ARRAV),
        HEARD_BLACK_ARM_GANG(1, "I've heard you're the Black Arm Gang.", SHIELD_OF_ARRAV),
        HEARD_BLACK_ARM_GANG1(5, "Get a job!", SHIELD_OF_ARRAV),
        ANY_OPTION(1, "I'd rather not reveal my sources.", SHIELD_OF_ARRAV),
        BECOME_MEMBER(1, "I want to become a member of your gang.", SHIELD_OF_ARRAV),
        GIVE_TRY(1, "Well, you can give me a try, can't you?", SHIELD_OF_ARRAV),
        GET_CROSSBOWS(1, "No problem. I'll get you two phoenix crossbows.", SHIELD_OF_ARRAV),
        TALK_SHIELD_OF_ARRAV_2(1, "Talk about the Shield of Arrav.", SHIELD_OF_ARRAV),
        FAREWELL(3, "Farewell.", SHIELD_OF_ARRAV),

        // Stolen Hearts
        LET_ME_IN(3, "Let me in or I'll poke your eyes out!", STOLEN_HEARTS),
        TELL_ME_JOBS(1, "Tell me what jobs you have in the works.", STOLEN_HEARTS),
        HOW_FIND_HQ(1, "How do we find the HQ?", STOLEN_HEARTS),
        LOCK_UP(4, "I'll lock you up and throw away the key.", STOLEN_HEARTS),
        FIND_EASILY(3, "We'll find them easily enough ourselves.", STOLEN_HEARTS),
        TAKE_PRIDE(3, "What will you take pride in while locked up?", STOLEN_HEARTS),
        CLEAR(1, "Crystal clear.", STOLEN_HEARTS),
        CARRY(1, "Carry their goods home and case the joint.", STOLEN_HEARTS),
        DIG(1, "Dig a tunnel.", STOLEN_HEARTS),
        LACKEY(1, "Have my lackeys work them over...", STOLEN_HEARTS),
        YESSR(1, "Yes.", STOLEN_HEARTS),
        BACK(1, "I'll be back soon.", STOLEN_HEARTS),
        PASSCODE(1, "Scheherazade.", STOLEN_HEARTS),
        CALM_OZAN_DOWN(1, "[Calm Ozan down.]", STOLEN_HEARTS),

        // Family Crest


        // THE GOLEM
        OPEN_PORTAL(1, "How do I open the portal?", THE_GOLEM),
        STATUETTE_UZER(3, "I'm looking for a statuette recovered from the city of Uzer.", THE_GOLEM),
        OPEN_ELDER_DEMON_PORTAL(1, "I want to open a portal to the lair of an elder-demon.", THE_GOLEM),
        LETTER_IN_DESERT(3, "I found a letter in the desert with your name on.", THE_GOLEM),

        //Rune Mythos
        IM_GOOD_WHATS_NEXT(4, "I'm good. What's next?", RUNE_MYTHOS),
        YES_yes(4, "Yes.", RUNE_MYTHOS),
        //Ghosts Ahoy
        YES_DONT_ASK(1, "Yes, and don't ask me again", GHOSTS_AHOY),
        WHY_WHAT_IS_THE_MATTER(1, "Why, what is the matter?", GHOSTS_AHOY),
        YES_I_DO_ITS_A_SAD_STORY(1, "Yes, I do. It is a very sad story.", GHOSTS_AHOY),
        DO_YOU_KNOW_WHERE_THIS_WOMAN_CAN_BE_FOUND(1, "Do you know where this woman can be found?", GHOSTS_AHOY),
        YOU_ARE_DOING_SO_MUCH_FOR_ME(1, "You are doing so much for me - is there anything I can do for you?", GHOSTS_AHOY),
        IS_THIS_YOUR_TOY_BOAT(1, "Is this your toy boat?", GHOSTS_AHOY),
        OKAY_WAIT_HERE(1, "Okay, wait here - I'll get you your bow.", GHOSTS_AHOY),
        TALK_ABOUT_SOMETHING_ELSE(1, "Talk about something else.", GHOSTS_AHOY),
        YES_ILL_GIVE_YOU_A_GAME(1, "Yes, I'll give you a game.", GHOSTS_AHOY),
        DO_YOU_HAVE_ANY_JOBS(1, "Do you have any jobs I can do?", GHOSTS_AHOY),
        YES_ID_BE_DELIGHTED(1, "Yes, I'd be delighted.", GHOSTS_AHOY),
        AFTER_HEARING_VELORINAS_STORY(1, "After hearing Velorina's story I will be happy to help out.", GHOSTS_AHOY),
        TALK_TO_GHOST_CAPTAIN(1, "Please take me to Dragontooth Island.", GHOSTS_AHOY),
        LET_ANY_GHOST_PASS_ON(1, "Let any ghost who so wishes pass on into the next world.", GHOSTS_AHOY),

        //Vessel Harbinger
        TALK_ABOUT_VESSEL_HARINGER(1, "Talk about 'Vessel of the Harbinger'.", VESSEL_HARINGER),

        //Tomes of Warlock
        RASIAL_VESSEL_ORGAN(1, "Death, I've been to Rasial's citadel. I think his vessel is a giant organ.", TOMES_OF_WARLOCK),
        YES_TOMES(1, "Yes.", TOMES_OF_WARLOCK),
        ICE_TOMES(1, "Ice.", TOMES_OF_WARLOCK),
        SMOKE_TOMES(2, "Smoke.", TOMES_OF_WARLOCK),
        SHADOW_TOMES(3, "Shadow.", TOMES_OF_WARLOCK),
        BLOOD_TOMES(4, "Blood.", TOMES_OF_WARLOCK),
        HAND_IN_BOOK(1, "Yes", TOMES_OF_WARLOCK),
        NO_THANKS(2, "No thanks.", TOMES_OF_WARLOCK),
        GOODBYE(1, "Goodbye.", TOMES_OF_WARLOCK),

        // Daughter of Chaos
        I_AM_READY(1, "I'm ready.", DAUGHTER_OF_CHAOS),
        ILL_SEE_IT_DONE(2, "I'll see it done.", DAUGHTER_OF_CHAOS),
        SHOULD_MOVE_ON(4, "We should move on.", DAUGHTER_OF_CHAOS),
        AGREE_WITH_ADRASTEIA(1, "Agree with Adrasteia.", DAUGHTER_OF_CHAOS),
        REPORT_BACK_TO_ADRASTEIA(2, "I'll report back to Adrasteia.", DAUGHTER_OF_CHAOS),
        LOWER_THE_DIFFICULTY(1, "Lower the difficulty.", DAUGHTER_OF_CHAOS),
        ADRASTEIA_SENT_ME(1, "Adrasteia sent me.", DAUGHTER_OF_CHAOS),
        RESPOND_DIPLOMATICALLY(1, "Respond diplomatically.", DAUGHTER_OF_CHAOS),

        //Diamond in the Rough
        LET_S_KEEP_GOING(4, "Let's keep going.", DIAMOND_ROUGH),
        CALL_FOR_HELP(4, "Call for help.", DIAMOND_ROUGH),
        REACH_FOR_THE_CACTUS(1, "Reach for the cactus.", DIAMOND_ROUGH),
        GRASP_FOR_THE_ROCK(2, "Grasp for the rock.", DIAMOND_ROUGH),
        DO_NOTHING(4, "Do nothing.", DIAMOND_ROUGH),

        //Jack of Spades
        TALK_ABOUT_JACK_OF_SPADES(1, "Talk about Jack of Spades.", JACK_OF_SPADES),
        NO_MORE_QUESTIONS(5, "No more questions.", JACK_OF_SPADES),
        I_KNOW_ALL_I_NEED_LET_S_GO(1, "I know all I need - let's go!", JACK_OF_SPADES),
        YES_LET_S_GO(1, "Yes, let's go!", JACK_OF_SPADES),
        I_D_LIKE_TO_ASK_FOR_DIRECTIONS(1, "I'd like to ask for directions.", JACK_OF_SPADES),
        CAN_YOU_TELL_ME_WHERE_THE_PORTS_DISTRICT_IS(1, "Can you tell me where the Ports district is?", JACK_OF_SPADES),
        I_VE_HEARD_ENOUGH(4, "I've heard enough.", JACK_OF_SPADES),
        WHY_STEAL_FROM_PEOPLE(3, "Why steal from those people?", JACK_OF_SPADES),

        //New Foundation
        YES_NEW_FOUNDATION(2, "Yes", NEW_FOUNDATION), 
        YES_RITUAL(2, "Yes.", NEW_FOUNDATION),
        SIGN_ME_UP(2, "Sign me up!", NEW_FOUNDATION),
        DUKES_QUEST(1, "Duke.", NEW_FOUNDATION),

        //Kili's row I
        TALK_ABOUT_UPGRADE1(1, "Talk about active equipment upgrade task.", KILI_ROW_I),
        CURRENT_TASK1(1, "Ask about your current task.", KILI_ROW_I),

        //Kili's row II
        TALK_ABOUT_UPGRADE2(1, "Talk about active equipment upgrade task.", KILI_ROW_II),
        CURRENT_TASK2(1, "Ask about your current task.", KILI_ROW_II);

        //Kili's row III
        //TALK_ABOUT_UPGRADE3(1, "Talk about active equipment upgrade task.", KILI_ROW_III),
        //CURRENT_TASK3(1, "Ask about your current task.", KILI_ROW_III);

        private final int number;
        private final String text;
        private final DebugScript.Quest quest;

        Dialogue(int number, String text, DebugScript.Quest quest) {
            this.number = number;
            this.text = text;
            this.quest = quest;
        }

        public int getNumber() {
            return number;
        }

        public String getText() {
            return text;
        }

        public DebugScript.Quest getQuest() {
            return quest;
        }
    }



    public static void println(String msg) {
        ScriptConsole.println(msg);
    }

}
