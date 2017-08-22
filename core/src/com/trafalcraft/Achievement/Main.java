package com.trafalcraft.Achievement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.error.YAMLException;

import com.trafalcraft.Achievement.Listener.PlayerListener;
import com.trafalcraft.Achievement.cache.achievement.AchievementList;
import com.trafalcraft.Achievement.cache.player.ManageCache;
import com.trafalcraft.Achievement.util.DataBaseManager;
import com.trafalcraft.Achievement.util.GUI;
import com.trafalcraft.Achievement.util.Msg;

public class Main extends JavaPlugin{

	private static JavaPlugin plugin;
	
    private static DataBaseManager manager;
    //private static ManageCache mc;
	
    /**
     * <b>Charge Le plugin et initialise les variables</b><br />
     * Charge le PlayerListener<br />
     * Charge et initialise la base de donnée<br />
     * 
     * @exception SQLException
     * 					Retourne une exception si le plugin ne peut pas ecrire/se connecter<br/>
     * 					A la base de donnée
     */
	public void onEnable(){
		plugin = this;
		plugin.getConfig().options().copyDefaults(true);
		saveConfig();
		plugin.reloadConfig();
		
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
		try{
			Msg.load();
		}catch(YAMLException e){
			this.getLogger().warning("An error as occured in the config.yml please fix it!");
			e.printStackTrace();
		}
        // Connect to the database
        manager = new DataBaseManager(this, "jdbc:mysql://" + plugin.getConfig().get("database.host") + ":" + plugin.getConfig().getInt("database.port") + "/" + plugin.getConfig().get("database.db") + "?useUnicode=true&characterEncoding=utf8", plugin.getConfig().get("database.user").toString(), plugin.getConfig().get("database.pass").toString());
        Connection db = manager.getConnection();
        if (db == null) {
            getLogger().severe("tr-Achievement is disabling. Please check your database settings in your config.yml");
            return;
        }
        
        // Initial database table setup
        try {
        	//a changer en GLobalUsers pour tous les plugins
        	db.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `" + getConfig().getString("database.prefix") + "users` "
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
        	db.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `" + getConfig().getString("database.prefix") + "Achievement` "
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
        	db.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `" + getConfig().getString("database.prefix") + "AchievementProgress` "
        			+ "(`ID` INTEGER NOT NULL AUTO_INCREMENT,"
        			+ " `progress` varChar(100) NOT NULL,"
        			+ " `success` tinyint (1) NOT NULL,"
        			+ " `userUUID` VARCHAR(100),"
        			+ " `idAchievement` INTEGER,"
        			+ " `rewardTaken` tinyint (1) NOT NULL,"
        			+ " CONSTRAINT PK_ID PRIMARY KEY (`userUUID`, `idAchievement`),"
        			+ " UNIQUE(`ID`), INDEX(`ID`)"
        			+ ") CHARACTER SET utf8");
        	
        } catch (SQLException e) {
            getLogger().severe("Unable to connect to the database. Disabling...");
            e.printStackTrace();
            return;
        }
        
