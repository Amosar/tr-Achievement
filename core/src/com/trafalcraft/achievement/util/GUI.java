package com.trafalcraft.achievement.util;

import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.cache.player.ManageCache;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUI {

        public static void opengui(Player p, String achievementName) {
                Inventory inv;
                ArrayList<ItemStack> vert = new ArrayList<>();
                ArrayList<ItemStack> orange = new ArrayList<>();
                ArrayList<ItemStack> rouge = new ArrayList<>();
                if (achievementName != null) {
                        inv = Bukkit.createInventory(p, 6 * 9, "§r§4Achievements>" + achievementName.replace("_", " "));
                        String[] achievements;
                        if (achievementName.contains(",")) {
                                achievements = achievementName.split(",");
                        } else {
                                achievements = new String[1];
                                achievements[0] = achievementName;
                        }
                        for (String achievement : achievements) {
                                for (int i = 0; i < AchievementList.getAllAchievement().length; i++) {
                                        System.out.println(
                                                AchievementList.getAllAchievement()[i][7] + ">" + achievementName);
                                        if (AchievementList.getAllAchievement()[i][7]
                                                .equalsIgnoreCase(achievement)) {
                                                String[] achievementInfo = AchievementList.getAllAchievement()[i];
                                                String[] playerAchievementInfo = ManageCache.getCache(p)
                                                        .getAchievementData(achievementInfo[1]);

                                                ItemStack item = new ItemStack(Material.WOOL);
                                                ItemMeta meta = item.getItemMeta();
                                                meta.setDisplayName(achievementInfo[1]);
                                                meta.setLore(Msg.getLoreAchievementGui(achievementInfo,
                                                        playerAchievementInfo));
                                                item.setItemMeta(meta);
                                                if (achievementInfo[6].length() > 0) {
                                                        if (ManageCache.getCache(p)
                                                                .getAchievementData(achievementInfo[6]) == null) {
                                                                return;
                                                        } else {
                                                                if (ManageCache.getCache(p)
                                                                        .getAchievementData(achievementInfo[6])[2]
                                                                        .equalsIgnoreCase("false")) {
                                                                        return;
                                                                }
                                                        }
                                                }

                                                if (playerAchievementInfo != null) {
                                                        System.out.println(playerAchievementInfo[2]);
                                                        if (playerAchievementInfo[2].equalsIgnoreCase("True")) {
                                                                item.setDurability((short) 5);
                                                                vert.add(item);
                                                        } else {
                                                                if (achievementInfo[4].equalsIgnoreCase("False")) {
                                                                        item.setDurability((short) 1);
                                                                        orange.add(item);
                                                                }
                                                        }
                                                } else {
                                                        if (achievementInfo[4].equalsIgnoreCase("False")) {
                                                                item.setDurability((short) 14);
                                                                rouge.add(item);
                                                        }

                                                }
                                        }
                                }
                        }
                } else {
                        inv = Bukkit.createInventory(p, 6 * 9, "§r§4Achievements");
                        for (int i = 0; i < AchievementList.getAllAchievement().length; i++) {
                                String[] achievementInfo = AchievementList.getAllAchievement()[i];
                                String[] playerAchievementInfo = ManageCache.getCache(p)
                                        .getAchievementData(achievementInfo[1]);
                                ItemStack item = new ItemStack(Material.WOOL);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(achievementInfo[1]);
                                meta.setLore(Msg.getLoreAchievementGui(achievementInfo, playerAchievementInfo));
                                item.setItemMeta(meta);
                                if (achievementInfo[6].length() > 0) {
                                        if (ManageCache.getCache(p).getAchievementData(achievementInfo[6]) == null) {
                                                //return
                                        } else {
                                                if (ManageCache.getCache(p).getAchievementData(achievementInfo[6])[2]
                                                        .equalsIgnoreCase("false")) {
                                                        //return;
                                                } else {
                                                        if (playerAchievementInfo != null) {
                                                                if (playerAchievementInfo[2].equalsIgnoreCase("True")) {
                                                                        item.setDurability((short) 5);
                                                                        vert.add(item);
                                                                } else {
                                                                        item.setDurability((short) 1);
                                                                        orange.add(item);
                                                                }
                                                        } else {
                                                                if (achievementInfo[4].equalsIgnoreCase("False")) {
                                                                        item.setDurability((short) 14);
                                                                        rouge.add(item);
                                                                }

                                                        }
                                                }
                                        }
                                } else {
                                        if (playerAchievementInfo != null) {
                                                if (playerAchievementInfo[2].equalsIgnoreCase("True")) {
                                                        item.setDurability((short) 5);
                                                        vert.add(item);
                                                } else {
                                                        if (achievementInfo[4].equalsIgnoreCase("False")) {
                                                                item.setDurability((short) 1);
                                                                orange.add(item);
                                                        }
                                                }
                                        } else {
                                                if (achievementInfo[4].equalsIgnoreCase("False")) {
                                                        item.setDurability((short) 14);
                                                        rouge.add(item);
                                                }

                                        }

                                }
                        }
                }
                for (ItemStack item : vert) {
                        inv.addItem(item);
                }
                for (ItemStack item : orange) {
                        inv.addItem(item);
                }
                for (ItemStack item : rouge) {
                        inv.addItem(item);
                }
                p.openInventory(inv);
        }

        public static void openrewardgui(Player p, String achievementName) {
                Inventory inv;
                String[] achievements;
                if (achievementName.contains(",")) {
                        achievements = achievementName.split(",");
                } else {
                        achievements = new String[1];
                        achievements[0] = achievementName;
                }
                inv = Bukkit.createInventory(p, 6 * 9, "§r§4Récompense>" + achievements[0].replace("_", " "));
                for (String achievement : achievements) {
                        for (int i = 0; i < AchievementList.getAllAchievement().length; i++) {
                                if (AchievementList.getAllAchievement()[i][7].equalsIgnoreCase(achievement)) {
                                        String[] info = AchievementList.getAllAchievement()[i];
                                        String[] info2 = ManageCache.getCache(p).getAchievementData(info[1]);
                                        if (info2 != null) {
                                                if (info2[3].equalsIgnoreCase("false")) {
                                                        if (info2[2].equalsIgnoreCase("true")) {
                                                                ItemStack item = new ItemStack(Material.WOOL);
                                                                item.setDurability((short) 14);
                                                                ItemMeta meta = item.getItemMeta();
                                                                meta.setDisplayName(
                                                                        AchievementList.getAllAchievement()[i][1]);
                                                                List<String> lore = new ArrayList<>();
                                                                lore.add(
                                                                        "Clique gauche pour récuperer la récompense"); //TODO Ajouter au Msg
                                                                //lore.add("§6Récompense: §a"+AchievementList.getAllAchievement()[i][5]);
                                                                String temp[] = info[5].split("\\{ligne\\}");
                                                                for (int k = 0; k < temp.length; k++) {
                                                                        if (k == 0) {
                                                                                lore.add("§6Récompense: §a" + temp[k]);
                                                                        } else {
                                                                                lore.add("§a" + temp[k]);
                                                                        }
                                                                }
                                                                meta.setLore(lore);
                                                                item.setItemMeta(meta);
                                                                inv.addItem(item);
                                                        }
                                                }
                                        }
                                }
                        }
                }
                p.openInventory(inv);
        }
}
