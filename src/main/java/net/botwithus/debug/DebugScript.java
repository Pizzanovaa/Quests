package net.botwithus.debug;


import net.botwithus.internal.scripts.ScriptDefinition;
import net.botwithus.rs3.events.impl.ServerTickedEvent;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.movement.Movement;
import net.botwithus.rs3.game.movement.NavPath;
import net.botwithus.rs3.game.movement.TraverseEvent;
import net.botwithus.rs3.game.scene.entities.characters.player.LocalPlayer;
import net.botwithus.rs3.script.LoopingScript;
import net.botwithus.rs3.script.config.ScriptConfig;

import static net.botwithus.debug.Dialogs.pressDialog;



public class DebugScript extends LoopingScript {



    public DebugScript(String s, ScriptConfig scriptConfig, ScriptDefinition scriptDefinition) {
        super(s, scriptConfig, scriptDefinition);
    }

    public boolean initialize() {
        this.sgc = new DebugGraphicsContext(getConsole(), this);
        loopDelay = 200;

        return super.initialize();
    }

    public static enum Quest {

        COOKS_ASSITANT,
        VIOLET_IS_BLUE,
        BLOOD_PACT,
        NECROMANCY_INTRO,
        RESTLESS_GHOST;

    }

    public static Quest currentQuest = Quest.NECROMANCY_INTRO;
    public static boolean running = false;


    public void onDeactivation(){
        unsubscribeAll();
    }

    static boolean moveTo(Coordinate location) {
        Dialogs.println("moveTo");
        LocalPlayer player = Client.getLocalPlayer();

        if (location.distanceTo(player.getCoordinate()) < 4) {
            Dialogs.println("moveTo | Already at the target location.");
            return true;
        }



        Dialogs.println("moveTo | Traversing to location: " + location);
        NavPath path = NavPath.resolve(location);
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



    @Override
    public void onLoop() {




        if(!running){
            return;

        }

        switch (currentQuest){
            case VIOLET_IS_BLUE -> VioletIsBlue.quest2();
            case COOKS_ASSITANT -> CooksAssitant.quest();
            case NECROMANCY_INTRO -> Necromancy1.quest2();
            case BLOOD_PACT -> BloodPact.quest();
            case RESTLESS_GHOST -> RestlessGhost.quest();
            default -> delay(100);
        }



    }

    public void onActivation() {
        subscribe(ServerTickedEvent.class, ServerTickedEvent -> {
            if (running) {
                pressDialog();
            }
        });
    }



}




