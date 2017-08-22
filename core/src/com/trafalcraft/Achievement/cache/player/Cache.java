package com.trafalcraft.Achievement.cache.player;

public class Cache {
	
	/*
	 * 			String[][] tab = new String[5][2];
			tab[0][0] = "L'heritage";  //name
			tab[0][1] = "0"; //progress
			tab[0][2] = "oui"; //success
			tab[0][3] = //rewardTaken
	 */
	private String[][] AchievementProgressTab;
	private int tailleAchievementProgressTab;
	
	public Cache(String[][] AchievementProgressTab){
		tailleAchievementProgressTab = AchievementProgressTab.length;
		AchievementProgressTab = new String[AchievementProgressTab.length][4];
		this.AchievementProgressTab = AchievementProgressTab;
	}
	
	
	public void addAchievement(String idAchievement,String progress,boolean success, boolean rewardTaken){
		
//		if(containsAchievement(idAchievement)) return;
		
		String[][] tabTemp = new String[tailleAchievementProgressTab+1][4];
		for(int i = 0;i<tailleAchievementProgressTab;i++){
			tabTemp[i][0] = AchievementProgressTab[i][0];
			tabTemp[i][1] = AchievementProgressTab[i][1];
			tabTemp[i][2] = AchievementProgressTab[i][2];
			tabTemp[i][3] = AchievementProgressTab[i][3];
		}
		AchievementProgressTab = tabTemp;
		AchievementProgressTab[tailleAchievementProgressTab][0] = idAchievement;
		AchievementProgressTab[tailleAchievementProgressTab][1] = progress;
		if(success){
			AchievementProgressTab[tailleAchievementProgressTab][2] = "True";
		}else{
			AchievementProgressTab[tailleAchievementProgressTab][2] = "False";
		}
		if(rewardTaken){
			AchievementProgressTab[tailleAchievementProgressTab][3] = "True";
		}else{
			AchievementProgressTab[tailleAchievementProgressTab][3] = "False";
		}
		tailleAchievementProgressTab++;
	}
	
	public boolean containsAchievement(String idAchievement){
		for(int i = 0;i<tailleAchievementProgressTab;i++){
			if(AchievementProgressTab[i][0].equalsIgnoreCase(idAchievement)){
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAchievementName(String achievementName){
		for(int i = 0;i<tailleAchievementProgressTab;i++){
			if(AchievementProgressTab[i][1].equalsIgnoreCase(achievementName)){
				return true;
			}
		}
		return false;
	}
	
	public void removeAchievement(String idAchievement){
		if(!containsAchievement(idAchievement)) return;
		
		String[][] tabTemp = new String[tailleAchievementProgressTab-1][4];
		int f=0;
		for(int i = 0;i<tailleAchievementProgressTab;i++){
			if(!AchievementProgressTab[i][0].equalsIgnoreCase(idAchievement)){
				tabTemp[f][0] = AchievementProgressTab[i][0];
				tabTemp[f][1] = AchievementProgressTab[i][1];
				tabTemp[f][2] = AchievementProgressTab[i][2];
				tabTemp[f][3] = AchievementProgressTab[i][3];
				f++;
			}else{
				
			}
		}
		AchievementProgressTab = tabTemp;
		
		tailleAchievementProgressTab--;
	}
	
	public String[] getAchievementData(String idAchievement){
	
		for(int i = 0;i<tailleAchievementProgressTab;i++){
			if(AchievementProgressTab[i][0].equalsIgnoreCase(idAchievement)){
				String[] tabTemp = new String[4];
				tabTemp[0] = AchievementProgressTab[i][0];
				tabTemp[1] = AchievementProgressTab[i][1];
				tabTemp[2] = AchievementProgressTab[i][2];
				tabTemp[3] = AchievementProgressTab[i][3];
				return tabTemp;
			}
		}
		
		return null;
	}
	
	
	public void modifyAchievement(String idAchievement,String progress,boolean success, boolean rewardTaken){
		for(int i = 0;i<tailleAchievementProgressTab;i++){
			if(AchievementProgressTab[i][0].equalsIgnoreCase(idAchievement)){
				AchievementProgressTab[i][1] = progress;
				if(success){
					AchievementProgressTab[i][2] = "True";
				}else{
					AchievementProgressTab[i][2] = "False";
				}
				if(rewardTaken){
					AchievementProgressTab[i][3] = "True";
				}else{
					AchievementProgressTab[i][3] = "False";
				}
				return;
			}
		}
		addAchievement(idAchievement, progress, success, rewardTaken);
	}
	
	
	public String[] getAllAchievementName(){
		String[] reponse = new String[tailleAchievementProgressTab];
		for(int i = 0;i<tailleAchievementProgressTab;i++){
			reponse[i] = AchievementProgressTab[i][0];
		}
		return reponse;
	}
	
	public String[][] getAllAchievement(){
		return AchievementProgressTab;
	}
	
	public String toString(){
		String reponse = "";
		for(int i = 0;i<tailleAchievementProgressTab;i++){
			reponse+="{idAchievement="+AchievementProgressTab[i][0]+",progress="+AchievementProgressTab[i][1]+",success="+AchievementProgressTab[i][2]+",rewardTaken="+AchievementProgressTab[i][3]+"},";
		}
		reponse+=",,,,,";
		if(!reponse.contains(",,,,,,")){
			return "{}";
		}
		reponse.replace(",,,,,,","}");
		return reponse;
	}
}
