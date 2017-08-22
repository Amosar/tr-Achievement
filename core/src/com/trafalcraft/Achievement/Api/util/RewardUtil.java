package com.trafalcraft.Achievement.Api.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RewardUtil {
	
	public static boolean checkInv(Player p, int placeLibre){
	    int tempPlaceLibre = 0;
        for (ItemStack item : p.getInventory().getContents()) {
            if(item == null) {
            	tempPlaceLibre++;
            }
        }  
        if(placeLibre <= tempPlaceLibre) {
            return true;
        } else {
            return false;
        }
	}
}
