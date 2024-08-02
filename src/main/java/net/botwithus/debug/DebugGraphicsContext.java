package net.botwithus.debug;

import net.botwithus.rs3.imgui.ImGui;
import net.botwithus.rs3.imgui.ImGuiWindowFlag;
import net.botwithus.rs3.imgui.NativeInteger;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.script.ScriptGraphicsContext;

import static net.botwithus.debug.DebugScript.*;


public class DebugGraphicsContext extends ScriptGraphicsContext {

    private final DebugScript script;


    public DebugGraphicsContext(ScriptConsole console, DebugScript script) {
        super(console);
        this.script = script;

    }
    static String[] questNames;

    static {
        // Fill the questNames array with the names of the quests
        Quest[] quests = Quest.values();
        questNames = new String[quests.length];
        for (int i = 0; i < quests.length; i++) {
            questNames[i] = quests[i].name();
        }
    }

   NativeInteger quest = new NativeInteger(0);


    @Override
    public void drawSettings() {
        if (ImGui.Begin("Quests", ImGuiWindowFlag.None.ordinal())) {

            if (ImGui.Button(running ? "Stop" : "Start")) {
                running = !running;
            }

            ImGui.Combo("Quest",quest,questNames);
            currentQuest = Quest.values()[quest.get()];
        }
        ImGui.End();

        }




}
