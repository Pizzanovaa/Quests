package net.botwithus.debug;


import net.botwithus.internal.scripts.ScriptDefinition;
import net.botwithus.rs3.events.EventBus;
import net.botwithus.rs3.events.Subscription;
import net.botwithus.rs3.events.impl.ChatMessageEvent;
import net.botwithus.rs3.events.impl.ServerTickedEvent;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.movement.NavPath;
import net.botwithus.rs3.game.movement.TraverseEvent;
import net.botwithus.rs3.game.scene.entities.characters.player.LocalPlayer;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.LoopingScript;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.script.config.ScriptConfig;


public class DebugScript extends LoopingScript {


    public DebugScript(String s, ScriptConfig scriptConfig, ScriptDefinition scriptDefinition) {
        super(s, scriptConfig, scriptDefinition);
    }

    public boolean initialize() {
        this.sgc = new DebugGraphicsContext(getConsole(), this);
        loopDelay = 200;

        return super.initialize();
    }


    public static Quest currentQuest = Quest.TEST_DONTSELECT;
    public static boolean running = false;


    private Subscription<ServerTickedEvent> subscription;
    private Subscription<ChatMessageEvent> subscription2;

    public static String message = "";

    @Override
    public void onLoop() {


        if (!running) {
            return;
        }


        switch (currentQuest) {
            case DEATH_PLATEAU -> DeathPlateau.quest();
            case DRUIDIC_RITUAL -> DruidicRitual.quest();
            //case LET_THEM_EAT_PIE -> LetThemEatPie.quest();
            case WOLF_WHISTLE -> WolfWhistle.quest();
            case VIOLET_IS_BLUE -> VioletIsBlue.quest2();
            case VIOLET_IS_BLUE_TOO -> VioletIsBlueToo.quest();
            case COOKS_ASSISTANT -> CooksAssitant.quest();
            //TODO: Rework the whole quest to use do movement base on a fix coordinate, instead of player coordinate, can use the coordinate from the entrace SceneObject, this will make it much faster, and reliable
            case MYTHS_OF_THE_WHITE_LANDS -> MythsOfTheWhiteLands.quest();
            case NECROMANCY_INTRO -> Necromancy1.quest2();
            case IMP_CATCHER -> Impcatcher.quest();
            case BLOOD_PACT -> BloodPact.quest();
            case RESTLESS_GHOST -> RestlessGhost.quest();
            case WHAT_LIES_BELOW -> WhatLiesBelow.quest();
            case THE_KNIGHT_SWORD -> TheKnightSword.quest(); // Nav stuck on agilty req
            case SHIELD_OF_ARRAV -> ShieldofArrav.quest();
            case STOLEN_HEARTS ->
                    StolenHearts.quest2(); //Requires manual play for diamond idol and seems to be crashy...
            case THE_GOLEM -> TheGolem.quest();
            case RUNE_MYTHOS -> RuneMythos.quest();
            case GHOSTS_AHOY -> GhostsAhoy.quest();
            case VESSEL_HARINGER -> VesselHarbinger.quest();
            case SPIRIT_WAR -> Spirit_War.quest();
            case TOMES_OF_WARLOCK -> TomesOfWarlock.quest(); //whynowrok
            case DIAMOND_ROUGH -> DiamondRough.quest();
            case JACK_OF_SPADES -> JackofSpades.quest();
            case DAUGHTER_OF_CHAOS -> DaughterofChaos.quest();
            case KILI_ROW -> KiliRow.quest();
            case ARCH_TUTORIAL -> ArchTut.quest();
            case F2P_LODESTONES -> Loadstones.unlockloadstones();
            case NEW_FOUNDATION -> NewFoundation.quest();
            case KILI_KNOWLEDGE_I -> KiliRowI.quest();
            case KILI_KNOWLEDGE_II -> KiliRowII.quest();
            case WATERFALL -> Waterfall.quest();
            case ENTER_THE_ABYSS -> Entertheabyss.quest();
            case FAMILY_CREST_INCOMPLETE -> FamilyCrest.quest();
            case WHATS_MINE_IS_YOURS -> WhatsMineIsYours.quest();
            case GERTRUDE_CAT -> GertrudeCat.quest();
            case MOGRE_ACTIVITY -> mogre_lore_activity.quest();
            case KILI_KNOWLEDGE_III -> KiliRowIII.quest();
            case KILI_KNOWLEDGE_IV -> KiliRowIV.quest();
            //case KILI_KNOWLEDGE_V -> KiliRowV.quest();
            case ANACHRONIA_TUT -> AnachroniaTut.quest();
            case CHRISTMAS_REUNION -> ChristmasReunion.quest();
            case ITS_SNOW_BOTHER -> ItsSnowBother.quest();
            case MURDER_ON_THE_BORDER -> Murderontheborder.quest();
            case UNWELCOME_GUESTS -> unwelcomeguests.quest();
            case DEAD_AND_BURIED -> DeadandBuried.quest();
            case ANCIENT_AWAKENING -> AncientAwakening.quest();
            case BATTLE_OF_FORINTHRY -> BattleofForinthry.quest();
            case REQUIEM_FOR_A_DRAGON -> RequiemforaDragon.quest();
            default -> delay(100);
        }


    }

    private static boolean disableDive = true;

