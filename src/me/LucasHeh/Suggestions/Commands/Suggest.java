package me.LucasHeh.Suggestions.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.LucasHeh.Suggestions.Main;
import me.LucasHeh.Suggestions.Suggestion;

public class Suggest implements CommandExecutor {
	
	public Suggest(Main main) {
		main.getCommand("suggest").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Must be a player");
			return true;
		}
		
		if(args.length == 0) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lPrimalMC » &cUsage: /suggestion <suggestion>"));
			return true;
		}
		
		Player p = (Player) sender;
		StringBuilder sb = new StringBuilder();
		for(String str : args) {
			sb.append(str);
			sb.append(" ");
		}
		String suggestionStr = sb.toString();
		Suggestion suggestion = new Suggestion(suggestionStr, p.getName());
		Main.getInstance().getUtils().getSuggestionList().add(suggestion);
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lPrimalMC » &7Suggestion added"));
		return true;
	}

}
