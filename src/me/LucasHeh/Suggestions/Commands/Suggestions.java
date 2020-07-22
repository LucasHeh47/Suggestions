package me.LucasHeh.Suggestions.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.LucasHeh.Suggestions.Main;
import me.LucasHeh.Suggestions.Inventory.MainInv;

public class Suggestions implements CommandExecutor {
	
	public Suggestions(Main main) {
		main.getCommand("suggestions").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player!");
			return true;
		}
		if(!sender.hasPermission("suggestions.open")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lPrimalMC » &cYou do not have permission!"));
			return true;
		}
		
		Player p = (Player) sender;
		new MainInv(p);
		
		return false;
	}

}
