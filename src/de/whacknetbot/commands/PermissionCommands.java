/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/


package de.whacknetbot.commands;

import de.whacknetbot.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PermissionCommands extends ListenerAdapter {


    public static boolean debug = true;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentDisplay().split(" ");
        if (event.getMessage().getContentDisplay().startsWith("'")) {
            if (event.getMember().hasPermission(Permission.MESSAGE_TTS)) {
                if (event.getMessage().isFromType(ChannelType.TEXT)) {

                    if (debug == true) {
                        String msg = String.valueOf(event.getMessage());

                        EmbedBuilder msgdel = new EmbedBuilder();
                        msgdel.setColor(0xff3923);
                        msgdel.setTitle("Commands Log");
                        msgdel.setDescription("Benutzer: " + event.getAuthor().getAsTag() + "\nCMD: " + event.getMessage());
                        msgdel.setFooter("Nachricht-ID: " + event.getMessage().getId(), event.getAuthor().getAvatarUrl());
                        Main.INSTANCE.shardMan.getTextChannelById("720232015571582976").sendMessage(msgdel.build()).queue();

                        try {

                            Main.INSTANCE.writer = new FileWriter(Main.INSTANCE.file, true);
                            Main.INSTANCE.writer.write(event.getMember() + " hat Versucht den Command " + event.getMessage() + " zu benutzen");
                            Main.INSTANCE.writer.write(System.getProperty("line.separator"));

                            Main.INSTANCE.writer.flush();
                            Main.INSTANCE.writer.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (args[0].equalsIgnoreCase(Main.p + "petrick")) {

                        event.getChannel().sendMessage("https://memegenerator.net/img/instances/67448087/nein-hier-ist-patrick.jpg").queue();
                        event.getMessage().delete().queue();

                    } else if (args[0].equalsIgnoreCase(Main.p + "clear")) {
                        if (args.length < 2) {
                            //error
                            event.getChannel().sendMessage(Main.namedc + "'clear <1-98>").queue();
                        } else {
                            try {

                                int msg = Integer.parseInt(args[1]);


                                EmbedBuilder msgdel = new EmbedBuilder();
                                msgdel.setColor(0xff3923);
                                msgdel.setTitle("Nachrichten werden gelöscht");
                                msgdel.setDescription("In 6 sekunden wird der Chat gelöscht");
                                event.getChannel().sendMessage(msgdel.build()).queue();
                                //Timer Unit
                                TimeUnit.SECONDS.sleep(3);
                                //Nachrichten getten
                                List<Message> messages = event.getChannel().getHistory().retrievePast(msg + 2).complete();
                                //Timer Unit
                                TimeUnit.SECONDS.sleep(3);

                                event.getChannel().deleteMessages(messages).queue();


                            }catch (Exception e){
                                e.printStackTrace();
                                if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")){
                                    //To many Messages
                                    EmbedBuilder error = new EmbedBuilder();
                                    error.setColor(0xff3923);
                                    error.setTitle("Zu viele Nachrichten ausgewählt");
                                    error.setDescription("Du kannst nur 98 nachrichten aufeinmal löschen!");
                                    event.getChannel().sendMessage(error.build()).queue();
                                } else {
                                    //To old Messages
                                    EmbedBuilder error2 = new EmbedBuilder();
                                    error2.setColor(0xff3923);
                                    error2.setTitle("Nachrichten zu alt");
                                    error2.setDescription("Nachrichten die älter als 2 Wochen alt sind können nicht gelöscht werden!");
                                    event.getChannel().sendMessage(error2.build()).queue();
                                }
                            }
                        }

                    } else if (args[0].equalsIgnoreCase(Main.p+"ip")){
                        // Returns the instance of InetAddress containing
                        // local host name and address
                        InetAddress localhost = null;
                        try {
                            localhost = InetAddress.getLocalHost();
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }

                        // Find public IP address
                        String systemipaddress = "";
                        try {
                            URL url_name = new URL("http://bot.whatismyipaddress.com");

                            BufferedReader sc =
                                    new BufferedReader(new InputStreamReader(url_name.openStream()));

                            // reads system IPAddress
                            systemipaddress = sc.readLine().trim();
                        } catch (Exception e) {
                            systemipaddress = "Cannot Execute Properly";
                        }
                        //Nachrichten Senden
                        EmbedBuilder IP = new EmbedBuilder();
                        IP.setColor(0x00ab44);
                        IP.setTitle("**IP**");
                        IP.setDescription("**Public:** " + systemipaddress + "\n" + "**Local:** " + localhost);
                        event.getChannel().sendMessage(IP.build()).queue();


                    } else if (args[0].equalsIgnoreCase(Main.p+"status")){

                        if (debug == true){
                            //Nachrichten Senden
                            EmbedBuilder msg1 = new EmbedBuilder();
                            msg1.setColor(0x00ab44);
                            msg1.setTitle("**DEBUG-ON**");
                            msg1.setDescription("");
                            event.getChannel().sendMessage(msg1.build()).queue();
                        }else if (debug == false){
                            //Nachrichten Senden
                            EmbedBuilder msg2 = new EmbedBuilder();
                            msg2.setColor(0x00ab44);
                            msg2.setTitle("**DEBUG-OFF**");
                            msg2.setDescription("");
                            event.getChannel().sendMessage(msg2.build()).queue();
                        }


                    }else if (args[0].equalsIgnoreCase(Main.p+"debug")){
                        if (debug == true){
                            debug = false;
                            //MESSAGE
                            EmbedBuilder msg2 = new EmbedBuilder();
                            msg2.setColor(0x00ab44);
                            msg2.setTitle("**DEBUG-OFF**");
                            msg2.setDescription("Debug-Mode ist Off gestellt");
                            event.getChannel().sendMessage(msg2.build()).queue();
                        } else {
                            debug = true;
                            //MESSAGE
                            EmbedBuilder msg2 = new EmbedBuilder();
                            msg2.setColor(0x00ab44);
                            msg2.setTitle("**DEBUG-ON**");
                            msg2.setDescription("Debug-Mode ist On gestellt");
                            event.getChannel().sendMessage(msg2.build()).queue();
                        }
                    }
                } else {

                }
            } else {
                if (debug == true){
                    String msg = String.valueOf(event.getMessage());
                    Main.INSTANCE.shardMan.getTextChannelById("720232015571582976").sendMessage("Der Spieler: " + "**" + event.getAuthor().getName() + "**" + " hat Versucht den CMD   " + msg + "   zu benutzen!").queue();

                    event.getMessage().delete().queue();
                    event.getAuthor().openPrivateChannel().complete().sendMessage("Den Command kannst du nicht benutzen. ").queue();
                }


            }

        } else {

        }

    }


}
