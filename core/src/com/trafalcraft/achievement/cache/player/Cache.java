package com.trafalcraft.achievement.cache.player;

public class Cache {

        /*
         * 			String[][] tab = new String[5][2];
                tab[0][0] = "L'heritage";  //name
                tab[0][1] = "0"; //progress
                tab[0][2] = "oui"; //success
                tab[0][3] = //rewardTaken
         */
        private String[][] achievementProgressTab;
        private int achievementProgressTabSize;

        public Cache(String[][] achievementProgressTab) {
                achievementProgressTabSize = achievementProgressTab.length;
                achievementProgressTab = new String[achievementProgressTab.length][4];
                this.achievementProgressTab = achievementProgressTab;
        }

        public void addAchievement(String idAchievement, String progress, boolean success, boolean rewardTaken) {

                //		if(containsAchievement(idAchievement)) return;

                String[][] tabTemp = new String[achievementProgressTabSize + 1][4];
                for (int i = 0; i < achievementProgressTabSize; i++) {
                        tabTemp[i][0] = achievementProgressTab[i][0];
                        tabTemp[i][1] = achievementProgressTab[i][1];
                        tabTemp[i][2] = achievementProgressTab[i][2];
                        tabTemp[i][3] = achievementProgressTab[i][3];
                }
                achievementProgressTab = tabTemp;
                achievementProgressTab[achievementProgressTabSize][0] = idAchievement;
                achievementProgressTab[achievementProgressTabSize][1] = progress;
                if (success) {
                        achievementProgressTab[achievementProgressTabSize][2] = "True";
                } else {
                        achievementProgressTab[achievementProgressTabSize][2] = "False";
                }
                if (rewardTaken) {
                        achievementProgressTab[achievementProgressTabSize][3] = "True";
                } else {
                        achievementProgressTab[achievementProgressTabSize][3] = "False";
                }
                achievementProgressTabSize++;
        }

        public boolean containsAchievement(String idAchievement) {
                for (int i = 0; i < achievementProgressTabSize; i++) {
                        if (achievementProgressTab[i][0].equalsIgnoreCase(idAchievement)) {
                                return true;
                        }
                }
                return false;
        }

        public boolean containsAchievementName(String achievementName) {
                for (int i = 0; i < achievementProgressTabSize; i++) {
                        if (achievementProgressTab[i][1].equalsIgnoreCase(achievementName)) {
                                return true;
                        }
                }
                return false;
        }

        public void removeAchievement(String idAchievement) {
                if (!containsAchievement(idAchievement))
                        return;

                String[][] tabTemp = new String[achievementProgressTabSize - 1][4];
                int f = 0;
                for (int i = 0; i < achievementProgressTabSize; i++) {
                        if (!achievementProgressTab[i][0].equalsIgnoreCase(idAchievement)) {
                                tabTemp[f][0] = achievementProgressTab[i][0];
                                tabTemp[f][1] = achievementProgressTab[i][1];
                                tabTemp[f][2] = achievementProgressTab[i][2];
                                tabTemp[f][3] = achievementProgressTab[i][3];
                                f++;
                        }
                }
                achievementProgressTab = tabTemp;

                achievementProgressTabSize--;
        }

        public String[] getAchievementData(String idAchievement) {

                for (int i = 0; i < achievementProgressTabSize; i++) {
                        if (achievementProgressTab[i][0].equalsIgnoreCase(idAchievement)) {
                                String[] tabTemp = new String[4];
                                tabTemp[0] = achievementProgressTab[i][0];
                                tabTemp[1] = achievementProgressTab[i][1];
                                tabTemp[2] = achievementProgressTab[i][2];
                                tabTemp[3] = achievementProgressTab[i][3];
                                return tabTemp;
                        }
                }

                return null;
        }

        public void modifyAchievement(String idAchievement, String progress, boolean success, boolean rewardTaken) {
                for (int i = 0; i < achievementProgressTabSize; i++) {
                        if (achievementProgressTab[i][0].equalsIgnoreCase(idAchievement)) {
                                achievementProgressTab[i][1] = progress;
                                if (success) {
                                        achievementProgressTab[i][2] = "True";
                                } else {
                                        achievementProgressTab[i][2] = "False";
                                }
                                if (rewardTaken) {
                                        achievementProgressTab[i][3] = "True";
                                } else {
                                        achievementProgressTab[i][3] = "False";
                                }
                                return;
                        }
                }
                addAchievement(idAchievement, progress, success, rewardTaken);
        }

        public String[] getAllAchievementName() {
                String[] reponse = new String[achievementProgressTabSize];
                for (int i = 0; i < achievementProgressTabSize; i++) {
                        reponse[i] = achievementProgressTab[i][0];
                }
                return reponse;
        }

        public String[][] getAllAchievement() {
                return achievementProgressTab;
        }

        public String toString() {
                StringBuilder reponse = new StringBuilder();
                for (int i = 0; i < achievementProgressTabSize; i++) {
                        reponse.append("{idAchievement=").append(achievementProgressTab[i][0]).append(",Progress=")
                                .append(achievementProgressTab[i][1]).append(",Success=")
                                .append(achievementProgressTab[i][2]).append(",RewardTaken=")
                                .append(achievementProgressTab[i][3]).append("},");
                }
                reponse.append(",,,,,");
                if (!reponse.toString().contains(",,,,,,")) {
                        return "{}";
                }
                reponse.toString().replace(",,,,,,", "}");
                return reponse.toString();
        }
}
