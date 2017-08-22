package com.trafalcraft.Achievement.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class MojangApi {

	  private static Gson gson = new Gson();
	
	  private static final String uuidRetrievalUrl = "https://api.mojang.com/users/profiles/minecraft/";
	
	  public static String getUUID(final String pName){
		    BufferedReader reader = null;
		    try{
		      final URL url = new URL(uuidRetrievalUrl + pName);
		      final URLConnection conn = url.openConnection();
		      
		      reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      StringBuilder content = new StringBuilder();
		      String line = "";
		      while((line = reader.readLine()) != null){
		        content.append(line);
		      }
		      
		      MojangUUIDProfile p = gson.fromJson(content.toString(), MojangUUIDProfile.class);
		      if(p != null && !p.id.isEmpty()){
		        return p.id;
		      }
		    } catch (IOException e) {
		      //BAT.getInstance().getLogger().log(Level.CONFIG, "Can't retrieve UUID from mojang servers", e);
		    } finally{
		      if(reader != null){
		        try {
		          reader.close();
		        } catch (IOException ignored) {}
		      }
		    }
		    return null;
		  }

	  
	  private class MojangUUIDProfile{
		    String id;
		    //String name;
		  }
}
