package me.LucasHeh.Suggestions.Inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import me.LucasHeh.Suggestions.Main;
import me.LucasHeh.Suggestions.Suggestion;
import me.LucasHeh.Suggestions.Utils;

public class MainInv implements Listener{
	
	private Main main = Main.getInstance();
	private Utils utils = main.getUtils();
	
	public MainInv(Player p) {
		openInv(p);
	}
	
	public void openInv(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54, ChatColor.AQUA+"Suggestions");
		int suggestionsLength = utils.getSuggestionList().size();
		if(suggestionsLength == 0) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lPrimalMC » &7There are no suggestions"));
			return;
		}
		for(int i=0; i<54; i++) {
			/*if(i<=suggestionsLength) {
				Suggestion suggestion = utils.getSuggestionList().get(i);
				List<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Player: " + ChatColor.YELLOW + suggestion.getPlayerWhoSuggested().getName());
				lore.add(" ");
				lore.add(ChatColor.GRAY + "Left-Click to remove Suggestion");
				utils.itemToInventory(Material.BOOK, ChatColor.GREEN + suggestion.getSuggestion(), lore, inv, i);
			} else {*/
				utils.itemToInventory(Material.BLACK_STAINED_GLASS_PANE, 
						" ", null, inv, i);
			//}
		}
		int x = 0;
		for(Suggestion suggestion : utils.getSuggestionList()) {
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Player: " + ChatColor.YELLOW + suggestion.getPlayerWhoSuggestedName());
			lore.add(" ");
			lore.add(ChatColor.GRAY + "Left-Click to remove Suggestion");
			utils.itemToInventory(Material.BOOK, ChatColor.GREEN + suggestion.getSuggestion(), lore, inv, x);
			x++;
		}
		p.openInventory(inv);
	}

}
