package net.botwithus.debug;


import net.botwithus.api.game.hud.Dialog;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        } else if (Interfaces.isOpen(720) && currentQuest != NEW_FOUNDATION && currentQuest != ARCH_TUTORIAL) {
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
        List<String> options = Dialog.getOptions();

        if (!options.isEmpty() && currentQuest != null) {
            ScriptConsole.println(options);
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
                            ScriptConsole.println("Interacting with option: " + dialogue.getText() + " Option: " + (i + 1));
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

        //Death Plateau
        DO_YOU_HAVE_ANY_QUESTS_FOR_ME(1, "Do you have any quests for me?", DEATH_PLATEAU),
        Ive_been_sent_to_look_for_a_route_to_Death_Plateau_Can_you_help(1, "I've been sent to look for a route to Death Plateau. Can you help?", DEATH_PLATEAU),
        Can_you_put_some_fresh_spikes_on_these_climbing_boots_for_me(1, "Can you put some fresh spikes on these climbing boots for me?", DEATH_PLATEAU),
        Prepare_to_die_troll(3, "Prepare to die, troll!", DEATH_PLATEAU),

        //Druidic Ritual
        Talk_about_Druidic_Ritual(1, "Talk about Druidic Ritual.", DRUIDIC_RITUAL),
        What_do_you_need_help_with(1, "What do you need help with?", DRUIDIC_RITUAL),
        Im_pretty_sure_Ive_heard_that_before(1, "I'm pretty sure I've heard that before.", DRUIDIC_RITUAL),
        Ive_been_sent_to_help_make_an_ointment_of_imbalance_for_Kaqemeex(1, "I've been sent to help make an ointment of imbalance for Kaqemeex.", DRUIDIC_RITUAL),
        Ok_Ill_do_that_then(1, "Ok, I'll do that then.", DRUIDIC_RITUAL),

        //Let Them Eat Pie
        //Id_best_get_on_with_it_then(1, "I'd best get on with it then.", LET_THEM_EAT_PIE),

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
        READY_FOR_ADVENTURE1(1, "To go on an adventure.", VIOLET_IS_BLUE),
        KNOCK(1, "Knock again.", VIOLET_IS_BLUE),
        READY_FOR_ADVENTURE(1, "Go on an adventure!", VIOLET_IS_BLUE),
        BREAK_THE_DOOR_DOWN(3, "Break the door down.", VIOLET_IS_BLUE),
        YOUVE_CAPTURED_A_HUMAN_GIRL(1, "YOU'VE CAPTURED A HUMAN GIRL!", VIOLET_IS_BLUE),
        USEFUL(1, "Do you have anything useful?", VIOLET_IS_BLUE),
        HEAD(1, "Happy face.", VIOLET_IS_BLUE),
        YES(1, "Yes!", VIOLET_IS_BLUE),
        VIOLET_YES(1, "Yes.", VIOLET_IS_BLUE),

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
        HI_I_AM_A_BOLD_ADVENTURER(1, "Hi, I am a bold adventurer.", FAMILY_CREST_INCOMPLETE),
        SO_WHERE_IS_THIS_CREST(2, "So where is this crest?", FAMILY_CREST_INCOMPLETE),
        ARE_YOU_CALEB_FITZHARMON(1, "Are you Caleb Fitzharmon?", FAMILY_CREST_INCOMPLETE),
        SO_CAN_I_HAVE_YOUR_BIT(2, "So can I have your bit?", FAMILY_CREST_INCOMPLETE),
        OK_I_WILL_GET_THOSE(1, "Ok, I will get those.", FAMILY_CREST_INCOMPLETE),
        UH_WHAT_HAPPENED_TO_THE_REST_OF_IT(1, "Uh... what happened to the rest of it?", FAMILY_CREST_INCOMPLETE),
        IM_LOOKING_FOR_A_MAN_NAMED_AVAN_FITZHARMON(1, "I'm looking for a man named Avan Fitzharmon.", FAMILY_CREST_INCOMPLETE),
        YES_SMELT_PERFECT_GOLDEN_RING(1, "Yes smelt 'perfect' golden ring.", FAMILY_CREST_INCOMPLETE),
        YES_SMELT_PERFECT_GOLDEN_NECKLACE(2, "Yes smelt 'perfect' golden necklace.", FAMILY_CREST_INCOMPLETE),
        WHERE_CAN_I_FIND_CHRONOZON(2, "Where can I find Chronozon?", FAMILY_CREST_INCOMPLETE),
        ASK_ABOUT_GAUNTLETS(1, "Ask about gauntlets.", FAMILY_CREST_INCOMPLETE),
        RECLAIM_LOST_GAUNTLETS(1, "Reclaim lost gauntlets.", FAMILY_CREST_INCOMPLETE),
        YES_PLEASE(1, "Yes please.", FAMILY_CREST_INCOMPLETE),

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
        LEAVE(1, "Leave the starting area.", NEW_FOUNDATION),
        YES_RITUAL(2, "Yes.", NEW_FOUNDATION),
        SIGN_ME_UP(2, "Sign me up!", NEW_FOUNDATION),
        DUKES_QUEST(1, "Duke.", NEW_FOUNDATION),

        //Kili's row I
        TALK_ABOUT_UPGRADE1(1, "Talk about active equipment upgrade task.", KILI_KNOWLEDGE_I),
        CURRENT_TASK1(1, "Ask about your current task.", KILI_KNOWLEDGE_I),

        //Kili's row II
        TALK_ABOUT_UPGRADE2(1, "Talk about active equipment upgrade task.", KILI_KNOWLEDGE_II),
        CURRENT_TASK2(1, "Ask about your current task.", KILI_KNOWLEDGE_II),

        //Kili's row III
        TALK_ABOUT_UPGRADE3(1, "Talk about active equipment upgrade task.", KILI_KNOWLEDGE_III),
        CURRENT_TASK3(1, "Ask about your current task.", KILI_KNOWLEDGE_III),

        //Kili's row IV
        TALK_ABOUT_UPGRADE4(1, "Talk about active equipment upgrade task.", KILI_KNOWLEDGE_IV),
        CURRENT_TASK4(1, "Ask about your current task.", KILI_KNOWLEDGE_IV),    

        //Kili's row V
        TALK_ABOUT_UPGRADE5(1, "Talk about active equipment upgrade task.", KILI_KNOWLEDGE_V),
        CURRENT_TASK5(1, "Ask about your current task.", KILI_KNOWLEDGE_V),    

        //Enter the Abyss
        YES_ENTER_THE_ABYSS(1, "Yes", ENTER_THE_ABYSS),
        WHERE_RUNES(2, "Where do you get your runes from?", ENTER_THE_ABYSS),

        //Whats Mine Is Yours
        DONT_WORRY_ILL_HELP_YOU(1, "Don't worry, I'll help you.", WHATS_MINE_IS_YOURS),
        YES_WHATS_MINE_IS_YOURS(2, "Yes", WHATS_MINE_IS_YOURS),
        YES_WHATS_MINE_IS_YOURS1(1, "Yes.", WHATS_MINE_IS_YOURS),
        I_LL_LEAVE_RIGHT_AWAY(3, "I'll leave right away.", WHATS_MINE_IS_YOURS),
        DORIC_SENT_ME_HERE_WITH_THESE_WEAPONS(1, "Doric sent me here with these weapons.", WHATS_MINE_IS_YOURS),
        I_D_LOVE_TO(1, "I'd love to.", WHATS_MINE_IS_YOURS),
        YES_I_HAVE_THEM_HERE(1, "Yes, I have them here.", WHATS_MINE_IS_YOURS),
        BORIC_TELL_DORIC_ABOUT_YOUR_GRADUATION(1, "Boric, tell Doric about your graduation.", WHATS_MINE_IS_YOURS),
        BORIC_TELL_DORIC_WHAT_YOU_THOUGHT_WHEN_DORIC_SENT_YOU_AWAY(1, "Boric, tell Doric what you thought when Doric sent you away.", WHATS_MINE_IS_YOURS),
        BORIC_TELL_DORIC_WHAT_YOU_THOUGHT_OF_YOUR_EDUCATION(1, "Boric, tell Doric what you thought of your education.", WHATS_MINE_IS_YOURS),
        BORIC_TELL_DORIC_WHY_YOU_WANTED_TO_STAY_HERE(1, "Boric, tell Doric why you wanted to stay here.", WHATS_MINE_IS_YOURS),
        BORIC_TELL_DORIC_WHAT_YOU_THOUGHT_OF_KELDAGRIM(1, "Boric, tell Doric what you thought of Keldagrim.", WHATS_MINE_IS_YOURS),
        DORIC_TELL_BORIC_WHAT_YOU_THOUGHT_WHEN_BORIC_WAS_BORN(1, "Doric, tell Boric what you thought when Boric was born.", WHATS_MINE_IS_YOURS),
        BORIC_TELL_BORIC_WHY_YOU_SENT_HIM_TO_KELDAGRIM(1, "Boric, tell Boric why you sent him to Keldagrim.", WHATS_MINE_IS_YOURS),
        BORIC_TELL_BORIC_HOW_YOU_FELT_BRINGING_BORIC_UP_ALONE(1, "Boric, tell Boric how you felt bringing Boric up alone.", WHATS_MINE_IS_YOURS),
        DORIC_TELL_BORIC_WHAT_YOU_HOPE_SENDING_HIM_AWAY_WOULD_TEACH_HIM(1, "Doric, tell Boric what you hoped sending him away would teach him.", WHATS_MINE_IS_YOURS),

        //Gertrude Cat
        WHAT_WILL_MAKE_YOU_TELL_ME(2, "What will make you tell me?", GERTRUDE_CAT),
        OKAY_THEN_ILL_PAY(2, "Okay then, I'll pay.", GERTRUDE_CAT),

        //Anachronia Tutorial
        YES_ANACHRONIA_TUTORIAL(1, "Yes.", ANACHRONIA_TUT),

        //Mogre Lore Activity
        WHO_ARE_THEY(2, "Who are (Dramatic pause) THEY?", MOGRE_ACTIVITY),
        THROW_THE_WATER(1, "Throw the water!", MOGRE_ACTIVITY),

        //Christmas Reunion
        PLAIN_BROWN_OUTFITS_DONT_EXACTLY_SCREAM_CHRISTMAS(1, "The plain brown outfits don't exactly scream 'Christmas'.", CHRISTMAS_REUNION),
        TALK_ABOUT_A_CHRISTMAS_REUNION(1, "Talk about 'A Christmas Reunion'.", CHRISTMAS_REUNION),
        COULD_I_PAY_YOU_TO_MAKE_THEM_FOR_ME(1, "Could I pay you to make them for me?", CHRISTMAS_REUNION),
        MAKE_CHRISTMAS_DYE(1, "Make Christmas dye.", CHRISTMAS_REUNION),
        RED_AND_BLACK(1, "Red and black.", CHRISTMAS_REUNION),
        THE_ABBEY(1, "The abbey?", CHRISTMAS_REUNION),

        //Its Snow Bother
        ITS_SNOW_BOTER(1, "Talk about 'It's Snow Bother'.", ITS_SNOW_BOTHER),
        TALK_TO_RELDO(2, "[Talk to Reldo.]", ITS_SNOW_BOTHER),

        //Dead and Buried
        TALK_ABOUT_DEAD_AND_BURIED(1, "Talk about 'Dead and Buried'.", DEAD_AND_BURIED),
        CONCLUDE_INTERVIEW(4, "(Conclude interview.)", DEAD_AND_BURIED),
        YES_I_VE_ASKED_ENOUGH_QUESTIONS(1, "Yes, I've asked enough questions.", DEAD_AND_BURIED),
        YES_I_KNOW_THAT(1, "Yes, I know that.", DEAD_AND_BURIED),
        MUST_WE(1, "Must we?", DEAD_AND_BURIED),
        LEARDERSHIP_SKILLS(1, "Leadership skills.", DEAD_AND_BURIED),
        YOUR_MAJESTY(1, "Your Majesty?", DEAD_AND_BURIED),
        WHY_DID_YOU_MARRY_THE_KING(1, "Why did you marry the king?", DEAD_AND_BURIED),
        COULDNT_YOU_HAVE_BEEN_A_WARRIIOR_QUEEN(1, "Couldn't you have been a warrior queen?", DEAD_AND_BURIED),
        SO_YOU_STARTED_WEARING_A_DISGUISE(1, "So you started wearing a disguise?", DEAD_AND_BURIED),
        YES_DEAD_AND_BURIED(1, "Yes.", DEAD_AND_BURIED),
        ENTER_DREAM(1, "Enter dream.", DEAD_AND_BURIED),
        TALK_ABOUT_QUESTS(2, "Talk about quests.", DEAD_AND_BURIED),

        //Ancient Awakening
        TALK_ABOUT_ANCIENT_AWAKENING(2, "Talk about 'Ancient Awakening'.", ANCIENT_AWAKENING),
        I_DONT_NEED_TO_KNOW_YOUR_FAMILY_HISTORY(1, "I don't need to know your family history, Bill.", ANCIENT_AWAKENING),
        LETS_SKIP_THE_SMALL_TALK(1, "Let's skip the small talk.", ANCIENT_AWAKENING),
        I_NEED_YOU_TO_RETURN_TO_THE_FORT(1, "I need you to return to the fort.", ANCIENT_AWAKENING),
        YES_ANCIENT_AWAKENING(1, "Yes.", ANCIENT_AWAKENING),
        WHO_ARE_YOU(1, "Who are you?", ANCIENT_AWAKENING),
        WHAT_IS_THIS_PLACE(1, "What is this place?", ANCIENT_AWAKENING),
        TELL_ME_ABOUT_VORKATH(1, "Tell me about Vorkath.", ANCIENT_AWAKENING),
        TELL_ME_ABOUT_THIS_TOMB(1, "Tell me about this tomb.", ANCIENT_AWAKENING),
        NONSENSE_ASTER_IS_DOING_FINE(1, "Nonsense! Aster is doing fine.", ANCIENT_AWAKENING),
        What_ARE_YOU_DOING_HERE(1, "What are you doing here?", ANCIENT_AWAKENING),
        IS_IT_SAFE_FOR_CIVILIANS_TO_BE_HERE(1, "Is it safe for civilians to be here?", ANCIENT_AWAKENING),
        I_WAS_JUST_CURIOUS(1, "I was just curious.", ANCIENT_AWAKENING),
        WE_NEED_TO_FOCUS(1, "We need to focus.", ANCIENT_AWAKENING),
        CAN_YOU_ACTIVATE_THIS_CONDUIT(1, "Can you activate this conduit?", ANCIENT_AWAKENING),

        //Battle of Forinthry
        TALK_ABOUT_BATTLE_OF_FORINTHRY(1, "Talk about 'Battle of Forinthry'.", BATTLE_OF_FORINTHRY),
        YES_I_AM_READY(1, "Yes, I am ready.", BATTLE_OF_FORINTHRY),
        OF_COURSE(1, "Of course.", BATTLE_OF_FORINTHRY),
        YES_BEGIN_THE_FEAST(1, "Yes, begin the feast.", BATTLE_OF_FORINTHRY),

        //Requiem for a Dragon
        
        TALK_ABOUT_QUESTS_REQUIEM(1, "Talk about quests...", REQUIEM_FOR_A_DRAGON),
        TALK_ABOUT_REQUIEM_FOR_A_DRAGON(1, "Talk about 'Requiem for a Dragon'.", REQUIEM_FOR_A_DRAGON),
        YES_REQUIEM(1, "Yes.", REQUIEM_FOR_A_DRAGON),
        REACH_OUT_YOUR_HAND(1, "Reach out your hand.(friendly)", REQUIEM_FOR_A_DRAGON),
        KEEP_YOUR_HAND_EXTENDED(1, "Keep your hand extended.(friendly)", REQUIEM_FOR_A_DRAGON),
        ENCOURAGE_VORKATH(1, "Encourage Vorkath.(friendly)", REQUIEM_FOR_A_DRAGON),
        TRY_NOT_TO_SHIVER(1, "Try not to shiver.(friendly)", REQUIEM_FOR_A_DRAGON),
        //STUDY_THE_CREATURE(1, "Study the creature. (neutral)", REQUIEM_FOR_A_DRAGON),
        LAY_THE_DRAGON_S_SOUL_TO_REST(1, "Lay the dragon's soul to rest. (Necromancy)", REQUIEM_FOR_A_DRAGON),
        // THE_CONGEALED_POTION(1, "The congealed potion.", REQUIEM_FOR_A_DRAGON),
        // THE_BROKEN_FOCUS(1, "The broken focus.", REQUIEM_FOR_A_DRAGON),
        // THE_CRYSTAL_SHARD(1, "The crystal shard.", REQUIEM_FOR_A_DRAGON),
        // THE_WALL_MURAL(1, "The wall mural.", REQUIEM_FOR_A_DRAGON),
        NO_HE_SUFFERED_ENOUGH(1, "No, he's suffered enough.", REQUIEM_FOR_A_DRAGON),
        // SHOW_OFF_YOUR_MASTERY_OF_NECROMANCY(1, "Show off your mastery of necromancy.", REQUIEM_FOR_A_DRAGON),
        // WHAT_DID_YOU_DO_TO_VORKATH(1, "What did you do to Vorkath?", REQUIEM_FOR_A_DRAGON),
        MOCK_HIS_SKELETAL_FORM(1, "Mock his skeletal form.", REQUIEM_FOR_A_DRAGON),
        REMIND_HIM_OF_THE_OTHER_TIMES_YOU_VE_DEFEATED_HIM(1, "Remind him of the other times you've defeated him.", REQUIEM_FOR_A_DRAGON),
        I_HAVE_A_SOUL_AND_YOU_DON_T(1, "I have a soul and you don't.", REQUIEM_FOR_A_DRAGON),
        WHAT_DID_YOU_DO_TO_VORKATH_REQUIEM(1, "What did you do to Vorkath?", REQUIEM_FOR_A_DRAGON),
        I_VE_HEARD_ENOUGH_REQUIEM(1, "I've heard enough.", REQUIEM_FOR_A_DRAGON),
        JUST_TELL_ME_WHAT_THE_LEAD_IS(1, "Just tell me what the lead is.", REQUIEM_FOR_A_DRAGON),
        CONTINUE_REQUIEM_FOR_A_DRAGON(1, "Continue Requiem for a Dragon.", REQUIEM_FOR_A_DRAGON),
        LETS_GET_STRAIGHT_TO_THE_POINT(1, "Let's get straight to the point.", REQUIEM_FOR_A_DRAGON),
        DEDUCE_THE_GLYPH_ORDER(1, "Deduce the glyph order.", REQUIEM_FOR_A_DRAGON),
        CHANGE_THE_GLYPHS(1, "Change the glyphs.", REQUIEM_FOR_A_DRAGON),
        ELEMENTAL(1, "Elemental", REQUIEM_FOR_A_DRAGON),
        COMMUNE(1, "Commune", REQUIEM_FOR_A_DRAGON),
        TALK_ABOUT_REQUIEM_FOR_A_DRAGON_1(1, "Talk about 'Requiem for a Dragon'", REQUIEM_FOR_A_DRAGON),
        CHANGE_THE_GLYPHS_REQUIEM(1, "Change the glyphs.", REQUIEM_FOR_A_DRAGON);



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


    public static enum QuestInstruction
    {

        ////Christmas Reunion
        CHRISTMAS_REUNION_INSTRUCTION("Have Christmas Village Teleport on Action Bar, Talk to Hunter NPC at the enterance of Citharede manual if you haven't done with before", CHRISTMAS_REUNION),

        //Its Snow Bother
        ITS_SNOW_BOTHER_INSTRUCTION("Have Christmas Village Teleport on Action Bar.  Might need to Select 5 NPCs to deliver presents to. - NPC are Sir Amik Varze, Bob, Brugsen Bursen, Doric and Reldo", ITS_SNOW_BOTHER),

        //Dead and Buried
        DEAD_AND_BURIED_INSTRUCTION("Have Items in banks and armour equipped and some food.Puzzel required manual intervention.", DEAD_AND_BURIED),

        //Ancient Awakening
        ANCIENT_AWAKENING_INSTRUCTION("Have Armour and weapon equipped. Inventory fill with food before going to Ungael Site, 12 Waves are required manual intervention.", ANCIENT_AWAKENING),

        //Battle of Forinthry
        BATTLE_OF_FORINTHRY_INSTRUCTION("Have Armour and weapon equipped. Inventory fill with food before going to fight with Vorkath", BATTLE_OF_FORINTHRY),

        //Requiem for a Dragon
        REQUIEM_FOR_A_DRAGON_INSTRUCTION("Talking to Archivist and Zemouregal is required manual intervention. Resotring becon chat option with tree of balance is required manual intervention. Ritual required manual intervention.", REQUIEM_FOR_A_DRAGON);


        private final String text;
        private final DebugScript.Quest quest;

        QuestInstruction(String text, DebugScript.Quest quest) {
            this.text = text;
            this.quest = quest;
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
