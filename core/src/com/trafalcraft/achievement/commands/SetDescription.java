package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.util.DataBaseManager;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.entity.Player;

public class SetDescription {
        private static SetDescription ourInstance = new SetDescription();

        private SetDescription() {
        }

        public static SetDescription getInstance() {
                return ourInstance;
        }

        public void performCMD(Player player, String... args) {
                if (args.length > 2) {
                        String achievementName = args[1];
                        if (AchievementList
                                .getAchievementIdByName(achievementName)
                                == null) {
                                player.sendMessage(Msg.Prefix
                                        + Msg.achievement_not_exist
                                        .toString()
                                        .replace("$achievement",
                                                achievementName));
                        } else {
                                StringBuilder message = new StringBuilder();
                                for (int i = 2; i < args.length; i++) {
                                        message.append(args[i])
                                                .append(" ");
                                }
                                DataBaseManager.setDescription(achievementName,
                                        message.toString());
                                player.sendMessage(Msg.Prefix + Msg.setValue
                                        .toString().replace("$value",
                                                "Description"));
                        }
                } else {
                        player.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "setdescription <nomAchievement> <description>"));
                }
        }
}
