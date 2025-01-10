package net.botwithus.debug;

import net.botwithus.rs3.game.js5.types.QuestType;
import net.botwithus.rs3.game.js5.types.configs.ConfigManager;
import net.botwithus.rs3.game.skills.Skills;
import net.botwithus.rs3.game.vars.VarManager;
import net.botwithus.rs3.imgui.ImGui;
import net.botwithus.rs3.imgui.ImGuiWindowFlag;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.script.ScriptGraphicsContext;
import net.botwithus.rs3.game.js5.types.vars.VarDomainType;

import static net.botwithus.debug.DebugScript.*;

import net.botwithus.debug.DebugScript.Quest;
import net.botwithus.debug.Dialogs.QuestInstruction;

public class DebugGraphicsContext extends ScriptGraphicsContext {
    private final DebugScript script;
    private Quest previousQuest = Quest.TEST_DONTSELECT;
    private String requiredItemsText = "";
    private String skillRequirementsText = "";
    private String progressVarbitsText = "";
    private String specialInstructionsText = "";
    private String dependentQuestsText = "";

    private int scrollPosition = 0;

    public DebugGraphicsContext(ScriptConsole console, DebugScript script) {
        super(console);
        this.script = script;
    }

    private static final String VERSION = "1.0";
    private static final String CREDITS = "Created by Billythebob, Pizzanova, Query";
    private static final float WINDOW_WIDTH = 625;
    private static final float WINDOW_HEIGHT = 500;
    private boolean forceScroll = false;

    @Override
    
    public void drawSettings() {
        ImGui.SetNextWindowSize(WINDOW_WIDTH, WINDOW_HEIGHT, ImGuiWindowFlag.None.ordinal());
        if (ImGui.Begin("Quest Helper " + VERSION, ImGuiWindowFlag.None.ordinal())) {
            //ImGui.Text(CREDITS);
            //ImGui.Separator();
            // Left column for quest list
            ImGui.BeginChild("quest_list", 250, 400, true, 0);
            for (Quest quest : Quest.values()) {
                if (quest == Quest.TEST_DONTSELECT) continue;
                boolean isSelected = script.currentQuest == quest;
                QuestType questType = ConfigManager.getQuestType(quest.getQuestId());
                
                // Check if quest is complete
                boolean isComplete = false;
                boolean meetsRequirements = true;

                if (questType != null) {
                    int[][] varbits = questType.progressVarbits();
                    if (varbits != null && varbits.length > 0) {
                        int[] varbit = varbits[0];
                        isComplete = VarManager.getVarbitValue(varbit[0]) >= varbit[2];
                    } else {
                        // Check for Varps if Varbits are not defined
                        int[][] varps = questType.progressVarps();
                        if (varps != null && varps.length > 0) {
                            int[] varp = varps[0];
                            if (varp.length == 3) {
                                int currentVarpValue = VarManager.getVarpValue(varp[0]);
                                isComplete = currentVarpValue >= varp[2];
                            }
                        }
                    }

                     // Check requirements
                     int questpoint = VarManager.getVarValue(VarDomainType.PLAYER, 101);
                     if (questpoint < questType.questPointReq()) {
                        meetsRequirements = false;
                    }
                    int[][] skillReqs = questType.skillRequirments();
                    if (skillReqs != null) {
                        for (int[] skill : skillReqs) {
                            if (skill.length == 2) {
                                Skills skillType = Skills.byId(skill[0]);
                                if (skillType != null && skillType.getActualLevel() < skill[1]) {
                                    meetsRequirements = false;
                                    break;
                                }
                            }
                        }
                    }

                    // Check dependent quests
                    int[] dependQuests = questType.dependentQuests();
                    if (dependQuests != null && dependQuests.length > 0) {
                        for (int dependQuestId : dependQuests) {
                            QuestType dependentQuestType = ConfigManager.getQuestType(dependQuestId);
                            if (dependentQuestType != null) {
                                boolean isDependentComplete = false;

                                // Check completion using varbits
                                if (varbits != null && varbits.length > 0) {
                                    int[] varbit = varbits[0];
                                    isDependentComplete = VarManager.getVarbitValue(varbit[0]) >= varbit[2];
                                } else {
                                    // Check completion using varps if varbits are not defined
                                    int[][] varps = dependentQuestType.progressVarps();
                                    if (varps != null && varps.length > 0) {
                                        int[] varp = varps[0];
                                        if (varp.length == 3) {
                                            int currentVarpValue = VarManager.getVarpValue(varp[0]);
                                            isDependentComplete = currentVarpValue >= varp[2];
                                        }
                                    }
                                }

                                // If any dependent quest is incomplete, set meetsRequirements to false
                                if (!isDependentComplete) {
                                    meetsRequirements = false;
                                    break;
                                }
                            }
                        }
                    }

                }
                
                // Set color based on status
                if (isComplete) {
                    ImGui.PushStyleColor(0, 0.0f, 1.0f, 0.0f, 1.0f);
                } else if (!meetsRequirements) {
                    ImGui.PushStyleColor(0, 1.0f, 0.0f, 0.0f, 1.0f);
                }
                
                if (ImGui.Selectable(quest.name(), isSelected, 0)) {
                    script.currentQuest = quest;
                }
                
                if (isComplete || !meetsRequirements) {
                    ImGui.PopStyleColor();
                }
            }

            if (currentQuest != Quest.TEST_DONTSELECT && forceScroll) { //Force scroll
                int i = currentQuest.ordinal();
                float scrollStart = 0.9f;
                float scrollEnd = 2.7f;
                int totalQuests = Quest.values().length;
                float step = (scrollEnd - scrollStart) / (totalQuests - 1);
                float scrollPosition = scrollEnd - (i * step);
                ImGui.SetScrollHereY(scrollPosition);
                forceScroll = false;
            }
            ImGui.EndChild();

            ImGui.SameLine();

            // Right column for quest details
            ImGui.BeginChild("quest_details", 350, 400, true, 0);
            if (script.currentQuest != previousQuest) {
                updateQuestInfo();
            }

            ImGui.Text("Quest Details for: " + script.currentQuest.name());
            ImGui.Separator();
            ImGui.Text(requiredItemsText);
            ImGui.Separator();
            ImGui.Text(skillRequirementsText);
            ImGui.Separator();
            ImGui.Text(progressVarbitsText);
            ImGui.Separator();
            dependQuest();
            ImGui.Separator();
            ImGui.Text(specialInstructionsText);
            
            ImGui.EndChild();

            // Bottom controls
            ImGui.SetCursorPosY(WINDOW_HEIGHT - 60);
            if (ImGui.Button(script.running ? "Stop" : "Start")) {
                script.running = !script.running;
            }
            ImGui.SameLine();
            float textWidth = ImGui.CalcTextSize(CREDITS).getX();
            ImGui.SetCursorPosX(WINDOW_WIDTH - textWidth - 20);
            ImGui.Text(CREDITS);
        }
        ImGui.End();
    }

