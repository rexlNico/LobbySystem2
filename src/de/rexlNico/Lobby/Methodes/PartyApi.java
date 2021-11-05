package de.rexlNico.Lobby.Methodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class PartyApi {

	public static ArrayList<Player> isinaParty = new ArrayList<>();
	public static HashMap<Player, ArrayList<Player>> partys = new HashMap<>();
	public static HashMap<Player, Player> request = new HashMap<>();
	
	public static boolean isPartyBesitzer(Player p){
		
		if(partys.containsKey(p)){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static void acceptPartyrequest(Player p, Player t){
		
		ArrayList<Player> partymember = partys.get(t);
		if(request.containsKey(p)){
			
			if(isInAParty(p)){
				p.sendMessage(Var.pr+"§cDu bist bereits in einer anderen §5Party.");
				return;
			}
			
			request.remove(p);
			isinaParty.add(p);
			partymember.add(p);
			
			partys.put(t, partymember);
			t.sendMessage(Var.pr+"§cDer Spieler §6"+p.getName()+" §cist der §5Party §cbeigetreten.");
			p.sendMessage(Var.pr+"§cDu bist der §5Party §cvon §6"+t.getName()+" §cbeigetreten.");
		}else{
			p.sendMessage(Var.pr+"§cDu hast kene einladung von §6"+t.getName()+" §cbekommen");
		}
		
	}
	
	public static void denyPartyrequest(Player p, Player t){
		
		if(request.containsKey(p)){
			request.remove(p);
			t.sendMessage(Var.pr+"§cDer Spieler §6"+p.getName()+" §chat deine §5Partyeinladung §4abgelehnt.");
			
			ArrayList<Player> partymember = partys.get(t);
			if(partymember.size() == 1){
				isinaParty.remove(t);
				t.sendMessage(Var.pr+"§cDie §5Party §cwurde wegen zuwenig Spielern aufgelöst.");
			}
			
		}else{
			p.sendMessage(Var.pr+"§cDu hast kene einladung von §6"+t.getName()+" §cbekommen");
		}
		
	}
	
	public static boolean isInParty(Player p){
		
		ArrayList<Player> partymember = partys.get(p);
		if(partymember.contains(p)){
			return true;
		}else{
			return false;
		}
		
	}
	public static boolean isInAParty(Player p){
		
		
		if(isinaParty.contains(p)){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static ArrayList<Player> PlayerInPartyList(Player p){
		ArrayList<Player> partymember = partys.get(p);
		
		return partymember;
	}
	
	public static String PlayerInPartyString(Player p){
		String player = PlayerInPartyList(p).get(0).getName();
		for(int i = 1; i< PlayerInPartyList(p).size(); i++){
			player = player + ", "+PlayerInPartyList(p).get(0).getName();
		}
		return player;
	}
	
	public static int getpartySize(Player p){
		ArrayList<Player> partymember = partys.get(p);
		
		
		return partymember.size();
	}

	public static void createParty(Player p, Player t){
		if(p == t){
			p.sendMessage(Var.pr+"§cDu kanst dich nicht in eine §5Party §ceinladen.");
			return;
		}
		ArrayList<Player> partymember = new ArrayList<>();
		
		if(isinaParty.contains(t)){
			p.sendMessage(Var.pr+"§cDer Spieler §6"+t.getName()+"§c ist in einer anderen §5Party.");
			return;
		}
		isinaParty.add(p);
		partymember.add(p);
		request.put(t, p);
		partys.put(p, partymember);
		TextComponent tc1 = new TextComponent();
		TextComponent tc2 = new TextComponent();
		TextComponent tc3 = new TextComponent();
		TextComponent tc4 = new TextComponent();
		tc1.setText("Annehmen");
		tc1.setBold(true);
		tc1.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/party accept "+p.getName()));
		tc1.setColor(ChatColor.GREEN);
		
		tc2.setText("Ablehnen");
		tc2.setBold(true);
		tc2.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/party deny "+p.getName()));
		tc2.setColor(ChatColor.DARK_RED);
		
		tc3.setText(Var.pr+"§cDu wurdest von §6"+p.getName()+" §cin eine §5Party §ceingeladen. ");
		tc3.setBold(true);
		
		tc4.setText(" §coder ");
		tc4.setBold(true);
		
		tc3.addExtra(tc1);
		tc3.addExtra(tc4);
		tc3.addExtra(tc2);
		
		t.spigot().sendMessage(tc3);
		
	}
	
	public static void addInParty(Player p, Player t){
		if(p == t){
			p.sendMessage(Var.pr+"§cDu kanst dich nicht in eine §5Party §ceinladen.");
			return;
		}
		if(!isPartyBesitzer(p)){
			p.sendMessage(Var.pr+"§cNur der Besitzer der §5Party §cdarf das.");
			return;
		}
		
		ArrayList<Player> partymember = partys.get(t);
		
		if(isinaParty.contains(t)){
			p.sendMessage(Var.pr+"§cDer Spieler §6"+t.getName()+"§c ist in einer anderen §5Party.");
			return;
		}
		isinaParty.add(p);
		request.put(t, p);
		TextComponent tc1 = new TextComponent();
		TextComponent tc2 = new TextComponent();
		TextComponent tc3 = new TextComponent();
		TextComponent tc4 = new TextComponent();
		tc1.setText("Annehmen");
		tc1.setBold(true);
		tc1.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/party accept "+p.getName()));
		tc1.setColor(ChatColor.GREEN);
		
		tc2.setText("Ablehnen");
		tc2.setBold(true);
		tc2.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/party deny "+p.getName()));
		tc2.setColor(ChatColor.DARK_RED);
		
		tc3.setText(Var.pr+"§cDu wurdest von §6"+p.getName()+" §cin eine §5Party §ceingeladen. ");
		tc3.setBold(true);
		
		tc4.setText(" §coder ");
		tc4.setBold(true);
		
		tc3.addExtra(tc1);
		tc3.addExtra(tc4);
		tc3.addExtra(tc2);
		
		t.spigot().sendMessage(tc3);
	}
	
	public static void removeFromParty(Player p, Player t){
		if(!isInAParty(p)){
			p.sendMessage(Var.pr+"§cDu bist in keiner §5Party.");
			return;
		}
		ArrayList<Player> partymember = partys.get(p);
		
		if(!partys.containsKey(p)){
			p.sendMessage(Var.pr+"§cNur der Besitzer der §5Party §cdarf das.");
			return;
		}
		
		if(partymember == null){
			if(!partys.containsKey(p)){
				p.sendMessage(Var.pr+"§cNur der Besitzer der §5Party §cdarf das.");
				return;
			}
			p.sendMessage(Var.pr+"§cDu bist in keiner §5Party.");
			return;
		}
		
		if(partymember.contains(t)){
			
			
		partymember.remove(t);
		isinaParty.remove(t);
		partys.remove(p);
		
		if(p == t){
			
			Random r = new Random();		
			if(partymember.isEmpty()){
				p.sendMessage(Var.pr+"§cDie §5Party §cwurde wegen zuwenig Spielern aufgelöst.");
			}else{
				Player pn = partymember.get(r.nextInt(partymember.size()));
				setPartyBesitzer(t, pn);
				partys.put(pn, partymember);
				pn.sendMessage(Var.pr+"§cDu bist der neue §5Partyleader.");
			}
			return;
		}
		
		
		p.sendMessage(Var.pr+"§cDu hast §6"+t.getName()+" §caus der §5Party §centfernt.");
		t.sendMessage(Var.pr+"§cDu wurdest aus der §5Party §cvon §6"+p.getName()+" §centfernt.");
		if(partymember.size() <= 1){
			p.sendMessage("");
			p.sendMessage(Var.pr+"§cDie §5Party §cwurde wegen zuwenig Spielern aufgelöst.");
			return;
		}
		partys.put(p, partymember);
		
		}else{
			p.sendMessage(Var.pr+"§cDer Spieler §6"+t.getName()+"§c ist nicht in der §5Party.");
		}
	}
	
	public static void setPartyBesitzer(Player p, Player t){
		if(!isPartyBesitzer(p)){
			p.sendMessage(Var.pr+"§cNur der Besitzer der §5Party §cdarf das.");
			return;
		}
		ArrayList<Player> partymember = partys.get(p);
		if(isInParty(t)){
			
			partys.remove(p);
			partys.put(t, partymember);
			
		}else{
			p.sendMessage(Var.pr+"§cDer Spieler §6"+t.getName()+"§c ist nicht in der §5Party.");
		}
		
	}
	
}
