package de.rexlNico.Lobby.Methodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;

public class NickAPI {

	public static ArrayList<String> nicks = new ArrayList<>();
	public static ArrayList<String> usedNicks = new ArrayList<>();
	public static Random r = new Random();
	
	public static void setAutoNick(Player p, boolean b){
		configAPI.Playercfg(p).set("AutoNick", b);
		try {
			configAPI.Playercfg(p).save(configAPI.Playerfile(p));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void nickPlayer(Player p){
		
		int ri = r.nextInt(nicks.size());
		
		String name = nicks.get(ri);
		
		p.setDisplayName(name);
		p.setPlayerListName(name);
		usedNicks.add(name);
		nicks.remove(name);
	}
	
	public static void nickToList(){
		
		nicks.add("Gosmo");
		nicks.add("_byNoah_");
		nicks.add("EntBoy");
		nicks.add("MyGames");
		nicks.add("FNOMA");
		nicks.add("bene20");
		nicks.add("clay_lp_");
		nicks.add("xFunkeZ");
		nicks.add("IBims1Leon");
		nicks.add("Timboxyz");
		nicks.add("Datin");
		nicks.add("IchHabeEinBrot");
		nicks.add("jillipili");
		nicks.add("Bauer100");
		nicks.add("SladeHD");
		nicks.add("LinoKing");
		nicks.add("28ms");
		nicks.add("KeinWunsch");
		nicks.add("marcxgaming");
		nicks.add("GameHTV");
		nicks.add("IchbinLara");
		nicks.add("Mrs_Miimii");
		nicks.add("Rainbow_Girl_");
		nicks.add("FlauschigerToast");
		
	}
	
}
