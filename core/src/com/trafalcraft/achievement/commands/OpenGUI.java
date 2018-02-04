package com.trafalcraft.achievement.commands;

import com.trafalcraft.achievement.util.GUI;
import org.bukkit.entity.Player;

public class OpenGUI {
        private static OpenGUI ourInstance = new OpenGUI();

        private OpenGUI() {
        }

        public static OpenGUI getInstance() {
                return ourInstance;
        }

        public void performCMD(Player player, String... args) {
                if (args.length > 1) {
                        String achievementName = args[1];
                        GUI.opengui(player, achievementName);
                } else {
                        GUI.opengui(player, null);
                }
        }
}
