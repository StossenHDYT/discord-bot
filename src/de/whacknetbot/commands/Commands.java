/**
 * ©2016-2020 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/


package de.whacknetbot.commands;

import de.whacknetbot.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Commands extends ListenerAdapter {

    public static void random(String[] args) throws InterruptedException {
        String[] name = {"https://bit.ly/37QnksF", "Kevin", "Jana", "Anna"};

        Random zufall = new Random();


        for (int i = 0; i < name.length; i++) ;
        {
            System.out.println(name[zufall.nextInt(4)]);
        }
    }


    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentDisplay().split(" ");
        if (event.getMessage().getContentDisplay().startsWith("+")) {
            if (event.getMessage().isFromType(ChannelType.TEXT)) {

                if (args[0].equalsIgnoreCase(Main.userprefix + "info")) {
                    if (event.getMember().hasPermission(Permission.MESSAGE_TTS)) {
                        try {
                            TimeUnit.SECONDS.sleep(2);

                            EmbedBuilder infoMsg = new EmbedBuilder();
                            infoMsg.setColor(0xFFFF00);
                            infoMsg.setTitle("**INFO**\n");
                            infoMsg.setDescription("**WhackNet** \n **Alle Befehle:** \n\n  +info \n\n **Musik:**\n 'play <String-streamLink> (Spielt ein Lied oder einen Sender ab)\n 'stop (Stopp musik) \n 'ti (Gibt Infos über Songa aus) \n\n **Admin Commands:**\n 'clear <int> \n 'ip \n 'debug (Schaltet den Logmodus um) \n 'status (Zeigt den Debug Status) \n 'reactrole (Erstellt ReactRole)");
                            infoMsg.setFooter("WhackNet", Main.shardMan.getUserById("724344054128705586").getAvatarUrl());

                            event.getAuthor().openPrivateChannel().complete().sendMessage(infoMsg.build()).queue();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        event.getMessage().delete().queue();



                    } else {
                        EmbedBuilder infoMsg2 = new EmbedBuilder();
                        infoMsg2.setColor(0xFFFF00);
                        infoMsg2.setTitle("**INFO**");
                        infoMsg2.setDescription("Hallo " + event.getAuthor().getAsTag() + ", momentan kann ich folgende Befehle: \n\n +info \n weitere folgen. \n Ich wurde übrigens von Iven & Lukas erscahffen!");
                        infoMsg2.setFooter("WhackNet", Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
                        event.getChannel().sendMessage(infoMsg2.build()).queue();
                    }

                } else if (args[0].equalsIgnoreCase(Main.userprefix + "meme")) {
                    String[] name = {"https://bit.ly/37QnksF", "https://bit.ly/3hQlcFH", "https://bit.ly/2CnnY55", "https://bit.ly/3eBnWok", "https://bit.ly/2V9KHIt", "https://bit.ly/3hT3n92", "https://bit.ly/2Bqjs5r",
                            "https://bit.ly/3dvzeti", "https://bit.ly/2YpBqhG", "https://bit.ly/37TUkA1", "https://bit.ly/2zTmtL8", "https://bit.ly/37U61GU", "https://bit.ly/2Yr5WaW", "https://bit.ly/2AS7ulk",
                            "https://bit.ly/2CnpDYn", "https://bit.ly/2Yoji7L"};

                    Random zufall = new Random();


                    for (int i = 0; i < name.length; i++) ;
                    {

                        EmbedBuilder infoMsg2 = new EmbedBuilder();
                        infoMsg2.setColor(0xFFFF00);
                        infoMsg2.setTitle("**Meme-System**");
                        infoMsg2.setDescription("**Freshes Meme kommt in 2 Sekunden!**");
                        infoMsg2.setFooter("WhackNet", Main.shardMan.getUserById("724344054128705586").getAvatarUrl());
                        event.getChannel().sendMessage(infoMsg2.build()).queue();
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        event.getChannel().sendMessage(name[zufall.nextInt(16)]).queue();
                    }


                }

            } else {

            }
        } else {

        }

    }

}

