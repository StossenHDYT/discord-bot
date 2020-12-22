package de.whacknetbot.commands;

import de.whacknetbot.commands.types.ServerCommand;
import de.whacknetbot.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;
/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class HugCommand implements ServerCommand {

	
	private ConcurrentHashMap<Long, Long> timestamps;
	
	public HugCommand() {
		this.timestamps = new ConcurrentHashMap<>();
	}
	
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		long id = m.getIdLong();
		if(timestamps.containsKey(id)) {
			long time = timestamps.get(id);
			
			if((System.currentTimeMillis() - time) >= 30000) {
				this.timestamps.put(id, System.currentTimeMillis());
				send(m, channel, message);
			}
			else {
				DecimalFormat df = new DecimalFormat("0.00");

				EmbedBuilder lol = new EmbedBuilder();
				lol.setTitle("**Umarmung :(**");
				lol.setDescription("Du musst noch " + df.format((30000.0d - (System.currentTimeMillis() - time))/1000.0d) + " Sekunden warten");
				lol.setFooter("WhackNet", Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
				lol.setColor(Main.rot);

			    channel.sendMessage(lol.build()).queue();
			}
		}
		else {
			this.timestamps.put(id, System.currentTimeMillis());
			send(m, channel, message);
		}
	}
	
	public void send(Member m, TextChannel channel, Message message) {

		EmbedBuilder lol = new EmbedBuilder();
		lol.setTitle("**Umarmung :(**");
		lol.setDescription(m.getAsMention() + " umarmt sich selbst. (*Der arme Dude*)");
		lol.setFooter("WhackNet", Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
		lol.setColor(Main.grün);

		channel.sendMessage(lol.build()).queue();
	}
	
}
