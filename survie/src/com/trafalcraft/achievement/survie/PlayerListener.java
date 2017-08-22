package com.trafalcraft.achievement.survie;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.trafalcraft.Achievement.cache.achievement.AchievementList;
import com.trafalcraft.Achievement.cache.player.ManageCache;
import com.trafalcraft.Achievement.util.Msg;

public class PlayerListener implements Listener{
	
	/*@EventHandler
	public void onTest(PlayerStatisticIncrementEvent e){
		System.out.println(e.getStatistic());
	}*/
	
	//Achievement Je vole
		@EventHandler
		public void onJeVole(PlayerStatisticIncrementEvent e){
			if(e.getStatistic() == Statistic.AVIATE_ONE_CM){
				if((e.getNewValue()/100) <= 400){ //=300m 
					String[] achievement = AchievementList.getAchievementDataByName("Je_vole_!");
					//System.out.println("achievement"+achievement);
					if(achievement != null){
						Player p = (Player) e.getPlayer();
						String[] info = ManageCache.getCache(p).getAchievementData("Je_vole_!");
						if(info != null){
							boolean success = Boolean.valueOf(info[2].toLowerCase());
							if(!success){
								int progress = e.getNewValue();
								if(progress/100 == 300){
									ManageCache.getCache(p).modifyAchievement("Je_vole_!", 300+"", true, false);
									Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
								}else{
									ManageCache.getCache(p).modifyAchievement("Je_vole_!", (progress/100)+"", false, false);
								}
							}
						}else{
							int progress = e.getNewValue();
							if(progress/100 >= 300){
								ManageCache.getCache(p).addAchievement("Je_vole_!", 300+"", true, false);
								Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
								
							}else{
								ManageCache.getCache(p).addAchievement("Je_vole_!", (progress/100)+"", false,false);
							}
						}
					}
				}
				
			}
		}
	
		/*private HashMap<Player, TempLoc> tempLocElytra = new HashMap<>();
		@EventHandler
		public void onJeVole(EntityToggleGlideEvent e){
			if(e.getEntity() instanceof Player){
				String[] achievement = AchievementList.getAchievementDataByName("Je_vole_!");
				if(achievement != null){
					Player p = (Player) e.getEntity();
					String[] info = ManageCache.getCache(p).getAchievementData("Je_vole_!");
					if(p.isGliding()){
						if(info != null){
							boolean success = Boolean.valueOf(info[2].toLowerCase());
							if(!success){
								int progress = Integer.parseInt(info[1]);
								if((progress+tempLocElytra.get(p).getDistance(p.getLocation()))>=300){
									ManageCache.getCache(p).modifyAchievement("Je_vole_!", progress+tempLocElytra.get(p).getDistance(p.getLocation())+"", true, false);
									Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));

								}else{
									ManageCache.getCache(p).modifyAchievement("Je_vole_!", progress+tempLocElytra.get(p).getDistance(p.getLocation())+"", false, false);
								}
							}
						}else{
							if((tempLocElytra.get(p).getDistance(p.getLocation()))>=300){
								ManageCache.getCache(p).addAchievement("Je_vole_!", tempLocElytra.get(p).getDistance(p.getLocation())+"", true, false);
								Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
								
							}else{
								ManageCache.getCache(p).addAchievement("Je_vole_!", tempLocElytra.get(p).getDistance(p.getLocation())+"", false,false);
							}
						}
					}else{
						tempLocElytra.put(p, new TempLoc(p.getLocation()));
					}
				}

			}
		}*/
		
		//Achievement Courrez pauvre fou !
		@EventHandler
		public void onCourrezPauvreFou(PlayerItemConsumeEvent e){
			if(e.getItem().getType() == Material.GOLDEN_APPLE && e.getItem().getDurability() == 1){
				String[] achievement = AchievementList.getAchievementDataByName("Courrez_pauvre_fou_!");
				if(achievement != null){
					String[] info = ManageCache.getCache(e.getPlayer()).getAchievementData("Courrez_pauvre_fou_!");
					if(info == null){
						ManageCache.getCache(e.getPlayer()).addAchievement("Courrez_pauvre_fou_!", "1", true,false); //TODO trouver recompense
						Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
					}
				}
			}
		}
	
