package com.trafalcraft.achievement.util;

import com.trafalcraft.achievement.Main;
import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.cache.player.Cache;
import com.trafalcraft.achievement.cache.player.ManageCache;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {

        private static final String savePlayer =
                "INSERT INTO `" + Main.getPlugin().getConfig().getString("database.prefix")
                        + "users` (UUID, name, lastIP, firstLogin) VALUES(?,?,?,NOW()) ON DUPLICATE KEY UPDATE UUID = VALUES(UUID), lastLogin = NOW()";
        private static final String savePlayerData =
                "INSERT INTO `" + Main.getPlugin().getConfig().getString("database.prefix")
                        + "AchievementProgress` (progress, success, userUUID, idAchievement, rewardTaken) VALUES(?,?,?,?,?) ON DUPLICATE KEY UPDATE progress = VALUES(progress), success = VALUES(success), rewardTaken = VALUES(rewardTaken)";
        private static final String addAchievement =
                "INSERT INTO `" + Main.getPlugin().getConfig().getString("database.prefix")
                        + "Achievement` (name, description, successValue, isHide, Reward, requiert, categorie, points) VALUES(?,?,?,?,?,?,?,?)";
        private static final String selectAllPlayerAchievementSQLStatement =
                "SELECT * FROM `" + Main.getPlugin().getConfig().getString("database.prefix")
                        + "AchievementProgress` where USERUUID = ?";
        private static final String selectAllAchievementSQLStatement =
                "SELECT * FROM `" + Main.getPlugin().getConfig().getString("database.prefix") + "Achievement`";
        private static final String removeAchievementSQLStatement =
                "DELETE FROM `" + Main.getPlugin().getConfig().getString("database.prefix")
                        + "Achievement` WHERE name = ?";
        private static final String achievementValueUpdate =
                "UPDATE `" + Main.getPlugin().getConfig().getString("database.prefix")
                        + "achievement` SET $colonne = ? WHERE name = ?";
        private List<Connection> connections = new ArrayList<>();
        private Main plugin;
        private String dsn;
        private String user;
        private String pass;

        public DataBaseManager(Main plugin, String dsn, String user, String pass) {
                this.plugin = plugin;
                this.dsn = dsn;
                this.user = user;
                this.pass = pass;
        }

        public static boolean containsPlayer(Player p) {

                return false;
        }

        public static void getAllAchievements() {
                try {
                        PreparedStatement st = Main.getMyDatabase().prepareStatement(selectAllAchievementSQLStatement);
                        PreparedStatement st2 = Main.getMyDatabase().prepareStatement("SELECT COUNT(*) FROM `"
                                + Main.getPlugin().getConfig().getString("database.prefix") + "Achievement`");
                        ResultSet rs = st.executeQuery();
                        ResultSet rs2 = st2.executeQuery();
                        rs2.next();
                        String[][] achievementTab = new String[rs2.getInt(1)][9];
                        int i = 0;
                        while (rs.next())
                                if (rs.getInt(1) > 0) {
                                        achievementTab[i][0] = rs.getInt(1) + ""; //idAchievement
                                        achievementTab[i][1] = rs.getString(2);    //name
                                        achievementTab[i][2] = rs.getString(3);    //description
                                        achievementTab[i][3] = rs.getString(4);    //successValue
                                        if (rs.getInt(5) == 0) {
                                                achievementTab[i][4] = "False";
                                        } else {                                    //isHide
                                                achievementTab[i][4] = "True";
                                        }
                                        achievementTab[i][5] = rs.getString(6);    //Reward
                                        achievementTab[i][6] = rs.getString(7);    //Requiert
                                        achievementTab[i][7] = rs.getString(8);    //Categorie
                                        achievementTab[i][8] = rs.getString(9);    //Points
                                        i++;
                                }
                        AchievementList.loadAchievement(achievementTab);
                        rs.close();
                        rs2.close();
                        st.close();
                        st.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public static void savePlayer(Player p) {

                try {
                        PreparedStatement st = Main.getMyDatabase().prepareStatement(savePlayer);
                        st.setString(1, p.getUniqueId().toString());
                        st.setString(2, p.getName());
                        st.setString(3, p.getAddress().getHostString());
                        st.execute();
                        st.close();

                } catch (SQLException e) {
                        e.printStackTrace();
                }

                Cache cache = ManageCache.getCache(p);
                if (cache == null)
                        return;

                String[] achievements = cache.getAllAchievementName();
                if (achievements == null)
                        return;

                //savePlayerData
                for (String achievement : achievements) {
                        PreparedStatement st;
                        try {
                                st = Main.getMyDatabase().prepareStatement(savePlayerData);
                                st.setString(1, cache.getAchievementData(achievement)[1]);
                                if (cache.getAchievementData(achievement)[2].equalsIgnoreCase("True")) {
                                        st.setInt(2, 1);
                                } else {
                                        st.setInt(2, 0);
                                }
                                st.setString(3, p.getUniqueId().toString());
                                st.setString(4, AchievementList.getAchievementIdByName(achievement));
                                if (cache.getAchievementData(achievement)[3].equalsIgnoreCase("True")) {
                                        st.setInt(5, 1);
                                } else {
                                        st.setInt(5, 0);
                                }
                                st.execute();
                                st.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }

        public static void addAchievement(String name) {
                try {
                        PreparedStatement st = Main.getMyDatabase().prepareStatement(addAchievement);
                        st.setString(1, name);    //nom
                        st.setString(2, "");    //description
                        st.setString(3, "");    //successValue
                        st.setBoolean(4, true);    //isHide
                        st.setString(5, "");    //Reward
                        st.setString(6, "");    //Requiert
                        st.setString(7, "");    //Categorie
                        st.setInt(8, 0);    //Points
                        st.execute();
                        Bukkit.getLogger().info("addAchievement:" + st);
                        st.close();

                } catch (SQLException e) {
                        e.printStackTrace();
                }
                DataBaseManager.getAllAchievements();
        }

        public static void removeAchievement(String name) {
                try {
                        PreparedStatement st = Main.getMyDatabase().prepareStatement(removeAchievementSQLStatement);
                        st.setString(1, name);
                        st.execute();
                        Bukkit.getLogger().info("removeAchievement:" + st);
                        st.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                DataBaseManager.getAllAchievements();
        }

        public static void setDescription(String name, String description) {
                try {
                        PreparedStatement st = Main.getMyDatabase().prepareStatement(
                                achievementValueUpdate.replace("$colonne", "description")
                                        .replace("$achievement", name));
                        st.setString(1, description);
                        st.setString(2, name);
                        st.execute();
                        Bukkit.getLogger().info("setDescription:" + st);
                        st.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                DataBaseManager.getAllAchievements();
        }

        public static void setSuccessValue(String name, String successValue) {
                try {
                        PreparedStatement st = Main.getMyDatabase().prepareStatement(
                                achievementValueUpdate.replace("$colonne", "successvalue")
                                        .replace("$achievement", name));
                        st.setString(1, successValue);
                        st.setString(2, name);
                        st.execute();
                        Bukkit.getLogger().info("setSuccessValue:" + st);
                        st.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                DataBaseManager.getAllAchievements();
        }

        public static void setHideValue(String name, boolean hide) {
                try {
                        PreparedStatement st = Main.getMyDatabase().prepareStatement(
                                achievementValueUpdate.replace("$colonne", "isHide").replace("$achievement", name));
                        if (hide) {
                                st.setInt(1, 1);
                        } else {
                                st.setInt(1, 0);
                        }
                        st.setString(2, name);
                        st.execute();
                        Bukkit.getLogger().info("setHideValue:" + st);
                        st.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                DataBaseManager.getAllAchievements();
        }

        public static void setReward(String name, String reward) {
                try {
                        PreparedStatement st = Main.getMyDatabase().prepareStatement(
                                achievementValueUpdate.replace("$colonne", "Reward").replace("$achievement", name));
                        st.setString(1, reward);
                        st.setString(2, name);
                        st.execute();
                        Bukkit.getLogger().info("setReward:" + st);
                        st.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                DataBaseManager.getAllAchievements();
        }

        public static void setRequire(String name, String requiert) {
                try {
                        PreparedStatement st = Main.getMyDatabase().prepareStatement(
                                achievementValueUpdate.replace("$colonne", "requiert").replace("$achievement", name));
                        st.setString(1, requiert);
                        st.setString(2, name);
                        st.execute();
                        Bukkit.getLogger().info("setRequire:" + st);
                        st.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                DataBaseManager.getAllAchievements();
        }

        public static void setCategory(String name, String Categorie) {
                try {
                        PreparedStatement st = Main.getMyDatabase().prepareStatement(
                                achievementValueUpdate.replace("$colonne", "categorie").replace("$achievement", name));
                        st.setString(1, Categorie);
                        st.setString(2, name);
                        st.execute();
                        Bukkit.getLogger().info("setCategory:" + st);
                        st.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                DataBaseManager.getAllAchievements();
        }

        public static void getPlayerAchievements(Player p) {
                try {
                        PreparedStatement st = Main.getMyDatabase()
                                .prepareStatement(selectAllPlayerAchievementSQLStatement);
                        st.setString(1, p.getUniqueId().toString());
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {
                                if (rs.getInt(3) == 1) {
                                        if (rs.getInt(6) == 1) {
                                                ManageCache.getCache(p).addAchievement(AchievementList
                                                                .getAchievementNameById(Integer.parseInt(rs.getString(5))),
                                                        rs.getString(2), true, true);
                                        } else {
                                                ManageCache.getCache(p).addAchievement(AchievementList
                                                                .getAchievementNameById(Integer.parseInt(rs.getString(5))),
                                                        rs.getString(2), true, false);
                                        }
                                } else {
                                        ManageCache.getCache(p).addAchievement(AchievementList
                                                        .getAchievementNameById(Integer.parseInt(rs.getString(5))),
                                                rs.getString(2), false, false);
                                }
                                //Bukkit.getLogger().info(rs.getString(1));  //id
                                //Bukkit.getLogger().info(rs.getString(2));	//progress
                                //Bukkit.getLogger().info(rs.getString(3));	//success
                                //Bukkit.getLogger().info(rs.getString(4));	//UUID
                                //Bukkit.getLogger().info(rs.getString(5));	//idAchievement
                        }
                        rs.close();
                        st.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }

        }

        public void initDatabase(Connection db) throws SQLException {
                createIfNotExistUserTable(db);
                extractIfNotExistAchievementTable(db);
                createIfNotExistAchievementProgressTable(db);
        }

        private void createIfNotExistAchievementProgressTable(Connection db) throws SQLException {
                db.createStatement().executeUpdate(
                        "CREATE TABLE IF NOT EXISTS `" + Main.getPlugin().getConfig().getString("database.prefix")
                                + "AchievementProgress` "
                                + "(`ID` INTEGER NOT NULL AUTO_INCREMENT,"
                                + " `progress` varChar(100) NOT NULL,"
                                + " `success` tinyint (1) NOT NULL,"
                                + " `userUUID` VARCHAR(100),"
                                + " `idAchievement` INTEGER,"
                                + " `rewardTaken` tinyint (1) NOT NULL,"
                                + " CONSTRAINT PK_ID PRIMARY KEY (`userUUID`, `idAchievement`),"
                                + " UNIQUE(`ID`), INDEX(`ID`)"
                                + ") CHARACTER SET utf8");
        }

        private void extractIfNotExistAchievementTable(Connection db) throws SQLException {
                db.createStatement().executeUpdate(
                        "CREATE TABLE IF NOT EXISTS `" + Main.getPlugin().getConfig().getString("database.prefix")
                                + "Achievement` "
                                + "(`ID` INTEGER NOT NULL AUTO_INCREMENT,"
                                + " `name` VARCHAR(100) NOT NULL,"
                                + " `description` VARCHAR(200) NOT NULL,"
                                + " `successValue` VARCHAR(200) NOT NULL,"
                                + " `isHide` tinyint(1) NOT NULL,"
                                + " `Reward` VARCHAR(200) NOT NULL,"
                                + " `requiert` VARCHAR(100),"
                                + " `categorie` VARCHAR(100),"
                                + " `points` INTEGER,"
                                + " PRIMARY KEY (`ID`),"
                                + " UNIQUE(`ID`), INDEX(`ID`)"
                                + ") CHARACTER SET utf8");
        }

        private void createIfNotExistUserTable(Connection db) throws SQLException {
                db.createStatement().executeUpdate(
                        "CREATE TABLE IF NOT EXISTS `" + Main.getPlugin().getConfig().getString("database.prefix")
                                + "users` "
                                + "(`UUID` VARCHAR(100) NOT NULL,"
                                + " `name` VARCHAR(30) NOT NULL,"
                                + " `lastIP` varchar(50),"
                                + " `firstLogin` timestamp NULL,"
                                + " `lastLogin` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                                + " `points` INTEGER,"
                                + " PRIMARY KEY (`UUID`),"
                                + " UNIQUE(`UUID`),"
                                + " INDEX(`UUID`)"
                                + ") CHARACTER SET utf8");
        }

        public synchronized Connection getConnection() {
                for (int i = 0; i < connections.size(); i++) {
                        Connection c = connections.get(i);
                        try {
                                if (c.isValid(2) && !c.isClosed()) {
                                        return c;
                                } else {
                                        connections.remove(c);
                                }
                        } catch (SQLException e) {
                                e.printStackTrace();
                                connections.remove(c);
                        }
                }
                Connection c = runConnection();
                if (c != null)
                        connections.add(c);
                return c;
        }

        private Connection runConnection() {
                try {
                        return DriverManager.getConnection(dsn, user, pass);
                } catch (SQLException e) {
                        plugin.getLogger().severe("Unable to connect to MySQL database.");
                        plugin.getLogger().severe(e.getMessage());
                        return null;
                }
        }

        public void removePlayer(Player p) {

        }

        public String[] listAchievement() {
                return null;
        }

}
