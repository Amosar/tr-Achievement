package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.util.DataBaseManager;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.command.CommandSender;

public class SetCategory {
        private static SetCategory ourInstance = new SetCategory();

        private SetCategory() {
        }

        public static SetCategory getInstance() {
                return ourInstance;
        }

        public void performCMD(CommandSender sender, String... args) {
                if (args.length > 2) {
                        String achievementName = args[1];
                        String categoryName = args[2];
                        if (AchievementList
                                .getAchievementIdByName(achievementName)
                                == null) {
                                sender.sendMessage(Msg.Prefix
                                        + Msg.achievement_not_exist
                                        .toString()
                                        .replace("$achievement",
                                                achievementName));
                        } else {
                                DataBaseManager
                                        .setCategory(achievementName, categoryName);
                                sender.sendMessage(Msg.Prefix + Msg.setValue
                                        .toString().replace("$value",
                                                "Category"));
                        }
                } else {
                        sender.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "setCategory <nomAchievement> <category>"));
                }
        }
}
