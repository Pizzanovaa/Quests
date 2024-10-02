package net.botwithus.debug;

import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.login.LoginManager;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.util.RandomGenerator;

import static net.botwithus.debug.Dialogs.isDialogOpen;
import static net.botwithus.rs3.script.Execution.delay;
/*
 boolean city_of_um = VarManager.getVarbitValue(53270) == 1;
        boolean fort_loadstone = VarManager.getVarbitValue(52518) == 1;
        boolean dinosaur_loadstone = VarManager.getVarbitValue(44270) == 1;
        boolean menaphos_loadstone = VarManager.getVarbitValue(36173) == 1;
        boolean prif_loadstone = VarManager.getVarbitValue(24967) == 1;
        boolean ashdale_loadstone = VarManager.getVarbitValue(22430) == 1;
        boolean wilderness_loadstone = VarManager.getVarbitValue(18529) == 1;
        boolean tirawin_loadstone = VarManager.getVarbitValue(18528) == 1;
        boolean ooglog_loadstone = VarManager.getVarbitValue(18527) == 1;
        boolean karamja_loadstone = VarManager.getVarbitValue(18526) == 1;
        boolean fremnik_loadstone = VarManager.getVarbitValue(18525) == 1;
        boolean eaglepeak_loadstone = VarManager.getVarbitValue(18524) == 1;
        boolean canifs_loadstone = VarManager.getVarbitValue(18523) == 1;
        boolean yanille_loadstone = VarManager.getVarbitValue(40) == 1;
        boolean varrok_loadstone = VarManager.getVarbitValue(39) == 1;
        boolean taverly_loadstone = VarManager.getVarbitValue(38) == 1;
        boolean seers_loadstone = VarManager.getVarbitValue(37) == 1;
        boolean portsarim_loadstone = VarManager.getVarbitValue(36) == 1;
        boolean lummy_loadstone = VarManager.getVarbitValue(35) == 1;
        boolean falador_loadstone = VarManager.getVarbitValue(34) == 1;
        boolean  edgevile_loadstone = VarManager.getVarbitValue(33) == 1;
        boolean  draynor_loadstone = VarManager.getVarbitValue(32) == 1;
        boolean  catherby_loadstone = VarManager.getVarbitValue(31) == 1;
        boolean ardy_loadstone =  VarManager.getVarbitValue(29) == 1;
        boolean alkahrid_loadstone =  VarManager.getVarbitValue(28) == 1;
        //boolean burthope_loadstone =  VarManager.getVarbitValue(30) == 1; Auto unlocked.
 */
public class Loadstones {
    static Coordinate player = Client.getLocalPlayer().getServerCoordinate();
    static Coordinate taverlycord = new Coordinate(2881, 3446, 0);
    static Area.Circular taverlyarea = new Area.Circular(taverlycord, 10);
    static Coordinate lummycord = new Coordinate(3233, 3221, 0);
    static Area.Circular lummyarea = new Area.Circular(lummycord, 10);
    static Coordinate alkaridcord = new Coordinate(3294, 3185, 0);
    static Area.Circular alkaridarea = new Area.Circular(alkaridcord, 10);
    static Coordinate draynorcord = new Coordinate(3105, 3298, 0);
    static Area.Circular draynorarea = new Area.Circular(draynorcord, 10);
    static Coordinate varrockcord = new Coordinate(3214, 3376, 0);
    static Area.Circular varrockarea = new Area.Circular(varrockcord, 10);
    static Coordinate edgevillecord = new Coordinate(3070, 3504, 0);
    static Area.Circular edgevillearea = new Area.Circular(edgevillecord, 10);
    static Coordinate wildernesscord = new Coordinate(3141, 3631, 0);
    static Area.Circular wildernessarea = new Area.Circular(wildernesscord, 10);
    static Coordinate fallycord = new Coordinate(2967, 3406, 0);
    static Area.Circular fallyarea = new Area.Circular(fallycord, 10);
    static Coordinate portsarimcord = new Coordinate(3014, 3216, 0);
    static Area.Circular portsarimarea = new Area.Circular(portsarimcord, 10);
    static Coordinate umcord = new Coordinate(1084, 1768, 1);
    static Area.Circular umarea = new Area.Circular(umcord, 10);

