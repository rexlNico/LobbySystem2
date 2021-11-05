package de.rexlNico.Lobby.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.rexlNico.Lobby.Listeners.BuildBreak;
import de.rexlNico.Lobby.Methodes.ItemBuilder;
import de.rexlNico.Lobby.Methodes.Var;

public class Build implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			
			if(p.hasPermission(Var.perms+"builder") || p.hasPermission(Var.perms+"admin") || p.hasPermission(Var.perms+"build")){
				
				if(args.length == 0){
					
					if(BuildBreak.canbuild.contains(p)){
						Var.sendActionbar(p, "§bBaumodus §4aus");
						BuildBreak.canbuild.remove(p);
					}else{
						BuildBreak.canbuild.add(p);
						Var.sendActionbar(p, "§bBaumodus §aAn");
					}
					
				}else if(args.length == 1){
					
					Player pl = Bukkit.getPlayer(args[0]);
					if(pl == null){
						p.sendMessage(Var.pr+"§cDer Spieler ist nicht online.");
					}else{
						if(BuildBreak.canbuild.contains(pl)){
							Var.sendActionbar(pl, "§bBaumodus §aAn");
							p.sendMessage(Var.pr+"§bDer Spieler §6"+pl.getName()+" §bkann §aBauen");
							BuildBreak.canbuild.remove(p);
						}else{
							BuildBreak.canbuild.add(pl);
							p.sendMessage(Var.pr+"§bDer Spieler §6"+pl.getName()+" §bkann nicht §cBauen");
							Var.sendActionbar(pl, "§bBaumodus §4Aus");
						}
					}
					
				}else{
					p.sendMessage(Var.error+"build <Player>");
				}
				
			}else{
				p.sendMessage(Var.noperm);
			}
			
		}else{
			sender.sendMessage(Var.console);
		}
		return false;
	}

}
