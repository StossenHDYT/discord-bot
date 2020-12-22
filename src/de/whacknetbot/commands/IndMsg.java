package de.whacknetbot.commands;

import de.whacknetbot.commands.types.ServerCommand;
import de.whacknetbot.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/

public class IndMsg implements ServerCommand {


    public void performCommand(Member m, TextChannel channel, Message message) {
        if (m.hasPermission(Permission.MESSAGE_TTS)){


            /** TextChannel ID **/
            String tchannel = "724184027782971452";

            /** Nachricht an Rollen ID **/
            String role = "717292340259323947";

            Main.shardMan.getTextChannelById(tchannel).sendMessage("**Moinsen,**" + Main.shardMan.getRoleById(role).getAsMention()).queue();
            EmbedBuilder indMsg = new EmbedBuilder();
            indMsg.setThumbnail("https://abload.de/img/animierte-gifs-linieneok9k.gif");
            indMsg.setColor(Main.gelb);
            indMsg.setTitle("**DURCHSAGE** \nvon " + m.getUser().getName());
            indMsg.setDescription("Test!");
            indMsg.setFooter("WhackNet - BOT", m.getUser().getAvatarUrl());



            Main.INSTANCE.shardMan.getTextChannelById(tchannel).sendMessage(indMsg.build()).queue();

        }


    }

}
