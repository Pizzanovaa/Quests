package net.botwithus.debug;

import net.botwithus.rs3.game.js5.types.QuestType;
import net.botwithus.rs3.game.js5.types.configs.ConfigManager;
import net.botwithus.rs3.game.skills.Skills;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.imgui.ImGui;
import net.botwithus.rs3.imgui.ImGuiWindowFlag;
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

    int quest = 0;
    private Quest previousQuest;
    private String requiredItemsText = "";
    private String skillRequirementsText = "";
    private String progressVarbitsText = "";

    @Override
    public void drawSettings() {
        if (ImGui.Begin("Quests", ImGuiWindowFlag.None.ordinal())) {
            if (ImGui.Button(running ? "Stop" : "Start")) {
                running = !running;
            }
            ImGui.SameLine();
            ImGui.SetItemWidth(200);
            quest = ImGui.Combo("Quest", quest, questNames);
            currentQuest = Quest.values()[quest];
            if (ImGui.Button("Refresh Info")) {
                //previousQuest = Quest.TEST;
            }
            // Check if the selected quest has changed
            if (previousQuest != currentQuest) {
                previousQuest = currentQuest; // Update the previous quest

                QuestType questType = ConfigManager.getQuestType(currentQuest.getQuestId());

                // Update required items text
                StringBuilder requiredItemsBuilder = new StringBuilder("Required Items:\n");
                if (questType != null) {
                    String param = (String) questType.params().get(7815);
                    if (param != null) {
                        requiredItemsBuilder.append(param);
                    } else {
                        requiredItemsBuilder.append("None.");
                    }
                } else {
                    requiredItemsBuilder.append("N/A");
                }
                requiredItemsText = requiredItemsBuilder.toString();

                // Update skill requirements text
                StringBuilder skillRequirementsBuilder = new StringBuilder("Skill Requirements:\n");
                if (questType != null) {
                    int[][] requirements = questType.skillRequirments();
                    if (requirements != null && requirements.length > 0) {
                        for (int[] skill : requirements) {
                            if (skill.length == 2) {
                                int skillId = skill[0];
                                int level = skill[1];
                                String skillString = String.valueOf(Skills.byId(skillId));
                                skillRequirementsBuilder.append("Skill: ").append(skillString).append(", Level: ").append(level).append("\n");
                            } else {
                                skillRequirementsBuilder.append("Invalid skill requirement format.\n");
                            }
                        }
                    } else {
                        skillRequirementsBuilder.append("None.");
                    }
                } else {
                    skillRequirementsBuilder.append("N/A");
                }
                skillRequirementsText = skillRequirementsBuilder.toString();

                // Update progress varbits text
                StringBuilder progressVarbitsBuilder = new StringBuilder("Progress Varbits:\n");
                if (questType != null) {
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
                                    progressStatus = "Uncompleted";
                                } else if (currentVarbitValue < value2) {
                                    progressStatus = "In progress";
                                } else {
                                    progressStatus = "Completed";
                                }

                                progressVarbitsBuilder.append("Status: ").append(progressStatus)
                                        .append(", Varbit ID: ").append(varbitId)
                                        .append(", Start: ").append(value)
                                        .append(", Finish: ").append(value2)
                                        .append(", Current: ").append(currentVarbitValue).append("\n");
                            } else {
                                progressVarbitsBuilder.append("Invalid progress varbit format.\n");
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
                                        progressStatus = "Uncompleted";
                                    } else if (currentvarpValue < value2) {
                                        progressStatus = "In progress";
                                    } else {
                                        progressStatus = "Completed";
                                    }

                                    progressVarbitsBuilder.append("Status: ").append(progressStatus)
                                            .append(", Varp ID: ").append(varpId)
                                            .append(", Start: ").append(value)
                                            .append(", Finish: ").append(value2)
                                            .append(", Current: ").append(currentvarpValue).append("\n");
                                } else {
                                    progressVarbitsBuilder.append("Invalid progress varp format.\n");
                                }
                            }
                        } else {
                            progressVarbitsBuilder.append("N/A");
                        }
                    }
                } else {
                    progressVarbitsBuilder.append("N/A");
                }
                progressVarbitsText = progressVarbitsBuilder.toString();
            }

            // Display texts
            ImGui.Separator();
            ImGui.Text(requiredItemsText);
            ImGui.Separator();
            ImGui.Text(skillRequirementsText);
            ImGui.Separator();
            ImGui.Text(progressVarbitsText);
            ImGui.Separator();

            ImGui.EndTabItem();
        }
        ImGui.End();
    }
}
