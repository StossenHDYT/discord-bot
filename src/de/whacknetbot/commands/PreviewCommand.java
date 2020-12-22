package de.whacknetbot.commands;

import de.whacknetbot.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class PreviewCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		
		
		//!preview `fdfsx` 
		
		String mess = message.getContentRaw().substring(9);
		
		EmbedBuilder builder = new EmbedBuilder();
		
		builder.setDescription(mess);
		builder.setColor(0xeb974e);
		
		message.delete().queue();
		channel.sendMessage(builder.build()).queue();
	}

}
