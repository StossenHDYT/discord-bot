package de.whacknetbot.listener;

import de.whacknetbot.games.CounterGame;
import de.whacknetbot.main.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class CommandListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		String message = event.getMessage().getContentDisplay();
		
		if(event.isFromType(ChannelType.TEXT)) {
			TextChannel channel = event.getTextChannel();

			//!tut arg0 arg1 arg2 ...
			if (message.startsWith("'")) {
				String[] args = message.substring(1).split(" ");



				if (args.length > 0) {
					if (!Main.INSTANCE.getCmdMan().perform(args[0], event.getMember(), channel, event.getMessage())) {
						channel.sendMessage("`Unbekanntes Commando`").queue();
					}
				}
			}
			
			
			if(channel.getIdLong() == 593768981135884309l) {
				CounterGame.countUpdate(channel, event.getMessage());
			}
		}
		
	}
	
	
	
}
