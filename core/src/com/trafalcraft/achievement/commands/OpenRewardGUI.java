package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.util.GUI;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.entity.Player;

public class OpenRewardGUI {
        private static OpenRewardGUI ourInstance = new OpenRewardGUI();

        private OpenRewardGUI() {
        }

        public static OpenRewardGUI getInstance() {
                return ourInstance;
        }

        public void performCMD(Player player, String... args) {
                if (args.length > 1) {
                        String achievementName = args[1];
                        GUI.openrewardgui(player, achievementName);
                } else {
                        player.sendMessage(Msg.Command_Use.toString()
                                .replace("$commande",
                                        "openRewardGui <category> (player)"));
                }
        }
}
