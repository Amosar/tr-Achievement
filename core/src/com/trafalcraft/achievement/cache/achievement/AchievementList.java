package com.trafalcraft.achievement.cache.achievement;

public class AchievementList {
        private static final int fixTabSize = 9;
        //TODO use ArrayList instead of array and transform achievement into object
        private static String[][] achievementTab;
        private static int achievementTabSize;

        public static void loadAchievement(String[][] AchievementTab) {
                achievementTabSize = AchievementTab.length;
                AchievementList.achievementTab = new String[AchievementTab.length][fixTabSize];
                AchievementList.achievementTab = AchievementTab;
        }

        public static void addAchievement(String idAchievement, String achievementName, String description,
                String successValue
                , boolean cache, String reward, String requires, String category, int points) {

                if (containsAchievement(idAchievement))
                        return;

                String[][] tabTemp = new String[achievementTabSize + 1][fixTabSize];
                for (int i = 0; i < achievementTabSize; i++) {
                        System.arraycopy(achievementTab[i], 0, tabTemp[i], 0, tabTemp[0].length);
                }
                achievementTab = tabTemp;
                achievementTab[achievementTabSize][0] = idAchievement;
                achievementTab[achievementTabSize][1] = achievementName;
                achievementTab[achievementTabSize][2] = description;
                achievementTab[achievementTabSize][3] = successValue;
                if (cache) {
                        achievementTab[achievementTabSize][4] = "true";
                } else {
                        achievementTab[achievementTabSize][4] = "false";
                }
                achievementTab[achievementTabSize][5] = reward;
                achievementTab[achievementTabSize][6] = requires;
                achievementTab[achievementTabSize][7] = category;
                achievementTab[achievementTabSize][8] = points + "";
                achievementTabSize++;
        }

        public static boolean containsAchievement(String idAchievement) {
                for (int i = 0; i < achievementTabSize; i++) {
                        if (achievementTab[i][0].equalsIgnoreCase(idAchievement)) {
                                return true;
                        }
                }
                return false;
        }

        public static boolean containsAchievementName(String achievementName) {
                for (int i = 0; i < achievementTabSize; i++) {
                        if (achievementTab[i][1].equalsIgnoreCase(achievementName)) {
                                return true;
                        }
                }
                return false;
        }

        public static void removeAchievement(String idAchievement) {
                if (!containsAchievement(idAchievement))
                        return;

                String[][] tabTemp = new String[achievementTabSize - 1][fixTabSize];
                int f = 0;
                for (int i = 0; i < achievementTabSize; i++) {
                        if (!achievementTab[i][0].equalsIgnoreCase(idAchievement)) {
                                System.arraycopy(achievementTab[i], 0, tabTemp[f], 0, tabTemp[0].length);
                                f++;
                        }
                }
                achievementTab = tabTemp;

                achievementTabSize--;
        }

        public static String[] getAchievementData(String idAchievement) {

                for (int i = 0; i < achievementTabSize; i++) {
                        if (achievementTab[i][0].equalsIgnoreCase(idAchievement)) {
                                String[] tabTemp = new String[fixTabSize];
                                System.arraycopy(achievementTab[i], 0, tabTemp, 0, achievementTab[0].length);
                                return tabTemp;
                        }
                }

                return null;
        }

        public static String[] getAchievementDataByName(String achievementName) {

                for (int i = 0; i < achievementTabSize; i++) {
                        if (achievementTab[i][1].equalsIgnoreCase(achievementName)) {
                                String[] tabTemp = new String[fixTabSize];
                                System.arraycopy(achievementTab[i], 0, tabTemp, 0, achievementTab[0].length);
                                return tabTemp;
                        }
                }

                return null;
        }

        public static String getAchievementIdByName(String achievementName) {

                for (int i = 0; i < achievementTabSize; i++) {
                        if (achievementTab[i][1].equalsIgnoreCase(achievementName)) {
                                return achievementTab[i][0];
                        }
                }

                return null;
        }

        public static String getAchievementNameById(int achievementId) {
                for (int i = 0; i < achievementTabSize; i++) {
                        if (achievementTab[i][0].equalsIgnoreCase(achievementId + "")) {
                                return achievementTab[i][1];
                        }
                }
                return null;
                //return achievementTab[achievementId][1];
        }

        public static void modifyAchievement(String idAchievement, String achievementName, String description
                , String successValue, boolean cache, String reward, String requires, String category, int points) {
                for (int i = 0; i < achievementTabSize; i++) {
                        if (achievementTab[i][0].equalsIgnoreCase(idAchievement)) {
                                achievementTab[i][1] = achievementName;
                                achievementTab[i][2] = description;
                                achievementTab[i][3] = successValue;
                                if (cache) {
                                        achievementTab[achievementTabSize][4] = "true";
                                } else {
                                        achievementTab[achievementTabSize][4] = "false";
                                }
                                achievementTab[i][5] = reward;
                                achievementTab[i][6] = requires;
                                achievementTab[i][7] = category;
                                achievementTab[i][8] = points + "";
                                return;
                        }
                }

        }

        public static String[] getAllAchievementName() {
                String[] reponse = new String[achievementTabSize];
                for (int i = 0; i < achievementTabSize; i++) {
                        reponse[i] = achievementTab[i][1];
                }
                return reponse;
        }

        public static String[][] getAllAchievement() {
                return achievementTab;
        }

        public String toString() {
                StringBuilder reponse = new StringBuilder("{");
                for (int i = 0; i < achievementTabSize; i++) {
                        if (i != 0) {
                                reponse.append(",");
                        }
                        reponse.append("{Name=").append(achievementTab[i][0]).append(",Description=")
                                .append(achievementTab[i][1]).append(",SuccessValue=").append(achievementTab[i][2])
                                .append(",Hide=").append(achievementTab[i][3]).append(",Reward=")
                                .append(achievementTab[i][4]).append("}");
                }
                reponse.append("}");
                return reponse.toString();
        }
}
