package com.trafalcraft.Achievement.util;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.trafalcraft.Achievement.Main;

public enum Msg {
	
	Prefix("&btr-Achievement &9&l> &r&b "),
	ERREUR("&4tr-Achievement &l> &r&c "),
	NO_PERMISSIONS("&4Erreur &9&l> &r&bYou dont have permission to do that!"),
	Command_Use("&4tr-Achievement &l> &r&cCommand usage: &6/ach $commande"),
	//Setup
	achievement_exist("L'achievement $achievement éxiste déjà"),
	achievement_not_exist("L'achievement $achievement n'éxiste pas"),
	achievement_add("L'achievement $achievement à été ajouté"),
	achievement_remove("L'achievement $achievement à été supprimé"),
	setValue("$value mis à jour"),
	//Joueur
	rewardTaken("Tu a bien reçu la récompense pour cette achievement"),
	rewardError("Une erreur est survenu veuillez contacter un administrateur");
	
	
	
	
	
	static JavaPlugin plugin = Main.getPlugin();
	  public static void getHelp(Player sender){
	        sender.sendMessage("");
	        sender.sendMessage("§3§l-------------------AntiRedstoneClock-------------------");
	        //sender.sendMessage("§3/ach setup <nom de l'arene> §b- crée l'arène.");
	        sender.sendMessage("§4-En construction...");
	        sender.sendMessage("§3------------------------------------------------");
	        sender.sendMessage("");
		  }
	  
	  
	    private String value;
		private Msg(String value) {
			this.value = value;
	    }
		
	    public String toString(){
	    	return value;
	    }
	    public void replaceby(String value){
			this.value = value;
	    }
	    
	    public static void load(){
	    	Prefix.replaceby(Main.getPlugin().getConfig().getString("Msg.default.prefix").replace("&", "§"));
	    	ERREUR.replaceby(Main.getPlugin().getConfig().getString("Msg.default.error").replace("&", "§"));
	    	NO_PERMISSIONS.replaceby(Main.getPlugin().getConfig().getString("Msg.default.no_permission").replace("&", "§"));
	    	Command_Use.replaceby(Main.getPlugin().getConfig().getString("Msg.default.command_use").replace("&", "§"));
	    	
	    	achievement_exist.replaceby(Main.getPlugin().getConfig().getString("Msg.setup.achievement_exist").replace("&", "§"));
	    	achievement_not_exist.replaceby(Main.getPlugin().getConfig().getString("Msg.setup.achievement_not_exist").replace("&", "§"));
	    	achievement_add.replaceby(Main.getPlugin().getConfig().getString("Msg.setup.achievement_add").replace("&", "§"));
	    	achievement_remove.replaceby(Main.getPlugin().getConfig().getString("Msg.setup.achievement_remove").replace("&", "§"));
	    	setValue.replaceby(Main.getPlugin().getConfig().getString("Msg.setup.setValue").replace("&", "§"));
	    }
	    
	    public static void DefaultMsg(){
	    	//default
	    	Main.getPlugin().getConfig().set("Msg.default.prefix", Prefix.toString().replace("§", "&"));
	    	Main.getPlugin().getConfig().set("Msg.default.error", ERREUR.toString().replace("§", "&"));
	    	Main.getPlugin().getConfig().set("Msg.default.no_permission", NO_PERMISSIONS.toString().replace("§", "&"));
	    	Main.getPlugin().getConfig().set("Msg.default.command_use", Command_Use.toString().replace("§", "&"));
	    	
	    	Main.getPlugin().getConfig().set("Msg.setup.achievement_exist", achievement_exist.toString().replace("§", "&"));
	    	Main.getPlugin().getConfig().set("Msg.setup.achievement_not_exist", achievement_not_exist.toString().replace("§", "&"));
	    	Main.getPlugin().getConfig().set("Msg.setup.achievement_add", achievement_add.toString().replace("§", "&"));
	    	Main.getPlugin().getConfig().set("Msg.setup.achievement_remove", achievement_remove.toString().replace("§", "&"));
	    	Main.getPlugin().getConfig().set("Msg.setup.setValue", setValue.toString().replace("§", "&"));
	    }
		
/*	    void set(String value) {
	        //this.value = value;
	    }
*/
	    
	    
	    
	    

		public static List<String> getLoreAchievementGui(String[] info, String[] info2){
			List<String> lore = new ArrayList<String>();
			lore.add(info[2]);
			if(info2 != null){
				lore.add("§aProgrès: §6"+info2[1]+"/"+info[3]);
			}else{
				lore.add("§aProgrès: §60/"+info[3]);
			}
			lore.add("§6========");
			lore.add("");
			if(info[5].length() > 0){
				String temp[] = info[5].split("\\{ligne\\}");
				for(int k = 0;k<temp.length;k++){
					if(k == 0){
						lore.add("§6Récompense: §a"+temp[k]);
					}else{
						lore.add("§a"+temp[k]);
					}
				}
			}else{
				lore.add("§6Récompense: §aJuste pour la gloire");
			}
			return lore;
		}
	    
	    public static String achievementProgress(String displayName, String[] playerData, String[] achievementData){
		  	String message = "\n \n ";
			message = "§a-=§6Info sur l'achievement \""+displayName+"\"§a=-\n";
			message+="§aDescription: §6"+achievementData[2]+"\n";
			if(playerData == null){
				message+="§aProgrès: §60/"+achievementData[3]+"\n";
			}else{
				message+="§aProgrès: §6"+playerData[1]+"/"+achievementData[3]+"\n";
			}
			message+="§aRécompense: §6"+achievementData[5].replace("{ligne}"," ")+"\n";
	    	return message;
	    }
	    
	    
	    
	    @SuppressWarnings("deprecation")
		public static void sendSuccess(Player p, String achievementName, String achievement, boolean hide){
	    	p.sendMessage("§2==================================");
			p.sendMessage("§3Achievement réussi §6"+achievementName);
			p.sendMessage("§3"+achievement);
	    	p.sendMessage("§2==================================");
	    	p.sendTitle("§6"+achievementName, "§3achievement réussi");
	    	p.spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation(),5);
	    	//ParticleEffect.FIREWORKS_SPARK.display(0, 3, 0, 0.1f, 500, location, 1);
	    	if(!hide){
		    	TextComponent message = new TextComponent("§3"+p.getName()+" à réussi l'achievement §6"+achievementName);
		    	message.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("§6Objectif:\n"+achievement).create()));
				for(Player p2 : Bukkit.getOnlinePlayers()){
					if(!p2.getName().equalsIgnoreCase(p.getName())){
						p2.spigot().sendMessage(message);
					}
				}
	    	}else{
		    	TextComponent message = new TextComponent("§3"+p.getName()+" à réussi l'achievement caché §6"+achievementName);
		    	message.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("§6Objectif:\n§k"+achievement).create()));
				for(Player p2 : Bukkit.getOnlinePlayers()){
					if(!p2.getName().equalsIgnoreCase(p.getName())){
						p2.spigot().sendMessage(message);
					
					}
				}
	    	}
	    }

}