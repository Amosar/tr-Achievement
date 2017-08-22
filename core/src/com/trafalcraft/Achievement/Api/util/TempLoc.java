package com.trafalcraft.Achievement.Api.util;

import org.bukkit.Location;

public class TempLoc{
	
	private Location loc;
	
	public TempLoc(Location loc){
		this.loc = loc;
	}
	
	
	public int getDistance(Location loc){
		int distance = 0;
		int X1 =  (int) loc.getX();
		int Z1 =  (int) loc.getZ();
		int X2 =  (int) this.loc.getX();
		int Z2 =  (int) this.loc.getZ();
		
		if(X1>X2){
			distance = X1-X2;
		}else{
			distance = X2-X1;
		}
		if(Z1>Z2){
			distance += Z1-Z2;
		}else{
			distance += Z2-Z1;
		}
		
		return distance;
	}
}