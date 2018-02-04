package com.trafalcraft.achievement.api.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RewardUtil {

        public static boolean checkInv(Player p, int numberOfNeededSlots) {
                int numberOfAvailableSlot = 0;
                for (ItemStack item : p.getInventory().getContents()) {
                        if (item == null) {
                                numberOfAvailableSlot++;
                        }
                }
                return numberOfNeededSlots <= numberOfAvailableSlot;
        }
}
