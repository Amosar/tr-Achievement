package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.util.DataBaseManager;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.entity.Player;

public class SetRequiert {
        private static SetRequiert ourInstance = new SetRequiert();

        private SetRequiert() {
        }

        public static SetRequiert getInstance() {
                return ourInstance;
        }

        public void performCMD(Player player, String... args) {
                if (args.length > 2) {
                        String achievementName = args[1];
                        String requirementValue = args[2];
                        if (AchievementList
                                .getAchievementIdByName(achievementName)
                                == null) {
                                player.sendMessage(Msg.Prefix
                                        + Msg.achievement_not_exist
                                        .toString()
                                        .replace("$achievement",
                                                achievementName));
                        } else {
                                DataBaseManager
                                        .setRequire(achievementName, requirementValue);
                                player.sendMessage(Msg.Prefix + Msg.setValue
                                        .toString().replace("$value",
                                                "RequiertValue"));
                        }
                } else {
                        player.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "setRequire <nomAchievement> <requiert>"));
                }
        }
}
