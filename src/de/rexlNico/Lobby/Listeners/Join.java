package de.rexlNico.Lobby.Listeners;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.rexlNico.Lobby.Main.Main;
import de.rexlNico.Lobby.Methodes.FreundeApi;
import de.rexlNico.Lobby.Methodes.ItemBuilder;
import de.rexlNico.Lobby.Methodes.Var;
import de.rexlNico.Lobby.Methodes.configAPI;

public class Join implements Listener{

	@EventHandler
	public void on(PlayerJoinEvent e){
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		
		p.getInventory().clear();
		p.getInventory().setItem(0, new ItemBuilder(Material.COMPASS, 1).setName("§6Teleporter").build());
		p.getInventory().setItem(7, new ItemBuilder(Material.CHEST, 1).setName("§5Options").build());
		
		YamlConfiguration cfg = configAPI.Playercfg(p);
		List<String> players = configAPI.servercfg.getStringList("Player");
		boolean bol = false;
		for(int i = 0; i<players.size(); i++){
			if(players.get(i).equalsIgnoreCase(p.getUniqueId().toString())){
				bol = true;
			}
		}
		if(bol == true){
			
			if(cfg.getString("PlayerHider").equals("Alle")){
				p.getInventory().setItem(8, new ItemBuilder(Material.INK_SACK, 1, 10).setName("§7Gezeigte Spieler§8: §aAlle").build());
			}else if(cfg.getString("PlayerHider").equals("Freunde")){
				p.getInventory().setItem(8, new ItemBuilder(Material.INK_SACK, 1, 5).setName("§7Gezeigte Spieler§8: §5Freunde").build());
				for(Player a : Bukkit.getOnlinePlayers()){
					if(!cfg.getString("Freunde").contains(a.getUniqueId().toString())){
						p.hidePlayer(a);
					}
				}
			}else if(cfg.getString("PlayerHider").equals("Keine")){
				p.getInventory().setItem(8, new ItemBuilder(Material.INK_SACK, 1, 1).setName("§7Gezeigte Spieler§8: §4Keine").build());
				for(Player a : Bukkit.getOnlinePlayers()){
					p.hidePlayer(a);
				}
			}
			
		}else{
			
			cfg.set("PlayerHider", "Alle");
			cfg.set("Freunde.list", "");
			cfg.set("Freunde.request", "");
			try {
				cfg.save(configAPI.Playerfile(p));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			p.getInventory().setItem(8, new ItemBuilder(Material.INK_SACK, 1, 10).setName("§7Gezeigte Spieler§8: §aAlle").build());
			players.add(p.getUniqueId().toString());
			configAPI.servercfg.set("Player", players);
			try {
				configAPI.servercfg.save(configAPI.Serverfile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		for(Player a : Bukkit.getOnlinePlayers()){
			if(configAPI.Playercfg(a).getString("PlayerHider").equals("Keine") || SHub.inShub.contains(a)){
				a.hidePlayer(p);
			}else if(configAPI.Playercfg(a).getString("PlayerHider").equals("Freunde")){
				if(!configAPI.Playercfg(a).getString("Freunde.list").contains(p.getUniqueId().toString())){
					a.hidePlayer(p);
				}
			}
		}
		if(p.hasPermission(Var.perms+"premium+") || p.hasPermission(Var.perms+"premiumItems")){
			
			p.getInventory().setItem(1, new ItemBuilder(Material.TNT, 1).setName("§3SilentHub §4Aus").build());
			p.getInventory().setItem(4, new ItemBuilder(Material.NAME_TAG, 1).setName("§eAutonick §4Aus").build());
			
		}
		if(!FreundeApi.RequestsInList(p).get(0).equals("")){
			p.sendMessage(Var.pr+"§cDu hast §6"+FreundeApi.RequestsInList(p).size()+" §cFreundesanfrage.");
		}
		String os = "";
		for(int i = 0; i< FreundeApi.onlineFriends(p).size(); i++){
			Player o = FreundeApi.onlineFriends(p).get(i);
			o.sendMessage(Var.pr+"§cDein Freund §6"+p.getName()+" §cist nun §aOnline.");
			if(i == 0){
				os = "§e"+o.getName();
			}else{
				os = os+"§8, §e"+o.getName();
			}
			
		}
		if(FreundeApi.onlineFriends(p).size() != 0){
		p.sendMessage(Var.pr+"§cEs sind gerade §6"+FreundeApi.onlineFriends(p).size()+" §cFreunde §aOnline§8: "+os);
		}else{
			p.sendMessage(Var.pr+"§cEs sind gerade §6"+FreundeApi.onlineFriends(p).size()+" §cFreunde §aOnline.");
		}
		p.teleport(Var.spawns.get("Spawn"));
	}
	
}
