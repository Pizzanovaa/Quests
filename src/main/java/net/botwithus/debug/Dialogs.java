package net.botwithus.debug;


import net.botwithus.api.game.hud.Dialog;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;


import java.util.Arrays;
import java.util.List;

import static net.botwithus.debug.DebugScript.Quest.*;
import static net.botwithus.debug.DebugScript.currentQuest;
import static net.botwithus.rs3.script.Execution.delay;

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
            String dialogText = Dialog.getText();
            ScriptConsole.println(dialogText);
            if (dialogText.contains("Don't take my word for it. Ask Bianca.")) {
                Murderontheborder.talkedtorodney = true;
            }

            if(dialogText.contains("In my former incarnation I was Filliman Tarlock")){
                naturespirit.planDialog = true;
            }

    /*        //Idek if u need to go threw this guys dialog but i did it anyway lol{
            if(dialogText.contains("This is our town...we's live here,")){
                InAidoftheMyreque.florinOption1 = true;
            }
            if(dialogText.contains("Well met! My name's Florion.")){
                InAidoftheMyreque.florinOption2 = true;
            }
            if(dialogText.contains("You's probably one of them stinking vampyres")){
                InAidoftheMyreque.florinOption3 = true;
            }
            if(dialogText.contains("You's probably want's to eat it all")){
                InAidoftheMyreque.florinOption4 = true;
            }*/
            //}
            if(dialogText.contains("Fix the floopin bank would ya!")){
                InAidoftheMyreque.cornelioustalked = true;
            }

            if(dialogText.contains("After you've informed Radigad and Polmafi")){
                InAidoftheMyreque.talkedtovelia = true;
            }

            if(dialogText.contains("Nothing relevant. It is not pertinent to our problems.")){
                InAidoftheMyreque.option2derzel = true;
            }

            if(dialogText.contains("Yeah, maybe you should tell the King what a great job you")){
                PriestInPeril.talkedtoMonk = true;
            }

            boolean matched = Arrays.stream(AutoCloseDialogs.values())
                    .anyMatch(topic -> dialogText.contains(topic.getPhrase()));

            if (matched) { //Check if the dialog should be closed instead of continued. checks 'AutoCloseDialogs' enum
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
        } else if (Interfaces.isOpen(720) && currentQuest != NEW_FOUNDATION && currentQuest != ARCH_TUTORIAL && currentQuest != MURDER_ON_THE_BORDER) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 47185921);
        } else if (Interfaces.isOpen(720) && currentQuest == MURDER_ON_THE_BORDER) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 47185949);
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
        else if (continueHandler()) {
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

    // Method to determine if the dialogue should be skipped, used for chats that require you to go threw various options but the previous still exist.
    private static boolean shouldSkipDialogue(Dialogue dialogue) {

        String textTitle = Dialog.getTitle();
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
            case VIAL:
                return hasItem("Poison detection potion");
            case HOLLYHOCK:
                return hasItem("Hollyhock");
            case DUCHLINK, TALK_ABOUT_THE_ASSASSIN:
                return VarManager.getVarbitValue(52689) == 155;
            case TALKRODNEY:
                return VarManager.getVarbitValue(52699) == 191;
            case SO_WHAT_S_YOUR_PLAN:
                return naturespirit.planDialog;
            case IVAN_STORM:
                return !textTitle.contains("Who is the youngest member of the Myreque?");
            case SANIPILIU:
                return !textTitle.contains("Name the only female member of the Myreque.");
            case VELIAF:
                return !textTitle.contains("Who is the leader of the Myreque?");
            case DRAKAN:
                return !textTitle.contains("What family is rumoured to rule Morytania?");
            case POLMAFI:
                return !textTitle.contains("Which member of the Myreque was originally a scholar?") && !textTitle.contains("Who was previously a scholar?");
            case HIDDEN:
                return !textTitle.contains("What does Myreque mean?");
            case CYREG:
                return !textTitle.contains("What is the boatman's name?");
            case WHAT_SHOULD_I_DO_NOW:
                return VarManager.getVarbitValue(11498) != 160 && VarManager.getVarbitValue(11498) != 170 && VarManager.getVarbitValue(11498) != 190 && VarManager.getVarbitValue(11498) != 200;
            case I_FOUND_OUT_SOME_THINGS_ABOUT_EFARITAY:
                return InAidoftheMyreque.option2derzel;
            default:
                return false; // Default case: do not skip
        }
    }


    public static boolean hasItem(String item) { //Murder on the border quest.
        Item potion = InventoryItemQuery.newQuery(93).name(item, String::contains).results().first();
        return potion != null;
    }

    public static boolean continueHandler() { // Continue prompt, for new characters.
        Component thing = ComponentQuery.newQuery(955).componentIndex(16).subComponentIndex(14).results().first();
        if (thing != null && !thing.getText().equals("") && !thing.getText().isBlank()) {
            return true;
        }
        return false;
    }


    public static enum Dialogue {

        //region Death Plateau
        DO_YOU_HAVE_ANY_QUESTS_FOR_ME(1, "Do you have any quests for me?", DEATH_PLATEAU),
        Ive_been_sent_to_look_for_a_route_to_Death_Plateau_Can_you_help(1, "I've been sent to look for a route to Death Plateau. Can you help?", DEATH_PLATEAU),
        Can_you_put_some_fresh_spikes_on_these_climbing_boots_for_me(1, "Can you put some fresh spikes on these climbing boots for me?", DEATH_PLATEAU),
        Prepare_to_die_troll(3, "Prepare to die, troll!", DEATH_PLATEAU),
        //endregion
        //region Druidic Ritual
        Talk_about_Druidic_Ritual(1, "Talk about Druidic Ritual.", DRUIDIC_RITUAL),
        What_do_you_need_help_with(1, "What do you need help with?", DRUIDIC_RITUAL),
        Im_pretty_sure_Ive_heard_that_before(1, "I'm pretty sure I've heard that before.", DRUIDIC_RITUAL),
        Ive_been_sent_to_help_make_an_ointment_of_imbalance_for_Kaqemeex(1, "I've been sent to help make an ointment of imbalance for Kaqemeex.", DRUIDIC_RITUAL),
        Ok_Ill_do_that_then(1, "Ok, I'll do that then.", DRUIDIC_RITUAL),
        //endregion
        //region Let Them Eat Pie (UNFINISHED)
        //Id_best_get_on_with_it_then(1, "I'd best get on with it then.", LET_THEM_EAT_PIE),
        //endregion
        //region Wolf Whistle
        Do_you_have_a_quest_for_me(2, "Do you have a quest for me?", WOLF_WHISTLE),
        Look_Ill_just_go_and_take_a_look_myself(1, "Look, I'll just go and take a look myself...", WOLF_WHISTLE),
        Bowloftrix_has_been_kidnapped_by_trolls(2, "Bowloftrix has been kidnapped by trolls!", WOLF_WHISTLE),
        I_think_I_had_better_go(5, "I think I had better go.", WOLF_WHISTLE),
        Ask_about_the_white_hare_meat(1, "Ask about the white hare meat.", WOLF_WHISTLE),
        HOW_MUCH_IS_THAT_PUPPY(2, "How much is that puppy in the window?.", WOLF_WHISTLE),
        I_need_to_ask_you_something_about_the_quest(2, "I need to ask you something about the quest...", WOLF_WHISTLE),
        I_made_the_giant_wolpertinger_pouch(1, "I made the giant wolpertinger pouch!", WOLF_WHISTLE),
        //endregion
        //region Violet is Blue
        READY_FOR_ADVENTURE1(1, "To go on an adventure.", VIOLET_IS_BLUE),
        KNOCK(1, "Knock again.", VIOLET_IS_BLUE),
        READY_FOR_ADVENTURE(1, "Go on an adventure!", VIOLET_IS_BLUE),
        BREAK_THE_DOOR_DOWN(3, "Break the door down.", VIOLET_IS_BLUE),
        YOUVE_CAPTURED_A_HUMAN_GIRL(1, "YOU'VE CAPTURED A HUMAN GIRL!", VIOLET_IS_BLUE),
        USEFUL(1, "Do you have anything useful?", VIOLET_IS_BLUE),
        HEAD(1, "Happy face.", VIOLET_IS_BLUE),
        YES(1, "Yes!", VIOLET_IS_BLUE),
        VIOLET_YES(1, "Yes.", VIOLET_IS_BLUE),
        //endregion
        //region VIOLET IS BLUE TOO
        AUDIO(1, "No.", VIOLET_IS_BLUE_TOO),
        VIOLETISBLUETOO(1, "Violet is Blue Too.", VIOLET_IS_BLUE_TOO),
        IREMEBERHIM(1, "I remember him.", VIOLET_IS_BLUE_TOO),
        What_should_we_do(2, "What should we do?", VIOLET_IS_BLUE_TOO),
        PEEK(1, "Peek.", VIOLET_IS_BLUE_TOO),
        At_least_you_got_out_before_Christmas(3, "At least you got out before Christmas.", VIOLET_IS_BLUE_TOO),
        How_can_we_help_out(1, "How can we help out?", VIOLET_IS_BLUE_TOO),
        //endregion
        //region BLOOD PACT
        HANDLE(4, "I can handle this.", BLOOD_PACT),
        YES_NOW_DIE(3, "Yes. Now die!", BLOOD_PACT),
        TIME_DIE(2, "Time for you to die!", BLOOD_PACT),
        GOANYWAY(1, "Go downstairs anyway.", BLOOD_PACT),
        NIGHTMARE(4, "I'm your worst nightmare, Zamorakian scum!", BLOOD_PACT),
        WHAT_HELP_DO_YOU_NEED(1, "What help do you need?", BLOOD_PACT),
        ILL_HELP_YOU(1, "I'll help you.", BLOOD_PACT),
        YES_RESCUE_ILONA(1, "Yes, rescue Ilona.", BLOOD_PACT),
        IM_READY_FOR_MY_REWARD(1, "I'm ready for my reward.", BLOOD_PACT),
        //endregion
        //region RESTLESS GHOST
        IM_LOOKING_FOR_A_QUEST(3, "I'm looking for a quest!", RESTLESS_GHOST),
        FATHER_AERECK_SENT_ME(2, "Father Aereck sent me to talk to you.", RESTLESS_GHOST),
        A_GHOST_IS_HAUNTING(1, "A ghost is haunting his graveyard.", RESTLESS_GHOST),
        YEP_NOW_TELL_ME(1, "Yep. Now, tell me what the problem is.", RESTLESS_GHOST),
        SKULL(1, "Put the skull in the coffin.", RESTLESS_GHOST),
        //endregion
        //region Cook's Assistant
        IM_AFTER_SOME_TOP_QUALITY_MILK(1, "I'm after some top-quality milk.", COOKS_ASSISTANT),
        IM_LOOKING_FOR_EXTRA_FINE_FLOWER(1, "I'm looking for extra fine flour.", COOKS_ASSISTANT),
        DO_YOU_HAVE_ANY_OTHER_QUESTS(1, "Do you have any other quests for me?", COOKS_ASSISTANT),
        ANGRY_IT_MAKES_ME_ANGRY(1, "Angry! It makes me angry!", COOKS_ASSISTANT),
        WHAT_SEEMS_TO_BE_THE_PROBLEM(1, "What seems to be the problem?", COOKS_ASSISTANT),
        IM_FINE_THANKS(2, "I'm fine, thanks.", COOKS_ASSISTANT),
        WHAT_WRONG(1, "What's wrong?", COOKS_ASSISTANT),
        I_GET_ON_IT(1, "I'll get right on it.", COOKS_ASSISTANT),
        //endregion
        //region Myths of the white lands
        YOU_MENTIONED_A_QUEST(2, "You mentioned a quest?", MYTHS_OF_THE_WHITE_LANDS),
        HOW_DO_I_GET_TO_THE_LAND_OF_SNOW(1, "How do I get to the Land of Snow?", MYTHS_OF_THE_WHITE_LANDS),
        ACTUALLY_NEVER_MIND(3, "Actually, never mind.", MYTHS_OF_THE_WHITE_LANDS),
        IM_A_FRIEND_OF_EXPLORER_JACK_AND_NEED_TELEPORTING(2, "I'm a friend of Explorer Jack and need teleporting.", MYTHS_OF_THE_WHITE_LANDS),
        ABOUT_MYTHS_OF_THE_WHITE_LANDS(2, "About Myths of the White Lands...", MYTHS_OF_THE_WHITE_LANDS),
        CARRY_ON_WITH_THE_IMPS_PRANK(2, "Carry on with the imps' prank.", MYTHS_OF_THE_WHITE_LANDS),
        //endregion
        //region Ernest the chicken (UNTESTED)

        //endregion
        //region Swept Away (UNFINISHED)

        //endregion
        //region LOST CITY
        WHY_CAMPED_OUT_HERE(1, "Why are you camped out here?", TEST_DONTSELECT),
        WHOS_ZANARIS(1, "Who's Zanaris?", TEST_DONTSELECT),
        HIDDEN_FIND_IT(1, "If it's hidden, how are you planning to find it?", TEST_DONTSELECT),
        DONT_KNOW_EITHER(2, "Looks like you don't know either.", TEST_DONTSELECT),
        IVE_BEEN_IN_THAT_SHED(2, "I've been in that shed and I didn't see a city.", TEST_DONTSELECT),
        YES_PLEASE_TELEPORT_USEFUL(1, "Yes, please, a teleport would be useful.", TEST_DONTSELECT),
        WELL_THAT_IS_A_RISK(2, "Well, that is a risk I will have to take.", TEST_DONTSELECT),
        //endregion
        //region WHAT LIES BELOW
        HELLO_THERE(2, "Hello there!", WHAT_LIES_BELOW),
        SHALL_I_GET_THEM_BACK(3, "Shall I get them back for you?", WHAT_LIES_BELOW),
        BRING_IT_ON(1, "Bring it on!", WHAT_LIES_BELOW),
        GO_ON_THEN(1, "Go on, then!", WHAT_LIES_BELOW),
        YES_I_HAVE_A_LETTER_FOR_YOU(1, "Yes! I have a letter for you.", WHAT_LIES_BELOW),
        RAT_BURGISS_SENT_ME(4, "Rat Burgiss sent me.", WHAT_LIES_BELOW),
        I_HAVE_THE_THINGS_YOU_WANTED(1, "I have the things you wanted!", WHAT_LIES_BELOW),
        //endregion
        //region The Knight's Sword
        CLOSE(6, "redberry pie. They REALLY like redberry pie.", THE_KNIGHT_SWORD),
        CHAT(1, "Chat", THE_KNIGHT_SWORD),
        ASK_LIFE(1, "And how is life as a squire?", THE_KNIGHT_SWORD),
        OFFER_SWORD(2, "I can make a new sword if you like...", THE_KNIGHT_SWORD),
        ASK_DWARVES(1, "So would these dwarves make another one?", THE_KNIGHT_SWORD),
        SOMETHING_ELSE(2, "Something else.", THE_KNIGHT_SWORD),
        OFFER_PIE(2, "Would you like some redberry pie?", THE_KNIGHT_SWORD),
        IMCANDO_DWARVES(3, "What do you know about the Imcando dwarves?", THE_KNIGHT_SWORD),
        //endregion
        //region Shield of Arrav
        SEARCH_QUEST(1, "I'm in search of a quest.", SHIELD_OF_ARRAV),
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
        //endregion
        //region  Stolen Hearts
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
        //endregion
        //region  Family Crest
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
        //endregion
        //region  THE GOLEM
        OPEN_PORTAL(1, "How do I open the portal?", THE_GOLEM),
        STATUETTE_UZER(3, "I'm looking for a statuette recovered from the city of Uzer.", THE_GOLEM),
        OPEN_ELDER_DEMON_PORTAL(1, "I want to open a portal to the lair of an elder-demon.", THE_GOLEM),
        LETTER_IN_DESERT(3, "I found a letter in the desert with your name on.", THE_GOLEM),
        //endregion
        //region Rune Mythos
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
        //endregion
        //region Vessel Harbinger
        TALK_ABOUT_VESSEL_HARINGER(1, "Talk about 'Vessel of the Harbinger'.", VESSEL_HARINGER),
        //endregion
        //region Tomes of Warlock
        RASIAL_VESSEL_ORGAN(1, "Death, I've been to Rasial's citadel. I think his vessel is a giant organ.", TOMES_OF_WARLOCK),
        YES_TOMES(1, "Yes.", TOMES_OF_WARLOCK),
        ICE_TOMES(1, "Ice.", TOMES_OF_WARLOCK),
        SMOKE_TOMES(2, "Smoke.", TOMES_OF_WARLOCK),
        SHADOW_TOMES(3, "Shadow.", TOMES_OF_WARLOCK),
        BLOOD_TOMES(4, "Blood.", TOMES_OF_WARLOCK),
        HAND_IN_BOOK(1, "Yes", TOMES_OF_WARLOCK),
        NO_THANKS(2, "No thanks.", TOMES_OF_WARLOCK),
        GOODBYE(1, "Goodbye.", TOMES_OF_WARLOCK),
        //endregion
        //region  Daughter of Chaos
        I_AM_READY(1, "I'm ready.", DAUGHTER_OF_CHAOS),
        ILL_SEE_IT_DONE(2, "I'll see it done.", DAUGHTER_OF_CHAOS),
        SHOULD_MOVE_ON(4, "We should move on.", DAUGHTER_OF_CHAOS),
        AGREE_WITH_ADRASTEIA(1, "Agree with Adrasteia.", DAUGHTER_OF_CHAOS),
        REPORT_BACK_TO_ADRASTEIA(2, "I'll report back to Adrasteia.", DAUGHTER_OF_CHAOS),
        LOWER_THE_DIFFICULTY(1, "Lower the difficulty.", DAUGHTER_OF_CHAOS),
        ADRASTEIA_SENT_ME(1, "Adrasteia sent me.", DAUGHTER_OF_CHAOS),
        RESPOND_DIPLOMATICALLY(1, "Respond diplomatically.", DAUGHTER_OF_CHAOS),
        //endregion
        //region Diamond in the Rough
        LET_S_KEEP_GOING(4, "Let's keep going.", DIAMOND_ROUGH),
        CALL_FOR_HELP(4, "Call for help.", DIAMOND_ROUGH),
        REACH_FOR_THE_CACTUS(1, "Reach for the cactus.", DIAMOND_ROUGH),
        GRASP_FOR_THE_ROCK(2, "Grasp for the rock.", DIAMOND_ROUGH),
        DO_NOTHING(4, "Do nothing.", DIAMOND_ROUGH),
        //endregion
        //region Jack of Spades
        TALK_ABOUT_JACK_OF_SPADES(1, "Talk about Jack of Spades.", JACK_OF_SPADES),
        NO_MORE_QUESTIONS(5, "No more questions.", JACK_OF_SPADES),
        I_KNOW_ALL_I_NEED_LET_S_GO(1, "I know all I need - let's go!", JACK_OF_SPADES),
        YES_LET_S_GO(1, "Yes, let's go!", JACK_OF_SPADES),
        I_D_LIKE_TO_ASK_FOR_DIRECTIONS(1, "I'd like to ask for directions.", JACK_OF_SPADES),
        CAN_YOU_TELL_ME_WHERE_THE_PORTS_DISTRICT_IS(1, "Can you tell me where the Ports district is?", JACK_OF_SPADES),
        I_VE_HEARD_ENOUGH(4, "I've heard enough.", JACK_OF_SPADES),
        WHY_STEAL_FROM_PEOPLE(3, "Why steal from those people?", JACK_OF_SPADES),
        //endregion
        //region New Foundation
        YES_NEW_FOUNDATION(2, "Yes", NEW_FOUNDATION),
        LEAVE(1, "Leave the starting area.", NEW_FOUNDATION),
        YES_RITUAL(2, "Yes.", NEW_FOUNDATION),
        SIGN_ME_UP(2, "Sign me up!", NEW_FOUNDATION),
        DUKES_QUEST(1, "Duke.", NEW_FOUNDATION),
        //endregion
        //region Kili's row I
        TALK_ABOUT_UPGRADE1(1, "Talk about active equipment upgrade task.", KILI_KNOWLEDGE_I),
        CURRENT_TASK1(1, "Ask about your current task.", KILI_KNOWLEDGE_I),
        //endregion
        //region Kili's row II
        TALK_ABOUT_UPGRADE2(1, "Talk about active equipment upgrade task.", KILI_KNOWLEDGE_II),
        CURRENT_TASK2(1, "Ask about your current task.", KILI_KNOWLEDGE_II),
        //endregion
        //region Kili's row III
        TALK_ABOUT_UPGRADE3(1, "Talk about active equipment upgrade task.", KILI_KNOWLEDGE_III),
        CURRENT_TASK3(1, "Ask about your current task.", KILI_KNOWLEDGE_III),
        //endregion
        //region Kili's row IV
        TALK_ABOUT_UPGRADE4(1, "Talk about active equipment upgrade task.", KILI_KNOWLEDGE_IV),
        CURRENT_TASK4(1, "Ask about your current task.", KILI_KNOWLEDGE_IV),
        //endregion
        //region Kili's row V
        TALK_ABOUT_UPGRADE5(1, "Talk about active equipment upgrade task.", KILI_KNOWLEDGE_V),
        CURRENT_TASK5(1, "Ask about your current task.", KILI_KNOWLEDGE_V),
        //endregion
        //region Enter the Abyss
        YES_ENTER_THE_ABYSS(1, "Yes", ENTER_THE_ABYSS),
        WHERE_RUNES(2, "Where do you get your runes from?", ENTER_THE_ABYSS),
        //endregion
        //region Whats Mine Is Yours
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
        //endregion
        //region Gertrude Cat
        WHAT_WILL_MAKE_YOU_TELL_ME(2, "What will make you tell me?", GERTRUDE_CAT),
        OKAY_THEN_ILL_PAY(2, "Okay then, I'll pay.", GERTRUDE_CAT),
        //endregion
        //region Anachronia Tutorial
        YES_ANACHRONIA_TUTORIAL(1, "Yes.", ANACHRONIA_TUT),
        //endregion
        //region Mogre Lore Activity
        WHO_ARE_THEY(2, "Who are (Dramatic pause) THEY?", MOGRE_ACTIVITY),
        THROW_THE_WATER(1, "Throw the water!", MOGRE_ACTIVITY),
        //endregion
        //region Christmas Reunion
        PLAIN_BROWN_OUTFITS_DONT_EXACTLY_SCREAM_CHRISTMAS(1, "The plain brown outfits don't exactly scream 'Christmas'.", CHRISTMAS_REUNION),
        TALK_ABOUT_A_CHRISTMAS_REUNION(1, "Talk about 'A Christmas Reunion'.", CHRISTMAS_REUNION),
        COULD_I_PAY_YOU_TO_MAKE_THEM_FOR_ME(1, "Could I pay you to make them for me?", CHRISTMAS_REUNION),
        MAKE_CHRISTMAS_DYE(1, "Make Christmas dye.", CHRISTMAS_REUNION),
        RED_AND_BLACK(1, "Red and black.", CHRISTMAS_REUNION),
        THE_ABBEY(1, "The abbey?", CHRISTMAS_REUNION),
        //endregion
        //region Its Snow Bother
        ITS_SNOW_BOTER(1, "Talk about 'It's Snow Bother'.", ITS_SNOW_BOTHER),
        TALK_TO_RELDO(2, "[Talk to Reldo.]", ITS_SNOW_BOTHER),
        //endregion
        //region Dead and Buried
        TALK_ABOUT_DEAD_AND_BURIED(1, "Talk about 'Dead and Buried'.", DEAD_AND_BURIED),
        CONCLUDE_INTERVIEW(4, "(Conclude interview.)", DEAD_AND_BURIED),
        YES_I_VE_ASKED_ENOUGH_QUESTIONS(1, "Yes. I've asked enough questions.", DEAD_AND_BURIED),
        YES_I_KNOW_THAT(1, "Yes, I know that.", DEAD_AND_BURIED),
        MUST_WE(1, "Must we?", DEAD_AND_BURIED),
        LEARDERSHIP_SKILLS(1, "Leadership skills.", DEAD_AND_BURIED),
        YOUR_MAJESTY(1, "Your majesty?", DEAD_AND_BURIED),
        WHY_DID_YOU_MARRY_THE_KING(1, "Why did you marry the king?", DEAD_AND_BURIED),
        COULDNT_YOU_HAVE_BEEN_A_WARRIIOR_QUEEN(1, "Couldn't you have been a warrior queen?", DEAD_AND_BURIED),
        SO_YOU_STARTED_WEARING_A_DISGUISE(1, "So you started wearing a disguise?", DEAD_AND_BURIED),
        YES_DEAD_AND_BURIED(1, "Yes.", DEAD_AND_BURIED),
        ENTER_DREAM(1, "Enter dream.", DEAD_AND_BURIED),
        TALK_ABOUT_QUESTS(2, "Talk about quests.", DEAD_AND_BURIED),
        //endregion
        //region Ancient Awakening
        TALK_ABOUT_QUEST(2, "Talk about quests.", ANCIENT_AWAKENING),
        TALK_ABOUT_ANCIENT_AWAKENING(2, "Talk about 'Ancient Awakening'.", ANCIENT_AWAKENING),
        I_DONT_NEED_TO_KNOW_YOUR_FAMILY_HISTORY(1, "I don't need to know your family history, Bill.", ANCIENT_AWAKENING),
        LETS_SKIP_THE_SMALL_TALK(1, "Let's skip the small talk.", ANCIENT_AWAKENING),
        I_NEED_YOU_TO_RETURN_TO_THE_FORT(1, "I need you to return to the fort.", ANCIENT_AWAKENING),
        YES_ANCIENT_AWAKENING(1, "Yes.", ANCIENT_AWAKENING),
        CAN_YOU_ACTIVATE_THIS_CONDUIT(1, "Can you activate the conduit?", ANCIENT_AWAKENING),
        TELL_ME_ABOUT_TOMB(1, "Tell me about this tomb.", ANCIENT_AWAKENING),
        TELL_ME_ABOUT_VORKATH(1, "Tell me about Vorkath.", ANCIENT_AWAKENING),
        //WHO_ARE_YOU(1, "Who are you?", ANCIENT_AWAKENING),
        WHAT_IS_THIS_PLACE(1, "What is this place?", ANCIENT_AWAKENING),
        TELL_ME_ABOUT_THIS_TOMB(1, "Tell me about this tomb.", ANCIENT_AWAKENING),
        NONSENSE_ASTER_IS_DOING_FINE(1, "Nonsense! Aster is doing fine.", ANCIENT_AWAKENING),
        What_ARE_YOU_DOING_HERE(1, "What are you doing here?", ANCIENT_AWAKENING),
        IS_IT_SAFE_FOR_CIVILIANS_TO_BE_HERE(1, "Is it safe for civilians to be here?", ANCIENT_AWAKENING),
        I_WAS_JUST_CURIOUS(1, "I was just curious.", ANCIENT_AWAKENING),
        WE_NEED_TO_FOCUS(1, "We need to focus.", ANCIENT_AWAKENING),
        //endregion
        //region Battle of Forinthry
        TALK_ABOUT_BATTLE_OF_FORINTHRY(1, "Talk about 'Battle of Forinthry'.", BATTLE_OF_FORINTHRY),
        YES_I_AM_READY(1, "Yes, I am ready.", BATTLE_OF_FORINTHRY),
        OF_COURSE(1, "Of course.", BATTLE_OF_FORINTHRY),
        YES_BEGIN_THE_FEAST(1, "Yes, begin the feast.", BATTLE_OF_FORINTHRY),
//endregion
        //region Requiem for a Dragon

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
        CHANGE_THE_GLYPHS_REQUIEM(1, "Change the glyphs.", REQUIEM_FOR_A_DRAGON),
        //endregion
        //region Murder on the Border
        TALK_ABOUT_QUESTS_MURDER(1, "Talk about quests...", MURDER_ON_THE_BORDER),
        TALK_ABOUT_MURDER_ON_THE_BORDER(1, "Talk about 'Murder on the Border'.", MURDER_ON_THE_BORDER),
        YES_MURDER(1, "Yes.", MURDER_ON_THE_BORDER),
        I_LL_BEAR_THAT_IN_MIND(1, "I'll bear that in mind.", MURDER_ON_THE_BORDER),
        I_M_SURE_IT_WILL_BE_FINE(1, "I'm sure it will be fine.", MURDER_ON_THE_BORDER),
        VERY_PRECEPTIVE_YOUR_MAJESTY(1, "Very perceptive, your majesty.", MURDER_ON_THE_BORDER),
        WELCOME_TO_FORT_FORINTHRY(1, "Welcome to Fort Forinthry.", MURDER_ON_THE_BORDER),
        YOU_RE_THE_DUKE_OF_EDGEVILLE(1, "You're the duke of Edgeville.", MURDER_ON_THE_BORDER),
        WE_VE_GOT_THIS(1, "We've got this.", MURDER_ON_THE_BORDER),
        JUST_MAKE_HIM_THE_DISH_ASTER(1, "Just make him the dish, Aster.", MURDER_ON_THE_BORDER),
        I_M_DOING_THE_BEST_I_CAN(1, "I'm doing the best I can.", MURDER_ON_THE_BORDER),
        LOCK_DOWN_THE_FORT_NOW(1, "Lock down the fort.Now.", MURDER_ON_THE_BORDER),
        I_M_GLAD_I_HAVE_YOUR_SUPPORT(1, "I'm glad I have your support.", MURDER_ON_THE_BORDER),
        WE_NEED_TO_FIND_WHO_THIS_BELONGS_TO(1, "We need to find who this belongs to.", MURDER_ON_THE_BORDER),
        USE_IT_ON_THE_DUKES_MEAT_PATE(2, "Use it on the duke's meat pâté.", MURDER_ON_THE_BORDER),
        TALK_TO_KING_ROALD(1, "Talk to King Roald.", MURDER_ON_THE_BORDER),
        DID_YOU_NOTICE_ANYTHING_SUSPICIOUS(1, "Did you notice anything suspicious?", MURDER_ON_THE_BORDER),
        //THAT_S_ALL_FOR_NOW(3, "That's all for now.", MURDER_ON_THE_BORDER),
        THAT_S_UNACCEPTABLE(1, "That's unacceptable.", MURDER_ON_THE_BORDER),
        TALK_ABOUT_THE_ASSASSIN(1, "Talk about the assassin.", MURDER_ON_THE_BORDER),
        CONFIRM_ASTER_S_INNOCENCE(1, "Confirm Aster's innocence.", MURDER_ON_THE_BORDER),
        I_THINK_SHE_REALLY_IS_THIS_DENSE(1, "I think she really is this dense.", MURDER_ON_THE_BORDER),
        WHAT_S_YOUR_BEEF_WITH_KING_ROALD(1, "What's your beef with King Roald?", MURDER_ON_THE_BORDER),
        ASK_ABOUT_ELLAMARIA(1, "Ask about Ellamaria.", MURDER_ON_THE_BORDER),
        I_HEARD_YOU_WERE_ONCE_FRIENDS_WITH_BIANCA(1, "I heard you were once friends with Bianca.", MURDER_ON_THE_BORDER),
        YES_I_M_READY(1, "Yes, I'm ready.", MURDER_ON_THE_BORDER),
        DIANCA_DUNNET(5, "Bianca Dunnet", MURDER_ON_THE_BORDER),
        END_THE_BANQUET(1, "End the banquet.", MURDER_ON_THE_BORDER),
        I_M_SO_SORRY_TO_HEAR_THAT(1, "I'm so sorry to hear that.", MURDER_ON_THE_BORDER),
        SIMON_LEFT_IT_THERE_HIMSELF(1, "Simon left it there himself.", MURDER_ON_THE_BORDER),
        THEY_WANTED_HIS_WEALTH(1, "They wanted his wealth.", MURDER_ON_THE_BORDER),
        IT_WAS_AN_EXCUSE_TO_PROTECT_YOU(1, "It was an excuse to protect you.", MURDER_ON_THE_BORDER),
        TO_TAKE_REVENGE_ON_QUEEN_ELLAMARIA(1, "To take revenge on Queen Ellamaria.", MURDER_ON_THE_BORDER),
        SHE_DESERVES_IT_AFTER_BETRAYING_ME_LIKE_THIS(1, "She deserves it after betraying me like this.", MURDER_ON_THE_BORDER),
        ASK_HOW_ASTER_IS_FEELING(1, "Ask how Aster is feeling.", MURDER_ON_THE_BORDER),
        JUST_DON_T_NURDER_ANYONE(1, "Just don't murder anyone.", MURDER_ON_THE_BORDER),
        SIMONLINK(1, "Link a clue to Simon.", MURDER_ON_THE_BORDER),
        DUCHLINK(1, "Link a clue to Duchess Alba.", MURDER_ON_THE_BORDER),
        BIANCALINK(1, "Link a clue to Bianca.", MURDER_ON_THE_BORDER),
        IRISLINK(1, "Link a clue to Iris.", MURDER_ON_THE_BORDER),
        PRINCESSLINK(1, "Link a clue to Princess.", MURDER_ON_THE_BORDER),
        BIANCA(1, "That's unacceptable", MURDER_ON_THE_BORDER),
        VIAL(1, "Take a vial.", MURDER_ON_THE_BORDER),
        HOLLYHOCK(1, "Take hollyhock.", MURDER_ON_THE_BORDER),
        LEAVEVIAL(1, "Leave.", MURDER_ON_THE_BORDER),
        DECEND(1, "Bottom floor.", MURDER_ON_THE_BORDER),
        TALKDUCHNESS(1, "Talk to Duchess Alba.", MURDER_ON_THE_BORDER),
        TALKELLA(1, "Talk to Ellamaria.", MURDER_ON_THE_BORDER),
        TALKRODNEY(1, "Talk to Rodney.", MURDER_ON_THE_BORDER),
        LINKRODNEY(1, "Link a clue to Rodney.", MURDER_ON_THE_BORDER),
        FRIENDS(1, "I heard you were once friends with Bianca.", MURDER_ON_THE_BORDER),


        //LINK_A_CLUE_TO_SIMON(2, "Link a clue to Simon.", MURDER_ON_THE_BORDER),
        TALK_ABOUT_MURDER_ON_THE_BORDER_1(1, "Yes, let's start the feast.", MURDER_ON_THE_BORDER),
        MURDERDIALOG(1, "Say nothing.", MURDER_ON_THE_BORDER),

        //endregion
        //region Unwelcome Guests

        TALK_ABOUT_QUESTS_UNWELCOME(1, "Talk about quests.", UNWELCOME_GUESTS),
        TALK_ABOUT_UNWELCOME_GUESTS(2, "Talk about 'Unwelcome Guests'.", UNWELCOME_GUESTS),
        YES_UNWELCOME_GUESTS(1, "Yes.", UNWELCOME_GUESTS),
        YES_I_UNDERSTAND_THE_RESTRICTIONS(1, "Yes, I understand the restrictions.", UNWELCOME_GUESTS),
        TALK_ABOUT_UNWELCOME_GUESTS_1(1, "Talk about 'Unwelcome Guests'.", UNWELCOME_GUESTS),
        //endregion

        //region Imp Catcher
        CAN_I_HELP_YOU(1, "Can I help you?", IMP_CATCHER),
        HAVE_YOU_TRIED_A_BOWL_OF_HOT_WATER(1, "Have you tried a bowl of hot water?", IMP_CATCHER),
        WHY_WOULD_HE_BE_DISAPPOINTED(1, "Why would he be disappointed?", IMP_CATCHER),
        SOME_DAYS_YOU_LL_HAVE_SETBACKS_TOMORROW_IS_NEW_DAY(1, "Some days you'll have setbacks. Tomorrow is a new day.", IMP_CATCHER),
        TAKE_YOUR_TIME_NO_ONE_IS_RUSHING_YOU_TO_FEEL_BETTER(1, "Take your time. No one is rushing you to feel better.", IMP_CATCHER),
        I_VE_GOT_ALL_FOUR_BEADS(1, "I've got all four beads.", IMP_CATCHER),
        //endregion

        //region Rune Mysteries

    /*    WHAT_S_HAPPENING_HERE(1, "What's happening here?", RUNE_MYSTERIES),
        WHAT_CAN_I_DO_TO_HELP(2, "What can I do to help?", RUNE_MYSTERIES),
        WHAT_DO_YOU_WANT_ME_TO_DO(3, "What do you want me to do?", RUNE_MYSTERIES),
        I_LL_GET_ON_IT(2, "I'll get on it.", RUNE_MYSTERIES),
        HAVE_YOU_SEEN_ANYTHING_UNUSUAL_LATER(1, "Have you seen anything unusual lately?", RUNE_MYSTERIES),
        IN_THE_POWER_BEAM(2, "In the power beam?", RUNE_MYSTERIES),
        WHERE_DO_THESE_THINGS_COME_FROM(3, "Where do these things come from?", RUNE_MYSTERIES),
        ARIANE_SAYS_THE_TOWER_IS_IN_DANGER(3, "Ariane says the tower is in danger.", RUNE_MYSTERIES),
        WHAT_ARE_YOU_GOING_TO_DO(4, "What are you going to do?", RUNE_MYSTERIES),
        GOODBYE_1(5, "Goodbye.", RUNE_MYSTERIES),
        THANKS_1(2, "Thanks.", RUNE_MYSTERIES),
        I_VE_SPOKEN_TO_SOME_OF_THE_WIZARDS(2, "I've spoken to some of the wizards...", RUNE_MYSTERIES),
        WIZARD_TRAIBORN_SAID_SOMETHING_CAME_THROUGH_THE_LIBRARY_FLOOR(3, "Wizard Traiborn said something came through the library floor.", RUNE_MYSTERIES),
        THEN_LET_S_GET_DOWN_THERE_AND_INVESTIGATE(2, "Then let's get down there and investigate!", RUNE_MYSTERIES),
        I_LL_GET_RIGHT_ON_IT(2, "I'll get right on it.", RUNE_MYSTERIES),
        HOW_CAN_I_GET_INTO_THE_OLD_TOWER(2, "How can I get into the old tower?", RUNE_MYSTERIES),
        YOU_DO_KNOW_THOUGH(3, "You do know, though?", RUNE_MYSTERIES),
        YOU_MEAN_THE_RUMOUR_ABOUT_WATER_SURGE(3, "You mean the rumour about Water Surge?", RUNE_MYSTERIES),
        WHAT_DO_YOU_MEAN_THE_KEY_LIES_IN_UNDERSTANDING_WATER_SURGE(3, "What do you mean, the key lies in understanding Water Surge?", RUNE_MYSTERIES),
        ITS_SUCH_A_PITY_YOU_COULDNT_HELP_ME_MAYBE_I_LL_VISIT_THE_LIBRARY(2, "It's such a pity you couldn't help me, Maybe I'll visit the library.", RUNE_MYSTERIES),
        I_VE_GOT_THE_KEY_TO_THE_RUINS(2, "I've got the key to the ruins.", RUNE_MYSTERIES),
        WE_SHOULD_KEEP_OUR_MINDS_ON_THE_JOB(2, "We should keep our minds on the job.", RUNE_MYSTERIES),
        YES_IT_S_INSPIRING(1, "Yes, it's inspiring.", RUNE_MYSTERIES),
        OKAY_WHAT_SHOULD_WE_DO_NOW(2, "Okay, what should we do now?", RUNE_MYSTERIES),
        I_M_READY_TO_BE_TESTED(3, "I'm ready to be tested.", RUNE_MYSTERIES),
        I_M_MAKING_US_A_BRIDGE(1, "I'm making us a bridge.", RUNE_MYSTERIES),
        DO_YOU_HAVE_A_BETTER_IDEA(1, "Do you have a better idea?", RUNE_MYSTERIES),
        I_WISH_I_COULD_HAVE_SEEN_IT(1, "I wish I could have seen it.", RUNE_MYSTERIES),
        NEVER_MIND_THE_HISTORY_WHAT_DOES_THAT_MEAN_FOR_US(2, "Never mind the history, what does that mean for us?", RUNE_MYSTERIES),
        OKAY_SO_WHAT_DO_WE_DO(2, "Okay, so what do we do?", RUNE_MYSTERIES),
        OKAY_I_LL_DO_THAT_NOW(3, "Okay, I'll do that now.", RUNE_MYSTERIES),
        IS_THERE_A_REWARD(3, "Is there a reward?", RUNE_MYSTERIES),*/
        //endregion

        //region Icthlarin Little Helper
        WHY_ICTHLARIN(1, "Why? [Icthlarin's Little Helper]", ICTHLARIN_LITTLE_HELPER),
        TELL_ME_ABOUT_SOPHANEM(2, "Tell me about Sophanem. [Icthlarin's Little Helper]", ICTHLARIN_LITTLE_HELPER),
        NUMBER_9(3, "9.", ICTHLARIN_LITTLE_HELPER),
        I_NEED_HELP(1, "I need help.", ICTHLARIN_LITTLE_HELPER),
        OKAY_THAT_SOUNDS_FAIR(2, "Okay, that sounds fair.", ICTHLARIN_LITTLE_HELPER),
        TOTALLY_POSITIVE(3, "Totally positive.", ICTHLARIN_LITTLE_HELPER),
        SURE_NO_PROBLEM(4, "Sure, no problem.", ICTHLARIN_LITTLE_HELPER),
        ALRIGHT_I_LL_GET_THE_WOOD_FOR_YOU(2, "Alright, I'll get the wood for you.", ICTHLARIN_LITTLE_HELPER),
        SCRAPE_IT_OUT(1, "Scrape it out.", ICTHLARIN_LITTLE_HELPER),
        HIDE_THE_UNHOLY_SYMBOL(1, "Hide the unholy symbol in this sarcophagus.", ICTHLARIN_LITTLE_HELPER),
        YES_RETURN_TO_HIGH_PRIEST(1, "Yes, return to the high priest in Sophanem.", ICTHLARIN_LITTLE_HELPER),
        //endregion

        //region Nature Spirit
        TALK_ABOUT_SOMETHING_ELSE_1(2, "Talk about something else.", NATURE_SPIRIT),
        IS_THERE_ANYTHING_ELSE_INTERESTING_TO_DO_AROUND_HERE(2, "Is there anything else interesting to do around here?", NATURE_SPIRIT),
        YES_I_LL_GO_AND_LOOK_FOR_HIM(4, "Yes, I'll go and look for him.", NATURE_SPIRIT),
        YES_I_M_SURE(1, "Yes, I'm sure.", NATURE_SPIRIT),
        HOW_LONG_HAVE_YOU_BEEN_A_GHOST(2, "How long have you been a ghost?", NATURE_SPIRIT),
        SO_WHAT_S_YOUR_PLAN(2, "So, what's your plan?", NATURE_SPIRIT),
        HOW_CAN_I_HELP(4, "How can I help?", NATURE_SPIRIT),
        TALK_ABOUT_SOMETHING_ELSE_2(2, "Talk about something else.", NATURE_SPIRIT),
        YES_PLEASE_1(1, "Yes, please", NATURE_SPIRIT),
        I_THINK_I_VE_SOLVED_THE_PUZZLE(3, "I think I've solved the puzzle!", NATURE_SPIRIT),
        YES_1(1, "Yes.", NATURE_SPIRIT),
        OK_THANKS(4, "Ok, thanks.", NATURE_SPIRIT),
        //endregion

        //region In Search of the Myreque
        YESS(4, "Yes", IN_SEARCH_OF_THE_MYREQUE),
        MAYBE_I_COULD_HELP_YOU_OUT_HERE(4, "Perhaps I could help you out here.", IN_SEARCH_OF_THE_MYREQUE),
        WHY_DO_THEY_NEED_HELP(2, "Why do they need help? Are they in trouble?", IN_SEARCH_OF_THE_MYREQUE),
        YES_I_LL_DO_IT(4, "Yes, I'll do it!", IN_SEARCH_OF_THE_MYREQUE),
        WELL_I_GUESS_THEY_LL_JUST_DIE_WITHOUT_WEAPONS(2, "Well, I guess they'll just die without weapons.", IN_SEARCH_OF_THE_MYREQUE),
        RESOURCEFUL_ENOUGH_TO_GET_THEIR_OWN_STEEL_WEAPONS(2, "Resourceful enough to get their own steel weapons?", IN_SEARCH_OF_THE_MYREQUE),
        IF_YOU_DON_T_TELL_ME_THEIR_DEATHS_ARE_ON_YOUR_HEAD(3, "If you don't tell me, their deaths are on your head!", IN_SEARCH_OF_THE_MYREQUE),
        WHAT_KIND_OF_A_MAN_ARE_YOU_TO_SAY_THAT_YOU_DON_T_CARE(3, "What kind of a man are you to say that you don't care?", IN_SEARCH_OF_THE_MYREQUE),
        GIVE_WOODEN_PLANKS_TO_CYREG(1, "Give wooden planks to Cyreg.", IN_SEARCH_OF_THE_MYREQUE),
        I_VE_COME_TO_HELP_THE_MYREQUE_I_VE_BRINGED_WEAPONS(1, "I've come to help the Myreque. I've brought weapons.", IN_SEARCH_OF_THE_MYREQUE),
        HOW_DO_I_GET_OUT_OF_HERE(3, "How do I get out of here?", IN_SEARCH_OF_THE_MYREQUE),
        OK_THANKS_1(5, "Ok, thanks.", IN_SEARCH_OF_THE_MYREQUE),
        IVAN_STORM(3, "Ivan Strom.", IN_SEARCH_OF_THE_MYREQUE),
        SANIPILIU(3, "Sani Piliu.", IN_SEARCH_OF_THE_MYREQUE),
        VELIAF(1,"Veliaf Hurtz.",IN_SEARCH_OF_THE_MYREQUE),
        DRAKAN(1,"Drakan.",IN_SEARCH_OF_THE_MYREQUE),
        POLMAFI(1,"Polmafi Ferdygris.",IN_SEARCH_OF_THE_MYREQUE),
        HIDDEN(1,"Hidden in Myre.",IN_SEARCH_OF_THE_MYREQUE),
        CYREG(1,"Cyreg Paddlehorn.",IN_SEARCH_OF_THE_MYREQUE),
        OKGOODBYE(1,"Ok, goodbye.",IN_SEARCH_OF_THE_MYREQUE),
        
        
        
        
        //endregion

        //region In Aid of the Myreque

        ARE_THERE_ANY_OUT_OF_THE_WAY_PLACES_IN_HERE(4, "Are there any 'out of the way' places in here?", IN_AID_OF_THE_MYREQUE),
        WANT_TO_JOIN_YOUR_ORGANISATION(1, "I want to join your organisation.", IN_AID_OF_THE_MYREQUE),
        OK_TELL_ME_THIS_INFORMATION_YOU_HAVE_TO_IMPART(2, "Ok, tell me this information you have to impart.", IN_AID_OF_THE_MYREQUE),
        CAN_YOU_TELL_ME_ABOUT_THE_JOB(1, "Can you tell me about the job?", IN_AID_OF_THE_MYREQUE),
        OK_I_LL_DO_THE_JOB(1, "Ok, I'll do the job.", IN_AID_OF_THE_MYREQUE),
        OK_THANKS_2(5, "Ok, thanks!", IN_AID_OF_THE_MYREQUE),
        I_D_LIKE_TO_HELP_FIX_UP_THE_TOWN(3, "I'd like to help fix up the town.", IN_AID_OF_THE_MYREQUE),
        DO_YOU_FANCY_THE_JOB(3, "Do you fancy the job?", IN_AID_OF_THE_MYREQUE),
        WHAT_SHOULD_I_DO_NOW(4, "What should I do now?", IN_AID_OF_THE_MYREQUE),
        YES_2(1, "Yes", IN_AID_OF_THE_MYREQUE),
        I_FOUND_OUT_SOME_THINGS_ABOUT_EFARITAY(2, "I found out some things about Efaritay.", IN_AID_OF_THE_MYREQUE),
        IS_THERE_SOMETHING_I_MIGHT_GET_MORE_INFORMATION_ABOUT_IVANDIS(4, "Is there somewhere that I might get more information about Ivandis?", IN_AID_OF_THE_MYREQUE),
        THE_LIVES_OF_THOSE_PITIFUL_FEW_LEFT_IN_MORYTANIA_COULD_REST_ON_THIS(3, "The lives of those pitiful few left in Morytania could rest on this!", IN_AID_OF_THE_MYREQUE),
        VELAF_TOLD_ME_ABOUT_IVANDIS(1, "Veliaf told me about Ivandis.", IN_AID_OF_THE_MYREQUE),
        I_HAVE_BROUGHT_YOU_THE_ROD_OF_IVANDIS(1, "I have brought you the Rod of Ivandis!", IN_AID_OF_THE_MYREQUE),
        YES_I_VE_COME_TO_GIVE_THE_ROD_OF_IVANDIS_TO_YOU(1, "Yes, I've come to give the Rod of Ivandis to you!", IN_AID_OF_THE_MYREQUE),
        OKTHANKSAID(1,"Ok, thanks.",IN_AID_OF_THE_MYREQUE),
        //endregion

        //region Priest in peri
        TALKABOUT(1,"Talk about Priest in Peril.",PRIEST_IN_PERIL),
        GREETRONALD(1,"Greet the king.",PRIEST_IN_PERIL),
        KNOCK_DOOR(1,"Knock at the door.",PRIEST_IN_PERIL),
        CHECKDERZEL(1,"Roald sent me to check on Drezel.",PRIEST_IN_PERIL),
        YESPREIST(1,"Yes.",PRIEST_IN_PERIL);
        //endregion


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

    // Enum to hold dialogue phrases that should close out dialog windows.
    // used for when a quest npc will get stuck in dialog since we don't want to press any options that are available
    enum AutoCloseDialogs {
        REDBERRY_PIE("redberry pie. They REALLY like redberry pie."),
        BARAEK("If I were you I would talk to Baraek,"),
        BLACK_ARM("The ruthless and notorious Black Arm "),
        PET_SHOP_OWNER("Is there anything else i can help you with?"),
        KING_RONALD("I've told you everything I know."),
        DUTCHNESS("Let us leave the duchess alone,"),
        RODNEY("Don't take my word for it. Ask Bianca."),
        VELIAF("While you're there, you could see if that murderer"),
        FLORIN("Listen, if you do manage to find a way to get a place here,"),
        RAZVAN("Hmm, perhaps you'd consider fixing up the general store."),
        CORNELIUS("Fix the floopin bank would ya!"),
        AUREL("Please can you fix the bank booth first");


        private final String phrase;

        AutoCloseDialogs(String phrase) {
            this.phrase = phrase;
        }

        public String getPhrase() {
            return phrase;
        }
    }

    public static enum QuestInstruction {

        ////Christmas Reunion
        CHRISTMAS_REUNION_INSTRUCTION("Have Christmas Village Teleport on Action Bar, Talk to Hunter NPC at the enterance of Citharede manual if you haven't done with before", CHRISTMAS_REUNION),

        //Its Snow Bother
        ITS_SNOW_BOTHER_INSTRUCTION("Have Christmas Village Teleport on Action Bar.  Might need to Select 5 NPCs to deliver presents to. - NPC are Sir Amik Varze, Bob, Brugsen Bursen, Doric and Reldo", ITS_SNOW_BOTHER),

        //Dead and Buried
        DEAD_AND_BURIED_INSTRUCTION("Have Items in banks and armour equipped and some food.Puzzel required manual intervention.", DEAD_AND_BURIED),

        //Ancient Awakening
        ANCIENT_AWAKENING_INSTRUCTION("Have Armour and weapon equipped. Inventory fill with food before going to Ungael Site, 12 Waves are required manual intervention.", ANCIENT_AWAKENING),

        //Battle of Forinthry
        BATTLE_OF_FORINTHRY_INSTRUCTION("Requires Grove Gabin Tier 1, Botanist's Workbench Tier 1. Have Armour and weapon equipped. Inventory fill with food before going to fight with Vorkath", BATTLE_OF_FORINTHRY),

        //Requiem for a Dragon
        REQUIEM_FOR_A_DRAGON_INSTRUCTION("Talking to Archivist and Zemouregal is required manual intervention. Resotring becon chat option with tree of balance is required manual intervention. Ritual required manual intervention.", REQUIEM_FOR_A_DRAGON),

        //Murder on the Border
        MURDER_ON_THE_BORDER_INSTRUCTION("Requires Town Hall Tier 1, Command Centre Tier 1, Chapel Tier 1.  Talk to Rodney during second half of the quest", MURDER_ON_THE_BORDER),

        //Imp Catcher
        IMP_CATCHER_INSTRUCTION("Race the imp at Air Ruins manual", IMP_CATCHER),

        Icthlarin_INSTRUCTION("Pramid walk is required manual intervention", ICTHLARIN_LITTLE_HELPER),


        //In Search of the Myreque
        IN_SEARCH_OF_THE_MYREQUE_INSTRUCTION("Make sure to overfill your druid pouch i recommened 40+ just in case. lots of ghasts hit you via nav", IN_SEARCH_OF_THE_MYREQUE),

        //In Aid of the Myreque
        IN_AID_OF_THE_MYREQUE_INSTRUCTION("Start quest with 5 food and 5 buckets in inventory. Buy STEEL Nails. Keep all required Items in Bank. Make sure you are in melee gear, or no gear so you have accuracy with the sickle. Requires manual input of enchanting rod with lvl 1 enchant", IN_AID_OF_THE_MYREQUE),
        PRIEST_IN_PERIL("Have 25 pure essence and a bucket in your inventory, along with a weapon wielded. Will have to hand in 50 rune essence manually if F2P", DebugScript.Quest.PRIEST_IN_PERIL);
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