		//Achievement Plus encore Plusss de Diamant
		@EventHandler
		public void onPlusEncorePlusssDeDiamant(PlayerStatisticIncrementEvent e){
			if(e.getStatistic() == Statistic.MINE_BLOCK && e.getMaterial() == Material.DIAMOND_ORE){
				if(e.getNewValue() == 500){
					String[] achievement = AchievementList.getAchievementDataByName("Plus_encore_Plusss_de_Diamant");
					if(achievement != null){
						ManageCache.getCache(e.getPlayer()).addAchievement("Plus_encore_Plusss_de_Diamant", 500+"", true,false);
						Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
					}
				}
			}
		}
		/*public void onPlusEncorePlusssDeDiamant(BlockBreakEvent e){
			if(e.getBlock().getType() == Material.DIAMOND_ORE){
				String[] achievement = AchievementList.getAchievementDataByName("Plus_encore_Plusss_de_Diamant");
				if(achievement != null){
					Player p = (Player) e.getPlayer();
					String[] info = ManageCache.getCache(p).getAchievementData("Plus_encore_Plusss_de_Diamant");
					if(info == null){
						ManageCache.getCache(e.getPlayer()).addAchievement("Plus_encore_Plusss_de_Diamant", "1", false,false);
					
					}else{
						boolean success = Boolean.valueOf(info[2].toLowerCase());
						if(!success){
							int progress = Integer.parseInt(info[1]);
							if((progress+1)>=500){
								ManageCache.getCache(p).modifyAchievement("Plus_encore_Plusss_de_Diamant", (progress+1)+"", true,false);
								Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
							}else{
								ManageCache.getCache(p).modifyAchievement("Plus_encore_Plusss_de_Diamant", (progress+1)+"", false,false);
							}
						}
					}
				}
			}
		}*/
		
		//Achievement genocide
		@EventHandler
		public void onGenocide(PlayerStatisticIncrementEvent e){
			if(e.getStatistic() == Statistic.PLAYER_KILLS){
				if(e.getNewValue() == 50){
					String[] achievement = AchievementList.getAchievementDataByName("genocide");
					if(achievement != null){
						ManageCache.getCache(e.getPlayer()).addAchievement("genocide", 50+"", true,false);
						Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
					}
				}
			}
		}
		/*public void onGenocide(EntityDamageByEntityEvent e){
			if(e.getEntityType() == EntityType.PLAYER && e.getDamager() instanceof Player){
				if(((Player)e.getEntity()).getHealth() > e.getDamage()){
					return;
				}
				String[] achievement = AchievementList.getAchievementDataByName("genocide");
				if(achievement != null){
					Player p = (Player) e.getDamager();
					String[] info = ManageCache.getCache(p).getAchievementData("genocide");
					if(info == null){
						ManageCache.getCache(p).addAchievement("genocide", "1", false,false);
					}else{
						boolean success = Boolean.valueOf(info[2].toLowerCase());
						if(!success){
							int progress = Integer.parseInt(info[1]);
							if((progress+1)>=50){
								ManageCache.getCache(p).modifyAchievement("genocide", (progress+1)+"", true,false);
								Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
								
							}else{
								ManageCache.getCache(p).modifyAchievement("genocide", (progress+1)+"", false,false);
							}
						}
					}
				}
			}
		}*/
		
