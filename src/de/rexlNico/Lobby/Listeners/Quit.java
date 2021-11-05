package de.rexlNico.Lobby.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.rexlNico.Lobby.Methodes.FreundeApi;
import de.rexlNico.Lobby.Methodes.Var;

public class Quit implements Listener{

	@EventHandler
	public void on(PlayerQuitEvent e){
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		
		for(int i = 0; i< FreundeApi.onlineFriends(p).size(); i++){
			Player o = FreundeApi.onlineFriends(p).get(i);
			o.sendMessage(Var.pr+"§cDein Freund §6"+p.getName()+" §cist nun §4Offline.");
		}
		
		//partyApi.remove(p);
		
	}
	
}
