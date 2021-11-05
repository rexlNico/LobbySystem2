package de.rexlNico.Lobby.Methodes;

import java.io.File;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class configAPI {

	public static File Serverfile = new File("plugins/Lobby/config.yml");
	public static YamlConfiguration servercfg = YamlConfiguration.loadConfiguration(Serverfile);
	
	public static File Locationfile = new File("plugins/Lobby/locations.yml");
	public static YamlConfiguration locationcfg = YamlConfiguration.loadConfiguration(Locationfile);
	
	
	
	public static YamlConfiguration Playercfg(Player p){
		
		File pfile = new File("plugins/Lobby/"+p.getUniqueId().toString()+".yml");
		YamlConfiguration pcfg = YamlConfiguration.loadConfiguration(pfile);
		
		return pcfg;
	}
	public static File Playerfile(Player p){
		File pfile = new File("plugins/Setings/Lobby/"+p.getUniqueId().toString()+".yml");
		return pfile;
	}
	
	public static YamlConfiguration PlayerOfflineCfg(OfflinePlayer p){
		
		File pfile = new File("plugins/Setings/Lobby/"+p.getUniqueId().toString()+".yml");
		YamlConfiguration pcfg = YamlConfiguration.loadConfiguration(pfile);
		
		return pcfg;
	}
	public static File PlayerOfflineFile(OfflinePlayer p){
		File pfile = new File("plugins/Setings/Lobby/"+p.getUniqueId().toString()+".yml");
		return pfile;
	}
	
}