		//Achievement coup dou pelle
		@EventHandler
		public void onCoupDouPelle(EntityDamageByEntityEvent e){
			if(e.getEntityType() == EntityType.PLAYER && e.getDamager() instanceof Player){
				Player p = (Player) e.getDamager();
				if(p.getInventory().getItemInMainHand().getType() == Material.WOOD_SPADE 
						|| p.getInventory().getItemInMainHand().getType() == Material.STONE_SPADE
						|| p.getInventory().getItemInMainHand().getType() == Material.GOLD_SPADE
						|| p.getInventory().getItemInMainHand().getType() == Material.IRON_SPADE
						|| p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SPADE){
					if(((Player)e.getEntity()).getHealth() > e.getDamage()){
						return;
					}
					String[] achievement = AchievementList.getAchievementDataByName("coup_dou_pelle");
					if(achievement != null){
						String[] info = ManageCache.getCache(p).getAchievementData("coup_dou_pelle");
						if(info == null){
							ManageCache.getCache(p).addAchievement("coup_dou_pelle", "1", true,false);
							Msg.sendSuccess((Player) e.getDamager(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
						}
					}
				}
			}
		}
		
		//Achievement La faucheuse
		@EventHandler
		public void onLaFaucheuse(EntityDamageByEntityEvent e){
			if(e.getEntityType() == EntityType.PLAYER && e.getDamager() instanceof Player){
				Player p = (Player) e.getDamager();
				if(p.getInventory().getItemInMainHand().getType() == Material.WOOD_HOE 
						|| p.getInventory().getItemInMainHand().getType() == Material.STONE_HOE
						|| p.getInventory().getItemInMainHand().getType() == Material.GOLD_HOE
						|| p.getInventory().getItemInMainHand().getType() == Material.IRON_HOE
						|| p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_HOE){
					if(((Player)e.getEntity()).getHealth() > e.getDamage()){
						return;
					}
					String[] achievement = AchievementList.getAchievementDataByName("La_faucheuse");
					if(achievement != null){
						String[] info = ManageCache.getCache(p).getAchievementData("La_faucheuse");
						if(info == null){
							ManageCache.getCache(p).addAchievement("La_faucheuse", "1", true,true);//TODO trouvez recompense
							Msg.sendSuccess((Player) e.getDamager(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
						}
					}
				}
			}
		}
		
		//Achievement Je volais !
		@EventHandler
		public void onJeVolais(PlayerDeathEvent e){
			if(!(e.getEntity() instanceof Player)){
				return;
			}
			if(e.getDeathMessage().contains("fell")
					|| e.getDeathMessage().contains("was doomed to fall by")
					|| e.getDeathMessage().contains("was shot off some vines by")
					|| e.getDeathMessage().contains("was shot off a ladder by")
					|| e.getDeathMessage().contains("was blown from a high place by")){
				String[] achievement = AchievementList.getAchievementDataByName("je_volais_!");
				if(achievement != null){
					String[] info = ManageCache.getCache((Player) e.getEntity()).getAchievementData("je_volais_!");
					if(info == null){
						ManageCache.getCache((Player) e.getEntity()).addAchievement("je_volais_!", "1", true,false);
						Msg.sendSuccess((Player) e.getEntity(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
					}
				}
			}
		}
		
		//Achievement Marathonien
		@EventHandler
		public void onMarathonien(PlayerMoveEvent e){
			Player p = e.getPlayer();
			String[] achievement = AchievementList.getAchievementDataByName("Marathonien");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("Marathonien");
				if(info == null){
					int temp = (p.getStatistic(Statistic.SPRINT_ONE_CM)/100);
					if(temp>0){
						ManageCache.getCache(p).addAchievement("Marathonien", temp+"", false,false);
					}
				}else{
					boolean success = Boolean.valueOf(info[2].toLowerCase());
					if(!success){
						int temp = (p.getStatistic(Statistic.SPRINT_ONE_CM)/100);
						if(temp >= 15000){
							ManageCache.getCache(p).modifyAchievement("Marathonien", 15000+"", true,false);
						}else{
							ManageCache.getCache(p).modifyAchievement("Marathonien", temp+"", false,false);
						}
					}
				}
			}
		}
		
		//Achievement nageur pro
		@EventHandler
		public void onNageurPro(PlayerMoveEvent e){
			Player p = e.getPlayer();
			String[] achievement = AchievementList.getAchievementDataByName("nageur_pro");
			if(achievement != null){
				String[] info = ManageCache.getCache(p).getAchievementData("nageur_pro");
				if(info == null){
					int temp = (p.getStatistic(Statistic.DIVE_ONE_CM)+p.getStatistic(Statistic.SWIM_ONE_CM))/100;
					if(temp>0){
						ManageCache.getCache(p).addAchievement("nageur_pro", temp+"", false,false);
					}
				}else{
					boolean success = Boolean.valueOf(info[2].toLowerCase());
					if(!success){
						int temp = ((p.getStatistic(Statistic.DIVE_ONE_CM)+p.getStatistic(Statistic.SWIM_ONE_CM))/100);
						if(temp >= 10000){
							ManageCache.getCache(p).modifyAchievement("nageur_pro", 10000+"", true,false);
						}else{
							ManageCache.getCache(p).modifyAchievement("nageur_pro", temp+"", false,false);
						}
					}
				}
			}
		}
		/*private HashMap<Player, TempLoc> tempLocPlayerSwim = new HashMap<>();

		@EventHandler
		public void onNageurPro(PlayerMoveEvent e){
				Player p = e.getPlayer();
				String[] achievement = AchievementList.getAchievementDataByName("nageur_pro");
				if(achievement != null){
					String[] info = ManageCache.getCache(p).getAchievementData("nageur_pro");
					if(p.getLocation().getBlock().getType() == Material.WATER
							|| p.getLocation().getBlock().getType() == Material.STATIONARY_WATER){
						if(info != null){
							boolean success = Boolean.valueOf(info[2].toLowerCase());
							if(success){
								return;
							}
						}
						if(!tempLocPlayerSwim.containsKey(p)){
							tempLocPlayerSwim.put(p, new TempLoc(p.getLocation()));
						}
					}else{
						if(tempLocPlayerSwim.containsKey(p)){
							if(info != null){
								boolean success = Boolean.valueOf(info[2].toLowerCase());
								if(!success){
									int progress = Integer.parseInt(info[1]);
									//TODO valeur 10000
									if((progress+tempLocPlayerSwim.get(p).getDistance(p.getLocation()))>=10000){
										//p.sendMessage(arg0);
										ManageCache.getCache(p).modifyAchievement("nageur_pro", progress+tempLocPlayerSwim.get(p).getDistance(p.getLocation())+"", true,false);
										Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
										tempLocPlayerSwim.remove(p);
									}else{
										ManageCache.getCache(p).modifyAchievement("nageur_pro", progress+tempLocPlayerSwim.get(p).getDistance(p.getLocation())+"", false,false);
										tempLocPlayerSwim.remove(p);
									}
								}
							}else{
								//TODO valeur 10000
								if((tempLocPlayerSwim.get(p).getDistance(p.getLocation()))>=10000){
									ManageCache.getCache(p).modifyAchievement("nageur_pro", tempLocPlayerSwim.get(p).getDistance(p.getLocation())+"", true,false);
									Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
									tempLocPlayerSwim.remove(p);
								}else{
									ManageCache.getCache(p).addAchievement("nageur_pro", tempLocPlayerSwim.get(p).getDistance(p.getLocation())+"", false,false);
								}
								tempLocPlayerSwim.remove(p);
							}
						}
					}
				}
		}*/
		
		//Achievement ecolo
		@EventHandler
		public void onEcolo(BlockPlaceEvent e){
			if(e.getBlock().getType() == Material.SAPLING){
				if(e.getPlayer().getStatistic(Statistic.USE_ITEM, Material.SAPLING)+1 == 1000){
					String[] achievement = AchievementList.getAchievementDataByName("ecolo");
					if(achievement != null){
						ManageCache.getCache(e.getPlayer()).addAchievement("ecolo",1000+"", true,false);
						Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
					}
				}
				/*String[] achievement = AchievementList.getAchievementDataByName("ecolo");
				if(achievement != null){
					String[] info = ManageCache.getCache(e.getPlayer()).getAchievementData("ecolo");
					if(info == null){
						ManageCache.getCache(e.getPlayer()).addAchievement("ecolo","1", false,false);
					}else{
						boolean success = Boolean.valueOf(info[2].toLowerCase());
						if(!success){
							int progress = Integer.parseInt(info[1]);
							if((progress+1)>=1000){
								ManageCache.getCache(e.getPlayer()).modifyAchievement("ecolo", (progress+1)+"", true,false);
								Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
								
							}else{
								ManageCache.getCache(e.getPlayer()).modifyAchievement("ecolo", (progress+1)+"", false,false);
							}
						}
					}
				}*/
			}
		}
		
		
		
		/*//Achievement Ma premiere fois
		@EventHandler
		public void onMaPremiereFois(PlayerBedEnterEvent e){
			String[] achievement = AchievementList.getAchievementDataByName("Ma_premiere_fois");
			if(achievement != null){
				String[] info = ManageCache.getCache(e.getPlayer()).getAchievementData("Ma_premiere_fois");
				if(info == null){
					ManageCache.getCache(e.getPlayer()).addAchievement("Ma_premiere_fois","1", true,false);//TODO a verifier
					Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}
		}*/
		
		//Achievement Bunny jumping
		@EventHandler
		public void onBunnyJumping(PlayerStatisticIncrementEvent e){
			if(e.getStatistic() == Statistic.JUMP && e.getNewValue() == 1000){
				String[] achievement = AchievementList.getAchievementDataByName("Bunny_Jumping");
				if(achievement != null){
					ManageCache.getCache(e.getPlayer()).addAchievement("Bunny_Jumping", "1000", true,false);
					Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}
		}

		
		//Achievement Sous l'ocean Sous...
		@EventHandler
		public void onSousLoceanSous(PlayerStatisticIncrementEvent e){
			if(e.getStatistic() == Statistic.KILL_ENTITY && e.getNewValue() == 1 && e.getEntityType() == EntityType.GUARDIAN){
				String[] achievement = AchievementList.getAchievementDataByName("Sous_l'ocean_Sous_...");
				if(achievement != null){
					ManageCache.getCache(e.getPlayer()).addAchievement("Sous_l'ocean_Sous_...", "1", true,false);
					Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}
		}
			
			/*if(e.getEntity().getType() == EntityType.GUARDIAN && e.getDamager() instanceof Player){
				if(((Guardian) e.getEntity()).getHealth() > e.getDamage()){
					return;
				}
				String[] achievement = AchievementList.getAchievementDataByName("Sous_l'ocean_Sous_...");
				if(achievement != null){
					if(((Guardian)e.getEntity()).isElder()){
						String[] info = ManageCache.getCache((Player) e.getDamager()).getAchievementData("Sous_l'ocean_Sous_...");
						if(info == null){
							ManageCache.getCache((Player) e.getDamager()).addAchievement("Sous_l'ocean_Sous_...", "1", true,false);
							Msg.sendSuccess((Player) e.getDamager(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
						}	
					}
				}
			}*/
		
		//Achievement Eazy !
		/*@EventHandler
		public void onEazy(PlayerStatisticIncrementEvent e){
			if(e.getStatistic() == Statistic.KILL_ENTITY && e.getNewValue() == 1 && e.getEntityType() == EntityType.WITHER){
				String[] achievement = AchievementList.getAchievementDataByName("Eazy_!");
				if(achievement != null){
					ManageCache.getCache(e.getPlayer()).addAchievement("Eazy_!", "1", true,false);
					Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
				}
			}*/
		@EventHandler
		public void onEazy(EntityDamageByEntityEvent e){
			if(e.getEntity().getType() == EntityType.WITHER && e.getDamager() instanceof Player){
				if(((Wither) e.getEntity()).getHealth() > e.getDamage()){
					return;
				}
				String[] achievement = AchievementList.getAchievementDataByName("Eazy_!");
				if(achievement != null){
					String[] info = ManageCache.getCache((Player) e.getDamager()).getAchievementData("Eazy_!");
					if(info == null){
						ManageCache.getCache((Player) e.getDamager()).addAchievement("Eazy_!", "1", true,true);
						Msg.sendSuccess((Player) e.getDamager(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
						Main.getEcon().depositPlayer((Player) e.getDamager(), 20000);
					}	
				}
			}
		}
		
		
		//Achievement Chauve qui peut !
		@EventHandler
		public void onChauveQuiPeut(PlayerShearEntityEvent e){
			if(!(e.getEntity() instanceof Sheep)){
				return;
			}
			String[] achievement = AchievementList.getAchievementDataByName("Chauve_qui_peut_!");
			if(achievement != null){
				String[] info = ManageCache.getCache(e.getPlayer()).getAchievementData("Chauve_qui_peut_!");
				if(info == null){
					ManageCache.getCache(e.getPlayer()).addAchievement("Chauve_qui_peut_!","1", false,false);
				}else{
					boolean success = Boolean.valueOf(info[2].toLowerCase());
					if(!success){
						int progress = Integer.parseInt(info[1]);
						if((progress+1)>=50){
							ManageCache.getCache(e.getPlayer()).modifyAchievement("Chauve_qui_peut_!", (progress+1)+"", true,false);
							Msg.sendSuccess(e.getPlayer(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
							
						}else{
							ManageCache.getCache(e.getPlayer()).modifyAchievement("Chauve_qui_peut_!", (progress+1)+"", false,false);
						}
					}
				}
			}
		}
		
		//Achievement On a la classe ou on ne l'a pas
		@EventHandler
		public void onOnALaClasseOuOnNeLaPasInventoryClick(InventoryClickEvent e){
			if(!(e.getWhoClicked() instanceof Player)){
				return;
			}
			Player p = (Player) e.getWhoClicked();
			if(p.getInventory().getHelmet() == null
					|| p.getInventory().getChestplate() == null
					|| p.getInventory().getLeggings() == null
					|| p.getInventory().getBoots() == null){
				return;
			}
			if(p.getInventory().getHelmet().getType() == Material.LEATHER_HELMET 
					&& p.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE
					&& p.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS
					&& p.getInventory().getBoots().getType() == Material.LEATHER_BOOTS){
				LeatherArmorMeta helmetMeta = (LeatherArmorMeta) p.getInventory().getHelmet().getItemMeta();
				LeatherArmorMeta chestPlateMeta = (LeatherArmorMeta) p.getInventory().getHelmet().getItemMeta();
				LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) p.getInventory().getHelmet().getItemMeta();
				LeatherArmorMeta bootsMeta = (LeatherArmorMeta) p.getInventory().getHelmet().getItemMeta();
				//System.out.println(Color.decode("#F27FA5"));
				if(helmetMeta.getColor().getRed() == 242
						&& helmetMeta.getColor().getGreen() == 127
						&& helmetMeta.getColor().getBlue() == 165
						&& chestPlateMeta.getColor().getRed() == 242
						&& chestPlateMeta.getColor().getGreen() == 127
						&& chestPlateMeta.getColor().getBlue() == 165
						&& leggingsMeta.getColor().getRed() == 242
						&& leggingsMeta.getColor().getGreen() == 127
						&& leggingsMeta.getColor().getBlue() == 165
						&& bootsMeta.getColor().getRed() == 242
						&& bootsMeta.getColor().getGreen() == 127
						&& bootsMeta.getColor().getBlue() == 165){
					
					String[] achievement = AchievementList.getAchievementDataByName("On_a_la_classe_ou_on_ne_l'a_pas");
					if(achievement != null){
						String[] info = ManageCache.getCache(p).getAchievementData("On_a_la_classe_ou_on_ne_l'a_pas");
						if(info == null){
							ManageCache.getCache(p).addAchievement("On_a_la_classe_ou_on_ne_l'a_pas", "1", true,false);
							Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
							Main.getEcon().depositPlayer(p, 20000);
						}	
					}
				}
			}
		}
		
		@EventHandler
		public void onOnALaClasseOuOnNeLaPasPlayerInteract(PlayerInteractEvent e){
			Player p = (Player) e.getPlayer();
			if(p.getInventory().getHelmet() == null
					|| p.getInventory().getChestplate() == null
					|| p.getInventory().getLeggings() == null
					|| p.getInventory().getBoots() == null){
				return;
			}
			if(p.getInventory().getHelmet().getType() == Material.LEATHER_HELMET 
					&& p.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE
					&& p.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS
					&& p.getInventory().getBoots().getType() == Material.LEATHER_BOOTS){
				LeatherArmorMeta helmetMeta = (LeatherArmorMeta) p.getInventory().getHelmet().getItemMeta();
				LeatherArmorMeta chestPlateMeta = (LeatherArmorMeta) p.getInventory().getHelmet().getItemMeta();
				LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) p.getInventory().getHelmet().getItemMeta();
				LeatherArmorMeta bootsMeta = (LeatherArmorMeta) p.getInventory().getHelmet().getItemMeta();
				if(helmetMeta.getColor().getRed() == 242
						&& helmetMeta.getColor().getGreen() == 127
						&& helmetMeta.getColor().getBlue() == 165
						&& chestPlateMeta.getColor().getRed() == 242
						&& chestPlateMeta.getColor().getGreen() == 127
						&& chestPlateMeta.getColor().getBlue() == 165
						&& leggingsMeta.getColor().getRed() == 242
						&& leggingsMeta.getColor().getGreen() == 127
						&& leggingsMeta.getColor().getBlue() == 165
						&& bootsMeta.getColor().getRed() == 242
						&& bootsMeta.getColor().getGreen() == 127
						&& bootsMeta.getColor().getBlue() == 165){
					
					String[] achievement = AchievementList.getAchievementDataByName("On_a_la_classe_ou_on_ne_l'a_pas");
					if(achievement != null){
						String[] info = ManageCache.getCache(p).getAchievementData("On_a_la_classe_ou_on_ne_l'a_pas");
						if(info == null){
							ManageCache.getCache(p).addAchievement("On_a_la_classe_ou_on_ne_l'a_pas", "1", true,false);
							Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
							Main.getEcon().depositPlayer(p, 20000);
						}	
					}
				}
			}
		}
		
		//Achievement j'aime le monde
		@EventHandler
		public void onJaimeLeMondePlayerDropItem(PlayerDropItemEvent e){
			if(e.getItemDrop().getItemStack().getType() == Material.PAPER){
				ItemMeta meta = e.getItemDrop().getItemStack().getItemMeta();
				if(meta.getDisplayName().equalsIgnoreCase("lettre d'amour")){
					e.getItemDrop().setMetadata("j'aime le monde", new FixedMetadataValue(Main.getPlugin(), e.getPlayer()));
				}
			}
		}
		
		@EventHandler
		public void onJaimeLeMondePlayerPickupItem(PlayerPickupItemEvent e){
			if(e.getItem().getItemStack().getType() == Material.PAPER){
				ItemMeta meta = e.getItem().getItemStack().getItemMeta();
				if(meta.getDisplayName().equalsIgnoreCase("lettre d'amour")){
					/*for(int i = 0;i<e.getItem().getMetadata("j'aime le monde").size();i++){
						System.out.println("J'aime le monde "+i+">"+e.getItem().getMetadata("j'aime le monde").get(i).value());
					}*/
					if((Player) e.getItem().getMetadata("j'aime le monde").get(0).value() == e.getPlayer()){
						return;
					}
					Player p2 = (Player) e.getItem().getMetadata("j'aime le monde").get(0).value();
					String[] achievement = AchievementList.getAchievementDataByName("j'aime_le_monde");
					if(achievement != null){
						String[] info = ManageCache.getCache(p2).getAchievementData("j'aime_le_monde");
						if(info == null){
							ManageCache.getCache(p2).addAchievement("j'aime_le_monde", "1", true,false);
							Msg.sendSuccess(p2, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
						}	
					}
				}
			}
		}
		
		//bonne nuit/avancé/confident/j'ai autre chose a faire/Left4Dead
		@EventHandler
		public void onKillZombie(EntityDamageByEntityEvent e){//Player p,int montant){
			if(e.getEntity().getType() == EntityType.ZOMBIE && e.getDamager() instanceof Player){
				if(((Zombie) e.getEntity()).getHealth() > e.getDamage()){
					return;
				}
				Player p = (Player) e.getDamager();
				//TODO :
				String[] achievement = AchievementList.getAchievementDataByName("bonne_nuit");
				String[] achievement2 = AchievementList.getAchievementDataByName("avancé");
				String[] achievement3 = AchievementList.getAchievementDataByName("confident");
				String[] achievement4 = AchievementList.getAchievementDataByName("j'ai_autre_chose_à_faire");
				String[] achievement5 = AchievementList.getAchievementDataByName("Left4Dead");
				if(achievement != null && achievement2 != null && achievement3 != null && achievement4 != null && achievement5 != null){
					String[] info = ManageCache.getCache(p).getAchievementData("bonne_nuit");
					if(info == null){
						ManageCache.getCache(p).addAchievement("bonne_nuit",1+"", true, false);
						Msg.sendSuccess(p, achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
					}else{
						String[] info2 = ManageCache.getCache(p).getAchievementData("avancé");
						if(info2 == null){
							ManageCache.getCache(p).addAchievement("avancé",2+"", false, false);
							Msg.sendSuccess(p, achievement2[1].replace("_", " "), achievement2[2], Boolean.valueOf(achievement2[4]));
						}else{
							boolean success = Boolean.valueOf(info2[2].toLowerCase());
							if(!success){
								if(Integer.parseInt(info2[1])+1 >= 25){
									ManageCache.getCache(p).modifyAchievement("avancé",(1+Integer.parseInt(info2[1]))+"", true, false);
									Msg.sendSuccess(p, achievement2[1].replace("_", " "), achievement2[2], Boolean.valueOf(achievement2[4]));
								}else{
									ManageCache.getCache(p).modifyAchievement("avancé",(1+Integer.parseInt(info2[1]))+"", false, false);
								}
							}
						}
						String[] info3 = ManageCache.getCache(p).getAchievementData("confident");
						if(info3 == null){
							ManageCache.getCache(p).addAchievement("confident",1+"", false, false);
						}else{
							boolean success = Boolean.valueOf(info3[2].toLowerCase());
							if(!success){
								if(Integer.parseInt(info3[1])+1 >= 100){
									ManageCache.getCache(p).modifyAchievement("confident",(1+Integer.parseInt(info3[1]))+"", true, false);
									Msg.sendSuccess(p, achievement3[1].replace("_", " "), achievement3[2], Boolean.valueOf(achievement3[4]));
								}else{
									ManageCache.getCache(p).modifyAchievement("confident",(1+Integer.parseInt(info3[1]))+"", false, false);
								}
							}
						}
						String[] info4 = ManageCache.getCache(p).getAchievementData("j'ai_autre_chose_à_faire");
						if(info4 == null){
							ManageCache.getCache(p).addAchievement("j'ai_autre_chose_à_faire",1+"", false, false);
						}else{
							boolean success = Boolean.valueOf(info4[2].toLowerCase());
							if(!success){
								if(Integer.parseInt(info4[1])+1 >= 1000){
									ManageCache.getCache(p).modifyAchievement("j'ai_autre_chose_à_faire",(1+Integer.parseInt(info4[1]))+"", true, false);
									Msg.sendSuccess(p, achievement4[1].replace("_", " "), achievement4[2], Boolean.valueOf(achievement4[4]));
								}else{
									ManageCache.getCache(p).modifyAchievement("j'ai_autre_chose_à_faire",(1+Integer.parseInt(info4[1]))+"", false, false);
								}
							}
						}
						String[] info5 = ManageCache.getCache(p).getAchievementData("Left4Dead");
						if(info5 == null){
							ManageCache.getCache(p).addAchievement("Left4Dead",1+"", false, false);
						}else{
							boolean success = Boolean.valueOf(info5[2].toLowerCase());
							if(!success){
								if(Integer.parseInt(info5[1])+1 >= 20000){
									ManageCache.getCache(p).modifyAchievement("Left4Dead",(1+Integer.parseInt(info5[1]))+"", true, false);
									Msg.sendSuccess(p, achievement5[1].replace("_", " "), achievement5[2], Boolean.valueOf(achievement5[4]));
								}else{
									ManageCache.getCache(p).modifyAchievement("Left4Dead",(1+Integer.parseInt(info5[1]))+"", false, false);
								}
							}
						}
					}
				}
			}
		}
		
		//Achievement Serial bucheron
		@EventHandler
		public void onSerialbucheron(EntityDamageByEntityEvent e){
			if(e.getEntity().getType() == EntityType.PLAYER && e.getDamager() instanceof Player){
				if(((Player) e.getEntity()).getHealth() > e.getDamage()){
					return;
				}else{
					Player p = (Player) e.getDamager();
					if(p.getInventory().getItemInMainHand().getType() == Material.WOOD_AXE 
							|| p.getInventory().getItemInMainHand().getType() == Material.STONE_AXE
							|| p.getInventory().getItemInMainHand().getType() == Material.GOLD_AXE
							|| p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE
							|| p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_AXE){
						String[] achievement = AchievementList.getAchievementDataByName("Serial_bucheron");
						if(achievement != null){
							String[] info = ManageCache.getCache(p).getAchievementData("Serial_bucheron");
							if(info == null){
								ManageCache.getCache(p).addAchievement("Serial_bucheron","1", false, false);
							}else{
								boolean success = Boolean.valueOf(info[2].toLowerCase());
								if(!success){
									if(Integer.parseInt(info[1])+1 >= 50){
										ManageCache.getCache(p).modifyAchievement("Serial_bucheron","50", true, false);
									}else{
										ManageCache.getCache(p).modifyAchievement("Serial_bucheron",(Integer.parseInt(info[1])+1)+"", false, false);
									}
								}
							}
						}
					}
				}
			}
		}
		
		/*//Achievement Au Mon Dieu...
		@EventHandler
		public void onAuMonDieu(PlayerStatisticIncrementEvent e){
			if(e.getStatistic() == Statistic.PLAYER_KILLS){
				String[] achievement = AchievementList.getAchievementDataByName("Au_Mon_Dieu...");
				if(achievement != null){
					Player p = (Player) e.getPlayer();
					String[] info = ManageCache.getCache(p).getAchievementData("Au_Mon_Dieu...");
					if(info == null){
						ManageCache.getCache(p).addAchievement("Au_Mon_Dieu...","1", true, false);
					}
				}
			}
		}*/
		
		//Achievement Je serais chevalier
		@EventHandler
		public void onJeSeraisChevalier(EntityDamageByEntityEvent e){
			if(e.getEntity().getType() == EntityType.PLAYER && e.getDamager() instanceof Player){
				if(((Player) e.getEntity()).getHealth() > e.getDamage()){
					return;
				}else{
					Player p = (Player) e.getDamager();
					if(p.getInventory().getItemInMainHand().getType() == Material.WOOD_AXE 
							|| p.getInventory().getItemInMainHand().getType() == Material.STONE_AXE
							|| p.getInventory().getItemInMainHand().getType() == Material.GOLD_AXE
							|| p.getInventory().getItemInMainHand().getType() == Material.IRON_AXE
							|| p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_AXE){
						String[] achievement = AchievementList.getAchievementDataByName("Je_serais_chevalier");
						if(achievement != null){
							String[] info = ManageCache.getCache(p).getAchievementData("Je_serais_chevalier");
							if(info == null){
								ManageCache.getCache(p).addAchievement("Je_serais_chevalier","1", false, false);
							}else{
								boolean success = Boolean.valueOf(info[2].toLowerCase());
								if(!success){
									if(Integer.parseInt(info[1])+1 >= 35){
										ManageCache.getCache(p).modifyAchievement("Je_serais_chevalier","35", true, false);
									}else{
										ManageCache.getCache(p).modifyAchievement("Je_serais_chevalier",(Integer.parseInt(info[1])+1)+"", false, false);
									}
								}
							}
						}
					}
				}
			}
		}
		
		//Achievement Genius
		@EventHandler
		public void onGenius(EntityDamageByEntityEvent e){
			if(e.getEntityType() == EntityType.PLAYER && e.getDamager() instanceof Player){
				Player p = (Player) e.getDamager();
				if(		   (p.getInventory().getItemInMainHand().getType() == Material.WOOD_SWORD
						|| p.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD
						|| p.getInventory().getItemInMainHand().getType() == Material.GOLD_SWORD
						|| p.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD
						|| p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD
													)&&(
						 p.getInventory().getItemInOffHand().getType() == Material.WOOD_SWORD
						|| p.getInventory().getItemInOffHand().getType() == Material.STONE_SWORD
						|| p.getInventory().getItemInOffHand().getType() == Material.GOLD_SWORD
						|| p.getInventory().getItemInOffHand().getType() == Material.IRON_SWORD
						|| p.getInventory().getItemInOffHand().getType() == Material.DIAMOND_SWORD)){
					if(((Player)e.getEntity()).getHealth() > e.getDamage()){
						return;
					}
					String[] achievement = AchievementList.getAchievementDataByName("Genius");
					if(achievement != null){
						String[] info = ManageCache.getCache(p).getAchievementData("Genius");
						if(info == null){
							ManageCache.getCache(p).addAchievement("Genius", "1", true,false);
							Msg.sendSuccess((Player) e.getDamager(), achievement[1].replace("_", " "), achievement[2], Boolean.valueOf(achievement[4]));
						}
					}
				}
			}
		}
		
		
}