    static boolean moveTo(Coordinate location) {
        Dialogs.println("moveTo");
        LocalPlayer player = Client.getLocalPlayer();

        if (location.distanceTo(player.getCoordinate()) < 4) {
            Dialogs.println("moveTo | Already at the target location.");
            return true;
        }
        int flag = 0;
        if (disableDive) {
            flag |= Movement.DISABLE_DIVE;
            ScriptConsole.println(" Movement Ability Dive is Disabled");
        }


        Dialogs.println("moveTo | Traversing to location: " + location);
        NavPath path = NavPath.resolve(location, flag).interrupt(event -> (VarManager.getVarbitValue(21222) == 1));
        TraverseEvent.State moveState = Movement.traverse(path);

        switch (moveState) {
            case INTERRUPTED -> {
                Dialogs.println("moveTo | Return false.");
                return false;
            }
            case FINISHED -> {
                Dialogs.println("moveTo | Successfully moved to the area.");
                return true;
            }
            case NO_PATH -> {
                Dialogs.println("moveTo | Path failed: " + moveState.toString());
                return false;
            }
            case FAILED -> {
                Dialogs.println("moveTo | Path state: " + moveState.toString());
                Dialogs.println("moveTo | No path found or movement failed.");
                return false;
            }
            default -> {
                Dialogs.println("moveTo | Unexpected state: " + moveState.toString());
                return false;
            }
        }
    }

    public void onActivation() {

        subscription = EventBus.EVENT_BUS.subscribe(this, ServerTickedEvent.class, this::onServerTickedEvent);
        subscription2 = EventBus.EVENT_BUS.subscribe(this, ChatMessageEvent.class, this::onChatMessageEvent);

    }

    private void onServerTickedEvent(ServerTickedEvent event) {
        if (running) {
            Dialogs.pressDialog();
        }
    }

    private void onChatMessageEvent(ChatMessageEvent event) {
        if (running) {
            String message = event.getMessage();
            if (currentQuest == Quest.VIOLET_IS_BLUE_TOO) {
                println("TRACKING CHAT FOR VIOLET IS BLUE TOO");
                println(message);
//                if (message.contains("Objective: Visit Taylor to the northwest of the town to pick out a new Christmas tree for the town centrepiece.")) {
//                   VioletIsBlueToo.currentStep="GOTOTAYLOR";
//                }
//                if (message.contains("5/5 lampposts decorated")) {
//                    VioletIsBlueToo.currentStep="DOHOUSES";
//                }
                if (message.contains("Objective: Find an area high enough in the town to launch a snow impling from to decorate the tree.")) {
                    VioletIsBlueToo.currentStep5 = "GOUPHILL";
                    println(VioletIsBlueToo.currentStep5);
                }
                return;
            }

            if (message.contains("Delivered 0/5 presents to citizens of Gielinor")) {
                DebugScript.message = message;
            }
            if (message.contains("Delivered 1/5 presents to citizens of Gielinor")) {
                DebugScript.message = message;
            } else if (message.contains("Delivered 2/5 presents to citizens of Gielinor")) {
                DebugScript.message = message;
            }
            if (message.contains("Delivered 3/5 presents to citizens of Gielinor")) {
                DebugScript.message = message;
            } else if (message.contains("Delivered 4/5 presents to citizens of Gielinor")) {
                DebugScript.message = message;
            }

        }
    }

    public void onDeactivation() {
        if (subscription != null) {
            EventBus.EVENT_BUS.unsubscribe(subscription);
            subscription = null;
        }
        if (subscription2 != null) {
            EventBus.EVENT_BUS.unsubscribe(subscription2);
            subscription2 = null;
        }


    }

    public enum Quest {
        DEATH_PLATEAU(140),
        DRUIDIC_RITUAL(111),
        // LET_THEM_EAT_PIE(200), //UNFINISHED
        WOLF_WHISTLE(324),
        VIOLET_IS_BLUE(400),
        VIOLET_IS_BLUE_TOO(453),
        BLOOD_PACT(335),
        RESTLESS_GHOST(27),
        COOKS_ASSISTANT(257),
        MYTHS_OF_THE_WHITE_LANDS(74),
       // MYTHS_OF_THE_WHITE_LANDS(74),
        NECROMANCY_INTRO(493),
        IMP_CATCHER(72),
        WHAT_LIES_BELOW(144),
        THE_KNIGHT_SWORD(261),
        SHIELD_OF_ARRAV(63),
        FAMILY_CREST_INCOMPLETE(116),
        TOMES_OF_WARLOCK(497),
        STOLEN_HEARTS(355),
        THE_GOLEM(286),
        RUNE_MYTHOS(494),
        GHOSTS_AHOY(82),
        VESSEL_HARINGER(495),
        SPIRIT_WAR(496),
        DIAMOND_ROUGH(356),
        JACK_OF_SPADES(390),
        DAUGHTER_OF_CHAOS(483),
        KILI_ROW(500),
        F2P_LODESTONES(9999999),
        ARCH_TUTORIAL(999999),
        ANACHRONIA_TUT(99999999),
        NEW_FOUNDATION(489),
        KILI_KNOWLEDGE_I(99999),
        KILI_KNOWLEDGE_II(99999),
        KILI_KNOWLEDGE_III(99999),
        KILI_KNOWLEDGE_IV(99999),
        KILI_KNOWLEDGE_V(99999),
        MOGRE_ACTIVITY(99999),
        WATERFALL(93),
        ENTER_THE_ABYSS(3149),
        WHATS_MINE_IS_YOURS(357),
        GERTRUDE_CAT(138),
        CHRISTMAS_REUNION(516),
        ITS_SNOW_BOTHER(508),
        DEAD_AND_BURIED(492),
        ANCIENT_AWAKENING(502),
        BATTLE_OF_FORINTHRY(507),
        REQUIEM_FOR_A_DRAGON(509),
        MURDER_ON_THE_BORDER(490),
        UNWELCOME_GUESTS(491),


        TEST_DONTSELECT(135);


        private final int questId;

        Quest(int questId) {
            this.questId = questId;
        }

        public int getQuestId() {
            return questId;
        }
    }


}




