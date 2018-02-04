package com.trafalcraft.achievement.util;

import com.trafalcraft.achievement.Main;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum Msg {

        Prefix("&btr-achievement &9&l> &r&b "),
        ERREUR("&4tr-achievement &l> &r&c "),
        NO_PERMISSIONS("&4Error &9&l> &r&bYou dont have permission to do that!"),
        Command_Use("&4tr-achievement &l> &r&cCommand usage: &6/ach $commande"),
        //Setup
        achievement_exist("The achievement $achievement already exist"),
        achievement_not_exist("The achievement $achievement doesn't exist"),
        achievement_add("The achievement $achievement have been added"),
        achievement_remove("The achievement $achievement have been remove"),
        setValue("$value updated"),
        //Joueur
        rewardTaken("You have receive the reward"),
        rewardError("An error as occurred, please contact the administrator");

        static JavaPlugin plugin = Main.getPlugin();
        private static YamlConfiguration msgFile;
        private String value;

        Msg(String value) {
                this.value = value;
        }

        public static void sendHelp(Player player) {
                player.sendMessage("");
                player.sendMessage("§3§l-------------------AntiRedstoneClock-------------------");
                //sender.sendMessage("§3/ach setup <nom de l'arene> §b- crée l'arène.");
                player.sendMessage("§4-En construction...");
                player.sendMessage("§3------------------------------------------------");
                player.sendMessage("");
        }

        public static void load() {
                File f = new File(Main.getPlugin().getDataFolder() + "/messages.yml");
                msgFile = YamlConfiguration.loadConfiguration(f);
                Prefix.replaceby(msgFile.getString("default.prefix").replace("&", "§"));
                ERREUR.replaceby(msgFile.getString("default.error").replace("&", "§"));
                NO_PERMISSIONS.replaceby(msgFile.getString("default.no_permission").replace("&", "§"));
                Command_Use.replaceby(msgFile.getString("default.command_use").replace("&", "§"));

                achievement_exist.replaceby(msgFile.getString("setup.achievement_exist").replace("&", "§"));
                achievement_not_exist.replaceby(msgFile.getString("setup.achievement_not_exist").replace("&", "§"));
                achievement_add.replaceby(msgFile.getString("setup.achievement_add").replace("&", "§"));
                achievement_remove.replaceby(msgFile.getString("setup.achievement_remove").replace("&", "§"));
                setValue.replaceby(msgFile.getString("setup.setValue").replace("&", "§"));

                rewardTaken.replaceby(msgFile.getString("player.reward_taken").replace("&", "§"));
                rewardError.replaceby(msgFile.getString("player.reward_error").replace("&", "§"));
        }

        public static List<String> getLoreAchievementGui(String[] achievementInfo, String[] playerAchievementInfo) {
                List<String> lore = new ArrayList<>();
                lore.add(achievementInfo[2]);
                if (playerAchievementInfo != null) {
                        lore.add("§aProgress: §6" + playerAchievementInfo[1] + "/" + achievementInfo[3]);
                } else {
                        lore.add("§aProgress: §60/" + achievementInfo[3]);
                }
                lore.add("§6========");
                lore.add("");
                if (achievementInfo[5].length() > 0) {
                        String temp[] = achievementInfo[5].split("\\{ligne\\}");
                        for (int k = 0; k < temp.length; k++) {
                                if (k == 0) {
                                        lore.add("§6Reward: §a" + temp[k]);
                                } else {
                                        lore.add("§a" + temp[k]);
                                }
                        }
                } else {
                        lore.add("§6Reward: §aJust for glory");
                }
                return lore;
        }

        public static String getAchievementProgressAsText(String displayName, String[] playerData,
                String[] achievementData) {
                StringBuilder message = new StringBuilder("\n \n ");
                for (Object o : msgFile.getList("gui.achievement_progress")) {
                        if (o instanceof String) {
                                String line = (String) o;
                                line = line.replace("$achievementName", displayName);
                                line = line.replace("$achievementDescription", achievementData[2]);
                                if (playerData == null) {
                                        line = line.replace("$actualValue", "0");
                                } else {
                                        line = line.replace("$actualValue", playerData[1]);
                                }
                                line = line.replace("$goal", achievementData[3]);
                                line = line.replace("$achievementReward", achievementData[5])
                                        .replace("{line}", " ");
                                message.append(line);
                        }
                }
                return message.toString();
        }

        public static void sendSuccess(Player p, String achievementName, String achievementDescription, boolean hide) {
                StringBuilder message = new StringBuilder();
                for (Object o : msgFile.getList("player.achievement_success_msg")) {
                        if (o instanceof String) {
                                String line = (String) o;
                                line = line.replace("$achievementName", achievementName);
                                line = line.replace("$achievementDescription", achievementDescription);
                                message.append(line);
                        }
                }
                p.sendMessage(message.toString());
                if (msgFile.getBoolean("player.achievement_success_title.enable")) {
                        String title = msgFile.getString("player.achievement_success_title.title");
                        String subtitle = msgFile.getString("player.achievement_success_title.subtitle");
                        p.sendTitle(title, subtitle, 10, 70, 20);
                }
                p.spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation(), 5);
                if (!hide) {

                        TextComponent messageComponent = new TextComponent(
                                msgFile.getString("player.achievement_success_broadcast_msg"));
                        messageComponent.setHoverEvent(new HoverEvent(Action.SHOW_TEXT,
                                new ComponentBuilder(msgFile.getString("player.achievement_success_mouseHover_msg")
                                        .replace("$achievementDescription", achievementDescription)).create()));
                        for (Player p2 : Bukkit.getOnlinePlayers()) {
                                if (!p2.getName().equalsIgnoreCase(p.getName())) {
                                        p2.spigot().sendMessage(messageComponent);
                                }
                        }
                } else {
                        TextComponent messageComponent = new TextComponent(
                                msgFile.getString("player.hidden_achievement_success_broadcast_msg"));
                        messageComponent.setHoverEvent(new HoverEvent(Action.SHOW_TEXT,
                                new ComponentBuilder(msgFile.getString("player.achievement_success_mouseHover_msg")
                                        .replace("$achievementDescription", achievementDescription)).create()));
                        for (Player p2 : Bukkit.getOnlinePlayers()) {
                                if (!p2.getName().equalsIgnoreCase(p.getName())) {
                                        p2.spigot().sendMessage(messageComponent);
                                }
                        }
                }
        }

        public String toString() {
                return value;
        }

        public void replaceby(String value) {
                this.value = value;
        }

}