    public static void unlockloadstones() {
        player = Client.getLocalPlayer().getServerCoordinate();

        boolean falador_loadstone = VarManager.getVarbitValue(34) == 1;
        boolean lummy_loadstone = VarManager.getVarbitValue(35) == 1;
        boolean taverly_loadstone = VarManager.getVarbitValue(38) == 1;
        boolean alkarid_loadstone = VarManager.getVarbitValue(28) == 1;
        boolean draynor_loadstone = VarManager.getVarbitValue(32) == 1;
        boolean varrok_loadstone = VarManager.getVarbitValue(39) == 1;
        boolean edgeville_loadstone = VarManager.getVarbitValue(33) == 1;
        boolean wilderness_loadstone = VarManager.getVarbitValue(18529) == 1;
        boolean portsarim_loadstone = VarManager.getVarbitValue(36) == 1;
        boolean umquestcomplete = VarManager.getVarpValue(10982) == 245318; // Quest completion check
        boolean city_of_um = VarManager.getVarbitValue(53270) == 1;

        if (isDialogOpen() || Client.getLocalPlayer().isMoving()) {
            return;
        }

      if(allloadsactive()){
          ScriptConsole.println("All loadstones complete.");
      }
        if (umquestcomplete && !city_of_um) {
            handleLoadstone(umarea, umcord, "City of Um");
            return;
        }

        if (!taverly_loadstone) {
            handleLoadstone(taverlyarea, taverlycord, "Taverley");
            return;
        }
        if (!falador_loadstone) {
            handleLoadstone(fallyarea, fallycord, "Falador");
            return;
        }

        if (!lummy_loadstone) {
            handleLoadstone(lummyarea, lummycord, "Lumbridge");
            return;
        }
        if (!alkarid_loadstone) {
            handleLoadstone(alkaridarea, alkaridcord, "Al Kharid");
            return;
        }
        if (!draynor_loadstone) {
            handleLoadstone(draynorarea, draynorcord, "Draynor");
            return;
        }
        if (!varrok_loadstone) {
            handleLoadstone(varrockarea, varrockcord, "Varrock");
            return;
        }
        if (!edgeville_loadstone) {
            handleLoadstone(edgevillearea, edgevillecord, "Edgeville");
            return;
        }
        if (!wilderness_loadstone) {
            handleLoadstone(wildernessarea, wildernesscord, "Wilderness");
            return;
        }
        if (!portsarim_loadstone) {
            handleLoadstone(portsarimarea, portsarimcord, "Port Sarim");
            return;
        }


    }

    private static boolean areAllLoadstonesActivated(boolean... loadstones) {
        for (boolean loadstone : loadstones) {
            if (!loadstone) {
                return false;
            }
        }
        return true;
    }

    private static void handleLoadstone(Area.Circular area, Coordinate coord, String loadstoneName) {
        if (area.contains(player)) {
            unlockloadstone();
        } else {
            DebugScript.moveTo(coord);
        }
    }

    public static void unlockloadstone() {
        SceneObject lodestone = SceneObjectQuery.newQuery().name("lodestone", String::contains).option("Activate").hidden(false).results().nearest();
        if (lodestone != null) {
            lodestone.interact("Activate");
            delay(RandomGenerator.nextInt(600, 1200));
        }
    }

    public static boolean allloadsactive(){
        boolean falador_loadstone = VarManager.getVarbitValue(34) == 1;
        boolean lummy_loadstone = VarManager.getVarbitValue(35) == 1;
        boolean taverly_loadstone = VarManager.getVarbitValue(38) == 1;
        boolean alkarid_loadstone = VarManager.getVarbitValue(28) == 1;
        boolean draynor_loadstone = VarManager.getVarbitValue(32) == 1;
        boolean varrok_loadstone = VarManager.getVarbitValue(39) == 1;
        boolean edgeville_loadstone = VarManager.getVarbitValue(33) == 1;
        boolean wilderness_loadstone = VarManager.getVarbitValue(18529) == 1;
        boolean portsarim_loadstone = VarManager.getVarbitValue(36) == 1;
        boolean umquestcomplete = VarManager.getVarpValue(10982) == 245318; // Quest completion check
        boolean city_of_um = VarManager.getVarbitValue(53270) == 1;

        if(falador_loadstone && lummy_loadstone && taverly_loadstone && alkarid_loadstone && draynor_loadstone && varrok_loadstone && edgeville_loadstone && wilderness_loadstone && portsarim_loadstone
                && (umquestcomplete && city_of_um || !umquestcomplete && !city_of_um)){
            return true;
        }else{
            return false;
        }
    }
}
