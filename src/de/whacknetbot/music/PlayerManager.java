package de.whacknetbot.music;

import java.util.concurrent.ConcurrentHashMap;

import de.whacknetbot.main.Main;
/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class PlayerManager {

	public ConcurrentHashMap<Long, MusicController> controller;
	
	public PlayerManager() {
		this.controller = new ConcurrentHashMap<Long, MusicController>();
	}
	
	public MusicController getController(long guildid) {
		MusicController mc = null;
		
		if(this.controller.containsKey(guildid)) {
			mc = this.controller.get(guildid);
		}
		else {
			mc = new MusicController(Main.INSTANCE.shardMan.getGuildById(guildid));
			
			this.controller.put(guildid, mc);
		}
		
		return mc;
	}
	
	public long getGuildByPlayerHash(int hash) {
		for(MusicController controller : this.controller.values()) {
			if(controller.getPlayer().hashCode() == hash) {
				return controller.getGuild().getIdLong();
			}
		}
		
		return -1;
	}
}
