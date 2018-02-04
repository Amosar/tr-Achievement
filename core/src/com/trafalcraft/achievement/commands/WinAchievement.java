package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.cache.player.ManageCache;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class WinAchievement {
        private static WinAchievement ourInstance = new WinAchievement();

        private WinAchievement() {
        }

        public static WinAchievement getInstance() {
                return ourInstance;
        }

        public void performCMD(Player p, String... args) {
                if (args.length > 3) {
                        String[] achievementInfo;
                        String[] achievementPlayerData;
                        String achievementName = args[1];
                        String playerName = args[2];
                        String newAchievementProgress = args[3];
                        achievementInfo = AchievementList
                                .getAchievementDataByName(achievementName);
                        if (achievementInfo != null) {
                                achievementPlayerData = ManageCache.getCache(
                                        Bukkit.getPlayer(playerName))
                                        .getAchievementData(achievementName);
                                if (Bukkit.getPlayer(playerName) != null) {
                                        if (achievementPlayerData == null) {
                                                ManageCache.getCache(
                                                        Bukkit.getPlayer(
                                                                playerName))
                                                        .addAchievement(
                                                                achievementName,
                                                                newAchievementProgress,
                                                                true,
                                                                true); //TODO found reward
                                                Msg.sendSuccess(
                                                        Bukkit.getPlayer(
                                                                playerName),
                                                        achievementInfo[1].replace(
                                                                "_",
                                                                " "),
                                                        achievementInfo[2],
                                                        Boolean.valueOf(
                                                                achievementInfo[4]));
                                        }
                                } else {
                                        p.sendMessage(Msg.ERREUR
                                                + "Ce joueur n'est pas connecté");
                                }
                        } else {
                                p.sendMessage(Msg.ERREUR
                                        + "Cette achievement n'a pas été trouvé");
                        }
                } else {
                        p.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "setAchievement <nomAchievement> <player> <valeur>"));
                }
        }
}
