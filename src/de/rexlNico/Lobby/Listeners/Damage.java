package de.rexlNico.Lobby.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements Listener{

	@EventHandler
	public void on(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			e.setCancelled(true);
		}
	}
	
}
