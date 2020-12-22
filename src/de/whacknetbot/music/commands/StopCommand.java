package de.whacknetbot.music.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

import de.whacknetbot.main.Main;
import de.whacknetbot.commands.types.ServerCommand;
import de.whacknetbot.music.MusicController;
import de.whacknetbot.music.MusicUtil;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class StopCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		GuildVoiceState state;
		if((state = m.getVoiceState()) != null) {
			VoiceChannel vc;
			if((vc = state.getChannel()) != null) {
				MusicController controller = Main.INSTANCE.playerManager.getController(vc.getGuild().getIdLong());
				AudioManager manager = vc.getGuild().getAudioManager();
				AudioPlayer player = controller.getPlayer();
				MusicUtil.updateChannel(channel);
				player.stopTrack();
				manager.closeAudioConnection();
				message.addReaction("U+1F44C").queue();
			}
		}	
	}
}
