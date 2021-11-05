package de.rexlNico.Lobby.Listeners;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.rexlNico.Lobby.Methodes.ItemBuilder;
import de.rexlNico.Lobby.Methodes.configAPI;

public class PlayerHider implements Listener{

	@EventHandler
	public void on(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			Player p = e.getPlayer();
			if(p.getItemInHand().getType().equals(Material.INK_SACK)){
				
				
			YamlConfiguration cfg = configAPI.Playercfg(p);
			
			if(cfg.getString("PlayerHider").equals("Alle")){
				p.getInventory().setItem(8, new ItemBuilder(Material.INK_SACK, 1, 5).setName("§7Gezeigte Spieler§8: §5Freunde").build());
				
				cfg.set("PlayerHider", "Freunde");
				try {
					cfg.save(configAPI.Playerfile(p));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(Player a : Bukkit.getOnlinePlayers()){
					p.showPlayer(a);
					if(!cfg.getString("Freunde.list").contains(a.getUniqueId().toString())){
						p.hidePlayer(a);
					}
				}
				
			}else if(cfg.getString("PlayerHider").equals("Freunde")){
				p.getInventory().setItem(8, new ItemBuilder(Material.INK_SACK, 1, 1).setName("§7Gezeigte Spieler§8: §4Keine").build());
				cfg.set("PlayerHider", "Keine");
				try {
					cfg.save(configAPI.Playerfile(p));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(Player a : Bukkit.getOnlinePlayers()){
					p.hidePlayer(a);
				}

			}else if(cfg.getString("PlayerHider").equals("Keine")){
				p.getInventory().setItem(8, new ItemBuilder(Material.INK_SACK, 1, 10).setName("§7Gezeigte Spieler§8: §aAlle").build());
				cfg.set("PlayerHider", "Alle");
				try {
					cfg.save(configAPI.Playerfile(p));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				for(Player a : Bukkit.getOnlinePlayers()){
					p.showPlayer(a);
				}

				}
			}
			
		}
	}
	
}
