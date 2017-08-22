package com.trafalcraft.Achievement.Listener;

import org.bukkit.entity.Player;

import com.trafalcraft.Achievement.cache.achievement.AchievementList;
import com.trafalcraft.Achievement.cache.player.ManageCache;
import com.trafalcraft.Achievement.util.Msg;

public class Defi {

	public void LeDebutDeLaFortune(Player p){
		String[] achievement = AchievementList.getAchievementDataByName("Le_début_de_la_fortune");
		if(achievement != null){
			String[] info = ManageCache.getCache(p).getAchievementData("Le_début_de_la_fortune");
			if(info == null){
				ManageCache.getCache(p).addAchievement("Le_début_de_la_fortune","1", true, true);
				Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
			}	
		}
	}
	
	public void JeSuisRIIIIICHE(Player p,int montant){
		String[] achievement = AchievementList.getAchievementDataByName("Je_suis_RIIIIICHE!-I");
		String[] achievement2 = AchievementList.getAchievementDataByName("Je_suis_RIIIIICHE!-II");
		String[] achievement3 = AchievementList.getAchievementDataByName("Je_suis_RIIIIICHE!-III");
		String[] achievement4 = AchievementList.getAchievementDataByName("Je_suis_RIIIIICHE!-IV");
		String[] achievement5 = AchievementList.getAchievementDataByName("Je_suis_RIIIIICHE!-V");
		if(achievement != null && achievement2 != null && achievement3 != null && achievement4 != null && achievement5 != null){
			String[] info = ManageCache.getCache(p).getAchievementData("Je_suis_RIIIIICHE!-I");
			if(info == null){
				if(montant >= 25000){
					ManageCache.getCache(p).addAchievement("Je_suis_RIIIIICHE!-I",montant+"", true, true);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}else{
					ManageCache.getCache(p).addAchievement("Je_suis_RIIIIICHE!-I",montant+"", false, false);
				}
			}else{
				boolean success = Boolean.valueOf(info[2].toLowerCase());
				if(!success){
					if(montant+Integer.parseInt(info[1]) >= 25000){
						ManageCache.getCache(p).modifyAchievement("Je_suis_RIIIIICHE!-I",(montant+Integer.parseInt(info[1]))+"", true, true);
						Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
					}else{
						ManageCache.getCache(p).modifyAchievement("Je_suis_RIIIIICHE!-I",(montant+Integer.parseInt(info[1]))+"", false, false);
					}
				}
			}
			String[] info2 = ManageCache.getCache(p).getAchievementData("Je_suis_RIIIIICHE!-II");
			if(info2 == null){
				if(montant >= 50000){
					ManageCache.getCache(p).addAchievement("Je_suis_RIIIIICHE!-II",montant+"", true, true);
					Msg.sendSuccess(p, achievement2[1].replace("_", " "), achievement2[2], Boolean.valueOf(achievement2[4]));
				}else{
					ManageCache.getCache(p).addAchievement("Je_suis_RIIIIICHE!-II",montant+"", false, false);
				}
			}else{
				boolean success = Boolean.valueOf(info2[2].toLowerCase());
				if(!success){
					if(montant+Integer.parseInt(info2[1]) >= 50000){
						ManageCache.getCache(p).modifyAchievement("Je_suis_RIIIIICHE!-II",(montant+Integer.parseInt(info2[1]))+"", true, true);
						Msg.sendSuccess(p, achievement2[1].replace("_", " "), achievement2[2], Boolean.valueOf(achievement2[4]));
					}else{
						ManageCache.getCache(p).modifyAchievement("Je_suis_RIIIIICHE!-II",(montant+Integer.parseInt(info2[1]))+"", false, false);
					}
				}
			}
			String[] info3 = ManageCache.getCache(p).getAchievementData("Je_suis_RIIIIICHE!-III");
			if(info3 == null){
				if(montant >= 100000){
					ManageCache.getCache(p).addAchievement("Je_suis_RIIIIICHE!-III",montant+"", true, true);
					Msg.sendSuccess(p, achievement3[1].replace("_", " "), achievement3[2], Boolean.valueOf(achievement3[4]));
				}else{
					ManageCache.getCache(p).addAchievement("Je_suis_RIIIIICHE!-III",montant+"", false, false);
				}
			}else{
				boolean success = Boolean.valueOf(info3[2].toLowerCase());
				if(!success){
					if(montant+Integer.parseInt(info3[1]) >= 100000){
						ManageCache.getCache(p).modifyAchievement("Je_suis_RIIIIICHE!-III",(montant+Integer.parseInt(info3[1]))+"", true, true);
						Msg.sendSuccess(p, achievement3[1].replace("_", " "), achievement3[2], Boolean.valueOf(achievement3[4]));
					}else{
						ManageCache.getCache(p).modifyAchievement("Je_suis_RIIIIICHE!-III",(montant+Integer.parseInt(info3[1]))+"", false, false);
					}
				}
			}
			String[] info4 = ManageCache.getCache(p).getAchievementData("Je_suis_RIIIIICHE!-IV");
			if(info4 == null){
				if(montant >= 200000){
					ManageCache.getCache(p).addAchievement("Je_suis_RIIIIICHE!-IV",montant+"", true, true);
					Msg.sendSuccess(p, achievement4[1].replace("_", " "), achievement4[2], Boolean.valueOf(achievement4[4]));
				}else{
					ManageCache.getCache(p).addAchievement("Je_suis_RIIIIICHE!-IV",montant+"", false, false);
				}
			}else{
				boolean success = Boolean.valueOf(info4[2].toLowerCase());
				if(!success){
					if(montant+Integer.parseInt(info4[1]) >= 200000){
						ManageCache.getCache(p).modifyAchievement("Je_suis_RIIIIICHE!-IV",(montant+Integer.parseInt(info4[1]))+"", true, true);
						Msg.sendSuccess(p, achievement4[1].replace("_", " "), achievement4[2], Boolean.valueOf(achievement4[4]));
					}else{
						ManageCache.getCache(p).modifyAchievement("Je_suis_RIIIIICHE!-IV",(montant+Integer.parseInt(info4[1]))+"", false, false);
					}
				}
			}
			String[] info5 = ManageCache.getCache(p).getAchievementData("Je_suis_RIIIIICHE!-V");
			if(info5 == null){
				if(montant >= 400000){
					ManageCache.getCache(p).addAchievement("Je_suis_RIIIIICHE!-V",montant+"", true, true);
					Msg.sendSuccess(p, achievement5[1].replace("_", " "), achievement5[2], Boolean.valueOf(achievement5[4]));
				}else{
					ManageCache.getCache(p).addAchievement("Je_suis_RIIIIICHE!-V",montant+"", false, false);
				}
			}else{
				boolean success = Boolean.valueOf(info5[2].toLowerCase());
				if(!success){
					if(montant+Integer.parseInt(info5[1]) >= 400000){
						ManageCache.getCache(p).modifyAchievement("Je_suis_RIIIIICHE!-V",(montant+Integer.parseInt(info5[1]))+"", true, true);
						Msg.sendSuccess(p, achievement5[1].replace("_", " "), achievement5[2], Boolean.valueOf(achievement5[4]));
					}else{
						ManageCache.getCache(p).modifyAchievement("Je_suis_RIIIIICHE!-V",(montant+Integer.parseInt(info5[1]))+"", false, false);
					}
				}
			}
		}
	}
	
	
	
	
}
