package de.rexlNico.Lobby.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.rexlNico.Lobby.Methodes.Factory;
import de.rexlNico.Lobby.Methodes.Var;
import de.rexlNico.Lobby.Methodes.configAPI;

public class SetMody implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.hasPermission(Var.perms+"admin")){
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("spawn")){
						p.sendMessage(Var.pr+"§cDu hast den §6Spawn §cgesetzt.");
						Factory.CreateConfigLocation(p.getLocation(), configAPI.locationcfg, configAPI.Locationfile, "Spawn");
					}else if(args[0].equalsIgnoreCase("Lom")){
						p.sendMessage(Var.pr+"§cDu hast den §6Lom §cgesetzt.");
						Factory.CreateConfigLocation(p.getLocation(), configAPI.locationcfg, configAPI.Locationfile, "Lom");
					}else if(args[0].equalsIgnoreCase("HideAndSeek")){
						p.sendMessage(Var.pr+"§cDu hast den §6HideAndSeek §cgesetzt.");
						Factory.CreateConfigLocation(p.getLocation(), configAPI.locationcfg, configAPI.Locationfile, "HaS");
					}else if(args[0].equalsIgnoreCase("TTT")){
						p.sendMessage(Var.pr+"§cDu hast den §6TTT §cgesetzt.");
						Factory.CreateConfigLocation(p.getLocation(), configAPI.locationcfg, configAPI.Locationfile, "TTT");
					}else if(args[0].equalsIgnoreCase("BW")){
						p.sendMessage(Var.pr+"§cDu hast den §6Bw §cgesetzt.");
						Factory.CreateConfigLocation(p.getLocation(), configAPI.locationcfg, configAPI.Locationfile, "BW");
					}else if(args[0].equalsIgnoreCase("SW")){
						p.sendMessage(Var.pr+"§cDu hast den §6Sw §cgesetzt.");
						Factory.CreateConfigLocation(p.getLocation(), configAPI.locationcfg, configAPI.Locationfile, "SW");
					}else if(args[0].equalsIgnoreCase("K-FFA")){
						p.sendMessage(Var.pr+"§cDu hast den §6K-FFA §cgesetzt.");
						Factory.CreateConfigLocation(p.getLocation(), configAPI.locationcfg, configAPI.Locationfile, "K-FFA");
					}else{
						p.sendMessage("§8------ §eMody §8------");
						p.sendMessage("§e/setmody Spawn");
						p.sendMessage("§e/setmody Lom");
						p.sendMessage("§e/setmody HideAndSeek");
						p.sendMessage("§e/setmody TTT");
						p.sendMessage("§e/setmody BW");
						p.sendMessage("§e/setmody SW");
						p.sendMessage("§e/setmody K-FFA");
						p.sendMessage("§e8----- §eMody §8------");
					}
				}else{
					p.sendMessage("§8------ §eMody §8------");
					p.sendMessage("§e/setmody Spawn");
					p.sendMessage("§e/setmody Lom");
					p.sendMessage("§e/setmody HideAndSeek");
					p.sendMessage("§e/setmody TTT");
					p.sendMessage("§e/setmody BW");
					p.sendMessage("§e/setmody SW");
					p.sendMessage("§e/setmody K-FFA");
					p.sendMessage("§e8----- §eMody §8------");
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
