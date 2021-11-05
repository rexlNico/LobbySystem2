package de.rexlNico.Lobby.Methodes;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Var {

	public static String pr = "§8» §brexlNico.de §8« ● ",
			noperm = pr+"§cDazu hast du keine Rechte!",
			error = pr+"Bitte nutze §c/",
			console = "§4Das darf nur ein Spieler ausführen.",
			perms = "lobby.";
	
	public static HashMap<String, Location> spawns = new HashMap<>();
	
	public static void sendActionbar(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" +
                ChatColor.translateAlternateColorCodes('&', message) + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
            
    }
	
}
