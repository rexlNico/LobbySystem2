package de.rexlNico.Lobby.Methodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class FreundeApi {

	public static void addFreund(Player p, Player t){
		if(configAPI.servercfg.getString("Player").contains(t.getUniqueId().toString())){
			YamlConfiguration pcfg = configAPI.Playercfg(p);
			YamlConfiguration tcfg = configAPI.Playercfg(t);
			if(t == p){
				p.sendMessage(Var.pr+"§cDu kannst dir keine Anfrage senden.");
				return;
			}
			if(!pcfg.getString("Freunde.list").contains(t.getUniqueId().toString())){
				if(tcfg.getString("Freunde.request").contains(p.getUniqueId().toString())){
					p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+" §cschon eine anfrage gesendet!");
					return;
				}
				
				tcfg.set("Freunde.request", tcfg.getString("Freunde.request")+p.getUniqueId().toString()+" , ");
				try {
					tcfg.save(configAPI.Playerfile(t));
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+"§c eine Anfrage gesendet.");
				if(t.isOnline()){
					t.sendMessage(Var.pr+"§cDu hast von §6"+p.getName()+" §ceine Freundesanfrage bekommen.");
					t.sendMessage(Var.pr+"§cDu kannst sie mit §e/friend accept "+p.getName()+" §cannehmen.");
				}
			}else{
				p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+"§cschon als Freund!");
			}
		}else{
			p.sendMessage(Var.pr+"§cDer Spieler §6"+t.getName()+" §cist uns nicht bekannt!");
		}
	}
	
	public static void addOfflineFreund(Player p, OfflinePlayer t){
		if(configAPI.servercfg.getString("Player").contains(t.getUniqueId().toString())){
			YamlConfiguration pcfg = configAPI.Playercfg(p);
			YamlConfiguration tcfg = configAPI.PlayerOfflineCfg(t);
			if(t == p){
				p.sendMessage(Var.pr+"§cDu kannst dir keine Anfrage senden.");
				return;
			}
			if(!pcfg.getString("Freunde.list").contains(t.getUniqueId().toString())){
				if(tcfg.getString("Freunde.request").contains(p.getUniqueId().toString())){
					p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+" §cschon eine anfrage gesendet!");
					return;
				}
				
				tcfg.set("Freunde.request", tcfg.getString("Freunde.request")+p.getUniqueId().toString()+" , ");
				try {
					tcfg.save(configAPI.PlayerOfflineFile(t));
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+"§c eine Anfrage gesendet.");
			}else{
				p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+"§cschon als Freund!");
			}
		}else{
			p.sendMessage(Var.pr+"§cDer Spieler §6"+t.getName()+" §cist uns nicht bekannt!");
		}
	}
	
	public static void removeFreund(Player p, Player t){
		
			YamlConfiguration pcfg = configAPI.Playercfg(p);
			YamlConfiguration tcfg = configAPI.Playercfg(t);
			if(t == p){
				p.sendMessage(Var.pr+"§cDu kannst dich nicht als Freund haben.");
				return;
			}
			if(pcfg.getString("Freunde.list").contains(t.getUniqueId().toString())){
				
				pcfg.set("Freunde.list", pcfg.getString("Freunde.list").replace(t.getUniqueId().toString()+" , ", ""));
				try {
					pcfg.save(configAPI.Playerfile(p));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				tcfg.set("Freunde.list", tcfg.getString("Freunde.list").replace(p.getUniqueId().toString()+" , ", ""));
				try {
					tcfg.save(configAPI.Playerfile(t));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+"§c von deinen freunden entfernt.");
				
			}else{
				p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+"§c nicht als Freund!");
			}
		
	}
	
	public static void removeOfflineFreund(Player p, OfflinePlayer t){
		
		YamlConfiguration pcfg = configAPI.Playercfg(p);
		YamlConfiguration tcfg = configAPI.PlayerOfflineCfg(t);
		if(t == p){
			p.sendMessage(Var.pr+"§cDu kannst dich nicht als Freund haben.");
			return;
		}
		if(pcfg.getString("Freunde.list").contains(t.getUniqueId().toString())){
			
			pcfg.set("Freunde.list", pcfg.getString("Freunde.list").replace(t.getUniqueId().toString()+" , ", ""));
			try {
				pcfg.save(configAPI.Playerfile(p));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			tcfg.set("Freunde.list", tcfg.getString("Freunde.list").replace(p.getUniqueId().toString()+" , ", ""));
			try {
				tcfg.save(configAPI.PlayerOfflineFile(t));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+"§c von deinen freunden entfernt.");
			
		}else{
			p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+"§c nicht als Freund!");
		}
	
}
	
	public static String RequestsInString(Player p){
		YamlConfiguration pcfg = configAPI.Playercfg(p);
		String request;
		request = pcfg.getString("Freunde.request");
		
		return request;
	}
	
	public static ArrayList<String> RequestsInList(Player p){
		YamlConfiguration pcfg = configAPI.Playercfg(p);
		String[] request;
		request = pcfg.getString("Freunde.request").split(",");
		ArrayList<String> ret = new ArrayList<>(Arrays.asList(request));
		
		return ret;
	}
	
	public static ArrayList<Player> onlineFriends(Player p){
		
		ArrayList<Player> online = new ArrayList<>();
		YamlConfiguration pcfg = configAPI.Playercfg(p);
		for(Player a : Bukkit.getOnlinePlayers()){
			if(pcfg.getString("Freunde.list").contains(a.getUniqueId().toString())){
				online.add(a);
			}
		}
		
		return online;
	}
	
	public static void acceptRequest(Player p, Player t){
		YamlConfiguration pcfg = configAPI.Playercfg(p);
		YamlConfiguration tcfg = configAPI.Playercfg(t);
		String requests = RequestsInString(p);
		
		if(requests.contains(t.getUniqueId().toString())){
			
			pcfg.set("Freunde.request", pcfg.getString("Freunde.request").replace(t.getUniqueId().toString()+" , ", ""));
			pcfg.set("Freunde.list", pcfg.getString("Freunde.list")+t.getUniqueId().toString()+" , ");
			try {
				pcfg.save(configAPI.Playerfile(p));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tcfg.set("Freunde.list", tcfg.getString("Freunde.list")+p.getUniqueId().toString()+" , ");
			try {
				tcfg.save(configAPI.Playerfile(t));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			p.sendMessage(Var.pr+"§cDu hast die Anfrage von §6"+t.getName()+"§a angenommen.");
			if(t.isOnline()){
				t.sendMessage(Var.pr+"§6"+p.getName()+"§c hat deine Freundesanfrage §aangenommen");
			}
		}else{
			p.sendMessage(Var.pr+"§cDu hast keine Anfrage von §6"+t.getName()+"§c!");
		}
	}
	
	public static void acceptOfflineRequest(Player p, OfflinePlayer t){
		YamlConfiguration pcfg = configAPI.Playercfg(p);
		YamlConfiguration tcfg = configAPI.PlayerOfflineCfg(t);
		String requests = RequestsInString(p);
		
		if(requests.contains(t.getUniqueId().toString())){
			
			pcfg.set("Freunde.request", pcfg.getString("Freunde.request").replace(t.getUniqueId().toString()+" , ", ""));
			pcfg.set("Freunde.list", pcfg.getString("Freunde.list")+t.getUniqueId().toString()+" , ");
			try {
				pcfg.save(configAPI.Playerfile(p));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tcfg.set("Freunde.list", tcfg.getString("Freunde.list")+p.getUniqueId().toString()+" , ");
			try {
				tcfg.save(configAPI.PlayerOfflineFile(t));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			p.sendMessage(Var.pr+"§cDu hast die Anfrage von §6"+t.getName()+"§a angenommen.");
		}else{
			p.sendMessage(Var.pr+"§cDu hast keine Anfrage von §6"+t.getName()+"§c!");
		}
	}
	
	public static void deAcceptRequest(Player p, Player t){
		YamlConfiguration pcfg = configAPI.Playercfg(p);
		String requests = RequestsInString(p);
		
		if(requests.contains(t.getUniqueId().toString())){
			
			pcfg.set("Freunde.request", pcfg.getString("Freunde.request").replace(t.getUniqueId().toString()+" , ", ""));
			try {
				pcfg.save(configAPI.Playerfile(p));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.sendMessage(Var.pr+"§cDu hast die Anfrage von §6"+t.getName()+"§c abgelehnt.");
			if(t.isOnline()){
				t.sendMessage(Var.pr+"§6"+p.getName()+"§c hat deine Freundesanfrage §cabgelehnt");
			}
		}else{
			p.sendMessage(Var.pr+"§cDu hast keine Anfrage von §6"+t.getName()+"§c!");
		}
	}
	
	public static void deAcceptOfflineRequest(Player p, OfflinePlayer t){
		YamlConfiguration pcfg = configAPI.Playercfg(p);
		String requests = RequestsInString(p);
		
		if(requests.contains(t.getUniqueId().toString())){
			
			pcfg.set("Freunde.request", pcfg.getString("Freunde.request").replace(t.getUniqueId().toString()+" , ", ""));
			try {
				pcfg.save(configAPI.Playerfile(p));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.sendMessage(Var.pr+"§cDu hast die Anfrage von §6"+t.getName()+"§c abgelehnt.");
		}else{
			p.sendMessage(Var.pr+"§cDu hast keine Anfrage von §6"+t.getName()+"§c!");
		}
	}
}
