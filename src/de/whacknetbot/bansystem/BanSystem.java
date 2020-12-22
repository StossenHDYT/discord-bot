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

public class BanSystem implements ServerCommand {
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        String[] args = message.getContentDisplay().split(" ");
        if (m.hasPermission(Permission.BAN_MEMBERS)) {


            if (args.length == 4) {
                try {
                    channel.getGuild().ban(args[1], Integer.parseInt(args[2])).queue();

                    EmbedBuilder error = new EmbedBuilder();
                    error.setTitle("**Ban-System**");
                    error.setDescription("Du hast den User: " + args[1] + " erfoglreich für " + args[2] + " Tage/n gebannt!");
                    error.setColor(Main.grün);
                    error.setFooter("WhackNet", "" + Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
                    channel.sendMessage(error.build()).queue();

                    EmbedBuilder error1 = new EmbedBuilder();
                    error1.setTitle("**Ban-System**");
                    error1.setDescription("Der User " + args[1] + " wurde von " + m.getNickname() + " für " + args[2] + " gebannt.\nGrund: " + args[3]);
                    error1.setColor(Main.grün);
                    error1.setFooter("WhackNet", "" + Main.shardMan.getUserById("724344054128705586").getAvatarUrl());


                    Main.shardMan.getTextChannelById("724545547737890846").sendMessage(error1.build()).queue();

                    Main.shardMan.getUserById(args[1]).openPrivateChannel().complete().sendMessage("Du wurdest von " + m.getNickname() + " gebannt!\nGrund:" + " " + args[3]).queue();

                } catch (Exception e) {
                    EmbedBuilder error = new EmbedBuilder();
                    error.setTitle("**Ban-System**");
                    error.setDescription("Irgendetwas ist schief gelaufen!");
                    error.setColor(Main.rot);
                    error.setFooter("WhackNet", "" + Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
                    channel.sendMessage(error.build()).queue();
                    e.printStackTrace();

                }
            } else {
                System.out.println("HLLLLLLLLL");
            }


        }


    }
}

