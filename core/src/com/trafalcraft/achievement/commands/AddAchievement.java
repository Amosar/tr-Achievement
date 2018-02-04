package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.util.DataBaseManager;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.entity.Player;

public class AddAchievement {
        private static AddAchievement ourInstance = new AddAchievement();

        private AddAchievement() {
        }

        public static AddAchievement getInstance() {
                return ourInstance;
        }

        public void performCMD(Player player, String... args) {
                if (args.length > 1) {
                        String achievementName = args[1];
                        if (AchievementList.getAchievementIdByName(achievementName) != null) {
                                player.sendMessage(Msg.Prefix
                                        + Msg.achievement_exist
                                        .toString()
                                        .replace("$achievement",
                                                achievementName));
                        } else {
                                DataBaseManager.addAchievement(achievementName);
                                player.sendMessage(
                                        Msg.Prefix + Msg.achievement_add
                                                .toString()
                                                .replace("$achievement",
                                                        achievementName));
                        }
                } else {
                        player.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "add <nomAchievement>"));
                }
        }
}
