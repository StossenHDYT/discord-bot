package de.whacknetbot.commands;

import de.whacknetbot.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/

public class Spam extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        try {
            String[] args = event.getMessage().getContentDisplay().split(" ");
            if (event.getMessage().getContentDisplay().startsWith("%")) {
                if (event.getMember().hasPermission(Permission.MESSAGE_TTS)) {
                    if (event.getMessage().isFromType(ChannelType.TEXT)) {
                        if (args[0].equalsIgnoreCase("%spam")) {

                           
                                try {

                                    EmbedBuilder SpamMsg = new EmbedBuilder();
                                    SpamMsg.setColor(Main.blau);
                                    SpamMsg.setTitle("**Spammanager**");
                                    SpamMsg.setDescription("Die Client ID: '" + args[1] + "' wird nun zu gespamt! \n @" + event.getAuthor().getAsTag());
                                    SpamMsg.setFooter("WhackNet", Main.shardMan.getUserById("724344054128705586").getAvatarUrl());

                                    event.getChannel().sendMessage(SpamMsg.build()).queue();


                                    EmbedBuilder Spam = new EmbedBuilder();
                                    Spam.setColor(Main.blau);
                                    Spam.setTitle("**Spammanager**");
                                    Spam.setDescription("Hallo, " + Main.shardMan.getUserById(args[1]).getAsMention() + "\n SPAM!");
                                    Spam.setFooter("WhackNet", Main.shardMan.getUserById("724344054128705586").getAvatarUrl());

                                    for (int i = 0; i < 100000; i++){
                                        Main.shardMan.getUserById(args[1]).openPrivateChannel().complete().sendMessage(Spam.build()).queue();
                                    }

                                } catch (Exception e) {

                                    EmbedBuilder sError = new EmbedBuilder();
                                    sError.setColor(Main.rot);
                                    sError.setTitle("**Spammanager**");
                                    sError.setDescription("Dieser User ist momentan offline! \n " + args[1]);
                                    sError.setFooter("WhackNet", Main.shardMan.getUserById("724344054128705586").getAvatarUrl());

                                    event.getChannel().sendMessage(sError.build()).queue();
                                    e.printStackTrace();
                                }



                        }


                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
