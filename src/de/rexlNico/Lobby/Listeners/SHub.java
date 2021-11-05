package de.rexlNico.Lobby.Listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.rexlNico.Lobby.Methodes.ItemBuilder;

public class SHub implements Listener{

	public static ArrayList<Player> inShub = new ArrayList<>();
	
	@EventHandler
	public void on(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) ||
				e.getAction().equals(Action.RIGHT_CLICK_BLOCK) ||
				e.getAction().equals(Action.LEFT_CLICK_AIR) ||
				e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			
			Player p = e.getPlayer();
			
			if(p.getItemInHand().getType().equals(Material.TNT) && p.getItemInHand().getItemMeta().getDisplayName().equals("§3SilentHub §4Aus")){
				
				p.getInventory().setItem(1, new ItemBuilder(Material.TNT, 1).setName("§3SilentHub §aAn").enchant(Enchantment.ARROW_DAMAGE, 1).addFlags(ItemFlag.HIDE_ENCHANTS).build());
				inShub.add(p);
				p.removePotionEffect(PotionEffectType.BLINDNESS);
				p.removePotionEffect(PotionEffectType.NIGHT_VISION);
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 24, 20));
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 24, 20));
				p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 1, 1);
				for(Player a : Bukkit.getOnlinePlayers()){
					p.hidePlayer(a);
				}
				
			}else if(p.getItemInHand().getType().equals(Material.TNT) && p.getItemInHand().getItemMeta().getDisplayName().equals("§3SilentHub §aAn")){
				
				p.getInventory().setItem(1, new ItemBuilder(Material.TNT, 1).setName("§3SilentHub §4Aus").build());
				inShub.remove(p);
				p.removePotionEffect(PotionEffectType.BLINDNESS);
				p.removePotionEffect(PotionEffectType.NIGHT_VISION);
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 24, 20));
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 24, 20));
				p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 1, 20);
				for(Player a : Bukkit.getOnlinePlayers()){
					p.showPlayer(a);
				}
				
			}
			
		}
	}
	
}
