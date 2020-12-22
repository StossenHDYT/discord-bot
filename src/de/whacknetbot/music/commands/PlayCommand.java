package de.whacknetbot.music.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import de.whacknetbot.commands.types.ServerCommand;
import de.whacknetbot.main.Main;
import de.whacknetbot.music.AudioLoadResult;
import de.whacknetbot.music.MusicController;
import de.whacknetbot.music.MusicUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;

import java.awt.*;
/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class PlayCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		String[] args = message.getContentDisplay().split(" ");
		
		if(args.length > 1) {
			GuildVoiceState state;
			if((state = m.getVoiceState()) != null) {
				VoiceChannel vc;
				if((vc = state.getChannel()) != null) {
					MusicController controller = Main.INSTANCE.playerManager.getController(vc.getGuild().getIdLong());
					AudioPlayerManager apm = Main.INSTANCE.audioPlayerManager;
					AudioManager manager = vc.getGuild().getAudioManager();
					manager.openAudioConnection(vc);
					
					MusicUtil.updateChannel(channel);

					StringBuilder strBuilder = new StringBuilder();
					for (int i = 1; i < args.length; i++) strBuilder.append(args[i] + " ");

					String url = strBuilder.toString().trim();
					if (!url.startsWith("http")) {
						url = "ytsearch: " + url;
					}

					apm.loadItem(url, new AudioLoadResult(controller, url));
				} else {
					EmbedBuilder builder = new EmbedBuilder();
					builder.setTitle("**Musik**");
					builder.setDescription("Du bist in keinem VoiceChannel.");
					builder.setColor(Color.decode("#f22613"));
					builder.setFooter("WhackNet", "" + Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
					channel.sendMessage(builder.build()).queue();
				}
			} else {
				EmbedBuilder builder = new EmbedBuilder();
				builder.setTitle("**Musik**");
				builder.setDescription("Du bist in keinem VoiceChannel.");
				builder.setColor(Color.decode("#f22613"));
				builder.setFooter("WhackNet", "" + Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
				channel.sendMessage(builder.build()).queue();
			}
		} else {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setTitle("**Musik**");
			builder.setDescription("Bitte benutze !play <url/search query>");
			builder.setFooter("WhackNet", "" + Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
			builder.setColor(Color.decode("#f22613"));
			channel.sendMessage(builder.build()).queue();
		}
	}
}
