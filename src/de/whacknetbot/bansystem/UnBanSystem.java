package de.whacknetbot.bansystem;

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

public class UnBanSystem implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        String[] args = message.getContentDisplay().split(" ");
        if (m.hasPermission(Permission.BAN_MEMBERS)) {


            if (args.length == 2) {
                try {
                    channel.getGuild().unban(args[1]).queue();


                    EmbedBuilder error = new EmbedBuilder();
                    error.setTitle("**Ban-System**");
                    error.setDescription("Du hast den User: " + args[1] + " erfoglreich für entsperrt");
                    error.setColor(Main.grün);
                    error.setFooter("WhackNet", "" + Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
                    channel.sendMessage(error.build()).queue();


                } catch (Exception e) {
                    EmbedBuilder error = new EmbedBuilder();
                    error.setTitle("**Ban-System**");
                    error.setDescription("Irgendetwas ist schief gelaufen!");
                    error.setColor(Main.rot);
                    error.setFooter("WhackNet", "" + Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
                    channel.sendMessage(error.build()).queue();


                }
            } else {
                EmbedBuilder error = new EmbedBuilder();
                error.setTitle("**Ban-System**");
                error.setDescription("'unban <userid>'");
                error.setColor(Main.rot);
                error.setFooter("WhackNet", "" + Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
                channel.sendMessage(error.build()).queue();

            }


        }


    }
}