    private void updateQuestInfo() {
        previousQuest = script.currentQuest;
        QuestType questType = ConfigManager.getQuestType(script.currentQuest.getQuestId());

        // Update required items
        StringBuilder requiredItemsBuilder = new StringBuilder("Required Items:\n");
        if (questType != null) {
            String param = (String) questType.params().get(7815);
            requiredItemsBuilder.append(param != null ? param : "None.");
        } else {
            requiredItemsBuilder.append("N/A");
        }
        requiredItemsText = requiredItemsBuilder.toString();

        // Update skill requirements
        StringBuilder skillReqBuilder = new StringBuilder("Skill Requirements:\n");
        if (questType != null && questType.skillRequirments() != null) {
            for (int[] skill : questType.skillRequirments()) {
                if (skill.length == 2) {
                    skillReqBuilder.append(Skills.byId(skill[0])).append(": Level ").append(skill[1]).append("\n");
                }
            }
        } else {
            skillReqBuilder.append("None.");
        }
        skillRequirementsText = skillReqBuilder.toString();

        // Update progress varbits
        StringBuilder progressBuilder = new StringBuilder("Progress:\n");
        if (questType != null) {
            int[][] varbits = questType.progressVarbits();
            if (varbits != null && varbits.length > 0) {
                for (int[] varbit : varbits) {
                    if (varbit.length == 3) {
                        int currentValue = VarManager.getVarbitValue(varbit[0]);
                        String status = currentValue < varbit[1] ? "Not Started" :
                                currentValue < varbit[2] ? "In Progress" : "Complete";
                        progressBuilder.append("Status: ").append(status)
                                .append(", Varbit ID: ").append(varbit[0])
                                .append(", Start: ").append(varbit[1])
                                .append(", Finish: ").append(varbit[2])
                                .append(", Current: ").append(currentValue).append("\n");
                    } else {
                        progressBuilder.append("Invalid progress varbit format.\n");
                    }
                }
            } else {
                progressBuilder.append("N/A");
            }
        } else {
            progressBuilder.append("N/A");
        }
        progressVarbitsText = progressBuilder.toString();

        // Update dependent quests
        StringBuilder dependentQuestsBuilder = new StringBuilder("Dependent Quests:\n");
        if (questType != null) {
            int[] dependQuests = questType.dependentQuests();
            if (dependQuests != null && dependQuests.length > 0) {
                for (int dependQuestId : dependQuests) {
                    QuestType dependentQuestType = ConfigManager.getQuestType(dependQuestId);
                    if (dependentQuestType != null) {
                        boolean isDependentComplete = false;

                        // Check completion using varbits
                        int[][] varbits = dependentQuestType.progressVarbits();
                        if (varbits != null && varbits.length > 0) {
                            int[] varbit = varbits[0];
                            isDependentComplete = VarManager.getVarbitValue(varbit[0]) >= varbit[2];
                        } else {
                            // Check completion using varps if varbits are not defined
                            int[][] varps = dependentQuestType.progressVarps();
                            if (varps != null && varps.length > 0) {
                                int[] varp = varps[0];
                                if (varp.length == 3) {
                                    int currentVarpValue = VarManager.getVarpValue(varp[0]);
                                    isDependentComplete = currentVarpValue >= varp[2];
                                }
                            }
                        }

                        // Add only the quest name to the builder
                        dependentQuestsBuilder.append(dependentQuestType.name());
                        if (isDependentComplete) {
                            dependentQuestsBuilder.append(" (Complete)");
                        } else {
                            dependentQuestsBuilder.append(" (Incomplete)");
                        }
                        dependentQuestsBuilder.append("\n");
                    } else {
                        dependentQuestsBuilder.append("(Unknown Quest)").append("\n");
                    }
                }
            } else {
                dependentQuestsBuilder.append("None.");
            }
        } else {
            dependentQuestsBuilder.append("N/A");
        }
        dependentQuestsText = dependentQuestsBuilder.toString();


        // Update special instructions
        StringBuilder instructionsBuilder = new StringBuilder("Special Instructions:\n");
        for (QuestInstruction instruction : QuestInstruction.values()) {
            if (instruction.getQuest() == script.currentQuest) {
                instructionsBuilder.append(instruction.getText());
                break;
            }
        }
        if (instructionsBuilder.toString().equals("Special Instructions:\n")) {
            instructionsBuilder.append("None.");
        }
        specialInstructionsText = instructionsBuilder.toString();
    }

