package com.trafalcraft.achievement.survie;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Banner;
import org.bukkit.block.Block;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.BookMeta.Generation;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.trafalcraft.achievement.api.Rewards;
import com.trafalcraft.achievement.api.util.RewardUtil;
import com.trafalcraft.achievement.cache.achievement.AchievementList;
import com.trafalcraft.achievement.cache.player.ManageCache;
import com.trafalcraft.achievement.util.Msg;
import com.trafalcraft.achievement.util.NMSItem;

public class Reward implements Rewards{

	@Override
	public boolean giveReward(Player p, String achievement) {
		if(achievement.equalsIgnoreCase("Je_vole_!")){
			if(p.getInventory().firstEmpty() != -1){
				p.getInventory().addItem(NMSItem.setNewEntityTag(new ItemStack(Material.MONSTER_EGG),"Bat"));
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Plus_encore_Plusss_de_Diamant")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
				item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
				item.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
				item.addUnsafeEnchantment(Enchantment.LURE, 10);
				item.addUnsafeEnchantment(Enchantment.LUCK, 10);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName("§3Master pioche");
				item.setItemMeta(meta);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("genocide")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.SKULL_ITEM);
				item.setDurability((short) 3);
				ItemMeta meta = item.getItemMeta();
				((SkullMeta) meta).setOwner("Draazen");
				item.setItemMeta(meta);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Courrez_pauvre_fou_!")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.SKULL_ITEM);
				item.setDurability((short) 3);
				ItemMeta meta = item.getItemMeta();
				((SkullMeta) meta).setOwner("notch");
				item.setItemMeta(meta);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("coup_dou_pelle")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.GOLD_SPADE);
				
				item.setAmount(10);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("La_faucheuse")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.GOLD_HOE);
				item.setAmount(10);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("je_volais_!")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.BONE);
				item.setAmount(10);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Marathonien")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.SADDLE);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("nageur_pro")){
			if(RewardUtil.checkInv(p, 6)){
				ItemStack item = new ItemStack(Material.BOAT);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.BOAT_ACACIA);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.BOAT_BIRCH);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.BOAT_DARK_OAK);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.BOAT_JUNGLE);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.BOAT_SPRUCE);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				p.sendMessage(Msg.Prefix+"Il te faut au moins 6 emplacements libre");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("ecolo")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.WOOD_HOE);
		        for(ItemStack item2 : p.getInventory().getContents()) {
		            if(item2 == null) {
		            	p.getInventory().addItem(item);
		            }
		        }
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Bunny_Jumping")){
			if(RewardUtil.checkInv(p, 2)){
				ItemStack item = new ItemStack(Material.SKULL_ITEM);
				item.setDurability((short) 3);
				ItemMeta meta = item.getItemMeta();
				((SkullMeta) meta).setOwner("maheo");
				item.setItemMeta(meta);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.SLIME_BLOCK);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Sous_l'ocean_Sous_...")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
				item.addEnchantment(Enchantment.THORNS, 2);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Chauve_qui_peut_!")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.WOOL);
				item.setAmount(64);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Le_début_de_la_fortune")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.BANNER);
				BannerMeta bmeta = (BannerMeta) item.getItemMeta();
				
				Block b = Bukkit.getWorld("test").getBlockAt(1, 1, 1);
				b.setType(Material.STANDING_BANNER);
				Banner banner = (Banner)b.getState();
				banner.setBaseColor(DyeColor.RED);
				((org.bukkit.block.Banner)new Location(null, 1, 1, 1).getBlock().getState()).setBaseColor(DyeColor.RED);
				Pattern p2 = new Pattern(DyeColor.GREEN, PatternType.CREEPER);
				banner.setPattern(1, p2);
				banner.update();
				
				/*bmeta.addPattern(new Pattern(DyeColor.LIME , PatternType.));
				bmeta.addPattern(new Pattern(DyeColor.LIME , PatternType.HALF_HORIZONTAL));
				bmeta.addPattern(new Pattern(DyeColor.BLACK , PatternType.HALF_HORIZONTAL_MIRROR));
				bmeta.addPattern(new Pattern(DyeColor.BLACK , PatternType.HALF_HORIZONTAL_MIRROR));
				bmeta.addPattern(new Pattern(DyeColor.BLACK , PatternType.HALF_HORIZONTAL_MIRROR));*/
				//TODO ajouter le style de la banniere
				//bmeta.
				item.setItemMeta(bmeta);
				//p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("On_a_la_classe_ou_on_ne_l'a_pas")){
			if(p.getInventory().firstEmpty() != -1){
				   ItemStack item = new ItemStack(Material.WOOD_SWORD);
				   ItemMeta meta = item.getItemMeta();
				   meta.setDisplayName("§dépée en mousse");
				   meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
				   item.setItemMeta(meta);
				   p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Citoyen")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta bmeta = (BookMeta) item.getItemMeta();
				bmeta.setAuthor("Le Maire");
				bmeta.setDisplayName("Lettre du maire");
				bmeta.setTitle("Lettre du maire");
				bmeta.setGeneration(Generation.ORIGINAL);
				bmeta.addPage("test\n\ncoucou");
				//TODO Ajouter du texte au livre
				item.setItemMeta(bmeta);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("j'aime_le_monde")){
			if(RewardUtil.checkInv(p, 3)){
				ItemStack item = new ItemStack(Material.SKULL_ITEM);
				item.setDurability((short) 3);
				ItemMeta meta = item.getItemMeta();
				((SkullMeta) meta).setOwner("link972");
				item.setItemMeta(meta);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.IRON_INGOT);
				item.setAmount(50);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.DIAMOND);
				item.setAmount(10);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("bonne_nuit")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.INK_SACK);
				item.setDurability((short) 15);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("avancé")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.INK_SACK);
				item.setDurability((short) 15);
				item.setAmount(25);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("confident")){
			if(RewardUtil.checkInv(p, 2)){
				ItemStack item = new ItemStack(Material.INK_SACK);
				item.setDurability((short) 15);
				item.setAmount(36);
				p.getInventory().addItem(item);
				item.setAmount(64);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				p.sendMessage(Msg.Prefix+"Il te faut au moins 2 emplacements libre");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("j'ai_autre_chose_à_faire")){
			if(RewardUtil.checkInv(p, 16)){
				ItemStack item = new ItemStack(Material.INK_SACK);
				item.setDurability((short) 15);
				item.setAmount(64);
				for(int i = 0;i<15;i++){
					p.getInventory().addItem(item);
				}
				item.setAmount(40);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				p.sendMessage(Msg.Prefix+"Il te faut au moins 16 emplacements libres");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Left4Dead")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.INK_SACK);
		        for(ItemStack item2 : p.getInventory().getContents()) {
		            if(item2 == null) {
		            	p.getInventory().addItem(item);
		            }
		        }
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Ma_premiere_fois")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.PAPER);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName("essui tout");
				item.setItemMeta(meta);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else 
			//Vendeur
			if(achievement.equalsIgnoreCase("Déçu_!")){
			if(p.getInventory().firstEmpty() != -1){
				ItemStack item = new ItemStack(Material.JUKEBOX);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("L'héritage")){
			if(p.getInventory().firstEmpty() != -1){
				   ItemStack item = new ItemStack(Material.SKULL_ITEM);
				   item.setDurability((short) 3);
				   ItemMeta meta = item.getItemMeta();
				   meta.setDisplayName(p.getName());
				   ((SkullMeta) meta).setOwner(p.getName());
				   item.setItemMeta(meta);
				   p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Serial_bucheron")){
			if(RewardUtil.checkInv(p, 6)){
				for(int i = 0;i<6;i++){
					   ItemStack item = new ItemStack(Material.WOOD);
					   item.setAmount(64);
					   item.setDurability((short) i);
					   p.getInventory().addItem(item);
				}
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				p.sendMessage(Msg.Prefix+"Il te faut au moins 6 emplacements libres");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Je_serais_chevalier")){
			if(RewardUtil.checkInv(p, 9)){
				ItemStack item = new ItemStack(Material.IRON_AXE);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.IRON_BARDING);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.IRON_BOOTS);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.IRON_CHESTPLATE);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.IRON_HELMET);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.IRON_HOE);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.IRON_LEGGINGS);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.IRON_PICKAXE);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.IRON_SPADE);
				p.getInventory().addItem(item);
				item = new ItemStack(Material.IRON_SWORD);
				p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				p.sendMessage(Msg.Prefix+"Il te faut au moins 9 emplacements libres");
				return false;
			}
		}else if(achievement.equalsIgnoreCase("Genius")){
			if(p.getInventory().firstEmpty() != -1){
				   ItemStack item = new ItemStack(Material.SHIELD);
				   p.getInventory().addItem(item);
				return true;
			}else{
				p.sendMessage(Msg.Prefix+"Ton inventaire est plein, impossible de recevoir la récompense");
				return false;
			}
		}
		return false;
	}

	
	
	@Override
	public void playerJoinChecKStats(Player p) {
		//Je vole
		int temp = p.getStatistic(Statistic.AVIATE_ONE_CM);
		if(temp != 0){
			String[] achievement = AchievementList.getAchievementDataByName("Je_vole_!");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("Je_vole_!");
				if(info==null){
					if(temp > 30000){ //=300m
						ManageCache.getCache(p).addAchievement("Je_vole_!", 300+"", true, false);
						Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
					}else{
						ManageCache.getCache(p).addAchievement("Je_vole_!", temp/100+"", false, false);
					}
				}
			}
		}
		
		//Plus encore Plusss de Diamant
		temp = p.getStatistic(Statistic.MINE_BLOCK,Material.DIAMOND_ORE);
		if(temp > 500){
			String[] achievement = AchievementList.getAchievementDataByName("Plus_encore_Plusss_de_Diamant");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("Plus_encore_Plusss_de_Diamant");
				if(info == null){
					ManageCache.getCache(p).addAchievement("Plus_encore_Plusss_de_Diamant", 500+"", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}
		}
			/*String[] achievement = AchievementList.getAchievementDataByName("Plus_encore_Plusss_de_Diamant");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("Plus_encore_Plusss_de_Diamant");
				if(info == null){
					if(temp>500){
						ManageCache.getCache(p).addAchievement("Plus_encore_Plusss_de_Diamant", 500+"", true,false);
					}else{
						ManageCache.getCache(p).addAchievement("Plus_encore_Plusss_de_Diamant", p.getStatistic(Statistic.MINE_BLOCK,Material.DIAMOND_ORE)+"", false,false);
					}
				}
			}*/
		//Genocide
		temp = p.getStatistic(Statistic.PLAYER_KILLS);
		if(temp > 50){
			String[] achievement = AchievementList.getAchievementDataByName("genocide");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("genocide");
				if(info == null){
					ManageCache.getCache(p).addAchievement("genocide", 50+"", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}
		}
		
		//Marathonien
		temp = ((p.getStatistic(Statistic.SPRINT_ONE_CM))/100);
		if(temp >= 15000){
			String[] achievement = AchievementList.getAchievementDataByName("Marathonien");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("Marathonien");
				if(info == null){
					ManageCache.getCache(p).addAchievement("Marathonien", 15000+"", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}
		}else{
			String[] achievement = AchievementList.getAchievementDataByName("Marathonien");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("Marathonien");
				if(info == null){
					ManageCache.getCache(p).addAchievement("Marathonien", temp+"", false,false);
				}
			}
		}
		
		//nageur pro
		temp = ((p.getStatistic(Statistic.DIVE_ONE_CM)+p.getStatistic(Statistic.SWIM_ONE_CM))/100);
		if(temp >= 10000){
			String[] achievement = AchievementList.getAchievementDataByName("nageur_pro");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("nageur_pro");
				if(info == null){
					ManageCache.getCache(p).addAchievement("nageur_pro", 10000+"", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}
		}else{
			String[] achievement = AchievementList.getAchievementDataByName("nageur_pro");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("nageur_pro");
				if(info == null){
					ManageCache.getCache(p).addAchievement("nageur_pro", temp+"", false,false);
				}
			}
		}
		
		
		
		//ecolo
		temp = p.getStatistic(Statistic.USE_ITEM,Material.SAPLING);
		if(temp >= 1000){
			String[] achievement = AchievementList.getAchievementDataByName("ecolo");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("ecolo");
				if(info == null){
					ManageCache.getCache(p).addAchievement("ecolo", 1000+"", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}	
		}
		
		temp = p.getStatistic(Statistic.JUMP);
		if(temp >= 1000){
			String[] achievement = AchievementList.getAchievementDataByName("Bunny_Jumping");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("Bunny_Jumping");
				if(info == null){
					ManageCache.getCache(p).addAchievement("Bunny_Jumping", 1000+"", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}	
		}
		
		temp = p.getStatistic(Statistic.KILL_ENTITY,EntityType.GUARDIAN);
		if(temp >= 1){
			String[] achievement = AchievementList.getAchievementDataByName("Sous_l'ocean_Sous_...");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("Sous_l'ocean_Sous_...");
				if(info == null){
					ManageCache.getCache(p).addAchievement("Sous_l'ocean_Sous_...", 1+"", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}	
		}
		
		/*temp = p.getStatistic(Statistic.KILL_ENTITY,EntityType.WITHER);
		if(temp >= 1){
			String[] achievement = AchievementList.getAchievementDataByName("Eazy_!");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("Eazy_!");
				if(info == null){
					ManageCache.getCache(p).addAchievement("Eazy_!", 1+"", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}	
		}*/
		
		//bonne nuit/avancé/confident/j'ai autre chose a faire/Left4Dead
		temp = p.getStatistic(Statistic.KILL_ENTITY,EntityType.ZOMBIE);
		if(temp >= 1){
			String[] achievement = AchievementList.getAchievementDataByName("bonne_nuit");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("bonne_nuit");
				if(info == null){
					ManageCache.getCache(p).addAchievement("bonne_nuit", "1", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}	
		}

		if(temp >= 25){
			String[] achievement = AchievementList.getAchievementDataByName("avancé");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("avancé");
				if(info == null){
					ManageCache.getCache(p).addAchievement("avancé", "25", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}	
		}
		if(temp >= 100){
			String[] achievement = AchievementList.getAchievementDataByName("confident");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("confident");
				if(info == null){
					ManageCache.getCache(p).addAchievement("confident", "100", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}	
		}
		if(temp >= 1000){
			String[] achievement = AchievementList.getAchievementDataByName("j'ai_autre_chose_à_faire");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("j'ai_autre_chose_à_faire");
				if(info == null){
					ManageCache.getCache(p).addAchievement("j'ai_autre_chose_à_faire", "1000", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}	
		}
		if(temp >= 20000){
			String[] achievement = AchievementList.getAchievementDataByName("Left4Dead");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("Left4Dead");
				if(info == null){
					ManageCache.getCache(p).addAchievement("Left4Dead", "20000", true,false);
					Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}	
		}
	}
}
