package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.player.ManageCache;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GetPlayerAchievements {
        private static GetPlayerAchievements ourInstance = new GetPlayerAchievements();

        private GetPlayerAchievements() {
        }

        public static GetPlayerAchievements getInstance() {
                return ourInstance;
        }

        //TODO Add HoverClick and clickEvent
        public void performCMD(Player player, String... args) {
                if (args.length > 0) {
                        StringBuilder message = new StringBuilder();
                        String[] info;
                        if (args.length > 1) {
                                Player player2;
                                player2 = Bukkit.getPlayer(args[1]);
                                if (player2 != null) {
                                        info = ManageCache.getCache(player2)
                                                .getAllAchievementName();
                                        message = new StringBuilder();
                                        for (String anInfo : info) {
                                                message.append("§a")
                                                        .append(anInfo)
                                                        .append("§f, ");
                                        }
                                }
                                player.sendMessage(
                                        "PlayerAchievements: " + message
                                                .toString()
                                                .replace("_", " "));
                        } else {
                                info = ManageCache.getCache(player)
                                        .getAllAchievementName();
                                message = new StringBuilder();
                                for (String anInfo : info) {
                                        message.append("§a").append(anInfo)
                                                .append("§f, ");
                                }
                                player.sendMessage("PlayerAchievements: " + message
                                        .toString()
                                        .replace("_", " "));
                        }
                } else {
                        player.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "getplayerachievements <player>"));
                }
        }
}
