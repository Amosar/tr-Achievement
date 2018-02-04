package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.util.DataBaseManager;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.entity.Player;

public class SetSuccessValue {
        private static SetSuccessValue ourInstance = new SetSuccessValue();

        private SetSuccessValue() {
        }

        public static SetSuccessValue getInstance() {
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
                                DataBaseManager.setSuccessValue(achievementName,
                                        message.toString());
                                player.sendMessage(Msg.Prefix + Msg.setValue
                                        .toString().replace("$value",
                                                "SuccessValue"));
                        }
                } else {
                        player.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "setsuccessvalue <nomAchievement> <successValue>"));
                }
        }
}
