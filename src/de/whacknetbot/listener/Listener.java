
/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 */


package de.whacknetbot.listener;

import de.whacknetbot.commands.PermissionCommands;
import de.whacknetbot.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Listener extends ListenerAdapter {



    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        EmbedBuilder msgdel = new EmbedBuilder();
        msgdel.setColor(Main.grün);
        msgdel.setTitle("**Willkommen**");
        msgdel.setDescription("Willkommen " + event.getMember().getNickname() + "!");

        Main.shardMan.getTextChannelById("724349931552243832").sendMessage(msgdel.build()).queue();

        try {

            Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
            Main.INSTANCE.writer.write(event.getMember() + " ist gejoint");
            Main.INSTANCE.writer.write(System.getProperty("line.separator"));

            Main.INSTANCE.writer.flush();
            Main.INSTANCE.writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onGuildBan(GuildBanEvent event) {
        if (PermissionCommands.debug == true) {
            EmbedBuilder banplayer = new EmbedBuilder();
            banplayer.setColor(Main.rot);

            banplayer.setTitle("**BANNSYSTEM**");
            banplayer.setDescription(" Der User: " + event.getUser().getAsMention() + " wurde gebannt.");

            Main.shardMan.getTextChannelById("720232015571582976").sendMessage(banplayer.build()).queue();

            try {

                Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                Main.INSTANCE.writer.write(event.getUser() + " wurde gebannt");
                Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                Main.INSTANCE.writer.flush();
                Main.INSTANCE.writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {

                Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                Main.INSTANCE.writer.write(event.getUser() + " wurde gebannt");
                Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                Main.INSTANCE.writer.flush();
                Main.INSTANCE.writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void onGuildUnban(GuildUnbanEvent event) {
        if (PermissionCommands.debug == true) {
            EmbedBuilder banplayer = new EmbedBuilder();
            banplayer.setColor(Main.grün);
            banplayer.setTitle("**Entbannt**");
            banplayer.setDescription(" Der User: " + event.getUser().getAsMention() + " wurde entbannt.");

            Main.shardMan.getTextChannelById("720232015571582976").sendMessage(banplayer.build()).queue();

            try {

                Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                Main.INSTANCE.writer.write(event.getUser() + " wurde entbannt");
                Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                Main.INSTANCE.writer.flush();
                Main.INSTANCE.writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {

                Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                Main.INSTANCE.writer.write(event.getUser() + " wurde entbannt");
                Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                Main.INSTANCE.writer.flush();
                Main.INSTANCE.writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {

        List<Role> roles = event.getRoles();


        if (PermissionCommands.debug == true) {
            EmbedBuilder roleadd = new EmbedBuilder();
            roleadd.setColor(Main.gelb);
            roleadd.setTitle("**Role added**");
            roleadd.setFooter("RespnseNumer: " + event.getResponseNumber(), event.getUser().getAvatarUrl());
            roleadd.setDescription("Der User: " + event.getMember().getAsMention() + " wurde die Role " + roles.toString() + " gegeben!");

            Main.shardMan.getTextChannelById("720232015571582976").sendMessage(roleadd.build()).queue();

            try {

                Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                Main.INSTANCE.writer.write(event.getMember() + " wurde die Role " + roles.toString() + " gegeben");
                Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                Main.INSTANCE.writer.flush();
                Main.INSTANCE.writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {

                Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                Main.INSTANCE.writer.write(event.getMember() + " wurde die Role " + roles.toString() + " gegeben");
                Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                Main.INSTANCE.writer.flush();
                Main.INSTANCE.writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        if (PermissionCommands.debug == true) {
            EmbedBuilder rolerm = new EmbedBuilder();
            rolerm.setColor(Main.rot);
            rolerm.setTitle("**Role removed**");
            rolerm.setFooter("RespnseNumer: " + event.getResponseNumber(), event.getUser().getAvatarUrl());
            rolerm.setDescription("Der User: " + event.getMember().getAsMention() + " wurde die Role " + event.getRoles() + " genommen");
            Main.shardMan.getTextChannelById("720232015571582976").sendMessage(rolerm.build()).queue();


            try {

                Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                Main.INSTANCE.writer.write(event.getMember() + " wurde die Role " + event.getRoles() + " genommen");
                Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                Main.INSTANCE.writer.flush();
                Main.INSTANCE.writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {

                Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                Main.INSTANCE.writer.write(event.getMember() + " wurde die Role " + event.getRoles() + " genommen");
                Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                Main.INSTANCE.writer.flush();
                Main.INSTANCE.writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
        String newname = event.getNewNickname();
        String oldname = event.getOldNickname();


        if (PermissionCommands.debug == true) {
            EmbedBuilder namechange = new EmbedBuilder();
            namechange.setColor(Main.gelb);
            namechange.setTitle("**Namens Wechsel**");
            namechange.setFooter(event.getMember().getEffectiveName(), event.getUser().getAvatarUrl());
            namechange.setDescription("Alter Name: " + oldname + "\nNeuer Name: " + newname);
            Main.shardMan.getTextChannelById("720232015571582976").sendMessage(namechange.build()).queue();


            try {

                Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                Main.INSTANCE.writer.write("Names wechsel von: " + oldname + "     zu:     " + newname);
                Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                Main.INSTANCE.writer.flush();
                Main.INSTANCE.writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {

                Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                Main.INSTANCE.writer.write("Names wechsel von: " + oldname + " zu: " + newname);
                Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                Main.INSTANCE.writer.flush();
                Main.INSTANCE.writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
