package com.trafalcraft.achievement.api;

import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.HashMap;

public class AchievementApi {

        private static HashMap<String, Rewards> pluginList = new HashMap<>();

        public static void registerReward(String categorie, Rewards plugin) {
                pluginList.put(categorie, plugin);
        }

        public static Collection<Rewards> getAllCategorie() {
                return pluginList.values();
        }

        public static void unregisterPlugin(Rewards plugin) {
                if (pluginList.containsValue(plugin)) {
                        pluginList.remove(plugin);
                } else {
                        Bukkit.getLogger().warning("achievement> Error, this plugin is not register");
                }
        }

        public static Rewards getCategorie(String categorie) {
                return pluginList.get(categorie);
        }
}
