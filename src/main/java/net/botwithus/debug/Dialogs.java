package net.botwithus.debug;


import net.botwithus.api.game.hud.Dialog;
import net.botwithus.rs3.game.cs2.ScriptBuilder;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;

import java.util.List;

import static net.botwithus.debug.DebugScript.currentQuest;
import static net.botwithus.rs3.game.cs2.layouts.Layout.INT;

public class Dialogs {

    public static final ScriptBuilder click1188 = ScriptBuilder.of(5593).args(INT, INT); // 1188 interface  (13,x) CLOSE DIALOG
    public static final ScriptBuilder click1193 = ScriptBuilder.of(5583).args(INT, INT); // 1188 interface  (13,x) CLOSE DIALOG


    public static void dialog1188() {
        int number = getDialogueNumber();
        int option = 1;
        switch (number) {
            case 1 -> option = 16;
            case 2 -> option = 17;
            case 3 -> option = 18;
            case 4 -> option = 19;
            case 5 -> option = 20;
            default -> option = -1; // Optional: handle cases not covered above
        }
        if (option != -1) {
            click1188.invokeExact(option, -1);
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
                click1188.invokeExact(13, -1); // closes chat without the X option :D
            } else if (Dialog.getText().contains("If I were you I would talk to Baraek,")) {
                println("Baraek into Located"); // Shield of Arrav
                click1188.invokeExact(13, -1); // closes chat without the X option :D
            } else if (Dialog.getText().contains("The ruthless and notorious Black Arm ")) {
                println("Talk to Charlie"); // Shield of Arrav
                click1188.invokeExact(13, -1); // closes chat without the X option :D
            } else {
                MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77594639);
            }
        } else if (Interfaces.isOpen(1187)) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 77791252);
        } else if (Interfaces.isOpen(1191)) {
            MiniMenu.interact(ComponentAction.DIALOGUE.getType(), 0, -1, 78053391);
        } else if (Interfaces.isOpen(1193)) {
            click1193.invokeExact(16, -1);
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
        }
    }

    public static Integer getDialogueNumber() {
        ScriptConsole.println(Dialog.getOptions());
        List<String> options = Dialog.getOptions();
        if (!options.isEmpty()) {
            for (Dialogue dialogue : Dialogue.values()) {
                if (options.contains(dialogue.getText())) {
                    int size = options.size();
                    int option = 0;

                    for (int i = 0; i < size; ++i) {
                        if (((String) options.get(i)).contains(dialogue.getText())) {
                            ScriptConsole.println("Interacting with option: " + dialogue.getText() + " Option: " + i);
                            option = i;
                            break;
                        }
                    }


                    return option;
                }
            }
        }
        return -1;
    }

    public static boolean isCLick() {
        Component thing = ComponentQuery.newQuery(955).componentIndex(18).subComponentIndex(-1).results().first();
        if (thing != null) {

            return true;
        }
        println("null");
        return false;
    }

    public static enum Dialogue {

        //LOST CITY
        WHY_CAMPED_OUT_HERE(1, "Why are you camped out here?"),
        WHOS_ZANARIS(1, "Who's Zanaris?"),
        HIDDEN_FIND_IT(1, "If it's hidden, how are you planning to find it?"),
        DONT_KNOW_EITHER(2, "Looks like you don't know either."),
        IVE_BEEN_IN_THAT_SHED(2, "I've been in that shed and I didn't see a city."),
        YES_PLEASE_TELEPORT_USEFUL(1, "Yes, please, a teleport would be useful."),
        WELL_THAT_IS_A_RISK(2, "Well, that is a risk I will have to take."),

        //Cook's Assistant https://runescape.wiki/w/Cook%27s_Assistant/Quick_guide
        IM_AFTER_SOME_TOP_QUALITY_MILK(1, "I'm after some top-quality milk."),
        IM_LOOKING_FOR_EXTRA_FINE_FLOWER(1, "I'm looking for extra fine flour."),
        DO_YOU_HAVE_ANY_OTHER_QUESTS(1, "Do you have any other quests for me?"),
        ANGRY_IT_MAKES_ME_ANGRY(1, "Angry! It makes me angry!"),
        WHAT_SEEMS_TO_BE_THE_PROBLEM(1, "What seems to be the problem?"),
        IM_LOOKING_FOR_EXTRA_FINE_FLOUR(1, "I'm looking for extra fine flour."),
        IM_FINE_THANKS(2, "I'm fine, thanks."),
        WHAT_WRONG(1, "What's wrong?"),
        I_GET_ON_IT(1, "I'll get right on it."),


        //Violetisblue
        KNOCK(1, "Knock again."),
        READY_FOR_ADVENTURE(1, "Go on an adventure!"),
        BREAK_THE_DOOR_DOWN(3, "Break the door down."),
        TO_GO_ON_AN_ADVENTURE(1, "To go on an adventure."),
        YOUVE_CAPTURED_A_HUMAN_GIRL(1, "YOU'VE CAPTURED A HUMAN GIRL!"),
        USEFUL(1, "Do you have anything useful?"),
        HEAD(1, "Happy face."),
        YES(1, "Yes!"),

        //BLOODPACT
        HANDLE(4, "I can handle this."),
        YES_NOW_DIE(3, "Yes. Now die!"),
        TIME_DIE(2, "Time for you to die!"),
        GOANYWAY(1, "Go downstairs anyway."),
        NIGHTMARE(4, "I'm your worst nightmare, Zamorakian scum!"),
        WHAT_HELP_DO_YOU_NEED(1, "What help do you need?"),
        ILL_HELP_YOU(1, "I'll help you."),
        YES_RESCUE_ILONA(1, "Yes, rescue Ilona."),
        IM_READY_FOR_MY_REWARD(1, "I'm ready for my reward."),

        //RESTLESS GHOST
        IM_LOOKING_FOR_A_QUEST(3, "I'm looking for a quest!"),
        FATHER_AERECK_SENT_ME(2, "Father Aereck sent me to talk to you."),
        A_GHOST_IS_HAUNTING(1, "A ghost is haunting his graveyard."),
        YEP_NOW_TELL_ME(1, "Yep. Now, tell me what the problem is."),
        SKULL(1, "Put the skull in the coffin."),

        //WHAT LIES BELOW
        HELLO_THERE(2, "Hello there!"),
        SHALL_I_GET_THEM_BACK(3, "Shall I get them back for you?"),
        BRING_IT_ON(1, "Bring it on!"),
        GO_ON_THEN(1, "Go on, then!"),
        YES_I_HAVE_A_LETTER_FOR_YOU(1, "Yes! I have a letter for you."),
        RAT_BURGISS_SENT_ME(4, "Rat Burgiss sent me."),
        I_HAVE_THE_THINGS_YOU_WANTED(1, "I have the things you wanted!"),

        //The Knight's Sword
        CLOSE(6, "redberry pie. They REALLY like redberry pie."),
        CHAT(1, "Chat"),
        ASK_LIFE(1, "And how is life as a squire?"),
        OFFER_SWORD(2, "I can make a new sword if you like..."),
        ASK_DWARVES(1, "So would these dwarves make another one?"),
        SOMETHING_ELSE(2, "Something else."),
        OFFER_PIE(2, "Would you like some redberry pie?"),
        IMCANDO_DWARVES(3, "What do you know about the Imcando dwarves?"),
        //8180

        ///Shield of Arrav
        SEARCH_QUEST(1, "I'm in search of a quest."),
        FIND_PHOENIX_GANG(1, "Do you know where I can find the Phoenix Gang?"),
        PHOENIX_GANG_LOCATION(1, "Can you tell me where I can find the Phoenix Gang?"),
        OFFER_GOLD(1, "Alright. Have 10 gold coins."),
        THANKS(2, "Thanks!"),
        IDENTIFY(1, "I know who you are!"),
        OFFER_SERVICES(1, "I'd like to offer you my services."),
        FIND_BLACK_ARM_GANG(3, "Do you know where I can find the Black Arm Gang hideout?"),
        FAIR_PAYMENT(1, "That sounds fair. (Pay 10 gold.)"),
        HEARD_BLACK_ARM_GANG(1, "I've heard you're the Black Arm Gang."),
        HEARD_BLACK_ARM_GANG1(5, "Get a job!"),
        ANY_OPTION(1, "I'd rather not reveal my sources."),
        BECOME_MEMBER(1, "I want to become a member of your gang."),
        GIVE_TRY(1, "Well, you can give me a try, can't you?"),
        GET_CROSSBOWS(1, "No problem. I'll get you two phoenix crossbows."),
        //TALK_SHIELD_OF_ARRAV(1, "Talk about the Shield of Arrav."),
        TALK_SHIELD_OF_ARRAV_2(1, "Talk about the Shield of Arrav."),
        FAREWELL(3, "Farewell."),

        /// Stolen Hearts
        LET_ME_IN(3, "Let me in or I'll poke your eyes out!"),
        TELL_ME_JOBS(1, "Tell me what jobs you have in the works."),
        HOW_FIND_HQ(1, "How do we find the HQ?"),
        LOCK_UP(4, "I'll lock you up and throw away the key."),
        FIND_EASILY(3, "We'll find them easily enough ourselves."),
        TAKE_PRIDE(3, "What will you take pride in while locked up?"),
        CLEAR(1, "Crystal clear."),
        CARRY(1, "Carry their goods home and case the joint."),
        DIG(1, "Dig a tunnel."),
        LACKEY(1, "Have my lackeys work them over..."),
        YESSR(1, "Yes."),
        BACK(1, "I'll be back soon."),
        PASSCODE(1, "Scheherazade."),
        ///Family Crest
        /// THE GOLEM
        OPEN_PORTAL(1, "How do I open the portal?"),
        STATUETTE_UZER(3, "I'm looking for a statuette recovered from the city of Uzer."),
        OPEN_ELDER_DEMON_PORTAL(1, "I want to open a portal to the lair of an elder-demon."),
        LETTER_IN_DESERT(3, "I found a letter in the desert with your name on."),


        ///Rune Mythos
        //YES("Yes."),
        IM_GOOD_WHATS_NEXT(4,"I'm good. What's next?");

        private final int number;
        private final String text;

        Dialogue(int number, String text) {
            this.number = number;
            this.text = text;
        }

        public int getNumber() {
            return number;
        }

        public String getText() {
            return text;
        }
    }


    public static void println(String msg) {
        ScriptConsole.println(msg);
    }

}
