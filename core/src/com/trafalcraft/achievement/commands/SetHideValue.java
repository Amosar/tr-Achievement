package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.util.DataBaseManager;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.entity.Player;

public class SetHideValue {
        private static SetHideValue ourInstance = new SetHideValue();

        private SetHideValue() {
        }

        public static SetHideValue getInstance() {
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
                                if (args[2].equalsIgnoreCase("false")) {
                                        DataBaseManager
                                                .setHideValue(achievementName,
                                                        false);
                                } else {
                                        DataBaseManager
                                                .setHideValue(achievementName,
                                                        true);
                                }
                                player.sendMessage(Msg.Prefix + Msg.setValue
                                        .toString().replace("$value",
                                                "HideValue"));
                        }
                } else {
                        player.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "sethidevalue <nomAchievement> <true/false>"));
                }
        }
}
