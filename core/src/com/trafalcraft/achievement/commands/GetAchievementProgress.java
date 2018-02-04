package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.cache.player.ManageCache;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GetAchievementProgress {
        private static GetAchievementProgress ourInstance = new GetAchievementProgress();

        private GetAchievementProgress() {
        }

        public static GetAchievementProgress getInstance() {
                return ourInstance;
        }

        public void performCMD(Player player, String... args) {
                if (args.length > 1) {
                        StringBuilder message = new StringBuilder();
                        String[] achievementData;
                        String[] AchievementInfo;
                        if (args.length > 2) {
                                Player player2 = Bukkit.getPlayer(args[2]);
                                if (player2 != null) {
                                        achievementData = ManageCache.getCache(player2)
                                                .getAchievementData(
                                                        args[1]);
                                        AchievementInfo = AchievementList
                                                .getAchievementDataByName(
                                                        args[1]);
                                        message = new StringBuilder(
                                                "§a-=§6Info sur l'achievement \""
                                                        + args[1]
                                                        + "\"§a=-\n");
                                        message.append("§aProgrès: §6")
                                                .append(achievementData[1])
                                                .append("/")
                                                .append(AchievementInfo[3])
                                                .append("\n");
                                        message.append("§aCaché: §6")
                                                .append(achievementData[2])
                                                .append("\n");

                                        player.sendMessage(
                                                message.toString());
                                        return;
                                } else {
                                        //TODO Manage disconnected player
                                        player.sendMessage(
                                                "Le plugin ne gère pas encore les joueurs déconnectés");
                                }
                        }
                        achievementData = ManageCache.getCache(player)
                                .getAchievementData(args[1]);
                        AchievementInfo = AchievementList
                                .getAchievementDataByName(args[1]);
                        message.append("§a-=§6Info sur l'achievement \"" + args[1] + "\"§a=-\n");
                        message.append("§aProgrès: §6").append(achievementData[1])
                                .append("/").append(AchievementInfo[3])
                                .append("\n");
                        message.append("§aCaché: §6").append(achievementData[1])
                                .append("\n");
                        player.sendMessage(message.toString());
                } else {
                        player.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "getachievementprogress <nomAchievement> (player)"));
                }
        }
}
