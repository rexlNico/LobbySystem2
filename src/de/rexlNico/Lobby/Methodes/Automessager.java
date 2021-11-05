package de.rexlNico.Lobby.Methodes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import de.rexlNico.Lobby.Main.Main;

public class Automessager {

	private int TaskID;
	private int msg = 0;
	
	@SuppressWarnings("deprecation")
	public Automessager(){
		File file = configAPI.Serverfile;
		YamlConfiguration cfg = configAPI.servercfg;
		
		if(cfg.getStringList("automessage") == null){
			List<String> list = new ArrayList<>();
			list.add("TEXT 1");
			list.add("TEXT 2");
			cfg.set("automessage", list);
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		List<String> list = cfg.getStringList("automessage");
		
		TaskID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				Bukkit.broadcastMessage(Var.pr+"§c"+list.get(msg).replaceAll("&", "§"));
				if(msg >= list.size()){
					msg = 0;
				}else{
					msg++;
				}
				
			}
		}, 1, 20*300);
		
	}
	
}
