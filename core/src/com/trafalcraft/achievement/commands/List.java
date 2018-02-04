package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import org.bukkit.entity.Player;

public class List {
        private static List ourInstance = new List();

        private List() {
        }

        public static List getInstance() {
                return ourInstance;
        }

        //TODO add HoverClick and clickEvent
        public void performCMD(Player player, String... args) {
                StringBuilder achievement = new StringBuilder();
                for (String achievementName : AchievementList.getAllAchievementName()) {
                        achievement.append("§a").append(achievementName).append("§f, ");
                }
                player.sendMessage("AchievementList: " + achievement.toString()
                        .replace("_", " "));
        }
}
