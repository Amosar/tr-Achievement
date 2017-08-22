package com.trafalcraft.Achievement.Api;

import org.bukkit.entity.Player;

public interface Rewards {
	
	public boolean giveReward(Player p, String achievement);
	
	public void playerJoinChecKStats(Player p);
	
}
