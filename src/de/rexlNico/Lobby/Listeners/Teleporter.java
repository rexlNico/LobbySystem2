package de.rexlNico.Lobby.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import de.rexlNico.Lobby.Methodes.ItemBuilder;
import de.rexlNico.Lobby.Methodes.Var;

public class Teleporter implements Listener{

	private Inventory inv = Bukkit.createInventory(null, 9*5, "§6Teleporter");
	
	@EventHandler
	public void on(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) 
				|| e.getAction().equals(Action.RIGHT_CLICK_BLOCK) 
				|| e.getAction().equals(Action.LEFT_CLICK_AIR) 
				|| e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			
			Player p = e.getPlayer();
			
			if(p.getItemInHand().getType().equals(Material.COMPASS) && p.getItemInHand().getItemMeta().getDisplayName().equals("§6Teleporter")){
				
				inv.setItem(2, new ItemBuilder(Material.HAY_BLOCK, 1).setName("§eHide and Seek").build());
				inv.setItem(6, new ItemBuilder(Material.STICK, 1).setName("§5Knockback FFA").build());
				inv.setItem(8+1, new ItemBuilder(Material.GRASS, 1).setName("§6SkyWars").build());
				inv.setItem(8+9, new ItemBuilder(Material.BED, 1).setName("§4Bed§fWars").build());
				inv.setItem(8+9+5, new ItemBuilder(Material.EYE_OF_ENDER, 1).setName("§cSpawn").enchant(Enchantment.ARROW_DAMAGE, 1).addFlags(ItemFlag.HIDE_ENCHANTS).build());
				inv.setItem(8+9+9+1, new ItemBuilder(Material.STONE_SWORD, 1).setName("§4TTT").addFlags(ItemFlag.HIDE_ATTRIBUTES).build());
				inv.setItem(8+9+9+9, new ItemBuilder(Material.BOOK_AND_QUILL, 1).setName("§9League of Minecraft").build());
				inv.setItem(8+9+9+9+3, new ItemBuilder(Material.BARRIER, 1).setName("§4???").build());
				inv.setItem(8+9+9+9+7, new ItemBuilder(Material.BARRIER, 1).setName("§4???").build());
				
				
				p.openInventory(inv);
				
			}
		}
	}
	
	@EventHandler
	public void on(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getName().equals("§6Teleporter")){
			e.setCancelled(true);
			
			if(e.getCurrentItem().getType().equals(Material.HAY_BLOCK)){
				try {
					p.teleport(Var.spawns.get("HaS"));
				} catch (Exception e2) {
					p.sendMessage(Var.pr+"§4ERROR §cBitte melden!");
				}
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
				
			}else if(e.getCurrentItem().getType().equals(Material.STICK)){
				
				try {
					p.teleport(Var.spawns.get("KFFA"));
				} catch (Exception e2) {
					p.sendMessage(Var.pr+"§4ERROR §cBitte melden!");
				}
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
				
			}else if(e.getCurrentItem().getType().equals(Material.GRASS)){
				
				try {
				p.teleport(Var.spawns.get("SkyWars"));
			} catch (Exception e2) {
				p.sendMessage(Var.pr+"§4ERROR §cBitte melden!");
			}
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
				
			}else if(e.getCurrentItem().getType().equals(Material.BED)){
				
				try {
				p.teleport(Var.spawns.get("BedWars"));
			} catch (Exception e2) {
				p.sendMessage(Var.pr+"§4ERROR §cBitte melden!");
			}
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
				
			}else if(e.getCurrentItem().getType().equals(Material.EYE_OF_ENDER)){
				
				try {
				p.teleport(Var.spawns.get("Spawn"));
			} catch (Exception e2) {
				p.sendMessage(Var.pr+"§4ERROR §cBitte melden!");
			}
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
				
			}else if(e.getCurrentItem().getType().equals(Material.STONE_SWORD)){
				
				try {
				p.teleport(Var.spawns.get("TTT"));
			} catch (Exception e2) {
				p.sendMessage(Var.pr+"§4ERROR §cBitte melden!");
			}
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
				
			}else if(e.getCurrentItem().getType().equals(Material.BOOK_AND_QUILL)){
				
				try {
				p.teleport(Var.spawns.get("LoM"));
			} catch (Exception e2) {
				p.sendMessage(Var.pr+"§4ERROR §cBitte melden!");
			}
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
				
			}
		}
		if(!BuildBreak.canbuild.contains(p)){
			e.setCancelled(true);
		}
	}
	
	
}
