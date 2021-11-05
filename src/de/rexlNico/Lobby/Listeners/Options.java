package de.rexlNico.Lobby.Listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import de.rexlNico.Lobby.Main.Main;
import de.rexlNico.Lobby.Methodes.ItemBuilder;
import de.rexlNico.Lobby.Methodes.Var;
import de.rexlNico.Lobby.Methodes.configAPI;

public class Options implements Listener {

	private Inventory inv = Bukkit.createInventory(null, InventoryType.BREWING, "§5Options");
	
	@EventHandler
	public void on(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)
				|| e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

			Player p = e.getPlayer();

			if (p.getItemInHand().getType().equals(Material.CHEST)
					&& p.getItemInHand().getItemMeta().getDisplayName().equals("§5Options")) {

				inv.setItem(0, new ItemBuilder(Material.SKULL_ITEM, 1, 3).setName("§5Freunde")
						.setSkullOwner(p.getName()).build());
				inv.setItem(1, new ItemBuilder(Material.PAPER, 1).setName("§eStats").build());
				inv.setItem(2, new ItemBuilder(Material.GOLD_INGOT, 1).setName("§6Premium").build());
				inv.setItem(3, new ItemBuilder(Material.NETHER_STAR, 1).setName("§blobbys").build());

				p.openInventory(inv);

			}
		}
	}
	
	private String server = Main.server;
	
	@EventHandler
	public void on(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equals("§5Options")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(Material.SKULL_ITEM) || e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Freunde")) {
				List<String> freunde = new ArrayList<>(Arrays.asList(configAPI.Playercfg(p).getString("").split(" , ")));
				Inventory in = Bukkit.createInventory(null, 9 * 6, "§5Freunde");
				for (int i = 9 * 4; i < 9 * 5 + 8; i++) {
					in.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 15).build());
				}
				if (freunde.isEmpty()) {
					p.openInventory(in);
					return;
				}
				for (int i = 0; i < freunde.size(); i++) {
					if (i <= 9 * 3 + 8) {
						Player f = Bukkit.getPlayer(freunde.get(i));
						if(f == null){
							OfflinePlayer of = Bukkit.getOfflinePlayer(freunde.get(i));
							in.addItem(new ItemBuilder(Material.SKULL_ITEM, 1, 3).setSkullOwner(of.getName()).setName(of.getName()).build());
						}else{
							in.addItem(new ItemBuilder(Material.SKULL_ITEM, 1, 3).setSkullOwner(f.getName()).setName(f.getName()).build());
						}
					}
				}
				p.openInventory(in);
			}else if(e.getCurrentItem().getType().equals(Material.PAPER)) {
				
			}else if(e.getCurrentItem().getType().equals(Material.GOLD_INGOT)) {
				
			}else if(e.getCurrentItem().getType().equals(Material.NETHER_STAR)){
				Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "§bLobbys");
				inv.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 15).setName("").build());
				inv.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 15).setName("").build());
				inv.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 15).setName("").build());
				
				
				
				if(server.equalsIgnoreCase("lobby-01")){
					inv.setItem(1, new ItemBuilder(Material.STAINED_CLAY, 1, 11).setName("§bLobby-01").enchant(Enchantment.ARROW_DAMAGE, 1).addFlags(ItemFlag.HIDE_ENCHANTS).build());
					inv.setItem(3, new ItemBuilder(Material.STAINED_CLAY, 1, 3).setName("§bLobby-02").build());
				}else{
					inv.setItem(1, new ItemBuilder(Material.STAINED_CLAY, 1, 3).setName("§bLobby-01").build());
					inv.setItem(3, new ItemBuilder(Material.STAINED_CLAY, 1, 11).setName("§bLobby-02").enchant(Enchantment.ARROW_DAMAGE, 1).addFlags(ItemFlag.HIDE_ENCHANTS).build());
				}
			}
		}
	}

}
