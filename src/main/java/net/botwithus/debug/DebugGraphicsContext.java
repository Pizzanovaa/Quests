package net.botwithus.debug;

import net.botwithus.rs3.game.js5.types.QuestType;
import net.botwithus.rs3.game.js5.types.configs.ConfigManager;
import net.botwithus.rs3.game.skills.Skills;
import net.botwithus.rs3.game.vars.VarManager;
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
            ImGui.SameLine();
            ImGui.SetItemWidth(200);
            ImGui.Combo("Quest", quest, questNames);
            currentQuest = Quest.values()[quest.get()];


            ImGui.Separator();
            QuestType questType = ConfigManager.getQuestType(currentQuest.getQuestId());


            // Display required items
            ImGui.Text("Required Items:");
            if (questType != null) {
                String param = (String) questType.params().get(7815);
                if (param != null) {
                    ImGui.Text(param);
                } else {
                    ImGui.Text("None.");
                }
            }
            ImGui.Separator();
            // Retrieve and display skill requirements
            ImGui.Text("Skill Requirements:");
            if (questType != null) {
                int[][] requirements = questType.skillRequirments();
                if (requirements != null && requirements.length > 0) {
                    for (int[] skill : requirements) {
                        if (skill.length == 2) {
                            int skillId = skill[0];
                            int level = skill[1];
                            String skillString = String.valueOf(Skills.byId(skillId));
                            ImGui.Text("Skill: " + skillString + ", Level: " + level);
                        } else {
                            ImGui.Text("Invalid skill requirement format.");
                        }
                    }
                } else {
                    ImGui.Text("None.");
                }
                ImGui.Separator();

                // Retrieve and display progress varbits
                int[][] varbits = questType.progressVarbits();
                if (varbits != null && varbits.length > 0) {
                    for (int[] varbit : varbits) {
                        if (varbit.length == 3) {
                            int varbitId = varbit[0];
                            int value = varbit[1];
                            int value2 = varbit[2];

                            int currentVarbitValue = VarManager.getVarbitValue(varbitId);
                            String progressStatus;
                            if (currentVarbitValue < value) {
                                ImGui.PushStyleColor(0, 1.0f, 0.0f, 0.0f, 1.0f);
                                progressStatus = "Uncompleted";
                            } else if (currentVarbitValue < value2) {
                                ImGui.PushStyleColor(0, 0.0f, 0.5f, 0.0f, 1.0f);
                                progressStatus = "In progress";
                            } else {
                                ImGui.PushStyleColor(0, 0.0f, 1.0f, 0.0f, 1.0f);
                                progressStatus = "Completed";
                            }

                            ImGui.Text(progressStatus);
                            ImGui.PopStyleColor(1);

                            ImGui.Text("Varbit ID: " + varbitId + ", Start: " + value + ", Finish: " + value2);
                            ImGui.Text("Quest Step: " + currentVarbitValue);
                        } else {
                            ImGui.Text("Invalid progress varbit format.");
                        }
                    }
                } else {
                    int[][] Varps = questType.progressVarps();
                    if (Varps != null && Varps.length > 0) {
                        for (int[] varp : Varps) {
                            if (varp.length == 3) {
                                int varpId = varp[0];
                                int value = varp[1];
                                int value2 = varp[2];

                                int currentvarpValue = VarManager.getVarpValue(varpId);
                                String progressStatus;
                                if (currentvarpValue < value) {
                                    ImGui.PushStyleColor(0, 1.0f, 0.0f, 0.0f, 1.0f);
                                    progressStatus = "Uncompleted";
                                } else if (currentvarpValue < value2) {
                                    ImGui.PushStyleColor(0, 0.0f, 0.5f, 0.0f, 1.0f);
                                    progressStatus = "In progress";
                                } else {
                                    ImGui.PushStyleColor(0, 0.0f, 1.0f, 0.0f, 1.0f);
                                    progressStatus = "Completed";
                                }


                                ImGui.Text(progressStatus);
                                ImGui.PopStyleColor(1);

                                ImGui.Text("Varp ID: " + varpId + ", Start: " + value + ", Finish: " + value2);
                                ImGui.Text("Quest Step: " + currentvarpValue);
                            } else {
                                ImGui.Text("Invalid progress varp format.");
                            }
                        }
                    } else {
                        ImGui.Text("N/A");
                    }
                }
            } else {
                ImGui.Text("N/A");
            }
            ImGui.Separator();


            ImGui.EndTabItem();
        }
        ImGui.End();
    }
}
