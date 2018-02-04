package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.entity.Player;

public class GetAchievementData {
        private static GetAchievementData ourInstance = new GetAchievementData();

        private GetAchievementData() {
        }

        public static GetAchievementData getInstance() {
                return ourInstance;
        }

        public void performCMD(Player player, String... args) {
                if (args.length > 1) {
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
                                String[] info;
                                info = AchievementList
                                        .getAchievementDataByName(
                                                achievementName);
                                message.append("§a-=§6Info sur l'achievement \"").append(achievementName)
                                        .append("\"§a=-\n");
                                message.append("§aId: §6")
                                        .append(info[0]).append("\n");
                                message.append("§aNom: §6")
                                        .append(info[1]).append("\n");
                                message.append("§aDescription: §6")
                                        .append(info[2]).append("\n");
                                message.append("§aValeur de succès: §6")
                                        .append(info[3]).append("\n");
                                message.append("§aestCaché?: §6")
                                        .append(info[4]).append("\n");
                                message.append("§aRécompense: §6")
                                        .append(info[5]).append("\n");
                                message.append("§aRequiert: §6")
                                        .append(info[6]).append("\n");
                                player.sendMessage(message.toString());
                        }
                } else {
                        player.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "getachievementdata <nomAchievement>"));
                }
        }
}
