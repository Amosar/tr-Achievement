package com.trafalcraft.Achievement.Api;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Bukkit;

public class AchievementApi {

	private static HashMap<String, Rewards> pluginList = new HashMap<String, Rewards>();
	
	public static void registerReward(String categorie, Rewards plugin){
		pluginList.put(categorie,plugin);
	}
	
	public static Collection<Rewards> getAllCategorie(){
		return pluginList.values();
	}
	
	public static void unregisterPlugin(Rewards plugin){
		if(pluginList.containsValue(plugin)){
			pluginList.remove(plugin);
		}else{
			Bukkit.getLogger().warning("Achievement> erreur, Ce plugin n'a pas ete inscrit");
		}
	}
	
	public static Rewards getCategorie(String categorie){
		return pluginList.get(categorie);
	}
}
