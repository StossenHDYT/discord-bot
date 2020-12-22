package de.whacknetbot.main;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import de.whacknetbot.commands.Commands;
import de.whacknetbot.commands.PermissionCommands;
import de.whacknetbot.commands.Spam;
import de.whacknetbot.commands.StatschannelCommand;
import de.whacknetbot.listener.*;
import de.whacknetbot.manage.Token;
import de.whacknetbot.manage.LiteSQL;
import de.whacknetbot.manage.SQLManager;
import de.whacknetbot.music.PlayerManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class Main {

	public static Main INSTANCE;

	public static String p = "'";
	//Um unnötige Fehler zu vermeiden bekommen die User einen anderen Prefix
	public static String userprefix = "+";
	public static String namedc = "**WhackNet** ";

	public static int rot = 0xDF0101;
	public static int gelb = 0xFFFF00;
	public static int grün = 0x00FF00;
	public static int blau = 0x0000FF;


	public FileWriter writer;
	public File file = new File("config.yml");
	
	public static ShardManager shardMan;
	private CommandManager cmdMan;
	private Thread loop;
	public AudioPlayerManager audioPlayerManager;
	public PlayerManager playerManager;
	
	public static void main(String[] args)  {
		try {
			new Main();
		} catch (LoginException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public Main() throws LoginException, IllegalArgumentException {
		INSTANCE = this;

		try {
			LiteSQL.connect();
			SQLManager.onCreate();
			System.out.println("Datenbankverbindung hergestellt!");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Datenenbank Error");
		}


		DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

		/**              Token - Token - Token - Token - Token - Token - Token - Token              **/

		builder.setToken(Token.token);

		/** TokenEnd - TokenEnd -  TokenEnd - TokenEnd - TokenEnd - TokenEnd - TokenEnd - TokenEnd **/

		//shardMan.setActivity(Activity.playing("WhackNet"));
		builder.setStatus(OnlineStatus.ONLINE);

		this.audioPlayerManager = new DefaultAudioPlayerManager();
		this.playerManager = new PlayerManager();

		this.cmdMan = new CommandManager();

		builder.setStatus(OnlineStatus.ONLINE);
		builder.addEventListeners(new PermissionCommands());
		builder.addEventListeners(new Commands());
		builder.addEventListeners(new Listener());
		builder.addEventListeners(new Spam());

		builder.addEventListeners(new CommandListener());
		builder.addEventListeners(new VoiceListener());
		builder.addEventListeners(new ReactionListener());
		builder.addEventListeners(new JoinListener());

		builder.setActivity(Activity.listening("LvckyRadio"));

		shardMan = builder.build();
		System.out.println("Bot online.");

		AudioSourceManagers.registerRemoteSources(audioPlayerManager);
		audioPlayerManager.getConfiguration().setFilterHotSwapEnabled(true);


		shutdown();
		try {
			System.out.println("Config wird geladen!");
			//Aktuelle Zeit
			GregorianCalendar now = new GregorianCalendar();
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);   // 14.04.12
			df = DateFormat.getDateInstance(DateFormat.MEDIUM);             // 14.04.2012
			df = DateFormat.getDateInstance(DateFormat.LONG);               // 14. April 2012
			System.out.println(df.format(now.getTime()));
			df = DateFormat.getTimeInstance(DateFormat.SHORT);              // 21:21
			df = DateFormat.getTimeInstance(DateFormat.MEDIUM);             // 21:21:12
			df = DateFormat.getTimeInstance(DateFormat.LONG);               // 21:21:12 MESZ
			System.out.println(df.format(now.getTime()));
			df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG); // 14.04.12 21:34:07 MESZ
			System.out.println(df.format(now.getTime()));


			writer = new FileWriter(file, true);
			writer.write(System.getProperty("line.separator"));
			writer.write("WhackNet Config");
			writer.write(System.getProperty("line.separator"));

			writer.write(df.format(now.getTime()));

			writer.flush();
			writer.close();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void shutdown() {
		
		new Thread(() -> {
			
			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				while((line = reader.readLine()) != null) {

					if (line.equalsIgnoreCase("stop") || line.equalsIgnoreCase("exit")) {
						shutdown = true;
						if (shardMan != null) {
							StatschannelCommand.onShutdown();
							shardMan.setStatus(OnlineStatus.OFFLINE);
							shardMan.shutdown();
							LiteSQL.disconnect();
							System.out.println("Bot offline.");
							shutdown();
						}
						
						if(loop != null) {
							loop.interrupt();
						}
						
						reader.close();
						break;
					}
					else if(line.equalsIgnoreCase("info")) {
						for(Guild guild : shardMan.getGuilds()) {
							System.out.println(guild.getName() + " " + guild.getIdLong());
						}
					}
					else {
						System.out.println("Use 'exit' to shutdown.");
					}
				}
			} catch (IOException e) {
				//e.printStackTrace();
			}
			
		}).start();
	}
	
	public boolean shutdown = false;
	public boolean hasStarted = false;
	
	public void runLoop() {
		this.loop = new Thread(() -> {
			
			long time = System.currentTimeMillis();
			
			while(!shutdown) {
				if(System.currentTimeMillis() >= time + 1000) {
					time = System.currentTimeMillis();
					onSecond();
				}
			}
		});
		this.loop.setName("Loop");
		this.loop.start();
	}
	
	
	String[] status = new String[] {"programmieren.", "Discord", "viele Spiele.", "%members online."};
	int[] colors = new int[] {0xff9478, 0xd2527f, 0x00b5cc, 0x19b5fe, 0x2ecc71, 0x23cba7, 0x00e640, 0x8c14fc, 0x9f5afd, 0x663399};
	int next = 30;
	
	public void onSecond() {
		//System.out.println("Next: " + next);
		
		if(next%5 == 0) {
			if(!hasStarted) {
				hasStarted = true;
				StatschannelCommand.onStartUP();
			}
			
			Random rand = new Random();
			
			int color = rand.nextInt(colors.length);
			for(Guild guild : shardMan.getGuilds()) {
				Role role = guild.getRoleById(608357590035857408l);
				role.getManager().setColor(colors[color]).queue();
			}
			
			
			int i = rand.nextInt(status.length);
			
			shardMan.getShards().forEach(jda -> {
				String text = status[i].replaceAll("%members", "" + jda.getUsers().size());
				
				jda.getPresence().setActivity(Activity.playing(text));
			});
			
			StatschannelCommand.checkStats();
			
			if(next == 0) {
				next = 60;
				
				onCheckTimeRanks();
			}
			else {
				next--;
			}
		}
		else {
			next--;
		}
	}
	
	public void onCheckTimeRanks() {
		try {
			ResultSet set = LiteSQL.onQueryRAW("SELECT userid, guildid FROM timeranks WHERE ((julianday(CURRENT_TIMESTAMP) - julianday(time)) * 1000) >= 1");
			List<Long> users = new ArrayList<>();
			//int count = 0;
			while(set.next()) {
				long userid = set.getLong("userid");
				long guildid = set.getLong("guildid");

				Guild guild = shardMan.getGuildById(guildid);
				guild.removeRoleFromMember(guild.getMemberById(userid), guild.getRoleById(582279555021012992l)).complete();
				System.out.println("Role entfernt.");

				users.add(userid);

				//count++;
			}
			
			for(long userid : users) {
				LiteSQL.onUpdate("DELETE FROM timeranks WHERE userid = " + userid);
			}
			
			//System.out.println(count + " Rolen entfernt.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public CommandManager getCmdMan() {
		return cmdMan;
	}
}
