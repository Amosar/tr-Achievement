package com.trafalcraft.Achievement.cache.achievement;

public class AchievementList {
	private static String[][] AchievementTab;
	private static int tailleAchievementTab;
	private static final int tailleTabFix = 9;
	
	public static void loadAchievement(String[][] AchievementTab){
		tailleAchievementTab = AchievementTab.length;
		AchievementList.AchievementTab = new String[AchievementTab.length][tailleTabFix];
		AchievementList.AchievementTab = AchievementTab;
	}
	
	public static void addAchievement(String idAchievement,String AchievementName,String Description,String SuccesValue,boolean Cache,String recompense,String requiert, String categorie, int points){
		
		if(containsAchievement(idAchievement)) return;
		
		String[][] tabTemp = new String[tailleAchievementTab+1][tailleTabFix];
		for(int i = 0;i<tailleAchievementTab;i++){
			tabTemp[i][0] = AchievementTab[i][0];
			tabTemp[i][1] = AchievementTab[i][1];
			tabTemp[i][2] = AchievementTab[i][2];
			tabTemp[i][3] = AchievementTab[i][3];
			tabTemp[i][4] = AchievementTab[i][4];
			tabTemp[i][5] = AchievementTab[i][5];
			tabTemp[i][6] = AchievementTab[i][6];
			tabTemp[i][7] = AchievementTab[i][7];
			tabTemp[i][8] = AchievementTab[i][8];
		}
		AchievementTab = tabTemp;
		AchievementTab[tailleAchievementTab][0] = idAchievement;
		AchievementTab[tailleAchievementTab][1] = AchievementName;
		AchievementTab[tailleAchievementTab][2] = Description;
		AchievementTab[tailleAchievementTab][3] = SuccesValue;
		if(Cache){
			AchievementTab[tailleAchievementTab][4] = "true";
		}else{
			AchievementTab[tailleAchievementTab][4] = "false";
		}
		AchievementTab[tailleAchievementTab][5] = recompense;
		AchievementTab[tailleAchievementTab][6] = requiert;
		AchievementTab[tailleAchievementTab][7] = categorie;
		AchievementTab[tailleAchievementTab][8] = points+"";
		tailleAchievementTab++;
	}
	
	public static boolean containsAchievement(String idAchievement){
		for(int i = 0;i<tailleAchievementTab;i++){
			if(AchievementTab[i][0].equalsIgnoreCase(idAchievement)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean containsAchievementName(String AchievementName){
		for(int i = 0;i<tailleAchievementTab;i++){
			if(AchievementTab[i][1].equalsIgnoreCase(AchievementName)){
				return true;
			}
		}
		return false;
	}
	
	public static void removeAchievement(String idAchievement){
		if(!containsAchievement(idAchievement)) return;
		
		String[][] tabTemp = new String[tailleAchievementTab-1][tailleTabFix];
		int f=0;
		for(int i = 0;i<tailleAchievementTab;i++){
			if(!AchievementTab[i][0].equalsIgnoreCase(idAchievement)){
				tabTemp[f][0] = AchievementTab[i][0];
				tabTemp[f][1] = AchievementTab[i][1];
				tabTemp[f][2] = AchievementTab[i][2];
				tabTemp[f][3] = AchievementTab[i][3];
				tabTemp[f][4] = AchievementTab[i][4];
				tabTemp[f][5] = AchievementTab[i][5];
				tabTemp[f][6] = AchievementTab[i][6];
				tabTemp[f][7] = AchievementTab[i][7];
				tabTemp[f][8] = AchievementTab[i][8];
				f++;
			}else{
				
			}
		}
		AchievementTab = tabTemp;
		
		tailleAchievementTab--;
	}
	
	public static String[] getAchievementData(String idAchievement){
	
		for(int i = 0;i<tailleAchievementTab;i++){
			if(AchievementTab[i][0].equalsIgnoreCase(idAchievement)){
				String[] tabTemp = new String[tailleTabFix];
				tabTemp[0] = AchievementTab[i][0];
				tabTemp[1] = AchievementTab[i][1];
				tabTemp[2] = AchievementTab[i][2];
				tabTemp[3] = AchievementTab[i][3];
				tabTemp[4] = AchievementTab[i][4];
				tabTemp[5] = AchievementTab[i][5];
				tabTemp[6] = AchievementTab[i][6];
				tabTemp[7] = AchievementTab[i][7];
				tabTemp[8] = AchievementTab[i][8];
				return tabTemp;
			}
		}
		
		return null;
	}
	
	public static String[] getAchievementDataByName(String achievementName){
		
		for(int i = 0;i<tailleAchievementTab;i++){
			if(AchievementTab[i][1].equalsIgnoreCase(achievementName)){
				String[] tabTemp = new String[tailleTabFix];
				tabTemp[0] = AchievementTab[i][0];
				tabTemp[1] = AchievementTab[i][1];
				tabTemp[2] = AchievementTab[i][2];
				tabTemp[3] = AchievementTab[i][3];
				tabTemp[4] = AchievementTab[i][4];
				tabTemp[5] = AchievementTab[i][5];
				tabTemp[6] = AchievementTab[i][6];
				tabTemp[7] = AchievementTab[i][7];
				tabTemp[8] = AchievementTab[i][8];
				return tabTemp;
			}
		}
		
		return null;
	}
	
	public static String getAchievementIdByName(String achievementName){
		
		for(int i = 0;i<tailleAchievementTab;i++){
			if(AchievementTab[i][1].equalsIgnoreCase(achievementName)){
				return AchievementTab[i][0];
			}
		}
		
		return null;
	}
	
	public static String getAchievementNameById(int achievementId){
		for(int i = 0;i<tailleAchievementTab;i++){
			if(AchievementTab[i][0].equalsIgnoreCase(achievementId+"")){
				return AchievementTab[i][1];
			}
		}
		return null;
		//return AchievementTab[achievementId][1];
	}
	
	public static void modifyAchievement(String idAchievement,String AchievementName,String Description,String SuccesValue,boolean Cache,String recompense, String requiert, String categorie, int points){
		for(int i = 0;i<tailleAchievementTab;i++){
			if(AchievementTab[i][0].equalsIgnoreCase(idAchievement)){
				AchievementTab[i][1] = AchievementName;
				AchievementTab[i][2] = Description;
				AchievementTab[i][3] = SuccesValue;
				if(Cache){
					AchievementTab[tailleAchievementTab][4] = "true";
				}else{
					AchievementTab[tailleAchievementTab][4] = "false";
				}
				AchievementTab[i][5] = recompense;
				AchievementTab[i][6] = requiert;
				AchievementTab[i][7] = categorie;
				AchievementTab[i][8] = points+"";
				return;
			}
		}
		
	}
	
	
	public static String[] getAllAchievementName(){
		String[] reponse = new String[tailleAchievementTab];
		for(int i = 0;i<tailleAchievementTab;i++){
			reponse[i] = AchievementTab[i][1];
		}
		return reponse;
	}
	
	public static String[][] getAllAchievement(){
		return AchievementTab;
	}
	
	public  String toString(){
		String reponse = "{";
		for(int i = 0;i<tailleAchievementTab;i++){
			reponse+="{name="+AchievementTab[i][0]+",description="+AchievementTab[i][1]+",SuccessValue="+AchievementTab[i][2]+",Caché="+AchievementTab[i][3]+",Recompense="+AchievementTab[i][4]+"},";
		}
		reponse+=",,,,,";
		if(!reponse.contains(",,,,,,")){
			return "{}";
		}
		reponse.replace(",,,,,","}");
		return reponse;
	}
}
