package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.util.DataBaseManager;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.entity.Player;

public class SetReward {
        private static SetReward ourInstance = new SetReward();

        private SetReward() {
        }

        public static SetReward getInstance() {
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
                                        message.append(args[i]);
                                }
                                DataBaseManager
                                        .setReward(achievementName,
                                                message.toString());
                                player.sendMessage(Msg.Prefix + Msg.setValue
                                        .toString()
                                        .replace("$value", "Reward"));
                        }
                } else {
                        player.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "setreward <nomAchievement> <recompense>"));
                }
        }
}
