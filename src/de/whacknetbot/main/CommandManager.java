package de.whacknetbot.main;

import de.whacknetbot.bansystem.BanSystem;
import de.whacknetbot.bansystem.UnBanSystem;
import de.whacknetbot.commands.*;
import de.whacknetbot.commands.types.ServerCommand;
import de.whacknetbot.music.commands.PlayCommand;
import de.whacknetbot.music.commands.ShuffelCommand;
import de.whacknetbot.music.commands.StopCommand;
import de.whacknetbot.music.commands.TrackInfoCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.ConcurrentHashMap;
/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class CommandManager {

	public ConcurrentHashMap<String, ServerCommand> commands;
	
	public CommandManager() {
		this.commands = new ConcurrentHashMap<>();

		this.commands.put("hug", new HugCommand());
        this.commands.put("info", new ClientInfo());
        this.commands.put("sql", new SQLCommand());
        this.commands.put("preview", new PreviewCommand());
        this.commands.put("react", new ReactCommand());
        this.commands.put("reactrole", new ReactRolesCommand());
        this.commands.put("timerank", new TimeRank());
        this.commands.put("statchannel", new StatschannelCommand());
        this.commands.put("admininvite", new AdminInvite());
        this.commands.put("createrole", new RoleCreation());
        this.commands.put("ban", new BanSystem());
        this.commands.put("unban", new UnBanSystem());
        this.commands.put("indmsg", new IndMsg());

        this.commands.put("play", new PlayCommand());
        this.commands.put("stop", new StopCommand());
        this.commands.put("ti", new TrackInfoCommand());
        this.commands.put("shuffel", new ShuffelCommand());
    }
	
	public boolean perform(String command, Member m, TextChannel channel, Message message) {
		
		ServerCommand cmd;
		if((cmd = this.commands.get(command.toLowerCase())) != null) {
			cmd.performCommand(m, channel, message);
			return true;
		}
		
		return false;
	}
}
