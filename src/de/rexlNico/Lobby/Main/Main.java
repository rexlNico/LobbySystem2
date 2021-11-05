package de.rexlNico.Lobby.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.rexlNico.Lobby.Commands.Build;
import de.rexlNico.Lobby.Commands.SetMody;
import de.rexlNico.Lobby.Listeners.BuildBreak;
import de.rexlNico.Lobby.Listeners.Damage;
import de.rexlNico.Lobby.Listeners.Join;
import de.rexlNico.Lobby.Listeners.Options;
import de.rexlNico.Lobby.Listeners.Quit;

import de.rexlNico.Lobby.Listeners.PlayerHider;
import de.rexlNico.Lobby.Listeners.SHub;
import de.rexlNico.Lobby.Listeners.Teleporter;
import de.rexlNico.Lobby.Listeners.WeatherListener;
import de.rexlNico.Lobby.Methodes.Automessager;
import de.rexlNico.Lobby.Methodes.Factory;
import de.rexlNico.Lobby.Methodes.NickAPI;
import de.rexlNico.Lobby.Methodes.Var;
import de.rexlNico.Lobby.Methodes.configAPI;

public class Main extends JavaPlugin implements PluginMessageListener{

	public static Main plugin;
	private PluginManager pm = Bukkit.getPluginManager();
	public static String server = "";
	@Override
	public void onEnable() {
		plugin = this;
		cfgToList();
		register();
		Bukkit.getConsoleSender().sendMessage("§erexlLobby §aAktiviert");
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		
	
		
		NickAPI.nickToList();
	}
		
	@Override
	  public void onPluginMessageReceived(String channel, Player player, byte[] message) {
	    if (!channel.equals("BungeeCord")) {
	      return;
	    }
	    ByteArrayDataInput in = ByteStreams.newDataInput(message);
	    String subchannel = in.readUTF();
	    if (subchannel.equals("SomeSubChannel")) {
	    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
	    	out.writeUTF("GetServer");
	    	server = in.readUTF();
	    }
	  }
	
	public static Main getPlugin() {
		return plugin;
	}
	
	public void register(){
		pm.registerEvents(new BuildBreak(), this);
		pm.registerEvents(new Teleporter(), this);
		pm.registerEvents(new Join(), this);
		pm.registerEvents(new PlayerHider(), this);
		pm.registerEvents(new SHub(), this);
		pm.registerEvents(new WeatherListener(), this);
		pm.registerEvents(new Quit(), this);
		pm.registerEvents(new Options(), this);
		pm.registerEvents(new Damage(), this);
		
		
		getCommand("build").setExecutor(new Build());
		getCommand("setmody").setExecutor(new SetMody());
		
		
	}
	
	public static void cfgToList(){
		if(!configAPI.Locationfile.exists()){
			Location loc = new Location(Bukkit.getWorld("world"), 0, 0, 0);
			Var.spawns.put("HaS", loc);
			Var.spawns.put("KFFA", loc);
			Var.spawns.put("TTT", loc);
			Var.spawns.put("SkyWars", loc);
			Var.spawns.put("BedWars", loc);
			Var.spawns.put("Spawn", loc);
			Var.spawns.put("LoM", loc);
			return;
		}
		Var.spawns.put("HaS", Factory.getConfigLocation("HaS", configAPI.locationcfg));
		Var.spawns.put("KFFA", Factory.getConfigLocation("K-FFA", configAPI.locationcfg));
		Var.spawns.put("TTT", Factory.getConfigLocation("TTT", configAPI.locationcfg));
		Var.spawns.put("SkyWars", Factory.getConfigLocation("SW", configAPI.locationcfg));
		Var.spawns.put("BedWars", Factory.getConfigLocation("BW", configAPI.locationcfg));
		Var.spawns.put("Spawn", Factory.getConfigLocation("Spawn", configAPI.locationcfg));
		Var.spawns.put("LoM", Factory.getConfigLocation("Lom", configAPI.locationcfg));
	}
	
	
}
