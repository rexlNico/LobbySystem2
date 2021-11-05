package de.rexlNico.Lobby.Listeners;

import java.awt.Event;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class BuildBreak implements Listener{

	public static ArrayList<Player> canbuild = new ArrayList<>();
	
	@EventHandler
	public void on(BlockPlaceEvent e){
		if(!canbuild.contains(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void on(BlockBreakEvent e){
		if(!canbuild.contains(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void on(PlayerDropItemEvent e){
		if(!canbuild.contains(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void on(PlayerPickupItemEvent e){
		if(!canbuild.contains(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void on(PlayerInteractEvent e){
		if(!canbuild.contains(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void on(PlayerArmorStandManipulateEvent e){
		
		if(!canbuild.contains(e.getPlayer())){
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void on(EntityDamageByEntityEvent e){
		if(!canbuild.contains( (Player) e.getDamager())){
			e.setCancelled(true);
		}
	}
	
}
