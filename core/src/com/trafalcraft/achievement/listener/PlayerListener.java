package com.trafalcraft.achievement.listener;

import com.trafalcraft.achievement.Main;
import com.trafalcraft.achievement.api.AchievementApi;
import com.trafalcraft.achievement.api.Rewards;
import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.cache.player.ManageCache;
import com.trafalcraft.achievement.util.DataBaseManager;
import com.trafalcraft.achievement.util.GUI;
import com.trafalcraft.achievement.util.Msg;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerListener implements Listener {

        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent e) {
                final Player p = e.getPlayer();
                Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
                        try {
                                ManageCache.addPlayer(p, new String[0][0]);
                                DataBaseManager.savePlayer(p);
                                DataBaseManager.getPlayerAchievements(p);
                                for (Rewards rewards : AchievementApi.getAllCategorie()) {
                                        try {
                                                rewards.playerJoinChecKStats(p);
                                        } catch (Exception ignored) {

                                        }
                                }
                        } catch (Exception e1) {
                                e1.printStackTrace();
                        }
                });

                //DataBaseManager.getPlayerAchievements(e.getPlayer());
        }

        @EventHandler
        public void onPlayerLeave(PlayerQuitEvent e) {
                DataBaseManager.savePlayer(e.getPlayer());
                ManageCache.removePlayer(e.getPlayer());
        }

        @EventHandler(priority = EventPriority.HIGH)
        void onInventoryDrag(InventoryDragEvent e) {
                if (e.getInventory().getTitle().contains("§r§9Achievements") ||
                        e.getInventory().getTitle().contains("§r§4Achievements") || e.getInventory().getTitle()
                        .contains("§r§4Récompense>")) {
                        e.setCancelled(true);
                }
        }

        @EventHandler(priority = EventPriority.LOW)
        public void oninventoryClick(InventoryClickEvent e) {
                if (e.getInventory().getTitle().contains("§r§4Achievements")) {
                        if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR
                                && e.getRawSlot() < (9 * 6) - 1) {
                                if (e.isLeftClick()) {
                                        String[] info, info2;
                                        info = ManageCache.getCache((Player) e.getWhoClicked())
                                                .getAchievementData(e.getCurrentItem().getItemMeta().getDisplayName());
                                        info2 = AchievementList.getAchievementDataByName(
                                                e.getCurrentItem().getItemMeta().getDisplayName());
                                        e.getWhoClicked().sendMessage("");
                                        e.getWhoClicked().sendMessage("");
                                        e.getWhoClicked().sendMessage(Msg.getAchievementProgressAsText(
                                                e.getCurrentItem().getItemMeta().getDisplayName(), info, info2));
                                        e.getWhoClicked().closeInventory();
                                }
                        }
                        e.setCancelled(true);
                } else if (e.getInventory().getTitle().contains("§r§4Récompense>")) {
                        if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR
                                && e.getRawSlot() < (9 * 6) - 1) {
                                if (e.isLeftClick()) {
                                        ItemStack item = e.getCurrentItem();
                                        ItemMeta meta = item.getItemMeta();
                                        String[] info = AchievementList.getAchievementDataByName(meta.getDisplayName());
                                        try {
                                                Boolean reward = AchievementApi.getCategorie(info[7])
                                                        .giveReward((Player) e.getWhoClicked(), meta.getDisplayName());
                                                if (reward) {
                                                        info = ManageCache.getCache((Player) e.getWhoClicked())
                                                                .getAchievementData(meta.getDisplayName());
                                                        ManageCache.getCache((Player) e.getWhoClicked())
                                                                .modifyAchievement(info[0], info[1], true, true);
                                                        //TODO ajouter aux Msg
                                                        e.getWhoClicked()
                                                                .sendMessage(Msg.Prefix + Msg.rewardTaken.toString());
                                                }
                                        } catch (NullPointerException e2) {
                                                e.getWhoClicked().sendMessage(Msg.Prefix + Msg.rewardError.toString());
                                                e2.printStackTrace();
                                        }
                                        e.getWhoClicked().closeInventory();
                                } else {
                                        e.setCancelled(true);
                                }
                        } else {
                                e.setCancelled(true);
                        }
                } else if (e.getInventory().getTitle().equals("§r§9Achievements")) {
                        if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR
                                && e.getRawSlot() < (9 * 3) - 1) {
                                if (e.isLeftClick()) {
                                        if (e.getCurrentItem().getType() == Material.BOOK) {
                                                GUI.opengui((Player) e.getWhoClicked(), null);
                                        } else if (e.getCurrentItem().getType() == Material.ENCHANTED_BOOK) {
                                                GUI.openrewardgui((Player) e.getWhoClicked(), "survie");
                                        }
                                } else {
                                        e.setCancelled(true);
                                }
                        } else {
                                e.setCancelled(true);
                        }
                }
        }

        @EventHandler
        public void onPlayerInteractWithNpc(NPCRightClickEvent e) {
                if (e.getNPC().getName().equals("§r§aAchievements")) {
                        Inventory inv = Bukkit.createInventory(e.getClicker(), 27, "§r§9Achievements");
                        ItemStack item = new ItemStack(Material.BOOK);
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName("§2Achievements");
                        item.setItemMeta(meta);
                        inv.setItem(12, item);
                        item = new ItemStack(Material.ENCHANTED_BOOK);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§4Récompences");
                        item.setItemMeta(meta);
                        inv.setItem(14, item);
                        e.getClicker().openInventory(inv);
                }
        }

        //----------------------------------------------------------------------------------------------------------------------------------------------------
        //achievement Trafal
	/*@EventHandler
	public void onPlayerEat(PlayerItemConsumeEvent e){
		System.out.println("test0");
		if(e.getItem().getType() == Material.APPLE){
			System.out.println(ManageCache.getAllCache());
			String[] info = ManageCache.getCache(e.getPlayer()).getAchievementData("test");
			System.out.println("test1");
			if(info != null){
				System.out.println("test2");
				boolean success = Boolean.valueOf(info[2].toLowerCase());
				if(!success){
					int progress = Integer.parseInt(info[1]);
					if((progress+1)>=3){
						ManageCache.getCache(e.getPlayer()).modifyAchievement("test", "3", true);
					}else{
						ManageCache.getCache(e.getPlayer()).modifyAchievement("test", progress+1+"", false);
					}
				}
				
				//ManageCache.getCache(e.getPlayer()).modifyAchievement("test", , success);
			}else{
				System.out.println("test3");
				ManageCache.getCache(e.getPlayer()).addAchievement("test", 1+"", false);
			}
			//ManageCache.getCache(e.getPlayer()).modifyAchievement(18, progress, success);
		}
	}*/

}
	