    private void dependQuest(){
        QuestType questType = ConfigManager.getQuestType(script.currentQuest.getQuestId());

        ImGui.Text("Dependent Quests:");
        if (questType != null) {
            int[] dependQuests = questType.dependentQuests();
            if (dependQuests != null && dependQuests.length > 0) {
                for (int dependQuestId : dependQuests) {
                    QuestType dependentQuestType = ConfigManager.getQuestType(dependQuestId);
                    if (dependentQuestType != null) {
                        boolean isDependentComplete = false;

                        // Check completion using varbits
                        int[][] varbits = dependentQuestType.progressVarbits();
                        if (varbits != null && varbits.length > 0) {
                            int[] varbit = varbits[0];
                            isDependentComplete = VarManager.getVarbitValue(varbit[0]) >= varbit[2];
                        } else {
                            // Check completion using varps if varbits are not defined
                            int[][] varps = dependentQuestType.progressVarps();
                            if (varps != null && varps.length > 0) {
                                int[] varp = varps[0];
                                if (varp.length == 3) {
                                    int currentVarpValue = VarManager.getVarpValue(varp[0]);
                                    isDependentComplete = currentVarpValue >= varp[2];
                                }
                            }
                        }

                        // Push color based on completion status
                        if (isDependentComplete) {
                            ImGui.PushStyleColor(0, 0.0f, 1.0f, 0.0f, 1.0f); // Green for complete
                        } else {
                            ImGui.PushStyleColor(0, 1.0f, 0.0f, 0.0f, 1.0f); // Red for incomplete
                        }

                        // Render quest name as selectable
                        if (ImGui.Selectable(dependentQuestType.name() + (isDependentComplete ? " (Complete)" : " (Incomplete)"), false,0)) {
                            Quest dependentQuest = Quest.getByQuestId(dependentQuestType.getId());
                            if (dependentQuest != null) {
                                script.currentQuest = dependentQuest;
                                updateQuestInfo(); // Refresh quest details after switching
                                forceScroll = true;//Left side scroll to new quest
                            }
                        }

                        // Pop color after rendering
                        ImGui.PopStyleColor();
                    } else {
                        ImGui.Text("(Unknown Quest)");
                    }
                }
            } else {
                ImGui.Text("None");
            }
        } else {
            ImGui.Text("N/A");
        }
    }

}