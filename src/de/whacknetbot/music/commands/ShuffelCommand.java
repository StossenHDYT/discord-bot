package de.whacknetbot.music.commands;

import de.whacknetbot.main.Main;
import de.whacknetbot.commands.types.ServerCommand;
import de.whacknetbot.music.MusicController;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class ShuffelCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		GuildVoiceState state;
		if((state = m.getVoiceState()) != null) {
			VoiceChannel vc;
			if((vc = state.getChannel()) != null) {
				MusicController controller = Main.INSTANCE.playerManager.getController(vc.getGuild().getIdLong());
				controller.getQueue().shuffel();
				message.addReaction("U+1F500").queue();
			}
		}
	}
}
