package com.trafalcraft.achievement;

import com.trafalcraft.achievement.cache.player.ManageCache;
import com.trafalcraft.achievement.commands.*;
import com.trafalcraft.achievement.listener.PlayerListener;
import com.trafalcraft.achievement.util.DataBaseManager;
import com.trafalcraft.achievement.util.Msg;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends JavaPlugin {

        private static JavaPlugin plugin;

        private static DataBaseManager manager;

        /**
         * Get the instance of the plugin
         */
        public static JavaPlugin getPlugin() {
                return plugin;
        }

        /**
         * Get the database connection
         *
         * @return Connection
         * Return the Connection Object
         */
        public static Connection getMyDatabase() {
                return manager.getConnection();
        }

        /**
         * <b>Load the plugin and it's variable</b><br />
         * Load PlayerListener<br />
         * Load and initialize the database<br />
         */
        public void onEnable() {
                plugin = this;
                plugin.getConfig().options().copyDefaults(true);
                saveConfig();
                plugin.reloadConfig();

                Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
                try {
                        File f = new File(getDataFolder() + "/messages.yml");
                        if (!f.exists()) {
                                this.saveResource("messages.yml", false);
                        }
                        Msg.load();
                } catch (YAMLException e) {
                        this.getLogger().warning("An error as occurred in the config.yml please fix it!");
                        e.printStackTrace();
                } catch (InvalidConfigurationException e) {
                        e.printStackTrace();
                }

                // Connect to the database
                manager = new DataBaseManager(this,
                        "jdbc:mysql://" + plugin.getConfig().get("database.host") + ":" + plugin.getConfig()
                                .getInt("database.port") + "/" + plugin.getConfig().get("database.db")
                                + "?useUnicode=true&characterEncoding=utf8",
                        plugin.getConfig().get("database.user").toString(),
                        plugin.getConfig().get("database.pass").toString());
                Connection db = manager.getConnection();
                if (db == null) {
                        getLogger()
                                .severe("tr-achievement is disabling. Please check your database settings in your config.yml");
                        return;
                }
                try {
                        manager.initDatabase(db);
                } catch (SQLException e) {
                        getLogger().severe("Unable to connect to the database. Disabling...");
                        e.printStackTrace();
                        return;
                }

                DataBaseManager.getAllAchievements();
                for (final Player p : Bukkit.getOnlinePlayers()) {
                        Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
                                try {
                                        ManageCache.addPlayer(p, new String[0][0]);
                                        DataBaseManager.savePlayer(p);
                                        DataBaseManager.getPlayerAchievements(p);
                                } catch (Exception e1) {
                                        e1.printStackTrace();
                                }
                        });
                }

        }

        /**
         * Do nothing actually
         */
        public void onDisable() {

        }

        /**
         * Manage the commands
         */
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
                if (cmd.getName().equalsIgnoreCase("achievement")) {
                        if (sender instanceof Player) {
                                Player player = (Player) sender;
                                if (player.isOp()) {
                                        if (args.length > 0) {
                                                switch (args[0].toLowerCase()) {
                                                        case "addachievement":
                                                                AddAchievement.getInstance().performCMD(player, args);
                                                                break;
                                                        case "list":
                                                                List.getInstance().performCMD(player, args);
                                                                break;
                                                        case "removeachievement":
                                                                RemoveAchievement.getInstance()
                                                                        .performCMD(player, args);
                                                                break;
                                                        case "setdescription":
                                                                SetDescription.getInstance().performCMD(player, args);
                                                                break;
                                                        case "setsuccessvalue":
                                                                SetSuccessValue.getInstance().performCMD(player, args);
                                                                break;
                                                        case "sethidevalue":
                                                                SetHideValue.getInstance().performCMD(player, args);
                                                                break;
                                                        case "setreward":
                                                                SetReward.getInstance().performCMD(player, args);
                                                                break;
                                                        case "setrequiert":
                                                                SetRequiert.getInstance().performCMD(player, args);
                                                                break;
                                                        case "setcategory":
                                                                SetCategory.getInstance().performCMD(player, args);
                                                                break;
                                                        case "getachievementdata":
                                                                GetAchievementData.getInstance()
                                                                        .performCMD(player, args);
                                                                break;
                                                        case "getplayerachievements":
                                                                GetPlayerAchievements.getInstance()
                                                                        .performCMD(player, args);
                                                                break;
                                                        case "getachievementprogress":
                                                                GetAchievementProgress.getInstance()
                                                                        .performCMD(player, args);
                                                                break;
                                                        case "winachievement":
                                                                WinAchievement.getInstance().performCMD(player, args);
                                                                break;
                                                        case "opengui":
                                                                OpenGUI.getInstance().performCMD(player, args);
                                                                break;
                                                        case "openrewardgui":
                                                                OpenRewardGUI.getInstance().performCMD(player, args);
                                                                break;
                                                        default:
                                                                Msg.sendHelp(player);
                                                                break;
                                                }
                                        } else {
                                                Msg.sendHelp(player);
                                        }
                                } else {
                                        player.sendMessage(Msg.ERREUR.toString() + Msg.NO_PERMISSIONS);
                                }
                        } else {
                                sender.sendMessage("Console command are not supported");
                        }
                }

                return false;
        }
}
