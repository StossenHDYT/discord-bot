package de.whacknetbot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import de.whacknetbot.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class AudioLoadResult implements AudioLoadResultHandler {

	private final MusicController controller;
	private final String uri;
	
	public AudioLoadResult(MusicController controller, String uri) {
		this.controller = controller;
		this.uri = uri;
	}
	
	@Override
	public void trackLoaded(AudioTrack track) {
		controller.getPlayer().playTrack(track);
		
		TextChannel tc;
		if((tc = MusicUtil.getMusicChannel(controller.getGuild().getIdLong())) != null) {
			EmbedBuilder msgInfo = new EmbedBuilder();
			msgInfo.setTitle("**Musik**");
			msgInfo.setDescription("\n Die Musik spielt nun!.");
			msgInfo.setColor(Main.grün);
			msgInfo.setFooter("WhackNet", Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
			tc.sendMessage(msgInfo.build()).queue();
		}
	}

	@Override
	public void playlistLoaded(AudioPlaylist playlist) {
		Queue queue = controller.getQueue();
		
		if(uri.startsWith("ytsearch: ")) {
			queue.addTrackToQueue(playlist.getTracks().get(0));
			return;
		}
		
		int added = 0;
		
		for(AudioTrack track : playlist.getTracks()) {
			queue.addTrackToQueue(track);
			added++;
		}
		
		EmbedBuilder builder = new EmbedBuilder().setColor(Color.decode("#8c14fc"))
				.setDescription(added + " tracks added to queue");
		
		MusicUtil.sendEmbed(controller.getGuild().getIdLong(), builder);
	}

	@Override
	public void noMatches() {
		
	}

	@Override
	public void loadFailed(FriendlyException exception) {
	
	}

}