        DataBaseManager.getAllAchievements();
        for(final Player p : Bukkit.getOnlinePlayers()){
    		Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
    			
    			@Override
    			public void run() {
    				try {
    					ManageCache.addPlayer(p, new String[0][0]);
    					DataBaseManager.savePlayer(p);
    					DataBaseManager.getPlayerAchievements(p);
    				} catch (Exception e1) {
    					e1.printStackTrace();
    				}
    			}
    		});
        }
        
    	//check if Citizens is present and enabled.

		if(getServer().getPluginManager().getPlugin("Citizens") == null || getServer().getPluginManager().getPlugin("Citizens").isEnabled() == false) {
			getLogger().log(Level.SEVERE, "Citizens 2.0 not found or not enabled");
			getServer().getPluginManager().disablePlugin(this);	
			return;
		}	
		
	}
	
	/**
	 * Ne fait rien pour l'instant
	 */
	public void onDisable(){

	}
	
	/**
	 * Gere les commandes
	 * Pour l'instant add,remove,list , retourne l'aide si commande rate
	 */
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[]args){
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("Achievement")){
				if(p.isOp()){
					if(args.length > 0){
						String[] info;
						String[] info2;
						String message = "";
						Player p2;
						switch (args[0].toLowerCase()) {
						//Achievement
						case "addachievement":
							if(args.length>1){
								if(AchievementList.getAchievementIdByName(args[1]) != null){
									p.sendMessage(Msg.Prefix+Msg.achievement_exist.toString().replace("$achievement", args[1]));
								}else{
									DataBaseManager.addAchievement(args[1]);
									p.sendMessage(Msg.Prefix+Msg.achievement_add.toString().replace("$achievement", args[1]));
								}
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "add <nomAchievement>"));
							}
							break;
							//TODO ajouter HoverClick et clickEvent
						case "list":
							String[] tab =  AchievementList.getAllAchievementName();
							String achievement = "";
							for(int i = 0;i<tab.length;i++){
								achievement+="§a"+tab[i]+"§f, ";
							}						
							p.sendMessage("AchievementList: "+achievement.replace("_", " "));
							break;
						case "removeachievement":
							if(args.length>1){
								if(AchievementList.getAchievementIdByName(args[1]) == null){
									p.sendMessage(Msg.Prefix+Msg.achievement_not_exist.toString().replace("$achievement", args[1]));
								}else{
									DataBaseManager.removeAchievement(args[1]);
									p.sendMessage(Msg.Prefix+Msg.achievement_remove.toString().replace("$achievement", args[1]));
								}
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "remove <nomAchievement>"));
							}
							break;
						case "setdescription":
							if(args.length>2){
								if(AchievementList.getAchievementIdByName(args[1]) == null){
									p.sendMessage(Msg.Prefix+Msg.achievement_not_exist.toString().replace("$achievement", args[1]));
								}else{
									for(int i = 2; i<args.length;i++){
										message += args[i]+" ";
									}
									DataBaseManager.setDescription(args[1], message);
									p.sendMessage(Msg.Prefix+Msg.setValue.toString().replace("$value", "Description"));
								}
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "setdescription <nomAchievement> <description>"));
							}
							break;
						case "setsuccessvalue":
							if(args.length>2){
								if(AchievementList.getAchievementIdByName(args[1]) == null){
									p.sendMessage(Msg.Prefix+Msg.achievement_not_exist.toString().replace("$achievement", args[1]));
								}else{
									message = "";
									for(int i = 2; i<args.length;i++){
										message += args[i]+" ";
									}
									DataBaseManager.setSuccessValue(args[1], message);
									p.sendMessage(Msg.Prefix+Msg.setValue.toString().replace("$value", "SuccessValue"));
								}
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "setsuccessvalue <nomAchievement> <successValue>"));
							}
							break;
						case "sethidevalue":
							if(args.length>2){
								if(AchievementList.getAchievementIdByName(args[1]) == null){
									p.sendMessage(Msg.Prefix+Msg.achievement_not_exist.toString().replace("$achievement", args[1]));
								}else{
									if(args[2].equalsIgnoreCase("false")){
										DataBaseManager.setHideValue(args[1], false);
									}else{
										DataBaseManager.setHideValue(args[1], true);
									}
									p.sendMessage(Msg.Prefix+Msg.setValue.toString().replace("$value", "HideValue"));
								}
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "sethidevalue <nomAchievement> <true/false>"));
							}
							break;
						case "setreward":
							if(args.length>2){
								if(AchievementList.getAchievementIdByName(args[1]) == null){
									p.sendMessage(Msg.Prefix+Msg.achievement_not_exist.toString().replace("$achievement", args[1]));
								}else{
									message = "";
									for(int i = 2; i<args.length;i++){
										message += args[i]+"";
									}
									DataBaseManager.setReward(args[1], message);
									p.sendMessage(Msg.Prefix+Msg.setValue.toString().replace("$value", "Reward"));
								}
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "setreward <nomAchievement> <recompense>"));
							}
							break;
						case "setrequiert":
							if(args.length>2){
								if(AchievementList.getAchievementIdByName(args[1]) == null){
									p.sendMessage(Msg.Prefix+Msg.achievement_not_exist.toString().replace("$achievement", args[1]));
								}else{
									DataBaseManager.setRequiert(args[1], args[2]);
									p.sendMessage(Msg.Prefix+Msg.setValue.toString().replace("$value", "RequiertValue"));
								}
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "setRequiert <nomAchievement> <requiert>"));
							}
							break;
						case "setcategorie":
							if(args.length>2){
								if(AchievementList.getAchievementIdByName(args[1]) == null){
									p.sendMessage(Msg.Prefix+Msg.achievement_not_exist.toString().replace("$achievement", args[1]));
								}else{
									DataBaseManager.setCategorie(args[1], args[2]);
									p.sendMessage(Msg.Prefix+Msg.setValue.toString().replace("$value", "Categorie"));
								}
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "setCategorie <nomAchievement> <categorie>"));
							}
							break;
						case "getachievementdata":
							if(args.length>1){
								if(AchievementList.getAchievementIdByName(args[1]) == null){
									p.sendMessage(Msg.Prefix+Msg.achievement_not_exist.toString().replace("$achievement", args[1]));
								}else{
									info = AchievementList.getAchievementDataByName(args[1]);
									message = "§a-=§6Info sur l'achievement \""+args[1]+"\"§a=-\n";
									message+="§aId: §6"+info[0]+"\n";
									message+="§aNom: §6"+info[1]+"\n";
									message+="§aDescription: §6"+info[2]+"\n";
									message+="§aValeur de succès: §6"+info[3]+"\n";
									message+="§aestCaché?: §6"+info[4]+"\n";
									message+="§aRécompense: §6"+info[5]+"\n";
									message+="§aRequiert: §6"+info[6]+"\n";
									p.sendMessage(message);
								}
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "getachievementdata <nomAchievement>"));
							}
							break;
							//TODO ajouter HoverClick et clickEvent
						case "getplayerachievements":
							if(args.length>0){
								if(args.length>1){
									p2 = Bukkit.getPlayer(args[1]);
									if(p2 != null){
										info =  ManageCache.getCache(p2).getAllAchievementName();
										message = "";
										for(int i = 0;i<info.length;i++){
											message+="§a"+info[i]+"§f, ";
										}
									}
									p.sendMessage("PlayerAchievements: "+message.replace("_", " "));
									return true;
								}
								info =  ManageCache.getCache(p).getAllAchievementName();
								message = "";
								for(int i = 0;i<info.length;i++){
									message+="§a"+info[i]+"§f, ";
								}
								p.sendMessage("PlayerAchievements: "+message.replace("_", " "));
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "getplayerachievements <player>"));
							}
							break;
						case "getachievementprogress":
							if(args.length>1){
								if(args.length>2){
									p2 = Bukkit.getPlayer(args[2]);
									if(p2 != null){
										info = ManageCache.getCache(p2).getAchievementData(args[1]);
										info2 = AchievementList.getAchievementDataByName(args[1]);
										message = "§a-=§6Info sur l'achievement \""+args[1]+"\"§a=-\n";
										message+="§aProgrès: §6"+info[1]+"/"+info2[3]+"\n";
										message+="§aCaché: §6"+info[2]+"\n";
										
										p.sendMessage(message);
										return true;
									}else{
										//TODO gérer les joueurs déconnectés
										p.sendMessage("Le plugin ne gère pas encore les joueurs déconnectés");
									}
								}
								info = ManageCache.getCache(p).getAchievementData(args[1]);
								info2 = AchievementList.getAchievementDataByName(args[1]);
								message = "§a-=§6Info sur l'achievement \""+args[1]+"\"§a=-\n";
								message+="§aProgrès: §6"+info[1]+"/"+info2[3]+"\n";
								message+="§aCaché: §6"+info[1]+"\n";
								p.sendMessage(message);
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "getachievementprogress <nomAchievement> (player)"));
							}
							break;
						case "winachievement":
							if(args.length > 3){
								info = AchievementList.getAchievementDataByName(args[1]);
								if(info != null){
									info2 = ManageCache.getCache(Bukkit.getPlayer(args[2])).getAchievementData(args[1]);
									if(Bukkit.getPlayer(args[2]) != null ){
										if(info2 == null){
											ManageCache.getCache(Bukkit.getPlayer(args[2])).addAchievement(args[1], args[3], true,true); //TODO trouver recompense
											Msg.sendSuccess(Bukkit.getPlayer(args[2]), info[1].replace("_", " "), info[2], Boolean.valueOf(info[4]));
										}
									}else{
										p.sendMessage(Msg.ERREUR+"Ce joueur n'est pas connecté");
									}
								}else{
									p.sendMessage(Msg.ERREUR+"Cette achievement n'a pas été trouvé");
								}
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "setAchievement <nomAchievement> <player> <valeur>"));
							}
							break;
						case "opengui":
							if(args.length >1){
								GUI.opengui(p, args[1]);
							}else{
								GUI.opengui(p, null);
							}
							break;
						case "openrewardgui":
							if(args.length >1){
								GUI.openrewardgui(p, args[1]);
							}else{
								p.sendMessage(Msg.Command_Use.toString().replace("$commande", "openrewardgui <categorie> (player)"));
							}
							break;
						
							
						default:
							p.sendMessage("Commande raté");
							break;
						}
					}else{
						p.sendMessage("Commande raté");
					}
				}else{
					p.sendMessage(Msg.ERREUR+"Vous n'avez pas la permission de faire sa!");
				}
			}
		}
		return false;
	}
	
	
	/**
	 * recupere l'instance du plugin
	 */
	public static JavaPlugin getPlugin(){
		return plugin;
	}
	
	/**
	 * Recupere la connection a la base donnée 
	 * 
	 * @return Connection
	 * 				Retourne l'object Connection
	 */
    public static Connection getMyDatabase() {
        return manager.getConnection();
    }
}
