package com.trafalcraft.achievement.cache.player;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class ManageCache {
        private static final Map<UUID, Cache> listPlayer = Maps.newHashMap();

        public static void addPlayer(Player p, String[][] achievements) {
                if (!listPlayer.containsKey(p.getUniqueId())) {
                        listPlayer.put(p.getUniqueId(), new Cache(achievements));
                }
        }

        public static boolean containsPlayer(Player p) {
                return listPlayer.containsKey(p.getUniqueId());
        }

        public static void removePlayer(Player p) {
                if (listPlayer.containsKey(p.getUniqueId())) {
                        listPlayer.remove(p.getUniqueId());
                }
        }

        public static Map<UUID, Cache> listCache() {
                return listPlayer;
        }

        public static Collection<Cache> getAllCache() {
                return listPlayer.values();
        }

        public static Cache getCache(Player p) {
                return listPlayer.get(p.getUniqueId());
        }

        public static void clear() {
                listPlayer.clear();
        }
}
