package com.trafalcraft.achievement.api;

import org.bukkit.entity.Player;

public interface Rewards {

        boolean giveReward(Player p, String achievement);

        void playerJoinChecKStats(Player p);

}
