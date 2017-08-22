package com.trafalcraft.achievement.survie;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.trafalcraft.Achievement.Api.AchievementApi;

public class Main extends JavaPlugin{
	
	  private static boolean economy = true;
	  private static Economy econ = null;
	  private static JavaPlugin plugin;
	  
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		AchievementApi.registerReward("survie", new Reward());
		
		plugin = this;
		
		if (economy) {
			if (!setupEconomy()) {
				getLogger().severe(String.format("[%s] - No Economy (Vault) dependency found! Disabling Economy.", getDescription().getName()));
				economy = false;
			}
		}
	}
	
	private boolean setupEconomy() {
		//charge l'économie.
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
		//Chargement de l'économie terminé.
	}
	
	public static Economy getEcon(){
		return econ;
	}
	
	public static JavaPlugin getPlugin(){
		return plugin;
	}
}
